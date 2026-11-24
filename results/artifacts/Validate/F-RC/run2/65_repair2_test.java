package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.regex.Pattern;

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
    @ValueSource(doubles = {-1.0, 4.0})
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
    @ValueSource(longs = {1L, 2L, 3L})
    public void testInclusiveBetweenLong(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
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

    
    @Test
    public void testNotEmpty() {
        // Arrange
        Collection<String> collection = Arrays.asList("test", "example");

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    
    @Test
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testExclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 2L));
        }

    
    @Test
        public void testInclusiveBetweenDouble() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testInclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        }

    
    @Test
        public void testIsAssignableFrom() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test"));
        }

    @Test
        public void testMatchesPattern_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNoNullElementsIterable() {
            List<String> list = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(list));
        }

    @Test
        public void testNoNullElementsArray() {
            String[] array = {"a", null, "c"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
        }

    @Test
        public void testExclusiveBetweenLong_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
        }

    @Test
        public void testInclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
        }

    @Test
        public void testInclusiveBetweenLong_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
        }

    @Test
        public void testIsAssignableFromThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    
    @Test
        public void testMatchesPattern_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("test", "^[0-9]+$"));
        }

    @Test
        public void testNoNullElementsIterable_2() {
            Iterable<String> iterable = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(iterable));
        }

    @Test
        public void testNoNullElementsArray_2() {
            String[] array = {"a", null, "c"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    @Test
        public void testNotBlank() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
        }

    
    @Test
        public void testNotEmptyArray() {
            String[] array = {};
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_3() {
            exclusiveBetween(1.0, 2.0, 1.5);
        }

    @Test
        public void testExclusiveBetweenDoubleThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> exclusiveBetween(1.0, 2.0, 1.0));
        }

    @Test
        public void testExclusiveBetweenLong_4() {
            exclusiveBetween(1L, 2L, 1L);
        }

    @Test
        public void testExclusiveBetweenLongThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> exclusiveBetween(1L, 2L, 2L));
        }

    @Test
        public void testInclusiveBetweenDouble_3() {
            inclusiveBetween(1.0, 2.0, 1.5);
        }

    @Test
        public void testInclusiveBetweenLong_4() {
            inclusiveBetween(1L, 2L, 1L);
        }

    @Test
        public void testIsAssignableFromThrowsException_3() {
            assertThrows(IllegalArgumentException.class, () -> isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsValid_2() {
            isValid("test", s -> !s.isEmpty());
        }

    @Test
        public void testIsValidThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> isValid("", s -> !s.isEmpty()));
        }

    @Test
        public void testMatchesPattern_4() {
            matchesPattern("123", "\\d+");
        }

    @Test
        public void testMatchesPatternThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNoNullElementsIterable_3() {
            noNullElements(Arrays.asList(1, 2, 3));
        }

    @Test
        public void testNotEmpty_2() {
            notEmpty(Collections.singletonList("test"));
        }

    @Test
        public void testNotNull_2() {
            assertNotNull("test");
        }
}
