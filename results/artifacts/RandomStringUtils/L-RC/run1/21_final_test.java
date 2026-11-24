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
    void testRandomWithOnlyLettersIncludesOnlyLetters() {
        String result = RandomStringUtils.random(10, true, false);
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testRandomWithOnlyNumbersIncludesOnlyNumbers() {
        String result = RandomStringUtils.random(10, false, true);
        assertTrue(result.matches("\\d+"));
    }

    @Test
    void testRandomWithCustomCharsUsesOnlyThoseChars() {
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
    void testRandomWithStartGreaterThanEndThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(10, 5, 3, true, false);
        });
        assertEquals("Parameter end (3) must be greater than start (5)", exception.getMessage());
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
        assertTrue(result.matches("[a-zA-Z\\d]+"));
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
            assertEquals("", RandomStringUtils.random(0, (char[]) null));
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, (char[]) null));
            assertEquals(5, RandomStringUtils.random(5, (char[]) null).length());
        }

    @Test
        void testRandomWithStartAndEndZero() {
            assertEquals("", RandomStringUtils.random(0, 0, 0, false, false));
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, 0, 0, false, false));
            assertEquals(5, RandomStringUtils.random(5, 0, 0, false, false).length());
        }

    @Test
        void testRandomWithInvalidEndForNumbers() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 48, 47, true, false));
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 0, 48, false, true));
        }

    
    @Test
        void testRandomWithNullStringChars() {
            assertEquals("", RandomStringUtils.random(0, (String) null));
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1, (String) null));
            assertEquals(5, RandomStringUtils.random(5, (String) null).length());
        }

    @Test
        void testRandomAlphabeticWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomAlphabetic(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(Character::isLetter));
        }

    @Test
        void testRandomAlphanumericWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomAlphanumeric(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
        }

    @Test
        void testRandomAsciiWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomAscii(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    @Test
        void testRandomGraphWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomGraph(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(c -> c >= 33 && c <= 126));
        }

    @Test
        void testRandomNumericWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomNumeric(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(Character::isDigit));
        }

    @Test
        void testRandomPrintWithRange() {
            int minLength = 3;
            int maxLength = 7;
            String result = RandomStringUtils.randomPrint(minLength, maxLength);
            assertTrue(result.length() >= minLength && result.length() < maxLength);
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }

    }
