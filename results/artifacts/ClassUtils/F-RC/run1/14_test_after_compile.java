package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.Collections;

public class ClassUtils_GenTest {

    
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
        assertTrue(interfaces.contains(java.util.Collection.class));
    }

    @Test
    public void testGetAllSuperclasses() {
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
        assertTrue(superclasses.contains(java.util.AbstractList.class));
    }

    @Test
    public void testGetCanonicalName() {
        String canonicalName = ClassUtils.getCanonicalName(String.class);
        assertEquals("java.lang.String", canonicalName);
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
    public void testGetPackageName() {
        String packageName = ClassUtils.getPackageName("java.lang.String");
        assertEquals("java.lang", packageName);
    }

    @Test
    public void testIsAssignable() {
        assertTrue(ClassUtils.isAssignable(String.class, Object.class));
    }

    @Test
    public void testIsInnerClass() {
        assertFalse(ClassUtils.isInnerClass(String.class));
    }

    @Test
    public void testIsPrimitiveOrWrapper() {
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.TYPE));
    }

    @Test
    public void testIsPublic() {
        assertTrue(ClassUtils.isPublic(String.class));
    }

    @Test
    public void testPrimitivesToWrappers() {
        Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class, boolean.class);
        assertEquals(Arrays.asList(Integer.class, Boolean.class), Arrays.asList(wrappers));
    }

    @Test
    public void testPrimitiveToWrapper() {
        Class<?> wrapper = ClassUtils.primitiveToWrapper(int.class);
        assertEquals(Integer.class, wrapper);
    }

    @Test
    public void testWrappersToPrimitives() {
        Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Boolean.class);
        assertEquals(Arrays.asList(int.class, boolean.class), Arrays.asList(primitives));
    }

    @Test
    public void testWrapperToPrimitive() {
        Class<?> primitive = ClassUtils.wrapperToPrimitive(Integer.class);
        assertEquals(int.class, primitive);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testInterfacesEnum() {
            assertEquals(ClassUtils.Interfaces.INCLUDE, ClassUtils.Interfaces.INCLUDE);
            assertEquals(ClassUtils.Interfaces.EXCLUDE, ClassUtils.Interfaces.EXCLUDE);
        }

    @Test
        public void testComparator() {
            Comparator<Class<?>> comparator = ClassUtils.comparator();
            assertNotNull(comparator);
        }

    @Test
        public void testConvertClassesToClassNames_NullInput() {
            List<String> result = ClassUtils.convertClassesToClassNames(null);
            assertNull(result);
        }

    @Test
        public void testConvertClassesToClassNames_EmptyList() {
            List<Class<?>> emptyList = Collections.emptyList();
            List<String> result = ClassUtils.convertClassesToClassNames(emptyList);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testConvertClassesToClassNames_SingleElement() {
            List<Class<?>> singleElementList = Arrays.asList(String.class);
            List<String> result = ClassUtils.convertClassesToClassNames(singleElementList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("java.lang.String", result.get(0));
        }

    @Test
        public void testConvertClassNamesToClasses_NullInput() {
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(null);
            assertNull(result);
        }

    @Test
        public void testConvertClassNamesToClasses_EmptyList() {
            List<String> emptyList = Collections.emptyList();
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(emptyList);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClasses_SingleElement() throws ClassNotFoundException {
            List<String> singleElementList = Arrays.asList("java.lang.String");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(singleElementList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(String.class, result.get(0));
        }

    @Test
        public void testConvertClassNamesToClasses_InvalidClassName() {
            List<String> invalidClassNameList = Arrays.asList("invalid.className");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(invalidClassNameList);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertNull(result.get(0));
        }

    @Test
        public void testGetAbbreviatedName_NullInput() {
            String result = ClassUtils.getAbbreviatedName(null, 5);
            assertEquals("", result);
        }

    @Test
        public void testGetAbbreviatedName_EmptyString() {
            String result = ClassUtils.getAbbreviatedName("", 5);
            assertEquals("", result);
        }

    @Test
        public void testGetAbbreviatedName_SingleCharacter() {
            String result = ClassUtils.getAbbreviatedName("A", 1);
            assertEquals("A", result);
        }

    @Test
        public void testGetAbbreviatedName_LengthHintGreaterThanClassNameLength() {
            String result = ClassUtils.getAbbreviatedName("ABC", 5);
            assertEquals("ABC", result);
        }

    @Test
        public void testGetAbbreviatedName_NormalCase() {
            String result = ClassUtils.getAbbreviatedName("com.example.MyClass", 10);
            assertEquals("com.examp...", result);
        }

    @Test
        public void testGetAllInterfaces_NullInput() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(null);
            assertNull(result);
        }

    @Test
        public void testGetAllInterfaces_EmptyClass() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(Object.class);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testGetAllInterfaces_SingleInterface() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(List.class);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(Collection.class, result.get(0));
        }

    @Test
        public void testGetAllSuperclasses_NullInput() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
            assertNull(result);
        }

    @Test
        public void testGetAllSuperclasses_EmptyClass() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(Object.class);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testGetAllSuperclasses_SingleSuperclass() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(List.class, result.get(0));
        }

    @Test
        public void testGetCanonicalName_NullInput() {
            String result = ClassUtils.getCanonicalName(null);
            assertEquals("", result);
        }

    @Test
        public void testGetCanonicalName_EmptyClass() {
            String result = ClassUtils.getCanonicalName(Object.class);
            assertEquals("java.lang.Object", result);
        }

    @Test
        public void testGetCanonicalName_ArrayClass() {
            String result = ClassUtils.getCanonicalName(int[].class);
            assertEquals("[I", result);
        }

    @Test
        public void testGetCanonicalName_InvalidClassName() {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass("invalid.className"));
        }

    @Test
        public void testGetCanonicalName_NullObject() {
            String result = ClassUtils.getCanonicalName((Object) null);
            assertEquals("", result);
        }
}
