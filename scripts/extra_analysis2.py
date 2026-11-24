"""Post-hoc robustness checks (not part of the frozen plan); existing data only, no LLM calls.
  4. seed stability: spread of branch coverage / mutation score across the 3 seeds
  5. coverage vs class size/complexity (Spearman)
  6. contamination: similarity of generated test methods to Commons Lang's own tests (rel/commons-lang-3.14.0)
Usage: python extra_analysis2.py <runs.jsonl> <artifacts_dir> <classes.json> <commons_test_dir> <out_dir>"""
import json, re, sys, pathlib, collections
import numpy as np, pandas as pd
from scipy.stats import spearmanr
sys.path.insert(0, str(pathlib.Path(__file__).parent))
import javatools as jt
from jparse import parse, walk

runs, art, classes, ctest, out = map(pathlib.Path, sys.argv[1:6])
df = pd.DataFrame([json.loads(l) for l in runs.read_text().splitlines() if l.strip()])
df["simple"] = df["class"].str.rsplit(".", n=1).str[1]
CONFIGS = ["S-ZS", "S-R", "S-RC", "L-ZS", "L-R", "L-RC"]
L = ["# Post-hoc robustness checks", "",
     "Computed after the main analysis from existing data (no new LLM calls); not part of the frozen plan.", ""]

# ---------------------------------------------------------------- 4. seed stability
L += ["## 4. Seed stability (3 seeds per class and config)", "",
      "Per class: standard deviation and range (max − min) across the 3 seeds, in percentage points; "
      "then mean / max over the 20 classes. Last column: share of (class) cells whose seed range exceeds 10 pp.", "",
      "| Config | Branch cov. std (mean) | Branch cov. range (mean / max) | Mutation score std (mean) | Mutation range (mean / max) | Branch range > 10 pp |",
      "|---|---|---|---|---|---|"]
for c in CONFIGS:
    g = df[df.config == c].groupby("simple")
    bs, br = 100 * g.branch_cov.std(ddof=1), 100 * (g.branch_cov.max() - g.branch_cov.min())
    ms, mr = 100 * g.mutation_score.std(ddof=1), 100 * (g.mutation_score.max() - g.mutation_score.min())
    L.append(f"| {c} | {bs.mean():.1f} | {br.mean():.1f} / {br.max():.1f} | {ms.mean():.1f} | {mr.mean():.1f} / {mr.max():.1f} | "
             f"{(br > 10).sum()}/20 |")
# do the ablation conclusions hold within every single seed?
L += ["", "Ablation deltas per seed (branch coverage, mean over classes, pp):", "",
      "| Step | seed 1 | seed 2 | seed 3 |", "|---|---|---|---|"]
piv = df.pivot_table(index=["simple", "run_seed"], columns="config", values="branch_cov")
for a, b in (("S-ZS", "S-R"), ("S-R", "S-RC"), ("L-ZS", "L-R"), ("L-R", "L-RC"), ("S-RC", "L-ZS"), ("S-RC", "L-RC")):
    d = (piv[b] - piv[a]) * 100
    L.append(f"| {a} → {b} | " + " | ".join(f"{d.xs(s, level='run_seed').mean():+.1f}" for s in (1, 2, 3)) + " |")

# ---------------------------------------------------------------- 5. complexity
cl = pd.DataFrame(json.load(open(classes))["eval"])
cl["simple"] = cl.fqn.str.rsplit(".", n=1).str[1]
pc = df.groupby(["config", "simple"]).mean(numeric_only=True).reset_index()
L += ["", "## 5. Coverage vs class size and complexity (Spearman ρ over the 20 classes)", "",
      "NCLOC and branch counts from results/classes.json.", "",
      "| Config | ρ(branch cov., NCLOC) | p | ρ(branch cov., #branches) | p | ρ(mutation score, #branches) | p |",
      "|---|---|---|---|---|---|---|"]
for c in CONFIGS:
    x = pc[pc.config == c].merge(cl, on="simple")
    r1 = spearmanr(x.branch_cov, x.ncloc)
    r2 = spearmanr(x.branch_cov, x.branches)
    r3 = spearmanr(x.mutation_score, x.branches)
    L.append(f"| {c} | {r1.statistic:+.2f} | {r1.pvalue:.3f} | {r2.statistic:+.2f} | {r2.pvalue:.3f} | "
             f"{r3.statistic:+.2f} | {r3.pvalue:.3f} |")
# context truncation (classes whose context was cut to signatures)
trunc = set(df[df.notes.str.contains("context truncated")].simple)
if trunc:
    a_ = pc[pc.simple.isin(trunc)].groupby("config").branch_cov.mean() * 100
    b_ = pc[~pc.simple.isin(trunc)].groupby("config").branch_cov.mean() * 100
    L += ["", f"Classes whose context was truncated to signatures ({', '.join(sorted(trunc))}) vs the others, mean branch coverage (%):", "",
          "| Config | truncated context | full context |", "|---|---|---|"]
    for c in CONFIGS:
        L.append(f"| {c} | {a_[c]:.1f} | {b_[c]:.1f} |")

# ---------------------------------------------------------------- 6. contamination
LEAF_SKIP = {"line_comment", "block_comment"}


def method_tokens(text):
    """Tokens of a method body (leaf nodes, comments dropped). Returns (raw, abstracted) token lists."""
    src = ("class X {\n" + text + "\n}").encode("utf-8")
    root = parse(src).root_node
    raw, absd = [], []
    body = None
    for n in walk(root):
        if n.type == "method_declaration":
            body = n.child_by_field_name("body")
            break
    if body is None:
        return [], []
    for n in walk(body):
        if n.child_count == 0 and n.type not in LEAF_SKIP:
            t = src[n.start_byte:n.end_byte].decode("utf-8", "replace")
            if not t.strip():
                continue
            raw.append(t)
            if n.type == "identifier":
                absd.append("ID")
            elif n.type in ("string_fragment", "string_literal", "character_literal"):
                absd.append("STR")
            elif "integer_literal" in n.type or "floating_point" in n.type:
                absd.append("NUM")
            else:
                absd.append(t)
    return raw, absd


def shingles(tok, k=5):
    return {tuple(tok[i:i + k]) for i in range(max(0, len(tok) - k + 1))}


def jacc(a, b):
    return len(a & b) / len(a | b) if a and b else 0.0


pkg_of = dict(zip(cl.simple, cl.fqn.str.rsplit(".", n=1).str[0]))
orig = {}
for simple, pkg in pkg_of.items():
    d = ctest / pkg.replace(".", "/")
    ms = []
    for f in sorted(d.glob(f"{simple}*Test.java")):
        for m in jt.methods(f.read_text(encoding="utf-8", errors="replace")):
            raw, ab = method_tokens(m["text"])
            if len(raw) >= 10:
                ms.append({"file": f.name, "name": m["name"], "body": " ".join(raw),
                           "S": shingles(raw), "A": shingles(ab)})
    orig[simple] = ms

rows = []
for _, r in df.iterrows():
    d = art / r.simple / r.config / f"run{r.run_seed}"
    fin = sorted(d.glob("*_final_test.java"))
    if not fin:
        continue
    code = fin[-1].read_text(encoding="utf-8", errors="replace")
    for m in jt.methods(code):
        if not m["test"]:
            continue
        raw, ab = method_tokens(m["text"])
        if len(raw) < 10:
            continue
        S, A = shingles(raw), shingles(ab)
        best_s = best_a = 0.0
        exact = name_hit = False
        for o in orig[r.simple]:
            if o["body"] == " ".join(raw):
                exact = True
            if o["name"] == m["name"]:
                name_hit = True
            best_s = max(best_s, jacc(S, o["S"]))
            best_a = max(best_a, jacc(A, o["A"]))
        rows.append({"config": r.config, "class": r.simple, "seed": r.run_seed, "method": m["name"],
                     "exact": exact, "same_name": name_hit, "max_jaccard_raw": best_s, "max_jaccard_abstract": best_a})
cdf = pd.DataFrame(rows)
cdf.to_csv(out / "contamination_similarity.csv", index=False)
n_orig = sum(len(v) for v in orig.values())
L += ["", "## 6. Contamination check: generated tests vs Commons Lang's own tests", "",
      f"Reference: {n_orig} test methods in `<Class>*Test.java` of rel/commons-lang-3.14.0 for the 20 classes. "
      "Each passing generated test method in the final suites is compared with every original test method of the same class: "
      "exact token-sequence match (comments/whitespace ignored), Jaccard similarity of 5-token shingles on raw tokens, "
      "and on abstracted tokens (identifiers→ID, literals→STR/NUM; captures renamed copies). Methods with < 10 tokens skipped.", "",
      "| Config | test methods | exact copies | same method name as an original | max Jaccard raw ≥ 0.5 | ≥ 0.8 | max Jaccard abstract ≥ 0.8 | median max Jaccard raw |",
      "|---|---|---|---|---|---|---|---|"]
for c in CONFIGS:
    x = cdf[cdf.config == c]
    n = len(x)
    pct = lambda s: f"{s.sum()} ({100 * s.mean():.1f}%)" if n else "–"
    L.append(f"| {c} | {n} | {pct(x.exact)} | {pct(x.same_name)} | {pct(x.max_jaccard_raw >= 0.5)} | "
             f"{pct(x.max_jaccard_raw >= 0.8)} | {pct(x.max_jaccard_abstract >= 0.8)} | {x.max_jaccard_raw.median():.2f} |")
top = cdf.sort_values("max_jaccard_raw", ascending=False).head(8)
L += ["", "Most similar generated methods:", "", "| Config | Class | Seed | Method | Jaccard raw | Jaccard abstract | exact |",
      "|---|---|---|---|---|---|---|"]
for x in top.itertuples():
    L.append(f"| {x.config} | {x[2]} | {x.seed} | {x.method} | {x.max_jaccard_raw:.2f} | {x.max_jaccard_abstract:.2f} | {x.exact} |")
(out / "robustness.md").write_text("\n".join(L) + "\n")
print("\n".join(L))
