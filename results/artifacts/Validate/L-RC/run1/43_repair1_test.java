package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        void testExclusiveBetweenDoubleLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 3.0, 1.0);
            });
            assertEquals("The value 1.0 is not in the specified exclusive range of 1.0 to 3.0", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenDoubleUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1.0, 3.0, 3.0);
            });
            assertEquals("The value 3.0 is not in the specified exclusive range of 1.0 to 3.0", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 3L, 1L);
            });
            assertEquals("The value 1 is not in the specified exclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenLongUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(1L, 3L, 3L);
            });
            assertEquals("The value 3 is not in the specified exclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableLowerBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(2, 4, 2);
            });
            assertEquals("The value 2 is not in the specified exclusive range of 2 to 4", exception.getMessage());
        }

    @Test
        void testExclusiveBetweenComparableUpperBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(2, 4, 4);
            });
            assertEquals("The value 4 is not in the specified exclusive range of 2 to 4", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 3.0, 0.9);
            });
            assertEquals("The value 0.9 is not in the specified inclusive range of 1.0 to 3.0", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1.0, 3.0, 3.1);
            });
            assertEquals("The value 3.1 is not in the specified inclusive range of 1.0 to 3.0", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongLowerBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 3L, 0L);
            });
            assertEquals("The value 0 is not in the specified inclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenLongUpperBound_2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(1L, 3L, 4L);
            });
            assertEquals("The value 4 is not in the specified inclusive range of 1 to 3", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableLowerBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2, 4, 1);
            });
            assertEquals("The value 1 is not in the specified inclusive range of 2 to 4", exception.getMessage());
        }

    @Test
        void testInclusiveBetweenComparableUpperBound() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(2, 4, 5);
            });
            assertEquals("The value 5 is not in the specified inclusive range of 2 to 4", exception.getMessage());
        }

    @Test
        void testIsAssignableFromNullSuperType() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(null, String.class);
            });
            assertEquals("Cannot assign a java.lang.String to a null type", exception.getMessage());
        }

    @Test
        void testIsAssignableFromNullType() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, null);
            });
            assertEquals("Cannot assign a null type to a java.lang.Number", exception.getMessage());
        }

    @Test
        void testIsAssignableFromFalseWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isAssignableFrom(Number.class, String.class, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testIsInstanceOfFalseWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isInstanceOf(String.class, 123, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    
    @Test
        void testIsTrueFalseWithLongValue() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %d", 123L);
            });
            assertEquals("Custom message 123", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "Custom message %s %d", "test", 456);
            });
            assertEquals("Custom message test 456", exception.getMessage());
        }

    @Test
        void testMatchesPatternNoMatchWithMessage() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("abc", "^def$", "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testNotNaNValue() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notNaN(Double.NaN);
            });
            assertEquals("The validated value is not a number", exception.getMessage());
        }

    @Test
        void testNotNullDeprecated() {
            Exception exception = assertThrows(NullPointerException.class, () -> {
                Validate.notNull(null);
            });
            assertEquals("The validated object is null", exception.getMessage());
        }

    @Test
        void testNotNullWithMessage() {
            Exception exception = assertThrows(NullPointerException.class, () -> {
                Validate.notNull(null, "Custom message");
            });
            assertEquals("Custom message", exception.getMessage());
        }

    @Test
        void testValidIndexCollectionLowerBound() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                Validate.validIndex(list, -1);
            });
            assertEquals("The validated collection index is invalid: -1", exception.getMessage());
        }

    @Test
        void testValidIndexCollectionUpperBound() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                Validate.validIndex(list, 3);
            });
            assertEquals("The validated collection index is invalid: 3", exception.getMessage());
        }

    @Test
        void testValidIndexCharSequenceLowerBound() {
            String str = "abc";
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                Validate.validIndex(str, -1);
            });
            assertEquals("The validated character sequence index is invalid: -1", exception.getMessage());
        }

    @Test
        void testValidIndexCharSequenceUpperBound() {
            String str = "abc";
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                Validate.validIndex(str, 3);
            });
            assertEquals("The validated character sequence index is invalid: 3", exception.getMessage());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenDoubleLowerBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 3.0, 1.0, "Custom message"));
        }

    @Test
        void testExclusiveBetweenDoubleUpperBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 3.0, 3.0, "Custom message"));
        }

    @Test
        void testExclusiveBetweenLongLowerBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 3L, 1L, "Custom message"));
        }

    @Test
        void testExclusiveBetweenLongUpperBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 3L, 3L, "Custom message"));
        }

    @Test
        void testInclusiveBetweenDoubleLowerBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 3.0, 0.9, "Custom message"));
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 3.0, 3.1, "Custom message"));
        }

    @Test
        void testInclusiveBetweenLongLowerBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 3L, 0L, "Custom message"));
        }

    @Test
        void testInclusiveBetweenLongUpperBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 3L, 4L, "Custom message"));
        }

    @Test
        void testIsAssignableFromFalseWithMessage_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(Number.class, String.class, "Custom message"));
        }

    @Test
        void testIsInstanceOfFalseWithMessage_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(String.class, 123, "Custom message"));
        }

    @Test
        void testIsTrueFalseWithLongValue_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false, "Custom message %d", 123L));
        }

    @Test
        void testIsTrueFalseWithValues_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isTrue(false, "Custom message %s", "value"));
        }

    @Test
        void testMatchesPatternNoMatchWithMessage_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "def", "Custom message"));
        }

    @Test
        void testNotNaNValue_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notNaN(Double.NaN, "Custom message"));
        }

    @Test
        void testNotNullDeprecated_2() {
            assertThrows(NullPointerException.class, () -> Validate.notNull(null));
        }

    @Test
        void testNotNullWithMessage_2() {
            assertThrows(NullPointerException.class, () -> Validate.notNull(null, "Custom message"));
        }

    @Test
        void testValidIndexCollectionLowerBound_2() {
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.emptyList(), -1));
        }

    @Test
        void testValidIndexCollectionUpperBound_2() {
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.singletonList("a"), 1));
        }

    @Test
        void testValidIndexCharSequenceLowerBound_2() {
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex("", -1));
        }

    @Test
        void testValidIndexCharSequenceUpperBound_2() {
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex("a", 1));
        }

    @Test
        void testValidStateFalse() {
            assertThrows(IllegalStateException.class, () -> Validate.validState(false));
        }

    @Test
        void testValidStateFalseWithMessage() {
            assertThrows(IllegalStateException.class, () -> Validate.validState(false, "Custom message"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testExclusiveBetweenDoubleUpperBound_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 5.0, 5.0));
        }

    @Test
        void testExclusiveBetweenLongLowerBound_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 5L, 1L));
        }

    @Test
        void testInclusiveBetweenDoubleUpperBound_3_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(1.0, 5.0, 5.0));
        }

    @Test
        void testInclusiveBetweenLongLowerBound_3_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 1L));
        }

    @Test
        void testExclusiveBetweenComparableLowerBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", (Comparable<String>) "a"));
        }

    @Test
        void testExclusiveBetweenComparableUpperBound_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", (Comparable<String>) "c"));
        }

    @Test
        void testInclusiveBetweenComparableLowerBound_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween("a", "c", (Comparable<String>) "a"));
        }

    @Test
        void testInclusiveBetweenComparableUpperBound_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween("a", "c", (Comparable<String>) "c"));
        }

    @Test
        void testIsAssignableFromFalseWithMessage_3() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isAssignableFrom(String.class, Integer.class, "Custom message %s", "Integer"));
            assertEquals("Custom message Integer", exception.getMessage());
        }

    @Test
        void testIsInstanceOfFalseWithMessage_3() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isInstanceOf(String.class, 123, "Custom message %s", "Integer"));
            assertEquals("Custom message Integer", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithLongValue_3() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isTrue(false, "Custom message %s", 123L));
            assertEquals("Custom message 123", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues_3() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isTrue(false, "Custom message %s and %s", "first", "second"));
            assertEquals("Custom message first and second", exception.getMessage());
        }

    @Test
        void testMatchesPatternNoMatchWithMessage_3() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.matchesPattern("abc", "^def$", "Custom message %s", "pattern"));
            assertEquals("Custom message pattern", exception.getMessage());
        }

    @Test
        void testNotNaNValue_3() {
            assertDoesNotThrow(() -> Validate.notNaN(1.0));
        }

    @Test
        void testNotNullDeprecated_3() {
            assertDoesNotThrow(() -> Validate.notNull(new Object()));
        }

    @Test
        void testNotNullWithMessage_3() {
            assertDoesNotThrow(() -> Validate.notNull(new Object(), "Custom message %s", "object"));
        }

    @Test
        void testValidIndexCollectionLowerBound_3() {
            List<String> list = new ArrayList<>(List.of("a", "b", "c"));
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, -1));
        }

    @Test
        void testValidIndexCharSequenceUpperBound_3() {
            String str = "abc";
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(str, 3));
        }

    @Test
        void testExclusiveBetweenDoubleLowerBound_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 5.0, 1.0, "Custom message"));
        }

    @Test
        void testInclusiveBetweenLongUpperBound_4() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 5L, "Custom message"));
        }

    @Test
        void testExclusiveBetweenComparableLowerBound_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", (Comparable<String>) "a", "Custom message %s", "a"));
        }

    @Test
        void testInclusiveBetweenComparableUpperBound_3() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween("a", "c", (Comparable<String>) "c", "Custom message %s", "c"));
        }

    @Test
        void testIsAssignableFromFalseWithMessage_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isAssignableFrom(String.class, Integer.class, "Custom message %s and %s", "Integer", "String"));
            assertEquals("Custom message Integer and String", exception.getMessage());
        }

    @Test
        void testIsInstanceOfFalseWithMessage_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isInstanceOf(String.class, 123, "Custom message %s and %s", "Integer", "String"));
            assertEquals("Custom message Integer and String", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithLongValue_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isTrue(false, "Custom message %s and %s", 123L, "value"));
            assertEquals("Custom message 123 and value", exception.getMessage());
        }

    @Test
        void testIsTrueFalseWithValues_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.isTrue(false, "Custom message %s, %s and %s", "first", "second", "third"));
            assertEquals("Custom message first, second and third", exception.getMessage());
        }

    @Test
        void testMatchesPatternNoMatchWithMessage_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.matchesPattern("abc", "^def$", "Custom message %s and %s", "pattern", "input"));
            assertEquals("Custom message pattern and input", exception.getMessage());
        }

    @Test
        void testNotNaNValue_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.notNaN(Double.NaN, "Custom message %s", "value"));
            assertEquals("Custom message value", exception.getMessage());
        }

    @Test
        void testNotNullDeprecated_4() {
            assertDoesNotThrow(() -> Validate.notNull(new Object()));
        }

    @Test
        void testNotNullWithMessage_4() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> Validate.notNull(null, "Custom message %s", "object"));
            assertEquals("Custom message object", exception.getMessage());
        }

    @Test
        void testValidIndexCollectionLowerBound_4() {
            List<String> list = new ArrayList<>(List.of("a", "b", "c"));
            IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, 
                () -> Validate.validIndex(list, -1, "Custom message %s", "-1"));
            assertEquals("Custom message -1", exception.getMessage());
        }

    @Test
        void testValidIndexCharSequenceUpperBound_4() {
            String str = "abc";
            IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, 
                () -> Validate.validIndex(str, 3, "Custom message %s", "3"));
            assertEquals("Custom message 3", exception.getMessage());
        }

    @Test
        void testValidStateFalse_2() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, 
                () -> Validate.validState(false));
            assertEquals("The validated state is false", exception.getMessage());
        }

    @Test
        void testValidStateFalseWithMessage_3() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, 
                () -> Validate.validState(false, "Custom message %s", "state"));
            assertEquals("Custom message state", exception.getMessage());
        }
}
