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
        public void testReflectionCompare_SuperClass_2() {
            class Parent {}
            class Child extends Parent {}
            Child child = new Child();
            Parent parent = (Parent) child;
            assertEquals(0, CompareToBuilder.reflectionCompare(child, parent));
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_NullObjects_3() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_SameObject_3() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    
    @Test
        public void testReflectionCompare_SuperClass_3() {
            class Parent {}
            class Child extends Parent {}
    
            Child child = new Child();
            Parent parent = (Parent) child;
            assertEquals(0, CompareToBuilder.reflectionCompare(child, parent));
        }

    @Test
        public void testAppend_Byte_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Char_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Double_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 2.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Float_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 2.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Int_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Long_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1L, 2L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Short_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 1, (short) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Byte_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Char_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Double_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new double[]{1.0}, new double[]{2.0});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Float_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f}, new float[]{2.0f});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Int_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{1}, new int[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Long_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new long[]{1L}, new long[]{2L});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Short_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new short[]{(short) 1}, new short[]{(short) 2});
            assertEquals(-1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_NullObjects_4() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_SameObject_4() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    
    @Test
        public void testReflectionCompare_SuperClass_4() {
            class Parent {}
            class Child extends Parent {}
            Child child = new Child();
            assertEquals(0, CompareToBuilder.reflectionCompare(child, child));
        }

    @Test
        public void testAppend_Byte_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Char_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Double_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 2.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Float_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 2.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Int_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Long_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1L, 2L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Short_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 1, (short) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Byte_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Char_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{'b'});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Double_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new double[]{1.0}, new double[]{2.0});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Float_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f}, new float[]{2.0f});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Int_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{1}, new int[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Long_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new long[]{1L}, new long[]{2L});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_Array_Short_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new short[]{(short) 1}, new short[]{(short) 2});
            assertEquals(-1, builder.toComparison());
        }

    }
