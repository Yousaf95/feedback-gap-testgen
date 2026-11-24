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
        public void testAndWithNull_2() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
        }

    @Test
        public void testToStringTrueFalse_2() {
            assertEquals("true", BooleanUtils.toString(true, "true", "false"));
            assertEquals("false", BooleanUtils.toString(false, "true", "false"));
            assertNull(BooleanUtils.toString(null, "true", "false"));
        }

    @Test
        public void testValues_2() {
            List<Boolean> values = BooleanUtils.values();
            assertEquals(2, values.size());
            assertTrue(values.contains(Boolean.FALSE));
            assertTrue(values.contains(Boolean.TRUE));
        }

    @Test
        public void testToBooleanObject_2() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true"));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false"));
            assertNull(BooleanUtils.toBooleanObject(null));
        }

    @Test
        public void testXorWithNull_2() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
        }
}
