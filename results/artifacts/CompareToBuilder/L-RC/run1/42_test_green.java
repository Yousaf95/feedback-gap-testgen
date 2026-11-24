package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CompareToBuilder_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReflectionCompareSameObject() {
        // Arrange
        String lhs = "test";
        String rhs = "test";

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    
    @Test
    public void testReflectionCompareNullLhs() {
        // Arrange
        String lhs = null;
        String rhs = "test";

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareNullRhs() {
        // Arrange
        String lhs = "test";
        String rhs = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareDifferentClasses() {
        // Arrange
        String lhs = "test";
        Integer rhs = 123;

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
        void testAppendBooleanSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, true);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanDifferentFirstTrue() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, false);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanDifferentSecondTrue() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(false, true);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true, false});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true}, new boolean[]{true, false});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new boolean[]{true, false});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 1);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteDifferentFirstGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 2, (byte) 1);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteDifferentSecondGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1, 2});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{1, 2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new byte[]{1, 2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'a');
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharDifferentFirstGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('b', 'a');
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharDifferentSecondGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a', 'b'});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{'a', 'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a'});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new char[]{'a', 'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, null);
            assertEquals(1, builder.toComparison());
        }

    
    @Test
        void testReflectionCompareWithExcludeFields() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "transientValue"));
        }

    @Test
        void testReflectionCompareWithExcludeFieldsCollection() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("transientValue")));
        }

    @Test
        void testReflectionCompareWithTransients() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2, true));
        }

    @Test
        void testReflectionCompareWithReflectUpToClass() {
            class Parent {
                private int value;
    
                public Parent(int value) {
                    this.value = value;
                }
            }
    
            class Child extends Parent implements Comparable<Child> {
                private int anotherValue;
    
                public Child(int value, int anotherValue) {
                    super(value);
                    this.anotherValue = anotherValue;
                }
    
                @Override
                public int compareTo(Child other) {
                    return new CompareToBuilder()
                        .appendSuper(super.hashCode())
                        .append(this.anotherValue, other.anotherValue)
                        .toComparison();
                }
            }
    
            Child child1 = new Child(10, 20);
            Child child2 = new Child(10, 30);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(child1, child2, false, Parent.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBooleanArrayDifferentLengthSecondShorter_2() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true};
            assertEquals(1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendByteArrayFirstNull_2() {
            byte[] lhs = null;
            byte[] rhs = {10};
            assertEquals(-1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendCharArraySecondNull_2() {
            char[] lhs = {'a'};
            char[] rhs = null;
            assertEquals(1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleArraySame() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0, 2.0};
            assertEquals(0, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatArrayDifferentLengthFirstShorter() {
            float[] lhs = {1.0f};
            float[] rhs = {1.0f, 2.0f};
            assertEquals(-1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatArraySecondNull() {
            float[] lhs = {1.0f};
            float[] rhs = null;
            assertEquals(1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleAlreadyCompared() {
            double[] lhs = {1.0};
            double[] rhs = {2.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatAlreadyCompared() {
            float[] lhs = {1.0f};
            float[] rhs = {2.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleArrayAlreadyCompared() {
            double[] lhs = {1.0};
            double[] rhs = {2.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatArrayAlreadyCompared() {
            float[] lhs = {1.0f};
            float[] rhs = {2.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleArrayDifferentLengthSecondShorter() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0};
            assertEquals(1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatArrayFirstNull() {
            float[] lhs = null;
            float[] rhs = {1.0f};
            assertEquals(-1, new CompareToBuilder().append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleAlreadyComparedWithDifferentValues() {
            double[] lhs = {2.0};
            double[] rhs = {1.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatAlreadyComparedWithDifferentValues() {
            float[] lhs = {2.0f};
            float[] rhs = {1.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendDoubleArrayAlreadyComparedWithDifferentValues() {
            double[] lhs = {2.0};
            double[] rhs = {1.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    @Test
        public void testAppendFloatArrayAlreadyComparedWithDifferentValues() {
            float[] lhs = {2.0f};
            float[] rhs = {1.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2); // Set comparison to -1
            assertEquals(-1, builder.append(lhs, rhs).toComparison());
        }

    
    @Test
        void testAppendByteArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{2});
            assertSame(builder, builder.append(new byte[]{3}, new byte[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append('a', 'b');
            assertSame(builder, builder.append('c', 'd'));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new double[]{1.0}, new double[]{2.0});
            assertSame(builder, builder.append(new double[]{3.0}, new double[]{4.0}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(1.0f, 2.0f);
            assertSame(builder, builder.append(3.0f, 4.0f));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(new int[]{1}, new int[]{2});
            assertSame(builder, builder.append(new int[]{3}, new int[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(1L, 2L);
            assertSame(builder, builder.append(3L, 4L));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append((short) 1, (short) 2);
            assertSame(builder, builder.append((short) 3, (short) 4));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(new Object[]{"a"}, new Object[]{"b"});
            assertSame(builder, builder.append(new Object[]{"c"}, new Object[]{"d"}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySecondNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new boolean[]{true}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayFirstNull_2_2() {
            CompareToBuilder builder = new CompareToBuilder().append(null, new byte[]{1});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySecondNull_2_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArrayFirstNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(null, new double[]{1.0});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArraySecondNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new float[]{1.0f}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendIntArrayFirstNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(null, new int[]{1});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArraySecondNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new long[]{1L}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendShortArrayFirstNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(null, new short[]{1});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArraySecondNull_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new Object[]{"a"}, null);
            assertEquals(1, builder.toComparison());
        }

    
    @Test
        void testAppendByteAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append((byte) 1, (byte) 2);
            assertSame(builder, builder.append((byte) 3, (byte) 4));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append('a', 'b');
            assertSame(builder, builder.append('c', 'd'));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(1.0, 2.0);
            assertSame(builder, builder.append(3.0, 4.0));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(1.0f, 2.0f);
            assertSame(builder, builder.append(3.0f, 4.0f));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(1, 2);
            assertSame(builder, builder.append(3, 4));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(1L, 2L);
            assertSame(builder, builder.append(3L, 4L));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append((short) 1, (short) 2);
            assertSame(builder, builder.append((short) 3, (short) 4));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append("a", "b");
            assertSame(builder, builder.append("c", "d"));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testReflectionCompareExcludeFieldsCollection_2() {
            class TestClass {
                public int field1;
                public transient int field2;
    
                public TestClass(int field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singleton("field2")));
        }

    
    
    @Test
        void testAppendFloatAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(1.0f, 2.0f);
            assertSame(builder, builder.append(3.0f, 4.0f));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new float[]{1.0f}, new float[]{2.0f});
            assertSame(builder, builder.append(new float[]{3.0f}, new float[]{4.0f}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append(new int[]{1}, new int[]{2});
            assertSame(builder, builder.append(new int[]{3}, new int[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append(new long[]{1L}, new long[]{2L});
            assertSame(builder, builder.append(new long[]{3L}, new long[]{4L}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append(new short[]{1}, new short[]{2});
            assertSame(builder, builder.append(new short[]{3}, new short[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder().append(new Object[]{"a"}, new Object[]{"b"});
            assertSame(builder, builder.append(new Object[]{"c"}, new Object[]{"d"}));
            assertEquals(-1, builder.toComparison());
        }

    
    @Test
        void testAppendByteArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{2});
            assertSame(builder, builder.append(new byte[]{3}, new byte[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, new char[]{'b'});
            assertSame(builder, builder.append(new char[]{'c'}, new char[]{'d'}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new double[]{1.0}, new double[]{2.0});
            assertSame(builder, builder.append(new double[]{3.0}, new double[]{4.0}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArrayAlreadyCompared_3() {
            CompareToBuilder builder = new CompareToBuilder().append(new float[]{1.0f}, new float[]{2.0f});
            assertSame(builder, builder.append(new float[]{3.0f}, new float[]{4.0f}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new int[]{1}, new int[]{2});
            assertSame(builder, builder.append(new int[]{3}, new int[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new long[]{1L}, new long[]{2L});
            assertSame(builder, builder.append(new long[]{3L}, new long[]{4L}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new short[]{1}, new short[]{2});
            assertSame(builder, builder.append(new short[]{3}, new short[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayAlreadyCompared_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new Object[]{"a"}, new Object[]{"b"});
            assertSame(builder, builder.append(new Object[]{"c"}, new Object[]{"d"}));
            assertEquals(-1, builder.toComparison());
        }

    
    @Test
        void testAppendByteArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{2});
            assertSame(builder, builder.append(new byte[]{3}, new byte[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, new char[]{'b'});
            assertSame(builder, builder.append(new char[]{'c'}, new char[]{'d'}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArrayAlreadyComparedWithDifferentValues_3() {
            CompareToBuilder builder = new CompareToBuilder().append(new double[]{1.0}, new double[]{2.0});
            assertSame(builder, builder.append(new double[]{3.0}, new double[]{4.0}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendFloatArrayAlreadyComparedWithDifferentValues_3() {
            CompareToBuilder builder = new CompareToBuilder().append(new float[]{1.0f}, new float[]{2.0f});
            assertSame(builder, builder.append(new float[]{3.0f}, new float[]{4.0f}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendIntArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new int[]{1}, new int[]{2});
            assertSame(builder, builder.append(new int[]{3}, new int[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendLongArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new long[]{1L}, new long[]{2L});
            assertSame(builder, builder.append(new long[]{3L}, new long[]{4L}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendShortArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new short[]{1}, new short[]{2});
            assertSame(builder, builder.append(new short[]{3}, new short[]{4}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayAlreadyComparedWithDifferentValues_2() {
            CompareToBuilder builder = new CompareToBuilder().append(new Object[]{"a"}, new Object[]{"b"});
            assertSame(builder, builder.append(new Object[]{"c"}, new Object[]{"d"}));
            assertEquals(-1, builder.toComparison());
        }
}
