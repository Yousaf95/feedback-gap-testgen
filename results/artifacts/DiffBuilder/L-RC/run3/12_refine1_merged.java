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

    @Test
        public void testAppendBooleanWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, true, false));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendBooleanArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new boolean[]{true}, new boolean[]{false}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendByteWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, (byte) 1, (byte) 2));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendByteArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new byte[]{1}, new byte[]{2}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendCharWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, 'a', 'b'));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendCharArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new char[]{'a'}, new char[]{'b'}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendDiffResultWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new DiffResult<>(new Object(), new Object(), Arrays.asList(), ToStringStyle.DEFAULT_STYLE)));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendDoubleWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, 1.0, 2.0));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendDoubleArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new double[]{1.0}, new double[]{2.0}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendFloatWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, 1.0f, 2.0f));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendFloatArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new float[]{1.0f}, new float[]{2.0f}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendObjectWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new Object(), new Object()));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendObjectArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new Object[]{new Object()}, new Object[]{new Object()}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendShortWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, (short) 1, (short) 2));
            assertTrue(exception.getMessage().contains("fieldName"));
        }

    @Test
        public void testAppendShortArrayWithNullFieldName() {
            DiffBuilder<Object> diffBuilder = new DiffBuilder<>(new Object(), new Object(), ToStringStyle.DEFAULT_STYLE);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> diffBuilder.append(null, new short[]{1}, new short[]{2}));
            assertTrue(exception.getMessage().contains("fieldName"));
        }
}
