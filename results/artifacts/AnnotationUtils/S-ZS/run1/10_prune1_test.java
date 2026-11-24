package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.AnnotationUtils.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    String value();
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnnotationUtils_GenTest {

    
    
    
    
    
    @Test
    public void testIsValidAnnotationMemberType_PrimitiveType() throws Exception {
        assertTrue(isValidAnnotationMemberType(int.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_EnumType() throws Exception {
        enum TestEnum { A, B }
        assertTrue(isValidAnnotationMemberType(TestEnum.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_AnnotationType() throws Exception {
        assertTrue(isValidAnnotationMemberType(TestAnnotation.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_StringType() throws Exception {
        assertTrue(isValidAnnotationMemberType(String.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_ClassType() throws Exception {
        assertTrue(isValidAnnotationMemberType(Class.class));
    }

    @Test
    public void testIsValidAnnotationMemberType_ArrayType() throws Exception {
        assertFalse(isValidAnnotationMemberType(int[].class));
    }

    
    
    @Retention(RetentionPolicy.RUNTIME)
    @interface MultipleValuesAnnotation {
        String[] values();
    }
}
