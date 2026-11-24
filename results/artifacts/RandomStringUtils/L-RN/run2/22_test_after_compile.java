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
        void testRandomWithStartGreaterThanEndThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 127, 33, false, false));
        }

    @Test
        void testRandomAlphabeticWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(-1, 5));
        }

    @Test
        void testRandomAlphanumericWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(-1, 5));
        }

    @Test
        void testRandomAsciiWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(-1, 5));
        }

    @Test
        void testRandomGraphWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(-1, 5));
        }

    @Test
        void testRandomNumericWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(-1, 5));
        }

    @Test
        void testRandomPrintWithNegativeMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(-1, 5));
        }

    @Test
        void testRandomAlphabeticWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(5, 3));
        }

    @Test
        void testRandomAlphanumericWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(5, 3));
        }

    @Test
        void testRandomAsciiWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(5, 3));
        }

    @Test
        void testRandomGraphWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(5, 3));
        }

    @Test
        void testRandomNumericWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(5, 3));
        }

    @Test
        void testRandomPrintWithMaxLengthLessThanMinLengthThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(5, 3));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testRandomWithStartEqualsEndThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, 65, 65));
        }

    @Test
        void testRandomAlphabeticWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphabetic(10, -1));
        }

    @Test
        void testRandomAlphanumericWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAlphanumeric(10, -1));
        }

    @Test
        void testRandomAsciiWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomAscii(10, -1));
        }

    @Test
        void testRandomGraphWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomGraph(10, -1));
        }

    @Test
        void testRandomNumericWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomNumeric(10, -1));
        }

    @Test
        void testRandomPrintWithMaxLengthLessThanZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.randomPrint(10, -1));
        }

    @Test
        void testRandomWithNegativeStartAndPositiveEndThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, -1, 65));
        }

    @Test
        void testRandomWithCustomCharsArrayGeneratesStringUsingProvidedCharsOnly() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(10, chars);
            assertTrue(result.matches("[abc]+"));
        }

    @Test
        void testRandomAlphabeticWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomAlphabetic(length, length + 1);
            assertEquals(length, result.length());
        }

    @Test
        void testRandomAlphanumericWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomAlphanumeric(length, length + 1);
            assertEquals(length, result.length());
        }

    @Test
        void testRandomAsciiWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomAscii(length, length + 1);
            assertEquals(length, result.length());
        }

    @Test
        void testRandomGraphWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomGraph(length, length + 1);
            assertEquals(length, result.length());
        }

    @Test
        void testRandomNumericWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomNumeric(length, length + 1);
            assertEquals(length, result.length());
        }

    @Test
        void testRandomPrintWithMinLengthEqualsMaxLengthGeneratesStringOfCorrectLength() {
            int length = 5;
            String result = RandomStringUtils.randomPrint(length, length + 1);
            assertEquals(length, result.length());
        }
}
