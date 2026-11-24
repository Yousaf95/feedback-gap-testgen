package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;

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
        public void testComparator() {
            Comparator<Class<?>> comparator = ClassUtils.comparator();
            assertNotNull(comparator);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNamesEmptyList() {
            List<Class<?>> classes = new ArrayList<>();
            List<String> result = ClassUtils.convertClassesToClassNames(classes);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClassesEmptyList() {
            List<String> classNames = new ArrayList<>();
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testInterfacesEnum() {
            assertEquals(ClassUtils.Interfaces.INCLUDE, ClassUtils.Interfaces.valueOf("INCLUDE"));
            assertEquals(ClassUtils.Interfaces.EXCLUDE, ClassUtils.Interfaces.valueOf("EXCLUDE"));
        }

    @Test
        public void testConvertClassesToClassNamesWithNull_2() {
            assertNull(ClassUtils.convertClassesToClassNames(null));
        }

    @Test
        public void testConvertClassNamesToClassesWithNull_2() {
            assertNull(ClassUtils.convertClassNamesToClasses(null));
        }

    @Test
        public void testGetAbbreviatedNameWithNegativeLengthHint() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("test", -1));
        }

    @Test
        public void testGetAbbreviatedNameWithNullClassName() {
            assertEquals("", ClassUtils.getAbbreviatedName(null, 5));
        }

    @Test
        public void testGetAbbreviatedNameWithEmptyClassName() {
            assertEquals("", ClassUtils.getAbbreviatedName("", 5));
        }

    @Test
        public void testGetAbbreviatedNameWithShortClassName() {
            assertEquals("short", ClassUtils.getAbbreviatedName("short", 10));
        }

    @Test
        public void testGetAllInterfacesWithNullClass() {
            assertNull(ClassUtils.getAllInterfaces(null));
        }

    @Test
        public void testGetCanonicalNameWithNullClass() {
            assertEquals("", ClassUtils.getCanonicalName((Class<?>) null, "default"));
        }

    @Test
        public void testGetCanonicalNameWithNullObject() {
            assertEquals("default", ClassUtils.getCanonicalName((Object) null, "default"));
        }

    @Test
        public void testGetCanonicalNameWithEmptyClassName() {
            assertNull(ClassUtils.getCanonicalName(""));
        }

    @Test
        public void testGetCanonicalNameWithArrayClassName() {
            assertEquals("[I", ClassUtils.getCanonicalName("int[]"));
        }

    @Test
        public void testGetClassWithNullClassLoaderAndNullClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass((ClassLoader) null, (String) null));
        }

    @Test
        public void testGetClassWithNullClassLoaderAndNonNullClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass((ClassLoader) null, "java.lang.String"));
        }

    @Test
        public void testGetClassWithNonNullClassLoaderAndNullClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass(ClassLoader.getSystemClassLoader(), (String) null));
        }

    @Test
        public void testGetClassWithNonNullClassLoaderAndEmptyClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass(ClassLoader.getSystemClassLoader(), ""));
        }

    @Test
        public void testGetClassWithInnerClassName() throws ClassNotFoundException {
            assertEquals(String.class, ClassUtils.getClass("java.lang.String$CaseInsensitiveComparator"));
        }
}
