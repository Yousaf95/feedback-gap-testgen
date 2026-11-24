package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSequenceUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testSubSequence(String cs) {
        assertEquals(cs, CharSequenceUtils.subSequence(cs, 0));
    }

    @Test
    public void testSubSequenceNull() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    
    @Test
    public void testToCharArrayEmpty() {
        assertArrayEquals(new char[0], CharSequenceUtils.toCharArray(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc"})
    public void testToCharArray(String cs) {
        assertArrayEquals(cs.toCharArray(), CharSequenceUtils.toCharArray(cs));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testCheckLaterThan1() {
            assertTrue(CharSequenceUtils.checkLaterThan1("abc", "a", 2, 0));
            assertFalse(CharSequenceUtils.checkLaterThan1("abc", "b", 2, 0));
            assertFalse(CharSequenceUtils.checkLaterThan1("abc", "c", 2, 0));
        }

    @Test
        public void testIndexOfCharSequence() {
            assertEquals(0, CharSequenceUtils.indexOf("hello", "l"));
            assertEquals(-1, CharSequenceUtils.indexOf("hello", "z"));
            assertEquals(3, CharSequenceUtils.indexOf("hello", "o"));
        }

    @Test
        public void testIndexOfCharSequenceStart() {
            assertEquals(2, CharSequenceUtils.indexOf("hello", "l", 2));
            assertEquals(-1, CharSequenceUtils.indexOf("hello", "l", 5));
            assertEquals(0, CharSequenceUtils.indexOf("hello", "h", -1));
        }

    @Test
        public void testLastIndexOfCharSequence() {
            assertEquals(3, CharSequenceUtils.lastIndexOf("hello", "o"));
            assertEquals(-1, CharSequenceUtils.lastIndexOf("hello", "z"));
            assertEquals(2, CharSequenceUtils.lastIndexOf("hello", "l"));
        }

    @Test
        public void testLastIndexOfCharSequenceStart() {
            assertEquals(2, CharSequenceUtils.lastIndexOf("hello", "l", 4));
            assertEquals(-1, CharSequenceUtils.lastIndexOf("hello", "l", -1));
            assertEquals(3, CharSequenceUtils.lastIndexOf("hello", "o", 5));
        }
}
