package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.Optional;
import java.time.Duration;

public class ObjectUtils_GenTest {

    @Test
    void testAllNotNull_withNullValues() {
        assertFalse(ObjectUtils.allNotNull(null, "test"));
    }

    @Test
    void testAllNotNull_withNonNullValues() {
        assertTrue(ObjectUtils.allNotNull("test1", "test2"));
    }

    @Test
    void testAllNotNull_withEmptyArray() {
        assertTrue(ObjectUtils.allNotNull());
    }

    @Test
    void testAllNull_withNullValues() {
        assertTrue(ObjectUtils.allNull(null, null));
    }

    @Test
    void testAllNull_withNonNullValues() {
        assertFalse(ObjectUtils.allNull("test", null));
    }

    @Test
    void testAnyNotNull_withNullValues() {
        assertFalse(ObjectUtils.anyNotNull(null, null));
    }

    @Test
    void testAnyNotNull_withNonNullValues() {
        assertTrue(ObjectUtils.anyNotNull("test", null));
    }

    @Test
    void testAnyNull_withNullValues() {
        assertTrue(ObjectUtils.anyNull(null, "test"));
    }

    @Test
    void testAnyNull_withNonNullValues() {
        assertFalse(ObjectUtils.anyNull("test1", "test2"));
    }

    @Test
    void testClone_withClonableObject() throws CloneNotSupportedException {
        Object original = new ArrayList<>(Arrays.asList(1, 2, 3));
        Object cloned = ObjectUtils.clone(original);
        assertNotSame(original, cloned);
        assertEquals(original, cloned);
    }

    @Test
    void testClone_withNonClonableObject() {
        Object nonClonable = new Object();
        assertNull(ObjectUtils.clone(nonClonable));
    }

    @Test
    void testCloneIfPossible_withClonableObject() throws CloneNotSupportedException {
        Object original = new ArrayList<>(Arrays.asList(1, 2, 3));
        Object cloned = ObjectUtils.cloneIfPossible(original);
        assertNotSame(original, cloned);
        assertEquals(original, cloned);
    }

    @Test
    void testCloneIfPossible_withNonClonableObject() {
        Object nonClonable = new Object();
        assertSame(nonClonable, ObjectUtils.cloneIfPossible(nonClonable));
    }

    @Test
    void testCompare_withNullValues() {
        assertEquals(0, ObjectUtils.compare(null, null));
        assertEquals(-1, ObjectUtils.compare(null, "test"));
        assertEquals(1, ObjectUtils.compare("test", null));
    }

    @Test
    void testCompare_withNonNullValues() {
        assertEquals(0, ObjectUtils.compare("test", "test"));
        assertTrue(ObjectUtils.compare("abc", "def") < 0);
        assertTrue(ObjectUtils.compare("def", "abc") > 0);
    }

    @Test
    void testDefaultIfNull_withNullValue() {
        assertEquals("default", ObjectUtils.defaultIfNull(null, "default"));
    }

    @Test
    void testDefaultIfNull_withNonNullValue() {
        assertEquals("value", ObjectUtils.defaultIfNull("value", "default"));
    }

    @Test
    void testEquals_withEqualObjects() {
        assertTrue(ObjectUtils.equals("test", "test"));
    }

    @Test
    void testEquals_withNonEqualObjects() {
        assertFalse(ObjectUtils.equals("test1", "test2"));
    }

    @Test
    void testFirstNonNull_withNullValues() {
        assertNull(ObjectUtils.firstNonNull(null, null));
    }

    @Test
    void testFirstNonNull_withNonNullValue() {
        assertEquals("first", ObjectUtils.firstNonNull(null, "first", "second"));
    }

    @Test
    void testGetClass_withNonNullObject() {
        assertEquals(String.class, ObjectUtils.getClass("test"));
    }

    @Test
    void testGetClass_withNullObject() {
        assertNull(ObjectUtils.getClass(null));
    }

    @Test
    void testGetFirstNonNull_withSuppliersReturningNull() {
        Supplier<String> supplier1 = () -> null;
        Supplier<String> supplier2 = () -> "value";
        assertEquals("value", ObjectUtils.getFirstNonNull(supplier1, supplier2));
    }

    @Test
    void testGetIfNull_withNullObject() {
        Supplier<String> supplier = () -> "default";
        assertEquals("default", ObjectUtils.getIfNull(null, supplier));
    }

    @Test
    void testGetIfNull_withNonNullObject() {
        assertEquals("value", ObjectUtils.getIfNull("value", () -> "default"));
    }

    @Test
    void testHashCode_withNullValue() {
        assertEquals(0, ObjectUtils.hashCode(null));
    }

    @Test
    void testHashCodeHex_withNullValue() {
        assertEquals("0", ObjectUtils.hashCodeHex(null));
    }

    @Test
    void testIdentityHashCodeHex_withNonNullObject() {
        assertNotNull(ObjectUtils.identityHashCodeHex(new Object()));
    }

    @Test
    void testIdentityToString_withNonNullObject() {
        assertNotNull(ObjectUtils.identityToString(new Object()));
    }

    @Test
    void testIsArray_withArray() {
        assertTrue(ObjectUtils.isArray(new int[0]));
    }

    @Test
    void testIsArray_withNonArray() {
        assertFalse(ObjectUtils.isArray("test"));
    }

    @Test
    void testIsEmpty_withNullObject() {
        assertTrue(ObjectUtils.isEmpty(null));
    }

    @Test
    void testIsEmpty_withEmptyString() {
        assertTrue(ObjectUtils.isEmpty(""));
    }

    @Test
    void testIsEmpty_withNonEmptyString() {
        assertFalse(ObjectUtils.isEmpty("test"));
    }

    @Test
    void testIsNotEmpty_withNullObject() {
        assertFalse(ObjectUtils.isNotEmpty(null));
    }

    @Test
    void testIsNotEmpty_withEmptyString() {
        assertFalse(ObjectUtils.isNotEmpty(""));
    }

    @Test
    void testIsNotEmpty_withNonEmptyString() {
        assertTrue(ObjectUtils.isNotEmpty("test"));
    }

    @Test
    void testMax_withComparableValues() {
        assertEquals(3, ObjectUtils.max(1, 2, 3));
    }

    @Test
    void testMin_withComparableValues() {
        assertEquals(1, ObjectUtils.min(1, 2, 3));
    }

    @Test
    void testMedian_withComparableValues() {
        assertEquals(2, ObjectUtils.median(1, 2, 3));
    }

    @Test
    void testMedian_withComparator() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        assertEquals(2, ObjectUtils.median(comparator, 1, 2, 3));
    }

    @Test
    void testMode_withSingleValue() {
        assertEquals("test", ObjectUtils.mode("test"));
    }

    @Test
    void testMode_withMultipleValues() {
        assertEquals("test", ObjectUtils.mode("test", "test", "other"));
    }

    @Test
    void testNotEqual_withEqualObjects() {
        assertFalse(ObjectUtils.notEqual("test", "test"));
    }

    @Test
    void testNotEqual_withNonEqualObjects() {
        assertTrue(ObjectUtils.notEqual("test1", "test2"));
    }

    @Test
    void testRequireNonEmpty_withNonNullObject() {
        assertEquals("test", ObjectUtils.requireNonEmpty("test"));
    }

    
    @Test
    void testToString_withNullObject() {
        assertEquals("", ObjectUtils.toString((Object) null));
    }

    @Test
    void testToString_withNonNullObject() {
        assertEquals("test", ObjectUtils.toString("test"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCONST_withBoolean() {
            assertTrue(ObjectUtils.CONST(true));
            assertFalse(ObjectUtils.CONST(false));
        }

    @Test
        void testCONST_withByte() {
            assertEquals((byte) 127, ObjectUtils.CONST((byte) 127));
        }

    @Test
        void testCONST_withChar() {
            assertEquals('a', ObjectUtils.CONST('a'));
        }

    @Test
        void testCONST_withDouble() {
            assertEquals(3.14, ObjectUtils.CONST(3.14), 0.0);
        }

    @Test
        void testCONST_withFloat() {
            assertEquals(2.71f, ObjectUtils.CONST(2.71f), 0.0);
        }

    @Test
        void testCONST_withInt() {
            assertEquals(42, ObjectUtils.CONST(42));
        }

    @Test
        void testCONST_withLong() {
            assertEquals(987654321L, ObjectUtils.CONST(987654321L));
        }

    @Test
        void testCONST_withShort() {
            assertEquals((short) 32767, ObjectUtils.CONST((short) 32767));
        }

    @Test
        void testCONST_withObject() {
            String testString = "test";
            assertSame(testString, ObjectUtils.CONST(testString));
        }

    @Test
        void testCONST_BYTE_withValidValue() {
            assertEquals(Byte.MAX_VALUE, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        void testCONST_BYTE_withInvalidValue() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> ObjectUtils.CONST_BYTE(128));
            assertTrue(exception.getMessage().contains("Supplied value must be a valid byte literal between -128 and 127"));
        }

    @Test
        void testCONST_SHORT_withValidValue() {
            assertEquals(Short.MAX_VALUE, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        void testCONST_SHORT_withInvalidValue() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> ObjectUtils.CONST_SHORT(32768));
            assertTrue(exception.getMessage().contains("Supplied value must be a valid byte literal between -32768 and 32767"));
        }

    @Test
        void testGetClass_withArray() {
            assertEquals(int[].class, ObjectUtils.getClass(new int[0]));
        }

    @Test
        void testGetFirstNonNull_withAllNullSuppliers() {
            assertNull(ObjectUtils.getFirstNonNull(() -> null, () -> null));
        }

    @Test
        void testGetIfNull_withSupplierReturningNonNull() {
            String result = ObjectUtils.getIfNull(null, () -> "default");
            assertEquals("default", result);
        }

    @Test
        void testHashCodeMulti_withMultipleObjects() {
            int hash = ObjectUtils.hashCodeMulti("test", 123, new Object());
            assertNotEquals(0, hash);
        }

    @Test
        void testIsEmpty_withEmptyCollection() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        void testIsNotEmpty_withNonEmptyMap() {
            assertTrue(ObjectUtils.isNotEmpty(Collections.singletonMap("key", "value")));
        }

    @Test
        void testMax_withNoValues() {
            assertNull(ObjectUtils.max());
        }

    @Test
        void testMedian_withComparatorAndSingleItem() {
            assertEquals(1, ObjectUtils.median(Comparator.naturalOrder(), 1));
        }

    @Test
        void testMedian_withNaturalOrderAndSingleItem() {
            assertEquals(1, ObjectUtils.median(1));
        }

    @Test
        void testMin_withNoValues() {
            assertNull(ObjectUtils.min());
        }

    @Test
        void testMode_withAllDifferentItems() {
            assertNull(ObjectUtils.mode("a", "b", "c"));
        }

    
    @Test
        void testRequireNonEmpty_withEmptyString() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> ObjectUtils.requireNonEmpty(""));
            assertEquals("object", exception.getMessage());
        }

    @Test
        void testToString_withSupplierReturningNull() {
            assertEquals("default", ObjectUtils.toString(() -> null, () -> "default"));
        }

    @Test
        void testWait_withNegativeDuration() {
            assertThrows(IllegalArgumentException.class,
                    () -> ObjectUtils.wait(new Object(), Duration.ofSeconds(-1)));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCompare_withNullGreater() {
            Integer nullValue = null;
            Integer nonNullValue = 10;
            assertEquals(1, ObjectUtils.compare(nullValue, nonNullValue, true));
            assertEquals(-1, ObjectUtils.compare(nonNullValue, nullValue, true));
        }

    @Test
        void testCompare_withBothNull() {
            Integer nullValue1 = null;
            Integer nullValue2 = null;
            assertEquals(0, ObjectUtils.compare(nullValue1, nullValue2, false));
            assertEquals(0, ObjectUtils.compare(nullValue1, nullValue2, true));
        }

    @Test
        void testMedian_withNaturalOrderAndEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median());
        }

    @Test
        void testMedian_withComparatorAndEmptyArray() {
            Comparator<Integer> comparator = Integer::compareTo;
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median(comparator));
        }

    @Test
        void testMode_withNoValues() {
            assertNull(ObjectUtils.mode());
        }

    @Test
        void testGetClass_withNullObjectAndEmptyArray() {
            assertNull(ObjectUtils.getClass((Object[]) null));
        }

    @Test
        void testIsEmpty_withNonEmptyCollection() {
            assertFalse(ObjectUtils.isEmpty(Collections.singletonList("element")));
        }

    @Test
        void testIsNotEmpty_withEmptyMap() {
            assertFalse(ObjectUtils.isNotEmpty(new HashMap<>()));
        }

    @Test
        void testRequireNonEmpty_withNullObject() {
            assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmpty((String) null));
        }

    @Test
        void testToString_withSupplierReturningNonNull() {
            Supplier<Object> supplier = () -> "non-null";
            assertEquals("non-null", ObjectUtils.toString(supplier, () -> "default"));
        }

    @Test
        void testWait_withNullDuration() {
            final Object obj = new Object();
            assertDoesNotThrow(() -> ObjectUtils.wait(obj, null));
        }
}
