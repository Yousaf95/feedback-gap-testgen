"""Post-hoc exploratory analyses (not part of the frozen plan); uses existing artifacts only, no LLM calls.
  1. branch coverage after each refinement round (RC configs)
  2. cost-effectiveness of each config
  3. failure types of all tests removed as failing in Stage 4, + a deterministic sample for manual review
Usage: python extra_analysis.py <runs.jsonl> <artifacts_dir> <out_dir>"""
import json, random, re, sys, pathlib, collections
import pandas as pd
from lxml import etree
sys.path.insert(0, str(pathlib.Path(__file__).parent))
import javatools as jt

runs, art, out = map(pathlib.Path, sys.argv[1:4])
out.mkdir(parents=True, exist_ok=True)
df = pd.DataFrame([json.loads(l) for l in runs.read_text().splitlines() if l.strip()])
df["simple"] = df["class"].str.rsplit(".", n=1).str[1]
P = etree.XMLParser(load_dtd=False, no_network=True, resolve_entities=False, huge_tree=True)
L = ["# Post-hoc exploratory analyses", "",
     "Computed after the main analysis from existing artifacts (no new LLM calls); not part of the frozen plan.", ""]


def branch_cov(xml, simple, pkg):
    for _, el in etree.iterparse(str(xml), tag="sourcefile", load_dtd=False, no_network=True,
                                 resolve_entities=False, huge_tree=True):
        if el.get("name") == f"{simple}.java" and el.getparent().get("name") == pkg:
            c = {e.get("type"): (int(e.get("missed")), int(e.get("covered"))) for e in el if e.tag == "counter"}
            m, cv = c.get("BRANCH", (0, 0))
            return cv / (m + cv) if m + cv else None
        el.clear()
    return None


# ---------------------------------------------------------------- 1. per-round coverage
rec = []
for _, r in df[df.config.str.endswith("-RC")].iterrows():
    d = art / r.simple / r.config / f"run{r.run_seed}"
    pkg = r["class"].rsplit(".", 1)[0].replace(".", "/")
    vals = {}
    for x in d.glob("*_cov_round*_jacoco.xml"):
        k = int(re.search(r"cov_round(\d+)_", x.name).group(1))
        vals[k] = branch_cov(x, r.simple, pkg)
    row = {"config": r.config, "class": r.simple, "seed": r.run_seed}
    last = vals.get(0)
    if last is None:  # no refinement (empty R suite): every round equals the final value
        last = r.branch_cov
    for k in range(0, 4):
        if k in vals and vals[k] is not None:
            last = vals[k]
        row[f"round{k}"] = last  # carried forward after an early stop
    row["final"] = r.branch_cov
    rec.append(row)
pr = pd.DataFrame(rec)
pr.to_csv(out / "coverage_per_round.csv", index=False)
L += ["## 1. Branch coverage after each refinement round (RC configs)", "",
      "Round 0 = green R suite before refinement; after an early stop the last value is carried forward. "
      "Mean over classes (per-class mean over seeds), %.", "",
      "| Config | round 0 | round 1 | round 2 | round 3 | final | rounds used (mean) | stopped: ≥90% branch | stopped: no gain |",
      "|---|---|---|---|---|---|---|---|---|"]
for c in ("S-RC", "L-RC"):
    x = pr[pr.config == c].groupby("class").mean(numeric_only=True)
    rr = df[df.config == c]
    n90 = rr.notes.str.contains("cov stop: branch>=").sum()
    nng = rr.notes.str.contains("added no covered branch").sum()
    L.append(f"| {c} | " + " | ".join(f"{100 * x[f'round{k}'].mean():.1f}" for k in range(4)) +
             f" | {100 * x['final'].mean():.1f} | {rr.cov_rounds_used.mean():.2f} | {n90} | {nng} |")

# ---------------------------------------------------------------- 2. cost-effectiveness
pc = df.groupby(["config", "class"]).mean(numeric_only=True).reset_index()
base = pc[pc.config == "S-ZS"].set_index("class")
L += ["", "## 2. Cost-effectiveness (per class, mean over classes)", "",
      "Gain = branch-coverage points above S-ZS. Times as in summary.md (8 concurrent requests per GPU).", "",
      "| Config | Branch cov. | Gain vs S-ZS (pp) | Total time (min) | Output tokens (k) | pp per minute | pp per 1k output tokens |",
      "|---|---|---|---|---|---|---|"]
for c in ["S-ZS", "S-R", "S-RC", "L-ZS", "L-R", "L-RC"]:
    x = pc[pc.config == c].set_index("class")
    gain = 100 * (x.branch_cov - base.branch_cov)
    t = x.wall_time_total_s / 60
    k = x.tokens_out / 1000
    L.append(f"| {c} | {100 * x.branch_cov.mean():.1f} | {gain.mean():+.1f} | {t.mean():.1f} | {k.mean():.1f} | "
             + ("–" if c == "S-ZS" else f"{gain.mean() / t.mean():.2f}") + " | "
             + ("–" if c == "S-ZS" else f"{gain.mean() / k.mean():.2f}") + " |")
L += ["", "Marginal cost of each feedback step (mean over classes):", "",
      "| Step | Δ branch (pp) | Δ time (min) | Δ output tokens (k) |", "|---|---|---|---|"]
for a, b in (("S-ZS", "S-R"), ("S-R", "S-RC"), ("L-ZS", "L-R"), ("L-R", "L-RC"), ("S-RC", "L-ZS"), ("S-RC", "L-RC")):
    xa, xb = pc[pc.config == a].set_index("class"), pc[pc.config == b].set_index("class")
    L.append(f"| {a} → {b} | {100 * (xb.branch_cov - xa.branch_cov).mean():+.1f} | "
             f"{((xb.wall_time_total_s - xa.wall_time_total_s) / 60).mean():+.1f} | "
             f"{((xb.tokens_out - xa.tokens_out) / 1000).mean():+.1f} |")

# ---------------------------------------------------------------- 3. removed failing tests
HEAD = re.compile(r"^\[ERROR\] (?:[\w.]+\.)?(\w+_GenTest)(?:\$\w+)?\.(\w+)(?:\(.*?\))?(?:\[\d+\])?.*?<<< (FAILURE|ERROR)!")


def classify(exc, msg):
    if "TimeoutException" in exc or "timed out after" in msg:
        return "timeout"
    if "AssertionFailedError" in exc:
        if "to be thrown, but nothing was thrown" in msg:
            return "expected exception not thrown"
        if "Unexpected exception type thrown" in msg:
            return "different exception thrown"
        if "expected:" in msg:
            return "assertion: wrong expected value"
        return "assertion: other"
    if "MultipleFailuresError" in exc:
        return "assertion: other"
    if "ParameterResolution" in exc or "PreconditionViolation" in exc or "JUnitException" in exc:
        return "JUnit setup / parameter error"
    return "unexpected exception: " + exc.rsplit(".", 1)[-1]


items = []
for cfgdir in sorted(art.glob("*/*/run*")):
    simple, config, run = cfgdir.parts[-3], cfgdir.parts[-2], cfgdir.parts[-1]
    files = sorted(p.name for p in cfgdir.iterdir())
    for i, f in enumerate(files):
        if not f.endswith("_run1.log"):
            continue
        src_file = next((g for g in reversed(files[:i]) if g.endswith(".java") and "context" not in g), None)
        code = (cfgdir / src_file).read_text() if src_file else ""
        lines = (cfgdir / f).read_text(errors="replace").splitlines()
        seen = set()
        for j, l in enumerate(lines):
            m = HEAD.match(l)
            if not m or m.group(2) in seen:
                continue
            seen.add(m.group(2))
            nxt = lines[j + 1] if j + 1 < len(lines) else ""
            exc, _, msg = nxt.partition(":")
            items.append({"class": simple, "config": config, "run": run, "stage": "refine" if "refine" in "".join(files[:i]) else "initial",
                          "method": m.group(2), "kind": m.group(3), "exception": exc.strip(), "message": msg.strip()[:300],
                          "category": classify(exc.strip(), msg), "log": str(cfgdir / f),
                          "source": next((x["text"] for x in jt.methods(code) if x["name"] == m.group(2)), "")})
it = pd.DataFrame(items)
it.to_csv(out / "removed_failing_tests.csv", index=False)
L += ["", "## 3. Tests removed as failing in Stage 4", "",
      f"Parsed from Surefire output: {len(it)} failing test methods across all class-runs "
      "(ZS and R share T0, so many initial-stage failures appear in both; counts are per config).", "",
      "| Category | " + " | ".join(["S-ZS", "S-R", "S-RC", "L-ZS", "L-R", "L-RC"]) + " |", "|---|" + "---|" * 6]
cats = it.category.value_counts().index.tolist()
for cat in cats:
    L.append(f"| {cat} | " + " | ".join(str(((it.category == cat) & (it.config == c)).sum())
                                         for c in ["S-ZS", "S-R", "S-RC", "L-ZS", "L-R", "L-RC"]) + " |")
# deterministic sample for manual review: unique failures (dedupe ZS/R copies), assertion-type only
u = it[it.category.str.startswith("assertion: wrong") | it.category.str.startswith("expected exception")]
u = u.assign(model=u.config.str[0]).drop_duplicates(["class", "model", "run", "method", "message"])
random.seed(2027)
idx = sorted(random.sample(range(len(u)), min(50, len(u))))
samp = u.iloc[idx]
samp.to_json(out / "removed_sample50.jsonl", orient="records", lines=True)
L += ["", f"Triage sample: 50 of {len(u)} unique assertion / expected-exception failures "
      "(random.seed(2027)); see removed_sample50.jsonl and the triage table below.", ""]
L[-1:-1] = [f"Parsed per-method failures: {len(it)}; tests removed as failing according to runs.jsonl: "
            f"{int(df.removed_failing.sum())} (the rest left no per-method Surefire line, e.g. JVM crashes, "
            "class-level errors, or methods removed together after a crash)."]
rv = out / "removed_sample50_reviewed.jsonl"
if rv.exists():
    R = pd.read_json(rv, lines=True)
    L += ["", "### LLM-assisted triage of a random sample of 50 tests", "",
          "Labels assigned by LLM-assisted triage against the Commons Lang 3.14 source and Javadoc "
          "(not independently confirmed by the authors).", "",
          "| Label | Count |", "|---|---|"]
    for lab, n in R.review_label.value_counts().items():
        L.append(f"| {lab} | {n} |")
    L += ["", "| # | Class | Config | Method | Label | Note |", "|---|---|---|---|---|---|"]
    for i, x in enumerate(R.itertuples(), 1):
        L.append(f"| {i} | {x[1]} | {x.config} | {x.method} | {x.review_label} | {x.review_note} |")
(out / "extra_analysis.md").write_text("\n".join(L) + "\n")
print("\n".join(L))
