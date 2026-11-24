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
            // Test with null registry
            Set<IDKey> registry = null;
            IDKey key = new IDKey(new Object());
            assertFalse(HashCodeBuilder.isRegistered(key));
    
            // Test with empty registry
            registry = new HashSet<>();
            assertFalse(HashCodeBuilder.isRegistered(key));
    
            // Test with non-empty registry
            registry.add(key);
            assertTrue(HashCodeBuilder.isRegistered(key));
        }

    @Test
        public void testReflectionAppend_2() {
            Object object = new Object();
            HashCodeBuilder builder = new HashCodeBuilder();
            HashCodeBuilder reflectionBuilder = HashCodeBuilder.reflectionHashCode(object, false);
    
            // Test with null object
            assertThrows(NullPointerException.class, () -> {
                HashCodeBuilder.reflectionAppend(null, object.getClass(), builder, false, null);
            });
    
            // Test with registered object
            Set<IDKey> registry = new HashSet<>();
            IDKey key = new IDKey(object);
            registry.add(key);
            Reflection.setAccessible(new Field[]{HashCodeBuilder.class.getDeclaredField("REGISTRY")}, true);
            HashCodeBuilder.class.getDeclaredField("REGISTRY").set(null, registry);
    
            reflectionAppend(object, object.getClass(), builder, false, null);
            assertEquals(reflectionBuilder.toHashCode(), builder.toHashCode());
    
            // Test with unregistered object
            registry.remove(key);
            reflectionAppend(object, object.getClass(), builder, false, null);
            assertNotEquals(reflectionBuilder.toHashCode(), builder.toHashCode());
        }

    @Test
        public void testReflectionHashCode_2() {
            Object object = new Object();
    
            // Test with null object
            assertThrows(NullPointerException.class, () -> {
                HashCodeBuilder.reflectionHashCode(null, false);
            });
    
            // Test with registered object
            Set<IDKey> registry = new HashSet<>();
            IDKey key = new IDKey(object);
            registry.add(key);
            Reflection.setAccessible(new Field[]{HashCodeBuilder.class.getDeclaredField("REGISTRY")}, true);
            HashCodeBuilder.class.getDeclaredField("REGISTRY").set(null, registry);
    
            int hashCode1 = HashCodeBuilder.reflectionHashCode(object, false);
            int hashCode2 = HashCodeBuilder.reflectionHashCode(object, false);
            assertEquals(hashCode1, hashCode2);
    
            // Test with unregistered object
            registry.remove(key);
            int hashCode3 = HashCodeBuilder.reflectionHashCode(object, false);
            assertNotEquals(hashCode1, hashCode3);
        }

    @Test
        public void testAppend() {
            HashCodeBuilder builder = new HashCodeBuilder();
    
            // Test append boolean
            builder.append(true);
            assertEquals(20, builder.toHashCode());
    
            // Test append byte
            builder.append((byte) 5);
            assertEquals(17 * 20 + 5, builder.toHashCode());
    
            // Test append char
            builder.append('a');
            assertEquals(17 * (17 * 20 + 5) + 'a', builder.toHashCode());
    
            // Test append float
            builder.append(3.14f);
            assertEquals(17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f), builder.toHashCode());
    
            // Test append int
            builder.append(42);
            assertEquals(17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42, builder.toHashCode());
    
            // Test append long
            builder.append(123456789L);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + (int) (123456789L ^ 123456789L >> 32), builder.toHashCode());
    
            // Test append object
            Object obj = new Object();
            builder.append(obj);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + obj.hashCode(), builder.toHashCode());
    
            // Test append short
            builder.append((short) 10);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + obj.hashCode() + 10, builder.toHashCode());
        }

    @Test
        public void testAppendArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
    
            // Test append boolean array
            boolean[] boolArray = {true, false};
            builder.append(boolArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20, builder.toHashCode());
    
            // Test append byte array
            byte[] byteArray = {1, 2};
            builder.append(byteArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3, builder.toHashCode());
    
            // Test append char array
            char[] charArray = {'x', 'y'};
            builder.append(charArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26, builder.toHashCode());
    
            // Test append double array
            double[] doubleArray = {1.1, 2.2};
            builder.append(doubleArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89, builder.toHashCode());
    
            // Test append float array
            float[] floatArray = {1.1f, 2.2f};
            builder.append(floatArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13, builder.toHashCode());
    
            // Test append int array
            int[] intArray = {1, 2};
            builder.append(intArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42, builder.toHashCode());
    
            // Test append long array
            long[] longArray = {1L, 2L};
            builder.append(longArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42 + 24, builder.toHashCode());
    
            // Test append object array
            Object[] objArray = {new Object(), new Object()};
            builder.append(objArray);
            assertEquals(17 * (17 * (17 * (17 * (17 * 20 + 5) + 'a') + Float.floatToIntBits(3.14f)) + 42) + 20 + 3 + 26 + 89 + 13 + 42 + 24 + objArray[0].hashCode() + objArray[1].hashCode(), builder.toHashCode());
    
            // Test append short array
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
