package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUtils_GenTest {

    @Test
    void testComparator() {
        // Arrange
        List<Class<?>> classes = Arrays.asList(String.class, Integer.class);
        Comparator<Class<?>> comparator = ClassUtils.comparator();

        // Act & Assert
        assertTrue(comparator.compare(Integer.class, String.class) < 0);
        assertTrue(comparator.compare(String.class, Integer.class) > 0);
        assertEquals(0, comparator.compare(Integer.class, Integer.class));
    }

    @Test
    void testConvertClassesToClassNames_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.convertClassesToClassNames(null));
    }

    @Test
    void testConvertClassesToClassNames_withEmptyList() {
        // Arrange & Act
        List<String> result = ClassUtils.convertClassesToClassNames(Collections.emptyList());

        // Assert
        assertNotNull(result);
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
        // Arrange & Act
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(Collections.emptyList());

        // Assert
        assertNotNull(result);
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

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void testGetAbbreviatedName_withInvalidLengthHint(int lengthHint) {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName("java.lang.String", lengthHint));
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
    void testGetAllSuperclasses_withNull() {
        // Arrange & Act & Assert
        assertNull(ClassUtils.getAllSuperclasses(null));
    }

    @Test
    void testGetAllSuperclasses_withObjectClass() {
        // Arrange
        Class<?> cls = Object.class;

        // Act
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(cls);

        // Assert
        assertTrue(superclasses.isEmpty());
    }

    @Test
    void testGetCanonicalName_withNullClass() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Class<?>) null));
    }

    @Test
    void testGetCanonicalName_withValidClass() {
        // Arrange
        Class<?> cls = String.class;

        // Act
        String canonicalName = ClassUtils.getCanonicalName(cls);

        // Assert
        assertEquals("java.lang.String", canonicalName);
    }

    @Test
    void testGetCanonicalName_withNullObject() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getCanonicalName((Object) null));
    }

    @Test
    void testGetCanonicalName_withValidObject() {
        // Arrange
        Object obj = new String();

        // Act
        String canonicalName = ClassUtils.getCanonicalName(obj);

        // Assert
        assertEquals("java.lang.String", canonicalName);
    }

    
    
    @Test
    void testGetName_withNullClass() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Class<?>) null));
    }

    @Test
    void testGetName_withValidClass() {
        // Arrange
        Class<?> cls = String.class;

        // Act
        String name = ClassUtils.getName(cls);

        // Assert
        assertEquals("java.lang.String", name);
    }

    @Test
    void testGetName_withNullObject() {
        // Arrange & Act & Assert
        assertEquals("", ClassUtils.getName((Object) null));
    }

    @Test
    void testGetName_withValidObject() {
        // Arrange
        Object obj = new String();

        // Act
        String name = ClassUtils.getName(obj);

        // Assert
        assertEquals("java.lang.String", name);
    }
}
