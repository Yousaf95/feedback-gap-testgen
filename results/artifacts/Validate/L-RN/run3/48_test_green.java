package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

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
    void testExclusiveBetweenDoubleLowerBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 1.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", value, start, end), exception.getMessage());
    }

    @Test
    void testExclusiveBetweenDoubleUpperBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 3.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", value, start, end), exception.getMessage());
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
    void testExclusiveBetweenLongLowerBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 1L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", value, start, end), exception.getMessage());
    }

    @Test
    void testExclusiveBetweenLongUpperBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 3L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", value, start, end), exception.getMessage());
    }

    @Test
    void testFiniteNormalCase() {
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
        assertEquals(String.format("The value is invalid: %f", value), exception.getMessage());
    }

    @Test
    void testFiniteInfinity() {
        // Arrange
        double value = Double.POSITIVE_INFINITY;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
        assertEquals(String.format("The value is invalid: %f", value), exception.getMessage());
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
    void testInclusiveBetweenDoubleBelowLowerBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 0.5;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", value, start, end), exception.getMessage());
    }

    @Test
    void testInclusiveBetweenDoubleAboveUpperBound() {
        // Arrange
        double start = 1.0;
        double end = 3.0;
        double value = 4.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", value, start, end), exception.getMessage());
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
    void testInclusiveBetweenLongBelowLowerBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 0L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", value, start, end), exception.getMessage());
    }

    @Test
    void testInclusiveBetweenLongAboveUpperBound() {
        // Arrange
        long start = 1L;
        long end = 3L;
        long value = 4L;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(start, end, value));
        assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", value, start, end), exception.getMessage());
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

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenComparableNormalCase() {
            Validate.exclusiveBetween("a", "z", "m");
        }

    
    
    @Test
        void testFiniteWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.finite(Double.NaN, "Value is not a number: %f", Double.NaN);
            });
            assertEquals("Value is not a number: NaN", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableNormalCase() {
            Validate.inclusiveBetween("a", "z", "m");
        }

    
    
    @Test
        void testIsAssignableFromWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, String.class, "Cannot assign %s to %s", String.class.getName(), Number.class.getName());
            });
            assertEquals("Cannot assign java.lang.String to java.lang.Number", exception.getMessage());
        }

    @Test
        void testIsInstanceWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(String.class, 123, "Expected type: %s, actual: %s", String.class.getName(), Integer.class.getName());
            });
            assertEquals("Expected type: java.lang.String, actual: java.lang.Integer", exception.getMessage());
        }

    @Test
        void testIsTrueWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Condition failed with value %d", 42);
            });
            assertEquals("Condition failed with value 42", exception.getMessage());
        }

    @Test
        void testMatchesPatternWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "\\d+", "String %s does not match pattern %s", "abc", "\\d+");
            });
            assertEquals("String abc does not match pattern \\d+", exception.getMessage());
        }

    @Test
        void testNoNullElementsArrayWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(new String[]{"a", null, "b"}, "Element at index %d is null", 1);
            });
            assertEquals("Element at index 1 is null", exception.getMessage());
        }

    @Test
        void testNoNullElementsCollectionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(Arrays.asList("a", null, "b"), "Element at index %d is null", 1);
            });
            assertEquals("Element at index 1 is null", exception.getMessage());
        }

    @Test
        void testNotBlankWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("", "String is blank: %s", "");
            });
            assertEquals("String is blank: ", exception.getMessage());
        }

    @Test
        void testNotEmptyArrayWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new String[]{}, "Array is empty");
            });
            assertEquals("Array is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCollectionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(Collections.emptyList(), "Collection is empty");
            });
            assertEquals("Collection is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyMapWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new HashMap<>(), "Map is empty");
            });
            assertEquals("Map is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCharSequenceWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty("", "String is empty: %s", "");
            });
            assertEquals("String is empty: ", exception.getMessage());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testNotNaNWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.notNaN(Double.NaN, "Value is NaN"));
            assertEquals("Value is NaN", exception.getMessage());
        }

    @Test
        void testNotNullNormalCase() {
            Object obj = new Object();
            assertSame(obj, Validate.notNull(obj));
        }

    
    @Test
        void testValidStateNormalCase() {
            Validate.validState(true);
        }

    
    @Test
        void testNoNullElementsArrayEmptyCase() {
            String[] array = {};
            assertSame(array, Validate.noNullElements(array));
        }

    @Test
        void testNotEmptyArrayWithCustomMessage_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(new String[0], "Array is empty"));
            assertEquals("Array is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCollectionWithCustomMessage_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyList(), "Collection is empty"));
            assertEquals("Collection is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyMapWithCustomMessage_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(new HashMap<>(), "Map is empty"));
            assertEquals("Map is empty", exception.getMessage());
        }

    @Test
        void testValidIndexArrayNormalCase() {
            String[] array = {"a", "b"};
            assertSame(array, Validate.validIndex(array, 1));
        }

    @Test
        void testValidIndexCharSequenceNormalCase() {
            String str = "ab";
            assertEquals(str, Validate.validIndex(str, 1));
        }

    
    }
