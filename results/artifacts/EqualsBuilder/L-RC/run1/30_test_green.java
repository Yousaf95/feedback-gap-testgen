package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class EqualsBuilder_GenTest {

    @Test
    public void testAppendBoolean() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(true, true).isEquals());
        assertFalse(builder.append(true, false).isEquals());
    }

    @Test
    public void testAppendBooleanArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, false};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new boolean[]{false, true}).isEquals());
    }

    @Test
    public void testAppendByteArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        byte[] lhs = {1, 2};
        byte[] rhs = {1, 2};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new byte[]{2, 1}).isEquals());
    }

    @Test
    public void testAppendCharArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'b'};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new char[]{'b', 'a'}).isEquals());
    }

    @Test
    public void testAppendDouble() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1.0, 1.0).isEquals());
        assertFalse(builder.append(1.0, 2.0).isEquals());
    }

    @Test
    public void testAppendDoubleArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        double[] lhs = {1.0, 2.0};
        double[] rhs = {1.0, 2.0};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new double[]{2.0, 1.0}).isEquals());
    }

    @Test
    public void testAppendFloat() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1.0f, 1.0f).isEquals());
        assertFalse(builder.append(1.0f, 2.0f).isEquals());
    }

    @Test
    public void testAppendFloatArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        float[] lhs = {1.0f, 2.0f};
        float[] rhs = {1.0f, 2.0f};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new float[]{2.0f, 1.0f}).isEquals());
    }

    @Test
    public void testAppendInt() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1, 1).isEquals());
        assertFalse(builder.append(1, 2).isEquals());
    }

    @Test
    public void testAppendIntArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        int[] lhs = {1, 2};
        int[] rhs = {1, 2};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new int[]{2, 1}).isEquals());
    }

    @Test
    public void testAppendLong() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1L, 1L).isEquals());
        assertFalse(builder.append(1L, 2L).isEquals());
    }

    @Test
    public void testAppendLongArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        long[] lhs = {1L, 2L};
        long[] rhs = {1L, 2L};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new long[]{2L, 1L}).isEquals());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Object lhs = "test";
        Object rhs = "test";

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, "different").isEquals());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Object[] lhs = {"test", 1};
        Object[] rhs = {"test", 1};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new Object[]{"different", 1}).isEquals());
    }

    @Test
    public void testAppendShort() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append((short) 1, (short) 1).isEquals());
        assertFalse(builder.append((short) 1, (short) 2).isEquals());
    }

    @Test
    public void testAppendShortArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        short[] lhs = {1, 2};
        short[] rhs = {1, 2};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());
        assertFalse(builder.append(lhs, new short[]{2, 1}).isEquals());
    }

    @Test
    public void testAppendSuper() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.appendSuper(true).isEquals());
        assertFalse(builder.appendSuper(false).isEquals());
    }

    @Test
    public void testIsEquals() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.isEquals());
        builder.setEquals(false);
        assertFalse(builder.isEquals());
    }

    @Test
    public void testReflectionAppendWithNulls() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Object lhs = null;
        Object rhs = null;

        // Act & Assert
        assertTrue(builder.reflectionAppend(lhs, rhs).isEquals());

        // Arrange
        lhs = "test";
        rhs = null;

        // Act & Assert
        assertFalse(builder.reflectionAppend(lhs, rhs).isEquals());
    }

    @Test
    public void testReflectionAppendWithEqualObjects() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Object lhs = "test";
        Object rhs = "test";

        // Act & Assert
        assertTrue(builder.reflectionAppend(lhs, rhs).isEquals());
    }

    @Test
    public void testReflectionAppendWithDifferentObjects() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Object lhs = "test";
        Object rhs = "different";

        // Act & Assert
        assertFalse(builder.reflectionAppend(lhs, rhs).isEquals());
    }

    @Test
    public void testReset() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        builder.setEquals(false);

        // Act
        builder.reset();

        // Assert
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        void testReflectionEqualsWithSameObject() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
        }

    
    
    
    
    
    
    @Test
        void testAppendBooleanWhenNotEqual() {
            EqualsBuilder builder = new EqualsBuilder().append(true, false);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendBooleanArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((boolean[]) null, new boolean[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new boolean[0], (boolean[]) null).isEquals());
        }

    @Test
        void testAppendByteArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((byte[]) null, new byte[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new byte[0], (byte[]) null).isEquals());
        }

    @Test
        void testAppendCharArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((char[]) null, new char[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new char[0], (char[]) null).isEquals());
        }

    @Test
        void testAppendDoubleArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((double[]) null, new double[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new double[0], (double[]) null).isEquals());
        }

    @Test
        void testAppendFloatArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((float[]) null, new float[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new float[0], (float[]) null).isEquals());
        }

    @Test
        void testAppendIntArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((int[]) null, new int[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new int[0], (int[]) null).isEquals());
        }

    @Test
        void testAppendLongArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((long[]) null, new long[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new long[0], (long[]) null).isEquals());
        }

    @Test
        void testAppendObjectArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((Object[]) null, new Object[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new Object[0], (Object[]) null).isEquals());
        }

    @Test
        void testAppendShortArrayWithNulls() {
            assertEquals(false, new EqualsBuilder().append((short[]) null, new short[0]).isEquals());
            assertEquals(false, new EqualsBuilder().append(new short[0], (short[]) null).isEquals());
        }

    
    @Test
        void testReflectionEqualsWithSameObjectReference() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj, false));
        }

    
    @Test
        void testReflectionEqualsWithExcludeFields() {
            class TestClass {
                private int field1;
                private int field2;
    
                public TestClass(int field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, Arrays.asList("field2")));
        }

    @Test
        void testReflectionEqualsWithExcludeFieldsCollection() {
            class TestClass {
                private int field1;
                private int field2;
    
                public TestClass(int field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, Collections.singleton("field2")));
        }

    @Test
        void testAppendBooleanWhenNotEqual_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(true, false);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendBooleanArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((boolean[]) null, (boolean[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new boolean[]{true}, (boolean[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendByteArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((byte[]) null, (byte[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new byte[]{1}, (byte[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendCharArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((char[]) null, (char[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new char[]{'a'}, (char[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendDoubleArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((double[]) null, (double[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new double[]{1.0}, (double[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendFloatArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((float[]) null, (float[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new float[]{1.0f}, (float[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendIntArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((int[]) null, (int[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new int[]{1}, (int[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendLongArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((long[]) null, (long[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new long[]{1L}, (long[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendObjectArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object[]) null, (Object[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new Object[]{new Object()}, (Object[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendShortArrayWithNulls_2() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((short[]) null, (short[]) null);
            assertTrue(builder.isEquals());
    
            builder = new EqualsBuilder();
            builder.append(new short[]{(short) 1}, (short[]) null);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendBooleanArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendByteArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendCharArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a'});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendDoubleArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new double[]{1.0, 2.0}, new double[]{1.0});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendFloatArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new float[]{1.0f, 2.0f}, new float[]{1.0f});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendIntArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new int[]{1, 2}, new int[]{1});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendLongArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new long[]{1L, 2L}, new long[]{1L});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendObjectArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new Object[]{new Object(), new Object()}, new Object[]{new Object()});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendShortArrayWithDifferentLengths() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new short[]{(short) 1, (short) 2}, new short[]{(short) 1});
            assertFalse(builder.isEquals());
        }
}
