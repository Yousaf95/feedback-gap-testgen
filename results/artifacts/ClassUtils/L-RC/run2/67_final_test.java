package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

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
        void testInterfacesEnum() {
            assertNotNull(ClassUtils.Interfaces.INCLUDE);
            assertNotNull(ClassUtils.Interfaces.EXCLUDE);
        }

    @Test
        void testGetAbbreviatedName_withNullClass() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getAbbreviatedName((Class<?>) null, 10));
        }

    
    
    @Test
        void testGetCanonicalName_withNullObjectAndValueIfNull() {
            assertEquals("default", ClassUtils.getCanonicalName((Object) null, "default"));
        }

    
    
    @Test
        void testGetComponentType_withNonNullArrayClass() {
            assertEquals(Integer.class, ClassUtils.getComponentType(Integer[].class));
        }

    @Test
        void testGetClassNameWithNullObjectAndValueIfNull() {
            assertEquals("default", ClassUtils.getName((Object) null, "default"));
        }

    @Test
        void testGetPackageCanonicalName_withNullClassAndValueIfNull() {
            assertEquals("default", ClassUtils.getPackageCanonicalName((Class<?>) null, "default"));
        }

    @Test
        void testGetPackageCanonicalName_withNullObjectAndValueIfNull() {
            assertEquals("default", ClassUtils.getPackageCanonicalName((Object) null, "default"));
        }

    @Test
        void testGetPackageName_withNullClassAndValueIfNull() {
            assertEquals("default", ClassUtils.getPackageName((Class<?>) null, "default"));
        }

    @Test
        void testGetPackageName_withNullObjectAndValueIfNull() {
            assertEquals("default", ClassUtils.getPackageName((Object) null, "default"));
        }

    @Test
        void testGetPackageName_withEmptyClassName() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName(""));
        }

    
    
    @Test
        void testGetClass_withInvalidClassName() {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass("invalid.ClassName"));
        }

    
    
    @Test
        void testUseFullBranchesInGetAbbreviatedName() {
            String className = "org.apache.commons.lang3.ClassUtils";
            String abbreviatedName = ClassUtils.getAbbreviatedName(className, 20);
            assertEquals("o.a.c.l.ClassUtils", abbreviatedName);
        }

    @Test
        void testGetAllInterfaces_withClassHavingNoInterfaces() {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(Object.class);
            assertTrue(interfaces.isEmpty());
        }

    @Test
        void testGetCanonicalName_withNullClassAndValueIfNull() {
            String canonicalName = ClassUtils.getCanonicalName((Class<?>) null, "default");
            assertEquals("default", canonicalName);
        }

    @Test
        void testGetCanonicalName_withNonNullObject() {
            String canonicalName = ClassUtils.getCanonicalName(new Object());
            assertEquals("java.lang.Object", canonicalName);
        }

    
    @Test
        void testGetComponentType_withNonNullArrayClass_2() {
            Class<?> componentType = ClassUtils.getComponentType(String[].class);
            assertEquals(String.class, componentType);
        }

    @Test
        void testGetClassNameWithNullObjectAndValueIfNull_2() {
            String className = ClassUtils.getName((Object) null, "default");
            assertEquals("default", className);
        }

    @Test
        void testGetPackageCanonicalName_withNullClassAndValueIfNull_2() {
            String packageCanonicalName = ClassUtils.getPackageCanonicalName((Class<?>) null, "default");
            assertEquals("default", packageCanonicalName);
        }

    @Test
        void testGetPackageName_withNullObjectAndValueIfNull_2() {
            String packageName = ClassUtils.getPackageName((Object) null, "default");
            assertEquals("default", packageName);
        }

    @Test
        void testGetPackageName_withEmptyClassNameBranches() {
            String packageName = ClassUtils.getPackageName("");
            assertEquals("", packageName);
        }

    @Test
        void testGetClass_withInvalidClassNameThrowsException() {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass("invalid.ClassName"));
        }

    @Test
        void testGetPublicMethod_throwsExceptionWhenMethodNotFound() {
            assertThrows(NoSuchMethodException.class, () -> ClassUtils.getPublicMethod(String.class, "nonExistentMethod"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetAbbreviatedName_withNullClass_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getAbbreviatedName((Class<?>) null, 10));
        }

    @Test
        void testGetCanonicalName_withNullClassAndValueIfNull_2() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getCanonicalName((Class<?>) null, valueIfNull));
        }

    @Test
        void testGetCanonicalName_withNullObjectAndValueIfNull_2() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getCanonicalName((Object) null, valueIfNull));
        }

    @Test
        void testGetPackageCanonicalName_withNullClassAndValueIfNull_3() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getPackageCanonicalName((Class<?>) null, valueIfNull));
        }

    @Test
        void testGetPackageCanonicalName_withNullObjectAndValueIfNull_2() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getPackageCanonicalName((Object) null, valueIfNull));
        }

    @Test
        void testGetPackageName_withNullClassAndValueIfNull_2() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getPackageName((Class<?>) null, valueIfNull));
        }

    @Test
        void testGetPackageName_withNullObjectAndValueIfNull_3() {
            String valueIfNull = "default";
            assertEquals(valueIfNull, ClassUtils.getPackageName((Object) null, valueIfNull));
        }

    @Test
        void testGetPackageName_withEmptyClassNameBranches_2() {
            assertEquals(StringUtils.EMPTY, ClassUtils.getPackageName(""));
        }

    
    
    
    
    @Test
        void testGetPublicMethod_throwsExceptionWhenMethodNotFoundOnSuperclass() {
            assertThrows(NoSuchMethodException.class, () -> 
                ClassUtils.getPublicMethod(String.class, "nonExistentMethod"));
        }

    @Test
        void testGetClass_withInvalidClassNameThrowsException_2() {
            assertThrows(ClassNotFoundException.class, () -> 
                ClassUtils.getClass("invalid.ClassName"));
        }
}
