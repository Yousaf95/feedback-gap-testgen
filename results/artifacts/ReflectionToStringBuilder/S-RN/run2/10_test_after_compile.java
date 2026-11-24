package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.builder.ToStringStyle.*;
import java.util.Arrays;
import java.util.Collections;

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
        public void testToStringWithCustomStyle() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE);
            assertTrue(result.contains("Person@"));
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomBuffer() {
            Person person = new Person("John", 30);
            StringBuilder buffer = new StringBuilder();
            ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE, buffer);
            assertTrue(buffer.toString().contains("Person@"));
            assertTrue(buffer.toString().contains("name=John"));
            assertTrue(buffer.toString().contains("age=30"));
        }

    @Test
        public void testToStringWithCustomExcludeFieldNames() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setExcludeFieldNames("name").toString();
            assertTrue(result.contains("Person@"));
            assertFalse(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomIncludeFieldNames() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setIncludeFieldNames("name").toString();
            assertTrue(result.contains("Person@"));
            assertTrue(result.contains("name=John"));
            assertFalse(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomExcludeNullValues() {
            Person person = new Person(null, 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setExcludeNullValues(true).toString();
            assertTrue(result.contains("Person@"));
            assertFalse(result.contains("name="));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomUpToClass() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setUpToClass(Object.class).toString();
            assertTrue(result.contains("Person@"));
            assertFalse(result.contains("name=John"));
            assertFalse(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomAppendTransients() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setAppendTransients(true).toString();
            assertTrue(result.contains("Person@"));
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomAppendStatics() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE).setAppendStatics(true).toString();
            assertTrue(result.contains("Person@"));
            assertTrue(result.contains("name=John"));
            assertTrue(result.contains("age=30"));
        }

    @Test
        public void testToStringWithCustomNullText() {
            Person person = null;
            String result = ReflectionToStringBuilder.toString(person, SHORT_PREFIX_STYLE);
            assertEquals(SHORT_PREFIX_STYLE.getNullText(), result);
        }
}
