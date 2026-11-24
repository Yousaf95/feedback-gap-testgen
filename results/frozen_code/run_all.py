"""Parallel, resumable runner.
Usage: python run_all.py --config CONFIG.json --set eval|dev --models S,L --seeds 1,2,3
                         --workers N --out results/raw/runs.jsonl --art results/artifacts --host HOST:PORT"""
import argparse, json, os, pathlib, shutil, subprocess, sys, time, traceback
from concurrent.futures import ProcessPoolExecutor, as_completed

sys.path.insert(0, str(pathlib.Path(__file__).parent))
ROOT = pathlib.Path(__file__).resolve().parent.parent


def prepare_workers(n, work):
    base = work / "harness_base"
    if not base.exists():
        shutil.copytree(ROOT / "harness", base, ignore=shutil.ignore_patterns("target", "*_GenTest.java"))
        for p in (base / "src/test/java").rglob("*.java"):
            p.unlink()
        r = subprocess.run(["mvn", "-o", "-B", "-q", "compile"], cwd=base, capture_output=True, text=True)
        if r.returncode != 0:
            raise SystemExit("base compile failed:\n" + r.stdout + r.stderr)
    dirs = []
    for i in range(n):
        d = work / f"w{i}"
        if not d.exists():
            subprocess.run(["cp", "-a", str(base), str(d)], check=True)
        dirs.append(d)
    return dirs


def run_chain(args):
    cfg, fqn, mk, seed, hdir, host, art = args
    os.environ.setdefault("MAVEN_OPTS", "-Xmx1g")
    from pipeline import Chain
    t = time.time()
    try:
        rows = Chain(cfg, fqn, mk, seed, hdir, host, art).run()
        return {"ok": True, "rows": rows, "key": (fqn, mk, seed), "secs": time.time() - t}
    except Exception:
        return {"ok": False, "err": traceback.format_exc(), "key": (fqn, mk, seed), "secs": time.time() - t}


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--config", required=True)
    ap.add_argument("--set", default="eval")
    ap.add_argument("--classes", default="")
    ap.add_argument("--models", default="S,L")
    ap.add_argument("--seeds", default="")
    ap.add_argument("--workers", type=int, default=8)
    ap.add_argument("--out", required=True)
    ap.add_argument("--art", required=True)
    ap.add_argument("--host", required=True)
    ap.add_argument("--work", default=os.environ.get("TMPDIR", "/tmp") + "/icect_work")
    a = ap.parse_args()
    cfg = json.load(open(a.config))
    hosts = dict(kv.split("=") for kv in a.host.split(","))  # e.g. S=node1:11434,L=node2:11434
    classes = json.load(open(ROOT / "results/classes.json"))[a.set]
    fqns = [c["fqn"] for c in classes]
    if a.classes:
        fqns = [f for f in fqns if f.rsplit(".", 1)[1] in a.classes.split(",")]
    seeds = [int(s) for s in a.seeds.split(",")] if a.seeds else cfg["seeds"]
    models = a.models.split(",")
    out = pathlib.Path(a.out)
    out.parent.mkdir(parents=True, exist_ok=True)
    done = set()
    if out.exists():
        for l in out.read_text().splitlines():
            r = json.loads(l)
            done.add((r["class"], r["config"].split("-")[0], r["run_seed"]))
    # seed-major (a partial run still has complete seeds); alternate models so both GPU servers stay busy
    order = sorted(models, key=lambda m: m != "L")
    jobs = [(f, m, s) for s in seeds for f in fqns for m in order if (f, m, s) not in done]
    print(f"{len(jobs)} chains to run ({len(done)} already done)", flush=True)
    work = pathlib.Path(a.work)
    work.mkdir(parents=True, exist_ok=True)
    dirs = prepare_workers(a.workers, work)
    free = list(dirs)
    errlog = out.with_suffix(".errors.log")
    with ProcessPoolExecutor(max_workers=a.workers) as ex:
        pending = {}
        it = iter(jobs)

        def submit():
            for f, m, s in it:
                d = free.pop()
                fut = ex.submit(run_chain, (cfg, f, m, s, str(d), hosts, a.art))
                pending[fut] = d
                return True
            return False

        for _ in range(min(a.workers, len(jobs))):
            submit()
        n = 0
        while pending:
            for fut in as_completed(list(pending)):
                d = pending.pop(fut)
                free.append(d)
                r = fut.result()
                n += 1
                if r["ok"]:
                    with open(out, "a") as fh:
                        for row in r["rows"]:
                            fh.write(json.dumps(row) + "\n")
                    summ = " | ".join(f"{x['config']}: pass={x['tests_passing']} br={x.get('branch_cov')}"
                                      f" ms={x.get('mutation_score')}" for x in r["rows"])
                    print(f"[{n}/{len(jobs)}] {r['key']} {r['secs']:.0f}s {summ}", flush=True)
                else:
                    with open(errlog, "a") as fh:
                        fh.write(f"=== {r['key']}\n{r['err']}\n")
                    print(f"[{n}/{len(jobs)}] {r['key']} FAILED (see {errlog})", flush=True)
                submit()
                break


if __name__ == "__main__":
    main()
