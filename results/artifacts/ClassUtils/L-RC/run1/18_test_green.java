package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // ---- added by coverage-guided refinement ----
    @Test
        void testInterfacesEnum() {
            assertEquals(ClassUtils.Interfaces.INCLUDE, ClassUtils.Interfaces.valueOf("INCLUDE"));
            assertEquals(ClassUtils.Interfaces.EXCLUDE, ClassUtils.Interfaces.valueOf("EXCLUDE"));
        }

    @Test
        void testGetAbbreviatedName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getAbbreviatedName((Class<?>) null, 10));
        }

    @Test
        void testGetComponentType_withNullArrayClass() {
            assertNull(ClassUtils.getComponentType(null));
        }

    @Test
        void testGetCanonicalName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName((Class<?>) null));
        }

    @Test
        void testGetCanonicalNameForObject_withNullObject() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName((Object) null));
        }

    @Test
        void testGetPackageCanonicalName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageCanonicalName((Class<?>) null));
        }

    
    @Test
        void testGetPackageName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName((Class<?>) null));
        }

    @Test
        void testGetPackageNameForObject_withNullObject() {
            assertEquals("default", ClassUtils.getPackageName((Object) null, "default"));
        }

    
    
    @Test
        void testGetAllSuperclasses_withObjectClass_2() {
            List<Class<?>> superClasses = ClassUtils.getAllSuperclasses(Object.class);
            assertEquals(0, superClasses.size());
        }

    @Test
        void testGetAllInterfaces_withNull_2() {
            assertNull(ClassUtils.getAllInterfaces(null));
        }

    @Test
        void testGetCanonicalName_withPrimitiveClass() {
            assertEquals("int", ClassUtils.getCanonicalName(int.class));
        }

    
    
    @Test
        void testGetPackageName_withEmptyClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName(""));
        }

    @Test
        void testGetPackageName_withArrayClassName() {
            assertEquals("java.lang", ClassUtils.getPackageName("[Ljava.lang.String;"));
        }
}
