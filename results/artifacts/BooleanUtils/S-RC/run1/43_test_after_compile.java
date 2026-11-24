package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.function.Consumer;

public class BooleanUtils_GenTest {

    
    private static Stream<Arguments> provideAndTestData() {
        return Stream.of(
            of(new boolean[]{true, true}, true),
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, false}, false),
            of(new boolean[]{false, true}, false),
            of(new boolean[]{}, false)
        );
    }

    
    private static Stream<Arguments> provideOrTestData() {
        return Stream.of(
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, true}, true),
            of(new boolean[]{true, false}, true),
            of(new boolean[]{false, true}, true),
            of(new boolean[]{}, false)
        );
    }

    
    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            of(new boolean[]{false}, true),
            of(new boolean[]{true}, false),
            of(new boolean[]{false, true}, false),
            of(new boolean[]{true, false}, false),
            of(new boolean[]{}, false)
        );
    }

    
    private static Stream<Arguments> provideXorTestData() {
        return Stream.of(
            of(new boolean[]{false, false}, false),
            of(new boolean[]{true, true}, false),
            of(new boolean[]{true, false}, true),
            of(new boolean[]{false, true}, true),
            of(new boolean[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(Boolean bool, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(bool));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            of(null, false),
            of(false, false),
            of(true, true)
        );
    }

    
    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            of(null, null),
            of(false, Boolean.FALSE),
            of(true, Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringTrueFalseTestData")
    public void testToStringTrueFalse(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringTrueFalse(bool));
    }

    private static Stream<Arguments> provideToStringTrueFalseTestData() {
        return Stream.of(
            of(null, null),
            of(false, "false"),
            of(true, "true")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringYesNoTestData")
    public void testToStringYesNo(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringYesNo(bool));
    }

    private static Stream<Arguments> provideToStringYesNoTestData() {
        return Stream.of(
            of(null, null),
            of(false, "no"),
            of(true, "yes")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringOnOffTestData")
    public void testToStringOnOff(Boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringOnOff(bool));
    }

    private static Stream<Arguments> provideToStringOnOffTestData() {
        return Stream.of(
            of(null, null),
            of(false, "off"),
            of(true, "on")
        );
    }

    @Test
    public void testBooleanValues() {
        assertArrayEquals(new Boolean[]{false, true}, BooleanUtils.booleanValues());
    }

    @Test
    public void testPrimitiveValues() {
        assertArrayEquals(new boolean[]{false, true}, BooleanUtils.primitiveValues());
    }

    @ParameterizedTest
    @MethodSource("provideToIntegerTestData")
    public void testToInteger(boolean bool, int expected) {
        assertEquals(expected, BooleanUtils.toInteger(bool));
    }

    private static Stream<Arguments> provideToIntegerTestData() {
        return Stream.of(
            of(false, 0),
            of(true, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToIntegerObjectTestData")
    public void testToIntegerObject(boolean bool, Integer expected) {
        assertEquals(expected, BooleanUtils.toIntegerObject(bool));
    }

    private static Stream<Arguments> provideToIntegerObjectTestData() {
        return Stream.of(
            of(false, 0),
            of(true, 1)
        );
    }

    
    private static Stream<Arguments> emptyArrayProvider() {
            return Stream.of(
                Arguments.of((Boolean[]) null),
                Arguments.of(new Boolean[0])
            );
        }

    
    private static Stream<Arguments> allTrueProvider() {
            return Stream.of(
                Arguments.of(true, true, true),
                Arguments.of(true, true, true, true)
            );
        }

    
    private static Stream<Arguments> mixedBooleansProvider() {
            return Stream.of(
                Arguments.of(true, false, true),
                Arguments.of(false, true, false)
            );
        }

    
    
    private static Stream<Arguments> allFalseProvider() {
            return Stream.of(
                Arguments.of(false, false, false),
                Arguments.of(false, false, false, false)
            );
        }

    
    
    
    private static Stream<Arguments> allFalseProvider2() {
            return Stream.of(
                Arguments.of(false, false, false),
                Arguments.of(false, false, false, false)
            );
        }

    
    private static Stream<Arguments> mixedBooleansProvider3() {
            return Stream.of(
                Arguments.of(true, false, true),
                Arguments.of(false, true, false)
            );
        }

    
    @ParameterizedTest
        @MethodSource("toBooleanObjectProvider")
        public void testToBooleanObjectTrueString(String str) {
            assertTrue(BooleanUtils.toBoolean(str));
        }

    private static Stream<Arguments> toBooleanObjectProvider() {
            return Stream.of(
                Arguments.of(BooleanUtils.TRUE),
                Arguments.of("true"),
                Arguments.of("TRUE"),
                Arguments.of("tRUE"),
                Arguments.of("TrUe")
            );
        }

    @ParameterizedTest
        @MethodSource("toBooleanObjectProvider2")
        public void testToBooleanObjectFalseString(String str) {
            assertFalse(BooleanUtils.toBoolean(str));
        }

    private static Stream<Arguments> toBooleanObjectProvider2() {
            return Stream.of(
                Arguments.of(BooleanUtils.FALSE),
                Arguments.of("false"),
                Arguments.of("FALSE"),
                Arguments.of("fAlSe"),
                Arguments.of("FaLsE")
            );
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAndEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.and());
        }

    @Test
        public void testAndAllTrue() {
            assertTrue(BooleanUtils.and(true, true, true));
        }

    @Test
        public void testAndMixedBooleans() {
            assertFalse(BooleanUtils.and(true, false, true));
        }

    @Test
        public void testOrEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.or());
        }

    @Test
        public void testOrAllFalse() {
            assertFalse(BooleanUtils.or(false, false, false));
        }

    @Test
        public void testOrMixedBooleans() {
            assertTrue(BooleanUtils.or(true, false, true));
        }

    @Test
        public void testOneHotEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.oneHot());
        }

    @Test
        public void testOneHotAllFalse() {
            assertFalse(BooleanUtils.oneHot(false, false, false));
        }

    @Test
        public void testOneHotMixedBooleans() {
            assertTrue(BooleanUtils.oneHot(true, false, true));
        }

    @Test
        public void testToBooleanObjectNull() {
            assertNull(BooleanUtils.toBooleanObject(null));
        }

    @Test
        public void testToBooleanObjectTrueString_2() {
            assertTrue(BooleanUtils.toBooleanObject(TRUE));
        }

    @Test
        public void testToBooleanObjectFalseString_2() {
            assertFalse(BooleanUtils.toBooleanObject(FALSE));
        }

    @Test
        public void testToBooleanObjectOtherString() {
            assertNull(BooleanUtils.toBooleanObject("other"));
        }

    @Test
        public void testToIntegerObjectNull() {
            assertNull(BooleanUtils.toIntegerObject(null));
        }

    @Test
        public void testToIntegerObjectTrue() {
            assertEquals(NumberUtils.INTEGER_ONE, BooleanUtils.toIntegerObject(true));
        }

    @Test
        public void testToIntegerObjectFalse() {
            assertEquals(NumberUtils.INTEGER_ZERO, BooleanUtils.toIntegerObject(false));
        }

    @Test
        public void testToStringOnOffNull() {
            assertNull(BooleanUtils.toStringOnOff(null));
        }

    @Test
        public void testToStringOnOffTrue() {
            assertEquals(ON, BooleanUtils.toStringOnOff(true));
        }

    @Test
        public void testToStringOnOffFalse() {
            assertEquals(OFF, BooleanUtils.toStringOnOff(false));
        }

    @Test
        public void testToStringTrueFalseNull() {
            assertNull(BooleanUtils.toStringTrueFalse(null));
        }

    @Test
        public void testToStringTrueFalseTrue() {
            assertEquals(TRUE, BooleanUtils.toStringTrueFalse(true));
        }

    @Test
        public void testToStringTrueFalseFalse() {
            assertEquals(FALSE, BooleanUtils.toStringTrueFalse(false));
        }

    @Test
        public void testToStringYesNoNull() {
            assertNull(BooleanUtils.toStringYesNo(null));
        }

    @Test
        public void testToStringYesNoTrue() {
            assertEquals(YES, BooleanUtils.toStringYesNo(true));
        }

    @Test
        public void testToStringYesNoFalse() {
            assertEquals(NO, BooleanUtils.toStringYesNo(false));
        }
}
