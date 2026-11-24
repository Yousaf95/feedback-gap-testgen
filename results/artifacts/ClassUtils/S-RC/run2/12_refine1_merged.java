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

    
    
    // ---- added by coverage-guided refinement ----
    @org.junit.jupiter.api.Test
        public void testInterfacesEnum() {
            assertEquals(ClassUtils.Interfaces.INCLUDE, ClassUtils.Interfaces.valueOf("INCLUDE"));
            assertEquals(ClassUtils.Interfaces.EXCLUDE, ClassUtils.Interfaces.valueOf("EXCLUDE"));
        }

    @org.junit.jupiter.api.Test
        public void testComparator() {
            Comparator<Class<?>> comparator = ClassUtils.comparator();
            assertNotNull(comparator);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNamesWithNull_2() {
            List<String> result = ClassUtils.convertClassesToClassNames(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNamesEmptyList() {
            List<Class<?>> classes = new ArrayList<>();
            List<String> result = ClassUtils.convertClassesToClassNames(classes);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNamesWithClasses() {
            List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
            List<String> result = ClassUtils.convertClassesToClassNames(classes);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains("java.lang.String"));
            assertTrue(result.contains("java.lang.Integer"));
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClassesWithNull_2() {
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClassesEmptyList() {
            List<String> classNames = new ArrayList<>();
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClassesWithClassNames() throws ClassNotFoundException {
            List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(String.class));
            assertTrue(result.contains(Integer.class));
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClassesWithInvalidClassName() {
            List<String> classNames = Arrays.asList("java.lang.String", "invalid.className");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(String.class));
            assertNull(result.get(1));
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedNameWithNull() {
            String result = ClassUtils.getAbbreviatedName(null, 10);
            assertEquals("", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedNameWithEmptyClassName() {
            String result = ClassUtils.getAbbreviatedName("", 10);
            assertEquals("", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedNameWithShortClassName() {
            String result = ClassUtils.getAbbreviatedName("String", 5);
            assertEquals("String", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedNameWithLongClassName() {
            String result = ClassUtils.getAbbreviatedName("java.lang.StringBuilder", 10);
            assertEquals("java.l...", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfacesWithNull_2() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfacesWithClassWithoutInterfaces() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(String.class);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfacesWithClassWithInterfaces() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(List.class);
            assertNotNull(result);
            assertTrue(result.contains(Collection.class));
            assertTrue(result.contains(Iterable.class));
        }
}
