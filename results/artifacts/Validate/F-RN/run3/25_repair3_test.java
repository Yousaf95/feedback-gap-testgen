package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;

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

    @ParameterizedTest
    @ValueSource(strings = {"2", "3"})
    public void testExclusiveBetweenComparable(String value) {
        // Arrange
        String start = "1";
        String end = "4";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "4"})
    public void testExclusiveBetweenComparableThrowsException(String value) {
        // Arrange
        String start = "1";
        String end = "4";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
    }

    @Test
    public void testFinite() {
        // Arrange
        double value = 0.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.finite(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY})
    public void testFiniteThrowsException(double value) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3"})
    public void testInclusiveBetween(String value) {
        // Arrange
        String start = "1";
        String end = "4";

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
    @ValueSource(doubles = {1.0, 2.5, 3.9})
    public void testInclusiveBetweenDouble(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    
    
    
    @Test
    public void testIsInstanceOf() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
    }

    
    @Test
    public void testIsValidState() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.validState(true));
    }

    
    @Test
    public void testMatchesPattern() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern("test", "t.*"));
    }

    
    
    
    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testNotEmpty() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty("test"));
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testNotBlank() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notBlank("test"));
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"123.0", "abc"})
    public void testNotNaN() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNaN(123.0));
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDoubleNegativeRange() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(-10.0, -5.0, -7.5));
        }

    @Test
        public void testExclusiveBetweenLongNegativeRange() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(-10L, -5L, -7L));
        }

    @Test
        public void testInclusiveBetweenDoubleNegativeRange() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(-10.0, -5.0, -7.5));
        }

    @Test
        public void testInclusiveBetweenLongNegativeRange() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(-10L, -5L, -7L));
        }

    @Test
        public void testMatchesPatternInvalidString() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNotEmptyEmptyCollection() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyList()));
        }

    @Test
        public void testNotEmptyNullCollection() {
            assertThrows(NullPointerException.class, () -> Validate.notEmpty((java.util.Collection<?>) null));
        }

    @Test
        public void testNotEmptyEmptyMap() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(new HashMap<>()));
        }

    @Test
        public void testNotEmptyNullMap() {
            assertThrows(NullPointerException.class, () -> Validate.notEmpty((java.util.Map<?, ?>) null));
        }

    @Test
        public void testNotBlankBlankString() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
        }

    @Test
        public void testNotBlankNullString() {
            assertThrows(NullPointerException.class, () -> Validate.notBlank(null));
        }

    @Test
        public void testNotNullNullObject() {
            assertThrows(NullPointerException.class, () -> Validate.notNull(null));
        }

    @Test
        public void testValidIndexNegativeIndex() {
            List<String> list = Arrays.asList("a", "b", "c");
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, -1));
        }

    @Test
        public void testValidIndexTooLargeIndex() {
            List<String> list = Arrays.asList("a", "b", "c");
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, 3));
        }
}
