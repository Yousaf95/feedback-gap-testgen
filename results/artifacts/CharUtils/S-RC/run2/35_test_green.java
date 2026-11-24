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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_2() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
            assertFalse(CharUtils.isAsciiAlphaLower('1'));
            assertFalse(CharUtils.isAsciiAlphaLower(' '));
        }

    @Test
        public void testIsAsciiControl_2() {
            assertTrue(CharUtils.isAsciiControl('\0'));
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertTrue(CharUtils.isAsciiControl('\r'));
            assertFalse(CharUtils.isAsciiControl('a'));
        }

    @Test
        public void testIsAsciiPrintable_2() {
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertTrue(CharUtils.isAsciiPrintable('z'));
            assertTrue(CharUtils.isAsciiPrintable('0'));
            assertTrue(CharUtils.isAsciiPrintable('9'));
            assertFalse(CharUtils.isAsciiPrintable('\0'));
            assertFalse(CharUtils.isAsciiPrintable('\n'));
        }

    @Test
        public void testToChar_CharacterNonNull_2() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringDefault_2() {
            assertEquals('a', CharUtils.toChar("abc", 'x'));
        }

    @Test
        public void testToCharacterObject() {
            assertNotNull(CharUtils.toCharacterObject("a"));
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_CharacterNonNull() {
            assertEquals(0, CharUtils.toIntValue(Character.valueOf('0')));
        }

    @Test
        public void testToIntValue_CharacterDefault() {
            assertEquals(42, CharUtils.toIntValue(null, 42));
        }

    @Test
        public void testToString_Character() {
            assertNotNull(CharUtils.toString(Character.valueOf('a')));
            assertNull(CharUtils.toString(null));
        }

    @Test
        public void testUnicodeEscaped_Character() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped(Character.valueOf('a')));
            assertNull(CharUtils.unicodeEscaped(null));
        }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("asciiAlphaLowerProvider")
        public void testIsAsciiAlphaLower_2_2(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiAlphaLower(ch));
        }

    static Stream<Arguments> asciiAlphaLowerProvider() {
            return Stream.of(
                arguments('A', false),
                arguments('a', true),
                arguments('9', false),
                arguments('\0', false)
            );
        }

    
    static Stream<Arguments> asciiControlProvider() {
            return Stream.of(
                arguments(31, true),
                arguments(32, false),
                arguments(126, true),
                arguments(127, false)
            );
        }

    @ParameterizedTest
        @MethodSource("asciiPrintableProvider")
        public void testIsAsciiPrintable_2_2(int code, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiPrintable((char) code));
        }

    static Stream<Arguments> asciiPrintableProvider() {
            return Stream.of(
                arguments(31, false),
                arguments(32, true),
                arguments(126, true),
                arguments(127, false)
            );
        }

    @Test
        public void testToChar_CharacterNonNull_2_2() {
            assertEquals('A', CharUtils.toChar(Character.valueOf('A')));
        }

    @Test
        public void testToChar_StringDefault_2_2() {
            assertEquals('A', CharUtils.toChar("AB", 'A'));
            assertEquals('B', CharUtils.toChar("", 'B'));
        }

    @Test
        public void testToCharacterObject_2() {
            assertNotNull(CharUtils.toCharacterObject('A'));
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_CharacterNonNull_2() {
            assertEquals(0, CharUtils.toIntValue(Character.valueOf('0')));
        }

    @Test
        public void testToIntValue_CharacterDefault_2() {
            assertEquals(5, CharUtils.toIntValue(null, 5));
        }

    @Test
        public void testToString_Character_2() {
            assertEquals("A", CharUtils.toString(Character.valueOf('A')));
            assertNull(CharUtils.toString(null));
        }

    @Test
        public void testUnicodeEscaped_Character_2() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped(Character.valueOf('A')));
            assertNull(CharUtils.unicodeEscaped(null));
        }
}
