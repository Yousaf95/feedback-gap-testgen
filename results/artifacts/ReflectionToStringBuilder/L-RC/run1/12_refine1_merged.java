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
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((Collection<String>) null);
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToNoNullStringArrayWithEmptyCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(Collections.emptyList());
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToNoNullStringArrayWithNonNullElements() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(Arrays.asList("a", "b", "c"));
            assertArrayEquals(new String[]{"a", "b", "c"}, result);
        }

    @Test
        public void testToNoNullStringArrayWithObjectArray() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(new Object[]{"a", null, "b"});
            assertArrayEquals(new String[]{"a", "b"}, result);
        }

    @Test
        public void testToStringWithStyle() {
            String result = ReflectionToStringBuilder.toString(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE);
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringWithOutputTransients() {
            String result = ReflectionToStringBuilder.toString(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, true);
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringWithOutputStatics() {
            String result = ReflectionToStringBuilder.toString(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, false, true);
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringWithAllParameters() {
            String result = ReflectionToStringBuilder.toString(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, false, false, true, null);
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringExcludeWithCollection() {
            String result = ReflectionToStringBuilder.toStringExclude(new TestObject(), Collections.singletonList("b"));
            assertEquals("TestObject@%h[a=1]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringExcludeWithVarargs() {
            String result = ReflectionToStringBuilder.toStringExclude(new TestObject(), "b");
            assertEquals("TestObject@%h[a=1]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringIncludeWithCollection() {
            String result = ReflectionToStringBuilder.toStringInclude(new TestObject(), Collections.singletonList("a"));
            assertEquals("TestObject@%h[a=1]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testToStringIncludeWithVarargs() {
            String result = ReflectionToStringBuilder.toStringInclude(new TestObject(), "a");
            assertEquals("TestObject@%h[a=1]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithStyle() {
            String result = new ReflectionToStringBuilder(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE).toString();
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithBuffer() {
            StringBuffer buffer = new StringBuffer();
            String result = new ReflectionToStringBuilder(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, buffer).toString();
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithAllParameters() {
            StringBuffer buffer = new StringBuffer();
            String result = new ReflectionToStringBuilder(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, buffer, null, false, false).toString();
            assertEquals("TestObject@%h[a=1,b=2]", result.replaceFirst("@[0-9a-fA-F]+", "@%h"));
        }

    @Test
        public void testSetExcludeFieldNamesWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestObject());
            builder.setExcludeFieldNames((String[]) null);
            assertNull(builder.getExcludeFieldNames());
        }

    @Test
        public void testSetIncludeFieldNamesWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestObject());
            builder.setIncludeFieldNames((String[]) null);
            assertNull(builder.getIncludeFieldNames());
        }

    @Test
        public void testGetUpToClass() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestObject(), ToStringStyle.SHORT_PREFIX_STYLE, null, String.class, false, false);
            assertEquals(String.class, builder.getUpToClass());
        }
}
