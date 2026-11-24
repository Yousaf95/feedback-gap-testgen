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

    
    @Test
        public void testAppendEmptyObjectArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new Object[0], new Object[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    @Test
        public void testAppendNonEmptyObjectArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new Object[]{"value1"}, new Object[]{"value2"});
            DiffResult<String> result = builder.build();
            assertEquals(1, result.getDiffs().size());
            assertEquals("arrayField", result.getDiffs().get(0).getFieldName());
            assertArrayEquals(new Object[]{"value1"}, (Object[]) result.getDiffs().get(0).getLeft());
            assertArrayEquals(new Object[]{"value2"}, (Object[]) result.getDiffs().get(0).getRight());
        }

    
    @Test
        public void testAppendEmptyBooleanArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new boolean[0], new boolean[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyByteArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new byte[0], new byte[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyCharArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new char[0], new char[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyDoubleArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new double[0], new double[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyFloatArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new float[0], new float[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyIntArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new int[0], new int[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyLongArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new long[0], new long[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    
    @Test
        public void testAppendEmptyShortArray() {
            DiffBuilder<String> builder = new DiffBuilder<>("left", "right", ToStringStyle.DEFAULT_STYLE);
            builder.append("arrayField", new short[0], new short[0]);
            DiffResult<String> result = builder.build();
            assertEquals(0, result.getDiffs().size());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendEmptyStringArray() {
            String[] lhs = {};
            String[] rhs = {};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE).append("array", lhs, rhs).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    
    
    
    
    @Test
        public void testAppendEmptyString() {
            String lhs = "";
            String rhs = "";
            DiffResult<String> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE).append("string", lhs, rhs).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        public void testAppendNonEmptyString() {
            String lhs = "abc";
            String rhs = "def";
            DiffResult<String> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.DEFAULT_STYLE).append("string", lhs, rhs).build();
            assertEquals(1, result.getDiffs().size());
        }

    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendEmptyShortArray_2() {
            DiffResult<Short[]> result = new DiffBuilder<>(new Short[]{}, new Short[] {}, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        public void testAppendNonEmptyShortArray() {
            DiffResult<Short[]> result = new DiffBuilder<>(new Short[]{1, 2, 3}, new Short[]{4, 5, 6}, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(3, result.getDiffs().size());
        }

    @Test
        public void testAppendNullShortArray() {
            assertThrows(NullPointerException.class, () -> 
                new DiffBuilder<>(new Short[]{1, 2, 3}, null, ToStringStyle.SHORT_PREFIX_STYLE)
            );
        }

    @Test
        public void testAppendEmptyObjectArrayWithNullElements() {
            Object[] lhs = {null, null};
            Object[] rhs = {null, null};
            DiffResult<Object[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        public void testAppendNonEmptyObjectArrayWithNullElements() {
            Object[] lhs = {1, null, 3};
            Object[] rhs = {4, null, 6};
            DiffResult<Object[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(2, result.getDiffs().size());
        }

    @Test
        public void testAppendEmptyStringArrayWithNullElements() {
            String[] lhs = {null, null};
            String[] rhs = {null, null};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        public void testAppendNonEmptyStringArrayWithNullElements() {
            String[] lhs = {"a", null, "c"};
            String[] rhs = {"d", null, "f"};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(2, result.getDiffs().size());
        }

    @Test
        public void testAppendEmptyStringArrayWithEmptyStrings() {
            String[] lhs = {"", ""};
            String[] rhs = {"", ""};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertTrue(result.getDiffs().isEmpty());
        }

    @Test
        public void testAppendNonEmptyStringArrayWithEmptyStrings() {
            String[] lhs = {"a", "", "c"};
            String[] rhs = {"d", "", "f"};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(2, result.getDiffs().size());
        }

    @Test
        public void testAppendEmptyStringArrayWithNonEmptyStrings() {
            String[] lhs = {"a", "b", "c"};
            String[] rhs = {""};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(3, result.getDiffs().size());
        }

    @Test
        public void testAppendNonEmptyStringArrayWithNonEmptyStrings() {
            String[] lhs = {"a", "b", "c"};
            String[] rhs = {"d", "e", "f"};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(3, result.getDiffs().size());
        }

    @Test
        public void testAppendEmptyStringArrayWithMixedContent() {
            String[] lhs = {"a", null, ""};
            String[] rhs = {null, "b", "c"};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(3, result.getDiffs().size());
        }

    @Test
        public void testAppendNonEmptyStringArrayWithMixedContent() {
            String[] lhs = {"a", null, "c"};
            String[] rhs = {null, "b", "f"};
            DiffResult<String[]> result = new DiffBuilder<>(lhs, rhs, ToStringStyle.SHORT_PREFIX_STYLE).build();
            assertEquals(2, result.getDiffs().size());
        }
}
