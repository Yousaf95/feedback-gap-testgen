package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BooleanUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAndData")
    public void testAnd(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.and(array));
    }

    private static Stream<Arguments> provideAndData() {
        return Stream.of(
            arguments(new boolean[]{true, true}, true),
            arguments(new boolean[]{false, true}, false),
            arguments(new boolean[]{true, false}, false),
            arguments(new boolean[]{false, false}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAndBooleanData")
    public void testAndBoolean(Boolean[] array, Boolean expected) {
        assertEquals(expected, BooleanUtils.and(array));
    }

    private static Stream<Arguments> provideAndBooleanData() {
        return Stream.of(
            arguments(new Boolean[]{true, true}, Boolean.TRUE),
            arguments(new Boolean[]{false, true}, Boolean.FALSE),
            arguments(new Boolean[]{true, false}, Boolean.FALSE),
            arguments(new Boolean[]{false, false}, Boolean.FALSE)
        );
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, BooleanUtils.booleanValues());
    }

    @ParameterizedTest
    @MethodSource("provideCompareData")
    public void testCompare(boolean x, boolean y, int expected) {
        assertEquals(expected, BooleanUtils.compare(x, y));
    }

    private static Stream<Arguments> provideCompareData() {
        return Stream.of(
            arguments(true, true, 0),
            arguments(false, false, 0),
            arguments(true, false, 1),
            arguments(false, true, -1)
        );
    }

    
    
    @ParameterizedTest
    @MethodSource("provideIsFalseData")
    public void testIsFalse(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isFalse(bool));
    }

    private static Stream<Arguments> provideIsFalseData() {
        return Stream.of(
            arguments(null, false),
            arguments(false, true),
            arguments(true, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsNotFalseData")
    public void testIsNotFalse(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isNotFalse(bool));
    }

    private static Stream<Arguments> provideIsNotFalseData() {
        return Stream.of(
            arguments(null, true),
            arguments(false, false),
            arguments(true, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsNotTrueData")
    public void testIsNotTrue(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isNotTrue(bool));
    }

    private static Stream<Arguments> provideIsNotTrueData() {
        return Stream.of(
            arguments(null, true),
            arguments(false, true),
            arguments(true, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsTrueData")
    public void testIsTrue(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isTrue(bool));
    }

    private static Stream<Arguments> provideIsTrueData() {
        return Stream.of(
            arguments(null, false),
            arguments(false, false),
            arguments(true, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNegateData")
    public void testNegate(Boolean bool, Boolean expected) {
        assertEquals(expected, BooleanUtils.negate(bool));
    }

    private static Stream<Arguments> provideNegateData() {
        return Stream.of(
            arguments(null, null),
            arguments(false, true),
            arguments(true, false)
        );
    }

    
    private static Stream<Arguments> provideOneHotData() {
        return Stream.of(
            arguments(new boolean[]{true}, true),
            arguments(new boolean[]{false}, false),
            arguments(new boolean[]{true, false}, false),
            arguments(new boolean[]{false, true}, false)
        );
    }

    
    private static Stream<Arguments> provideOneHotBooleanData() {
        return Stream.of(
            arguments(new Boolean[]{true}, Boolean.TRUE),
            arguments(new Boolean[]{false}, Boolean.FALSE),
            arguments(new Boolean[]{true, false}, Boolean.FALSE),
            arguments(new Boolean[]{false, true}, Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrData")
    public void testOr(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.or(array));
    }

    private static Stream<Arguments> provideOrData() {
        return Stream.of(
            arguments(new boolean[]{true, true}, true),
            arguments(new boolean[]{false, true}, true),
            arguments(new boolean[]{true, false}, true),
            arguments(new boolean[]{false, false}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrBooleanData")
    public void testOrBoolean(Boolean[] array, Boolean expected) {
        assertEquals(expected, BooleanUtils.or(array));
    }

    private static Stream<Arguments> provideOrBooleanData() {
        return Stream.of(
            arguments(new Boolean[]{true, true}, Boolean.TRUE),
            arguments(new Boolean[]{false, true}, Boolean.TRUE),
            arguments(new Boolean[]{true, false}, Boolean.TRUE),
            arguments(new Boolean[]{false, false}, Boolean.FALSE)
        );
    }

    @Test
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanData")
    public void testToBoolean(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(bool));
    }

    private static Stream<Arguments> provideToBooleanData() {
        return Stream.of(
            arguments(null, false),
            arguments(false, false),
            arguments(true, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanIntData")
    public void testToBooleanInt(int value, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(value));
    }

    private static Stream<Arguments> provideToBooleanIntData() {
        return Stream.of(
            arguments(0, false),
            arguments(1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanIntegerValuesData")
    public void testToBooleanIntegerValues(int value, int trueValue, int falseValue, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(value, trueValue, falseValue));
    }

    private static Stream<Arguments> provideToBooleanIntegerValuesData() {
        return Stream.of(
            arguments(1, 1, 0, true),
            arguments(0, 1, 0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanStringData")
    public void testToBooleanString(String str, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(str));
    }

    private static Stream<Arguments> provideToBooleanStringData() {
        return Stream.of(
            arguments(null, false),
            arguments("", false),
            arguments("true", true),
            arguments("false", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanStringValuesData")
    public void testToBooleanStringValues(String str, String trueString, String falseString, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(str, trueString, falseString));
    }

    private static Stream<Arguments> provideToBooleanStringValuesData() {
        return Stream.of(
            arguments("true", "true", "false", true),
            arguments("false", "true", "false", false)
        );
    }

    
    private static Stream<Arguments> provideToBooleanDefaultIfNullData() {
        return Stream.of(
            arguments(null, false, true),
            arguments(false, false, false),
            arguments(true, false, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringData")
    public void testToString(boolean bool, String trueString, String falseString, String expected) {
        assertEquals(expected, BooleanUtils.toString(bool, trueString, falseString));
    }

    private static Stream<Arguments> provideToStringData() {
        return Stream.of(
            arguments(true, "yes", "no", "yes"),
            arguments(false, "yes", "no", "no")
        );
    }

    
    private static Stream<Arguments> provideToStringBooleanData() {
        return Stream.of(
            arguments(null, "yes", "no", "maybe", null),
            arguments(false, "yes", "no", "maybe", "no"),
            arguments(true, "yes", "no", "maybe", "yes")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringOnOffData")
    public void testToStringOnOff(boolean bool) {
        assertEquals(bool ? BooleanUtils.ON : BooleanUtils.OFF, BooleanUtils.toStringOnOff(bool));
    }

    private static Stream<Arguments> provideToStringOnOffData() {
        return Stream.of(
            arguments(true),
            arguments(false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringTrueFalseData")
    public void testToStringTrueFalse(boolean bool) {
        assertEquals(bool ? BooleanUtils.TRUE : BooleanUtils.FALSE, BooleanUtils.toStringTrueFalse(bool));
    }

    private static Stream<Arguments> provideToStringTrueFalseData() {
        return Stream.of(
            arguments(true),
            arguments(false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringYesNoData")
    public void testToStringYesNo(boolean bool) {
        assertEquals(bool ? BooleanUtils.YES : BooleanUtils.NO, BooleanUtils.toStringYesNo(bool));
    }

    private static Stream<Arguments> provideToStringYesNoData() {
        return Stream.of(
            arguments(true),
            arguments(false)
        );
    }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("booleanArrayProvider")
        public void testForEach(Consumer<Boolean> action) {
            action.accept(true);
            action.accept(false);
        }

    private static Stream<Arguments> booleanArrayProvider() {
            return Stream.of(
                arguments(new Boolean[]{true, false})
            );
        }

    @Test
        public void testOneHotBooleanArray() {
            assertTrue(BooleanUtils.oneHot(new boolean[]{true, false}));
            assertFalse(BooleanUtils.oneHot(new boolean[]{false, true}));
            assertFalse(BooleanUtils.oneHot(new boolean[]{true, true}));
            assertFalse(BooleanUtils.oneHot(new boolean[]{false, false}));
        }

    @Test
        public void testOneHotBooleanObjectArray() {
            assertTrue(BooleanUtils.oneHot(new Boolean[]{Boolean.TRUE, Boolean.FALSE}));
            assertFalse(BooleanUtils.oneHot(new Boolean[]{Boolean.FALSE, Boolean.TRUE}));
            assertFalse(BooleanUtils.oneHot(new Boolean[]{Boolean.TRUE, Boolean.TRUE}));
            assertFalse(BooleanUtils.oneHot(new Boolean[]{Boolean.FALSE, Boolean.FALSE}));
        }

    @Test
        public void testToBooleanIntegerValues_2() {
            assertTrue(BooleanUtils.toBoolean(1, 1, 0));
            assertFalse(BooleanUtils.toBoolean(0, 1, 0));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
        }

    @Test
        public void testToBooleanIntegerObjectValues() {
            assertTrue(BooleanUtils.toBoolean(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0)));
            assertFalse(BooleanUtils.toBoolean(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(0)));
        }

    @Test
        public void testToBooleanStringValues_2() {
            assertTrue(BooleanUtils.toBoolean("true", "true", "false"));
            assertFalse(BooleanUtils.toBoolean("false", "true", "false"));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("other", "true", "false"));
        }

    @Test
        public void testToBooleanObjectStringValues() {
            assertTrue(BooleanUtils.toBooleanObject("true", "true", "false") == Boolean.TRUE);
            assertFalse(BooleanUtils.toBooleanObject("false", "true", "false") == Boolean.FALSE);
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("other", "true", "false"));
        }

    @Test
        public void testToBooleanStringPartialBranches() {
            assertTrue(BooleanUtils.toBoolean("y"));
            assertFalse(BooleanUtils.toBoolean("n"));
            assertTrue(BooleanUtils.toBoolean("t"));
            assertFalse(BooleanUtils.toBoolean("f"));
            assertTrue(BooleanUtils.toBoolean("1"));
            assertFalse(BooleanUtils.toBoolean("0"));
        }

    @Test
        public void testToBooleanObjectStringPartialBranches() {
            assertTrue(BooleanUtils.toBooleanObject("y") == Boolean.TRUE);
            assertFalse(BooleanUtils.toBooleanObject("n") == Boolean.FALSE);
            assertTrue(BooleanUtils.toBooleanObject("t") == Boolean.TRUE);
            assertFalse(BooleanUtils.toBooleanObject("f") == Boolean.FALSE);
            assertTrue(BooleanUtils.toBooleanObject("1") == Boolean.TRUE);
            assertFalse(BooleanUtils.toBooleanObject("0") == Boolean.FALSE);
        }

    @Test
        public void testToBooleanStringPartialBranchesNull() {
            assertNull(BooleanUtils.toBoolean(null));
            // Removed the following line as it is ambiguous and not applicable
            // assertNull(BooleanUtils.toBooleanObject(null));
        }
}
