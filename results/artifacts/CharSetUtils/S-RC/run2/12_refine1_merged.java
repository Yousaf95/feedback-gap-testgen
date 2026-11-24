package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharSetUtils_GenTest {

    @Test
    public void testContainsAny_Normal() {
        assertTrue(CharSetUtils.containsAny("hello", "aeiou"));
        assertFalse(CharSetUtils.containsAny("hello", "xyz"));
    }

    @Test
    public void testContainsAny_EmptyString() {
        assertFalse(CharSetUtils.containsAny("", "aeiou"));
    }

    
    @Test
    public void testCount_Normal() {
        assertEquals(2, CharSetUtils.count("hello", "l"));
        assertEquals(0, CharSetUtils.count("hello", "xyz"));
    }

    @Test
    public void testCount_EmptyString() {
        assertEquals(0, CharSetUtils.count("", "aeiou"));
    }

    @Test
    public void testCount_NullSet() {
        assertEquals(0, CharSetUtils.count("hello", (String[]) null));
    }

    @Test
    public void testDelete_Normal() {
        assertEquals("heo", CharSetUtils.delete("hello", "l"));
        assertEquals("hello", CharSetUtils.delete("hello", "xyz"));
    }

    @Test
    public void testDelete_EmptyString() {
        assertEquals("", CharSetUtils.delete("", "aeiou"));
    }

    @Test
    public void testDelete_NullSet() {
        assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
    }

    
    @Test
    public void testKeep_EmptyString() {
        assertEquals("", CharSetUtils.keep("", "aeiou"));
    }

    @Test
    public void testKeep_NullSet() {
        assertNull(CharSetUtils.keep(null, (String[]) null));
    }

    
    @Test
    public void testSqueeze_EmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "aeiou"));
    }

    @Test
    public void testSqueeze_NullSet() {
        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_EmptySet() {
            assertFalse(CharSetUtils.containsAny("abc", new String[]{}));
        }

    @Test
        public void testCount_EmptySet() {
            assertEquals(0, CharSetUtils.count("abc", new String[]{}));
        }

    @Test
        public void testDelete_EmptySet() {
            assertEquals("abc", CharSetUtils.delete("abc", new String[]{}));
        }

    @Test
        public void testKeep_EmptySet() {
            assertEquals("", CharSetUtils.keep("abc", new String[]{}));
        }

    @Test
        public void testSqueeze_EmptySet() {
            assertEquals("abc", CharSetUtils.squeeze("abc", new String[]{}));
        }
}
