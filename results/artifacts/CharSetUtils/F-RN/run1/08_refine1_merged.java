package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ArrayUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSetUtils_GenTest {

    
    
    
    
    
    @Test
    public void testContainsAny_NullString() {
        assertFalse(containsAny(null, "a", "b"));
    }

    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_EmptyString() {
            assertFalse(CharSetUtils.containsAny("", "a", "b", "c"));
        }

    @Test
        public void testContainsAny_NullSet() {
            assertTrue(CharSetUtils.containsAny("abc", (String[]) null));
        }

    @Test
        public void testContainsAny_EmptySet() {
            assertFalse(CharSetUtils.containsAny("abc", ""));
        }

    @Test
        public void testContainsAny_MixedCase() {
            assertTrue(CharSetUtils.containsAny("HelloWorld", "h", "W"));
        }

    @Test
        public void testContainsAny_NoMatch() {
            assertFalse(CharSetUtils.containsAny("abcdefg", "x", "y", "z"));
        }

    @Test
        public void testCount_NullString() {
            assertEquals(0, CharSetUtils.count(null, "a", "b", "c"));
        }

    @Test
        public void testCount_EmptyString() {
            assertEquals(0, CharSetUtils.count("", "a", "b", "c"));
        }

    @Test
        public void testCount_MixedCase() {
            assertEquals(2, CharSetUtils.count("HelloWorld", "h", "W"));
        }

    @Test
        public void testCount_NoMatch() {
            assertEquals(0, CharSetUtils.count("abcdefg", "x", "y", "z"));
        }

    @Test
        public void testDelete_NullString() {
            assertNull(CharSetUtils.delete(null, "a", "b", "c"));
        }

    @Test
        public void testDelete_EmptyString() {
            assertEquals("", CharSetUtils.delete("", "a", "b", "c"));
        }

    @Test
        public void testDelete_MixedCase() {
            assertEquals("elloWorld", CharSetUtils.delete("HelloWorld", "H", "W"));
        }

    @Test
        public void testDelete_NoMatch() {
            assertEquals("abcdefg", CharSetUtils.delete("abcdefg", "x", "y", "z"));
        }

    @Test
        public void testKeep_NullString() {
            assertNull(CharSetUtils.keep(null, "a", "b", "c"));
        }

    @Test
        public void testKeep_EmptyString() {
            assertEquals("", CharSetUtils.keep("", "a", "b", "c"));
        }

    @Test
        public void testKeep_MixedCase() {
            assertEquals("HelloWorld", CharSetUtils.keep("HelloWorld", "h", "W"));
        }

    @Test
        public void testKeep_NoMatch() {
            assertEquals("", CharSetUtils.keep("abcdefg", "x", "y", "z"));
        }

    @Test
        public void testSqueeze_NullString() {
            assertNull(CharSetUtils.squeeze(null, "a", "b", "c"));
        }

    @Test
        public void testSqueeze_EmptyString() {
            assertEquals("", CharSetUtils.squeeze("", "a", "b", "c"));
        }

    @Test
        public void testSqueeze_MixedCase() {
            assertEquals("HelloWorld", CharSetUtils.squeeze("HelloWorld", "h", "W"));
        }

    @Test
        public void testSqueeze_NoMatch() {
            assertEquals("abcdefg", CharSetUtils.squeeze("abcdefg", "x", "y", "z"));
        }
}
