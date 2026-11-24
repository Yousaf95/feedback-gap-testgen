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

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendDetail_withAnnotation() {
            TestAnnotation annotation = this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0];
            ToStringBuilder builder = new ToStringBuilder(new Object(), AnnotationUtils.TO_STRING_STYLE);
            AnnotationUtils.TO_STRING_STYLE.appendDetail(builder.getStringBuffer(), "test", annotation);
            assertTrue(builder.toString().contains("@org.apache.commons.lang3.AnnotationUtils_GenTest$TestAnnotation"));
        }

    @Test
        void testGetShortClassName_withAnnotation() {
            String className = AnnotationUtils.TO_STRING_STYLE.getShortClassName(TestAnnotation.class);
            assertEquals("@org.apache.commons.lang3.AnnotationUtils_GenTest$TestAnnotation", className);
        }

    @Test
        void testAnnotationArrayMemberEquals_differentLengths() {
            TestAnnotation[] a1 = new TestAnnotation[0];
            TestAnnotation[] a2 = new TestAnnotation[1];
            assertFalse(AnnotationUtils.annotationArrayMemberEquals(a1, a2));
        }

    @Test
        void testAnnotationArrayMemberEquals_sameAnnotations() {
            TestAnnotation[] a1 = { this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0] };
            TestAnnotation[] a2 = { this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0] };
            assertTrue(AnnotationUtils.annotationArrayMemberEquals(a1, a2));
        }

    @Test
        void testArrayMemberEquals_withAnnotationArrays() {
            TestAnnotation[] a1 = { this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0] };
            TestAnnotation[] a2 = { this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0] };
            assertTrue(AnnotationUtils.arrayMemberEquals(TestAnnotation[].class.getComponentType(), a1, a2));
        }

    @Test
        void testArrayMemberEquals_withByteArrays() {
            byte[] a1 = { 1, 2, 3 };
            byte[] a2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.arrayMemberEquals(byte.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withShortArrays() {
            short[] a1 = { 1, 2, 3 };
            short[] a2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.arrayMemberEquals(short.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withIntArrays() {
            int[] a1 = { 1, 2, 3 };
            int[] a2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.arrayMemberEquals(int.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withCharArrays() {
            char[] a1 = { 'a', 'b', 'c' };
            char[] a2 = { 'a', 'b', 'c' };
            assertTrue(AnnotationUtils.arrayMemberEquals(char.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withLongArrays() {
            long[] a1 = { 1L, 2L, 3L };
            long[] a2 = { 1L, 2L, 3L };
            assertTrue(AnnotationUtils.arrayMemberEquals(long.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withFloatArrays() {
            float[] a1 = { 1.0f, 2.0f, 3.0f };
            float[] a2 = { 1.0f, 2.0f, 3.0f };
            assertTrue(AnnotationUtils.arrayMemberEquals(float.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withDoubleArrays() {
            double[] a1 = { 1.0, 2.0, 3.0 };
            double[] a2 = { 1.0, 2.0, 3.0 };
            assertTrue(AnnotationUtils.arrayMemberEquals(double.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withBooleanArrays() {
            boolean[] a1 = { true, false, true };
            boolean[] a2 = { true, false, true };
            assertTrue(AnnotationUtils.arrayMemberEquals(boolean.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withObjectArrays() {
            Object[] a1 = { "a", "b", "c" };
            Object[] a2 = { "a", "b", "c" };
            assertTrue(AnnotationUtils.arrayMemberEquals(Object.class, a1, a2));
        }

    @Test
        void testArrayMemberHash_withByteArrays() {
            byte[] array = { 1, 2, 3 };
            int hash = AnnotationUtils.arrayMemberHash(byte.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withShortArrays() {
            short[] array = { 1, 2, 3 };
            int hash = AnnotationUtils.arrayMemberHash(short.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withIntArrays() {
            int[] array = { 1, 2, 3 };
            int hash = AnnotationUtils.arrayMemberHash(int.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withCharArrays() {
            char[] array = { 'a', 'b', 'c' };
            int hash = AnnotationUtils.arrayMemberHash(char.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withLongArrays() {
            long[] array = { 1L, 2L, 3L };
            int hash = AnnotationUtils.arrayMemberHash(long.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withFloatArrays() {
            float[] array = { 1.0f, 2.0f, 3.0f };
            int hash = AnnotationUtils.arrayMemberHash(float.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withDoubleArrays() {
            double[] array = { 1.0, 2.0, 3.0 };
            int hash = AnnotationUtils.arrayMemberHash(double.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withBooleanArrays() {
            boolean[] array = { true, false, true };
            int hash = AnnotationUtils.arrayMemberHash(boolean.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testArrayMemberHash_withObjectArrays() {
            Object[] array = { "a", "b", "c" };
            int hash = AnnotationUtils.arrayMemberHash(Object.class, array);
            assertEquals(Arrays.hashCode(array), hash);
        }

    @Test
        void testEquals_nullAnnotations() {
            assertFalse(AnnotationUtils.equals(null, null));
        }

    @Test
        void testEquals_firstAnnotationNull() {
            TestAnnotation annotation = this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0];
            assertFalse(AnnotationUtils.equals(null, annotation));
        }

    @Test
        void testEquals_secondAnnotationNull() {
            TestAnnotation annotation = this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0];
            assertFalse(AnnotationUtils.equals(annotation, null));
        }

    @Test
        void testEquals_differentTypes() {
            TestAnnotation annotation1 = this.getClass().getDeclaredAnnotationsByType(TestAnnotation.class)[0];
            Retention retention = String.class.getAnnotation(Retention.class);
            assertFalse(AnnotationUtils.equals(annotation1, retention));
        }
}
