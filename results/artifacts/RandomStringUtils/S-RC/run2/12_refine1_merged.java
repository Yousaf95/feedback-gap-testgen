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
        public void testRandomNullCharsArrayThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, (char[]) null));
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_2() {
            char[] emptyArray = {};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyArray));
        }

    @Test
        public void testRandomStartEndZeroWithChars() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(3, 0, 0, false, false, chars);
            assertTrue(result.length() == 3 && result.chars().allMatch(c -> c >= 'a' && c <= 'c'));
        }

    @Test
        public void testRandomStartEndZeroWithoutChars() {
            String result = RandomStringUtils.random(3, 0, 0, false, false, null);
            assertTrue(result.length() == 3 && result.chars().allMatch(c -> c >= '!' && c <= '~'));
        }

    @Test
        public void testRandomEndLessThanStartThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 10, 5));
        }

    @Test
        public void testRandomNumericWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, false, true, null, (char[]) null));
        }

    @Test
        public void testRandomLettersWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, true, false, null, (char[]) null));
        }

    @Test
        public void testRandomCountZero_2() {
            assertEquals("", RandomStringUtils.random(0));
        }

    @Test
        public void testRandomNegativeCountThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
        }

    @Test
        public void testRandomAlphabeticWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(5, 65));
        }

    @Test
        public void testRandomAlphanumericWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(5, 48));
        }

    @Test
        public void testRandomAsciiWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(5, 31));
        }

    @Test
        public void testRandomGraphWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(5, 32));
        }

    @Test
        public void testRandomNumericWithInvalidEndThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(5, 47));
        }

    @Test
        public void testRandomPrintWithInvalidEndThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(5, 31));
        }
}
