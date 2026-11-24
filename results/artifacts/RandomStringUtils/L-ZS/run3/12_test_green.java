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
}
