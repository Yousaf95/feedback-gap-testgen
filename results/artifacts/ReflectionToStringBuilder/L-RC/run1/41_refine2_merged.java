package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.ArrayUtils;

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

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((Collection<String>) null);
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToStringWithStyle() {
            String result = ReflectionToStringBuilder.toString(new Object(), ToStringStyle.SHORT_PREFIX_STYLE);
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringWithStyleAndOutputTransients() {
            String result = ReflectionToStringBuilder.toString(new Object(), ToStringStyle.SHORT_PREFIX_STYLE, true);
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringWithStyleAndOutputTransientsAndStatics() {
            String result = ReflectionToStringBuilder.toString(new Object(), ToStringStyle.SHORT_PREFIX_STYLE, true, true);
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringWithAllParameters() {
            String result = ReflectionToStringBuilder.toString(
                    new Object(), ToStringStyle.SHORT_PREFIX_STYLE, true, true, false, Object.class);
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringExcludeWithCollection() {
            String result = ReflectionToStringBuilder.toStringExclude(new Object(), Collections.singletonList("fieldName"));
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringExcludeWithVarargs() {
            String result = ReflectionToStringBuilder.toStringExclude(new Object(), "fieldName1", "fieldName2");
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringIncludeWithCollection() {
            String result = ReflectionToStringBuilder.toStringInclude(new Object(), Collections.singletonList("fieldName"));
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testToStringIncludeWithVarargs() {
            String result = ReflectionToStringBuilder.toStringInclude(new Object(), "fieldName1", "fieldName2");
            assertEquals("Object@<hashcode>", result.replaceFirst("@\\p{XDigit}+", "@<hashcode>"));
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithStyle() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE);
            assertNotNull(builder);
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithStyleAndBuffer() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE, new StringBuffer());
            assertNotNull(builder);
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithAllParameters() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
                    new Object(), ToStringStyle.SHORT_PREFIX_STYLE, new StringBuffer(), Object.class, true, true);
            assertNotNull(builder);
        }

    @Test
        public void testGetExcludeFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeFieldNames("field1", "field2");
            assertArrayEquals(new String[]{"field1", "field2"}, builder.getExcludeFieldNames());
        }

    @Test
        public void testGetIncludeFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setIncludeFieldNames("field1", "field2");
            assertArrayEquals(new String[]{"field1", "field2"}, builder.getIncludeFieldNames());
        }

    @Test
        public void testGetUpToClass() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setUpToClass(Object.class);
            assertEquals(Object.class, builder.getUpToClass());
        }

    @Test
        public void testIsAppendStatics() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendStatics(true);
            assertTrue(builder.isAppendStatics());
        }

    @Test
        public void testIsAppendTransients() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendTransients(true);
            assertTrue(builder.isAppendTransients());
        }

    @Test
        public void testIsExcludeNullValues() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeNullValues(true);
            assertTrue(builder.isExcludeNullValues());
        }
}
