package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;

public class CompareToBuilder_GenTest {

    @Test
    public void testReflectionCompare_nullObjects() {
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
    }

    @Test
    public void testReflectionCompare_sameObject() {
        assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
    }

    @Test
    public void testReflectionCompare_differentObjects() {
        class TestClass {
            int value = 1;
        }
        TestClass lhs = new TestClass();
        TestClass rhs = new TestClass();
        rhs.value = 2;
        assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    
    
    
    @Test
    public void testAppend_byte() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append((byte) 2, (byte) 1);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_char() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append('b', 'a');
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_double() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2.0, 1.0);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_float() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2.0f, 1.0f);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_int() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2, 1);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_long() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2L, 1L);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_short() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append((short) 2, (short) 1);
        assertEquals(1, builder.toComparison());
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppend_nullArrays() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((Object[]) null, (Object[]) null);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppend_nullLeftArray() {
            Object[] lhs = null;
            Object[] rhs = {1};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_nullRightArray() {
            Object[] lhs = {1};
            Object[] rhs = null;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppend_differentLengthArrays() {
            Object[] lhs = {1};
            Object[] rhs = {1, 2};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_sameLengthDifferentElementsArrays() {
            Object[] lhs = {1, 3};
            Object[] rhs = {1, 2};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppend_sameLengthSameElementsArrays() {
            Object[] lhs = {1, 2};
            Object[] rhs = {1, 2};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppend_Comparator() {
            Comparator<Integer> comparator = Integer::compare;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2, comparator);
            assertEquals(-1, builder.toComparison());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppend_Comparator_2() {
            Comparator<String> comparator = String::compareToIgnoreCase;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append("apple", "APPLE", comparator);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppend_Comparator_nullLeft() {
            Comparator<String> comparator = String::compareToIgnoreCase;
            CompareToBuilder builder = new CompareToBuilder();
            assertThrows(NullPointerException.class, () -> builder.append(null, "APPLE", comparator));
        }

    @Test
        public void testAppend_Comparator_nullRight() {
            Comparator<String> comparator = String::compareToIgnoreCase;
            CompareToBuilder builder = new CompareToBuilder();
            assertThrows(NullPointerException.class, () -> builder.append("apple", null, comparator));
        }

    @Test
        public void testAppend_Comparator_differentLengthArrays() {
            Comparator<Integer> comparator = Integer::compare;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new Integer[]{1, 2}, new Integer[]{1, 2, 3}, comparator);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Comparator_sameLengthDifferentElementsArrays() {
            Comparator<Integer> comparator = Integer::compare;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new Integer[]{1, 2, 3}, new Integer[]{1, 4, 3}, comparator);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Comparator_sameLengthSameElementsArrays() {
            Comparator<Integer> comparator = Integer::compare;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3}, comparator);
            assertEquals(0, builder.toComparison());
        }
}
