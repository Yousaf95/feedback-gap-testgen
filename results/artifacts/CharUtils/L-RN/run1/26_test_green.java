package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        void testIsAscii_true() {
            assertTrue(CharUtils.isAscii('a'));
        }

    @Test
        void testIsAscii_false() {
            assertFalse(CharUtils.isAscii('\u0100'));
        }

    
    @Test
        void testToCharacterObject_String_empty_returnsNull() {
            Character result = CharUtils.toCharacterObject("");
            assertEquals(null, result);
        }

    @Test
        void testToIntValue_char_nonNumeric_withDefaultValue_outOfRange() {
            int result = CharUtils.toIntValue('a', -1);
            assertEquals(-1, result);
        }

    
    @Test
        void testUnicodeEscaped_char_minValue() {
            String result = CharUtils.unicodeEscaped(Character.MIN_VALUE);
            assertEquals("\\u0000", result);
        }

    @Test
        void testToString_char_maxValue() {
            String result = CharUtils.toString(Character.MAX_VALUE);
            assertEquals("\uFFFF", result);
        }

    @Test
        void testToString_char_minValue() {
            String result = CharUtils.toString(Character.MIN_VALUE);
            assertEquals("\u0000", result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testIsAsciiAlpha_true() {
            assertTrue(CharUtils.isAsciiAlpha('a'));
            assertTrue(CharUtils.isAsciiAlpha('Z'));
        }

    @Test
        void testIsAsciiControl_minValue() {
            assertTrue(CharUtils.isAsciiControl('\0'));
        }

    @Test
        void testIsAsciiControl_maxValue() {
            assertTrue(CharUtils.isAsciiControl((char) 127));
        }

    @Test
        void testIsAsciiPrintable_minValue() {
            assertFalse(CharUtils.isAsciiPrintable((char) 31));
        }

    @Test
        void testIsAsciiPrintable_maxValue() {
            assertTrue(CharUtils.isAsciiPrintable((char) 126));
        }

    @Test
        void testToIntValue_char_nonNumeric_withDefaultValue_outOfRange_2() {
            assertEquals(100, CharUtils.toIntValue('a', 100));
        }

    @Test
        void testToString_char_maxValue_2() {
            assertEquals("\u007f", CharUtils.toString((char) 127));
        }

    @Test
        void testUnicodeEscaped_char_minValue_2() {
            assertEquals("\\u0000", CharUtils.unicodeEscaped('\0'));
        }
}
