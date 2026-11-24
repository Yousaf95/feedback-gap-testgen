package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    @Test
    public void testConstructorDefault() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(17, builder.iTotal);
        assertEquals(37, builder.iConstant);
    }

    @Test
    public void testConstructorCustom() {
        HashCodeBuilder builder = new HashCodeBuilder(53, 41);
        assertEquals(53, builder.iTotal);
        assertEquals(41, builder.iConstant);
    }

    @Test
    public void testAppendBoolean() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(true);
        assertEquals(17 * 37 + 0, builder.toHashCode());
        builder.append(false);
        assertEquals(17 * 37 * 37 + 1, builder.toHashCode());
    }

    @Test
    public void testAppendBooleanArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new boolean[]{true, false});
        assertEquals(17 * 37 * 37 + (0 * 37 + 1), builder.toHashCode());
    }

    @Test
    public void testAppendByte() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((byte) 5);
        assertEquals(17 * 37 + 5, builder.toHashCode());
    }

    @Test
    public void testAppendByteArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new byte[]{10, 20});
        assertEquals(17 * 37 * 37 + (10 * 37 + 20), builder.toHashCode());
    }

    @Test
    public void testAppendChar() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append('a');
        assertEquals(17 * 37 + 'a', builder.toHashCode());
    }

    @Test
    public void testAppendCharArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new char[]{'x', 'y'});
        assertEquals(17 * 37 * 37 + ('x' * 37 + 'y'), builder.toHashCode());
    }

    @Test
    public void testAppendDouble() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(3.14);
        long bits = Double.doubleToLongBits(3.14);
        assertEquals(17 * 37 + (int) (bits ^ bits >> 32), builder.toHashCode());
    }

    @Test
    public void testAppendDoubleArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new double[]{2.71, 0.5});
        long bits1 = Double.doubleToLongBits(2.71);
        long bits2 = Double.doubleToLongBits(0.5);
        assertEquals(17 * 37 * 37 + ((int) (bits1 ^ bits1 >> 32) * 37 + (int) (bits2 ^ bits2 >> 32)), builder.toHashCode());
    }

    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(1.41f);
        int bits = Float.floatToIntBits(1.41f);
        assertEquals(17 * 37 + bits, builder.toHashCode());
    }

    @Test
    public void testAppendFloatArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new float[]{1.62f, 0.85f});
        int bits1 = Float.floatToIntBits(1.62f);
        int bits2 = Float.floatToIntBits(0.85f);
        assertEquals(17 * 37 * 37 + (bits1 * 37 + bits2), builder.toHashCode());
    }

    @Test
    public void testAppendInt() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    @Test
    public void testAppendIntArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new int[]{99, -1});
        assertEquals(17 * 37 * 37 + (99 * 37 - 1), builder.toHashCode());
    }

    @Test
    public void testAppendLong() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(123456789L);
        assertEquals(17 * 37 + (int) (123456789L ^ 123456789L >> 32), builder.toHashCode());
    }

    @Test
    public void testAppendLongArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new long[]{987654321L, -1000000000L});
        assertEquals(17 * 37 * 37 + ((int) (987654321L ^ 987654321L >> 32) * 37 + (int) (-1000000000L ^ -1000000000L >> 32)), builder.toHashCode());
    }

    @Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(17 * 37 + obj.hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendObjectArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object[] array = {new Object(), null};
        builder.append(array);
        assertEquals(17 * 37 * 37 + (array[0].hashCode() * 37), builder.toHashCode());
    }

    @Test
    public void testAppendShort() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((short) 25);
        assertEquals(17 * 37 + 25, builder.toHashCode());
    }

    @Test
    public void testAppendShortArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new short[]{30, -5});
        assertEquals(17 * 37 * 37 + (30 * 37 - 5), builder.toHashCode());
    }

    @Test
    public void testAppendSuper() {
        class SuperClass {
            @Override
            public int hashCode() {
                return 42;
            }
        }
        class SubClass extends SuperClass {
            private final HashCodeBuilder builder = new HashCodeBuilder();
            public SubClass() {
                builder.appendSuper(super.hashCode());
            }
            public int getHashCode() {
                return builder.toHashCode();
            }
        }
        SubClass sub = new SubClass();
        assertEquals(42 * 37 + 17, sub.getHashCode());
    }

    @Test
    public void testBuild() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42);
        Integer hash = builder.build();
        assertNotNull(hash);
        assertEquals(17 * 37 + 42, hash.intValue());
    }

    @Test
    public void testEquals() {
        HashCodeBuilder builder1 = new HashCodeBuilder();
        builder1.append(42);
        HashCodeBuilder builder2 = new HashCodeBuilder();
        builder2.append(42);
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42);
        assertEquals(17 * 37 + 42, builder.hashCode());
    }
}
