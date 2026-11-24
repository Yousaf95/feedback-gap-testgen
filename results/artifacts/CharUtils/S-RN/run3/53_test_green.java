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
    @ParameterizedTest
        @MethodSource("provideAsciiValues")
        public void testIsAsciiExtended(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAscii(ch));
        }

    private static Stream<Arguments> provideAsciiValues() {
            return Stream.of(
                arguments('\u007F', true),
                arguments('\u0080', false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiAlphaValues")
        public void testIsAsciiAlphaExtended(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiAlpha(ch));
        }

    private static Stream<Arguments> provideAsciiAlphaValues() {
            return Stream.of(
                arguments('A', true),
                arguments('z', true),
                arguments('\u007F', false),
                arguments('\u0080', false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiAlphanumericValues")
        public void testIsAsciiAlphanumericExtended(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiAlphanumeric(ch));
        }

    private static Stream<Arguments> provideAsciiAlphanumericValues() {
            return Stream.of(
                arguments('A', true),
                arguments('z', true),
                arguments('0', true),
                arguments('\u007F', false),
                arguments('\u0080', false)
            );
        }

    
    private static Stream<Arguments> provideAsciiControlValues() {
            return Stream.of(
                arguments('\t', true),
                arguments('\n', true),
                arguments('A', false),
                arguments('\u007F', false),
                arguments('\u0080', false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiNumericValues")
        public void testIsAsciiNumericExtended(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiNumeric(ch));
        }

    private static Stream<Arguments> provideAsciiNumericValues() {
            return Stream.of(
                arguments('0', true),
                arguments('9', true),
                arguments('A', false),
                arguments('\u007F', false),
                arguments('\u0080', false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiPrintableValues")
        public void testIsAsciiPrintableExtended(char ch, boolean expected) {
            assertEquals(expected, CharUtils.isAsciiPrintable(ch));
        }

    private static Stream<Arguments> provideAsciiPrintableValues() {
            return Stream.of(
                arguments('A', true),
                arguments('z', true),
                arguments('0', true),
                arguments('\t', false),
                arguments('\n', false),
                arguments('\u007F', false),
                arguments('\u0080', false)
            );
        }

    
    
    private static Stream<Arguments> provideToCharStringValues() {
            return Stream.of(
                arguments("A", 'A'),
                arguments("", ' '), // Default value for empty string
                arguments("AB", 'A') // Only first character is used
            );
        }

    
    private static Stream<Arguments> provideToIntValueCharValues() {
            return Stream.of(
                arguments('0', 0),
                arguments('9', 9),
                arguments('A', -1), // Invalid character
                arguments('\u007F', -1) // Invalid character
            );
        }

    @Test
        public void testToString_CharacterExtended() {
            assertEquals("A", CharUtils.toString('A'));
            assertNull(CharUtils.toString(null));
        }
}
