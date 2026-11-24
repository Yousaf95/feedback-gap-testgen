package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class DiffBuilder_GenTest {

    private DiffBuilder<String> diffBuilder;

    @BeforeEach
    public void setUp() {
        diffBuilder = new DiffBuilder<>("leftValue", "rightValue", ToStringStyle.DEFAULT_STYLE);
    }

    @Test
    public void testAppendBoolean() {
        diffBuilder.append("booleanField", true, false);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Boolean> diff = (Diff<Boolean>) diffs.get(0);
        assertEquals("booleanField", diff.getFieldName());
        assertTrue(diff.getLeft());
        assertFalse(diff.getRight());
    }

    @Test
    public void testAppendBooleanArray() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {false, true};
        diffBuilder.append("booleanArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Boolean[]> diff = (Diff<Boolean[]>) diffs.get(0);
        assertArrayEquals(new Boolean[]{true, false}, diff.getLeft());
        assertArrayEquals(new Boolean[]{false, true}, diff.getRight());
    }

    @Test
    public void testAppendByte() {
        diffBuilder.append("byteField", (byte) 10, (byte) 20);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Byte> diff = (Diff<Byte>) diffs.get(0);
        assertEquals("byteField", diff.getFieldName());
        assertEquals((byte) 10, diff.getLeft());
        assertEquals((byte) 20, diff.getRight());
    }

    @Test
    public void testAppendByteArray() {
        byte[] lhs = {10, 20};
        byte[] rhs = {20, 30};
        diffBuilder.append("byteArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Byte[]> diff = (Diff<Byte[]>) diffs.get(0);
        assertArrayEquals(new Byte[]{10, 20}, diff.getLeft());
        assertArrayEquals(new Byte[]{20, 30}, diff.getRight());
    }

    @Test
    public void testAppendChar() {
        diffBuilder.append("charField", 'a', 'b');
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Character> diff = (Diff<Character>) diffs.get(0);
        assertEquals("charField", diff.getFieldName());
        assertEquals('a', diff.getLeft());
        assertEquals('b', diff.getRight());
    }

    @Test
    public void testAppendCharArray() {
        char[] lhs = {'a', 'b'};
        char[] rhs = {'b', 'c'};
        diffBuilder.append("charArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Character[]> diff = (Diff<Character[]>) diffs.get(0);
        assertArrayEquals(new Character[]{new Character('a'), new Character('b')}, diff.getLeft());
        assertArrayEquals(new Character[]{new Character('b'), new Character('c')}, diff.getRight());
    }

    @Test
    public void testAppendDouble() {
        diffBuilder.append("doubleField", 1.0, 2.0);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Double> diff = (Diff<Double>) diffs.get(0);
        assertEquals("doubleField", diff.getFieldName());
        assertEquals(1.0, diff.getLeft(), 0.0);
        assertEquals(2.0, diff.getRight(), 0.0);
    }

    
    @Test
    public void testAppendFloat() {
        diffBuilder.append("floatField", (float) 1.0, (float) 2.0);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Float> diff = (Diff<Float>) diffs.get(0);
        assertEquals("floatField", diff.getFieldName());
        assertEquals((float) 1.0, diff.getLeft(), 0.0);
        assertEquals((float) 2.0, diff.getRight(), 0.0);
    }

    
    @Test
    public void testAppendInt() {
        diffBuilder.append("intField", 10, 20);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Integer> diff = (Diff<Integer>) diffs.get(0);
        assertEquals("intField", diff.getFieldName());
        assertEquals(10, diff.getLeft());
        assertEquals(20, diff.getRight());
    }

    @Test
    public void testAppendIntArray() {
        int[] lhs = {10, 20};
        int[] rhs = {20, 30};
        diffBuilder.append("intArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Integer[]> diff = (Diff<Integer[]>) diffs.get(0);
        assertArrayEquals(new Integer[]{10, 20}, diff.getLeft());
        assertArrayEquals(new Integer[]{20, 30}, diff.getRight());
    }

    @Test
    public void testAppendLong() {
        diffBuilder.append("longField", 1L, 2L);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Long> diff = (Diff<Long>) diffs.get(0);
        assertEquals("longField", diff.getFieldName());
        assertEquals(1L, diff.getLeft());
        assertEquals(2L, diff.getRight());
    }

    @Test
    public void testAppendLongArray() {
        long[] lhs = {1L, 2L};
        long[] rhs = {2L, 3L};
        diffBuilder.append("longArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Long[]> diff = (Diff<Long[]>) diffs.get(0);
        assertArrayEquals(new Long[]{1L, 2L}, diff.getLeft());
        assertArrayEquals(new Long[]{2L, 3L}, diff.getRight());
    }

    @Test
    public void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        diffBuilder.append("objectField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object> diff = (Diff<Object>) diffs.get(0);
        assertEquals("objectField", diff.getFieldName());
        assertSame(lhs, diff.getLeft());
        assertSame(rhs, diff.getRight());
    }

    @Test
    public void testAppendObjectArray() {
        Object[] lhs = {new Object(), new Object()};
        Object[] rhs = {new Object(), new Object()};
        diffBuilder.append("objectArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object[]> diff = (Diff<Object[]>) diffs.get(0);
        assertSame(lhs, diff.getLeft());
        assertSame(rhs, diff.getRight());
    }

    @Test
    public void testAppendShort() {
        diffBuilder.append("shortField", (short) 10, (short) 20);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short> diff = (Diff<Short>) diffs.get(0);
        assertEquals("shortField", diff.getFieldName());
        assertEquals((short) 10, diff.getLeft());
        assertEquals((short) 20, diff.getRight());
    }

    @Test
    public void testAppendShortArray() {
        short[] lhs = {10, 20};
        short[] rhs = {20, 30};
        diffBuilder.append("shortArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short[]> diff = (Diff<Short[]>) diffs.get(0);
        assertArrayEquals(new Short[]{10, 20}, diff.getLeft());
        assertArrayEquals(new Short[]{20, 30}, diff.getRight());
    }

    @Test
    public void testAppendNull() {
        diffBuilder.append("nullField", null, "rightValue");
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object> diff = (Diff<Object>) diffs.get(0);
        assertNull(diff.getLeft());
        assertEquals("rightValue", diff.getRight());
    }

    
    @Test
    public void testAppendTriviallyEqualObjects() {
        diffBuilder.append("triviallyEqualField", "leftValue", "leftValue");
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }

    // ---- added by coverage-guided refinement ----
    @BeforeEach
        public void setUp_2() {
            diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
        }

    @Test
        public void testAppendBooleanTriviallyEqual() {
            boolean lhs = true;
            boolean rhs = true;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendBooleanNotEqual() {
            boolean lhs = true;
            boolean rhs = false;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendByteArrayTriviallyEqual() {
            byte[] lhs = {1, 2, 3};
            byte[] rhs = {1, 2, 3};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendByteArrayNotEqual() {
            byte[] lhs = {1, 2, 3};
            byte[] rhs = {4, 5, 6};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendCharTriviallyEqual() {
            char lhs = 'a';
            char rhs = 'a';
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendCharNotEqual() {
            char lhs = 'a';
            char rhs = 'b';
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendCharArrayTriviallyEqual() {
            char[] lhs = {'a', 'b', 'c'};
            char[] rhs = {'a', 'b', 'c'};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendCharArrayNotEqual() {
            char[] lhs = {'a', 'b', 'c'};
            char[] rhs = {'d', 'e', 'f'};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendDoubleTriviallyEqual() {
            double lhs = 1.0;
            double rhs = 1.0;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendDoubleNotEqual() {
            double lhs = 1.0;
            double rhs = 2.0;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendDoubleArrayTriviallyEqual() {
            double[] lhs = {1.0, 2.0, 3.0};
            double[] rhs = {1.0, 2.0, 3.0};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendDoubleArrayNotEqual() {
            double[] lhs = {1.0, 2.0, 3.0};
            double[] rhs = {4.0, 5.0, 6.0};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendFloatTriviallyEqual() {
            float lhs = 1.0f;
            float rhs = 1.0f;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendFloatNotEqual() {
            float lhs = 1.0f;
            float rhs = 2.0f;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendFloatArrayTriviallyEqual() {
            float[] lhs = {1.0f, 2.0f, 3.0f};
            float[] rhs = {1.0f, 2.0f, 3.0f};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendFloatArrayNotEqual() {
            float[] lhs = {1.0f, 2.0f, 3.0f};
            float[] rhs = {4.0f, 5.0f, 6.0f};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendIntTriviallyEqual() {
            int lhs = 1;
            int rhs = 1;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendIntNotEqual() {
            int lhs = 1;
            int rhs = 2;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendIntArrayTriviallyEqual() {
            int[] lhs = {1, 2, 3};
            int[] rhs = {1, 2, 3};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendIntArrayNotEqual() {
            int[] lhs = {1, 2, 3};
            int[] rhs = {4, 5, 6};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendLongTriviallyEqual() {
            long lhs = 1L;
            long rhs = 1L;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendLongNotEqual() {
            long lhs = 1L;
            long rhs = 2L;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendLongArrayTriviallyEqual() {
            long[] lhs = {1L, 2L, 3L};
            long[] rhs = {1L, 2L, 3L};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendLongArrayNotEqual() {
            long[] lhs = {1L, 2L, 3L};
            long[] rhs = {4L, 5L, 6L};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendObjectTriviallyEqual() {
            Object lhs = new Object();
            Object rhs = lhs;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendObjectNotEqual() {
            Object lhs = new Object();
            Object rhs = new Object();
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendObjectArrayTriviallyEqual() {
            Object[] lhs = {new Object(), new Object()};
            Object[] rhs = {new Object(), new Object()};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendObjectArrayNotEqual() {
            Object[] lhs = {new Object(), new Object()};
            Object[] rhs = {new Object(), new Object(), new Object()};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendShortTriviallyEqual() {
            short lhs = 1;
            short rhs = 1;
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendShortNotEqual() {
            short lhs = 1;
            short rhs = 2;
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendShortArrayTriviallyEqual() {
            short[] lhs = {1, 2, 3};
            short[] rhs = {1, 2, 3};
            diffBuilder.append("testField", lhs, rhs);
            assertTrue(diffBuilder.objectsTriviallyEqual);
        }

    @Test
        public void testAppendShortArrayNotEqual() {
            short[] lhs = {1, 2, 3};
            short[] rhs = {4, 5, 6};
            diffBuilder.append("testField", lhs, rhs);
            assertFalse(diffBuilder.objectsTriviallyEqual);
            assertEquals(1, diffBuilder.diffs.size());
        }

    @Test
        public void testAppendNull_2() {
            assertThrows(NullPointerException.class, () -> diffBuilder.append("testField", null, null));
        }
}
