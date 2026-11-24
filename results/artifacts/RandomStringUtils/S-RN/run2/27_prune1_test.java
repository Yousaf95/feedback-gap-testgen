package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStringUtils_GenTest {

    @Test
    public void testRandomCountZero() {
        assertEquals("", RandomStringUtils.random(0));
    }

    @Test
    public void testRandomNegativeCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
    }

    @Test
    public void testRandomEmptyCharsArrayThrowsException() {
        char[] emptyChars = {};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyChars));
    }

    
    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(10);
        assertTrue(result.matches("[a-zA-Z]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(10);
        assertTrue(result.matches("[a-zA-Z0-9]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomGraph() {
        String result = RandomStringUtils.randomGraph(10);
        assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(10);
        assertTrue(result.matches("[0-9]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        assertEquals(10, result.length());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithCustomChars() {
            String result = RandomStringUtils.random(5, new char[]{'a', 'b', 'c'});
            assertTrue(result.matches("[abc]{5}"));
        }

    
    
    
    @Test
        public void testRandomWithNonAlphabeticAndNonNumericChars() {
            String result = RandomStringUtils.random(5, new char[]{'!', '@', '#'});
            assertTrue(result.matches("[!@#]{5}"));
        }

    @Test
        public void testRandomWithEmptyCustomCharsThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, new char[]{})
            );
        }
}
