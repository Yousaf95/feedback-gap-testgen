package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        assertFalse(ObjectUtils.allNull("test1", "test2"));
    }

    @Test
    void testAnyNotNull_withNullValues() {
        assertFalse(ObjectUtils.anyNotNull(null, null));
    }

    @Test
    void testAnyNotNull_withNonNullValues() {
        assertTrue(ObjectUtils.anyNotNull("test1", "test2"));
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
        assertTrue(ObjectUtils.compare("a", "b") < 0);
        assertTrue(ObjectUtils.compare("b", "a") > 0);
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
    void testFirstNonNull_withNonNullValues() {
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
        assertEquals("value", ObjectUtils.getIfNull("value", null));
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
    void testMedian_withComparableValues() {
        assertEquals(2, ObjectUtils.median(1, 2, 3));
    }

    @Test
    void testMin_withComparableValues() {
        assertEquals(1, ObjectUtils.min(1, 2, 3));
    }

    @Test
    void testMode_withMultipleItems() {
        assertEquals("a", ObjectUtils.mode("a", "b", "a"));
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
    void testRequireNonEmpty_withEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
    }

    @Test
    void testRequireNonEmpty_withNonEmptyString() {
        assertEquals("test", ObjectUtils.requireNonEmpty("test"));
    }

    @Test
    void testToString_withNullObject() {
        assertEquals("", ObjectUtils.toString(null));
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
            assertEquals((byte) 10, ObjectUtils.CONST((byte) 10));
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
            assertEquals(123456789L, ObjectUtils.CONST(123456789L));
        }

    @Test
        void testCONST_withShort() {
            assertEquals((short) 100, ObjectUtils.CONST((short) 100));
        }

    @Test
        void testCONST_withObject() {
            String str = "test";
            assertSame(str, ObjectUtils.CONST(str));
        }

    @Test
        void testCONST_BYTE_withValidValue() {
            assertEquals(Byte.MAX_VALUE, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        void testCONST_BYTE_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
        }

    @Test
        void testCONST_SHORT_withValidValue() {
            assertEquals(Short.MAX_VALUE, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        void testCONST_SHORT_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
        }

    @Test
        void testGetClass_withNullObject_2() {
            assertNull(ObjectUtils.getClass(null));
        }

    @Test
        void testGetFirstNonNull_withSuppliersReturningNonNull() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "non-null";
            assertEquals("non-null", ObjectUtils.getFirstNonNull(supplier1, supplier2));
        }

    @Test
        void testGetIfNull_withSupplierReturningNonNull() {
            String result = ObjectUtils.getIfNull(null, () -> "default");
            assertEquals("default", result);
        }

    @Test
        void testHashCodeMulti_withMultipleObjects() {
            int hash = ObjectUtils.hashCodeMulti("test", 123, new ArrayList<>());
            assertNotEquals(0, hash);
        }

    @Test
        void testIsEmpty_withEmptyCollection() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        void testIsEmpty_withNonEmptyCollection() {
            assertFalse(ObjectUtils.isEmpty(Collections.singletonList("element")));
        }

    @Test
        void testIsEmpty_withEmptyMap() {
            assertTrue(ObjectUtils.isEmpty(new HashMap<>()));
        }

    @Test
        void testIsEmpty_withNonEmptyMap() {
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");
            assertFalse(ObjectUtils.isEmpty(map));
        }

    @Test
        void testIsEmpty_withEmptyOptional() {
            assertTrue(ObjectUtils.isEmpty(Optional.empty()));
        }

    @Test
        void testIsEmpty_withNonEmptyOptional() {
            assertFalse(ObjectUtils.isEmpty(Optional.of("value")));
        }

    @Test
        void testMax_withComparableValuesIncludingNulls() {
            Integer max = ObjectUtils.max(1, null, 3, 2);
            assertEquals(Integer.valueOf(3), max);
        }

    @Test
        void testMedian_withComparatorAndMultipleItems() {
            Comparator<Integer> comparator = Comparator.naturalOrder();
            Integer median = ObjectUtils.median(comparator, 5, 3, 8, 1, 9);
            assertEquals(Integer.valueOf(5), median);
        }

    @Test
        void testMin_withComparableValuesIncludingNulls() {
            Integer min = ObjectUtils.min(null, 2, 1, null);
            assertEquals(Integer.valueOf(1), min);
        }

    @Test
        void testMode_withMultipleItemsHavingNoMode() {
            assertNull(ObjectUtils.mode("a", "b", "c"));
        }

    @Test
        void testRequireNonEmpty_withNonNullAndNotEmptyString() {
            String result = ObjectUtils.requireNonEmpty("test");
            assertEquals("test", result);
        }

    
    @Test
        void testRequireNonEmpty_withEmptyString_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
        }

    @Test
        void testToString_withSupplierReturningNonNull() {
            String result = ObjectUtils.toString(() -> "test", () -> "default");
            assertEquals("test", result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCompare_withNullGreaterTrue() {
            assertEquals(1, ObjectUtils.compare(null, "test", true));
            assertEquals(-1, ObjectUtils.compare("test", null, true));
        }

    @Test
        void testCompare_withNullGreaterFalse() {
            assertEquals(-1, ObjectUtils.compare(null, "test", false));
            assertEquals(1, ObjectUtils.compare("test", null, false));
        }

    @Test
        void testMedian_withComparatorAndSingleItem() {
            Comparator<Integer> comparator = Integer::compareTo;
            assertEquals(Integer.valueOf(5), ObjectUtils.median(comparator, 5));
        }

    @Test
        void testMedian_withSingleItem() {
            assertEquals(Integer.valueOf(5), ObjectUtils.median(5));
        }

    @Test
        void testMode_withNoDuplicates() {
            assertNull(ObjectUtils.mode(1, 2, 3, 4, 5));
        }

    
    @Test
        void testRequireNonEmpty_withEmptyString_3() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
        }

    @Test
        void testRequireNonEmpty_withEmptyCollection() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(new ArrayList<>()));
        }

    @Test
        void testRequireNonEmpty_withEmptyMap() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(new HashMap<>()));
        }

    @Test
        void testRequireNonEmpty_withEmptyOptional() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(Optional.empty()));
        }

    @Test
        void testToString_withSupplierReturningNull() {
            assertEquals("default", ObjectUtils.toString((Object) null, () -> "default"));
        }

    @Test
        void testToString_withSupplierReturningNonNull_2() {
            assertEquals("value", ObjectUtils.toString("value", () -> "default"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testMedian_withComparatorAndEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median(Comparator.naturalOrder()));
        }

    @Test
        void testMedian_withSingleItemUsingComparator() {
            assertEquals(Integer.valueOf(5), ObjectUtils.median(Comparator.naturalOrder(), 5));
        }

    @Test
        void testMode_withEmptyArray() {
            assertNull(ObjectUtils.mode());
        }

    @Test
        void testMode_withNoDuplicatesInArray() {
            assertNull(ObjectUtils.mode(1, 2, 3, 4, 5));
        }

    @Test
        void testRequireNonEmpty_withEmptyCollection_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(Collections.emptyList()));
        }

    @Test
        void testRequireNonEmpty_withEmptyMap_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(new HashMap<>()));
        }

    @Test
        void testRequireNonEmpty_withEmptyOptional_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(Optional.empty()));
        }

    @Test
        void testToString_withSupplierReturningNullString() {
            assertEquals("default", ObjectUtils.toString((Object) null, () -> "default"));
        }

    @Test
        void testToString_withSupplierReturningNonNullString() {
            assertEquals("value", ObjectUtils.toString("value", () -> "default"));
        }
}
