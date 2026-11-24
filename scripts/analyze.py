"""Analysis (spec section 7). Usage: python analyze.py <runs.jsonl> <artifacts_dir> <out_dir> [deviations.md]"""
import json, re, sys, pathlib
import numpy as np, pandas as pd
from scipy.stats import wilcoxon
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt

runs, art, out = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2]), pathlib.Path(sys.argv[3])
dev_file = pathlib.Path(sys.argv[4]) if len(sys.argv) > 4 else None
out.mkdir(parents=True, exist_ok=True)
df = pd.DataFrame([json.loads(l) for l in runs.read_text().splitlines() if l.strip()])
CONFIGS = [c for c in ["S-ZS", "S-R", "S-RC", "L-ZS", "L-R", "L-RC"] if c in set(df.config)]
df["simple"] = df["class"].str.rsplit(".", n=1).str[1]
g = df["tests_generated"].replace(0, np.nan)
df["compile_rate"] = (df["tests_compiled"] / g).fillna(0.0)
df["pass_rate"] = (df["tests_passing"] / g).fillna(0.0)
df["tokens"] = df["tokens_in"] + df["tokens_out"]
for c in ("line_cov", "branch_cov", "mutation_score", "test_strength"):
    df[c] = pd.to_numeric(df[c], errors="coerce")

# per class: average over runs
pc = df.groupby(["config", "class"]).mean(numeric_only=True).reset_index()
nruns = df.groupby("config")["run_seed"].nunique().to_dict()
ncls = df.groupby("config")["class"].nunique().to_dict()


def ms(s, pct=True, d=1):
    s = s.dropna()
    if not len(s):
        return "n/a"
    f = 100 if pct else 1
    return f"{s.mean() * f:.{d}f} ± {s.std(ddof=1) * f:.{d}f}" if len(s) > 1 else f"{s.mean() * f:.{d}f}"


lines = ["# Results summary", "",
         f"Rows: {len(df)}. Runs per config: {nruns}. Classes per config: {ncls}.",
         "All values: per-class mean over runs, then mean ± std over classes. Rates and coverage in %.",
         "compile rate = tests_compiled / tests_generated; passing rate = tests_passing / tests_generated "
         "(per class-run; 0 when nothing was generated). In RC configs tests_generated includes the test methods "
         "generated during coverage-guided refinement (T0 + all refinement rounds).",
         "Times: wall-clock per class-run, measured with up to 8 concurrent requests per Ollama server (one GPU per model) "
         "and 16 parallel pipeline workers; they reflect relative cost between configs, not single-request latency.", ""]

# Table I
lines += ["## Table I: compilation, passing, coverage", "",
          "| Config | Compile rate | Passing-test rate | Line cov. | Branch cov. | Tests generated | Tests passing | gen_ok |",
          "|---|---|---|---|---|---|---|---|"]
for c in CONFIGS:
    x = pc[pc.config == c]
    lines.append(f"| {c} | {ms(x.compile_rate)} | {ms(x.pass_rate)} | {ms(x.line_cov)} | {ms(x.branch_cov)} | "
                 f"{ms(x.tests_generated, False)} | {ms(x.tests_passing, False)} | "
                 f"{df[df.config == c].gen_ok.mean() * 100:.1f}% |")
# Table II
lines += ["", "## Table II: mutation analysis and cost (per class)", "",
          "| Config | Mutation score | Test strength | Tokens in | Tokens out | LLM calls | Cost (USD) | LLM time (s) | Build time (s) | Total time (s) |",
          "|---|---|---|---|---|---|---|---|---|---|"]
for c in CONFIGS:
    x = pc[pc.config == c]
    lines.append(f"| {c} | {ms(x.mutation_score)} | {ms(x.test_strength)} | {ms(x.tokens_in, False, 0)} | "
                 f"{ms(x.tokens_out, False, 0)} | {ms(x.llm_calls, False)} | {x.est_cost_usd.mean():.2f} | "
                 f"{ms(x.wall_time_llm_s, False, 0)} | {ms(x.wall_time_build_s, False, 0)} | {ms(x.wall_time_total_s, False, 0)} |")
# Stage-4 counts
lines += ["", "## Test-method counts (sums over all class-runs)", "",
          "| Config | generated | compiled | passing | removed failing | removed flaky | repair rounds | cov rounds |",
          "|---|---|---|---|---|---|---|---|"]
for c in CONFIGS:
    x = df[df.config == c]
    lines.append(f"| {c} | {x.tests_generated.sum()} | {x.tests_compiled.sum()} | {x.tests_passing.sum()} | "
                 f"{x.removed_failing.sum()} | {x.removed_flaky.sum()} | {x.repair_rounds_used.sum()} | "
                 f"{x.cov_rounds_used.sum()} |")

# Ablation deltas
METRICS = ["compile_rate", "pass_rate", "line_cov", "branch_cov", "mutation_score", "test_strength"]
lines += ["", "## Ablation deltas (percentage points, mean over classes of per-class differences)", "",
          "| Step | " + " | ".join(METRICS) + " |", "|---|" + "---|" * len(METRICS)]
piv = {m: pc.pivot(index="class", columns="config", values=m) for m in METRICS}
for m_ in ("S", "L"):
    for a, b in (("ZS", "R"), ("R", "RC")):
        ca, cb = f"{m_}-{a}", f"{m_}-{b}"
        if ca in CONFIGS and cb in CONFIGS:
            cells = []
            for m in METRICS:
                d = (piv[m][cb] - piv[m][ca]).dropna()
                cells.append(f"{d.mean() * 100:+.1f}" if len(d) else "n/a")
            lines.append(f"| {ca} → {cb} | " + " | ".join(cells) + " |")


def a12(x, y):
    """Vargha-Delaney A12: P(X > Y) + 0.5 P(X = Y)."""
    x, y = np.asarray(x), np.asarray(y)
    gt = sum((xi > y).sum() for xi in x)
    eq = sum((xi == y).sum() for xi in x)
    return (gt + 0.5 * eq) / (len(x) * len(y))


lines += ["", "## Paired Wilcoxon signed-rank tests over classes (two-sided) and Vargha–Delaney Â12", "",
          "Â12 = P(second config > first config). p (Holm) = Holm–Bonferroni adjusted over all tests in this table. "
          "The first four comparisons test each feedback stage on its own (ZS→R, R→RC).", "",
          "| Comparison | Metric | n | mean Δ (pp) | median Δ (pp) | W | p-value | p (Holm) | Â12 |", "|---|---|---|---|---|---|---|---|---|"]
tests = []
for ca, cb in (("S-ZS", "S-R"), ("S-R", "S-RC"), ("L-ZS", "L-R"), ("L-R", "L-RC"),
               ("S-ZS", "S-RC"), ("L-ZS", "L-RC"), ("S-RC", "L-RC"), ("S-RC", "L-ZS"), ("S-R", "L-ZS")):
    if ca not in CONFIGS or cb not in CONFIGS:
        continue
    for m in ("line_cov", "branch_cov", "mutation_score", "pass_rate"):
        p2 = piv[m][[ca, cb]].dropna()
        d = p2[cb] - p2[ca]
        t = {"cmp": f"{ca} vs {cb}", "m": m, "n": len(p2), "med": d.median() * 100 if len(d) else float("nan"),
             "mean": d.mean() * 100 if len(d) else float("nan"),
             "a12": a12(p2[cb], p2[ca]) if len(p2) else float("nan"), "W": None, "p": None}
        if len(p2) >= 2 and not (d == 0).all():
            w = wilcoxon(p2[cb], p2[ca], zero_method="wilcox", alternative="two-sided")
            t["W"], t["p"] = w.statistic, w.pvalue
        tests.append(t)
valid = sorted([t for t in tests if t["p"] is not None], key=lambda t: t["p"])
k, run_max = len(valid), 0.0
for i, t in enumerate(valid):  # Holm step-down, monotone
    run_max = max(run_max, min(1.0, (k - i) * t["p"]))
    t["p_holm"] = run_max
for t in tests:
    if t["p"] is None:
        lines.append(f"| {t['cmp']} | {t['m']} | {t['n']} | {t['mean']:+.1f} | {t['med']:+.1f} | – | n/a (all differences zero or n<2) | – | {t['a12']:.3f} |")
    else:
        lines.append(f"| {t['cmp']} | {t['m']} | {t['n']} | {t['mean']:+.1f} | {t['med']:+.1f} | {t['W']:.1f} | {t['p']:.4g} | {t['p_holm']:.4g} | {t['a12']:.3f} |")

# Equivalence analysis for the title comparison (post-hoc)
rng = np.random.default_rng(2027)
lines += ["", "## Equivalence analysis: S-RC vs L-ZS (post-hoc)", "",
          "Paired per-class differences d = S-RC − L-ZS (pp). 95% CI of the mean difference by bootstrap over classes "
          "(10,000 resamples, seed 2027). TOST = two one-sided Wilcoxon signed-rank tests against an equivalence margin "
          "±δ; equivalence is shown only if p(TOST) < 0.05.", "",
          "| Metric | mean d | 95% CI | p(TOST, δ = 5 pp) | p(TOST, δ = 10 pp) | smallest δ (integer pp) with p(TOST) < 0.05 |",
          "|---|---|---|---|---|---|"]
if "S-RC" in CONFIGS and "L-ZS" in CONFIGS:
    for m in ("line_cov", "branch_cov", "mutation_score"):
        d = ((piv[m]["S-RC"] - piv[m]["L-ZS"]).dropna() * 100).values
        boots = rng.choice(d, size=(10000, len(d)), replace=True).mean(axis=1)
        lo, hi = np.percentile(boots, [2.5, 97.5])
        def tost(delta):
            p1 = wilcoxon(d + delta, alternative="greater").pvalue
            p2 = wilcoxon(d - delta, alternative="less").pvalue
            return max(p1, p2)
        smallest = next((k for k in range(1, 51) if tost(k) < 0.05), None)
        lines.append(f"| {m} | {d.mean():+.1f} | [{lo:+.1f}, {hi:+.1f}] | {tost(5):.3f} | {tost(10):.3f} | "
                     f"{'±' + str(smallest) if smallest else '> 50'} |")

# Per-class branch coverage table
bc = piv["branch_cov"][CONFIGS] * 100
bc.index = [i.rsplit(".", 1)[1] for i in bc.index]
lines += ["", "## Per-class branch coverage (%, mean over runs)", "",
          "| Class | " + " | ".join(CONFIGS) + " |", "|---|" + "---|" * len(CONFIGS)]
for cls, r in bc.sort_index().iterrows():
    lines.append(f"| {cls} | " + " | ".join("n/a" if pd.isna(v) else f"{v:.1f}" for v in r) + " |")
msc = piv["mutation_score"][CONFIGS] * 100
msc.index = [i.rsplit(".", 1)[1] for i in msc.index]
lines += ["", "## Per-class mutation score (%, mean over runs)", "",
          "| Class | " + " | ".join(CONFIGS) + " |", "|---|" + "---|" * len(CONFIGS)]
for cls, r in msc.sort_index().iterrows():
    lines.append(f"| {cls} | " + " | ".join("n/a" if pd.isna(v) else f"{v:.1f}" for v in r) + " |")

# Failure breakdown: compile errors in generated tests (all compile logs of all configs)
CATS = [("cannot find symbol", r"cannot find symbol"), ("incompatible types", r"incompatible types"),
        ("private access", r"has private access|is not public|not visible"),
        ("unreported exception", r"unreported exception"),
        ("ambiguous reference", r"reference to \S+ is ambiguous"),
        ("syntax / parse", r"expected|illegal start|reached end of file|not a statement|unclosed"),
        ("abstract / no instance", r"is abstract; cannot be instantiated"),
        ("method not applicable", r"cannot be applied to|no suitable (method|constructor)")]
err_re = re.compile(r"^\[ERROR\] (/\S+?_GenTest\.java):\[(\d+),(\d+)\] (.*)$")
counts = {}
for c in CONFIGS:
    cnt = {k: 0 for k, _ in CATS}
    cnt["other"] = 0
    for f in art.glob(f"*/{c}/*/*all_compile_logs.txt"):
        seen = set()
        for l in f.read_text(errors="replace").splitlines():
            m = err_re.match(l)
            if not m:
                continue
            key = (m.group(2), m.group(3), m.group(4))
            if key in seen:
                continue
            seen.add(key)
            for k, rx in CATS:
                if re.search(rx, m.group(4)):
                    cnt[k] += 1
                    break
            else:
                cnt["other"] += 1
    counts[c] = cnt
cats = [k for k, _ in CATS] + ["other"]
lines += ["", "## Compile-error categories (error messages in generated test files, all compile attempts; "
          "ZS/R/RC logs counted separately per config; RC counts only refinement-stage compiles)", "",
          "| Category | " + " | ".join(CONFIGS) + " |", "|---|" + "---|" * len(CONFIGS)]
for k in cats:
    lines.append(f"| {k} | " + " | ".join(str(counts[c][k]) for c in CONFIGS) + " |")

# Notes frequency
lines += ["", "## Pipeline notes (count of class-runs)", ""]
nt = {}
for c, n in zip(df.config, df.notes.fillna("")):
    for part in filter(None, [p.strip() for p in n.split(";")]):
        part = re.sub(r"\d+", "N", part)
        nt[(c, part)] = nt.get((c, part), 0) + 1
for (c, p), v in sorted(nt.items()):
    lines.append(f"- {c}: {p} — {v}")

if dev_file and dev_file.exists():
    lines += ["", "## Deviations from the spec", "", dev_file.read_text().strip()]
(out / "summary.md").write_text("\n".join(lines) + "\n")

# Figure: per-class branch coverage, 6 configs
fig, axes = plt.subplots(1, 2, figsize=(10, 3.6), gridspec_kw={"width_ratios": [3, 1]})
order = bc.mean(axis=1).sort_values().index
colors = {"S-ZS": "#9ecae1", "S-R": "#4292c6", "S-RC": "#08519c", "L-ZS": "#fdae6b", "L-R": "#f16913", "L-RC": "#a63603"}
x = np.arange(len(order))
w = 0.8 / len(CONFIGS)
for i, c in enumerate(CONFIGS):
    axes[0].bar(x + (i - (len(CONFIGS) - 1) / 2) * w, bc.loc[order, c], w, label=c, color=colors[c])
axes[0].set_xticks(x)
axes[0].set_xticklabels(order, rotation=60, ha="right", fontsize=7)
axes[0].set_ylabel("Branch coverage (%)")
axes[0].set_ylim(0, 100)
axes[0].legend(ncol=6, fontsize=7, loc="upper left", frameon=False)
axes[1].boxplot([bc[c].dropna() for c in CONFIGS], tick_labels=CONFIGS, widths=0.6)
axes[1].tick_params(axis="x", labelrotation=60, labelsize=7)
axes[1].set_ylim(0, 100)
fig.tight_layout()
fig.savefig(out / "branch_coverage.pdf")
pc.to_csv(out / "per_class_means.csv", index=False)
print((out / "summary.md").read_text())
