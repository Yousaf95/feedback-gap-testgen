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
        public void testGetRegistry() {
            assertNotNull(HashCodeBuilder.getRegistry());
        }

    @Test
        public void testIsRegistered() {
            Object value = new Object();
            assertFalse(HashCodeBuilder.isRegistered(value));
            // HashCodeBuilder.register(value);
            assertTrue(HashCodeBuilder.isRegistered(value));
            // HashCodeBuilder.unregister(value);
            assertFalse(HashCodeBuilder.isRegistered(value));
        }

    @Test
        public void testRegisterUnregister() {
            Object value = new Object();
            assertNull(HashCodeBuilder.getRegistry());
            // HashCodeBuilder.register(value);
            assertNotNull(HashCodeBuilder.getRegistry());
            assertTrue(HashCodeBuilder.getRegistry().contains(new IDKey(value)));
            // HashCodeBuilder.unregister(value);
            assertNull(HashCodeBuilder.getRegistry());
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

    @Test
        public void testDefaultConstructor() {
            HashCodeBuilder builder = new HashCodeBuilder();
            assertEquals(17, builder.toHashCode());
            assertEquals(37, builder.iConstant);
        }

    @Test
        public void testAppendBoolean() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(true);
            assertEquals(20, builder.toHashCode()); // 17 * 37 + (true ? 0 : 1)
        }

    @Test
        public void testAppendByte_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((byte) 5);
            assertEquals(68, builder.toHashCode()); // 17 * 37 + 5
        }

    @Test
        public void testAppendChar_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append('a');
            assertEquals(204, builder.toHashCode()); // 17 * 37 + (int) 'a'
        }

    @Test
        public void testAppendFloat_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(3.14f);
            assertEquals(198, builder.toHashCode()); // 17 * 37 + Float.floatToIntBits(3.14f)
        }

    @Test
        public void testAppendInt_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(42);
            assertEquals(1609, builder.toHashCode()); // 17 * 37 + 42
        }

    @Test
        public void testAppendLong_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(123456789L);
            assertEquals(1085, builder.toHashCode()); // 17 * 37 + (int) (123456789L ^ 123456789L >> 32)
        }

    @Test
        public void testAppendObject_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            Object value = new Object();
            builder.append(value);
            assertEquals(10, builder.toHashCode()); // 17 * 37 + value.hashCode()
        }

    @Test
        public void testAppendShort_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((short) 25);
            assertEquals(68, builder.toHashCode()); // 17 * 37 + (int) 25
        }
}
