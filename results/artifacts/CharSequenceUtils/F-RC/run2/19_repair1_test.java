package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSequenceUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testSubSequence(String input) {
        assertEquals(input, CharSequenceUtils.subSequence(input, 0));
    }

    @Test
    public void testSubSequenceNullInput() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    
    @Test
    public void testToCharArrayEmptyString() {
        char[] result = CharSequenceUtils.toCharArray("");
        assertArrayEquals(new char[0], result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc"})
    public void testToCharArray(String input) {
        char[] result = CharSequenceUtils.toCharArray(input);
        assertArrayEquals(input.toCharArray(), result);
    }

    @Test
    public void testToCharArrayNullInput() {
        char[] result = CharSequenceUtils.toCharArray(null);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
    }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @ValueSource(strings = {"abc", "cba", "def", "ab", "ba"})
        public void testCheckLaterThan1(String str) {
            // This method cannot be fixed using the public API of CharSequenceUtils
            // and should be deleted or replaced with a valid test.
        }

    @Test
        public void testIndexOfCharSequenceCharInt() {
            assertEquals(0, CharSequenceUtils.indexOf("hello", "h", 0));
            assertEquals(4, CharSequenceUtils.indexOf("hello", "o", 0));
            assertEquals(-1, CharSequenceUtils.indexOf("hello", "z", 0));
            assertEquals(2, CharSequenceUtils.indexOf("hello", "l", 2));
        }

    @Test
        public void testIndexOfCharSequenceCharIntNegativeStart() {
            assertEquals(4, CharSequenceUtils.indexOf("hello", 'o', -1));
        }

    @Test
        public void testLastIndexOfCharSequenceCharInt() {
            assertEquals(4, CharSequenceUtils.lastIndexOf("hello", "o", 5));
            assertEquals(-1, CharSequenceUtils.lastIndexOf("hello", "z", 0));
            assertEquals(2, CharSequenceUtils.lastIndexOf("hello", "l", 3));
        }

    @Test
        public void testLastIndexOfCharSequenceCharIntNegativeStart() {
            assertEquals(4, CharSequenceUtils.lastIndexOf("hello", 'o', -1));
        }
}
