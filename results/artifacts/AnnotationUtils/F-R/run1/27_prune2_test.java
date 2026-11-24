package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class AnnotationUtils_GenTest {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestAnnotation {
        String value();
        int count() default 0;
    }

    private static final TestAnnotation TEST_ANNOTATION = new TestAnnotation() {
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
            return 42;
        }
    };

    private static final TestAnnotation TEST_ANNOTATION_NULL_COUNT = new TestAnnotation() {
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

    private static final TestAnnotation TEST_ANNOTATION_DIFFERENT_COUNT = new TestAnnotation() {
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
            return 100;
        }
    };

    private static final TestAnnotation TEST_ANNOTATION_DIFFERENT_VALUE = new TestAnnotation() {
        @Override
        public Class<? extends java.lang.annotation.Annotation> annotationType() {
            return TestAnnotation.class;
        }

        @Override
        public String value() {
            return "different";
        }

        @Override
        public int count() {
            return 42;
        }
    };

    private static final TestAnnotation TEST_ANNOTATION_NULL = null;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testEquals_SameInstance() {
        assertTrue(AnnotationUtils.equals(TEST_ANNOTATION, TEST_ANNOTATION));
    }

    @Test
    void testEquals_NullFirst() {
        assertFalse(AnnotationUtils.equals(null, TEST_ANNOTATION));
    }

    @Test
    void testEquals_NullSecond() {
        assertFalse(AnnotationUtils.equals(TEST_ANNOTATION, null));
    }

       }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
