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
            public void testDefaultIfNullWithNonNull() {
                String result = ObjectUtils.defaultIfNull("Hello", "World");
                assertEquals("Hello", result);
            }

    @Test
            public void testDefaultIfNullWithNull() {
                String result = ObjectUtils.defaultIfNull(null, "World");
                assertEquals("World", result);
            }

    @Test
            public void testGetFirstNonNullWithNonNullValues() {
                String result = ObjectUtils.getFirstNonNull(() -> "Hello", () -> null, () -> "World");
                assertEquals("Hello", result);
            }

    @Test
            public void testGetFirstNonNullWithNullValues() {
                String result = ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> "World");
                assertEquals("World", result);
            }

    @Test
            public void testGetFirstNonNullWithAllNullValues() {
                String result = ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> null);
                assertNull(result);
            }

    @Test
            public void testGetIfNullWithNonNullObject() {
                String result = ObjectUtils.getIfNull("Hello", () -> "World");
                assertEquals("Hello", result);
            }

    @Test
            public void testGetIfNullWithNullObject() {
                String result = ObjectUtils.getIfNull(null, () -> "World");
                assertEquals("World", result);
            }

    
    @Test
        public void testHashCodeHexForNonNullObject() {
            String result = ObjectUtils.hashCodeHex("Hello");
            assertNotNull(result);
        }

    @Test
        public void testHashCodeHexForNullObject() {
            String result = ObjectUtils.hashCodeHex(null);
            assertEquals("0", result);
        }

    @Test
        public void testIdentityToStringWithNonNullObject() throws IOException {
            StringBuilder buffer = new StringBuilder();
            ObjectUtils.identityToString(buffer, "Hello");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("Hello")), buffer.toString());
        }

    @Test
        public void testIdentityToStringWithNullObject() {
            String result = ObjectUtils.identityToString(null);
            assertNull(result);
        }

    @Test
        public void testIsArrayWithNonNullArray() {
            boolean result = ObjectUtils.isArray(new int[]{1, 2, 3});
            assertTrue(result);
        }

    @Test
        public void testIsArrayWithNullObject() {
            boolean result = ObjectUtils.isArray(null);
            assertFalse(result);
        }

    @Test
        public void testIsEmptyWithNonNullEmptyCollection() {
            boolean result = ObjectUtils.isEmpty(Collections.emptyList());
            assertTrue(result);
        }

    @Test
        public void testIsEmptyWithNullObject() {
            boolean result = ObjectUtils.isEmpty(null);
            assertTrue(result);
        }

    @Test
        public void testIsNotEmptyWithNonNullNonEmptyCollection() {
            boolean result = ObjectUtils.isNotEmpty(Arrays.asList("Hello", "World"));
            assertTrue(result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAllNotNullWithNullValues() {
            assertFalse(ObjectUtils.allNotNull((Object[]) null));
            assertFalse(ObjectUtils.allNotNull(null, "value"));
            assertTrue(ObjectUtils.allNotNull("value", 123));
        }

    @Test
        public void testAnyNotNullWithNullValues() {
            assertFalse(ObjectUtils.anyNotNull((Object[]) null));
            assertFalse(ObjectUtils.anyNotNull(null, null));
            assertTrue(ObjectUtils.anyNotNull(null, "value"));
        }

    @Test
        public void testGetFirstNonNullWithMixedValues() {
            assertEquals("value", ObjectUtils.getFirstNonNull("value", 123, null));
            assertEquals(123, ObjectUtils.getFirstNonNull((Object) null, 123, "value"));
            assertNull(ObjectUtils.getFirstNonNull(null, null, null));
        }

    @Test
        public void testGetIfNullWithNonNullableObject() {
            String value = "non-null";
            Supplier<String> supplier = () -> "supplier";
            assertEquals(value, ObjectUtils.getIfNull(value, supplier));
        }

    @Test
        public void testGetIfNullWithNullableObject() {
            String value = null;
            Supplier<String> supplier = () -> "supplier";
            assertEquals("supplier", ObjectUtils.getIfNull(value, supplier));
        }

    @Test
        public void testHashCodeHexForNonNullObject_2() {
            assertEquals("0", ObjectUtils.hashCodeHex(null));
            assertEquals(Integer.toHexString(Objects.hashCode("test")), ObjectUtils.hashCodeHex("test"));
        }

    @Test
        public void testIsEmptyWithNonNullEmptyCollection_2() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
            assertTrue(ObjectUtils.isEmpty(new ArrayList<>()));
            assertTrue(ObjectUtils.isEmpty(new HashSet<>())); // Assuming HashSet is imported
        }

    @Test
        public void testIsNotEmptyWithNonNullNonEmptyCollection_2() {
            assertFalse(ObjectUtils.isNotEmpty(Collections.emptyList()));
            assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>()));
            assertFalse(ObjectUtils.isNotEmpty(new HashSet<>())); // Assuming HashSet is imported
        }
}
