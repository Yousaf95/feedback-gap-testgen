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
        public void testToStringWithCustomStyle() {
            Person person = new Person("John", 30);
            ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
            String result = ReflectionToStringBuilder.toString(person, style);
            assertEquals("Person@12345678[name=John, age=30]", result);
        }

    @Test
        public void testToStringWithCustomBuffer() {
            Person person = new Person("John", 30);
            StringBuffer buffer = new StringBuffer();
            ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE, buffer);
            assertEquals("Person[name=John, age=30]", buffer.toString());
        }

    @Test
        public void testToStringWithExcludeFieldNames() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toStringExclude(person, "age");
            assertEquals("Person@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithIncludeFieldNames() {
            Person person = new Person("John", 30);
            String result = ReflectionToStringBuilder.toStringInclude(person, "name");
            assertEquals("Person@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithNullValuesIncluded() {
            Person person = new Person(null, null);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE, false, false, false);
            assertEquals("Person@12345678[name=null, age=null]", result);
        }

    @Test
        public void testToStringWithNullValuesExcluded_2() {
            Person person = new Person(null, null);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE, false, false, true);
            assertEquals("Person@12345678", result);
        }

    @Test
        public void testToStringWithTransientField_2() {
            TransientPerson person = new TransientPerson("John");
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE);
            assertEquals("TransientPerson@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithStaticField_2() {
            StaticPerson person = new StaticPerson();
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE);
            assertEquals("StaticPerson@12345678[staticValue=value]", result);
        }

    @Test
        public void testToStringWithReflectUpToClass() {
            SubPerson person = new SubPerson("John", 30);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE, false, false, null, Person.class);
            assertEquals("SubPerson@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithExcludeFieldAnnotation() {
            ExcludePerson person = new ExcludePerson("John", 30);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE);
            assertEquals("ExcludePerson@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithIncludeFieldAnnotation() {
            IncludePerson person = new IncludePerson("John", 30);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE);
            assertEquals("IncludePerson@12345678[name=John, age=30]", result);
        }

    @Test
        public void testToStringWithSummaryAnnotation() {
            SummaryPerson person = new SummaryPerson("John", 30);
            String result = ReflectionToStringBuilder.toString(person, ToStringStyle.DEFAULT_STYLE);
            assertEquals("SummaryPerson@12345678[name=John]", result);
        }

    @Test
        public void testToStringWithNullObject() {
            Object nullObject = null;
            String result = ReflectionToStringBuilder.toString(nullObject, ToStringStyle.DEFAULT_STYLE);
            assertEquals("", result);
        }
}
