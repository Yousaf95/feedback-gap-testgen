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
    static Stream<Arguments> asciiAlphaLowerProvider() {
            return Stream.of(
                arguments('A', false),
                arguments('z', true),
                arguments('0', false)
            );
        }

    static Stream<Arguments> asciiControlProvider() {
            return Stream.of(
                arguments('\t', true),
                arguments(' ', false),
                arguments((char) 127, true)
            );
        }

    static Stream<Arguments> asciiPrintableProvider() {
            return Stream.of(
                arguments('A', true),
                arguments('\t', false),
                arguments((char) 127, false)
            );
        }

    @Test
        public void testToChar_CharacterNonNull_2() {
            assertEquals('A', CharUtils.toChar(Character.valueOf('A')));
        }

    @Test
        public void testToCharacterObject_Characters() {
            assertEquals(Character.valueOf('A'), CharUtils.toCharacterObject('A'));
        }

    @Test
        public void testToIntValue_CharDefault_2() {
            assertEquals(5, CharUtils.toIntValue('5', 0));
        }

    @Test
        public void testToString_Character_2() {
            assertNull(CharUtils.toString(null));
        }

    @Test
        public void testUnicodeEscaped_Character() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped(Character.valueOf('A')));
        }

    @Test
        public void testUnicodeEscaped_Characters() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_NotCoveredBranch() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
        }

    
    @Test
        public void testIsAsciiPrintable_NotCoveredBranch() {
            assertFalse(CharUtils.isAsciiPrintable('\0'));
        }

    @Test
        public void testToChar_CharacterNonNull_NotCovered() {
            assertEquals('A', CharUtils.toChar(Character.valueOf('A')));
        }

    @Test
        public void testToCharacterObject_Characters_NotCovered() {
            assertNotNull(CharUtils.toCharacterObject("A"));
        }

    @Test
        public void testToIntValue_CharDefault_2_NotCoveredBranch() {
            assertEquals(0, CharUtils.toIntValue('A', 0));
        }

    @Test
        public void testToString_Character_2_NotCovered() {
            assertEquals("A", CharUtils.toString(Character.valueOf('A')));
        }

    @Test
        public void testUnicodeEscaped_Character_NotCoveredBranch() {
            assertNull(CharUtils.unicodeEscaped(null));
        }

    @Test
        public void testCharUtils_Constructor_NotCovered() {
            new CharUtils();
        }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("provideAsciiAlphaLowerChars")
        public void testIsAsciiAlphaLower_NotCoveredBranch_2(char ch) {
            assertTrue(CharUtils.isAsciiAlphaLower(ch));
        }

    private static Stream<Arguments> provideAsciiAlphaLowerChars() {
            return Stream.of(
                arguments('a'), arguments('b'), arguments('c'), arguments('d'),
                arguments('e'), arguments('f'), arguments('g'), arguments('h'),
                arguments('i'), arguments('j'), arguments('k'), arguments('l'),
                arguments('m'), arguments('n'), arguments('o'), arguments('p'),
                arguments('q'), arguments('r'), arguments('s'), arguments('t'),
                arguments('u'), arguments('v'), arguments('w'), arguments('x'),
                arguments('y'), arguments('z')
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiPrintableChars")
        public void testIsAsciiPrintable_NotCoveredBranch_2(char ch) {
            assertTrue(CharUtils.isAsciiPrintable(ch));
        }

    private static Stream<Arguments> provideAsciiPrintableChars() {
            return Stream.of(
                arguments(' '), arguments('!'), arguments('"'), arguments('#'),
                arguments('$'), arguments('%'), arguments('&'), arguments("'"),
                arguments('('), arguments(')'), arguments('*'), arguments('+'),
                arguments(','), arguments('-'), arguments('.'), arguments('/'),
                arguments('0'), arguments('1'), arguments('2'), arguments('3'),
                arguments('4'), arguments('5'), arguments('6'), arguments('7'),
                arguments('8'), arguments('9'), arguments(':'), arguments(';'),
                arguments('<'), arguments('='), arguments('>'), arguments('?'),
                arguments('@'), arguments('A'), arguments('B'), arguments('C'),
                arguments('D'), arguments('E'), arguments('F'), arguments('G'),
                arguments('H'), arguments('I'), arguments('J'), arguments('K'),
                arguments('L'), arguments('M'), arguments('N'), arguments('O'),
                arguments('P'), arguments('Q'), arguments('R'), arguments('S'),
                arguments('T'), arguments('U'), arguments('V'), arguments('W'),
                arguments('X'), arguments('Y'), arguments('Z'), arguments('['),
                arguments('\\'), arguments(']'), arguments('^'), arguments('_'),
                arguments('`'), arguments('a'), arguments('b'), arguments('c'),
                arguments('d'), arguments('e'), arguments('f'), arguments('g'),
                arguments('h'), arguments('i'), arguments('j'), arguments('k'),
                arguments('l'), arguments('m'), arguments('n'), arguments('o'),
                arguments('p'), arguments('q'), arguments('r'), arguments('s'),
                arguments('t'), arguments('u'), arguments('v'), arguments('w'),
                arguments('x'), arguments('y'), arguments('z'), arguments('{'),
                arguments('|'), arguments('}'), arguments('~')
            );
        }

    @Test
        public void testToChar_CharacterNonNull_NotCovered_2() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @ParameterizedTest
        @MethodSource("provideEmptyStrings")
        public void testToCharacterObject_Characters_NotCovered_2(String str) {
            assertNull(CharUtils.toCharacterObject(str));
        }

    private static Stream<Arguments> provideEmptyStrings() {
            return Stream.of(
                arguments(""), arguments(" ")
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiNumericChars")
        public void testToIntValue_CharDefault_2_NotCoveredBranch_2(char ch) {
            assertEquals(ch - 48, CharUtils.toIntValue(ch, 0));
        }

    private static Stream<Arguments> provideAsciiNumericChars() {
            return Stream.of(
                arguments('0'), arguments('1'), arguments('2'), arguments('3'),
                arguments('4'), arguments('5'), arguments('6'), arguments('7'),
                arguments('8'), arguments('9')
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiChars")
        public void testToString_Character_2_NotCovered_2(char ch) {
            assertEquals(String.valueOf(ch), CharUtils.toString(ch));
        }

    private static Stream<Arguments> provideAsciiChars() {
            return Stream.of(
                arguments(' '), arguments('!'), arguments('"'), arguments('#'),
                arguments('$'), arguments('%'), arguments('&'), arguments("'"),
                arguments('('), arguments(')'), arguments('*'), arguments('+'),
                arguments(','), arguments('-'), arguments('.'), arguments('/'),
                arguments('0'), arguments('1'), arguments('2'), arguments('3'),
                arguments('4'), arguments('5'), arguments('6'), arguments('7'),
                arguments('8'), arguments('9'), arguments(':'), arguments(';'),
                arguments('<'), arguments('='), arguments('>'), arguments('?'),
                arguments('@'), arguments('A'), arguments('B'), arguments('C'),
                arguments('D'), arguments('E'), arguments('F'), arguments('G'),
                arguments('H'), arguments('I'), arguments('J'), arguments('K'),
                arguments('L'), arguments('M'), arguments('N'), arguments('O'),
                arguments('P'), arguments('Q'), arguments('R'), arguments('S'),
                arguments('T'), arguments('U'), arguments('V'), arguments('W'),
                arguments('X'), arguments('Y'), arguments('Z'), arguments('['),
                arguments('\\'), arguments(']'), arguments('^'), arguments('_'),
                arguments('`'), arguments('a'), arguments('b'), arguments('c'),
                arguments('d'), arguments('e'), arguments('f'), arguments('g'),
                arguments('h'), arguments('i'), arguments('j'), arguments('k'),
                arguments('l'), arguments('m'), arguments('n'), arguments('o'),
                arguments('p'), arguments('q'), arguments('r'), arguments('s'),
                arguments('t'), arguments('u'), arguments('v'), arguments('w'),
                arguments('x'), arguments('y'), arguments('z'), arguments('{'),
                arguments('|'), arguments('}'), arguments('~')
            );
        }

    @ParameterizedTest
        @MethodSource("provideAsciiChars")
        public void testUnicodeEscaped_Character_NotCoveredBranch_2(char ch) {
            assertEquals("\\u" + String.format("%04x", (int) ch), CharUtils.unicodeEscaped(ch));
        }
}
