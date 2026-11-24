package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompareToBuilder_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReflectionCompareSameObject() {
        // Arrange
        String lhs = "test";
        String rhs = "test";

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    
    @Test
    public void testReflectionCompareNullLhs() {
        // Arrange
        String lhs = null;
        String rhs = "test";

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareNullRhs() {
        // Arrange
        String lhs = "test";
        String rhs = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareDifferentClasses() {
        // Arrange
        String lhs = "test";
        Integer rhs = 123;

        // Act & Assert
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    @Test
    public void testReflectionCompareExcludeFields() {
        // Arrange
        class TestClass {
            private int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, "field2"));
    }

    @Test
    public void testReflectionCompareExcludeFieldsCollection() {
        // Arrange
        class TestClass {
            private int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, Collections.singletonList("field2")));
    }

    @Test
    public void testReflectionCompareTransients() {
        // Arrange
        class TestClass {
            private transient int field1;
            private int field2;

            public TestClass(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

        TestClass lhs = new TestClass(1, 2);
        TestClass rhs = new TestClass(1, 3);

        // Act & Assert
        assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs, true));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendBooleanSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, true);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanDifferentFirstTrue() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, false);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanDifferentSecondTrue() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(false, true);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true, false});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true}, new boolean[]{true, false});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, new boolean[]{true});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new boolean[]{true, false});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true, false}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 1);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteDifferentFirstGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 2, (byte) 1);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteDifferentSecondGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1, 2});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{1, 2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, new byte[]{1});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new byte[]{1, 2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1, 2}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharSame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'a');
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharDifferentFirstGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('b', 'a');
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharDifferentSecondGreater() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySame() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a', 'b'});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharArrayDifferentLengthFirstShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{'a', 'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayDifferentLengthSecondShorter() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, new char[]{'a'});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharArrayFirstNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new char[]{'a', 'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySecondNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a', 'b'}, null);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testReflectionCompareWithSuperclass() {
            class Parent {
                private int value;
    
                public Parent(int value) {
                    this.value = value;
                }
            }
    
            class Child extends Parent implements Comparable<Child> {
                private int anotherValue;
    
                public Child(int value, int anotherValue) {
                    super(value);
                    this.anotherValue = anotherValue;
                }
    
                @Override
                public int compareTo(Child other) {
                    return new CompareToBuilder()
                        .appendSuper(super.hashCode())
                        .append(this.anotherValue, other.anotherValue)
                        .toComparison();
                }
            }
    
            Child child1 = new Child(10, 20);
            Child child2 = new Child(10, 30);
    
            assertEquals(-1, child1.compareTo(child2));
        }

    @Test
        void testReflectionCompareWithExcludeFields() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, false, null, "transientValue"));
        }

    @Test
        void testReflectionCompareWithExcludeFieldsCollection() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("transientValue")));
        }

    @Test
        void testReflectionCompareWithTransients() {
            class TestClass {
                private int value;
                private transient int transientValue;
    
                public TestClass(int value, int transientValue) {
                    this.value = value;
                    this.transientValue = transientValue;
                }
            }
    
            TestClass obj1 = new TestClass(10, 20);
            TestClass obj2 = new TestClass(10, 30);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2, true));
        }

    @Test
        void testReflectionCompareWithReflectUpToClass() {
            class Parent {
                private int value;
    
                public Parent(int value) {
                    this.value = value;
                }
            }
    
            class Child extends Parent implements Comparable<Child> {
                private int anotherValue;
    
                public Child(int value, int anotherValue) {
                    super(value);
                    this.anotherValue = anotherValue;
                }
    
                @Override
                public int compareTo(Child other) {
                    return new CompareToBuilder()
                        .appendSuper(super.hashCode())
                        .append(this.anotherValue, other.anotherValue)
                        .toComparison();
                }
            }
    
            Child child1 = new Child(10, 20);
            Child child2 = new Child(10, 30);
    
            assertEquals(-1, CompareToBuilder.reflectionCompare(child1, child2, false, Parent.class));
        }
}
