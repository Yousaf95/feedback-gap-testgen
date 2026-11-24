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
    public void testAndWithNull() {
        assertThrows(NullPointerException.class, () -> and((Boolean[]) null));
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
    public void testXorWithNull() {
        assertThrows(NullPointerException.class, () -> xor((Boolean[]) null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCompare() {
            assertEquals(0, BooleanUtils.compare(true, true));
            assertEquals(-1, BooleanUtils.compare(false, true));
            assertEquals(1, BooleanUtils.compare(true, false));
        }

    @Test
        public void testForEach() {
            int count = 0;
            BooleanUtils.forEach(b -> count++);
            assertEquals(2, count);
        }

    @Test
        public void testIsFalseWithNull() {
            assertFalse(BooleanUtils.isFalse(null));
        }

    @Test
        public void testIsNotTrueWithNull() {
            assertTrue(BooleanUtils.isNotTrue(null));
        }

    @Test
        public void testNegateWithNull() {
            assertNull(BooleanUtils.negate(null));
        }

    
    @Test
        public void testOrWithNull() {
            assertTrue(BooleanUtils.or(null, true));
        }

    @Test
        public void testToBooleanDefaultIfNullWithNull() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        public void testToIntegerObjectWithNull() {
            assertNull(BooleanUtils.toIntegerObject(null));
        }

    @Test
        public void testToStringOnOffWithNull() {
            assertNull(BooleanUtils.toStringOnOff(null));
        }

    }
