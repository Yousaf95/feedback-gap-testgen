# Results summary

Rows: 360. Runs per config: {'L-R': 3, 'L-RC': 3, 'L-ZS': 3, 'S-R': 3, 'S-RC': 3, 'S-ZS': 3}. Classes per config: {'L-R': 20, 'L-RC': 20, 'L-ZS': 20, 'S-R': 20, 'S-RC': 20, 'S-ZS': 20}.
All values: per-class mean over runs, then mean ± std over classes. Rates and coverage in %.
compile rate = tests_compiled / tests_generated; passing rate = tests_passing / tests_generated (per class-run; 0 when nothing was generated). In RC configs tests_generated includes the test methods generated during coverage-guided refinement (T0 + all refinement rounds).
Times: wall-clock per class-run, measured with up to 8 concurrent requests per Ollama server (one GPU per model) and 16 parallel pipeline workers; they reflect relative cost between configs, not single-request latency.

## Table I: compilation, passing, coverage

| Config | Compile rate | Passing-test rate | Line cov. | Branch cov. | Tests generated | Tests passing | gen_ok |
|---|---|---|---|---|---|---|---|
| S-ZS | 78.3 ± 22.5 | 50.4 ± 25.8 | 34.0 ± 24.1 | 25.5 ± 23.1 | 17.3 ± 6.7 | 8.7 ± 5.7 | 100.0% |
| S-R | 80.1 ± 20.3 | 52.0 ± 24.0 | 35.3 ± 22.7 | 26.3 ± 21.6 | 17.3 ± 6.7 | 8.9 ± 5.5 | 100.0% |
| S-RC | 68.2 ± 24.1 | 46.8 ± 23.1 | 43.0 ± 26.0 | 36.0 ± 25.7 | 44.6 ± 18.3 | 23.1 ± 15.0 | 100.0% |
| L-ZS | 82.0 ± 21.7 | 71.4 ± 24.6 | 45.2 ± 26.1 | 38.2 ± 26.9 | 25.6 ± 13.5 | 19.4 ± 13.2 | 100.0% |
| L-R | 96.4 ± 8.4 | 83.4 ± 20.3 | 50.6 ± 22.6 | 43.3 ± 24.2 | 25.6 ± 13.5 | 21.6 ± 12.9 | 100.0% |
| L-RC | 86.5 ± 14.4 | 71.5 ± 21.3 | 72.0 ± 22.6 | 63.5 ± 25.7 | 66.2 ± 28.1 | 48.7 ± 25.8 | 100.0% |

## Table II: mutation analysis and cost (per class)

| Config | Mutation score | Test strength | Tokens in | Tokens out | LLM calls | Cost (USD) | LLM time (s) | Build time (s) | Total time (s) |
|---|---|---|---|---|---|---|---|---|---|
| S-ZS | 17.8 ± 17.2 | 58.8 ± 21.8 | 2710 ± 1369 | 1347 ± 616 | 1.0 ± 0.0 | 0.00 | 14 ± 6 | 33 ± 9 | 47 ± 11 |
| S-R | 17.7 ± 15.2 | 58.0 ± 18.8 | 12940 ± 8228 | 4345 ± 2579 | 3.1 ± 1.1 | 0.00 | 38 ± 23 | 38 ± 8 | 77 ± 25 |
| S-RC | 27.6 ± 22.0 | 60.1 ± 15.9 | 32562 ± 19989 | 7528 ± 4108 | 7.5 ± 2.7 | 0.00 | 68 ± 34 | 105 ± 67 | 175 ± 76 |
| L-ZS | 27.7 ± 21.1 | 64.8 ± 14.6 | 2710 ± 1369 | 1647 ± 815 | 1.0 ± 0.0 | 0.00 | 256 ± 99 | 33 ± 11 | 289 ± 98 |
| L-R | 31.2 ± 19.4 | 64.1 ± 16.4 | 7644 ± 6764 | 3222 ± 2287 | 2.0 ± 1.0 | 0.00 | 514 ± 335 | 35 ± 11 | 549 ± 334 |
| L-RC | 51.6 ± 25.2 | 67.6 ± 14.9 | 30854 ± 20286 | 7996 ± 4473 | 6.8 ± 3.0 | 0.00 | 1447 ± 706 | 98 ± 25 | 1546 ± 722 |

## Test-method counts (sums over all class-runs)

| Config | generated | compiled | passing | removed failing | removed flaky | repair rounds | cov rounds |
|---|---|---|---|---|---|---|---|
| S-ZS | 1036 | 786 | 519 | 267 | 0 | 0 | 0 |
| S-R | 1036 | 805 | 534 | 271 | 0 | 127 | 0 |
| S-RC | 2676 | 1876 | 1384 | 492 | 0 | 283 | 108 |
| L-ZS | 1537 | 1298 | 1163 | 135 | 0 | 0 | 0 |
| L-R | 1537 | 1446 | 1294 | 152 | 0 | 59 | 0 |
| L-RC | 3972 | 3467 | 2923 | 544 | 0 | 217 | 130 |

## Ablation deltas (percentage points, mean over classes of per-class differences)

| Step | compile_rate | pass_rate | line_cov | branch_cov | mutation_score | test_strength |
|---|---|---|---|---|---|---|
| S-ZS → S-R | +1.7 | +1.6 | +1.3 | +0.8 | -0.0 | -0.7 |
| S-R → S-RC | -11.9 | -5.2 | +7.8 | +9.7 | +9.9 | +2.1 |
| L-ZS → L-R | +14.4 | +12.0 | +5.4 | +5.1 | +3.5 | -0.7 |
| L-R → L-RC | -9.9 | -11.9 | +21.4 | +20.1 | +20.4 | +3.5 |

## Paired Wilcoxon signed-rank tests over classes (two-sided) and Vargha–Delaney Â12

Â12 = P(second config > first config). p (Holm) = Holm–Bonferroni adjusted over all tests in this table.

| Comparison | Metric | n | median Δ (pp) | W | p-value | p (Holm) | Â12 |
|---|---|---|---|---|---|---|---|
| S-ZS vs S-RC | line_cov | 20 | +5.8 | 17.0 | 0.001696 | 0.01696 | 0.606 |
| S-ZS vs S-RC | branch_cov | 20 | +6.6 | 13.0 | 0.0009673 | 0.01064 | 0.641 |
| S-ZS vs S-RC | mutation_score | 20 | +5.7 | 0.0 | 0.0001318 | 0.001846 | 0.656 |
| S-ZS vs S-RC | pass_rate | 20 | -5.0 | 79.0 | 0.3488 | 1 | 0.440 |
| L-ZS vs L-RC | line_cov | 20 | +29.3 | 19.0 | 0.0005856 | 0.007027 | 0.802 |
| L-ZS vs L-RC | branch_cov | 20 | +25.7 | 7.0 | 3.624e-05 | 0.0005798 | 0.762 |
| L-ZS vs L-RC | mutation_score | 20 | +21.4 | 5.0 | 1.907e-05 | 0.0003242 | 0.762 |
| L-ZS vs L-RC | pass_rate | 20 | +0.9 | 91.0 | 0.6215 | 1 | 0.482 |
| S-RC vs L-RC | line_cov | 20 | +28.2 | 11.0 | 0.0001049 | 0.001574 | 0.807 |
| S-RC vs L-RC | branch_cov | 20 | +26.2 | 3.0 | 9.537e-06 | 0.0001907 | 0.780 |
| S-RC vs L-RC | mutation_score | 20 | +21.0 | 3.0 | 9.537e-06 | 0.0001907 | 0.767 |
| S-RC vs L-RC | pass_rate | 20 | +16.0 | 3.0 | 9.537e-06 | 0.0001907 | 0.795 |
| S-RC vs L-ZS | line_cov | 20 | +0.3 | 94.0 | 0.7012 | 1 | 0.527 |
| S-RC vs L-ZS | branch_cov | 20 | +1.1 | 91.0 | 0.6215 | 1 | 0.542 |
| S-RC vs L-ZS | mutation_score | 20 | +1.7 | 88.0 | 0.7782 | 1 | 0.499 |
| S-RC vs L-ZS | pass_rate | 20 | +14.7 | 15.0 | 0.0002613 | 0.003397 | 0.762 |
| S-R vs L-ZS | line_cov | 20 | +7.6 | 47.0 | 0.02958 | 0.207 | 0.644 |
| S-R vs L-ZS | branch_cov | 20 | +8.4 | 47.0 | 0.02958 | 0.207 | 0.642 |
| S-R vs L-ZS | mutation_score | 20 | +8.0 | 27.0 | 0.00621 | 0.05589 | 0.654 |
| S-R vs L-ZS | pass_rate | 20 | +11.4 | 37.0 | 0.009436 | 0.07549 | 0.720 |

## Per-class branch coverage (%, mean over runs)

| Class | S-ZS | S-R | S-RC | L-ZS | L-R | L-RC |
|---|---|---|---|---|---|---|
| AnnotationUtils | 11.3 | 12.1 | 12.1 | 5.0 | 29.8 | 29.8 |
| BooleanUtils | 16.8 | 13.9 | 24.3 | 17.5 | 17.6 | 36.5 |
| CharSequenceUtils | 4.1 | 4.1 | 4.1 | 5.7 | 5.7 | 32.2 |
| CharSetUtils | 59.0 | 59.0 | 65.3 | 88.2 | 88.2 | 89.6 |
| CharUtils | 76.4 | 68.8 | 83.3 | 78.5 | 78.5 | 95.1 |
| ClassUtils | 19.8 | 19.8 | 20.7 | 14.9 | 17.1 | 23.4 |
| CompareToBuilder | 7.4 | 7.4 | 15.6 | 24.5 | 24.5 | 54.8 |
| DiffBuilder | 14.3 | 14.3 | 26.3 | 55.0 | 55.0 | 64.0 |
| EqualsBuilder | 16.0 | 16.0 | 18.1 | 25.4 | 36.2 | 64.8 |
| ExceptionUtils | 40.9 | 40.9 | 51.8 | 24.8 | 35.8 | 67.6 |
| Failable | 0.0 | 25.0 | 25.0 | 50.0 | 52.8 | 77.8 |
| HashCodeBuilder | 10.2 | 9.9 | 11.2 | 49.3 | 49.3 | 64.6 |
| JavaVersion | 3.3 | 4.9 | 74.0 | 13.0 | 14.6 | 91.9 |
| LocaleUtils | 22.6 | 23.3 | 23.3 | 15.9 | 40.0 | 72.2 |
| ObjectUtils | 8.0 | 4.2 | 14.0 | 58.6 | 61.6 | 87.8 |
| RandomStringUtils | 79.3 | 79.9 | 87.9 | 88.5 | 88.5 | 93.7 |
| Range | 24.6 | 26.8 | 59.6 | 68.9 | 68.9 | 90.4 |
| ReflectionToStringBuilder | 40.4 | 40.4 | 44.9 | 26.3 | 42.3 | 55.8 |
| SystemUtils | 30.4 | 30.4 | 20.3 | 26.1 | 28.3 | 9.4 |
| Validate | 25.7 | 26.0 | 38.3 | 27.8 | 31.9 | 67.8 |

## Per-class mutation score (%, mean over runs)

| Class | S-ZS | S-R | S-RC | L-ZS | L-R | L-RC |
|---|---|---|---|---|---|---|
| AnnotationUtils | 3.4 | 5.4 | 5.4 | 2.6 | 12.3 | 12.3 |
| BooleanUtils | 16.2 | 12.9 | 19.2 | 15.1 | 15.0 | 31.3 |
| CharSequenceUtils | 2.7 | 2.7 | 2.7 | 4.1 | 4.1 | 20.2 |
| CharSetUtils | 47.2 | 47.2 | 56.5 | 70.4 | 70.4 | 77.8 |
| CharUtils | 72.4 | 61.0 | 79.5 | 71.9 | 71.9 | 93.3 |
| ClassUtils | 16.6 | 16.7 | 17.6 | 15.7 | 18.0 | 24.2 |
| CompareToBuilder | 3.4 | 3.4 | 5.5 | 13.0 | 13.0 | 34.4 |
| DiffBuilder | 10.3 | 10.3 | 15.6 | 31.0 | 31.0 | 39.2 |
| EqualsBuilder | 9.1 | 9.1 | 13.2 | 15.6 | 22.2 | 49.5 |
| ExceptionUtils | 28.3 | 29.6 | 36.8 | 16.9 | 25.1 | 46.8 |
| Failable | 1.5 | 11.3 | 11.3 | 13.3 | 24.6 | 62.6 |
| HashCodeBuilder | 19.5 | 19.5 | 21.5 | 47.5 | 47.5 | 67.6 |
| JavaVersion | 1.6 | 2.4 | 69.1 | 15.4 | 17.1 | 89.4 |
| LocaleUtils | 16.0 | 16.4 | 16.4 | 16.4 | 30.1 | 58.9 |
| ObjectUtils | 6.4 | 3.3 | 12.5 | 47.8 | 49.9 | 77.8 |
| RandomStringUtils | 31.2 | 31.2 | 38.1 | 40.7 | 42.3 | 58.7 |
| Range | 19.1 | 21.3 | 56.4 | 59.6 | 59.6 | 83.7 |
| ReflectionToStringBuilder | 23.1 | 23.1 | 32.9 | 15.1 | 22.2 | 45.3 |
| SystemUtils | 12.0 | 12.0 | 17.1 | 23.1 | 25.6 | 9.4 |
| Validate | 15.2 | 15.5 | 25.3 | 19.3 | 23.0 | 49.7 |

## Compile-error categories (error messages in generated test files, all compile attempts; ZS/R/RC logs counted separately per config; RC counts only refinement-stage compiles)

| Category | S-ZS | S-R | S-RC | L-ZS | L-R | L-RC |
|---|---|---|---|---|---|---|
| cannot find symbol | 216 | 364 | 478 | 132 | 144 | 425 |
| incompatible types | 20 | 19 | 31 | 0 | 0 | 60 |
| private access | 34 | 41 | 213 | 43 | 60 | 407 |
| unreported exception | 7 | 7 | 1 | 10 | 10 | 1 |
| ambiguous reference | 14 | 20 | 87 | 7 | 8 | 32 |
| syntax / parse | 37 | 67 | 0 | 8 | 2 | 38 |
| abstract / no instance | 0 | 0 | 0 | 0 | 0 | 0 |
| method not applicable | 88 | 143 | 128 | 7 | 14 | 81 |
| other | 39 | 94 | 18 | 24 | 44 | 136 |

## Pipeline notes (count of class-runs)

- L-R: TN code block unterminated — 1
- L-R: TN hit max_tokens — 1
- L-R: context truncated: N methods signature-only — 6
- L-R: empty suite: placeholder test used for measurement — 1
- L-RC: RC: no green R suite to refine — 1
- L-RC: TN code block unterminated — 1
- L-RC: TN hit max_tokens — 1
- L-RC: all tests treated as failing — 2
- L-RC: context truncated: N methods signature-only — 6
- L-RC: cov stop: branch>=N.N — 13
- L-RC: cov stop: round N added no covered branch — 25
- L-RC: empty suite: placeholder test used for measurement — 3
- L-RC: no surefire report — 2
- L-RC: reverting to base — 1
- L-RC: stageN prune touched protected methods — 1
- L-ZS: TN code block unterminated — 1
- L-ZS: TN hit max_tokens — 1
- L-ZS: context truncated: N methods signature-only — 6
- L-ZS: empty suite: placeholder test used for measurement — 3
- S-R: TN code block unterminated — 1
- S-R: TN hit max_tokens — 1
- S-R: all tests treated as failing — 1
- S-R: context truncated: N methods signature-only — 6
- S-R: empty suite: placeholder test used for measurement — 6
- S-R: no surefire report — 1
- S-RC: RC: no green R suite to refine — 6
- S-RC: TN code block unterminated — 1
- S-RC: TN hit max_tokens — 1
- S-RC: all tests treated as failing — 2
- S-RC: context truncated: N methods signature-only — 6
- S-RC: cov stop: branch>=N.N — 3
- S-RC: cov stop: round N added no covered branch — 43
- S-RC: empty suite: placeholder test used for measurement — 7
- S-RC: no surefire report — 2
- S-ZS: TN code block unterminated — 1
- S-ZS: TN hit max_tokens — 1
- S-ZS: all tests treated as failing — 1
- S-ZS: context truncated: N methods signature-only — 6
- S-ZS: empty suite: placeholder test used for measurement — 9
- S-ZS: no surefire report — 1
