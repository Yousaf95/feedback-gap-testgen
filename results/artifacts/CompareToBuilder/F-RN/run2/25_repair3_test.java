package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;

public class CompareToBuilder_GenTest {

    @Test
    public void testReflectionCompare_NullObjects() {
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
    }

    @Test
    public void testReflectionCompare_SameObject() {
        assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
    }

    @Test
    public void testReflectionCompare_DifferentClasses() {
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(new String("a"), 1));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReflectionCompare_SuperClass() {
        class SuperClass implements Comparable<SuperClass> {
            private int value;

            public SuperClass(int value) {
                this.value = value;
            }

            @Override
            public int compareTo(SuperClass other) {
                return Integer.compare(this.value, other.value);
            }
        }

        class SubClass extends SuperClass {
            public SubClass(int value) {
                super(value);
            }
        }

        assertEquals(0, CompareToBuilder.reflectionCompare(new SubClass(1), new SubClass(1)));
        assertEquals(-1, CompareToBuilder.reflectionCompare(new SubClass(0), new SubClass(1)));
        assertEquals(1, CompareToBuilder.reflectionCompare(new SubClass(1), new SubClass(0)));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_Arrays() {
            int[] lhsArray = {1, 2, 3};
            int[] rhsArray = {1, 2, 4};
            assertEquals(-1, CompareToBuilder.reflectionCompare(lhsArray, rhsArray));
        }

    @Test
        public void testReflectionCompare_ComparableObjects() {
            ComparableObject lhs = new ComparableObject(5);
            ComparableObject rhs = new ComparableObject(3);
            assertEquals(1, CompareToBuilder.reflectionCompare(lhs, rhs));
        }

    @Test
        public void testReflectionCompare_Comparator() {
            Integer[] lhsArray = {2, 4, 6};
            Integer[] rhsArray = {1, 3, 5};
            Comparator<Integer> comparator = (a, b) -> b - a;
            assertEquals(1, CompareToBuilder.reflectionCompare(lhsArray, rhsArray, comparator));
        }

    @Test
        public void testReflectionCompare_ExcludeFields() {
            ExcludeFieldObject lhs = new ExcludeFieldObject("value", 10);
            ExcludeFieldObject rhs = new ExcludeFieldObject("otherValue", 20);
            assertEquals(0, CompareToBuilder.reflectionCompare(lhs, rhs, "value"));
        }

    @Test
        public void testReflectionCompare_NullArrays() {
            Integer[] lhsArray = null;
            Integer[] rhsArray = {1, 2, 3};
            assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(lhsArray, rhsArray));
        }

    @Test
        public void testReflectionCompare_SameClassDifferentFields() {
            DifferentFieldObject lhs = new DifferentFieldObject(5);
            DifferentFieldObject rhs = new DifferentFieldObject(10);
            assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs));
        }
}
