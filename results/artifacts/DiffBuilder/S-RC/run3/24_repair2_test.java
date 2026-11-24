package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiffBuilder_GenTest {

    private DiffBuilder<Object> diffBuilder;

    @BeforeEach
    public void setUp() {
        diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendEmptyArray() {
        Object[] lhs = {};
        Object[] rhs = {};
        diffBuilder.append("emptyArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(0, diffs.size());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBooleanNotEqual() {
            diffBuilder.append("field", true, false);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Boolean> diff = (Diff<Boolean>) diffs.get(0);
            assertEquals("field", diff.getFieldName());
            assertTrue(diff.getLeft());
            assertFalse(diff.getRight());
        }

    @Test
        public void testAppendBooleanEqual() {
            diffBuilder.append("field", true, true);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendBooleanArrayNotEqual() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {false, true};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Boolean[]> diff = (Diff<Boolean[]>) diffs.get(0);
            assertArrayEquals(lhs, diff.getLeft());
            assertArrayEquals(rhs, diff.getRight());
        }

    @Test
        public void testAppendBooleanArrayEqual() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true, false};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendByteNotEqual() {
            diffBuilder.append("field", (byte) 1, (byte) 2);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Byte> diff = (Diff<Byte>) diffs.get(0);
            assertEquals((byte) 1, diff.getLeft());
            assertEquals((byte) 2, diff.getRight());
        }

    @Test
        public void testAppendByteEqual() {
            diffBuilder.append("field", (byte) 1, (byte) 1);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendByteArrayNotEqual() {
            byte[] lhs = {1, 2};
            byte[] rhs = {2, 1};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Byte[]> diff = (Diff<Byte[]>) diffs.get(0);
            assertArrayEquals(lhs, diff.getLeft());
            assertArrayEquals(rhs, diff.getRight());
        }

    @Test
        public void testAppendByteArrayEqual() {
            byte[] lhs = {1, 2};
            byte[] rhs = {1, 2};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendCharNotEqual() {
            diffBuilder.append("field", 'a', 'b');
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Character> diff = (Diff<Character>) diffs.get(0);
            assertEquals('a', diff.getLeft());
            assertEquals('b', diff.getRight());
        }

    @Test
        public void testAppendCharEqual() {
            diffBuilder.append("field", 'a', 'a');
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendCharArrayNotEqual() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'b', 'a'};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Character[]> diff = (Diff<Character[]>) diffs.get(0);
            assertArrayEquals(lhs, diff.getLeft());
            assertArrayEquals(rhs, diff.getRight());
        }

    @Test
        public void testAppendCharArrayEqual() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'a', 'b'};
            diffBuilder.append("field", lhs, rhs);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendDoubleNotEqual() {
            diffBuilder.append("field", 1.0, 2.0);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Double> diff = (Diff<Double>) diffs.get(0);
            assertEquals(1.0, diff.getLeft(), 0.0);
            assertEquals(2.0, diff.getRight(), 0.0);
        }

    @Test
        public void testAppendDoubleEqual() {
            diffBuilder.append("field", 1.0, 1.0);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendFloatNotEqual() {
            diffBuilder.append("field", (float) 1.0, (float) 2.0);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Float> diff = (Diff<Float>) diffs.get(0);
            assertEquals((float) 1.0, diff.getLeft(), 0.0);
            assertEquals((float) 2.0, diff.getRight(), 0.0);
        }

    @Test
        public void testAppendFloatEqual() {
            diffBuilder.append("field", (float) 1.0, (float) 1.0);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendIntNotEqual() {
            diffBuilder.append("field", 1, 2);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Integer> diff = (Diff<Integer>) diffs.get(0);
            assertEquals(1, diff.getLeft());
            assertEquals(2, diff.getRight());
        }

    @Test
        public void testAppendIntEqual() {
            diffBuilder.append("field", 1, 1);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendLongNotEqual() {
            diffBuilder.append("field", 1L, 2L);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Long> diff = (Diff<Long>) diffs.get(0);
            assertEquals(1L, diff.getLeft());
            assertEquals(2L, diff.getRight());
        }

    @Test
        public void testAppendLongEqual() {
            diffBuilder.append("field", 1L, 1L);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }

    @Test
        public void testAppendShortNotEqual() {
            diffBuilder.append("field", (short) 1, (short) 2);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertEquals(1, diffs.size());
            Diff<Short> diff = (Diff<Short>) diffs.get(0);
            assertEquals((short) 1, diff.getLeft());
            assertEquals((short) 2, diff.getRight());
        }

    @Test
        public void testAppendShortEqual() {
            diffBuilder.append("field", (short) 1, (short) 1);
            List<Diff<?>> diffs = diffBuilder.build().getDiffs();
            assertTrue(diffs.isEmpty());
        }
}
