package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.StringUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSequenceUtils_GenTest {

    
    
    @Test
    public void testSubSequence_withNull() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testToCharArray_withValidString(CharSequence cs) {
        char[] result = CharSequenceUtils.toCharArray(cs);
        assertArrayEquals(cs.toString().toCharArray(), result);
    }

    @Test
    public void testToCharArray_withNull() {
        char[] result = CharSequenceUtils.toCharArray(null);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testSubSequence_withValidString() {
            CharSequence cs = "Hello, World!";
            CharSequence result = CharSequenceUtils.subSequence(cs, 7);
            assertEquals("World!", result.toString());
        }

    @Test
        public void testSubSequence_withEmptyString() {
            CharSequence cs = "";
            CharSequence result = CharSequenceUtils.subSequence(cs, 0);
            assertEquals("", result.toString());
        }

    @Test
        public void testToCharArray_withEmptyString() {
            char[] result = CharSequenceUtils.toCharArray("");
            assertArrayEquals(new char[0], result);
        }

    @Test
        public void testToCharArray_withSingleChar() {
            char[] result = CharSequenceUtils.toCharArray("A");
            assertArrayEquals(new char[]{'A'}, result);
        }

    @Test
        public void testSubSequence_withNegativeStartIndex() {
            CharSequence cs = "Hello, World!";
            assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(cs, -1));
        }

    @Test
        public void testSubSequence_withExceedingStartIndex() {
            CharSequence cs = "Hello, World!";
            assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(cs, 20));
        }
}
