package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

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
        void testReflectionEqualsSameObject() {
            Object obj = new Object();
            assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
        }

    @Test
        void testReflectionEqualsDifferentObjects() {
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), new Object()));
        }

    @Test
        void testReflectionEqualsNullObjects() {
            assertTrue(EqualsBuilder.reflectionEquals(null, null));
            assertFalse(EqualsBuilder.reflectionEquals(null, new Object()));
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), null));
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
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, "field2"));
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
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, Collections.singletonList("field2")));
        }

    @Test
        void testReflectionEqualsWithReflectUpToClass() {
            class Parent {
                private int parentField;
    
                public Parent(int parentField) {
                    this.parentField = parentField;
                }
            }
            class Child extends Parent {
                private int childField;
    
                public Child(int parentField, int childField) {
                    super(parentField);
                    this.childField = childField;
                }
            }
            Child obj1 = new Child(1, 2);
            Child obj2 = new Child(1, 3);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, false, Parent.class));
        }

    @Test
        void testReflectionEqualsWithTestTransients() {
            class TestClass {
                private transient int transientField;
                private int nonTransientField;
    
                public TestClass(int transientField, int nonTransientField) {
                    this.transientField = transientField;
                    this.nonTransientField = nonTransientField;
                }
            }
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 2);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, true));
        }

    @Test
        void testAppendBooleanArray() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true, false};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendByteArray() {
            byte[] lhs = {1, 2};
            byte[] rhs = {1, 2};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendCharArray() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'a', 'b'};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendDoubleArray() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0, 2.0};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendFloatArray() {
            float[] lhs = {1.0f, 2.0f};
            float[] rhs = {1.0f, 2.0f};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendIntArray() {
            int[] lhs = {1, 2};
            int[] rhs = {1, 2};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendLongArray() {
            long[] lhs = {1L, 2L};
            long[] rhs = {1L, 2L};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendShortArray() {
            short[] lhs = {1, 2};
            short[] rhs = {1, 2};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        void testAppendObjectArray_2() {
            Object[] lhs = {"a", "b"};
            Object[] rhs = {"a", "b"};
            assertEquals(true, new EqualsBuilder().append(lhs, rhs).isEquals());
        }
}
