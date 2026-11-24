package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;

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
    @BeforeEach
        public void setUp() {
            // Clear the registry before each test to ensure a clean state
            HashCodeBuilder.clearRegistry();
        }

    @Test
        public void testGetRegistry() {
            assertNotNull(HashCodeBuilder.getRegistry());
        }

    @Test
        public void testIsRegistered() {
            assertFalse(HashCodeBuilder.isRegistered(new Object()));
        }

    @ParameterizedTest
        @ValueSource(ints = {17, 37})
        public void testRegisterAndUnregister(int initialValue) {
            Object obj = new Object();
            HashCodeBuilder builder = new HashCodeBuilder(initialValue, 37);
            builder.register(obj);
            assertTrue(HashCodeBuilder.isRegistered(obj));
            builder.unregister(obj);
            assertFalse(HashCodeBuilder.isRegistered(obj));
        }

    @Test
        public void testReflectionAppendWithNullObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(null, Object.class, builder, false, null);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testReflectionAppendWithEmptyArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new int[0], builder, false, null);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testReflectionHashCodeWithNullObject() {
            assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null));
        }
}
