package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import static org.apache.commons.lang3.ClassUtils.*;

public class ClassUtils_GenTest {

    
    
    @Test
    public void testConvertClassesToClassNamesWithNull() {
        List<Class<?>> classes = null;
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNull(result);
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

    
    @Test
    public void testGetAllSuperclassesWithNull() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
        assertNull(result);
    }

    
    
    
    
    
    
    
    @Test
    public void testIsAssignable() throws ClassNotFoundException {
        boolean result = ClassUtils.isAssignable(Class.forName("java.lang.String"), Class.forName("java.lang.Object"));
        assertTrue(result);
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testInterfacesEnum() {
            assertEquals(Interfaces.INCLUDE, Interfaces.INCLUDE);
            assertEquals(Interfaces.EXCLUDE, Interfaces.EXCLUDE);
        }

    @Test
        public void testComparator() {
            Comparator<Class<?>> comparator = comparator();
            assertNotNull(comparator);
        }

    @Test
        public void testConvertClassesToClassNamesWithNull_2() {
            List<String> result = convertClassesToClassNames(null);
            assertNull(result);
        }

    @Test
        public void testConvertClassesToClassNamesWithEmptyList() {
            List<Class<?>> classes = Collections.emptyList();
            List<String> result = convertClassesToClassNames(classes);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testConvertClassesToClassNamesWithSingleElement() {
            List<Class<?>> classes = Arrays.asList(String.class);
            List<String> result = convertClassesToClassNames(classes);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("java.lang.String", result.get(0));
        }

    @Test
        public void testConvertClassNamesToClassesWithNull_2() {
            List<Class<?>> result = convertClassNamesToClasses(null);
            assertNull(result);
        }

    @Test
        public void testConvertClassNamesToClassesWithEmptyList() {
            List<String> classNames = Collections.emptyList();
            List<Class<?>> result = convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClassesWithSingleElementSuccess() {
            List<String> classNames = Arrays.asList("java.lang.String");
            List<Class<?>> result = convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(String.class, result.get(0));
        }

    @Test
        public void testConvertClassNamesToClassesWithSingleElementFailure() {
            List<String> classNames = Arrays.asList("nonexistent.class");
            List<Class<?>> result = convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertNull(result.get(0));
        }

    @Test
        public void testGetAbbreviatedNameWithNull() {
            String result = getAbbreviatedName(null, 5);
            assertEquals("", result);
        }

    @Test
        public void testGetAbbreviatedNameWithEmptyString() {
            String result = getAbbreviatedName("", 5);
            assertEquals("", result);
        }

    @Test
        public void testGetAbbreviatedNameWithShortClassName() {
            String result = getAbbreviatedName("short", 10);
            assertEquals("short", result);
        }

    @Test
        public void testGetAbbreviatedNameWithLongClassName() {
            String result = getAbbreviatedName("verylongclassname", 5);
            assertEquals("ver..name", result);
        }

    @Test
        public void testGetAllInterfacesWithNull_2() {
            List<Class<?>> result = getAllInterfaces(null);
            assertNull(result);
        }

    @Test
        public void testGetAllInterfacesWithInterfaceClass() {
            List<Class<?>> result = getAllInterfaces(List.class);
            assertNotNull(result);
            assertTrue(result.contains(List.class));
        }

    @Test
        public void testGetAllInterfacesWithNonInterfaceClass() {
            List<Class<?>> result = getAllInterfaces(String.class);
            assertNotNull(result);
            assertFalse(result.contains(List.class));
        }

    @Test
        public void testGetAllSuperclassesWithNull_2() {
            List<Class<?>> result = getAllSuperclasses(null);
            assertNull(result);
        }

    @Test
        public void testGetAllSuperclassesWithClass() {
            List<Class<?>> result = getAllSuperclasses(String.class);
            assertNotNull(result);
            assertTrue(result.contains(Object.class));
        }
}
