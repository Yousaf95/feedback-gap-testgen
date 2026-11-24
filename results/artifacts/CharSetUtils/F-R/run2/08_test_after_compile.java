package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CharSetUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testContainsAny_Normal(String charSet) {
        assertTrue(CharSetUtils.containsAny("abc", charSet));
    }

    @Test
    public void testContainsAny_EmptyString() {
        assertFalse(CharSetUtils.containsAny("", "a"));
    }

    @Test
    public void testContainsAny_NullString() {
        assertFalse(CharSetUtils.containsAny(null, "a"));
    }

    @Test
    public void testContainsAny_EmptyCharSet() {
        assertFalse(CharSetUtils.containsAny("abc", ""));
    }

    @Test
    public void testContainsAny_NullCharSet() {
        assertFalse(CharSetUtils.containsAny("abc", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testCount_Normal(String charSet) {
        assertEquals(1, CharSetUtils.count("abc", charSet));
    }

    @Test
    public void testCount_EmptyString() {
        assertEquals(0, CharSetUtils.count("", "a"));
    }

    @Test
    public void testCount_NullString() {
        assertEquals(0, CharSetUtils.count(null, "a"));
    }

    @Test
    public void testCount_EmptyCharSet() {
        assertEquals(0, CharSetUtils.count("abc", ""));
    }

    @Test
    public void testCount_NullCharSet() {
        assertEquals(0, CharSetUtils.count("abc", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testDelete_Normal(String charSet) {
        assertEquals("bc", CharSetUtils.delete("abc", charSet));
    }

    @Test
    public void testDelete_EmptyString() {
        assertEquals("", CharSetUtils.delete("", "a"));
    }

    @Test
    public void testDelete_NullString() {
        assertNull(CharSetUtils.delete(null, "a"));
    }

    @Test
    public void testDelete_EmptyCharSet() {
        assertEquals("abc", CharSetUtils.delete("abc", ""));
    }

    @Test
    public void testDelete_NullCharSet() {
        assertEquals("abc", CharSetUtils.delete("abc", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testKeep_Normal(String charSet) {
        assertEquals("a", CharSetUtils.keep("abc", charSet));
    }

    @Test
    public void testKeep_EmptyString() {
        assertEquals("", CharSetUtils.keep("", "a"));
    }

    @Test
    public void testKeep_NullString() {
        assertNull(CharSetUtils.keep(null, "a"));
    }

    @Test
    public void testKeep_EmptyCharSet() {
        assertEquals("abc", CharSetUtils.keep("abc", ""));
    }

    @Test
    public void testKeep_NullCharSet() {
        assertEquals("abc", CharSetUtils.keep("abc", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testSqueeze_Normal(String charSet) {
        assertEquals("abc", CharSetUtils.squeeze("aaabbbccc", charSet));
    }

    @Test
    public void testSqueeze_EmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a"));
    }

    @Test
    public void testSqueeze_NullString() {
        assertNull(CharSetUtils.squeeze(null, "a"));
    }

    @Test
    public void testSqueeze_EmptyCharSet() {
        assertEquals("aaabbbccc", CharSetUtils.squeeze("aaabbbccc", ""));
    }

    @Test
    public void testSqueeze_NullCharSet() {
        assertEquals("aaabbbccc", CharSetUtils.squeeze("aaabbbccc", null));
    }
}
