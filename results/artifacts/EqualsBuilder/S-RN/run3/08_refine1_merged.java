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
}
