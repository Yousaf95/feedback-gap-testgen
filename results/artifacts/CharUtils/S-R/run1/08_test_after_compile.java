package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.CharUtils.*;

public class CharUtils_GenTest {

    @Test
    public void testCompare() {
        assertEquals(0, compare('a', 'a'));
        assertTrue(compare('a', 'b') < 0);
        assertTrue(compare('b', 'a') > 0);
    }

    @Test
    public void testIsAscii() {
        assertTrue(isAscii('A'));
        assertFalse(isAscii('\u0100'));
    }

    @Test
    public void testIsAsciiAlpha() {
        assertTrue(isAsciiAlpha('A'));
        assertTrue(isAsciiAlpha('z'));
        assertFalse(isAsciiAlpha('1'));
        assertFalse(isAsciiAlpha(' '));
    }

    @Test
    public void testIsAsciiAlphaLower() {
        assertTrue(isAsciiAlphaLower('a'));
        assertFalse(isAsciiAlphaLower('Z'));
        assertFalse(isAsciiAlphaLower('1'));
        assertFalse(isAsciiAlphaLower(' '));
    }

    @Test
    public void testIsAsciiAlphanumeric() {
        assertTrue(isAsciiAlphanumeric('A'));
        assertTrue(isAsciiAlphanumeric('z'));
        assertTrue(isAsciiAlphanumeric('0'));
        assertFalse(isAsciiAlphanumeric(' '));
    }

    @Test
    public void testIsAsciiAlphaUpper() {
        assertTrue(isAsciiAlphaUpper('A'));
        assertFalse(isAsciiAlphaUpper('a'));
        assertFalse(isAsciiAlphaUpper('1'));
        assertFalse(isAsciiAlphaUpper(' '));
    }

    @Test
    public void testIsAsciiControl() {
        assertTrue(isAsciiControl('\0'));
        assertTrue(isAsciiControl('\n'));
        assertFalse(isAsciiControl('A'));
    }

    @Test
    public void testIsAsciiNumeric() {
        assertTrue(isAsciiNumeric('0'));
        assertFalse(isAsciiNumeric('a'));
    }

    @Test
    public void testIsAsciiPrintable() {
        assertTrue(isAsciiPrintable('A'));
        assertFalse(isAsciiPrintable('\0'));
    }

    @Test
    public void testToChar_Character() {
        assertEquals('A', toChar(Character.valueOf('A')));
    }

    @Test
    public void testToChar_CharacterDefaultValue() {
        assertEquals('B', toChar(Character.valueOf('A'), 'B'));
    }

    @Test
    public void testToChar_String() {
        assertEquals('A', toChar("AB"));
    }

    @Test
    public void testToChar_StringDefaultValue() {
        assertEquals('B', toChar("", 'B'));
    }

    @Test
    public void testToCharacterObject_Character() {
        assertNotNull(toCharacterObject(Character.valueOf('A')));
    }

    @Test
    public void testToCharacterObject_String() {
        assertNotNull(toCharacterObject("AB"));
    }

    @Test
    public void testToIntValue_Character() {
        assertEquals(0, toIntValue(Character.valueOf('0')));
    }

    @Test
    public void testToIntValue_CharacterDefaultValue() {
        assertEquals(1, toIntValue(Character.valueOf('A'), 1));
    }

    @Test
    public void testToIntValue_String() {
        assertEquals(0, toIntValue("0"));
    }

    @Test
    public void testToIntValue_StringDefaultValue() {
        assertEquals(1, toIntValue("", 1));
    }

    @Test
    public void testToString_Character() {
        assertNotNull(toString(Character.valueOf('A')));
    }

    @Test
    public void testUnicodeEscaped_Character() {
        assertEquals("\\u0041", unicodeEscaped(Character.valueOf('A')));
    }
}
