package org.apache.commons.lang3.builder;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.List;
import static org.mockito.Mockito.*;

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
        public void testToNoNullStringArrayWithNullCollection() {
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(null);
            assertArrayEquals(new String[0], result);
        }

    @Test
        public void testToNoNullStringArrayWithEmptyCollection() {
            Collection<String> collection = Collections.emptyList();
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertArrayEquals(new String[0], result);
        }

    @Test
        public void testToNoNullStringArrayWithNonEmptyCollection() {
            Collection<String> collection = Arrays.asList("a", null, "b");
            String[] result = ReflectionToStringBuilder.toNoNullStringArray(collection);
            assertArrayEquals(new String[]{"a", "b"}, result);
        }

    @Test
        public void testToStringWithDefaultStyleAndNullObject() {
            String result = ReflectionToStringBuilder.toString((Object) null);
            assertEquals("null", result);
        }

    @Test
        public void testToStringExcludeFieldsWithNullFieldNames() {
            Object obj = new Object();
            String result = ReflectionToStringBuilder.toStringExclude(obj, (String[]) null);
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
        }

    @Test
        public void testToStringIncludeFieldsWithNullFieldNames() {
            Object obj = new Object();
            String result = ReflectionToStringBuilder.toStringInclude(obj, (String[]) null);
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
        }

    @Test
        public void testToStringExcludeFieldsWithEmptyFieldNames() {
            Object obj = new Object();
            String result = ReflectionToStringBuilder.toStringExclude(obj, Collections.emptyList());
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
        }

    @Test
        public void testToStringIncludeFieldsWithEmptyFieldNames() {
            Object obj = new Object();
            String result = ReflectionToStringBuilder.toStringInclude(obj, Collections.emptyList());
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
        }

    @Test
        public void testToStringExcludeFieldsWithNonEmptyFieldNames() {
            Object obj = new Object();
            String[] excludeFieldNames = {"field1", "field2"};
            String result = ReflectionToStringBuilder.toStringExclude(obj, excludeFieldNames);
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
        }

    @Test
        public void testToStringIncludeFieldsWithNonEmptyFieldNames() {
            Object obj = new Object();
            String[] includeFieldNames = {"field1", "field2"};
            String result = ReflectionToStringBuilder.toStringInclude(obj, includeFieldNames);
            assertEquals(ReflectionToStringBuilder.toString(obj), result);
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
        public void testGetExcludeFieldNamesWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setExcludeFieldNames((String[]) null);
            assertNull(builder.getExcludeFieldNames());
        }

    @Test
        public void testGetIncludeFieldNamesWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setIncludeFieldNames((String[]) null);
            assertNull(builder.getIncludeFieldNames());
        }

    @Test
        public void testSetAppendTransientsWithTrue() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendTransients(true);
            assertTrue(builder.isAppendTransients());
        }

    @Test
        public void testSetAppendTransientsWithFalse() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendTransients(false);
            assertFalse(builder.isAppendTransients());
        }

    @Test
        public void testSetAppendStaticsWithTrue() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendStatics(true);
            assertTrue(builder.isAppendStatics());
        }

    @Test
        public void testSetAppendStaticsWithFalse() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setAppendStatics(false);
            assertFalse(builder.isAppendStatics());
        }

    @Test
        public void testGetUpToClassWithNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.setUpToClass(null);
            assertNull(builder.getUpToClass());
        }

    @Test
        public void testGetUpToClassWithNonNull() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            Class<?> clazz = Object.class;
            builder.setUpToClass(clazz);
            assertEquals(clazz, builder.getUpToClass());
        }

    @Test
        public void testReflectionAppendArrayWithNullArray() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            assertThrows(NullPointerException.class, () -> builder.reflectionAppendArray(null));
        }

    @Test
        public void testReflectionAppendArrayWithEmptyArray() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.reflectionAppendArray(new Object[0]);
            assertEquals("", builder.toString());
        }

    @Test
        public void testReflectionAppendArrayWithNonEmptyArray() {
            ReflectionToStringBuilder builder = new ReflectionToStringBuilder(new Object());
            builder.reflectionAppendArray(new Object[]{"a", "b"});
            assertEquals("a,b", builder.toString());
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
