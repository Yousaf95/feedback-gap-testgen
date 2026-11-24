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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithLargeCount() {
            String result = RandomStringUtils.random(10000);
            assertEquals(10000, result.length());
        }

    @Test
        public void testRandomWithSmallCount() {
            String result = RandomStringUtils.random(5);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithCustomRange() {
            String result = RandomStringUtils.random(10, 'A', 'Z');
            assertTrue(result.chars().allMatch(c -> c >= 65 && c <= 90));
        }

    
    
    @Test
        public void testRandomWithNonAlphabeticChars() {
            String result = RandomStringUtils.random(10, new char[] {'!', '@', '#'});
            assertTrue(result.chars().allMatch(c -> c == '!' || c == '@' || c == '#'));
        }

    @Test
        public void testRandomWithMixedCaseLetters() {
            String result = RandomStringUtils.randomAlphabetic(10);
            assertTrue(result.chars().anyMatch(Character::isUpperCase));
            assertTrue(result.chars().anyMatch(Character::isLowerCase));
        }

    
    @Test
        public void testRandomAsciiWithValidRange() {
            String result = RandomStringUtils.randomAscii(10);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
        }

    @Test
        public void testRandomGraphWithValidRange() {
            String result = RandomStringUtils.randomGraph(10);
            assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
        }

    @Test
        public void testRandomNumericWithDigitsOnly() {
            String result = RandomStringUtils.randomNumeric(10);
            assertTrue(result.chars().allMatch(Character::isDigit));
        }

    @Test
        public void testRandomPrintWithValidRange() {
            String result = RandomStringUtils.randomPrint(10);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithCustomRangeAndChars() {
            String result = RandomStringUtils.random(10, 65, 90, false, false, "ABCD".toCharArray());
            assertTrue(result.matches("[ABCD]{10}"));
        }

    @Test
        public void testRandomWithLargeCountAndNonAlphabeticChars() {
            String result = RandomStringUtils.random(1000, '!', '~');
            assertTrue(result.chars().allMatch(c -> c >= '!' && c <= '~'));
        }

    @Test
        public void testRandomWithSmallCountAndMixedCaseLetters() {
            String result = RandomStringUtils.random(5, true, false);
            assertTrue(result.matches("[a-zA-Z]{5}"));
        }

    @Test
        public void testRandomWithNonAlphabeticCharsAndCustomRange() {
            String result = RandomStringUtils.random(10, 32, 47, false, false);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 47));
        }

    @Test
        public void testRandomWithNonAlphanumericCharsAndCustomRange() {
            String result = RandomStringUtils.random(10, 58, 64, false, false);
            assertTrue(result.chars().allMatch(c -> c >= 58 && c <= 64));
        }

    @Test
        public void testRandomWithNonPrintableCharsAndCustomRange() {
            String result = RandomStringUtils.random(10, 0, 31, false, false);
            assertTrue(result.chars().allMatch(c -> c >= 0 && c <= 31));
        }

    @Test
        public void testRandomWithLargeCountAndNonPrintableChars() {
            String result = RandomStringUtils.random(1000, 0, 7);
            assertTrue(result.chars().allMatch(c -> c >= 0 && c <= 7));
        }

    @Test
        public void testRandomWithSmallCountAndCustomRange() {
            String result = RandomStringUtils.random(5, 48, 57);
            assertTrue(result.matches("[0-9]{5}"));
        }

    @Test
        public void testRandomWithNonAlphabeticCharsAndLargeRange() {
            String result = RandomStringUtils.random(10, '!', '~');
            assertTrue(result.chars().allMatch(c -> c >= '!' && c <= '~'));
        }

    @Test
        public void testRandomWithCustomRangeAndNonAlphabeticChars() {
            String result = RandomStringUtils.random(10, 32, 47);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 47));
        }
}
