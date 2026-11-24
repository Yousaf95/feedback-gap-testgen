package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionToStringBuilder_GenTest {

    @Test
    public void testToStringNullObject() {
        String result = ReflectionToStringBuilder.toString(null);
        assertEquals("null", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "test"})
    public void testToStringEmptyString(String input) {
        String result = ReflectionToStringBuilder.toString(input);
        assertEquals("\"" + input + "\"", result);
    }

    @Test
    public void testToStringWithExcludeFieldNames() {
        MyClass obj = new MyClass();
        obj.setName("John");
        obj.setAge(30);

        String result = ReflectionToStringBuilder.toStringExclude(obj, Collections.singletonList("name"));
        assertEquals("[age=30]", result);
    }

    @Test
    public void testToStringWithIncludeFieldNames() {
        MyClass obj = new MyClass();
        obj.setName("John");
        obj.setAge(30);

        String result = ReflectionToStringBuilder.toStringInclude(obj, Collections.singletonList("name"));
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithTransientField() {
        TransientClass obj = new TransientClass();
        obj.setName("John");
        obj.setAge(30);

        String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithStaticField() {
        StaticClass obj = new StaticClass();
        obj.setName("John");
        obj.setAge(30);

        String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithNullValues() {
        NullValueClass obj = new NullValueClass();
        obj.setName("John");
        obj.setAge(null);

        String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithExcludeNullValues() {
        NullValueClass obj = new NullValueClass();
        obj.setName("John");
        obj.setAge(null);

        String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, false, true);
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithReflectUpToClass() {
        ReflectUpToClass obj = new ReflectUpToClass();
        obj.setName("John");
        obj.setAge(30);

        String result = ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, false, MyClass.class);
        assertEquals("[name=John]", result);
    }

    @Test
    public void testToStringWithReflectUpToClassException() {
        ReflectUpToClass obj = new ReflectUpToClass();
        obj.setName("John");
        obj.setAge(30);

        assertThrows(IllegalArgumentException.class, () -> 
            ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE, false, false, String.class)
        );
    }

    private static class MyClass {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class TransientClass {
        @ToStringExclude
        private String name;

        private transient int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class StaticClass {
        @ToStringExclude
        private static String name;

        private static int age;

        public static String getName() {
            return name;
        }

        public static void setName(String name) {
            StaticClass.name = name;
        }

        public static int getAge() {
            return age;
        }

        public static void setAge(int age) {
            StaticClass.age = age;
        }
    }

    private static class NullValueClass {
        private String name;

        @ToStringExclude
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    private static class ReflectUpToClass {
        @ToStringExclude
        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
