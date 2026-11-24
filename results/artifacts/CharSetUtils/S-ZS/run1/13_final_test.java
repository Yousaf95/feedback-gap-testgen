package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ArrayUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSetUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testContainsAny_withSingleCharSet(String set) {
        assertTrue(CharSetUtils.containsAny("abc", set));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testContainsAny_withMultipleCharSets(String set) {
        assertTrue(CharSetUtils.containsAny("abc", "d", set, "e"));
    }

    @Test
    public void testContainsAny_withEmptyString() {
        assertFalse(CharSetUtils.containsAny("", "a", "b", "c"));
    }

    @Test
    public void testContainsAny_withNullString() {
        assertFalse(CharSetUtils.containsAny(null, "a", "b", "c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testContainsAny_withNoMatchingChars(String set) {
        assertFalse(CharSetUtils.containsAny("abc", set));
    }

    
    
    @Test
    public void testCount_withEmptyString() {
        assertEquals(0, CharSetUtils.count("", "a", "b", "c"));
    }

    @Test
    public void testCount_withNullString() {
        assertEquals(0, CharSetUtils.count(null, "a", "b", "c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testCount_withNoMatchingChars(String set) {
        assertEquals(0, CharSetUtils.count("abc", set));
    }

    
    
    @Test
    public void testDelete_withEmptyString() {
        assertEquals("", CharSetUtils.delete("", "a", "b", "c"));
    }

    @Test
    public void testDelete_withNullString() {
        assertNull(CharSetUtils.delete(null, "a", "b", "c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testDelete_withNoMatchingChars(String set) {
        assertEquals("abc", CharSetUtils.delete("abc", set));
    }

    
    
    @Test
    public void testKeep_withEmptyString() {
        assertEquals("", CharSetUtils.keep("", "a", "b", "c"));
    }

    @Test
    public void testKeep_withNullString() {
        assertNull(CharSetUtils.keep(null, "a", "b", "c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testKeep_withNoMatchingChars(String set) {
        assertEquals("", CharSetUtils.keep("abc", set));
    }

    
    
    @Test
    public void testSqueeze_withEmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "a", "b", "c"));
    }

    @Test
    public void testSqueeze_withNullString() {
        assertNull(CharSetUtils.squeeze(null, "a", "b", "c"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testSqueeze_withNoMatchingChars(String set) {
        assertEquals("aaabbcc", CharSetUtils.squeeze("aaabbcc", set));
    }
}
