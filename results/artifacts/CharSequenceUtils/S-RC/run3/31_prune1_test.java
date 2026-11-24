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
    @MethodSource("subSequenceProvider")
    public void testSubSequence(CharSequence cs, int start, CharSequence expected) {
        // Act
        CharSequence result = CharSequenceUtils.subSequence(cs, start);
        
        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> subSequenceProvider() {
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
    @MethodSource("toCharArrayProvider")
    public void testToCharArray(CharSequence source, char[] expected) {
        // Act
        char[] result = CharSequenceUtils.toCharArray(source);
        
        // Assert
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> toCharArrayProvider() {
        return Stream.of(
            arguments(null, new char[0]),
            arguments("", new char[0]),
            arguments("a", new char[]{'a'}),
            arguments("ab", new char[]{'a', 'b'}),
            arguments("abc", new char[]{'a', 'b', 'c'})
        );
    }

    
    private static Stream<Arguments> provideCheckLaterThan1Data() {
            return Stream.of(
                arguments("abc", "abc", 3, 0, true),
                arguments("abc", "def", 3, 0, false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideIndexOfData")
        public void testIndexOf(CharSequence cs, CharSequence searchStr, int start, int expected) {
            assertEquals(expected, CharSequenceUtils.indexOf(cs, searchStr, start));
        }

    private static Stream<Arguments> provideIndexOfData() {
            return Stream.of(
                arguments("hello world", "world", 0, 6),
                arguments(new StringBuilder("hello world"), "world", 0, 6),
                arguments(new StringBuffer("hello world"), "world", 0, 6),
                arguments("hello world", 'l', 0, 2)
            );
        }

    @ParameterizedTest
        @MethodSource("provideLastIndexOfData")
        public void testLastIndexOf(CharSequence cs, CharSequence searchStr, int start, int expected) {
            assertEquals(expected, CharSequenceUtils.lastIndexOf(cs, searchStr, start));
        }

    private static Stream<Arguments> provideLastIndexOfData() {
            return Stream.of(
                arguments("hello world", "world", 0, 6),
                arguments(new StringBuilder("hello world"), "world", 0, 6),
                arguments(new StringBuffer("hello world"), "world", 0, 6)
            );
        }
}
