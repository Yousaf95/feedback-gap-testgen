package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

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
    void testEquals_sameAnnotations() throws NoSuchMethodException {
        // Arrange
        TestAnnotation annotation1 = this.getClass().getDeclaredMethod("testEquals_sameAnnotations").getAnnotation(TestAnnotation.class);
        TestAnnotation annotation2 = this.getClass().getDeclaredMethod("testEquals_sameAnnotations").getAnnotation(TestAnnotation.class);

        // Act & Assert
        assertTrue(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_differentAnnotations() throws NoSuchMethodException {
        // Arrange
        TestAnnotation annotation1 = this.getClass().getDeclaredMethod("testEquals_differentAnnotations").getAnnotation(TestAnnotation.class);
        AnotherTestAnnotation annotation2 = this.getClass().getDeclaredMethod("testEquals_differentAnnotations").getAnnotation(AnotherTestAnnotation.class);

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_nullAnnotations() throws NoSuchMethodException {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.equals(null, null));
        assertFalse(AnnotationUtils.equals(null, this.getClass().getDeclaredMethod("testEquals_nullAnnotations").getAnnotation(TestAnnotation.class)));
        assertFalse(AnnotationUtils.equals(this.getClass().getDeclaredMethod("testEquals_nullAnnotations").getAnnotation(TestAnnotation.class), null));
    }

    @Test
    void testHashCode_sameAnnotations() throws NoSuchMethodException {
        // Arrange
        TestAnnotation annotation1 = this.getClass().getDeclaredMethod("testHashCode_sameAnnotations").getAnnotation(TestAnnotation.class);
        TestAnnotation annotation2 = this.getClass().getDeclaredMethod("testHashCode_sameAnnotations").getAnnotation(TestAnnotation.class);

        // Act & Assert
        assertEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_differentAnnotations() throws NoSuchMethodException {
        // Arrange
        TestAnnotation annotation1 = this.getClass().getDeclaredMethod("testHashCode_differentAnnotations").getAnnotation(TestAnnotation.class);
        AnotherTestAnnotation annotation2 = this.getClass().getDeclaredMethod("testHashCode_differentAnnotations").getAnnotation(AnotherTestAnnotation.class);

        // Act & Assert
        assertNotEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
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

    @ParameterizedTest
    @ValueSource(classes = {TestAnnotation.class})
    void testToString_validAnnotations(Class<? extends Annotation> annotationClass) throws NoSuchMethodException {
        // Arrange
        Method method = this.getClass().getDeclaredMethod("testEquals_sameAnnotations");
        Annotation annotation = method.getAnnotation(annotationClass);

        // Act
        String result = AnnotationUtils.toString(annotation);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("@"));
    }

    @Test
    void testToString_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(null));
    }
}
