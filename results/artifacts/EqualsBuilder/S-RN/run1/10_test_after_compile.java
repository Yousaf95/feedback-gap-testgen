package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    public void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendSuper() {
        assertTrue(equalsBuilder.appendSuper(true).isEquals());
        assertFalse(equalsBuilder.appendSuper(false).isEquals());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object) null, (Object) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDifferentTypes() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(123, "123");
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object[]) null, (Object[]) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendArrayDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new Integer[]{1, 2}, new Integer[]{1, 2, 3});
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayDifferentValues() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new Integer[]{1, 2}, new Integer[]{1, 3});
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendCollectionNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Collection<?>) null, (Collection<?>) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendCollectionDifferentSize() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(List.of(1, 2), List.of(1, 2, 3));
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendCollectionDifferentValues() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(List.of(1, 2), List.of(1, 3));
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendSetNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Set<?>) null, (Set<?>) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendSetDifferentSize() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(Set.of(1, 2), Set.of(1, 2, 3));
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendSetDifferentValues() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(Set.of(1, 2), Set.of(1, 3));
            assertFalse(builder.isEquals());
        }
}
