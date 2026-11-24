"""Post-hoc quantization check: 7B model with FP16 weights (configs F-ZS/F-R/F-RC/F-RN) vs the 4-bit 7B model (S-*).
Usage: python fp16_analysis.py <runs.jsonl> <runs_control.jsonl> <runs_fp16.jsonl> <runs_control_fp16.jsonl> <out.md>"""
import json, sys
import numpy as np, pandas as pd
from scipy.stats import wilcoxon

frames = [pd.DataFrame([json.loads(l) for l in open(f)]) for f in sys.argv[1:5]]
out = sys.argv[5]
df = pd.concat(frames, ignore_index=True)
df = df[df.config.str[0].isin(["S", "F"])]
df["simple"] = df["class"].str.rsplit(".", n=1).str[1]
g = df["tests_generated"].replace(0, np.nan)
df["compile_rate"] = (df.tests_compiled / g).fillna(0.0)
df["pass_rate"] = (df.tests_passing / g).fillna(0.0)
for c in ("line_cov", "branch_cov", "mutation_score", "test_strength"):
    df[c] = pd.to_numeric(df[c], errors="coerce")
pc = df.groupby(["config", "simple"]).mean(numeric_only=True).reset_index()
M = ["compile_rate", "pass_rate", "line_cov", "branch_cov", "mutation_score"]
piv = {m: pc.pivot(index="simple", columns="config", values=m) for m in M}
n = df[df.config.str.startswith("F")].groupby("config").size().to_dict()
L = ["# Post-hoc quantization check: 7B model in FP16 vs 4-bit (Q4_K_M)", "",
     "Same pipeline, prompts, classes, seeds and configurations as the main run; only the model weights differ "
     "(`qwen2.5-coder:7b-instruct-fp16`). F = FP16, S = 4-bit. RN = refinement without coverage report (same control as for S/L). "
     f"Rows per config: {n}. Per-class mean over seeds, then mean over 20 classes (%).", "",
     "## Means", "", "| Config | " + " | ".join(M) + " | LLM calls | tokens out |", "|---|" + "---|" * (len(M) + 2)]
for c in ["S-ZS", "F-ZS", "S-R", "F-R", "S-RC", "F-RC", "S-RN", "F-RN"]:
    x = pc[pc.config == c]
    if not len(x):
        continue
    L.append(f"| {c} | " + " | ".join(f"{100 * x[m].mean():.1f}" for m in M) +
             f" | {x.llm_calls.mean():.1f} | {x.tokens_out.mean():.0f} |")
tests = []


def add(a, b, m):
    if a not in piv[m] or b not in piv[m]:
        return
    x, y = piv[m][a], piv[m][b]
    ok = x.notna() & y.notna(); d = (y - x)[ok]
    p = wilcoxon(y[ok], x[ok]).pvalue if (d != 0).any() else 1.0
    tests.append(dict(cmp=f"{a} → {b}", m=m, d=100 * d.mean(), b=int((d > 0).sum()), e=int((d == 0).sum()), w=int((d < 0).sum()), p=p))


for a, b in (("F-ZS", "F-R"), ("F-R", "F-RC"), ("F-R", "F-RN"), ("F-RN", "F-RC")):
    for m in ("branch_cov", "mutation_score"):
        add(a, b, m)
for c in ("ZS", "R", "RC", "RN"):
    for m in ("branch_cov", "mutation_score"):
        add(f"S-{c}", f"F-{c}", m)
add("F-ZS", "F-R", "compile_rate"); add("S-ZS", "S-R", "compile_rate")
srt = sorted(range(len(tests)), key=lambda i: tests[i]["p"]); run = 0
for j, i in enumerate(srt):
    run = max(run, min(1, (len(tests) - j) * tests[i]["p"])); tests[i]["ph"] = run
L += ["", "## Paired Wilcoxon tests over 20 classes (two-sided; Holm over the tests in this table)", "",
      "First block: the stage effects within the FP16 model (does the coverage report help: F-RN → F-RC). "
      "Second block: FP16 vs 4-bit at the same configuration.", "",
      "| Comparison | Metric | mean Δ (pp) | classes up/equal/down | p | p (Holm) |", "|---|---|---|---|---|---|"]
for t in tests:
    L.append(f"| {t['cmp']} | {t['m']} | {t['d']:+.1f} | {t['b']}/{t['e']}/{t['w']} | {t['p']:.3g} | {t['ph']:.3g} |")
p2 = df.pivot_table(index=["simple", "run_seed"], columns="config", values="branch_cov")
if "F-RC" in p2 and "F-RN" in p2:
    d = (p2["F-RC"] - p2["F-RN"]) * 100
    L += ["", "Per seed, branch coverage F-RC − F-RN (pp): " +
          ", ".join(f"seed {s}: {d.xs(s, level='run_seed').mean():+.1f}" for s in (1, 2, 3))]
open(out, "w").write("\n".join(L) + "\n")
print("\n".join(L))
