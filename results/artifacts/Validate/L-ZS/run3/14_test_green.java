package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Validate_GenTest {

    @Test
    void testExclusiveBetweenDoubleNormalCase() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    
    
    @Test
    void testExclusiveBetweenLongNormalCase() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 2L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    
    
    @Test
    void testFiniteNormalCase() {
        // Arrange
        double value = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.finite(value));
    }

    
    
    @Test
    void testInclusiveBetweenDoubleNormalCase() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @Test
    void testInclusiveBetweenDoubleLowerBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 1.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @Test
    void testInclusiveBetweenDoubleUpperBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 3.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    
    
    @Test
    void testInclusiveBetweenLongNormalCase() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 2L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @Test
    void testInclusiveBetweenLongLowerBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 1L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    @Test
    void testInclusiveBetweenLongUpperBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 3L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
    }

    
    
    @Test
    void testIsAssignableFromNormalCase() {
        // Arrange
        Class<?> superType = Number.class;
        Class<?> type = Integer.class;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isAssignableFrom(superType, type));
    }

    
    @Test
    void testIsInstanceNormalCase() {
        // Arrange
        Class<?> type = String.class;
        Object obj = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(type, obj));
    }

    
    @Test
    void testIsTrueNormalCase() {
        // Arrange
        boolean expression = true;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isTrue(expression));
    }

    
    @Test
    void testMatchesPatternNormalCase() {
        // Arrange
        CharSequence input = "test123";
        String pattern = "\\w+";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern(input, pattern));
    }

    
    @Test
    void testNoNullElementsArrayNormalCase() {
        // Arrange
        String[] array = {"a", "b", "c"};

        // Act & Assert
        assertDoesNotThrow(() -> Validate.noNullElements(array));
    }

    
    @Test
    void testNoNullElementsCollectionNormalCase() {
        // Arrange
        List<String> collection = Arrays.asList("a", "b", "c");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.noNullElements(collection));
    }

    
    @Test
    void testNotBlankNormalCase() {
        // Arrange
        String chars = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notBlank(chars));
    }

    
    
    @Test
    void testNotEmptyArrayNormalCase() {
        // Arrange
        String[] array = {"a", "b"};

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(array));
    }

    
    @Test
    void testNotEmptyCollectionNormalCase() {
        // Arrange
        List<String> collection = Arrays.asList("a", "b");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    
    
    
    @Test
    void testNotEmptyCharSequenceNormalCase() {
        // Arrange
        String chars = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(chars));
    }

    }
