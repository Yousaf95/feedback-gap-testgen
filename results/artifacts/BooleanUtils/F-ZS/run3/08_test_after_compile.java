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
        assertFalse(and(true, false));
        assertFalse(and(false, true));
        assertFalse(and(false, false));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testAndWithSingleElement(boolean value) {
        assertEquals(value, and(value));
    }

    @Test
    public void testAndWithNull() {
        assertThrows(NullPointerException.class, () -> and((Boolean[]) null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBoolean(String str) {
        assertTrue(toBoolean(str));
    }

    @Test
    public void testToBooleanWithNull() {
        assertFalse(toBoolean(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testToInteger(boolean bool, int value) {
        assertEquals(value, toInteger(bool));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToStringTrueFalse(String str) {
        assertTrue(toStringTrueFalse(Boolean.valueOf(str)).equals(str));
    }

    @Test
    public void testValues() {
        List<Boolean> values = BooleanUtils.values();
        assertEquals(2, values.size());
        assertTrue(values.contains(false));
        assertTrue(values.contains(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanObject(String str) {
        assertTrue(toBooleanObject(str).equals(Boolean.valueOf(str)));
    }

    @Test
    public void testXor() {
        assertTrue(xor(true, false));
        assertFalse(xor(false, true));
        assertFalse(xor(true, true));
        assertFalse(xor(false, false));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testXorWithSingleElement(boolean value) {
        assertEquals(value, xor(value));
    }

    @Test
    public void testXorWithNull() {
        assertThrows(NullPointerException.class, () -> xor((Boolean[]) null));
    }
}
