package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BooleanUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // No setup needed for this test class
    }

    @ParameterizedTest
    @MethodSource("provideAndTestData")
    public void testAnd(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.and(array));
    }

    private static Stream<Arguments> provideAndTestData() {
        return Stream.of(
            arguments(new boolean[]{true, true, true}, true),
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrTestData")
    public void testOr(boolean[] array, boolean expected) {
        assertEquals(expected, BooleanUtils.or(array));
    }

    private static Stream<Arguments> provideOrTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    
    private static Stream<Arguments> provideXorTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    @Test
    public void testAndWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
    }

    @Test
    public void testOrWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.or((Boolean[]) null));
    }

    @Test
    public void testXorWithNullArrayThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
    }

    
    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, BooleanUtils.booleanValues());
    }

    @Test
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(bool));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, true),
            arguments(Boolean.FALSE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanObjectTestData")
    public void testToBooleanObject(String str, Boolean expected) {
        assertEquals(expected, BooleanUtils.toBooleanObject(str));
    }

    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            arguments(null, null),
            arguments(Boolean.TRUE.toString(), Boolean.TRUE),
            arguments(Boolean.FALSE.toString(), Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringTestData")
    public void testToString(boolean bool, String trueString, String falseString, String expected) {
        assertEquals(expected, BooleanUtils.toString(bool, trueString, falseString));
    }

    private static Stream<Arguments> provideToStringTestData() {
        return Stream.of(
            arguments(true, "yes", "no", "yes"),
            arguments(false, "yes", "no", "no")
        );
    }

    
    @ParameterizedTest
    @MethodSource("provideIsTrueTestData")
    public void testIsTrue(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isTrue(bool));
    }

    private static Stream<Arguments> provideIsTrueTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, true),
            arguments(Boolean.FALSE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsFalseTestData")
    public void testIsFalse(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.isFalse(bool));
    }

    private static Stream<Arguments> provideIsFalseTestData() {
        return Stream.of(
            arguments(null, false),
            arguments(Boolean.TRUE, false),
            arguments(Boolean.FALSE, true)
        );
    }

    
    
    
    @Test
        public void testBooleanValues_2() {
            assertEquals(Boolean.FALSE, BooleanUtils.booleanValues()[0]);
            assertEquals(Boolean.TRUE, BooleanUtils.booleanValues()[1]);
        }

    @Test
        public void testPrimitiveValues_2() {
            assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
        }

    @Test
        public void testCompareTrueTrue() {
            assertEquals(0, BooleanUtils.compare(true, true));
        }

    @Test
        public void testCompareTrueFalse() {
            assertEquals(1, BooleanUtils.compare(true, false));
        }

    @Test
        public void testCompareFalseTrue() {
            assertEquals(-1, BooleanUtils.compare(false, true));
        }

    @Test
        public void testForEachAction() {
            boolean[] captured = new boolean[2];
            BooleanUtils.forEach(b -> captured[b ? 0 : 1] = true);
            assertTrue(captured[0]);
            assertTrue(captured[1]);
        }

    @Test
        public void testIsFalseNull() {
            assertFalse(BooleanUtils.isFalse(null));
        }

    @Test
        public void testIsTrueNull() {
            assertFalse(BooleanUtils.isTrue(null));
        }

    @Test
        public void testNegateNull() {
            assertNull(BooleanUtils.negate(null));
        }

    
    @Test
        public void testOneHotMixed() {
            boolean[] array = {true, false, true};
            assertFalse(BooleanUtils.oneHot(array));
        }

    
    
    
    @Test
        public void testToBooleanDefaultIfNullTrue() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        public void testToBooleanDefaultIfNullFalse() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
        }

    @Test
        public void testToBooleanObjectZero() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0));
        }

    @Test
        public void testToBooleanObjectNonZero() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1));
        }

    @Test
        public void testToBooleanObjectNullValue() {
            assertNull(BooleanUtils.toBooleanObject(null, 1, 0, null));
        }

    @Test
        public void testToBooleanObjectTrueValue() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1, 1, 0, null));
        }

    @Test
        public void testToBooleanObjectFalseValue() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0, 1, 0, null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAndWithNullArrayThrowsException_2() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
        }

    @Test
        public void testOrWithNullArrayThrowsException_2() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.or((Boolean[]) null));
        }

    @Test
        public void testXorWithNullArrayThrowsException_2() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
        }

    @Test
        public void testIsFalseNull_2() {
            assertTrue(BooleanUtils.isFalse(null));
        }

    @Test
        public void testIsTrueNull_2() {
            assertFalse(BooleanUtils.isTrue(null));
        }

    @Test
        public void testNegateNull_2() {
            assertNull(BooleanUtils.negate(null));
        }

    @Test
        public void testOneHotMixed_2() {
            assertTrue(BooleanUtils.oneHot(true, false, true));
            assertFalse(BooleanUtils.oneHot(false, true, false));
        }

    @Test
        public void testToBooleanDefaultIfNullTrue_2() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        public void testToBooleanDefaultIfNullFalse_2() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
        }

    @Test
        public void testToBooleanObjectZero_2() {
            assertNull(BooleanUtils.toBooleanObject(0));
        }

    @Test
        public void testToBooleanObjectNonZero_2() {
            assertTrue(BooleanUtils.toBooleanObject(1));
        }

    @Test
        public void testToBooleanObjectNullValue_2() {
            assertNull(BooleanUtils.toBooleanObject(null, 1, 0, -1));
        }

    @Test
        public void testToBooleanObjectTrueValue_2() {
            assertTrue(BooleanUtils.toBooleanObject(1, 1, 0, -1));
        }

    @Test
        public void testToBooleanObjectFalseValue_2() {
            assertFalse(BooleanUtils.toBooleanObject(0, 1, 0, -1));
        }
}
