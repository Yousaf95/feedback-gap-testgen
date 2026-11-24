package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;

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
}
