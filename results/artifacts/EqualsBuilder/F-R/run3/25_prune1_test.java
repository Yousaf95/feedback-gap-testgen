package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    public void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    @ParameterizedTest
    @MethodSource("provideBooleanData")
    public void testAppendBoolean(boolean lhs, boolean rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideByteData")
    public void testAppendByte(byte lhs, byte rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideCharData")
    public void testAppendChar(char lhs, char rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideDoubleData")
    public void testAppendDouble(double lhs, double rhs) {
        assertEquals(Double.doubleToLongBits(lhs) == Double.doubleToLongBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideFloatData")
    public void testAppendFloat(float lhs, float rhs) {
        assertEquals(Float.floatToIntBits(lhs) == Float.floatToIntBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideIntData")
    public void testAppendInt(int lhs, int rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideLongData")
    public void testAppendLong(long lhs, long rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideShortData")
    public void testAppendShort(short lhs, short rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideBooleanArrayData")
    public void testAppendBooleanArray(boolean[] lhs, boolean[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideByteArrayData")
    public void testAppendByteArray(byte[] lhs, byte[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideCharArrayData")
    public void testAppendCharArray(char[] lhs, char[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideDoubleArrayData")
    public void testAppendDoubleArray(double[] lhs, double[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideFloatArrayData")
    public void testAppendFloatArray(float[] lhs, float[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideIntArrayData")
    public void testAppendIntArray(int[] lhs, int[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideLongArrayData")
    public void testAppendLongArray(long[] lhs, long[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideShortArrayData")
    public void testAppendShortArray(short[] lhs, short[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    @ParameterizedTest
    @MethodSource("provideObjectData")
    public void testAppendObject(Object lhs, Object rhs) {
        assertEquals(lhs.equals(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideObjectArrayData")
    public void testAppendObjectArray(Object[] lhs, Object[] rhs) {
        assertArrayEquals(lhs, rhs);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
