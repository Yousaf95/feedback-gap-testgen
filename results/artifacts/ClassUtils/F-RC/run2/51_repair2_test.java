package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ClassUtils.*;

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
        void testConvertClassesToClassNames_NullInput() {
            List<String> result = convertClassesToClassNames(null);
            assertNull(result);
        }

    @Test
        void testConvertClassesToClassNames_EmptyList() {
            List<Class<?>> emptyList = new ArrayList<>();
            List<String> result = convertClassesToClassNames(emptyList);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        void testConvertClassesToClassNames_SingleElement() {
            List<Class<?>> singleElementList = Arrays.asList(String.class);
            List<String> result = convertClassesToClassNames(singleElementList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("java.lang.String", result.get(0));
        }

    @Test
        void testConvertClassNamesToClasses_NullInput() {
            List<Class<?>> result = convertClassNamesToClasses(null);
            assertNull(result);
        }

    @Test
        void testConvertClassNamesToClasses_EmptyList() {
            List<String> emptyList = new ArrayList<>();
            List<Class<?>> result = convertClassNamesToClasses(emptyList);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        void testConvertClassNamesToClasses_SingleElement() throws ClassNotFoundException {
            List<String> singleElementList = Arrays.asList("java.lang.String");
            List<Class<?>> result = convertClassNamesToClasses(singleElementList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(String.class, result.get(0));
        }

    @Test
        void testConvertClassNamesToClasses_InvalidClassName() {
            List<String> invalidClassNameList = Arrays.asList("java.lang.NonExistentClass");
            List<Class<?>> result = convertClassNamesToClasses(invalidClassNameList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertNull(result.get(0));
        }

    @Test
        void testGetAbbreviatedName_NullInput() {
            String result = getAbbreviatedName((String) null, 5);
            assertEquals("", result);
        }

    @Test
        void testGetAbbreviatedName_EmptyString() {
            String result = getAbbreviatedName("", 5);
            assertEquals("", result);
        }

    @Test
        void testGetAbbreviatedName_ShorterThanLengthHint() {
            String result = getAbbreviatedName("short", 10);
            assertEquals("short", result);
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAbbreviatedName_NullInput_2() {
            assertEquals("", getAbbreviatedName((Class<?>) null, 10));
        }

    @Test
        public void testGetAbbreviatedName_EmptyString_2() {
            assertEquals("", getAbbreviatedName("", 10));
        }

    @Test
        public void testGetAbbreviatedName_ShorterThanLengthHint_2() {
            assertEquals("short", getAbbreviatedName("short", 5));
        }

    @Test
        public void testGetAbbreviatedName_LongerThanLengthHint() {
            assertEquals("lo", getAbbreviatedName("longer", 2));
        }

    @Test
        public void testGetAbbreviatedName_WithDots() {
            assertEquals("com.example.MyClass", getAbbreviatedName("com.example.MyClass", 10));
        }

    @Test
        public void testGetAllInterfaces_NullInput() {
            assertNull(getAllInterfaces((Class<?>) null));
        }

    @Test
        public void testGetAllSuperclasses_NullInput() {
            assertNull(getAllSuperclasses((Class<?>) null));
        }

    @Test
        public void testGetCanonicalName_NullInput() {
            assertEquals("", getCanonicalName((Class<?>) null, "default"));
        }

    @Test
        public void testGetName_NullInput() {
            assertEquals("", getName((Class<?>) null, "default"));
        }

    @Test
        public void testGetPackageCanonicalName_NullInput() {
            assertEquals("", getPackageCanonicalName((Class<?>) null));
        }

    @Test
        public void testGetShortClassName_NullInput() {
            assertEquals("", getShortClassName((Class<?>) null, "default"));
        }
}
