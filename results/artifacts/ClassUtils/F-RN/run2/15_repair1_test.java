package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    @Test
    public void testComparator() {
        Comparator<Class<?>> comparator = ClassUtils.comparator();
        assertNotNull(comparator);
    }

    @Test
    public void testConvertClassesToClassNames() {
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class, null);
        List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
        assertEquals(Arrays.asList("java.lang.String", "java.lang.Integer", null), classNames);
    }

    @Test
    public void testConvertClassNamesToClasses() {
        List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer", null);
        List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
        assertEquals(Arrays.asList(String.class, Integer.class, null), classes);
    }

    
    @Test
    public void testGetAllInterfaces() {
        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
        assertTrue(interfaces.contains(Iterable.class));
    }

    @Test
    public void testGetAllSuperclasses() {
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
        assertTrue(superclasses.contains(Object.class)); // AbstractList is not a superclass of ArrayList
    }

    @Test
    public void testGetCanonicalName() {
        String canonicalName = ClassUtils.getCanonicalName(String.class);
        assertEquals("java.lang.String", canonicalName);
    }

    @Test
    public void testGetClass() throws ClassNotFoundException {
        Class<?> clazz = ClassUtils.getClass("java.lang.String");
        assertEquals(String.class, clazz);
    }

    
    @Test
    public void testGetName() {
        String name = ClassUtils.getName(String.class);
        assertEquals("java.lang.String", name);
    }

    @Test
    public void testGetPackageCanonicalName() {
        String packageName = ClassUtils.getPackageCanonicalName(String.class);
        assertEquals("java.lang", packageName);
    }

    
    @Test
    public void testGetShortCanonicalName() {
        String shortName = ClassUtils.getShortCanonicalName(String.class);
        assertEquals("String", shortName);
    }

    @Test
    public void testGetShortClassName() {
        String shortName = ClassUtils.getShortClassName(String.class);
        assertEquals("String", shortName);
    }

    @Test
    public void testgetSimpleName() {
        String simpleName = ClassUtils.getSimpleName(String.class);
        assertEquals("String", simpleName);
    }

    @Test
    public void testHierarchy() {
        Iterable<Class<?>> hierarchy = ClassUtils.hierarchy(List.class);
        assertTrue(hierarchy.iterator().hasNext());
    }

    @Test
    public void testIsAssignable() {
        boolean assignable = ClassUtils.isAssignable(String.class, Object.class);
        assertTrue(assignable);
    }

    @Test
    public void testIsInnerClass() {
        boolean innerClass = ClassUtils.isInnerClass(new Object(){}.getClass());
        assertTrue(innerClass);
    }

    @Test
    public void testIsPrimitiveOrWrapper() {
        boolean primitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(Integer.class);
        assertTrue(primitiveOrWrapper);
    }

    @Test
    public void testIsPublic() {
        boolean isPublic = ClassUtils.isPublic(String.class);
        assertTrue(isPublic);
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAbbreviatedName() {
            assertEquals("org.apache", ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils", 10));
            assertEquals("java.lang.String", ClassUtils.getAbbreviatedName("java.lang.String", 20));
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", -1));
        }

    @Test
        public void testGetPackageName() {
            assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(ClassUtils.class));
            assertEquals("", ClassUtils.getPackageName(String.class));
            assertNull(ClassUtils.getPackageName(null));
        }

    @Test
        public void testIsPublic_2() {
            assertTrue(ClassUtils.isPublic(Object.class));
            assertFalse(ClassUtils.isPublic(ArrayList.class.getDeclaredConstructor()));
        }

    @Test
        public void testGetAllInterfaces_2() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertTrue(interfaces.contains(java.util.Collection.class));
            assertTrue(interfaces.contains(Iterable.class));
        }

    @Test
        public void testGetAllSuperclasses_2() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertTrue(superclasses.contains(java.util.AbstractList.class));
            assertTrue(superclasses.contains(java.util.AbstractCollection.class));
        }

    @Test
        public void testConvertClassesToClassNames_2() {
            List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(Arrays.asList("java.lang.String", "java.lang.Integer"), classNames);
        }

    @Test
        public void testConvertClassNamesToClasses_2() {
            List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(Arrays.asList(String.class, Integer.class), classes);
        }

    @Test
        public void testGetCanonicalName_2() {
            assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getCanonicalName(ClassUtils.class));
            assertNull(ClassUtils.getCanonicalName(null));
        }

    @Test
        public void testGetName_2() {
            assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
            assertEquals("", ClassUtils.getSimpleName(null));
        }

    @Test
        public void testGetPackageCanonicalName_2() {
            assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils.class));
            assertNull(ClassUtils.getPackageCanonicalName(null));
        }

    @Test
        public void testGetShortClassName_2() {
            assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class));
            assertEquals("", ClassUtils.getShortClassName(null));
        }

    @Test
        public void testIsPrimitiveOrWrapper_2() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
            assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
        }

    @Test
        public void testIsInnerClass_2() {
            assertFalse(ClassUtils.isInnerClass(Object.class));
            assertTrue(ClassUtils.isInnerClass(ArrayList.class.getDeclaredClasses()[0]));
        }
}
