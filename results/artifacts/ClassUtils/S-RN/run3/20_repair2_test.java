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
    @Test
            public void testGetPackageName() {
                assertEquals("org.apache.commons", ClassUtils.getPackageName(ClassUtils.class));
                assertNull(ClassUtils.getPackageName(null));
            }

    @Test
            public void testIsPublic_2() {
                assertTrue(ClassUtils.isPublic(String.class));
                assertFalse(ClassUtils.isPublic(new Object() {}).getClass().isPublic());
            }

    @Test
            public void testGetAllSuperclasses() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(List.class);
            assertEquals(2, superclasses.size());
            assertTrue(superclasses.contains(Object.class));
            assertTrue(superclasses.contains(java.util.AbstractList.class));
        }

    @Test
            public void testIsInnerClass_2() {
                assertFalse(ClassUtils.isInnerClass(new Object() {}).getClass().isMemberClass());
                assertTrue(ClassUtils.isInnerClass(String.class));
            }

    @Test
            public void testGetAllInterfaces_2() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertEquals(2, interfaces.size());
            assertTrue(interfaces.contains(java.util.Collection.class));
            assertTrue(interfaces.contains(java.lang.Iterable.class));
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
            public void testGetCanonicalNameWithNull() {
                assertNull(ClassUtils.getCanonicalName(null));
            }

    @Test
            public void testGetShortClassName() {
                assertEquals("String", ClassUtils.getShortClassName(String.class));
                assertEquals("Object", ClassUtils.getShortClassName(new Object()));
            }

    @Test
            public void testIsPrimitiveOrWrapper_2() {
                assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
                assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
                assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
            }

    @Test
            public void testPrimitivesToWrappers_2() {
                Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class, boolean.class);
                assertEquals(Arrays.asList(Integer.class, Boolean.class), Arrays.asList(wrappers));
            }

    @Test
            public void testWrappersToPrimitives_2() {
                Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Boolean.class);
                assertEquals(Arrays.asList(int.class, boolean.class), Arrays.asList(primitives));
            }
}
