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
        public void testSubSequence_StartIndexAtBeginning() {
            CharSequence cs = "Hello, World!";
            CharSequence result = CharSequenceUtils.subSequence(cs, 0);
            assertEquals("Hello, World!", result);
        }

    @Test
        public void testSubSequence_EndIndexAtStart() {
            CharSequence cs = "Hello, World!";
            assertThrows(StringIndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(cs, cs.length()));
        }

    @Test
        public void testToCharArray_NullInput() {
            char[] result = CharSequenceUtils.toCharArray(null);
            assertNull(result);
        }

    @Test
        public void testToCharArray_SingleCharacter() {
            CharSequence cs = "A";
            char[] result = CharSequenceUtils.toCharArray(cs);
            assertArrayEquals(new char[]{'A'}, result);
        }

    @Test
        public void testToCharArray_MultipleCharacters() {
            CharSequence cs = "Hello, World!";
            char[] result = CharSequenceUtils.toCharArray(cs);
            assertArrayEquals("Hello, World!".toCharArray(), result);
        }
}
