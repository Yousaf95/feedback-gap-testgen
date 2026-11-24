package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

public class CompareToBuilder_GenTest {

    @Test
    public void testAppendBoolean() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append(true, true).toComparison());
        assertEquals(-1, builder.append(false, true).toComparison());
        assertEquals(1, builder.append(true, false).toComparison());
    }

    @Test
    public void testAppendBooleanArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, true};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendByte() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append((byte) 1, (byte) 1).toComparison());
        assertEquals(-1, builder.append((byte) 0, (byte) 1).toComparison());
        assertEquals(1, builder.append((byte) 2, (byte) 1).toComparison());
    }

    @Test
    public void testAppendByteArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        byte[] lhs = {1, 0};
        byte[] rhs = {1, 1};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendChar() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append('a', 'a').toComparison());
        assertEquals(-1, builder.append('a', 'b').toComparison());
        assertEquals(1, builder.append('b', 'a').toComparison());
    }

    @Test
    public void testAppendCharArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'c'};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendDouble() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append(1.0, 1.0).toComparison());
        assertEquals(-1, builder.append(0.0, 1.0).toComparison());
        assertEquals(1, builder.append(2.0, 1.0).toComparison());
    }

    @Test
    public void testAppendDoubleArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        double[] lhs = {1.0, 0.0};
        double[] rhs = {1.0, 1.0};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendFloat() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append(1.0f, 1.0f).toComparison());
        assertEquals(-1, builder.append(0.0f, 1.0f).toComparison());
        assertEquals(1, builder.append(2.0f, 1.0f).toComparison());
    }

    @Test
    public void testAppendFloatArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        float[] lhs = {1.0f, 0.0f};
        float[] rhs = {1.0f, 1.0f};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendInt() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append(1, 1).toComparison());
        assertEquals(-1, builder.append(0, 1).toComparison());
        assertEquals(1, builder.append(2, 1).toComparison());
    }

    @Test
    public void testAppendIntArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        int[] lhs = {1, 0};
        int[] rhs = {1, 1};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendLong() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append(1L, 1L).toComparison());
        assertEquals(-1, builder.append(0L, 1L).toComparison());
        assertEquals(1, builder.append(2L, 1L).toComparison());
    }

    @Test
    public void testAppendLongArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        long[] lhs = {1L, 0L};
        long[] rhs = {1L, 1L};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        String lhs = "a";
        String rhs = "b";

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        String[] lhs = {"a", "b"};
        String[] rhs = {"a", "c"};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendShort() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.append((short) 1, (short) 1).toComparison());
        assertEquals(-1, builder.append((short) 0, (short) 1).toComparison());
        assertEquals(1, builder.append((short) 2, (short) 1).toComparison());
    }

    @Test
    public void testAppendShortArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        short[] lhs = {1, 0};
        short[] rhs = {1, 1};

        // Act & Assert
        assertEquals(-1, builder.append(lhs, rhs).toComparison());
        assertEquals(0, builder.append(lhs, lhs).toComparison());
    }

    @Test
    public void testAppendSuper() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();

        // Act & Assert
        assertEquals(0, builder.appendSuper(0).toComparison());
        assertEquals(-1, builder.appendSuper(-1).toComparison());
        assertEquals(1, builder.appendSuper(1).toComparison());
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
    public void testReflectionCompareDifferentObjects() {
        // Arrange
        String lhs = "a";
        String rhs = "b";

        // Act & Assert
        assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs));
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
}
