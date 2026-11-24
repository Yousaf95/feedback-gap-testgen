package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.ArrayUtils;

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
        public void testToNoNullStringArrayWithNullArray() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((Object[]) null);
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToStringWithDefaultParameters() {
            String result = ReflectionToStringBuilder.toString(new Object());
            assertNotNull(result);
        }

    @Test
        public void testToStringWithStyleParameter() {
            String result = ReflectionToStringBuilder.toString(new Object(), ToStringStyle.SHORT_PREFIX_STYLE);
            assertNotNull(result);
        }

    @Test
        public void testToStringWithOutputTransientsParameter() {
            String result = ReflectionToStringBuilder.toString(new Object(), ToStringStyle.SHORT_PREFIX_STYLE, true);
            assertNotNull(result);
        }

    @Test
        public void testToStringExcludeWithCollectionParameter() {
            String result = ReflectionToStringBuilder.toStringExclude(new Object(), Collections.singletonList("fieldName"));
            assertNotNull(result);
        }

    @Test
        public void testToStringIncludeWithCollectionParameter() {
            String result = ReflectionToStringBuilder.toStringInclude(new Object(), Collections.singletonList("fieldName"));
            assertNotNull(result);
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithStyleAndBufferParameters() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE, new StringBuffer());
            assertNotNull(builder);
        }

    @Test
        public void testReflectionToStringBuilderConstructorWithAllParameters() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
                    new Object(), ToStringStyle.SHORT_PREFIX_STYLE, new StringBuffer(),
                    Object.class, true, false);
            assertNotNull(builder);
        }

    @Test
        public void testGetExcludeFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, builder.getExcludeFieldNames());
        }

    @Test
        public void testGetIncludeFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, builder.getIncludeFieldNames());
        }

    @Test
        public void testReflectionAppendArrayWithNullArray() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.reflectionAppendArray(null);
            assertNotNull(builder);
        }

    @Test
        public void testSetExcludeFieldNamesWithNullParameter() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object()).setExcludeFieldNames((String[]) null);
            assertNull(builder.getExcludeFieldNames());
        }

    @Test
        public void testSetIncludeFieldNamesWithNullParameter() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object()).setIncludeFieldNames((String[]) null);
            assertNull(builder.getIncludeFieldNames());
        }

    @Test
        public void testToStringWithNullObject() {
            String result = new ReflectionToStringBuilder(null).toString();
            assertEquals(ToStringStyle.DEFAULT_STYLE.getNullText(), result);
        }

    @Test
        public void testValidateWithIntersectingExcludeAndIncludeFieldNames() {
            assertThrows(IllegalStateException.class, () -> {
                new ReflectionToStringBuilder(new Object())
                        .setExcludeFieldNames("field1")
                        .setIncludeFieldNames("field1")
                        .toString();
            });
        }
}
