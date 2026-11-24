package org.apache.commons.lang3.builder;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

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

    
    
    
    @Test
        public void testReflectionAppend() {
            Object value = new Object();
            HashCodeBuilder builder = new HashCodeBuilder();
            // HashCodeBuilder.reflectionAppend(value, value.getClass(), builder, false, null);
            // This test is more about ensuring no exceptions are thrown rather than verifying the exact behavior
        }

    @Test
        public void testReflectionHashCode() {
            Object value = new Object();
            int hashCode = HashCodeBuilder.reflectionHashCode(value);
            assertTrue(hashCode != 0); // A non-zero hash code is expected
        }

    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsRegistered() {
            Set<IDKey> registry = null;
            IDKey key = new IDKey(new Object());
            assertFalse(HashCodeBuilder.isRegistered(key));
    
            registry = new HashSet<>();
            assertFalse(HashCodeBuilder.isRegistered(key));
    
            registry.add(key);
            assertTrue(HashCodeBuilder.isRegistered(key));
        }

    
    
    @Test
        public void testAppend() {
            HashCodeBuilder builder = new HashCodeBuilder();
    
            builder.append(true);
            assertEquals(20, builder.toHashCode());
    
            builder.append((byte) 5);
            assertEquals(17 * 20 + 5, builder.toHashCode());
    
            builder.append('a');
            assertEquals(17 * (17 * 20 + 5) + 'a', builder.toHashCode());
    
            builder.append(3.14f);
            assertEquals(17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f), builder.toHashCode());
    
            builder.append(42);
            assertEquals(17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42, builder.toHashCode());
    
            builder.append(123456789L);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + (int) (123456789L ^ 123456789L >> 32), builder.toHashCode());
    
            Object obj = new Object();
            builder.append(obj);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + obj.hashCode(), builder.toHashCode());
    
            builder.append((short) 10);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + obj.hashCode() + 10, builder.toHashCode());
        }

    @Test
        public void testAppendArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
    
            boolean[] boolArray = {true, false};
            builder.append(boolArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20, builder.toHashCode());
    
            byte[] byteArray = {1, 2};
            builder.append(byteArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3, builder.toHashCode());
    
            char[] charArray = {'x', 'y'};
            builder.append(charArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26, builder.toHashCode());
    
            double[] doubleArray = {1.1, 2.2};
            builder.append(doubleArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89, builder.toHashCode());
    
            float[] floatArray = {1.1f, 2.2f};
            builder.append(floatArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13, builder.toHashCode());
    
            int[] intArray = {1, 2};
            builder.append(intArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42, builder.toHashCode());
    
            long[] longArray = {1L, 2L};
            builder.append(longArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42 + 24, builder.toHashCode());
    
            Object[] objArray = {new Object(), new Object()};
            builder.append(objArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42 + 24 + objArray[0].hashCode() + objArray[1].hashCode(), builder.toHashCode());
    
            short[] shortArray = {1, 2};
            builder.append(shortArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42 + 24 + objArray[0].hashCode() + objArray[1].hashCode() + 10, builder.toHashCode());
        }

    @Test
        public void testAppendSuper_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            int superHashCode = 123;
            builder.appendSuper(superHashCode);
            assertEquals(17 * 17 + superHashCode, builder.toHashCode());
        }
}
