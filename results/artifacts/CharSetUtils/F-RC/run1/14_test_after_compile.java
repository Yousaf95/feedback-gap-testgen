package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ArrayUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.apache.commons.lang3.CharSetUtils.*;

public class CharSetUtils_GenTest {

    
    
    
    
    
    @Test
    public void testContainsAny_NullString() {
        assertFalse(containsAny(null, "a", "b"));
    }

    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_EmptyString() {
            assertFalse(containsAny("", "a", "b", "c"));
        }

    @Test
        public void testContainsAny_NullString_2() {
            assertFalse(containsAny(null, "a", "b", "c"));
        }

    @Test
        public void testContainsAny_NoMatch() {
            assertFalse(containsAny("xyz", "d", "e", "f"));
        }

    @Test
        public void testContainsAny_Match() {
            assertTrue(containsAny("xyz", "x", "y", "z"));
        }

    @Test
        public void testCount_EmptyString() {
            assertEquals(0, count("", "a", "b", "c"));
        }

    @Test
        public void testCount_NullString() {
            assertEquals(0, count(null, "a", "b", "c"));
        }

    @Test
        public void testCount_NoMatch() {
            assertEquals(0, count("xyz", "d", "e", "f"));
        }

    @Test
        public void testCount_Match() {
            assertEquals(3, count("xyz", "x", "y", "z"));
        }

    @Test
        public void testDelete_EmptyString() {
            assertEquals("", delete("", "a", "b", "c"));
        }

    @Test
        public void testDelete_NullString() {
            assertNull(delete(null, "a", "b", "c"));
        }

    @Test
        public void testDelete_NoMatch() {
            assertEquals("xyz", delete("xyz", "d", "e", "f"));
        }

    @Test
        public void testDelete_Match() {
            assertEquals("", delete("abc", "a", "b", "c"));
        }

    @Test
        public void testKeep_EmptyString() {
            assertEquals("", keep("", "a", "b", "c"));
        }

    @Test
        public void testKeep_NullString() {
            assertNull(keep(null, "a", "b", "c"));
        }

    @Test
        public void testKeep_NoMatch() {
            assertEquals(StringUtils.EMPTY, keep("xyz", "d", "e", "f"));
        }

    @Test
        public void testKeep_Match() {
            assertEquals("abc", keep("abc", "a", "b", "c"));
        }

    @Test
        public void testSqueeze_EmptyString() {
            assertEquals("", squeeze("", "a", "b", "c"));
        }

    @Test
        public void testSqueeze_NullString() {
            assertNull(squeeze(null, "a", "b", "c"));
        }

    @Test
        public void testSqueeze_NoMatch() {
            assertEquals("xyz", squeeze("xyz", "d", "e", "f"));
        }

    @Test
        public void testSqueeze_Match() {
            assertEquals("xyc", squeeze("xxxyyyzzz", "x", "y", "z"));
        }
}
