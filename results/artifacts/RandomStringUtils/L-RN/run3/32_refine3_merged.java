package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;

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


    // ---- added by coverage-guided refinement ----
    @Test
        void testRandomWithStartGreaterThanEndThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 127, 33, false, false));
        }

    @Test
        void testRandomAlphabeticWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(-1));
        }

    @Test
        void testRandomAlphanumericWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(-1));
        }

    @Test
        void testRandomAsciiWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(-1));
        }

    @Test
        void testRandomGraphWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(-1));
        }

    @Test
        void testRandomNumericWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(-1));
        }

    @Test
        void testRandomPrintWithNegativeCountThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(-1));
        }

    @Test
        void testRandomAlphabeticWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomAlphabetic(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomAlphanumericWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomAlphanumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomAsciiWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomAscii(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomGraphWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomGraph(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomNumericWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomNumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomPrintWithRangeGeneratesStringOfCorrectLength() {
            String result = RandomStringUtils.randomPrint(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomWithCustomCharsArrayAndStartEndGeneratesStringUsingThoseChars() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(5, 0, 3, false, false, chars, new Random(12345));
            assertTrue(result.matches("[abc]+"));
        }

    @Test
        void testRandomWithStartEndAndLettersTrueGeneratesOnlyLetters() {
            String result = RandomStringUtils.random(5, 'A', 'Z' + 1, true, false);
            assertTrue(result.matches("[A-Z]+"));
        }

    @Test
        void testRandomWithStartEndAndNumbersTrueGeneratesOnlyDigits() {
            String result = RandomStringUtils.random(5, '0', '9' + 1, false, true);
            assertTrue(result.matches("\\d+"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testRandomWithStartGreaterThanEndThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 10, 5, true, false));
        }

    @Test
        void testRandomAlphabeticWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(-1));
        }

    @Test
        void testRandomAlphanumericWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(-1));
        }

    @Test
        void testRandomAsciiWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(-1));
        }

    @Test
        void testRandomGraphWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(-1));
        }

    @Test
        void testRandomNumericWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(-1));
        }

    @Test
        void testRandomPrintWithNegativeCountThrowsIllegalArgumentException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(-1));
        }

    @Test
        void testRandomAlphabeticWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomAlphabetic(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomAlphanumericWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomAlphanumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomAsciiWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomAscii(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomGraphWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomGraph(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomNumericWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomNumeric(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomPrintWithRangeGeneratesStringOfCorrectLength_2() {
            String result = RandomStringUtils.randomPrint(5, 10);
            assertTrue(result.length() >= 5 && result.length() < 10);
        }

    @Test
        void testRandomWithCustomCharsArrayAndStartEndGeneratesStringUsingThoseChars_2() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(5, 0, 3, false, false, chars);
            assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, (char) c) >= 0));
        }

    @Test
        void testRandomWithStartEndAndLettersTrueGeneratesOnlyLetters_2() {
            String result = RandomStringUtils.random(5, 'A', 'Z' + 1, true, false);
            assertTrue(result.chars().allMatch(Character::isLetter));
        }

    @Test
        void testRandomWithStartEndAndNumbersTrueGeneratesOnlyDigits_2() {
            String result = RandomStringUtils.random(5, '0', '9' + 1, false, true);
            assertTrue(result.chars().allMatch(Character::isDigit));
        }
}
