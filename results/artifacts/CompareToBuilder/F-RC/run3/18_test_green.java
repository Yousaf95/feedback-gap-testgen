package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CompareToBuilder_GenTest {

    private CompareToBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new CompareToBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReflectionCompareWithNull() {
        class TestClass {
            int field1 = 1;
        }
        TestClass obj1 = new TestClass();
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(obj1, null));
    }

    @Test
    public void testAppendArrayWithComparator() {
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 4};
        Comparator<Integer> comparator = Comparator.naturalOrder();
        assertEquals(-1, builder.append(array1, array2, comparator).toComparison());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompareWithNull_2() {
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
        }

    @Test
        public void testAppendArrayWithComparator_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 4};
            Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, comparator);
            assertEquals(-1, builder.toComparison());
        }

    
    @Test
        public void testAppendBooleanArray() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true, true};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendByteArray() {
            byte[] lhs = {1, 2};
            byte[] rhs = {1, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendCharArray() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'a', 'c'};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArray() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0, 3.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendFloatArray() {
            float[] lhs = {1.0f, 2.0f};
            float[] rhs = {1.0f, 3.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendIntArray() {
            int[] lhs = {1, 2};
            int[] rhs = {1, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendLongArray() {
            long[] lhs = {1L, 2L};
            long[] rhs = {1L, 3L};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendShortArray() {
            short[] lhs = {1, 2};
            short[] rhs = {1, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }
}
