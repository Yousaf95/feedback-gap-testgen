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
        void testExclusiveBetweenDoubleLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 3.0, 1.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenDoubleUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 3.0, 3.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 3L, 1L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 3L, 3L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(2, 4, 2);
            });
            assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", 2, 2, 4), exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(2, 4, 4);
            });
            assertEquals(String.format("The value %s is not in the specified exclusive range of %s to %s", 4, 2, 4), exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableCustomMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(2, 4, 2, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2.0, 4.0, 1.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2.0, 4.0, 5.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2L, 4L, 1L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2L, 4L, 5L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableLowerBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(3, 5, 2);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 2, 3, 5), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableUpperBoundException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(3, 5, 6);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 6, 3, 5), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableCustomMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(3, 5, 2, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    
    
    @Test
        void testIsAssignableFromCustomMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, String.class, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    
    @Test
        void testIsInstanceCustomMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(Number.class, new Object(), "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testIsTrueFalseException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false);
            });
            assertEquals("The validated expression is false", exception.getMessage());
        }

    
    @Test
        void testIsTrueFalseWithLongMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %d", 1L);
            });
            assertEquals("Custom message 1", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValuesMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %s %d", "test", 1);
            });
            assertEquals("Custom message test 1", exception.getMessage());
        }

    @Test
        void testMatchesPatternNoMatchException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "def");
            });
            assertEquals(String.format("The string %s does not match the pattern %s", "abc", "def"), exception.getMessage());
        }

    @Test
        void testMatchesPatternCustomMessageException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "def", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    
    @Test
        void testNotBlankBlankStringException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenDoubleUpperBoundExceptionWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 5.0, 5.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongUpperBoundExceptionWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 5L, 5L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleUpperBoundExceptionWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 5.0, 6.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongUpperBoundExceptionWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 5L, 6L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testIsAssignableFromCustomMessageExceptionWithNullSuperType() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(null, String.class, "Custom message");
            });
            assertEquals(String.format(Validate.DEFAULT_IS_ASSIGNABLE_EX_MESSAGE, ClassUtils.getName(String.class, "null type"), ClassUtils.getName(null, "null type")), exception.getMessage());
        }

    @Test
        void testIsAssignableFromCustomMessageExceptionWithNullType() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, null, "Custom message");
            });
            assertEquals(String.format(Validate.DEFAULT_IS_ASSIGNABLE_EX_MESSAGE, ClassUtils.getName(null, "null type"), ClassUtils.getName(Number.class, "null type")), exception.getMessage());
        }

    @Test
        void testIsInstanceCustomMessageExceptionWithNullType() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(null, new Object(), "Custom message");
            });
            assertEquals(String.format(Validate.DEFAULT_IS_INSTANCE_OF_EX_MESSAGE, ClassUtils.getName(null, "null type"), ClassUtils.getName(new Object(), "null")), exception.getMessage());
        }

    @Test
        void testIsTrueFalseExceptionWithDoubleMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %f", 1.0);
            });
            assertEquals("Custom message 1.0", exception.getMessage());
        }

    @Test
        void testIsTrueFalseExceptionWithValuesMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %s", "value");
            });
            assertEquals("Custom message value", exception.getMessage());
        }

    @Test
        void testMatchesPatternNoMatchExceptionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "def", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testNoNullElementsArrayNormalCaseWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.noNullElements(new Object[]{1, 2, 3}, "Custom message %d", 0);
            });
        }

    @Test
        void testNoNullElementsCollectionNormalCaseWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.noNullElements(Collections.singletonList(1), "Custom message %d", 0);
            });
        }

    @Test
        void testNotBlankBlankStringExceptionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("   ", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testNotEmptyArrayNormalCaseWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.notEmpty(new Object[]{1}, "Custom message %d", 0);
            });
        }

    @Test
        void testNotEmptyCollectionNormalCaseWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.notEmpty(Collections.singletonList(1), "Custom message %d", 0);
            });
        }

    @Test
        void testNotEmptyCharSequenceNormalCaseWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.notEmpty("abc", "Custom message");
            });
        }

    @Test
        void testIsEmptyMapExceptionWithCustomMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new HashMap<>(), "Custom message %d", 0);
            });
            assertEquals("Custom message 0", exception.getMessage());
        }

    @Test
        void testNotNaNNormalCase() {
            assertDoesNotThrow(() -> {
                Validate.notNaN(1.0);
            });
        }

    @Test
        void testNotNaNWithCustomMessage() {
            assertDoesNotThrow(() -> {
                Validate.notNaN(1.0, "Custom message");
            });
        }
}
