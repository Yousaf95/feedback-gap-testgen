package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EqualsBuilder_GenTest {

    @Test
    public void testAppendBoolean() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(true, true).isEquals());
        builder.reset();
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

        rhs[1] = true;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendByte() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append((byte) 1, (byte) 1).isEquals());
        builder.reset();
        assertFalse(builder.append((byte) 1, (byte) 2).isEquals());
    }

    @Test
    public void testAppendByteArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        byte[] lhs = {1, 2};
        byte[] rhs = {1, 2};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        rhs[1] = 3;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendChar() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append('a', 'a').isEquals());
        builder.reset();
        assertFalse(builder.append('a', 'b').isEquals());
    }

    @Test
    public void testAppendCharArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'b'};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        rhs[1] = 'c';
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendDouble() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1.0, 1.0).isEquals());
        builder.reset();
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

        rhs[1] = 3.0;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendFloat() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1.0f, 1.0f).isEquals());
        builder.reset();
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

        rhs[1] = 3.0f;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendInt() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1, 1).isEquals());
        builder.reset();
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

        rhs[1] = 3;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendLong() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1L, 1L).isEquals());
        builder.reset();
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

        rhs[1] = 3L;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendObject() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String lhs = "test";
        String rhs = "test";

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        rhs = "different";
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String[] lhs = {"test", "example"};
        String[] rhs = {"test", "example"};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        rhs[1] = "different";
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendShort() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append((short) 1, (short) 1).isEquals());
        builder.reset();
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

        rhs[1] = 3;
        builder.reset();
        assertFalse(builder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendSuper() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.appendSuper(true).isEquals());
        builder.reset();
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

        lhs = "test";
        builder.reset();
        assertFalse(builder.reflectionAppend(lhs, rhs).isEquals());
    }

    @Test
    public void testReflectionAppendWithSameObject() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String obj = "test";

        // Act & Assert
        assertTrue(builder.reflectionAppend(obj, obj).isEquals());
    }

    @Test
    public void testSetBypassReflectionClasses() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        List<Class<?>> bypassClasses = new ArrayList<>();
        bypassClasses.add(String.class);

        // Act & Assert
        assertEquals(builder, builder.setBypassReflectionClasses(bypassClasses));
    }

    @Test
    public void testSetExcludeFields() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String[] excludeFields = {"field1", "field2"};

        // Act & Assert
        assertEquals(builder, builder.setExcludeFields(excludeFields));
    }

    @Test
    public void testSetReflectUpToClass() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertEquals(builder, builder.setReflectUpToClass(String.class));
    }

    @Test
    public void testSetTestRecursive() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertEquals(builder, builder.setTestRecursive(true));
    }

    @Test
    public void testSetTestTransients() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertEquals(builder, builder.setTestTransients(true));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testReflectionEqualsWithSameObject() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj, false));
        }

    @Test
        void testReflectionEqualsWithNulls() {
            assertTrue(EqualsBuilder.reflectionEquals(null, null, false));
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), null, false));
            assertFalse(EqualsBuilder.reflectionEquals(null, new Object(), false));
        }

    @Test
        void testReflectionEqualsWithDifferentObjects() {
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), new Object(), false));
        }

    @Test
        void testAppendBooleanBranches() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(true, true).append(false, false);
            assertTrue(builder.isEquals());
    
            builder.append(true, false);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendBooleanArrayBranches() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[]{true}, new boolean[]{true});
            assertTrue(builder.isEquals());
    
            builder.append((boolean[]) null, (boolean[]) null);
            assertTrue(builder.isEquals());
    
            builder.append(new boolean[]{true}, (boolean[]) null);
            assertFalse(builder.isEquals());
    
            builder.append((boolean[]) null, new boolean[]{true});
            assertFalse(builder.isEquals());
    
            builder.append(new boolean[]{true}, new boolean[]{false});
            assertFalse(builder.isEquals());
    
            builder.append(new boolean[]{true, true}, new boolean[]{true});
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendByteBranches() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((byte) 1, (byte) 1);
            assertTrue(builder.isEquals());
    
            builder.append((byte) 1, (byte) 2);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendByteArrayBranches() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new byte[]{1}, new byte[]{1});
            assertTrue(builder.isEquals());
    
            builder.append((byte[]) null, (byte[]) null);
            assertTrue(builder.isEquals());
    
            builder.append(new byte[]{1}, (byte[]) null);
            assertFalse(builder.isEquals());
    
            builder.append((byte[]) null, new byte[]{1});
            assertFalse(builder.isEquals());
    
            builder.append(new byte[]{1}, new byte[]{2});
            assertFalse(builder.isEquals());
    
            builder.append(new byte[]{1, 1}, new byte[]{1});
            assertFalse(builder.isEquals());
        }
}
