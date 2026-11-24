package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

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
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((java.util.Collection<String>) null);
            assertArrayEquals(org.apache.commons.lang3.ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToStringWithNullObject() {
            String result = ReflectionToStringBuilder.toString(null);
            assertEquals(ToStringStyle.DEFAULT_STYLE.getNullText(), result);
        }

    @Test
        public void testToStringWithStyleAndNullObject() {
            String result = ReflectionToStringBuilder.toString(null, ToStringStyle.JSON_STYLE);
            assertEquals(ToStringStyle.JSON_STYLE.getNullText(), result);
        }

    @Test
        public void testToStringWithStyleOutputTransientsAndNullObject() {
            String result = ReflectionToStringBuilder.toString(null, ToStringStyle.JSON_STYLE, true);
            assertEquals(ToStringStyle.JSON_STYLE.getNullText(), result);
        }

    @Test
        public void testToStringWithStyleOutputTransientsOutputStaticsAndNullObject() {
            String result = ReflectionToStringBuilder.toString(null, ToStringStyle.JSON_STYLE, true, true);
            assertEquals(ToStringStyle.JSON_STYLE.getNullText(), result);
        }

    
    
    
    
    
    
    @Test
        public void testAcceptWithInnerClassField() {
            class Inner {}
            Field field = null;
            try {
                field = Inner.class.getDeclaredField("this$0");
            } catch (NoSuchFieldException e) {
                // Expected if the compiler doesn't generate this field
            }
            if (field != null) {
                ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
                assertFalse(builder.accept(field));
            }
        }

    
    
    
    @Test
        public void testIsAppendStatics() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertFalse(builder.isAppendStatics());
        }

    @Test
        public void testIsExcludeNullValues() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertFalse(builder.isExcludeNullValues());
        }

    
    @Test
        public void testSetAppendStatics() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendStatics(true);
            assertTrue(builder.isAppendStatics());
        }

    @Test
        public void testSetAppendTransients() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendTransients(true);
            assertTrue(builder.isAppendTransients());
        }

    @Test
        public void testSetUpToClassWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setUpToClass(null);
            assertNull(builder.getUpToClass());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithNonNullCollection() {
            java.util.Collection<String> collection = Arrays.asList("field1", "field2");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertArrayEquals(new String[]{"field1", "field2"}, result);
        }

    
    @Test
        public void testToStringExcludeWithCollection() {
            Object obj = new Object();
            java.util.Collection<String> excludeFieldNames = Arrays.asList("field1", "field2");
            String result = ReflectionToStringBuilder.toStringExclude(obj, excludeFieldNames);
            assertNotNull(result);
        }

    @Test
        public void testToStringIncludeWithCollection() {
            Object obj = new Object();
            java.util.Collection<String> includeFieldNames = Arrays.asList("field1", "field2");
            String result = ReflectionToStringBuilder.toStringInclude(obj, includeFieldNames);
            assertNotNull(result);
        }

    @Test
        public void testConstructorWithStyleAndBuffer() {
            Object obj = new Object();
            ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
            StringBuffer buffer = new StringBuffer();
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj, style, buffer);
            assertNotNull(builder);
        }

    
    @Test
        public void testAcceptWithTransientField() {
            class TestClass { transient int field; }
            Field field;
            try {
                field = TestClass.class.getDeclaredField("field");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());
            builder.setAppendTransients(false);
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithStaticField() {
            class TestClass { static int field; }
            Field field;
            try {
                field = TestClass.class.getDeclaredField("field");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());
            builder.setAppendStatics(false);
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithExcludedFieldName() {
            class TestClass { int field; }
            Field field;
            try {
                field = TestClass.class.getDeclaredField("field");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());
            builder.setExcludeFieldNames("field");
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithIncludedFieldName() {
            class TestClass { int field; }
            Field field;
            try {
                field = TestClass.class.getDeclaredField("field");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());
            builder.setIncludeFieldNames("field");
            assertTrue(builder.accept(field));
        }

    @Test
        public void testAppendFieldsInWithArray() {
            int[] array = {1, 2, 3};
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(array);
            builder.appendFieldsIn(int[].class);
            assertNotNull(builder.toString());
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
        public void testReflectionAppendArray() {
            int[] array = {1, 2, 3};
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(array);
            builder.reflectionAppendArray(array);
            assertNotNull(builder.toString());
        }

    
    
    @Test
        public void testSetUpToClassWithValidClass() {
            class TestClass {}
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass());
            assertDoesNotThrow(() -> builder.setUpToClass(TestClass.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToStringWithAllParameters() {
            String result = ReflectionToStringBuilder.toString(
                    new Object(), ToStringStyle.DEFAULT_STYLE, true, true, false, Object.class);
            assertNotNull(result);
        }

    @Test
        public void testConstructorWithObjectAndStyle() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object(), ToStringStyle.DEFAULT_STYLE);
            assertNotNull(builder);
        }

    @Test
        public void testConstructorWithAllParameters() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
                    new Object(), ToStringStyle.DEFAULT_STYLE, null, Object.class, true, true, false);
            assertNotNull(builder);
        }

    @Test
        public void testAcceptWithTransientFieldAndIncludeTransientsTrue() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendTransients(true);
            Field transientField = new TestClass().getClass().getDeclaredFields()[0];
            assertTrue(builder.accept(transientField));
        }

    @Test
        public void testAcceptWithStaticFieldAndIncludeStaticsTrue() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendStatics(true);
            Field staticField = new TestClass().getClass().getDeclaredFields()[1];
            assertTrue(builder.accept(staticField));
        }

    
    
    @Test
        public void testSetUpToClassWithInvalidClassThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> 
                new ReflectionToStringBuilder(new Object()).setUpToClass(String.class)
            );
        }

    @Test
        public void testValidateWithIntersectingIncludeAndExcludeFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object())
                    .setIncludeFieldNames("field1")
                    .setExcludeFieldNames("field1");
            assertThrows(IllegalStateException.class, builder::toString);
        }
}
