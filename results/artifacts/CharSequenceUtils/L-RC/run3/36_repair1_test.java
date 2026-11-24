package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    // ---- added by coverage-guided refinement ----
    @Test
        void testCheckLaterThan1_withMismatch() {
            CharSequence cs = "abcdef";
            CharSequence searchChar = "xyz";
            int len2 = searchChar.length();
            int start1 = 0;
            assertEquals(false, checkLaterThan1(cs, searchChar, len2, start1));
        }

    @Test
        void testCheckLaterThan1_withMatch() {
            CharSequence cs = "abccba";
            CharSequence searchChar = "abc";
            int len2 = searchChar.length();
            int start1 = 0;
            assertEquals(true, checkLaterThan1(cs, searchChar, len2, start1));
        }

    @Test
        void testIndexOf_withStringInstance() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withStringBuilderInstance() {
            CharSequence cs = new StringBuilder("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withStringBufferInstance() {
            CharSequence cs = new StringBuffer("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    
    @Test
        void testIndexOf_withSingleChar() {
            CharSequence cs = "hello";
            int searchChar = 'l';
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withSupplementaryCharacter() {
            CharSequence cs = "\uD835\uDC00\uD835\uDC01"; // Mathematical bold capital a followed by b
            int searchChar = Character.toCodePoint('\uD835', '\uDC00');
            assertEquals(0, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testLastIndexOf_withNullSearchChar() {
            CharSequence cs = "hello";
            CharSequence searchChar = null;
            assertEquals(-1, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withNullCs() {
            CharSequence cs = null;
            CharSequence searchChar = "ll";
            assertEquals(-1, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStringInstance() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStringBuilderInstance() {
            CharSequence cs = new StringBuilder("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStringBufferInstance() {
            CharSequence cs = new StringBuffer("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStartGreaterThanLength() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 10));
        }

    @Test
        void testLastIndexOf_withEmptySearchChar() {
            CharSequence cs = "hello";
            CharSequence searchChar = "";
            assertEquals(5, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withNonSpecificInstance() {
            CharSequence cs = new CharSequence() {
                private final String str = "hello";
    
                @Override
                public int length() {
                    return str.length();
                }
    
                @Override
                public char charAt(int index) {
                    return str.charAt(index);
                }
    
                @Override
                public CharSequence subSequence(int start, int end) {
                    return str.subSequence(start, end);
                }
            };
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    private static boolean checkLaterThan1(final CharSequence cs, final CharSequence searchChar, final int len2, final int start1) {
            for (int i = 1, j = len2 - 1; i <= j; i++, j--) {
                if (cs.charAt(start1 + i) != searchChar.charAt(i) || cs.charAt(start1 + j) != searchChar.charAt(j)) {
                    return false;
                }
            }
            return true;
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSubSequence_withStartIndexLessThanZero() {
            assertEquals("abc", CharSequenceUtils.subSequence("abc", -1));
        }

    @Test
        void testToCharArray_withStringBuilderInstance() {
            char[] expected = {'a', 'b', 'c'};
            char[] result = CharSequenceUtils.toCharArray(new StringBuilder("abc"));
            assertArrayEquals(expected, result);
        }

    @Test
        void testToCharArray_withStringBufferInstance() {
            char[] expected = {'a', 'b', 'c'};
            char[] result = CharSequenceUtils.toCharArray(new StringBuffer("abc"));
            assertArrayEquals(expected, result);
        }

    @Test
        void testIndexOf_withNonSpecificInstance() {
            int index = CharSequenceUtils.indexOf(new StringBuilder("abc"), "b", 0);
            assertEquals(1, index);
        }

    @Test
        void testIndexOf_withStartLessThanZero() {
            int index = CharSequenceUtils.indexOf("abc", 'a', -1);
            assertEquals(0, index);
        }

    @Test
        void testLastIndexOf_withNonSpecificInstance_2() {
            int index = CharSequenceUtils.lastIndexOf(new StringBuilder("abc"), "b", 2);
            assertEquals(1, index);
        }

    @Test
        void testLastIndexOf_withStartGreaterThanLength_2() {
            int index = CharSequenceUtils.lastIndexOf("abc", 'c', 5);
            assertEquals(2, index);
        }
}
