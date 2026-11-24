"""Recompute the E1 round-matched table from results/extra/round_matched_values.csv (no JaCoCo XMLs needed).
Usage: python round_matched_from_csv.py results/extra/round_matched_values.csv"""
import sys
import pandas as pd
from scipy.stats import wilcoxon

df = pd.read_csv(sys.argv[1])
pc = df.groupby(["model", "cfg", "round", "cls"]).branch.mean()
tests = []
for mk in ("S", "L"):
    for k in (1, 2, 3):
        rn, rc = pc.loc[(mk, f"{mk}-RN", k)], pc.loc[(mk, f"{mk}-RC", k)]
        rc = rc.loc[rn.index]; d = rc - rn
        tests.append([mk, k, rn.mean(), rc.mean(), d.mean(), wilcoxon(rc, rn).pvalue if (d != 0).any() else 1.0])
order = sorted(range(len(tests)), key=lambda i: tests[i][5]); run = 0
for j, i in enumerate(order):
    run = max(run, min(1, (len(tests) - j) * tests[i][5])); tests[i].append(run)
print("model round  RN    RC    RC-RN  p        p(Holm, 6 tests)")
for t in tests:
    print(f"{t[0]:5} {t[1]:5} {t[2]:5.1f} {t[3]:5.1f} {t[4]:+6.1f} {t[5]:<8.3g} {t[6]:.3g}")
