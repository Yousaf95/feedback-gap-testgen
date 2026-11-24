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
}
