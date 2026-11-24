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
        public void testContainsAny_singleCharacter() {
            assertTrue(CharSetUtils.containsAny("a", "a"));
            assertFalse(CharSetUtils.containsAny("b", "a"));
        }

    @Test
        public void testCount_singleCharacter() {
            assertEquals(1, CharSetUtils.count("a", "a"));
            assertEquals(0, CharSetUtils.count("b", "a"));
        }

    @Test
        public void testDelete_singleCharacter() {
            assertEquals("", CharSetUtils.delete("a", "a"));
            assertEquals("b", CharSetUtils.delete("ab", "a"));
        }

    @Test
        public void testKeep_singleCharacter() {
            assertEquals("a", CharSetUtils.keep("a", "a"));
            assertEquals("", CharSetUtils.keep("ab", "b"));
        }

    @Test
        public void testSqueeze_singleCharacter() {
            assertEquals("a", CharSetUtils.squeeze("aa", "a"));
            assertEquals("ab", CharSetUtils.squeeze("aab", "a"));
        }

    @Test
        public void testContainsAny_multipleCharacters() {
            assertTrue(CharSetUtils.containsAny("abc", "b,c,d"));
            assertFalse(CharSetUtils.containsAny("xyz", "a,b,c"));
        }

    @Test
        public void testCount_multipleCharacters() {
            assertEquals(2, CharSetUtils.count("abc", "b,c,d"));
            assertEquals(0, CharSetUtils.count("xyz", "a,b,c"));
        }

    @Test
        public void testDelete_multipleCharacters() {
            assertEquals("", CharSetUtils.delete("abc", "b,c,d"));
            assertEquals("x", CharSetUtils.delete("abxc", "a,b,c"));
        }

    @Test
        public void testKeep_multipleCharacters() {
            assertEquals("bc", CharSetUtils.keep("abc", "b,c,d"));
            assertEquals("xyz", CharSetUtils.keep("xyz", "a,b,c"));
        }

    @Test
        public void testSqueeze_multipleCharacters() {
            assertEquals("ab", CharSetUtils.squeeze("aabbbcc", "b,c"));
            assertEquals("x", CharSetUtils.squeeze("xaaxbxc", "a,b,c"));
        }

    @Test
        public void testContainsAny_caseInsensitive() {
            assertTrue(CharSetUtils.containsAny("Abc", "a,B,C"));
            assertFalse(CharSetUtils.containsAny("xyz", "A,B,C"));
        }

    @Test
        public void testCount_caseInsensitive() {
            assertEquals(2, CharSetUtils.count("Abc", "a,B,C"));
            assertEquals(0, CharSetUtils.count("xyz", "A,B,C"));
        }

    @Test
        public void testDelete_caseInsensitive() {
            assertEquals("", CharSetUtils.delete("Abc", "a,B,C"));
            assertEquals("x", CharSetUtils.delete("abxc", "A,B,C"));
        }

    @Test
        public void testKeep_caseInsensitive() {
            assertEquals("bc", CharSetUtils.keep("Abc", "b,c,D"));
            assertEquals("xyz", CharSetUtils.keep("xyz", "A,B,C"));
        }

    @Test
        public void testSqueeze_caseInsensitive() {
            assertEquals("ab", CharSetUtils.squeeze("aabbbcc", "B,C"));
            assertEquals("x", CharSetUtils.squeeze("xaaxbxc", "A,B,C"));
        }
}
