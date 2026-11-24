"""Source-level helpers: context extraction (Stage 1), code-block extraction,
test-method listing, method removal (pruning) and merging (Stage 5)."""
import re
from jparse import parse, txt, modifiers, walk

TEST_ANN = ("@Test", "@ParameterizedTest", "@RepeatedTest", "@TestFactory", "@TestTemplate")
CHARS_PER_TOKEN = 3.5  # heuristic token estimate for the context budget


def est_tokens(s: str) -> int:
    return int(len(s) / CHARS_PER_TOKEN)


# ---------------------------------------------------------------- Stage 1
def _javadoc_before(node, src):
    p = node.prev_sibling
    if p is not None and p.type == "block_comment" and txt(p, src).startswith("/**"):
        return p
    return None


def _first_sentence(doc: str) -> str:
    body = doc[3:-2] if doc.endswith("*/") else doc[3:]
    lines = []
    for l in body.splitlines():
        l = l.strip().lstrip("*").strip()
        if l.startswith("@"):
            break
        lines.append(l)
    text = " ".join(x for x in lines if x)
    text = re.sub(r"\s+", " ", text).strip()
    m = re.search(r"(.+?[.!?])(\s|$)", text)
    s = m.group(1) if m else text
    return f"/** {s} */" if s else ""


def _header(node, src):
    body = node.child_by_field_name("body")
    end = body.start_byte if body is not None else node.end_byte
    return src[node.start_byte:end].decode("utf-8").rstrip()


def _signature(node, src):
    return _header(node, src) + ";"


def _called_names(node, src):
    names = set()
    for n in walk(node):
        if n.type == "method_invocation":
            nm = n.child_by_field_name("name")
            if nm is not None:
                names.add(txt(nm, src))
    return names


def _visibility(node, src):
    mods = modifiers(node, src).split()
    for v in ("public", "protected", "private"):
        if v in mods:
            return v
    return "package"


def _render_type(tnode, src, indent, budget_state, interface_parent=False):
    """Render a class/enum/interface declaration per Stage-1 rules.
    budget_state['full'] is a set of public/protected method node ids to keep with full bodies."""
    out = []
    doc = _javadoc_before(tnode, src)
    if doc is not None:
        out.append(indent + _first_sentence(txt(doc, src)))
    out.append(indent + _header(tnode, src) + " {")
    body = tnode.child_by_field_name("body")
    if body is None:
        out.append(indent + "}")
        return out
    is_iface = tnode.type in ("interface_declaration", "annotation_type_declaration")
    members = [c for c in body.named_children]
    # private/package helpers called by public/protected methods
    called = set()
    for m in members:
        if m.type == "method_declaration" and (is_iface or _visibility(m, src) in ("public", "protected")):
            called |= _called_names(m, src)
    helpers = []
    ind2 = indent + "    "
    for m in members:
        t = m.type
        if t in ("block_comment", "line_comment"):
            continue
        if t == "enum_body_declarations":
            # enum constants come first in enum body; declarations container holds members
            sub = _render_members(m.named_children, src, ind2, budget_state, called, helpers, is_iface)
            out.extend(sub)
            continue
        if t == "enum_constant":
            out.append(ind2 + txt(m, src) + ",")
            continue
        out.extend(_render_members([m], src, ind2, budget_state, called, helpers, is_iface))
    if helpers:
        out.append(ind2 + "// non-public helper methods (signatures only)")
        out.extend(helpers)
    out.append(indent + "}")
    return out


def _render_members(members, src, ind, budget_state, called, helpers, is_iface):
    out = []
    for m in members:
        t = m.type
        if t in ("block_comment", "line_comment", ";"):
            continue
        doc = _javadoc_before(m, src)
        docs = [ind + _first_sentence(txt(doc, src))] if doc is not None else []
        if t in ("field_declaration", "constant_declaration"):
            out += docs + [ind + txt(m, src)]
        elif t in ("constructor_declaration", "compact_constructor_declaration"):
            out += docs + [ind + txt(m, src)]
        elif t == "method_declaration":
            vis = "public" if is_iface else _visibility(m, src)
            if vis in ("public", "protected"):
                if m.id in budget_state["full"]:
                    out += docs + [ind + txt(m, src)]
                else:
                    out += docs + [ind + _signature(m, src) + "  // body omitted (context budget)"]
            else:
                nm = txt(m.child_by_field_name("name"), src)
                if nm in called:
                    helpers.append(ind + _signature(m, src))
        elif t in ("class_declaration", "enum_declaration", "interface_declaration",
                   "record_declaration", "annotation_type_declaration"):
            if _visibility(m, src) == "private":
                out.append(ind + _header(m, src) + " { /* private nested type */ }")
            else:
                out += _render_type(m, src, ind, budget_state)
        elif t in ("static_initializer", "block"):
            out.append(ind + txt(m, src))
    return out


def extract_context(src_text: str, budget_tokens: int):
    """Returns (context_text, info dict)."""
    src = src_text.encode("utf-8")
    root = parse(src).root_node
    pre = []
    for c in root.children:
        if c.type in ("package_declaration", "import_declaration"):
            pre.append(txt(c, src))
    types = [c for c in root.children if c.type.endswith("_declaration") and c.type not in
             ("package_declaration", "import_declaration")]
    # all public/protected methods in document order
    pubs = []
    for t in types:
        for n in walk(t):
            if n.type == "method_declaration":
                par = n.parent.parent if n.parent is not None else None
                iface = par is not None and par.type == "interface_declaration"
                if iface or _visibility(n, src) in ("public", "protected"):
                    pubs.append(n)
    state = {"full": {n.id for n in pubs}}

    def render():
        lines = pre + [""]
        for t in types:
            lines += _render_type(t, src, "", state)
        return "\n".join(lines)

    ctx = render()
    truncated = []
    # over budget: convert full bodies to signatures from the end (overflow methods)
    i = len(pubs) - 1
    while est_tokens(ctx) > budget_tokens and i >= 0:
        state["full"].discard(pubs[i].id)
        truncated.append(txt(pubs[i].child_by_field_name("name"), src))
        i -= 1
        if len(truncated) % 5 == 0 or i < 0:
            ctx = render()
    ctx = render()
    return ctx, {"context_tokens_est": est_tokens(ctx), "n_public_methods": len(pubs),
                 "n_truncated_to_signature": len(truncated), "truncated_methods": truncated[::-1]}


# ---------------------------------------------------------------- code blocks
FENCE = re.compile(r"```[ \t]*([A-Za-z0-9_+-]*)[^\n]*\n(.*?)(```|\Z)", re.S)


def extract_code_block(resp: str):
    """Largest fenced code block (an unterminated trailing fence is accepted; truncation is logged)."""
    blocks = [(m.group(2), m.group(3) == "") for m in FENCE.finditer(resp)]
    if not blocks:
        return None, False
    with_class = [b for b in blocks if re.search(r"\bclass\b", b[0])]
    pool = with_class or blocks
    code, unterminated = max(pool, key=lambda b: len(b[0]))
    return code, unterminated


def normalize_test_class(code: str, package: str, test_class: str):
    """Force package and top-level class name (file name must match). Returns (code, notes)."""
    notes = []
    src = code.encode("utf-8")
    root = parse(src).root_node
    pk = [c for c in root.children if c.type == "package_declaration"]
    if not pk:
        code = f"package {package};\n\n" + code
        notes.append("added package")
    elif txt(pk[0], src).replace("package", "").strip(" ;") != package:
        code = code.replace(txt(pk[0], src), f"package {package};", 1)
        notes.append("fixed package")
    src = code.encode("utf-8")
    root = parse(src).root_node
    tops = [c for c in root.children if c.type == "class_declaration"]
    if tops:
        nm = tops[0].child_by_field_name("name")
        old = txt(nm, src)
        if old != test_class:
            code = re.sub(r"\b%s\b" % re.escape(old), test_class, code)
            notes.append(f"renamed class {old}->{test_class}")
    return code, notes


# ---------------------------------------------------------------- test methods
def _annotations(m, src):
    return modifiers(m, src)


def is_test_method(m, src):
    a = _annotations(m, src)
    return any(re.search(re.escape(t) + r"\b", a) for t in TEST_ANN)


def methods(code: str):
    """All method declarations inside any class of the file: list of dicts."""
    src = code.encode("utf-8")
    root = parse(src).root_node
    res = []
    for n in walk(root):
        if n.type == "method_declaration":
            nm = n.child_by_field_name("name")
            start = n.start_byte
            doc = n.prev_sibling
            # attach preceding comments to the method for removal
            while doc is not None and doc.type in ("block_comment", "line_comment") \
                    and doc.end_point[0] >= n.start_point[0] - 1 - 0:
                start = doc.start_byte
                doc = doc.prev_sibling
            res.append({"name": txt(nm, src), "test": is_test_method(n, src),
                        "start_line": n.start_point[0] + 1, "end_line": n.end_point[0] + 1,
                        "start_byte": start, "end_byte": n.end_byte,
                        "text": src[start:n.end_byte].decode("utf-8")})
    return res


def test_method_names(code: str):
    return [m["name"] for m in methods(code) if m["test"]]


def count_tests(code: str) -> int:
    return len(test_method_names(code)) if code else 0


def _cut_ranges(code: str, ranges):
    src = code.encode("utf-8")
    ranges = sorted(set(ranges), reverse=True)
    for a, b in ranges:
        # swallow trailing newline
        if b < len(src) and src[b:b + 1] == b"\n":
            b += 1
        src = src[:a] + src[b:]
    return src.decode("utf-8")


def remove_methods_by_name(code: str, names):
    names = set(names)
    rng = [(m["start_byte"], m["end_byte"]) for m in methods(code) if m["name"] in names]
    return _cut_ranges(code, rng)


def prune_error_lines(code: str, lines):
    """Remove methods / imports / fields that contain compile-error lines.
    Returns (new_code, removed_test_names, unresolved_lines)."""
    src = code.encode("utf-8")
    root = parse(src).root_node
    lines = set(lines)
    cut, removed, covered = [], [], set()
    for m in methods(code):
        hit = {l for l in lines if m["start_line"] <= l <= m["end_line"]}
        if hit:
            cut.append((m["start_byte"], m["end_byte"]))
            covered |= hit
            if m["test"]:
                removed.append(m["name"])
    for n in walk(root):
        if n.type in ("import_declaration", "field_declaration"):
            a, b = n.start_point[0] + 1, n.end_point[0] + 1
            hit = {l for l in lines if a <= l <= b}
            if hit and not any(s <= n.start_byte and n.end_byte <= e for s, e in cut):
                cut.append((n.start_byte, n.end_byte))
                covered |= hit
    return _cut_ranges(code, cut), removed, sorted(lines - covered)


def merge_new_methods(base: str, addition: str, test_class: str):
    """Merge methods (+imports, +fields) from `addition` into `base`. Name clashes are renamed.
    Returns (merged_code, list_of_new_test_method_names, n_new_methods_total)."""
    if not re.search(r"\bclass\b", addition):
        addition = f"class {test_class} {{\n{addition}\n}}"
    asrc = addition.encode("utf-8")
    aroot = parse(asrc).root_node
    base_names = {m["name"] for m in methods(base)}
    base_src = base.encode("utf-8")
    broot = parse(base_src).root_node
    base_imports = {txt(c, base_src).strip() for c in broot.children if c.type == "import_declaration"}
    new_imports = [txt(c, asrc).strip() for c in aroot.children
                   if c.type == "import_declaration" and txt(c, asrc).strip() not in base_imports]
    top = [c for c in aroot.children if c.type == "class_declaration"]
    body_nodes = top[0].child_by_field_name("body").named_children if top else []
    base_fields = set()
    for n in walk(broot):
        if n.type == "variable_declarator" and n.parent.type == "field_declaration":
            base_fields.add(txt(n.child_by_field_name("name"), base_src))
    chunks, new_tests = [], []
    used = set(base_names)
    for n in body_nodes:
        if n.type == "method_declaration":
            nm = txt(n.child_by_field_name("name"), asrc)
            text = txt(n, asrc)
            doc = n.prev_sibling
            if nm in used:
                k = 2
                while f"{nm}_{k}" in used:
                    k += 1
                new = f"{nm}_{k}"
                text = re.sub(r"\b%s(\s*\()" % re.escape(nm), new + r"\1", text, count=1)
                nm = new
            used.add(nm)
            chunks.append(text)
            if is_test_method(n, asrc):
                new_tests.append(nm)
        elif n.type == "field_declaration":
            decl = [d for d in n.named_children if d.type == "variable_declarator"]
            if decl and all(txt(d.child_by_field_name("name"), asrc) not in base_fields for d in decl):
                chunks.append(txt(n, asrc))
    # insert imports after the last import (or package)
    merged = base
    if new_imports:
        imps = [c for c in broot.children if c.type in ("import_declaration", "package_declaration")]
        pos = imps[-1].end_byte if imps else 0
        merged = (base_src[:pos].decode() + "\n" + "\n".join(new_imports) + base_src[pos:].decode())
    msrc = merged.encode("utf-8")
    mroot = parse(msrc).root_node
    mtop = [c for c in mroot.children if c.type == "class_declaration"]
    if not mtop:
        return merged, [], 0
    body = mtop[0].child_by_field_name("body")
    pos = body.end_byte - 1  # the closing brace
    ins = "\n    // ---- added by coverage-guided refinement ----\n" + "\n\n".join(
        "    " + c.replace("\n", "\n    ") if not c.startswith("    ") else c for c in chunks) + "\n"
    merged = msrc[:pos].decode() + ins + msrc[pos:].decode()
    return merged, new_tests, len(chunks)
