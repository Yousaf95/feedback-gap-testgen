package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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
        public void testContainsAny_singleCharacterMatch() {
            assertTrue(CharSetUtils.containsAny("abc", "b"));
        }

    @Test
        public void testContainsAny_multipleCharactersMatch() {
            assertTrue(CharSetUtils.containsAny("hello world", "lho"));
        }

    @Test
        public void testContainsAny_noMatch() {
            assertFalse(CharSetUtils.containsAny("abcdefg", "xyz"));
        }

    @Test
        public void testCount_singleCharacterMatch() {
            assertEquals(1, CharSetUtils.count("abc", "b"));
        }

    
    @Test
        public void testCount_noMatch() {
            assertEquals(0, CharSetUtils.count("abcdefg", "xyz"));
        }

    @Test
        public void testDelete_singleCharacterMatch() {
            assertEquals("ac", CharSetUtils.delete("abc", "b"));
        }

    
    @Test
        public void testDelete_noMatch() {
            assertEquals("abcdefg", CharSetUtils.delete("abcdefg", "xyz"));
        }

    @Test
        public void testKeep_singleCharacterMatch() {
            assertEquals("b", CharSetUtils.keep("abc", "b"));
        }

    
    @Test
        public void testKeep_noMatch() {
            assertEquals("", CharSetUtils.keep("abcdefg", "xyz"));
        }

    @Test
        public void testSqueeze_singleCharacterMatch() {
            assertEquals("a", CharSetUtils.squeeze("aa", "a"));
        }

    
    @Test
        public void testSqueeze_noMatch() {
            assertEquals("abcdefg", CharSetUtils.squeeze("abcdefg", "xyz"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_singleCharacterNoMatch() {
            assertFalse(CharSetUtils.containsAny("hello", "w"));
        }

    @Test
        public void testCount_multipleCharactersMatch() {
            assertEquals(3, CharSetUtils.count("hello", "l", "o"));
        }

    @Test
        public void testDelete_multipleCharacters() {
            assertEquals("heo", CharSetUtils.delete("hello", "l", "w"));
        }

    @Test
        public void testKeep_multipleCharacters() {
            assertEquals("helo", CharSetUtils.keep("hello", "l", "o"));
        }

    @Test
        public void testSqueeze_multipleCharacters() {
            assertEquals("heo", CharSetUtils.squeeze("hellooo", "o"));
        }

    @Test
        public void testContainsAny_emptySetWithMatch() {
            assertFalse(CharSetUtils.containsAny("hello", ""));
        }

    @Test
        public void testCount_emptySetWithMatch() {
            assertEquals(0, CharSetUtils.count("hello", ""));
        }

    @Test
        public void testDelete_emptySetWithMatch() {
            assertEquals("hello", CharSetUtils.delete("hello", ""));
        }

    @Test
        public void testKeep_emptySetWithMatch() {
            assertEquals("", CharSetUtils.keep("hello", ""));
        }

    @Test
        public void testSqueeze_emptySetWithMatch() {
            assertEquals("hello", CharSetUtils.squeeze("hellooo", ""));
        }

    @Test
        public void testContainsAny_nullSetWithMatch() {
            assertFalse(CharSetUtils.containsAny("hello", (String[]) null));
        }

    @Test
        public void testCount_nullSetWithMatch() {
            assertEquals(0, CharSetUtils.count("hello", (String[]) null));
        }

    @Test
        public void testDelete_nullSetWithMatch() {
            assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
        }

    @Test
        public void testKeep_nullSetWithMatch() {
            assertEquals("", CharSetUtils.keep("hello", (String[]) null));
        }

    @Test
        public void testSqueeze_nullSetWithMatch() {
            assertEquals("hello", CharSetUtils.squeeze("hellooo", (String[]) null));
        }
}
