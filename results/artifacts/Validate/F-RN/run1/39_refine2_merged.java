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
            public void testInclusiveBetween_long() {
                assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 0L));
                assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(1L, 5L, 6L));
                assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 1L));
                assertDoesNotThrow(() -> Validate.inclusiveBetween(1L, 5L, 5L));
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
        public void testValidIndex_charSequence() {
            CharSequence chars = "ab";
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, 2));
            assertDoesNotThrow(() -> Validate.validIndex(chars, 0));
            assertDoesNotThrow(() -> Validate.validIndex(chars, 1));
        }

    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetween_double_negative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(2.0, 5.0, 1.0));
        }

    @Test
        public void testExclusiveBetween_double_positive() {
            assertDoesNotThrow(() -> Validate.exclusiveBetween(2.0, 5.0, 3.0));
        }

    @Test
        public void testExclusiveBetween_long_negative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(2L, 5L, 1L));
        }

    @Test
        public void testExclusiveBetween_long_positive() {
            assertDoesNotThrow(() -> Validate.exclusiveBetween(2L, 5L, 3L));
        }

    @Test
        public void testInclusiveBetween_double_negative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(2.0, 5.0, 1.0));
        }

    @Test
        public void testInclusiveBetween_double_positive() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(2.0, 5.0, 3.0));
        }

    @Test
        public void testInclusiveBetween_long_negative() {
            assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(2L, 5L, 1L));
        }

    @Test
        public void testInclusiveBetween_long_positive() {
            assertDoesNotThrow(() -> Validate.inclusiveBetween(2L, 5L, 3L));
        }

    @Test
        public void testMatchesPattern_valid() {
            assertDoesNotThrow(() -> Validate.matchesPattern("hello", "h.*o"));
        }

    @Test
        public void testMatchesPattern_invalid() {
            assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern("world", "h.*o"));
        }

    @Test
        public void testNoNullElements_array_valid() {
            String[] array = {"a", "b", "c"};
            assertDoesNotThrow(() -> Validate.noNullElements(array));
        }

    @Test
        public void testNoNullElements_array_invalid() {
            String[] array = {"a", null, "c"};
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
        }

    @Test
        public void testNoNullElements_collection_valid() {
            Collection<String> collection = Arrays.asList("a", "b", "c");
            assertDoesNotThrow(() -> Validate.noNullElements(collection));
        }

    @Test
        public void testNoNullElements_collection_invalid() {
            Collection<String> collection = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(collection));
        }

    @Test
        public void testNotEmpty_array_valid() {
            String[] array = {"a", "b", "c"};
            assertDoesNotThrow(() -> Validate.notEmpty(array));
        }

    @Test
        public void testNotEmpty_array_invalid() {
            String[] array = {};
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
        }

    @Test
        public void testNotEmpty_collection_valid() {
            Collection<String> collection = Arrays.asList("a", "b", "c");
            assertDoesNotThrow(() -> Validate.notEmpty(collection));
        }

    @Test
        public void testNotEmpty_collection_invalid() {
            Collection<String> collection = Collections.emptyList();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(collection));
        }

    @Test
        public void testNotEmpty_map_valid() {
            Map<String, String> map = new HashMap<>();
            map.put("key1", "value1");
            map.put("key2", "value2");
            assertDoesNotThrow(() -> Validate.notEmpty(map));
        }

    @Test
        public void testNotEmpty_map_invalid() {
            Map<String, String> map = Collections.emptyMap();
            assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
        }

    @Test
        public void testNotBlank_valid() {
            assertDoesNotThrow(() -> Validate.notBlank("hello"));
        }

    @Test
        public void testNotBlank_invalid() {
            assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(""));
        }
}
