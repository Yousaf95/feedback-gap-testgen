package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
        public void testExclusiveBetweenDoubleNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(2.0, 5.0, 1.0));
        }

    @Test
        public void testExclusiveBetweenLongNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(2L, 5L, 1L));
        }

    @Test
        public void testInclusiveBetweenDoubleNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(2.0, 5.0, 1.0));
        }

    @Test
        public void testInclusiveBetweenLongNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(2L, 5L, 1L));
        }

    @Test
        public void testIsAssignableFromNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(String.class, Integer.class));
        }

    @Test
        public void testIsInstanceOfNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(Integer.class, "test"));
        }

    @Test
        public void testMatchesPatternNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("123", "[a-z]+"));
        }

    @Test
        public void testNoNullElementsCollectionNegative() {
            List<String> list = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(list));
        }

    @Test
        public void testNoNullElementsArrayNegative() {
            String[] array = {"a", null, "c"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    @Test
        public void testNotBlankNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(" "));
        }

    @Test
        public void testNotEmptyCollectionNegative() {
            List<String> list = Collections.emptyList();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(list));
        }

    @Test
        public void testNotEmptyMapNegative() {
            Map<String, String> map = new HashMap<>();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
        }

    @Test
        public void testNotEmptyArrayNegative() {
            String[] array = {};
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
        }

    @Test
        public void testValidIndexCollectionNegative() {
            List<String> list = Arrays.asList("a", "b");
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(list, 2));
        }

    @Test
        public void testValidIndexCharSequenceNegative() {
            String str = "ab";
            assertThrows(StringIndexOutOfBoundsException.class, () -> Validate.validIndex(str, 2));
        }

    @Test
        public void testValidIndexArrayNegative() {
            String[] array = {"a", "b"};
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, 2));
        }

    @Test
        public void testValidStateNegative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.validState(false));
        }
}
