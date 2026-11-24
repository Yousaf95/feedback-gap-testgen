# Post-hoc quantization check: 7B model in FP16 vs 4-bit (Q4_K_M)

Same pipeline, prompts, classes, seeds and configurations as the main run; only the model weights differ (`qwen2.5-coder:7b-instruct-fp16`). F = FP16, S = 4-bit. RN = refinement without coverage report (same control as for S/L). Rows per config: {'F-R': 60, 'F-RC': 60, 'F-RN': 60, 'F-ZS': 60}. Per-class mean over seeds, then mean over 20 classes (%).

## Means

| Config | compile_rate | pass_rate | line_cov | branch_cov | mutation_score | LLM calls | tokens out |
|---|---|---|---|---|---|---|---|
| S-ZS | 78.3 | 50.4 | 34.0 | 25.5 | 17.8 | 1.0 | 1347 |
| F-ZS | 72.9 | 49.5 | 32.8 | 24.3 | 16.9 | 1.0 | 1485 |
| S-R | 80.1 | 52.0 | 35.3 | 26.3 | 17.7 | 3.1 | 4345 |
| F-R | 74.6 | 50.9 | 33.2 | 24.6 | 17.2 | 3.4 | 4811 |
| S-RC | 68.2 | 47.1 | 44.5 | 36.6 | 27.8 | 7.5 | 7528 |
| F-RC | 60.1 | 41.8 | 42.7 | 36.6 | 27.7 | 8.1 | 8467 |
| S-RN | 75.5 | 48.1 | 43.6 | 34.9 | 24.2 | 7.2 | 7362 |
| F-RN | 73.1 | 50.4 | 42.3 | 32.8 | 25.2 | 7.4 | 7592 |

## Paired Wilcoxon tests over 20 classes (two-sided; Holm over the tests in this table)

First block: the stage effects within the FP16 model (does the coverage report help: F-RN → F-RC). Second block: FP16 vs 4-bit at the same configuration.

| Comparison | Metric | mean Δ (pp) | classes up/equal/down | p | p (Holm) |
|---|---|---|---|---|---|
| F-ZS → F-R | branch_cov | +0.3 | 7/12/1 | 0.123 | 1 |
| F-ZS → F-R | mutation_score | +0.3 | 10/8/2 | 0.0844 | 1 |
| F-R → F-RC | branch_cov | +12.0 | 17/3/0 | 0.000293 | 0.00498 |
| F-R → F-RC | mutation_score | +10.4 | 17/3/0 | 0.000293 | 0.00498 |
| F-R → F-RN | branch_cov | +8.2 | 16/4/0 | 0.000438 | 0.00657 |
| F-R → F-RN | mutation_score | +7.9 | 18/2/0 | 0.000196 | 0.00353 |
| F-RN → F-RC | branch_cov | +3.8 | 11/1/8 | 0.469 | 1 |
| F-RN → F-RC | mutation_score | +2.5 | 12/1/7 | 0.398 | 1 |
| S-ZS → F-ZS | branch_cov | -1.2 | 10/0/10 | 0.956 | 1 |
| S-ZS → F-ZS | mutation_score | -0.8 | 9/0/11 | 0.812 | 1 |
| S-R → F-R | branch_cov | -1.7 | 9/0/11 | 0.812 | 1 |
| S-R → F-R | mutation_score | -0.5 | 9/0/11 | 0.869 | 1 |
| S-RC → F-RC | branch_cov | +0.0 | 11/0/9 | 0.701 | 1 |
| S-RC → F-RC | mutation_score | -0.2 | 9/1/10 | 0.748 | 1 |
| S-RN → F-RN | branch_cov | -2.2 | 10/1/9 | 0.658 | 1 |
| S-RN → F-RN | mutation_score | +0.9 | 11/1/8 | 0.629 | 1 |
| F-ZS → F-R | compile_rate | +1.7 | 9/9/2 | 0.0754 | 1 |
| S-ZS → S-R | compile_rate | +1.7 | 9/8/3 | 0.182 | 1 |

Per seed, branch coverage F-RC − F-RN (pp): seed 1: +2.6, seed 2: +6.7, seed 3: +2.1
