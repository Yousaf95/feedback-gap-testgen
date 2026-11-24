package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAllNotNullData")
    public void testAllNotNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.allNotNull(values));
    }

    private static Stream<Arguments> provideAllNotNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, true},
            {new Object[]{null, 2, 3}, false},
            {new Object[]{}, true},
            {new Object[]{null}, false}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAllNullData")
    public void testAllNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.allNull(values));
    }

    private static Stream<Arguments> provideAllNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, false},
            {new Object[]{null, null, null}, true},
            {new Object[]{}, true},
            {new Object[]{null}, true}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAnyNotNullData")
    public void testAnyNotNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNotNull(values));
    }

    private static Stream<Arguments> provideAnyNotNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, true},
            {new Object[]{null, null, null}, false},
            {new Object[]{}, false},
            {new Object[]{null}, false}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAnyNullData")
    public void testAnyNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNull(values));
    }

    private static Stream<Arguments> provideAnyNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, false},
            {new Object[]{null, null, null}, true},
            {new Object[]{}, true},
            {new Object[]{null}, true}
        }).map(Arguments::arguments);
    }

    @Test
    public void testClone() {
        String original = "test";
        String clone = ObjectUtils.clone(original);
        assertEquals(original, clone);
        assertNotSame(original, clone);
    }

    @Test
    public void testCloneIfPossible() {
        String original = "test";
        String clone = ObjectUtils.cloneIfPossible(original);
        assertEquals(original, clone);
        assertNotSame(original, clone);

        Object nullObject = null;
        assertNull(ObjectUtils.cloneIfPossible(nullObject));
    }

    @ParameterizedTest
    @MethodSource("provideCompareData")
    public void testCompare(T c1, T c2, boolean nullGreater, int expected) {
        assertEquals(expected, ObjectUtils.compare(c1, c2, nullGreater));
    }

    private static Stream<Arguments> provideCompareData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), false, -1},
            {Integer.valueOf(2), Integer.valueOf(1), false, 1},
            {Integer.valueOf(1), Integer.valueOf(1), false, 0},
            {null, null, false, 0},
            {null, Integer.valueOf(1), false, -1},
            {Integer.valueOf(1), null, false, 1},
            {null, null, true, 0},
            {null, Integer.valueOf(1), true, 1},
            {Integer.valueOf(1), null, true, -1}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideDefaultIfNullData")
    public void testDefaultIfNull(T object, T defaultValue, T expected) {
        assertEquals(expected, ObjectUtils.defaultIfNull(object, defaultValue));
    }

    private static Stream<Arguments> provideDefaultIfNullData() {
        return Arrays.stream(new Object[][]{
            {null, "default", "default"},
            {"value", "default", "value"}
        }).map(Arguments::arguments);
    }

    @Test
    public void testFirstNonNull() {
        String result = ObjectUtils.firstNonNull(null, "default");
        assertEquals("default", result);

        result = ObjectUtils.firstNonNull("value", null);
        assertEquals("value", result);

        result = ObjectUtils.firstNonNull(null, null, "default");
        assertEquals("default", result);
    }

    @ParameterizedTest
    @MethodSource("provideGetClassData")
    public void testGetClass(T object, Class<?> expected) {
        assertEquals(expected, ObjectUtils.getClass(object));
    }

    private static Stream<Arguments> provideGetClassData() {
        return Arrays.stream(new Object[][]{
            {null, null},
            {"test", String.class},
            (Object) new Integer(1), Integer.class
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideGetFirstNonNullData")
    public void testGetFirstNonNull(Supplier<T>... suppliers) {
        assertEquals("value", ObjectUtils.getFirstNonNull(() -> null, () -> "value"));
    }

    private static Stream<Arguments> provideGetFirstNonNullData() {
        return Arrays.stream(new Object[][]{
            {arguments((Supplier<String>) () -> null, (Supplier<String>) () -> "value")},
            {arguments((Supplier<String>) () -> "value", (Supplier<String>) () -> null)}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideGetIfNullData")
    public void testGetIfNull(T object, Supplier<T> defaultSupplier, T expected) {
        assertEquals(expected, ObjectUtils.getIfNull(object, defaultSupplier));
    }

    private static Stream<Arguments> provideGetIfNullData() {
        return Arrays.stream(new Object[][]{
            {null, () -> "default", "default"},
            {"value", () -> "default", "value"}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideHashCodeHexData")
    public void testHashCodeHex(Object object, String expected) {
        assertEquals(expected, ObjectUtils.hashCodeHex(object));
    }

    private static Stream<Arguments> provideHashCodeHexData() {
        return Arrays.stream(new Object[][]{
            {null, "0"},
            {"test", Integer.toHexString("test".hashCode())},
            (Object) new Integer(1), Integer.toHexString(Integer.valueOf(1).hashCode())
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideIdentityToStringData")
    public void testIdentityToString(Object object, String expected) {
        assertEquals(expected, ObjectUtils.identityToString(object));
    }

    private static Stream<Arguments> provideIdentityToStringData() {
        return Arrays.stream(new Object[][]{
            {null, null},
            {"test", "test@" + Integer.toHexString(System.identityHashCode("test"))},
            (Object) new Integer(1), "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(Integer.valueOf(1)))
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayData")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
