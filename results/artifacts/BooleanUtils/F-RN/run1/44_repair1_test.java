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

    
    
    
    
    
    
    
    
    
    
    @Test
        public void testOneHotWithAllTrue() {
            boolean[] array = {true, true, true};
            assertFalse(oneHot(array));
        }

    @Test
        public void testOneHotWithMixedValues() {
            boolean[] array = {false, true, false};
            assertTrue(oneHot(array));
        }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testOrWithBooleanArray() {
            assertTrue(BooleanUtils.or(true, false, true));
            assertFalse(BooleanUtils.or(false, false, false));
        }

    @Test
        public void testOrWithBooleanObjectArray() {
            assertTrue(BooleanUtils.or(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE));
            assertFalse(BooleanUtils.or(null, null, null));
        }

    @Test
        public void testXorWithBooleanArray() {
            assertTrue(BooleanUtils.xor(true, false, true));
            assertFalse(BooleanUtils.xor(false, false, false));
        }

    @Test
        public void testXorWithBooleanObjectArray() {
            assertTrue(BooleanUtils.xor(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE));
            assertFalse(BooleanUtils.xor(null, null, null));
        }

    @Test
        public void testToIntegerDefaultIfNull() {
            assertEquals(1, BooleanUtils.toIntegerDefaultIfNull(Boolean.TRUE, 0));
            assertEquals(0, BooleanUtils.toIntegerDefaultIfNull(Boolean.FALSE, 1));
            assertEquals(42, BooleanUtils.toIntegerDefaultIfNull(null, 42));
        }

    @Test
        public void testToBooleanObjectWithNull() {
            assertNull(BooleanUtils.toBooleanObject((String) null));
            assertNull(BooleanUtils.toBooleanObject((Integer) null));
        }

    @Test
        public void testToBooleanObjectWithInvalidValues() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("invalid"));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(123));
        }

    @Test
        public void testToIntegerWithNull() {
            assertEquals(0, BooleanUtils.toInteger((Boolean) null));
        }

    @Test
        public void testToStringOnOffWithNull() {
            assertNull(BooleanUtils.toStringOnOff((Boolean) null));
        }

    @Test
        public void testToStringTrueFalseWithNull() {
            assertNull(BooleanUtils.toStringTrueFalse((Boolean) null));
        }

    @Test
        public void testToStringYesNoWithNull() {
            assertNull(BooleanUtils.toStringYesNo((Boolean) null));
        }
}
