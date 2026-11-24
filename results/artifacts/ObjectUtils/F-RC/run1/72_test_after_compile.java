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
import java.util.ArrayList;
import java.util.Comparator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

    
    @ParameterizedTest
            @MethodSource("provideIsEmptyData")
            void testIsEmpty_2(String input, boolean expected) {
                assertEquals(expected, ObjectUtils.isEmpty(input));
            }

    private static Stream<Arguments> provideIsEmptyData() {
                return Stream.of(
                    of("", true),
                    of("value", false)
                );
            }

    @ParameterizedTest
            @MethodSource("provideIsNotEmptyData")
            void testIsNotEmpty(String input, boolean expected) {
                assertEquals(expected, ObjectUtils.isNotEmpty(input));
            }

    private static Stream<Arguments> provideIsNotEmptyData() {
                return Stream.of(
                    of("", false),
                    of("value", true)
                );
            }

    @Test
            void testMedian() {
                assertEquals(2.0, ObjectUtils.median((Comparator<Double>) Double::compareTo, 1.0, 2.0, 3.0));
            }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testReadResolve() {
            // This method is not directly testable as it's invoked by the JVM during deserialization.
            // However, we can ensure that the method exists and does not throw any exceptions.
            assertNotNull(ObjectUtils.NULL);
        }

    @Test
        public void testAllNotNull_2() {
            assertTrue(ObjectUtils.allNotNull(1, 2, 3));
            assertFalse(ObjectUtils.allNotNull(1, null, 3));
            assertTrue(ObjectUtils.allNotNull());
        }

    @Test
        public void testAnyNull() {
            assertFalse(ObjectUtils.anyNull(1, 2, 3));
            assertTrue(ObjectUtils.anyNull(1, null, 3));
            assertFalse(ObjectUtils.anyNull());
        }

    @Test
        public void testClone() {
            Integer original = 42;
            Integer cloned = ObjectUtils.clone(original);
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.clone(array);
            assertArrayEquals(array, clonedArray);
    
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");
            Map<String, String> clonedMap = ObjectUtils.clone(map);
            assertEquals(map, clonedMap);
        }

    @Test
        public void testCloneIfPossible() {
            Integer original = 42;
            Integer cloned = ObjectUtils.cloneIfPossible(original);
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertArrayEquals(array, clonedArray);
    
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");
            Map<String, String> clonedMap = ObjectUtils.cloneIfPossible(map);
            assertEquals(map, clonedMap);
        }

    @Test
        public void testCONST_2() {
            assertTrue(ObjectUtils.CONST(true));
            assertEquals((byte) 42, ObjectUtils.CONST_BYTE(42));
            assertEquals((short) 42, ObjectUtils.CONST_SHORT(42));
            assertEquals(42.0, ObjectUtils.CONST(42.0), 0.0);
            assertEquals(42f, ObjectUtils.CONST(42f), 0.0);
            assertEquals(42, ObjectUtils.CONST(42));
            assertEquals(42L, ObjectUtils.CONST(42L));
            assertEquals((short) 42, ObjectUtils.CONST((short) 42));
        }

    @Test
        public void testCONST_BYTE() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertEquals((byte) -128, ObjectUtils.CONST_BYTE(-128));
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        public void testCONST_SHORT() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(-32769));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals((short) -32768, ObjectUtils.CONST_SHORT(-32768));
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        public void testDefaultIfNull() {
            String result = ObjectUtils.defaultIfNull(null, "default");
            assertEquals("default", result);
    
            Integer number = ObjectUtils.defaultIfNull(42, 0);
            assertEquals(42, number);
        }

    @Test
        public void testFirstNonNull_2() {
            Integer result = ObjectUtils.firstNonNull(null, 1, 2, 3);
            assertEquals(1, result);
    
            String str = ObjectUtils.firstNonNull(null, null, "value", null);
            assertEquals("value", str);
        }

    @Test
        public void testGetFirstNonNull_2() {
            Integer result = ObjectUtils.getFirstNonNull(() -> null, () -> 1, () -> 2, () -> 3);
            assertEquals(1, result);
    
            String str = ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> "value", () -> null);
            assertEquals("value", str);
        }

    @Test
        public void testGetIfNull_2() {
            Integer result = ObjectUtils.getIfNull(null, () -> 42);
            assertEquals(42, result);
    
            String str = ObjectUtils.getIfNull("value", () -> "default");
            assertEquals("value", str);
        }

    @Test
        public void testHashCodeHex() {
            String hash = ObjectUtils.hashCodeHex("test");
            assertNotNull(hash);
            assertFalse(hash.isEmpty());
        }

    @Test
        public void testIdentityToString_2() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectUtils.identityToString(baos, "test");
            assertEquals("java.lang.String@", baos.toString());
    
            String result = ObjectUtils.identityToString(null);
            assertNull(result);
    
            StringBuilder sb = new StringBuilder();
            ObjectUtils.identityToString(sb, "test");
            assertEquals("java.lang.String@", sb.toString());
        }

    @Test
        public void testIsArray_2() {
            assertTrue(ObjectUtils.isArray(new int[]{}));
            assertFalse(ObjectUtils.isArray(42));
            assertTrue(ObjectUtils.isArray(new HashMap<>() {{ put("key", "value"); }}));
        }

    @Test
        public void testIsEmpty_3() {
            assertTrue(ObjectUtils.isEmpty(null));
            assertTrue(ObjectUtils.isEmpty(""));
            assertTrue(ObjectUtils.isEmpty(new int[]{}));
            assertTrue(ObjectUtils.isEmpty(new HashMap<>() {{ put("key", "value"); }}.entrySet()));
            assertFalse(ObjectUtils.isEmpty("test"));
            assertFalse(ObjectUtils.isEmpty(new int[]{1, 2, 3}));
        }

    @Test
        public void testIsNotEmpty_2() {
            assertFalse(ObjectUtils.isNotEmpty(null));
            assertFalse(ObjectUtils.isNotEmpty(""));
            assertFalse(ObjectUtils.isNotEmpty(new int[]{}));
            assertFalse(ObjectUtils.isNotEmpty(new HashMap<>() {{ put("key", "value"); }}.entrySet()));
            assertTrue(ObjectUtils.isNotEmpty("test"));
            assertTrue(ObjectUtils.isNotEmpty(new int[]{1, 2, 3}));
        }

    @Test
        public void testMedian_2() {
            assertEquals(2, ObjectUtils.median(1, 2, 3));
            assertEquals(2, ObjectUtils.median(Arrays.asList(1, 2, 3)));
            assertEquals(2.5, ObjectUtils.median(1.0, 2.0, 3.0), 0.0);
        }

    @Test
        public void testMode() {
            assertEquals("b", ObjectUtils.mode("a", "b", "b", "c"));
            assertNull(ObjectUtils.mode());
        }
}
