package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testAppendByte() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append((byte) 1, (byte) 1).isEquals());
        assertFalse(builder.append((byte) 1, (byte) 2).isEquals());
    }

    @Test
    public void testAppendChar() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append('a', 'a').isEquals());
        assertFalse(builder.append('a', 'b').isEquals());
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
    public void testAppendFloat() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1.0f, 1.0f).isEquals());
        assertFalse(builder.append(1.0f, 2.0f).isEquals());
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
    public void testAppendLong() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.append(1L, 1L).isEquals());
        assertFalse(builder.append(1L, 2L).isEquals());
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
    public void testAppendObject() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String lhs = "test";
        String rhs = "test";

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        builder.reset();
        assertFalse(builder.append(lhs, null).isEquals());

        builder.reset();
        assertFalse(builder.append(null, rhs).isEquals());
    }

    @Test
    public void testAppendObjectArray() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String[] lhs = {"test"};
        String[] rhs = {"test"};

        // Act & Assert
        assertTrue(builder.append(lhs, rhs).isEquals());

        builder.reset();
        assertFalse(builder.append(lhs, null).isEquals());

        builder.reset();
        assertFalse(builder.append(null, rhs).isEquals());
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

        builder.reset();
        builder.setEquals(false);
        assertFalse(builder.isEquals());
    }

    @Test
    public void testReflectionAppendSameObject() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String lhs = "test";

        // Act & Assert
        assertTrue(builder.reflectionAppend(lhs, lhs).isEquals());
    }

    @Test
    public void testReflectionAppendDifferentObjects() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        String lhs = "test";
        String rhs = "different";

        // Act & Assert
        assertFalse(builder.reflectionAppend(lhs, rhs).isEquals());
    }

    @Test
    public void testReflectionAppendNullObjects() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        assertTrue(builder.reflectionAppend(null, null).isEquals());

        builder.reset();
        assertFalse(builder.reflectionAppend("test", null).isEquals());

        builder.reset();
        assertFalse(builder.reflectionAppend(null, "test").isEquals());
    }

    
    
    
    
    
    @Test
    public void testReset() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(1, 2); // This will set isEquals to false

        // Act
        builder.reset();

        // Assert
        assertTrue(builder.isEquals());
    }
}
