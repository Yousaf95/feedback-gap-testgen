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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testOneHotWithNull() {
            assertThrows(NullPointerException.class, () -> oneHot((Boolean[]) null));
        }

    
    @Test
        public void testToStringOnOffTrue() {
            assertEquals("on", toStringOnOff(true));
        }

    @Test
        public void testToStringYesNoFalse() {
            assertEquals("no", toStringYesNo(false));
        }

    
    
    
    
    @Test
        public void testToStringTrueFalseNull() {
            assertNull(toStringTrueFalse((Boolean) null));
        }

    @Test
        public void testIsNotTrueWithTrueValue() {
            assertFalse(isNotTrue(Boolean.TRUE));
        }

    @Test
        public void testIsNotFalseWithFalseValue() {
            assertFalse(isNotFalse(Boolean.FALSE));
        }

    @Test
        public void testNegateWithBooleanTrue() {
            assertEquals(Boolean.FALSE, negate(Boolean.TRUE));
        }

    @Test
        public void testCompareWithEqualValues() {
            assertEquals(0, compare(true, true));
        }

    @Test
        public void testToBooleanDefaultIfNullWithFalseValue() {
            assertFalse(toBooleanDefaultIfNull(Boolean.FALSE, true));
        }

    
    @Test
        public void testToBooleanDefaultIfNullWithTrueValue() {
            assertEquals(true, toBooleanDefaultIfNull(null, true));
        }

    
    @Test
        public void testToStringOnOffFalse() {
            assertEquals("off", toStringOnOff(false));
        }

    @Test
        public void testToStringYesNoTrue() {
            assertEquals("yes", toStringYesNo(true));
        }

    
    @Test
        public void testXorWithAllFalseValues() {
            boolean[] array = {false, false, false};
            assertFalse(xor(array));
        }

    @Test
        public void testToIntegerWithTrueValue() {
            assertEquals(1, toInteger(true));
        }

    @Test
        public void testToStringTrueFalseNull_2() {
            assertNull(toStringTrueFalse(null));
        }

    @Test
        public void testIsNotTrueWithFalseValue() {
            assertTrue(isNotTrue(false));
        }

    
    @Test
        public void testXorWithAllTrueValues() {
            boolean[] array = {true, true, true};
            assertTrue(xor(array));
        }
}
