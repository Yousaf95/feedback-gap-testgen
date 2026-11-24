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
    public void testGetAbbreviatedName() {
        String abbreviatedName = ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils", 10);
        assertEquals("org.apache.commons.l...", abbreviatedName);
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
    public void testGetComponentType() {
        Class<String> componentType = ClassUtils.getComponentType(new String[0].getClass());
        assertEquals(String.class, componentType);
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
    public void testGetPublicMethod() throws NoSuchMethodException {
        Method method = ClassUtils.getPublicMethod(List.class, "add", Object.class);
        assertNotNull(method);
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
    public void testPrimitivesToWrappers() {
        Class<?>[] wrappers = ClassUtils.primitivesToWrappers(int.class, double.class);
        assertEquals(new Class<?>[]{Integer.class, Double.class}, wrappers);
    }

    @Test
    public void testToClass() {
        Class<?>[] classes = ClassUtils.toClass("java.lang.String", 123);
        assertEquals(new Class<?>[]{String.class, Integer.class}, classes);
    }

    @Test
    public void testWrappersToPrimitives() {
        Class<?>[] primitives = ClassUtils.wrappersToPrimitives(Integer.class, Double.class);
        assertEquals(new Class<?>[]{int.class, double.class}, primitives);
    }
}
