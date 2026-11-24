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

    
    @Test
    public void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @ParameterizedTest
    @MethodSource("provideArrayData")
    public void testAppendArray(Object lhs, Object rhs) {
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    
    @Test
    public void testAppendSuper() {
        assertTrue(equalsBuilder.appendSuper(true).isEquals());
        assertFalse(equalsBuilder.appendSuper(false).isEquals());
    }

    }
