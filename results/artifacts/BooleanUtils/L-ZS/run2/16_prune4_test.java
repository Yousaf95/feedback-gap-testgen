package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BooleanUtils_GenTest {

    @Test
    void testAndWithBooleanArray() {
        // Arrange
        boolean[] array = {true, true, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testAndWithBooleanArrayContainingFalse() {
        // Arrange
        boolean[] array = {true, false, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testAndWithEmptyBooleanArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and(new boolean[0]));
    }

    @Test
    void testAndWithBooleanObjectArray() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE};

        // Act
        Boolean result = BooleanUtils.and(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testAndWithBooleanObjectArrayContainingFalse() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE};

        // Act
        Boolean result = BooleanUtils.and(array);

        // Assert
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testAndWithEmptyBooleanObjectArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and(new Boolean[0]));
    }

    @Test
    void testBooleanValues() {
        // Arrange & Act
        Boolean[] result = BooleanUtils.booleanValues();

        // Assert
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,true",
            "false,false"
    })
    void testCompare(boolean x, boolean y) {
        // Arrange & Act
        int result = BooleanUtils.compare(x, y);

        // Assert
        assertEquals(0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,false",
            "false,true"
    })
    void testCompareWithDifferentValues(boolean x, boolean y) {
        // Arrange & Act
        int result = BooleanUtils.compare(x, y);

        // Assert
        assertEquals(x ? 1 : -1, result);
    }

    @Test
    void testForEach() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        Consumer<Boolean> action = b -> sb.append(b.toString());

        // Act
        BooleanUtils.forEach(action);

        // Assert
        assertEquals("falsetrue", sb.toString());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testIsFalse(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.isFalse(bool);

        // Assert
        assertEquals(Boolean.FALSE.equals(bool), result);
    }

    @Test
    void testIsFalseWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.isFalse(null);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testIsNotFalse(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.isNotFalse(bool);

        // Assert
        assertEquals(!Boolean.FALSE.equals(bool), result);
    }

    @Test
    void testIsNotFalseWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.isNotFalse(null);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testIsNotTrue(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.isNotTrue(bool);

        // Assert
        assertEquals(!Boolean.TRUE.equals(bool), result);
    }

    @Test
    void testIsNotTrueWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.isNotTrue(null);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testIsTrue(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.isTrue(bool);

        // Assert
        assertEquals(Boolean.TRUE.equals(bool), result);
    }

    @Test
    void testIsTrueWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.isTrue(null);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testNegate(Boolean bool) {
        // Arrange & Act
        Boolean result = BooleanUtils.negate(bool);

        // Assert
        assertEquals(bool == null ? null : !bool, result);
    }

    @Test
    void testNegateWithNull() {
        // Arrange & Act
        Boolean result = BooleanUtils.negate(null);

        // Assert
        assertNull(result);
    }

    @Test
    void testOneHotWithBooleanArray() {
        // Arrange
        boolean[] array = {false, true, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testOneHotWithMultipleTrueValuesInBooleanArray() {
        // Arrange
        boolean[] array = {true, true, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testOneHotWithEmptyBooleanArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.oneHot(new boolean[0]));
    }

    @Test
    void testOneHotWithBooleanObjectArray() {
        // Arrange
        Boolean[] array = {false, true, false};

        // Act
        Boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testOneHotWithMultipleTrueValuesInBooleanObjectArray() {
        // Arrange
        Boolean[] array = {true, true, false};

        // Act
        Boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testOneHotWithEmptyBooleanObjectArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.oneHot(new Boolean[0]));
    }

    @Test
    void testOrWithBooleanArray() {
        // Arrange
        boolean[] array = {false, false, true};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testOrWithAllFalseInBooleanArray() {
        // Arrange
        boolean[] array = {false, false, false};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testOrWithEmptyBooleanArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or(new boolean[0]));
    }

    @Test
    void testOrWithBooleanObjectArray() {
        // Arrange
        Boolean[] array = {false, false, true};

        // Act
        Boolean result = BooleanUtils.or(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testOrWithAllFalseInBooleanObjectArray() {
        // Arrange
        Boolean[] array = {false, false, false};

        // Act
        Boolean result = BooleanUtils.or(array);

        // Assert
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testOrWithEmptyBooleanObjectArrayThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or(new Boolean[0]));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testToBoolean(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(bool);

        // Assert
        assertEquals(bool == null ? false : bool, result);
    }

    @Test
    void testToBooleanWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(null);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    void testToBooleanWithInt(int value) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(value);

        // Assert
        assertEquals(value != 0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0",
            "0,1,0",
            "-1,1,-1"
    })
    void testToBooleanWithIntAndTrueFalseValues(int value, int trueValue, int falseValue) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(value, trueValue, falseValue);

        // Assert
        assertEquals(value == trueValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0",
            "null,null,0",
            "0,1,null"
    })
    void testToBooleanWithIntegerAndTrueFalseValues(Integer value, Integer trueValue, Integer falseValue) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(value, trueValue, falseValue);

        // Assert
        assertEquals(value == null ? (trueValue == null) : value.equals(trueValue), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false", "yes", "no", "on", "off"})
    void testToBooleanWithString(String str) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(str);

        // Assert
        assertEquals(Boolean.TRUE.equals(BooleanUtils.toBooleanObject(str)), result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,true,false",
            "false,true,false",
            "yes,yes,no"
    })
    void testToBooleanWithStringAndTrueFalseValues(String str, String trueString, String falseString) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(str, trueString, falseString);

        // Assert
        assertEquals(str.equals(trueString), result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testToBooleanDefaultIfNull(Boolean bool) {
        // Arrange & Act
        boolean result = BooleanUtils.toBooleanDefaultIfNull(bool, !bool);

        // Assert
        assertEquals(bool == null ? !bool : bool, result);
    }

    @Test
    void testToBooleanDefaultIfNullWithNull() {
        // Arrange & Act
        boolean result = BooleanUtils.toBooleanDefaultIfNull(null, true);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    void testToBooleanObjectWithInt(int value) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(value);

        // Assert
        assertEquals(value != 0 ? Boolean.TRUE : Boolean.FALSE, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0,2",
            "0,1,0,2",
            "-1,1,-1,2"
    })
    void testToBooleanObjectWithIntAndTrueFalseNullValues(int value, int trueValue, int falseValue, int nullValue) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(value, trueValue, falseValue, nullValue);

        // Assert
        assertEquals(value == trueValue ? Boolean.TRUE : (value == falseValue ? Boolean.FALSE : (value == nullValue ? null : value)), result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    void testToBooleanObjectWithInteger(Integer value) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(value);

        // Assert
        assertEquals(value == null ? null : (value != 0 ? Boolean.TRUE : Boolean.FALSE), result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0,2",
            "null,null,0,2",
            "0,1,null,2"
    })
    void testToBooleanObjectWithIntegerAndTrueFalseNullValues(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(value, trueValue, falseValue, nullValue);

        // Assert
        assertEquals(value == null ? (trueValue == null ? Boolean.TRUE : (falseValue == null ? Boolean.FALSE : null)) : value.equals(trueValue) ? Boolean.TRUE : (value.equals(falseValue) ? Boolean.FALSE : (value.equals(nullValue) ? null : value)), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false", "yes", "no", "on", "off", "tRuE", "FaLsE", "yEs", "No", "oN", "OfF", "1", "0"})
    void testToBooleanObjectWithString(String str) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(str);

        // Assert
        assertEquals(Boolean.valueOf(str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("on") || str.equals("1")), result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,true,false,null",
            "false,true,false,null",
            "null,true,false,null"
    })
    void testToBooleanObjectWithStringAndTrueFalseNullValues(String str, String trueString, String falseString, String nullString) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(str, trueString, falseString, nullString);

        // Assert
        assertEquals(str == null ? (trueString == null ? Boolean.TRUE : (falseString == null ? Boolean.FALSE : null)) : str.equals(trueString) ? Boolean.TRUE : (str.equals(falseString) ? Boolean.FALSE : (str.equals(nullString) ? null : str)), result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testToInteger(boolean bool) {
        // Arrange & Act
        int result = BooleanUtils.toInteger(bool);

        // Assert
        assertEquals(bool ? 1 : 0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,1,0",
            "false,1,0"
    })
    void testToIntegerWithTrueFalseValues(boolean bool, int trueValue, int falseValue) {
        // Arrange & Act
        int result = BooleanUtils.toInteger(bool, trueValue, falseValue);

        // Assert
        assertEquals(bool ? trueValue : falseValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,1,0,2",
            "false,1,0,2",
            "null,1,0,2"
    })
    void testToIntegerWithBooleanAndTrueFalseNullValues(Boolean bool, int trueValue, int falseValue, int nullValue) {
        // Arrange & Act
        int result = BooleanUtils.toInteger(bool, trueValue, falseValue, nullValue);

        // Assert
        assertEquals(bool == null ? nullValue : (bool ? trueValue : falseValue), result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testToIntegerObject(boolean bool) {
        // Arrange & Act
        Integer result = BooleanUtils.toIntegerObject(bool);

        // Assert
        assertEquals(bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,1,0",
            "false,1,0"
    })
    void testToIntegerObjectWithTrueFalseValues(boolean bool, Integer trueValue, Integer falseValue) {
        // Arrange & Act
        Integer result = BooleanUtils.toIntegerObject(bool, trueValue, falseValue);

        // Assert
        assertEquals(bool ? trueValue : falseValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,1,0,2",
            "false,1,0,2",
            "null,1,0,2"
    })
    void testToIntegerObjectWithBooleanAndTrueFalseNullValues(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
        // Arrange & Act
        Integer result = BooleanUtils.toIntegerObject(bool, trueValue, falseValue, nullValue);

        // Assert
        assertEquals(bool == null ? nullValue : (bool ? trueValue : falseValue), result);
    }

    @ParameterizedTest
    @CsvSource({
            "true,true,false",
            "false,true,false"
    })
    void testToStringWithBooleanAndTrueFalseValues(boolean bool, String trueString, String falseString) {
        // Arrange & Act
        String result = BooleanUtils.toString(bool, trueString, falseString);

        // Assert
        assertEquals(bool ? trueString : falseString, result);
    }

    
    
    
    