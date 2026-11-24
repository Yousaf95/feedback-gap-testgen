package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
            assertThrows(NullPointerException.class, () -> Validate.noNullElements((Object[]) null));
        }

    
    @Test
        public void testNotBlankWithBlank() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank("\t"));
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank("\n"));
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testNoNullElementsWithEmptyCollection() {
            Collection<String> emptyCollection = Collections.emptyList();
            assertDoesNotThrow(() -> Validate.noNullElements(emptyCollection));
        }

    @Test
        public void testNoNullElementsWithNonEmptyCollection() {
            Collection<String> nonEmptyCollection = Arrays.asList("a", "b", "c");
            assertDoesNotThrow(() -> Validate.noNullElements(nonEmptyCollection));
        }

    @Test
        public void testNoNullElementsThrowsExceptionWithNullElementInCollection() {
            Collection<String> collectionWithNull = Arrays.asList("a", null, "c");
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(collectionWithNull));
            assertEquals("The validated collection contains null element at index: 1", exception.getMessage());
        }

    @Test
        public void testNoNullElementsWithEmptyArray() {
            String[] emptyArray = new String[0];
            assertDoesNotThrow(() -> Validate.noNullElements(emptyArray));
        }

    @Test
        public void testNoNullElementsWithNonEmptyArray() {
            String[] nonEmptyArray = {"a", "b", "c"};
            assertDoesNotThrow(() -> Validate.noNullElements(nonEmptyArray));
        }

    @Test
        public void testNoNullElementsThrowsExceptionWithNullElementInArray() {
            String[] arrayWithNull = {"a", null, "c"};
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(arrayWithNull));
            assertEquals("The validated array contains null element at index: 1", exception.getMessage());
        }

    
    @Test
        public void testValidIndexWithNonEmptyCollection() {
            Collection<String> nonEmptyCollection = Arrays.asList("a", "b", "c");
            assertDoesNotThrow(() -> Validate.validIndex(nonEmptyCollection, 1));
        }

    
    
    @Test
        public void testValidIndexWithNonEmptyArray() {
            String[] nonEmptyArray = {"a", "b", "c"};
            assertDoesNotThrow(() -> Validate.validIndex(nonEmptyArray, 1));
        }

    
    @Test
        public void testValidStateWithTrueExpression() {
            assertDoesNotThrow(() -> Validate.validState(true));
        }

    
    @Test
        public void testValidStateWithTrueExpressionAndMessage() {
            assertDoesNotThrow(() -> Validate.validState(true, "Custom message"));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenWithNonComparableValues() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", "b"));
        }

    @Test
        public void testInclusiveBetweenWithNonComparableValues() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("a", "c", "b"));
        }

    @Test
        public void testNoNullElementsWithNullMap() {
            Map<String, String> map = null;
            assertThrows(NullPointerException.class, () -> Validate.noNullElements(map));
        }

    @Test
        public void testNotEmptyWithEmptyMap() {
            Map<String, String> map = Collections.emptyMap();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
        }

    @Test
        public void testNotBlankWithNonStringType() {
            Object obj = 123;
            assertThrows(ClassCastException.class, () -> Validate.notBlank((CharSequence) obj));
        }

    @Test
        public void testValidIndexWithNegativeArray() {
            String[] array = new String[0];
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, -1));
        }

    @Test
        public void testValidIndexWithTooLargeArray() {
            String[] array = new String[5];
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, 5));
        }

    @Test
        public void testValidStateWithFalseExpressionAndMessage() {
            String message = "Invalid state";
            assertThrows(IllegalArgumentException.class, () -> Validate.validState(false, message));
        }

    @Test
        public void testIsAssignableFromWithNullTypes() {
            Class<?> superType = null;
            Class<?> type = null;
            assertThrows(NullPointerException.class, () -> Validate.isAssignableFrom(superType, type));
        }

    @Test
        public void testIsInstanceOfWithNullObject() {
            Object obj = null;
            assertThrows(NullPointerException.class, () -> Validate.isInstanceOf(String.class, obj));
        }

    @Test
        public void testMatchesPatternWithNonStringInput() {
            Object input = 123;
            String pattern = "\\d+";
            assertThrows(ClassCastException.class, () -> Validate.matchesPattern((CharSequence) input, pattern));
        }
}
