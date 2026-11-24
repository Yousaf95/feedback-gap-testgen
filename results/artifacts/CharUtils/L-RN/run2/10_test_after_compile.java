package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharUtils_GenTest {

    @Test
    public void testCompare() {
        // Arrange
        char x = 'a';
        char y = 'b';

        // Act & Assert
        assertEquals(-1, CharUtils.compare(x, y));
        assertEquals(0, CharUtils.compare(y, y));
        assertEquals(1, CharUtils.compare(y, x));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@', ' ', '\n', '\r', '\0'})
    public void testIsAscii(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAscii(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'Z'})
    public void testIsAsciiAlphaUpper(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiAlphaUpper(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z'})
    public void testIsAsciiAlphaLower(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiAlphaLower(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    public void testIsAsciiNumeric(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiNumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'z', '0', '9'})
    public void testIsAsciiAlphanumeric(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiAlphanumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'\n', '\r', '\0', (char) 127})
    public void testIsAsciiControl(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiControl(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {' ', '!', '~'})
    public void testIsAsciiPrintable(char ch) {
        // Act & Assert
        assertTrue(CharUtils.isAsciiPrintable(ch));
    }

    @Test
    public void testToChar_Character() {
        // Arrange
        Character ch = 'A';

        // Act & Assert
        assertEquals('A', CharUtils.toChar(ch));
    }

    @Test
    public void testToChar_Character_Null() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CharUtils.toChar(ch));
    }

    @Test
    public void testToChar_String() {
        // Arrange
        String str = "Hello";

        // Act & Assert
        assertEquals('H', CharUtils.toChar(str));
    }

    @Test
    public void testToChar_String_Empty() {
        // Arrange
        String str = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(str));
    }

    @Test
    public void testToChar_Character_DefaultValue() {
        // Arrange
        Character ch = null;
        char defaultValue = 'D';

        // Act & Assert
        assertEquals('D', CharUtils.toChar(ch, defaultValue));
    }

    @Test
    public void testToChar_String_DefaultValue() {
        // Arrange
        String str = "";
        char defaultValue = 'D';

        // Act & Assert
        assertEquals('D', CharUtils.toChar(str, defaultValue));
    }

    @Test
    public void testToIntValue_Char() {
        // Arrange
        char ch = '5';

        // Act & Assert
        assertEquals(5, CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_Char_NonNumeric() {
        // Arrange
        char ch = 'A';

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_Char_DefaultValue() {
        // Arrange
        char ch = 'A';
        int defaultValue = 9;

        // Act & Assert
        assertEquals(9, CharUtils.toIntValue(ch, defaultValue));
    }

    @Test
    public void testToIntValue_Character() {
        // Arrange
        Character ch = '5';

        // Act & Assert
        assertEquals(5, CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_Character_Null() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_Character_DefaultValue() {
        // Arrange
        Character ch = null;
        int defaultValue = 9;

        // Act & Assert
        assertEquals(9, CharUtils.toIntValue(ch, defaultValue));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@', ' ', '\n', '\r', '\0'})
    public void testToString_Char(char ch) {
        // Act & Assert
        assertEquals(String.valueOf(ch), CharUtils.toString(ch));
    }

    @Test
    public void testToString_Character() {
        // Arrange
        Character ch = 'A';

        // Act & Assert
        assertEquals("A", CharUtils.toString(ch));
    }

    @Test
    public void testToString_Character_Null() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertNull(CharUtils.toString(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@', ' ', '\n', '\r', '\0'})
    public void testUnicodeEscaped_Char(char ch) {
        // Arrange
        String expected = String.format("\\u%04x", (int) ch);

        // Act & Assert
        assertEquals(expected, CharUtils.unicodeEscaped(ch));
    }

    @Test
    public void testUnicodeEscaped_Character() {
        // Arrange
        Character ch = 'A';
        String expected = "\\u0041";

        // Act & Assert
        assertEquals(expected, CharUtils.unicodeEscaped(ch));
    }

    @Test
    public void testUnicodeEscaped_Character_Null() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertNull(CharUtils.unicodeEscaped(ch));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testIsAsciiAlpha() {
            assertTrue(CharUtils.isAsciiAlpha('a'));
            assertTrue(CharUtils.isAsciiAlpha('Z'));
            assertFalse(CharUtils.isAsciiAlpha('9'));
            assertFalse(CharUtils.isAsciiAlpha('@'));
        }

    @Test
        void testIsAsciiAlphanumeric_2() {
            assertTrue(CharUtils.isAsciiAlphanumeric('a'));
            assertTrue(CharUtils.isAsciiAlphanumeric('Z'));
            assertTrue(CharUtils.isAsciiAlphanumeric('9'));
            assertFalse(CharUtils.isAsciiAlphanumeric('@'));
        }

    @Test
        void testIsAsciiControl_2() {
            assertTrue(CharUtils.isAsciiControl('\0'));
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertTrue(CharUtils.isAsciiControl((char) 127));
            assertFalse(CharUtils.isAsciiControl('a'));
        }

    @Test
        void testIsAsciiPrintable_2() {
            assertTrue(CharUtils.isAsciiPrintable(' '));
            assertTrue(CharUtils.isAsciiPrintable('a'));
            assertTrue(CharUtils.isAsciiPrintable('~'));
            assertFalse(CharUtils.isAsciiPrintable('\0'));
            assertFalse(CharUtils.isAsciiPrintable((char) 127));
        }

    @Test
        void testToCharacterObject_Character() {
            assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject('a'));
            assertNull(CharUtils.toCharacterObject(null));
        }

    @Test
        void testToIntValue_Char_DefaultValue_2() {
            assertEquals(5, CharUtils.toIntValue('5', 0));
            assertEquals(0, CharUtils.toIntValue('a', 0));
        }

    @Test
        void testToString_Char_OutOfBounds() {
            assertEquals(String.valueOf((char) 128), CharUtils.toString((char) 128));
        }

    @Test
        void testUnicodeEscaped_Character_Null_2() {
            assertNull(CharUtils.unicodeEscaped(null));
        }
}
