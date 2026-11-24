package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

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

    
    
    @Test
        public void testAppendSuper() {
            class BaseClass implements Comparable<BaseClass> {
                int baseValue = 5;
    
                @Override
                public int compareTo(BaseClass other) {
                    return Integer.compare(this.baseValue, other.baseValue);
                }
            }
    
            class TestClass extends BaseClass {
                int value = 10;
            }
    
            TestClass obj1 = new TestClass();
            TestClass obj2 = new TestClass();
    
            CompareToBuilder builder = new CompareToBuilder();
            builder.appendSuper(obj1.compareTo(obj2));
            assertEquals(0, builder.toComparison());
    
            obj1.baseValue = 6;
            builder = new CompareToBuilder();
            builder.appendSuper(obj1.compareTo(obj2));
            assertEquals(1, builder.toComparison());
    
            obj2.baseValue = 7;
            builder = new CompareToBuilder();
            builder.appendSuper(obj1.compareTo(obj2));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendWithComparator() {
            class TestClass {
                String value = "test";
            }
    
            TestClass obj1 = new TestClass();
            TestClass obj2 = new TestClass();
    
            // Use a custom comparator that reverses the order
            java.util.Comparator<String> reverseComparator = (s1, s2) -> s2.compareTo(s1);
    
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(obj1.value, obj2.value, reverseComparator);
            assertEquals(0, builder.toComparison());
    
            obj1.value = "a";
            obj2.value = "b";
    
            builder = new CompareToBuilder();
            builder.append(obj1.value, obj2.value, reverseComparator);
            assertEquals(1, builder.toComparison()); // 'b' should come before 'a' with the custom comparator
    
            builder = new CompareToBuilder();
            builder.append(obj2.value, obj1.value, reverseComparator);
            assertEquals(-1, builder.toComparison()); // 'a' should come after 'b' with the custom comparator
        }

    @Test
        public void testAppendArrayWithComparator() {
            class TestClass {
                String[] values = {"a", "b"};
            }
    
            TestClass obj1 = new TestClass();
            TestClass obj2 = new TestClass();
    
            // Use a custom comparator that reverses the order of strings
            java.util.Comparator<String> reverseComparator = (s1, s2) -> s2.compareTo(s1);
    
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(obj1.values, obj2.values, reverseComparator);
            assertEquals(0, builder.toComparison());
    
            obj1.values = new String[]{"b", "a"};
            builder = new CompareToBuilder();
            builder.append(obj1.values, obj2.values, reverseComparator);
            assertEquals(-1, builder.toComparison()); // ["b", "a"] should come after ["a", "b"] with the custom comparator
    
            obj2.values = new String[]{"c", "d"};
            builder = new CompareToBuilder();
            builder.append(obj1.values, obj2.values, reverseComparator);
            assertEquals(1, builder.toComparison()); // ["b", "a"] should come before ["c", "d"] with the custom comparator
        }
}
