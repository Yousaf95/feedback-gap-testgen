package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    @Test
    void testComparator() {
        // Arrange
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
        
        // Act
        classes.sort(ClassUtils.comparator());
        
        // Assert
        assertEquals(Arrays.asList(Integer.class, String.class), classes);
    }

    @Test
    void testConvertClassesToClassNames_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.convertClassesToClassNames(null));
    }

    @Test
    void testConvertClassesToClassNames_withEmptyList() {
        // Arrange
        List<Class<?>> classes = Collections.emptyList();
        
        // Act
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertClassesToClassNames_withClasses() {
        // Arrange
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
        
        // Act
        List<String> result = ClassUtils.convertClassesToClassNames(classes);
        
        // Assert
        assertEquals(Arrays.asList("java.lang.String", "java.lang.Integer"), result);
    }

    @Test
    void testConvertClassNamesToClasses_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.convertClassNamesToClasses(null));
    }

    @Test
    void testConvertClassNamesToClasses_withEmptyList() {
        // Arrange
        List<String> classNames = Collections.emptyList();
        
        // Act
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertClassNamesToClasses_withValidClassNames() {
        // Arrange
        List<String> classNames = Arrays.asList("java.lang.String", "java.lang.Integer");
        
        // Act
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        
        // Assert
        assertEquals(Arrays.asList(String.class, Integer.class), result);
    }

    @Test
    void testConvertClassNamesToClasses_withInvalidClassName() {
        // Arrange
        List<String> classNames = Arrays.asList("java.lang.String", "NonExistentClass");
        
        // Act
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(classNames);
        
        // Assert
        assertEquals(Arrays.asList(String.class, null), result);
    }

    @Test
    void testGetAbbreviatedName_withNullClassName() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getAbbreviatedName((String) null, 10));
    }

    @Test
    void testGetAbbreviatedName_withLengthHintLessThanOne() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", 0));
    }

    @Test
    void testGetAbbreviatedName_withShortClassName() {
        // Arrange
        String className = "java.lang.String";
        
        // Act
        String result = ClassUtils.getAbbreviatedName(className, 20);
        
        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetAbbreviatedName_withLongClassName() {
        // Arrange
        String className = "org.apache.commons.lang3.ClassUtils";
        
        // Act
        String result = ClassUtils.getAbbreviatedName(className, 10);
        
        // Assert
        assertEquals("o.a.c.l.ClassUtils", result);
    }

    @Test
    void testGetAllInterfaces_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getAllInterfaces(null));
    }

    @Test
    void testGetAllInterfaces_withClassHavingInterfaces() {
        // Arrange
        Class<?> cls = List.class;
        
        // Act
        List<Class<?>> result = ClassUtils.getAllInterfaces(cls);
        
        // Assert
        assertTrue(result.contains(java.util.Collection.class));
        assertTrue(result.contains(Iterable.class));
    }

    @Test
    void testGetAllSuperclasses_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getAllSuperclasses(null));
    }

    @Test
    void testGetAllSuperclasses_withClassHavingSuperclasses() {
        // Arrange
        Class<?> cls = java.util.ArrayList.class;
        
        // Act
        List<Class<?>> result = ClassUtils.getAllSuperclasses(cls);
        
        // Assert
        assertTrue(result.contains(java.util.AbstractList.class));
        assertTrue(result.contains(java.util.AbstractCollection.class));
    }

    @Test
    void testGetCanonicalName_withNullClass() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Class<?>) null));
    }

    @Test
    void testGetCanonicalName_withNonNullClass() {
        // Arrange
        Class<?> cls = String.class;
        
        // Act
        String result = ClassUtils.getCanonicalName(cls);
        
        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetCanonicalName_withNullObject() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Object) null));
    }

    @Test
    void testGetComponentType_withNullArrayClass() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getComponentType((Class<String[]>) null));
    }

    
    @Test
    void testGetName_withNullClass() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Class<?>) null));
    }

    @Test
    void testGetName_withNonNullClass() {
        // Arrange
        Class<?> cls = String.class;
        
        // Act
        String result = ClassUtils.getName(cls);
        
        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetName_withNullObject() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Object) null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetPackageName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((Class<?>) null));
        }

    @Test
        void testGetPackageName_withNonNullClass() {
            assertEquals("java.lang", ClassUtils.getPackageName(String.class));
        }

    @Test
        void testGetPackageCanonicalName_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageCanonicalName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetPackageCanonicalName_withNonNullObject() {
            assertEquals("java.lang", ClassUtils.getPackageCanonicalName(new String(), StringUtils.EMPTY));
        }

    @Test
        void testGetShortClassName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortClassName((Class<?>) null));
        }

    @Test
        void testGetShortClassName_withNonNullClass() {
            assertEquals("String", ClassUtils.getShortClassName(String.class));
        }

    @Test
        void testGetSimpleName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName((Class<?>) null));
        }

    @Test
        void testGetSimpleName_withNonNullClass() {
            assertEquals("String", ClassUtils.getSimpleName(String.class));
        }

    @Test
        void testIsInnerClass_withNonInnerClass() {
            assertFalse(ClassUtils.isInnerClass(String.class));
        }

    @Test
        void testIsPrimitiveOrWrapper_withPrimitiveType() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(int.class));
        }

    @Test
        void testIsPrimitiveOrWrapper_withWrapperType() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class));
        }

    @Test
        void testIsPrimitiveOrWrapper_withNonPrimitiveOrWrapperType() {
            assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class));
        }

    @Test
        void testIsPublic_withPublicClass() {
            assertTrue(ClassUtils.isPublic(String.class));
        }

    
    @Test
        void testPrimitivesToWrappers_withEmptyArray() {
            assertEquals(0, ClassUtils.primitivesToWrappers().length);
        }

    @Test
        void testPrimitivesToWrappers_withPrimitiveClasses() {
            assertArrayEquals(new Class<?>[]{Integer.class}, ClassUtils.primitivesToWrappers(int.class));
        }

    @Test
        void testPrimitiveToWrapper_withNullClass() {
            assertNull(ClassUtils.primitiveToWrapper(null));
        }

    @Test
        void testPrimitiveToWrapper_withPrimitiveType() {
            assertEquals(Integer.class, ClassUtils.primitiveToWrapper(int.class));
        }

    
    @Test
        void testWrappersToPrimitives_withEmptyArray() {
            assertEquals(0, ClassUtils.wrappersToPrimitives().length);
        }

    @Test
        void testWrappersToPrimitives_withWrapperClasses() {
            assertArrayEquals(new Class<?>[]{int.class}, ClassUtils.wrappersToPrimitives(Integer.class));
        }

    @Test
        void testWrapperToPrimitive_withNullClass() {
            assertNull(ClassUtils.wrapperToPrimitive(null));
        }

    @Test
        void testWrapperToPrimitive_withWrapperType() {
            assertEquals(int.class, ClassUtils.wrapperToPrimitive(Integer.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetPackageName_withNullClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((String) null));
        }

    @Test
        void testGetPackageName_withEmptyClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName(""));
        }

    @Test
        void testGetPackageName_withSimpleClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName("String"));
        }

    @Test
        void testGetPackageName_withFullyQualifiedClassName() {
            assertEquals("java.lang", ClassUtils.getPackageName("java.lang.String"));
        }

    @Test
        void testGetShortClassName_withNullClass_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortClassName((Class<?>) null));
        }

    @Test
        void testGetShortClassName_withSimpleClass() {
            assertEquals("String", ClassUtils.getShortClassName(String.class));
        }

    @Test
        void testGetComponentType_withNonArrayClass() {
            assertNull(ClassUtils.getComponentType((Class<String[]>) null));
        }

    @Test
        void testGetComponentType_withArrayClass() {
            assertEquals(String.class, ClassUtils.getComponentType(String[].class));
        }

    @Test
        void testGetCanonicalName_withNullClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName((String) null));
        }

    
    @Test
        void testGetCanonicalName_withSimpleClassName() {
            assertEquals("java.lang.String", ClassUtils.getCanonicalName("String"));
        }

    @Test
        void testGetCanonicalName_withFullyQualifiedClassName() {
            assertEquals("java.lang.String", ClassUtils.getCanonicalName("java.lang.String"));
        }

    
    @Test
        void testWrapperToPrimitive_withNonWrapperType() {
            assertNull(ClassUtils.wrapperToPrimitive(String.class));
        }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        void testGetPackageName_withNullClass_2() {
            assertNull(ClassUtils.getPackageName((Class<?>) null));
        }

    @Test
        void testGetComponentType_withNonArrayClass_2() {
            assertNull(ClassUtils.getComponentType((Class<String[]>) null));
        }

    @Test
        void testGetComponentType_withMultiDimensionalArrayClass() {
            assertEquals(Integer.class, ClassUtils.getComponentType(int[][].class));
        }

    @Test
        void testGetShortClassName_withNullObject() {
            assertEquals("", ClassUtils.getShortClassName((Class<?>) null));
        }

    @Test
        void testGetSimpleName_withNullObject_2() {
            assertEquals("", ClassUtils.getSimpleName((Object) null));
        }

    @Test
        void testIsInnerClass_withStaticNestedClass() {
            class Outer {
                static class StaticNested {}
            }
            assertFalse(ClassUtils.isInnerClass(Outer.StaticNested.class));
        }

    @Test
        void testIsPrimitiveOrWrapper_withVoidType() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(void.class));
        }

    @Test
        void testPrimitivesToWrappers_withNullArray() {
            assertNull(ClassUtils.primitivesToWrappers((Class<?>[]) null));
        }

    @Test
        void testWrappersToPrimitives_withNullArray() {
            assertNull(ClassUtils.wrappersToPrimitives((Class<?>[]) null));
        }

    @Test
        void testGetCanonicalName_withNullClassName_2() {
            assertEquals("", ClassUtils.getCanonicalName((String) null));
        }

    @Test
        void testGetShortClassName_withFullyQualifiedClassName() {
            assertEquals("ClassUtils_GenTest", ClassUtils.getShortClassName(ClassUtils_GenTest.class.getName()));
        }

    @Test
        void testGetPackageName_withSimpleClassName_2() {
            assertEquals("", ClassUtils.getPackageName("SimpleClassName"));
        }

    @Test
        void testGetPackageCanonicalName_withNullObject_2() {
            assertEquals("", ClassUtils.getPackageCanonicalName((Object) null, "default"));
        }

    @Test
        void testGetShortCanonicalName_withFullyQualifiedClassName() {
            assertEquals("ClassUtils_GenTest", ClassUtils.getShortCanonicalName(ClassUtils_GenTest.class.getName()));
        }

    @Test
        void testGetAbbreviatedName_withLengthHintEqualToClassNameLength() {
            String className = "org.apache.commons.lang3.ClassUtils";
            assertEquals(className, ClassUtils.getAbbreviatedName(className, className.length()));
        }

    @Test
        void testGetAllInterfaces_withClassHavingNoInterfaces() {
            assertTrue(ClassUtils.getAllInterfaces(String.class).isEmpty());
        }

    @Test
        void testIsPublic_withNonPublicClass() {
            class Outer {
                class NonPublicInner {}
            }
            assertFalse(ClassUtils.isPublic(Outer.NonPublicInner.class));
        }

    @Test
        void testGetCanonicalName_withArrayClassName() {
            assertEquals("int[]", ClassUtils.getCanonicalName(int[].class.getName()));
        }

    @Test
        void testGetPackageName_withFullyQualifiedClassName_2() {
            assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(ClassUtils_GenTest.class.getName()));
        }

    @Test
        void testIsPrimitiveWrapper_withVoidType() {
            assertFalse(ClassUtils.isPrimitiveWrapper(void.class));
        }

    @Test
        void testGetClass_withNullClassName() {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass((String) null));
        }

    @Test
        void testGetClass_withInvalidClassName() {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass("NonExistentClass"));
        }
}
