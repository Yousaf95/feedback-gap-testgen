package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.Validate.*;

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
    @ValueSource(longs = {1L, 2L, 3L})
    public void testInclusiveBetweenLong(long value) {
        // Arrange
        long start = 0L;
        long end = 4L;

        // Act & Assert
        assertDoesNotThrow(() -> Validate.inclusiveBetween(start, end, value));
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

    
    
    
    @Test
    public void testIsInstanceOf() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
    }

    
    @Test
    public void testIsValidState() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.validState(true));
    }

    
    @Test
    public void testMatchesPattern() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.matchesPattern("test", "t.*"));
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
    @ValueSource(strings = {"123", "abc"})
    public void testNotNull() {
        // Arrange

        // Act & Assert
        assertDoesNotThrow(() -> Validate.notNull("test"));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
        }

    @Test
        public void testExclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
        }

    
    @Test
        public void testInclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
        }

    @Test
        public void testInclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
        }

    @Test
        public void testInclusiveBetweenComparable() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("a", "c", "d"));
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
        public void testNoNullElementsList() {
            List<String> list = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(list));
        }

    @Test
        public void testNotBlank_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(""));
        }

    @Test
        public void testNotEmptyList() {
            List<String> list = Arrays.asList();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(list));
        }

    @Test
        public void testNotNaN_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notNaN(Double.NaN));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testExclusiveBetweenLong_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testExclusiveBetweenLongThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 0L, "Invalid value"));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 3L, "Invalid value"));
        }

    
    
    @Test
        public void testInclusiveBetweenDouble_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testInclusiveBetweenLong_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testInclusiveBetweenDouble_2_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(1.0, 2.0, 1.5));
        }

    @Test
        public void testInclusiveBetweenLong_2_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 2L, 1L));
        }

    
    @Test
        public void testInclusiveBetweenComparable_2_2() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween("a", "c", "a"));
        }

    @Test
        public void testIsAssignableFrom_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsAssignableFrom_2_2() {
            assertDoesNotThrow(() -> Validate.isAssignableFrom(Object.class, String.class));
        }

    @Test
        public void testIsAssignableFromThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class, "Invalid assignment"));
        }

    @Test
        public void testIsInstanceOf_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test"));
        }

    @Test
        public void testIsInstanceOf_2_2() {
            assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
        }

    @Test
        public void testIsInstanceOfThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test", "Invalid instance"));
        }

    @Test
        public void testMatchesPattern_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testMatchesPattern_2_2() {
            assertDoesNotThrow(() -> Validate.matchesPattern("123", "\\d+"));
        }

    @Test
        public void testMatchesPatternThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+", "Invalid pattern"));
        }

    @Test
        public void testNotEmptyCollection() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyList()));
        }

    @Test
        public void testNotEmptyMap() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(new HashMap<>()));
        }

    @Test
        public void testNotEmptyThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyList(), "Collection is empty"));
        }

    @Test
        public void testNoNullElementsList_2() {
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
        public void testExclusiveBetweenDouble_3() {
            exclusiveBetween(1.0, 2.0, 1.5);
        }

    @Test
        public void testExclusiveBetweenLong_4() {
            exclusiveBetween(1L, 2L, 1L);
        }

    @Test
        public void testExclusiveBetweenComparable_2() {
            exclusiveBetween("A", "C", "B");
        }

    @Test
        public void testInclusiveBetweenDouble_4() {
            inclusiveBetween(1.0, 2.0, 1.5);
        }

    @Test
        public void testInclusiveBetweenLong_4() {
            inclusiveBetween(1L, 2L, 1L);
        }

    @Test
        public void testInclusiveBetweenComparable_2() {
            inclusiveBetween("A", "C", "B");
        }

    @Test
        public void testIsAssignableFrom_3() {
            isAssignableFrom(String.class, new Object());
        }

    @Test
        public void testIsInstanceOf_4() {
            isInstanceOf(Object.class, new Object());
        }

    @Test
        public void testMatchesPattern_4() {
            matchesPattern("abc", "a.c");
        }

    @Test
        public void testNotEmptyCollection_2() {
            notEmpty(Arrays.asList(1));
        }

    @Test
        public void testNotEmptyMap_2() {
            notEmpty(Collections.singletonMap("key", "value"));
        }

    @Test
        public void testNotEmptyCharSequence() {
            notEmpty("abc");
        }

    @Test
        public void testNoNullElementsList_3() {
            noNullElements(Arrays.asList(1, 2));
        }

    @Test
        public void testNoNullElementsArray_2() {
            noNullElements(new Integer[]{1, 2});
        }

    @Test
        public void testValidIndexCollection() {
            validIndex(Arrays.asList(1), 0);
        }

    @Test
        public void testValidIndexCharSequence() {
            validIndex("abc", 1);
        }

    @Test
        public void testExclusiveBetweenDoubleThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> exclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testExclusiveBetweenLongThrowsException_3() {
            assertThrows(IllegalArgumentException.class, () -> exclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testExclusiveBetweenComparableThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> exclusiveBetween("A", "C", "D"));
        }

    @Test
        public void testInclusiveBetweenDoubleThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> inclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testInclusiveBetweenLongThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> inclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testInclusiveBetweenComparableThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> inclusiveBetween("A", "C", "D"));
        }

    @Test
        public void testIsAssignableFromThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> isAssignableFrom(String.class, new Object()));
        }

    @Test
        public void testIsInstanceOfThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> isInstanceOf(Object.class, null));
        }

    @Test
        public void testMatchesPatternThrowsException_2() {
            assertThrows(IllegalArgumentException.class, () -> matchesPattern("abc", "a["));
        }

    @Test
        public void testNotEmptyCollectionThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> notEmpty(Collections.emptyList()));
        }

    @Test
        public void testNotEmptyMapThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> notEmpty(Collections.emptyMap()));
        }

    @Test
        public void testNotEmptyCharSequenceThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> notEmpty(""));
        }

    @Test
        public void testNoNullElementsListThrowsException() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, null));
            assertThrows(IllegalArgumentException.class, () -> noNullElements(list));
        }

    @Test
        public void testNoNullElementsArrayThrowsException() {
            Integer[] array = {1, null};
            assertThrows(IllegalArgumentException.class, () -> noNullElements(array));
        }

    @Test
        public void testValidIndexCollectionThrowsException() {
            List<Integer> list = Arrays.asList(1);
            assertThrows(IndexOutOfBoundsException.class, () -> validIndex(list, 1));
        }

    @Test
        public void testValidIndexCharSequenceThrowsException() {
            String str = "abc";
            assertThrows(IndexOutOfBoundsException.class, () -> validIndex(str, 3));
        }
}
