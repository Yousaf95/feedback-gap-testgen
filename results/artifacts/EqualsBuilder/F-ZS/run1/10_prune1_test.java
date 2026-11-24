package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    @ParameterizedTest
    @MethodSource("provideBooleanValues")
    void testAppendBoolean(boolean lhs, boolean rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideByteValues")
    void testAppendByte(byte lhs, byte rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideCharValues")
    void testAppendChar(char lhs, char rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideDoubleValues")
    void testAppendDouble(double lhs, double rhs) {
        assertEquals(Double.doubleToLongBits(lhs) == Double.doubleToLongBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideFloatValues")
    void testAppendFloat(float lhs, float rhs) {
        assertEquals(Float.floatToIntBits(lhs) == Float.floatToIntBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideIntValues")
    void testAppendInt(int lhs, int rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideLongValues")
    void testAppendLong(long lhs, long rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideShortValues")
    void testAppendShort(short lhs, short rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @Test
    void testAppendArrayBoolean() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, false};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayByte() {
        byte[] lhs = {(byte) 0, (byte) 1};
        byte[] rhs = {(byte) 0, (byte) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayChar() {
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'b'};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayDouble() {
        double[] lhs = {0.0, 1.0};
        double[] rhs = {0.0, 1.0};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayFloat() {
        float[] lhs = {0.0f, 1.0f};
        float[] rhs = {0.0f, 1.0f};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayInt() {
        int[] lhs = {0, 1};
        int[] rhs = {0, 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayLong() {
        long[] lhs = {0L, 1L};
        long[] rhs = {0L, 1L};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayShort() {
        short[] lhs = {(short) 0, (short) 1};
        short[] rhs = {(short) 0, (short) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertFalse(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectNull() {
        Object lhs = null;
        Object rhs = null;
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectSame() {
        Object obj = new Object();
        assertTrue(equalsBuilder.append(obj, obj).isEquals());
    }

    @Test
    void testAppendArrayObject() {
        Object[] lhs = {new Object(), new Object()};
        Object[] rhs = {new Object(), new Object()};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testReflectionEqualsNull() {
        assertFalse(EqualsBuilder.reflectionEquals(null, null));
        assertFalse(EqualsBuilder.reflectionEquals(new Object(), null));
        assertFalse(EqualsBuilder.reflectionEquals(null, new Object()));
    }

    @Test
    void testReflectionEqualsSameObject() {
        Object obj = new Object();
        assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
    }

    @Test
    void testReflectionEqualsDifferentObjects() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertFalse(EqualsBuilder.reflectionEquals(lhs, rhs));
    }
}
