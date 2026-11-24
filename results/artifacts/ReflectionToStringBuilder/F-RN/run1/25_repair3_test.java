package org.apache.commons.lang3.builder;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        public void testToStringWithCustomStyle() {
            Person person = new Person("John", 30);
            ToStringStyle style = new ToStringStyle() {
                @Override
                protected String getShortClassName(Object object) {
                    return "Person";
                }
    
                @Override
                protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
                    buffer.append(fieldName).append("=").append(value);
                }
            };
            String result = ReflectionToStringBuilder.toString(person, style);
            assertEquals("Person[name=John,age=30]", result);
        }

    @Test
        public void testToStringWithNullObject() {
            String result = ReflectionToStringBuilder.toString(null);
            assertEquals("[null]", result);
        }

    @Test
        public void testToStringWithExcludeFieldNamesAndNullValues() {
            Person person = new Person("John", null, 30);
            String result = ReflectionToStringBuilder.toString(person).setExcludeFieldNames("age").toString();
            assertEquals("Person[name=John]", result);
        }

    @Test
        public void testToStringWithIncludeFieldNames_2() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person).setIncludeFieldNames("name").toString();
            assertEquals("Person[name=John]", result);
        }

    @Test
        public void testToStringWithNullExcludeFieldNames_2() {
            Person person = new Person("John", 30);
            assertThrows(NullPointerException.class, () -> ReflectionToStringBuilder.toString(person).setExcludeFieldNames((String[]) null));
        }

    @Test
        public void testToStringWithNullIncludeFieldNames_2() {
            Person person = new Person("John", 30);
            assertThrows(NullPointerException.class, () -> ReflectionToStringBuilder.toString(person).setIncludeFieldNames((String[]) null));
        }

    @Test
        public void testToStringWithExcludeAndIncludeFieldNames() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toString(person)
                    .setExcludeFieldNames("age")
                    .setIncludeFieldNames("name")
                    .toString();
            assertEquals("Person[name=John]", result);
        }

    @Test
        public void testToStringWithReflectUpToClass() {
            Parent parent = new Parent("Parent");
            Child child = new Child(parent, "Child");
            String result = ReflectionToStringBuilder.toString(child).setUpToClass(Parent.class).toString();
            assertEquals("Child[parent=Parent]", result);
        }

    @Test
        public void testToStringWithReflectUpToClassException() {
            Parent parent = new Parent("Parent");
            Child child = new Child(parent, "Child");
            assertThrows(IllegalArgumentException.class, () -> ReflectionToStringBuilder.toString(child).setUpToClass(String.class));
        }
}
