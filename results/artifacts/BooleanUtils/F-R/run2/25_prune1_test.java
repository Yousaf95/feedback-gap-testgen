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

    @ParameterizedTest
    @MethodSource("provideXorTestData")
    public void testXor(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.xor(array));
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

    @ParameterizedTest
    @MethodSource("provideOneHotTestData")
    public void testOneHot(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.oneHot(array));
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
}
