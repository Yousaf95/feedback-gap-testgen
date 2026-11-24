package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.builder.ToStringStyle.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiffBuilder_GenTest {

    private DiffBuilder<Object> diffBuilder;

    @BeforeEach
    public void setUp() {
        diffBuilder = new DiffBuilder<>(new Object(), new Object(), DEFAULT_STYLE);
    }

    @Test
    public void testAppendBoolean() {
        boolean lhs = true;
        boolean rhs = false;
        diffBuilder.append("booleanField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Boolean> diff = (Diff<Boolean>) diffs.get(0);
        assertEquals("booleanField", diff.getFieldName());
        assertTrue(diff.getLeft() == Boolean.valueOf(lhs));
        assertFalse(diff.getRight() == Boolean.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendByte() {
        byte lhs = 1;
        byte rhs = 2;
        diffBuilder.append("byteField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Byte> diff = (Diff<Byte>) diffs.get(0);
        assertEquals("byteField", diff.getFieldName());
        assertTrue(diff.getLeft() == Byte.valueOf(lhs));
        assertFalse(diff.getRight() == Byte.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendChar() {
        char lhs = 'a';
        char rhs = 'b';
        diffBuilder.append("charField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Character> diff = (Diff<Character>) diffs.get(0);
        assertEquals("charField", diff.getFieldName());
        assertTrue(diff.getLeft() == Character.valueOf(lhs));
        assertFalse(diff.getRight() == Character.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendDouble() {
        double lhs = 1.0;
        double rhs = 2.0;
        diffBuilder.append("doubleField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Double> diff = (Diff<Double>) diffs.get(0);
        assertEquals("doubleField", diff.getFieldName());
        assertTrue(diff.getLeft() == Double.valueOf(lhs));
        assertFalse(diff.getRight() == Double.valueOf(rhs));
    }

    @Test
    public void testAppendDoubleArray() {
        double[] lhs = {1.0, 2.0};
        double[] rhs = {2.0, 3.0};
        diffBuilder.append("doubleArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Double[]> diff = (Diff<Double[]>) diffs.get(0);
        assertEquals("doubleArrayField", diff.getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendFloat() {
        float lhs = 1.0f;
        float rhs = 2.0f;
        diffBuilder.append("floatField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Float> diff = (Diff<Float>) diffs.get(0);
        assertEquals("floatField", diff.getFieldName());
        assertTrue(diff.getLeft() == Float.valueOf(lhs));
        assertFalse(diff.getRight() == Float.valueOf(rhs));
    }

    @Test
    public void testAppendFloatArray() {
        float[] lhs = {1.0f, 2.0f};
        float[] rhs = {2.0f, 3.0f};
        diffBuilder.append("floatArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Float[]> diff = (Diff<Float[]>) diffs.get(0);
        assertEquals("floatArrayField", diff.getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendInt() {
        int lhs = 1;
        int rhs = 2;
        diffBuilder.append("intField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Integer> diff = (Diff<Integer>) diffs.get(0);
        assertEquals("intField", diff.getFieldName());
        assertTrue(diff.getLeft() == Integer.valueOf(lhs));
        assertFalse(diff.getRight() == Integer.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendLong() {
        long lhs = 1L;
        long rhs = 2L;
        diffBuilder.append("longField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Long> diff = (Diff<Long>) diffs.get(0);
        assertEquals("longField", diff.getFieldName());
        assertTrue(diff.getLeft() == Long.valueOf(lhs));
        assertFalse(diff.getRight() == Long.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
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
        assertTrue(diff.getLeft() == lhs);
        assertFalse(diff.getRight() == rhs);
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
    }

    @Test
    public void testAppendShort() {
        short lhs = 1;
        short rhs = 2;
        diffBuilder.append("shortField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short> diff = (Diff<Short>) diffs.get(0);
        assertEquals("shortField", diff.getFieldName());
        assertTrue(diff.getLeft() == Short.valueOf(lhs));
        assertFalse(diff.getRight() == Short.valueOf(rhs));
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
        assertArrayEquals(ArrayUtils.toObject(lhs), diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), diff.getRight());
    }

    @Test
    public void testAppendNull() {
        Object lhs = null;
        Object rhs = new Object();
        diffBuilder.append("nullField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object> diff = (Diff<Object>) diffs.get(0);
        assertEquals("nullField", diff.getFieldName());
        assertNull(diff.getLeft());
        assertFalse(diff.getRight() == rhs);
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
    }

    @Test
    public void testAppendTriviallyEqual() {
        Object lhs = new Object();
        Object rhs = lhs;
        diffBuilder.append("triviallyEqualField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }

    @Test
    public void testAppendNullTriviallyEqual() {
        Object lhs = null;
        Object rhs = null;
        diffBuilder.append("nullTriviallyEqualField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }

    @Test
    public void testAppendNullAndNonNull() {
        Object lhs = null;
        Object rhs = new Object();
        diffBuilder.append("nullAndNonNullField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object> diff = (Diff<Object>) diffs.get(0);
        assertEquals("nullAndNonNullField", diff.getFieldName());
        assertNull(diff.getLeft());
        assertFalse(diff.getRight() == rhs);
    }

    @Test
    public void testAppendEmptyString() {
        String lhs = "";
        String rhs = "test";
        diffBuilder.append("emptyStringField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<String> diff = (Diff<String>) diffs.get(0);
        assertEquals("emptyStringField", diff.getFieldName());
        assertTrue(diff.getLeft() == lhs);
        assertFalse(diff.getRight() == rhs);
    }

    @Test
    public void testAppendSameObject() {
        Object obj = new Object();
        diffBuilder.append("sameObjectField", obj, obj);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }
}
