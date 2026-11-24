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
    @Test
        public void testAnd_withEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and((boolean[]) null));
        }

    @Test
        public void testAnd_withAllTrue() {
            assertTrue(BooleanUtils.and(true, true));
        }

    @Test
        public void testAnd_withMixedValues() {
            assertFalse(BooleanUtils.and(true, false));
        }

    @Test
        public void testOr_withEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or((boolean[]) null));
        }

    @Test
        public void testOr_withAllFalse() {
            assertFalse(BooleanUtils.or(false, false));
        }

    @Test
        public void testOr_withMixedValues() {
            assertTrue(BooleanUtils.or(true, false));
        }

    @Test
        public void testToBooleanObject_withNullValue() {
            assertNull(BooleanUtils.toBooleanObject(null));
        }

    @Test
        public void testToBooleanObject_withTrueValue() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1));
        }

    @Test
        public void testToBooleanObject_withFalseValue() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0));
        }
}
