package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    public void testAppendBooleanDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", true, false);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Boolean.TRUE, result.getDiffs().get(0).getLeft());
        assertEquals(Boolean.FALSE, result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendBooleanSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", true, true);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendBooleanArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new boolean[]{true}, new boolean[]{false});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Boolean[]{Boolean.TRUE}, (Boolean[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Boolean[]{Boolean.FALSE}, (Boolean[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendBooleanArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new boolean[]{true}, new boolean[]{true});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendByteArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new byte[]{1}, new byte[]{2});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Byte[]{Byte.valueOf((byte) 1)}, (Byte[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Byte[]{Byte.valueOf((byte) 2)}, (Byte[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendByteArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new byte[]{1}, new byte[]{1});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendCharArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new char[]{'a'}, new char[]{'b'});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Character[]{'a'}, (Character[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Character[]{'b'}, (Character[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendCharArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new char[]{'a'}, new char[]{'a'});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendDoubleDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1.0, 2.0);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Double.valueOf(1.0), result.getDiffs().get(0).getLeft());
        assertEquals(Double.valueOf(2.0), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendDoubleSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1.0, 1.0);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendDoubleArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new double[]{1.0}, new double[]{2.0});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Double[]{Double.valueOf(1.0)}, (Double[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Double[]{Double.valueOf(2.0)}, (Double[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendDoubleArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new double[]{1.0}, new double[]{1.0});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendFloatDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1.0f, 2.0f);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Float.valueOf(1.0f), result.getDiffs().get(0).getLeft());
        assertEquals(Float.valueOf(2.0f), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendFloatSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1.0f, 1.0f);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendFloatArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new float[]{1.0f}, new float[]{2.0f});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Float[]{Float.valueOf(1.0f)}, (Float[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Float[]{Float.valueOf(2.0f)}, (Float[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendFloatArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new float[]{1.0f}, new float[]{1.0f});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendIntDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1, 2);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Integer.valueOf(1), result.getDiffs().get(0).getLeft());
        assertEquals(Integer.valueOf(2), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendIntSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1, 1);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendIntArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new int[]{1}, new int[]{2});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Integer[]{Integer.valueOf(1)}, (Integer[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Integer[]{Integer.valueOf(2)}, (Integer[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendIntArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new int[]{1}, new int[]{1});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendLongDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1L, 2L);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Long.valueOf(1L), result.getDiffs().get(0).getLeft());
        assertEquals(Long.valueOf(2L), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendLongSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1L, 1L);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendLongArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new long[]{1L}, new long[]{2L});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Long[]{Long.valueOf(1L)}, (Long[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Long[]{Long.valueOf(2L)}, (Long[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendLongArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new long[]{1L}, new long[]{1L});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendObjectDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", "value1", "value2");
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals("value1", result.getDiffs().get(0).getLeft());
        assertEquals("value2", result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendObjectSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", "value1", "value1");
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendObjectArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new Object[]{"value1"}, new Object[]{"value2"});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Object[]{"value1"}, (Object[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Object[]{"value2"}, (Object[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendObjectArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new Object[]{"value1"}, new Object[]{"value1"});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendShortDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", (short) 1, (short) 2);
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Short.valueOf((short) 1), result.getDiffs().get(0).getLeft());
        assertEquals(Short.valueOf((short) 2), result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendShortSameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", (short) 1, (short) 1);
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendShortArrayDifferentValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new short[]{1}, new short[]{2});
        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
        assertArrayEquals(new Short[]{Short.valueOf((short) 1)}, (Short[]) result.getDiffs().get(0).getLeft());
        assertArrayEquals(new Short[]{Short.valueOf((short) 2)}, (Short[]) result.getDiffs().get(0).getRight());
    }

    @Test
    public void testAppendShortArraySameValues() {
        DiffBuilder<String> builder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", new short[]{1}, new short[]{1});
        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testAppendDiffResultDifferentValues() {
        DiffBuilder<String> innerBuilder = new DiffBuilder<>("lhsInner", "rhsInner", ToStringStyle.DEFAULT_STYLE);
        innerBuilder.append("innerField1", 1, 2);
        DiffResult<String> innerResult = innerBuilder.build();

        DiffBuilder<String> outerBuilder = new DiffBuilder<>("lhsOuter", "rhsOuter", ToStringStyle.DEFAULT_STYLE);
        outerBuilder.append("outerField1", innerResult);

        DiffResult<String> result = outerBuilder.build();
        assertEquals(1, result.getDiffs().size());
        assertEquals(Integer.valueOf(1), ((Diff<Integer>) result.getDiffs().get(0)).getLeft());
        assertEquals(Integer.valueOf(2), ((Diff<Integer>) result.getDiffs().get(0)).getRight());
    }

    @Test
    public void testAppendDiffResultSameValues() {
        DiffBuilder<String> innerBuilder = new DiffBuilder<>("lhsInner", "rhsInner", ToStringStyle.DEFAULT_STYLE);
        innerBuilder.append("innerField1", 1, 1);
        DiffResult<String> innerResult = innerBuilder.build();

        DiffBuilder<String> outerBuilder = new DiffBuilder<>("lhsOuter", "rhsOuter", ToStringStyle.DEFAULT_STYLE);
        outerBuilder.append("outerField1", innerResult);

        DiffResult<String> result = outerBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testTriviallyEqualObjects() {
        String lhs = new String("test");
        String rhs = new String("test");

        DiffBuilder<String> builder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE);
        builder.append("field1", 1, 2);

        DiffResult<String> result = builder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    public void testNonTriviallyEqualObjects() {
        String lhs = new String("test");
        String rhs = new String("test");

        DiffBuilder<String> builder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE, false);
        builder.append("field1", 1, 2);

        DiffResult<String> result = builder.build();
        assertEquals(1, result.getDiffs().size());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testConstructorWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            assertThrows(NullPointerException.class, () -> diffBuilder.append(null, 1, 2));
        }

    @Test
        void testAppendDiffResultWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> diffResult = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE).build();
            assertThrows(NullPointerException.class, () -> diffBuilder.append(null, diffResult));
        }

    @Test
        void testAppendDiffResultWithNullDiffResult() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            assertThrows(NullPointerException.class, () -> diffBuilder.append("fieldName", (DiffResult<Object>) null));
        }

    @Test
        void testBuildWithNoDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testTriviallyEqualObjectsWithCustomStyle() {
            Object obj1 = new Object();
            Object obj2 = obj1;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(obj1, obj2, ToStringStyle.NO_FIELD_NAMES_STYLE);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    @Test
        void testAppendObjectWithSameReference() {
            Object obj = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(obj, obj, ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", obj, obj);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testAppendObjectArrayWithSameReference() {
            Object[] array = new Object[0];
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(array, array, ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", array, array);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testBuildWithDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", 1, 2);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testBuildWithMultipleDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field1", 1, 2).append("field2", "a", "b");
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(2, result.getDiffs().size());
        }

    @Test
        void testAppendWithTriviallyEqualObjects() {
            Object obj = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(obj, obj, ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", 1, 2);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testAppendWithNonTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, false);
            diffBuilder.append("field", 1, 2);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendObjectArrayWithDifferentReferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", new String[]{"a"}, new String[]{"a"});
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testAppendObjectArrayWithDifferentContents() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field", new String[]{"a"}, new String[]{"b"});
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendDiffResultWithEmptyDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> emptyDiffResult = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE).build();
            diffBuilder.append("field", emptyDiffResult);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testAppendDiffResultWithNonEmptyDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> nonEmptyDiffResult = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE)
                    .append("nestedField", 1, 2).build();
            diffBuilder.append("field", nonEmptyDiffResult);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendWithNullFieldNameThrowsException() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, 1, 2));
        }
}
