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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetRegisterPair() {
            Object lhs = new Object();
            Object rhs = new Object();
            Pair<IDKey, IDKey> pair = EqualsBuilder.getRegisterPair(lhs, rhs);
            assertNotNull(pair);
            assertEquals(new IDKey(lhs), pair.getLeft());
            assertEquals(new IDKey(rhs), pair.getRight());
        }

    @Test
        public void testGetRegistry() {
            Set<Pair<IDKey, IDKey>> registry = EqualsBuilder.getRegistry();
            assertNotNull(registry);
        }

    @Test
        public void testIsRegistered() {
            Object lhs = new Object();
            Object rhs = new Object();
            assertFalse(EqualsBuilder.isRegistered(lhs, rhs));
            EqualsBuilder.register(lhs, rhs);
            assertTrue(EqualsBuilder.isRegistered(lhs, rhs));
            EqualsBuilder.unregister(lhs, rhs);
            assertFalse(EqualsBuilder.isRegistered(lhs, rhs));
        }

    @Test
        public void testReflectionEquals_withNulls_2() {
            Object lhs = null;
            Object rhs = null;
            assertTrue(EqualsBuilder.reflectionEquals(lhs, rhs));
        }

    @Test
        public void testReflectionEquals_withEqualObjects_2() {
            Object lhs = new Object();
            Object rhs = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(lhs, rhs));
        }
}
