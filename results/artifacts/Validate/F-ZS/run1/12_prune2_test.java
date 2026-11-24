package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        "1L, 2L, 1L",
        "-1L, 0L, -1L"
    })
    public void testExclusiveBetween_long(long start, long end, long value) {
        Validate.exclusiveBetween(start, end, value);
    }

    @ParameterizedTest
    @CsvSource({
        "1L, 2L, 1L",
        "-1L, 0L, -1L"
    })
    public void testExclusiveBetween_long_message(long start, long end, long value) {
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
        "1L, 2L, 1L",
        "-1L, 0L, -1L"
    })
    public void testInclusiveBetween_long(long start, long end, long value) {
        Validate.inclusiveBetween(start, end, value);
    }

    @ParameterizedTest
    @CsvSource({
        "1L, 2L, 1L",
        "-1L, 0L, -1L"
    })
    public void testInclusiveBetween_long_message(long start, long end, long value) {
        Validate.inclusiveBetween(start, end, value, "Value %s is not between %s and %s");
    }

    
    
    @ParameterizedTest
    @CsvSource({
        "Object.class, String.class",
        "Integer.class, Number.class"
    })
    public void testIsAssignableFrom(Class<?> superType, Class<?> type) {
        Validate.isAssignableFrom(superType, type);
    }

    @ParameterizedTest
    @CsvSource({
        "Object.class, String.class",
        "Integer.class, Number.class"
    })
    public void testIsAssignableFrom_message(Class<?> superType, Class<?> type) {
        Validate.isAssignableFrom(superType, type, "Expected %s to be assignable from %s");
    }

    @ParameterizedTest
    @CsvSource({
        "String.class, \"Hello\"",
        "Integer.class, 123"
    })
    public void testIsInstanceOf(Class<?> type, Object obj) {
        Validate.isInstanceOf(type, obj);
    }

    @ParameterizedTest
    @CsvSource({
        "String.class, \"Hello\"",
        "Integer.class, 123"
    })
    public void testIsInstanceOf_message(Class<?> type, Object obj) {
        Validate.isInstanceOf(type, obj, "Expected %s to be an instance of %s");
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

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3], [1, 2, null]",
        "[\"a\", \"b\", \"c\"], [\"a\", null, \"c\"]"
    })
    public void testNoNullElements_Iterable(Iterable<?> iterable) {
        Validate.noNullElements(iterable);
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3], [1, 2, null]",
        "[\"a\", \"b\", \"c\"], [\"a\", null, \"c\"]"
    })
    public void testNoNullElements_Iterable_message(Iterable<?> iterable) {
        Validate.noNullElements(iterable, "Collection contains null element at index: %d");
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3], [1, 2, null]",
        "[\"a\", \"b\", \"c\"], [\"a\", null, \"c\"]"
    })
    public void testNoNullElements_Array(Object[] array) {
        Validate.noNullElements(array);
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3], [1, 2, null]",
        "[\"a\", \"b\", \"c\"], [\"a\", null, \"c\"]"
    })
    public void testNoNullElements_Array_message(Object[] array) {
        Validate.noNullElements(array, "Array contains null element at index: %d");
    }

    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testNotBlank(CharSequence chars) {
        Validate.notBlank(chars);
    }

    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testNotBlank_message(CharSequence chars) {
        Validate.notBlank(chars, "Character sequence is blank");
    }

    
    
    
    
    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testNotEmpty_CharSequence(CharSequence chars) {
        Validate.notEmpty(chars);
    }

    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testNotEmpty_CharSequence_message(CharSequence chars) {
        Validate.notEmpty(chars, "Character sequence is empty");
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3]",
        "[]"
    })
    public void testNotEmpty_Array(Object[] array) {
        Validate.notEmpty(array);
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3]",
        "[]"
    })
    public void testNotEmpty_Array_message(Object[] array) {
        Validate.notEmpty(array, "Array is empty");
    }

    
    
    @ParameterizedTest
    @CsvSource({
        "null",
        "\"Hello\""
    })
    public void testNotNull_Object(Object object) {
        assertThrows(NullPointerException.class, () -> Validate.notNull(object));
    }

    @ParameterizedTest
    @CsvSource({
        "null",
        "\"Hello\""
    })
    public void testNotNull_message_Object(Object object) {
        assertThrows(NullPointerException.class, () -> Validate.notNull(object, "Object is null"));
    }

    
    
    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testValidIndex_CharSequence(CharSequence chars, int index) {
        Validate.validIndex(chars, index);
    }

    @ParameterizedTest
    @CsvSource({
        "\"Hello World\"",
        ""
    })
    public void testValidIndex_CharSequence_message(CharSequence chars, int index) {
        assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(chars, index, "Invalid index: %d"));
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3]",
        "[]"
    })
    public void testValidIndex_Array(Object[] array, int index) {
        Validate.validIndex(array, index);
    }

    @ParameterizedTest
    @CsvSource({
        "[1, 2, 3]",
        "[]"
    })
    public void testValidIndex_Array_message(Object[] array, int index) {
        assertThrows(IndexOutOfBoundsException.class, () -> Validate.validIndex(array, index, "Invalid index: %d"));
    }

    @ParameterizedTest
    @CsvSource({
        "true",
        "false"
    })
    public void testValidState(boolean expression) {
        Validate.validState(expression);
    }

    @ParameterizedTest
    @CsvSource({
        "true, \"Hello\"",
        "false, \"World\""
    })
    public void testValidState_message(boolean expression, Object... values) {
        Validate.validState(expression, "State is false");
    }
}
