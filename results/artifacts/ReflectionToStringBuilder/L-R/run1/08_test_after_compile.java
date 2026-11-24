package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    @Test
    void testToStringWithNullObject() {
        // Arrange
        Object object = null;

        // Act & Assert
        assertEquals(ReflectionToStringBuilder.toString(object), ToStringStyle.DEFAULT_STYLE.getNullText());
    }

    @Test
    void testToStringWithDefaultStyle() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toString(obj);

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testToStringWithCustomStyle() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;

        // Act
        String result = ReflectionToStringBuilder.toString(obj, style);

        // Assert
        assertEquals("TestClass(name=test,age=123)", result);
    }

    @Test
    void testToStringWithOutputTransients() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toString(obj, null, true);

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123,transientField=null)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testToStringWithOutputStatics() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toString(obj, null, false, true);

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testToStringExcludeFields() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toStringExclude(obj, Collections.singletonList("name"));

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testToStringIncludeFields() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toStringInclude(obj, Collections.singletonList("name"));

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetExcludeFieldNames() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setExcludeFieldNames("name");
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetIncludeFieldNames() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setIncludeFieldNames("name");
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetExcludeNullValues() {
        // Arrange
        TestClass obj = new TestClass(null, 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setExcludeNullValues(true);
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetAppendTransients() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setAppendTransients(true);
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123,transientField=null)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetAppendStatics() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setAppendStatics(true);
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetUpToClass() {
        // Arrange
        TestClass obj = new TestClass("test", 123);
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);

        // Act
        builder.setUpToClass(Object.class);
        String result = builder.toString();

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }

    @Test
    void testSetUpToClassWithInvalidClass() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            new ReflectionToStringBuilder(obj).setUpToClass(String.class)
        );
    }

    static class TestClass {
        private String name;
        private int age;
        private transient Object transientField;

        public TestClass(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
