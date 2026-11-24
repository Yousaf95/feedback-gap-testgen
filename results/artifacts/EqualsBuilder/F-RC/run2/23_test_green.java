package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(builder.append(1.0f, 1.0f).isEquals());
        assertFalse(builder.append(1.0f, 2.0f).isEquals());
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
        boolean[] arr1 = {true, false};
        boolean[] arr2 = {true, false};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = false;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayByte() {
        byte[] arr1 = {(byte) 1, (byte) 2};
        byte[] arr2 = {(byte) 1, (byte) 2};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = (byte) 3;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayChar() {
        char[] arr1 = {'a', 'b'};
        char[] arr2 = {'a', 'b'};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = 'c';
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayDouble() {
        double[] arr1 = {1.0, 2.0};
        double[] arr2 = {1.0, 2.0};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = 3.0;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayFloat() {
        float[] arr1 = {1.0f, 2.0f};
        float[] arr2 = {1.0f, 2.0f};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = 3.0f;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayInt() {
        int[] arr1 = {1, 2};
        int[] arr2 = {1, 2};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = 3;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayLong() {
        long[] arr1 = {1L, 2L};
        long[] arr2 = {1L, 2L};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = 3L;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    @Test
    public void testAppendArrayShort() {
        short[] arr1 = {(short) 1, (short) 2};
        short[] arr2 = {(short) 1, (short) 2};
        assertTrue(builder.append(arr1, arr2).isEquals());
        arr2[0] = (short) 3;
        assertFalse(builder.append(arr1, arr2).isEquals());
    }

    
    @Test
    public void testAppendSuper() {
        assertTrue(builder.appendSuper(true).isEquals());
        assertFalse(builder.appendSuper(false).isEquals());
    }

    @Test
    public void testIsEquals() {
        assertTrue(new EqualsBuilder().append(1, 1).isEquals());
        assertFalse(new EqualsBuilder().append(1, 2).isEquals());
    }

    @Test
    public void testReset() {
        builder.append(1, 2);
        builder.reset();
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionEquals_withNulls() {
            Object lhs = null;
            Object rhs = new Object();
            assertFalse(EqualsBuilder.reflectionEquals(lhs, rhs, false));
        }

    @Test
        public void testReflectionEquals_withEqualObjects() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj, false));
        }

    
    @Test
        public void testAppendBoolean_trueTrue() {
            EqualsBuilder builder = new EqualsBuilder().append(true, true);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendBoolean_falseFalse() {
            EqualsBuilder builder = new EqualsBuilder().append(false, false);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendBoolean_trueFalse() {
            EqualsBuilder builder = new EqualsBuilder().append(true, false);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendByte_byte1_byte2() {
            EqualsBuilder builder = new EqualsBuilder().append((byte) 1, (byte) 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendChar_charA_charB() {
            EqualsBuilder builder = new EqualsBuilder().append('a', 'b');
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendDouble_double1_double2() {
            EqualsBuilder builder = new EqualsBuilder().append(1.0, 2.0);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendFloat_float1_float2() {
            EqualsBuilder builder = new EqualsBuilder().append(1.0f, 2.0f);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendInt_int1_int2() {
            EqualsBuilder builder = new EqualsBuilder().append(1, 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendLong_long1_long2() {
            EqualsBuilder builder = new EqualsBuilder().append(1L, 2L);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendShort_short1_short2() {
            EqualsBuilder builder = new EqualsBuilder().append((short) 1, (short) 2);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendObject_nullNull() {
            Object obj = null;
            EqualsBuilder builder = new EqualsBuilder().append(obj, obj);
            assertTrue(builder.isEquals());
        }
}
