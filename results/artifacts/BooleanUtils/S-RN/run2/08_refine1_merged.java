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
        // No-op setup method
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

    
    private static Stream<Arguments> provideOneHotTestData() {
        return Stream.of(
            arguments(new boolean[]{false, false, false}, false),
            arguments(new boolean[]{true, false, true}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanObjectTestData")
    public void testToBooleanObject(String str, Boolean expected) {
        assertEquals(expected, BooleanUtils.toBooleanObject(str));
    }

    private static Stream<Arguments> provideToBooleanObjectTestData() {
        return Stream.of(
            arguments("true", Boolean.TRUE),
            arguments("false", Boolean.FALSE),
            arguments("yes", Boolean.TRUE),
            arguments("no", Boolean.FALSE),
            arguments("on", Boolean.TRUE),
            arguments("off", Boolean.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideToBooleanTestData")
    public void testToBoolean(String str, boolean expected) {
        assertEquals(expected, BooleanUtils.toBoolean(str));
    }

    private static Stream<Arguments> provideToBooleanTestData() {
        return Stream.of(
            arguments("true", true),
            arguments("false", false),
            arguments("yes", true),
            arguments("no", false),
            arguments("on", true),
            arguments("off", false)
        );
    }

    
    
    @ParameterizedTest
    @MethodSource("provideToStringTrueFalseTestData")
    public void testToStringTrueFalse(boolean bool, String expected) {
        assertEquals(expected, BooleanUtils.toStringTrueFalse(bool));
    }

    private static Stream<Arguments> provideToStringTrueFalseTestData() {
        return Stream.of(
            arguments(true, "true"),
            arguments(false, "false")
        );
    }

    
    private static Stream<Arguments> provideToBooleanObjectWithNullStringTestData() {
        return Stream.of(
            arguments("true", "true", "false", Boolean.TRUE),
            arguments("false", "true", "false", Boolean.FALSE),
            arguments(null, null, null, null)
        );
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testXor() {
            assertTrue(BooleanUtils.xor(true, false));
            assertFalse(BooleanUtils.xor(false, false));
            assertTrue(BooleanUtils.xor(true, true));
            assertFalse(BooleanUtils.xor(false, true));
    
            assertTrue(BooleanUtils.xor(new Boolean[]{true, false}));
            assertFalse(BooleanUtils.xor(new Boolean[]{false, false}));
            assertTrue(BooleanUtils.xor(new Boolean[]{true, true}));
            assertFalse(BooleanUtils.xor(new Boolean[]{false, true}));
    
            assertThrows(NullPointerException.class, () -> BooleanUtils.xor((Boolean[]) null));
        }

    @Test
        public void testOneHot() {
            assertTrue(BooleanUtils.oneHot(true, false));
            assertFalse(BooleanUtils.oneHot(false, true));
    
            assertTrue(BooleanUtils.oneHot(new boolean[]{true, false}));
            assertFalse(BooleanUtils.oneHot(new boolean[]{false, true}));
    
            assertThrows(NullPointerException.class, () -> BooleanUtils.oneHot((boolean[]) null));
        }

    @Test
        public void testCompare() {
            assertEquals(0, BooleanUtils.compare(true, true));
            assertEquals(-1, BooleanUtils.compare(false, true));
            assertEquals(1, BooleanUtils.compare(true, false));
    
            assertThrows(NullPointerException.class, () -> BooleanUtils.compare(null, true));
            assertThrows(NullPointerException.class, () -> BooleanUtils.compare(true, null));
        }

    @Test
        public void testForEach() {
            boolean[] captured = new boolean[2];
            int index = 0;
            BooleanUtils.forEach(b -> captured[index++] = b);
            assertTrue(captured[0]);
            assertFalse(captured[1]);
    
            assertThrows(NullPointerException.class, () -> BooleanUtils.forEach((Consumer<Boolean>) null));
        }

    @Test
        public void testToBooleanObjectWithNull() {
            assertNull(BooleanUtils.toBooleanObject(null));
    
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("unknown"));
        }
}
