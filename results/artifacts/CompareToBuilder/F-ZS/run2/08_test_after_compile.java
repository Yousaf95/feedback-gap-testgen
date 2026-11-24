package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

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
    public void testReflectionCompare_Boolean() {
        assertEquals(0, CompareToBuilder.reflectionCompare(true, true));
        assertEquals(-1, CompareToBuilder.reflectionCompare(false, true));
        assertEquals(1, CompareToBuilder.reflectionCompare(true, false));
    }

    @Test
    public void testReflectionCompare_BooleanArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new boolean[]{true}, new boolean[]{true}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new boolean[]{false}, new boolean[]{true}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new boolean[]{true}, new boolean[]{false}) > 0);
    }

    @Test
    public void testReflectionCompare_Byte() {
        assertEquals(0, CompareToBuilder.reflectionCompare((byte) 1, (byte) 1));
        assertEquals(-1, CompareToBuilder.reflectionCompare((byte) 0, (byte) 1));
        assertEquals(1, CompareToBuilder.reflectionCompare((byte) 1, (byte) 0));
    }

    @Test
    public void testReflectionCompare_ByteArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new byte[]{1}, new byte[]{1}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new byte[]{0}, new byte[]{1}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new byte[]{1}, new byte[]{0}) > 0);
    }

    @Test
    public void testReflectionCompare_Char() {
        assertEquals(0, CompareToBuilder.reflectionCompare('a', 'a'));
        assertEquals(-1, CompareToBuilder.reflectionCompare('a', 'b'));
        assertEquals(1, CompareToBuilder.reflectionCompare('b', 'a'));
    }

    @Test
    public void testReflectionCompare_CharArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new char[]{'a'}, new char[]{'a'}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new char[]{'a'}, new char[]{'b'}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new char[]{'b'}, new char[]{'a'}) > 0);
    }

    @Test
    public void testReflectionCompare_Double() {
        assertEquals(0, CompareToBuilder.reflectionCompare(1.0, 1.0));
        assertEquals(-1, CompareToBuilder.reflectionCompare(0.5, 1.0));
        assertEquals(1, CompareToBuilder.reflectionCompare(1.0, 0.5));
    }

    @Test
    public void testReflectionCompare_DoubleArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new double[]{1.0}, new double[]{1.0}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new double[]{0.5}, new double[]{1.0}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new double[]{1.0}, new double[]{0.5}) > 0);
    }

    @Test
    public void testReflectionCompare_Float() {
        assertEquals(0, CompareToBuilder.reflectionCompare(1.0f, 1.0f));
        assertEquals(-1, CompareToBuilder.reflectionCompare(0.5f, 1.0f));
        assertEquals(1, CompareToBuilder.reflectionCompare(1.0f, 0.5f));
    }

    @Test
    public void testReflectionCompare_FloatArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new float[]{1.0f}, new float[]{1.0f}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new float[]{0.5f}, new float[]{1.0f}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new float[]{1.0f}, new float[]{0.5f}) > 0);
    }

    @Test
    public void testReflectionCompare_Int() {
        assertEquals(0, CompareToBuilder.reflectionCompare(1, 1));
        assertEquals(-1, CompareToBuilder.reflectionCompare(0, 1));
        assertEquals(1, CompareToBuilder.reflectionCompare(1, 0));
    }

    @Test
    public void testReflectionCompare_IntArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new int[]{1}, new int[]{1}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new int[]{0}, new int[]{1}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new int[]{1}, new int[]{0}) > 0);
    }

    @Test
    public void testReflectionCompare_Long() {
        assertEquals(0, CompareToBuilder.reflectionCompare(1L, 1L));
        assertEquals(-1, CompareToBuilder.reflectionCompare(0L, 1L));
        assertEquals(1, CompareToBuilder.reflectionCompare(1L, 0L));
    }

    @Test
    public void testReflectionCompare_LongArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new long[]{1L}, new long[]{1L}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new long[]{0L}, new long[]{1L}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new long[]{1L}, new long[]{0L}) > 0);
    }

    @Test
    public void testReflectionCompare_Object() {
        assertEquals(0, CompareToBuilder.reflectionCompare("a", "a"));
        assertEquals(-1, CompareToBuilder.reflectionCompare("a", "b"));
        assertEquals(1, CompareToBuilder.reflectionCompare("b", "a"));
    }

    @Test
    public void testReflectionCompare_ObjectArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new String[]{"a"}, new String[]{"a"}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new String[]{"a"}, new String[]{"b"}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new String[]{"b"}, new String[]{"a"}) > 0);
    }

    @Test
    public void testReflectionCompare_Short() {
        assertEquals(0, CompareToBuilder.reflectionCompare((short) 1, (short) 1));
        assertEquals(-1, CompareToBuilder.reflectionCompare((short) 0, (short) 1));
        assertEquals(1, CompareToBuilder.reflectionCompare((short) 1, (short) 0));
    }

    @Test
    public void testReflectionCompare_ShortArray() {
        assertTrue(CompareToBuilder.reflectionCompare(new short[]{1}, new short[]{1}) == 0);
        assertTrue(CompareToBuilder.reflectionCompare(new short[]{0}, new short[]{1}) < 0);
        assertTrue(CompareToBuilder.reflectionCompare(new short[]{1}, new short[]{0}) > 0);
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
}
