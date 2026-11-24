package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    // ---- added by coverage-guided refinement ----
    @Test
        void testAndEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
        }

    @Test
        void testOrEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.or((Boolean[]) null));
        }

    @Test
        void testXorEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
        }

    @Test
        void testOneHotEmptyArray() {
            assertThrows(NullPointerException.class, () -> BooleanUtils.oneHot((Boolean[]) null));
        }

    @Test
        void testToIntegerDefaultIfNull() {
            assertEquals(0, BooleanUtils.toIntegerDefaultIfNull(null, 0));
            assertEquals(1, BooleanUtils.toIntegerDefaultIfNull(Boolean.TRUE, 0));
            assertEquals(0, BooleanUtils.toIntegerDefaultIfNull(Boolean.FALSE, 0));
        }

    @Test
        void testToBooleanObjectWithNull() {
            assertNull(BooleanUtils.toBooleanObject((String) null));
            assertTrue(BooleanUtils.toBooleanObject("true"));
            assertFalse(BooleanUtils.toBooleanObject("false"));
        }

    @Test
        void testToStringOnOffDefaultIfNull() {
            assertEquals(null, BooleanUtils.toStringOnOff(null, "on", "off", null));
            assertEquals("on", BooleanUtils.toStringOnOff(true, "on", "off", null));
            assertEquals("off", BooleanUtils.toStringOnOff(false, "on", "off", null));
        }

    @Test
        void testToStringTrueFalseDefaultIfNull() {
            assertEquals(null, BooleanUtils.toStringTrueFalse(null, "true", "false", null));
            assertEquals("true", BooleanUtils.toStringTrueFalse(true, "true", "false", null));
            assertEquals("false", BooleanUtils.toStringTrueFalse(false, "true", "false", null));
        }

    @Test
        void testToStringYesNoDefaultIfNull() {
            assertEquals(null, BooleanUtils.toStringYesNo(null, "yes", "no", null));
            assertEquals("yes", BooleanUtils.toStringYesNo(true, "yes", "no", null));
            assertEquals("no", BooleanUtils.toStringYesNo(false, "yes", "no", null));
        }
}
