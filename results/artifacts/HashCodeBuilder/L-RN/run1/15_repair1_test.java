package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HashCodeBuilder_GenTest {

    @Test
    public void testConstructor_DefaultValues() {
        // Arrange & Act
        HashCodeBuilder builder = new HashCodeBuilder();

        // Assert
        assertEquals(17, builder.toHashCode());
    }

    @Test
    public void testConstructor_CustomOddValues() {
        // Arrange & Act
        HashCodeBuilder builder = new HashCodeBuilder(5, 13);

        // Assert
        assertEquals(5, builder.toHashCode());
    }

    @Test
    public void testConstructor_InitialValueEven_ThrowsIllegalArgumentException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(2, 13));
    }

    @Test
    public void testConstructor_MultiplierValueEven_ThrowsIllegalArgumentException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HashCodeBuilder(5, 14));
    }

    
    
    @Test
    public void testAppendBooleanArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((boolean[]) null);

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
    public void testAppendByteArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((byte[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendByteArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new byte[]{5, 10});

        // Assert
        assertEquals((37 * (37 * 17 + 5) + 10), builder.toHashCode());
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
    public void testAppendCharArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((char[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendCharArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new char[]{'a', 'b'});

        // Assert
        assertEquals((37 * (37 * 17 + 'a') + 'b'), builder.toHashCode());
    }

    
    @Test
    public void testAppendDoubleArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((double[]) null);

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
        assertEquals(37 * 17 + Float.floatToIntBits(5.0f), builder.toHashCode());
    }

    @Test
    public void testAppendFloatArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((float[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendFloatArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new float[]{5.0f, 10.0f});

        // Assert
        assertEquals((37 * (37 * 17 + Float.floatToIntBits(5.0f)) + Float.floatToIntBits(10.0f)), builder.toHashCode());
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
    public void testAppendIntArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((int[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendIntArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new int[]{5, 10});

        // Assert
        assertEquals((37 * (37 * 17 + 5) + 10), builder.toHashCode());
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
    public void testAppendLongArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((long[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendLongArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new long[]{5L, 10L});

        // Assert
        assertEquals((37 * (37 * 17 + (int) (5L ^ 5L >> 32)) + (int) (10L ^ 10L >> 32)), builder.toHashCode());
    }

    @Test
    public void testAppendObject_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((Object) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    @Test
    public void testAppendObject_NonNull() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        String str = "test";

        // Act
        builder.append(str);

        // Assert
        assertEquals(37 * 17 + str.hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendObjectArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((Object[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendObjectArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        String str1 = "test1";
        String str2 = "test2";

        // Act
        builder.append(new Object[]{str1, str2});

        // Assert
        assertEquals((37 * (37 * 17 + str1.hashCode()) + str2.hashCode()), builder.toHashCode());
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
    public void testAppendShortArray_Null() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append((short[]) null);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }

    
    @Test
    public void testAppendShortArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new short[]{5, 10});

        // Assert
        assertEquals((37 * (37 * 17 + 5) + 10), builder.toHashCode());
    }

    @Test
    public void testAppendSuper() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.appendSuper(42);

        // Assert
        assertEquals(37 * 17 + 42, builder.toHashCode());
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
    public void testEquals_SameInstance() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act & Assert
        assertTrue(builder.equals(builder));
    }

    @Test
    public void testEquals_DifferentInstancesSameHashCode() {
        // Arrange
        HashCodeBuilder builder1 = new HashCodeBuilder();
        builder1.append(5);
        HashCodeBuilder builder2 = new HashCodeBuilder();
        builder2.append(5);

        // Act & Assert
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testEquals_DifferentHashCodes() {
        // Arrange
        HashCodeBuilder builder1 = new HashCodeBuilder();
        builder1.append(5);
        HashCodeBuilder builder2 = new HashCodeBuilder();
        builder2.append(10);

        // Act & Assert
        assertFalse(builder1.equals(builder2));
    }

    @Test
    public void testEquals_NotHashCodeBuilder() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act & Assert
        assertFalse(builder.equals("not a hashcodebuilder"));
    }

    @Test
    public void testHashCode_ReturnsComputedValue() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act
        int result = builder.hashCode();

        // Assert
        assertEquals(37 * 17 + 5, result);
    }

    @Test
    public void testToHashCode_ReturnsComputedValue() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(5);

        // Act
        int result = builder.toHashCode();

        // Assert
        assertEquals(37 * 17 + 5, result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDouble_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(123.456);
            assertEquals(190870269, builder.toHashCode());
        }

    @Test
        public void testAppendFloat_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(123.456f);
            assertEquals(190870269, builder.toHashCode());
        }

    @Test
        public void testAppendLong_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(123456789L);
            assertEquals(-1521135388, builder.toHashCode());
        }

    @Test
        public void testAppendShort_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((short) 1234);
            assertEquals(64097, builder.toHashCode());
        }

    @Test
        public void testAppendChar_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append('A');
            assertEquals(583, builder.toHashCode());
        }

    @Test
        public void testAppendByte_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((byte) 123);
            assertEquals(460, builder.toHashCode());
        }

    @Test
        public void testAppendBoolean_True() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(true);
            assertEquals(59, builder.toHashCode());
        }

    @Test
        public void testAppendBoolean_False() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(false);
            assertEquals(34, builder.toHashCode());
        }

    @Test
        public void testAppendSuper_SpecificValue() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.appendSuper(123456789);
            assertEquals(-1521135388, builder.toHashCode());
        }
}
