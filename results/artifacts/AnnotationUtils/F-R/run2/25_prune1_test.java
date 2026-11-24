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
        int count() default 0;
    }

    @Test
    public void testEquals_withEqualAnnotations() {
        TestAnnotation a1 = new TestAnnotationImpl("test", 5);
        TestAnnotation a2 = new TestAnnotationImpl("test", 5);
        assertTrue(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testEquals_withDifferentAnnotations() {
        TestAnnotation a1 = new TestAnnotationImpl("test", 5);
        TestAnnotation a2 = new TestAnnotationImpl("other", 5);
        assertFalse(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testEquals_withNullAnnotations() {
        TestAnnotation a1 = null;
        TestAnnotation a2 = null;
        assertTrue(AnnotationUtils.equals(a1, a2));

        a1 = new TestAnnotationImpl("test", 5);
        assertFalse(AnnotationUtils.equals(a1, a2));
    }

    @Test
    public void testHashCode_withEqualAnnotations() {
        TestAnnotation a1 = new TestAnnotationImpl("test", 5);
        TestAnnotation a2 = new TestAnnotationImpl("test", 5);
        assertEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
    }

    @Test
    public void testHashCode_withDifferentAnnotations() {
        TestAnnotation a1 = new TestAnnotationImpl("test", 5);
        TestAnnotation a2 = new TestAnnotationImpl("other", 5);
        assertNotEquals(AnnotationUtils.hashCode(a1), AnnotationUtils.hashCode(a2));
    }

    @Test
    public void testHashCode_withNullAnnotations() {
        TestAnnotation a1 = null;
        assertThrows(NullPointerException.class, () -> AnnotationUtils.hashCode(a1));
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class, int.class, boolean.class})
    public void testIsValidAnnotationMemberType_validTypes(Class<?> type) {
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    @ParameterizedTest
    @ValueSource(classes = {void.class, Object.class})
    public void testIsValidAnnotationMemberType_invalidTypes(Class<?> type) {
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    @Test
    public void testToString_withAnnotation() {
        TestAnnotation a1 = new TestAnnotationImpl("test", 5);
        assertEquals("@TestAnnotation(value=test, count=5)", AnnotationUtils.toString(a1));
    }

    @Test
    public void testToString_withNullAnnotation() {
        TestAnnotation a1 = null;
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(a1));
    }

    private static class TestAnnotationImpl implements TestAnnotation {
        private final String value;
        private final int count;

        public TestAnnotationImpl(String value, int count) {
            this.value = value;
            this.count = count;
        }

        
        @Override
        public String value() {
            return value;
        }

        @Override
        public int count() {
            return count;
        }
    }
}
