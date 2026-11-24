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
    public void testAppendEmptyArray() {
        Object[] lhs = {};
        Object[] rhs = {new Object()};
        diffBuilder.append("emptyArrayField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertEquals(1, diffs.size());
        Diff<Object[]> diff = (Diff<Object[]>) diffs.get(0);
        assertEquals("emptyArrayField", diff.getFieldName());
        assertArrayEquals(lhs, diff.getLeft());
        assertArrayEquals(rhs, diff.getRight());
    }

    @Test
    public void testAppendTriviallyEqual() {
        Object lhs = new Object();
        Object rhs = lhs;
        diffBuilder.append("triviallyEqualField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }

    @Test
    public void testAppendNullTriviallyEqual() {
        Object lhs = null;
        Object rhs = null;
        diffBuilder.append("nullTriviallyEqualField", lhs, rhs);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }

    
    
    @Test
    public void testAppendSameObject() {
        Object obj = new Object();
        diffBuilder.append("sameObjectField", obj, obj);
        List<Diff<?>> diffs = diffBuilder.build().getDiffs();
        assertTrue(diffs.isEmpty());
    }
}
