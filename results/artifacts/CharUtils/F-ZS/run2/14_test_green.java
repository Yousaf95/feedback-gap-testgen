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
}
