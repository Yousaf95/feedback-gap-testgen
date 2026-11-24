package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class Validate_GenTest {

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0, 1.5",
        "-1.0, 0.0, -0.5"
    })
    public void testExclusiveBetween_double(double start, double end, double value) {
        Validate.exclusiveBetween(start, end, value);
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0, 1.5",
        "-1.0, 0.0, -0.5"
    })
    public void testExclusiveBetween_double_message(double start, double end, double value) {
        Validate.exclusiveBetween(start, end, value, "Value %s is not between %s and %s");
    }

    
    
    
    
    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0",
        "-1.0, 0.0"
    })
    public void testFinite_double(double value) {
        Validate.finite(value);
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0",
        "-1.0, 0.0"
    })
    public void testFinite_double_message(double value) {
        Validate.finite(value, "Value %f is not finite");
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0, 1.5",
        "-1.0, 0.0, -0.5"
    })
    public void testInclusiveBetween_double(double start, double end, double value) {
        Validate.inclusiveBetween(start, end, value);
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 2.0, 1.5",
        "-1.0, 0.0, -0.5"
    })
    public void testInclusiveBetween_double_message(double start, double end, double value) {
        Validate.inclusiveBetween(start, end, value, "Value %s is not between %s and %s");
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @ParameterizedTest
    @CsvSource({
        "Hello, [a-zA-Z]+",
        "123, \\d+"
    })
    public void testMatchesPattern(CharSequence input, String pattern) {
        Validate.matchesPattern(input, pattern);
    }

    @ParameterizedTest
    @CsvSource({
        "Hello, [a-zA-Z]+",
        "123, \\d+"
    })
    public void testMatchesPattern_message(CharSequence input, String pattern) {
        Validate.matchesPattern(input, pattern, "Value %s does not match pattern %s");
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
            public void testExclusiveBetween_long() {
                assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 5L, 0L));
                assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 5L, 6L));
                assertDoesNotThrow(() -> Validate.exclusiveBetween(1L, 5L, 2L));
            }

    @Test
            public void testExclusiveBetween_long_message() {
                IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 5L, 0L, "Invalid value: %d"));
                assertEquals("Invalid value: 0", ex.getMessage());
                
                ex = assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(1L, 5L, 6L, "Invalid value: %d"));
                assertEquals("Invalid value: 6", ex.getMessage());
            }

    @Test
            public void testInclusiveBetween_long() {
                assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 0L));
                assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 6L));
                assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 1L));
                assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 5L));
            }

    @Test
            public void testInclusiveBetween_long_message() {
                IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 0L, "Invalid value: %d"));
                assertEquals("Invalid value: 0", ex.getMessage());
                
                ex = assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 6L, "Invalid value: %d"));
                assertEquals("Invalid value: 6", ex.getMessage());
            }

    
    
    
    
    
    
    
    
    
    
    @Test
        public void testValidIndex_array() {
            String[] array = {"a", "b"};
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, 2));
            assertDoesNotThrow(() -> Validate.validIndex(array, 0));
            assertDoesNotThrow(() -> Validate.validIndex(array, 1));
        }

    @Test
        public void testValidIndex_array_message() {
            String[] array = {"a", "b"};
            IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, -1, "Invalid index: %d"));
            assertEquals("Invalid index: -1", ex.getMessage());
            
            ex = assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, 2, "Invalid index: %d"));
            assertEquals("Invalid index: 2", ex.getMessage());
        }

    
    
    @Test
        public void testValidIndex_charSequence() {
            CharSequence chars = "ab";
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, 2));
            assertDoesNotThrow(() -> Validate.validIndex(chars, 0));
            assertDoesNotThrow(() -> Validate.validIndex(chars, 1));
        }

    @Test
        public void testValidIndex_charSequence_message() {
            CharSequence chars = "ab";
            IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, -1, "Invalid index: %d"));
            assertEquals("Invalid index: -1", ex.getMessage());
            
            ex = assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, 2, "Invalid index: %d"));
            assertEquals("Invalid index: 2", ex.getMessage());
        }

    
    
    
    
    @Test
        public void testValidState() {
            assertDoesNotThrow(() -> Validate.validState(true));
            
            assertThrows(IllegalArgumentException.class, () -> Validate.validState(false));
        }

    @Test
        public void testValidState_message() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validate.validState(false, "Invalid state: %b"));
            assertEquals("Invalid state: false", ex.getMessage());
        }
}
