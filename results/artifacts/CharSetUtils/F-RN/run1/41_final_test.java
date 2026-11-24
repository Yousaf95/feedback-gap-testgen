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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_SingleCharacterMatch() {
            assertTrue(CharSetUtils.containsAny("abc", "b"));
        }

    @Test
        public void testContainsAny_MultipleCharactersMatch() {
            assertTrue(CharSetUtils.containsAny("hello world", "helo"));
        }

    @Test
        public void testCount_SingleCharacterMatch() {
            assertEquals(1, CharSetUtils.count("abc", "b"));
        }

    @Test
        public void testCount_MultipleCharactersMatch() {
            assertEquals(3, CharSetUtils.count("hello world", "l"));
        }

    @Test
        public void testDelete_SingleCharacterMatch() {
            assertEquals("ac", CharSetUtils.delete("abc", "b"));
        }

    
    @Test
        public void testKeep_SingleCharacterMatch() {
            assertEquals("b", CharSetUtils.keep("abc", "b"));
        }

    
    @Test
        public void testSqueeze_SingleCharacterRepetition() {
            assertEquals("a", CharSetUtils.squeeze("aa", "a"));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsAny_SingleCharacterNoMatch() {
            assertFalse(CharSetUtils.containsAny("abcdefg", "h"));
        }

    @Test
        public void testCount_SingleCharacterNoMatch() {
            assertEquals(0, CharSetUtils.count("abcdefg", "h"));
        }

    @Test
        public void testDelete_SingleCharacterNoMatch() {
            assertEquals("abcdefg", CharSetUtils.delete("abcdefg", "h"));
        }

    @Test
        public void testKeep_SingleCharacterNoMatch() {
            assertEquals("", CharSetUtils.keep("abcdefg", "h"));
        }

    @Test
        public void testSqueeze_NoRepetition() {
            assertEquals("abcde", CharSetUtils.squeeze("abcde", "f"));
        }

    @Test
        public void testContainsAny_MultipleCharactersNoMatch() {
            assertFalse(CharSetUtils.containsAny("abcdefg", "h", "i", "j"));
        }

    @Test
        public void testCount_MultipleCharactersNoMatch() {
            assertEquals(0, CharSetUtils.count("abcdefg", "h", "i", "j"));
        }

    @Test
        public void testDelete_MultipleCharactersNoMatch() {
            assertEquals("abcdefg", CharSetUtils.delete("abcdefg", "h", "i", "j"));
        }

    @Test
        public void testKeep_MultipleCharactersNoMatch() {
            assertEquals("", CharSetUtils.keep("abcdefg", "h", "i", "j"));
        }

    @Test
        public void testSqueeze_NoRepetitionMultipleSets() {
            assertEquals("abcde", CharSetUtils.squeeze("abcde", "f", "g"));
        }
}
