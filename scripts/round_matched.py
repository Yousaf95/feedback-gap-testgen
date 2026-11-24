"""Post-hoc E1 (review M-02): round-matched comparison of RC (coverage report) vs RN (no coverage report).
Uses the JaCoCo reports saved after each refinement round (*_cov_round<k>_jacoco.xml); no re-measurement, no LLM calls.
Round 0 = green R suite; after an early stop the last value is carried forward (a stopped loop adds nothing).
Also: m-01 classes better/equal/worse for Table III comparisons; m-11 paired test for test strength R vs RC.
Usage: python round_matched.py <runs.jsonl> <runs_control.jsonl> <artifacts> <out.md>"""
import json, re, sys, pathlib
import numpy as np, pandas as pd
from scipy.stats import wilcoxon
from lxml import etree

runs, ctl, art, out = sys.argv[1], sys.argv[2], pathlib.Path(sys.argv[3]), pathlib.Path(sys.argv[4])
rows = [json.loads(l) for l in open(runs)] + [json.loads(l) for l in open(ctl)]
R0 = {(r["class"], r["config"][0], r["run_seed"]): r["branch_cov"] for r in rows if r["config"].endswith("-R")}


def bcov(x, simple, pkg):
    for _, el in etree.iterparse(str(x), tag="sourcefile", load_dtd=False, no_network=True, resolve_entities=False, huge_tree=True):
        if el.get("name") == f"{simple}.java" and el.getparent().get("name") == pkg:
            c = {e.get("type"): (int(e.get("missed")), int(e.get("covered"))) for e in el if e.tag == "counter"}
            m, cv = c.get("BRANCH", (0, 0))
            return cv / (m + cv) if m + cv else 0.0
        el.clear()
    return 0.0


rec, chk = [], []
for r in rows:
    if not r["config"].endswith(("-RC", "-RN")):
        continue
    simple, pkg = r["class"].rsplit(".", 1)[1], r["class"].rsplit(".", 1)[0].replace(".", "/")
    d = art / simple / r["config"] / f"run{r['run_seed']}"
    vals = {int(re.search(r"cov_round(\d+)_", x.name).group(1)): bcov(x, simple, pkg) for x in d.glob("*_cov_round*_jacoco.xml")}
    v = [R0[(r["class"], r["config"][0], r["run_seed"])] or 0.0]
    fixed = "recomputed after bug fix" in r["notes"]  # deviation 20: crashed round keeps the previous green suite
    for k in (1, 2, 3):
        x = vals.get(k, v[-1])
        if fixed and x < v[-1]:
            x = v[-1]
        v.append(x)
    for k in range(4):
        rec.append({"cls": simple, "cfg": r["config"], "model": r["config"][0], "seed": r["run_seed"], "round": k, "branch": 100 * v[k]})
    chk.append(abs(100 * v[3] - 100 * (r["branch_cov"] or 0.0)))
df = pd.DataFrame(rec)
pc = df.groupby(["model", "cfg", "round", "cls"]).branch.mean()
tests = []
for mk in ("S", "L"):
    for k in (1, 2, 3):
        rn, rc = pc.loc[(mk, f"{mk}-RN", k)], pc.loc[(mk, f"{mk}-RC", k)]
        rc = rc.loc[rn.index]; d = rc - rn
        p = wilcoxon(rc, rn).pvalue if (d != 0).any() else 1.0
        tests.append(dict(mk=mk, k=k, rn=rn.mean(), rc=rc.mean(), d=d.mean(), b=(d > 0).sum(), e=(d == 0).sum(), w=(d < 0).sum(), p=p))
srt = sorted(range(len(tests)), key=lambda i: tests[i]["p"]); run = 0
for j, i in enumerate(srt):
    run = max(run, min(1, (len(tests) - j) * tests[i]["p"])); tests[i]["ph"] = run
chk = np.array(chk)
L = ["# E1: round-matched RC vs RN (post-hoc; review item M-02)", "",
     "Branch coverage (%) of the green suite after refinement round k, from the JaCoCo reports saved during the runs "
     "(no re-measurement, no LLM calls). Round 0 = green R suite; after an early stop the last value is carried forward. "
     "Per-class mean over seeds; Wilcoxon over 20 classes; Holm over the 6 tests.", "",
     f"Consistency: |round-3 value − reported final| max = {chk.max():.2f} pp; runs off by > 1 pp: {(chk > 1).sum()} of {len(chk)} "
     "(the final measurement is a separate test execution; small differences come from tests with random inputs).", "",
     "| Model | after round | RN | RC | RC − RN | classes RC > / = / < RN | p | p (Holm, 6) |", "|---|---|---|---|---|---|---|---|"]
for t in tests:
    L.append(f"| {t['mk']} | {t['k']} | {t['rn']:.1f} | {t['rc']:.1f} | {t['d']:+.1f} | {t['b']}/{t['e']}/{t['w']} | {t['p']:.3g} | {t['ph']:.3g} |")
# m-01: better/equal/worse for Table III comparisons; m-11: test strength R vs RC
main = pd.DataFrame([json.loads(l) for l in open(runs)])
main["simple"] = main["class"].str.rsplit(".", n=1).str[1]
g = main["tests_generated"].replace(0, np.nan); main["pass_rate"] = (main.tests_passing / g).fillna(0)
p2 = main.groupby(["config", "simple"]).mean(numeric_only=True).reset_index()
piv = {m: p2.pivot(index="simple", columns="config", values=m) for m in ("branch_cov", "mutation_score", "pass_rate", "test_strength")}
L += ["", "## Classes better / equal / worse for the Table III comparisons (second vs first)", "",
      "| Comparison | branch | mutation | pass rate |", "|---|---|---|---|"]
for a, b in (("S-ZS", "S-RC"), ("L-ZS", "L-RC"), ("S-RC", "L-RC"), ("S-RC", "L-ZS")):
    cells = []
    for m in ("branch_cov", "mutation_score", "pass_rate"):
        d = (piv[m][b] - piv[m][a]).round(9)
        cells.append(f"{(d > 0).sum()}/{(d == 0).sum()}/{(d < 0).sum()}")
    L.append(f"| {a} vs {b} | " + " | ".join(cells) + " |")
L += ["", "## Test strength R vs RC (paired Wilcoxon, exploratory)", "", "| Model | R | RC | mean Δ | p | classes up/equal/down |", "|---|---|---|---|---|---|"]
for mk in ("S", "L"):
    x, y = piv["test_strength"][f"{mk}-R"], piv["test_strength"][f"{mk}-RC"]; ok = x.notna() & y.notna(); d = (y - x)[ok]
    L.append(f"| {mk} | {100 * x[ok].mean():.1f} | {100 * y[ok].mean():.1f} | {100 * d.mean():+.1f} | {wilcoxon(y[ok], x[ok]).pvalue:.3g} | {(d > 0).sum()}/{(d == 0).sum()}/{(d < 0).sum()} |")
out.write_text("\n".join(L) + "\n"); print("\n".join(L))
