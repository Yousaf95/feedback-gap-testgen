package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
        public void testGetName_2() {
            assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
            assertEquals("", ClassUtils.getSimpleName(null));
        }

    
    
    @Test
        public void testIsPrimitiveOrWrapper_2() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
            assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetPackageName() {
            assertEquals("org.apache.commons", ClassUtils.getPackageName(ClassUtils.class));
            assertNull(ClassUtils.getPackageName(null, "default"));
            assertEquals("default", ClassUtils.getPackageName((Class<?>) null, "default"));
        }

    @Test
        public void testIsPublic_2() {
            assertTrue(ClassUtils.isPublic(String.class));
            assertFalse(ClassUtils.isPublic(new MutableObject<String>() {}.getClass()));
        }

    @Test
        public void testGetAllInterfaces_3() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertTrue(interfaces.contains(Iterable.class));
            assertTrue(interfaces.contains(Collection.class));
            assertTrue(interfaces.contains(Serializable.class));
        }

    @Test
        public void testConvertClassesToClassNames_3() {
            List<Class<?>> classes = Arrays.asList(String.class, Integer.class, Double.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(Arrays.asList("java.lang.String", "java.lang.Integer", "java.lang.Double"), classNames);
        }

    @Test
        public void testConvertClassNamesToClasses_3() {
            List<String> classNames = Arrays.asList("java.util.List", "java.util.Map");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(Arrays.asList(List.class, Map.class), classes);
        }

    @Test
        public void testGetAllSuperclasses_3() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertTrue(superclasses.contains(AbstractList.class));
            assertTrue(superclasses.contains(AbstractCollection.class));
            assertTrue(superclasses.contains(Collection.class));
            assertTrue(superclasses.contains(Object.class));
        }

    @Test
        public void testGetAbbreviatedName() {
            assertEquals("java.lang.Str", ClassUtils.getAbbreviatedName(String.class, 10));
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName(String.class, -1));
        }

    @Test
        public void testIsPrimitiveOrWrapper_3() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
            assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
        }

    @Test
        public void testGetAllInterfaces_4() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Map.Entry.class);
            assertTrue(interfaces.contains(Map.Entry.class));
            assertTrue(interfaces.contains(Object.class));
        }

    @Test
        public void testConvertClassesToClassNames_4() {
            List<Class<?>> classes = Arrays.asList(StringBuilder.class, StringBuffer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(Arrays.asList("java.lang.StringBuilder", "java.lang.StringBuffer"), classNames);
        }

    @Test
        public void testConvertClassNamesToClasses_4() {
            List<String> classNames = Arrays.asList("java.util.concurrent.atomic.AtomicInteger");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(Arrays.asList(AtomicInteger.class), classes);
        }

    @Test
        public void testGetAllSuperclasses_4() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(HashMap.class);
            assertTrue(superclasses.contains(AbstractMap.class));
            assertTrue(superclasses.contains(Map.class));
            assertTrue(superclasses.contains(Object.class));
        }
}
