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
        public void testReflectionCompare_NullObjects() {
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
        }

    @Test
        public void testReflectionCompare_SameObject() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test"));
        }

    
    
    
    @Test
        public void testAppendBoolean_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(false, false);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendByte_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendByte_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 0, (byte) 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendChar_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendChar_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'a');
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendDouble_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 2.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendDouble_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0.0, 0.0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendFloat_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 2.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendFloat_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0.0f, 0.0f);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendInt_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendInt_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0, 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendLong_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1L, 2L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendLong_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0L, 0L);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendShort_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 1, (short) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendShort_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 0, (short) 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendObject_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append("test1", "test2");
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendObject_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append("test", "test");
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendArray_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{1}, new int[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendArray_ComparisonZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new int[]{0}, new int[]{0});
            assertEquals(0, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_NullObjects_2() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_SameObject_2() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    @Test
        public void testAppendBoolean_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(false, false);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendBoolean_ComparisonNotZero() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, false);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendByte_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 0, (byte) 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendByte_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendChar_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'a');
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendChar_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('b', 'a');
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendDouble_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0.0, 0.0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendDouble_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 2.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendFloat_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0.0f, 0.0f);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendFloat_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 2.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendInt_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0, 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendInt_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendLong_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(0L, 0L);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendLong_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1L, 2L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendShort_ComparisonZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 0, (short) 0);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendShort_ComparisonNotZero_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 1, (short) 2);
            assertEquals(-1, builder.toComparison());
        }
}
