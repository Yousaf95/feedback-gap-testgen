package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
}
