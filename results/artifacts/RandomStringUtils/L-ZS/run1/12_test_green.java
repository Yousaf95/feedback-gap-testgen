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
}
