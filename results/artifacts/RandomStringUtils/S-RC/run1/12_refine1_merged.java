package org.apache.commons.lang3;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        public void testRandomWithNullCharsArray() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, new char[0]);
            });
        }

    @Test
        public void testRandomWithStartEndZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(5, 0, 0, false, false, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithStartEndZeroAndEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, 0, 0, false, false, new char[0]);
            });
        }

    @Test
        public void testRandomWithNegativeCount() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1);
            });
        }

    @Test
        public void testRandomAlphabeticWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomAlphabetic(0);
            });
        }

    @Test
        public void testRandomAlphanumericWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomAlphanumeric(0);
            });
        }

    @Test
        public void testRandomAsciiWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomAscii(0);
            });
        }

    @Test
        public void testRandomGraphWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomGraph(0);
            });
        }

    @Test
        public void testRandomNumericWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomNumeric(0);
            });
        }

    @Test
        public void testRandomPrintWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomPrint(0);
            });
        }

    @Test
        public void testRandomWithCharsArrayAndEndLessThanZeroDigitAscii() {
            char[] chars = {'0', '1', '2'};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, 0, 48, false, true, chars);
            });
        }

    @Test
        public void testRandomWithCharsArrayAndEndLessThanFirstLetterAscii() {
            char[] chars = {'A', 'B', 'C'};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, 0, 64, true, false, chars);
            });
        }

    @Test
        public void testRandomWithCountZeroAndCharsArray() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(0, chars);
            assertEquals("", result);
        }

    @Test
        public void testRandomWithCountZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(0, (char[]) null);
            assertEquals("", result);
        }

    @Test
        public void testRandomWithCountZeroAndEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(0, new char[0]);
            });
        }
}
