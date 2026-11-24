package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Validate_GenTest {

    @Test
    void testExclusiveBetweenDoubleNormal() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @Test
    void testExclusiveBetweenDoubleLowerBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 1.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals("The value 1.0 is not in the specified exclusive range of 1.0 to 3.0", exception.getMessage());
    }

    @Test
    void testExclusiveBetweenDoubleUpperBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 3.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals("The value 3.0 is not in the specified exclusive range of 1.0 to 3.0", exception.getMessage());
    }

    @Test
    void testExclusiveBetweenLongNormal() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 2L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.exclusiveBetween(start, end, value));
    }

    @Test
    void testExclusiveBetweenLongLowerBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 1L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals("The value 1 is not in the specified exclusive range of 1 to 3", exception.getMessage());
    }

    @Test
    void testExclusiveBetweenLongUpperBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 3L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals("The value 3 is not in the specified exclusive range of 1 to 3", exception.getMessage());
    }

    @Test
    void testInclusiveBetweenDoubleNormal() {
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
    void testInclusiveBetweenLongNormal() {
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
    void testFiniteNormal() {
        // Arrange
        double value = 2.0;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.finite(value));
    }

    @Test
    void testFiniteNaN() {
        // Arrange
        double value = Double.NaN;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
        assertEquals("The value is invalid: NaN", exception.getMessage());
    }

    @Test
    void testFiniteInfinity() {
        // Arrange
        double value = Double.POSITIVE_INFINITY;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
        assertEquals("The value is invalid: Infinity", exception.getMessage());
    }

    @Test
    void testIsAssignableFromTrue() {
        // Arrange
        Class<?> superType = Number.class;
        Class<?> type = Integer.class;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isAssignableFrom(superType, type));
    }

    @Test
    void testIsAssignableFromFalse() {
        // Arrange
        Class<?> superType = String.class;
        Class<?> type = Integer.class;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(superType, type));
        assertEquals("Cannot assign a java.lang.Integer to a java.lang.String", exception.getMessage());
    }

    @Test
    void testIsInstanceOfTrue() {
        // Arrange
        Class<?> type = String.class;
        Object obj = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(type, obj));
    }

    @Test
    void testIsInstanceOfFalse() {
        // Arrange
        Class<?> type = Integer.class;
        Object obj = "test";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(type, obj));
        assertEquals("Expected type: java.lang.Integer, actual: java.lang.String", exception.getMessage());
    }

    @Test
    void testIsTrueTrue() {
        // Arrange
        boolean expression = true;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isTrue(expression));
    }

    @Test
    void testIsTrueFalse() {
        // Arrange
        boolean expression = false;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(expression));
        assertEquals("The validated expression is false", exception.getMessage());
    }

    @Test
    void testMatchesPatternMatch() {
        // Arrange
        CharSequence input = "test";
        String pattern = "t.*";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern(input, pattern));
    }

    @Test
    void testMatchesPatternNoMatch() {
        // Arrange
        CharSequence input = "test";
        String pattern = "a.*";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(input, pattern));
        assertEquals("The string test does not match the pattern a.*", exception.getMessage());
    }

    @Test
    void testNoNullElementsArrayNormal() {
        // Arrange
        String[] array = {"a", "b", "c"};

        // Act & Assert
        assertDoesNotThrow(() -> Validate.noNullElements(array));
    }

    @Test
    void testNoNullElementsArrayWithNull() {
        // Arrange
        String[] array = {"a", null, "c"};

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        assertEquals("The validated array contains null element at index: 1", exception.getMessage());
    }

    @Test
    void testNoNullElementsCollectionNormal() {
        // Arrange
        List<String> collection = Arrays.asList("a", "b", "c");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.noNullElements(collection));
    }

    @Test
    void testNoNullElementsCollectionWithNull() {
        // Arrange
        List<String> collection = Arrays.asList("a", null, "c");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(collection));
        assertEquals("The validated collection contains null element at index: 1", exception.getMessage());
    }

    @Test
    void testNotBlankNormal() {
        // Arrange
        CharSequence chars = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notBlank(chars));
    }

    @Test
    void testNotBlankEmptyString() {
        // Arrange
        CharSequence chars = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(chars));
        assertEquals("The validated character sequence is blank", exception.getMessage());
    }

    @Test
    void testNotBlankWhitespace() {
        // Arrange
        CharSequence chars = "   ";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(chars));
        assertEquals("The validated character sequence is blank", exception.getMessage());
    }

    @Test
    void testNotEmptyArrayNormal() {
        // Arrange
        String[] array = {"a", "b"};

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(array));
    }

    @Test
    void testNotEmptyArrayEmpty() {
        // Arrange
        String[] array = {};

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
        assertEquals("The validated array is empty", exception.getMessage());
    }

    @Test
    void testNotEmptyCollectionNormal() {
        // Arrange
        List<String> collection = Arrays.asList("a", "b");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    @Test
    void testNotEmptyCollectionEmpty() {
        // Arrange
        List<String> collection = Collections.emptyList();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(collection));
        assertEquals("The validated collection is empty", exception.getMessage());
    }

    @Test
    void testNotEmptyMapNormal() {
        // Arrange
        Map<String, String> map = Map.of("a", "1", "b", "2");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(map));
    }

    @Test
    void testNotEmptyMapEmpty() {
        // Arrange
        Map<String, String> map = Collections.emptyMap();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
        assertEquals("The validated map is empty", exception.getMessage());
    }

    @Test
    void testNotEmptyCharSequenceNormal() {
        // Arrange
        CharSequence chars = "test";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(chars));
    }

    @Test
    void testNotEmptyCharSequenceEmpty() {
        // Arrange
        CharSequence chars = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(chars));
        assertEquals("The validated character sequence is empty", exception.getMessage());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenComparableNormal() {
            Validate.exclusiveBetween(1, 3, 2);
        }

    @Test
        void testExclusiveBetweenComparableLowerBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1, 3, 1);
            });
            assertEquals("The value 1 is not in the specified exclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableUpperBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1, 3, 3);
            });
            assertEquals("The value 3 is not in the specified exclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableNormal() {
            Validate.inclusiveBetween(1, 3, 2);
        }

    @Test
        void testInclusiveBetweenComparableLowerBound() {
            Validate.inclusiveBetween(1, 3, 1);
        }

    @Test
        void testInclusiveBetweenComparableUpperBound() {
            Validate.inclusiveBetween(1, 3, 3);
        }

    @Test
        void testNoNullElementsArrayWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(new String[]{"a", null, "b"}, "Element at index %d is null");
            });
            assertEquals("Element at index 1 is null", exception.getMessage());
        }

    @Test
        void testNoNullElementsCollectionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(Arrays.asList("a", null, "b"), "Element at index %d is null");
            });
            assertEquals("Element at index 1 is null", exception.getMessage());
        }

    @Test
        void testNotEmptyArrayWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new String[]{}, "The array must not be empty");
            });
            assertEquals("The array must not be empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCollectionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(Collections.emptyList(), "The collection must not be empty");
            });
            assertEquals("The collection must not be empty", exception.getMessage());
        }

    @Test
        void testNotEmptyMapWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new HashMap<>(), "The map must not be empty");
            });
            assertEquals("The map must not be empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCharSequenceWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty("", "The char sequence must not be empty");
            });
            assertEquals("The char sequence must not be empty", exception.getMessage());
        }

    @Test
        void testNotBlankWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("   ", "The char sequence must not be blank");
            });
            assertEquals("The char sequence must not be blank", exception.getMessage());
        }

    @Test
        void testValidIndexArrayNormal() {
            Validate.validIndex(new String[]{"a", "b", "c"}, 1);
        }

    
    
    @Test
        void testValidIndexCollectionNormal() {
            Validate.validIndex(Arrays.asList("a", "b", "c"), 1);
        }

    
    
    @Test
        void testValidIndexCharSequenceNormal() {
            Validate.validIndex("abc", 1);
        }

    
    }
