"""Repair success in the R configurations: runs whose initial test class did not compile (repair needed) and
whether the LLM repair rounds made it compile without pruning. Usage: python repair_success.py <artifacts_dir> <out.md>"""
import re, sys, pathlib
art, out = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2])
L = ["# Repair success (R configurations)", "",
     "A run *needs repair* if its R folder contains at least one repair round; it is *fixed by repair* if the compile log "
     "after the last repair round contains no error in the generated test file (no pruning needed).", "",
     "| Model | runs needing repair | fixed by repair | share |", "|---|---|---|---|"]
for m in ("S", "L"):
    need = fixed = 0
    for d in sorted(art.glob(f"*/{m}-R/run*")):
        reps = sorted(p for p in d.iterdir() if re.search(r"_repair\d+_compile\.log$", p.name))
        if not reps:
            continue
        need += 1
        if not re.search(r"\[ERROR\].*GenTest\.java", reps[-1].read_text(errors="replace")):
            fixed += 1
    L.append(f"| {m} | {need} | {fixed} | {100 * fixed / need:.0f}% |")
out.write_text("\n".join(L) + "\n")
print("\n".join(L))
