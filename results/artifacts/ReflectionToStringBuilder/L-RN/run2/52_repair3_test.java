package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.builder.ToStringStyle;

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
            class TestClassWithTransientField {
                transient String transientField = "transientValue";
            }
            TestClassWithTransientField obj = new TestClassWithTransientField();
            String result = ReflectionToStringBuilder.toString(obj, null, true, false);
            assertTrue(result.contains("transientField"));
        }

    @Test
        public void testExcludeTransients() {
            class TestClassWithTransientField {
                transient String transientField = "transientValue";
            }
            TestClassWithTransientField obj = new TestClassWithTransientField();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("transientField"));
        }

    
    @Test
        public void testExcludeStatics() {
            class TestClassWithStaticField {
                static String staticField = "staticValue";
            }
            TestClassWithStaticField obj = new TestClassWithStaticField();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("staticField")); // Static fields are not instance-specific
        }

    @Test
        public void testExcludeNullValues() {
            class TestClassWithNullableFields {
                String nullField;
                String nonNullField;
    
                TestClassWithNullableFields(String nullField, String nonNullField) {
                    this.nullField = nullField;
                    this.nonNullField = nonNullField;
                }
            }
            TestClassWithNullableFields obj = new TestClassWithNullableFields(null, "nonNull");
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, true, null);
            assertFalse(result.contains("nullField"));
            assertTrue(result.contains("nonNullField=nonNull"));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testDefaultToStringStyle() {
            TestObject obj = new TestObject("test", 123);
            String result = ReflectionToStringBuilder.toString(obj);
            assertEquals("ReflectionToStringBuilder_GenTest$TestObject@<hashcode>(name=test,value=123)", 
                         result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testCustomToStringStyle() {
            TestObject obj = new TestObject("test", 123);
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
            assertEquals("TestObject(name=test,value=123)", 
                         result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testExcludeNullValuesTrue() {
            TestObject obj = new TestObject(null, 123);
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, true);
            assertEquals("ReflectionToStringBuilder_GenTest$TestObject@<hashcode>(value=123)", 
                         result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testExcludeNullValuesFalse() {
            TestObject obj = new TestObject(null, 123);
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, false);
            assertEquals("ReflectionToStringBuilder_GenTest$TestObject@<hashcode>(name=<null>,value=123)", 
                         result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testReflectUpToClass() {
            SubTestObject obj = new SubTestObject("test", 123, "sub");
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, null, TestObject.class);
            assertEquals("ReflectionToStringBuilder_GenTest$SubTestObject@<hashcode>(subName=sub)", 
                         result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
        }

    @Test
        public void testReflectUpToClassInvalid() {
            TestObject obj = new TestObject("test", 123);
            assertThrows(IllegalArgumentException.class, () -> ReflectionToStringBuilder.toString(obj, null, false, false, null, SubTestObject.class));
        }
}
