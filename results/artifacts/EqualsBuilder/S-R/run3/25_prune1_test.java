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

    private EqualsBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new EqualsBuilder();
    }

    @ParameterizedTest
    @MethodSource("provideBooleanData")
    public void testAppendBoolean(boolean lhs, boolean rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideByteData")
    public void testAppendByte(byte lhs, byte rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideCharData")
    public void testAppendChar(char lhs, char rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideDoubleData")
    public void testAppendDouble(double lhs, double rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideFloatData")
    public void testAppendFloat(float lhs, float rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideIntData")
    public void testAppendInt(int lhs, int rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideLongData")
    public void testAppendLong(long lhs, long rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideShortData")
    public void testAppendShort(short lhs, short rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideObjectData")
    public void testAppendObject(Object lhs, Object rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideArrayData")
    public void testAppendArray(Object lhs, Object rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @Test
    public void testAppendSuper() {
        builder.appendSuper(true);
        assertTrue(builder.isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideBooleanArrayData")
    public void testAppendBooleanArray(boolean[] lhs, boolean[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideByteArrayData")
    public void testAppendByteArray(byte[] lhs, byte[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideCharArrayData")
    public void testAppendCharArray(char[] lhs, char[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideDoubleArrayData")
    public void testAppendDoubleArray(double[] lhs, double[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideFloatArrayData")
    public void testAppendFloatArray(float[] lhs, float[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideIntArrayData")
    public void testAppendIntArray(int[] lhs, int[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideLongArrayData")
    public void testAppendLongArray(long[] lhs, long[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideShortArrayData")
    public void testAppendShortArray(short[] lhs, short[] rhs) {
        builder.append(lhs, rhs);
        assertTrue(builder.isEquals());
    }

    
    @Test
    public void testReset() {
        builder.append(true, false);
        assertFalse(builder.isEquals());
        builder.reset();
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    
    
    
    
    }
