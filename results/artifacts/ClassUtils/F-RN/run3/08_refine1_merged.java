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
            List<Class<?>> classes = Collections.emptyList();
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertTrue(classNames.isEmpty());
        }

    @Test
        public void testConvertClassNamesToClassesWithEmptyList() {
            List<String> classNames = Collections.emptyList();
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertTrue(classes.isEmpty());
        }

    @Test
        public void testGetAbbreviatedNameWithShortClassName() {
            String className = "java.lang.String";
            int lengthHint = 10;
            assertEquals("String", ClassUtils.getAbbreviatedName(className, lengthHint));
        }

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
        public void testIsAssignableWithPrimitiveToWrapper() {
            assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class));
        }

    @Test
        public void testIsAssignableFromWithWrapperToPrimitive() {
            assertFalse(ClassUtils.isAssignable(Integer.class, Integer.TYPE));
        }

    @Test
        public void testIsAssignableFromWithArrayTypes() {
            assertTrue(ClassUtils.isAssignable(int[].class, Number[].class));
        }

    @Test
        public void testIsAssignableFromWithNonArrayTypes() {
            assertFalse(ClassUtils.isAssignableFrom(String.class, Number.class));
        }
}
