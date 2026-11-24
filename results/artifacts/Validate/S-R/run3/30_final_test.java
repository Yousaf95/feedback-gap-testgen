package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Validate_GenTest {

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.5})
    public void testExclusiveBetweenDouble(double value) {
        assertDoesNotThrow(() -> Validate.exclusiveBetween(0, 2, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 3.0})
    public void testExclusiveBetweenDoubleThrowsException(double value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(0, 2, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {5L, 7L})
    public void testExclusiveBetweenLong(long value) {
        assertDoesNotThrow(() -> Validate.exclusiveBetween(3L, 10L, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {-2L, 11L})
    public void testExclusiveBetweenLongThrowsException(long value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween(3L, 10L, value));
    }

    
    
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0})
    public void testInclusiveBetweenDouble(double value) {
        assertDoesNotThrow(() -> Validate.inclusiveBetween(0, 3, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 4.0})
    public void testInclusiveBetweenDoubleThrowsException(double value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(0, 3, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {5L, 8L})
    public void testInclusiveBetweenLong(long value) {
        assertDoesNotThrow(() -> Validate.inclusiveBetween(3L, 10L, value));
    }

    @ParameterizedTest
    @ValueSource(longs = {-2L, 12L})
    public void testInclusiveBetweenLongThrowsException(long value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween(3L, 10L, value));
    }

    
    
    @Test
    public void testFiniteDouble() {
        assertDoesNotThrow(() -> Validate.finite(1.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY})
    public void testFiniteDoubleThrowsException(double value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.finite(value));
    }

    @Test
    public void testIsAssignableFrom() {
        assertDoesNotThrow(() -> Validate.isAssignableFrom(Number.class, Integer.class));
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class})
    public void testIsAssignableFromThrowsException(Class<?> type) {
        assertThrows(IllegalArgumentException.class, () -> Validate.isAssignableFrom(Number.class, type));
    }

    @Test
    public void testIsInstanceOf() {
        assertDoesNotThrow(() -> Validate.isInstanceOf(String.class, "test"));
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testMatchesPattern(String input) {
        assertDoesNotThrow(() -> Validate.matchesPattern(input, "\\b(true|false)\\b"));
    }

    
    
    
    @Test
    public void testNoNullElementsArray() {
        String[] array = {"A", "B"};
        assertDoesNotThrow(() -> Validate.noNullElements(array));
    }

    
    @Test
    public void testNotBlank() {
        String str = "test";
        assertDoesNotThrow(() -> Validate.notBlank(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testNotBlankThrowsException(String str) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notBlank(str));
    }

    
    
    @Test
    public void testNotEmptyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "B");
        assertDoesNotThrow(() -> Validate.notEmpty(map));
    }

    
    @Test
    public void testNotEmptyString() {
        String str = "test";
        assertDoesNotThrow(() -> Validate.notEmpty(str));
    }

    
    @Test
    public void testNotEmptyArray() {
        String[] array = {"A", "B"};
        assertDoesNotThrow(() -> Validate.notEmpty(array));
    }

    
    @Test
    public void testNotNaN() {
        assertDoesNotThrow(() -> Validate.notNaN(1.0));
    }

    
    @Test
    public void testNotNull() {
        String str = "test";
        assertDoesNotThrow(() -> Validate.notNull(str));
    }

    
    
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testValidIndexString(int index) {
        String str = "test";
        assertDoesNotThrow(() -> Validate.validIndex(str, index));
    }

    
    
    
    @Test
    public void testValidState() {
        assertDoesNotThrow(() -> Validate.validState(true));
    }

    }
