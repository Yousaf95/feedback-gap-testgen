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
    public void testAppendNull() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(null, null);
        assertEquals(0, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append(null, "a");
        assertEquals(-1, builder.toComparison());

        builder = new CompareToBuilder();
        builder.append("a", null);
        assertEquals(1, builder.toComparison());
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

    @Test
    public void testReflectionCompare() {
        class TestClass {
            private int a;
            private String b;

            public TestClass(int a, String b) {
                this.a = a;
                this.b = b;
            }
        }

        TestClass obj1 = new TestClass(1, "a");
        TestClass obj2 = new TestClass(2, "b");

        assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2));
        assertEquals(1, CompareToBuilder.reflectionCompare(obj2, obj1));

        obj2.a = 1;
        assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2));

        obj2.b = "a";
        assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2));
    }

    @Test
    public void testReflectionCompareWithExcludeFields() {
        class TestClass {
            private int a;
            private String b;

            public TestClass(int a, String b) {
                this.a = a;
                this.b = b;
            }
        }

        TestClass obj1 = new TestClass(1, "a");
        TestClass obj2 = new TestClass(2, "b");

        assertEquals(-1, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("b")));
        assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, Collections.singletonList("a")));
    }

    @Test
    public void testReflectionCompareWithNull() {
        class TestClass {
            private int a;
            private String b;

            public TestClass(int a, String b) {
                this.a = a;
                this.b = b;
            }
        }

        TestClass obj1 = new TestClass(1, "a");
        TestClass obj2 = null;

        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(obj1, obj2));
    }
}
