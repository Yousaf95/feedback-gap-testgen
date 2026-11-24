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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetAllInterfacesWithNonEmptyClass() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(List.class);
            assertTrue(interfaces.contains(java.util.Collection.class));
            assertTrue(interfaces.contains(Iterable.class));
            assertEquals(2, interfaces.size());
        }

    @Test
        public void testIsPrimitiveOrWrapperWithBoolean() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithByte() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Byte.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithCharacter() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Character.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithShort() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Short.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithInteger() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithLong() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Long.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithDouble() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Double.class));
        }

    @Test
        public void testIsPrimitiveOrWrapperWithFloat() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Float.class));
        }

    
    @Test
        public void testIsInnerClassWithStaticNestedClass() {
            class Outer {
                static class Inner {}
            }
            assertTrue(ClassUtils.isInnerClass(Outer.Inner.class));
        }

    
    @Test
        public void testConvertClassesToClassNamesWithNonEmptyListAndNullElement() {
            List<Class<?>> classes = Arrays.asList(String.class, null, Integer.class);
            List<String> classNames = ClassUtils.convertClassesToClassNames(classes);
            assertEquals(3, classNames.size());
            assertEquals("java.lang.String", classNames.get(0));
            assertNull(classNames.get(1));
            assertEquals("java.lang.Integer", classNames.get(2));
        }

    @Test
        public void testConvertClassNamesToClassesWithNonEmptyListAndNullElement() {
            List<String> classNames = Arrays.asList("java.lang.String", null, "java.lang.Integer");
            List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(classNames);
            assertEquals(3, classes.size());
            assertEquals(String.class, classes.get(0));
            assertNull(classes.get(1));
            assertEquals(Integer.class, classes.get(2));
        }

    
    @Test
        public void testIsPrimitiveWrapperWithBooleanWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Boolean.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithByteWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Byte.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithCharacterWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Character.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithShortWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Short.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithIntegerWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Integer.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithLongWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Long.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithDoubleWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Double.class));
        }

    @Test
        public void testIsPrimitiveWrapperWithFloatWrapper() {
            assertTrue(ClassUtils.isPrimitiveWrapper(Float.class));
        }

    @Test
        public void testIsPublicWithPublicClass() {
            assertTrue(ClassUtils.isPublic(String.class));
        }

    @Test
        public void testIsPublicWithNonPublicClass() {
            class NonPublic {}
            assertFalse(ClassUtils.isPublic(NonPublic.class));
        }
}
