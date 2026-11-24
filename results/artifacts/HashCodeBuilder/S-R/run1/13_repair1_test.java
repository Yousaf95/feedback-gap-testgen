package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class HashCodeBuilder_GenTest {

    @ParameterizedTest
    @ValueSource(ints = {17, 37})
    public void testConstructorWithValidValues(int value) {
        HashCodeBuilder builder = new HashCodeBuilder(value, value);
        assertEquals(17 * value + value, builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    public void testAppendBoolean(boolean value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + (value ? 0 : 1), builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    public void testAppendByte(byte value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + value, builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    public void testAppendChar(char value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + value, builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    public void testAppendShort(short value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + value, builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10})
    public void testAppendInt(int value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + value, builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 2L, 4L, 6L, 8L, 10L})
    public void testAppendLong(long value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + (int) (value ^ value >> 32), builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(floats = {0.0f, 2.0f, 4.0f, 6.0f, 8.0f, 10.0f})
    public void testAppendFloat(float value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + Float.floatToIntBits(value), builder.toHashCode());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 2.0, 4.0, 6.0, 8.0, 10.0})
    public void testAppendDouble(double value) {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(value);
        assertEquals(37 * 17 + (int) (Double.doubleToLongBits(value) ^ Double.doubleToLongBits(value) >> 32), builder.toHashCode());
    }

    @ParameterizedTest
    public void testAppendNull() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object) null);
        assertEquals(17 * 37, builder.toHashCode());
    }

    @ParameterizedTest
    public void testAppendArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        int[] array = {1, 2, 3};
        builder.append(array);
        assertEquals(37 * 17 + 1 + 2 + 3, builder.toHashCode());
    }

    @ParameterizedTest
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(37 * 17 + obj.hashCode(), builder.toHashCode());
    }

    @ParameterizedTest
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        int superHashCode = 42;
        builder.appendSuper(superHashCode);
        assertEquals(37 * 17 + superHashCode, builder.toHashCode());
    }
}
