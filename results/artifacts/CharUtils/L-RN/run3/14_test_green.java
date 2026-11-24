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
        assertEquals(0, CharUtils.compare(x, x));
        assertEquals(1, CharUtils.compare(y, x));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
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
    @ValueSource(chars = {CharUtils.LF, CharUtils.CR, '\b', '\t', (char) 127})
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
    public void testToChar_CharacterNull() {
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
    public void testToChar_StringEmpty() {
        // Arrange
        String str = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(str));
    }

    @Test
    public void testToChar_CharacterWithDefault() {
        // Arrange
        Character ch = 'A';
        char defaultValue = 'B';

        // Act & Assert
        assertEquals('A', CharUtils.toChar(ch, defaultValue));
    }

    @Test
    public void testToChar_CharacterNullWithDefault() {
        // Arrange
        Character ch = null;
        char defaultValue = 'B';

        // Act & Assert
        assertEquals('B', CharUtils.toChar(ch, defaultValue));
    }

    @Test
    public void testToChar_StringWithDefault() {
        // Arrange
        String str = "Hello";
        char defaultValue = 'B';

        // Act & Assert
        assertEquals('H', CharUtils.toChar(str, defaultValue));
    }

    @Test
    public void testToChar_StringEmptyWithDefault() {
        // Arrange
        String str = "";
        char defaultValue = 'B';

        // Act & Assert
        assertEquals('B', CharUtils.toChar(str, defaultValue));
    }

    @Test
    public void testToCharacterObject_Char() {
        // Arrange
        char ch = 'A';

        // Act & Assert
        assertEquals(Character.valueOf(ch), CharUtils.toCharacterObject(ch));
    }

    @Test
    public void testToCharacterObject_String() {
        // Arrange
        String str = "Hello";

        // Act & Assert
        assertEquals(Character.valueOf('H'), CharUtils.toCharacterObject(str));
    }

    @Test
    public void testToCharacterObject_StringEmpty() {
        // Arrange
        String str = "";

        // Act & Assert
        assertNull(CharUtils.toCharacterObject(str));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    public void testToIntValue_Char(char ch) {
        // Act & Assert
        assertEquals(ch - 48, CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_CharNonNumeric() {
        // Arrange
        char ch = 'A';

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    public void testToIntValue_CharWithDefault(char ch) {
        // Arrange
        int defaultValue = 5;

        // Act & Assert
        assertEquals(ch - 48, CharUtils.toIntValue(ch, defaultValue));
    }

    @Test
    public void testToIntValue_CharNonNumericWithDefault() {
        // Arrange
        char ch = 'A';
        int defaultValue = 5;

        // Act & Assert
        assertEquals(defaultValue, CharUtils.toIntValue(ch, defaultValue));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    public void testToIntValue_Character(Character ch) {
        // Act & Assert
        assertEquals(ch - 48, CharUtils.toIntValue(ch));
    }

    @Test
    public void testToIntValue_CharacterNull() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CharUtils.toIntValue(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    public void testToIntValue_CharacterWithDefault(Character ch) {
        // Arrange
        int defaultValue = 5;

        // Act & Assert
        assertEquals(ch - 48, CharUtils.toIntValue(ch, defaultValue));
    }

    @Test
    public void testToIntValue_CharacterNullWithDefault() {
        // Arrange
        Character ch = null;
        int defaultValue = 5;

        // Act & Assert
        assertEquals(defaultValue, CharUtils.toIntValue(ch, defaultValue));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
    public void testToString_Char(char ch) {
        // Act & Assert
        assertEquals(String.valueOf(ch), CharUtils.toString(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
    public void testToString_Character(Character ch) {
        // Act & Assert
        assertEquals(String.valueOf(ch), CharUtils.toString(ch));
    }

    @Test
    public void testToString_CharacterNull() {
        // Arrange
        Character ch = null;

        // Act & Assert
        assertNull(CharUtils.toString(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
    public void testUnicodeEscaped_Char(char ch) {
        // Act & Assert
        assertEquals(String.format("\\u%04x", (int) ch), CharUtils.unicodeEscaped(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '@'})
    public void testUnicodeEscaped_Character(Character ch) {
        // Act & Assert
        assertEquals(String.format("\\u%04x", (int) ch), CharUtils.unicodeEscaped(ch));
    }

    @Test
    public void testUnicodeEscaped_CharacterNull() {
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
        void testIsAsciiControl_2() {
            assertTrue(CharUtils.isAsciiControl('\0'));
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertTrue(CharUtils.isAsciiControl((char) 127));
            assertFalse(CharUtils.isAsciiControl('A'));
        }

    @Test
        void testIsAsciiPrintable_2() {
            assertTrue(CharUtils.isAsciiPrintable(' '));
            assertTrue(CharUtils.isAsciiPrintable('~'));
            assertFalse(CharUtils.isAsciiPrintable('\0'));
            assertFalse(CharUtils.isAsciiPrintable((char) 127));
        }

    @Test
        void testToIntValue_CharacterNullWithDefault_2() {
            assertEquals(42, CharUtils.toIntValue((Character) null, 42));
        }

    @Test
        void testToString_CharBoundary() {
            assertEquals("\0", CharUtils.toString('\0'));
            assertEquals("~", CharUtils.toString('~'));
            assertEquals(String.valueOf((char) 127), CharUtils.toString((char) 127));
        }

    @Test
        void testUnicodeEscaped_BoundaryValues() {
            assertEquals("\\u0000", CharUtils.unicodeEscaped('\0'));
            assertEquals("\\u007f", CharUtils.unicodeEscaped((char) 127));
            assertEquals("\\u0080", CharUtils.unicodeEscaped((char) 128));
        }

    @Test
        void testUnicodeEscaped_CharacterNull_2() {
            assertNull(CharUtils.unicodeEscaped((Character) null));
        }
}
