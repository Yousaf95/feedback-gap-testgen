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
    @Test
        public void testSubSequenceNegativeStart() {
            assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence("test", -1));
        }

    @Test
        public void testSubSequenceEndExceedsLength() {
            assertEquals("test", CharSequenceUtils.subSequence("test", 0, 5));
        }

    @Test
        public void testToCharArraySingleChar() {
            char[] result = CharSequenceUtils.toCharArray("a");
            assertArrayEquals(new char[]{'a'}, result);
        }

    @Test
        public void testToCharArrayMultipleChars() {
            char[] result = CharSequenceUtils.toCharArray("hello");
            assertArrayEquals(new char[]{'h', 'e', 'l', 'l', 'o'}, result);
        }

    @Test
        public void testToCharArrayNullInput_2() {
            assertThrows(NullPointerException.class, () -> CharSequenceUtils.toCharArray(null));
        }
}
