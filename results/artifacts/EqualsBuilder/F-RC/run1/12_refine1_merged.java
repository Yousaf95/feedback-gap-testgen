package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashSet;
import java.util.Set;

class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void testAppendArrayBoolean() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, false};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayByte() {
        byte[] lhs = {(byte) 0, (byte) 1};
        byte[] rhs = {(byte) 0, (byte) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayChar() {
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'b'};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayDouble() {
        double[] lhs = {0.0, 1.0};
        double[] rhs = {0.0, 1.0};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayFloat() {
        float[] lhs = {0.0f, 1.0f};
        float[] rhs = {0.0f, 1.0f};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayInt() {
        int[] lhs = {0, 1};
        int[] rhs = {0, 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayLong() {
        long[] lhs = {0L, 1L};
        long[] rhs = {0L, 1L};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayShort() {
        short[] lhs = {(short) 0, (short) 1};
        short[] rhs = {(short) 0, (short) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertFalse(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectNull() {
        Object lhs = null;
        Object rhs = null;
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectSame() {
        Object obj = new Object();
        assertTrue(equalsBuilder.append(obj, obj).isEquals());
    }

    
    
    @Test
    void testReflectionEqualsSameObject() {
        Object obj = new Object();
        assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
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
        public void testReflectionEqualsSameObject_2() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj, false));
        }

    @Test
        public void testAppendBoolean() {
            EqualsBuilder builder = new EqualsBuilder().append(true, true);
            assertTrue(builder.isEquals());
            builder.append(false, false);
            assertTrue(builder.isEquals());
            builder.append(true, false);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendByte() {
            EqualsBuilder builder = new EqualsBuilder().append((byte) 1, (byte) 1);
            assertTrue(builder.isEquals());
            builder.append((byte) 2, (byte) 2);
            assertTrue(builder.isEquals());
            builder.append((byte) 1, (byte) 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendChar() {
            EqualsBuilder builder = new EqualsBuilder().append('a', 'a');
            assertTrue(builder.isEquals());
            builder.append('b', 'b');
            assertTrue(builder.isEquals());
            builder.append('a', 'b');
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendDouble() {
            EqualsBuilder builder = new EqualsBuilder().append(1.0, 1.0);
            assertTrue(builder.isEquals());
            builder.append(2.0, 2.0);
            assertTrue(builder.isEquals());
            builder.append(1.0, 2.0);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendFloat() {
            EqualsBuilder builder = new EqualsBuilder().append(1.0f, 1.0f);
            assertTrue(builder.isEquals());
            builder.append(2.0f, 2.0f);
            assertTrue(builder.isEquals());
            builder.append(1.0f, 2.0f);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendInt() {
            EqualsBuilder builder = new EqualsBuilder().append(1, 1);
            assertTrue(builder.isEquals());
            builder.append(2, 2);
            assertTrue(builder.isEquals());
            builder.append(1, 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendLong() {
            EqualsBuilder builder = new EqualsBuilder().append(1L, 1L);
            assertTrue(builder.isEquals());
            builder.append(2L, 2L);
            assertTrue(builder.isEquals());
            builder.append(1L, 2L);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendShort() {
            EqualsBuilder builder = new EqualsBuilder().append((short) 1, (short) 1);
            assertTrue(builder.isEquals());
            builder.append((short) 2, (short) 2);
            assertTrue(builder.isEquals());
            builder.append((short) 1, (short) 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendObject_2() {
            Object obj1 = new Object();
            Object obj2 = new Object();
            EqualsBuilder builder = new EqualsBuilder().append(obj1, obj1);
            assertTrue(builder.isEquals());
            builder.append(obj1, obj2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendObjectNull_2() {
            Object obj = null;
            EqualsBuilder builder = new EqualsBuilder().append(obj, null);
            assertTrue(builder.isEquals());
            builder.append(null, obj);
            assertTrue(builder.isEquals());
            builder.append(obj, new Object());
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendObjectSame_2() {
            Object obj = new Object();
            EqualsBuilder builder = new EqualsBuilder().append(obj, obj);
            assertTrue(builder.isEquals());
        }
}
