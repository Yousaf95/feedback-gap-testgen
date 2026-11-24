package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.function.Consumer;

public class BooleanUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // No-op setup method
    }

    @ParameterizedTest
    @MethodSource("provideAndTestData")
    public void testAnd(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.and(array));
    }

    private static Stream<Arguments> provideAndTestData() {
        return Stream.of(
            arguments(new boolean[]{true, true, true}, true),
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrTestData")
    public void testOr(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.or(array));
    }

    private static Stream<Arguments> provideOrTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    
    private static Stream<Arguments> provideXorTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    
    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanObjectTestData")
    public void testToBooleanObject(String str, Boolean expected) {
        assertEquals(expected, BooleanUtils.toBooleanObject(str));
    }

    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            arguments("true", Boolean.TRUE),
            arguments("false", Boolean.FALSE),
            arguments("yes", Boolean.TRUE),
            arguments("no", Boolean.FALSE),
            arguments("on", Boolean.TRUE),
            arguments("off", Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(String str, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(str));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            arguments("true", true),
            arguments("false", false),
            arguments("yes", true),
            arguments("no", false),
            arguments("on", true),
            arguments("off", false)
        );
    }

    
    
    @ParameterizedTest
    @MethodSource("provideToStringTrueFalseTestData")
    public void testToStringTrueFalse(boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringTrueFalse(bool));
    }

    private static Stream<Arguments> provideToStringTrueFalseTestData() {
        return Stream.of(
            arguments(true, "true"),
            arguments(false, "false")
        );
    }

    
    private static Stream<Arguments> provideToBooleanObjectWithNullStringTestData() {
        return Stream.of(
            arguments("true", "true", "false", Boolean.TRUE),
            arguments("false", "true", "false", Boolean.FALSE),
            arguments(null, null, null, null)
        );
    }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("booleanArrayProvider")
        public void testXor(boolean[] array, boolean expected) {
            assertEquals(expected, BooleanUtils.xor(array));
        }

    @ParameterizedTest
        @MethodSource("booleanArrayProvider")
        public void testOneHot(boolean[] array, boolean expected) {
            assertEquals(expected, BooleanUtils.oneHot(array));
        }

    @ParameterizedTest
        @MethodSource("compareValuesProvider")
        public void testCompare(boolean x, boolean y, int expected) {
            assertEquals(expected, BooleanUtils.compare(x, y));
        }

    
    
    private static Stream<Arguments> booleanArrayProvider() {
            return Stream.of(
                arguments(new boolean[]{true, false}, true),
                arguments(new boolean[]{false, false}, false),
                arguments(new boolean[]{true, true}, false),
                arguments(new boolean[]{false, true}, true)
            );
        }

    private static Stream<Arguments> compareValuesProvider() {
            return Stream.of(
                arguments(true, true, 0),
                arguments(false, true, -1),
                arguments(true, false, 1)
            );
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToBooleanObjectWithNullString() {
            assertNull(BooleanUtils.toBooleanObject(null));
        }

    @Test
        public void testToBooleanObjectWithEmptyString() {
            assertFalse(BooleanUtils.toBooleanObject(""));
        }

    @Test
        public void testToBooleanObjectWithTrueString() {
            assertTrue(BooleanUtils.toBooleanObject("true"));
        }

    @Test
        public void testToBooleanObjectWithFalseString() {
            assertFalse(BooleanUtils.toBooleanObject("false"));
        }

    @Test
        public void testToBooleanObjectWithYesString() {
            assertTrue(BooleanUtils.toBooleanObject("yes"));
        }

    @Test
        public void testToBooleanObjectWithNoString() {
            assertFalse(BooleanUtils.toBooleanObject("no"));
        }

    @Test
        public void testToBooleanObjectWithOnString() {
            assertTrue(BooleanUtils.toBooleanObject("on"));
        }

    @Test
        public void testToBooleanObjectWithOffString() {
            assertFalse(BooleanUtils.toBooleanObject("off"));
        }

    @Test
        public void testToBooleanObjectWithIntegerZero() {
            assertFalse(BooleanUtils.toBooleanObject(0));
        }

    @Test
        public void testToBooleanObjectWithIntegerOne() {
            assertTrue(BooleanUtils.toBooleanObject(1));
        }

    @ParameterizedTest
        @MethodSource("provideTrueValues")
        public void testToBooleanObjectWithTrueIntegerValue(int value) {
            assertTrue(BooleanUtils.toBooleanObject(value, 42, 0));
        }

    private static Stream<Arguments> provideTrueValues() {
            return Stream.of(42);
        }

    @ParameterizedTest
        @MethodSource("provideFalseValues")
        public void testToBooleanObjectWithFalseIntegerValue(int value) {
            assertFalse(BooleanUtils.toBooleanObject(value, 0, 1));
        }

    private static Stream<Arguments> provideFalseValues() {
            return Stream.of(0);
        }

    @Test
        public void testXorWithEmptyArray() {
            assertTrue(BooleanUtils.xor(new boolean[0]));
        }

    @ParameterizedTest
        @MethodSource("provideSingleTrueValue")
        public void testXorWithSingleTrueElement(boolean value) {
            assertTrue(BooleanUtils.xor(value));
        }

    private static Stream<Arguments> provideSingleTrueValue() {
            return Stream.of(true);
        }

    @ParameterizedTest
        @MethodSource("provideSingleFalseValue")
        public void testXorWithSingleFalseElement(boolean value) {
            assertFalse(BooleanUtils.xor(value));
        }

    private static Stream<Arguments> provideSingleFalseValue() {
            return Stream.of(false);
        }

    @Test
        public void testXorWithMultipleElements() {
            assertTrue(BooleanUtils.xor(true, false, true));
        }

    @Test
        public void testOneHotWithEmptyArray() {
            assertFalse(BooleanUtils.oneHot(new boolean[0]));
        }

    @ParameterizedTest
        @MethodSource("provideSingleTrueValue")
        public void testOneHotWithSingleTrueElement(boolean value) {
            assertTrue(BooleanUtils.oneHot(value));
        }

    private static Stream<Arguments> provideSingleTrueValue_2() {
            return Stream.of(true);
        }

    @ParameterizedTest
        @MethodSource("provideSingleFalseValue")
        public void testOneHotWithSingleFalseElement(boolean value) {
            assertFalse(BooleanUtils.oneHot(value));
        }

    private static Stream<Arguments> provideSingleFalseValue_2() {
            return Stream.of(false);
        }

    @Test
        public void testOneHotWithMultipleElements() {
            assertTrue(BooleanUtils.oneHot(true, false, true));
        }
}
