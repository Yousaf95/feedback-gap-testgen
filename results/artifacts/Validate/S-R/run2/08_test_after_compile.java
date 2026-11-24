package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_GenTest {

    @Test
    public void testExclusiveBetweenDouble() {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1.0, 2.0, 2.0));
        assertDoesNotThrow(() -> Validate.exclusiveBetween(1.0, 2.0, 1.5));
    }

    @Test
    public void testExclusiveBetweenLong() {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 1L));
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 2L, 2L));
        assertDoesNotThrow(() -> Validate.exclusiveBetween(1L, 2L, 1L + (long) 0.5));
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

    @Test
    public void testIsValidState() {
        assertThrows(IllegalArgumentException.class, () -> Validate.validState(false));
        assertDoesNotThrow(() -> Validate.validState(true));
    }
}
