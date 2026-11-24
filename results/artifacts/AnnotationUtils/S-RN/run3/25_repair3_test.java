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
        public void testToString_withSimpleAnnotation() {
            // Define a simple annotation for testing
            @interface SimpleAnnotation {
                String value();
            }
    
            // Create an instance of the annotation
            SimpleAnnotation annotation = () -> "test";
    
            // Test the toString method
            assertEquals("@SimpleAnnotation(value=test)", AnnotationUtils.toString(annotation));
        }

    @Test
        public void testToString_withComplexAnnotation() {
            // Define a complex annotation for testing
            @interface ComplexAnnotation {
                String value();
                int count();
            }
    
            // Create an instance of the annotation
            ComplexAnnotation annotation = () -> "test", 5;
    
            // Test the toString method
            assertEquals("@ComplexAnnotation(value=test, count=5)", AnnotationUtils.toString(annotation));
        }

    @Test
        public void testToString_withNestedAnnotations() {
            // Define nested annotations for testing
            @interface OuterAnnotation {
                InnerAnnotation inner();
            }
    
            @interface InnerAnnotation {
                String value();
            }
    
            // Create an instance of the outer annotation with nested inner annotation
            OuterAnnotation annotation = () -> () -> "test";
    
            // Test the toString method
            assertEquals("@OuterAnnotation(inner=@InnerAnnotation(value=test))", AnnotationUtils.toString(annotation));
        }

    @Test
        public void testToString_withNullValue() {
            // Define an annotation for testing with a null value
            @interface NullValueAnnotation {
                String value();
            }
    
            // Create an instance of the annotation with a null value
            NullValueAnnotation annotation = () -> null;
    
            // Test the toString method
            assertEquals("@NullValueAnnotation(value=null)", AnnotationUtils.toString(annotation));
        }
}
