package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharSequenceUtils_GenTest {

    @Test
    public void testSubSequence_withNullCharSequence() {
        // Arrange
        CharSequence cs = null;
        int start = 0;

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertNull(result);
    }

    
    @Test
    public void testSubSequence_withStartGreaterThanLength() {
        // Arrange
        CharSequence cs = "abc";
        int start = 5;

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(cs, start));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testSubSequence_withValidStart(int start) {
        // Arrange
        CharSequence cs = "abc";

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("abc".substring(start), result);
    }

    @Test
    public void testToCharArray_withNullCharSequence() {
        // Arrange
        CharSequence source = null;

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[0], result);
    }

    @Test
    public void testToCharArray_withEmptyCharSequence() {
        // Arrange
        CharSequence source = "";

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[0], result);
    }

    @Test
    public void testToCharArray_withString() {
        // Arrange
        CharSequence source = "abc";

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[]{'a', 'b', 'c'}, result);
    }

    @Test
    public void testToCharArray_withStringBuilder() {
        // Arrange
        CharSequence source = new StringBuilder("abc");

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[]{'a', 'b', 'c'}, result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testIndexOf_withStringAndSearchChar() {
            assertEquals(2, CharSequenceUtils.indexOf("hello", 'l', 0));
        }

    @Test
        void testIndexOf_withStringBuilderAndSearchChar() {
            assertEquals(1, CharSequenceUtils.indexOf(new StringBuilder("abc"), 'b', 0));
        }

    @Test
        void testIndexOf_withStringBufferAndSearchChar() {
            assertEquals(2, CharSequenceUtils.indexOf(new StringBuffer("java"), 'v', 0));
        }

    @Test
        void testIndexOf_withSupplementaryCharacterFound() {
            assertEquals(2, CharSequenceUtils.indexOf("\uD835\uDC9C\uD835\uDC9E", 0x1D49E, 0));
        }

    @Test
        void testIndexOf_withStringAndSearchSequence() {
            assertEquals(2, CharSequenceUtils.indexOf("hello", "ll", 0));
        }

    @Test
        void testIndexOf_withStringBuilderAndSearchSequence() {
            assertEquals(3, CharSequenceUtils.indexOf(new StringBuilder("ababc"), "bc", 0));
        }

    @Test
        void testIndexOf_withStringBufferAndSearchSequence() {
            assertEquals(1, CharSequenceUtils.indexOf(new StringBuffer("xyz"), "yz", 0));
        }

    @Test
        void testLastIndexOf_withNullCharSequence() {
            assertEquals(-1, CharSequenceUtils.lastIndexOf(null, "test", 5));
        }

    @Test
        void testLastIndexOf_withNullSearchSequence() {
            assertEquals(-1, CharSequenceUtils.lastIndexOf("test", null, 2));
        }

    @Test
        void testLastIndexOf_withStringAndSearchSequence() {
            assertEquals(4, CharSequenceUtils.lastIndexOf("banana", "na", 6));
        }

    @Test
        void testLastIndexOf_withStringBuilderAndSearchSequence() {
            assertEquals(3, CharSequenceUtils.lastIndexOf(new StringBuilder("bananas"), "nan", 7));
        }

    @Test
        void testLastIndexOf_withStringBufferAndSearchSequence() {
            assertEquals(2, CharSequenceUtils.lastIndexOf(new StringBuffer("banana"), "na", 5));
        }

    @Test
        void testLastIndexOf_withSupplementaryCharacterFound() {
            assertEquals(2, CharSequenceUtils.lastIndexOf("\uD835\uDC9C\uD835\uDC9E", 0x1D49E, 3));
        }
}
