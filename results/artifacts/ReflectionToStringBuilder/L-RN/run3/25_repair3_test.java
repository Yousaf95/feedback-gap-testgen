package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    
    
    
    
    
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testSetIncludeFieldNames() {
            TestClass obj = new TestClass();
            String result = ReflectionToStringBuilder.toStringInclude(obj, "field1");
            assertEquals("TestClass@<hashcode>(field1=1)", result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testSetExcludeFieldNames() {
            TestClass obj = new TestClass();
            String result = ReflectionToStringBuilder.toStringExclude(obj, "field2");
            assertEquals("TestClass@<hashcode>(field1=1)", result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testReflectUpToClassWithNull() {
            TestClass obj = new TestClass();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, true, (Class<?>) null);
            assertEquals("TestClass@<hashcode>(field1=1, field2=2)", result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testReflectUpToClassWithInvalidClassThrowsException() {
            TestClass obj = new TestClass();
            assertThrows(IllegalArgumentException.class, () -> ReflectionToStringBuilder.toString(obj, null, false, false, true, String.class));
        }
}
