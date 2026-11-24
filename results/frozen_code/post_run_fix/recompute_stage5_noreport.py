"""Recompute RC rows hit by the Stage-5 'no surefire report' bug (all tests, incl. already-green ones,
were treated as failing). Correct rule (spec: Stage 4 applies to new methods only): discard the new
methods and keep the previous green suite. No LLM calls are made; LLM cost fields are kept.

Usage: python recompute_stage5_noreport.py <runs.jsonl> <artifacts_dir> <work_dir>
Writes <runs.jsonl> (fixed) and keeps the original as runs.pre_fix.jsonl."""
import json, re, shutil, subprocess, sys, pathlib
sys.path.insert(0, str(pathlib.Path(__file__).parent))
import javatools as jt
from harness_ops import Harness
from pipeline import Art

ROOT = pathlib.Path(__file__).resolve().parent.parent
runs, art_root, work = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2]), pathlib.Path(sys.argv[3])
cfg = json.load(open(ROOT / "scripts/config.json"))
BUG = "no surefire report; all tests treated as failing"

rows = [json.loads(l) for l in runs.read_text().splitlines() if l.strip()]
backup = runs.with_name("runs.pre_fix.jsonl")
if not backup.exists():
    shutil.copy(runs, backup)

hdir = work / "recompute_harness"
if not hdir.exists():
    shutil.copytree(ROOT / "harness", hdir, ignore=shutil.ignore_patterns("target", "*_GenTest.java"))
    subprocess.run(["mvn", "-o", "-B", "-q", "compile"], cwd=hdir, check=True)
h = Harness(hdir, pit_threads=cfg["pit_threads"])

for r in rows:
    if not r["config"].endswith("-RC") or BUG not in r["notes"] or "RC: no green R suite" in r["notes"]:
        continue
    simple = r["class"].rsplit(".", 1)[1]
    d = art_root / simple / r["config"] / f"run{r['run_seed']}"
    files = sorted(p.name for p in d.iterdir())
    # the crashed run is the last Stage-4 run in the RC folder (the wiped suite ended the loop)
    crash_idx = [f for f in files if f.endswith("_run1.log")][-1]
    greens = [f for f in files if f.endswith("_test_green.java") and (crash_idx is None or f < crash_idx)]
    if greens:
        base_file = d / greens[-1]
    else:  # crash in round 1: base is the green R suite
        rdir = art_root / simple / r["config"].replace("-RC", "-R") / f"run{r['run_seed']}"
        base_file = next(p for p in sorted(rdir.iterdir()) if p.name.endswith("_final_test.java"))
    base = base_file.read_text()
    n_old = jt.count_tests(base)
    print(f"{r['config']} {simple} seed {r['run_seed']}: crash at {crash_idx}, base {base_file.name} ({n_old} tests)")
    a = Art(d)
    a.i = len(files)
    a.put("recompute_base_test.java", base)
    h.write_test(r["class"], base)
    status, out = h.run_tests(r["class"], with_coverage=True)
    a.put("recompute_run.log", out)
    cov = h.jacoco(r["class"])
    a.put("recompute_jacoco.xml", cov["xml"])
    pit, pout = h.pit(r["class"])
    a.put("recompute_pit.log", pout)
    a.put("recompute_mutations.xml", pit.pop("xml"))
    pit.pop("status_counts")
    old = dict(r)
    r["tests_passing"] = n_old
    r["removed_failing"] = r["removed_failing"] - n_old
    r.update(line_cov=cov["line_cov"], branch_cov=cov["branch_cov"], line_covered=cov["line_covered"],
             line_total=cov["line_covered"] + cov["line_missed"], branch_covered=cov["branch_covered"],
             branch_total=cov["branch_covered"] + cov["branch_missed"])
    r.update(pit)
    notes = [n.strip() for n in r["notes"].split(";")]
    notes = [n for n in notes if n not in (BUG, "empty suite: placeholder test used for measurement")]
    notes.append("stage5: no surefire report; new methods discarded (row recomputed after bug fix; "
                 "no new LLM calls; time fields from the original run)")
    r["notes"] = "; ".join(notes)
    a.put("recompute_row.json", {"before": old, "after": r})
    print(f"   branch {old['branch_cov']} -> {r['branch_cov']}, ms {old['mutation_score']} -> {r['mutation_score']}, "
          f"passing {old['tests_passing']} -> {r['tests_passing']}")

runs.write_text("".join(json.dumps(r) + "\n" for r in rows))
