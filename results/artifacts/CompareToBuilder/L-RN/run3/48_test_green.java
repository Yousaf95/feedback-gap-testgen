package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Comparator;

public class CompareToBuilder_GenTest {

    
    @Test
    public void testAppendBooleanArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, true};

        // Act & Assert
        assertEquals(0, builder.append(new boolean[]{}, new boolean[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendByteArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        byte[] lhs = {1, 0};
        byte[] rhs = {1, 1};

        // Act & Assert
        assertEquals(0, builder.append(new byte[]{}, new byte[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendCharArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'c'};

        // Act & Assert
        assertEquals(0, builder.append(new char[]{}, new char[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendDoubleArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        double[] lhs = {1.0, 0.0};
        double[] rhs = {1.0, 1.0};

        // Act & Assert
        assertEquals(0, builder.append(new double[]{}, new double[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendFloatArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        float[] lhs = {1.0f, 0.0f};
        float[] rhs = {1.0f, 1.0f};

        // Act & Assert
        assertEquals(0, builder.append(new float[]{}, new float[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendIntArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        int[] lhs = {1, 0};
        int[] rhs = {1, 1};

        // Act & Assert
        assertEquals(0, builder.append(new int[]{}, new int[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendLongArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        long[] lhs = {1L, 0L};
        long[] rhs = {1L, 1L};

        // Act & Assert
        assertEquals(0, builder.append(new long[]{}, new long[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        String lhs = "a";
        String rhs = "b";

        // Act & Assert
        assertEquals(0, builder.append("a", "a").toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        String[] lhs = {"a", "b"};
        String[] rhs = {"a", "c"};

        // Act & Assert
        assertEquals(0, builder.append(new String[]{}, new String[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendShortArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        short[] lhs = {1, 0};
        short[] rhs = {1, 1};

        // Act & Assert
        assertEquals(0, builder.append(new short[]{}, new short[]{}).toComparison());
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
    }

    
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
    public void testReflectionCompareSameObject() {
        // Arrange
        String lhs = "test";
        String rhs = "test";

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    
    @Test
    public void testReflectionCompareNullObjects() {
        // Arrange

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, "test"));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare("test", null));
    }

    @Test
    public void testReflectionCompareDifferentClasses() {
        // Arrange
        String lhs = "a";
        Integer rhs = 1;

        // Act & Assert
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareExcludeFields() {
        // Arrange
        class TestClass {
            private int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, "field2"));
    }

    @Test
    public void testReflectionCompareExcludeFieldsCollection() {
        // Arrange
        class TestClass {
            private int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, Collections.singletonList("field2")));
    }

    @Test
    public void testReflectionCompareTransients() {
        // Arrange
        class TestClass {
            private transient int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs, true));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendSuper() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(0, builder.toComparison());
    
            builder.appendSuper(-1);
            assertEquals(-1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.appendSuper(1);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendWithComparator() {
            Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
    
            CompareToBuilder builder = new CompareToBuilder();
            builder.append("short", "longer", lengthComparator);
            assertEquals(-1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append("longer", "short", lengthComparator);
            assertEquals(1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append("same", "size", lengthComparator);
            assertEquals(0, builder.toComparison());
        }

    
    @Test
        void testAppendWithArrayComparator() {
            Comparator<Object[]> arrayLengthComparator = (a, b) -> Integer.compare(a.length, b.length);
    
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new Object[]{1, 2}, new Object[]{1, 2, 3}, arrayLengthComparator);
            assertEquals(-1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append(new Object[]{1, 2, 3}, new Object[]{1, 2}, arrayLengthComparator);
            assertEquals(1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append(new Object[]{1, 2}, new Object[]{1, 2}, arrayLengthComparator);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testReflectionCompareWithReflectUpToClass() {
            class Parent {
                int parentField;
                Parent(int parentField) { this.parentField = parentField; }
            }
            class Child extends Parent {
                int childField;
                Child(int parentField, int childField) { super(parentField); this.childField = childField; }
            }
    
            Child lhs = new Child(1, 2);
            Child rhs = new Child(1, 3);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs, false, Parent.class));
        }

    @Test
        void testReflectionCompareWithExcludeFieldsCollection() {
            class TestClass {
                int field1;
                int field2;
                TestClass(int field1, int field2) { this.field1 = field1; this.field2 = field2; }
            }
    
            TestClass lhs = new TestClass(1, 2);
            TestClass rhs = new TestClass(1, 3);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, Collections.singleton("field2")));
        }

    @Test
        void testAppendWithNullArrays() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((Object[]) null, (Object[]) null);
            assertEquals(0, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append(new Object[]{1}, (Object[]) null);
            assertEquals(1, builder.toComparison());
    
            builder = new CompareToBuilder();
            builder.append((Object[]) null, new Object[]{1});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendWithDifferentArrayTypes() {
            CompareToBuilder builder = new CompareToBuilder();
            assertThrows(ClassCastException.class, () -> builder.append(new int[]{1}, new String[]{"1"}));
        }

    
    @Test
        public void testAppendWithComparatorCustomComparator() {
            final Comparator<String> customComparator = (s1, s2) -> s2.compareTo(s1);
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append("apple", "banana", customComparator);
            assertEquals(1, builder.toComparison());
        }

    
    
    @Test
        public void testAppendSuperNonZeroComparison() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            builder.appendSuper(-5);
            assertEquals(-1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendWithComparatorNullComparator() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append("test", "test", null);
            assertEquals(0, builder.toComparison());
        }

    
    @Test
        public void testAppendWithArrayComparatorNullComparator() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new String[]{"test"}, new String[]{"test"}, null);
            assertEquals(0, builder.toComparison());
        }

    
    @Test
        public void testAppendBooleanArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true}, new boolean[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendByteArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendCharArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new double[]{1.0}, new double[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendFloatArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f}, new float[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendIntArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{1}, new int[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendLongArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new long[]{1L}, new long[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendShortArrayDifferentLengths() {
            final CompareToBuilder builder = new CompareToBuilder();
            builder.append(new short[]{(short) 1}, new short[]{});
            assertEquals(1, builder.toComparison());
        }
}
