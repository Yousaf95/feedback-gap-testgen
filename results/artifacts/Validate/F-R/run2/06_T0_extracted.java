package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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
    @ValueSource(doubles = {-1.0, 4.0, Double.NaN, Double.POSITIVE_INFINITY})
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
    @ValueSource(doubles = {-1.0, 4.0})
    public void testInclusiveBetweenThrowsException(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
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
    @ValueSource(longs = {-1L, 4L})
    public void testInclusiveBetweenLongThrowsException(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
    }

    @Test
    public void testIsAssignableFrom() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isAssignableFrom(String.class, Object.class));
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

    @ParameterizedTest
    @ValueSource(objects = {123})
    public void testIsInstanceOfThrowsException(Object obj) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(String.class, obj));
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

    @ParameterizedTest
    @ValueSource(strings = {"test", null, "example"})
    public void testNoNullElementsThrowsException(String input) {
        // Arrange
        Iterable<String> iterable = Arrays.asList(input);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(iterable));
    }

    @Test
    public void testNotEmpty() {
        // Arrange
        Collection<String> collection = Arrays.asList("test", "example");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", null})
    public void testNotEmptyThrowsException(String input) {
        // Arrange
        Collection<String> collection = Collections.singletonList(input);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(collection));
    }

    @Test
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {null})
    public void testNotNullThrowsException(String input) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.notNull(input));
    }
}
