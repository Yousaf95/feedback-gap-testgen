package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    
    @Test
    public void testConvertClassesToClassNames() {
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class, null);
        List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
        assertEquals(Arrays.asList("java.lang.String", "java.lang.Integer", null), classNames);
    }

    @Test
    public void testConvertClassNamesToClasses() {
        List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer", "nonexistent.class");
        List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
        assertEquals(3, classes.size());
        assertNotNull(classes.get(0));
        assertNotNull(classes.get(1));
        assertNull(classes.get(2));
    }

    
    @Test
    public void testGetAllInterfaces() {
        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
        assertTrue(interfaces.contains(java.util.Collection.class));
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
        assertEquals(2, wrappers.length);
        assertEquals(Integer.class, wrappers[0]);
        assertEquals(Boolean.class, wrappers[1]);
    }

    @Test
    public void testToClass() {
        Class<?>[] classes = ClassUtils.toClass("java.lang.String", 123);
        assertEquals(2, classes.length);
        assertEquals(String.class, classes[0]);
        assertEquals(Integer.class, classes[1]);
    }

    @Test
    public void testWrappersToPrimitives() {
        Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Boolean.class);
        assertEquals(2, primitives.length);
        assertEquals(int.class, primitives[0]);
        assertEquals(boolean.class, primitives[1]);
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
        public void testConvertClassesToClassNames_nullInput() {
            List<String> result = ClassUtils.convertClassesToClassNames(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNames_emptyList() {
            List<Class<?>> classes = Collections.emptyList();
            List<String> result = ClassUtils.convertClassesToClassNames(classes);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassesToClassNames_nonEmptyList() {
            List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
            List<String> result = ClassUtils.convertClassesToClassNames(classes);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains("java.lang.String"));
            assertTrue(result.contains("java.lang.Integer"));
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClasses_nullInput() {
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClasses_emptyList() {
            List<String> classNames = Collections.emptyList();
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClasses_nonEmptyList() {
            List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(String.class));
            assertTrue(result.contains(Integer.class));
        }

    @org.junit.jupiter.api.Test
        public void testConvertClassNamesToClasses_invalidClassName() {
            List<String> classNames = Arrays.asList("java.lang.String", "invalid.className");
            List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(String.class));
            assertNull(result.get(1));
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_nullInput() {
            String result = ClassUtils.getAbbreviatedName(null, 5);
            assertEquals("", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_emptyString() {
            String result = ClassUtils.getAbbreviatedName("", 5);
            assertEquals("", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_lengthHintLessThanOne() {
            assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("test", 0));
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_lengthHintGreaterThanStringLength() {
            String result = ClassUtils.getAbbreviatedName("test", 10);
            assertEquals("test", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_shortClassName() {
            String result = ClassUtils.getAbbreviatedName("java.lang.String", 5);
            assertEquals("String", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAbbreviatedName_longClassName() {
            String result = ClassUtils.getAbbreviatedName("com.example.MyVeryLongClassName", 10);
            assertEquals("MyVe...Class", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfaces_nullInput() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfaces_emptyClass() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(Object.class);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testGetAllInterfaces_nonEmptyClass() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(List.class);
            assertNotNull(result);
            assertTrue(result.contains(Collection.class));
            assertTrue(result.contains(Iterable.class));
        }

    @org.junit.jupiter.api.Test
        public void testGetAllSuperclasses_nullInput() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
            assertNull(result);
        }

    @org.junit.jupiter.api.Test
        public void testGetAllSuperclasses_emptyClass() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(Object.class);
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

    @org.junit.jupiter.api.Test
        public void testGetAllSuperclasses_nonEmptyClass() {
            List<Class<?>> result = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertNotNull(result);
            assertTrue(result.contains(List.class));
            assertTrue(result.contains(Collection.class));
            assertTrue(result.contains(Iterable.class));
            assertTrue(result.contains(Object.class));
        }

    @org.junit.jupiter.api.Test
        public void testGetCanonicalName_nullInput() {
            String result = ClassUtils.getCanonicalName(null, "default");
            assertEquals("default", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetCanonicalName_emptyString() {
            String result = ClassUtils.getCanonicalName("", "default");
            assertEquals("default", result);
        }

    @org.junit.jupiter.api.Test
        public void testGetCanonicalName_nonEmptyString() {
            String result = ClassUtils.getCanonicalName("java.lang.String", "default");
            assertEquals("java.lang.String", result);
        }
}
