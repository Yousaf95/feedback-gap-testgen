# Example generated tests

## Example 1 (good): CharUtils, L-RC, seed 3

Branch coverage 97.9%, mutation score 94.3%, 41 passing tests. Full file: `artifacts/CharUtils/L-RC/run3/21_final_test.java`.

First test methods of the suite:

```java
    @Test
    public void testCompare() {
        // Arrange
        char x = 'a';
        char y = 'b';

        // Act & Assert
        assertEquals(-1, CharUtils.compare(x, y));
        assertEquals(0, CharUtils.compare(x, x));
        assertEquals(1, CharUtils.compare(y, x));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
    public void testIsAscii(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAscii(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'Z'})
    public void testIsAsciiAlphaUpper(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiAlphaUpper(ch));
    }
```

Methods added by the coverage loop (after the marker comment):

```java
    // ---- added by coverage-guided refinement ----
    @Test
        void testIsAsciiBranches() {
            assertFalse(CharUtils.isAscii((char) 128));
            assertTrue(CharUtils.isAscii('a'));
        }

    @Test
        void testIsAsciiAlphaLowerBranches() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
            assertFalse(CharUtils.isAsciiAlphaLower('9'));
            assertFalse(CharUtils.isAsciiAlphaLower('!'));
            assertTrue(CharUtils.isAsciiAlphaLower('z'));
        }

    @Test
        void testIsAsciiAlphanumericBranches() {
            assertFalse(CharUtils.isAsciiAlphanumeric('!'));
            assertTrue(CharUtils.isAsciiAlphanumeric('a'));
            assertTrue(CharUtils.isAsciiAlphanumeric('9'));
        }
```

## Example 2 (typical runtime failure, removed in Stage 4): Range, S-ZS, seed 1

Surefire: `Range_GenTest.testIsOverlappedBy:125 expected: <false> but was: <true>`

```java
    @Test
    public void testIsOverlappedBy() {
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(3, 7);
        assertTrue(range1.isOverlappedBy(range2));
        assertFalse(range2.isOverlappedBy(range1));
    }
```

## Example 3 (typical compile failure, private access; not fixed by repair, pruned): CharSequenceUtils, S-RC, seed 1, refinement round 1

Compiler errors given to the repair prompt (excerpt):

```
in method testCheckLaterThan1_NotEqual: checkLaterThan1(java.lang.CharSequence,java.lang.CharSequence,int,int) has private access in org.apache.commons.lang3.CharSequenceUtils
    code: assertFalse(CharSequenceUtils.checkLaterThan1("abc", "def", 3, 0));
in method testCheckLaterThan1_Equal: checkLaterThan1(java.lang.CharSequence,java.lang.CharSequence,int,int) has private access in org.apache.commons.lang3.CharSequenceUtils
    code: assertTrue(CharSequenceUtils.checkLaterThan1("aba", "aba", 3, 0));
in method testIndexOf_String: no suitable method found for indexOf(java.lang.String,java.lang.String) (method org.apache.commons.lang3.CharSequenceUtils.indexOf(java.lang.CharSequence,java.lang.CharSequence,int) is not applicable; (actual and formal argument lists differ in length); method org.apache.commons.lang3.CharSequenceUtils.indexOf(java.lang.CharSequence,int,int) is not applicable)
    code: assertEquals(2, CharSequenceUtils.indexOf("hello world", "world"));
```

```java
    // ---- added by coverage-guided refinement ----
    @Test
        public void testCheckLaterThan1_NotEqual() {
            assertFalse(CharSequenceUtils.checkLaterThan1("abc", "def", 3, 0));
        }

    @Test
        public void testIndexOf_String() {
            assertEquals(2, CharSequenceUtils.indexOf("hello world", "world"));
        }
```
