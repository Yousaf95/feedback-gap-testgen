package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EqualsBuilder_GenTest {

    private EqualsBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendSuper() {
        builder.appendSuper(true);
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReset() {
        builder.append(true, false);
        assertFalse(builder.isEquals());
        builder.reset();
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object) null, (Object) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDifferentObjects() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new Object(), new Object());
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithNullElements() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object[]) null, (Object[]) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new Object[1], new Object[2]);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithEqualElements() {
            Object[] array1 = { "a", 1, true };
            Object[] array2 = { "a", 1, true };
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithUnequalElements() {
            Object[] array1 = { "a", 1, true };
            Object[] array2 = { "b", 1, true };
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithNullAndNonNullElements() {
            Object[] array1 = { null, 1, true };
            Object[] array2 = { "a", 1, true };
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithNonNullAndNullElements() {
            Object[] array1 = { "a", 1, true };
            Object[] array2 = { null, 1, true };
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithDifferentTypes() {
            Object[] array1 = { "a", 1, true };
            Object[] array2 = { "a", 1.0, true };
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayWithEmptyArrays() {
            Object[] array1 = {};
            Object[] array2 = {};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(array1, array2);
            assertTrue(builder.isEquals());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendArrayWithDifferentTypes_2() {
            Object[] lhs = {1, "test", 3.14};
            Object[] rhs = {1, "test", 2.718};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayWithDifferentOrder() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {3, 2, 1};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayWithNullAndNonEmpty() {
            String[] lhs = {null, "test"};
            String[] rhs = {"test", null};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayWithAllNulls() {
            Object[] lhs = {null, null, null};
            Object[] rhs = {null, null, null};
            assertTrue(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayWithEmptyArrays_2() {
            String[] lhs = {};
            String[] rhs = {};
            assertTrue(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayWithDifferentLengthsAndNulls() {
            Integer[] lhs = {1, null};
            Integer[] rhs = {null, 2};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }
}
