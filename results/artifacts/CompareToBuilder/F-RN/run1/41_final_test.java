package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ArrayUtils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CompareToBuilder_GenTest {

    @ParameterizedTest
    @MethodSource("provideDataForBooleanComparison")
    public void testAppendBoolean(boolean lhs, boolean rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForBooleanComparison() {
        return new Arguments[]{
            Arguments.of(true, true, 0),
            Arguments.of(true, false, 1),
            Arguments.of(false, true, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForByteComparison")
    public void testAppendByte(byte lhs, byte rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForByteComparison() {
        return new Arguments[]{
            Arguments.of((byte) 0, (byte) 0, 0),
            Arguments.of((byte) 1, (byte) 0, 1),
            Arguments.of((byte) 0, (byte) 1, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForCharComparison")
    public void testAppendChar(char lhs, char rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForCharComparison() {
        return new Arguments[]{
            Arguments.of('a', 'a', 0),
            Arguments.of('b', 'a', 1),
            Arguments.of('a', 'b', -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForDoubleComparison")
    public void testAppendDouble(double lhs, double rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForDoubleComparison() {
        return new Arguments[]{
            Arguments.of(0.0, 0.0, 0),
            Arguments.of(1.0, 0.0, 1),
            Arguments.of(0.0, 1.0, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForFloatComparison")
    public void testAppendFloat(float lhs, float rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForFloatComparison() {
        return new Arguments[]{
            Arguments.of(0.0f, 0.0f, 0),
            Arguments.of(1.0f, 0.0f, 1),
            Arguments.of(0.0f, 1.0f, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForIntComparison")
    public void testAppendInt(int lhs, int rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForIntComparison() {
        return new Arguments[]{
            Arguments.of(0, 0, 0),
            Arguments.of(1, 0, 1),
            Arguments.of(0, 1, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForLongComparison")
    public void testAppendLong(long lhs, long rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForLongComparison() {
        return new Arguments[]{
            Arguments.of(0L, 0L, 0),
            Arguments.of(1L, 0L, 1),
            Arguments.of(0L, 1L, -1)
        };
    }

    @ParameterizedTest
    @MethodSource("provideDataForShortComparison")
    public void testAppendShort(short lhs, short rhs, int expected) {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(lhs, rhs);
        assertEquals(expected, builder.toComparison());
    }

    private static Arguments[] provideDataForShortComparison() {
        return new Arguments[]{
            Arguments.of((short) 0, (short) 0, 0),
            Arguments.of((short) 1, (short) 0, 1),
            Arguments.of((short) 0, (short) 1, -1)
        };
    }

    
    @Test
    public void testAppendObject() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(new Integer(1), new Integer(2));
        assertEquals(-1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append(new Integer(2), new Integer(1));
        assertEquals(1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append("a", "b");
        assertEquals(-1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append("b", "a");
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppendObjectWithComparator() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(new Integer(1), new Integer(2), Comparator.reverseOrder());
        assertEquals(1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append(new Integer(2), new Integer(1), Comparator.reverseOrder());
        assertEquals(-1, builder.toComparison());
    }

    @Test
    public void testAppendArray() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(new int[]{1}, new int[]{2});
        assertEquals(-1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append(new int[]{2}, new int[]{1});
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppendArrayWithComparator() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(new Integer[]{1}, new Integer[]{2}, Comparator.reverseOrder());
        assertEquals(1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append(new Integer[]{2}, new Integer[]{1}, Comparator.reverseOrder());
        assertEquals(-1, builder.toComparison());
    }

    @Test
    public void testAppendSuper() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.appendSuper(1);
        assertEquals(1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.appendSuper(-1);
        assertEquals(-1, builder.toComparison());
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((Object) null, (Object) null);
            assertEquals(0, builder.toComparison());
        }

    
    @Test
        public void testAppendArrayWithNullElements() {
            Integer[] lhs = {1, 2, null};
            Integer[] rhs = {1, 2, null};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentNullElements() {
            Integer[] lhs = {1, 2, null};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentLengths() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(1, builder.toComparison());
        }

    
    @Test
        public void testAppendArrayWithSameOrder() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentComparators() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 4};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, Comparator.reverseOrder());
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithSameComparators() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, Comparator.reverseOrder());
            assertEquals(0, builder.toComparison());
        }

    
    @Test
        public void testAppendArrayWithEmptyArrays() {
            Integer[] lhs = {};
            Integer[] rhs = {};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    
    
    @Test
        public void testAppendArrayWithEmptyArrays_2() {
            Integer[] lhs = {};
            Integer[] rhs = {};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithNullElements_2() {
            Integer[] lhs = {1, null, 3};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentNullElements_2() {
            Integer[] lhs = {null, 2, 3};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentLengths_2() {
            Integer[] lhs = {1, 2};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithSameOrder_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithDifferentComparatorsAndNullElements() {
            Integer[] lhs = {null, 2, 3};
            Integer[] rhs = {1, null, 3};
            Comparator<Integer> comparator = (a, b) -> a - b;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, comparator);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithSameComparatorsAndNullElements() {
            Integer[] lhs = {null, 2, 3};
            Integer[] rhs = {1, null, 3};
            Comparator<Integer> comparator = (a, b) -> a - b;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs, comparator);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArrayWithEmptyArraysAndNullElements() {
            Integer[] lhs = {};
            Integer[] rhs = {null};
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(lhs, rhs);
            assertEquals(-1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendArrayWithDifferentComparatorsAndNullElements_2() {
            Integer[] lhs = {1, 2, null};
            Integer[] rhs = {1, null, 3};
            Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
            new CompareToBuilder().append(lhs, rhs, comparator).toComparison();
        }

    @Test
        public void testAppendArrayWithSameComparatorsAndNullElements_2() {
            Integer[] lhs = {1, 2, null};
            Integer[] rhs = {1, 2, null};
            Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
            new CompareToBuilder().append(lhs, rhs, comparator).toComparison();
        }

    @Test
        public void testAppendArrayWithEmptyArraysAndNullElements_2() {
            Integer[] lhs = {};
            Integer[] rhs = {null};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }

    @Test
        public void testAppendArrayWithDifferentComparators_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {4, 5, 6};
            Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
            new CompareToBuilder().append(lhs, rhs, comparator).toComparison();
        }

    @Test
        public void testAppendArrayWithSameComparators_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 3};
            Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
            new CompareToBuilder().append(lhs, rhs, comparator).toComparison();
        }

    @Test
        public void testAppendArrayWithEmptyArrays_2_2() {
            Integer[] lhs = {};
            Integer[] rhs = {};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }

    @Test
        public void testAppendArrayWithNullElements_2_2() {
            Integer[] lhs = {1, null, 3};
            Integer[] rhs = {1, null, 3};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }

    @Test
        public void testAppendArrayWithDifferentNullElements_2_2() {
            Integer[] lhs = {1, null, 3};
            Integer[] rhs = {1, 2, 3};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }

    @Test
        public void testAppendArrayWithDifferentLengths_2_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {4, 5};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }

    @Test
        public void testAppendArrayWithSameOrder_2_2() {
            Integer[] lhs = {1, 2, 3};
            Integer[] rhs = {1, 2, 3};
            new CompareToBuilder().append(lhs, rhs).toComparison();
        }
}
