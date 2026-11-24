package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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

    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_emptySet() {
            assertFalse(CharSetUtils.containsAny("abc", new String[]{}));
        }

    @Test
        public void testCount_emptySet() {
            assertEquals(0, CharSetUtils.count("abc", new String[]{}));
        }

    @Test
        public void testDelete_emptySet() {
            assertEquals("abc", CharSetUtils.delete("abc", new String[]{}));
        }

    @Test
        public void testKeep_emptySet() {
            assertEquals("", CharSetUtils.keep("abc", new String[]{}));
        }

    @Test
        public void testSqueeze_emptySet() {
            assertEquals("abc", CharSetUtils.squeeze("abc", new String[]{}));
        }

    @Test
        public void testContainsAny_singleCharacter_setNotInString() {
            assertFalse(CharSetUtils.containsAny("abc", "d"));
        }

    @Test
        public void testCount_singleCharacter_setNotInString() {
            assertEquals(0, CharSetUtils.count("abc", "d"));
        }

    @Test
        public void testDelete_singleCharacter_setNotInString() {
            assertEquals("abc", CharSetUtils.delete("abc", "d"));
        }

    @Test
        public void testKeep_singleCharacter_setNotInString() {
            assertEquals("", CharSetUtils.keep("abc", "d"));
        }

    @Test
        public void testSqueeze_singleCharacter_setNotInString() {
            assertEquals("abc", CharSetUtils.squeeze("abc", "d"));
        }

    @Test
        public void testContainsAny_multipleCharacters_setNotInString() {
            assertFalse(CharSetUtils.containsAny("abc", "def"));
        }

    @Test
        public void testCount_multipleCharacters_setNotInString() {
            assertEquals(0, CharSetUtils.count("abc", "def"));
        }

    @Test
        public void testDelete_multipleCharacters_setNotInString() {
            assertEquals("abc", CharSetUtils.delete("abc", "def"));
        }

    @Test
        public void testKeep_multipleCharacters_setNotInString() {
            assertEquals("", CharSetUtils.keep("abc", "def"));
        }

    @Test
        public void testSqueeze_multipleCharacters_setNotInString() {
            assertEquals("abc", CharSetUtils.squeeze("abc", "def"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_singleCharacter_setInString() {
            assertTrue(CharSetUtils.containsAny("abc", "a"));
        }

    @Test
        public void testCount_singleCharacter_setInString() {
            assertEquals(1, CharSetUtils.count("abc", "a"));
        }

    @Test
        public void testDelete_singleCharacter_setInString() {
            assertEquals("bc", CharSetUtils.delete("abc", "a"));
        }

    @Test
        public void testKeep_singleCharacter_setInString() {
            assertEquals("a", CharSetUtils.keep("abc", "a"));
        }

    @Test
        public void testSqueeze_singleCharacter_setInString() {
            assertEquals("abc", CharSetUtils.squeeze("aaabbcc", "a"));
        }

    @Test
        public void testContainsAny_multipleCharacters_setInString() {
            assertTrue(CharSetUtils.containsAny("abcde", "ace"));
        }

    @Test
        public void testCount_multipleCharacters_setInString() {
            assertEquals(3, CharSetUtils.count("abcde", "ace"));
        }

    @Test
        public void testDelete_multipleCharacters_setInString() {
            assertEquals("bdf", CharSetUtils.delete("abcdefg", "ace"));
        }

    @Test
        public void testKeep_multipleCharacters_setInString() {
            assertEquals("ace", CharSetUtils.keep("abcdefg", "ace"));
        }

    @Test
        public void testSqueeze_multipleCharacters_setInString() {
            assertEquals("abcde", CharSetUtils.squeeze("aaabbbcccddd", "abcd"));
        }

    @Test
        public void testContainsAny_emptySet_2() {
            assertFalse(CharSetUtils.containsAny("abc", new String[]{}));
        }

    @Test
        public void testCount_emptySet_2() {
            assertEquals(0, CharSetUtils.count("abc", new String[]{}));
        }

    @Test
        public void testDelete_emptySet_2() {
            assertEquals("abc", CharSetUtils.delete("abc", new String[]{}));
        }

    @Test
        public void testKeep_emptySet_2() {
            assertEquals("", CharSetUtils.keep("abc", new String[]{}));
        }

    @Test
        public void testSqueeze_emptySet_2() {
            assertEquals("abc", CharSetUtils.squeeze("abc", new String[]{}));
        }

    @Test
        public void testContainsAny_setNotInString() {
            assertFalse(CharSetUtils.containsAny("abc", "d"));
        }

    @Test
        public void testCount_setNotInString() {
            assertEquals(0, CharSetUtils.count("abc", "d"));
        }

    @Test
        public void testDelete_setNotInString() {
            assertEquals("abc", CharSetUtils.delete("abc", "d"));
        }

    @Test
        public void testKeep_setNotInString() {
            assertEquals("", CharSetUtils.keep("abc", "d"));
        }

    @Test
        public void testSqueeze_setNotInString() {
            assertEquals("abc", CharSetUtils.squeeze("abc", "d"));
        }
}
