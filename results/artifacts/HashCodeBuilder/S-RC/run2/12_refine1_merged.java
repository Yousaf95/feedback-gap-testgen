package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

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
        public void testGetRegistry() {
            // Ensure the registry is not null and can be retrieved
            Set<IDKey> registry = HashCodeBuilder.getRegistry();
            assertNotNull(registry);
        }

    @Test
        public void testIsRegisteredWithNullValue() {
            // Ensure isRegistered returns false for a null value
            boolean result = HashCodeBuilder.isRegistered(null);
            assertFalse(result);
        }

    @Test
        public void testIsRegisteredWithNonRegisteredValue() {
            // Ensure isRegistered returns false for a non-registered value
            Object value = new Object();
            boolean result = HashCodeBuilder.isRegistered(value);
            assertFalse(result);
        }

    @Test
        public void testRegisterAndUnregister() {
            // Ensure register and unregister work correctly
            Object value = new Object();
            HashCodeBuilder.register(value);
            Set<IDKey> registry = HashCodeBuilder.getRegistry();
            assertTrue(registry.contains(new IDKey(value)));
            HashCodeBuilder.unregister(value);
            registry = HashCodeBuilder.getRegistry();
            assertFalse(registry.contains(new IDKey(value)));
        }

    @Test
        public void testReflectionAppendWithNullArray() {
            // Ensure reflectionAppend handles null arrays correctly
            Object value = new Object();
            HashCodeBuilder builder = new HashCodeBuilder(17, 37);
            HashCodeBuilder reflectionAppendResult = HashCodeBuilder.reflectionAppend(value, value.getClass(), builder, false, null);
            assertEquals(builder, reflectionAppendResult);
        }

    @Test
        public void testReflectionHashCodeWithNullObject() {
            // Ensure reflectionHashCode throws a NullPointerException for a null object
            assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null, true));
        }

    @Test
        public void testReflectionHashCodeWithDefaultValues() {
            // Ensure reflectionHashCode works with default values
            Object value = new Object();
            int result = HashCodeBuilder.reflectionHashCode(value, true);
            assertEquals(17 * 37 + value.hashCode(), result);
        }
}
