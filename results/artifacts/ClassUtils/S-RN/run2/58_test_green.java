package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

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

    
    
    
    
    @Test
        public void testGetAllInterfacesWithEmptyClass() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(new Object(){}.getClass());
            assertNotNull(interfaces);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        public void testConvertClassesToClassNamesWithEmptyList() {
            List<String> classNames = ClassUtils.convertClassesToClassNames(Collections.emptyList());
            assertNotNull(classNames);
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testGetAllSuperclassesWithObjectClass() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(Object.class);
            assertNotNull(superclasses);
            assertEquals(0, superclasses.size());
        }

    
    @Test
        public void testGetAbbreviatedNameWithEmptyClassName() {
            assertEquals("", ClassUtils.getAbbreviatedName("", 10));
        }

    @Test
        public void testConvertClassNamesToClassesWithInvalidClassName() {
            List<String> classNames = Arrays.asList("java.lang.String", "com.example.NonExistentClass");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(classes);
            assertEquals(2, classes.size());
            assertNull(classes.get(1));
        }

    @Test
        public void testGetCanonicalNameWithNull_2() {
            assertEquals("", ClassUtils.getCanonicalName((Object) null));
        }

    @Test
        public void testIsInnerClassWithNonInnerClass() {
            assertFalse(ClassUtils.isInnerClass(String.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetPackageNameWithEmptyClassName() {
            assertEquals("", ClassUtils.getPackageName(""));
        }

    
    @Test
        public void testIsPrimitiveOrWrapperWithBooleanClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithByteClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Byte.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithCharacterClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Character.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithShortClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Short.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithIntegerClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithLongClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Long.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithDoubleClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Double.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithFloatClass() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Float.class));
        }

    
    @Test
        public void testIsPrimitiveOrWrapperWithNonPrimitiveClass() {
            assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
        }

    @Test
        public void testGetAllInterfacesWithEmptyClass_2() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Object.class);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        public void testGetAllSuperclassesWithNull_2() {
            assertNull(ClassUtils.getAllSuperclasses(null));
        }

    @Test
        public void testConvertClassesToClassNamesWithEmptyList_2() {
            List<String> classNames = ClassUtils.convertClassesToClassNames(Collections.emptyList());
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testGetAbbreviatedNameWithNegativeLengthHint() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", -1));
        }

    
    @Test
        public void testGetAllInterfacesWithInterface() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertEquals(2, interfaces.size());
            assertTrue(interfaces.contains(java.util.Collection.class));
            assertTrue(interfaces.contains(java.lang.Iterable.class));
        }

    
    @Test
        public void testGetPackageNameWithEmptyString() {
            assertEquals("", ClassUtils.getPackageName(""));
        }

    
    @Test
        public void testGetPackageNameWithClassInPackage() {
            assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(ClassUtils_GenTest.class));
        }

    
    
    
    
    
    
    
    
    
    @Test
        public void testConvertClassesToClassNamesWithEmptyArray() {
            List<String> classNames = ClassUtils.convertClassesToClassNames(Arrays.asList());
            assertTrue(classNames.isEmpty());
        }
}
