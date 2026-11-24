package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.Set;

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

    
    @Test
            public void testGetAllInterfacesWithMultipleInterfaces() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertEquals(2, interfaces.size());
            assertTrue(interfaces.contains(java.util.Collection.class));
            assertTrue(interfaces.contains(java.lang.Iterable.class));
        }

    
    @Test
            public void testGetAllSuperclassesWithMultipleSuperclasses() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertEquals(3, superclasses.size());
            assertTrue(superclasses.contains(java.util.AbstractList.class));
            assertTrue(superclasses.contains(java.util.AbstractCollection.class));
            assertTrue(superclasses.contains(java.lang.Object.class));
        }

    @Test
            public void testGetCanonicalNameWithNull() {
                String canonicalName = ClassUtils.getCanonicalName(null);
                assertEquals("", canonicalName);
            }

    @Test
            public void testGetCanonicalNameWithClass() {
            String canonicalName = ClassUtils.getCanonicalName(String.class);
            assertEquals("java.lang.String", canonicalName);
        }

    @Test
            public void testGetPackageNameWithNull() {
            String packageName = ClassUtils.getPackageName(null, "");
            assertEquals("", packageName);
        }

    @Test
            public void testGetPackageNameWithClass() {
            String packageName = ClassUtils.getPackageName(String.class, "default");
            assertEquals("java.lang", packageName);
        }

    @Test
            public void testIsPrimitiveOrWrapperWithPrimitive() {
            boolean isPrimitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(int.class);
            assertTrue(isPrimitiveOrWrapper);
        }

    @Test
            public void testIsPrimitiveOrWrapperWithWrapper() {
            boolean isPrimitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(Integer.class);
            assertTrue(isPrimitiveOrWrapper);
        }

    @Test
            public void testIsPublicWithNonPublicClass() {
            boolean isPublic = ClassUtils.isPublic(new Object(){}.getClass());
            assertFalse(isPublic);
        }

    @Test
            public void testIsPublicWithPublicClass() {
            boolean isPublic = ClassUtils.isPublic(String.class);
            assertTrue(isPublic);
        }

    @Test
            public void testPrimitivesToWrappersWithSinglePrimitive() {
            Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class);
            assertEquals(1, wrappers.length);
            assertEquals(Integer.class, wrappers[0]);
        }

    @Test
            public void testPrimitivesToWrappersWithMultiplePrimitives() {
            Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class, boolean.class, double.class);
            assertEquals(3, wrappers.length);
            assertEquals(Integer.class, wrappers[0]);
            assertEquals(Boolean.class, wrappers[1]);
            assertEquals(Double.class, wrappers[2]);
        }

    @Test
            public void testWrappersToPrimitivesWithSingleWrapper() {
            Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class);
            assertEquals(1, primitives.length);
            assertEquals(int.class, primitives[0]);
        }

    @Test
            public void testWrappersToPrimitivesWithMultipleWrappers() {
            Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Boolean.class, Double.class);
            assertEquals(3, primitives.length);
            assertEquals(int.class, primitives[0]);
            assertEquals(boolean.class, primitives[1]);
            assertEquals(double.class, primitives[2]);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAllInterfacesWithEmptyClass() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Object.class);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        public void testGetAllSuperclassesWithEmptyClass() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(Object.class);
            assertEquals(1, superclasses.size());
            assertEquals(Object.class, superclasses.get(0));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithNull() {
            assertFalse(ClassUtils.isPrimitiveOrWrapper(null));
        }

    @Test
        public void testIsPublicWithInterface() {
            assertTrue(ClassUtils.isPublic(List.class));
        }

    @Test
        public void testGetAllInterfacesWithMultipleInterfaces_2() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Set.class);
            assertEquals(2, interfaces.size());
            assertTrue(interfaces.contains(Collection.class));
            assertTrue(interfaces.contains(Map.class));
        }

    @Test
        public void testGetAllSuperclassesWithMultipleSuperclasses_2() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertEquals(3, superclasses.size());
            assertTrue(superclasses.contains(List.class));
            assertTrue(superclasses.contains(Collection.class));
            assertTrue(superclasses.contains(Object.class));
        }

    @Test
        public void testGetCanonicalNameWithNull_2() {
            assertEquals("", ClassUtils.getCanonicalName(null));
        }

    @Test
        public void testGetPackageNameWithNull_2() {
            assertEquals("", ClassUtils.getPackageName(null));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithPrimitive_2() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithWrapper_2() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
        }

    @Test
        public void testIsPublicWithNonPublicClass_2() {
            assertFalse(ClassUtils.isPublic(new Object(){}.getClass()));
        }

    @Test
        public void testIsPublicWithPublicClass_2() {
            assertTrue(ClassUtils.isPublic(String.class));
        }

    @Test
        public void testPrimitivesToWrappersWithSinglePrimitive_2() {
            Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class);
            assertEquals(1, wrappers.length);
            assertEquals(Integer.class, wrappers[0]);
        }

    @Test
        public void testPrimitivesToWrappersWithMultiplePrimitives_2() {
            Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class, boolean.class, double.class);
            assertEquals(3, wrappers.length);
            assertEquals(Integer.class, wrappers[0]);
            assertEquals(Boolean.class, wrappers[1]);
            assertEquals(Double.class, wrappers[2]);
        }

    @Test
        public void testWrappersToPrimitivesWithSingleWrapper_2() {
            Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class);
            assertEquals(1, primitives.length);
            assertEquals(int.class, primitives[0]);
        }

    @Test
        public void testWrappersToPrimitivesWithMultipleWrappers_2() {
            Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Boolean.class, Double.class);
            assertEquals(3, primitives.length);
            assertEquals(int.class, primitives[0]);
            assertEquals(boolean.class, primitives[1]);
            assertEquals(double.class, primitives[2]);
        }

    @Test
        public void testGetAllInterfacesWithInterface() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertEquals(2, interfaces.size());
            assertTrue(interfaces.contains(Collection.class));
            assertTrue(interfaces.contains(Map.class));
        }
}
