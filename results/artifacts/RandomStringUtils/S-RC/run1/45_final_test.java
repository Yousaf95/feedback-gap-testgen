package org.apache.commons.lang3;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.RandomStringUtils.*;

public class RandomStringUtils_GenTest {

    @Test
    public void testRandomWithCountZero() {
        assertEquals("", RandomStringUtils.random(0));
    }

    @Test
    public void testRandomWithNegativeCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(-1));
    }

    @Test
    public void testRandomWithEmptyCharsArrayThrowsException() {
        char[] emptyChars = {};
        assertThrows(IllegalArgumentException.class, () -> RandomStringUtils.random(5, emptyChars));
    }

    @Test
    public void testRandomAlphabetic() {
        String result = RandomStringUtils.randomAlphabetic(10);
        assertTrue(result.matches("[a-zA-Z]{10}"));
    }

    @Test
    public void testRandomAlphanumeric() {
        String result = RandomStringUtils.randomAlphanumeric(10);
        assertTrue(result.matches("[a-zA-Z0-9]{10}"));
    }

    @Test
    public void testRandomAscii() {
        String result = RandomStringUtils.randomAscii(10);
        assertTrue(result.matches("[\\x20-\\x7F]{10}"));
    }

    
    @Test
    public void testRandomNumeric() {
        String result = RandomStringUtils.randomNumeric(10);
        assertTrue(result.matches("\\d{10}"));
    }

    @Test
    public void testRandomPrint() {
        String result = RandomStringUtils.randomPrint(10);
        assertTrue(result.matches("[\\x20-\\x7E]{10}"));
    }

    @Test
    public void testRandomWithCustomChars() {
        char[] customChars = {'a', 'b', 'c'};
        String result = RandomStringUtils.random(5, customChars);
        assertTrue(result.matches("[abc]{5}"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testRandomWithNullCharsArray() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, new char[0]);
            });
        }

    @Test
        public void testRandomWithStartEndZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(5, 0, 0, false, false, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithStartEndZeroAndEmptyCharsArray() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(5, 0, 0, false, false, new char[0]);
            });
        }

    @Test
        public void testRandomWithNegativeCount() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.random(-1);
            });
        }

    
    
    
    
    
    
    
    
    @Test
        public void testRandomWithCountZeroAndCharsArray() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(0, chars);
            assertEquals("", result);
        }

    @Test
        public void testRandomWithCountZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(0, (char[]) null);
            assertEquals("", result);
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testStartEndZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(5, 0, 0, false, false, null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testStartEndZeroAndEmptyCharsArray() {
            char[] chars = {};
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, 0, 0, false, false, chars)
            );
        }

    @Test
        public void testNegativeCountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(-1)
            );
        }

    @Test
        public void testEmptyCharsArrayThrowsException() {
            char[] chars = {};
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, chars)
            );
        }

    @Test
        public void testNullCharsArray() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testCountZeroAndCharsArray() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(0, chars);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testCountZeroAndNullCharsArray() {
            String result = RandomStringUtils.random(0, (char[]) null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testRandomWithCustomChars_2() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(5, chars);
            assertNotNull(result);
            assertEquals(5, result.length());
            for (char c : result.toCharArray()) {
                assertTrue(c == 'a' || c == 'b' || c == 'c');
            }
        }

    @Test
        public void testRandomWithStartEndZeroAndNullCharsArray_2() {
            String result = RandomStringUtils.random(5, 0, 0, false, false, null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithStartEndZeroAndEmptyCharsArray_2() {
            char[] chars = {};
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, 0, 0, false, false, chars)
            );
        }

    @Test
        public void testRandomWithNegativeCountThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(-1)
            );
        }

    @Test
        public void testRandomWithEmptyCharsArrayThrowsException_2() {
            char[] chars = {};
            assertThrows(IllegalArgumentException.class, () -> 
                RandomStringUtils.random(5, chars)
            );
        }

    @Test
        public void testRandomWithNullCharsArray_2() {
            String result = RandomStringUtils.random(5, (char[]) null);
            assertNotNull(result);
            assertEquals(5, result.length());
        }

    @Test
        public void testRandomWithCountZeroAndCharsArray_2() {
            char[] chars = {'a', 'b', 'c'};
            String result = RandomStringUtils.random(0, chars);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testRandomWithCountZeroAndNullCharsArray_2() {
            String result = RandomStringUtils.random(0, (char[]) null);
            assertTrue(result.isEmpty());
        }

    
    @Test
        public void testRandomWithStartEndZeroAndEmptyCharsArray_3() {
            assertThrows(IllegalArgumentException.class, () -> random(10, 0, 0, false, false, new char[0]));
        }

    @Test
        public void testNegativeCountThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> random(-1));
        }

    
    
    @Test
        public void testCountZeroAndCharsArray_2() {
            String result = random(0, "abc".toCharArray());
            assertTrue(result.isEmpty());
        }

    @Test
        public void testCountZeroAndNullCharsArray_2() {
            String result = random(0, (char[]) null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testRandomWithCustomChars_2_2() {
            char[] chars = {'a', 'b', 'c'};
            String result = random(5, chars);
            assertNotNull(result);
            assertEquals(5, result.length());
            for (char c : result.toCharArray()) {
                assertTrue(c == 'a' || c == 'b' || c == 'c');
            }
        }

    
    @Test
        public void testRandomWithStartEndZeroAndEmptyCharsArray_2_2() {
            assertThrows(IllegalArgumentException.class, () -> random(10, 0, 0, false, false, new char[0]));
        }

    @Test
        public void testNegativeCountThrowsException_2_2() {
            assertThrows(IllegalArgumentException.class, () -> random(-1));
        }

    
    
    @Test
        public void testCountZeroAndCharsArray_2_2() {
            String result = random(0, "abc".toCharArray());
            assertTrue(result.isEmpty());
        }

    @Test
        public void testCountZeroAndNullCharsArray_2_2() {
            String result = random(0, (char[]) null);
            assertTrue(result.isEmpty());
        }
}
