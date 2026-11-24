package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CompareToBuilder_GenTest {

    @ParameterizedTest
    @MethodSource("provideDataForReflectionCompare")
    public void testReflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class<?> reflectUpToClass, String[] excludeFields, int expected) {
        assertEquals(expected, CompareToBuilder.reflectionCompare(lhs, rhs, compareTransients, reflectUpToClass, excludeFields));
    }

    
    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppend(boolean lhs, boolean rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendByte(byte lhs, byte rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendChar(char lhs, char rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendDouble(double lhs, double rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendFloat(float lhs, float rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendInt(int lhs, int rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendLong(long lhs, long rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppend")
    public void testAppendShort(short lhs, short rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendBooleanArray(boolean[] lhs, boolean[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendByteArray(byte[] lhs, byte[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendCharArray(char[] lhs, char[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendDoubleArray(double[] lhs, double[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendFloatArray(float[] lhs, float[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendIntArray(int[] lhs, int[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendLongArray(long[] lhs, long[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendArray")
    public void testAppendShortArray(short[] lhs, short[] rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendObject")
    public void testAppendObject(Object lhs, Object rhs, Comparator<?> comparator, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs, comparator).toComparison());
    }

    @ParameterizedTest
    @MethodSource("provideDataForAppendObjectArray")
    public void testAppendObjectArray(Object[] lhs, Object[] rhs, Comparator<?> comparator, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(expected, builder.append(lhs, rhs, comparator).toComparison());
    }

    
    
    
    
    }
