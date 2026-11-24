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
            assertEquals(0, compare(true, true));
            assertEquals(-1, compare(false, true));
            assertEquals(1, compare(true, false));
        }

    @Test
        public void testIsFalse() {
            assertTrue(isFalse(Boolean.FALSE));
            assertFalse(isFalse(Boolean.TRUE));
            assertFalse(isFalse(null));
        }

    @Test
        public void testIsNotFalse() {
            assertFalse(isNotFalse(Boolean.FALSE));
            assertTrue(isNotFalse(Boolean.TRUE));
            assertTrue(isNotFalse(null));
        }

    @Test
        public void testIsNotTrue() {
            assertTrue(isNotTrue(Boolean.FALSE));
            assertFalse(isNotTrue(Boolean.TRUE));
            assertTrue(isNotTrue(null));
        }

    @Test
        public void testIsTrue() {
            assertFalse(isTrue(Boolean.FALSE));
            assertTrue(isTrue(Boolean.TRUE));
            assertFalse(isTrue(null));
        }

    @Test
        public void testNegate_2() {
            assertNull(negate(null));
            assertEquals(Boolean.FALSE, negate(Boolean.TRUE));
            assertEquals(Boolean.TRUE, negate(Boolean.FALSE));
        }

    
    
    @Test
        public void testOrBooleanArray() {
            assertTrue(or(new boolean[] {true, false}));
            assertTrue(or(new boolean[] {false, true}));
            assertTrue(or(new boolean[] {true, true}));
            assertFalse(or(new boolean[] {false, false}));
        }

    @Test
        public void testOrBooleanObjectArray() {
            assertTrue(or(new Boolean[] {Boolean.TRUE, Boolean.FALSE}));
            assertTrue(or(new Boolean[] {Boolean.FALSE, Boolean.TRUE}));
            assertTrue(or(new Boolean[] {Boolean.TRUE, Boolean.TRUE}));
            assertFalse(or(new Boolean[] {Boolean.FALSE, Boolean.FALSE}));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testOneHot_booleanArray_true() {
            assertTrue(BooleanUtils.oneHot(new boolean[] {true, false}));
        }

    
    @Test
        public void testOneHot_booleanArray_allFalse() {
            assertFalse(BooleanUtils.oneHot(new boolean[] {false, false}));
        }

    
    @Test
        public void testToBoolean_trueString() {
            assertTrue(BooleanUtils.toBoolean("true", "true", "false"));
        }

    @Test
        public void testToBoolean_falseString() {
            assertFalse(BooleanUtils.toBoolean("false", "true", "false"));
        }

    @Test
        public void testToBoolean_nullString() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(null, "true", "false"));
        }

    @Test
        public void testToBooleanObject_trueInteger() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1, 1, 0, -1));
        }

    @Test
        public void testToBooleanObject_falseInteger() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0, 1, 0, -1));
        }

    @Test
        public void testToBooleanObject_nullInteger() {
            assertNull(BooleanUtils.toBooleanObject(-1, 1, 0, -1));
        }

    @Test
        public void testToBooleanDefaultIfNull_true() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        public void testToBooleanDefaultIfNull_false() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testOneHot_booleanArray_true_2() {
            assertTrue(BooleanUtils.oneHot(true, false));
        }

    @Test
        public void testOneHot_booleanArray_allFalse_2() {
            assertFalse(BooleanUtils.oneHot(false, false));
        }

    @Test
        public void testToBoolean_trueString_2() {
            assertTrue(BooleanUtils.toBoolean("true"));
        }

    @Test
        public void testToBoolean_falseString_2() {
            assertFalse(BooleanUtils.toBoolean("false"));
        }

    @Test
        public void testToBoolean_nullString_2() {
            assertFalse(BooleanUtils.toBoolean(null));
        }

    @Test
        public void testToBooleanObject_trueInteger_2() {
            assertTrue(BooleanUtils.toBooleanObject(1) == Boolean.TRUE);
        }

    @Test
        public void testToBooleanObject_falseInteger_2() {
            assertFalse(BooleanUtils.toBooleanObject(0) == Boolean.FALSE);
        }

    @Test
        public void testToBooleanObject_nullInteger_2() {
            assertNull(BooleanUtils.toBooleanObject(null));
        }

    @Test
        public void testToBoolean_defaultIfNull_true() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        public void testToBoolean_defaultIfNull_false() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
        }

    @Test
        public void testToIntegerObject_true() {
            assertEquals(1, BooleanUtils.toIntegerObject(true).intValue());
        }

    @Test
        public void testToIntegerObject_false() {
            assertEquals(0, BooleanUtils.toIntegerObject(false).intValue());
        }

    @Test
        public void testToIntegerObject_null() {
            assertNull(BooleanUtils.toIntegerObject(null));
        }

    @Test
        public void testToBoolean_trueString_custom() {
            assertTrue(BooleanUtils.toBoolean("yes", "yes", "no"));
        }

    @Test
        public void testToBoolean_falseString_custom() {
            assertFalse(BooleanUtils.toBoolean("no", "yes", "no"));
        }

    @Test
        public void testToBoolean_nullString_custom() {
            assertNull(BooleanUtils.toBoolean(null, "yes", "no"));
        }

    @Test
        public void testToBooleanObject_trueInteger_custom() {
            assertTrue(BooleanUtils.toBooleanObject(1, 1, 0) == Boolean.TRUE);
        }

    @Test
        public void testToBooleanObject_falseInteger_custom() {
            assertFalse(BooleanUtils.toBooleanObject(0, 1, 0) == Boolean.FALSE);
        }

    @Test
        public void testToBooleanObject_nullInteger_custom() {
            assertNull(BooleanUtils.toBooleanObject(null, 1, 0));
        }

    @Test
        public void testToBooleanObject_trueString_custom() {
            assertTrue(BooleanUtils.toBooleanObject("yes", "yes", "no") == Boolean.TRUE);
        }

    @Test
        public void testToBooleanObject_falseString_custom() {
            assertFalse(BooleanUtils.toBooleanObject("no", "yes", "no") == Boolean.FALSE);
        }

    @Test
        public void testToBooleanObject_nullString_custom() {
            assertNull(BooleanUtils.toBooleanObject(null, "yes", "no"));
        }
}
