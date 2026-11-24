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

    
    
    
    
    
    
    
    @Test
        public void testToBooleanObjectWithNull() {
            assertNull(toBooleanObject((String) null));
            assertNull(toBooleanObject((Integer) null));
        }

    
    
    @Test
        public void testToStringOnOffWithNull() {
            assertNull(toStringOnOff((Boolean) null));
        }

    @Test
        public void testToStringTrueFalseWithNull() {
            assertNull(toStringTrueFalse((Boolean) null));
        }

    @Test
        public void testToStringYesNoWithNull() {
            assertNull(toStringYesNo((Boolean) null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testOrWithBooleanArray() {
            assertTrue(BooleanUtils.or(true, false));
            assertFalse(BooleanUtils.or(false, false));
            assertTrue(BooleanUtils.or(true, true));
        }

    @Test
        public void testOrWithBooleanObjectArray() {
            assertTrue(BooleanUtils.or(Boolean.TRUE, Boolean.FALSE));
            assertFalse(BooleanUtils.or(Boolean.FALSE, Boolean.FALSE));
            assertTrue(BooleanUtils.or(Boolean.TRUE, Boolean.TRUE));
        }

    @Test
        public void testXorWithBooleanArray() {
            assertTrue(BooleanUtils.xor(true, false));
            assertFalse(BooleanUtils.xor(false, false));
            assertTrue(BooleanUtils.xor(true, true));
        }

    @Test
        public void testXorWithBooleanObjectArray() {
            assertTrue(BooleanUtils.xor(Boolean.TRUE, Boolean.FALSE));
            assertFalse(BooleanUtils.xor(Boolean.FALSE, Boolean.FALSE));
            assertTrue(BooleanUtils.xor(Boolean.TRUE, Boolean.TRUE));
        }

    @Test
        public void testToIntegerDefaultIfNull() {
            assertEquals(1, BooleanUtils.toIntegerDefaultIfNull(Boolean.TRUE, 0));
            assertEquals(0, BooleanUtils.toIntegerDefaultIfNull(Boolean.FALSE, 0));
            assertEquals(42, BooleanUtils.toIntegerDefaultIfNull(null, 42));
        }

    @Test
        public void testToBooleanObjectWithNull_2() {
            assertNull(BooleanUtils.toBooleanObject((String) null));
            assertTrue(BooleanUtils.toBooleanObject("true"));
            assertFalse(BooleanUtils.toBooleanObject("false"));
        }

    @Test
        public void testToStringOnOff() {
            assertEquals("on", BooleanUtils.toStringOnOff(true));
            assertEquals("off", BooleanUtils.toStringOnOff(false));
            assertNull(BooleanUtils.toStringOnOff(null));
        }

    @Test
        public void testToStringTrueFalse() {
            assertEquals("true", BooleanUtils.toStringTrueFalse(true));
            assertEquals("false", BooleanUtils.toStringTrueFalse(false));
            assertNull(BooleanUtils.toStringTrueFalse(null));
        }

    @Test
        public void testToStringYesNo() {
            assertEquals("yes", BooleanUtils.toStringYesNo(true));
            assertEquals("no", BooleanUtils.toStringYesNo(false));
            assertNull(BooleanUtils.toStringYesNo(null));
        }

    @Test
        public void testToBooleanObjectWithNullValues() {
            assertTrue(BooleanUtils.toBooleanObject("true", "true", "false", null));
            assertFalse(BooleanUtils.toBooleanObject("false", "true", "false", null));
            assertNull(BooleanUtils.toBooleanObject(null, "true", "false", null));
        }

    @Test
        public void testToBooleanWithNull() {
            assertFalse(BooleanUtils.toBoolean((String) null));
        }

    @Test
        public void testToBooleanIntThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
        }

    @Test
        public void testToBooleanObjectIntThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(2, 1, 0, -1));
        }

    @Test
        public void testToBooleanStringThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("unknown", "true", "false"));
        }

    @Test
        public void testToBooleanObjectStringThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("unknown", "true", "false", null));
        }
}
