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

    // ---- added by coverage-guided refinement ----
    @Test
        void testReadResolve() {
            // This method is private and returns a singleton instance, so we can't directly test it.
            // However, we can ensure that the class has a no-arg constructor.
            assertNotNull(new ObjectUtils.Null());
        }

    @Test
        void testAllNotNull_2() {
            assertTrue(ObjectUtils.allNotNull(1, "test", 3.14));
            assertFalse(ObjectUtils.allNotNull(1, null, 3.14));
            assertTrue(ObjectUtils.allNotNull());
        }

    @Test
        void testAnyNull() {
            assertFalse(ObjectUtils.anyNull(1, "test", 3.14));
            assertTrue(ObjectUtils.anyNull(1, null, 3.14));
            assertFalse(ObjectUtils.anyNull());
        }

    @Test
        void testClone() {
            Integer original = 42;
            Integer cloned = ObjectUtils.clone(original);
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.clone(array);
            assertArrayEquals(array, clonedArray);
    
            Cloneable cloneableObject = new Cloneable() {};
            Cloneable clonedCloneable = ObjectUtils.clone(cloneableObject);
            assertNotNull(clonedCloneable);
    
            assertNull(ObjectUtils.clone(null));
        }

    @Test
        void testCloneIfPossible() {
            Integer original = 42;
            Integer cloned = ObjectUtils.cloneIfPossible(original);
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertArrayEquals(array, clonedArray);
    
            Cloneable cloneableObject = new Cloneable() {};
            Cloneable clonedCloneable = ObjectUtils.cloneIfPossible(cloneableObject);
            assertNotNull(clonedCloneable);
    
            assertNull(ObjectUtils.cloneIfPossible(null));
        }

    @Test
        void testCONST_2() {
            assertEquals(true, ObjectUtils.CONST(true));
            assertEquals(false, ObjectUtils.CONST(false));
            assertEquals('a', ObjectUtils.CONST('a'));
            assertEquals(1, ObjectUtils.CONST(1));
            assertEquals(3.14f, ObjectUtils.CONST(3.14f));
            assertEquals(2.718, ObjectUtils.CONST(2.718));
            assertEquals(0L, ObjectUtils.CONST(0L));
            assertEquals((short) 1, ObjectUtils.CONST((short) 1));
        }

    @Test
        void testCONST_BYTE() {
            assertEquals((byte) -128, ObjectUtils.CONST_BYTE(-128));
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
    
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_BYTE(-129));
            assertEquals("Supplied value must be a valid byte literal between -128 and 127: [-129]", exception.getMessage());
    
            exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_BYTE(128));
            assertEquals("Supplied value must be a valid byte literal between -128 and 127: [128]", exception.getMessage());
        }

    @Test
        void testCONST_SHORT() {
            assertEquals((short) -32768, ObjectUtils.CONST_SHORT(-32768));
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
    
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_SHORT(-32769));
            assertEquals("Supplied value must be a valid byte literal between -32768 and 32767: [-32769]", exception.getMessage());
    
            exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals("Supplied value must be a valid byte literal between -32768 and 32767: [32768]", exception.getMessage());
        }

    @Test
        void testDefaultIfNull() {
            String result = ObjectUtils.defaultIfNull(null, "default");
            assertEquals("default", result);
    
            result = ObjectUtils.defaultIfNull("value", "default");
            assertEquals("value", result);
        }

    @Test
        void testFirstNonNull_2() {
            String result = ObjectUtils.firstNonNull(null, "default");
            assertEquals("default", result);
    
            result = ObjectUtils.firstNonNull(null, null, "default");
            assertEquals("default", result);
    
            result = ObjectUtils.firstNonNull("value", null);
            assertEquals("value", result);
        }

    @Test
        void testGetFirstNonNull_2() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "value";
            String result = ObjectUtils.getFirstNonNull(supplier1, supplier2);
            assertEquals("value", result);
    
            result = ObjectUtils.getFirstNonNull(supplier2, supplier1);
            assertEquals("value", result);
        }

    @Test
        void testGetIfNull_2() {
            Supplier<String> supplier = () -> "default";
            String result = ObjectUtils.getIfNull(null, supplier);
            assertEquals("default", result);
    
            result = ObjectUtils.getIfNull("value", supplier);
            assertEquals("value", result);
        }

    @Test
        void testHashCodeHex() {
            assertEquals("0", ObjectUtils.hashCodeHex(null));
            assertEquals(Integer.toHexString(Objects.hashCode("test")), ObjectUtils.hashCodeHex("test"));
        }

    @Test
        void testIdentityToString_2() throws IOException {
            String result = ObjectUtils.identityToString(new Object());
            assertNotNull(result);
    
            result = ObjectUtils.identityToString(null);
            assertNull(result);
    
            Appendable appendable = new StringBuilder();
            ObjectUtils.identityToString(appendable, new Object());
            assertTrue(appendable.toString().length() > 0);
        }

    @Test
        void testIsArray_2() {
            assertTrue(ObjectUtils.isArray(new int[]{}));
            assertFalse(ObjectUtils.isArray("test"));
            assertFalse(ObjectUtils.isArray(null));
        }

    @Test
        void testIsEmpty_2() {
            assertTrue(ObjectUtils.isEmpty(""));
            assertTrue(ObjectUtils.isEmpty(new ArrayList<>())); // empty list
            assertTrue(ObjectUtils.isEmpty((Object) null));
    
            assertFalse(ObjectUtils.isEmpty("value"));
            assertFalse(ObjectUtils.isEmpty(new ArrayList<>(Collections.singletonList("value")))); // non-empty list
        }

    @Test
        void testIsNotEmpty() {
            assertFalse(ObjectUtils.isNotEmpty(""));
            assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>())); // empty list
            assertFalse(ObjectUtils.isNotEmpty((Object) null));
    
            assertTrue(ObjectUtils.isNotEmpty("value"));
            assertTrue(ObjectUtils.isNotEmpty(new ArrayList<>(Collections.singletonList("value")))); // non-empty list
        }

    @Test
        void testMax() {
            assertEquals(3.14, ObjectUtils.max(1.0, 2.0, 3.14));
            assertNull(ObjectUtils.max());
        }

    @Test
        void testMedian() {
            assertEquals(2.0, ObjectUtils.median(1.0, 2.0, 3.0));
            assertEquals(2.0, ObjectUtils.median((Comparator<Double>) Double::compareTo, 1.0, 2.0, 3.0));
            assertNull(ObjectUtils.median());
        }

    @Test
        void testMin() {
            assertEquals(1.0, ObjectUtils.min(1.0, 2.0, 3.0));
            assertNull(ObjectUtils.min());
        }

    @Test
        void testMode() {
            assertEquals("b", ObjectUtils.mode("a", "b", "b", "c"));
            assertNull(ObjectUtils.mode());
        }

    @Test
        void testNotEqual() {
            assertFalse(ObjectUtils.notEqual(null, null));
            assertTrue(ObjectUtils.notEqual(1, 2));
            assertTrue(ObjectUtils.notEqual("test", "other"));
        }

    @Test
        void testRequireNonEmpty() {
            String result = ObjectUtils.requireNonEmpty("value");
            assertEquals("value", result);
    
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.requireNonEmpty(""));
            assertEquals("object must not be empty", exception.getMessage());
    
            exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.requireNonEmpty((String) null));
            assertEquals("object must not be empty", exception.getMessage());
        }

    @Test
        void testToString() {
            String result = ObjectUtils.toString(null);
            assertEquals("", result);
    
            result = ObjectUtils.toString("value");
            assertEquals("value", result);
    
            Supplier<String> supplier = () -> "supplier";
            result = ObjectUtils.toString(supplier, () -> "default");
            assertEquals("supplier", result);
        }

    @Test
        void testWait() throws InterruptedException {
            // This method is not easily testable without a real object and thread.
            // We can only ensure that it does not throw an exception.
            Object obj = new Object();
            Duration duration = Duration.ofMillis(100);
            ObjectUtils.wait(obj, duration);
        }
}
