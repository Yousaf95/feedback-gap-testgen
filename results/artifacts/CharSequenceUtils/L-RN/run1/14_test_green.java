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

    
    @Test
        void testSubSequence_withEmptyCharSequence() {
            CharSequence result = CharSequenceUtils.subSequence("", 0);
            assertNotNull(result);
            assertTrue(result instanceof String);
            assertEquals("", result.toString());
        }

    @Test
        void testToCharArray_withNullElementsInStringBuilder() {
            StringBuilder sb = new StringBuilder();
            sb.append("test").append((char) 0).append("example");
            char[] expectedArray = {'t', 'e', 's', 't', '\0', 'e', 'x', 'a', 'm', 'p', 'l', 'e'};
            assertArrayEquals(expectedArray, CharSequenceUtils.toCharArray(sb));
        }

    @Test
        void testToCharArray_withNonStringCharSequence() {
            CharSequence cs = new CharSequence() {
                @Override
                public int length() {
                    return 3;
                }
                @Override
                public char charAt(int index) {
                    switch (index) {
                        case 0: return 'a';
                        case 1: return 'b';
                        case 2: return 'c';
                        default: throw new IndexOutOfBoundsException();
                    }
                }
                @Override
                public CharSequence subSequence(int start, int end) {
                    throw new UnsupportedOperationException();
                }
            };
            char[] expectedArray = {'a', 'b', 'c'};
            assertArrayEquals(expectedArray, CharSequenceUtils.toCharArray(cs));
        }

    @Test
        void testSubSequence_withStartEqualToLength() {
            CharSequence result = CharSequenceUtils.subSequence("test", 4);
            assertNotNull(result);
            assertTrue(result instanceof String);
            assertEquals("", result.toString());
        }
}
