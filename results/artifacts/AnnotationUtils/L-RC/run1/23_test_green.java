package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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
    void testEquals_sameAnnotations() throws NoSuchMethodException {
        // Arrange
        TestAnnotation annotation1 = this.getClass().getDeclaredMethod("testEquals_sameAnnotations").getAnnotation(TestAnnotation.class);
        TestAnnotation annotation2 = this.getClass().getDeclaredMethod("testEquals_sameAnnotations").getAnnotation(TestAnnotation.class);

        // Act & Assert
        assertTrue(AnnotationUtils.equals(annotation1, annotation2));
    }

    
    
    
    
    @Test
    void testIsValidAnnotationMemberType_primitiveTypes() {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(int.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(boolean.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(char.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(byte.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(short.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(long.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(float.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(double.class));
    }

    @Test
    void testIsValidAnnotationMemberType_referenceTypes() {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(String.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(Class.class));
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(Object.class));
    }

    @Test
    void testIsValidAnnotationMemberType_enumAndAnnotations() {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(RetentionPolicy.class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation.class));
    }

    @Test
    void testIsValidAnnotationMemberType_arrayTypes() {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(int[].class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(String[].class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(RetentionPolicy[].class));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(TestAnnotation[].class));
    }

    @Test
    void testIsValidAnnotationMemberType_null() {
        // Arrange & Act & Assert
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(null));
    }

    
    @Test
    void testToString_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(null));
    }

    
    
    
    }
