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
    @MethodSource("provideDataForReflectionCompare")
    public void testReflectionCompareWithCollection(Object lhs, Object rhs, boolean compareTransients, Collection<String> excludeFields, int expected) {
        assertEquals(expected, CompareToBuilder.reflectionCompare(lhs, rhs, compareTransients, excludeFields));
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

    private static Stream<Arguments> provideDataForReflectionCompare() {
        return Arrays.stream(new Arguments[]{
            arguments(null, null, false, null, null, 0),
            arguments(new Object(), new Object(), false, null, null, 0),
            arguments("a", "b", false, null, null, -1),
            arguments("b", "a", false, null, null, 1),
            arguments(1, 2, false, null, null, -1),
            arguments(2, 1, false, null, null, 1)
        });
    }

    private static Stream<Arguments> provideDataForAppend() {
        return Arrays.stream(new Arguments[]{
            arguments(true, true, 0),
            arguments(true, false, 1),
            arguments(false, true, -1),
            arguments((byte) 1, (byte) 1, 0),
            arguments((byte) 2, (byte) 1, 1),
            arguments((byte) 1, (byte) 2, -1),
            arguments('a', 'a', 0),
            arguments('b', 'a', 1),
            arguments('a', 'b', -1),
            arguments(1.0, 1.0, 0),
            arguments(2.0, 1.0, 1),
            arguments(1.0, 2.0, -1),
            arguments(1.0f, 1.0f, 0),
            arguments(2.0f, 1.0f, 1),
            arguments(1.0f, 2.0f, -1),
            arguments(1L, 1L, 0),
            arguments(2L, 1L, 1),
            arguments(1L, 2L, -1),
            arguments((short) 1, (short) 1, 0),
            arguments((short) 2, (short) 1, 1),
            arguments((short) 1, (short) 2, -1)
        });
    }

    private static Stream<Arguments> provideDataForAppendArray() {
        return Arrays.stream(new Arguments[]{
            arguments(null, null, 0),
            arguments(new boolean[]{true}, new boolean[]{true}, 0),
            arguments(new byte[]{1}, new byte[]{1}, 0),
            arguments(new char[]{'a'}, new char[]{'a'}, 0),
            arguments(new double[]{1.0}, new double[]{1.0}, 0),
            arguments(new float[]{1.0f}, new float[]{1.0f}, 0),
            arguments(new int[]{1}, new int[]{1}, 0),
            arguments(new long[]{1L}, new long[]{1L}, 0),
            arguments(new short[]{1}, new short[]{1}, 0)
        });
    }

    private static Stream<Arguments> provideDataForAppendObject() {
        return Arrays.stream(new Arguments[]{
            arguments(null, null, null, 0),
            arguments("a", "b", null, -1),
            arguments("b", "a", null, 1),
            arguments(1, 2, Comparator.naturalOrder(), -1),
            arguments(2, 1, Comparator.naturalOrder(), 1)
        });
    }

    private static Stream<Arguments> provideDataForAppendObjectArray() {
        return Arrays.stream(new Arguments[]{
            arguments(null, null, null, 0),
            arguments(new String[]{"a"}, new String[]{"b"}, null, -1),
            arguments(new String[]{"b"}, new String[]{"a"}, null, 1),
            arguments(new Integer[]{1}, new Integer[]{2}, Comparator.naturalOrder(), -1),
            arguments(new Integer[]{2}, new Integer[]{1}, Comparator.naturalOrder(), 1)
        });
    }
}
