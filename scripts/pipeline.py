"""One chain = (class, model, seed). Produces rows for configs <M>-ZS, <M>-R, <M>-RC.

T0 generation is shared by the three configs of a chain; ZS prunes T0, R repairs T0,
RC continues from the green R suite with coverage-guided refinement (paired ablation)."""
import json, pathlib, re, time, traceback, copy
import javatools as jt
from harness_ops import Harness
from llm import LLM

ROOT = pathlib.Path(__file__).resolve().parent.parent
PROMPTS = {k: (ROOT / "prompts" / f"{k}.txt").read_text() for k in ("system", "generate", "repair", "refine")}
if (ROOT / "prompts" / "refine_blind.txt").exists():  # post-hoc control: refinement without coverage report
    PROMPTS["refine_blind"] = (ROOT / "prompts" / "refine_blind.txt").read_text()


def fill(tpl, **kw):
    for k, v in kw.items():
        tpl = tpl.replace("{" + k + "}", str(v))
    return tpl


class Art:
    """Artifact writer for one (class, config, run) directory."""
    def __init__(self, d):
        self.d = pathlib.Path(d)
        self.d.mkdir(parents=True, exist_ok=True)
        self.i = 0

    def put(self, name, data):
        self.i += 1
        p = self.d / f"{self.i:02d}_{name}"
        if isinstance(data, bytes):
            p.write_bytes(data)
        else:
            p.write_text(data if isinstance(data, str) else json.dumps(data, indent=2), encoding="utf-8")


def pct(x):
    return 'n/a' if x is None else f'{100 * x:.1f}%'


def fmt_errors(errs, code, limit=30, by_method=False):
    src_lines = code.splitlines()
    ms = jt.methods(code)
    out = []
    for e in errs:
        if not e["in_test"]:
            continue
        ln = e["line"]
        owner = next((m["name"] for m in ms if m["start_line"] <= ln <= m["end_line"]), None)
        if by_method:
            s = (f"in method {owner}: " if owner else "outside methods: ") + e["msg"]
        else:
            s = f"line {ln}: {e['msg']}"
        if e["detail"]:
            s += " (" + "; ".join(e["detail"][:3]) + ")"
        if 0 < ln <= len(src_lines):
            s += f"\n    code: {src_lines[ln - 1].strip()}"
        out.append(s)
    return "\n".join(out[:limit])


def new_only_view(code, base_names, test_class):
    """Imports + a class containing only the methods not in base_names (Stage-5 repair input)."""
    src = code.encode("utf-8")
    from jparse import parse, txt
    root = parse(src).root_node
    head = [txt(c, src) for c in root.children if c.type in ("package_declaration", "import_declaration")]
    tops = [c for c in root.children if c.type == "class_declaration"]
    fields = [txt(n, src) for n in (tops[0].child_by_field_name("body").named_children if tops else [])
              if n.type == "field_declaration"]
    body = fields + [m["text"] for m in jt.methods(code) if m["name"] not in base_names]
    return "\n".join(head) + f"\n\nclass {test_class} {{\n\n    " + "\n\n    ".join(body) + "\n}\n"


class Chain:
    def __init__(self, cfg, cls, model_key, seed, harness_dir, host, art_root):
        self.cfg, self.fqn, self.mk, self.seed = cfg, cls, model_key, seed
        self.pkg, self.simple = cls.rsplit(".", 1)
        self.tc = f"{self.simple}_GenTest"
        self.model_id = cfg["models"][model_key]["tag"]
        self.h = Harness(harness_dir, pit_threads=cfg["pit_threads"])
        self.llm = LLM(host[model_key], self.model_id, cfg["temperature"], cfg["max_tokens"], cfg["num_ctx"], seed)
        self.art_root = pathlib.Path(art_root)
        src_path = self.h.root / "src/main/java" / (cls.replace(".", "/") + ".java")
        self.src_text = src_path.read_text(encoding="utf-8")
        self.src_lines = self.src_text.splitlines()
        self.ctx, self.ctx_info = jt.extract_context(self.src_text, cfg["context_budget_tokens"])

    def art(self, config):
        return Art(self.art_root / self.simple / config / f"run{self.seed}")

    # ------------------------------------------------------------ LLM steps
    def ask(self, kind, user, arts, tag):
        for a in arts:
            a.put(f"{tag}_prompt.txt", "SYSTEM:\n" + PROMPTS["system"] + "\nUSER:\n" + user)
        resp, rec = self.llm.chat(PROMPTS["system"], user, tag)
        for a in arts:
            a.put(f"{tag}_response.txt", resp)
            a.put(f"{tag}_meta.json", rec)
        return resp, rec

    # ------------------------------------------------------------ Stage 3
    def compile_stage(self, code, repair, art, st, protect=None, k=None):
        """Compile; optional LLM repair rounds; then method-level pruning.
        protect: set of method names that must be kept as in `base` (Stage-5: only new methods are touched).
        Returns compiled code or None."""
        k = self.cfg["K_comp"] if k is None else k
        base = protect
        rounds = 0
        self.h.write_test(self.fqn, code)
        ok, errs, out = self.h.compile(self.fqn)
        art.put("compile.log", out)
        art.put("test_after_compile.java", code)
        st["compile_logs"].append(out)
        while not ok and repair and rounds < k:
            if not any(e["in_test"] for e in errs):
                break
            rounds += 1
            if base is None:
                shown, errtxt = code, fmt_errors(errs, code)
            else:  # Stage 5: only the new methods are shown and repaired
                shown, errtxt = new_only_view(code, base["names"], self.tc), fmt_errors(errs, code, by_method=True)
            user = fill(PROMPTS["repair"], test_class=self.tc, simple_name=self.simple, fqn=self.fqn,
                        errors=errtxt, test_code=shown, context=self.ctx)
            resp, rec = self.ask("repair", user, [art], f"repair{rounds}")
            new, unterminated = jt.extract_code_block(resp)
            if new is None:
                st["notes"].append(f"repair{rounds}: no code block")
                continue
            new, nn = jt.normalize_test_class(new, self.pkg, self.tc)
            if base is not None:
                # Stage-5 repair: keep old methods verbatim, take only new methods from the response
                new, _, _ = jt.merge_new_methods(base["code"], jt.remove_methods_by_name(new, base["names"]), self.tc)
            code = new
            self.h.write_test(self.fqn, code)
            ok, errs, out = self.h.compile(self.fqn)
            art.put(f"repair{rounds}_compile.log", out)
            art.put(f"repair{rounds}_test.java", code)
            st["compile_logs"].append(out)
        st["repair_rounds_used"] += rounds
        # pruning (fixpoint, max 5 passes)
        passes = 0
        while not ok and passes < 5:
            lines = [e["line"] for e in errs if e["in_test"]]
            if not lines:
                break
            if base is not None:
                # never prune protected (already green) methods
                prot = {m["name"] for m in jt.methods(code) if m["name"] in base["names"]}
                pruned, removed, unresolved = jt.prune_error_lines(code, lines)
                if prot - {m["name"] for m in jt.methods(pruned)}:
                    st["notes"].append("stage5 prune touched protected methods; reverting to base")
                    code = base["code"]
                    self.h.write_test(self.fqn, code)
                    ok, errs, out = self.h.compile(self.fqn)
                    break
            else:
                pruned, removed, unresolved = jt.prune_error_lines(code, lines)
            passes += 1
            if pruned == code:
                break
            code = pruned
            self.h.write_test(self.fqn, code)
            ok, errs, out = self.h.compile(self.fqn)
            art.put(f"prune{passes}_compile.log", out)
            art.put(f"prune{passes}_test.java", code)
            st["compile_logs"].append(out)
        st["prune_passes"] += passes
        return code if ok else None

    # ------------------------------------------------------------ Stage 4
    def green_stage(self, code, art, st, base_names=None):
        """Remove failing tests, then flaky ones (2 re-runs). Returns green code (may have 0 tests).
        base_names (Stage 5): if the test JVM produces no report, only the new methods are discarded
        (returns None so the caller keeps the previous green suite)."""
        self.h.write_test(self.fqn, code)
        status, out = self.h.run_tests(self.fqn)
        art.put("run1.log", out)
        names = jt.test_method_names(code)
        if status is None and base_names is not None:
            new = [n for n in names if n not in base_names]
            st["notes"].append("stage5: no surefire report; new methods discarded")
            st["removed_failing"] += len(new)
            return None
        if status is None:
            st["notes"].append("no surefire report; all tests treated as failing")
            status = {n: "fail" for n in names}
        failing = [n for n in names if status.get(n) != "pass"]
        st["removed_failing"] += len(failing)
        if failing:
            code = jt.remove_methods_by_name(code, failing)
            code = self._recompile_after_removal(code, art, st)
            if code is None:
                return None
        for it in range(3):
            if jt.count_tests(code) == 0:
                break
            self.h.write_test(self.fqn, code)
            s1, o1 = self.h.run_tests(self.fqn)
            s2, o2 = self.h.run_tests(self.fqn)
            art.put(f"flaky{it}_runA.log", o1)
            art.put(f"flaky{it}_runB.log", o2)
            names = jt.test_method_names(code)
            flaky = [n for n in names if (s1 or {}).get(n) != "pass" or (s2 or {}).get(n) != "pass"]
            if not flaky:
                break
            st["removed_flaky"] += len(flaky)
            code = jt.remove_methods_by_name(code, flaky)
            code = self._recompile_after_removal(code, art, st)
            if code is None:
                return None
        art.put("test_green.java", code)
        return code

    def _recompile_after_removal(self, code, art, st):
        self.h.write_test(self.fqn, code)
        ok, errs, out = self.h.compile(self.fqn)
        if ok:
            return code
        st["notes"].append("compile broke after removing failing tests; pruning")
        return self.compile_stage(code, False, art, st)

    # ------------------------------------------------------------ Stage 5
    def coverage_stage(self, code, art, st, deadline_cov):
        cov = self.measure_cov(code, art, "cov_round0")
        for r in range(1, self.cfg["K_cov"] + 1):
            if cov is None:
                break
            if cov["branch_cov"] is not None and cov["branch_cov"] >= self.cfg["branch_stop"]:
                st["notes"].append(f"cov stop: branch>={self.cfg['branch_stop']}")
                break
            st["cov_rounds_used"] += 1
            snippets = self.snippets(cov["lines"])
            existing = ", ".join(jt.test_method_names(code)) or "(none)"
            user = fill(PROMPTS[getattr(self, "refine_key", "refine")], test_class=self.tc, simple_name=self.simple, fqn=self.fqn,
                        package=self.pkg, line_cov=pct(cov["line_cov"]), branch_cov=pct(cov["branch_cov"]),
                        snippets=snippets, existing=existing, context=self.ctx)
            resp, rec = self.ask("refine", user, [art], f"refine{r}")
            add, _ = jt.extract_code_block(resp)
            if add is None:
                st["notes"].append(f"refine{r}: no code block")
                prev_bc = cov["branch_covered"]
                break
            merged, new_tests, nchunks = jt.merge_new_methods(code, add, self.tc)
            st["tests_generated"] += len(new_tests)
            art.put(f"refine{r}_merged.java", merged)
            base = {"code": code, "names": {m["name"] for m in jt.methods(code)}}
            comp = self.compile_stage(merged, True, art, st, protect=base)
            if comp is None:
                comp = code
            st["tests_compiled"] += max(0, jt.count_tests(comp) - jt.count_tests(code))
            green = self.green_stage(comp, art, st, base_names=base["names"])
            if green is None:
                green = code
            new_cov = self.measure_cov(green, art, f"cov_round{r}")
            gained = (new_cov["branch_covered"] - cov["branch_covered"]) if new_cov and cov else 0
            code, cov = green, new_cov
            if gained <= 0:
                st["notes"].append(f"cov stop: round {r} added no covered branch")
                break
        return code

    def measure_cov(self, code, art, tag):
        self.h.write_test(self.fqn, code)
        status, out = self.h.run_tests(self.fqn, with_coverage=True)
        art.put(f"{tag}_run.log", out)
        cov = self.h.jacoco(self.fqn)
        if cov is not None:
            art.put(f"{tag}_jacoco.xml", cov["xml"])
        return cov

    def snippets(self, lines):
        marks = {}
        for nr, mi, ci, mb, cb in lines:
            if ci == 0 and mi > 0:
                marks[nr] = "NOT COVERED"
            elif mb > 0:
                marks[nr] = f"PARTIAL ({mb} of {mb + cb} branches missed)"
        sel = sorted(marks)[: self.cfg["max_uncovered_lines"]]
        show = set()
        for n in sel:
            show |= set(range(max(1, n - 2), min(len(self.src_lines), n + 2) + 1))
        out, prev = [], None
        for n in sorted(show):
            if prev is not None and n != prev + 1:
                out.append("    ...")
            tag = f"  // <-- {marks[n]}" if n in marks and n in sel else ""
            out.append(f"{n:5d}: {self.src_lines[n - 1]}{tag}")
            prev = n
        return "\n".join(out)

    # ------------------------------------------------------------ Stage 6
    def final(self, code, art, row):
        n = jt.count_tests(code) if code else 0
        if not code or n == 0:
            code = (f"package {self.pkg};\n\nimport org.junit.jupiter.api.Test;\n\n"
                    f"class {self.tc} {{\n    @Test\n    void placeholderNoGeneratedTests() {{ }}\n}}\n")
            row["notes"].append("empty suite: placeholder test used for measurement")
        art.put("final_test.java", code)
        cov = self.measure_cov(code, art, "final")
        if cov is None:
            row["notes"].append("jacoco: target class not in report")
            row.update(line_cov=None, branch_cov=None)
        else:
            row.update(line_cov=cov["line_cov"], branch_cov=cov["branch_cov"],
                       line_covered=cov["line_covered"], line_total=cov["line_covered"] + cov["line_missed"],
                       branch_covered=cov["branch_covered"],
                       branch_total=cov["branch_covered"] + cov["branch_missed"])
            if n == 0:
                row.update(line_cov=0.0 if cov["line_cov"] is not None else None,
                           branch_cov=0.0 if cov["branch_cov"] is not None else None)
        pit, out = self.h.pit(self.fqn)
        art.put("pit.log", out)
        if pit is None:
            row["notes"].append("PIT: no mutations.xml")
        else:
            art.put("mutations.xml", pit.pop("xml"))
            pit.pop("status_counts")
            row.update(pit)

    # ------------------------------------------------------------ main
    def _snap(self):
        d = self.llm.snapshot()
        d["t"], d["b"] = time.time(), self.h.build_time
        return d

    @staticmethod
    def _seg(a, b):
        return {k: b[k] - a[k] for k in a}

    @staticmethod
    def _add(*segs):
        return {k: sum(s[k] for s in segs) for k in segs[0]}

    def _cost_fields(self, seg):
        return {"llm_calls": seg["llm_calls"], "tokens_in": seg["tokens_in"], "tokens_out": seg["tokens_out"],
                "est_cost_usd": 0.0, "wall_time_llm_s": round(seg["wall_time_llm_s"], 2),
                "wall_time_build_s": round(seg["b"], 2), "wall_time_total_s": round(seg["t"], 2)}

    def run(self):
        """Cost of a config = T0 segment + its own stage segments + its final measurement.
        RC = T0 + R stages + RC stages + RC final (R's final measurement excluded)."""
        mk = self.mk
        arts = {c: self.art(f"{mk}-{c}") for c in ("ZS", "R", "RC")}
        for a in arts.values():
            a.put("context.java", self.ctx)
            a.put("context_info.json", self.ctx_info)
        s0 = self._snap()
        user = fill(PROMPTS["generate"], test_class=self.tc, package=self.pkg, simple_name=self.simple,
                    fqn=self.fqn, context=self.ctx)
        resp, rec = self.ask("generate", user, list(arts.values()), "T0")
        code0, unterminated = jt.extract_code_block(resp)
        base_notes = []
        if self.ctx_info["n_truncated_to_signature"]:
            base_notes.append(f"context truncated: {self.ctx_info['n_truncated_to_signature']} methods signature-only")
        if rec.get("done_reason") == "length":
            base_notes.append("T0 hit max_tokens")
        gen_ok = code0 is not None
        if gen_ok:
            code0, nn = jt.normalize_test_class(code0, self.pkg, self.tc)
            base_notes += nn
            if unterminated:
                base_notes.append("T0 code block unterminated")
        else:
            base_notes.append("generation failure: no code block")
        for a in arts.values():
            a.put("T0_extracted.java", code0 or "")
        n_gen = jt.count_tests(code0) if gen_ok else 0
        seg_t0 = self._seg(s0, self._snap())

        def new_state():
            return {"tests_generated": n_gen, "tests_compiled": 0, "removed_failing": 0, "removed_flaky": 0,
                    "repair_rounds_used": 0, "cov_rounds_used": 0, "prune_passes": 0,
                    "notes": list(base_notes), "compile_logs": []}

        def finish(config, st, code, stage_segs):
            a = arts[config]
            row = {"class": self.fqn, "config": f"{mk}-{config}", "model_id": self.model_id,
                   "run_seed": self.seed, "gen_ok": gen_ok}
            row.update({k: st[k] for k in ("tests_generated", "tests_compiled", "removed_failing",
                                           "removed_flaky", "repair_rounds_used", "cov_rounds_used",
                                           "prune_passes")})
            row["notes"] = st["notes"]
            row["tests_passing"] = jt.count_tests(code) if code else 0
            f0 = self._snap()
            self.final(code, a, row)
            seg_final = self._seg(f0, self._snap())
            row.update(self._cost_fields(self._add(seg_t0, *stage_segs, seg_final)))
            row["notes"] = "; ".join(row["notes"])
            a.put("all_compile_logs.txt", "\n\n=====\n\n".join(st["compile_logs"]))
            a.put("row.json", row)
            return row

        rows, keep = [], {}
        for config, repair in (("ZS", False), ("R", True)):
            st = new_state()
            s1 = self._snap()
            code = None
            if gen_ok:
                code = self.compile_stage(code0, repair, arts[config], st)
                st["tests_compiled"] = jt.count_tests(code) if code else 0
                if code is not None:
                    code = self.green_stage(code, arts[config], st)
            seg = self._seg(s1, self._snap())
            keep[config] = (copy.deepcopy(st), code, seg)
            rows.append(finish(config, st, code, [seg]))
        st_r, code_r, seg_r = keep["R"]
        st = copy.deepcopy(st_r)
        st["compile_logs"] = []
        s2 = self._snap()
        code = code_r
        if code is not None and jt.count_tests(code) > 0:
            code = self.coverage_stage(code, arts["RC"], st, None)
        else:
            st["notes"].append("RC: no green R suite to refine")
        seg_rc = self._seg(s2, self._snap())
        rows.append(finish("RC", st, code, [seg_r, seg_rc]))
        return rows
