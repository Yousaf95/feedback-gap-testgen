package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DiffBuilder_GenTest {

    private DiffBuilder<Object> diffBuilder;

    @BeforeEach
    public void setUp() {
        diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
    }

    @Test
    public void testAppendBoolean() {
        diffBuilder.append("booleanField", true, false);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Boolean>);
        assertEquals("booleanField", diffs.get(0).getFieldName());
        assertEquals(Boolean.valueOf(true), diffs.get(0).getLeft());
        assertEquals(Boolean.valueOf(false), diffs.get(0).getRight());
    }

    @Test
    public void testAppendBooleanArray() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {false, true};
        diffBuilder.append("booleanArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Boolean[]>);
        assertEquals("booleanArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Boolean[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Boolean[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendByte() {
        diffBuilder.append("byteField", (byte) 1, (byte) 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Byte>);
        assertEquals("byteField", diffs.get(0).getFieldName());
        assertEquals(Byte.valueOf((byte) 1), diffs.get(0).getLeft());
        assertEquals(Byte.valueOf((byte) 2), diffs.get(0).getRight());
    }

    @Test
    public void testAppendByteArray() {
        byte[] lhs = {1, 2};
        byte[] rhs = {2, 3};
        diffBuilder.append("byteArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Byte[]>);
        assertEquals("byteArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Byte[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Byte[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendChar() {
        diffBuilder.append("charField", 'a', 'b');
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Character>);
        assertEquals("charField", diffs.get(0).getFieldName());
        assertEquals(Character.valueOf('a'), diffs.get(0).getLeft());
        assertEquals(Character.valueOf('b'), diffs.get(0).getRight());
    }

    @Test
    public void testAppendCharArray() {
        char[] lhs = {'a', 'b'};
        char[] rhs = {'b', 'c'};
        diffBuilder.append("charArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Character[]>);
        assertEquals("charArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Character[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Character[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendDouble() {
        diffBuilder.append("doubleField", 1.1, 2.2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Double>);
        assertEquals("doubleField", diffs.get(0).getFieldName());
        assertEquals(Double.valueOf(1.1), diffs.get(0).getLeft());
        assertEquals(Double.valueOf(2.2), diffs.get(0).getRight());
    }

    @Test
    public void testAppendDoubleArray() {
        double[] lhs = {1.1, 2.2};
        double[] rhs = {2.2, 3.3};
        diffBuilder.append("doubleArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Double[]>);
        assertEquals("doubleArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Double[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Double[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendFloat() {
        diffBuilder.append("floatField", 1.1f, 2.2f);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Float>);
        assertEquals("floatField", diffs.get(0).getFieldName());
        assertEquals(Float.valueOf(1.1f), diffs.get(0).getLeft());
        assertEquals(Float.valueOf(2.2f), diffs.get(0).getRight());
    }

    @Test
    public void testAppendFloatArray() {
        float[] lhs = {1.1f, 2.2f};
        float[] rhs = {2.2f, 3.3f};
        diffBuilder.append("floatArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Float[]>);
        assertEquals("floatArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Float[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Float[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendInt() {
        diffBuilder.append("intField", 1, 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Integer>);
        assertEquals("intField", diffs.get(0).getFieldName());
        assertEquals(Integer.valueOf(1), diffs.get(0).getLeft());
        assertEquals(Integer.valueOf(2), diffs.get(0).getRight());
    }

    @Test
    public void testAppendIntArray() {
        int[] lhs = {1, 2};
        int[] rhs = {2, 3};
        diffBuilder.append("intArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Integer[]>);
        assertEquals("intArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Integer[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Integer[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendLong() {
        diffBuilder.append("longField", 1L, 2L);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Long>);
        assertEquals("longField", diffs.get(0).getFieldName());
        assertEquals(Long.valueOf(1L), diffs.get(0).getLeft());
        assertEquals(Long.valueOf(2L), diffs.get(0).getRight());
    }

    @Test
    public void testAppendLongArray() {
        long[] lhs = {1L, 2L};
        long[] rhs = {2L, 3L};
        diffBuilder.append("longArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Long[]>);
        assertEquals("longArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Long[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Long[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        diffBuilder.append("objectField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Object>);
        assertEquals("objectField", diffs.get(0).getFieldName());
        assertEquals(lhs, diffs.get(0).getLeft());
        assertEquals(rhs, diffs.get(0).getRight());
    }

    @Test
    public void testAppendObjectArray() {
        Object[] lhs = {new Object(), new Object()};
        Object[] rhs = {new Object(), new Object()};
        diffBuilder.append("objectArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(0, diffs.size());
    }

    @Test
    public void testAppendShort() {
        diffBuilder.append("shortField", (short) 1, (short) 2);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Short>);
        assertEquals("shortField", diffs.get(0).getFieldName());
        assertEquals(Short.valueOf((short) 1), diffs.get(0).getLeft());
        assertEquals(Short.valueOf((short) 2), diffs.get(0).getRight());
    }

    @Test
    public void testAppendShortArray() {
        short[] lhs = {1, 2};
        short[] rhs = {2, 3};
        diffBuilder.append("shortArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Short[]>);
        assertEquals("shortArrayField", diffs.get(0).getFieldName());
        assertArrayEquals(ArrayUtils.toObject(lhs), (Short[]) diffs.get(0).getLeft());
        assertArrayEquals(ArrayUtils.toObject(rhs), (Short[]) diffs.get(0).getRight());
    }

    @Test
    public void testAppendNull() {
        diffBuilder.append("nullField", null, new Object());
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        assertTrue(diffs.get(0) instanceof Diff<Object>);
        assertEquals("nullField", diffs.get(0).getFieldName());
        assertNull(diffs.get(0).getLeft());
        assertNotNull(diffs.get(0).getRight());
    }

    @Test
    public void testAppendEmptyArray() {
        Object[] lhs = {};
        Object[] rhs = {};
        diffBuilder.append("emptyArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(0, diffs.size());
    }
}
