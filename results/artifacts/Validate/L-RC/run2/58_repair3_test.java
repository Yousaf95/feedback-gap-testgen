package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        void testExclusiveBetweenDoubleLowerBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 3.0, 1.0));
        }

    @Test
        void testExclusiveBetweenDoubleUpperBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 3.0, 3.0));
        }

    @Test
        void testExclusiveBetweenLongLowerBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 3L, 1L));
        }

    @Test
        void testExclusiveBetweenLongUpperBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 3L, 3L));
        }

    @Test
        void testExclusiveBetweenComparableLowerBound() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", "a"));
        }

    @Test
        void testExclusiveBetweenComparableUpperBound() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", "c"));
        }

    @Test
        void testExclusiveBetweenComparableWithMessageLowerBound() {
            String message = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.exclusiveBetween("a", "c", "a", message));
            assertEquals(message, exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableWithMessageUpperBound() {
            String message = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.exclusiveBetween("a", "c", "c", message));
            assertEquals(message, exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleLowerBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 3.0, 0.9));
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 3.0, 3.1));
        }

    @Test
        void testInclusiveBetweenLongLowerBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 3L, 0L));
        }

    @Test
        void testInclusiveBetweenLongUpperBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 3L, 4L));
        }

    
    @Test
        void testInclusiveBetweenComparableUpperBound() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("a", "c", "d"));
        }

    @Test
        void testIsAssignableFromNullSuperType() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(null, String.class));
        }

    @Test
        void testIsAssignableFromNullType() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(Object.class, null));
        }

    @Test
        void testIsAssignableFromNotAssignable() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        void testIsAssignableFromWithMessageNotAssignable() {
            String message = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.isAssignableFrom(String.class, Integer.class, message));
            assertEquals(message, exception.getMessage());
        }

    
    @Test
        void testIsInstanceNotAssignable() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(String.class, new Integer(1)));
        }

    @Test
        void testIsInstanceWithMessageNotAssignable() {
            String message = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.isInstanceOf(String.class, new Integer(1), message));
            assertEquals(message, exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithDoubleValue() {
            String message = "Custom message";
            double value = 1.0;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.isTrue(false, message, value));
            assertEquals(String.format(message, Double.valueOf(value)), exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithLongValue() {
            String message = "Custom message";
            long value = 1L;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.isTrue(false, message, value));
            assertEquals(String.format(message, Long.valueOf(value)), exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues() {
            String message = "Custom message %s";
            Object[] values = {"value"};
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.isTrue(false, message, values));
            assertEquals(String.format(message, (Object[]) values), exception.getMessage());
        }

    @Test
        void testMatchesPatternMismatch_2() {
            String pattern = "\\d+";
            CharSequence input = "abc";
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(input, pattern));
        }

    @Test
        void testMatchesPatternWithMessageMismatch() {
            String message = "Custom message";
            String pattern = "\\d+";
            CharSequence input = "abc";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.matchesPattern(input, pattern, message));
            assertEquals(String.format(message, input, pattern), exception.getMessage());
        }

    @Test
        void testNoNullElementsWithNullIterable() {
            assertThrows(NullPointerException.class, () -> Validate.noNullElements((List<String>) null));
        }

    @Test
        void testNoNullElementsWithNullElement() {
            List<String> list = Arrays.asList("a", null, "b");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(list));
        }

    @Test
        void testNoNullElementsWithMessageAndNullElement() {
            String message = "Custom message";
            List<String> list = Arrays.asList("a", null, "b");
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> Validate.noNullElements(list, message));
            assertEquals(String.format(message, 1), exception.getMessage());
        }

    @Test
        void testNoNullElementsEmptyIterable() {
            List<String> list = Collections.emptyList();
            Validate.noNullElements(list); // Should not throw
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenDoubleLowerBound_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 5.0, 1.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenDoubleUpperBound_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 5.0, 5.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongLowerBound_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 5L, 1L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongUpperBound_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 5L, 5L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleLowerBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 5.0, 0.9);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 0.9, 1.0, 5.0), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 5.0, 5.1);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 5.1, 1.0, 5.0), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongLowerBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 5L, 0L);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 0L, 1L, 5L), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongUpperBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 5L, 6L);
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", 6L, 1L, 5L), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "a");
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", "a", "b", "d"), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "e");
            });
            assertEquals(String.format("The value %s is not in the specified inclusive range of %s to %s", "e", "b", "d"), exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableWithMessageLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "a", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableWithMessageUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "e", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    
    @Test
        void testIsAssignableFromWithMessageNotAssignable_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, String.class, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    @Test
        void testIsInstanceWithMessageNotAssignable_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(String.class, 1, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithDoubleValue_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %f", 3.14);
            });
            assertEquals(String.format("Custom message %f", Double.valueOf(3.14)), exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithLongValue_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %d", 42L);
            });
            assertEquals(String.format("Custom message %d", Long.valueOf(42L)), exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %s %s", "value1", "value2");
            });
            assertEquals(String.format("Custom message value1 value2"), exception.getMessage());
        }

    @Test
        void testMatchesPatternMismatch_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "def", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    @Test
        void testNoNullElementsWithNullElement_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(Collections.singletonList(null));
            });
            assertEquals(String.format("The validated collection contains null element at index: %d", 0), exception.getMessage());
        }

    @Test
        void testNoNullElementsWithMessageAndNullElement_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(Collections.singletonList(null), "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testNoNullElementsEmptyIterable_2() {
            assertDoesNotThrow(() -> {
                Validate.noNullElements(Collections.emptyList());
            });
        }

    
    @Test
        void testNotBlankWithEmptyString() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("");
            });
            assertEquals("The validated character sequence is blank", exception.getMessage());
        }

    @Test
        void testNotBlankWithWhitespaceOnly() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("   ");
            });
            assertEquals("The validated character sequence is blank", exception.getMessage());
        }

    @Test
        void testNotEmptyMapEmpty() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(Collections.emptyMap());
            });
            assertEquals("The validated map is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyCollectionEmpty_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(Collections.emptyList());
            });
            assertEquals("The validated collection is empty", exception.getMessage());
        }

    @Test
        void testNotEmptyArrayEmpty_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(new Object[0]);
            });
            assertEquals("The validated array is empty", exception.getMessage());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testInclusiveBetweenDoubleLowerBound_2_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 3.0, 0.5, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_2_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 3.0, 4.0, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongLowerBound_2_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 3L, 0L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongUpperBound_2_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 3L, 4L, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableLowerBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "a", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableUpperBound_2_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween("b", "d", "e", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testIsAssignableFromWithMessageNotAssignable_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, String.class, "Custom message");
            });
            assertEquals(String.format(Validate.DEFAULT_IS_ASSIGNABLE_EX_MESSAGE, ClassUtils.getName(String.class), ClassUtils.getName(Number.class)), exception.getMessage());
        }

    @Test
        void testIsInstanceWithMessageNotAssignable_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(Number.class, "string", "Custom message");
            });
            assertEquals(String.format(Validate.DEFAULT_IS_INSTANCE_OF_EX_MESSAGE, Number.class.getName(), ClassUtils.getName("string")), exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithDoubleValue_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %f", 1.0);
            });
            assertEquals("Custom message 1.0", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithLongValue_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %d", 1L);
            });
            assertEquals("Custom message 1", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues_3() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %s %s", "value1", "value2");
            });
            assertEquals("Custom message value1 value2", exception.getMessage());
        }

    @Test
        void testMatchesPatternMismatch_4() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "def", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testNoNullElementsWithNullElement_3() {
            String[] array = {"a", null, "c"};
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(array);
            });
            assertEquals(String.format(Validate.DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE, 1), exception.getMessage());
        }

    @Test
        void testNoNullElementsWithMessageAndNullElement_3() {
            String[] array = {"a", null, "c"};
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(array, "Custom message %d");
            });
            assertEquals("Custom message 1", exception.getMessage());
        }

    @Test
        void testNoNullElementsEmptyIterable_3() {
            Iterable<String> iterable = Collections.emptyList();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(iterable);
            });
            // The default message for collections is not provided in the public API, so we cannot check it.
        }

    @Test
        void testNotBlankWithEmptyString_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("");
            });
            assertEquals(Validate.DEFAULT_NOT_BLANK_EX_MESSAGE, exception.getMessage());
        }

    @Test
        void testNotBlankWithWhitespaceOnly_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("   ");
            });
            assertEquals(Validate.DEFAULT_NOT_BLANK_EX_MESSAGE, exception.getMessage());
        }

    @Test
        void testNotEmptyMapEmpty_3() {
            Map<String, String> map = new HashMap<>();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(map);
            });
            // The default message for maps is not provided in the public API, so we cannot check it.
        }

    @Test
        void testNotEmptyCollectionEmpty_3() {
            java.util.Collection<String> collection = Collections.emptyList();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(collection);
            });
            // The default message for collections is not provided in the public API, so we cannot check it.
        }

    @Test
        void testNotEmptyArrayEmpty_3() {
            String[] array = {};
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(array);
            });
            assertEquals(Validate.DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE, exception.getMessage());
        }

    @Test
        void testNotNaNNormal_2() {
            assertDoesNotThrow(() -> Validate.notNaN(1.0));
        }

    @Test
        void testNotNaNWithMessageNormal_2() {
            assertDoesNotThrow(() -> Validate.notNaN(1.0, "Custom message"));
        }
}
