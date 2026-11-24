package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Comparator;

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
        public void testComparator() {
            Comparator<Class<?>> comparator = ClassUtils.comparator();
            assertNotNull(comparator);
        }

    @Test
        public void testGetAbbreviatedName_NullInput() {
            String result = ClassUtils.getAbbreviatedName(null, 5);
            assertEquals("", result);
        }

    @Test
        public void testGetAllInterfaces_SingleInterface() {
            List<Class<?>> result = ClassUtils.getAllInterfaces(List.class);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(Collection.class, result.get(0));
        }
}
