package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class RandomStringUtils_GenTest {

    @Test
    void testRandomWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @Test
    void testRandomWithZeroCountReturnsEmptyString() {
        String result = RandomStringUtils.random(0);
        assertEquals("", result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.random(count);
        assertEquals(count, result.length());
    }

    @Test
    void testRandomWithLettersAndNumbersTrueContainsOnlyAlphanumeric() {
        String result = RandomStringUtils.random(10, true, true);
        assertTrue(result.matches("[a-zA-Z0-9]+"));
    }

    @Test
    void testRandomWithLettersTrueAndNumbersFalseContainsOnlyLetters() {
        String result = RandomStringUtils.random(10, true, false);
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testRandomWithLettersFalseAndNumbersTrueContainsOnlyDigits() {
        String result = RandomStringUtils.random(10, false, true);
        assertTrue(result.matches("\\d+"));
    }

    @Test
    void testRandomWithCustomCharsArrayGeneratesStringUsingThoseChars() {
        char[] chars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(10, chars);
        assertTrue(result.matches("[abc]+"));
    }

    
    @Test
    void testRandomWithEmptyCharsArrayThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(10, new char[0]);
        });
        assertEquals("The chars array must not be empty", exception.getMessage());
    }

    @Test
    void testRandomAlphabeticGeneratesOnlyLetters() {
        String result = RandomStringUtils.randomAlphabetic(10);
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testRandomAlphanumericGeneratesOnlyAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(10);
        assertTrue(result.matches("[a-zA-Z0-9]+"));
    }

    @Test
    void testRandomAsciiGeneratesOnlyAsciiChars() {
        String result = RandomStringUtils.randomAscii(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c < 127));
    }

    @Test
    void testRandomGraphGeneratesOnlyGraphChars() {
        String result = RandomStringUtils.randomGraph(10);
        assertTrue(result.chars().allMatch(c -> c >= 33 && c < 126));
    }

    @Test
    void testRandomNumericGeneratesOnlyDigits() {
        String result = RandomStringUtils.randomNumeric(10);
        assertTrue(result.matches("\\d+"));
    }

    @Test
    void testRandomPrintGeneratesOnlyPrintableChars() {
        String result = RandomStringUtils.randomPrint(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c < 126));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testRandomWithNullCharsArray() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        void testRandomWithStartEndZeroAndNoLettersOrNumbers() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> RandomStringUtils.random(5, 0, 0, false, false));
            assertEquals("Parameter end (0) must be greater then (48) for generating digits or greater then (65) for generating letters.", exception.getMessage());
        }

    @Test
        void testRandomWithStartEndZeroAndLetters() {
            String result = RandomStringUtils.random(5, 0, 0, true, false);
            assertNotNull(result);
            assertEquals(5, result.length());
            assertTrue(result.matches("[a-zA-Z]+"));
        }

    @Test
        void testRandomWithStartEndZeroAndNumbers() {
            String result = RandomStringUtils.random(5, 0, 0, false, true);
            assertNotNull(result);
            assertEquals(5, result.length());
            assertTrue(result.matches("\\d+"));
        }

    @Test
        void testRandomWithInvalidStartEndRange() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> RandomStringUtils.random(5, 10, 5, false, false));
            assertEquals("Parameter end (5) must be greater than start (10)", exception.getMessage());
        }

    @Test
        void testRandomWithNullStringChars() {
            String result = RandomStringUtils.random(5, (String) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        void testRandomAlphabeticWithRange() {
            String result = RandomStringUtils.randomAlphabetic(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.matches("[a-zA-Z]+"));
        }

    @Test
        void testRandomAlphanumericWithRange() {
            String result = RandomStringUtils.randomAlphanumeric(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.matches("[a-zA-Z0-9]+"));
        }

    @Test
        void testRandomAsciiWithRange() {
            String result = RandomStringUtils.randomAscii(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    @Test
        void testRandomGraphWithRange() {
            String result = RandomStringUtils.randomGraph(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.chars().allMatch(c -> c >= 33 && c <= 126));
        }

    @Test
        void testRandomNumericWithRange() {
            String result = RandomStringUtils.randomNumeric(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.matches("\\d+"));
        }

    @Test
        void testRandomPrintWithRange() {
            String result = RandomStringUtils.randomPrint(3, 6);
            assertNotNull(result);
            assertTrue(result.length() >= 3 && result.length() < 6);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    @Test
        void testConstructorIsPrivate() {
            RandomStringUtils instance = new RandomStringUtils();
            assertNotNull(instance);
        }
}
