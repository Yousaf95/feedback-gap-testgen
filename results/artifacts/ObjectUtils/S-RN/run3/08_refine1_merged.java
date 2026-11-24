package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;

public class ObjectUtils_GenTest {

    
    private static Stream<Arguments> provideAllNotNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(1, 2, 3),
            arguments("a", "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    
    private static Stream<Arguments> provideAnyNotNullValues() {
        return Stream.of(
            arguments(1, 2, null),
            arguments(null, "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    
    private static Stream<Arguments> provideAllNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(null, null, null),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    
    private static Stream<Arguments> provideAnyNullValues() {
        return Stream.of(
            arguments(1, null, 3),
            arguments(null, "b", null),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    
    
    
    
    @Test
    public void testFirstNonNull() {
        assertEquals("a", ObjectUtils.firstNonNull("a", "b"));
        assertEquals("b", ObjectUtils.firstNonNull(null, "b"));
        assertNull(ObjectUtils.firstNonNull((String) null, (String) null));
    }

    
    
    @Test
    public void testGetIfNull() {
        assertEquals("a", ObjectUtils.getIfNull("a", () -> "b"));
        assertEquals("b", ObjectUtils.getIfNull(null, () -> "b"));
    }

    @ParameterizedTest
    @MethodSource("provideHashCodeValues")
    public void testHashCode(Object object, int expectedHash) {
        assertEquals(expectedHash, ObjectUtils.hashCode(object));
    }

    private static Stream<Arguments> provideHashCodeValues() {
        return Stream.of(
            arguments(null, 0),
            arguments("a", "a".hashCode()),
            arguments(123, 123)
        );
    }

    
    private static Stream<Arguments> provideIdentityToStringValues() {
        return Stream.of(
            arguments(null, null),
            arguments("a", "java.lang.String@12345678"),
            arguments(123, "int@12345678")
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayValues")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    private static Stream<Arguments> provideIsArrayValues() {
        return Stream.of(
            arguments(null, false),
            arguments(new int[]{1, 2, 3}, true),
            arguments("a", false)
        );
    }

    
    
    
    
    
    private static Stream<Arguments> provideMaxValues() {
        return Stream.of(
            arguments(1, 2, 3),
            arguments("c", "a", "b"),
            arguments(new Integer[]{3, 1, 4})
        );
    }

    
    private static Stream<Arguments> provideMinValues() {
        return Stream.of(
            arguments(1, 2, 3),
            arguments("a", "b", "c"),
            arguments(new Integer[]{3, 1, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringValues")
    public void testToString(Object object, String expected) {
        assertEquals(expected, ObjectUtils.toString(object));
    }

    private static Stream<Arguments> provideToStringValues() {
        return Stream.of(
            arguments(null, ""),
            arguments("a", "a"),
            arguments(123, "123")
        );
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, 2, 3));
            assertFalse(ObjectUtils.allNotNull(1, null, 3));
            assertTrue(ObjectUtils.allNotNull(null, null, null));
            assertTrue(ObjectUtils.allNotNull());
        }

    @Test
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull((Object[]) null));
            assertTrue(ObjectUtils.allNull());
            assertFalse(ObjectUtils.allNull(1, null, 3));
            assertFalse(ObjectUtils.allNull(1, 2, 3));
        }

    @Test
        public void testAnyNotNull() {
            assertTrue(ObjectUtils.anyNotNull(1, null, 3));
            assertFalse(ObjectUtils.anyNotNull(null, null, null));
            assertTrue(ObjectUtils.anyNotNull());
        }

    @Test
        public void testAnyNull() {
            assertTrue(ObjectUtils.anyNull(1, null, 3));
            assertFalse(ObjectUtils.anyNull(1, 2, 3));
            assertTrue(ObjectUtils.anyNull((Object[]) null));
            assertTrue(ObjectUtils.anyNull());
        }

    @Test
        public void testCloneIfPossible() {
            String original = "test";
            String cloned = ObjectUtils.cloneIfPossible(original);
            assertSame(original, cloned);
    
            Integer[] array = {1, 2, 3};
            Integer[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertNotSame(array, clonedArray);
            assertArrayEquals(array, clonedArray);
        }

    @Test
        public void testCompare() {
            assertEquals(0, ObjectUtils.compare("a", "a"));
            assertTrue(ObjectUtils.compare("b", "a") > 0);
            assertTrue(ObjectUtils.compare("a", "b") < 0);
    
            assertTrue(ObjectUtils.compare("a", "a", true) == 0);
            assertTrue(ObjectUtils.compare("b", "a", true) > 0);
            assertTrue(ObjectUtils.compare("a", "b", true) < 0);
        }

    @Test
        public void testCompareThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
        }

    @Test
        public void testDefaultIfNull() {
            String result = ObjectUtils.defaultIfNull(null, "default");
            assertEquals("default", result);
    
            Integer number = 42;
            Integer defaultNumber = ObjectUtils.defaultIfNull(number, 10);
            assertEquals(42, defaultNumber);
        }

    @Test
        public void testGetFirstNonNull() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "value";
            String result = ObjectUtils.getFirstNonNull(supplier1, supplier2);
            assertEquals("value", result);
    
            Supplier<Integer> supplier3 = () -> 10;
            Integer intResult = ObjectUtils.getFirstNonNull(supplier1, supplier3);
            assertEquals(10, intResult);
        }

    @Test
        public void testGetIfNull_2() {
            String result = ObjectUtils.getIfNull(null, () -> "default");
            assertEquals("default", result);
    
            Integer number = 42;
            Integer defaultNumber = ObjectUtils.getIfNull(number, () -> 10);
            assertEquals(42, defaultNumber);
        }

    @Test
        public void testHashCodeHex() {
            String hash = ObjectUtils.hashCodeHex("test");
            assertTrue(hash.length() == 8);
    
            String nullHash = ObjectUtils.hashCodeHex(null);
            assertEquals("0", nullHash);
        }

    @Test
        public void testIdentityToString() throws IOException {
            Appendable appendable = new StringBuilder();
            ObjectUtils.identityToString(appendable, "test");
            assertEquals("java.lang.String@12345678", appendable.toString());
    
            String result = ObjectUtils.identityToString(null);
            assertNull(result);
    
            StrBuilder builder = new StrBuilder();
            ObjectUtils.identityToString(builder, "test");
            assertEquals("java.lang.String@12345678", builder.toString());
        }

    @Test
        public void testIsArray_2() {
            assertTrue(ObjectUtils.isArray(new int[] {}));
            assertFalse(ObjectUtils.isArray("test"));
            assertTrue(ObjectUtils.isArray(new HashMap<String, String>()));
            assertFalse(ObjectUtils.isArray(null));
        }

    @Test
        public void testIsEmpty() {
            assertTrue(ObjectUtils.isEmpty(""));
            assertTrue(ObjectUtils.isEmpty(new ArrayList<>()));
            assertTrue(ObjectUtils.isEmpty((Object) null));
            assertFalse(ObjectUtils.isEmpty("test"));
            assertFalse(ObjectUtils.isEmpty(Arrays.asList(1, 2, 3)));
        }

    @Test
        public void testIsNotEmpty() {
            assertTrue(ObjectUtils.isNotEmpty("test"));
            assertTrue(ObjectUtils.isNotEmpty(Arrays.asList(1, 2, 3)));
            assertFalse(ObjectUtils.isNotEmpty(""));
            assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>()));
            assertFalse(ObjectUtils.isNotEmpty((Object) null));
        }

    @Test
        public void testMax() {
            Integer max = ObjectUtils.max(1, 2, 3);
            assertEquals(3, max);
    
            String strMax = ObjectUtils.max("a", "b", "c");
            assertEquals("c", strMax);
        }

    @Test
        public void testMedian() {
            Double median = ObjectUtils.median(Arrays.asList(1.0, 2.0, 3.0));
            assertEquals(2.0, median);
    
            Integer intMedian = ObjectUtils.median(Arrays.asList(3, 1, 2));
            assertEquals(2, intMedian);
        }

    @Test
        public void testMin() {
            Integer min = ObjectUtils.min(1, 2, 3);
            assertEquals(1, min);
    
            String strMin = ObjectUtils.min("a", "b", "c");
            assertEquals("a", strMin);
        }

    @Test
        public void testMode() {
            String mode = ObjectUtils.mode("a", "b", "a");
            assertEquals("a", mode);
    
            Integer intMode = ObjectUtils.mode(1, 2, 2, 3);
            assertEquals(2, intMode);
        }

    @Test
        public void testNotEqual() {
            assertTrue(ObjectUtils.notEqual("a", null));
            assertFalse(ObjectUtils.notEqual("a", "a"));
            assertTrue(ObjectUtils.notEqual(null, "b"));
        }

    @Test
        public void testRequireNonEmpty() {
            String result = ObjectUtils.requireNonEmpty("test");
            assertEquals("test", result);
    
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty((String) null));
        }

    @Test
        public void testToString_2() {
            String result = ObjectUtils.toString(null);
            assertEquals("", result);
    
            String nonNullResult = ObjectUtils.toString("test");
            assertEquals("test", nonNullResult);
    
            Supplier<String> supplier = () -> "supplier";
            String supplierResult = ObjectUtils.toString(supplier, () -> "default");
            assertEquals("supplier", supplierResult);
        }
}
