package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    
    private static Stream<Arguments> provideAllNotNullCases() {
        return Stream.of(
            of(1, 2, 3),
            of("a", "b", "c"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideAnyNotNullCases() {
        return Stream.of(
            of(1, null, 3),
            of(null, "b", null),
            of(true, false, null)
        );
    }

    
    private static Stream<Arguments> provideAllNullCases() {
        return Stream.of(
            of(null, null, null),
            of("", "", ""),
            of(false, false, false)
        );
    }

    
    private static Stream<Arguments> provideAnyNullCases() {
        return Stream.of(
            of(1, null, 3),
            of(null, "b", null),
            of(true, false, null)
        );
    }

    
    
    @ParameterizedTest
    @MethodSource("provideCompareCases")
    public <T extends Comparable<? super T>> void testCompare(T c1, T c2, int expected) {
        assertEquals(expected, ObjectUtils.compare(c1, c2));
    }

    private static Stream<Arguments> provideCompareCases() {
        return Stream.of(
            of(1, 2, -1),
            of("a", "b", -1),
            of(true, false, 1)
        );
    }

    
    private static Stream<Arguments> provideFirstNonNullCases() {
        return Stream.of(
            of(1, null, 3),
            of("a", "b", null),
            of(true, false, true)
        );
    }

    
    
    
    
    
    private static Stream<Arguments> provideHashCodeHexCases() {
        return Stream.of(
            of(1),
            of("a"),
            of(true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIdentityToStringCases")
    public void testIdentityToString(Object object) {
        assertEquals(object.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(object)), ObjectUtils.identityToString(object));
    }

    private static Stream<Arguments> provideIdentityToStringCases() {
        return Stream.of(
            of(1),
            of("a"),
            of(true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayCases")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    private static Stream<Arguments> provideIsArrayCases() {
        return Stream.of(
            of(1, false),
            of(new int[]{1, 2, 3}, true),
            of("a", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsEmptyCases")
    public void testIsEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isEmpty(object));
    }

    private static Stream<Arguments> provideIsEmptyCases() {
        return Stream.of(
            of(1, false),
            of("", true),
            of(new int[]{}, true),
            of(Collections.emptyList(), true),
            of(Optional.empty(), true)
        );
    }

    
    private static Stream<Arguments> provideIsNotEmptyCases() {
        return Stream.of(
            of(1, true),
            of("", false),
            of(new int[]{1, 2, 3}, false),
            of(Collections.singletonList("a"), false),
            of(Optional.of(1), false)
        );
    }

    
    private static Stream<Arguments> provideMaxCases() {
        return Stream.of(
            of(1, 3, 2),
            of("a", "c", "b"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideMinCases() {
        return Stream.of(
            of(1, 3, 2),
            of("a", "c", "b"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideModeCases() {
        return Stream.of(
            of(1, 2, 2, 3),
            of("a", "b", "b", "c"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideNotEqualCases() {
        return Stream.of(
            of(1, 2),
            of("a", "b"),
            of(true, false)
        );
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, 2, 3));
            assertFalse(ObjectUtils.allNotNull(1, null, 3));
            assertTrue(ObjectUtils.allNotNull());
        }

    @Test
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull(null, null, null));
            assertFalse(ObjectUtils.allNull(1, null, 3));
            assertTrue(ObjectUtils.allNull());
        }

    
    
    
    
    @Test
        public void testCompare_2() {
            assertEquals(0, ObjectUtils.compare(1, 1));
            assertEquals(-1, ObjectUtils.compare(1, 2));
            assertEquals(1, ObjectUtils.compare(2, 1));
    
            assertEquals(0, ObjectUtils.compare(null, null, true));
            assertEquals(1, ObjectUtils.compare(null, 1, true));
            assertEquals(-1, ObjectUtils.compare(1, null, true));
    
            assertEquals(0, ObjectUtils.compare(null, null, false));
            assertEquals(-1, ObjectUtils.compare(null, 1, false));
            assertEquals(1, ObjectUtils.compare(1, null, false));
        }

    @Test
        public void testCONST() {
            assertTrue(ObjectUtils.CONST(true));
            assertFalse(ObjectUtils.CONST(false));
    
            byte b = 42;
            assertEquals(b, ObjectUtils.CONST_BYTE((int) b));
    
            short s = 42;
            assertEquals(s, ObjectUtils.CONST_SHORT((int) s));
        }

    
    @Test
        public void testFirstNonNull() {
            assertEquals("a", ObjectUtils.firstNonNull("a", null, "b"));
            assertNull(ObjectUtils.firstNonNull(null, null, null));
        }

    @Test
        public void testGetClass() {
            assertNotNull(ObjectUtils.getClass(new Object()));
            assertNull(ObjectUtils.getClass(null));
        }

    @Test
        public void testGetFirstNonNull() {
            assertEquals("a", ObjectUtils.getFirstNonNull(() -> "a", () -> null, () -> "b"));
            assertNull(ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> null));
        }

    @Test
        public void testGetIfNull() {
            String value = "test";
            Supplier<String> defaultSupplier = () -> "default";
            assertEquals(value, ObjectUtils.getIfNull(value, defaultSupplier));
    
            assertEquals("default", ObjectUtils.getIfNull(null, defaultSupplier));
        }
}
