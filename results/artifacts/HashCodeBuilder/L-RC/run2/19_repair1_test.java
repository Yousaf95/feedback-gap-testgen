package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        HashCodeBuilder builder = new HashCodeBuilder();

        // Assert
        assertEquals(17, builder.toHashCode());
    }

    @Test
    public void testParameterizedConstructorWithOddNumbers() {
        // Arrange & Act
        HashCodeBuilder builder = new HashCodeBuilder(5, 13);

        // Assert
        assertEquals(5, builder.toHashCode());
    }

    @Test
    public void testParameterizedConstructorWithEvenInitialNumberThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(4, 13));
    }

    @Test
    public void testParameterizedConstructorWithEvenMultiplierNumberThrowsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(5, 12));
    }

    @Test
    public void testAppendBooleanTrue() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(true);

        // Assert
        assertEquals(37 * 17 + 0, builder.toHashCode());
    }

    @Test
    public void testAppendBooleanFalse() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(false);

        // Assert
        assertEquals(37 * 17 + 1, builder.toHashCode());
    }

    @Test
    public void testAppendBooleanArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        boolean[] array = {true, false};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 0) + 1, builder.toHashCode());
    }

    @Test
    public void testAppendBooleanArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        boolean[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendByte() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((byte) 5);

        // Assert
        assertEquals(37 * 17 + 5, builder.toHashCode());
    }

    @Test
    public void testAppendByteArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        byte[] array = {10, 20};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 10) + 20, builder.toHashCode());
    }

    @Test
    public void testAppendByteArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        byte[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendChar() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append('a');

        // Assert
        assertEquals(37 * 17 + 'a', builder.toHashCode());
    }

    @Test
    public void testAppendCharArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        char[] array = {'x', 'y'};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 'x') + 'y', builder.toHashCode());
    }

    @Test
    public void testAppendCharArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        char[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendDouble() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(5.0);

        // Assert
        assertEquals(37 * 17 + Double.hashCode(5.0), builder.toHashCode());
    }

    @Test
    public void testAppendDoubleArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        double[] array = {1.0, 2.0};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + Double.hashCode(1.0)) + Double.hashCode(2.0), builder.toHashCode());
    }

    @Test
    public void testAppendDoubleArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        double[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendFloat() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(5.0f);

        // Assert
        assertEquals(37 * 17 + Float.hashCode(5.0f), builder.toHashCode());
    }

    @Test
    public void testAppendFloatArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        float[] array = {1.0f, 2.0f};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + Float.hashCode(1.0f)) + Float.hashCode(2.0f), builder.toHashCode());
    }

    @Test
    public void testAppendFloatArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        float[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendInt() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(5);

        // Assert
        assertEquals(37 * 17 + 5, builder.toHashCode());
    }

    @Test
    public void testAppendIntArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        int[] array = {10, 20};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 10) + 20, builder.toHashCode());
    }

    @Test
    public void testAppendIntArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        int[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendLong() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(5L);

        // Assert
        assertEquals(37 * 17 + (int) (5L ^ 5L >> 32), builder.toHashCode());
    }

    @Test
    public void testAppendLongArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        long[] array = {10L, 20L};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + (int) (10L ^ 10L >> 32)) + (int) (20L ^ 20L >> 32), builder.toHashCode());
    }

    @Test
    public void testAppendLongArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        long[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        String object = "test";

        // Act
        builder.append(object);

        // Assert
        assertEquals(37 * 17 + object.hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendObjectNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        String object = null;

        // Act
        builder.append(object);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        Object[] array = {"test", "example"};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + "test".hashCode()) + "example".hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendObjectArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        Object[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendShort() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((short) 5);

        // Assert
        assertEquals(37 * 17 + 5, builder.toHashCode());
    }

    @Test
    public void testAppendShortArray() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        short[] array = {10, 20};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 10) + 20, builder.toHashCode());
    }

    @Test
    public void testAppendShortArrayNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        short[] array = null;

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendSuper() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.appendSuper(50);

        // Assert
        assertEquals(37 * 17 + 50, builder.toHashCode());
    }

    @Test
    public void testBuild() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act
        Integer result = builder.build();

        // Assert
        assertEquals(Integer.valueOf(37 * 17 + 5), result);
    }

    @Test
    public void testEqualsSameInstance() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act & Assert
        assertTrue(builder.equals(builder));
    }

    @Test
    public void testEqualsDifferentInstancesSameHashCode() {
        // Arrange
        HashCodeBuilder builder1 = new HashCodeBuilder();
        builder1.append(5);
        HashCodeBuilder builder2 = new HashCodeBuilder();
        builder2.append(5);

        // Act & Assert
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testEqualsDifferentHashCodes() {
        // Arrange
        HashCodeBuilder builder1 = new HashCodeBuilder();
        builder1.append(5);
        HashCodeBuilder builder2 = new HashCodeBuilder();
        builder2.append(6);

        // Act & Assert
        assertFalse(builder1.equals(builder2));
    }

    @Test
    public void testEqualsNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act & Assert
        assertFalse(builder.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        String string = "test";

        // Act & Assert
        assertFalse(builder.equals(string));
    }

    @Test
    public void testHashCode() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act
        int result = builder.hashCode();

        // Assert
        assertEquals(37 * 17 + 5, result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testIsRegisteredNotRegistered() {
            assertFalse(HashCodeBuilder.isRegistered(new Object()));
        }

    @Test
        void testReflectionHashCodeDefaultValues() {
            int hashCode = HashCodeBuilder.reflectionHashCode(new Object());
            assertEquals(hashCode, new HashCodeBuilder().toHashCode());
        }

    @Test
        void testReflectionHashCodeWithTransients() {
            int hashCode = HashCodeBuilder.reflectionHashCode(new Object(), true);
            assertEquals(hashCode, new HashCodeBuilder().toHashCode());
        }

    @Test
        void testReflectionHashCodeWithExcludeFieldsCollection() {
            int hashCode = HashCodeBuilder.reflectionHashCode(new Object(), Collections.emptyList());
            assertEquals(hashCode, new HashCodeBuilder().toHashCode());
        }

    @Test
        void testReflectionHashCodeWithExcludeFieldsArray() {
            int hashCode = HashCodeBuilder.reflectionHashCode(new Object(), new String[0]);
            assertEquals(hashCode, new HashCodeBuilder().toHashCode());
        }

    @Test
        void testAppendArrayBoolean() {
            int initialHash = 17;
            int multiplier = 37;
            boolean[] array = {true, false};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(true).append(false).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayByte() {
            int initialHash = 17;
            int multiplier = 37;
            byte[] array = {1, 2};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append((byte) 1).append((byte) 2).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayChar() {
            int initialHash = 17;
            int multiplier = 37;
            char[] array = {'a', 'b'};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append('a').append('b').toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayDouble() {
            int initialHash = 17;
            int multiplier = 37;
            double[] array = {1.0, 2.0};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(1.0).append(2.0).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayFloat() {
            int initialHash = 17;
            int multiplier = 37;
            float[] array = {1.0f, 2.0f};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(1.0f).append(2.0f).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayInt() {
            int initialHash = 17;
            int multiplier = 37;
            int[] array = {1, 2};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(1).append(2).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayLong() {
            int initialHash = 17;
            int multiplier = 37;
            long[] array = {1L, 2L};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(1L).append(2L).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayShort() {
            int initialHash = 17;
            int multiplier = 37;
            short[] array = {1, 2};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append((short) 1).append((short) 2).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }

    @Test
        void testAppendArrayObject() {
            int initialHash = 17;
            int multiplier = 37;
            Object[] array = {new Object(), new Object()};
            int expectedHash = new HashCodeBuilder(initialHash, multiplier)
                    .append(array[0]).append(array[1]).toHashCode();
            assertEquals(expectedHash, new HashCodeBuilder(initialHash, multiplier)
                    .append(array).toHashCode());
        }
}
