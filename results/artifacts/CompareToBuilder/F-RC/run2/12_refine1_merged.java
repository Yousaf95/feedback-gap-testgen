package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

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
        public void testReflectionCompare_NullObjects_2() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_SameObject_2() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    @Test
        public void testReflectionCompare_DifferentClasses_2() {
            assertThrows(ClassCastException.class, () -> 
                CompareToBuilder.reflectionCompare(new Object(), "string"));
        }

    @Test
        public void testReflectionCompare_SuperClass_2() {
            class Parent {}
            class Child extends Parent {}
            Child child = new Child();
            Parent parent = (Parent) child;
            assertEquals(0, CompareToBuilder.reflectionCompare(child, parent));
        }

    @Test
        public void testAppend_Boolean() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, false);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Byte() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 2, (byte) 3);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Char() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Double() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(2.0, 3.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Float() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(2.0f, 3.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Int() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(2, 3);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Long() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(2L, 3L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Short() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 2, (short) 3);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Object() {
            Object obj1 = new Object();
            Object obj2 = new Object();
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(obj1, obj2);
            assertNotEquals(0, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Boolean() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {false, true};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Byte() {
            byte[] lhs = {2, 3};
            byte[] rhs = {3, 4};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Char() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'b', 'c'};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Double() {
            double[] lhs = {2.0, 3.0};
            double[] rhs = {3.0, 4.0};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Float() {
            float[] lhs = {2.0f, 3.0f};
            float[] rhs = {3.0f, 4.0f};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Int() {
            int[] lhs = {2, 3};
            int[] rhs = {3, 4};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Long() {
            long[] lhs = {2L, 3L};
            long[] rhs = {3L, 4L};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Short() {
            short[] lhs = {(short) 2, (short) 3};
            short[] rhs = {(short) 3, (short) 4};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Object() {
            Object[] lhs = {new Object(), null};
            Object[] rhs = {null, new Object()};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertNotEquals(0, builder.toComparison());
        }
}
