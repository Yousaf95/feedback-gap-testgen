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
        HashCodeBuilder builder = new HashCodeBuilder(23, 47);
        assertEquals(23, builder.iTotal);
        assertEquals(47, builder.iConstant);
    }

    @Test
    public void testAppendBoolean() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(true).append(false);
        assertEquals(17 * 37 + 0 + 1, builder.toHashCode());
    }

    @Test
    public void testAppendByte() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((byte) 5).append((byte) -3);
        assertEquals(17 * 37 + 5 + (-3), builder.toHashCode());
    }

    @Test
    public void testAppendChar() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append('a').append('z');
        assertEquals(17 * 37 + 'a' + 'z', builder.toHashCode());
    }

    @Test
    public void testAppendDouble() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(3.14).append(-2.71);
        long expected = (long) (17 * 37 + Double.doubleToLongBits(3.14) + Double.doubleToLongBits(-2.71));
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(1.0f).append(-1.0f);
        int expected = 17 * 37 + Float.floatToIntBits(1.0f) + Float.floatToIntBits(-1.0f);
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendInt() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42).append(-42);
        assertEquals(17 * 37 + 42 + (-42), builder.toHashCode());
    }

    @Test
    public void testAppendLong() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(123456789L).append(-123456789L);
        long expected = 17 * 37 + (int) (123456789L ^ 123456789L >> 32) + (int) (-123456789L ^ -123456789L >> 32);
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(17 * 37 + obj.hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendNull() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object) null);
        assertEquals(17 * 37, builder.toHashCode());
    }

    @Test
    public void testAppendArrayBoolean() {
        HashCodeBuilder builder = new HashCodeBuilder();
        boolean[] array = {true, false, true};
        builder.append(array);
        assertEquals(17 * 37 + 0 + 1 + 1, builder.toHashCode());
    }

    @Test
    public void testAppendArrayByte() {
        HashCodeBuilder builder = new HashCodeBuilder();
        byte[] array = {(byte) 5, (byte) -3};
        builder.append(array);
        assertEquals(17 * 37 + 5 + (-3), builder.toHashCode());
    }

    @Test
    public void testAppendArrayChar() {
        HashCodeBuilder builder = new HashCodeBuilder();
        char[] array = {'a', 'z'};
        builder.append(array);
        assertEquals(17 * 37 + 'a' + 'z', builder.toHashCode());
    }

    @Test
    public void testAppendArrayDouble() {
        HashCodeBuilder builder = new HashCodeBuilder();
        double[] array = {3.14, -2.71};
        long expected = (long) (17 * 37 + Double.doubleToLongBits(3.14) + Double.doubleToLongBits(-2.71));
        for (double value : array) {
            builder.append(value);
        }
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendArrayFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        float[] array = {1.0f, -1.0f};
        int expected = 17 * 37 + Float.floatToIntBits(1.0f) + Float.floatToIntBits(-1.0f);
        for (float value : array) {
            builder.append(value);
        }
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendArrayInt() {
        HashCodeBuilder builder = new HashCodeBuilder();
        int[] array = {42, -42};
        builder.append(array);
        assertEquals(17 * 37 + 42 + (-42), builder.toHashCode());
    }

    @Test
    public void testAppendArrayLong() {
        HashCodeBuilder builder = new HashCodeBuilder();
        long[] array = {123456789L, -123456789L};
        long expected = 17 * 37 + (int) (123456789L ^ 123456789L >> 32) + (int) (-123456789L ^ -123456789L >> 32);
        for (long value : array) {
            builder.append(value);
        }
        assertEquals(expected, builder.toHashCode());
    }

    @Test
    public void testAppendArrayObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object[] array = {new Object(), new Object()};
        builder.append(array);
        assertEquals(17 * 37 + array[0].hashCode() + array[1].hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendArrayNull() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object[]) null);
        assertEquals(17 * 37, builder.toHashCode());
    }

    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        int superHashCode = 42;
        builder.appendSuper(superHashCode);
        assertEquals(17 * 37 + superHashCode, builder.toHashCode());
    }

    @Test
    public void testBuild() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Integer hashCode = builder.build();
        assertNotNull(hashCode);
        assertEquals(builder.toHashCode(), hashCode.intValue());
    }

    @Test
    public void testEquals() {
        HashCodeBuilder builder1 = new HashCodeBuilder().append(42);
        HashCodeBuilder builder2 = new HashCodeBuilder().append(42);
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(builder.toHashCode(), builder.hashCode());
    }
}
