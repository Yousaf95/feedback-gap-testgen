# Post-hoc robustness checks

Computed after the main analysis from existing data (no new LLM calls); not part of the frozen plan.

## 4. Seed stability (3 seeds per class and config)

Per class: standard deviation and range (max − min) across the 3 seeds, in percentage points; then mean / max over the 20 classes. Last column: share of (class) cells whose seed range exceeds 10 pp.

| Config | Branch cov. std (mean) | Branch cov. range (mean / max) | Mutation score std (mean) | Mutation range (mean / max) | Branch range > 10 pp |
|---|---|---|---|---|---|
| S-ZS | 11.9 | 22.2 / 65.4 | 8.4 | 15.8 / 44.7 | 13/20 |
| S-R | 13.4 | 24.6 / 75.0 | 8.2 | 15.2 / 38.3 | 13/20 |
| S-RC | 15.1 | 27.7 / 75.0 | 10.7 | 19.8 / 50.7 | 13/20 |
| L-ZS | 10.4 | 19.1 / 57.7 | 8.0 | 15.2 / 39.9 | 14/20 |
| L-R | 9.5 | 17.7 / 53.8 | 7.8 | 15.1 / 39.9 | 13/20 |
| L-RC | 9.4 | 17.8 / 65.4 | 8.5 | 15.9 / 50.9 | 12/20 |

Ablation deltas per seed (branch coverage, mean over classes, pp):

| Step | seed 1 | seed 2 | seed 3 |
|---|---|---|---|
| S-ZS → S-R | +0.1 | +3.1 | -0.7 |
| S-R → S-RC | +10.7 | +13.0 | +7.0 |
| L-ZS → L-R | +1.5 | +5.7 | +8.3 |
| L-R → L-RC | +22.3 | +20.7 | +22.3 |
| S-RC → L-ZS | +4.7 | -7.1 | +7.3 |
| S-RC → L-RC | +28.5 | +19.3 | +37.9 |

## 5. Coverage vs class size and complexity (Spearman ρ over the 20 classes)

NCLOC and branch counts from results/classes.json.

| Config | ρ(branch cov., NCLOC) | p | ρ(branch cov., #branches) | p | ρ(mutation score, #branches) | p |
|---|---|---|---|---|---|---|
| S-ZS | -0.25 | 0.295 | -0.27 | 0.242 | -0.24 | 0.315 |
| S-R | -0.38 | 0.094 | -0.51 | 0.021 | -0.38 | 0.098 |
| S-RC | -0.53 | 0.016 | -0.56 | 0.010 | -0.49 | 0.029 |
| L-ZS | -0.28 | 0.227 | -0.52 | 0.018 | -0.40 | 0.078 |
| L-R | -0.38 | 0.097 | -0.56 | 0.011 | -0.58 | 0.007 |
| L-RC | -0.63 | 0.003 | -0.58 | 0.008 | -0.61 | 0.004 |

Classes whose context was truncated to signatures (ClassUtils, Validate) vs the others, mean branch coverage (%):

| Config | truncated context | full context |
|---|---|---|
| S-ZS | 22.7 | 25.8 |
| S-R | 22.9 | 26.7 |
| S-RC | 29.5 | 37.3 |
| L-ZS | 21.3 | 40.1 |
| L-R | 24.5 | 45.4 |
| L-RC | 45.6 | 67.3 |

## 6. Contamination check: generated tests vs Commons Lang's own tests

Reference: 1187 test methods in `<Class>*Test.java` of rel/commons-lang-3.14.0 for the 20 classes. Each passing generated test method in the final suites is compared with every original test method of the same class: exact token-sequence match (comments/whitespace ignored), Jaccard similarity of 5-token shingles on raw tokens, and on abstracted tokens (identifiers→ID, literals→STR/NUM; captures renamed copies). Methods with < 10 tokens skipped.

| Config | test methods | exact copies | same method name as an original | max Jaccard raw ≥ 0.5 | ≥ 0.8 | max Jaccard abstract ≥ 0.8 | median max Jaccard raw |
|---|---|---|---|---|---|---|---|
| S-ZS | 519 | 2 (0.4%) | 84 (16.2%) | 2 (0.4%) | 2 (0.4%) | 2 (0.4%) | 0.05 |
| S-R | 534 | 2 (0.4%) | 82 (15.4%) | 2 (0.4%) | 2 (0.4%) | 2 (0.4%) | 0.05 |
| S-RC | 1379 | 7 (0.5%) | 97 (7.0%) | 11 (0.8%) | 7 (0.5%) | 13 (0.9%) | 0.05 |
| L-ZS | 1160 | 2 (0.2%) | 66 (5.7%) | 11 (0.9%) | 2 (0.2%) | 22 (1.9%) | 0.06 |
| L-R | 1291 | 2 (0.2%) | 72 (5.6%) | 11 (0.9%) | 2 (0.2%) | 22 (1.7%) | 0.06 |
| L-RC | 2913 | 10 (0.3%) | 72 (2.5%) | 20 (0.7%) | 10 (0.3%) | 55 (1.9%) | 0.06 |

Most similar generated methods:

| Config | Class | Seed | Method | Jaccard raw | Jaccard abstract | exact |
|---|---|---|---|---|---|---|
| L-RC | ExceptionUtils | 3 | testIsChecked_withNullThrowable_2 | 1.00 | 1.00 | True |
| L-RC | ExceptionUtils | 3 | testIsUnchecked_withNullThrowable_2 | 1.00 | 1.00 | True |
| L-ZS | BooleanUtils | 3 | testAndWithNullBooleanObjectArray | 1.00 | 1.00 | True |
| S-R | BooleanUtils | 3 | testPrimitiveValues | 1.00 | 1.00 | True |
| L-ZS | BooleanUtils | 3 | testOrWithNullBooleanObjectArray | 1.00 | 1.00 | True |
| S-RC | BooleanUtils | 3 | testPrimitiveValues | 1.00 | 1.00 | True |
| S-ZS | BooleanUtils | 3 | testPrimitiveValues | 1.00 | 1.00 | True |
| L-RC | ExceptionUtils | 1 | testIsChecked_withNullThrowable | 1.00 | 1.00 | True |
