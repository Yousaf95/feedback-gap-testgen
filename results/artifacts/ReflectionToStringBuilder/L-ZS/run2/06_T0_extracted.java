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
        TestClass obj = new TestClass();

        // Act
        String result = ReflectionToStringBuilder.toString(obj);

        // Assert
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=10"));
        assertTrue(result.contains("field2=null"));
    }

    @Test
    void testToStringWithCustomStyle() {
        // Arrange
        TestClass obj = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(obj, style);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null]"));
    }

    @Test
    void testToStringWithOutputTransients() {
        // Arrange
        TestClass obj = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(obj, style, true);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null"));
        assertTrue(result.contains("transientField=20]"));
    }

    @Test
    void testToStringWithOutputStatics() {
        // Arrange
        TestClass obj = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(obj, style, false, true);

        // Assert
        assertTrue(result.contains("TestClass[field1=10"));
        assertTrue(result.contains("field2=null]"));
    }

    @Test
    void testToStringExcludeFields() {
        // Arrange
        TestClass obj = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toStringExclude(obj, Collections.singletonList("field1"));

        // Assert
        assertTrue(result.contains("TestClass[field2=null]"));
    }

    @Test
    void testToStringIncludeFields() {
        // Arrange
        TestClass obj = new TestClass();
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toStringInclude(obj, Collections.singletonList("field1"));

        // Assert
        assertTrue(result.contains("TestClass[field1=10]"));
    }

    @Test
    void testSetExcludeFieldNames() {
        // Arrange
        TestClass obj = new TestClass();
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
        builder.setExcludeFieldNames("field1", "field2");

        // Act
        String result = builder.toString();

        // Assert
        assertFalse(result.contains("field1=10"));
        assertFalse(result.contains("field2=null"));
    }

    @Test
    void testSetIncludeFieldNames() {
        // Arrange
        TestClass obj = new TestClass();
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
        builder.setIncludeFieldNames("field1");

        // Act
        String result = builder.toString();

        // Assert
        assertTrue(result.contains("field1=10"));
        assertFalse(result.contains("field2=null"));
    }

    @Test
    void testSetExcludeNullValues() {
        // Arrange
        TestClass obj = new TestClass();
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
        builder.setExcludeNullValues(true);

        // Act
        String result = builder.toString();

        // Assert
        assertTrue(result.contains("field1=10"));
        assertFalse(result.contains("field2=null"));
    }

    @Test
    void testSetUpToClass() {
        // Arrange
        TestSubClass obj = new TestSubClass();
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
        builder.setUpToClass(TestClass.class);

        // Act
        String result = builder.toString();

        // Assert
        assertFalse(result.contains("subField=30"));
    }

    @Test
    void testSetUpToClassWithInvalidClass() {
        // Arrange
        TestClass obj = new TestClass();
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> builder.setUpToClass(TestSubClass.class));
    }

    static class TestClass {
        public int field1 = 10;
        public String field2 = null;
        transient int transientField = 20;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }

    static class TestSubClass extends TestClass {
        public int subField = 30;
    }
}
