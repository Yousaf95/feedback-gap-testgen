package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(null);
            assertArrayEquals(new String[0], result);
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
        public void testToStringWithNullObject() {
            String result = ReflectionToStringBuilder.toString(null);
            assertEquals("null", result);
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
        public void testToStringWithExcludeField_2() throws Exception {
            class ExcludeFieldClass {
                private int field1;
                private int field2;
            }
            ExcludeFieldClass obj = new ExcludeFieldClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, Arrays.asList("field1"));
            assertFalse(result.contains("field1"));
        }

    @Test
        public void testToStringWithIncludeField_2() throws Exception {
            class IncludeFieldClass {
                private int field1;
                private int field2;
            }
            IncludeFieldClass obj = new IncludeFieldClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, Arrays.asList("field1"));
            assertTrue(result.contains("field1"));
        }

    @Test
        public void testToStringWithNullValues_2() throws Exception {
            class NullValueClass {
                private Integer field;
            }
            NullValueClass obj = new NullValueClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
            assertTrue(result.contains("field=null"));
        }

    @Test
        public void testToStringWithNullValuesExcluded_2() throws Exception {
            class NullValueClass {
                private Integer field;
            }
            NullValueClass obj = new NullValueClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, Collections.emptyList(), true);
            assertFalse(result.contains("field=null"));
        }

    @Test
        public void testToStringWithNullExcludeFieldNames_2() {
            assertThrows(NullPointerException.class, () -> ReflectionToStringBuilder.toStringExclude(null, (String[]) null));
        }

    @Test
        public void testToStringWithNullIncludeFieldNames_2() {
            assertThrows(NullPointerException.class, () -> ReflectionToStringBuilder.toStringInclude(null, (String[]) null));
        }

    @Test
        public void testToStringWithNullUpToClass_2() throws Exception {
            class UpToClass {
                private int field;
            }
            UpToClass obj = new UpToClass();
            String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, null, null, true);
            assertTrue(result.contains("field"));
        }

    @Test
        public void testAcceptWithTransientField() throws Exception {
            class TransientFieldClass {
                private transient int transientField;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TransientFieldClass());
            Field field = TransientFieldClass.class.getDeclaredField("transientField");
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithStaticField() throws Exception {
            class StaticFieldClass {
                private static int staticField;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new StaticFieldClass());
            Field field = StaticFieldClass.class.getDeclaredField("staticField");
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithExcludeField() throws Exception {
            class ExcludeFieldClass {
                private int field1;
                private int field2;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new ExcludeFieldClass());
            builder.setExcludeFieldNames("field1");
            Field field = ExcludeFieldClass.class.getDeclaredField("field1");
            assertFalse(builder.accept(field));
        }

    @Test
        public void testAcceptWithIncludeField() throws Exception {
            class IncludeFieldClass {
                private int field1;
                private int field2;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new IncludeFieldClass());
            builder.setIncludeFieldNames("field1");
            Field field = IncludeFieldClass.class.getDeclaredField("field1");
            assertTrue(builder.accept(field));
        }

    @Test
        public void testAcceptWithNullExcludeFieldNames() throws Exception {
            class ExcludeFieldClass {
                private int field;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new ExcludeFieldClass());
            builder.setExcludeFieldNames((String[]) null);
            Field field = ExcludeFieldClass.class.getDeclaredField("field");
            assertTrue(builder.accept(field));
        }

    @Test
        public void testAcceptWithNullIncludeFieldNames() throws Exception {
            class IncludeFieldClass {
                private int field;
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new IncludeFieldClass());
            builder.setIncludeFieldNames((String[]) null);
            Field field = IncludeFieldClass.class.getDeclaredField("field");
            assertTrue(builder.accept(field));
        }

    @Test
        public void testAppendFieldsInWithArray() throws Exception {
            class ArrayClass {
                private int[] array;
            }
            ArrayClass obj = new ArrayClass();
            obj.array = new int[]{1, 2, 3};
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(obj);
            builder.appendFieldsIn(ArrayClass.class);
            assertTrue(builder.toString().contains("array=[1, 2, 3]"));
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

    @Test
        public void testReflectionAppendArrayWithNullArray() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(null);
            builder.reflectionAppendArray(null);
            assertEquals("", builder.toString());
        }
}
