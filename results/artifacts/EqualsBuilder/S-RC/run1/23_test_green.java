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
import java.util.HashSet;
import java.util.Set;

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
        public void testReflectionEquals_withNulls() {
            Object lhs = null;
            Object rhs = null;
            assertTrue(EqualsBuilder.reflectionEquals(lhs, rhs, false));
        }

    @Test
        public void testReflectionEquals_withEqualObjects() {
            Object lhs = new Object();
            Object rhs = lhs;
            assertTrue(EqualsBuilder.reflectionEquals(lhs, rhs, false));
        }

    }
