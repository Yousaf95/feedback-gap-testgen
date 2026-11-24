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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testSubSequence_withValidStartIndex(final int start) {
        // Arrange
        CharSequence cs = "Hello";

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("Hello".substring(start), result.toString());
    }

    @Test
    public void testSubSequence_withStartIndexEqualToLength() {
        // Arrange
        CharSequence cs = "Hello";
        int start = 5;

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("", result.toString());
    }

    @Test
    public void testSubSequence_withStartIndexGreaterThanLength() {
        // Arrange
        CharSequence cs = "Hello";
        int start = 6;

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

    @Test
    public void testToCharArray_withEmptyString() {
        // Arrange
        CharSequence source = "";

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[0], result);
    }

    @Test
    public void testToCharArray_withNonEmptyString() {
        // Arrange
        CharSequence source = "Hello";

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[]{'H', 'e', 'l', 'l', 'o'}, result);
    }

    @Test
    public void testToCharArray_withStringBuilder() {
        // Arrange
        CharSequence source = new StringBuilder("Hello");

        // Act
        char[] result = CharSequenceUtils.toCharArray(source);

        // Assert
        assertArrayEquals(new char[]{'H', 'e', 'l', 'l', 'o'}, result);
    }

    
    @Test
        void testToCharArray_withNullCharSequence_2() {
            char[] result = CharSequenceUtils.toCharArray(null);
            assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
        }

    @Test
        void testSubSequence_withEmptyString() {
            CharSequence result = CharSequenceUtils.subSequence("", 0);
            assertEquals("", result.toString());
        }

    @Test
        void testSubSequence_withNonEmptyStringAndZeroStartIndex() {
            CharSequence result = CharSequenceUtils.subSequence("hello", 0);
            assertEquals("hello", result.toString());
        }

    @Test
        void testToCharArray_withStringBuilder_2() {
            char[] result = CharSequenceUtils.toCharArray(new StringBuilder("test"));
            assertArrayEquals(new char[]{'t', 'e', 's', 't'}, result);
        }
}
