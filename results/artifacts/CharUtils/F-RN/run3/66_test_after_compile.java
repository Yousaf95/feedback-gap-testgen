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
    void testCompare(char x, char y, int expected) {
        assertEquals(expected, CharUtils.compare(x, y));
    }

    static Stream<Arguments> provideCompareData() {
        return Stream.of(
            arguments('a', 'b', -1),
            arguments('b', 'a', 1),
            arguments('a', 'a', 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiData")
    void testIsAscii(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAscii(ch));
    }

    static Stream<Arguments> provideIsAsciiData() {
        return Stream.of(
            arguments((char) 127, true),
            arguments((char) 128, false),
            arguments('\0', true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphaData")
    void testIsAsciiAlpha(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlpha(ch));
    }

    static Stream<Arguments> provideIsAsciiAlphaData() {
        return Stream.of(
            arguments('a', true),
            arguments('A', true),
            arguments('z', true),
            arguments('Z', true),
            arguments('0', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphaLowerData")
    void testIsAsciiAlphaLower(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphaLower(ch));
    }

    static Stream<Arguments> provideIsAsciiAlphaLowerData() {
        return Stream.of(
            arguments('a', true),
            arguments('A', false),
            arguments('z', true),
            arguments('Z', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiAlphanumericData")
    void testIsAsciiAlphanumeric(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphanumeric(ch));
    }

    static Stream<Arguments> provideIsAsciiAlphanumericData() {
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
    void testIsAsciiAlphaUpper(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiAlphaUpper(ch));
    }

    static Stream<Arguments> provideIsAsciiAlphaUpperData() {
        return Stream.of(
            arguments('a', false),
            arguments('A', true),
            arguments('z', false),
            arguments('Z', true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiControlData")
    void testIsAsciiControl(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiControl(ch));
    }

    static Stream<Arguments> provideIsAsciiControlData() {
        return Stream.of(
            arguments('\0', true),
            arguments(' ', false),
            arguments('\n', true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsAsciiNumericData")
    void testIsAsciiNumeric(char ch, boolean expected) {
        assertEquals(expected, CharUtils.isAsciiNumeric(ch));
    }

    static Stream<Arguments> provideIsAsciiNumericData() {
        return Stream.of(
            arguments('0', true),
            arguments('9', true),
            arguments('a', false)
        );
    }

    
    static Stream<Arguments> provideIsAsciiPrintableData() {
        return Stream.of(
            arguments('\0', false),
            arguments(' ', true),
            arguments('\n', true)
        );
    }

    @Test
    void testToChar_Character() {
        assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
    }

    
    @Test
    void testToChar_String() {
        assertEquals('a', CharUtils.toChar("abc"));
    }

    @Test
    void testToChar_StringDefaultValue() {
        assertEquals('b', CharUtils.toChar("", 'b'));
    }

    @Test
    void testToCharacterObject_String() {
        assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject("abc"));
    }

    @Test
    void testToCharacterObject_EmptyString() {
        assertNull(CharUtils.toCharacterObject(""));
    }

    @ParameterizedTest
    @MethodSource("provideToIntValueData")
    void testToIntValue(char ch, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch));
    }

    static Stream<Arguments> provideToIntValueData() {
        return Stream.of(
            arguments('0', 0),
            arguments('9', 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntValueDefaultValueData")
    void testToIntValueDefaultValue(char ch, int defaultValue, int expected) {
        assertEquals(expected, CharUtils.toIntValue(ch, defaultValue));
    }

    static Stream<Arguments> provideToIntValueDefaultValueData() {
        return Stream.of(
            arguments('0', 1, 0),
            arguments('9', 1, 9),
            arguments('a', 1, 1)
        );
    }

    @Test
    void testToString_Character() {
        assertEquals("a", CharUtils.toString(Character.valueOf('a')));
    }

    @Test
    void testToString_Char() {
        assertEquals("a", CharUtils.toString('a'));
    }

    @Test
    void testUnicodeEscaped_Character() {
        assertEquals("\\u0061", CharUtils.unicodeEscaped(Character.valueOf('a')));
    }

    @Test
    void testUnicodeEscaped_Char() {
        assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testIsAsciiPrintable() {
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertTrue(CharUtils.isAsciiPrintable('z'));
            assertTrue(CharUtils.isAsciiPrintable('0'));
            assertTrue(CharUtils.isAsciiPrintable(' '));
            assertFalse(CharUtils.isAsciiPrintable('\t'));
            assertFalse(CharUtils.isAsciiPrintable('\n'));
            assertFalse(CharUtils.isAsciiPrintable('\r'));
            assertFalse(CharUtils.isAsciiPrintable('\f'));
            assertFalse(CharUtils.isAsciiPrintable('\b'));
            assertFalse(CharUtils.isAsciiPrintable((char) 127));
        }

    @Test
        void testUnicodeEscaped() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
            assertEquals("\\u006a", CharUtils.unicodeEscaped('j'));
            assertEquals("\\u0030", CharUtils.unicodeEscaped('0'));
            assertEquals("\\u0020", CharUtils.unicodeEscaped(' '));
        }

    @Test
        void testToIntValue_InvalidCharacter() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('A'));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(' '));
        }

    @Test
        void testToIntValue_DefaultValue() {
            assertEquals(0, CharUtils.toIntValue('a', 0));
            assertEquals(1, CharUtils.toIntValue('b', 1));
            assertEquals(2, CharUtils.toIntValue('c', 2));
        }

    @Test
        void testToString_Character_2() {
            assertEquals("A", CharUtils.toString('A'));
            assertEquals("z", CharUtils.toString('z'));
            assertEquals("0", CharUtils.toString('0'));
            assertEquals(" ", CharUtils.toString(' '));
        }

    @Test
        void testToString_Char_2() {
            assertEquals("A", CharUtils.toString((char) 65));
            assertEquals("z", CharUtils.toString((char) 122));
            assertEquals("0", CharUtils.toString((char) 48));
            assertEquals(" ", CharUtils.toString((char) 32));
        }

    
    @Test
        void testToCharacterObject_EmptyString_2() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiExtended() {
            assertFalse(CharUtils.isAscii('\u0100')); // Latin capital letter A with macron
        }

    @Test
        public void testIsAsciiAlphaExtended() {
            assertFalse(CharUtils.isAsciiAlpha('\u0100')); // Latin capital letter A with macron
        }

    @Test
        public void testIsAsciiAlphanumericExtended() {
            assertFalse(CharUtils.isAsciiAlphanumeric('\u0100')); // Latin capital letter A with macron
        }

    
    @Test
        public void testIsAsciiNumericExtended() {
            assertFalse(CharUtils.isAsciiNumeric('\u0100')); // Latin capital letter A with macron
        }

    @Test
        public void testIsAsciiPrintableExtended() {
            assertTrue(CharUtils.isAsciiPrintable((char) 32)); // Space character
        }

    @ParameterizedTest
        @MethodSource("emptyStringProvider")
        public void testToChar_StringEmpty(String str) {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(str));
        }

    private static Stream<Arguments> emptyStringProvider() {
            return Stream.of(
                arguments("")
            );
        }

    @Test
        public void testToCharacterObject_EmptyString_3() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    @ParameterizedTest
        @MethodSource("invalidCharacterProvider")
        public void testToIntValue_InvalidCharacter_2(char ch) {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(ch));
        }

    private static Stream<Arguments> invalidCharacterProvider() {
            return Stream.of(
                arguments('a'),
                arguments('b')
            );
        }

    @Test
        public void testToString_CharacterNull() {
            assertNull(CharUtils.toString(null));
        }

    @ParameterizedTest
        @MethodSource("characterExtendedProvider")
        public void testUnicodeEscaped_CharacterExtended(char ch) {
            assertEquals("\\u0100", CharUtils.unicodeEscaped(ch)); // Latin capital letter A with macron
        }

    private static Stream<Arguments> characterExtendedProvider() {
            return Stream.of(
                arguments('\u0100')
            );
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiExtended_2() {
            assertTrue(CharUtils.isAsciiExtended('\u007F'));
            assertFalse(CharUtils.isAsciiExtended('\u0080'));
        }

    @Test
        public void testIsAsciiAlphaExtended_2() {
            assertTrue(CharUtils.isAsciiAlphaExtended('A'));
            assertTrue(CharUtils.isAsciiAlphaExtended('a'));
            assertFalse(CharUtils.isAsciiAlphaExtended('1'));
            assertFalse(CharUtils.isAsciiAlphaExtended('\u007F'));
        }

    @Test
        public void testIsAsciiAlphanumericExtended_2() {
            assertTrue(CharUtils.isAsciiAlphanumericExtended('A'));
            assertTrue(CharUtils.isAsciiAlphanumericExtended('a'));
            assertTrue(CharUtils.isAsciiAlphanumericExtended('1'));
            assertFalse(CharUtils.isAsciiAlphanumericExtended('\u007F'));
        }

    @Test
        public void testIsAsciiNumericExtended_2() {
            assertTrue(CharUtils.isAsciiNumericExtended('1'));
            assertFalse(CharUtils.isAsciiNumericExtended('A'));
            assertFalse(CharUtils.isAsciiNumericExtended('a'));
            assertFalse(CharUtils.isAsciiNumericExtended('\u007F'));
        }

    @Test
        public void testIsAsciiPrintableExtended_2() {
            assertTrue(CharUtils.isAsciiPrintableExtended(' '));
            assertTrue(CharUtils.isAsciiPrintableExtended('~'));
            assertFalse(CharUtils.isAsciiPrintableExtended('\u007F'));
        }

    @Test
        public void testToChar_StringEmpty_2() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(""));
        }

    @Test
        public void testToCharacterObject_EmptyString_3_2() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_InvalidCharacter_2_2() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('A'));
        }

    @Test
        public void testToString_CharacterNull_2() {
            assertNull(CharUtils.toString((char) 0));
        }

    @Test
        public void testUnicodeEscaped_CharacterExtended_2() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
            assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
            assertEquals("\\u0031", CharUtils.unicodeEscaped('1'));
        }
}
