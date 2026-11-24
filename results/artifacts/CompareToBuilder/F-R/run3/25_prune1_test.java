package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CompareToBuilder_GenTest {

    private CompareToBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new CompareToBuilder();
    }

    @ParameterizedTest
    @MethodSource("provideBooleanData")
    public void testAppendBoolean(boolean lhs, boolean rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideByteData")
    public void testAppendByte(byte lhs, byte rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideCharData")
    public void testAppendChar(char lhs, char rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideDoubleData")
    public void testAppendDouble(double lhs, double rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideFloatData")
    public void testAppendFloat(float lhs, float rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideIntData")
    public void testAppendInt(int lhs, int rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideLongData")
    public void testAppendLong(long lhs, long rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideShortData")
    public void testAppendShort(short lhs, short rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideObjectData")
    public void testAppendObject(Object lhs, Object rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @ParameterizedTest
    @MethodSource("provideArrayData")
    public void testAppendArray(Object lhs, Object rhs, int expected) {
        assertEquals(expected, builder.append(lhs, rhs).toComparison());
    }

    
    @Test
    public void testAppendSuper() {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(0, builder.appendSuper(0).toComparison());
        assertEquals(-1, builder.appendSuper(-1).toComparison());
        assertEquals(1, builder.appendSuper(1).toComparison());
    }

    @Test
    public void testReflectionCompare() {
        class TestClass {
            int field1 = 1;
            String field2 = "test";
        }
        TestClass obj1 = new TestClass();
        TestClass obj2 = new TestClass();
        assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2));

        obj1.field1 = 2;
        assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2));

        obj2.field1 = 3;
        assertEquals(1, CompareToBuilder.reflectionCompare(obj1, obj2));
    }

    @Test
    public void testReflectionCompareWithExcludeFields() {
        class TestClass {
            int field1 = 1;
            String field2 = "test";
        }
        TestClass obj1 = new TestClass();
        TestClass obj2 = new TestClass();
        assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("field1")));

        obj1.field2 = "other";
        assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("field1")));
    }

    @Test
    public void testReflectionCompareWithNull() {
        class TestClass {
            int field1 = 1;
        }
        TestClass obj1 = new TestClass();
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(obj1, null));
    }

    @Test
    public void testAppendArrayWithComparator() {
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 4};
        Comparator<Integer> comparator = Comparator.naturalOrder();
        assertEquals(-1, builder.append(array1, array2, comparator).toComparison());
    }
}
