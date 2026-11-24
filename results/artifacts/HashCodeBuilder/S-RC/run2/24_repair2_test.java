package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
            Set<IDKey> registry = HashCodeBuilder.getRegistry();
            assertNotNull(registry);
        }

    @Test
        public void testIsRegisteredWithNullValue() {
            boolean result = HashCodeBuilder.isRegistered(null);
            assertFalse(result);
        }

    @Test
        public void testIsRegisteredWithNonRegisteredValue() {
            Object value = new Object();
            boolean result = HashCodeBuilder.isRegistered(value);
            assertFalse(result);
        }

    @Test
        public void testReflectionHashCodeWithNullObject() {
            assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null, true));
        }

    @Test
        public void testReflectionHashCodeWithDefaultValues() {
            Object value = new Object();
            int result = HashCodeBuilder.reflectionHashCode(value, true);
            assertEquals(17 * 37 + value.hashCode(), result);
        }
}
