package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharSequenceUtils_GenTest {

    @Test
    public void testSubSequence_NullInput() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    @Test
    public void testSubSequence_EmptyString() {
        assertEquals("", CharSequenceUtils.subSequence("", 0));
    }

    @Test
    public void testSubSequence_NormalCase() {
        assertEquals("world", CharSequenceUtils.subSequence("hello world", 6));
    }

    @Test
    public void testSubSequence_StartIndexAtEnd() {
        assertEquals("", CharSequenceUtils.subSequence("hello world", 11));
    }

    @Test
    public void testSubSequence_StartIndexNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence("hello world", -1));
    }

    @Test
    public void testSubSequence_StartIndexGreaterThanLength() {
        assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence("hello world", 20));
    }

    
    @Test
    public void testToCharArray_EmptyString() {
        assertArrayEquals(new char[0], CharSequenceUtils.toCharArray(""));
    }

    @Test
    public void testToCharArray_NormalCase() {
        assertArrayEquals("hello".toCharArray(), CharSequenceUtils.toCharArray("hello"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIndexOf_String() {
            assertEquals(2, CharSequenceUtils.indexOf("hello world", "world"));
        }

    @Test
        public void testLastIndexOf_String() {
            assertEquals(6, CharSequenceUtils.lastIndexOf("hello world", "world"));
        }
}
