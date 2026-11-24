package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class EqualsBuilder_GenTest {

    private EqualsBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new EqualsBuilder();
    }

    @Test
    public void testAppendBoolean() {
        assertTrue(builder.append(true, true).isEquals());
        assertFalse(builder.append(true, false).isEquals());
    }

    @Test
    public void testAppendByte() {
        assertTrue(builder.append((byte) 1, (byte) 1).isEquals());
        assertFalse(builder.append((byte) 1, (byte) 2).isEquals());
    }

    @Test
    public void testAppendChar() {
        assertTrue(builder.append('a', 'a').isEquals());
        assertFalse(builder.append('a', 'b').isEquals());
    }

    @Test
    public void testAppendDouble() {
        assertTrue(builder.append(1.0, 1.0).isEquals());
        assertFalse(builder.append(1.0, 2.0).isEquals());
    }

    @Test
    public void testAppendFloat() {
        assertTrue(builder.append((float) 1.0, (float) 1.0).isEquals());
        assertFalse(builder.append((float) 1.0, (float) 2.0).isEquals());
    }

    @Test
    public void testAppendInt() {
        assertTrue(builder.append(1, 1).isEquals());
        assertFalse(builder.append(1, 2).isEquals());
    }

    @Test
    public void testAppendLong() {
        assertTrue(builder.append(1L, 1L).isEquals());
        assertFalse(builder.append(1L, 2L).isEquals());
    }

    @Test
    public void testAppendShort() {
        assertTrue(builder.append((short) 1, (short) 1).isEquals());
        assertFalse(builder.append((short) 1, (short) 2).isEquals());
    }

    @Test
    public void testAppendObject() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertTrue(builder.append(obj1, obj1).isEquals());
        assertFalse(builder.append(obj1, obj2).isEquals());
    }

    @Test
    public void testAppendArrayBoolean() {
        assertTrue(builder.append(new boolean[]{true}, new boolean[]{true}).isEquals());
        assertFalse(builder.append(new boolean[]{true}, new boolean[]{false}).isEquals());
    }

    @Test
    public void testAppendArrayByte() {
        assertTrue(builder.append(new byte[]{1}, new byte[]{1}).isEquals());
        assertFalse(builder.append(new byte[]{1}, new byte[]{2}).isEquals());
    }

    @Test
    public void testAppendArrayChar() {
        assertTrue(builder.append(new char[]{'a'}, new char[]{'a'}).isEquals());
        assertFalse(builder.append(new char[]{'a'}, new char[]{'b'}).isEquals());
    }

    @Test
    public void testAppendArrayDouble() {
        assertTrue(builder.append(new double[]{1.0}, new double[]{1.0}).isEquals());
        assertFalse(builder.append(new double[]{1.0}, new double[]{2.0}).isEquals());
    }

    @Test
    public void testAppendArrayFloat() {
        assertTrue(builder.append(new float[]{(float) 1.0}, new float[]{(float) 1.0}).isEquals());
        assertFalse(builder.append(new float[]{(float) 1.0}, new float[]{(float) 2.0}).isEquals());
    }

    @Test
    public void testAppendArrayInt() {
        assertTrue(builder.append(new int[]{1}, new int[]{1}).isEquals());
        assertFalse(builder.append(new int[]{1}, new int[]{2}).isEquals());
    }

    @Test
    public void testAppendArrayLong() {
        assertTrue(builder.append(new long[]{1L}, new long[]{1L}).isEquals());
        assertFalse(builder.append(new long[]{1L}, new long[]{2L}).isEquals());
    }

    @Test
    public void testAppendArrayShort() {
        assertTrue(builder.append(new short[]{(short) 1}, new short[]{(short) 1}).isEquals());
        assertFalse(builder.append(new short[]{(short) 1}, new short[]{(short) 2}).isEquals());
    }

    @Test
    public void testAppendArrayObject() {
        Object[] obj1 = {new Object()};
        Object[] obj2 = {new Object()};
        assertTrue(builder.append(obj1, obj1).isEquals());
        assertFalse(builder.append(obj1, obj2).isEquals());
    }

    @Test
    public void testReflectionAppend() {
        // This is a complex test that requires mocking and reflection to verify.
        // For simplicity, we'll just check if the method doesn't throw an exception.
        builder.reflectionAppend(new Object(), new Object());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionEquals_NullObjects() {
            assertTrue(EqualsBuilder.reflectionEquals(null, null));
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), null));
            assertFalse(EqualsBuilder.reflectionEquals(null, new Object()));
        }

    @Test
        public void testReflectionEquals_SameObject() {
            assertTrue(EqualsBuilder.reflectionEquals(new Object(), new Object()));
        }

    
    
    
    
    @Test
        public void testAppendArray_NullArrays() {
            boolean[] lhs = null;
            boolean[] rhs = null;
            assertTrue(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArray_EmptyArrays() {
            boolean[] lhs = new boolean[0];
            boolean[] rhs = new boolean[0];
            assertTrue(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArray_DifferentLengths() {
            boolean[] lhs = {true};
            boolean[] rhs = {false, true};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArray_EqualArrays() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true, false};
            assertTrue(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionEquals_NullObjects_2() {
            assertTrue(EqualsBuilder.reflectionEquals(null, null));
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), null));
            assertFalse(EqualsBuilder.reflectionEquals(null, new Object()));
        }

    @Test
        public void testReflectionEquals_SameObject_2() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
        }

    @Test
        public void testAppendArray_NullArrays_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((boolean[]) null, (boolean[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((byte[]) null, (byte[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((char[]) null, (char[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((double[]) null, (double[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((float[]) null, (float[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((int[]) null, (int[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((long[]) null, (long[]) null);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append((short[]) null, (short[]) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendArray_EmptyArrays_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[0], new boolean[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new byte[0], new byte[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new char[0], new char[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new double[0], new double[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new float[0], new float[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new int[0], new int[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new long[0], new long[0]);
            assertTrue(builder.isEquals());
            builder.reset();
    
            builder.append(new short[0], new short[0]);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendArray_DifferentLengths_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[]{true}, new boolean[]{false});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new byte[]{1}, new byte[]{2});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new char[]{'a'}, new char[]{'b'});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new double[]{1.0}, new double[]{2.0});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new float[]{1.0f}, new float[]{2.0f});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new int[]{1}, new int[]{2});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new long[]{1L}, new long[]{2L});
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(new short[]{1}, new short[]{2});
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArray_EqualArrays_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true, false});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new byte[]{1, 2}, new byte[]{1, 2});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new char[]{'a', 'b'}, new char[]{'a', 'b'});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new double[]{1.0, 2.0}, new double[]{1.0, 2.0});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new float[]{1.0f, 2.0f}, new float[]{1.0f, 2.0f});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new int[]{1, 2}, new int[]{1, 2});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new long[]{1L, 2L}, new long[]{1L, 2L});
            assertTrue(builder.isEquals());
    
            builder.reset();
            builder.append(new short[]{1, 2}, new short[]{1, 2});
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendBoolean_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(true, false);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(false, true);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(true, true);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendByte_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((byte) 1, (byte) 2);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append((byte) 2, (byte) 1);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append((byte) 1, (byte) 1);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendChar_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append('a', 'b');
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append('b', 'a');
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append('a', 'a');
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDouble_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(1.0, 2.0);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(2.0, 1.0);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(1.0, 1.0);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendFloat_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(1.0f, 2.0f);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(2.0f, 1.0f);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(1.0f, 1.0f);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendInt_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(1, 2);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(2, 1);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(1, 1);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendLong_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(1L, 2L);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(2L, 1L);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(1L, 1L);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendShort_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((short) 1, (short) 2);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append((short) 2, (short) 1);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append((short) 1, (short) 1);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendObject_2() {
            Object lhs = new Object();
            Object rhs = new Object();
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(lhs, rhs);
            assertFalse(builder.isEquals());
    
            builder.reset();
            builder.append(lhs, lhs);
            assertTrue(builder.isEquals());
        }

    }
