package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Validate_GenTest {

    @Test
    public void testExclusiveBetweenDouble() {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.0));
        assertDoesNotThrow(() -> Validate.exclusiveBetween(1.0, 2.0, 1.5));
    }

    
    @Test
    public void testExclusiveBetweenComparable() {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "A"));
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "C"));
        assertDoesNotThrow(() -> Validate.exclusiveBetween("A", "C", "B"));
    }

    @Test
    public void testFiniteDouble() {
        assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY));
        assertDoesNotThrow(() -> Validate.finite(0.0));
    }

    @Test
    public void testInclusiveBetweenDouble() {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 0.9));
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 2.1));
        assertDoesNotThrow(() -> Validate.inclusiveBetween(1.0, 2.0, 1.0));
        assertDoesNotThrow(() -> Validate.inclusiveBetween(1.0, 2.0, 2.0));
    }

    @Test
    public void testInclusiveBetweenLong() {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 0L));
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 2L, 1L));
        assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 2L, 2L));
    }

    @Test
    public void testInclusiveBetweenComparable() {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "Z"));
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "D"));
        assertDoesNotThrow(() -> Validate.inclusiveBetween("A", "C", "A"));
        assertDoesNotThrow(() -> Validate.inclusiveBetween("A", "C", "C"));
    }

    @Test
    public void testIsAssignableFrom() {
        assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        assertDoesNotThrow(() -> Validate.isAssignableFrom(Object.class, String.class));
    }

    @Test
    public void testIsInstanceOf() {
        assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test"));
        assertDoesNotThrow(() -> Validate.isInstanceOf(Object.class, new Object()));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testExclusiveBetweenComparable_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "A"));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "C"));
        }

    @Test
        public void testFiniteDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.NaN));
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY));
        }

    @Test
        public void testInclusiveBetweenDouble_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testInclusiveBetweenLong_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testInclusiveBetweenComparable_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "Z"));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "a"));
        }

    @Test
        public void testIsAssignableFrom_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "123"));
        }

    @Test
        public void testMatchesPattern() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "[0-9]"));
        }

    
    @Test
        public void testNoNullElementsArray() {
            String[] array = {"A", null, "C"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.5));
        }

    @Test
        public void testExclusiveBetweenComparable_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "A"));
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "C"));
        }

    @Test
        public void testFiniteDouble_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.NaN));
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY));
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
        public void testInclusiveBetweenComparable_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "Z"));
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "C", "a"));
        }

    @Test
        public void testIsAssignableFrom_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "123"));
        }

    @Test
        public void testMatchesPattern_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNoNullElementsArray_2() {
            Object[] array = {1, 2, null};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    @Test
        public void testNoNullElementsCollection() {
            Collection<Object> collection = Arrays.asList(1, 2, null);
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(collection));
        }

    @Test
        public void testNotBlank() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(""));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetweenDouble_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 0.5));
        }

    
    @Test
        public void testFiniteDouble_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.NaN));
        }

    @Test
        public void testInclusiveBetweenDouble_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, -1.0));
        }

    @Test
        public void testInclusiveBetweenLong_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, -1L));
        }

    @Test
        public void testInclusiveBetweenComparable_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("a", "c", "d"));
        }

    @Test
        public void testIsAssignableFrom_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_4() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "not an integer"));
        }

    @Test
        public void testExclusiveBetweenDouble_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testExclusiveBetweenComparable_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("c", "a", "b"));
        }

    @Test
        public void testFiniteDouble_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY));
        }

    @Test
        public void testInclusiveBetweenDouble_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1.0, 2.0, 3.0));
        }

    @Test
        public void testInclusiveBetweenLong_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 2L, 3L));
        }

    @Test
        public void testInclusiveBetweenComparable_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("c", "a", "d"));
        }

    @Test
        public void testIsAssignableFrom_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "not an integer"));
        }

    @Test
        public void testMatchesPattern_3() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNoNullElementsArray_3() {
            Object[] array = {1, 2, null};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    
    @Test
        public void testExclusiveBetweenComparable_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("a", "c", "a"));
        }

    @Test
        public void testFiniteDouble_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.finite(Double.NEGATIVE_INFINITY));
        }

    
    
    
    @Test
        public void testIsAssignableFrom_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOf_3_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "not an integer"));
        }

    @Test
        public void testMatchesPattern_2_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("abc", "\\d+"));
        }

    @Test
        public void testNoNullElementsArray_2_2() {
            Object[] array = {1, null, 3};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    @Test
        public void testNoNullElementsCollection_2() {
            Collection<Object> collection = Arrays.asList(1, 2, null);
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(collection));
        }

    @Test
        public void testNotEmptyMap() {
            Map<String, Object> map = new HashMap<>();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
        }

    @Test
        public void testNotEmptyCharSequence() {
            CharSequence chars = "";
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(chars));
        }

    @Test
        public void testNotEmptyArray() {
            Object[] array = {};
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
        }
}
