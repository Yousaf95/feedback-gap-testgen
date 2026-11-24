package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validate_GenTest {

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.5, 3.9})
    public void testExclusiveBetween(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 4.0})
    public void testExclusiveBetweenThrowsException(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    public void testExclusiveBetweenLong(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 4L})
    public void testExclusiveBetweenLongThrowsException(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
    }

    @Test
    public void testFinite() {
        // Arrange
        double value = 1.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.finite(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void testFiniteThrowsException(double value) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.5, 3.9})
    public void testInclusiveBetween(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    public void testInclusiveBetweenLong(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    
    
    @ParameterizedTest
    @ValueSource(classes = {Integer.class})
    public void testIsAssignableFromThrowsException(Class<?> type) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, type));
    }

    @Test
    public void testIsInstanceOf() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
    }

    
    @Test
    public void testIsValid() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isTrue(true));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false})
    public void testIsValidThrowsException(boolean expression) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(expression));
    }

    @Test
    public void testMatchesPattern() {
        // Arrange
        CharSequence input = "test";
        String pattern = "^[a-z]+$";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern(input, pattern));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "test!"})
    public void testMatchesPatternThrowsException(String input) {
        // Arrange
        String pattern = "^[a-z]+$";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(input, pattern));
    }

    @Test
    public void testNoNullElements() {
        // Arrange
        Iterable<String> iterable = Arrays.asList("test", "example");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.noNullElements(iterable));
    }

    
    @Test
    public void testNotEmpty() {
        // Arrange
        Collection<String> collection = Arrays.asList("test", "example");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    
    @Test
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble() {
            Validate.exclusiveBetween(1.0, 5.0, 3.0);
        }

    @Test
        public void testExclusiveBetweenLong_2() {
            Validate.exclusiveBetween(1L, 5L, 3L);
        }

    @Test
        public void testInclusiveBetweenDouble() {
            Validate.inclusiveBetween(1.0, 5.0, 3.0);
        }

    @Test
        public void testInclusiveBetweenLong_2() {
            Validate.inclusiveBetween(1L, 5L, 3L);
        }

    @Test
        public void testIsAssignableFrom() {
            Validate.isAssignableFrom(Number.class, Integer.class);
        }

    @Test
        public void testIsInstanceOf_2() {
            Validate.isInstanceOf(String.class, "test");
        }

    @Test
        public void testIsValidDouble() {
            assertTrue(Validate.isValid(1.0));
        }

    @Test
        public void testIsValidLong() {
            assertTrue(Validate.isValid(1L));
        }

    @Test
        public void testMatchesPattern_2() {
            Validate.matchesPattern("test", "t.e.s.t");
        }

    @Test
        public void testNoNullElementsArray() {
            String[] array = {"a", "b", "c"};
            Validate.noNullElements(array);
        }

    @Test
        public void testNoNullElementsCollection() {
            Collection<String> collection = Arrays.asList("a", "b", "c");
            Validate.noNullElements(collection);
        }

    @Test
        public void testNotBlank() {
            Validate.notBlank("test");
        }

    @Test
        public void testNotEmptyArray() {
            String[] array = {"a", "b", "c"};
            Validate.notEmpty(array);
        }

    @Test
        public void testNotEmptyCollection() {
            Collection<String> collection = Arrays.asList("a", "b", "c");
            Validate.notEmpty(collection);
        }

    @Test
        public void testNotEmptyMap() {
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");
            Validate.notEmpty(map);
        }

    @Test
        public void testNotNull_2() {
            Object obj = new Object();
            Validate.notNull(obj);
        }

    @Test
        public void testValidIndexArray() {
            String[] array = {"a", "b", "c"};
            Validate.validIndex(array, 1);
        }

    @Test
        public void testValidIndexCollection() {
            Collection<String> collection = Arrays.asList("a", "b", "c");
            Validate.validIndex(collection, 1);
        }

    @Test
        public void testValidIndexCharSequence() {
            CharSequence chars = "test";
            Validate.validIndex(chars, 1);
        }

    @Test
        public void testValidState() {
            Validate.validState(true);
        }
}
