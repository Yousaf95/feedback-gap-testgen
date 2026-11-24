package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    
    
    @Test
    public void testConvertClassesToClassNamesWithNull() {
        List<Class<?>> classes = null;
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "java.util.List"})
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
    @ValueSource(strings = {"java.lang.String"})
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
    @ValueSource(strings = {"java.lang.String"})
    public void testGetAllSuperclasses(String className) throws ClassNotFoundException {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(Class.forName(className));
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetAllSuperclassesWithNull() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String"})
    public void testGetCanonicalName(String className) throws ClassNotFoundException {
        String canonicalName = ClassUtils.getCanonicalName(Class.forName(className));
        assertNotNull(canonicalName);
        assertEquals(className, canonicalName);
    }

    @Test
    public void testGetCanonicalNameWithNull() {
        String result = ClassUtils.getCanonicalName(null);
        assertEquals("", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String"})
    public void testGetName(String className) throws ClassNotFoundException {
        String name = ClassUtils.getName(Class.forName(className));
        assertNotNull(name);
        assertEquals(className, name);
    }

    @Test
    public void testGetNameWithNull() {
        String result = ClassUtils.getName(null);
        assertEquals("", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String"})
    public void testGetPackageCanonicalName(String className) throws ClassNotFoundException {
        String packageName = ClassUtils.getPackageCanonicalName(Class.forName(className));
        assertNotNull(packageName);
        assertTrue(!packageName.isEmpty());
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String"})
    public void testGetPackageName(String className) throws ClassNotFoundException {
        String packageName = ClassUtils.getPackageName(Class.forName(className));
        assertNotNull(packageName);
        assertTrue(!packageName.isEmpty());
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String"})
    public void testIsPrimitiveOrWrapper(String className) throws ClassNotFoundException {
        boolean result = ClassUtils.isPrimitiveOrWrapper(Class.forName(className));
        if (className.equals("boolean") || className.equals("byte") || className.equals("char")
                || className.equals("short") || className.equals("int") || className.equals("long")
                || className.equals("double") || className.equals("float")) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    @Test
    public void testIsPrimitiveOrWrapperWithNull() {
        boolean result = ClassUtils.isPrimitiveOrWrapper(null);
        assertFalse(result);
    }

    
    }
