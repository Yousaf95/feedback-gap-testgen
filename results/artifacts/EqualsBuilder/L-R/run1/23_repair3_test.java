package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testSetBypassReflectionClasses() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        List<Class<?>> bypassClasses = Collections.singletonList(String.class);

        // Act
        builder.setBypassReflectionClasses(bypassClasses);

        // Assert
        assertEquals(bypassClasses, builder.bypassReflectionClasses);
    }

    @Test
    public void testSetExcludeFields() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String[] excludeFields = {"field1", "field2"};

        // Act
        builder.setExcludeFields(excludeFields);

        // Assert
        assertArrayEquals(excludeFields, builder.excludeFields);
    }

    @Test
    public void testSetReflectUpToClass() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        Class<?> reflectUpToClass = String.class;

        // Act
        builder.setReflectUpToClass(reflectUpToClass);

        // Assert
        assertEquals(reflectUpToClass, builder.reflectUpToClass);
    }

    @Test
    public void testSetTestRecursive() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setTestRecursive(true);
        assertTrue(builder.testRecursive);

        builder.setTestRecursive(false);
        assertFalse(builder.testRecursive);
    }

    @Test
    public void testSetTestTransients() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setTestTransients(true);
        assertTrue(builder.testTransients);

        builder.setTestTransients(false);
        assertFalse(builder.testTransients);
    }
}
