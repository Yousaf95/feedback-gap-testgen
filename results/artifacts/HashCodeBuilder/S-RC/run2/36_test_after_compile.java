package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;

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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsRegisteredWithNullValue_2() {
            assertFalse(HashCodeBuilder.isRegistered(null));
        }

    @Test
        public void testIsRegisteredWithNonRegisteredValue_2() {
            Object value = new Object();
            assertFalse(HashCodeBuilder.isRegistered(value));
        }

    @Test
        public void testReflectionAppendWithNullObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            reflectionAppend(null, null, builder, false, null);
            assertEquals(17 * 37, builder.toHashCode());
        }

    @Test
        public void testReflectionAppendWithRegisteredObject() {
            Object value = new Object();
            register(value);
            HashCodeBuilder builder = new HashCodeBuilder();
            reflectionAppend(value, value.getClass(), builder, false, null);
            assertEquals(17 * 37, builder.toHashCode());
            unregister(value);
        }

    @Test
        public void testRegisterWithNullValue() {
            register(null);
            assertNull(getRegistry());
        }

    @Test
        public void testRegisterWithNonRegisteredValue() {
            Object value = new Object();
            register(value);
            assertNotNull(getRegistry());
            assertTrue(getRegistry().contains(new IDKey(value)));
            unregister(value);
        }

    @Test
        public void testUnregisterWithNullValue() {
            unregister(null);
            assertNull(getRegistry());
        }

    @Test
        public void testUnregisterWithNonRegisteredValue() {
            Object value = new Object();
            unregister(value);
            assertNull(getRegistry());
        }

    @Test
        public void testHashCodeBuilderWithOddInitialNumberAndMultiplier() {
            HashCodeBuilder builder = new HashCodeBuilder(17, 37);
            assertEquals(17 * 37, builder.toHashCode());
        }

    @Test
        public void testHashCodeBuilderWithEvenInitialNumberThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(2, 37));
        }

    @Test
        public void testHashCodeBuilderWithEvenMultiplierThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(17, 4));
        }
}
