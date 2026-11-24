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
    @BeforeEach
        public void setUp_2() {
            EqualsBuilder.reset();
        }

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
        public void testReflectionEquals() {
            Object lhs = new Object();
            Object rhs = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(lhs, rhs, false));
            assertFalse(EqualsBuilder.reflectionEquals(null, rhs, false));
            assertFalse(EqualsBuilder.reflectionEquals(lhs, null, false));
        }

    @Test
        public void testAppendBoolean() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(true, true).isEquals());
            assertFalse(builder.append(true, false).isEquals());
        }

    @Test
        public void testAppendBooleanArray() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true, false};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new boolean[]{false, true}).isEquals());
        }

    @Test
        public void testAppendByte() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append((byte) 1, (byte) 1).isEquals());
            assertFalse(builder.append((byte) 1, (byte) 2).isEquals());
        }

    @Test
        public void testAppendByteArray() {
            byte[] lhs = {1, 2};
            byte[] rhs = {1, 2};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new byte[]{2, 1}).isEquals());
        }

    @Test
        public void testAppendChar() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append('a', 'a').isEquals());
            assertFalse(builder.append('a', 'b').isEquals());
        }

    @Test
        public void testAppendCharArray() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'a', 'b'};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new char[]{'b', 'a'}).isEquals());
        }

    @Test
        public void testAppendDouble() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(1.0, 1.0).isEquals());
            assertFalse(builder.append(1.0, 2.0).isEquals());
        }

    @Test
        public void testAppendDoubleArray() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0, 2.0};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new double[]{2.0, 1.0}).isEquals());
        }

    @Test
        public void testAppendFloat() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(1.0f, 1.0f).isEquals());
            assertFalse(builder.append(1.0f, 2.0f).isEquals());
        }

    @Test
        public void testAppendFloatArray() {
            float[] lhs = {1.0f, 2.0f};
            float[] rhs = {1.0f, 2.0f};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new float[]{2.0f, 1.0f}).isEquals());
        }

    @Test
        public void testAppendInt() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(1, 1).isEquals());
            assertFalse(builder.append(1, 2).isEquals());
        }

    @Test
        public void testAppendIntArray() {
            int[] lhs = {1, 2};
            int[] rhs = {1, 2};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new int[]{2, 1}).isEquals());
        }

    @Test
        public void testAppendLong() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(1L, 1L).isEquals());
            assertFalse(builder.append(1L, 2L).isEquals());
        }

    @Test
        public void testAppendLongArray() {
            long[] lhs = {1L, 2L};
            long[] rhs = {1L, 2L};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new long[]{2L, 1L}).isEquals());
        }

    @Test
        public void testAppendObject() {
            Object lhs = new Object();
            Object rhs = new Object();
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new Object()).isEquals());
        }

    @Test
        public void testAppendObjectArray() {
            Object[] lhs = {new Object(), new Object()};
            Object[] rhs = {new Object(), new Object()};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new Object[]{new Object(), null}).isEquals());
        }

    @Test
        public void testAppendShort() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append((short) 1, (short) 1).isEquals());
            assertFalse(builder.append((short) 1, (short) 2).isEquals());
        }

    @Test
        public void testAppendShortArray() {
            short[] lhs = {1, 2};
            short[] rhs = {1, 2};
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.append(lhs, rhs).isEquals());
            assertFalse(builder.append(lhs, new short[]{2, 1}).isEquals());
        }

    @Test
        public void testAppendSuper_2() {
            EqualsBuilder builder = new EqualsBuilder();
            assertTrue(builder.appendSuper(true).isEquals());
            assertFalse(builder.appendSuper(false).isEquals());
        }
}
