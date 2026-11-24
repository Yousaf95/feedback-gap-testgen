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
        byte[] array = {1, 2};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 1) + 2, builder.toHashCode());
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
        char[] array = {'a', 'b'};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 'a') + 'b', builder.toHashCode());
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
        int[] array = {1, 2};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 1) + 2, builder.toHashCode());
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
        long[] array = {1L, 2L};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + (int) (1L ^ 1L >> 32)) + (int) (2L ^ 2L >> 32), builder.toHashCode());
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
        short[] array = {1, 2};

        // Act
        builder.append(array);

        // Assert
        assertEquals(37 * (37 * 17 + 1) + 2, builder.toHashCode());
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
        builder.appendSuper(5);

        // Assert
        assertEquals(37 * 17 + 5, builder.toHashCode());
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
    public void testEqualsDifferentInstancesDifferentHashCode() {
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
        void testReflectionHashCodeWithDefaultValues() {
            SampleClass sample = new SampleClass(10, "test");
            int expectedHashCode = new HashCodeBuilder()
                    .append(sample.getIntField())
                    .append(sample.getStringField())
                    .toHashCode();
            assertEquals(expectedHashCode, HashCodeBuilder.reflectionHashCode(sample));
        }

    @Test
        void testReflectionHashCodeWithCustomInitialAndMultiplier() {
            SampleClass sample = new SampleClass(10, "test");
            int expectedHashCode = new HashCodeBuilder(5, 7)
                    .append(sample.getIntField())
                    .append(sample.getStringField())
                    .toHashCode();
            assertEquals(expectedHashCode, HashCodeBuilder.reflectionHashCode(5, 7, sample));
        }

    @Test
        void testReflectionHashCodeExcludingFields() {
            SampleClass sample = new SampleClass(10, "test");
            int expectedHashCode = new HashCodeBuilder()
                    .append(sample.getIntField())
                    .toHashCode();
            assertEquals(expectedHashCode, HashCodeBuilder.reflectionHashCode(sample, "stringField"));
        }

    @Test
        void testReflectionHashCodeWithTransients() {
            SampleClass sample = new SampleClass(10, "test");
            int expectedHashCode = new HashCodeBuilder()
                    .append(sample.getIntField())
                    .append(sample.getStringField())
                    .toHashCode();
            assertEquals(expectedHashCode, HashCodeBuilder.reflectionHashCode(sample, true));
        }

    @Test
        void testReflectionHashCodeWithNullObjectThrowsException() {
            assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null));
        }
}
