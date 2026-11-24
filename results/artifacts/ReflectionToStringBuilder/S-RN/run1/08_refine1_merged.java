package org.apache.commons.lang3.builder;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    @Test
    public void testToStringWithDefaultStyle() {
        Person person = new Person("John", 30);
        String result = ReflectionToStringBuilder.toString(person);
        assertTrue(result.contains("name=John"));
        assertTrue(result.contains("age=30"));
    }

    
    
    
    @Test
    public void testToStringExcludeFields() {
        Person person = new Person("John", 30);
        String result = ReflectionToStringBuilder.toStringExclude(person, Collections.singletonList("age"));
        assertTrue(result.contains("name=John"));
        assertFalse(result.contains("age=30"));
    }

    @Test
    public void testToStringIncludeFields() {
        Person person = new Person("John", 30);
        String result = ReflectionToStringBuilder.toStringInclude(person, Collections.singletonList("age"));
        assertFalse(result.contains("name=John"));
        assertTrue(result.contains("age=30"));
    }

    
    
    
    @Test
    public void testToStringWithNullObject() {
        Object object = null;
        String result = ReflectionToStringBuilder.toString(object);
        assertEquals(ToStringStyle.DEFAULT_STYLE.getNullText(), result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testToStringWithCustomStyle() {
            ToStringStyle customStyle = new ToStringStyle() {
                @Override
                protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
                    buffer.append(fieldName).append(": ").append(value);
                }
            };
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setStyle(customStyle);
            assertEquals("Object: null", builder.toString());
        }

    @Test
        public void testToStringWithExcludeNullValues() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeNullValues(true);
            assertEquals("Object: null", builder.toString());
        }

    @Test
        public void testToStringWithIncludeFieldNames() {
            class TestClass {
                private String field1;
                private int field2;
    
                public TestClass(String field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass("value1", 42));
            builder.setIncludeFieldNames("field1");
            assertEquals("TestClass: field1=value1", builder.toString());
        }

    @Test
        public void testToStringWithExcludeFieldNames() {
            class TestClass {
                private String field1;
                private int field2;
    
                public TestClass(String field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass("value1", 42));
            builder.setExcludeFieldNames("field1");
            assertEquals("TestClass: field2=42", builder.toString());
        }

    @Test
        public void testToStringWithTransientFields() {
            class TestClass {
                private transient String transientField;
                private int nonTransientField;
    
                public TestClass(String transientField, int nonTransientField) {
                    this.transientField = transientField;
                    this.nonTransientField = nonTransientField;
                }
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass("value1", 42));
            builder.setAppendTransients(true);
            assertEquals("TestClass: transientField=value1, nonTransientField=42", builder.toString());
        }

    @Test
        public void testToStringWithStaticFields() {
            class TestClass {
                private static String staticField;
                private int instanceField;
    
                public TestClass(int instanceField) {
                    this.instanceField = instanceField;
                }
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new TestClass(42));
            builder.setAppendStatics(true);
            assertEquals("TestClass: staticField=null, instanceField=42", builder.toString());
        }

    @Test
        public void testToStringWithReflectUpToClass() {
            class ParentClass {
                private String parentField;
            }
            class ChildClass extends ParentClass {
                private int childField;
    
                public ChildClass(String parentField, int childField) {
                    super(parentField);
                    this.childField = childField;
                }
            }
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new ChildClass("value1", 42));
            builder.setUpToClass(ParentClass.class);
            assertEquals("ChildClass: parentField=value1", builder.toString());
        }

    @Test
        public void testToStringWithNullObject_2() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(null);
            assertEquals("null", builder.toString());
        }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (if needed)
}

class TransientPerson {
    private transient String name;
    private int age;

    public TransientPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (if needed)
}

class StaticPerson {
    private static final int staticValue = 42;
    private String name;
    private int age;

    public StaticPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (if needed)
}

class NullPerson {
    private String name;
    private Integer age;

    public NullPerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (if needed)
}

class SubPerson extends Person {
    private int age;

    public SubPerson(String name, int age) {
        super(name, age);
    }

    // Getters and setters (if needed)
}
