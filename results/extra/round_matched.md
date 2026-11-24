# E1: round-matched RC vs RN (post-hoc; review item M-02)

Branch coverage (%) of the green suite after refinement round k, from the JaCoCo reports saved during the runs (no re-measurement, no LLM calls). Round 0 = green R suite; after an early stop the last value is carried forward. Per-class mean over seeds; Wilcoxon over 20 classes; Holm over the 6 tests.

Consistency: |round-3 value − reported final| max = 1.72 pp; runs off by > 1 pp: 1 of 240 (the final measurement is a separate test execution; small differences come from tests with random inputs).

| Model | after round | RN | RC | RC − RN | classes RC > / = / < RN | p | p (Holm, 6) |
|---|---|---|---|---|---|---|---|
| S | 1 | 32.3 | 32.7 | +0.4 | 9/2/9 | 0.948 | 1 |
| S | 2 | 34.7 | 35.9 | +1.2 | 9/2/9 | 0.845 | 1 |
| S | 3 | 34.9 | 36.6 | +1.6 | 9/3/8 | 0.906 | 1 |
| L | 1 | 50.6 | 59.5 | +8.9 | 13/1/6 | 0.00791 | 0.0316 |
| L | 2 | 52.6 | 63.3 | +10.7 | 15/1/4 | 0.00427 | 0.0214 |
| L | 3 | 53.5 | 65.1 | +11.6 | 15/1/4 | 0.0017 | 0.0102 |

## Classes better / equal / worse for the Table III comparisons (second vs first)

| Comparison | branch | mutation | pass rate |
|---|---|---|---|
| S-ZS vs S-RC | 19/1/0 | 19/1/0 | 7/0/13 |
| L-ZS vs L-RC | 20/0/0 | 20/0/0 | 11/0/9 |
| S-RC vs L-RC | 20/0/0 | 20/0/0 | 20/0/0 |
| S-RC vs L-ZS | 10/0/10 | 11/1/8 | 17/0/3 |

## Test strength R vs RC (paired Wilcoxon, exploratory)

| Model | R | RC | mean Δ | p | classes up/equal/down |
|---|---|---|---|---|---|
| S | 58.0 | 60.0 | +2.0 | 0.148 | 11/4/5 |
| L | 64.1 | 68.2 | +4.1 | 0.0329 | 15/1/4 |

The per-round values used above (RC and RN, after the deviation-20 rule) are in `round_matched_values.csv`; `scripts/round_matched_from_csv.py` recomputes this table from that file alone. The older `coverage_per_round.csv` (from `extra_analysis.py`) contains RC only and the raw values before the deviation-20 rule.
