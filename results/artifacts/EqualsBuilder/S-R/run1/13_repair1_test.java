package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.Arrays;
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

    private static Stream<Arguments> provideBooleanData() {
        return Arrays.stream(new Object[][] {
            { true, true },
            { false, false },
            { true, false }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideByteData")
    public void testAppendByte(byte lhs, byte rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideByteData() {
        return Arrays.stream(new Object[][] {
            { (byte) 0, (byte) 0 },
            { (byte) 1, (byte) 1 },
            { (byte) -1, (byte) -1 }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideCharData")
    public void testAppendChar(char lhs, char rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideCharData() {
        return Arrays.stream(new Object[][] {
            { 'a', 'a' },
            { 'b', 'b' },
            { 'z', 'z' }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideDoubleData")
    public void testAppendDouble(double lhs, double rhs) {
        assertEquals(Double.doubleToLongBits(lhs) == Double.doubleToLongBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideDoubleData() {
        return Arrays.stream(new Object[][] {
            { 0.0, 0.0 },
            { 1.0, 1.0 },
            { -1.0, -1.0 }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideFloatData")
    public void testAppendFloat(float lhs, float rhs) {
        assertEquals(Float.floatToIntBits(lhs) == Float.floatToIntBits(rhs), equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideFloatData() {
        return Arrays.stream(new Object[][] {
            { 0.0f, 0.0f },
            { 1.0f, 1.0f },
            { -1.0f, -1.0f }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideIntData")
    public void testAppendInt(int lhs, int rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideIntData() {
        return Arrays.stream(new Object[][] {
            { 0, 0 },
            { 1, 1 },
            { -1, -1 }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideLongData")
    public void testAppendLong(long lhs, long rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideLongData() {
        return Arrays.stream(new Object[][] {
            { 0L, 0L },
            { 1L, 1L },
            { -1L, -1L }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("provideShortData")
    public void testAppendShort(short lhs, short rhs) {
        assertEquals(lhs == rhs, equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideShortData() {
        return Arrays.stream(new Object[][] {
            { (short) 0, (short) 0 },
            { (short) 1, (short) 1 },
            { (short) -1, (short) -1 }
        }).map(Arguments::of);
    }

    @Test
    public void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    public void testAppendNull() {
        assertTrue(equalsBuilder.append(null, null).isEquals());
        assertFalse(equalsBuilder.append(null, new Object()).isEquals());
        assertFalse(equalsBuilder.append(new Object(), null).isEquals());
    }

    @ParameterizedTest
    @MethodSource("provideArrayData")
    public void testAppendArray(Object lhs, Object rhs) {
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    private static Stream<Arguments> provideArrayData() {
        return Arrays.stream(new Object[][] {
            { new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 } },
            { new String[] { "a", "b" }, new String[] { "a", "b" } }
        }).map(Arguments::of);
    }

    @Test
    public void testAppendSuper() {
        assertTrue(equalsBuilder.appendSuper(true).isEquals());
        assertFalse(equalsBuilder.appendSuper(false).isEquals());
    }

    @Test
    public void testReset() {
        equalsBuilder.setEquals(false);
        assertEquals(true, equalsBuilder.reset().isEquals());
    }
}
