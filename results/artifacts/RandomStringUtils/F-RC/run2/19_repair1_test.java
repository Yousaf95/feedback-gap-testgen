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
    @ValueSource(ints = {-1, -5})
    public void testRandomNegativeCountThrowsException(int count) {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(count));
    }

    @Test
    public void testRandomWithEmptyCharsArrayThrowsException() {
        char[] chars = {};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, chars));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomWithCharArray(int count) {
        char[] chars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(count, chars);
        assertTrue(result.chars().allMatch(c -> c == 'a' || c == 'b' || c == 'c'));
    }

    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(5);
        assertTrue(result.chars().allMatch(Character::isLetter));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAlphabeticRange(int count) {
        String result = RandomStringUtils.randomAlphabetic(count);
        assertTrue(result.chars().allMatch(Character::isLetter));
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(5);
        assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAlphanumericRange(int count) {
        String result = RandomStringUtils.randomAlphanumeric(count);
        assertTrue(result.chars().allMatch(c -> Character.isLetterOrDigit(c)));
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(5);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomAsciiRange(int count) {
        String result = RandomStringUtils.randomAscii(count);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
    }

    @Test
    public void testRandomGraph() {
        String result = RandomStringUtils.randomGraph(5);
        assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomGraphRange(int count) {
        String result = RandomStringUtils.randomGraph(count);
        assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
    }

    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(5);
        assertTrue(result.chars().allMatch(Character::isDigit));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomNumericRange(int count) {
        String result = RandomStringUtils.randomNumeric(count);
        assertTrue(result.chars().allMatch(Character::isDigit));
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(5);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void testRandomPrintRange(int count) {
        String result = RandomStringUtils.randomPrint(count);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithNullCharsArrayThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, (char[]) null));
        }

    @Test
        public void testRandomWithEmptyCharsArrayThrowsException_2() {
            char[] emptyArray = {};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyArray));
        }

    @Test
        public void testRandomWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
        }

    @Test
        public void testRandomWithCharsAndEmptyRangeThrowsException() {
            char[] chars = {'a', 'b'};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 0, 0, false, false, chars));
        }

    @Test
        public void testRandomWithCharsAndInvalidEndThrowsException() {
            char[] chars = {'a', 'b'};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 0, 1, true, true, chars));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomAlphabeticRange_2(int length) {
            String result = RandomStringUtils.randomAlphabetic(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.matches("[a-zA-Z]+"));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomAlphanumericRange_2(int length) {
            String result = RandomStringUtils.randomAlphanumeric(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.matches("[a-zA-Z0-9]+"));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomAsciiRange_2(int length) {
            String result = RandomStringUtils.randomAscii(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomGraphRange_2(int length) {
            String result = RandomStringUtils.randomGraph(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomNumericRange_2(int length) {
            String result = RandomStringUtils.randomNumeric(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.matches("[0-9]+"));
        }

    @ParameterizedTest
        @ValueSource(ints = {5, 10})
        public void testRandomPrintRange_2(int length) {
            String result = RandomStringUtils.randomPrint(length);
            assertNotNull(result);
            assertEquals(length, result.length());
            assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        }
}
