package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals("booleanArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
    }

    @Test
    public void testAppendByte() {
        diffBuilder.append("byteField", (byte) 1, (byte) 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Byte> diff = (Diff<Byte>) diffs.get(0);
        assertEquals("byteField", diff.getFieldName());
        assertEquals((byte) 1, diff.getLeft());
        assertEquals((byte) 2, diff.getRight());
    }

    @Test
    public void testAppendByteArray() {
        byte[] lhs = {1, 2};
        byte[] rhs = {2, 3};
        diffBuilder.append("byteArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Byte[]> diff = (Diff<Byte[]>) diffs.get(0);
        assertEquals("byteArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
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
        assertEquals("charArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
    }

    @Test
    public void testAppendDouble() {
        diffBuilder.append("doubleField", 1.1, 2.2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Double> diff = (Diff<Double>) diffs.get(0);
        assertEquals("doubleField", diff.getFieldName());
        assertEquals(1.1, diff.getLeft(), 0.001);
        assertEquals(2.2, diff.getRight(), 0.001);
    }

    @Test
    public void testAppendDoubleArray() {
        double[] lhs = {1.1, 2.2};
        double[] rhs = {2.2, 3.3};
        diffBuilder.append("doubleArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Double[]> diff = (Diff<Double[]>) diffs.get(0);
        assertEquals("doubleArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
    }

    @Test
    public void testAppendFloat() {
        diffBuilder.append("floatField", 1.1f, 2.2f);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Float> diff = (Diff<Float>) diffs.get(0);
        assertEquals("floatField", diff.getFieldName());
        assertEquals(1.1f, diff.getLeft(), 0.001);
        assertEquals(2.2f, diff.getRight(), 0.001);
    }

    @Test
    public void testAppendFloatArray() {
        float[] lhs = {1.1f, 2.2f};
        float[] rhs = {2.2f, 3.3f};
        diffBuilder.append("floatArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Float[]> diff = (Diff<Float[]>) diffs.get(0);
        assertEquals("floatArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
    }

    @Test
    public void testAppendInt() {
        diffBuilder.append("intField", 1, 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Integer> diff = (Diff<Integer>) diffs.get(0);
        assertEquals("intField", diff.getFieldName());
        assertEquals(1, diff.getLeft());
        assertEquals(2, diff.getRight());
    }

    @Test
    public void testAppendIntArray() {
        int[] lhs = {1, 2};
        int[] rhs = {2, 3};
        diffBuilder.append("intArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Integer[]> diff = (Diff<Integer[]>) diffs.get(0);
        assertEquals("intArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
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
        assertEquals("longArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
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
        assertEquals("objectArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(lhs, diff.getLeft()));
        assertTrue(Arrays.equals(rhs, diff.getRight()));
    }

    @Test
    public void testAppendShort() {
        diffBuilder.append("shortField", (short) 1, (short) 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short> diff = (Diff<Short>) diffs.get(0);
        assertEquals("shortField", diff.getFieldName());
        assertEquals((short) 1, diff.getLeft());
        assertEquals((short) 2, diff.getRight());
    }

    @Test
    public void testAppendShortArray() {
        short[] lhs = {1, 2};
        short[] rhs = {2, 3};
        diffBuilder.append("shortArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short[]> diff = (Diff<Short[]>) diffs.get(0);
        assertEquals("shortArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(ArrayUtils.toObject(lhs), diff.getLeft()));
        assertTrue(Arrays.equals(ArrayUtils.toObject(rhs), diff.getRight()));
    }

    @Test
    public void testAppendNull() {
        diffBuilder.append("nullField", null, "rightValue");
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object> diff = (Diff<Object>) diffs.get(0);
        assertEquals("nullField", diff.getFieldName());
        assertNull(diff.getLeft());
        assertSame("rightValue", diff.getRight());
    }

    @Test
    public void testAppendEmptyArray() {
        Object[] lhs = {};
        Object[] rhs = {new Object()};
        diffBuilder.append("emptyArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object[]> diff = (Diff<Object[]>) diffs.get(0);
        assertEquals("emptyArrayField", diff.getFieldName());
        assertTrue(Arrays.equals(lhs, diff.getLeft()));
        assertTrue(Arrays.equals(rhs, diff.getRight()));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
