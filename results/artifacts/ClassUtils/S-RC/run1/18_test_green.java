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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testInterfacesEnum() {
            assertEquals(ClassUtils.Interfaces.INCLUDE, ClassUtils.Interfaces.values()[0]);
            assertEquals(ClassUtils.Interfaces.EXCLUDE, ClassUtils.Interfaces.values()[1]);
        }

    @Test
        public void testGetAbbreviatedNameEmptyString_2() {
            assertEquals("", ClassUtils.getAbbreviatedName("", 5));
        }

    @Test
        public void testGetAbbreviatedNameLengthHintZero_2() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("test", 0));
        }

    
    @Test
        public void testGetAllInterfacesNullInput_2() {
            assertNull(ClassUtils.getAllInterfaces(null));
        }

    @Test
        public void testGetAllInterfacesEmptyClass_2() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Object.class);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        public void testGetAllSuperclassesNullInput_2() {
            assertNull(ClassUtils.getAllSuperclasses(null));
        }

    @Test
        public void testGetAllSuperclassesEmptyClass_2() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(Object.class);
            assertTrue(superclasses.isEmpty());
        }

    @Test
        public void testGetCanonicalNameNullInput_2() {
            assertEquals("", ClassUtils.getCanonicalName(null));
        }

    @Test
        public void testGetCanonicalNameEmptyClass_2() {
            assertEquals("java.lang.Object", ClassUtils.getCanonicalName(Object.class));
        }

    
    
    @Test
        public void testGetComponentTypeNullInput_2() {
            assertNull(ClassUtils.getComponentType(null));
        }

    @Test
        public void testGetNameNullInput_2() {
            assertEquals("", ClassUtils.getName(null));
        }

    @Test
        public void testGetNameEmptyClass_2() {
            assertEquals("java.lang.Object", ClassUtils.getName(Object.class));
        }

    
    
    @Test
        public void testGetPackageCanonicalNameEmptyClass_2() {
            assertEquals("java.lang", ClassUtils.getPackageCanonicalName(Object.class));
        }

    @Test
        public void testGetPackageNameEmptyClass_2() {
            assertEquals("java.lang", ClassUtils.getPackageName(Object.class));
        }

    @Test
        public void testGetShortCanonicalNameEmptyClass_2() {
            assertEquals("Object", ClassUtils.getShortCanonicalName(Object.class));
        }

    @Test
        public void testGetShortClassNameEmptyClass_2() {
            assertEquals("Object", ClassUtils.getShortClassName(Object.class));
        }
}
