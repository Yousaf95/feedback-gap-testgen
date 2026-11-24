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
}
