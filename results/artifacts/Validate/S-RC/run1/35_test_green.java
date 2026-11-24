package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

public class Validate_GenTest {

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.5})
    public void testExclusiveBetween(double value) {
        // Arrange
        double start = 0.0;
        double end = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 3.0})
    public void testExclusiveBetweenThrowsException(double value) {
        // Arrange
        double start = 0.0;
        double end = 2.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.5})
    public void testInclusiveBetween(double value) {
        // Arrange
        double start = 0.0;
        double end = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 3.0})
    public void testInclusiveBetweenThrowsException(double value) {
        // Arrange
        double start = 0.0;
        double end = 2.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0})
    public void testFinite(double value) {
        // Arrange

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
    @ValueSource(doubles = {1.0})
    public void testNotNaN(double value) {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNaN(value));
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testNotBlank(String input) {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notBlank(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\n\r"})
    public void testNotBlankThrowsException(String input) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testNotEmpty(String input) {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    public void testNotEmptyThrowsExceptionEmptyString(String input) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(input));
    }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
        }

    @Test
        public void testExclusiveBetweenLong() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
        }

    
    @Test
        public void testInclusiveBetweenDouble() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
        }

    @Test
        public void testInclusiveBetweenLong() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
        }

    @Test
        public void testInclusiveBetweenComparable() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "D"));
        }

    @Test
        public void testIsAssignableFrom() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "123"));
        }

    @Test
        public void testIsTrue() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false));
        }

    @Test
        public void testMatchesPattern() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testExclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testInclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testInclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testExclusiveBetweenComparable() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "A"));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "C"));
        }

    @Test
        public void testInclusiveBetweenComparable_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "Z"));
        }

    @Test
        public void testIsAssignableFrom_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test"));
        }

    @Test
        public void testIsTrue_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false));
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false, "Value is not true", 123.45));
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false, "Value is not true", 123L));
        }

    @Test
        public void testMatchesPattern_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "[0-9]+"));
        }

    
    @Test
        public void testNoNullElementsArray() {
            String[] array = {"A", null, "C"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }
}
