package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    
    }
