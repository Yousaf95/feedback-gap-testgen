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
}
