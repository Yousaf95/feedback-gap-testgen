# Environment

Recorded: 2026-11-24T09:59:31.576338+01:00

## Build / test toolchain
- OS: Linux 5.14.0-687.64.1.el9_8.x86_64 x86_64 (Rocky Linux release 9.8 (Blue Onyx))
- JDK: openjdk version "17.0.21.1" 2026-11-17 OpenJDK Runtime Environment Temurin-17.0.21.1+2 (build 17.0.21.1+2)
- Maven: Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
- Python: Python 3.11.18
- JUnit Jupiter: 5.10.2
- maven-compiler-plugin: 3.13.0 (maven.compiler.release=17)
- maven-surefire-plugin: 3.2.5
- jacoco-maven-plugin: 0.8.12
- pitest-maven: 1.15.8; pitest-junit5-plugin: 1.2.1; mutators: DEFAULTS; threads: 2
- JUnit default per-test timeout: 10 s (SEPARATE_THREAD)
- Python packages: lxml==6.1.3, matplotlib==3.11.2, numpy==2.4.6, pandas==3.1.1, requests==2.34.2, scipy==1.17.1, tqdm==4.70.1, tree-sitter==0.27.0, tree-sitter-java==0.23.5

## Subject
- Apache Commons Lang, tag rel/commons-lang-3.14.0 (commit c8774fa74adbbbcd4e5f915ab6bc3aa10d877419 Sat Nov 18 09:54:12 2023 -0500); only src/main/java copied into a fresh Maven harness.

## LLM serving
- Ollama 0.23.0; one server per model, each on its own PBS GPU job (1 GPU), OLLAMA_NUM_PARALLEL=8, OLLAMA_MAX_LOADED_MODELS=1, OLLAMA_CONTEXT_LENGTH=20480
- GPU (S server): MK=S host=<GPU node> PBS CUDA_VISIBLE_DEVICES=; index, name, memory.total [MiB], memory.used [MiB], driver_version; 0, NVIDIA H100 NVL, 95830 MiB, 2201 MiB, 595.71.05; 1, NVIDIA H100 NVL, 95830 MiB, 0 MiB, 595.71.05
- GPU (L server): MK=L host=<GPU node> PBS CUDA_VISIBLE_DEVICES=; index, name, memory.total [MiB], memory.used [MiB], driver_version; 0, NVIDIA H100 NVL, 95830 MiB, 579 MiB, 580.105.08; 1, NVIDIA H100 NVL, 95830 MiB, 529 MiB, 580.105.08
- S: `qwen2.5-coder:7b-instruct` manifest sha256:dae161e27b0e90dd1856c8bb3209201fd6736d8eb66298e75ed87571486f4364, weights sha256:60e05f2100071479f596b964f89f510f057ce397ea22f2833a0cfe029bfc2463 (small local (~7B))
- L: `qwen2.5-coder:32b-instruct` manifest sha256:b92d6a0bd47ee79114298de0177bf920c05a706d12633950b3936778492bef41, weights sha256:ac3d1ba8aa77755dab3806d9024e9c385ea0d5b412d6bdf9157f8a4a7e9fc0d9 (large local (32B), replaces the API model)
- Quantization (from the model config blobs): both models GGUF, **Q4_K_M** (4-bit); parameter counts 7.6B (S) and 32.8B (L); weight files 4.68 GB and 19.85 GB
- Post-hoc FP16 check (deviation 30): `qwen2.5-coder:7b-instruct-fp16`, manifest sha256:9bff1b5c46f86f50f916c6b8642edbac1a00aaa95c9e39f78cc72cc405a58e5a, weights sha256:d8e374f171cbf20a79619dc6e0926124c7690497609bc94bdad18ef6a86cdee0 (15.24 GB), file_type F16, served on its own H100 NVL with the same Ollama settings
- Decoding: temperature 0.2, num_predict 4096, num_ctx 20480, seed = run seed [1, 2, 3]
- No commercial API model was used (no API key available); cost in USD is 0 for both models, time and tokens are reported instead.
