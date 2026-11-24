package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationUtils_GenTest {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestAnnotation {
        String value();
        int number() default 42;
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnotherTestAnnotation {
        String name();
    }

    @Test
    void testEquals_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertTrue(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_nullAnnotations() {
        // Arrange
        TestAnnotation annotation = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation, null));
        assertFalse(AnnotationUtils.equals(null, annotation));
    }

    @Test
    void testEquals_differentValues() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "different";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testHashCode_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertNotEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.hashCode(null));
    }

    @ParameterizedTest
    @ValueSource(classes = {int.class, String.class, Class.class, AnotherTestAnnotation.class})
    void testIsValidAnnotationMemberType_validTypes(Class<?> type) {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    
    
    
    @Test
    void testToString_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(null));
    }

    
    
    @Test
        void testIsValidAnnotationMemberType_invalidTypes() {
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(new Object().getClass()));
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(Object[].class));
        }

    
    
    
    
    
    
    
    @Test
        void testIsValidAnnotationMemberType_arrayOfValidTypes() {
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(String[].class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(int[].class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(Class[].class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(Enum[].class));
            assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation[].class));
        }

    @Test
        void testIsValidAnnotationMemberType_arrayOfInvalidTypes() {
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(Object[].class));
            assertFalse(AnnotationUtils.isValidAnnotationMemberType(String[][].class));
        }

    