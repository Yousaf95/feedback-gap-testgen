package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStringUtils_GenTest {

    @Test
    public void testRandomCountZero() {
        assertEquals("", RandomStringUtils.random(0));
    }

    @Test
    public void testRandomNegativeCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
    }

    @Test
    public void testRandomEmptyCharsArrayThrowsException() {
        char[] emptyChars = {};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyChars));
    }

    
    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(10);
        assertTrue(result.matches("[a-zA-Z]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(10);
        assertTrue(result.matches("[a-zA-Z0-9]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 127));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomGraph() {
        String result = RandomStringUtils.randomGraph(10);
        assertTrue(result.chars().allMatch(c -> c > 32 && c < 127));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(10);
        assertTrue(result.matches("[0-9]+"));
        assertEquals(10, result.length());
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(10);
        assertTrue(result.chars().allMatch(c -> c >= 32 && c <= 126));
        assertEquals(10, result.length());
    }

    
    @Test
        public void testRandomEmptyCharsArrayThrowsException_2() {
            char[] emptyArray = {};
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyArray));
        }

    @Test
        public void testRandomStartEndZeroWithChars() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(3, 0, 0, false, false, chars);
            assertTrue(result.length() == 3 && result.chars().allMatch(c -> c >= 'a' && c <= 'c'));
        }

    
    @Test
        public void testRandomCountZero_2() {
            assertEquals("", RandomStringUtils.random(0));
        }

    @Test
        public void testRandomNegativeCountThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomStartEndZeroWithChars_2() {
            String result = RandomStringUtils.random(5, 'a', 'c');
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 'a' && c <= 'c');
            }
        }

    @Test
        public void testRandomCountZero_2_2() {
            String result = RandomStringUtils.random(0, 'a', 'z');
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_2_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, 'a', 'z');
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_2_2() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithChars() {
            String result = RandomStringUtils.random(5, 'a', 'z');
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 'a' && c <= 'z');
            }
        }

    @Test
        public void testRandomCountZeroWithChars() {
            String result = RandomStringUtils.random(0, new char[] {'a', 'b', 'c'});
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_3() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_3() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZero() {
            String result = RandomStringUtils.random(5, 'a', 'z');
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 'a' && c <= 'z');
            }
        }

    @Test
        public void testRandomCountZero_3() {
            String result = RandomStringUtils.random(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_3_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_3_2() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithAlphabetic() {
            String result = RandomStringUtils.randomAlphabetic(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isLetter(c));
            }
        }

    @Test
        public void testRandomCountZeroWithAlphabetic() {
            String result = RandomStringUtils.randomAlphabetic(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_4() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_4() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithAlphanumeric() {
            String result = RandomStringUtils.randomAlphanumeric(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isLetterOrDigit(c));
            }
        }

    @Test
        public void testRandomCountZeroWithAlphanumeric() {
            String result = RandomStringUtils.randomAlphanumeric(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_5() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_5() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithAscii() {
            String result = RandomStringUtils.randomAscii(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 32 && c <= 127);
            }
        }

    @Test
        public void testRandomCountZeroWithAscii() {
            String result = RandomStringUtils.randomAscii(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_6() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_6() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithGraph() {
            String result = RandomStringUtils.randomGraph(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 33 && c <= 126);
            }
        }

    @Test
        public void testRandomCountZeroWithGraph() {
            String result = RandomStringUtils.randomGraph(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_7() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_7() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithNumeric() {
            String result = RandomStringUtils.randomNumeric(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(Character.isDigit(c));
            }
        }

    @Test
        public void testRandomCountZeroWithNumeric() {
            String result = RandomStringUtils.randomNumeric(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_8() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_8() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }

    @Test
        public void testRandomEndGreaterThanZeroWithPrint() {
            String result = RandomStringUtils.randomPrint(5);
            assertTrue(result.length() == 5);
            for (char c : result.toCharArray()) {
                assertTrue(c >= 32 && c <= 126);
            }
        }

    @Test
        public void testRandomCountZeroWithPrint() {
            String result = RandomStringUtils.randomPrint(0);
            assertEquals("", result);
        }

    @Test
        public void testRandomNegativeCountThrowsException_9() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1, true, false);
            });
        }

    @Test
        public void testRandomEmptyCharsArrayThrowsException_9() {
            char[] emptyChars = {};
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, emptyChars);
            });
        }
}
