package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

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
        
        // Act & Assert
        assertEquals(0, builder.append(new boolean[]{true}, new boolean[]{true}).toComparison());
        assertEquals(-1, builder.append(new boolean[]{false}, new boolean[]{true}).toComparison());
        assertEquals(1, builder.append(new boolean[]{true}, new boolean[]{false}).toComparison());
        assertEquals(-1, builder.append(null, new boolean[]{true}).toComparison());
        assertEquals(1, builder.append(new boolean[]{true}, null).toComparison());
        assertEquals(-1, builder.append(new boolean[]{true}, new boolean[]{}).toComparison());
        assertEquals(1, builder.append(new boolean[]{}, new boolean[]{true}).toComparison());
    }

    @Test
    public void testAppendByte() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append((byte) 1, (byte) 1).toComparison());
        assertEquals(-1, builder.append((byte) 0, (byte) 1).toComparison());
        assertEquals(1, builder.append((byte) 1, (byte) 0).toComparison());
    }

    @Test
    public void testAppendByteArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new byte[]{1}, new byte[]{1}).toComparison());
        assertEquals(-1, builder.append(new byte[]{0}, new byte[]{1}).toComparison());
        assertEquals(1, builder.append(new byte[]{1}, new byte[]{0}).toComparison());
        assertEquals(-1, builder.append(null, new byte[]{1}).toComparison());
        assertEquals(1, builder.append(new byte[]{1}, null).toComparison());
        assertEquals(-1, builder.append(new byte[]{1}, new byte[]{}).toComparison());
        assertEquals(1, builder.append(new byte[]{}, new byte[]{1}).toComparison());
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
        
        // Act & Assert
        assertEquals(0, builder.append(new char[]{'a'}, new char[]{'a'}).toComparison());
        assertEquals(-1, builder.append(new char[]{'a'}, new char[]{'b'}).toComparison());
        assertEquals(1, builder.append(new char[]{'b'}, new char[]{'a'}).toComparison());
        assertEquals(-1, builder.append(null, new char[]{'a'}).toComparison());
        assertEquals(1, builder.append(new char[]{'a'}, null).toComparison());
        assertEquals(-1, builder.append(new char[]{'a'}, new char[]{}).toComparison());
        assertEquals(1, builder.append(new char[]{}, new char[]{'a'}).toComparison());
    }

    @Test
    public void testAppendDouble() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(1.0, 1.0).toComparison());
        assertEquals(-1, builder.append(0.0, 1.0).toComparison());
        assertEquals(1, builder.append(1.0, 0.0).toComparison());
    }

    @Test
    public void testAppendDoubleArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new double[]{1.0}, new double[]{1.0}).toComparison());
        assertEquals(-1, builder.append(new double[]{0.0}, new double[]{1.0}).toComparison());
        assertEquals(1, builder.append(new double[]{1.0}, new double[]{0.0}).toComparison());
        assertEquals(-1, builder.append(null, new double[]{1.0}).toComparison());
        assertEquals(1, builder.append(new double[]{1.0}, null).toComparison());
        assertEquals(-1, builder.append(new double[]{1.0}, new double[]{}).toComparison());
        assertEquals(1, builder.append(new double[]{}, new double[]{1.0}).toComparison());
    }

    @Test
    public void testAppendFloat() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(1.0f, 1.0f).toComparison());
        assertEquals(-1, builder.append(0.0f, 1.0f).toComparison());
        assertEquals(1, builder.append(1.0f, 0.0f).toComparison());
    }

    @Test
    public void testAppendFloatArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new float[]{1.0f}, new float[]{1.0f}).toComparison());
        assertEquals(-1, builder.append(new float[]{0.0f}, new float[]{1.0f}).toComparison());
        assertEquals(1, builder.append(new float[]{1.0f}, new float[]{0.0f}).toComparison());
        assertEquals(-1, builder.append(null, new float[]{1.0f}).toComparison());
        assertEquals(1, builder.append(new float[]{1.0f}, null).toComparison());
        assertEquals(-1, builder.append(new float[]{1.0f}, new float[]{}).toComparison());
        assertEquals(1, builder.append(new float[]{}, new float[]{1.0f}).toComparison());
    }

    @Test
    public void testAppendInt() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(1, 1).toComparison());
        assertEquals(-1, builder.append(0, 1).toComparison());
        assertEquals(1, builder.append(1, 0).toComparison());
    }

    @Test
    public void testAppendIntArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new int[]{1}, new int[]{1}).toComparison());
        assertEquals(-1, builder.append(new int[]{0}, new int[]{1}).toComparison());
        assertEquals(1, builder.append(new int[]{1}, new int[]{0}).toComparison());
        assertEquals(-1, builder.append(null, new int[]{1}).toComparison());
        assertEquals(1, builder.append(new int[]{1}, null).toComparison());
        assertEquals(-1, builder.append(new int[]{1}, new int[]{}).toComparison());
        assertEquals(1, builder.append(new int[]{}, new int[]{1}).toComparison());
    }

    @Test
    public void testAppendLong() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(1L, 1L).toComparison());
        assertEquals(-1, builder.append(0L, 1L).toComparison());
        assertEquals(1, builder.append(1L, 0L).toComparison());
    }

    @Test
    public void testAppendLongArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new long[]{1L}, new long[]{1L}).toComparison());
        assertEquals(-1, builder.append(new long[]{0L}, new long[]{1L}).toComparison());
        assertEquals(1, builder.append(new long[]{1L}, new long[]{0L}).toComparison());
        assertEquals(-1, builder.append(null, new long[]{1L}).toComparison());
        assertEquals(1, builder.append(new long[]{1L}, null).toComparison());
        assertEquals(-1, builder.append(new long[]{1L}, new long[]{}).toComparison());
        assertEquals(1, builder.append(new long[]{}, new long[]{1L}).toComparison());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append("a", "a").toComparison());
        assertEquals(-1, builder.append("a", "b").toComparison());
        assertEquals(1, builder.append("b", "a").toComparison());
        assertEquals(-1, builder.append(null, "a").toComparison());
        assertEquals(1, builder.append("a", null).toComparison());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new String[]{"a"}, new String[]{"a"}).toComparison());
        assertEquals(-1, builder.append(new String[]{"a"}, new String[]{"b"}).toComparison());
        assertEquals(1, builder.append(new String[]{"b"}, new String[]{"a"}).toComparison());
        assertEquals(-1, builder.append(null, new String[]{"a"}).toComparison());
        assertEquals(1, builder.append(new String[]{"a"}, null).toComparison());
        assertEquals(-1, builder.append(new String[]{"a"}, new String[]{}).toComparison());
        assertEquals(1, builder.append(new String[]{}, new String[]{"a"}).toComparison());
    }

    @Test
    public void testAppendShort() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append((short) 1, (short) 1).toComparison());
        assertEquals(-1, builder.append((short) 0, (short) 1).toComparison());
        assertEquals(1, builder.append((short) 1, (short) 0).toComparison());
    }

    @Test
    public void testAppendShortArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new short[]{1}, new short[]{1}).toComparison());
        assertEquals(-1, builder.append(new short[]{0}, new short[]{1}).toComparison());
        assertEquals(1, builder.append(new short[]{1}, new short[]{0}).toComparison());
        assertEquals(-1, builder.append(null, new short[]{1}).toComparison());
        assertEquals(1, builder.append(new short[]{1}, null).toComparison());
        assertEquals(-1, builder.append(new short[]{1}, new short[]{}).toComparison());
        assertEquals(1, builder.append(new short[]{}, new short[]{1}).toComparison());
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
}
