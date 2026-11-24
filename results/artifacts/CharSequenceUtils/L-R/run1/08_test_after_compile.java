package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testSubSequence_withEmptyCharSequence(int start) {
        // Arrange
        CharSequence cs = "";

        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);

        // Assert
        assertEquals("", result);
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
}
