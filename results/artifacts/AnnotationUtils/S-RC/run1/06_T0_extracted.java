package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.AnnotationUtils.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    String value();
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnnotationUtils_GenTest {

    @Test
    public void testEquals_SameAnnotations() throws Exception {
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
                return "test";
            }
        };

        assertTrue(equals(a1, a2));
    }

    @Test
    public void testEquals_DifferentAnnotations() throws Exception {
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
                return "different";
            }
        };

        assertFalse(equals(a1, a2));
    }

    @Test
    public void testEquals_NullAnnotations() throws Exception {
        TestAnnotation a1 = null;

        assertThrows(NullPointerException.class, () -> equals(a1, new TestAnnotation() {
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

    @Test
    public void testHashCode_SameAnnotations() throws Exception {
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
                return "test";
            }
        };

        assertEquals(hashCode(a1), hashCode(a2));
    }

    @Test
    public void testHashCode_DifferentAnnotations() throws Exception {
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
                return "different";
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

    @Test
    public void testToString_SingleValueAnnotation() throws Exception {
        TestAnnotation a = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }
        };

        assertEquals("@TestAnnotation(value=test)", toString(a));
    }

    @Test
    public void testToString_MultipleValuesAnnotation() throws Exception {
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

        assertEquals("@MultipleValuesAnnotation(values=[test1, test2])", toString(a));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface MultipleValuesAnnotation {
        String[] values();
    }
}
