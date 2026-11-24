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
            public void testComparator() {
                Comparator<Class<?>> comparator = ClassUtils.comparator();
                assertNotNull(comparator);
            }

    @org.junit.jupiter.api.Test
            public void testConvertClassesToClassNames_emptyList() {
                List<Class<?>> classes = Collections.emptyList();
                List<String> result = ClassUtils.convertClassesToClassNames(classes);
                assertNotNull(result);
                assertTrue(result.isEmpty());
            }

    @org.junit.jupiter.api.Test
            public void testConvertClassNamesToClasses_emptyList() {
                List<String> classNames = Collections.emptyList();
                List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
                assertNotNull(result);
                assertTrue(result.isEmpty());
            }

    @org.junit.jupiter.api.Test
            public void testGetAbbreviatedName_nullInput() {
                String result = ClassUtils.getAbbreviatedName(null, 5);
                assertEquals("", result);
            }

    @org.junit.jupiter.api.Test
            public void testGetAllInterfaces_nonEmptyClass() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(List.class);
            assertNotNull(result);
            assertTrue(result.contains(Collection.class));
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
}
