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
        public void testIsAsciiPrintable_NotCovered() {
            assertFalse(CharUtils.isAsciiPrintable((char) 127));
            assertTrue(CharUtils.isAsciiPrintable(' '));
        }

    @Test
        public void testToChar_Character_NotCovered() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringDefaultValue_NotCovered() {
            assertEquals('b', CharUtils.toChar("", 'b'));
        }

    @Test
        public void testToCharacterObject_EmptyString_NotCovered() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_NotCovered() {
            assertEquals(5, CharUtils.toIntValue('5'));
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToIntValueDefaultValue_NotCovered() {
            assertEquals(9, CharUtils.toIntValue('9', 9));
            assertEquals(0, CharUtils.toIntValue('a', 0));
        }

    @Test
        public void testToString_Char_NotCovered() {
            assertEquals(" ", CharUtils.toString(' '));
            assertEquals("\u007F", CharUtils.toString((char) 127));
        }

    @Test
        public void testUnicodeEscaped_Character_NotCovered() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped(Character.valueOf('A')));
            assertNull(CharUtils.unicodeEscaped(null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_NotCovered() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
            assertFalse(CharUtils.isAsciiAlphaLower('z'));
        }

    @Test
        public void testIsAsciiControl_NotCovered() {
            assertTrue(CharUtils.isAsciiControl((char) 31));
            assertFalse(CharUtils.isAsciiControl((char) 32));
            assertTrue(CharUtils.isAsciiControl((char) 126));
            assertFalse(CharUtils.isAsciiControl((char) 127));
        }

    @Test
        public void testIsAsciiPrintable_NotCovered_2() {
            assertFalse(CharUtils.isAsciiPrintable((char) 31));
            assertTrue(CharUtils.isAsciiPrintable((char) 32));
            assertTrue(CharUtils.isAsciiPrintable((char) 126));
            assertFalse(CharUtils.isAsciiPrintable((char) 127));
        }

    @Test
        public void testToChar_Character_NotCovered_2() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringDefaultValue_NotCovered_2() {
            assertEquals('b', CharUtils.toChar("", 'b'));
        }

    @Test
        public void testToCharacterObject_EmptyString_NotCovered_2() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_NotCovered_2() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToIntValueDefaultValue_NotCovered_2() {
            assertEquals(0, CharUtils.toIntValue('a', 0));
        }

    @Test
        public void testToString_Char_NotCovered_2() {
            assertEquals("128", CharUtils.toString((char) 128));
        }

    @Test
        public void testUnicodeEscaped_Character_NotCovered_2() {
            assertEquals("\\u007F", CharUtils.unicodeEscaped((char) 127));
        }
}
