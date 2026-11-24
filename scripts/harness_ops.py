"""Maven harness operations for one worker copy of the harness."""
import os, re, shutil, subprocess, time, pathlib
from lxml import etree

MVN = ["mvn", "-o", "-B", "-q"]
ERR_RE = re.compile(r"^\[ERROR\] (/\S+?\.java):\[(\d+),(\d+)\] (.*)$")


class Harness:
    def __init__(self, root, pit_threads=2):
        self.root = pathlib.Path(root)
        self.pit_threads = pit_threads
        self.build_time = 0.0

    # -------------------------------------------------- utilities
    def _mvn(self, args, timeout=900):
        t = time.time()
        try:
            p = subprocess.run(MVN + args, cwd=self.root, capture_output=True, text=True, timeout=timeout)
            out, rc = p.stdout + p.stderr, p.returncode
        except subprocess.TimeoutExpired as e:
            out = (e.stdout or b"").decode(errors="replace") if isinstance(e.stdout, bytes) else (e.stdout or "")
            out += "\n[HARNESS] maven timeout"
            rc = -9
        self.build_time += time.time() - t
        return rc, out

    def test_path(self, fqn):
        pkg, simple = fqn.rsplit(".", 1)
        return self.root / "src/test/java" / pkg.replace(".", "/") / f"{simple}_GenTest.java"

    def clear_tests(self):
        """Only one target class's tests may be present at a time."""
        tdir = self.root / "src/test/java"
        if tdir.exists():
            shutil.rmtree(tdir)
        tdir.mkdir(parents=True)
        for d in ("target/test-classes", "target/surefire-reports", "target/site", "target/pit-reports",
                  "target/maven-status/maven-compiler-plugin/testCompile"):
            shutil.rmtree(self.root / d, ignore_errors=True)
        for f in ("target/jacoco.exec",):
            try:
                os.remove(self.root / f)
            except FileNotFoundError:
                pass

    def write_test(self, fqn, code):
        self.clear_tests()
        p = self.test_path(fqn)
        p.parent.mkdir(parents=True, exist_ok=True)
        p.write_text(code, encoding="utf-8")

    # -------------------------------------------------- Stage 3
    def compile(self, fqn):
        """Returns (ok, errors[list of dict], raw_output)."""
        rc, out = self._mvn(["test-compile"])
        tp = str(self.test_path(fqn))
        errs, seen = [], set()
        lines = out.splitlines()
        for i, l in enumerate(lines):
            m = ERR_RE.match(l)
            if not m:
                continue
            f, ln, col, msg = m.group(1), int(m.group(2)), int(m.group(3)), m.group(4).strip()
            # following detail lines (symbol/location/required/found)
            det = []
            for j in range(i + 1, min(i + 5, len(lines))):
                d = lines[j]
                if ERR_RE.match(d) or not d.startswith("[ERROR]   ") and not d.startswith("  "):
                    break
                det.append(d.replace("[ERROR]", "").strip())
            key = (f, ln, col, msg)
            if key in seen:
                continue
            seen.add(key)
            errs.append({"file": f, "line": ln, "col": col, "msg": msg, "detail": det,
                         "in_test": os.path.realpath(f) == os.path.realpath(tp)})
        ok = rc == 0
        return ok, errs, out

    # -------------------------------------------------- Stage 4 / 6
    def run_tests(self, fqn, with_coverage=False):
        """Run the generated test class. Returns (per-method status dict, raw_output).
        Status per method name: 'pass' | 'fail'. None if no report was produced."""
        for d in ("target/surefire-reports", "target/site/jacoco"):
            shutil.rmtree(self.root / d, ignore_errors=True)
        try:
            os.remove(self.root / "target/jacoco.exec")
        except FileNotFoundError:
            pass
        pkg, simple = fqn.rsplit(".", 1)
        goals = ["test", f"-Dtest={pkg}.{simple}_GenTest", "-Dsurefire.failIfNoSpecifiedTests=false"]
        if with_coverage:
            goals.append("jacoco:report")
        rc, out = self._mvn(goals, timeout=900)
        rep = self.root / "target/surefire-reports"
        files = list(rep.glob(f"TEST-{pkg}.{simple}_GenTest*.xml")) if rep.exists() else []
        if not files:
            return None, out
        status = {}
        for f in files:
            tree = etree.parse(str(f))
            for tc in tree.iter("testcase"):
                name = re.split(r"[(\[]", tc.get("name", ""))[0].strip()
                bad = any(c.tag in ("failure", "error", "skipped", "flakyFailure", "flakyError", "rerunFailure")
                          for c in tc)
                prev = status.get(name, "pass")
                status[name] = "fail" if (bad or prev == "fail") else "pass"
        return status, out

    def jacoco(self, fqn):
        """Line/branch coverage for the target source file (incl. nested classes).
        Returns dict or None."""
        x = self.root / "target/site/jacoco/jacoco.xml"
        if not x.exists():
            return None
        pkg, simple = fqn.rsplit(".", 1)
        parser = etree.XMLParser(load_dtd=False, no_network=True, resolve_entities=False)
        tree = etree.parse(str(x), parser)
        for p in tree.iter("package"):
            if p.get("name") != pkg.replace(".", "/"):
                continue
            for sf in p.iter("sourcefile"):
                if sf.get("name") != f"{simple}.java":
                    continue
                c = {e.get("type"): (int(e.get("missed")), int(e.get("covered")))
                     for e in sf if e.tag == "counter"}
                lines = [(int(l.get("nr")), int(l.get("mi")), int(l.get("ci")), int(l.get("mb")), int(l.get("cb")))
                         for l in sf.iter("line")]
                lm, lc = c.get("LINE", (0, 0))
                bm, bc = c.get("BRANCH", (0, 0))
                return {"line_missed": lm, "line_covered": lc, "branch_missed": bm, "branch_covered": bc,
                        "line_cov": lc / (lm + lc) if lm + lc else None,
                        "branch_cov": bc / (bm + bc) if bm + bc else None,
                        "lines": lines, "xml": x.read_bytes()}
        return None

    def pit(self, fqn):
        pkg, simple = fqn.rsplit(".", 1)
        shutil.rmtree(self.root / "target/pit-reports", ignore_errors=True)
        rc, out = self._mvn(["org.pitest:pitest-maven:mutationCoverage",
                             f"-DtargetClasses={fqn},{fqn}$*", f"-DtargetTests={pkg}.{simple}_GenTest",
                             f"-DpitThreads={self.pit_threads}"], timeout=3600)
        x = self.root / "target/pit-reports/mutations.xml"
        if not x.exists():
            return None, out
        tree = etree.parse(str(x))
        cnt = {}
        for m in tree.iter("mutation"):
            s = m.get("status")
            cnt[s] = cnt.get(s, 0) + 1
        total = sum(cnt.values())
        killed = cnt.get("KILLED", 0)
        no_cov = cnt.get("NO_COVERAGE", 0)
        res = {"mut_total": total, "mut_killed": killed, "mut_survived": cnt.get("SURVIVED", 0),
               "mut_no_cov": no_cov, "mut_timeout": cnt.get("TIMED_OUT", 0),
               "mut_other": total - killed - no_cov - cnt.get("SURVIVED", 0) - cnt.get("TIMED_OUT", 0),
               "mutation_score": killed / total if total else None,
               "test_strength": killed / (total - no_cov) if total - no_cov > 0 else None,
               "status_counts": cnt, "xml": x.read_bytes()}
        return res, out
