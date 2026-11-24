package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

public class DiffBuilder_GenTest {

    @Test
    public void testConstructorWithNullLhs() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(null, "rhs", ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testConstructorWithNullRhs() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>("lhs", null, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testAppendBooleanFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", true, true)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendBooleanFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", true, false)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Boolean.TRUE, result.getDiffs().get(0).getLeft());
        assertEquals(Boolean.FALSE, result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendBooleanArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new boolean[]{true}, new boolean[]{true})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendBooleanArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new boolean[]{true}, new boolean[]{false})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Boolean[]{Boolean.TRUE}, (Boolean[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Boolean[]{Boolean.FALSE}, (Boolean[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendByteFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", (byte) 1, (byte) 1)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendByteFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", (byte) 1, (byte) 2)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Byte.valueOf((byte) 1), result.getDiffs().get(0).getLeft());
        assertEquals(Byte.valueOf((byte) 2), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendByteArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new byte[]{1}, new byte[]{1})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendByteArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new byte[]{1}, new byte[]{2})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Byte[]{Byte.valueOf((byte) 1)}, (Byte[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Byte[]{Byte.valueOf((byte) 2)}, (Byte[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendCharFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 'a', 'a')
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendCharFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 'a', 'b')
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Character.valueOf('a'), result.getDiffs().get(0).getLeft());
        assertEquals(Character.valueOf('b'), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendCharArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new char[]{'a'}, new char[]{'a'})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendCharArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new char[]{'a'}, new char[]{'b'})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Character[]{Character.valueOf('a')}, (Character[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Character[]{Character.valueOf('b')}, (Character[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendDoubleFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1.0, 1.0)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendDoubleFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1.0, 2.0)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Double.valueOf(1.0), result.getDiffs().get(0).getLeft());
        assertEquals(Double.valueOf(2.0), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendDoubleArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new double[]{1.0}, new double[]{1.0})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendDoubleArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new double[]{1.0}, new double[]{2.0})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Double[]{Double.valueOf(1.0)}, (Double[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Double[]{Double.valueOf(2.0)}, (Double[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendFloatFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1.0f, 1.0f)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendFloatFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1.0f, 2.0f)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Float.valueOf(1.0f), result.getDiffs().get(0).getLeft());
        assertEquals(Float.valueOf(2.0f), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendFloatArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new float[]{1.0f}, new float[]{1.0f})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendFloatArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new float[]{1.0f}, new float[]{2.0f})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Float[]{Float.valueOf(1.0f)}, (Float[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Float[]{Float.valueOf(2.0f)}, (Float[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendIntFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1, 1)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendIntFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1, 2)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Integer.valueOf(1), result.getDiffs().get(0).getLeft());
        assertEquals(Integer.valueOf(2), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendIntArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new int[]{1}, new int[]{1})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendIntArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new int[]{1}, new int[]{2})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Integer[]{Integer.valueOf(1)}, (Integer[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Integer[]{Integer.valueOf(2)}, (Integer[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendLongFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1L, 1L)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendLongFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1L, 2L)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Long.valueOf(1L), result.getDiffs().get(0).getLeft());
        assertEquals(Long.valueOf(2L), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendLongArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new long[]{1L}, new long[]{1L})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendLongArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new long[]{1L}, new long[]{2L})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Long[]{Long.valueOf(1L)}, (Long[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Long[]{Long.valueOf(2L)}, (Long[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendObjectFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", "value", "value")
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendObjectFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", "value1", "value2")
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals("value1", result.getDiffs().get(0).getLeft());
        assertEquals("value2", result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendObjectArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new Object[]{"value"}, new Object[]{"value"})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendObjectArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new Object[]{"value1"}, new Object[]{"value2"})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Object[]{"value1"}, (Object[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Object[]{"value2"}, (Object[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendShortFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", (short) 1, (short) 1)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendShortFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", (short) 1, (short) 2)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Short.valueOf((short) 1), result.getDiffs().get(0).getLeft());
        assertEquals(Short.valueOf((short) 2), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendShortArrayFieldEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new short[]{1}, new short[]{1})
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendShortArrayFieldNotEqual() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", new short[]{1}, new short[]{2})
                .build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Short[]{Short.valueOf((short) 1)}, (Short[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Short[]{Short.valueOf((short) 2)}, (Short[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendDiffResultFieldEqual() {
        DiffResult<String> innerResult = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .build();
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", innerResult)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }

    @Test
    public void testAppendDiffResultFieldNotEqual() {
        DiffResult<String> innerResult = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("innerField", 1, 2)
                .build();
        DiffResult<String> result = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", innerResult)
                .build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Integer.valueOf(1), ((Diff<Integer>) result.getDiffs().get(0)).getLeft());
        assertEquals(Integer.valueOf(2), ((Diff<Integer>) result.getDiffs().get(0)).getRight());
    }

    @Test
    public void testTriviallyEqualObjects() {
        DiffResult<String> result = new DiffBuilder<>("lhs", "lhs", ToStringStyle.DEFAULT_STYLE)
                .append("field", 1, 2)
                .build();
        assertTrue(result.getDiffs().isEmpty());
    }
}
