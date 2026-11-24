package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CharSequenceUtils_GenTest {

    @ParameterizedTest
    @MethodSource("subSequenceDataProvider")
    public void testSubSequence(CharSequence cs, int start, CharSequence expected) {
        // Arrange
        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);
        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> subSequenceDataProvider() {
        return Stream.of(
            arguments(null, 0, null),
            arguments("", 0, ""),
            arguments("abc", 0, "abc"),
            arguments("abc", 1, "bc"),
            arguments("abc", 2, "c"),
            arguments("abc", 3, "")
        );
    }

    @ParameterizedTest
    @MethodSource("toCharArrayDataProvider")
    public void testToCharArray(CharSequence source, char[] expected) {
        // Arrange
        // Act
        char[] result = CharSequenceUtils.toCharArray(source);
        // Assert
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> toCharArrayDataProvider() {
        return Stream.of(
            arguments(null, new char[0]),
            arguments("", new char[0]),
            arguments("abc", new char[]{'a', 'b', 'c'}),
            arguments("a", new char[]{'a'})
        );
    }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("provideCheckLaterThan1Data")
        public void testCheckLaterThan1(String str1, String str2, int start1, int start2, boolean expected) {
            assertEquals(expected, CharSequenceUtils.checkLaterThan1(str1, str2, start1, start2));
        }

    private static Stream<Arguments> provideCheckLaterThan1Data() {
            return Stream.of(
                arguments("abc", "abc", 3, 0, true),
                arguments("abc", "def", 3, 0, false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideIndexOfData")
        public void testIndexOf(CharSequence cs, CharSequence search, int start, int expected) {
            assertEquals(expected, CharSequenceUtils.indexOf(cs, search, start));
        }

    private static Stream<Arguments> provideIndexOfData() {
            return Stream.of(
                arguments("hello world", "world", 0, 2),
                arguments(new StringBuilder("hello world"), "world", 0, 6),
                arguments(new StringBuffer("hello world"), "world", 0, 6)
            );
        }

    @ParameterizedTest
        @MethodSource("provideLastIndexOfData")
        public void testLastIndexOf(CharSequence cs, CharSequence search, int start, int expected) {
            assertEquals(expected, CharSequenceUtils.lastIndexOf(cs, search, start));
        }

    private static Stream<Arguments> provideLastIndexOfData() {
            return Stream.of(
                arguments("hello world", "world", 0, 6),
                arguments(new StringBuilder("hello world"), "world", 0, 6),
                arguments(new StringBuffer("hello world"), "world", 0, 6)
            );
        }
}
