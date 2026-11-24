package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        void testGetRegistry_NotCovered() {
            Set<IDKey> registry = HashCodeBuilder.getRegistry();
            assertTrue(registry instanceof HashSet);
        }

    @Test
        void testIsRegistered_ObjectNotRegistered_ReturnsFalse() {
            assertFalse(HashCodeBuilder.isRegistered(new Object()));
        }

    @Test
        void testIsRegistered_ObjectRegistered_ReturnsTrue() {
            Object obj = new Object();
            HashCodeBuilder.register(obj);
            assertTrue(HashCodeBuilder.isRegistered(obj));
            HashCodeBuilder.unregister(obj);
        }

    @Test
        void testReflectionAppend_ObjectRegistered_DoesNotAppendFields() {
            Object obj = new Object();
            HashCodeBuilder.register(obj);
            HashCodeBuilder builder = new HashCodeBuilder();
            HashCodeBuilder.reflectionAppend(obj, obj.getClass(), builder, false, null);
            assertEquals(HashCodeBuilder.DEFAULT_INITIAL_VALUE, builder.toHashCode());
            HashCodeBuilder.unregister(obj);
        }

    @Test
        void testReflectionHashCode_DefaultValues() {
            int hashCode = HashCodeBuilder.reflectionHashCode(new Object());
            assertEquals(17, hashCode); // Default initial value
        }

    @Test
        void testReflectionHashCode_WithTransients() {
            class TestClass {
                private transient int transientField;
                private int nonTransientField;
    
                public TestClass(int transientField, int nonTransientField) {
                    this.transientField = transientField;
                    this.nonTransientField = nonTransientField;
                }
            }
    
            TestClass obj = new TestClass(10, 20);
            int hashCodeWithTransients = HashCodeBuilder.reflectionHashCode(obj, true);
            int hashCodeWithoutTransients = HashCodeBuilder.reflectionHashCode(obj, false);
    
            assertFalse(hashCodeWithTransients == hashCodeWithoutTransients);
        }

    @Test
        void testReflectionHashCode_WithExcludeFields() {
            class TestClass {
                private int field1;
                private int field2;
    
                public TestClass(int field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj = new TestClass(10, 20);
            int hashCodeWithExclusion = HashCodeBuilder.reflectionHashCode(obj, "field1");
            int hashCodeWithoutExclusion = HashCodeBuilder.reflectionHashCode(obj);
    
            assertFalse(hashCodeWithExclusion == hashCodeWithoutExclusion);
        }

    @Test
        void testAppendBooleanArray_NotNull() {
            boolean[] array = {true, false};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(1237, builder.toHashCode());
        }

    @Test
        void testAppendDoubleArray_NotNull() {
            double[] array = {1.0, 2.0};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(465893503789499425L, builder.toHashCode());
        }

    @Test
        void testAppendObject_Array() {
            Object[] array = {1, 2};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(1237, builder.toHashCode());
        }

    @Test
        void testAppendArray_LongArray() {
            long[] array = {1L, 2L};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(465893503789499425L, builder.toHashCode());
        }

    @Test
        void testAppendArray_IntArray() {
            int[] array = {1, 2};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(1237, builder.toHashCode());
        }

    @Test
        void testAppendArray_ShortArray() {
            short[] array = {1, 2};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(1237, builder.toHashCode());
        }

    @Test
        void testAppendArray_CharArray() {
            char[] array = {'a', 'b'};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(29879, builder.toHashCode());
        }
}
