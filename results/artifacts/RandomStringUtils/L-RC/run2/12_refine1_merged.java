package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
    void testRandomWithCustomCharsArrayGeneratesStringUsingProvidedChars() {
        char[] chars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(10, chars);
        assertTrue(result.matches("[abc]+"));
    }

    
    @Test
    void testRandomWithEmptyCharsArrayThrowsIllegalArgumentException() {
        char[] chars = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(10, chars);
        });
        assertEquals("The chars array must not be empty", exception.getMessage());
    }

    @Test
    void testRandomAlphabeticWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomAlphabetic(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomAlphabeticWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomAlphabetic(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testRandomAlphanumericWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomAlphanumeric(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomAlphanumericWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomAlphanumeric(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("[a-zA-Z0-9]+"));
    }

    @Test
    void testRandomAsciiWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomAscii(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomAsciiWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomAscii(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("[ -~]+"));
    }

    @Test
    void testRandomGraphWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomGraph(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomGraphWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomGraph(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("[!-~]+"));
    }

    @Test
    void testRandomNumericWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomNumeric(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomNumericWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomNumeric(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("\\d+"));
    }

    @Test
    void testRandomPrintWithNegativeCountThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.randomPrint(-1);
        });
        assertEquals("Requested random string length -1 is less than 0.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testRandomPrintWithPositiveCountGeneratesStringOfCorrectLength(int count) {
        String result = RandomStringUtils.randomPrint(count);
        assertEquals(count, result.length());
        assertTrue(result.matches("[ -~]+"));
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
            assertEquals("Parameter end (126) must be greater then (48) for generating digits or greater then (65) for generating letters.", exception.getMessage());
        }

    @Test
        void testRandomWithStartEndZeroAndLetters() {
            String result = RandomStringUtils.random(5, 0, 0, true, false);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        void testRandomWithStartEndZeroAndNumbers() {
            String result = RandomStringUtils.random(5, 0, 0, false, true);
            assertNotNull(result);
            assertEquals(5, result.length());
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
            String result = RandomStringUtils.randomAlphabetic(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.matches("[a-zA-Z]+"));
        }

    @Test
        void testRandomAlphanumericWithRange() {
            String result = RandomStringUtils.randomAlphanumeric(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.matches("[a-zA-Z0-9]+"));
        }

    @Test
        void testRandomAsciiWithRange() {
            String result = RandomStringUtils.randomAscii(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    @Test
        void testRandomGraphWithRange() {
            String result = RandomStringUtils.randomGraph(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.chars().allMatch(c -> c >= 33 && c <= 126));
        }

    @Test
        void testRandomNumericWithRange() {
            String result = RandomStringUtils.randomNumeric(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.matches("[0-9]+"));
        }

    @Test
        void testRandomPrintWithRange() {
            String result = RandomStringUtils.randomPrint(2, 5);
            assertTrue(result.length() >= 2 && result.length() < 5);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    @Test
        void testConstructorIsPrivate() {
            assertThrows(IllegalAccessException.class, () -> {
                RandomStringUtils.class.getDeclaredConstructor().newInstance();
            });
        }
}
