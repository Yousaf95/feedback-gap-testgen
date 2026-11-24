"""Post-hoc control for review point A-03: refinement WITHOUT coverage information (configs S-RN, L-RN).

Each control run starts from exactly the same green R suite as RC (the saved final suite of <M>-R/run<seed>)
and runs the same Stage 5 loop (same K_cov rounds, same compile/repair/green steps, same existing-test names,
same class context, same seed, same stopping rule; coverage is measured for the stopping rule but never shown
to the model). The only difference is the prompt: prompts/refine_blind.txt instead of prompts/refine.txt.

Cost fields are cumulative like RC: LLM calls/tokens = R row (T0 + R stages) + control stage;
wall_time_total_s = R row total (which includes R's final measurement) + control stage + control final.
The *_stage fields hold the control stage alone.

Usage: python run_control.py --config C --models S,L --seeds 1,2,3 --workers N --runs results/raw/runs.jsonl
                             --out results/raw/runs_control.jsonl --art results/artifacts --host S=h:p,L=h:p"""
import argparse, json, os, pathlib, sys, time, traceback, copy
from concurrent.futures import ProcessPoolExecutor, as_completed

sys.path.insert(0, str(pathlib.Path(__file__).parent))
ROOT = pathlib.Path(__file__).resolve().parent.parent


def one(args):
    cfg, fqn, mk, seed, hdir, hosts, art_root, rrow = args
    import javatools as jt
    from pipeline import Chain
    t = time.time()
    try:
        ch = Chain(cfg, fqn, mk, seed, hdir, hosts, art_root)
        ch.refine_key = "refine_blind"
        simple = fqn.rsplit(".", 1)[1]
        art = ch.art(f"{mk}-RN")
        art.put("context.java", ch.ctx)
        rdir = pathlib.Path(art_root) / simple / f"{mk}-R" / f"run{seed}"
        base = sorted(rdir.glob("*_final_test.java"))[-1].read_text()
        st = {k: rrow[k] for k in ("tests_generated", "tests_compiled", "removed_failing", "removed_flaky",
                                   "repair_rounds_used", "prune_passes")}
        st.update(cov_rounds_used=0, notes=[n for n in rrow["notes"].split("; ") if n and "placeholder" not in n],
                  compile_logs=[])
        code = base if rrow["tests_passing"] > 0 else None
        s0 = ch._snap()
        if code is not None and jt.count_tests(code) > 0:
            art.put("start_suite_from_R.java", code)
            code = ch.coverage_stage(code, art, st, None)
        else:
            st["notes"].append("RN: no green R suite to refine")
            code = None
        seg = ch._seg(s0, ch._snap())
        row = {"class": fqn, "config": f"{mk}-RN", "model_id": ch.model_id, "run_seed": seed, "gen_ok": rrow["gen_ok"]}
        row.update({k: st[k] for k in ("tests_generated", "tests_compiled", "removed_failing", "removed_flaky",
                                       "repair_rounds_used", "cov_rounds_used", "prune_passes")})
        row["notes"] = st["notes"]
        row["tests_passing"] = jt.count_tests(code) if code else 0
        f0 = ch._snap()
        ch.final(code, art, row)
        segf = ch._seg(f0, ch._snap())
        row.update(llm_calls=rrow["llm_calls"] + seg["llm_calls"], tokens_in=rrow["tokens_in"] + seg["tokens_in"],
                   tokens_out=rrow["tokens_out"] + seg["tokens_out"], est_cost_usd=0.0,
                   wall_time_llm_s=round(rrow["wall_time_llm_s"] + seg["wall_time_llm_s"], 2),
                   wall_time_build_s=round(rrow["wall_time_build_s"] + seg["b"] + segf["b"], 2),
                   wall_time_total_s=round(rrow["wall_time_total_s"] + seg["t"] + segf["t"], 2),
                   llm_calls_stage=seg["llm_calls"], tokens_in_stage=seg["tokens_in"], tokens_out_stage=seg["tokens_out"],
                   wall_time_stage_s=round(seg["t"], 2))
        row["notes"] = "; ".join(row["notes"] + ["post-hoc control: refinement prompt without coverage report"])
        art.put("all_compile_logs.txt", "\n\n=====\n\n".join(st["compile_logs"]))
        art.put("row.json", row)
        return {"ok": True, "row": row, "key": (fqn, mk, seed), "secs": time.time() - t}
    except Exception:
        return {"ok": False, "err": traceback.format_exc(), "key": (fqn, mk, seed), "secs": time.time() - t}


def main():
    ap = argparse.ArgumentParser()
    for a in ("--config", "--runs", "--out", "--art", "--host"):
        ap.add_argument(a, required=True)
    ap.add_argument("--models", default="S,L")
    ap.add_argument("--seeds", default="1,2,3")
    ap.add_argument("--workers", type=int, default=16)
    ap.add_argument("--work", default=os.environ.get("TMPDIR", "/tmp") + "/icect_ctrl")
    a = ap.parse_args()
    cfg = json.load(open(a.config))
    hosts = dict(kv.split("=") for kv in a.host.split(","))
    rows = [json.loads(l) for l in open(a.runs)]
    R = {(r["class"], r["config"][0], r["run_seed"]): r for r in rows if r["config"].endswith("-R")}
    fqns = [c["fqn"] for c in json.load(open(ROOT / "results/classes.json"))["eval"]]
    out = pathlib.Path(a.out)
    done = set()
    if out.exists():
        for l in out.read_text().splitlines():
            r = json.loads(l)
            done.add((r["class"], r["config"][0], r["run_seed"]))
    jobs = [(f, m, int(s)) for s in a.seeds.split(",") for f in fqns for m in a.models.split(",")
            if (f, m, int(s)) not in done]
    print(f"{len(jobs)} control chains to run ({len(done)} done)", flush=True)
    from run_all import prepare_workers
    work = pathlib.Path(a.work)
    work.mkdir(parents=True, exist_ok=True)
    free = prepare_workers(a.workers, work)
    errlog = out.with_suffix(".errors.log")
    with ProcessPoolExecutor(max_workers=a.workers) as ex:
        pending, it, n = {}, iter(jobs), 0

        def submit():
            for f, m, s in it:
                d = free.pop()
                pending[ex.submit(one, (cfg, f, m, s, str(d), hosts, a.art, R[(f, m, s)]))] = d
                return

        for _ in range(min(a.workers, len(jobs))):
            submit()
        while pending:
            fut = next(as_completed(list(pending)))
            free.append(pending.pop(fut))
            r = fut.result()
            n += 1
            if r["ok"]:
                with open(out, "a") as fh:
                    fh.write(json.dumps(r["row"]) + "\n")
                x = r["row"]
                print(f"[{n}/{len(jobs)}] {r['key']} {r['secs']:.0f}s pass={x['tests_passing']} br={x.get('branch_cov')} "
                      f"ms={x.get('mutation_score')} rounds={x['cov_rounds_used']}", flush=True)
            else:
                with open(errlog, "a") as fh:
                    fh.write(f"=== {r['key']}\n{r['err']}\n")
                print(f"[{n}/{len(jobs)}] {r['key']} FAILED", flush=True)
            submit()


if __name__ == "__main__":
    main()
