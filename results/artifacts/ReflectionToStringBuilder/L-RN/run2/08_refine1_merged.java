package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    
    
    
    
    
    
    
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendTransients() {
            TestClassWithTransientField obj = new TestClassWithTransientField();
            String result = ReflectionToStringBuilder.toString(obj, null, true, false);
            assertTrue(result.contains("transientField"));
        }

    @Test
        public void testExcludeTransients() {
            TestClassWithTransientField obj = new TestClassWithTransientField();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("transientField"));
        }

    @Test
        public void testAppendStatics() {
            TestClassWithStaticField obj = new TestClassWithStaticField();
            String result = ReflectionToStringBuilder.toString(obj, null, false, true);
            assertTrue(result.contains("staticField"));
        }

    @Test
        public void testExcludeStatics() {
            TestClassWithStaticField obj = new TestClassWithStaticField();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("staticField"));
        }

    @Test
        public void testExcludeNullValues() {
            TestClassWithNullableFields obj = new TestClassWithNullableFields(null, "nonNull");
            String result = ReflectionToStringBuilder.toString(obj).setExcludeNullValues(true).toString();
            assertFalse(result.contains("nullField"));
            assertTrue(result.contains("nonNullField=nonNull"));
        }

    @Test
        public void testReflectUpToClass() {
            SubTestClass obj = new SubTestClass();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, SubTestClass.class);
            assertFalse(result.contains("superField"));
            assertTrue(result.contains("subField"));
        }
}
