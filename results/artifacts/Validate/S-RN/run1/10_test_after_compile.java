package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        public void testExclusiveBetweenWithNull() {
            assertThrows(NullPointerException.class, () -> Validate.exclusiveBetween(null, 10, 5));
            assertThrows(NullPointerException.class, () -> Validate.exclusiveBetween(5, null, 10));
            assertThrows(NullPointerException.class, () -> Validate.exclusiveBetween(5, 10, null));
        }

    @Test
        public void testInclusiveBetweenWithNull() {
            assertThrows(NullPointerException.class, () -> Validate.inclusiveBetween(null, 10, 5));
            assertThrows(NullPointerException.class, () -> Validate.inclusiveBetween(5, null, 10));
            assertThrows(NullPointerException.class, () -> Validate.inclusiveBetween(5, 10, null));
        }

    @Test
        public void testNoNullElementsWithNull() {
            assertThrows(NullPointerException.class, () -> Validate.noNullElements((Collection<?>) null));
            assertThrows(NullPointerException.class, () -> Validate.noNullElements((Map<?, ?>) null));
            assertThrows(NullPointerException.class, () -> Validate.noNullElements((Object[]) null));
        }

    @Test
        public void testNotEmptyWithEmpty() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyList()));
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyMap()));
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(new String[] {}));
        }

    @Test
        public void testNotBlankWithBlank() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank("\t"));
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank("\n"));
        }

    @Test
        public void testIsInstanceOfWithNull() {
            assertThrows(NullPointerException.class, () -> Validate.isInstanceOf(String.class, null));
        }

    @Test
        public void testIsAssignableFromWithNull() {
            assertThrows(NullPointerException.class, () -> Validate.isAssignableFrom(null, String.class));
            assertThrows(NullPointerException.class, () -> Validate.isAssignableFrom(String.class, null));
        }

    @Test
        public void testValidIndexWithNegativeIndex() {
            List<String> list = Arrays.asList("a", "b", "c");
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, -1));
        }

    @Test
        public void testValidIndexWithTooLargeIndex() {
            List<String> list = Arrays.asList("a", "b", "c");
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, 3));
        }

    @Test
        public void testValidIndexWithNullCollection() {
            assertThrows(NullPointerException.class, () -> Validate.validIndex((Collection<?>) null, 0));
        }

    @Test
        public void testValidIndexWithNullCharSequence() {
            assertThrows(NullPointerException.class, () -> Validate.validIndex((CharSequence) null, 0));
        }

    @Test
        public void testValidIndexWithNullArray() {
            assertThrows(NullPointerException.class, () -> Validate.validIndex((Object[]) null, 0));
        }
}
