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
            arguments('9', false)
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
            arguments('9', false)
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
            arguments('!', false)
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
            arguments('9', false)
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
            arguments('A', false)
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

    @ParameterizedTest
    @MethodSource("provideToIntValue_CharData")
    public void testToIntValue_Char(char ch, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch));
    }

    private static Stream<Arguments> provideToIntValue_CharData() {
        return Stream.of(
            arguments('0', 0),
            arguments('9', 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntValue_CharDefaultData")
    public void testToIntValue_CharDefault(char ch, int defaultValue, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch, defaultValue));
    }

    private static Stream<Arguments> provideToIntValue_CharDefaultData() {
        return Stream.of(
            arguments('a', 0, 0),
            arguments('9', 1, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntValue_CharacterData")
    public void testToIntValue_Character(Character ch, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch));
    }

    private static Stream<Arguments> provideToIntValue_CharacterData() {
        return Stream.of(
            arguments(Character.valueOf('0'), 0),
            arguments(Character.valueOf('9'), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntValue_CharacterDefaultData")
    public void testToIntValue_CharacterDefault(Character ch, int defaultValue, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch, defaultValue));
    }

    private static Stream<Arguments> provideToIntValue_CharacterDefaultData() {
        return Stream.of(
            arguments(null, 0, 0),
            arguments(Character.valueOf('9'), 1, 9)
        );
    }

    
    private static Stream<Arguments> provideToString_CharData() {
        return Stream.of(
            arguments('a', "a"),
            arguments('\n', "\n"),
            arguments(128, "[C@")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToString_CharacterData")
    public void testToString_Character(Character ch, String expected) {
        assertEquals(expected, CharUtils.toString(ch));
    }

    private static Stream<Arguments> provideToString_CharacterData() {
        return Stream.of(
            arguments(null, null),
            arguments(Character.valueOf('a'), "a")
        );
    }

    
    private static Stream<Arguments> provideUnicodeEscaped_CharData() {
        return Stream.of(
            arguments('a', "\\u0061"),
            arguments('\n', "\\u000A"),
            arguments(128, "[C@")
        );
    }

    // ---- added by coverage-guided refinement ----
    private static Stream<Arguments> provideAsciiChars() {
                return Stream.of(
                    arguments('\u007F', true),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiAlphaChars() {
                return Stream.of(
                    arguments('A', true),
                    arguments('z', true),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiAlphaLowerChars() {
                return Stream.of(
                    arguments('a', true),
                    arguments('z', true),
                    arguments('A', false),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiAlphanumericChars() {
                return Stream.of(
                    arguments('A', true),
                    arguments('z', true),
                    arguments('0', true),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiAlphaUpperChars() {
                return Stream.of(
                    arguments('A', true),
                    arguments('Z', true),
                    arguments('a', false),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiControlChars() {
                return Stream.of(
                    arguments('\r', true),
                    arguments('\n', true),
                    arguments('A', false),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiNumericChars() {
                return Stream.of(
                    arguments('0', true),
                    arguments('9', true),
                    arguments('A', false),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    private static Stream<Arguments> provideAsciiPrintableChars() {
                return Stream.of(
                    arguments(' ', true),
                    arguments('~', true),
                    arguments('\r', false),
                    arguments('\n', false),
                    arguments('\u007F', false),
                    arguments('\u0080', false)
                );
            }

    @Test
            public void testToChar_CharacterNull() {
                assertThrows(NullPointerException.class, () -> CharUtils.toChar((Character) null));
            }

    @ParameterizedTest
            @MethodSource("provideNonNumericChars")
            public void testToIntValue_CharNonNumeric(char ch) {
                assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(ch));
            }

    private static Stream<Arguments> provideNonNumericChars() {
                return Stream.of(
                    arguments('A'),
                    arguments('z'),
                    arguments('\u007F'),
                    arguments('\u0080')
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
            assertFalse(CharUtils.isAscii('\u0080')); // First non-ASCII character
        }

    @Test
        public void testIsAsciiAlphaExtended() {
            assertTrue(CharUtils.isAsciiAlpha('A'));
            assertTrue(CharUtils.isAsciiAlpha('z'));
            assertFalse(CharUtils.isAsciiAlpha('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAsciiAlpha('\u0080')); // First non-ASCII character
        }

    @Test
        public void testIsAsciiAlphanumericExtended() {
            assertTrue(CharUtils.isAsciiAlphanumeric('A'));
            assertTrue(CharUtils.isAsciiAlphanumeric('z'));
            assertTrue(CharUtils.isAsciiAlphanumeric('0'));
            assertFalse(CharUtils.isAsciiAlphanumeric('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAsciiAlphanumeric('\u0080')); // First non-ASCII character
        }

    @Test
        public void testIsAsciiControlExtended() {
            assertTrue(CharUtils.isAsciiControl('\t')); // Tab character
            assertTrue(CharUtils.isAsciiControl('\n')); // Linefeed character
            assertFalse(CharUtils.isAsciiControl('A'));
            assertFalse(CharUtils.isAsciiControl('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAsciiControl('\u0080')); // First non-ASCII character
        }

    @Test
        public void testIsAsciiNumericExtended() {
            assertTrue(CharUtils.isAsciiNumeric('0'));
            assertTrue(CharUtils.isAsciiNumeric('9'));
            assertFalse(CharUtils.isAsciiNumeric('A'));
            assertFalse(CharUtils.isAsciiNumeric('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAsciiNumeric('\u0080')); // First non-ASCII character
        }

    @Test
        public void testIsAsciiPrintableExtended() {
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertTrue(CharUtils.isAsciiPrintable('z'));
            assertTrue(CharUtils.isAsciiPrintable('0'));
            assertFalse(CharUtils.isAsciiPrintable('\t')); // Tab character
            assertFalse(CharUtils.isAsciiPrintable('\n')); // Linefeed character
            assertFalse(CharUtils.isAsciiPrintable('\u007F')); // Last ASCII character
            assertFalse(CharUtils.isAsciiPrintable('\u0080')); // First non-ASCII character
        }

    @Test
        public void testToChar_CharacterExtended() {
            assertEquals('A', CharUtils.toChar(Character.valueOf('A')));
            assertThrows(NullPointerException.class, () -> CharUtils.toChar(null));
        }

    @Test
        public void testToChar_StringExtended() {
            assertEquals('A', CharUtils.toChar("A"));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(""));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar("AB"));
        }

    @Test
        public void testToIntValue_CharExtended() {
            assertEquals(0, CharUtils.toIntValue('0'));
            assertEquals(9, CharUtils.toIntValue('9'));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('A'));
        }

    @Test
        public void testToString_CharacterExtended() {
            assertEquals("A", CharUtils.toString('A'));
            assertNull(CharUtils.toString(null));
        }
}
