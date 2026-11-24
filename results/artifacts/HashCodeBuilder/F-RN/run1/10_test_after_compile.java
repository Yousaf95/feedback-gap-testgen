package org.apache.commons.lang3.builder;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    @Test
    public void testHashCodeBuilderDefaultConstructor() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(17, builder.toHashCode());
    }

    
    
    @Test
    public void testAppendByte() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((byte) 5);
        assertEquals(17 * 37 + 5, builder.toHashCode());
    }

    
    @Test
    public void testAppendChar() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append('a');
        assertEquals(17 * 37 + 'a', builder.toHashCode());
    }

    
    
    
    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(1.41f);
        assertEquals(17 * 37 + Float.floatToIntBits(1.41f), builder.toHashCode());
    }

    
    @Test
    public void testAppendInt() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    
    @Test
    public void testAppendLong() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(987654321L);
        assertEquals(17 * 37 + (int) (987654321L ^ 987654321L >> 32), builder.toHashCode());
    }

    
    @Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(17 * 37 + obj.hashCode(), builder.toHashCode());
    }

    
    @Test
    public void testAppendShort() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((short) 25);
        assertEquals(17 * 37 + 25, builder.toHashCode());
    }

    
    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDouble() {
            double value = 123.456;
            int expectedHashCode = (int) (value * 37 + 17);
            assertEquals(expectedHashCode, new HashCodeBuilder().append(value).toHashCode());
        }

    @Test
        public void testAppendFloatArray() {
            float[] array = {1.0f, 2.0f, 3.0f};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendLongArray() {
            long[] array = {1L, 2L, 3L};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendShortArray() {
            short[] array = {1, 2, 3};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendCharArray() {
            char[] array = {'a', 'b', 'c'};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendByteArray() {
            byte[] array = {1, 2, 3};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendBooleanArray() {
            boolean[] array = {true, false, true};
            int expectedHashCode = (int) ((array[0] * 37 + 17) * 37 + (array[1] * 37 + 17) * 37 + (array[2] * 37 + 17));
            assertEquals(expectedHashCode, new HashCodeBuilder().append(array).toHashCode());
        }

    @Test
        public void testAppendNullArray() {
            int expectedHashCode = 17;
            assertEquals(expectedHashCode, new HashCodeBuilder().append((Object[]) null).toHashCode());
        }
}
