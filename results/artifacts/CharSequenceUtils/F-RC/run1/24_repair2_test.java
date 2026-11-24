package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.StringUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;

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
        public void testCheckLaterThan1_withEmptyCharSequence() {
            assertTrue(CharSequenceUtils.checkLaterThan1("", "a", 0, 0));
        }

    @Test
        public void testCheckLaterThan1_withSingleCharacterMatch() {
            assertFalse(CharSequenceUtils.checkLaterThan1("ab", "b", 2, 0));
        }

    @Test
        public void testCheckLaterThan1_withSingleCharacterMismatch() {
            assertFalse(CharSequenceUtils.checkLaterThan1("ab", "c", 2, 0));
        }

    @Test
        public void testIndexOf_withString() {
            assertEquals(3, CharSequenceUtils.indexOf("hello world", "world"));
        }

    @Test
        public void testIndexOf_withStringBuilder() {
            StringBuilder sb = new StringBuilder("hello world");
            assertEquals(6, CharSequenceUtils.indexOf(sb, "world"));
        }

    @Test
        public void testIndexOf_withStringBuffer() {
            StringBuffer sb = new StringBuffer("hello world");
            assertEquals(6, CharSequenceUtils.indexOf(sb, "world"));
        }

    @Test
        public void testIndexOf_withNullCharSequence() {
            assertThrows(NullPointerException.class, () -> CharSequenceUtils.indexOf(null, "world"));
        }

    @Test
        public void testIndexOf_withNullSearchChar() {
            assertThrows(NullPointerException.class, () -> CharSequenceUtils.indexOf("hello world", null));
        }

    @Test
        public void testLastIndexOf_withString() {
            assertEquals(6, CharSequenceUtils.lastIndexOf("hello world", "world"));
        }

    @Test
        public void testLastIndexOf_withStringBuilder() {
            StringBuilder sb = new StringBuilder("hello world");
            assertEquals(6, CharSequenceUtils.lastIndexOf(sb, "world"));
        }

    @Test
        public void testLastIndexOf_withStringBuffer() {
            StringBuffer sb = new StringBuffer("hello world");
            assertEquals(6, CharSequenceUtils.lastIndexOf(sb, "world"));
        }

    @Test
        public void testLastIndexOf_withNullCharSequence() {
            assertThrows(NullPointerException.class, () -> CharSequenceUtils.lastIndexOf(null, "world"));
        }

    @Test
        public void testLastIndexOf_withNullSearchChar() {
            assertThrows(NullPointerException.class, () -> CharSequenceUtils.lastIndexOf("hello world", null));
        }
}
