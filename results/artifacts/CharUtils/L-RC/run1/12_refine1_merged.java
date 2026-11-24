package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharUtils_GenTest {

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'z'})
    void testIsAsciiAlphaLower_true(char ch) {
        assertTrue(CharUtils.isAsciiAlphaLower(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'B', 'Z'})
    void testIsAsciiAlphaLower_false(char ch) {
        assertFalse(CharUtils.isAsciiAlphaLower(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '1', '9'})
    void testIsAsciiNumeric_true(char ch) {
        assertTrue(CharUtils.isAsciiNumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'A', '@', ' ', '\n'})
    void testIsAsciiNumeric_false(char ch) {
        assertFalse(CharUtils.isAsciiNumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'0', '1', '9', 'a', 'b', 'z', 'A', 'B', 'Z'})
    void testIsAsciiAlphanumeric_true(char ch) {
        assertTrue(CharUtils.isAsciiAlphanumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'@', ' ', '\n'})
    void testIsAsciiAlphanumeric_false(char ch) {
        assertFalse(CharUtils.isAsciiAlphanumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'B', 'Z'})
    void testIsAsciiAlphaUpper_true(char ch) {
        assertTrue(CharUtils.isAsciiAlphaUpper(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'z', '@', ' ', '\n'})
    void testIsAsciiAlphaUpper_false(char ch) {
        assertFalse(CharUtils.isAsciiAlphaUpper(ch));
    }

    
    @ParameterizedTest
    @ValueSource(chars = {'@', ' ', '\n', '!', '#'})
    void testIsAsciiAlpha_false(char ch) {
        assertFalse(CharUtils.isAsciiAlpha(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {0, 127})
    void testIsAsciiControl_true(char ch) {
        assertTrue(CharUtils.isAsciiControl(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {' ', '!', '#', 'A', 'a', '0'})
    void testIsAsciiControl_false(char ch) {
        assertFalse(CharUtils.isAsciiControl(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {32, 126})
    void testIsAsciiPrintable_true(char ch) {
        assertTrue(CharUtils.isAsciiPrintable(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {0, 31, 127})
    void testIsAsciiPrintable_false(char ch) {
        assertFalse(CharUtils.isAsciiPrintable(ch));
    }

    @Test
    void testCompare_equal() {
        assertEquals(0, CharUtils.compare('a', 'a'));
    }

    @Test
    void testCompare_lessThan() {
        assertTrue(CharUtils.compare('a', 'b') < 0);
    }

    @Test
    void testCompare_greaterThan() {
        assertTrue(CharUtils.compare('b', 'a') > 0);
    }

    @Test
    void testToChar_Character_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CharUtils.toChar((Character) null));
    }

    @Test
    void testToChar_Character_notNull_returnsValue() {
        assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
    }

    @Test
    void testToChar_String_empty_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(""));
    }

    @Test
    void testToChar_String_notEmpty_returnsFirstCharacter() {
        assertEquals('a', CharUtils.toChar("abc"));
    }

    @Test
    void testToChar_String_null_withDefaultValue_returnsDefaultValue() {
        assertEquals('x', CharUtils.toChar((String) null, 'x'));
    }

    @Test
    void testToChar_String_empty_withDefaultValue_returnsDefaultValue() {
        assertEquals('x', CharUtils.toChar("", 'x'));
    }

    @Test
    void testToChar_String_notEmpty_withDefaultValue_returnsFirstCharacter() {
        assertEquals('a', CharUtils.toChar("abc", 'x'));
    }

    @Test
    void testToIntValue_char_numeric_returnsIntValue() {
        assertEquals(5, CharUtils.toIntValue('5'));
    }

    @Test
    void testToIntValue_char_nonNumeric_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
    }

    @Test
    void testToIntValue_char_numeric_withDefaultValue_returnsIntValue() {
        assertEquals(5, CharUtils.toIntValue('5', 0));
    }

    @Test
    void testToIntValue_char_nonNumeric_withDefaultValue_returnsDefaultValue() {
        assertEquals(0, CharUtils.toIntValue('a', 0));
    }

    @Test
    void testToIntValue_Character_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CharUtils.toIntValue((Character) null));
    }

    @Test
    void testToIntValue_Character_numeric_returnsIntValue() {
        assertEquals(5, CharUtils.toIntValue(Character.valueOf('5')));
    }

    @Test
    void testToIntValue_Character_nonNumeric_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(Character.valueOf('a')));
    }

    @Test
    void testToIntValue_Character_numeric_withDefaultValue_returnsIntValue() {
        assertEquals(5, CharUtils.toIntValue(Character.valueOf('5'), 0));
    }

    @Test
    void testToIntValue_Character_nonNumeric_withDefaultValue_returnsDefaultValue() {
        assertEquals(0, CharUtils.toIntValue(Character.valueOf('a'), 0));
    }

    @Test
    void testToString_char_returnsString() {
        assertEquals("a", CharUtils.toString('a'));
    }

    @Test
    void testToString_Character_null_returnsNull() {
        assertNull(CharUtils.toString((Character) null));
    }

    @Test
    void testToString_Character_notNull_returnsString() {
        assertEquals("a", CharUtils.toString(Character.valueOf('a')));
    }

    @Test
    void testUnicodeEscaped_char_returnsUnicodeString() {
        assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
    }

    @Test
    void testUnicodeEscaped_Character_null_returnsNull() {
        assertNull(CharUtils.unicodeEscaped((Character) null));
    }

    @Test
    void testUnicodeEscaped_Character_notNull_returnsUnicodeString() {
        assertEquals("\\u0061", CharUtils.unicodeEscaped(Character.valueOf('a')));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAscii_true() {
            assertTrue(CharUtils.isAscii('A'));
        }

    @Test
        public void testIsAscii_false() {
            assertFalse(CharUtils.isAscii((char) 128));
        }

    @Test
        public void testIsAsciiAlphaLower_edgeCaseTrue() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
        }

    @Test
        public void testIsAsciiAlphaLower_edgeCaseFalse() {
            assertFalse(CharUtils.isAsciiAlphaLower('z' + 1));
        }

    @Test
        public void testToChar_Character_null_withDefaultValue_returnsDefaultValue() {
            assertEquals('x', CharUtils.toChar(null, 'x'));
        }

    @Test
        public void testToCharacterObject_char_returnsCharacterObject() {
            assertEquals(Character.valueOf('A'), CharUtils.toCharacterObject('A'));
        }

    @Test
        public void testToCharacterObject_string_empty_returnsNull() {
            assertEquals(null, CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_Character_null_withDefaultValue_returnsDefaultValue() {
            assertEquals(5, CharUtils.toIntValue((Character) null, 5));
        }

    @Test
        public void testToString_char_outOfBounds_returnsStringValue() {
            assertEquals(String.valueOf((char) 128), CharUtils.toString((char) 128));
        }

    @Test
        public void testCharUtils_constructor_exists() {
            new CharUtils();
        }
}
