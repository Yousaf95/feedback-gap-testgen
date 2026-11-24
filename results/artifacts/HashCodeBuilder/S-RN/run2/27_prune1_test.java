package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    
    
    @Test
    public void testAppendBoolean() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(true);
        assertEquals(17 * 37 + 0, builder.toHashCode());
        builder.append(false);
        assertEquals(17 * 37 * 37 + 1, builder.toHashCode());
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
        builder.append('A');
        assertEquals(17 * 37 + 'A', builder.toHashCode());
    }

    
    
    
    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(2.718f);
        assertEquals(17 * 37 + Float.floatToIntBits(2.718f), builder.toHashCode());
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
        builder.append(123456789L);
        assertEquals(17 * 37 + (int) (123456789L ^ 123456789L >> 32), builder.toHashCode());
    }

    
    
    
    @Test
    public void testAppendShort() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((short) 10);
        assertEquals(17 * 37 + 10, builder.toHashCode());
    }

    
    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
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
        HashCodeBuilder builder1 = new HashCodeBuilder(23, 47).append(1).append(2);
        HashCodeBuilder builder2 = new HashCodeBuilder(23, 47).append(1).append(2);
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(builder.toHashCode(), builder.hashCode());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDouble() {
            HashCodeBuilder builder = new HashCodeBuilder();
            double value = 123.456;
            builder.append(value);
            assertEquals(17 * 37 + Double.doubleToLongBits(value), builder.toHashCode());
        }

    @Test
        public void testAppendFloatArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            float[] array = {1.0f, 2.0f, 3.0f};
            builder.append(array);
            assertEquals(17 * 37 + Float.floatToIntBits(1.0f) * 37 + Float.floatToIntBits(2.0f) * 37 + Float.floatToIntBits(3.0f), builder.toHashCode());
        }

    @Test
        public void testAppendLongArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            long[] array = {1L, 2L, 3L};
            builder.append(array);
            assertEquals(17 * 37 + (int) (1L ^ 1L >> 32) * 37 + (int) (2L ^ 2L >> 32) * 37 + (int) (3L ^ 3L >> 32), builder.toHashCode());
        }

    
    @Test
        public void testAppendNullObjectArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            Object[] array = null;
            builder.append(array);
            assertEquals(17 * 37, builder.toHashCode());
        }
}
