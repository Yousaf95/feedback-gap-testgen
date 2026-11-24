package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharSetUtils_GenTest {

    @Test
    public void testContainsAny_withNullString() {
        assertFalse(CharSetUtils.containsAny(null, "a-z"));
    }

    @Test
    public void testContainsAny_withEmptyString() {
        assertFalse(CharSetUtils.containsAny("", "a-z"));
    }

    @Test
    public void testContainsAny_withNullSet() {
        assertFalse(CharSetUtils.containsAny("abc", (String[]) null));
    }

    @Test
    public void testContainsAny_withEmptySet() {
        assertFalse(CharSetUtils.containsAny("abc", ""));
    }

    @Test
    public void testContainsAny_withMatchingCharacter() {
        assertTrue(CharSetUtils.containsAny("abc", "a-z"));
    }

    @Test
    public void testContainsAny_withoutMatchingCharacter() {
        assertFalse(CharSetUtils.containsAny("abc", "d-f"));
    }

    @Test
    public void testCount_withNullString() {
        assertEquals(0, CharSetUtils.count(null, "a-z"));
    }

    @Test
    public void testCount_withEmptyString() {
        assertEquals(0, CharSetUtils.count("", "a-z"));
    }

    @Test
    public void testCount_withNullSet() {
        assertEquals(0, CharSetUtils.count("abc", (String[]) null));
    }

    @Test
    public void testCount_withEmptySet() {
        assertEquals(0, CharSetUtils.count("abc", ""));
    }

    @Test
    public void testCount_withMatchingCharacters() {
        assertEquals(3, CharSetUtils.count("abc", "a-z"));
    }

    @Test
    public void testCount_withoutMatchingCharacters() {
        assertEquals(0, CharSetUtils.count("abc", "d-f"));
    }

    @Test
    public void testDelete_withNullString() {
        assertNull(CharSetUtils.delete(null, "a-z"));
    }

    @Test
    public void testDelete_withEmptyString() {
        assertEquals("", CharSetUtils.delete("", "a-z"));
    }

    @Test
    public void testDelete_withNullSet() {
        assertEquals("abc", CharSetUtils.delete("abc", (String[]) null));
    }

    @Test
    public void testDelete_withEmptySet() {
        assertEquals("abc", CharSetUtils.delete("abc", ""));
    }

    @Test
    public void testDelete_withMatchingCharacters() {
        assertEquals("", CharSetUtils.delete("abc", "a-z"));
    }

    @Test
    public void testDelete_withoutMatchingCharacters() {
        assertEquals("abc", CharSetUtils.delete("abc", "d-f"));
    }

    @Test
    public void testKeep_withNullString() {
        assertNull(CharSetUtils.keep(null, "a-z"));
    }

    @Test
    public void testKeep_withEmptyString() {
        assertEquals("", CharSetUtils.keep("", "a-z"));
    }

    @Test
    public void testKeep_withNullSet() {
        assertEquals("", CharSetUtils.keep("abc", (String[]) null));
    }

    @Test
    public void testKeep_withEmptySet() {
        assertEquals("", CharSetUtils.keep("abc", ""));
    }

    @Test
    public void testKeep_withMatchingCharacters() {
        assertEquals("abc", CharSetUtils.keep("abc", "a-z"));
    }

    @Test
    public void testKeep_withoutMatchingCharacters() {
        assertEquals("", CharSetUtils.keep("abc", "d-f"));
    }

    @Test
    public void testSqueeze_withNullString() {
        assertNull(CharSetUtils.squeeze(null, "a-z"));
    }

    @Test
    public void testSqueeze_withEmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a-z"));
    }

    @Test
    public void testSqueeze_withNullSet() {
        assertEquals("aaabbb", CharSetUtils.squeeze("aaabbb", (String[]) null));
    }

    @Test
    public void testSqueeze_withEmptySet() {
        assertEquals("aaabbb", CharSetUtils.squeeze("aaabbb", ""));
    }

    @Test
    public void testSqueeze_withMatchingCharacters() {
        assertEquals("abc", CharSetUtils.squeeze("aabbcc", "a-z"));
    }

    @Test
    public void testSqueeze_withoutMatchingCharacters() {
        assertEquals("aaabbb", CharSetUtils.squeeze("aaabbb", "d-f"));
    }
}
