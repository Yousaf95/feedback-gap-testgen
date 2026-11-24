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

    
    @Test
    public void testKeep_EmptyString() {
        assertEquals("", CharSetUtils.keep("", "a"));
    }

    @Test
    public void testKeep_NullString() {
        assertNull(CharSetUtils.keep(null, "a"));
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_SingleCharSet() {
            assertTrue(CharSetUtils.containsAny("abc", "a"));
            assertFalse(CharSetUtils.containsAny("abc", "d"));
        }

    @Test
        public void testCount_SingleCharSet() {
            assertEquals(1, CharSetUtils.count("abc", "a"));
            assertEquals(0, CharSetUtils.count("abc", "d"));
        }

    @Test
        public void testDelete_SingleCharSet() {
            assertEquals("bc", CharSetUtils.delete("abc", "a"));
            assertEquals("abc", CharSetUtils.delete("abc", "d"));
        }

    @Test
        public void testKeep_SingleCharSet() {
            assertEquals("a", CharSetUtils.keep("abc", "a"));
            assertEquals("", CharSetUtils.keep("abc", "d"));
        }

    
    @Test
        public void testContainsAny_MultipleCharSets() {
            assertTrue(CharSetUtils.containsAny("abc", "a", "b"));
            assertFalse(CharSetUtils.containsAny("abc", "d", "e"));
        }

    @Test
        public void testCount_MultipleCharSets() {
            assertEquals(2, CharSetUtils.count("abc", "a", "b"));
            assertEquals(0, CharSetUtils.count("abc", "d", "e"));
        }

    @Test
        public void testDelete_MultipleCharSets() {
            assertEquals("c", CharSetUtils.delete("abc", "a", "b"));
            assertEquals("abc", CharSetUtils.delete("abc", "d", "e"));
        }

    @Test
        public void testKeep_MultipleCharSets() {
            assertEquals("ab", CharSetUtils.keep("abc", "a", "b"));
            assertEquals("", CharSetUtils.keep("abc", "d", "e"));
        }

    
    @Test
        public void testContainsAny_OverlapCharSets() {
            assertTrue(CharSetUtils.containsAny("abc", "a", "b", "c"));
            assertFalse(CharSetUtils.containsAny("abc", "d", "e", "f"));
        }

    @Test
        public void testCount_OverlapCharSets() {
            assertEquals(3, CharSetUtils.count("abc", "a", "b", "c"));
            assertEquals(0, CharSetUtils.count("abc", "d", "e", "f"));
        }

    @Test
        public void testDelete_OverlapCharSets() {
            assertEquals("", CharSetUtils.delete("abc", "a", "b", "c"));
            assertEquals("abc", CharSetUtils.delete("abc", "d", "e", "f"));
        }

    @Test
        public void testKeep_OverlapCharSets() {
            assertEquals("abc", CharSetUtils.keep("abc", "a", "b", "c"));
            assertEquals("", CharSetUtils.keep("abc", "d", "e", "f"));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_SpecialCharacters() {
            assertTrue(CharSetUtils.containsAny("Hello, World!", "!@#$%^&*()"));
            assertFalse(CharSetUtils.containsAny("Hello, World!", "abcdefghijklmnopqrstuvwxyz"));
        }

    @Test
        public void testCount_SpecialCharacters() {
            assertEquals(2, CharSetUtils.count("Hello, World!", "!@#$%^&*()"));
            assertEquals(0, CharSetUtils.count("Hello, World!", "abcdefghijklmnopqrstuvwxyz"));
        }

    @Test
        public void testDelete_SpecialCharacters() {
            assertEquals("Hello, World", CharSetUtils.delete("Hello, World!", "!@#$%^&*()"));
            assertEquals("Hello, World!", CharSetUtils.delete("Hello, World!", "abcdefghijklmnopqrstuvwxyz"));
        }

    @Test
        public void testKeep_SpecialCharacters() {
            assertEquals("!@#$%^&*()", CharSetUtils.keep("Hello, World!", "!@#$%^&*()"));
            assertEquals("", CharSetUtils.keep("Hello, World!", "abcdefghijklmnopqrstuvwxyz"));
        }

    @Test
        public void testSqueeze_SpecialCharacters() {
            assertEquals("Helo, Wrd!", CharSetUtils.squeeze("Hello, World!", "!@#$%^&*()"));
            assertEquals("Hello, World!", CharSetUtils.squeeze("Hello, World!", "abcdefghijklmnopqrstuvwxyz"));
        }

    @Test
        public void testContainsAny_Whitespace() {
            assertTrue(CharSetUtils.containsAny("Hello, World!", " "));
            assertFalse(CharSetUtils.containsAny("Hello,World!", " "));
        }

    @Test
        public void testCount_Whitespace() {
            assertEquals(1, CharSetUtils.count("Hello, World!", " "));
            assertEquals(0, CharSetUtils.count("Hello,World!", " "));
        }

    @Test
        public void testDelete_Whitespace() {
            assertEquals("Hello,World!", CharSetUtils.delete("Hello, World!", " "));
            assertEquals("Hello, World!", CharSetUtils.delete("Hello,World!", " "));
        }

    @Test
        public void testKeep_Whitespace() {
            assertEquals("", CharSetUtils.keep("Hello, World!", " "));
            assertEquals("Hello, World!", CharSetUtils.keep("Hello,World!", " "));
        }

    @Test
        public void testSqueeze_Whitespace() {
            assertEquals("Helo, Wrd!", CharSetUtils.squeeze("Hello, World!", " "));
            assertEquals("Hello, World!", CharSetUtils.squeeze("Hello,World!", " "));
        }
}
