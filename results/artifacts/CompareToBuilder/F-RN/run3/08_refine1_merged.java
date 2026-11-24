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
        public void testAppendArrayWithComparatorNull() {
            Object[] lhs = {1, 2, 3};
            Object[] rhs = {1, 2, 4};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithComparatorNotNull() {
            Object[] lhs = {1, 2, 3};
            Object[] rhs = {1, 2, 4};
            Comparator<Integer> comparator = (a, b) -> Integer.compare((Integer)a, (Integer)b);
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, comparator);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithNullElements() {
            Object[] lhs = {1, null, 3};
            Object[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithEmptyArrays() {
            Object[] lhs = {};
            Object[] rhs = {};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentLengths() {
            Object[] lhs = {1, 2};
            Object[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithSameElements() {
            Object[] lhs = {1, 2, 3};
            Object[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentOrder() {
            Object[] lhs = {3, 2, 1};
            Object[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithNullArrays() {
            Object[] lhs = null;
            Object[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithNullComparator() {
            Object[] lhs = {1, 2, 3};
            Object[] rhs = {1, 2, 4};
            CompareToBuilder builder = new CompareToBuilder();
            assertThrows(NullPointerException.class, () -> builder.append(lhs, rhs, null));
        }
}
