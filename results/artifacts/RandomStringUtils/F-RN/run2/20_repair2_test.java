package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.concurrent.ThreadLocalRandom;
import static org.mockito.Mockito.*;

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
    private ThreadLocalRandom randomMock;

    @BeforeEach
        public void setUp() {
            randomMock = mock(ThreadLocalRandom.class);
        }

    @Test
        public void testRandomWithCustomRandom() {
            when(randomMock.nextInt(10)).thenReturn(5);
            String result = RandomStringUtils.random(1, false, false, null, null, new char[]{'a', 'b'}, randomMock);
            assertEquals("b", result);
        }

    @Test
        public void testRandomWithEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, (char[])null)
            );
        }

    @Test
        public void testRandomWithNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(-1)
            );
        }

    @Test
        public void testRandomAlphabeticRange_2() {
            String result = RandomStringUtils.randomAlphabetic(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isLetter(c));
            }
        }

    @Test
        public void testRandomAlphanumericRange_2() {
            String result = RandomStringUtils.randomAlphanumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isLetterOrDigit(c));
            }
        }

    @Test
        public void testRandomAsciiRange_2() {
            String result = RandomStringUtils.randomAscii(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 32 && c <= 127);
            }
        }

    @Test
        public void testRandomGraphRange_2() {
            String result = RandomStringUtils.randomGraph(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(c > 32 && c < 127);
            }
        }

    @Test
        public void testRandomNumericRange_2() {
            String result = RandomStringUtils.randomNumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isDigit(c));
            }
        }

    @Test
        public void testRandomPrintRange_2() {
            String result = RandomStringUtils.randomPrint(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 32 && c <= 126);
            }
        }
}
