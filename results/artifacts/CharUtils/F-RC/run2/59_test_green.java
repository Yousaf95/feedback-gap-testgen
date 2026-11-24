package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.CharUtils.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharUtils_GenTest {

    @Test
    public void testCompare() {
        assertEquals(0, compare('a', 'a'));
        assertEquals(-1, compare('a', 'b'));
        assertEquals(1, compare('b', 'a'));
    }

    @ParameterizedTest
    @ValueSource(chars = { '\n', '\r', '\0' })
    public void testIsAscii(char ch) {
        assertTrue(isAscii(ch));
    }

    @ParameterizedTest
    @ValueSource(ints = { 128, 256, -1 }) // Use ints instead of chars for values outside the ASCII range
    public void testIsAsciiNonAscii(int value) {
        char ch = (char) value;
        assertFalse(isAscii(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = { 'a', 'b', 'c' })
    public void testIsAsciiAlphaLower(char ch) {
        assertTrue(isAsciiAlphaLower(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = { 'A', 'B', 'C' })
    public void testIsAsciiAlphaUpper(char ch) {
        assertFalse(isAsciiAlphaLower(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = { '0', '1', '2' })
    public void testIsAsciiNumeric(char ch) {
        assertTrue(isAsciiNumeric(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = { 'a', 'b', 'A', 'B' })
    public void testIsAsciiAlphanumeric(char ch) {
        assertTrue(isAsciiAlphanumeric(ch));
    }

    
    @ParameterizedTest
    @ValueSource(ints = { 128, 256, -1 }) // Use ints instead of chars for values outside the ASCII range
    public void testIsAsciiNonPrintable(int value) {
        char ch = (char) value;
        assertFalse(isAsciiPrintable(ch));
    }

    @Test
    public void testToChar_Character() {
        assertEquals('a', toChar(Character.valueOf('a')));
    }

    
    @Test
    public void testToChar_String() {
        assertEquals('a', toChar("abc"));
    }

    @Test
    public void testToChar_StringDefault() {
        assertEquals('b', toChar("", 'b'));
    }

    @Test
    public void testToIntValue_Character() {
        assertEquals(0, toIntValue(Character.valueOf('0')));
    }

    @Test
    public void testToIntValue_CharacterDefault() {
        assertEquals(1, toIntValue(Character.valueOf('a'), 1));
    }

    
    @Test
    public void testUnicodeEscaped_Character() {
        assertEquals("\\u0061", unicodeEscaped(Character.valueOf('a')));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiControl() {
            assertTrue(CharUtils.isAsciiControl((char) 0)); // NUL
            assertTrue(CharUtils.isAsciiControl((char) 31)); // DEL
            assertFalse(CharUtils.isAsciiControl('a'));
            assertFalse(CharUtils.isAsciiControl('A'));
            assertFalse(CharUtils.isAsciiControl('0'));
        }

    @Test
        public void testToChar_Character_2() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringDefault_2() {
            assertEquals('b', CharUtils.toChar("bc", 'b'));
        }

    @Test
        public void testToIntValue_CharacterDefault_2() {
            assertEquals(2, CharUtils.toIntValue(Character.valueOf('2'), 2));
        }

    @Test
        public void testUnicodeEscaped_Character_2() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped(Character.valueOf('A')));
        }

    
    @Test
        public void testIsAsciiAlphanumeric_2() {
            assertTrue(CharUtils.isAsciiAlphanumeric('a'));
            assertTrue(CharUtils.isAsciiAlphanumeric('A'));
            assertTrue(CharUtils.isAsciiAlphanumeric('0'));
            assertFalse(CharUtils.isAsciiAlphanumeric(' '));
            assertFalse(CharUtils.isAsciiAlphanumeric('\t'));
        }

    
    @Test
        public void testIsAsciiControl_2() {
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertTrue(CharUtils.isAsciiControl('\r'));
            assertTrue(CharUtils.isAsciiControl('\t'));
            assertFalse(CharUtils.isAsciiControl('a'));
            assertFalse(CharUtils.isAsciiControl('A'));
            assertFalse(CharUtils.isAsciiControl('0'));
        }

    @Test
        public void testIsAsciiNumeric_2() {
            assertTrue(CharUtils.isAsciiNumeric('0'));
            assertTrue(CharUtils.isAsciiNumeric('9'));
            assertFalse(CharUtils.isAsciiNumeric('a'));
            assertFalse(CharUtils.isAsciiNumeric('A'));
            assertFalse(CharUtils.isAsciiNumeric(' '));
        }

    @Test
        public void testIsAsciiPrintable() {
            assertTrue(CharUtils.isAsciiPrintable('a'));
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertTrue(CharUtils.isAsciiPrintable('0'));
            assertTrue(CharUtils.isAsciiPrintable(' '));
            assertFalse(CharUtils.isAsciiPrintable('\n'));
            assertFalse(CharUtils.isAsciiPrintable('\r'));
        }

    @Test
        public void testToChar_Character_3() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringDefault_3() {
            assertEquals('a', CharUtils.toChar("abc", 'a'));
        }

    @Test
        public void testToIntValue_CharacterDefault_2_2() {
            assertEquals(0, CharUtils.toIntValue(null, 0));
        }

    @Test
        public void testUnicodeEscaped_Character_2_2() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_UncoveredBranch() {
            assertFalse(isAsciiAlphaLower('A')); // ASCII uppercase letter
        }

    
    @Test
        public void testToChar_Character_NotCovered() {
            Character ch = 'a';
            assertEquals('a', toChar(ch));
        }

    @Test
        public void testToChar_StringDefault_NotCovered() {
            String str = "";
            char defaultValue = 'b';
            assertEquals('b', toChar(str, defaultValue));
        }

    @Test
        public void testToCharacterObject_Character_NotCovered() {
            Character ch = 'a';
            assertNotNull(toCharacterObject(ch));
        }

    @Test
        public void testToCharacterObject_String_NotCovered() {
            String str = "b";
            assertNotNull(toCharacterObject(str));
        }

    @Test
        public void testToIntValue_CharacterDefault_NotCovered() {
            Character ch = '5';
            int defaultValue = 10;
            assertEquals(5, toIntValue(ch, defaultValue));
        }

    @Test
        public void testUnicodeEscaped_Character_2_NotCovered() {
            Character ch = 'a';
            assertNotNull(unicodeEscaped(ch));
        }

    @Test
        public void testIsAsciiAlphanumeric_2_NotCovered() {
            assertTrue(isAsciiAlphanumeric('A')); // ASCII uppercase letter
        }

    
    @Test
        public void testIsAsciiNumeric_2_NotCovered() {
            assertTrue(isAsciiNumeric('9')); // ASCII digit
        }

    @Test
        public void testToChar_Character_2_NotCovered() {
            Character ch = 'b';
            assertEquals('b', toChar(ch));
        }

    @Test
        public void testToChar_StringDefault_2_NotCovered() {
            String str = "c";
            char defaultValue = 'd';
            assertEquals('c', toChar(str, defaultValue));
        }

    @Test
        public void testToIntValue_CharacterDefault_2_NotCovered() {
            Character ch = '6';
            int defaultValue = 15;
            assertEquals(6, toIntValue(ch, defaultValue));
        }

    @Test
        public void testUnicodeEscaped_Character_2_2_NotCovered() {
            Character ch = 'b';
            assertNotNull(unicodeEscaped(ch));
        }
}
