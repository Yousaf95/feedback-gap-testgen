package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

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
    void testIsTrueNormal() {
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
    void testMatchesPatternNormal() {
        // Arrange
        String input = "abc";
        String pattern = "a.*";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern(input, pattern));
    }

    @Test
    void testMatchesPatternMismatch() {
        // Arrange
        String input = "def";
        String pattern = "a.*";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(input, pattern));
        assertEquals("The string def does not match the pattern a.*", exception.getMessage());
    }

    @Test
    void testNotEmptyArrayNormal() {
        // Arrange
        String[] array = {"a"};

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
        List<String> collection = Collections.singletonList("a");

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
    void testNotNullNormal() {
        // Arrange
        String object = "a";

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull(object, "Object must not be null"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testIsAssignableFromNormal() {
            Validate.isAssignableFrom(Number.class, Integer.class);
        }

    @Test
        void testIsAssignableFromMismatch() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(String.class, Integer.class);
            });
            assertTrue(exception.getMessage().contains("Cannot assign a java.lang.Integer to a java.lang.String"));
        }

    @Test
        void testIsInstanceOfNormal() {
            Validate.isInstanceOf(Integer.class, 10);
        }

    @Test
        void testIsInstanceOfMismatch() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(String.class, 10);
            });
            assertTrue(exception.getMessage().contains("Expected type: java.lang.String, actual: java.lang.Integer"));
        }

    @Test
        void testNoNullElementsArrayNormal() {
            String[] array = {"a", "b", "c"};
            Validate.noNullElements(array);
        }

    @Test
        void testNoNullElementsArrayWithNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                String[] array = {"a", null, "c"};
                Validate.noNullElements(array);
            });
            assertTrue(exception.getMessage().contains("The validated array contains null element at index: 1"));
        }

    @Test
        void testNoNullElementsCollectionNormal() {
            List<String> list = Arrays.asList("a", "b", "c");
            Validate.noNullElements(list);
        }

    @Test
        void testNoNullElementsCollectionWithNull() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                List<String> list = Arrays.asList("a", null, "c");
                Validate.noNullElements(list);
            });
            assertTrue(exception.getMessage().contains("The validated collection contains null element at index: 1"));
        }

    @Test
        void testNotEmptyMapNormal() {
            Validate.notEmpty(Collections.singletonMap("key", "value"));
        }

    @Test
        void testNotEmptyMapEmpty() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(Collections.emptyMap());
            });
            assertTrue(exception.getMessage().contains("The validated map is empty"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testNotBlankNormal() {
            String input = "Hello";
            assertTrue(Validate.notBlank(input) == input);
        }

    @Test
        void testNotBlankEmptyString() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(""));
        }

    @Test
        void testNotBlankWhitespaceOnly() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank("   "));
        }

    
    @Test
        void testNoNullElementsCollectionWithNullElement() {
            List<String> list = new ArrayList<>();
            list.add("Hello");
            list.add(null);
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(list));
        }

    @Test
        void testNotEmptyArrayCustomMessage() {
            String[] array = {};
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.notEmpty(array, "Custom message for empty array"));
            assertTrue(exception.getMessage().equals("Custom message for empty array"));
        }

    @Test
        void testNoNullElementsArrayWithNullElementCustomMessage() {
            String[] array = {"Hello", null};
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.noNullElements(array, "Custom message for null element at index %d", 1));
            assertTrue(exception.getMessage().equals("Custom message for null element at index 1"));
        }

    @Test
        void testValidIndexArrayNormal() {
            String[] array = {"Hello"};
            assertTrue(Validate.validIndex(array, 0) == array);
        }

    
    }
