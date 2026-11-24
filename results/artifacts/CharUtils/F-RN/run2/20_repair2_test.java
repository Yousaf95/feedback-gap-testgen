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
            assertTrue(isAsciiControl('\0'));
            assertTrue(isAsciiControl('\t'));
            assertTrue(isAsciiControl('\n'));
            assertTrue(isAsciiControl('\r'));
            assertTrue(isAsciiControl('\f'));
            assertTrue(isAsciiControl('\b'));
            assertFalse(isAsciiControl('a'));
        }

    @Test
        public void testIsAsciiNonPrintable_2() {
            assertTrue(isAsciiNonPrintable('\0'));
            assertTrue(isAsciiNonPrintable('\t'));
            assertTrue(isAsciiNonPrintable('\n'));
            assertTrue(isAsciiNonPrintable('\r'));
            assertTrue(isAsciiNonPrintable('\f'));
            assertTrue(isAsciiNonPrintable('\b'));
            assertFalse(isAsciiNonPrintable('a'));
        }

    @Test
        public void testIsAsciiAlpha() {
            assertTrue(isAsciiAlpha('a'));
            assertTrue(isAsciiAlpha('z'));
            assertTrue(isAsciiAlpha('A'));
            assertTrue(isAsciiAlpha('Z'));
            assertFalse(isAsciiAlpha('0'));
            assertFalse(isAsciiAlpha('9'));
        }

    @Test
        public void testIsAsciiNumeric_2() {
            assertTrue(isAsciiNumeric('0'));
            assertTrue(isAsciiNumeric('9'));
            assertFalse(isAsciiNumeric('a'));
            assertFalse(isAsciiNumeric('z'));
            assertFalse(isAsciiNumeric('A'));
            assertFalse(isAsciiNumeric('Z'));
        }

    @Test
        public void testIsAsciiAlphanumeric_2() {
            assertTrue(isAsciiAlphanumeric('0'));
            assertTrue(isAsciiAlphanumeric('9'));
            assertTrue(isAsciiAlphanumeric('a'));
            assertTrue(isAsciiAlphanumeric('z'));
            assertTrue(isAsciiAlphanumeric('A'));
            assertTrue(isAsciiAlphanumeric('Z'));
            assertFalse(isAsciiAlphanumeric(' '));
        }

    @Test
        public void testToIntValue_InvalidCharacter() {
            assertThrows(IllegalArgumentException.class, () -> toIntValue('a'));
        }

    @Test
        public void testToString_Character() {
            assertEquals("a", toString('a'));
            assertEquals("\u0020", toString(' '));
        }

    @Test
        public void testToString_CharacterObject() {
            assertEquals("a", toString(Character.valueOf('a')));
            assertEquals("\u0020", toString(Character.valueOf(' ')));
        }
}
