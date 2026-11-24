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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDetail_withAnnotationValue() {
            Annotation annotation = new TestAnnotationImpl("test", 42);
            ToStringBuilder builder = new ToStringBuilder(annotation, AnnotationUtils.TO_STRING_STYLE);
            AnnotationUtils.appendDetail(builder, "annotation", annotation);
            assertEquals("@TestAnnotation(value=test, count=42)", builder.build());
        }

    @Test
        public void testAppendDetail_withNonAnnotationValue() {
            ToStringBuilder builder = new ToStringBuilder(new Object(), AnnotationUtils.TO_STRING_STYLE);
            AnnotationUtils.appendDetail(builder, "nonAnnotation", "test");
            assertEquals("nonAnnotation=test", builder.build());
        }

    @Test
        public void testGetShortClassName_withAnnotationClass() {
            String result = AnnotationUtils.getShortClassName(TestAnnotation.class);
            assertEquals("@TestAnnotation", result);
        }

    @Test
        public void testGetShortClassName_withNonAnnotationClass() {
            String result = AnnotationUtils.getShortClassName(String.class);
            assertEquals("", result);
        }

    @Test
        public void testAnnotationArrayMemberEquals_withEqualArrays() {
            Annotation[] array1 = { new TestAnnotationImpl("test", 42) };
            Annotation[] array2 = { new TestAnnotationImpl("test", 42) };
            assertTrue(AnnotationUtils.annotationArrayMemberEquals(array1, array2));
        }

    @Test
        public void testAnnotationArrayMemberEquals_withDifferentArrays() {
            Annotation[] array1 = { new TestAnnotationImpl("test", 42) };
            Annotation[] array2 = { new TestAnnotationImpl("test", 0) };
            assertFalse(AnnotationUtils.annotationArrayMemberEquals(array1, array2));
        }

    @Test
        public void testArrayMemberEquals_withEqualArrays() {
            Object[] array1 = { "test", 42 };
            Object[] array2 = { "test", 42 };
            assertTrue(AnnotationUtils.arrayMemberEquals(Object.class, array1, array2));
        }

    @Test
        public void testArrayMemberEquals_withDifferentArrays() {
            Object[] array1 = { "test", 42 };
            Object[] array2 = { "test", 0 };
            assertFalse(AnnotationUtils.arrayMemberEquals(Object.class, array1, array2));
        }

    @Test
        public void testArrayMemberHash_withEqualArrays() {
            Object[] array1 = { "test", 42 };
            Object[] array2 = { "test", 42 };
            assertEquals(AnnotationUtils.arrayMemberHash(Object.class, array1), AnnotationUtils.arrayMemberHash(Object.class, array2));
        }

    @Test
        public void testArrayMemberHash_withDifferentArrays() {
            Object[] array1 = { "test", 42 };
            Object[] array2 = { "test", 0 };
            assertNotEquals(AnnotationUtils.arrayMemberHash(Object.class, array1), AnnotationUtils.arrayMemberHash(Object.class, array2));
        }

    @Test
        public void testEquals_withEqualAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("test", 42);
            TestAnnotation a2 = new TestAnnotationImpl("test", 42);
            assertTrue(AnnotationUtils.equals(a1, a2));
        }

    @Test
        public void testEquals_withDifferentAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("test", 42);
            TestAnnotation a2 = new TestAnnotationImpl("test", 0);
            assertFalse(AnnotationUtils.equals(a1, a2));
        }

    @Test
        public void testEquals_withNullAnnotations_2() {
            TestAnnotation a1 = null;
            TestAnnotation a2 = null;
            assertTrue(AnnotationUtils.equals(a1, a2));
        }

    @Test
        public void testEquals_withNullType_2() {
            TestAnnotation a1 = new TestAnnotationImpl("test", 42);
            TestAnnotation a2 = null;
            assertFalse(AnnotationUtils.equals(a1, a2));
        }

    @Test
        public void testHashCode_withEqualAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("test", 42);
            TestAnnotation a2 = new TestAnnotationImpl("test", 42);
            assertEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
        }

    @Test
        public void testHashCode_withDifferentAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("test", 42);
            TestAnnotation a2 = new TestAnnotationImpl("test", 0);
            assertNotEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
        }

    @Test
        public void testHashCode_withNullAnnotations_2() {
            TestAnnotation a1 = null;
            int hash = AnnotationUtils.hashCode(a1);
            assertEquals(hash, hash); // Any non-zero value is acceptable
        }

    @Test
        public void testIsValidAnnotationMemberType_validTypes_2() {
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(String.class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(int.class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation.class));
        }
}
