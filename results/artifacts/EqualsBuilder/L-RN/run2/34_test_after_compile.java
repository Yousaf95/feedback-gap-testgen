package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

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
    public void testSetBypassReflectionClasses() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setBypassReflectionClasses(Arrays.asList(String.class));
    }

    @Test
    public void testSetExcludeFields() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setExcludeFields("field1", "field2");
    }

    @Test
    public void testSetReflectUpToClass() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setReflectUpToClass(String.class);
    }

    @Test
    public void testSetTestRecursive() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setTestRecursive(true);

        builder.reset();
        builder.setTestRecursive(false);
    }

    @Test
    public void testSetTestTransients() {
        // Arrange
        EqualsBuilder builder = new EqualsBuilder();

        // Act & Assert
        builder.setTestTransients(true);

        builder.reset();
        builder.setTestTransients(false);
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

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendBooleanArray() {
            boolean[] array1 = {true, false};
            boolean[] array2 = {true, false};
            boolean[] array3 = {false, true};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendByteArray() {
            byte[] array1 = {1, 2};
            byte[] array2 = {1, 2};
            byte[] array3 = {2, 1};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendCharArray() {
            char[] array1 = {'a', 'b'};
            char[] array2 = {'a', 'b'};
            char[] array3 = {'b', 'a'};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendDoubleArray() {
            double[] array1 = {1.0, 2.0};
            double[] array2 = {1.0, 2.0};
            double[] array3 = {2.0, 1.0};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendFloatArray() {
            float[] array1 = {1.0f, 2.0f};
            float[] array2 = {1.0f, 2.0f};
            float[] array3 = {2.0f, 1.0f};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendIntArray() {
            int[] array1 = {1, 2};
            int[] array2 = {1, 2};
            int[] array3 = {2, 1};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendLongArray() {
            long[] array1 = {1L, 2L};
            long[] array2 = {1L, 2L};
            long[] array3 = {2L, 1L};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    @Test
        void testAppendShortArray() {
            short[] array1 = {1, 2};
            short[] array2 = {1, 2};
            short[] array3 = {2, 1};
    
            assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
            assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
        }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendSuperWithFalse() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.appendSuper(false);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendSuperWithTrue() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.appendSuper(true);
            assertTrue(builder.isEquals());
        }

    
    
    @Test
        void testSetReflectUpToClassWithNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.setReflectUpToClass(null);
            assertTrue(builder.isEquals());
        }

    @Test
        void testResetAfterAppendBoolean() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(true, false).reset();
            assertTrue(builder.isEquals());
        }

    @Test
        void testReflectionAppendWithSameObject() {
            List<String> list = new ArrayList<>();
            assertTrue(EqualsBuilder.reflectionEquals(list, list));
        }

    
    @Test
        void testReflectionAppendWithNullObjects() {
            assertTrue(EqualsBuilder.reflectionEquals(null, null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSetBypassReflectionClassesWithNull() {
            EqualsBuilder builder = new EqualsBuilder();
            assertThrows(NullPointerException.class, () -> builder.setBypassReflectionClasses(null));
        }

    @Test
        void testSetExcludeFieldsWithNull() {
            EqualsBuilder builder = new EqualsBuilder();
            assertThrows(NullPointerException.class, () -> builder.setExcludeFields((String[]) null));
        }

    @Test
        void testSetExcludeFieldsWithEmptyArray() {
            EqualsBuilder builder = new EqualsBuilder().setExcludeFields(new String[0]);
            assertEquals(0, builder.excludeFields.length);
        }

    @Test
        void testSetReflectUpToClassWithObjectClass() {
            EqualsBuilder builder = new EqualsBuilder().setReflectUpToClass(Object.class);
            assertEquals(Object.class, builder.reflectUpToClass);
        }

    @Test
        void testAppendSuperWithFalseAfterOtherAppends() {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(1, 2)
                    .appendSuper(false);
            assertFalse(builder.isEquals());
        }

    @Test
        void testAppendSuperWithTrueAfterOtherAppends() {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(1, 1)
                    .appendSuper(true);
            assertTrue(builder.isEquals());
        }

    @Test
        void testResetAfterMultipleAppends() {
            EqualsBuilder builder = new EqualsBuilder()
                    .append(1, 2)
                    .append("a", "b")
                    .reset();
            assertTrue(builder.isEquals());
        }
}
