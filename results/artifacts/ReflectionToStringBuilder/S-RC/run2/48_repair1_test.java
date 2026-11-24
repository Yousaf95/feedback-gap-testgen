package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

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
            private transient int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithStaticField() {
        class TestClass {
            private static int field1;
            private String field2;

            public TestClass(String field2) {
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass("test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field2=test"));
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
        String result = ReflectionToStringBuilder.toStringExclude(obj, "field1");
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
        String result = ReflectionToStringBuilder.toStringInclude(obj, "field1");
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertFalse(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullValues() {
        class TestClass {
            private Integer field1;
            private String field2;

            public TestClass(Integer field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(null, "test");
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("TestClass@"));
        assertFalse(result.contains("field1=null"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullValuesExcluded() {
        class TestClass {
            private Integer field1;
            private String field2;

            public TestClass(Integer field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(null, "test");
        String result = ReflectionToStringBuilder.toStringExclude(obj, "field1");
        assertTrue(result.contains("TestClass@"));
        assertFalse(result.contains("field1=null"));
        assertTrue(result.contains("field2=test"));
    }

    
    @Test
    public void testToStringWithNullExcludeFieldNames() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toStringExclude(obj, (String[]) null);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullIncludeFieldNames() {
        class TestClass {
            private int field1;
            private String field2;

            public TestClass(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }
        TestClass obj = new TestClass(42, "test");
        String result = ReflectionToStringBuilder.toStringInclude(obj, (String[]) null);
        assertTrue(result.contains("TestClass@"));
        assertTrue(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullUpToClass() {
        class TestClass {}
        String result = ReflectionToStringBuilder.toString(new TestClass(), null);
        assertTrue(result.contains("TestClass@"));
    }

    
    @Test
        public void testToNoNullStringArrayWithEmptyCollection() {
            List<String> collection = Collections.emptyList();
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertArrayEquals(new String[0], result);
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection() {
            List<String> collection = Arrays.asList("a", null, "b");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertArrayEquals(new String[]{"a", "b"}, result);
        }

    
    @Test
        public void testToStringWithEmptyClass() {
            class EmptyClass {}
            String result = ReflectionToStringBuilder.toString(new EmptyClass());
            assertTrue(result.contains("EmptyClass@"));
        }

    @Test
        public void testToStringWithTransientField_2() throws Exception {
            class TransientFieldClass {
                private transient int transientField;
            }
            TransientFieldClass obj = new TransientFieldClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
            assertFalse(result.contains("transientField"));
        }

    @Test
        public void testToStringWithStaticField_2() throws Exception {
            class StaticFieldClass {
                private static int staticField;
            }
            StaticFieldClass obj = new StaticFieldClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
            assertFalse(result.contains("staticField"));
        }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
        public void testAppendFieldsInWithNonArray() throws Exception {
            class NonArrayClass {
                private int field;
            }
            NonArrayClass obj = new NonArrayClass();
            obj.field = 42;
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
            builder.appendFieldsIn(NonArrayClass.class);
            assertTrue(builder.toString().contains("field=42"));
        }

    @Test
        public void testGetExcludeFieldNames() throws Exception {
            class ExcludeFieldClass {
                private int field1;
                private int field2;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new ExcludeFieldClass());
            builder.setExcludeFieldNames("field1");
            String[] excludeFieldNames = builder.getExcludeFieldNames();
            assertArrayEquals(new String[]{"field1"}, excludeFieldNames);
        }

    @Test
        public void testGetIncludeFieldNames() throws Exception {
            class IncludeFieldClass {
                private int field1;
                private int field2;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new IncludeFieldClass());
            builder.setIncludeFieldNames("field1");
            String[] includeFieldNames = builder.getIncludeFieldNames();
            assertArrayEquals(new String[]{"field1"}, includeFieldNames);
        }

    @Test
        public void testIsExcludeNullValues() throws Exception {
            class NullValueClass {
                private Integer field;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new NullValueClass());
            builder.setExcludeNullValues(true);
            assertTrue(builder.isExcludeNullValues());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithEmptyCollection_2() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(Arrays.asList());
            assertTrue(Arrays.equals(new String[0], result));
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection_2() {
            Collection<String> collection = Arrays.asList("a", "b", null, "c");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertTrue(Arrays.equals(new String[]{"a", "b", "c"}, result));
        }

    @Test
        public void testIsExcludeNullValues_2() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object()).setExcludeNullValues(true);
            assertTrue(builder.isExcludeNullValues());
        }

    @Test
        public void testSetUpToClassWithValidClass() throws Exception {
            class TestClass {}
            Object object = new TestClass();
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(object).setUpToClass(TestClass.class);
            assertEquals(TestClass.class, builder.getUpToClass());
        }

    @Test
        public void testValidateWithIntersectingExcludeInclude() throws Exception {
            class TestClass {}
            Object object = new TestClass();
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(object).setExcludeFieldNames("exclude").setIncludeFieldNames("exclude");
            assertThrows(IllegalStateException.class, () -> builder.validate());
        }
}
