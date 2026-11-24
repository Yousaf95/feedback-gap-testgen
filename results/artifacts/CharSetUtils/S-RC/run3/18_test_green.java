package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CharSetUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testContainsAny_normal(String charSet) {
        assertTrue(CharSetUtils.containsAny("abc", charSet));
    }

    @Test
    public void testContainsAny_emptyString() {
        assertFalse(CharSetUtils.containsAny("", "a"));
    }

    @Test
    public void testContainsAny_nullString() {
        assertFalse(CharSetUtils.containsAny(null, "a"));
    }

    
    @Test
    public void testCount_normal() {
        assertEquals(2, CharSetUtils.count("abcde", "a", "c"));
    }

    @Test
    public void testCount_emptyString() {
        assertEquals(0, CharSetUtils.count("", "a"));
    }

    @Test
    public void testCount_nullString() {
        assertEquals(0, CharSetUtils.count(null, "a"));
    }

    
    
    @Test
    public void testDelete_emptyString() {
        assertEquals("", CharSetUtils.delete("", "a"));
    }

    @Test
    public void testDelete_nullString() {
        assertNull(CharSetUtils.delete(null, "a"));
    }

    
    
    @Test
    public void testKeep_emptyString() {
        assertEquals("", CharSetUtils.keep("", "a"));
    }

    @Test
    public void testKeep_nullString() {
        assertNull(CharSetUtils.keep(null, "a"));
    }

    
    
    @Test
    public void testSqueeze_emptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a"));
    }

    @Test
    public void testSqueeze_nullString() {
        assertNull(CharSetUtils.squeeze(null, "a"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_normal_2() {
            assertTrue(CharSetUtils.containsAny("abc", "b"));
        }

    @Test
        public void testContainsAny_emptyString_2() {
            assertFalse(CharSetUtils.containsAny("", "a"));
        }

    @Test
        public void testContainsAny_nullString_2() {
            assertFalse(CharSetUtils.containsAny(null, "a"));
        }

    
    @Test
        public void testCount_emptyString_2() {
            assertEquals(0, CharSetUtils.count("", "a"));
        }

    @Test
        public void testCount_nullString_2() {
            assertEquals(0, CharSetUtils.count(null, "a"));
        }

    @Test
        public void testDelete_emptyString_2() {
            assertEquals("", CharSetUtils.delete(""));
        }

    @Test
        public void testDelete_nullString_2() {
            assertNull(CharSetUtils.delete(null));
        }

    @Test
        public void testKeep_emptyString_2() {
            assertEquals("", CharSetUtils.keep(""));
        }

    @Test
        public void testKeep_nullString_2() {
            assertNull(CharSetUtils.keep(null));
        }

    @Test
        public void testSqueeze_emptyString_2() {
            assertEquals("", CharSetUtils.squeeze(""));
        }

    @Test
        public void testSqueeze_nullString_2() {
            assertNull(CharSetUtils.squeeze(null));
        }
}
