# feedback-gap-testgen

Experiment code and data for the empirical study **"Compiler Repair or Coverage Feedback? A Paired Ablation of LLM-Based Java Unit Test Generation with Open-Weight Models"** (ICECT 2027).

The study measures, step by step, how much compiler-error repair and coverage-guided refinement improve LLM-generated JUnit 5 tests, and compares a small (7B) and a larger (32B) open code model.

## Research questions
- **RQ1** How much does compiler-error self-repair improve the rate of compilable, passing tests?
- **RQ2** How much does coverage-guided refinement add to line coverage, branch coverage and mutation score?
- **RQ3** With full feedback, how close does a small local model get to a larger model, and what does each cost in time and tokens?

## Setup
| | |
|---|---|
| Subject | Apache Commons Lang `rel/commons-lang-3.14.0`, 20 evaluation classes + 3 dev classes ([results/classes.json](results/classes.json)) |
| Models | `qwen2.5-coder:7b-instruct` (S) and `qwen2.5-coder:32b-instruct` (L) via Ollama 0.23.0, temperature 0.2, 4096 max output tokens |
| Configs | ZS (prune only), R (+ compile repair, K=3), RC (+ coverage-guided refinement, K=3) for each model |
| Runs | 3 seeds per (class, model): 120 generation chains, each measured in 3 configurations = 360 rows |
| Toolchain | Temurin JDK 17, Maven 3.9.9, JUnit 5.10.2, JaCoCo 0.8.12, PIT 1.15.8 + pitest-junit5-plugin 1.2.1 |

Exact versions, model digests and hardware: [results/environment.md](results/environment.md). Frozen configuration and prompt hashes: [results/config_frozen.json](results/config_frozen.json). All deviations from the original experiment plan: [results/deviations.md](results/deviations.md).

## Pipeline
For each (class, model, seed): pruned class context → initial generation (T0) → compile, repair (R/RC) and method-level pruning → removal of failing and flaky tests (green suite) → coverage-guided refinement rounds (RC) → final JaCoCo + PIT measurement. ZS, R and RC share the same T0; RC continues from the R suite.

| Path | Content |
|---|---|
| `prompts/` | Prompt templates (generation, repair, refinement, system) |
| `scripts/pipeline.py` | Stages 1–6 for one (class, model, seed) chain |
| `scripts/javatools.py` | Context extraction, pruning, merging (tree-sitter-java) |
| `scripts/harness_ops.py` | Maven / Surefire / JaCoCo / PIT wrappers |
| `scripts/run_all.py` | Parallel, resumable runner |
| `scripts/analyze.py` | Tables, Wilcoxon + Vargha–Delaney Â12, figure, error breakdown |
| `scripts/select_classes.py` | Deterministic class selection |
| `scripts/recompute_stage5_noreport.py` | Post-run fix for 3 rows (deviation 20) |
| `harness/` | Clean Maven harness (pom + JUnit platform config) |
| `jobs/` | PBS job scripts (Ollama servers, workers) |
| `results/` | Raw rows, summary, figure, examples, per-run artifacts |

## Results
- [results/summary.md](results/summary.md): all tables and statistics
- [results/branch_coverage.pdf](results/branch_coverage.pdf): per-class branch coverage
- [results/raw/runs.jsonl](results/raw/runs.jsonl): one row per (class, config, seed)
- [results/extra/extra_analysis.md](results/extra/extra_analysis.md): post-hoc exploratory analyses (coverage per refinement round, cost-effectiveness, failure types of removed tests, 50-test LLM-assisted triage)
- [results/extra/robustness.md](results/extra/robustness.md): post-hoc robustness checks (seed stability, coverage vs class complexity, contamination check against the Commons Lang test suite)
- [results/extra/developer_baseline.md](results/extra/developer_baseline.md): post-hoc reference point, Commons Lang's own tests measured in the same harness
- [results/extra/control_no_coverage.md](results/extra/control_no_coverage.md): post-hoc control, refinement rounds without the coverage report (separates coverage feedback from additional attempts)
- [results/extra/repair_success.md](results/extra/repair_success.md): how often LLM repair made the test class compile (R configurations)
- [results/extra/round_matched.md](results/extra/round_matched.md): post-hoc round-matched comparison of RC vs RN (coverage report vs none at equal refinement rounds); per-round values in `results/extra/round_matched_values.csv`, recomputable with `scripts/round_matched_from_csv.py`
- [results/extra/fp16_check.md](results/extra/fp16_check.md): post-hoc quantization check, 7B model with FP16 weights vs 4-bit
- `results/artifacts/<class>/<config>/run<seed>/`: every prompt, raw LLM response, intermediate test file, compiler/test log and PIT `mutations.xml` (JaCoCo XML reports are not in the repository because of their size)

## Reproducing
```bash
# 1. subject sources
git clone --depth 1 --branch rel/commons-lang-3.14.0 https://github.com/apache/commons-lang.git commons-lang-src
cp -r commons-lang-src/src/main/java harness/src/main/
# 2. toolchain: JDK 17, Maven 3.9, Python 3.11 with
pip install requests lxml pandas scipy matplotlib tree-sitter==0.27.0 tree-sitter-java==0.23.5
# 3. models
ollama pull qwen2.5-coder:7b-instruct && ollama pull qwen2.5-coder:32b-instruct
# 4. run (one Ollama server per model; see jobs/ for the PBS versions)
python scripts/run_all.py --config scripts/config.json --set eval --models S,L --seeds 1,2,3 \
  --workers 16 --out results/raw/runs.jsonl --art results/artifacts \
  --host S=<host>:11434,L=<host>:11434
# 5. analysis
python scripts/analyze.py results/raw/runs.jsonl results/artifacts results results/deviations.md
```
Note: requests are batched by Ollama, so LLM outputs are not guaranteed to be bit-for-bit reproducible even with fixed seeds.

## License
Code in this repository: MIT (see `LICENSE`). Apache Commons Lang is licensed under the Apache License 2.0 and is not redistributed here.
