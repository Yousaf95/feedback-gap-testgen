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
    public void testToStringWithCustomStyle() {
        Person person = new Person("John", 30);
        ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
        String result = ReflectionToStringBuilder.toString(person, style);
        assertEquals("Person(name=John, age=30)", result);
    }

    @Test
    public void testToStringWithTransientField() {
        TransientPerson person = new TransientPerson("John", 30);
        String result = ReflectionToStringBuilder.toString(person);
        assertTrue(result.contains("name=John"));
        assertFalse(result.contains("age=30"));
    }

    @Test
    public void testToStringWithStaticField() {
        StaticPerson person = new StaticPerson("John", 30);
        String result = ReflectionToStringBuilder.toString(person);
        assertTrue(result.contains("name=John"));
        assertTrue(result.contains("staticValue=42"));
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
