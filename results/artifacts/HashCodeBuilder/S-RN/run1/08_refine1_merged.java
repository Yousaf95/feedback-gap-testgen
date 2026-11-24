package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

public class HashCodeBuilder_GenTest {

    
    
    
    
    
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

    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            HashCodeBuilder builder = new HashCodeBuilder();
            int hashCode = builder.append((Object) null).toHashCode();
            assertEquals(17, hashCode);
        }

    @Test
        public void testAppendArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            int[] array = {1, 2, 3};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + 1 * 37 * 37 + 2 * 37 * 37 * 37 + 3 * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            Object obj = new Object();
            int hashCode = builder.append(obj).toHashCode();
            assertEquals(17 * 37 + obj.hashCode(), hashCode);
        }

    @Test
        public void testAppendBooleanArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            boolean[] array = {true, false, true};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + (0 ^ 1) * 37 * 37 + (1 ^ 1) * 37 * 37 * 37 + (0 ^ 1) * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendByteArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            byte[] array = {1, 2, 3};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + 1 * 37 * 37 + 2 * 37 * 37 * 37 + 3 * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendCharArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            char[] array = {'a', 'b', 'c'};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + 'a' * 37 * 37 + 'b' * 37 * 37 * 37 + 'c' * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendFloatArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            float[] array = {1.0f, 2.0f, 3.0f};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + Float.floatToIntBits(1.0f) * 37 * 37 + Float.floatToIntBits(2.0f) * 37 * 37 * 37 + Float.floatToIntBits(3.0f) * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendDoubleArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            double[] array = {1.0, 2.0, 3.0};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + Double.doubleToLongBits(1.0) ^ (Double.doubleToLongBits(1.0) >> 32) * 37 * 37 + Double.doubleToLongBits(2.0) ^ (Double.doubleToLongBits(2.0) >> 32) * 37 * 37 * 37 + Double.doubleToLongBits(3.0) ^ (Double.doubleToLongBits(3.0) >> 32) * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendShortArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            short[] array = {1, 2, 3};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + 1 * 37 * 37 + 2 * 37 * 37 * 37 + 3 * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendLongArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            long[] array = {1L, 2L, 3L};
            int hashCode = builder.append(array).toHashCode();
            assertEquals(17 * 37 + (int) (1L ^ 1L >> 32) * 37 * 37 + (int) (2L ^ 2L >> 32) * 37 * 37 * 37 + (int) (3L ^ 3L >> 32) * 37 * 37 * 37 * 37, hashCode);
        }

    @Test
        public void testAppendSuper() {
            HashCodeBuilder builder = new HashCodeBuilder();
            int superHashCode = 42;
            int hashCode = builder.appendSuper(superHashCode).toHashCode();
            assertEquals(17 * 37 + 42, hashCode);
        }
}
