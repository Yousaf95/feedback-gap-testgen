package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    
    @Test
    public void testToStringEmptyClass() {
        class EmptyClass {}
        String result = ReflectionToStringBuilder.toString(new EmptyClass());
        assertTrue(result.contains("EmptyClass@"));
    }

    @Test
    public void testToStringWithFields() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithTransientField() {
        class TestClass {
            private transient int transientField;
            private String nonTransientField;

            public TestClass(int transientField, String nonTransientField) {
                this.transientField = transientField;
                this.nonTransientField = nonTransientField;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("nonTransientField=test"));
        assertFalse(result.contains("transientField=42"));
    }

    @Test
    public void testToStringWithStaticField() {
        class TestClass {
            private static int staticField;
            private String nonStaticField;

            public TestClass(int staticField, String nonStaticField) {
                this.staticField = staticField;
                this.nonStaticField = nonStaticField;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("nonStaticField=test"));
        assertFalse(result.contains("staticField=42"));
    }

    @Test
    public void testToStringWithExcludeField() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toStringExclude(obj, Collections.singletonList("field1"));
        assertTrue(result.contains("TestClass@"));
        assertFalse(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithIncludeField() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toStringInclude(obj, Collections.singletonList("field1"));
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertFalse(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullValuesExcluded() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = null;
            }
        }
        TestClass obj = new TestClass(42, null);
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertFalse(result.contains("field2=null"));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringWithOutputTransients(boolean outputTransients) {
        class TestClass {
            private transient int transientField;
            private String nonTransientField;

            public TestClass(int transientField, String nonTransientField) {
                this.transientField = transientField;
                this.nonTransientField = nonTransientField;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj, null, outputTransients);
        if (outputTransients) {
            assertTrue(result.contains("transientField=42"));
        } else {
            assertFalse(result.contains("transientField=42"));
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringWithOutputStatics(boolean outputStatics) {
        class TestClass {
            private static int staticField;
            private String nonStaticField;

            public TestClass(int staticField, String nonStaticField) {
                this.staticField = staticField;
                this.nonStaticField = nonStaticField;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj, null, false, outputStatics);
        if (outputStatics) {
            assertTrue(result.contains("staticField=42"));
        } else {
            assertFalse(result.contains("staticField=42"));
        }
    }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(null);
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToNoNullStringArrayWithEmptyCollection() {
            Collection<String> emptyCollection = Collections.emptyList();
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(emptyCollection);
            assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection() {
            Collection<String> nonEmptyCollection = Arrays.asList("a", "b", null, "c");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(nonEmptyCollection);
            assertArrayEquals(new String[]{"a", "b", "c"}, result);
        }

    @Test
        public void testToStringWithDefaultStyle() {
            Object object = new Object();
            String result = ReflectionToStringBuilder.toString(object);
            assertEquals(ToStringStyle.DEFAULT_STYLE.toString(object), result);
        }

    @Test
        public void testToStringWithCustomStyle() {
            Object object = new Object();
            ToStringStyle customStyle = ToStringStyle.SHORT_PREFIX_STYLE;
            String result = ReflectionToStringBuilder.toString(object, customStyle);
            assertEquals(customStyle.toString(object), result);
        }

    @Test
        public void testToStringWithExcludeNullValues() {
            class TestClass {
                private Integer field = null;
            }
            TestClass object = new TestClass();
            String result = ReflectionToStringBuilder.toString(object, ToStringStyle.DEFAULT_STYLE, false, false, true);
            assertFalse(result.contains("field"));
        }

    @Test
        public void testValidateWithNoConflictingFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeFieldNames(new String[]{"a"});
            builder.setIncludeFieldNames(new String[]{"b"});
            assertDoesNotThrow(() -> builder.validate());
        }

    @Test
        public void testValidateWithConflictingFieldNames() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeFieldNames(new String[]{"a"});
            builder.setIncludeFieldNames(new String[]{"a"});
            assertThrows(IllegalStateException.class, () -> builder.validate());
        }
}
