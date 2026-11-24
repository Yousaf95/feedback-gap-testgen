package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.nio.CharBuffer;

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
        void testIndexOf_withString() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withStringBuilder() {
            CharSequence cs = new StringBuilder("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withStringBuffer() {
            CharSequence cs = new StringBuffer("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withNonString() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withSingleCharFound() {
            CharSequence cs = "hello";
            int searchChar = 'l';
            assertEquals(2, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withSingleCharNotFound() {
            CharSequence cs = "hello";
            int searchChar = 'z';
            assertEquals(-1, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withSupplementaryCharacterFound() {
            CharSequence cs = "\uD835\uDC00\uD835\uDC01";
            int searchChar = Character.toCodePoint('\uD835', '\uDC00');
            assertEquals(0, CharSequenceUtils.indexOf(cs, searchChar, 0));
        }

    @Test
        void testIndexOf_withSupplementaryCharacterNotFound() {
            CharSequence cs = "\uD835\uDC00\uD835\uDC01";
            int searchChar = Character.toCodePoint('\uD835', '\uDC02');
            assertEquals(-1, CharSequenceUtils.indexOf(cs, searchChar, 0));
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
        void testLastIndexOf_withString() {
            CharSequence cs = "hello";
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStringBuilder() {
            CharSequence cs = new StringBuilder("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withStringBuffer() {
            CharSequence cs = new StringBuffer("hello");
            CharSequence searchChar = "ll";
            assertEquals(2, CharSequenceUtils.lastIndexOf(cs, searchChar, 5));
        }

    @Test
        void testLastIndexOf_withNonString() {
            CharSequence cs = "hello";
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
        void testLastIndexOf_withSearchCharLongerThanCs() {
            CharSequence cs = "he";
            CharSequence searchChar = "hello";
            assertEquals(-1, CharSequenceUtils.lastIndexOf(cs, searchChar, 2));
        }

    @Test
        void testLastIndexOf_withSingleCharFound() {
            CharSequence cs = "hello";
            int searchChar = 'l';
            assertEquals(3, CharSequenceUtils.lastIndexOf(cs, (char) searchChar, 5));
        }

    @Test
        void testLastIndexOf_withSingleCharNotFound() {
            CharSequence cs = "hello";
            int searchChar = 'z';
            assertEquals(-1, CharSequenceUtils.lastIndexOf(cs, (char) searchChar, 5));
        }

    
    @Test
        public void testToCharArray_withEmptyCharSequence() {
            char[] result = CharSequenceUtils.toCharArray("");
            assertEquals(0, result.length);
        }

    @Test
        public void testIndexOf_withStringBuilderNotFound() {
            StringBuilder sb = new StringBuilder("test");
            assertEquals(-1, CharSequenceUtils.indexOf(sb, "xyz", 0));
        }

    @Test
        public void testIndexOf_withStringBufferNotFound() {
            StringBuffer sb = new StringBuffer("test");
            assertEquals(-1, CharSequenceUtils.indexOf(sb, "xyz", 0));
        }

    @Test
        public void testIndexOf_withNonStringFound() {
            CharBuffer cb = CharBuffer.wrap("test");
            assertEquals(2, CharSequenceUtils.indexOf(cb, 's', 0));
        }

    @Test
        public void testIndexOf_withSupplementaryCharacterNotFound_2() {
            String str = "test";
            assertEquals(-1, CharSequenceUtils.indexOf(str, Character.toChars(Character.MIN_SUPPLEMENTARY_CODE_POINT)[0], 0));
        }

    @Test
        public void testLastIndexOf_withStringBuilderNotFound() {
            StringBuilder sb = new StringBuilder("test");
            assertEquals(-1, CharSequenceUtils.lastIndexOf(sb, "xyz", 0));
        }

    @Test
        public void testLastIndexOf_withStringBufferNotFound() {
            StringBuffer sb = new StringBuffer("test");
            assertEquals(-1, CharSequenceUtils.lastIndexOf(sb, "xyz", 0));
        }

    @Test
        public void testLastIndexOf_withNonStringFound() {
            CharBuffer cb = CharBuffer.wrap("test");
            assertEquals(2, CharSequenceUtils.lastIndexOf(cb, 's', 3));
        }

    @Test
        public void testLastIndexOf_withSupplementaryCharacterNotFound() {
            String str = "test";
            assertEquals(-1, CharSequenceUtils.lastIndexOf(str, Character.toChars(Character.MIN_SUPPLEMENTARY_CODE_POINT)[0], 0));
        }
}
