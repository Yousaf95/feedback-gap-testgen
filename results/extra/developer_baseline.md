# Reference point: Commons Lang's own tests

Post-hoc, no LLM calls. The developer-written test classes `<Class>*Test.java` of rel/commons-lang-3.14.0 (the same reference set as the contamination check) were run in the same harness on the same 20 classes, with the same JaCoCo 0.8.12 and PIT 1.15.8 (DEFAULTS) settings; target class and its nested classes only. The whole original test tree was compiled so that shared helpers resolve, and the project's own Java 9+ JVM flags (`--add-opens`) were used. Tests of other classes that also exercise a target class are not included. PIT ran with `skipFailingTests`.

Developer test methods executed: 3067 (failures 0, errors 0, skipped 4).

## Mean over the 20 classes (%)

| Suite | Line cov. | Branch cov. | Mutation score | Test strength |
|---|---|---|---|---|
| Developer tests | 92.4 | 86.5 | 77.7 | 83.2 |
| S-ZS | 34.0 | 25.5 | 17.8 | 58.8 |
| S-RC | 44.5 | 36.6 | 27.8 | 60.0 |
| L-ZS | 45.2 | 38.2 | 27.7 | 64.8 |
| L-RC | 75.2 | 65.1 | 53.4 | 68.2 |

## Paired comparison with the developer tests (Wilcoxon over 20 classes, two-sided; exploratory)

| Config | Metric | mean Δ (config − developer, pp) | p | Â12 (config > developer) | classes where config ≥ developer |
|---|---|---|---|---|---|
| S-RC | branch_cov | -49.9 | 1.91e-06 | 0.06 | 0/20 |
| S-RC | mutation_score | -49.9 | 1.91e-06 | 0.05 | 0/20 |
| L-RC | branch_cov | -21.3 | 0.000134 | 0.20 | 2/20 |
| L-RC | mutation_score | -24.3 | 2.67e-05 | 0.20 | 1/20 |

## Per class (%)

| Class | dev. test methods | Dev branch | S-RC | L-ZS | L-RC | Dev mut. | S-RC | L-ZS | L-RC |
|---|---|---|---|---|---|---|---|---|---|
| AnnotationUtils | 10 | 90.4 | 12.1 | 5.0 | 29.8 | 69.2 | 5.4 | 2.6 | 12.3 |
| BooleanUtils | 90 | 95.4 | 24.3 | 17.5 | 36.5 | 89.7 | 19.2 | 15.1 | 31.3 |
| CharSequenceUtils | 14 | 54.9 | 4.1 | 5.7 | 32.2 | 42.9 | 2.7 | 4.1 | 20.2 |
| CharSetUtils | 11 | 97.9 | 65.3 | 88.2 | 89.6 | 91.7 | 56.5 | 70.4 | 77.8 |
| CharUtils | 24 | 100.0 | 83.3 | 78.5 | 95.1 | 98.6 | 79.5 | 71.9 | 93.3 |
| ClassUtils | 66 | 93.0 | 20.7 | 14.9 | 23.4 | 90.0 | 17.6 | 15.7 | 24.2 |
| CompareToBuilder | 48 | 86.1 | 15.6 | 24.5 | 54.8 | 77.5 | 5.5 | 13.0 | 34.4 |
| DiffBuilder | 33 | 81.0 | 26.3 | 55.0 | 64.0 | 60.8 | 15.6 | 31.0 | 39.2 |
| EqualsBuilder | 57 | 79.8 | 18.1 | 25.4 | 64.8 | 72.3 | 13.2 | 15.6 | 49.5 |
| ExceptionUtils | 64 | 94.5 | 51.8 | 24.8 | 67.6 | 73.8 | 36.8 | 16.9 | 46.8 |
| Failable | 167 | 66.7 | 25.0 | 50.0 | 77.8 | 75.4 | 11.3 | 13.3 | 62.6 |
| HashCodeBuilder | 50 | 95.9 | 11.2 | 49.3 | 64.6 | 95.6 | 21.5 | 47.5 | 67.6 |
| JavaVersion | 3 | 82.9 | 74.0 | 13.0 | 91.9 | 80.5 | 69.1 | 15.4 | 89.4 |
| LocaleUtils | 2051 | 91.1 | 23.3 | 15.9 | 72.2 | 76.7 | 16.4 | 16.4 | 58.9 |
| ObjectUtils | 57 | 98.2 | 14.0 | 58.6 | 87.8 | 85.8 | 12.5 | 47.8 | 77.8 |
| RandomStringUtils | 18 | 100.0 | 87.9 | 88.5 | 93.7 | 60.3 | 38.1 | 40.7 | 58.7 |
| Range | 28 | 92.1 | 59.6 | 68.9 | 90.4 | 86.2 | 56.4 | 59.6 | 83.7 |
| ReflectionToStringBuilder | 44 | 75.0 | 44.9 | 26.3 | 55.8 | 52.0 | 32.9 | 15.1 | 45.3 |
| SystemUtils | 20 | 54.3 | 31.2 | 26.1 | 42.8 | 76.9 | 21.4 | 23.1 | 46.2 |
| Validate | 212 | 100.0 | 38.3 | 27.8 | 67.8 | 99.1 | 25.3 | 19.3 | 49.7 |
