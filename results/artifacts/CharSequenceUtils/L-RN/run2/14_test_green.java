package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testSubSequence_withStartIndexEqualToLength() {
        // Arrange
        CharSequence cs = "abc";
        int start = 3;

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("", result);
    }

    @Test
    public void testSubSequence_withValidStartIndex() {
        // Arrange
        CharSequence cs = "hello";
        int start = 2;

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("llo", result);
    }

    @Test
    public void testSubSequence_withNegativeStartIndex() {
        // Arrange
        CharSequence cs = "world";
        int start = -1;

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(cs, start));
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

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testToCharArray_withVariousCharSequences(String source) {
        // Arrange & Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(source.toCharArray(), result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSubSequence_withStartIndexGreaterThanLength() {
            String input = "Hello";
            assertThrows(IndexOutOfBoundsException.class, () -> CharSequenceUtils.subSequence(input, 10));
        }

    @Test
        void testSubSequence_withEmptyCharSequence() {
            assertEquals("", CharSequenceUtils.subSequence("", 0).toString());
        }

    @Test
        void testToCharArray_withEmptyString() {
            assertArrayEquals(new char[0], CharSequenceUtils.toCharArray(""));
        }

    @Test
        void testToCharArray_withSingleCharacterString() {
            assertArrayEquals(new char[]{'A'}, CharSequenceUtils.toCharArray("A"));
        }

    @Test
        void testToCharArray_withStringBuilder() {
            StringBuilder sb = new StringBuilder("Hello");
            assertArrayEquals(new char[]{'H', 'e', 'l', 'l', 'o'}, CharSequenceUtils.toCharArray(sb));
        }
}
