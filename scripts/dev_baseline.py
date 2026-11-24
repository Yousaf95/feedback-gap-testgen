"""Post-hoc reference point: Commons Lang's own (developer-written) tests on the 20 evaluation classes,
measured in the same harness with the same JaCoCo / PIT settings. No LLM calls.

Test classes per target class: <Class>*Test.java in the class's package (the same reference set as the
contamination check). The whole original test tree is compiled so that shared test helpers resolve.
The project's own JVM flags for Java 9+ (--add-opens, from the Commons Lang pom) are used.

Usage: python dev_baseline.py <classes.json> <commons_src> <harness_dir> <work_dir> <out_dir> [workers]"""
import json, re, shutil, subprocess, sys, pathlib, time
from concurrent.futures import ThreadPoolExecutor
import queue
from lxml import etree

classes, csrc, harness, work, out = map(pathlib.Path, sys.argv[1:6])
workers = int(sys.argv[6]) if len(sys.argv) > 6 else 4
out.mkdir(parents=True, exist_ok=True)
OPENS = ["--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED",
         "--add-opens", "java.base/java.util=ALL-UNNAMED"]
TEST_DEPS = """
    <dependency><groupId>org.junit-pioneer</groupId><artifactId>junit-pioneer</artifactId><version>1.9.1</version><scope>test</scope></dependency>
    <dependency><groupId>org.hamcrest</groupId><artifactId>hamcrest</artifactId><version>2.2</version><scope>test</scope></dependency>
    <dependency><groupId>org.easymock</groupId><artifactId>easymock</artifactId><version>5.2.0</version><scope>test</scope></dependency>
    <dependency><groupId>org.apache.commons</groupId><artifactId>commons-text</artifactId><version>1.11.0</version><scope>test</scope></dependency>
    <dependency><groupId>org.openjdk.jmh</groupId><artifactId>jmh-core</artifactId><version>1.37</version><scope>test</scope></dependency>
    <dependency><groupId>org.openjdk.jmh</groupId><artifactId>jmh-generator-annprocess</artifactId><version>1.37</version><scope>test</scope></dependency>
    <dependency><groupId>com.google.code.findbugs</groupId><artifactId>jsr305</artifactId><version>3.0.2</version><scope>test</scope></dependency>
"""


def sh(cmd, cwd, timeout=3600):
    t = time.time()
    p = subprocess.run(cmd, cwd=cwd, capture_output=True, text=True, timeout=timeout)
    return p.returncode, p.stdout + p.stderr, time.time() - t


# ---------------------------------------------------------------- build base harness
base = work / "devbase"
if not base.exists():
    shutil.copytree(harness, base, ignore=shutil.ignore_patterns("target", "src"))
    shutil.copytree(harness / "src/main", base / "src/main")
    shutil.copytree(csrc / "src/test", base / "src/test")
    pom = (base / "pom.xml").read_text()
    pom = pom.replace("  </dependencies>", TEST_DEPS + "  </dependencies>", 1)
    pom = pom.replace("<properties>", "<properties>\n    <argLine>" + " ".join(OPENS) + "</argLine>", 1)
    pom = pom.replace("<timeoutConstant>4000</timeoutConstant>",
                      "<timeoutConstant>4000</timeoutConstant>\n          <jvmArgs>" +
                      "".join(f"<value>{o}</value>" for o in OPENS) + "</jvmArgs>")
    pom = pom.replace("<testFailureIgnore>true</testFailureIgnore>",
                      "<testFailureIgnore>true</testFailureIgnore>\n          <excludes><exclude>org/apache/commons/lang3/time/Java15BugFastDateParserTest.java</exclude></excludes>")
    (base / "pom.xml").write_text(pom)
    rc, o, dt = sh(["mvn", "-B", "-q", "test-compile"], base)
    (out / "build.log").write_text(o)
    if rc != 0:
        raise SystemExit("test-compile of the original test suite failed; see build.log")
    print(f"base built in {dt:.0f}s", flush=True)

cl = json.load(open(classes))["eval"]


def tests_for(fqn):
    pkg, simple = fqn.rsplit(".", 1)
    d = base / "src/test/java" / pkg.replace(".", "/")
    return [f"{pkg}.{f.stem}" for f in sorted(d.glob(f"{simple}*Test.java"))]


def counters(xml, fqn):
    pkg, simple = fqn.rsplit(".", 1)
    tree = etree.parse(str(xml), etree.XMLParser(load_dtd=False, no_network=True, resolve_entities=False, huge_tree=True))
    for p in tree.iter("package"):
        if p.get("name") != pkg.replace(".", "/"):
            continue
        for sf in p.iter("sourcefile"):
            if sf.get("name") == f"{simple}.java":
                return {e.get("type"): (int(e.get("missed")), int(e.get("covered"))) for e in sf if e.tag == "counter"}
    return {}


def run_one(args):
    i, c = args
    fqn = c["fqn"]
    simple = fqn.rsplit(".", 1)[1]
    w = FREE.get()  # exclusive harness copy for this task
    try:
        return _run(c, fqn, simple, w)
    finally:
        FREE.put(w)


def _run(c, fqn, simple, w):
    od = out / simple
    od.mkdir(exist_ok=True)
    tests = tests_for(fqn)
    res = {"class": simple, "test_classes": [t.rsplit(".", 1)[1] for t in tests]}
    for d in ("target/surefire-reports", "target/site/jacoco", "target/pit-reports"):
        shutil.rmtree(w / d, ignore_errors=True)
    (w / "target/jacoco.exec").unlink(missing_ok=True)
    rc, o, dt = sh(["mvn", "-o", "-B", "-q", "test", "jacoco:report", "-Dtest=" + ",".join(tests),
                    "-Dsurefire.failIfNoSpecifiedTests=false"], w)
    (od / "test_run.log").write_text(o)
    n = f = e = s = 0
    for x in (w / "target/surefire-reports").glob("TEST-*.xml"):
        t = etree.parse(str(x)).getroot()
        n += int(t.get("tests", 0)); f += int(t.get("failures", 0)); e += int(t.get("errors", 0)); s += int(t.get("skipped", 0))
    res.update(tests_run=n, failures=f, errors=e, skipped=s, test_time_s=round(dt, 1))
    jx = w / "target/site/jacoco/jacoco.xml"
    if jx.exists():
        ct = counters(jx, fqn)
        shutil.copy(jx, od / "jacoco.xml")
        lm, lc = ct.get("LINE", (0, 0)); bm, bc = ct.get("BRANCH", (0, 0))
        res.update(line_cov=lc / (lm + lc) if lm + lc else None, branch_cov=bc / (bm + bc) if bm + bc else None)
    rc, o, dt = sh(["mvn", "-o", "-B", "-q", "org.pitest:pitest-maven:mutationCoverage",
                    f"-DtargetClasses={fqn},{fqn}$*", "-DtargetTests=" + ",".join(tests),
                    "-DpitThreads=2", "-DskipFailingTests=true"], w, timeout=5400)
    (od / "pit.log").write_text(o)
    mx = w / "target/pit-reports/mutations.xml"
    if mx.exists():
        shutil.copy(mx, od / "mutations.xml")
        cnt = {}
        for m in etree.parse(str(mx)).iter("mutation"):
            cnt[m.get("status")] = cnt.get(m.get("status"), 0) + 1
        tot = sum(cnt.values()); k = cnt.get("KILLED", 0); nc = cnt.get("NO_COVERAGE", 0)
        res.update(mut_total=tot, mut_killed=k, mut_no_cov=nc, mut_timeout=cnt.get("TIMED_OUT", 0),
                   mutation_score=k / tot if tot else None, test_strength=k / (tot - nc) if tot - nc else None,
                   pit_time_s=round(dt, 1))
    else:
        res["notes"] = "PIT produced no report (see pit.log)"
    print(simple, {k: res.get(k) for k in ("tests_run", "failures", "errors", "branch_cov", "mutation_score")}, flush=True)
    return res


FREE = queue.Queue()
for k in range(workers):
    wd = work / f"dw{k}"
    if not wd.exists():
        subprocess.run(["cp", "-a", str(base), str(wd)], check=True)
    FREE.put(wd)
with ThreadPoolExecutor(max_workers=workers) as ex:
    rows = list(ex.map(run_one, enumerate(cl)))
(out / "developer_baseline.json").write_text(json.dumps(rows, indent=2))
