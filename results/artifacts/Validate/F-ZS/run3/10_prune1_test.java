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
    @ValueSource(strings = {"1", "4"})
    public void testInclusiveBetweenThrowsException(String value) {
        // Arrange
        String start = "1";
        String end = "4";

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

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.5, 3.9})
    public void testInclusiveBetweenDouble(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 4.0})
    public void testInclusiveBetweenDoubleThrowsException(double value) {
        // Arrange
        double start = 0.0;
        double end = 4.0;

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
    @ValueSource(strings = {"String", "Object"})
    public void testIsAssignableFromThrowsException(String className) {
        // Arrange
        Class<?> superType;
        try {
            superType = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(superType, null));
    }

    @Test
    public void testIsInstanceOf() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"String", "Object"})
    public void testIsInstanceOfThrowsException(String className) {
        // Arrange
        Class<?> type;
        try {
            type = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(type, null));
    }

    @Test
    public void testIsValidState() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.validState(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"false"})
    public void testIsValidStateThrowsException(String value) {
        // Arrange
        boolean expression = Boolean.parseBoolean(value);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.validState(expression));
    }

    @Test
    public void testMatchesPattern() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern("test", "t.*"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testMatchesPatternThrowsException(String value) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(value, "^[0-9]+$"));
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
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY})
    public void testNotNaNThrowsException(double value) {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Validate.notNaN(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    }
