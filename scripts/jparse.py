"""tree-sitter helpers shared by selection, context extraction and pruning."""
import tree_sitter_java as tsj, tree_sitter as ts
LANG = ts.Language(tsj.language())
PARSER = ts.Parser(LANG)

def parse(src: bytes):
    return PARSER.parse(src)

def txt(node, src):
    return src[node.start_byte:node.end_byte].decode("utf-8")

def modifiers(node, src):
    for c in node.children:
        if c.type == "modifiers":
            return txt(c, src)
    return ""

def walk(node):
    yield node
    for c in node.children:
        yield from walk(c)
