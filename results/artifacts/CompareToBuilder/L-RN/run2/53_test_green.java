package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Comparator;

public class CompareToBuilder_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testBuild() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(Integer.valueOf(0), builder.build());
        builder.append(1, 2);
        assertEquals(Integer.valueOf(-1), builder.build());
    }

    @Test
    public void testReflectionCompare() {
        // Arrange
        class TestClass implements Comparable<TestClass> {
            private int value;
            public TestClass(int value) { this.value = value; }
            @Override
            public int compareTo(TestClass other) { return Integer.compare(this.value, other.value); }
        }

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(new TestClass(1), new TestClass(1)));
        assertEquals(-1, CompareToBuilder.reflectionCompare(new TestClass(0), new TestClass(1)));
        assertEquals(1, CompareToBuilder.reflectionCompare(new TestClass(1), new TestClass(0)));
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(new TestClass(1), "string"));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new TestClass(1)));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new TestClass(1), null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendBooleanArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true, true});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1, 3});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a', 'c'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new double[]{1.0, 2.0}, new double[]{1.0, 3.0});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f, 2.0f}, new float[]{1.0f, 3.0f});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{1, 2}, new int[]{1, 3});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new long[]{1L, 2L}, new long[]{1L, 3L});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortArray() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new short[]{1, 2}, new short[]{1, 3});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayWithComparator() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new String[]{"a", "b"}, new String[]{"a", "c"}, Collections.reverseOrder());
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendSuper() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.appendSuper(-1);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testReflectionCompareWithNullLhs() {
            Object obj2 = new Object();
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, obj2));
        }

    @Test
        void testReflectionCompareWithNullRhs() {
            Object obj1 = new Object();
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(obj1, null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBoolean() {
            assertEquals(0, new CompareToBuilder().append(true, true).toComparison());
            assertEquals(-1, new CompareToBuilder().append(false, true).toComparison());
            assertEquals(1, new CompareToBuilder().append(true, false).toComparison());
        }

    
    @Test
        public void testAppendChar() {
            assertEquals(0, new CompareToBuilder().append('a', 'a').toComparison());
            assertEquals(-1, new CompareToBuilder().append('b', 'c').toComparison());
            assertEquals(1, new CompareToBuilder().append('d', 'c').toComparison());
        }

    @Test
        public void testAppendDouble() {
            assertEquals(0, new CompareToBuilder().append(5.0, 5.0).toComparison());
            assertEquals(-1, new CompareToBuilder().append(3.0, 5.0).toComparison());
            assertEquals(1, new CompareToBuilder().append(7.0, 5.0).toComparison());
        }

    @Test
        public void testAppendFloat() {
            assertEquals(0, new CompareToBuilder().append(5.0f, 5.0f).toComparison());
            assertEquals(-1, new CompareToBuilder().append(3.0f, 5.0f).toComparison());
            assertEquals(1, new CompareToBuilder().append(7.0f, 5.0f).toComparison());
        }

    @Test
        public void testAppendInt() {
            assertEquals(0, new CompareToBuilder().append(5, 5).toComparison());
            assertEquals(-1, new CompareToBuilder().append(3, 5).toComparison());
            assertEquals(1, new CompareToBuilder().append(7, 5).toComparison());
        }

    @Test
        public void testAppendLong() {
            assertEquals(0, new CompareToBuilder().append(5L, 5L).toComparison());
            assertEquals(-1, new CompareToBuilder().append(3L, 5L).toComparison());
            assertEquals(1, new CompareToBuilder().append(7L, 5L).toComparison());
        }

    
    @Test
        public void testAppendObjectWithComparator() {
            Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
            assertEquals(0, new CompareToBuilder().append("abc", "def", lengthComparator).toComparison());
            assertEquals(-1, new CompareToBuilder().append("ab", "def", lengthComparator).toComparison());
            assertEquals(1, new CompareToBuilder().append("abcd", "def", lengthComparator).toComparison());
        }

    
    @Test
        public void testAppendSuper_2() {
            assertEquals(0, new CompareToBuilder().appendSuper(0).toComparison());
            assertEquals(-1, new CompareToBuilder().appendSuper(-1).toComparison());
            assertEquals(1, new CompareToBuilder().appendSuper(1).toComparison());
        }

    @Test
        public void testReflectionCompareWithClassCastException() {
            assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare("string", 123));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendObjectWithNullComparator() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append("test", "test");
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendObjectWithCustomComparator() {
            final Comparator<String> customComparator = Collections.reverseOrder();
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append("apple", "banana", customComparator);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayWithNullComparator() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new String[]{"a", "b"}, new String[]{"a", "b"});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayWithCustomComparator() {
            final Comparator<String> customComparator = Collections.reverseOrder();
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new String[]{"apple", "banana"}, new String[]{"banana", "apple"}, customComparator);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendSuperWithNonZeroValue() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.appendSuper(5);
            assertEquals(5, builder.toComparison());
        }

    @Test
        void testBuildWithNonZeroComparison() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(Integer.valueOf(-1), builder.build());
        }

    @Test
        void testAppendWithComparableObjects() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append("apple", "banana");
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendWithNonComparableObjectsThrowsClassCastException() {
            final CompareToBuilder builder = new CompareToBuilder();
            assertThrows(ClassCastException.class, () -> builder.append(new Object(), "test"));
        }
}
