package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    @Test
    public void testComparator() {
        Comparator<Class<?>> comparator = ClassUtils.comparator();
        assertNotNull(comparator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testConvertClassesToClassNames(String className) throws ClassNotFoundException {
        List<String> classNames = Arrays.asList(className);
        List<String> result = ClassUtils.convertClassesToClassNames(classNames);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Class.forName(className).getCanonicalName(), result.get(0));
    }

    @Test
    public void testConvertClassesToClassNamesWithNull() {
        List<Class<?>> classes = null;
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testConvertClassNamesToClasses(String className) throws ClassNotFoundException {
        List<String> classNames = Arrays.asList(className);
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Class.forName(className), result.get(0));
    }

    @Test
    public void testConvertClassNamesToClassesWithNull() {
        List<String> classNames = null;
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetAbbreviatedName(String className) {
        String abbreviatedName = ClassUtils.getAbbreviatedName(className, 10);
        assertNotNull(abbreviatedName);
        assertTrue(abbreviatedName.length() <= 10);
    }

    @Test
    public void testGetAllInterfacesWithNull() {
        List<Class<?>> result = ClassUtils.getAllInterfaces(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class, Object.class})
    public void testGetAllSuperclasses(Class<?> cls) {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(cls);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetAllSuperclassesWithNull() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetCanonicalName(String className) throws ClassNotFoundException {
        String canonicalName = ClassUtils.getCanonicalName(className);
        assertNotNull(canonicalName);
        assertEquals(Class.forName(className).getCanonicalName(), canonicalName);
    }

    @Test
    public void testGetCanonicalNameWithNull() {
        String result = ClassUtils.getCanonicalName(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetName(String className) throws ClassNotFoundException {
        String name = ClassUtils.getName(className);
        assertNotNull(name);
        assertEquals(Class.forName(className).getName(), name);
    }

    @Test
    public void testGetNameWithNull() {
        String result = ClassUtils.getName(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetPackageCanonicalName(String className) throws ClassNotFoundException {
        String packageName = ClassUtils.getPackageCanonicalName(className);
        assertNotNull(packageName);
        assertTrue(!packageName.isEmpty());
    }

    @Test
    public void testGetPackageCanonicalNameWithNull() {
        String result = ClassUtils.getPackageCanonicalName(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetPackageName(String className) throws ClassNotFoundException {
        String packageName = ClassUtils.getPackageName(className);
        assertNotNull(packageName);
        assertTrue(!packageName.isEmpty());
    }

    @Test
    public void testIsAssignable() throws ClassNotFoundException {
        boolean result = ClassUtils.isAssignable(Class.forName("java.lang.String"), Class.forName("java.lang.Object"));
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testIsPrimitiveOrWrapper(String className) throws ClassNotFoundException {
        boolean result = ClassUtils.isPrimitiveOrWrapper(Class.forName(className));
        assertTrue(result);
    }
}
