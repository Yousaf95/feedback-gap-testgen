package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RandomStringUtils_GenTest {

    private static final String EMPTY = StringUtils.EMPTY;
    private static final char[] ALL_CHARS = (char[]) Character.toChars(0, Character.MAX_CODE_POINT);
    private static final char[] LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] NUMBERS = "0123456789".toCharArray();

    @BeforeEach
    public void setUp() {
        // No-op
    }

    @ParameterizedTest
    @MethodSource("provideRandomCount")
    public void testRandom(int count) {
        String result = RandomStringUtils.random(count);
        assertEquals(count, result.length());
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndLetters")
    public void testRandomWithLetters(int count, boolean letters) {
        String result = RandomStringUtils.random(count, letters, false);
        if (letters) {
            assertTrue(result.chars().allMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().anyMatch(Character::isLetter));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndNumbers")
    public void testRandomWithNumbers(int count, boolean numbers) {
        String result = RandomStringUtils.random(count, false, numbers);
        if (numbers) {
            assertTrue(result.chars().allMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().anyMatch(Character::isDigit));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndChars")
    public void testRandomWithChars(int count, char[] chars) {
        String result = RandomStringUtils.random(count, chars);
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRange")
    public void testRandomWithRange(int count, int start, int end) {
        String result = RandomStringUtils.random(count, start, end, false, false);
        assertTrue(result.chars().allMatch(c -> c >= start && c < end));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithLetters")
    public void testRandomWithRangeAndLetters(int count, int start, int end, boolean letters) {
        String result = RandomStringUtils.random(count, start, end, letters, false);
        if (letters) {
            assertTrue(result.chars().allMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().anyMatch(Character::isLetter));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithNumbers")
    public void testRandomWithRangeAndNumbers(int count, int start, int end, boolean numbers) {
        String result = RandomStringUtils.random(count, start, end, false, numbers);
        if (numbers) {
            assertTrue(result.chars().allMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().anyMatch(Character::isDigit));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithChars")
    public void testRandomWithRangeAndChars(int count, int start, int end, char[] chars) {
        String result = RandomStringUtils.random(count, start, end, false, false, chars);
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithLettersAndNumbers")
    public void testRandomWithRangeAndLettersAndNumbers(int count, int start, int end, boolean letters, boolean numbers) {
        String result = RandomStringUtils.random(count, start, end, letters, numbers);
        if (letters) {
            assertTrue(result.chars().anyMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().allMatch(Character::isLetter));
        }
        if (numbers) {
            assertTrue(result.chars().anyMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().allMatch(Character::isDigit));
        }
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLetters")
    public void testRandomWithRangeAndCharsAndLetters(int count, int start, int end, char[] chars, boolean letters) {
        String result = RandomStringUtils.random(count, start, end, letters, false, chars);
        if (letters) {
            assertTrue(result.chars().anyMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().allMatch(Character::isLetter));
        }
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndNumbers")
    public void testRandomWithRangeAndCharsAndNumbers(int count, int start, int end, char[] chars, boolean numbers) {
        String result = RandomStringUtils.random(count, start, end, false, numbers, chars);
        if (numbers) {
            assertTrue(result.chars().anyMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().allMatch(Character::isDigit));
        }
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbers")
    public void testRandomWithRangeAndCharsAndLettersAndNumbers(int count, int start, int end, char[] chars, boolean letters, boolean numbers) {
        String result = RandomStringUtils.random(count, start, end, letters, numbers, chars);
        if (letters) {
            assertTrue(result.chars().anyMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().allMatch(Character::isLetter));
        }
        if (numbers) {
            assertTrue(result.chars().anyMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().allMatch(Character::isDigit));
        }
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandom(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        String result = RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        if (letters) {
            assertTrue(result.chars().anyMatch(Character::isLetter));
        } else {
            assertFalse(result.chars().allMatch(Character::isLetter));
        }
        if (numbers) {
            assertTrue(result.chars().anyMatch(Character::isDigit));
        } else {
            assertFalse(result.chars().allMatch(Character::isDigit));
        }
        assertTrue(result.chars().allMatch(c -> Arrays.binarySearch(chars, c) >= 0));
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException2(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException3(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException4(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException5(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException6(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException7(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException8(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException9(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException10(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException11(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException12(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException13(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException14(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException15(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException16(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException17(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException18(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException19(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException20(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException21(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException22(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException23(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException24(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException25(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count, start, end, letters, numbers, chars, random);
        });
    }

    @ParameterizedTest
    @MethodSource("provideRandomCountAndRangeWithCharsAndLettersAndNumbersAndRandom")
    public void testRandomWithRangeAndCharsAndLettersAndNumbersAndRandomThrowsIllegalArgumentException26(int count, int start, int end, char[] chars, boolean letters, boolean numbers, Random random) {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomStringUtils.random(count,