package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class CompareToBuilder_GenTest {

    @Test
    public void testReflectionCompare_NullObjects() {
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
    }

    @Test
    public void testReflectionCompare_SameObject() {
        assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
    }

    @Test
    public void testReflectionCompare_DifferentClasses() {
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare("string", 123));
    }

    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendObjectArray() {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1, 2}).toComparison());
        assertNotEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1, 3}).toComparison());
        assertNotEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1}).toComparison());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_NullObjects_2() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_SameObject_2() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    
    @Test
        public void testAppendBooleanArray_NotEqualLengths() {
            boolean[] lhs = {true};
            boolean[] rhs = {false, true};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendByteArray_NotEqualLengths() {
            byte[] lhs = {0};
            byte[] rhs = {1, 2};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendCharArray_NotEqualLengths() {
            char[] lhs = {'a'};
            char[] rhs = {'b', 'c'};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArray_NotEqualLengths() {
            double[] lhs = {0.5};
            double[] rhs = {1.0, 2.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendFloatArray_NotEqualLengths() {
            float[] lhs = {0.5f};
            float[] rhs = {1.0f, 2.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendIntArray_NotEqualLengths() {
            int[] lhs = {1};
            int[] rhs = {2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendLongArray_NotEqualLengths() {
            long[] lhs = {1L};
            long[] rhs = {2L, 3L};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendShortArray_NotEqualLengths() {
            short[] lhs = {1};
            short[] rhs = {2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendObjectArray_NotEqualLengths() {
            Object[] lhs = {"a"};
            Object[] rhs = {"b", "c"};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendObjectArray_NullElements() {
            Object[] lhs = {null};
            Object[] rhs = {"a"};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }
}
