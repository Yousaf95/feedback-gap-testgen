"""Analysis of the post-hoc A-03 control (RN = refinement without coverage report) against R and RC.
Usage: python control_analysis.py <runs.jsonl> <runs_control.jsonl> <out.md>"""
import json, sys
import numpy as np, pandas as pd
from scipy.stats import wilcoxon

main = pd.DataFrame([json.loads(l) for l in open(sys.argv[1])])
ctl = pd.DataFrame([json.loads(l) for l in open(sys.argv[2])])
out = sys.argv[3]
df = pd.concat([main, ctl], ignore_index=True)
df["simple"] = df["class"].str.rsplit(".", n=1).str[1]
g = df["tests_generated"].replace(0, np.nan)
df["pass_rate"] = (df["tests_passing"] / g).fillna(0.0)
for c in ("line_cov", "branch_cov", "mutation_score"):
    df[c] = pd.to_numeric(df[c], errors="coerce")
pc = df.groupby(["config", "simple"]).mean(numeric_only=True).reset_index()
M = ("line_cov", "branch_cov", "mutation_score", "pass_rate")
piv = {m: pc.pivot(index="simple", columns="config", values=m) for m in M}


def a12(x, y):
    x, y = np.asarray(x), np.asarray(y)
    return (sum((xi > y).sum() for xi in x) + 0.5 * sum((xi == y).sum() for xi in x)) / (len(x) * len(y))


n_ctl = len(ctl)
L = ["# Post-hoc control: refinement without coverage report (RN)", "",
     f"{n_ctl} control runs (20 classes × 2 models × 3 seeds). Each RN run starts from the same green R suite as RC and runs "
     "the same Stage-5 loop (rounds, compile/repair/green, stopping rule, seed); the only difference is that the prompt "
     "contains no coverage information (prompts/refine_blind.txt). Values: per-class mean over seeds, then mean over 20 classes (%).", "",
     "## Means", "",
     "| Model | Metric | R | RN | RC | R→RN | R→RC | share of the R→RC gain reached by RN |",
     "|---|---|---|---|---|---|---|---|"]
for mk in ("S", "L"):
    for m in M:
        r, rn, rc = (100 * piv[m][f"{mk}-{c}"].mean() for c in ("R", "RN", "RC"))
        share = (rn - r) / (rc - r) if abs(rc - r) > 1e-9 else float("nan")
        L.append(f"| {mk} | {m} | {r:.1f} | {rn:.1f} | {rc:.1f} | {rn - r:+.1f} | {rc - r:+.1f} | "
                 + ("–" if np.isnan(share) or m == "pass_rate" else f"{100 * share:.0f}%") + " |")
tests = []
for mk in ("S", "L"):
    for a, b in (("R", "RN"), ("RN", "RC")):
        for m in ("line_cov", "branch_cov", "mutation_score"):
            x, y = piv[m][f"{mk}-{a}"], piv[m][f"{mk}-{b}"]
            d = (y - x).dropna()
            w = wilcoxon(y[d.index], x[d.index]) if (d != 0).any() else None
            tests.append({"cmp": f"{mk}-{a} vs {mk}-{b}", "m": m, "mean": 100 * d.mean(), "med": 100 * d.median(),
                          "W": w.statistic if w else None, "p": w.pvalue if w else None,
                          "a12": a12(y[d.index], x[d.index]), "better": int((d > 0).sum()), "worse": int((d < 0).sum())})
valid = sorted([t for t in tests if t["p"] is not None], key=lambda t: t["p"])
run_max = 0.0
for i, t in enumerate(valid):
    run_max = max(run_max, min(1.0, (len(valid) - i) * t["p"]))
    t["holm"] = run_max
L += ["", "## Paired Wilcoxon tests over 20 classes (two-sided); Holm over the tests in this table", "",
      "Â12 = P(second > first). R vs RN: effect of additional rounds alone; RN vs RC: effect of the coverage report.", "",
      "| Comparison | Metric | mean Δ (pp) | median Δ (pp) | W | p | p (Holm) | Â12 | classes better / worse |",
      "|---|---|---|---|---|---|---|---|---|"]
for t in tests:
    L.append(f"| {t['cmp']} | {t['m']} | {t['mean']:+.1f} | {t['med']:+.1f} | " +
             (f"{t['W']:.1f} | {t['p']:.3g} | {t['holm']:.3g}" if t["p"] is not None else "– | n/a | –") +
             f" | {t['a12']:.2f} | {t['better']} / {t['worse']} |")
# per seed
p2 = df.pivot_table(index=["simple", "run_seed"], columns="config", values="branch_cov")
L += ["", "## Per seed: branch coverage RC − RN (mean over classes, pp)", "", "| Model | seed 1 | seed 2 | seed 3 |", "|---|---|---|---|"]
for mk in ("S", "L"):
    d = (p2[f"{mk}-RC"] - p2[f"{mk}-RN"]) * 100
    L.append(f"| {mk} | " + " | ".join(f"{d.xs(s, level='run_seed').mean():+.1f}" for s in (1, 2, 3)) + " |")
# effort of the refinement stage
L += ["", "## Refinement-stage effort per class-run (mean)", "",
      "| Model | RN rounds | RC rounds | RN LLM calls (stage) | RC LLM calls (stage) | RN output tokens (stage) | RC output tokens (stage) |",
      "|---|---|---|---|---|---|---|"]
key = ["class", "run_seed"]
for mk in ("S", "L"):
    rn = ctl[ctl.config == f"{mk}-RN"].set_index(key)
    rc = main[main.config == f"{mk}-RC"].set_index(key)
    r = main[main.config == f"{mk}-R"].set_index(key)
    rc_calls = (rc.llm_calls - r.llm_calls).reindex(rn.index)
    rc_tok = (rc.tokens_out - r.tokens_out).reindex(rn.index)
    L.append(f"| {mk} | {rn.cov_rounds_used.mean():.2f} | {rc.cov_rounds_used.reindex(rn.index).mean():.2f} | "
             f"{rn.llm_calls_stage.mean():.2f} | {rc_calls.mean():.2f} | {rn.tokens_out_stage.mean():.0f} | {rc_tok.mean():.0f} |")
stops = ctl.notes.str.extract(r"cov stop: (branch>=|round \d added no covered branch)")[0].fillna("other/none")
L += ["", "RN stopping reasons: " + ", ".join(f"{k}: {v}" for k, v in stops.str.replace(r"round \d", "round N", regex=True).value_counts().items()), ""]
L += ["## Per class: branch coverage (%, mean over seeds)", "", "| Class | S-R | S-RN | S-RC | L-R | L-RN | L-RC |", "|---|---|---|---|---|---|---|"]
for k in sorted(piv["branch_cov"].index):
    L.append(f"| {k} | " + " | ".join(f"{100 * piv['branch_cov'].loc[k, c]:.1f}" for c in ("S-R", "S-RN", "S-RC", "L-R", "L-RN", "L-RC")) + " |")
L += ["", "Note: wall-clock times of RN are not comparable with the main run (both models were served from one GPU); compare effort by rounds, calls and tokens."]
open(out, "w").write("\n".join(L) + "\n")
print("\n".join(L))
