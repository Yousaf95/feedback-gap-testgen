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
    public void testAppendBooleanArray() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {false, true};
        diffBuilder.append("booleanArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Boolean[]> diff = (Diff<Boolean[]>) diffs.get(0);
        assertEquals("booleanArrayField", diff.getFieldName());
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
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
    public void testAppendShortArray() {
        short[] lhs = {1, 2};
        short[] rhs = {2, 3};
        diffBuilder.append("shortArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Short[]> diff = (Diff<Short[]>) diffs.get(0);
        assertEquals("shortArrayField", diff.getFieldName());
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
    }
}
