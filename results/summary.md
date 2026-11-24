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
| S-RC | 68.2 ± 24.1 | 47.1 ± 22.9 | 44.5 ± 27.6 | 36.6 ± 25.5 | 44.6 ± 18.3 | 23.1 ± 14.9 | 100.0% |
| L-ZS | 82.0 ± 21.7 | 71.4 ± 24.6 | 45.2 ± 26.1 | 38.2 ± 26.9 | 25.6 ± 13.5 | 19.4 ± 13.2 | 100.0% |
| L-R | 96.4 ± 8.4 | 83.4 ± 20.3 | 50.6 ± 22.6 | 43.3 ± 24.2 | 25.6 ± 13.5 | 21.6 ± 12.9 | 100.0% |
| L-RC | 86.5 ± 14.4 | 73.6 ± 17.5 | 75.2 ± 20.9 | 65.1 ± 22.9 | 66.2 ± 28.1 | 49.4 ± 24.7 | 100.0% |

## Table II: mutation analysis and cost (per class)

| Config | Mutation score | Test strength | Tokens in | Tokens out | LLM calls | Cost (USD) | LLM time (s) | Build time (s) | Total time (s) |
|---|---|---|---|---|---|---|---|---|---|
| S-ZS | 17.8 ± 17.2 | 58.8 ± 21.8 | 2710 ± 1369 | 1347 ± 616 | 1.0 ± 0.0 | 0.00 | 14 ± 6 | 33 ± 9 | 47 ± 11 |
| S-R | 17.7 ± 15.2 | 58.0 ± 18.8 | 12940 ± 8228 | 4345 ± 2579 | 3.1 ± 1.1 | 0.00 | 38 ± 23 | 38 ± 8 | 77 ± 25 |
| S-RC | 27.8 ± 21.9 | 60.0 ± 15.9 | 32562 ± 19989 | 7528 ± 4108 | 7.5 ± 2.7 | 0.00 | 68 ± 34 | 105 ± 67 | 175 ± 76 |
| L-ZS | 27.7 ± 21.1 | 64.8 ± 14.6 | 2710 ± 1369 | 1647 ± 815 | 1.0 ± 0.0 | 0.00 | 256 ± 99 | 33 ± 11 | 289 ± 98 |
| L-R | 31.2 ± 19.4 | 64.1 ± 16.4 | 7644 ± 6764 | 3222 ± 2287 | 2.0 ± 1.0 | 0.00 | 514 ± 335 | 35 ± 11 | 549 ± 334 |
| L-RC | 53.4 ± 23.2 | 68.2 ± 14.2 | 30854 ± 20286 | 7996 ± 4473 | 6.8 ± 3.0 | 0.00 | 1447 ± 706 | 98 ± 25 | 1546 ± 722 |

## Test-method counts (sums over all class-runs)

| Config | generated | compiled | passing | removed failing | removed flaky | repair rounds | cov rounds |
|---|---|---|---|---|---|---|---|
| S-ZS | 1036 | 786 | 519 | 267 | 0 | 0 | 0 |
| S-R | 1036 | 805 | 534 | 271 | 0 | 127 | 0 |
| S-RC | 2676 | 1876 | 1389 | 487 | 0 | 283 | 108 |
| L-ZS | 1537 | 1298 | 1163 | 135 | 0 | 0 | 0 |
| L-R | 1537 | 1446 | 1294 | 152 | 0 | 59 | 0 |
| L-RC | 3972 | 3467 | 2965 | 502 | 0 | 217 | 130 |

## Ablation deltas (percentage points, mean over classes of per-class differences)

| Step | compile_rate | pass_rate | line_cov | branch_cov | mutation_score | test_strength |
|---|---|---|---|---|---|---|
| S-ZS → S-R | +1.7 | +1.6 | +1.3 | +0.8 | -0.0 | -0.7 |
| S-R → S-RC | -11.9 | -4.8 | +9.2 | +10.2 | +10.1 | +2.0 |
| L-ZS → L-R | +14.4 | +12.0 | +5.4 | +5.1 | +3.5 | -0.7 |
| L-R → L-RC | -9.9 | -9.8 | +24.7 | +21.8 | +22.2 | +4.1 |

## Paired Wilcoxon signed-rank tests over classes (two-sided) and Vargha–Delaney Â12

Â12 = P(second config > first config). p (Holm) = Holm–Bonferroni adjusted over all tests in this table. The first four comparisons test each feedback stage on its own (ZS→R, R→RC).

| Comparison | Metric | n | mean Δ (pp) | median Δ (pp) | W | p-value | p (Holm) | Â12 |
|---|---|---|---|---|---|---|---|---|
| S-ZS vs S-R | line_cov | 20 | +1.3 | +0.0 | 33.0 | 0.3824 | 1 | 0.525 |
| S-ZS vs S-R | branch_cov | 20 | +0.8 | +0.0 | 29.0 | 0.7221 | 1 | 0.531 |
| S-ZS vs S-R | mutation_score | 20 | -0.0 | +0.0 | 28.0 | 0.6566 | 1 | 0.519 |
| S-ZS vs S-R | pass_rate | 20 | +1.6 | +0.0 | 24.0 | 0.2393 | 1 | 0.530 |
| S-R vs S-RC | line_cov | 20 | +9.2 | +5.1 | 0.0 | 0.0004378 | 0.009631 | 0.610 |
| S-R vs S-RC | branch_cov | 20 | +10.2 | +7.1 | 0.0 | 0.0004378 | 0.009631 | 0.625 |
| S-R vs S-RC | mutation_score | 20 | +10.1 | +6.6 | 0.0 | 0.0004378 | 0.009631 | 0.660 |
| S-R vs S-RC | pass_rate | 20 | -4.8 | -5.8 | 74.0 | 0.2611 | 1 | 0.435 |
| L-ZS vs L-R | line_cov | 20 | +5.4 | +0.7 | 1.0 | 0.004439 | 0.07103 | 0.604 |
| L-ZS vs L-R | branch_cov | 20 | +5.1 | +1.9 | 0.0 | 0.002218 | 0.0377 | 0.588 |
| L-ZS vs L-R | mutation_score | 20 | +3.5 | +1.9 | 1.0 | 0.001871 | 0.03369 | 0.591 |
| L-ZS vs L-R | pass_rate | 20 | +12.0 | +4.6 | 1.0 | 0.0008053 | 0.0153 | 0.661 |
| L-R vs L-RC | line_cov | 20 | +24.7 | +26.9 | 0.0 | 0.0001318 | 0.003823 | 0.791 |
| L-R vs L-RC | branch_cov | 20 | +21.8 | +20.2 | 0.0 | 0.0001318 | 0.003823 | 0.754 |
| L-R vs L-RC | mutation_score | 20 | +22.2 | +21.4 | 0.0 | 0.0001318 | 0.003823 | 0.764 |
| L-R vs L-RC | pass_rate | 20 | -9.8 | -9.9 | 32.0 | 0.00486 | 0.0729 | 0.287 |
| S-ZS vs S-RC | line_cov | 20 | +10.4 | +5.8 | 0.0 | 0.0001318 | 0.003823 | 0.616 |
| S-ZS vs S-RC | branch_cov | 20 | +11.0 | +6.6 | 0.0 | 0.0001318 | 0.003823 | 0.651 |
| S-ZS vs S-RC | mutation_score | 20 | +10.1 | +6.5 | 0.0 | 0.0001318 | 0.003823 | 0.661 |
| S-ZS vs S-RC | pass_rate | 20 | -3.3 | -5.0 | 79.0 | 0.3488 | 1 | 0.448 |
| L-ZS vs L-RC | line_cov | 20 | +30.1 | +29.3 | 0.0 | 1.907e-06 | 6.866e-05 | 0.833 |
| L-ZS vs L-RC | branch_cov | 20 | +26.9 | +25.7 | 0.0 | 1.907e-06 | 6.866e-05 | 0.787 |
| L-ZS vs L-RC | mutation_score | 20 | +25.7 | +22.3 | 0.0 | 1.907e-06 | 6.866e-05 | 0.795 |
| L-ZS vs L-RC | pass_rate | 20 | +2.2 | +0.9 | 86.0 | 0.498 | 1 | 0.492 |
| S-RC vs L-RC | line_cov | 20 | +30.8 | +28.2 | 0.0 | 1.907e-06 | 6.866e-05 | 0.818 |
| S-RC vs L-RC | branch_cov | 20 | +28.6 | +26.2 | 0.0 | 1.907e-06 | 6.866e-05 | 0.805 |
| S-RC vs L-RC | mutation_score | 20 | +25.6 | +22.5 | 0.0 | 1.907e-06 | 6.866e-05 | 0.797 |
| S-RC vs L-RC | pass_rate | 20 | +26.5 | +17.2 | 0.0 | 1.907e-06 | 6.866e-05 | 0.823 |
| S-RC vs L-ZS | line_cov | 20 | +0.7 | +0.0 | 92.0 | 0.9039 | 1 | 0.514 |
| S-RC vs L-ZS | branch_cov | 20 | +1.6 | -2.1 | 95.0 | 0.7285 | 1 | 0.527 |
| S-RC vs L-ZS | mutation_score | 20 | -0.1 | +1.5 | 91.0 | 0.8721 | 1 | 0.496 |
| S-RC vs L-ZS | pass_rate | 20 | +24.3 | +14.7 | 15.0 | 0.0002613 | 0.00601 | 0.760 |
| S-R vs L-ZS | line_cov | 20 | +9.9 | +7.6 | 47.0 | 0.02958 | 0.3549 | 0.644 |
| S-R vs L-ZS | branch_cov | 20 | +11.8 | +8.4 | 47.0 | 0.02958 | 0.3549 | 0.642 |
| S-R vs L-ZS | mutation_score | 20 | +10.0 | +8.0 | 27.0 | 0.00621 | 0.08694 | 0.654 |
| S-R vs L-ZS | pass_rate | 20 | +19.5 | +11.4 | 37.0 | 0.009436 | 0.1227 | 0.720 |

## Equivalence analysis: S-RC vs L-ZS (post-hoc)

Paired per-class differences d = S-RC − L-ZS (pp). 95% CI of the mean difference by bootstrap over classes (10,000 resamples, seed 2027). TOST = two one-sided Wilcoxon signed-rank tests against an equivalence margin ±δ; equivalence is shown only if p(TOST) < 0.05.

| Metric | mean d | 95% CI | p(TOST, δ = 5 pp) | p(TOST, δ = 10 pp) | smallest δ (integer pp) with p(TOST) < 0.05 |
|---|---|---|---|---|---|
| line_cov | -0.7 | [-9.1, +7.8] | 0.131 | 0.038 | ±10 |
| branch_cov | -1.6 | [-11.6, +8.7] | 0.273 | 0.057 | ±11 |
| mutation_score | +0.1 | [-7.2, +8.4] | 0.057 | 0.004 | ±6 |

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
| SystemUtils | 30.4 | 30.4 | 31.2 | 26.1 | 28.3 | 42.8 |
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
| SystemUtils | 12.0 | 12.0 | 21.4 | 23.1 | 25.6 | 46.2 |
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
- L-RC: empty suite: placeholder test used for measurement — 1
- L-RC: new methods discarded (row recomputed after bug fix — 2
- L-RC: no new LLM calls — 2
- L-RC: no surefire report — 2
- L-RC: reverting to base — 1
- L-RC: stageN prune touched protected methods — 1
- L-RC: stageN: no surefire report — 2
- L-RC: time fields from the original run) — 2
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
- S-RC: empty suite: placeholder test used for measurement — 6
- S-RC: new methods discarded (row recomputed after bug fix — 1
- S-RC: no new LLM calls — 1
- S-RC: no surefire report — 2
- S-RC: stageN: no surefire report — 1
- S-RC: time fields from the original run) — 1
- S-ZS: TN code block unterminated — 1
- S-ZS: TN hit max_tokens — 1
- S-ZS: all tests treated as failing — 1
- S-ZS: context truncated: N methods signature-only — 6
- S-ZS: empty suite: placeholder test used for measurement — 9
- S-ZS: no surefire report — 1

## Deviations from the spec

1. **No commercial API model.** No API key was available. The "API" tier (A-ZS/A-R/A-RC) is replaced by a larger open model, `qwen2.5-coder:32b-instruct`, served by the same Ollama instance (configs renamed L-ZS/L-R/L-RC). RQ3 therefore compares a 7B vs a 32B open model. `est_cost_usd` is 0 for all rows; cost is reported as tokens and wall time.
2. **Small model tag.** `qwen2.5-coder:7b-instruct` (spec example).
3. **JDK.** Temurin 17.0.21.1 (installed in user space); the system JRE had no `javac`.
4. **Parser.** tree-sitter-java instead of javalang (javalang fails to parse Commons Lang 3.14 sources, e.g. method references `String[]::new`).
5. **Class selection.** Branch count uses tree-sitter AST nodes (if/for/foreach/while/do/case label/catch/ternary/&&/||). `org.apache.commons.lang3.ThreadUtils` was excluded manually (heavy concurrency criterion) before any evaluation run. Only 24 classes passed all filters, so the dev set is the 3 classes alphabetically after the first 20.
6. **Shared T0 across ZS/R/RC (paired ablation).** For each (class, model, seed) the initial generation T0 is made once and used by ZS, R and RC; RC starts its coverage loop from the green R suite. Per-config costs are T0 + that config's own stages + its final measurement (RC = T0 + R stages + RC stages + RC final measurement).
7. **Pruning** (Stage 3) is iterated to a fixpoint (max 5 passes) and also removes import/field declarations that contain error lines; after pruning, if the file still does not compile, the class gets 0 compilable tests.
8. **Normalization before compiling** (all configs): the package declaration is added/fixed and the top-level class is renamed to `<C>_GenTest` if the model chose another name. Any fenced code block is accepted (largest one containing `class`); an unterminated final block (output truncated) is accepted and logged.
9. **Repair and refine prompts include the class context** (same pruned context as generation), in addition to the test file + errors (repair) or coverage data (refine). Compiler errors are given as `line N: message (symbol/location details)` plus the offending source line.
10. **Stage-5 repair** (the only change made during dev-set tuning): the repair prompt shows only the new methods (plus the suite's imports and fields), with errors labelled by method name; already-green methods are kept verbatim and only new methods are taken from the response; pruning never removes previously green methods. Motivation (dev set, DateUtils, 7B): when shown the full merged file, the model returned the old suite and dropped all new methods.
11. **JUnit default timeout** of 10 s per test (separate thread) so non-terminating generated tests fail instead of hanging the build; such tests are removed as failing.
12. **Coverage scope.** JaCoCo line/branch coverage uses the counters of the target source file (top-level class plus nested/anonymous classes). PIT `targetClasses` = `<C>,<C>$*`.
13. **Mutation score** = KILLED / total (TIMED_OUT, MEMORY_ERROR, RUN_ERROR not counted as killed; reported separately). Test strength = KILLED / (total − NO_COVERAGE).
14. **Empty suites.** When no test survives, a placeholder empty test is used only to let JaCoCo/PIT report the totals; coverage is recorded as 0.
15. **Token counts** are Ollama's `prompt_eval_count`/`eval_count`. Ollama may reuse a cached prompt prefix, in which case `prompt_eval_count` can undercount input tokens. Context budget (6k tokens) is estimated as chars/3.5.
16. **Seeds / determinism.** Seed is passed to Ollama, but requests are batched (OLLAMA_NUM_PARALLEL=8), so outputs are not guaranteed to be reproducible.
17. **EvoSuite (RQ4)** not attempted: EvoSuite comparison left to future work.
18. **Dev runs.** Two early dev runs (results/dev/artifacts_dev1, results/dev/run2) were aborted because of an Ollama serving problem (both models on one GPU caused continuous model eviction, ~8 tok/s). Fixed by serving each model on its own GPU; the complete dev run is results/dev/run3. No eval-class results existed at that point.
19. **Class-level compile errors.** Errors outside methods/imports/fields (e.g. a class annotation with a missing import) cannot be pruned at method level; per the spec the class then gets 0 compilable tests.
20. **Post-run bug fix (Stage 5, 3 rows).** When a refinement round's new tests crashed the test JVM (generated SystemUtils tests calling `System.setSecurityManager`, unsupported on Java 17), Surefire wrote no report and the pipeline treated *all* tests, including the already-green suite, as failing, emptying the suite. This contradicts the spec (Stage 4 applies to the new methods only). Fixed rule: discard the new methods, keep the previous green suite. The 3 affected rows (L-RC SystemUtils seeds 2 and 3, S-RC SystemUtils seed 3) were recomputed by re-measuring that previous green suite (JaCoCo + PIT) with `scripts/recompute_stage5_noreport.py`; no new LLM calls; LLM/time fields are from the original run. Original rows kept in `results/raw/runs.pre_fix.jsonl`; before/after values in each affected artifact folder (`*_recompute_row.json`).
21. **JVM crash in the initial Stage 4 (3 rows, kept as is).** For S-ZS/S-R/S-RC DiffBuilder seed 1, the test JVM produced no report, so no test could be confirmed passing; per the Stage-4 rule all tests were removed and the class got 0 passing tests.
22. **Interference between tests.** Stage-5 Stage-4 cleanup removes any failing method; in one row (S-RC SystemUtils seed 1) two previously green tests failed after new tests modified system properties and were removed (coverage unchanged).
23. **Redacted paths in published logs.** In the repository copies of the artifact logs, cluster-specific path prefixes (PBS scratch directories, home directory, node host names) were replaced with `/work/`, `/home/user` and `cluster-node`. No other content was changed; compile-error categories in summary.md are unaffected (re-running analyze.py on the redacted logs gives the same table).
24. **Post-hoc additions after the main analysis** (no new LLM calls): Holm–Bonferroni adjusted p-values added to the Wilcoxon table (over its 20 tests); the paired comparisons S-RC vs L-ZS and S-R vs L-ZS added; exploratory analyses in `results/extra/` (coverage per round, cost-effectiveness, removed-test failure types and 50-test review, seed stability, coverage vs complexity, contamination check).
25. **Post-hoc reference point: developer-written tests** (no LLM calls). Commons Lang's own `<Class>*Test.java` classes for the 20 evaluation classes were run in the same harness with the same JaCoCo/PIT settings (`scripts/dev_baseline.py`, `results/extra/developer_baseline.md`). Differences from the LLM runs: the project's own Java 9+ `--add-opens` JVM flags were used (as in the Commons Lang pom), the whole original test tree was compiled for shared helpers, and PIT ran with `skipFailingTests` (no test failed). Tests of other classes that also exercise a target class are not included.
26. **Sensitivity check for deviation 20**: with the original values of the three recomputed rows all conclusions are unchanged (largest Holm-adjusted p of the main comparisons 0.011 instead of 0.0017); see `results/extra/sensitivity_prefix.md`.
27. **Post-hoc statistics added in response to review**: stage-wise paired tests (S-ZS vs S-R, S-R vs S-RC, L-ZS vs L-R, L-R vs L-RC) were added to the Wilcoxon table, which enlarges the Holm–Bonferroni family from 20 to 36 tests (adjusted p-values of the earlier comparisons change accordingly; e.g. ZS vs RC for the 7B model 0.0038 instead of 0.0017); mean differences are reported next to medians; an equivalence analysis (bootstrap 95% CI and TOST) was added for S-RC vs L-ZS.
