package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
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
        public void testAppendBooleanWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", true, false));
        }

    @Test
        public void testAppendBooleanArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new boolean[]{true}, new boolean[]{false}));
        }

    @Test
        public void testAppendByteWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", (byte) 1, (byte) 2));
        }

    @Test
        public void testAppendByteArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new byte[]{1}, new byte[]{2}));
        }

    @Test
        public void testAppendCharWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 'a', 'b'));
        }

    @Test
        public void testAppendCharArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new char[]{'a'}, new char[]{'b'}));
        }

    @Test
        public void testAppendDiffResultWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new DiffResult<>(new Object(), new Object(), Arrays.asList(), ToStringStyle.DEFAULT_STYLE)));
        }

    @Test
        public void testAppendDoubleWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1.0, 2.0));
        }

    @Test
        public void testAppendDoubleArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new double[]{1.0}, new double[]{2.0}));
        }

    @Test
        public void testAppendFloatWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1.0f, 2.0f));
        }

    @Test
        public void testAppendFloatArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new float[]{1.0f}, new float[]{2.0f}));
        }

    @Test
        public void testAppendObjectWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new Object(), new Object()));
        }

    @Test
        public void testAppendObjectArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new Object[]{new Object()}, new Object[]{new Object()}));
        }

    @Test
        public void testAppendShortWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", (short) 1, (short) 2));
        }

    @Test
        public void testAppendShortArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new short[]{1}, new short[]{2}));
        }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBooleanWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", true, true));
        }

    @Test
        public void testAppendBooleanArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new boolean[]{true}, new boolean[]{true}));
        }

    @Test
        public void testAppendByteWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", (byte) 1, (byte) 1));
        }

    @Test
        public void testAppendByteArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new byte[]{1}, new byte[]{1}));
        }

    @Test
        public void testAppendCharWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 'a', 'a'));
        }

    @Test
        public void testAppendCharArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new char[]{'a'}, new char[]{'a'}));
        }

    @Test
        public void testAppendDoubleWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1.0, 1.0));
        }

    @Test
        public void testAppendDoubleArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new double[]{1.0}, new double[]{1.0}));
        }

    @Test
        public void testAppendFloatWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1.0f, 1.0f));
        }

    @Test
        public void testAppendFloatArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new float[]{1.0f}, new float[]{1.0f}));
        }

    @Test
        public void testAppendIntWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1, 1));
        }

    @Test
        public void testAppendIntArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new int[]{1}, new int[]{1}));
        }

    @Test
        public void testAppendLongWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1L, 1L));
        }

    @Test
        public void testAppendLongArrayWithTriviallyEqualObjects() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new long[]{1L}, new long[]{1L}));
        }

    @Test
        public void testAppendShortWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", (short) 1, (short) 1));
        }

    @Test
        public void testAppendShortArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new short[]{1}, new short[]{1}));
        }

    @Test
        public void testAppendObjectWithTriviallyEqualObjects_2() {
            Object obj = new Object();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(obj, obj, ToStringStyle.DEFAULT_STYLE);
            assertSame(diffBuilder, diffBuilder.append("fieldName", obj, obj));
        }

    @Test
        public void testAppendObjectArrayWithTriviallyEqualObjects_2() {
            Object[] array = {new Object()};
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(array, array, ToStringStyle.DEFAULT_STYLE);
            assertSame(diffBuilder, diffBuilder.append("fieldName", array, array));
        }

    @Test
        public void testAppendDiffResultWithTriviallyEqualObjects_2() {
            DiffResult<Object> diffResult = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE).build();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", diffResult));
        }

    @Test
        public void testAppendBooleanWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", true, false);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendBooleanArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new boolean[]{true}, new boolean[]{false});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendByteWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", (byte) 1, (byte) 2);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendByteArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new byte[]{1}, new byte[]{2});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendCharWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 'a', 'b');
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendCharArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new char[]{'a'}, new char[]{'b'});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendDoubleWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 1.0, 2.0);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendDoubleArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new double[]{1.0}, new double[]{2.0});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendFloatWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 1.0f, 2.0f);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendFloatArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new float[]{1.0f}, new float[]{2.0f});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendIntWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 1, 2);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendIntArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new int[]{1}, new int[]{2});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 1L, 2L);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new long[]{1L}, new long[]{2L});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendShortWithDifferentValues() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", (short) 1, (short) 2);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendShortArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new short[]{1}, new short[]{2});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new Object(), new Object());
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectArrayWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new Object[]{new Object()}, new Object[]{new Object()});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendDiffResultWithDifferentValues_2() {
            DiffResult<Object> diffResult = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE).append("innerField", 1, 2).build();
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", diffResult);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", 1L, 1L));
        }

    @Test
        public void testAppendLongArrayWithTriviallyEqualObjects_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(diffBuilder, diffBuilder.append("fieldName", new long[]{1L}, new long[]{1L}));
        }

    @Test
        public void testAppendLongWithDifferentValues_2() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", 1L, 2L);
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongArrayWithDifferentValues_3() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            diffBuilder.append("fieldName", new long[]{1L}, new long[]{2L});
            assertEquals(1, diffBuilder.build().getDiffs().size());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendBooleanWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", true, true));
        }

    @Test
        public void testAppendBooleanArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new boolean[]{true}, new boolean[]{true}));
        }

    @Test
        public void testAppendByteWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", (byte) 1, (byte) 1));
        }

    @Test
        public void testAppendByteArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new byte[]{1}, new byte[]{1}));
        }

    @Test
        public void testAppendCharWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", 'a', 'a'));
        }

    @Test
        public void testAppendCharArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new char[]{'a'}, new char[]{'a'}));
        }

    @Test
        public void testAppendDiffResultWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new DiffResult<>(new Object(), new Object(), Arrays.asList(), ToStringStyle.DEFAULT_STYLE)));
        }

    @Test
        public void testAppendDoubleWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", 1.0, 1.0));
        }

    @Test
        public void testAppendDoubleArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new double[]{1.0}, new double[]{1.0}));
        }

    @Test
        public void testAppendFloatWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", 1.0f, 1.0f));
        }

    @Test
        public void testAppendFloatArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new float[]{1.0f}, new float[]{1.0f}));
        }

    @Test
        public void testAppendIntWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", 1, 1));
        }

    @Test
        public void testAppendIntArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new int[]{1}, new int[]{1}));
        }

    @Test
        public void testAppendLongWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", 1L, 1L));
        }

    @Test
        public void testAppendLongArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new long[]{1L}, new long[]{1L}));
        }

    @Test
        public void testAppendShortWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", (short) 1, (short) 1));
        }

    @Test
        public void testAppendShortArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new short[]{1}, new short[]{1}));
        }

    @Test
        public void testAppendObjectWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new Object(), new Object()));
        }

    @Test
        public void testAppendObjectArrayWithTriviallyEqualObjects_ReturnsSameInstance() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE, true);
            assertSame(builder, builder.append("fieldName", new Object[]{}, new Object[]{}));
        }

    @Test
        public void testAppendBooleanArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new boolean[]{true}, new boolean[]{false});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendByteArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new byte[]{1}, new byte[]{2});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendCharArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new char[]{'a'}, new char[]{'b'});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendDoubleArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new double[]{1.0}, new double[]{2.0});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendFloatArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new float[]{1.0f}, new float[]{2.0f});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendIntArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new int[]{1}, new int[]{2});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendLongArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new long[]{1L}, new long[]{2L});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendShortArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new short[]{1}, new short[]{2});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectArrayWithDifferentValues_CoversNotEqualBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new Object[]{new Object()}, new Object[]{new Object()});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectWithDifferentArrayTypes_CoversArrayTypeBranches() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new boolean[]{true}, new byte[]{1});
            assertEquals(2, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectWithBooleanArray_CoversArrayTypeBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new boolean[]{true}, new boolean[]{false});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectWithByteArray_CoversArrayTypeBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new byte[]{1}, new byte[]{2});
            assertEquals(1, builder.build().getDiffs().size());
        }

    @Test
        public void testAppendObjectWithCharArray_CoversArrayTypeBranch() {
            DiffBuilder<Object> builder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            builder.append("fieldName", new char[]{'a'}, new char[]{'b'});
            assertEquals(1, builder.build().getDiffs().size());
        }
}
