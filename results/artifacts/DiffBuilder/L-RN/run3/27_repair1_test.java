package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiffBuilder_GenTest {

    @Test
    void testConstructorWithNullLhs() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(null, "rhs", ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    void testConstructorWithNullRhs() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>("lhs", null, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    void testAppendBooleanWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", true, false);
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendBooleanWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", true, true);
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendBooleanArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new boolean[]{true}, new boolean[]{false});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendBooleanArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new boolean[]{true}, new boolean[]{true});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendByteArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new byte[]{1}, new byte[]{2});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendByteArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new byte[]{1}, new byte[]{1});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendCharArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new char[]{'a'}, new char[]{'b'});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendCharArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new char[]{'a'}, new char[]{'a'});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendDoubleArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new double[]{1.0}, new double[]{2.0});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendDoubleArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new double[]{1.0}, new double[]{1.0});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendFloatArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new float[]{1.0f}, new float[]{2.0f});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendFloatArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new float[]{1.0f}, new float[]{1.0f});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendIntArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new int[]{1}, new int[]{2});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendIntArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new int[]{1}, new int[]{1});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendLongArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new long[]{1L}, new long[]{2L});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendLongArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new long[]{1L}, new long[]{1L});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendShortArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new short[]{1}, new short[]{2});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendShortArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new short[]{1}, new short[]{1});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendObjectArrayWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new Object[]{"a"}, new Object[]{"b"});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendObjectArrayWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", new Object[]{"a"}, new Object[]{"a"});
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendObjectWithDifferentValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", "a", "b");
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendObjectWithSameValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", "a", "a");
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendObjectWithNullValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", null, "b");
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendObjectWithSameNullValues() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", (Object) null, (Object) null);
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendDiffResultWithDifferentValues() {
        DiffBuilder<String> innerDiffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        innerDiffBuilder.append("innerField", 1, 2);
        DiffResult<String> diffResult = innerDiffBuilder.build();

        DiffBuilder<String> outerDiffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        outerDiffBuilder.append("field", diffResult);

        DiffResult<String> result = outerDiffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    @Test
    void testAppendDiffResultWithSameValues() {
        DiffBuilder<String> innerDiffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        innerDiffBuilder.append("innerField", 1, 1);
        DiffResult<String> diffResult = innerDiffBuilder.build();

        DiffBuilder<String> outerDiffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE);
        outerDiffBuilder.append("field", diffResult);

        DiffResult<String> result = outerDiffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    @Test
    void testAppendWithTriviallyEqualObjects() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "lhs", ToStringStyle.DEFAULT_STYLE);
        diffBuilder.append("field", 1, 2);
        DiffResult<String> result = diffBuilder.build();
        assertEquals(0, result.getDiffs().size());
    }

    
    @Test
    void testAppendWithNonTriviallyEqualObjectsAndFalseFlag() {
        DiffBuilder<String> diffBuilder = new DiffBuilder<>("lhs", "rhs", ToStringStyle.DEFAULT_STYLE, false);
        diffBuilder.append("field", 1, 2);
        DiffResult<String> result = diffBuilder.build();
        assertEquals(1, result.getDiffs().size());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testConstructorWithTriviallyEqualObjects() {
            Object lhs = new Object();
            Object rhs = lhs; // same instance, trivially equal
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    
    @Test
        void testAppendWithNullFieldName() {
            Object lhs = new Object();
            Object rhs = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE);
            assertThrows(NullPointerException.class, () -> diffBuilder.append(null, lhs, rhs));
        }

    @Test
        void testAppendDoubleWithDifferentValues() {
            double lhs = 1.0;
            double rhs = 2.0;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendDoubleWithSameValues() {
            double lhs = 1.0;
            double rhs = 1.0;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendFloatWithDifferentValues() {
            float lhs = 1.0f;
            float rhs = 2.0f;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendFloatWithSameValues() {
            float lhs = 1.0f;
            float rhs = 1.0f;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendShortWithDifferentValues() {
            short lhs = 1;
            short rhs = 2;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendShortWithSameValues() {
            short lhs = 1;
            short rhs = 1;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendByteWithDifferentValues() {
            byte lhs = 1;
            byte rhs = 2;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendByteWithSameValues() {
            byte lhs = 1;
            byte rhs = 1;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendCharWithDifferentValues() {
            char lhs = 'a';
            char rhs = 'b';
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendCharWithSameValues() {
            char lhs = 'a';
            char rhs = 'a';
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendLongWithDifferentValues() {
            long lhs = 1L;
            long rhs = 2L;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendLongWithSameValues() {
            long lhs = 1L;
            long rhs = 1L;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendIntWithDifferentValues() {
            int lhs = 1;
            int rhs = 2;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(1, result.getDiffs().size());
        }

    @Test
        void testAppendIntWithSameValues() {
            int lhs = 1;
            int rhs = 1;
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", lhs, rhs);
            DiffResult<Object> result = diffBuilder.build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        void testAppendDiffResultWithNullDiffResult() {
            Object lhs = new Object();
            Object rhs = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE);
            assertThrows(NullPointerException.class, () -> diffBuilder.append("fieldName", null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testConstructorWithTriviallyEqualObjectsAndFalseFlag() {
            Object lhs = new Object();
            Object rhs = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE, false);
            assertEquals(0, diffBuilder.build().getDiffs().size());
        }

    @Test
        void testAppendWithNullFieldNameThrowsNullPointerException() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            assertThrows(NullPointerException.class, () -> diffBuilder.append(null, 1, 2));
        }

    @Test
        void testBuildWithNoDifferences() {
            Object lhs = new Object();
            Object rhs = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        void testBuildWithMultipleDifferences() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("field1", 1, 2);
            diffBuilder.append("field2", 'a', 'b');
            diffBuilder.append("field3", true, false);
            DiffResult<Object> result = diffBuilder.build();
            assertEquals(3, result.getDiffs().size());
        }

    @Test
        void testAppendDiffResultWithNestedDifferences() {
            DiffBuilder<String> innerDiffBuilder = new DiffBuilder<>("innerLeft", "innerRight", ToStringStyle.DEFAULT_STYLE);
            innerDiffBuilder.append("nestedField", 10, 20);
            DiffResult<String> innerDiffResult = innerDiffBuilder.build();
    
            DiffBuilder<Object> outerDiffBuilder = new DiffBuilder<>("outerLeft", "outerRight", ToStringStyle.DEFAULT_STYLE);
            outerDiffBuilder.append("field", (DiffResult<Object>) (Object) innerDiffResult);
    
            DiffResult<Object> result = outerDiffBuilder.build();
            assertEquals(1, result.getDiffs().size());
            assertEquals("field.nestedField", result.getDiffs().get(0).getFieldName());
        }
}
