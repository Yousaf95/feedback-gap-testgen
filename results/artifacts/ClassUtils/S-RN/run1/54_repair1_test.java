package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClassUtils_GenTest {

    @Test
    public void testComparator() {
        Comparator<Class<?>> comparator = ClassUtils.comparator();
        assertNotNull(comparator);
    }

    @Test
    public void testConvertClassesToClassNamesNullInput() {
        List<String> result = ClassUtils.convertClassesToClassNames(null);
        assertNull(result);
    }

    @Test
    public void testConvertClassesToClassNamesEmptyList() {
        List<Class<?>> classes = Collections.emptyList();
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testConvertClassesToClassNamesSingleElement() {
        List<Class<?>> classes = Arrays.asList(String.class);
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("java.lang.String", result.get(0));
    }

    @Test
    public void testConvertClassNamesToClassesNullInput() {
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(null);
        assertNull(result);
    }

    @Test
    public void testConvertClassNamesToClassesEmptyList() {
        List<String> classNames = Collections.emptyList();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testConvertClassNamesToClassesSingleElement() {
        List<String> classNames = Arrays.asList("java.lang.String");
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(String.class, result.get(0));
    }

    @Test
    public void testConvertClassNamesToClassesInvalidClassName() {
        List<String> classNames = Arrays.asList("java.lang.NonExistentClass");
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.get(0));
    }

    
    @Test
    public void testGetAbbreviatedNameEmptyString() {
        String result = ClassUtils.getAbbreviatedName("", 5);
        assertEquals("", result);
    }

    @Test
    public void testGetAbbreviatedNameLengthHintZero() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", 0));
    }

    @Test
    public void testGetAbbreviatedNameShortClassName() {
        String result = ClassUtils.getAbbreviatedName("String", 5);
        assertEquals("String", result);
    }

    
    @Test
    public void testGetAllInterfacesNullInput() {
        List<Class<?>> result = ClassUtils.getAllInterfaces(null);
        assertNull(result);
    }

    @Test
    public void testGetAllInterfacesEmptyClass() {
        List<Class<?>> result = ClassUtils.getAllInterfaces(Object.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    
    @Test
    public void testGetAllSuperclassesNullInput() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
        assertNull(result);
    }

    @Test
    public void testGetAllSuperclassesEmptyClass() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(Object.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    
    @Test
    public void testGetCanonicalNameNullInput() {
        String result = ClassUtils.getCanonicalName(null);
        assertEquals("", result);
    }

    @Test
    public void testGetCanonicalNameEmptyClass() {
        String result = ClassUtils.getCanonicalName(Object.class);
        assertEquals("java.lang.Object", result);
    }

    @Test
    public void testGetCanonicalNameNullObject() {
        String result = ClassUtils.getCanonicalName((Object) null);
        assertEquals("", result);
    }

    @Test
    public void testGetCanonicalNameEmptyObject() {
        String result = ClassUtils.getCanonicalName(new Object());
        assertEquals("java.lang.Object", result);
    }

    @Test
    public void testGetComponentTypeNullInput() {
        Class<?> result = ClassUtils.getComponentType(null);
        assertNull(result);
    }

    @Test
    public void testGetComponentTypeArrayClass() {
        Class<?> result = ClassUtils.getComponentType(String[].class);
        assertEquals(String.class, result);
    }

    @Test
    public void testGetNameNullInput() {
        String result = ClassUtils.getName(null);
        assertEquals("", result);
    }

    @Test
    public void testGetNameEmptyClass() {
        String result = ClassUtils.getName(Object.class);
        assertEquals("java.lang.Object", result);
    }

    @Test
    public void testGetNameNullObject() {
        String result = ClassUtils.getName((Object) null);
        assertEquals("", result);
    }

    @Test
    public void testGetNameEmptyObject() {
        String result = ClassUtils.getName(new Object());
        assertEquals("java.lang.Object", result);
    }

    
    @Test
    public void testGetPackageCanonicalNameEmptyClass() {
        String result = ClassUtils.getPackageCanonicalName(Object.class);
        assertEquals("java.lang", result);
    }

    
    
    
    @Test
    public void testGetPackageNameEmptyClass() {
        String result = ClassUtils.getPackageName(Object.class);
        assertEquals("java.lang", result);
    }

    
    @Test
    public void testGetShortCanonicalNameEmptyClass() {
        String result = ClassUtils.getShortCanonicalName(Object.class);
        assertEquals("Object", result);
    }

    
    
    
    @Test
    public void testGetShortClassNameEmptyClass() {
        String result = ClassUtils.getShortClassName(Object.class);
        assertEquals("Object", result);
    }

    
    
    
    @Test
    public void testIsPrimitiveOrWrapperBooleanClass() {
        boolean result = ClassUtils.isPrimitiveOrWrapper(Boolean.class);
        assertTrue(result);
    }

    @Test
    public void testIsPublicObjectClass() {
        boolean result = ClassUtils.isPublic(Object.class);
        assertTrue(result);
    }

    @Test
    public void testPrimitivesToWrappersNullInput() {
        Class<?>[] result = ClassUtils.primitivesToWrappers(null);
        assertNull(result);
    }

    @Test
    public void testPrimitivesToWrappersBooleanClass() {
        Class<?>[] result = ClassUtils.primitivesToWrappers(boolean.class);
        assertEquals(1, result.length);
        assertEquals(Boolean.class, result[0]);
    }

    @Test
    public void testToClassNullInput() {
        Class<?>[] result = ClassUtils.toClass(null);
        assertNull(result);
    }

    @Test
    public void testToClassSingleElement() {
        Class<?>[] result = ClassUtils.toClass("Hello");
        assertEquals(1, result.length);
        assertEquals(String.class, result[0]);
    }

    
    
    
    @Test
        public void testIsPublicNonPublicClass() {
            assertFalse(ClassUtils.isPublic(new Object(){}.getClass()));
        }

    @Test
        public void testPrimitivesToWrappersEmptyInput() {
            assertArrayEquals(new Class<?>[0], ClassUtils.primitivesToWrappers());
        }

    
    @Test
        public void testConvertClassesToClassNamesWithNullElement() {
            List<Class<?>> classes = Arrays.asList(String.class, null, Integer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(3, classNames.size());
            assertNull(classNames.get(1));
        }

    @Test
        public void testConvertClassNamesToClassesWithInvalidClassName() {
            List<String> classNames = Arrays.asList("java.lang.String", "com.example.NonExistentClass");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(2, classes.size());
            assertNotNull(classes.get(0));
            assertNull(classes.get(1));
        }

    
    
    @Test
        public void testGetCanonicalNameNullObject_2() {
            assertEquals("", ClassUtils.getCanonicalName((Object) null));
        }

    @Test
        public void testIsPrimitiveOrWrapperBooleanWrapper() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
        }

    @Test
        public void testPrimitivesToWrappersSingleElement() {
            assertArrayEquals(new Class<?>[]{Integer.class}, ClassUtils.primitivesToWrappers(Integer.TYPE));
        }

    @Test
        public void testToClassWithNullInput() {
            assertNull(ClassUtils.toClass((Object[]) null));
        }

    @Test
        public void testConvertClassesToClassNamesWithEmptyList() {
            List<Class<?>> classes = Collections.emptyList();
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClassesWithEmptyList() {
            List<String> classNames = Collections.emptyList();
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertTrue(classes.isEmpty());
        }

    
    
    @Test
            public void testIsPrimitiveOrWrapperBooleanWrapper_2() {
                assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
            }

    @Test
            public void testPrimitivesToWrappersEmptyInput_2() {
                assertArrayEquals(new Class<?>[0], ClassUtils.primitivesToWrappers());
            }

    @Test
            public void testConvertClassesToClassNamesWithNullElement_2() {
                List<Class<?>> classes = Arrays.asList(String.class, null, Integer.class);
                List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
                assertEquals(3, classNames.size());
                assertEquals("java.lang.String", classNames.get(0));
                assertNull(classNames.get(1));
                assertEquals("java.lang.Integer", classNames.get(2));
            }

    @Test
            public void testConvertClassNamesToClassesWithInvalidClassName_2() {
                List<String> classNames = Arrays.asList("java.lang.String", "invalid.class.name", "java.lang.Integer");
                List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
                assertEquals(3, classes.size());
                assertNotNull(classes.get(0));
                assertNull(classes.get(1));
                assertNotNull(classes.get(2));
            }

    
    @Test
            public void testIsPrimitiveOrWrapperBooleanClass_2() {
                assertTrue(ClassUtils.isPrimitiveOrWrapper(boolean.class));
            }

    @Test
            public void testPrimitivesToWrappersSingleElement_2() {
            Class<?>[] result = ClassUtils.primitivesToWrappers(int.class);
            assertArrayEquals(new Class<?>[]{Integer.class}, result);
        }

    
    @Test
        public void testConvertClassesToClassNamesWithEmptyList_2() {
            List<Class<?>> classes = Collections.emptyList();
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClassesWithEmptyList_2() {
            List<String> classNames = Collections.emptyList();
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertTrue(classes.isEmpty());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetPackageNameEmptyString() {
            assertEquals("", ClassUtils.getPackageName(""));
        }

    @Test
        public void testGetPackageNameNullInput() {
            assertNull(ClassUtils.getPackageName(null));
        }

    @Test
        public void testGetShortClassNameNullInput() {
            assertNull(ClassUtils.getShortClassName((Class<?>) null));
        }

    @Test
        public void testIsPrimitiveOrWrapperBooleanWrapper_3() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
        }

    @Test
        public void testPrimitivesToWrappersNullInput_2() {
            assertThrows(NullPointerException.class, () -> ClassUtils.primitivesToWrappers((Class<?>[]) null));
        }

    @Test
        public void testConvertClassesToClassNamesWithNullElement_3() {
            List<Class<?>> classes = Arrays.asList(String.class, null, Integer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(2, classNames.size());
            assertEquals("java.lang.String", classNames.get(0));
            assertNull(classNames.get(1));
            assertEquals("java.lang.Integer", classNames.get(2));
        }

    @Test
        public void testConvertClassNamesToClassesWithInvalidClassName_3() {
            List<String> classNames = Arrays.asList("java.lang.String", "invalid.class.name", "java.lang.Integer");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(2, classes.size());
            assertNotNull(classes.get(0));
            assertNull(classes.get(1));
            assertNotNull(classes.get(2));
        }

    @Test
        public void testGetCanonicalNameNullObject_3() {
            Object obj = null;
            assertEquals("", ClassUtils.getCanonicalName(obj));
        }

    @Test
        public void testIsPublicNonPublicClass_2() {
            class NonPublicClass {}
            assertFalse(ClassUtils.isPublic(NonPublicClass.class));
        }

    @Test
        public void testGetAllInterfacesNullInput_2() {
            assertNull(ClassUtils.getAllInterfaces((Class<?>) null));
        }

    @Test
        public void testGetAllSuperclassesNullInput_2() {
            assertNull(ClassUtils.getAllSuperclasses((Class<?>) null));
        }

    @Test
        public void testGetNameNullObject_3() {
            Object obj = null;
            assertEquals("", ClassUtils.getName(obj));
        }

    @Test
        public void testGetComponentTypeNullInput_2() {
            assertNull(ClassUtils.getComponentType((Class<?>[]) null));
        }

    @Test
        public void testGetAbbreviatedNameEmptyClassName_2() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("", 10));
        }

    @Test
        public void testIsPrimitiveOrWrapperBooleanWrapper_4() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.TYPE));
        }

    @Test
        public void testPrimitivesToWrappersEmptyInput_3() {
            Class<?>[] result = ClassUtils.primitivesToWrappers();
            assertEquals(0, result.length);
        }

    @Test
        public void testConvertClassesToClassNamesWithNullElement_4() {
            List<Class<?>> classes = Arrays.asList(String.class, null, Integer.class, null);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(3, classNames.size());
            assertEquals("java.lang.String", classNames.get(0));
            assertNull(classNames.get(1));
            assertEquals("java.lang.Integer", classNames.get(2));
            assertNull(classNames.get(3));
        }

    @Test
        public void testConvertClassNamesToClassesWithInvalidClassName_4() {
            List<String> classNames = Arrays.asList("java.lang.String", "invalid.class.name", "java.lang.Integer", "another.invalid.class");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(3, classes.size());
            assertNotNull(classes.get(0));
            assertNull(classes.get(1));
            assertNotNull(classes.get(2));
            assertNull(classes.get(3));
        }

    @Test
        public void testGetCanonicalNameNullObject_4() {
            Object obj = null;
            assertEquals("", ClassUtils.getCanonicalName(obj, "default"));
        }

    @Test
        public void testIsPublicNonPublicClass_3() {
            class NonPublicClass {}
            assertFalse(ClassUtils.isPublic(NonPublicClass.class));
        }

    @Test
        public void testGetAllInterfacesNullInput_3() {
            assertNull(ClassUtils.getAllInterfaces((Class<?>) null));
        }

    @Test
        public void testGetAllSuperclassesNullInput_3() {
            assertNull(ClassUtils.getAllSuperclasses((Class<?>) null));
        }

    @Test
        public void testGetNameNullObject_4() {
            Object obj = null;
            assertEquals("", ClassUtils.getName(obj, "default"));
        }

    @Test
        public void testGetComponentTypeNullInput_3() {
            assertNull(ClassUtils.getComponentType((Class<?>[]) null));
        }

    @Test
        public void testGetAbbreviatedNameEmptyClassName_3() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("", 10));
        }
}
