package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CharSetUtils_GenTest {

    
    @Test
    public void testContainsAny_emptyString() {
        assertFalse(CharSetUtils.containsAny("", "a", "b", "c"));
    }

    @Test
    public void testContainsAny_nullString() {
        assertFalse(CharSetUtils.containsAny(null, "a", "b", "c"));
    }

    @Test
    public void testContainsAny_emptySet() {
        assertFalse(CharSetUtils.containsAny("abc", ""));
    }

    @Test
    public void testContainsAny_nullSet() {
        assertFalse(CharSetUtils.containsAny("abc", null));
    }

    
    @Test
    public void testCount_emptyString() {
        assertEquals(0, CharSetUtils.count("", "a", "b", "c"));
    }

    @Test
    public void testCount_nullString() {
        assertEquals(0, CharSetUtils.count(null, "a", "b", "c"));
    }

    @Test
    public void testCount_emptySet() {
        assertEquals(0, CharSetUtils.count("abc", ""));
    }

    @Test
    public void testCount_nullSet() {
        assertEquals(0, CharSetUtils.count("abc", null));
    }

    
    @Test
    public void testDelete_emptyString() {
        assertEquals("", CharSetUtils.delete("", "a", "b", "c"));
    }

    @Test
    public void testDelete_nullString() {
        assertNull(CharSetUtils.delete(null, "a", "b", "c"));
    }

    @Test
    public void testDelete_emptySet() {
        assertEquals("abc", CharSetUtils.delete("abc", ""));
    }

    @Test
    public void testDelete_nullSet() {
        assertEquals("abc", CharSetUtils.delete("abc", null));
    }

    
    @Test
    public void testKeep_emptyString() {
        assertEquals("", CharSetUtils.keep("", "a", "b", "c"));
    }

    @Test
    public void testKeep_nullString() {
        assertNull(CharSetUtils.keep(null, "a", "b", "c"));
    }

    @Test
    public void testKeep_emptySet() {
        assertEquals("", CharSetUtils.keep("abc", ""));
    }

    @Test
    public void testKeep_nullSet() {
        assertEquals("", CharSetUtils.keep("abc", null));
    }

    
    @Test
    public void testSqueeze_emptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a", "b", "c"));
    }

    @Test
    public void testSqueeze_nullString() {
        assertNull(CharSetUtils.squeeze(null, "a", "b", "c"));
    }

    @Test
    public void testSqueeze_emptySet() {
        assertEquals("aaabbcc", CharSetUtils.squeeze("aaabbcc", ""));
    }

    @Test
    public void testSqueeze_nullSet() {
        assertEquals("aaabbcc", CharSetUtils.squeeze("aaabbcc", null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_nonEmptyString_emptySet() {
            assertFalse(CharSetUtils.containsAny("abc", new String[]{}));
        }

    @Test
        public void testContainsAny_nonEmptyString_nullSet() {
            assertFalse(CharSetUtils.containsAny("abc", (String[]) null));
        }

    @Test
        public void testCount_nonEmptyString_emptySet() {
            assertEquals(0, CharSetUtils.count("abc", new String[]{}));
        }

    @Test
        public void testCount_nonEmptyString_nullSet() {
            assertEquals(0, CharSetUtils.count("abc", (String[]) null));
        }

    @Test
        public void testDelete_nonEmptyString_emptySet() {
            assertEquals("abc", CharSetUtils.delete("abc", new String[]{}));
        }

    @Test
        public void testDelete_nonEmptyString_nullSet() {
            assertEquals("abc", CharSetUtils.delete("abc", (String[]) null));
        }

    @Test
        public void testKeep_nonEmptyString_emptySet() {
            assertEquals("", CharSetUtils.keep("abc", new String[]{}));
        }

    @Test
        public void testKeep_nonEmptyString_nullSet() {
            assertEquals("", CharSetUtils.keep("abc", (String[]) null));
        }

    @Test
        public void testSqueeze_nonEmptyString_emptySet() {
            assertEquals("abc", CharSetUtils.squeeze("abc", new String[]{}));
        }

    @Test
        public void testSqueeze_nonEmptyString_nullSet() {
            assertEquals("abc", CharSetUtils.squeeze("abc", (String[]) null));
        }
}
