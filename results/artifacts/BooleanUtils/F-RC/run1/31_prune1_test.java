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
        public void testOneHotBooleanArray() {
            assertTrue(oneHot(new boolean[] {true, false}));
            assertFalse(oneHot(new boolean[] {false, true}));
            assertFalse(oneHot(new boolean[] {true, true}));
            assertFalse(oneHot(new boolean[] {false, false}));
        }

    @Test
        public void testOneHotBooleanObjectArray() {
            assertTrue(oneHot(new Boolean[] {Boolean.TRUE, Boolean.FALSE}));
            assertFalse(oneHot(new Boolean[] {Boolean.FALSE, Boolean.TRUE}));
            assertFalse(oneHot(new Boolean[] {Boolean.TRUE, Boolean.TRUE}));
            assertFalse(oneHot(new Boolean[] {Boolean.FALSE, Boolean.FALSE}));
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

    }
