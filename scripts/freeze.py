"""Write results/config_frozen.json and results/environment.md. Usage: python freeze.py"""
import hashlib, json, pathlib, subprocess, re, datetime, os

ROOT = pathlib.Path(__file__).resolve().parent.parent
cfg = json.load(open(ROOT / "scripts/config.json"))
classes = json.load(open(ROOT / "results/classes.json"))
MAN = pathlib.Path(os.environ.get("OLLAMA_MODELS", pathlib.Path.home() / "ollama/models")) / \
    "manifests/registry.ollama.ai/library"


def sh(cmd):
    try:
        return subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=60).stdout.strip() + \
            subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=60).stderr.strip()
    except Exception as e:
        return f"n/a ({e})"


models = {}
for k, m in cfg["models"].items():
    name, tag = m["tag"].split(":")
    mf = MAN / name / tag
    raw = mf.read_bytes()
    j = json.loads(raw)
    weights = [l for l in j["layers"] if l["mediaType"].endswith("model")]
    models[k] = {**m, "manifest_digest": "sha256:" + hashlib.sha256(raw).hexdigest(),
                 "weights_digest": weights[0]["digest"] if weights else None,
                 "weights_size_bytes": weights[0]["size"] if weights else None}

prompts = {}
for p in sorted((ROOT / "prompts").glob("*.txt")):
    prompts[p.name] = {"sha256": hashlib.sha256(p.read_bytes()).hexdigest(), "text": p.read_text()}

frozen = {
    "frozen_at": datetime.datetime.now().astimezone().isoformat(),
    "config": cfg, "models": models, "prompts": prompts,
    "eval_classes": [c["fqn"] for c in classes["eval"]],
    "dev_classes": [c["fqn"] for c in classes["dev"]],
    "selection_rule": classes["rule"],
}
(ROOT / "results/config_frozen.json").write_text(json.dumps(frozen, indent=2))

pom = (ROOT / "harness/pom.xml").read_text()


def pv(art):
    m = re.search(r"<artifactId>%s</artifactId>\s*<version>([^<]+)</version>" % re.escape(art), pom)
    return m.group(1) if m else "?"


junit = re.search(r"<junit.version>([^<]+)<", pom).group(1)
gpu = (ROOT / "logs/ollama_gpu.txt").read_text().strip() if (ROOT / "logs/ollama_gpu.txt").exists() else "n/a"
cl = sh("git -C %s log -1 --format='%%H %%cd'" % (ROOT / "commons-lang-src"))
JAVA_V = sh('java -version 2>&1 | head -2 | tr "\\n" " "')
OLLAMA_V = sh('ollama --version 2>&1 | grep -oE "[0-9]+\\.[0-9]+\\.[0-9]+" | tail -1')
PIPS = sh("pip freeze 2>/dev/null | grep -iE '^(requests|lxml|pandas|scipy|numpy|tqdm|tree.sitter|matplotlib)'").replace(chr(10), ", ")
GPU_S = (ROOT / "logs/ollama_gpu_S.txt").read_text().strip().replace(chr(10), "; ") if (ROOT / "logs/ollama_gpu_S.txt").exists() else "n/a"
GPU_L = (ROOT / "logs/ollama_gpu_L.txt").read_text().strip().replace(chr(10), "; ") if (ROOT / "logs/ollama_gpu_L.txt").exists() else "n/a"
MODEL_LINES = "".join(f"- {k}: `{m['tag']}` manifest {m['manifest_digest']}, weights {m['weights_digest']} ({m['role']})" + chr(10) for k, m in models.items())
env = f"""# Environment

Recorded: {frozen['frozen_at']}

## Build / test toolchain
- OS: {sh('uname -srm')} ({sh('cat /etc/redhat-release')})
- JDK: {JAVA_V}
- Maven: {sh('mvn -v 2>/dev/null | head -1')}
- Python: {sh('python --version')}
- JUnit Jupiter: {junit}
- maven-compiler-plugin: {pv('maven-compiler-plugin')} (maven.compiler.release=17)
- maven-surefire-plugin: {pv('maven-surefire-plugin')}
- jacoco-maven-plugin: {pv('jacoco-maven-plugin')}
- pitest-maven: {pv('pitest-maven')}; pitest-junit5-plugin: {pv('pitest-junit5-plugin')}; mutators: DEFAULTS; threads: {cfg['pit_threads']}
- JUnit default per-test timeout: {cfg['junit_default_timeout']} (SEPARATE_THREAD)
- Python packages: {PIPS}

## Subject
- Apache Commons Lang, tag rel/commons-lang-3.14.0 (commit {cl}); only src/main/java copied into a fresh Maven harness.

## LLM serving
- Ollama {OLLAMA_V}; one server per model, each on its own PBS GPU job (1 GPU), OLLAMA_NUM_PARALLEL=8, OLLAMA_MAX_LOADED_MODELS=1, OLLAMA_CONTEXT_LENGTH=20480
- GPU (S server): {GPU_S}
- GPU (L server): {GPU_L}
{MODEL_LINES}- Decoding: temperature {cfg['temperature']}, num_predict {cfg['max_tokens']}, num_ctx {cfg['num_ctx']}, seed = run seed {cfg['seeds']}
- No commercial API model was used (no API key available); cost in USD is 0 for both models, time and tokens are reported instead.
"""
(ROOT / "results/environment.md").write_text(env)
print(env)
