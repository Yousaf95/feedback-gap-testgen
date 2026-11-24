package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStringUtils_GenTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandom(int count) {
        String result = RandomStringUtils.random(count);
        assertEquals(count, result.length());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(count));
    }

    @Test
    public void testRandomWithLetters() {
        String result = RandomStringUtils.random(5, true, false);
        assertTrue(result.chars().allMatch(Character::isLetter));
    }

    @Test
    public void testRandomWithNumbers() {
        String result = RandomStringUtils.random(5, false, true);
        assertTrue(result.chars().allMatch(Character::isDigit));
    }

    @Test
    public void testRandomWithLettersAndNumbers() {
        String result = RandomStringUtils.random(5, true, true);
        assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomWithChars(int count) {
        char[] chars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(count, chars);
        assertTrue(result.chars().allMatch(c -> c == 'a' || c == 'b' || c == 'c'));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomWithCharsNegativeCountThrowsException(int count) {
        char[] chars = {'a', 'b', 'c'};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(count, chars));
    }

    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(5);
        assertTrue(result.chars().allMatch(Character::isLetter));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAlphabeticWithRange(int count) {
        String result = RandomStringUtils.randomAlphabetic(count);
        assertTrue(result.chars().allMatch(Character::isLetter));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomAlphabeticNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(count));
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(5);
        assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAlphanumericWithRange(int count) {
        String result = RandomStringUtils.randomAlphanumeric(count);
        assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomAlphanumericNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(count));
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(5);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAsciiWithRange(int count) {
        String result = RandomStringUtils.randomAscii(count);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomAsciiNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(count));
    }

    @Test
    public void testRandomGraph() {
        String result = RandomStringUtils.randomGraph(5);
        assertTrue(result.chars().allMatch(c -> c >= 33 && c <= 126));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomGraphWithRange(int count) {
        String result = RandomStringUtils.randomGraph(count);
        assertTrue(result.chars().allMatch(c -> c >= 33 && c <= 126));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomGraphNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(count));
    }

    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(5);
        assertTrue(result.chars().allMatch(Character::isDigit));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomNumericWithRange(int count) {
        String result = RandomStringUtils.randomNumeric(count);
        assertTrue(result.chars().allMatch(Character::isDigit));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomNumericNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(count));
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(5);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomPrintWithRange(int count) {
        String result = RandomStringUtils.randomPrint(count);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, Integer.MIN_VALUE})
    public void testRandomPrintNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(count));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithNullChars() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomNegativeCountThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
        }

    @Test
        public void testRandomWithEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, new char[0]));
        }

    @Test
        public void testRandomAlphabeticWithRange_2() {
            String result = RandomStringUtils.randomAlphabetic(10, 20);
            assertNotNull(result);
            assertTrue(result.length() >= 10 && result.length() < 20);
        }

    @Test
        public void testRandomAlphanumericWithRange_2() {
            String result = RandomStringUtils.randomAlphanumeric(15, 30);
            assertNotNull(result);
            assertTrue(result.length() >= 15 && result.length() < 30);
        }

    @Test
        public void testRandomAsciiWithRange_2() {
            String result = RandomStringUtils.randomAscii(20, 40);
            assertNotNull(result);
            assertTrue(result.length() >= 20 && result.length() < 40);
        }

    @Test
        public void testRandomGraphWithRange_2() {
            String result = RandomStringUtils.randomGraph(10, 25);
            assertNotNull(result);
            assertTrue(result.length() >= 10 && result.length() < 25);
        }

    @Test
        public void testRandomNumericWithRange_2() {
            String result = RandomStringUtils.randomNumeric(8, 16);
            assertNotNull(result);
            assertTrue(result.length() >= 8 && result.length() < 16);
        }

    @Test
        public void testRandomPrintWithRange_2() {
            String result = RandomStringUtils.randomPrint(5, 10);
            assertNotNull(result);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }
}
