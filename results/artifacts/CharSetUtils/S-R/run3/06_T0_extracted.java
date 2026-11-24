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

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testContainsAny_emptyOrNullCharSet(String charSet) {
        assertFalse(CharSetUtils.containsAny("abc", charSet));
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

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testCount_emptyOrNullCharSet(String charSet) {
        assertEquals(0, CharSetUtils.count("abcde", charSet));
    }

    @Test
    public void testDelete_normal() {
        assertEquals("bcd", CharSetUtils.delete("abcde", "a"));
    }

    @Test
    public void testDelete_emptyString() {
        assertEquals("", CharSetUtils.delete("", "a"));
    }

    @Test
    public void testDelete_nullString() {
        assertNull(CharSetUtils.delete(null, "a"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testDelete_emptyOrNullCharSet(String charSet) {
        assertEquals("abcde", CharSetUtils.delete("abcde", charSet));
    }

    @Test
    public void testKeep_normal() {
        assertEquals("ace", CharSetUtils.keep("abcde", "a", "c"));
    }

    @Test
    public void testKeep_emptyString() {
        assertEquals("", CharSetUtils.keep("", "a"));
    }

    @Test
    public void testKeep_nullString() {
        assertNull(CharSetUtils.keep(null, "a"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testKeep_emptyOrNullCharSet(String charSet) {
        assertEquals("", CharSetUtils.keep("abcde", charSet));
    }

    @Test
    public void testSqueeze_normal() {
        assertEquals("abce", CharSetUtils.squeeze("aaabbccddeee"));
    }

    @Test
    public void testSqueeze_emptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a"));
    }

    @Test
    public void testSqueeze_nullString() {
        assertNull(CharSetUtils.squeeze(null, "a"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testSqueeze_emptyOrNullCharSet(String charSet) {
        assertEquals("aaabbccddeee", CharSetUtils.squeeze("aaabbccddeee", charSet));
    }
}
