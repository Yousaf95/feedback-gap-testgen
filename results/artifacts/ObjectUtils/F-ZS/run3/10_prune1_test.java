package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAllNotNullCases")
    public void testAllNotNull(Object... values) {
        assertTrue(ObjectUtils.allNotNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAnyNotNullCases")
    public void testAnyNotNull(Object... values) {
        assertTrue(ObjectUtils.anyNotNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAllNullCases")
    public void testAllNull(Object... values) {
        assertTrue(ObjectUtils.allNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAnyNullCases")
    public void testAnyNull(Object... values) {
        assertTrue(ObjectUtils.anyNull(values));
    }

    
    @Test
    public void testClone() {
        String original = "test";
        String cloned = ObjectUtils.clone(original);
        assertEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    public void testCloneIfPossible() {
        String original = "test";
        String cloned = ObjectUtils.cloneIfPossible(original);
        assertEquals(original, cloned);
        assertNotSame(original, cloned);

        Object nullObject = null;
        assertNull(ObjectUtils.cloneIfPossible(nullObject));
    }

    
    
    
    
    @ParameterizedTest
    @MethodSource("provideDefaultIfNullCases")
    public void testDefaultIfNull(Object object, Object defaultValue, Object expected) {
        assertEquals(expected, ObjectUtils.defaultIfNull(object, defaultValue));
    }

    
    @ParameterizedTest
    @MethodSource("provideFirstNonNullCases")
    public void testFirstNonNull(Object... values) {
        assertEquals(values[0], ObjectUtils.firstNonNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideGetClassCases")
    public void testGetClass(Object object, Class<?> expected) {
        assertEquals(expected, ObjectUtils.getClass(object));
    }

    
    
    
    
    
    @ParameterizedTest
    @MethodSource("provideHashCodeHexCases")
    public void testHashCodeHex(Object object, String expected) {
        assertEquals(expected, ObjectUtils.hashCodeHex(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIdentityToStringCases")
    public void testIdentityToString(Object object, String expected) {
        assertEquals(expected, ObjectUtils.identityToString(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsArrayCases")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsEmptyCases")
    public void testIsEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isEmpty(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsNotEmptyCases")
    public void testIsNotEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isNotEmpty(object));
    }

    
    
    
    
    
    
    
    @ParameterizedTest
    @MethodSource("provideModeCases")
    public void testMode(Comparable<?>... items) {
        assertEquals(items[0], ObjectUtils.mode(items));
    }

    
    @ParameterizedTest
    @MethodSource("provideNotEqualCases")
    public void testNotEqual(Object object1, Object object2, boolean expected) {
        assertEquals(expected, ObjectUtils.notEqual(object1, object2));
    }

    
    @ParameterizedTest
    @MethodSource("provideRequireNonEmptyCases")
    public void testRequireNonEmpty(Object obj, String message) {
        assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(obj, message));
    }

    
    @ParameterizedTest
    @MethodSource("provideToStringCases")
    public void testToString(Object obj, String nullStr, String expected) {
        assertEquals(expected, ObjectUtils.toString(obj, nullStr));
    }

    }
