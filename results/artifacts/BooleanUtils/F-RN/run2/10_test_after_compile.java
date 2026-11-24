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
import java.util.Arrays;
import java.util.function.Consumer;

public class BooleanUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // No setup needed for this test class
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

    @Test
    public void testAndWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
    }

    @Test
    public void testOrWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.or((Boolean[]) null));
    }

    @Test
    public void testXorWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
    }

    
    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, BooleanUtils.booleanValues());
    }

    @Test
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(bool));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, true),
            arguments(Boolean.FALSE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanObjectTestData")
    public void testToBooleanObject(String str, Boolean expected) {
        assertEquals(expected, BooleanUtils.toBooleanObject(str));
    }

    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            arguments(null, null),
            arguments(Boolean.TRUE.toString(), Boolean.TRUE),
            arguments(Boolean.FALSE.toString(), Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringTestData")
    public void testToString(boolean bool, String trueString, String falseString, String expected) {
        assertEquals(expected, BooleanUtils.toString(bool, trueString, falseString));
    }

    private static Stream<Arguments> provideToStringTestData() {
        return Stream.of(
            arguments(true, "yes", "no", "yes"),
            arguments(false, "yes", "no", "no")
        );
    }

    
    @ParameterizedTest
    @MethodSource("provideIsTrueTestData")
    public void testIsTrue(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isTrue(bool));
    }

    private static Stream<Arguments> provideIsTrueTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, true),
            arguments(Boolean.FALSE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsFalseTestData")
    public void testIsFalse(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isFalse(bool));
    }

    private static Stream<Arguments> provideIsFalseTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, false),
            arguments(Boolean.FALSE, true)
        );
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCompare() {
            assertEquals(0, BooleanUtils.compare(true, true));
            assertEquals(-1, BooleanUtils.compare(false, true));
            assertEquals(1, BooleanUtils.compare(true, false));
        }

    @Test
        public void testForEach() {
            List<Boolean> expected = Arrays.asList(Boolean.FALSE, Boolean.TRUE);
            List<Boolean> actual = new java.util.ArrayList<>();
            BooleanUtils.forEach(actual::add);
            assertEquals(expected, actual);
        }

    @Test
        public void testOneHot() {
            assertTrue(BooleanUtils.oneHot(true));
            assertFalse(BooleanUtils.oneHot(false));
            assertTrue(BooleanUtils.oneHot(true, false));
            assertFalse(BooleanUtils.oneHot(false, true));
            assertFalse(BooleanUtils.oneHot(true, true));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.oneHot());
        }

    @Test
        public void testToInteger() {
            assertEquals(1, BooleanUtils.toInteger(true));
            assertEquals(0, BooleanUtils.toInteger(false));
            assertEquals(1, BooleanUtils.toInteger(true, 1, 0));
            assertEquals(0, BooleanUtils.toInteger(false, 1, 0));
        }

    @Test
        public void testToIntegerObject() {
            assertEquals(NumberUtils.INTEGER_ONE, BooleanUtils.toIntegerObject(true));
            assertEquals(NumberUtils.INTEGER_ZERO, BooleanUtils.toIntegerObject(false));
            assertEquals(NumberUtils.INTEGER_ONE, BooleanUtils.toIntegerObject(true, NumberUtils.INTEGER_ONE, NumberUtils.INTEGER_ZERO));
            assertEquals(NumberUtils.INTEGER_ZERO, BooleanUtils.toIntegerObject(false, NumberUtils.INTEGER_ONE, NumberUtils.INTEGER_ZERO));
        }

    @Test
        public void testToString_2() {
            assertEquals("true", BooleanUtils.toString(true, "true", "false"));
            assertEquals("false", BooleanUtils.toString(false, "true", "false"));
            assertEquals("null", BooleanUtils.toString(null, "true", "false", "null"));
        }

    @Test
        public void testToStringOnOff() {
            assertEquals(ON, BooleanUtils.toStringOnOff(true));
            assertEquals(OFF, BooleanUtils.toStringOnOff(false));
            assertNull(BooleanUtils.toStringOnOff(null));
        }

    @Test
        public void testToStringTrueFalse() {
            assertEquals(TRUE, BooleanUtils.toStringTrueFalse(true));
            assertEquals(FALSE, BooleanUtils.toStringTrueFalse(false));
            assertNull(BooleanUtils.toStringTrueFalse(null));
        }

    @Test
        public void testToStringYesNo() {
            assertEquals(YES, BooleanUtils.toStringYesNo(true));
            assertEquals(NO, BooleanUtils.toStringYesNo(false));
            assertNull(BooleanUtils.toStringYesNo(null));
        }
}
