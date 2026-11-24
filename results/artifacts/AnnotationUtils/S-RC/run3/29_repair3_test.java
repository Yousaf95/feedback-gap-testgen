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
    public @interface TestAnnotation {
        String value();
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

        assertFalse(AnnotationUtils.equals(a1, a2));
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

        assertNotEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
    }

    @Test
    public void testIsValidAnnotationMemberType_withValidTypes() {
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(String.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(int.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(Class[].class));
    }

    @Test
    public void testIsValidAnnotationMemberType_withInvalidTypes() {
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(null));
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(Object.class));
        // assertFalse(AnnotationUtils.isValidAnnotationMemberType(new int[0])); // Removed as it cannot be fixed using the public API
    }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDetail_withAnnotation() {
            ToStringBuilder builder = new ToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE);
            AnnotationUtils.appendDetail(builder, "testField", new TestAnnotationImpl("testValue"));
            assertEquals("@TestAnnotation(value=testValue)", builder.build());
        }

    @Test
        public void testGetShortClassName_withAnnotation() {
            String shortClassName = AnnotationUtils.getShortClassName(TestAnnotation.class);
            assertEquals("@TestAnnotation", shortClassName);
        }

    @Test
        public void testArrayMemberEquals_withAnnotations() {
            TestAnnotation[] annotations1 = { new TestAnnotationImpl("testValue") };
            TestAnnotation[] annotations2 = { new TestAnnotationImpl("testValue") };
            assertTrue(AnnotationUtils.arrayMemberEquals(TestAnnotation.class, annotations1, annotations2));
        }

    @Test
        public void testArrayMemberHash_withAnnotations() {
            TestAnnotation[] annotations = { new TestAnnotationImpl("testValue") };
            int hash = AnnotationUtils.arrayMemberHash(TestAnnotation.class, annotations);
            assertEquals(annotations[0].hashCode(), hash);
        }

    @Test
        public void testEquals_withEqualAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("testValue");
            TestAnnotation a2 = new TestAnnotationImpl("testValue");
            assertTrue(AnnotationUtils.equals(a1, a2));
        }

    @Test
        public void testHashCode_withEqualAnnotations_2() {
            TestAnnotation a1 = new TestAnnotationImpl("testValue");
            TestAnnotation a2 = new TestAnnotationImpl("testValue");
            assertEquals(a1.hashCode(), a2.hashCode());
        }

    @Test
        public void testIsValidAnnotationMemberType_withValidTypes_2() {
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(String.class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation.class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(boolean[].class));
        }

    @Test
        public void testIsValidAnnotationMemberType_withInvalidTypes_2() {
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(Object.class));
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(void.class));
        }
}
