package org.apache.commons.lang3;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStringUtils_GenTest {

    @Test
    public void testRandomWithCountZero() {
        assertEquals("", RandomStringUtils.random(0));
    }

    @Test
    public void testRandomWithNegativeCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
    }

    @Test
    public void testRandomWithEmptyCharsArrayThrowsException() {
        char[] emptyChars = {};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyChars));
    }

    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(10);
        assertTrue(result.matches("[a-zA-Z]{10}"));
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(10);
        assertTrue(result.matches("[a-zA-Z0-9]{10}"));
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(10);
        assertTrue(result.matches("[\\x20-\\x7F]{10}"));
    }

    
    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(10);
        assertTrue(result.matches("\\d{10}"));
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(10);
        assertTrue(result.matches("[\\x20-\\x7E]{10}"));
    }

    @Test
    public void testRandomWithCustomChars() {
        char[] customChars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(5, customChars);
        assertTrue(result.matches("[abc]{5}"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithCustomChars_2() {
            char[] customChars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(3, customChars);
            assertTrue(result.length() == 3);
            for (char c : result.toCharArray()) {
                assertTrue(c == 'a' || c == 'b' || c == 'c');
            }
        }

    @Test
        public void testRandomWithCustomCharsEmptyArrayThrowsException() {
            char[] customChars = {};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(3, customChars));
        }

    @Test
        public void testRandomWithCustomCharsNullThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(3, (char[]) null));
        }

    @Test
        public void testRandomAlphabeticWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(-1));
        }

    @Test
        public void testRandomAlphanumericWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(-1));
        }

    @Test
        public void testRandomAsciiWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(-1));
        }

    @Test
        public void testRandomNumericWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(-1));
        }

    @Test
        public void testRandomPrintWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(-1));
        }

    @Test
        public void testRandomAlphabeticMinLengthInclusiveMaxLengthExclusive() {
            String result = RandomStringUtils.randomAlphabetic(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        public void testRandomAlphanumericMinLengthInclusiveMaxLengthExclusive() {
            String result = RandomStringUtils.randomAlphanumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        public void testRandomAsciiMinLengthInclusiveMaxLengthExclusive() {
            String result = RandomStringUtils.randomAscii(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        public void testRandomNumericMinLengthInclusiveMaxLengthExclusive() {
            String result = RandomStringUtils.randomNumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        public void testRandomPrintMinLengthInclusiveMaxLengthExclusive() {
            String result = RandomStringUtils.randomPrint(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }
}
