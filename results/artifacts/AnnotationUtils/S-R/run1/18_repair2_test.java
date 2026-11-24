package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.AnnotationUtils.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnnotationUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "different"})
    public void testEquals_SameAnnotations(String value) throws Exception {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        assertTrue(equals(a1, a2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "different"})
    public void testEquals_DifferentAnnotations(String value) throws Exception {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }
        };

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        assertFalse(equals(a1, a2));
    }

    @Test
    public void testEquals_NullAnnotations() throws Exception {
        assertThrows(NullPointerException.class, () -> equals(null, new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }
        }));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "different"})
    public void testHashCode_SameAnnotations(String value) throws Exception {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        assertEquals(hashCode(a1), hashCode(a2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "different"})
    public void testHashCode_DifferentAnnotations(String value) throws Exception {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }
        };

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        assertNotEquals(hashCode(a1), hashCode(a2));
    }

    @Test
    public void testIsValidAnnotationMemberType_PrimitiveType() throws Exception {
        assertTrue(isValidAnnotationMemberType(int.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_EnumType() throws Exception {
        enum TestEnum { A, B }
        assertTrue(isValidAnnotationMemberType(TestEnum.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_AnnotationType() throws Exception {
        assertTrue(isValidAnnotationMemberType(TestAnnotation.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_StringType() throws Exception {
        assertTrue(isValidAnnotationMemberType(String.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_ClassType() throws Exception {
        assertTrue(isValidAnnotationMemberType(Class.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_ArrayType() throws Exception {
        assertFalse(isValidAnnotationMemberType(int[].class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "different"})
    public void testToString_SingleValueAnnotation(String value) throws Exception {
        TestAnnotation a = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return value;
            }
        };

        assertEquals("@TestAnnotation(value=" + value + ")", toString(a));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1", "test2"})
    public void testToString_MultipleValuesAnnotation(String value) throws Exception {
        MultipleValuesAnnotation a = new MultipleValuesAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return MultipleValuesAnnotation.class;
            }

            @Override
            public String[] values() {
                return new String[]{"test1", "test2"};
            }
        };

        assertEquals("@MultipleValuesAnnotation(values=[" + value + "])", toString(a));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MultipleValuesAnnotation {
        String[] values();
    }
}
