"""Report for the developer-test reference point. Usage: python dev_baseline_report.py <baseline_dir> <per_class_means.csv>"""
import json, sys, pathlib
import pandas as pd
from scipy.stats import wilcoxon

bdir, pcm = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2])
dev = pd.DataFrame(json.load(open(bdir / "developer_baseline.json"))).set_index("class")
pc = pd.read_csv(pcm)
pc["simple"] = pc["class"].str.rsplit(".", n=1).str[1]
CFG = ["S-ZS", "S-RC", "L-ZS", "L-RC"]
piv = {m: pc.pivot(index="simple", columns="config", values=m) for m in ("line_cov", "branch_cov", "mutation_score", "test_strength")}


def a12(x, y):
    gt = sum((xi > y).sum() for xi in x); eq = sum((xi == y).sum() for xi in x)
    return (gt + 0.5 * eq) / (len(x) * len(y))


L = ["# Reference point: Commons Lang's own tests", "",
     "Post-hoc, no LLM calls. The developer-written test classes `<Class>*Test.java` of rel/commons-lang-3.14.0 "
     "(the same reference set as the contamination check) were run in the same harness on the same 20 classes, "
     "with the same JaCoCo 0.8.12 and PIT 1.15.8 (DEFAULTS) settings; target class and its nested classes only. "
     "The whole original test tree was compiled so that shared helpers resolve, and the project's own Java 9+ JVM "
     "flags (`--add-opens`) were used. Tests of other classes that also exercise a target class are not included. "
     "PIT ran with `skipFailingTests`.", "",
     f"Developer test methods executed: {int(dev.tests_run.sum())} "
     f"(failures {int(dev.failures.sum())}, errors {int(dev.errors.sum())}, skipped {int(dev.skipped.sum())}).", "",
     "## Mean over the 20 classes (%)", "",
     "| Suite | Line cov. | Branch cov. | Mutation score | Test strength |", "|---|---|---|---|---|"]
L.append(f"| Developer tests | {100 * dev.line_cov.mean():.1f} | {100 * dev.branch_cov.mean():.1f} | "
         f"{100 * dev.mutation_score.mean():.1f} | {100 * dev.test_strength.mean():.1f} |")
for c in CFG:
    L.append(f"| {c} | {100 * piv['line_cov'][c].mean():.1f} | {100 * piv['branch_cov'][c].mean():.1f} | "
             f"{100 * piv['mutation_score'][c].mean():.1f} | {100 * piv['test_strength'][c].mean():.1f} |")
L += ["", "## Paired comparison with the developer tests (Wilcoxon over 20 classes, two-sided; exploratory)", "",
      "| Config | Metric | mean Δ (config − developer, pp) | p | Â12 (config > developer) | classes where config ≥ developer |",
      "|---|---|---|---|---|---|"]
for c in ("S-RC", "L-RC"):
    for m, dm in (("branch_cov", "branch_cov"), ("mutation_score", "mutation_score")):
        x = piv[m][c].reindex(dev.index)
        y = dev[dm]
        d = x - y
        p = wilcoxon(x, y).pvalue if (d != 0).any() else float("nan")
        L.append(f"| {c} | {m} | {100 * d.mean():+.1f} | {p:.3g} | {a12(x.values, y.values):.2f} | {(d >= 0).sum()}/20 |")
L += ["", "## Per class (%)", "",
      "| Class | dev. test methods | Dev branch | S-RC | L-ZS | L-RC | Dev mut. | S-RC | L-ZS | L-RC |",
      "|---|---|---|---|---|---|---|---|---|---|"]
for k in sorted(dev.index):
    r = dev.loc[k]
    L.append(f"| {k} | {int(r.tests_run)} | {100 * r.branch_cov:.1f} | " +
             " | ".join(f"{100 * piv['branch_cov'].loc[k, c]:.1f}" for c in ("S-RC", "L-ZS", "L-RC")) +
             f" | {100 * r.mutation_score:.1f} | " +
             " | ".join(f"{100 * piv['mutation_score'].loc[k, c]:.1f}" for c in ("S-RC", "L-ZS", "L-RC")) + " |")
(bdir.parent / "developer_baseline.md").write_text("\n".join(L) + "\n")
print("\n".join(L))
