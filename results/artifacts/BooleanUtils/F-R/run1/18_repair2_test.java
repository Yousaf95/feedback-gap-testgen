package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.BooleanUtils.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BooleanUtils_GenTest {

    @Test
    public void testAnd() {
        assertTrue(and(true, true));
        assertFalse(and(false, true));
        assertFalse(and(true, false));
        assertFalse(and(false, false));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testAndWithBooleanArray(boolean value) {
        Boolean[] array = {value, value};
        assertEquals(value, and(array));
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, booleanValues());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testCompare(boolean value) {
        assertEquals(value ? 1 : -1, compare(true, value));
        assertEquals(0, compare(value, value));
    }

    @Test
    public void testForEach() {
        forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testIsFalse(boolean value) {
        assertFalse(isFalse(Boolean.valueOf(!value)));
        assertTrue(isFalse(Boolean.valueOf(value)));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testIsNotFalse(boolean value) {
        assertTrue(isNotFalse(Boolean.valueOf(!value)));
        assertFalse(isNotFalse(Boolean.valueOf(value)));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testIsNotTrue(boolean value) {
        assertTrue(isNotTrue(Boolean.valueOf(value)));
        assertFalse(isNotTrue(Boolean.valueOf(!value)));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testIsTrue(boolean value) {
        assertTrue(isTrue(Boolean.valueOf(value)));
        assertFalse(isTrue(Boolean.valueOf(!value)));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testNegate(boolean value) {
        assertEquals(!value, negate(Boolean.valueOf(value)).booleanValue());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testOneHot(boolean value) {
        assertTrue(oneHot(value));
        assertFalse(oneHot(!value));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testOr(boolean value) {
        assertTrue(or(true, value));
        assertTrue(or(value, true));
        assertFalse(or(false, !value));
        assertFalse(or(!value, false));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, primitiveValues());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToBoolean(boolean value) {
        assertEquals(value, toBoolean(Boolean.valueOf(value)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testToBooleanInt(int value) {
        assertEquals(value != 0, toBoolean(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    public void testToBooleanIntWithValues(int value) {
        assertTrue(toBoolean(value, 1, 0));
        assertFalse(toBoolean(value, 0, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanString(String str) {
        assertEquals(str.equals("true"), toBoolean(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanStringWithValues(String str) {
        assertTrue(toBoolean(str, "true", "false"));
        assertFalse(toBoolean(str, "false", "true"));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToBooleanDefaultIfNull(boolean value) {
        assertEquals(value, toBooleanDefaultIfNull(Boolean.valueOf(value), !value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testToBooleanObjectInt(int value) {
        assertEquals(value != 0, toBooleanObject(value).booleanValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    public void testToBooleanObjectIntWithValues(int value) {
        assertTrue(toBooleanObject(value, 1, 0, -1).booleanValue());
        assertFalse(toBooleanObject(value, 0, 1, -1).booleanValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanObjectString(String str) {
        assertEquals(str.equals("true"), toBooleanObject(str).booleanValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanObjectStringWithValues(String str) {
        assertTrue(toBooleanObject(str, "true", "false", null).booleanValue());
        assertFalse(toBooleanObject(str, "false", "true", null).booleanValue());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToInteger(boolean value) {
        assertEquals(value ? 1 : 0, toInteger(value));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToIntegerWithValues(boolean value) {
        assertEquals(1, toInteger(value, 1, 0));
        assertEquals(0, toInteger(value, 0, 1));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToString(boolean value) {
        assertEquals(TRUE, toString(value, TRUE, FALSE));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringWithValues(boolean value) {
        assertEquals(TRUE, toString(value, TRUE, FALSE, null));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringOnOff(boolean value) {
        assertEquals(ON, toStringOnOff(value));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringTrueFalse(boolean value) {
        assertEquals(TRUE, toStringTrueFalse(value));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringYesNo(boolean value) {
        assertEquals(YES, toStringYesNo(value));
    }

    @Test
    public void testXor() {
        assertTrue(xor(true, false));
        assertFalse(xor(false, false));
        assertFalse(xor(true, true));
        assertTrue(xor(false, true));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testXorWithBooleanArray(boolean value) {
        Boolean[] array = {value, !value};
        assertEquals(value, xor(array));
    }
}
