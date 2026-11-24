package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BooleanUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAndTestData")
    public void testAnd(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.and(array));
        assertEquals(expected, BooleanUtils.and(ArrayUtils.toObject(array)));
    }

    private static Stream<Arguments> provideAndTestData() {
        return Stream.of(
            of(new boolean[]{true, true}, true),
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, false}, false),
            of(new boolean[]{false, true}, false),
            of(new boolean[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrTestData")
    public void testOr(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.or(array));
        assertEquals(expected, BooleanUtils.or(ArrayUtils.toObject(array)));
    }

    private static Stream<Arguments> provideOrTestData() {
        return Stream.of(
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, true}, true),
            of(new boolean[]{true, false}, true),
            of(new boolean[]{false, true}, true),
            of(new boolean[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOneHotTestData")
    public void testOneHot(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.oneHot(array));
        assertEquals(expected, BooleanUtils.oneHot(ArrayUtils.toObject(array)));
    }

    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            of(new boolean[]{false}, true),
            of(new boolean[]{true}, false),
            of(new boolean[]{false, true}, false),
            of(new boolean[]{true, false}, false),
            of(new boolean[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideXorTestData")
    public void testXor(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.xor(array));
        assertEquals(expected, BooleanUtils.xor(ArrayUtils.toObject(array)));
    }

    private static Stream<Arguments> provideXorTestData() {
        return Stream.of(
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, true}, false),
            of(new boolean[]{true, false}, true),
            of(new boolean[]{false, true}, true),
            of(new boolean[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(bool));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            of(null, false),
            of(false, false),
            of(true, true)
        );
    }

    
    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            of(null, null),
            of(false, Boolean.FALSE),
            of(true, Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringTrueFalseTestData")
    public void testToStringTrueFalse(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringTrueFalse(bool));
    }

    private static Stream<Arguments> provideToStringTrueFalseTestData() {
        return Stream.of(
            of(null, null),
            of(false, "false"),
            of(true, "true")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringYesNoTestData")
    public void testToStringYesNo(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringYesNo(bool));
    }

    private static Stream<Arguments> provideToStringYesNoTestData() {
        return Stream.of(
            of(null, null),
            of(false, "no"),
            of(true, "yes")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringOnOffTestData")
    public void testToStringOnOff(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringOnOff(bool));
    }

    private static Stream<Arguments> provideToStringOnOffTestData() {
        return Stream.of(
            of(null, null),
            of(false, "off"),
            of(true, "on")
        );
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{false, true}, BooleanUtils.booleanValues());
    }

    @Test
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
    }

    @ParameterizedTest
    @MethodSource("provideToIntegerTestData")
    public void testToInteger(boolean bool, int expected) {
        assertEquals(expected, BooleanUtils.toInteger(bool));
    }

    private static Stream<Arguments> provideToIntegerTestData() {
        return Stream.of(
            of(false, 0),
            of(true, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntegerObjectTestData")
    public void testToIntegerObject(boolean bool, Integer expected) {
        assertEquals(expected, BooleanUtils.toIntegerObject(bool));
    }

    private static Stream<Arguments> provideToIntegerObjectTestData() {
        return Stream.of(
            of(false, 0),
            of(true, 1)
        );
    }
}
