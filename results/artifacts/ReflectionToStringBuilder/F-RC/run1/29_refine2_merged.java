package org.apache.commons.lang3.builder;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import static org.apache.commons.lang3.builder.ToStringStyle.*;

public class ReflectionToStringBuilder_GenTest {

    
    @Test
    public void testToStringWithEmptyClass() {
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
        assertTrue(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithTransientField() {
        class TestClass {
            private transient int transientField;
            private int nonTransientField;

            public TestClass(int transientField, int nonTransientField) {
                this.transientField = transientField;
                this.nonTransientField = nonTransientField;
            }
        }
        TestClass obj = new TestClass(42, 99);
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("nonTransientField=99"));
        assertFalse(result.contains("transientField"));
    }

    @Test
    public void testToStringWithStaticField() {
        class TestClass {
            private static int staticField;
            private int nonStaticField;

            public TestClass(int nonStaticField) {
                this.nonStaticField = nonStaticField;
            }
        }
        TestClass.staticField = 42;
        TestClass obj = new TestClass(99);
        String result = ReflectionToStringBuilder.toString(obj);
        assertTrue(result.contains("nonStaticField=99"));
        assertFalse(result.contains("staticField"));
    }

    @Test
    public void testToStringWithExcludeFieldNames() {
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
        assertTrue(result.contains("field2=test"));
        assertFalse(result.contains("field1=42"));
    }

    @Test
    public void testToStringWithIncludeFieldNames() {
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
        assertTrue(result.contains("field1=42"));
        assertFalse(result.contains("field2=test"));
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
        assertTrue(result.contains("field1=42"));
        assertTrue(result.contains("field2=test"));
    }

    @Test
    public void testToStringWithNullObjectAndStyle() {
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
        String result = ReflectionToStringBuilder.toString(null, style);
        assertEquals(style.getNullText(), result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((Collection<String>) null);
            assertArrayEquals(new String[0], result);
        }

    @Test
        public void testToNoNullStringArrayWithEmptyCollection() {
            Collection<String> emptyList = Collections.emptyList();
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(emptyList);
            assertArrayEquals(new String[0], result);
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection() {
            Collection<String> list = Arrays.asList("a", null, "b");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(list);
            assertArrayEquals(new String[]{"a", "b"}, result);
        }

    
    
    
    @Test
        public void testSetExcludeNullValuesWithTrue() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeNullValues(true);
            assertTrue(builder.isExcludeNullValues());
        }

    @Test
        public void testSetExcludeNullValuesWithFalse() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeNullValues(false);
            assertFalse(builder.isExcludeNullValues());
        }

    @Test
        public void testSetUpToClassWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setUpToClass(null);
            assertNull(builder.getUpToClass());
        }

    @Test
        public void testSetUpToClassWithInvalidClass() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertThrows(IllegalArgumentException.class, () -> builder.setUpToClass(String.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToStringWithEmptyClass_2() {
            Object obj = new Object();
            String result = ReflectionToStringBuilder.toString(obj, EMPTY_STYLE);
            assertTrue(result.contains("class java.lang.Object"));
        }

    @Test
        public void testToStringWithFields_2() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SIMPLE_STYLE);
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithTransientField_2() {
            TransientPerson transientPerson = new TransientPerson("Jane", 25);
            String result = ReflectionToStringBuilder.toString(transientPerson, SIMPLE_STYLE);
            assertFalse(result.contains("password=secret"));
        }

    @Test
        public void testToStringWithStaticField_2() {
            StaticPerson staticPerson = new StaticPerson("Alice", 35);
            String result = ReflectionToStringBuilder.toString(staticPerson, SIMPLE_STYLE);
            assertTrue(result.contains("name=Alice"));
            assertTrue(result.contains("age=35"));
        }

    @Test
        public void testToStringWithExcludeFieldNames_2() {
            ExcludePerson excludePerson = new ExcludePerson("Bob", 40);
            String result = ReflectionToStringBuilder.toString(excludePerson, SIMPLE_STYLE).replace("\n", "");
            assertFalse(result.contains("name=Bob"));
        }

    @Test
        public void testToStringWithIncludeFieldNames_2() {
            IncludePerson includePerson = new IncludePerson("Charlie", 45);
            String result = ReflectionToStringBuilder.toString(includePerson, SIMPLE_STYLE).replace("\n", "");
            assertTrue(result.contains("age=45"));
        }

    @Test
        public void testToStringWithNullExcludeFieldNames_2() {
            Person person = new Person("David", 50);
            String result = ReflectionToStringBuilder.toString(person, SIMPLE_STYLE, false, null);
            assertTrue(result.contains("name=David"));
            assertTrue(result.contains("age=50"));
        }

    @Test
        public void testToStringWithNullIncludeFieldNames_2() {
            Person person = new Person("Eve", 55);
            String result = ReflectionToStringBuilder.toString(person, SIMPLE_STYLE, false, null);
            assertTrue(result.contains("name=Eve"));
            assertTrue(result.contains("age=55"));
        }

    @Test
        public void testToStringWithNullObjectAndStyle_2() {
            assertThrows(NullPointerException.class, () -> ReflectionToStringBuilder.toString(null, null));
        }

    @Test
        public void testToNoNullStringArrayWithNullCollection_2() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray((Collection<String>) null);
            assertTrue(Arrays.equals(result, new String[0]));
        }

    @Test
        public void testToNoNullStringArrayWithEmptyCollection_2() {
            Collection<String> collection = Arrays.asList();
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertTrue(Arrays.equals(result, new String[0]));
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection_2() {
            Collection<String> collection = Arrays.asList("foo", "bar");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertTrue(Arrays.equals(result, new String[]{"bar", "foo"}));
        }

    @Test
        public void testSetExcludeNullValuesWithTrue_2() {
            Person person = new Person(null, 60);
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(person).setExcludeNullValues(true);
            String result = builder.toString();
            assertFalse(result.contains("name="));
        }

    @Test
        public void testSetExcludeNullValuesWithFalse_2() {
            Person person = new Person(null, 65);
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(person).setExcludeNullValues(false);
            String result = builder.toString();
            assertTrue(result.contains("name=null"));
        }

    @Test
        public void testSetUpToClassWithNull_2() {
            Person person = new Person("Frank", 70);
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(person).setUpToClass(null);
            String result = builder.toString();
            assertTrue(result.contains("class java.lang.Object"));
        }

    @Test
        public void testSetUpToClassWithInvalidClass_2() {
            Person person = new Person("Grace", 75);
            assertThrows(IllegalArgumentException.class, () -> 
                new ReflectionToStringBuilder(person).setUpToClass(String.class)
            );
        }
}
