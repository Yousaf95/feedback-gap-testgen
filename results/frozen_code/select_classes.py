"""Deterministic class selection (spec section 3). Usage: python select_classes.py <src_main_java> <out_json>"""
import json, re, sys, pathlib
sys.path.insert(0, str(pathlib.Path(__file__).parent))
from jparse import parse, txt, modifiers, walk

SRC, OUT = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2])
EXCL_PKG = ("org.apache.commons.lang3.concurrent", "org.apache.commons.lang3.reflect")
# Manual exclusion (criterion "no heavy concurrency"), fixed before any eval run:
MANUAL_EXCL = {"org.apache.commons.lang3.ThreadUtils": "thread/thread-group state (concurrency, environment-dependent)"}
BRANCH_NODES = {"if_statement", "for_statement", "enhanced_for_statement", "while_statement",
                "do_statement", "switch_label", "catch_clause", "ternary_expression"}

def ncloc(text):
    text = re.sub(r"/\*.*?\*/", "", text, flags=re.S)
    text = re.sub(r"//[^\n]*", "", text)
    return sum(1 for l in text.splitlines() if l.strip())

def branches(root, src):
    n = 0
    for nd in walk(root):
        if nd.type in BRANCH_NODES:
            n += 1
        elif nd.type == "binary_expression" and nd.child_by_field_name("operator") is not None \
                and txt(nd.child_by_field_name("operator"), src) in ("&&", "||"):
            n += 1
    return n

cands, rejected = [], {}
for f in sorted(SRC.rglob("*.java")):
    if f.name in ("package-info.java", "module-info.java"):
        continue
    src = f.read_bytes()
    root = parse(src).root_node
    pkg = next(txt(c, src).replace("package", "").strip(" ;") for c in root.children if c.type == "package_declaration")
    top = [c for c in root.children if c.type.endswith("_declaration") and c.child_by_field_name("name")
           and txt(c.child_by_field_name("name"), src) == f.stem]
    if not top:
        continue
    t = top[0]
    fqn = f"{pkg}.{f.stem}"
    mods = modifiers(t, src)
    loc, br = ncloc(src.decode()), branches(t, src)
    reason = None
    if fqn in MANUAL_EXCL: reason = "manual: " + MANUAL_EXCL[fqn]
    elif pkg.startswith(EXCL_PKG): reason = "excluded package"
    elif t.type not in ("class_declaration", "enum_declaration"): reason = f"kind={t.type}"
    elif "public" not in mods: reason = "not public"
    elif "@Deprecated" in mods: reason = "deprecated"
    elif not (80 <= loc <= 600): reason = f"ncloc={loc}"
    elif br < 15: reason = f"branches={br}"
    if reason:
        rejected[fqn] = reason
    else:
        cands.append({"fqn": fqn, "ncloc": loc, "branches": br, "kind": t.type,
                      "abstract": "abstract" in mods.split()})

cands.sort(key=lambda c: c["fqn"])
out = {
    "rule": "public top-level class/enum in org.apache.commons.lang3.** excluding concurrent.* and reflect.*, "
            "not @Deprecated, 80<=NCLOC<=600 (non-blank, non-comment lines of the file), >=15 branch constructs "
            "(if/for/foreach/while/do/case-label/catch/ternary/&&/||, counted with tree-sitter-java); "
            "sorted alphabetically by FQN; first 20 = eval, next 3 = dev. Manual exclusions: " + json.dumps(MANUAL_EXCL),
    "n_candidates": len(cands),
    "eval": cands[:20], "dev": cands[20:23],
    "all_candidates": cands, "rejected": rejected,
}
OUT.write_text(json.dumps(out, indent=2))
print(len(cands), "candidates")
for i, c in enumerate(cands[:23]): print(i, c["fqn"], c["ncloc"], c["branches"], c["kind"], "abstract" if c["abstract"] else "")
