package org.apache.commons.lang3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationUtils_GenTest {

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestAnnotation {
        String value();
        int count() default 0;
    }

    @Test
    public void testEquals_withEqualAnnotations() {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
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

            @Override
            public int count() {
                return 0;
            }
        };

        assertTrue(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testEquals_withDifferentAnnotations() {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
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

            @Override
            public int count() {
                return 1;
            }
        };

        assertFalse(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testEquals_withNullAnnotations() {
        TestAnnotation a1 = null;

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
            }
        };

        assertFalse(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testEquals_withNullType() {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return null;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
            }
        };

        TestAnnotation a2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return null;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
            }
        };

        assertThrows(NullPointerException.class, () -> AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testHashCode_withEqualAnnotations() {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
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

            @Override
            public int count() {
                return 0;
            }
        };

        assertEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
    }

    @Test
    public void testHashCode_withDifferentAnnotations() {
        TestAnnotation a1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }

            @Override
            public String value() {
                return "test";
            }

            @Override
            public int count() {
                return 0;
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

            @Override
            public int count() {
                return 1;
            }
        };

        assertNotEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
    }

    @Test
    public void testHashCode_withNullAnnotations() {
        TestAnnotation a1 = null;

        assertThrows(NullPointerException.class, () -> AnnotationUtils.hashCode(a1));
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class, int.class, boolean.class, TestAnnotation.class})
    public void testIsValidAnnotationMemberType_validTypes(Class<?> type) {
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    
    
    
    
    
    @Test
        void testIsValidAnnotationMemberType_withInvalidType() {
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(new Object().getClass()));
        }

    
    
    
    @Test
        void testEquals_withEmptyAnnotations() {
            TestAnnotation a1 = TestAnnotation.class.getAnnotation(TestAnnotation.class);
            TestAnnotation a2 = TestAnnotation.class.getAnnotation(TestAnnotation.class);
            assertTrue(AnnotationUtils.equals(a1, a2));
        }

    
    
    @Test
        void testIsValidAnnotationMemberType_withNull() {
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(null));
        }

    
    
    
    
    
    @Test
        public void testIsValidAnnotationMemberType_withInvalidNestedArray() {
            Class<?> type = int[][][].class;
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(type));
        }

    }
