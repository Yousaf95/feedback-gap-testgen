package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAllNotNullValues")
    public void testAllNotNull(Object... values) {
        assertTrue(ObjectUtils.allNotNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideAnyNullValues")
    public void testAnyNotNull(Object... values) {
        assertTrue(ObjectUtils.anyNotNull(values));
    }

    
    
    
    @Test
    public void testCompare() {
        assertEquals(0, ObjectUtils.compare(1, 1));
        assertTrue(ObjectUtils.compare(2, 1) > 0);
        assertTrue(ObjectUtils.compare(1, 2) < 0);

        String str1 = "a";
        String str2 = "b";
        assertTrue(ObjectUtils.compare(str1, str2) < 0);
        assertTrue(ObjectUtils.compare(str2, str1) > 0);
    }

    @Test
    public void testCompareWithNullGreater() {
        assertEquals(1, ObjectUtils.compare(null, 1, true));
        assertEquals(-1, ObjectUtils.compare(1, null, true));

        String str = "a";
        assertTrue(ObjectUtils.compare(str, null, true) > 0);
        assertTrue(ObjectUtils.compare(null, str, true) < 0);
    }

    @Test
    public void testDefaultIfNull() {
        Integer defaultValue = 42;
        assertEquals(defaultValue, ObjectUtils.defaultIfNull(null, defaultValue));
        assertEquals(1, ObjectUtils.defaultIfNull(1, defaultValue));
    }

    @ParameterizedTest
    @MethodSource("provideEqualsValues")
    public void testEquals(Object object1, Object object2) {
        assertTrue(ObjectUtils.equals(object1, object2));
    }

    
    @ParameterizedTest
    @MethodSource("provideFirstNonNullValues")
    public void testFirstNonNull(Object... values) {
        assertEquals(2, ObjectUtils.firstNonNull(values));
    }

    
    @ParameterizedTest
    @MethodSource("provideGetClassValues")
    public void testGetClass(Object object) {
        assertEquals(object.getClass(), ObjectUtils.getClass(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideGetFirstNonNullValues")
    public void testGetFirstNonNull(Supplier<Object>... suppliers) {
        assertEquals(2, ObjectUtils.getFirstNonNull(suppliers));
    }

    
    @ParameterizedTest
    @MethodSource("provideGetIfNullValues")
    public void testGetIfNull(Object object, Supplier<Object> defaultSupplier) {
        assertEquals(object, ObjectUtils.getIfNull(object, defaultSupplier));
    }

    
    
    
    @ParameterizedTest
    @MethodSource("provideIdentityToStringValues")
    public void testIdentityToString(Object object) {
        assertNotNull(ObjectUtils.identityToString(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsArrayValues")
    public void testIsArray(Object object) {
        assertTrue(ObjectUtils.isArray(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsEmptyValues")
    public void testIsEmpty(Object object) {
        assertTrue(ObjectUtils.isEmpty(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsNotEmptyValues")
    public void testIsNotEmpty(Object object) {
        assertTrue(ObjectUtils.isNotEmpty(object));
    }

    
    
    
    
    
    
    
    
    
    @ParameterizedTest
    @MethodSource("provideNotEqualValues")
    public void testNotEqual(Object object1, Object object2) {
        assertTrue(ObjectUtils.notEqual(object1, object2));
    }

    
    @ParameterizedTest
    @MethodSource("provideRequireNonEmptyValues")
    public void testRequireNonEmpty(Object obj) {
        assertEquals(obj, ObjectUtils.requireNonEmpty(obj));
    }

    
    @ParameterizedTest
    @MethodSource("provideToStringValues")
    public void testToString(Object obj) {
        assertNotNull(ObjectUtils.toString(obj));
    }

    }
