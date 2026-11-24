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

    
    @Test
    public void testForEach() {
        forEach(System.out::println);
    }

    
    
    
    
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testNegate(boolean value) {
        assertEquals(!value, negate(Boolean.valueOf(value)).booleanValue());
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
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanString(String str) {
        assertEquals(str.equals("true"), toBoolean(str));
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
    @ValueSource(strings = {"true", "false"})
    public void testToBooleanObjectString(String str) {
        assertEquals(str.equals("true"), toBooleanObject(str).booleanValue());
    }

    
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToInteger(boolean value) {
        assertEquals(value ? 1 : 0, toInteger(value));
    }

    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testCompare() {
            assertEquals(0, BooleanUtils.compare(true, true));
            assertEquals(-1, BooleanUtils.compare(false, true));
            assertEquals(1, BooleanUtils.compare(true, false));
        }

    @Test
        public void testIsFalse() {
            assertTrue(BooleanUtils.isFalse(Boolean.FALSE));
            assertFalse(BooleanUtils.isFalse(Boolean.TRUE));
            assertFalse(BooleanUtils.isFalse(null));
        }

    @Test
        public void testIsNotFalse() {
            assertFalse(BooleanUtils.isNotFalse(Boolean.FALSE));
            assertTrue(BooleanUtils.isNotFalse(Boolean.TRUE));
            assertTrue(BooleanUtils.isNotFalse(null));
        }

    @Test
        public void testIsNotTrue() {
            assertTrue(BooleanUtils.isNotTrue(Boolean.FALSE));
            assertFalse(BooleanUtils.isNotTrue(Boolean.TRUE));
            assertTrue(BooleanUtils.isNotTrue(null));
        }

    @Test
        public void testIsTrue() {
            assertFalse(BooleanUtils.isTrue(Boolean.FALSE));
            assertTrue(BooleanUtils.isTrue(Boolean.TRUE));
            assertFalse(BooleanUtils.isTrue(null));
        }

    @Test
        public void testNegate_2() {
            assertNull(BooleanUtils.negate(null));
            assertEquals(Boolean.FALSE, BooleanUtils.negate(Boolean.TRUE));
            assertEquals(Boolean.TRUE, BooleanUtils.negate(Boolean.FALSE));
        }

    @Test
        public void testOneHotBooleanArray() {
            assertTrue(BooleanUtils.oneHot(true, false));
            assertFalse(BooleanUtils.oneHot(false, true));
            assertFalse(BooleanUtils.oneHot(true, true));
            assertFalse(BooleanUtils.oneHot(false, false));
        }

    @Test
        public void testOneHotBooleanObjectArray() {
            assertTrue(BooleanUtils.oneHot(Boolean.TRUE, Boolean.FALSE));
            assertFalse(BooleanUtils.oneHot(Boolean.FALSE, Boolean.TRUE));
            assertFalse(BooleanUtils.oneHot(Boolean.TRUE, Boolean.TRUE));
            assertFalse(BooleanUtils.oneHot(Boolean.FALSE, Boolean.FALSE));
        }

    @Test
        public void testOrBooleanArray() {
            assertTrue(BooleanUtils.or(true, false));
            assertTrue(BooleanUtils.or(false, true));
            assertTrue(BooleanUtils.or(true, true));
            assertFalse(BooleanUtils.or(false, false));
        }

    @Test
        public void testOrBooleanObjectArray() {
            assertTrue(BooleanUtils.or(Boolean.TRUE, Boolean.FALSE));
            assertTrue(BooleanUtils.or(Boolean.FALSE, Boolean.TRUE));
            assertTrue(BooleanUtils.or(Boolean.TRUE, Boolean.TRUE));
            assertFalse(BooleanUtils.or(Boolean.FALSE, Boolean.FALSE));
        }

    @Test
        public void testToBoolean_2() {
            assertTrue(BooleanUtils.toBoolean(Boolean.TRUE));
            assertFalse(BooleanUtils.toBoolean(Boolean.FALSE));
            assertFalse(BooleanUtils.toBoolean(null));
        }

    @Test
        public void testToBooleanInt_2() {
            assertTrue(BooleanUtils.toBoolean(1, 1, 0));
            assertFalse(BooleanUtils.toBoolean(0, 1, 0));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
        }

    @Test
        public void testToBooleanInteger() {
            assertTrue(BooleanUtils.toBoolean(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0)));
            assertFalse(BooleanUtils.toBoolean(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(0)));
        }

    @Test
        public void testToBooleanString_2() {
            assertTrue(BooleanUtils.toBoolean("true", "true", "false"));
            assertFalse(BooleanUtils.toBoolean("false", "true", "false"));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("other", "true", "false"));
        }

    @Test
        public void testToBooleanDefaultIfNull_2() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, true));
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, false));
        }

    @Test
        public void testToBooleanObjectInt_2() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1, 1, 0, 2));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0, 1, 0, 2));
            assertNull(BooleanUtils.toBooleanObject(2, 1, 0, 2));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(3, 1, 0, 2));
        }

    @Test
        public void testToBooleanObjectString_2() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true", "true", "false", "other"));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false", "true", "false", "other"));
            assertNull(BooleanUtils.toBooleanObject("other", "true", "false", "other"));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("another", "true", "false", "other"));
        }
}
