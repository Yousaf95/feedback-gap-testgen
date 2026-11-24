package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;

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
    public void testKeep_EmptyString() {
        assertEquals("", CharSetUtils.keep("", "aeiou"));
    }

    @Test
    public void testKeep_NullSet() {
        assertNull(CharSetUtils.keep(null, (String[]) null));
    }

    
    @Test
    public void testSqueeze_EmptyString() {
        assertEquals("", CharSetUtils.squeeze("", "aeiou"));
    }

    @Test
    public void testSqueeze_NullSet() {
        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_SingleCharacter() {
            assertTrue(CharSetUtils.containsAny("abc", "b"));
            assertFalse(CharSetUtils.containsAny("abc", "d"));
        }

    @Test
        public void testCount_SingleCharacter() {
            assertEquals(1, CharSetUtils.count("abc", "b"));
            assertEquals(0, CharSetUtils.count("abc", "d"));
        }

    @Test
        public void testDelete_SingleCharacter() {
            assertEquals("ac", CharSetUtils.delete("abc", "b"));
            assertEquals("abc", CharSetUtils.delete("abc", "d"));
        }

    @Test
        public void testKeep_SingleCharacter() {
            assertEquals("b", CharSetUtils.keep("abc", "b"));
            assertEquals("", CharSetUtils.keep("abc", "d"));
        }

    
    @Test
        public void testContainsAny_MultipleCharacters() {
            assertTrue(CharSetUtils.containsAny("abc", "b", "c"));
            assertFalse(CharSetUtils.containsAny("abc", "d", "e"));
        }

    @Test
        public void testCount_MultipleCharacters() {
            assertEquals(2, CharSetUtils.count("abc", "a", "c"));
            assertEquals(0, CharSetUtils.count("abc", "d", "e"));
        }

    @Test
        public void testDelete_MultipleCharacters() {
            assertEquals("ac", CharSetUtils.delete("abc", "b", "d"));
            assertEquals("abc", CharSetUtils.delete("abc", "d", "e"));
        }

    @Test
        public void testKeep_MultipleCharacters() {
            assertEquals("bc", CharSetUtils.keep("abc", "b", "c"));
            assertEquals("", CharSetUtils.keep("abc", "d", "e"));
        }

    
    
    
    
    
    @Test
        public void testSqueeze_SpecialCharacters() {
            assertEquals("a!@#b", CharSetUtils.squeeze("a!!@@##b", "!@#"));
            assertEquals("abc!@#", CharSetUtils.squeeze("abc!@#", "#$%"));
        }

    @Test
        public void testContainsAny_MixedCase() {
            assertTrue(CharSetUtils.containsAny("HelloWorld", "hW"));
            assertFalse(CharSetUtils.containsAny("HelloWorld", "xyz"));
        }

    
    
    
    @Test
        public void testSqueeze_MixedCase() {
            assertEquals("e!@#o", CharSetUtils.squeeze("e!!@@##o", "!@#"));
            assertEquals("HelloWorld", CharSetUtils.squeeze("HelloWorld", "xyz"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_EmptySet() {
            assertFalse(CharSetUtils.containsAny("abc", new String[]{}));
        }

    @Test
        public void testCount_EmptySet() {
            assertEquals(0, CharSetUtils.count("abc", new String[]{}));
        }

    @Test
        public void testDelete_EmptySet() {
            assertEquals("abc", CharSetUtils.delete("abc", new String[]{}));
        }

    @Test
        public void testKeep_EmptySet() {
            assertEquals("", CharSetUtils.keep("abc", new String[]{}));
        }

    @Test
        public void testSqueeze_EmptySet() {
            assertEquals("abc", CharSetUtils.squeeze("abc", new String[]{}));
        }

    @Test
        public void testContainsAny_MultipleSets() {
            assertTrue(CharSetUtils.containsAny("abc", "a", "b"));
        }

    @Test
        public void testCount_MultipleSets() {
            assertEquals(2, CharSetUtils.count("abc", "a", "b"));
        }

    @Test
        public void testDelete_MultipleSets() {
            assertEquals("c", CharSetUtils.delete("abc", "a", "b"));
        }

    @Test
        public void testKeep_MultipleSets() {
            assertEquals("ab", CharSetUtils.keep("abc", "a", "b"));
        }

    @Test
        public void testSqueeze_MultipleSets() {
            assertEquals("abc", CharSetUtils.squeeze("aaabbcc", "a", "b", "c"));
        }

    @Test
        public void testContainsAny_WithWhitespace() {
            assertTrue(CharSetUtils.containsAny("hello world", "h", "w"));
        }

    @Test
        public void testCount_WithWhitespace() {
            assertEquals(2, CharSetUtils.count("hello world", "h", "w"));
        }

    
    
    @Test
        public void testSqueeze_WithWhitespace() {
            assertEquals("he lo w", CharSetUtils.squeeze("he  lo   w", " ", "e", "o"));
        }
}
