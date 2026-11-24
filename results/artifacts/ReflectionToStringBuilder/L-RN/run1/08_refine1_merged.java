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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToStringWithExcludeFieldNames() {
            TestClass obj = new TestClass("value1", "value2");
            String result = ReflectionToStringBuilder.toStringExclude(obj, "field1");
            assertTrue(result.contains("field2=value2"));
            assertFalse(result.contains("field1=value1"));
        }

    @Test
        public void testToStringWithIncludeFieldNames() {
            TestClass obj = new TestClass("value1", "value2");
            String result = ReflectionToStringBuilder.toStringInclude(obj, "field1");
            assertTrue(result.contains("field1=value1"));
            assertFalse(result.contains("field2=value2"));
        }

    @Test
        public void testToStringWithExcludeNullValues() {
            TestClass obj = new TestClass(null, "value2");
            String result = ReflectionToStringBuilder.toString(obj, null, false, false, true);
            assertTrue(result.contains("field2=value2"));
            assertFalse(result.contains("field1=null"));
        }

    @Test
        public void testToStringWithAppendTransients() {
            TransientTestClass obj = new TransientTestClass("value");
            String result = ReflectionToStringBuilder.toString(obj, null, true, false);
            assertTrue(result.contains("transientField=value"));
        }

    @Test
        public void testToStringWithoutAppendTransients() {
            TransientTestClass obj = new TransientTestClass("value");
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("transientField=value"));
        }

    @Test
        public void testToStringWithAppendStatics() {
            StaticTestClass obj = new StaticTestClass();
            String result = ReflectionToStringBuilder.toString(obj, null, false, true);
            assertTrue(result.contains("staticField=staticValue"));
        }

    @Test
        public void testToStringWithoutAppendStatics() {
            StaticTestClass obj = new StaticTestClass();
            String result = ReflectionToStringBuilder.toString(obj, null, false, false);
            assertFalse(result.contains("staticField=staticValue"));
        }
}
