# Post-hoc control: refinement without coverage report (RN)

120 control runs (20 classes × 2 models × 3 seeds). Each RN run starts from the same green R suite as RC and runs the same Stage-5 loop (rounds, compile/repair/green, stopping rule, seed); the only difference is that the prompt contains no coverage information (prompts/refine_blind.txt). Values: per-class mean over seeds, then mean over 20 classes (%).

## Means

| Model | Metric | R | RN | RC | R→RN | R→RC | share of the R→RC gain reached by RN |
|---|---|---|---|---|---|---|---|
| S | line_cov | 35.3 | 43.6 | 44.5 | +8.3 | +9.2 | 91% |
| S | branch_cov | 26.3 | 34.9 | 36.6 | +8.6 | +10.2 | 84% |
| S | mutation_score | 17.7 | 24.2 | 27.8 | +6.5 | +10.1 | 64% |
| S | pass_rate | 52.0 | 48.1 | 47.1 | -3.9 | -4.8 | – |
| L | line_cov | 50.6 | 62.2 | 75.2 | +11.6 | +24.7 | 47% |
| L | branch_cov | 43.3 | 53.5 | 65.1 | +10.2 | +21.8 | 47% |
| L | mutation_score | 31.2 | 41.4 | 53.4 | +10.2 | +22.2 | 46% |
| L | pass_rate | 83.4 | 77.7 | 73.6 | -5.7 | -9.8 | – |

## Paired Wilcoxon tests over 20 classes (two-sided); Holm over the tests in this table

Â12 = P(second > first). R vs RN: effect of additional rounds alone; RN vs RC: effect of the coverage report.

| Comparison | Metric | mean Δ (pp) | median Δ (pp) | W | p | p (Holm) | Â12 | classes better / worse |
|---|---|---|---|---|---|---|---|---|
| S-R vs S-RN | line_cov | +8.3 | +4.1 | 0.0 | 0.000438 | 0.00394 | 0.60 | 16 / 0 |
| S-R vs S-RN | branch_cov | +8.6 | +6.0 | 0.0 | 0.000438 | 0.00394 | 0.61 | 16 / 0 |
| S-R vs S-RN | mutation_score | +6.5 | +3.7 | 0.0 | 0.000438 | 0.00394 | 0.62 | 16 / 0 |
| S-RN vs S-RC | line_cov | +0.9 | +0.3 | 76.0 | 0.679 | 1 | 0.51 | 10 / 8 |
| S-RN vs S-RC | branch_cov | +1.6 | +0.0 | 73.0 | 0.868 | 1 | 0.52 | 9 / 8 |
| S-RN vs S-RC | mutation_score | +3.6 | +0.0 | 69.0 | 0.472 | 1 | 0.54 | 9 / 9 |
| L-R vs L-RN | line_cov | +11.6 | +8.2 | 0.0 | 0.000293 | 0.00322 | 0.66 | 17 / 0 |
| L-R vs L-RN | branch_cov | +10.2 | +8.5 | 0.0 | 0.000438 | 0.00394 | 0.63 | 16 / 0 |
| L-R vs L-RN | mutation_score | +10.2 | +9.4 | 0.0 | 0.000293 | 0.00322 | 0.67 | 17 / 0 |
| L-RN vs L-RC | line_cov | +13.0 | +9.0 | 11.0 | 0.000105 | 0.00126 | 0.68 | 17 / 3 |
| L-RN vs L-RC | branch_cov | +11.6 | +7.4 | 17.0 | 0.0017 | 0.00678 | 0.64 | 15 / 4 |
| L-RN vs L-RC | mutation_score | +12.0 | +7.9 | 21.0 | 0.000851 | 0.00425 | 0.65 | 16 / 4 |

## Per seed: branch coverage RC − RN (mean over classes, pp)

| Model | seed 1 | seed 2 | seed 3 |
|---|---|---|---|
| S | +3.1 | +4.2 | -2.5 |
| L | +13.5 | +10.3 | +11.1 |

## Refinement-stage effort per class-run (mean)

| Model | RN rounds | RC rounds | RN LLM calls (stage) | RC LLM calls (stage) | RN output tokens (stage) | RC output tokens (stage) |
|---|---|---|---|---|---|---|
| S | 1.88 | 1.80 | 4.03 | 4.40 | 3017 | 3183 |
| L | 1.95 | 2.17 | 3.88 | 4.80 | 2578 | 4775 |

RN stopping reasons: round N added no covered branch: 85, other/none: 32, branch>=: 3

## Per class: branch coverage (%, mean over seeds)

| Class | S-R | S-RN | S-RC | L-R | L-RN | L-RC |
|---|---|---|---|---|---|---|
| AnnotationUtils | 12.1 | 12.1 | 12.1 | 29.8 | 30.5 | 29.8 |
| BooleanUtils | 13.9 | 15.6 | 24.3 | 17.6 | 32.2 | 36.5 |
| CharSequenceUtils | 4.1 | 4.1 | 4.1 | 5.7 | 6.6 | 32.2 |
| CharSetUtils | 59.0 | 86.1 | 65.3 | 88.2 | 89.6 | 89.6 |
| CharUtils | 68.8 | 75.0 | 83.3 | 78.5 | 88.9 | 95.1 |
| ClassUtils | 19.8 | 22.4 | 20.7 | 17.1 | 33.7 | 23.4 |
| CompareToBuilder | 7.4 | 10.6 | 15.6 | 24.5 | 56.3 | 54.8 |
| DiffBuilder | 14.3 | 23.0 | 26.3 | 55.0 | 62.0 | 64.0 |
| EqualsBuilder | 16.0 | 22.6 | 18.1 | 36.2 | 46.2 | 64.8 |
| ExceptionUtils | 40.9 | 48.2 | 51.8 | 35.8 | 68.5 | 67.6 |
| Failable | 25.0 | 27.8 | 25.0 | 52.8 | 52.8 | 77.8 |
| HashCodeBuilder | 9.9 | 16.3 | 11.2 | 49.3 | 49.3 | 64.6 |
| JavaVersion | 4.9 | 8.1 | 74.0 | 14.6 | 14.6 | 91.9 |
| LocaleUtils | 23.3 | 38.1 | 23.3 | 40.0 | 67.8 | 72.2 |
| ObjectUtils | 4.2 | 27.4 | 14.0 | 61.6 | 79.2 | 87.8 |
| RandomStringUtils | 79.9 | 85.6 | 87.9 | 88.5 | 89.7 | 93.7 |
| Range | 26.8 | 66.7 | 59.6 | 68.9 | 79.4 | 90.4 |
| ReflectionToStringBuilder | 40.4 | 40.4 | 44.9 | 42.3 | 45.5 | 55.8 |
| SystemUtils | 30.4 | 30.4 | 31.2 | 28.3 | 28.3 | 42.8 |
| Validate | 26.0 | 38.3 | 38.3 | 31.9 | 49.1 | 67.8 |

Note: wall-clock times of RN are not comparable with the main run (both models were served from one GPU); compare effort by rounds, calls and tokens.
