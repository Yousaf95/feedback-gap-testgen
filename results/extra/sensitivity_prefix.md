# Sensitivity check: original (pre-fix) values of the 3 recomputed SystemUtils RC rows

`results/extra/summary_pre_fix.md` is the full analysis (analyze.py) run on `results/raw/runs.pre_fix.jsonl`,
i.e. with the three affected rows (L-RC SystemUtils seeds 2 and 3, S-RC SystemUtils seed 3) at their original values.

| | reported (fixed) | original values |
|---|---|---|
| S-RC branch cov. / line cov. | 36.6 / 44.5 | 36.0 / 43.0 |
| L-RC branch cov. / line cov. | 65.1 / 75.2 | 63.5 / 72.0 |
| S-ZS vs S-RC, branch: p (Holm), Â12 | 0.0017, 0.65 | 0.011, 0.64 |
| L-ZS vs L-RC, branch: p (Holm), Â12 | 3.8e-5, 0.79 | 5.8e-4, 0.76 |
| S-RC vs L-RC, branch: p (Holm), Â12 | 3.8e-5, 0.81 | 1.9e-4, 0.78 |
| S-RC vs L-ZS, branch: p (Holm), Â12 | 1.0, 0.53 | 1.0, 0.54 |

All conclusions are unchanged: every main comparison remains significant after Holm–Bonferroni adjustment
(largest adjusted p = 0.011 instead of 0.0017), and S-RC vs L-ZS remains non-significant.
