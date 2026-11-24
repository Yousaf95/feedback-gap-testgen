package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

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
        void testAppendBooleanTrueFalse() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(true, false));
            assertEquals(1, builder.toComparison());
        }

    
    
    
    
    
    
    
    
    @Test
        void testReflectionCompareWithSuperclass() {
            class Parent {
                int value;
    
                Parent(int value) {
                    this.value = value;
                }
            }
    
            class Child extends Parent {
                int anotherValue;
    
                Child(int value, int anotherValue) {
                    super(value);
                    this.anotherValue = anotherValue;
                }
            }
    
            Child child1 = new Child(1, 2);
            Child child2 = new Child(1, 3);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(child1, child2, true, Parent.class));
        }

    @Test
        void testReflectionCompareWithExcludeFields() {
            class TestClass {
                int value;
                transient int transientValue;
    
                TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "transientValue"));
        }

    @Test
        void testReflectionCompareWithExcludeFieldsCollection() {
            class TestClass {
                int value;
                transient int transientValue;
    
                TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singleton("transientValue")));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendBooleanWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(true, false));
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new boolean[]{true}, new boolean[]{false}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new byte[]{1}, new byte[]{2}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new char[]{'a'}, new char[]{'b'}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendDoubleArrayWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new double[]{1.0}, new double[]{2.0}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append("a", "b"));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendObjectArrayWithNonZeroComparison() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new Object[]{"a"}, new Object[]{"b"}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testReflectionCompareExcludeFieldsWithTransientField() throws Exception {
            class TestClass {
                transient int transientField;
                int nonTransientField;
    
                public TestClass(int transientField, int nonTransientField) {
                    this.transientField = transientField;
                    this.nonTransientField = nonTransientField;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 4);
    
            assertEquals(-2, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "transientField"));
        }

    @Test
        void testReflectionCompareExcludeFieldsWithStaticField() throws Exception {
            class TestClass {
                static int staticField;
                int nonStaticField;
    
                public TestClass(int staticField, int nonStaticField) {
                    this.staticField = staticField;
                    this.nonStaticField = nonStaticField;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 4);
    
            assertEquals(-2, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "staticField"));
        }

    @Test
        void testReflectionCompareExcludeFieldsWithDollarField() throws Exception {
            class TestClass {
                int $dollarField;
                int nonDollarField;
    
                public TestClass(int dollarField, int nonDollarField) {
                    this.$dollarField = dollarField;
                    this.nonDollarField = nonDollarField;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 4);
    
            assertEquals(-2, CompareToBuilder.reflectionCompare(obj1, obj2, false, null));
        }

    @Test
        void testReflectionCompareExcludeFieldsWithExcludedField() throws Exception {
            class TestClass {
                int excludedField;
                int nonExcludedField;
    
                public TestClass(int excludedField, int nonExcludedField) {
                    this.excludedField = excludedField;
                    this.nonExcludedField = nonExcludedField;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 4);
    
            assertEquals(-2, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "excludedField"));
        }

    }
