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
    public void testContainsAny_NullSet() {
        assertTrue(CharSetUtils.containsAny("hello", (String[]) null));
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
    public void testKeep_Normal() {
        assertEquals("heo", CharSetUtils.keep("hello", "l"));
        assertEquals("", CharSetUtils.keep("hello", "xyz"));
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
    public void testSqueeze_Normal() {
        assertEquals("heo", CharSetUtils.squeeze("hello", "l"));
        assertEquals("hello", CharSetUtils.squeeze("hello", "xyz"));
    }

    @Test
    public void testSqueeze_EmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "aeiou"));
    }

    @Test
    public void testSqueeze_NullSet() {
        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
    }
}
