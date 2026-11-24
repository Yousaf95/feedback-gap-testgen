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
    @Test
        public void testCheckLaterThan1_withEqualChars() {
            assertTrue(CharSequenceUtils.checkLaterThan1("abc", "abc", 3, 0));
        }

    @Test
        public void testCheckLaterThan1_withUnequalChars() {
            assertFalse(CharSequenceUtils.checkLaterThan1("abc", "def", 3, 0));
        }

    @Test
        public void testIndexOf_withString() {
            assertEquals(2, CharSequenceUtils.indexOf("hello world", "world"));
        }

    @Test
        public void testIndexOf_withStringBuilder() {
            StringBuilder sb = new StringBuilder("hello world");
            assertEquals(6, CharSequenceUtils.indexOf(sb, "world"));
        }

    @Test
        public void testIndexOf_withStringBuffer() {
            StringBuffer sb = new StringBuffer("hello world");
            assertEquals(6, CharSequenceUtils.indexOf(sb, "world"));
        }

    @Test
        public void testIndexOf_withChar() {
            assertEquals(2, CharSequenceUtils.indexOf("hello world", 'l'));
        }

    @Test
        public void testLastIndexOf_withString() {
            assertEquals(6, CharSequenceUtils.lastIndexOf("hello world", "world"));
        }

    @Test
        public void testLastIndexOf_withStringBuilder() {
            StringBuilder sb = new StringBuilder("hello world");
            assertEquals(6, CharSequenceUtils.lastIndexOf(sb, "world"));
        }

    @Test
        public void testLastIndexOf_withStringBuffer() {
            StringBuffer sb = new StringBuffer("hello world");
            assertEquals(6, CharSequenceUtils.lastIndexOf(sb, "world"));
        }
}
