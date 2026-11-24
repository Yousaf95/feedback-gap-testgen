package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        public void testExclusiveBetween_double_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 4.9);
            });
        }

    @Test
        public void testExclusiveBetween_double_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 10.1);
            });
        }

    @Test
        public void testExclusiveBetween_double_message_valueLessThanStart() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 4.9, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testExclusiveBetween_double_message_valueGreaterThanEnd() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 10.1, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testFinite_double_nan() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.finite(Double.NaN);
            });
        }

    @Test
        public void testFinite_double_infinite() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.finite(Double.POSITIVE_INFINITY);
            });
        }

    @Test
        public void testFinite_double_message_nan() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.finite(Double.NaN, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testFinite_double_message_infinite() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.finite(Double.POSITIVE_INFINITY, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testInclusiveBetween_double_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 4.9);
            });
        }

    @Test
        public void testInclusiveBetween_double_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 10.1);
            });
        }

    @Test
        public void testInclusiveBetween_double_message_valueLessThanStart() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 4.9, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testInclusiveBetween_double_message_valueGreaterThanEnd() {
            String expectedMessage = "Custom message";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 10.1, expectedMessage);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testIsTrue_expressionFalse() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false);
            });
        }

    @Test
        public void testIsTrue_expressionFalse_messageDoubleValue() {
            String expectedMessage = "Custom message: 5.0";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, expectedMessage, 5.0);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testIsTrue_expressionFalse_messageLongValue() {
            String expectedMessage = "Custom message: 5";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, expectedMessage, 5L);
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
        public void testIsTrue_expressionFalse_messageValues() {
            String expectedMessage = "Custom message: value1, value2";
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, expectedMessage, "value1", "value2");
            });
            assertEquals(expectedMessage, exception.getMessage());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testExclusiveBetween_double_valueLessThanStart_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 3.0);
            });
        }

    @Test
        public void testExclusiveBetween_double_valueGreaterThanEnd_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5.0, 10.0, 12.0);
            });
        }

    @Test
        public void testExclusiveBetween_long_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5L, 10L, 3L);
            });
        }

    @Test
        public void testExclusiveBetween_long_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(5L, 10L, 12L);
            });
        }

    @Test
        public void testInclusiveBetween_double_valueLessThanStart_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 3.0);
            });
        }

    @Test
        public void testInclusiveBetween_double_valueGreaterThanEnd_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5.0, 10.0, 12.0);
            });
        }

    @Test
        public void testInclusiveBetween_long_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5L, 10L, 3L);
            });
        }

    @Test
        public void testInclusiveBetween_long_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(5L, 10L, 12L);
            });
        }

    @Test
        public void testExclusiveBetween_Comparable_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(3, 10, 1);
            });
        }

    @Test
        public void testExclusiveBetween_Comparable_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(3, 10, 12);
            });
        }

    @Test
        public void testInclusiveBetween_Comparable_valueLessThanStart() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(3, 10, 1);
            });
        }

    @Test
        public void testInclusiveBetween_Comparable_valueGreaterThanEnd() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(3, 10, 12);
            });
        }

    @Test
        public void testIsTrue_expressionFalse_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false);
            });
        }

    @Test
        public void testIsTrue_expressionFalse_messageDoubleValue_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "The value is %f", 3.14);
            });
        }

    @Test
        public void testIsTrue_expressionFalse_messageLongValue_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "The value is %d", 42L);
            });
        }

    @Test
        public void testIsTrue_expressionFalse_messageValues_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false, "The values are %s and %s", "one", "two");
            });
        }

    @Test
        public void testMatchesPattern_inputDoesNotMatchPattern() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("hello", "^\\d+$");
            });
        }

    @Test
        public void testMatchesPattern_messageInputDoesNotMatchPattern() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.matchesPattern("hello", "^\\d+$", "The input %s does not match the pattern %s", "hello", "^\\d+$");
            });
        }

    @Test
        public void testNoNullElements_collectionWithNullElement() {
            assertThrows(IllegalArgumentException.class, () -> {
                Iterable<String> iterable = Arrays.asList("one", null, "three");
                Validate.noNullElements(iterable);
            });
        }

    @Test
        public void testNoNullElements_arrayWithNullElement() {
            assertThrows(IllegalArgumentException.class, () -> {
                String[] array = {"one", null, "three"};
                Validate.noNullElements(array);
            });
        }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @CsvSource({
            "5.0, 10.0, 3.0",
            "5.0, 10.0, 12.0"
        })
        public void testExclusiveBetween_double_valueLessThanStart_3(double start, double end, double value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5.0, 10.0, 3.0",
            "5.0, 10.0, 12.0"
        })
        public void testExclusiveBetween_double_valueGreaterThanEnd_3(double start, double end, double value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5L, 10L, 3L",
            "5L, 10L, 12L"
        })
        public void testExclusiveBetween_long_valueLessThanStart_2(long start, long end, long value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5L, 10L, 3L",
            "5L, 10L, 12L"
        })
        public void testExclusiveBetween_long_valueGreaterThanEnd_2(long start, long end, long value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.exclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5.0, 10.0, 3.0",
            "5.0, 10.0, 12.0"
        })
        public void testInclusiveBetween_double_valueLessThanStart_3(double start, double end, double value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5.0, 10.0, 3.0",
            "5.0, 10.0, 12.0"
        })
        public void testInclusiveBetween_double_valueGreaterThanEnd_3(double start, double end, double value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5L, 10L, 3L",
            "5L, 10L, 12L"
        })
        public void testInclusiveBetween_long_valueLessThanStart_2(long start, long end, long value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(start, end, value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "5L, 10L, 3L",
            "5L, 10L, 12L"
        })
        public void testInclusiveBetween_long_valueGreaterThanEnd_2(long start, long end, long value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.inclusiveBetween(start, end, value);
            });
        }

    
    
    
    
    @Test
        public void testIsTrue_expressionFalse_3() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(false);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "false, 3.14",
            "false, 42L"
        })
        public void testIsTrue_expressionFalse_messageDoubleValue_3(boolean expression, double value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(expression, "Value is not true: {}", value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "false, 42L",
            "false, 3.14"
        })
        public void testIsTrue_expressionFalse_messageLongValue_3(boolean expression, long value) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(expression, "Value is not true: {}", value);
            });
        }

    @ParameterizedTest
        @CsvSource({
            "false, 3.14",
            "false, 42L"
        })
        public void testIsTrue_expressionFalse_messageValues_3(boolean expression, double value, long value2) {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.isTrue(expression, "Value is not true: {} and {}", value, value2);
            });
        }

    @Test
        public void testNoNullElements_collectionWithNullElement_2() {
            List<String> list = Arrays.asList("a", null, "c");
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(list);
            });
        }

    @Test
        public void testNoNullElements_arrayWithNullElement_2() {
            String[] array = {"a", null, "c"};
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.noNullElements(array);
            });
        }

    @Test
        public void testNotBlank_charSequenceIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.notBlank("   ");
            });
        }

    @Test
        public void testNotEmpty_collectionIsEmpty() {
            List<String> list = Arrays.asList();
            assertThrows(IllegalArgumentException.class, () -> {
                Validate.notEmpty(list);
            });
        }
}
