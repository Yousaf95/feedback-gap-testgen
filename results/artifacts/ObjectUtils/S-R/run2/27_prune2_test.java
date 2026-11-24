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

    
    @ParameterizedTest
    @MethodSource("provideAllNullData")
    public void testAllNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.allNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAnyNotNullData")
    public void testAnyNotNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNotNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAnyNullData")
    public void testAnyNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNull(values));
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
    public <T extends Comparable<? super T>> void testCompare(T c1, T c2, boolean nullGreater, int expected) {
        assertEquals(expected, ObjectUtils.compare(c1, c2, nullGreater));
    }

    
    @ParameterizedTest
    @MethodSource("provideDefaultIfNullData")
    public <T> void testDefaultIfNull(T object, T defaultValue, T expected) {
        assertEquals(expected, ObjectUtils.defaultIfNull(object, defaultValue));
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
    public <T> void testGetClass(T object, Class<?> expected) {
        assertEquals(expected, ObjectUtils.getClass(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsArrayData")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
