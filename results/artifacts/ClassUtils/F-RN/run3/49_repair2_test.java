package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    
    
    @Test
    public void testConvertClassesToClassNamesWithNull() {
        List<Class<?>> classes = null;
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        assertNull(result);
    }

    
    @Test
    public void testConvertClassNamesToClassesWithNull() {
        List<String> classNames = null;
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.String", "int"})
    public void testGetAbbreviatedName(String className) {
        String abbreviatedName = ClassUtils.getAbbreviatedName(className, 10);
        assertNotNull(abbreviatedName);
        assertTrue(abbreviatedName.length() <= 10);
    }

    @Test
    public void testGetAllInterfacesWithNull() {
        List<Class<?>> result = ClassUtils.getAllInterfaces(null);
        assertNull(result);
    }

    
    @Test
    public void testGetAllSuperclassesWithNull() {
        List<Class<?>> result = ClassUtils.getAllSuperclasses(null);
        assertNull(result);
    }

    
    
    
    
    
    
    
    @Test
    public void testIsAssignable() throws ClassNotFoundException {
        boolean result = ClassUtils.isAssignable(Class.forName("java.lang.String"), Class.forName("java.lang.Object"));
        assertTrue(result);
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testConvertClassesToClassNamesWithEmptyList() {
            List<Class<?>> classes = Arrays.asList();
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClassesWithEmptyList() {
            List<String> classNames = Arrays.asList();
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertTrue(classes.isEmpty());
        }

    
    @Test
        public void testGetAllInterfacesWithEmptyClass() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Object.class);
            assertTrue(interfaces.isEmpty());
        }

    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAllInterfacesWithClassWithNoInterfaces() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(String.class);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        public void testGetAllInterfacesWithClassWithInterfaces() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertEquals(1, interfaces.size());
            assertEquals(java.util.Collection.class, interfaces.get(0));
        }

    @Test
        public void testGetAllSuperclassesWithClassWithNoSuperclass() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(String.class);
            assertTrue(superclasses.isEmpty());
        }

    @Test
        public void testGetAllSuperclassesWithClassWithSuperclass() {
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
            assertEquals(1, superclasses.size());
            assertEquals(java.util.AbstractList.class, superclasses.get(0));
        }

    @Test
        public void testIsAssignableWithPrimitiveToWrapper() {
            assertTrue(ClassUtils.isAssignable(int.class, Integer.class));
        }

    @Test
        public void testConvertClassesToClassNamesWithNonEmptyList() {
            List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(2, classNames.size());
            assertEquals("java.lang.String", classNames.get(0));
            assertEquals("java.lang.Integer", classNames.get(1));
        }

    @Test
        public void testConvertClassNamesToClassesWithNonEmptyList() {
            List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(2, classes.size());
            assertEquals(String.class, classes.get(0));
            assertEquals(Integer.class, classes.get(1));
        }

    @Test
        public void testGetAbbreviatedNameWithLongClassName() {
            String abbreviatedName = ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils", 5);
            assertEquals("org.a.c.l.C", abbreviatedName);
        }

    @Test
        public void testGetAbbreviatedNameWithShortClassName() {
            String abbreviatedName = ClassUtils.getAbbreviatedName("String", 10);
            assertEquals("String", abbreviatedName);
        }

    @Test
        public void testIsInnerClassWithNonInnerClass() {
            assertFalse(ClassUtils.isInnerClass(String.class));
        }

    @Test
        public void testIsInnerClassWithInnerClass() {
            assertTrue(ClassUtils.isInnerClass(new Object(){}.getClass()));
        }
}
