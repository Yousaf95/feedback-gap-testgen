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
        public void testGetAllInterfaces_NullInput() {
            assertNull(getAllInterfaces((Class<?>) null));
        }

    @Test
        public void testGetAllSuperclasses_NullInput() {
            assertNull(getAllSuperclasses((Class<?>) null));
        }

    
    
    @Test
        public void testGetPackageCanonicalName_NullInput() {
            assertEquals("", getPackageCanonicalName((Class<?>) null));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAbbreviatedName_NullInput_3() {
            assertEquals("", getAbbreviatedName(null, 10));
        }

    @Test
        public void testGetAbbreviatedName_EmptyString_3() {
            assertEquals("", getAbbreviatedName("", 10));
        }

    @Test
        public void testGetAbbreviatedName_ShorterThanLengthHint_3() {
            assertEquals("short", getAbbreviatedName("short", 5));
        }

    @Test
        public void testGetAbbreviatedName_NullInput_2_2() {
            assertThrows(NullPointerException.class, () -> getAbbreviatedName(null, -1));
        }

    @Test
        public void testGetAbbreviatedName_EmptyString_2_2() {
            assertThrows(IllegalArgumentException.class, () -> getAbbreviatedName("", 0));
        }

    @Test
        public void testGetAbbreviatedName_ShorterThanLengthHint_2_2() {
            assertEquals("short", getAbbreviatedName("short", 10));
        }

    @Test
        public void testGetAllInterfaces_NullInput_2() {
            assertNull(getAllInterfaces(null));
        }

    @Test
        public void testGetAllSuperclasses_NullInput_2() {
            assertNull(getAllSuperclasses(null));
        }

    @Test
        public void testGetCanonicalName_NullInput() {
            assertEquals("", getCanonicalName(null, "default"));
        }

    @Test
        public void testGetCanonicalName_EmptyString() {
            assertEquals("", getCanonicalName("", "default"));
        }

    @Test
        public void testGetCanonicalName_NullValueIfNull() {
            assertNull(getCanonicalName(null, null));
        }

    @Test
        public void testGetCanonicalName_EmptyClass() {
            assertEquals("java.lang.Object", getCanonicalName(Object.class, "default"));
        }

    @Test
        public void testGetCanonicalName_NullObject() {
            assertEquals("", getCanonicalName((Object) null, "default"));
        }

    @Test
        public void testGetCanonicalName_EmptyValueIfNull() {
            assertEquals("default", getCanonicalName(Object.class, ""));
        }

    @Test
        public void testGetClass_NullClassLoader() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> getClass((ClassLoader) null, "java.lang.String"));
        }

    @Test
        public void testGetClass_EmptyClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> getClass(ClassLoader.getSystemClassLoader(), ""));
        }

    @Test
        public void testGetClass_NullObject() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> getClass((String) null));
        }

    @Test
        public void testGetClass_EmptyValueIfNull() throws ClassNotFoundException {
            assertNull(getClass("java.lang.String", false));
        }

    @Test
        public void testGetComponentType_NullInput() {
            assertNull(getComponentType(null));
        }

    @Test
        public void testGetName_NullInput() {
            assertEquals("", getName(null, "default"));
        }

    @Test
        public void testGetName_EmptyString() {
            assertEquals("", getName("", "default"));
        }

    @Test
        public void testGetName_NullValueIfNull() {
            assertNull(getName(null, null));
        }

    @Test
        public void testGetName_EmptyClass() {
            assertEquals("java.lang.Object", getName(Object.class, "default"));
        }

    @Test
        public void testGetName_NullObject() {
            assertEquals("", getName((Object) null, "default"));
        }

    @Test
        public void testGetName_EmptyValueIfNull() {
            assertEquals("default", getName(Object.class, ""));
        }

    @Test
        public void testGetPackageCanonicalName_NullInput_2() {
            assertEquals("", getPackageCanonicalName(null, "default"));
        }

    @Test
        public void testGetPackageCanonicalName_EmptyString() {
            assertEquals("", getPackageCanonicalName("", "default"));
        }

    @Test
        public void testGetPackageCanonicalName_NullValueIfNull() {
            assertNull(getPackageCanonicalName(null, null));
        }

    @Test
        public void testGetPackageCanonicalName_EmptyClass() {
            assertEquals("java.lang", getPackageCanonicalName(Object.class, "default"));
        }

    @Test
        public void testGetPackageCanonicalName_NullObject() {
            assertEquals("", getPackageCanonicalName((Object) null, "default"));
        }

    @Test
        public void testGetPackageCanonicalName_EmptyValueIfNull() {
            assertEquals("default", getPackageCanonicalName(Object.class, ""));
        }

    @Test
        public void testGetPackageName_NullInput() {
            assertEquals("", getPackageName(null));
        }

    @Test
        public void testGetPackageName_EmptyString() {
            assertEquals("", getPackageName(""));
        }

    @Test
        public void testGetPackageName_EmptyClass() {
            assertEquals("java.lang", getPackageName(Object.class));
        }

    @Test
        public void testGetPackageName_NullObject() {
            assertEquals("", getPackageName((Object) null));
        }
}
