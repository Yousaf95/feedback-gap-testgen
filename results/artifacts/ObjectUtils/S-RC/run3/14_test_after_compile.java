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
        public void testReadResolve() {
            ObjectUtils.Null nullObject = new ObjectUtils.Null();
            assertSame(nullObject, nullObject.readResolve());
        }

    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, "test", 3.14));
            assertFalse(ObjectUtils.allNotNull(1, null, 3.14));
            assertFalse(ObjectUtils.allNotNull(null, null, null));
        }

    @Test
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull((Object) null));
            assertTrue(ObjectUtils.allNull((Object[]) null));
            assertTrue(ObjectUtils.allNull());
            assertFalse(ObjectUtils.allNull(1, "test", 3.14));
            assertFalse(ObjectUtils.allNull(1, null, 3.14));
        }

    @Test
        public void testAnyNotNull() {
            assertTrue(ObjectUtils.anyNotNull(1, "test", 3.14));
            assertTrue(ObjectUtils.anyNotNull(null, "test", 3.14));
            assertFalse(ObjectUtils.anyNotNull((Object) null));
            assertFalse(ObjectUtils.anyNotNull((Object[]) null));
            assertFalse(ObjectUtils.anyNotNull());
        }

    @Test
        public void testAnyNull() {
            assertTrue(ObjectUtils.anyNull(1, null, 3.14));
            assertTrue(ObjectUtils.anyNull(null, null, null));
            assertFalse(ObjectUtils.anyNull(1, "test", 3.14));
            assertFalse(ObjectUtils.anyNull((Object) null));
            assertFalse(ObjectUtils.anyNull((Object[]) null));
            assertFalse(ObjectUtils.anyNull());
        }

    @Test
        public void testClone() {
            String original = "test";
            String cloned = ObjectUtils.clone(original);
            assertSame(original, cloned);
    
            Integer[] array = {1, 2, 3};
            Integer[] clonedArray = ObjectUtils.clone(array);
            assertArrayEquals(array, clonedArray);
    
            Object nonCloneable = new Object();
            assertNull(ObjectUtils.clone(nonCloneable));
        }

    @Test
        public void testCloneIfPossible() {
            String original = "test";
            String cloned = ObjectUtils.cloneIfPossible(original);
            assertSame(original, cloned);
    
            Integer[] array = {1, 2, 3};
            Integer[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertArrayEquals(array, clonedArray);
    
            Object nonCloneable = new Object();
            assertSame(nonCloneable, ObjectUtils.cloneIfPossible(nonCloneable));
        }

    @Test
        public void testCompare() {
            assertEquals(0, ObjectUtils.compare("a", "a"));
            assertTrue(ObjectUtils.compare("b", "a") > 0);
            assertTrue(ObjectUtils.compare("a", "b") < 0);
    
            assertTrue(ObjectUtils.compare("a", "a", true) == 0);
            assertTrue(ObjectUtils.compare("b", "a", true) > 0);
            assertTrue(ObjectUtils.compare("a", "b", true) < 0);
    
            assertTrue(ObjectUtils.compare("a", "a", false) == 0);
            assertTrue(ObjectUtils.compare("b", "a", false) < 0);
            assertTrue(ObjectUtils.compare("a", "b", false) > 0);
        }

    @Test
        public void testCONST() {
            assertEquals(true, ObjectUtils.CONST(true));
            assertEquals(false, ObjectUtils.CONST(false));
            assertEquals('a', ObjectUtils.CONST('a'));
            assertEquals((byte) 1, ObjectUtils.CONST_BYTE(1));
            assertEquals((short) 2, ObjectUtils.CONST_SHORT(2));
            assertEquals(3.0f, ObjectUtils.CONST(3.0f), 0.0);
            assertEquals(4.0, ObjectUtils.CONST(4.0), 0.0);
            assertEquals(5L, ObjectUtils.CONST(5L));
            assertEquals(6, ObjectUtils.CONST((short) 6));
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
            String value = "test";
            assertSame(value, ObjectUtils.defaultIfNull(value, "default"));
            assertEquals("default", ObjectUtils.defaultIfNull(null, "default"));
        }

    @Test
        public void testGetFirstNonNull() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "test";
            Supplier<String> supplier3 = () -> null;
    
            assertThrows(NullPointerException.class, () -> ObjectUtils.getFirstNonNull(supplier1));
            assertEquals("test", ObjectUtils.getFirstNonNull(supplier1, supplier2).get());
            assertEquals("test", ObjectUtils.getFirstNonNull(supplier1, supplier2, supplier3).get());
        }
}
