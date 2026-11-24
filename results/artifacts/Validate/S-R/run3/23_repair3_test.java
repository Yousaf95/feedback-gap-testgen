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
    @ValueSource(strings = {"A", "C"})
    public <T extends Comparable<T>> void testExclusiveBetweenComparable(T value) {
        assertDoesNotThrow(() -> Validate.exclusiveBetween("A", "D", value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Z", "B"})
    public <T extends Comparable<T>> void testExclusiveBetweenComparableThrowsException(T value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "D", value));
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

    @ParameterizedTest
    @ValueSource(strings = {"A", "D"})
    public <T extends Comparable<T>> void testInclusiveBetweenComparable(T value) {
        assertDoesNotThrow(() -> Validate.inclusiveBetween("A", "E", value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Z", "B"})
    public <T extends Comparable<T>> void testInclusiveBetweenComparableThrowsException(T value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.inclusiveBetween("A", "E", value));
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
    @ValueSource(objects = {123})
    public void testIsInstanceOfThrowsException(Object obj) {
        assertThrows(IllegalArgumentException.class, () -> Validate.isInstanceOf(String.class, obj));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testMatchesPattern(String input) {
        assertDoesNotThrow(() -> Validate.matchesPattern(input, "\\b(true|false)\\b"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abc"})
    public void testMatchesPatternThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> Validate.matchesPattern(input, "\\d+"));
    }

    @Test
    public void testNoNullElementsCollection() {
        Collection<String> collection = Arrays.asList("A", "B");
        assertDoesNotThrow(() -> Validate.noNullElements(collection));
    }

    @ParameterizedTest
    @ValueSource(iterables = {Collections.singletonList(null)})
    public <T extends Iterable<?>> void testNoNullElementsCollectionThrowsException(T iterable) {
        assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(iterable));
    }

    @Test
    public void testNoNullElementsArray() {
        String[] array = {"A", "B"};
        assertDoesNotThrow(() -> Validate.noNullElements(array));
    }

    @ParameterizedTest
    @ValueSource(strings = {null})
    public <T> void testNoNullElementsArrayThrowsException(T[] array) {
        assertThrows(IllegalArgumentException.class, () -> Validate.noNullElements(array));
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
    public void testNotEmptyCollection() {
        Collection<String> collection = Arrays.asList("A", "B");
        assertDoesNotThrow(() -> Validate.notEmpty(collection));
    }

    @ParameterizedTest
    @ValueSource(iterables = {Collections.emptyList()})
    public <T extends Iterable<?>> void testNotEmptyCollectionThrowsException(T iterable) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(iterable));
    }

    @Test
    public void testNotEmptyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "B");
        assertDoesNotThrow(() -> Validate.notEmpty(map));
    }

    @ParameterizedTest
    @ValueSource(maps = {Collections.emptyMap()})
    public <T extends Map<?, ?>> void testNotEmptyMapThrowsException(T map) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(map));
    }

    @Test
    public void testNotEmptyString() {
        String str = "test";
        assertDoesNotThrow(() -> Validate.notEmpty(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testNotEmptyStringThrowsException(String str) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(str));
    }

    @Test
    public void testNotEmptyArray() {
        String[] array = {"A", "B"};
        assertDoesNotThrow(() -> Validate.notEmpty(array));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    public <T> void testNotEmptyArrayThrowsException(T[] array) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(array));
    }

    @Test
    public void testNotNaN() {
        assertDoesNotThrow(() -> Validate.notNaN(1.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY})
    public void testNotNaNTrowsException(double value) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notNaN(value));
    }

    @Test
    public void testNotNull() {
        String str = "test";
        assertDoesNotThrow(() -> Validate.notNull(str));
    }

    @ParameterizedTest
    @ValueSource(objects = {null})
    public <T> void testNotNullThrowsException(T object) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notNull(object));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testValidIndexCollection(int index) {
        Collection<String> collection = Arrays.asList("A", "B");
        assertDoesNotThrow(() -> Validate.validIndex(collection, index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    public <T extends Iterable<?>> void testValidIndexCollectionThrowsException(T collection, int index) {
        assertThrows(IllegalArgumentException.class, () -> Validate.validIndex(collection, index));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testValidIndexString(int index) {
        String str = "test";
        assertDoesNotThrow(() -> Validate.validIndex(str, index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    public <T extends CharSequence> void testValidIndexStringThrowsException(T chars, int index) {
        assertThrows(IllegalArgumentException.class, () -> Validate.validIndex(chars, index));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public <T> void testValidIndexArray(int index) {
        String[] array = {"A", "B"};
        assertDoesNotThrow(() -> Validate.validIndex(array, index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    public <T> void testValidIndexArrayThrowsException(T[] array, int index) {
        assertThrows(IllegalArgumentException.class, () -> Validate.validIndex(array, index));
    }

    @Test
    public void testValidState() {
        assertDoesNotThrow(() -> Validate.validState(true));
    }

    @ParameterizedTest
    @ValueSource(objects = {false})
    public void testValidStateThrowsException(boolean expression) {
        assertThrows(IllegalArgumentException.class, () -> Validate.validState(expression));
    }
}
