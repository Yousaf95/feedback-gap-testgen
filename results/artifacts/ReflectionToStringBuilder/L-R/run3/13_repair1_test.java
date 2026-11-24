package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    @Test
    void testToStringWithNullObject() {
        // Arrange
        Object object = null;

        // Act & Assert
        assertEquals("null", ReflectionToStringBuilder.toString(object));
    }

    @Test
    void testToStringWithDefaultStyle() {
        // Arrange
        TestClass testObject = new TestClass();

        // Act
        String result = ReflectionToStringBuilder.toString(testObject);

        // Assert
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=10"));
        assertTrue(result.contains("field2=null"));
    }

    @Test
    void testToStringWithCustomStyle() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(testObject, style);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null]"));
    }

    @Test
    void testToStringWithOutputTransients() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(testObject, style, true);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null"));
        assertTrue(result.contains("transientField=20]"));
    }

    @Test
    void testToStringWithOutputStatics() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(testObject, style, false, true);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null]"));
        assertFalse(result.contains("staticField")); // Static fields are not included by default
    }

    @Test
    void testToStringWithExcludeNullValues() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(testObject, style, false, false, true, null);

        // Assert
        assertTrue(result.contains("TestClass[field1=10]"));
        assertFalse(result.contains("field2")); // field2 is null and should be excluded
    }

    @Test
    void testToStringExcludeFieldNames() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toStringExclude(testObject, Collections.singletonList("field1"));

        // Assert
        assertTrue(result.contains("TestClass[field2=null]"));
        assertFalse(result.contains("field1")); // field1 is excluded
    }

    @Test
    void testToStringIncludeFieldNames() {
        // Arrange
        TestClass testObject = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toStringInclude(testObject, Collections.singletonList("field1"));

        // Assert
        assertTrue(result.contains("TestClass[field1=10]"));
        assertFalse(result.contains("field2")); // field2 is not included
    }

    @Test
    void testSetExcludeFieldNamesWithNull() {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act
        builder.setExcludeFieldNames((String[]) null);

        // Assert
        assertNull(builder.getExcludeFieldNames());
    }

    @Test
    void testSetIncludeFieldNamesWithNull() {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act
        builder.setIncludeFieldNames((String[]) null);

        // Assert
        assertNull(builder.getIncludeFieldNames());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testSetAppendTransients(boolean value) {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act
        builder.setAppendTransients(value);

        // Assert
        assertEquals(value, builder.isAppendTransients());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testSetAppendStatics(boolean value) {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act
        builder.setAppendStatics(value);

        // Assert
        assertEquals(value, builder.isAppendStatics());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testSetExcludeNullValues(boolean value) {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act
        builder.setExcludeNullValues(value);

        // Assert
        assertEquals(value, builder.isExcludeNullValues());
    }

    @Test
    void testSetUpToClassWithInvalidClass() {
        // Arrange
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> builder.setUpToClass(String.class));
    }

    private static class TestClass {
        public int field1 = 10;
        public String field2 = null;
        public transient int transientField = 20;
        public static int staticField = 30;
    }
}
