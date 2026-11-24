package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    @Test
    void testComparator() {
        // Arrange
        Comparator<Class<?>> comparator = ClassUtils.comparator();
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class);

        // Act & Assert
        assertTrue(comparator.compare(Integer.class, String.class) < 0);
        assertTrue(comparator.compare(String.class, Integer.class) > 0);
    }

    @Test
    void testConvertClassesToClassNames_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.convertClassesToClassNames(null));
    }

    @Test
    void testConvertClassesToClassNames_withEmptyList() {
        // Arrange
        List<Class<?>> classes = Arrays.asList();

        // Act
        List<String> result = ClassUtils.convertClassesToClassNames(classes);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertClassesToClassNames_withValidClasses() {
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
        List<String> classNames = Arrays.asList();

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
        assertTrue(result.contains(Iterable.class));
    }

    @Test
    void testGetAllSuperclasses_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getAllSuperclasses(null));
    }

    @Test
    void testGetAllSuperclasses_withObjectClass() {
        // Arrange
        Class<?> cls = Object.class;

        // Act
        List<Class<?>> result = ClassUtils.getAllSuperclasses(cls);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCanonicalName_withNull() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Class<?>) null));
    }

    @Test
    void testGetCanonicalName_withValidClass() {
        // Arrange
        Class<?> cls = String.class;

        // Act
        String result = ClassUtils.getCanonicalName(cls);

        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetCanonicalNameForObject_withNull() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Object) null));
    }

    @Test
    void testGetCanonicalNameForObject_withValidObject() {
        // Arrange
        Object obj = new String();

        // Act
        String result = ClassUtils.getCanonicalName(obj);

        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetComponentType_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getComponentType((Class<String[]>) null));
    }

    
    @Test
    void testGetName_withNull() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Class<?>) null));
    }

    @Test
    void testGetName_withValidClass() {
        // Arrange
        Class<?> cls = String.class;

        // Act
        String result = ClassUtils.getName(cls);

        // Assert
        assertEquals("java.lang.String", result);
    }

    @Test
    void testGetNameForObject_withNull() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Object) null));
    }

    @Test
    void testGetNameForObject_withValidObject() {
        // Arrange
        Object obj = new String();

        // Act
        String result = ClassUtils.getName(obj);

        // Assert
        assertEquals("java.lang.String", result);
    }

    
    
    @Test
        void testGetPackageCanonicalName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageCanonicalName((Class<?>) null));
        }

    @Test
        void testGetPackageCanonicalName_withValidClass() {
            assertEquals("java.lang", ClassUtils.getPackageCanonicalName(String.class));
        }

    @Test
        void testGetPackageName_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetPackageName_withValidObject() {
            assertEquals("java.lang", ClassUtils.getPackageName(new String(), StringUtils.EMPTY));
        }

    @Test
        void testGetShortCanonicalName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortCanonicalName((Class<?>) null, StringUtils.EMPTY));
        }

    
    @Test
        void testGetShortClassName_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortClassName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetShortClassName_withValidObject() {
            assertEquals("String", ClassUtils.getShortClassName(new String(), StringUtils.EMPTY));
        }

    @Test
        void testGetSimpleName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName((Class<?>) null, StringUtils.EMPTY));
        }

    @Test
        void testGetSimpleName_withValidClass() {
            assertEquals("String", ClassUtils.getSimpleName(String.class, StringUtils.EMPTY));
        }

    @Test
        void testGetSimpleName_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetSimpleName_withValidObject() {
            assertEquals("String", ClassUtils.getSimpleName(new String(), StringUtils.EMPTY));
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
        void testIsPublic_withPrivateClass() {
            class PrivateClass {}
            assertFalse(ClassUtils.isPublic(PrivateClass.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetComponentType_withNullArrayClass() {
            assertNull(ClassUtils.getComponentType((Class<Integer[]>) null));
        }

    
    @Test
        void testGetPackageName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((Class<?>) null));
        }

    @Test
        void testGetPackageName_withObjectClass() {
            assertEquals("java.lang", ClassUtils.getPackageName(Object.class));
        }

    @Test
        void testGetShortClassName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortClassName((Class<?>) null));
        }

    @Test
        void testGetShortClassName_withValidClass() {
            assertEquals("String", ClassUtils.getShortClassName(String.class));
        }

    @Test
        void testGetSimpleName_withNullClass_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName((Class<?>) null));
        }

    @Test
        void testGetSimpleName_withObjectClass() {
            assertEquals("Object", ClassUtils.getSimpleName(Object.class));
        }

    @Test
        void testIsPrimitiveOrWrapper_withVoidType() {
            assertTrue(ClassUtils.isPrimitiveOrWrapper(void.class));
        }

    @Test
        void testGetCanonicalName_withArrayClass() {
            assertEquals("int[]", ClassUtils.getCanonicalName(int[].class));
        }

    @Test
        void testGetShortCanonicalName_withNullClass_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortCanonicalName((Class<?>) null));
        }

    @Test
        void testGetShortCanonicalName_withValidClass() {
            assertEquals("String", ClassUtils.getShortCanonicalName(String.class));
        }

    
    
    @Test
        void testGetAbbreviatedName_withLengthHintEqualToClassNameLength() {
            assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class, 17));
        }

    @Test
        void testGetAllInterfaces_withClassHavingNoInterfaces() {
            assertTrue(ClassUtils.getAllInterfaces(Object.class).isEmpty());
        }

    @Test
        void testIsPublic_withAnonymousInnerClass() {
            assertFalse(ClassUtils.isPublic(new Object(){}.getClass()));
        }

    @Test
        void testGetCanonicalName_withPrimitiveType() {
            assertEquals("int", ClassUtils.getCanonicalName(int.class));
        }

    @Test
        void testGetShortClassName_withArrayClass() {
            assertEquals("int[]", ClassUtils.getShortClassName(int[].class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetComponentType_withNonArrayClass() {
            assertEquals(null, ClassUtils.getComponentType((Class<Integer[]>) null));
        }

    @Test
        void testGetPackageName_withNullClass_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((Class<?>) null));
        }

    @Test
        void testGetShortCanonicalName_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortCanonicalName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetShortClassName_withNullObject_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortClassName((Object) null, StringUtils.EMPTY));
        }

    @Test
        void testGetSimpleName_withNullClass_3() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName((Class<?>) null, StringUtils.EMPTY));
        }

    @Test
        void testGetCanonicalName_withPrimitiveTypeArray() {
            assertEquals("[I", ClassUtils.getCanonicalName(int[].class));
        }

    @Test
        void testGetShortClassName_withPrimitiveArrayType() {
            assertEquals("int[]", ClassUtils.getShortClassName(int[].class));
        }

    @Test
        void testGetComponentType_withValidArrayClass() {
            assertEquals(Integer.class, ClassUtils.getComponentType((Class<Integer[]>) new Integer[0].getClass()));
        }

    @Test
        void testGetPackageName_withObjectClass_2() {
            assertEquals("java.lang", ClassUtils.getPackageName(Object.class));
        }

    @Test
        void testGetShortCanonicalName_withNullClass_3() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getShortCanonicalName((Class<?>) null));
        }

    @Test
        void testGetAbbreviatedName_withLengthHintEqualToClassNameLength_2() {
            assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class.getName(), 17));
        }

    @Test
        void testGetAllInterfaces_withInterfaceClass() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Comparable.class);
            assertTrue(interfaces.isEmpty());
        }
}
