package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CharUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideCompareData")
    public void testCompare(char x, char y, int expected) {
        assertEquals(expected, CharUtils.compare(x, y));
    }

    private static Stream<Arguments> provideCompareData() {
        return Stream.of(
            arguments('a', 'b', -1),
            arguments('b', 'a', 1),
            arguments('a', 'a', 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiData")
    public void testIsAscii(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAscii(ch));
    }

    private static Stream<Arguments> provideIsAsciiData() {
        return Stream.of(
            arguments((char) 0, true),
            arguments('a', true),
            arguments('\u007F', true),
            arguments('\u0080', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphaData")
    public void testIsAsciiAlpha(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlpha(ch));
    }

    private static Stream<Arguments> provideIsAsciiAlphaData() {
        return Stream.of(
            arguments('a', true),
            arguments('A', true),
            arguments('z', true),
            arguments('Z', true),
            arguments('0', false),
            arguments(' ', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphaLowerData")
    public void testIsAsciiAlphaLower(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphaLower(ch));
    }

    private static Stream<Arguments> provideIsAsciiAlphaLowerData() {
        return Stream.of(
            arguments('a', true),
            arguments('z', true),
            arguments('A', false),
            arguments('Z', false),
            arguments('0', false),
            arguments(' ', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphanumericData")
    public void testIsAsciiAlphanumeric(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphanumeric(ch));
    }

    private static Stream<Arguments> provideIsAsciiAlphanumericData() {
        return Stream.of(
            arguments('a', true),
            arguments('A', true),
            arguments('z', true),
            arguments('Z', true),
            arguments('0', true),
            arguments('9', true),
            arguments(' ', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphaUpperData")
    public void testIsAsciiAlphaUpper(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphaUpper(ch));
    }

    private static Stream<Arguments> provideIsAsciiAlphaUpperData() {
        return Stream.of(
            arguments('A', true),
            arguments('Z', true),
            arguments('a', false),
            arguments('z', false),
            arguments('0', false),
            arguments(' ', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiControlData")
    public void testIsAsciiControl(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiControl(ch));
    }

    private static Stream<Arguments> provideIsAsciiControlData() {
        return Stream.of(
            arguments('\0', true),
            arguments('\n', true),
            arguments('\r', true),
            arguments(' ', false),
            arguments('a', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiNumericData")
    public void testIsAsciiNumeric(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiNumeric(ch));
    }

    private static Stream<Arguments> provideIsAsciiNumericData() {
        return Stream.of(
            arguments('0', true),
            arguments('9', true),
            arguments('a', false),
            arguments('A', false),
            arguments(' ', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiPrintableData")
    public void testIsAsciiPrintable(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiPrintable(ch));
    }

    private static Stream<Arguments> provideIsAsciiPrintableData() {
        return Stream.of(
            arguments(' ', true),
            arguments('\n', false),
            arguments('\r', false),
            arguments('\0', false)
        );
    }

    @Test
    public void testToChar_CharacterNonNull() {
        assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
    }

    
    @ParameterizedTest
    @MethodSource("provideToChar_StringData")
    public void testToChar_String(String str, char expected) {
        assertEquals(expected, CharUtils.toChar(str));
    }

    private static Stream<Arguments> provideToChar_StringData() {
        return Stream.of(
            arguments("a", 'a'),
            arguments("A", 'A'),
            arguments("1", '1')
        );
    }

    @ParameterizedTest
    @MethodSource("provideToChar_StringDefaultData")
    public void testToChar_StringDefault(String str, char defaultValue, char expected) {
        assertEquals(expected, CharUtils.toChar(str, defaultValue));
    }

    private static Stream<Arguments> provideToChar_StringDefaultData() {
        return Stream.of(
            arguments("", 'a', 'a'),
            arguments("A", 'b', 'A'),
            arguments(null, 'c', 'c')
        );
    }

    
    private static Stream<Arguments> provideNullAndEmptyString() {
            return Stream.of(
                arguments(null),
                arguments("")
            );
        }

    @Test
        public void testToString_CharacterNull() {
            assertNull(CharUtils.toString((Character) null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiExtended() {
            assertTrue(CharUtils.isAscii('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAscii('\u0080')); // First extended ASCII character
        }

    
    
    
    
    
    
    @Test
        public void testToChar_StringEmpty() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(""));
        }

    @Test
        public void testToIntValue_NonNumericCharacter() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToString_CharacterNull_2() {
            assertNull(CharUtils.toString(null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiExtended_2() {
            assertTrue(CharUtils.isAsciiExtended('\u007F'));
            assertFalse(CharUtils.isAsciiExtended('\u0080'));
        }

    @Test
        public void testToChar_CharacterNull() {
            assertThrows(NullPointerException.class, () -> CharUtils.toChar(null));
        }
}
