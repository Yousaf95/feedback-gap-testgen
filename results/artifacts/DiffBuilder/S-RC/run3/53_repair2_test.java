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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBooleanNotEqual_2() {
            DiffBuilder<Boolean> builder = new DiffBuilder<>(true, false, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", true, false);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendByteNotEqual_2() {
            DiffBuilder<Byte> builder = new DiffBuilder<>((byte) 10, (byte) 20, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", (byte) 10, (byte) 20);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendCharNotEqual_2() {
            DiffBuilder<Character> builder = new DiffBuilder<>('a', 'b', ToStringStyle.DEFAULT_STYLE);
            builder.append("test", 'a', 'b');
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendDoubleNotEqual_2() {
            DiffBuilder<Double> builder = new DiffBuilder<>(1.0, 2.0, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", 1.0, 2.0);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendFloatNotEqual_2() {
            DiffBuilder<Float> builder = new DiffBuilder<>(1.0f, 2.0f, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", 1.0f, 2.0f);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendIntNotEqual_2() {
            DiffBuilder<Integer> builder = new DiffBuilder<>(1, 2, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", 1, 2);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongNotEqual_2() {
            DiffBuilder<Long> builder = new DiffBuilder<>(1L, 2L, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", 1L, 2L);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendShortNotEqual_2() {
            DiffBuilder<Short> builder = new DiffBuilder<>((short) 10, (short) 20, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", (short) 10, (short) 20);
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendBooleanArrayNotEqual() {
            DiffBuilder<Boolean[]> builder = new DiffBuilder<>(new Boolean[]{true}, new Boolean[]{false}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Boolean[]{true}, new Boolean[]{false});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendByteArrayNotEqual() {
            DiffBuilder<Byte[]> builder = new DiffBuilder<>(new Byte[]{(byte) 10}, new Byte[]{(byte) 20}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Byte[]{(byte) 10}, new Byte[]{(byte) 20});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendCharArrayNotEqual() {
            DiffBuilder<Character[]> builder = new DiffBuilder<>(new Character[]{'a'}, new Character[]{'b'}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Character[]{'a'}, new Character[]{'b'});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendDoubleArrayNotEqual() {
            DiffBuilder<Double[]> builder = new DiffBuilder<>(new Double[]{1.0}, new Double[]{2.0}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Double[]{1.0}, new Double[]{2.0});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendFloatArrayNotEqual() {
            DiffBuilder<Float[]> builder = new DiffBuilder<>(new Float[]{1.0f}, new Float[]{2.0f}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Float[]{1.0f}, new Float[]{2.0f});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendIntArrayNotEqual() {
            DiffBuilder<Integer[]> builder = new DiffBuilder<>(new Integer[]{1}, new Integer[]{2}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Integer[]{1}, new Integer[]{2});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongArrayNotEqual() {
            DiffBuilder<Long[]> builder = new DiffBuilder<>(new Long[]{1L}, new Long[]{2L}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Long[]{1L}, new Long[]{2L});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendShortArrayNotEqual() {
            DiffBuilder<Short[]> builder = new DiffBuilder<>(new Short[]{(short) 10}, new Short[]{(short) 20}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Short[]{(short) 10}, new Short[]{(short) 20});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectNotEqual() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Object(), new Object());
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectArrayNotEqual() {
            DiffBuilder<Object[]> builder = new DiffBuilder<>(new Object[]{new Object()}, new Object[]{new Object()}, ToStringStyle.DEFAULT_STYLE);
            builder.append("test", new Object[]{new Object()}, new Object[]{new Object()});
            assertEquals(1, builder.build().getDiffs().size());
        }
}
