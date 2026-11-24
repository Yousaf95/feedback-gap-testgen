package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

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
        Integer[] original = {1, 2, 3};
        Integer[] cloned = ObjectUtils.clone(original);
        assertArrayEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testClone_withNonClonableObject() {
        assertNull(ObjectUtils.clone("string"));
    }

    @Test
    void testCloneIfPossible_withClonableObject() throws CloneNotSupportedException {
        Integer[] original = {1, 2, 3};
        Integer[] cloned = ObjectUtils.cloneIfPossible(original);
        assertArrayEquals(original, cloned);
        assertNotSame(original, cloned);
    }

    @Test
    void testCloneIfPossible_withNonClonableObject() {
        String original = "string";
        String cloned = ObjectUtils.cloneIfPossible(original);
        assertEquals(original, cloned);
        assertSame(original, cloned);
    }

    @Test
    void testCompare_withNullValues() {
        assertEquals(0, ObjectUtils.compare(null, null));
        assertEquals(-1, ObjectUtils.compare(null, 1));
        assertEquals(1, ObjectUtils.compare(1, null));
    }

    @Test
    void testCompare_withNonNullValues() {
        assertEquals(0, ObjectUtils.compare(1, 1));
        assertEquals(-1, ObjectUtils.compare(1, 2));
        assertEquals(1, ObjectUtils.compare(2, 1));
    }

    @Test
    void testCompare_withNullGreater() {
        assertEquals(1, ObjectUtils.compare(null, 1, true));
        assertEquals(-1, ObjectUtils.compare(1, null, true));
    }

    @Test
    void testDefaultIfNull_withNonNullValue() {
        assertEquals("test", ObjectUtils.defaultIfNull("test", "default"));
    }

    @Test
    void testDefaultIfNull_withNullValue() {
        assertEquals("default", ObjectUtils.defaultIfNull(null, "default"));
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
    void testEquals_withNullValues() {
        assertTrue(ObjectUtils.equals(null, null));
        assertFalse(ObjectUtils.equals(null, "test"));
        assertFalse(ObjectUtils.equals("test", null));
    }

    @Test
    void testFirstNonNull_withAllNulls() {
        assertNull(ObjectUtils.firstNonNull(null, null));
    }

    @Test
    void testFirstNonNull_withSomeNulls() {
        assertEquals("test", ObjectUtils.firstNonNull(null, "test"));
    }

    @Test
    void testFirstNonNull_withNoNulls() {
        assertEquals("first", ObjectUtils.firstNonNull("first", "second"));
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
    void testGetFirstNonNull_withAllNullSuppliers() {
        assertNull(ObjectUtils.getFirstNonNull(() -> null, () -> null));
    }

    @Test
    void testGetFirstNonNull_withSomeNullSuppliers() {
        assertEquals("test", ObjectUtils.getFirstNonNull(() -> null, () -> "test"));
    }

    @Test
    void testGetFirstNonNull_withNoNullSuppliers() {
        assertEquals("first", ObjectUtils.getFirstNonNull(() -> "first", () -> "second"));
    }

    @Test
    void testGetIfNull_withNonNullObject() {
        assertEquals("test", ObjectUtils.getIfNull("test", () -> "default"));
    }

    @Test
    void testGetIfNull_withNullObject() {
        assertEquals("default", ObjectUtils.getIfNull(null, () -> "default"));
    }

    @Test
    void testHashCode_withNonNullObject() {
        assertEquals(java.util.Objects.hashCode("test"), ObjectUtils.hashCode("test"));
    }

    @Test
    void testHashCode_withNullObject() {
        assertEquals(0, ObjectUtils.hashCode(null));
    }

    @Test
    void testHashCodeHex_withNonNullObject() {
        assertEquals(Integer.toHexString(java.util.Objects.hashCode("test")), ObjectUtils.hashCodeHex("test"));
    }

    @Test
    void testHashCodeHex_withNullObject() {
        assertEquals("0", ObjectUtils.hashCodeHex(null));
    }

    @Test
    void testIdentityHashCodeHex_withNonNullObject() {
        assertEquals(Integer.toHexString(System.identityHashCode("test")), ObjectUtils.identityHashCodeHex("test"));
    }

    @Test
    void testIdentityToString_withNonNullObject() throws java.io.IOException {
        String expected = "java.lang.String@" + Integer.toHexString(System.identityHashCode("test"));
        StringBuilder builder = new StringBuilder();
        ObjectUtils.identityToString(builder, "test");
        assertEquals(expected, builder.toString());
    }

    @Test
    void testIdentityToString_withNullObject() {
        assertNull(ObjectUtils.identityToString((Object) null));
    }

    @Test
    void testIsArray_withArray() {
        assertTrue(ObjectUtils.isArray(new int[]{}));
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
    void testIsEmpty_withEmptyCollection() {
        assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
    }

    @Test
    void testIsEmpty_withNonEmptyCollection() {
        assertFalse(ObjectUtils.isEmpty(Arrays.asList("test")));
    }

    @Test
    void testIsEmpty_withEmptyMap() {
        assertTrue(ObjectUtils.isEmpty(Collections.emptyMap()));
    }

    @Test
    void testIsEmpty_withNonEmptyMap() {
        assertFalse(ObjectUtils.isEmpty(Collections.singletonMap("key", "value")));
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
    void testMedian_withComparator() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        assertEquals(2, ObjectUtils.median(comparator, 1, 2, 3));
    }

    @Test
    void testMin_withComparableValues() {
        assertEquals(1, ObjectUtils.min(1, 2, 3));
    }

    
    @Test
    void testMode_withMultipleItems() {
        assertEquals("test", ObjectUtils.mode("test", "test", "other"));
    }

    @Test
    void testMode_withNoItems() {
        assertNull(ObjectUtils.mode());
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
    void testNotEqual_withNullValues() {
        assertFalse(ObjectUtils.notEqual(null, null));
        assertTrue(ObjectUtils.notEqual(null, "test"));
        assertTrue(ObjectUtils.notEqual("test", null));
    }

    @Test
    void testRequireNonEmpty_withNonNullObject() {
        assertEquals("test", ObjectUtils.requireNonEmpty("test"));
    }

    
    @Test
    void testRequireNonEmpty_withEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
    }

    @Test
    void testToString_withNonNullObject() {
        assertEquals("test", ObjectUtils.toString("test"));
    }

    @Test
    void testToString_withNullObject() {
        assertEquals("", ObjectUtils.toString((Object) null));
    }

    @Test
    void testToString_withSupplier() {
        assertEquals("test", ObjectUtils.toString(() -> "test", () -> "default"));
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
            assertEquals(10.5, ObjectUtils.CONST(10.5), 0.0);
        }

    @Test
        void testCONST_withFloat() {
            assertEquals(10.5f, ObjectUtils.CONST(10.5f), 0.0);
        }

    @Test
        void testCONST_withInt() {
            assertEquals(10, ObjectUtils.CONST(10));
        }

    @Test
        void testCONST_withLong() {
            assertEquals(10L, ObjectUtils.CONST(10L));
        }

    @Test
        void testCONST_withShort() {
            assertEquals((short) 10, ObjectUtils.CONST((short) 10));
        }

    @Test
        void testCONST_withObject() {
            String testString = "test";
            assertSame(testString, ObjectUtils.CONST(testString));
        }

    @Test
        void testCONST_BYTE_withValidValue() {
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        void testCONST_BYTE_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(130));
        }

    @Test
        void testCONST_SHORT_withValidValue() {
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        void testCONST_SHORT_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(33000));
        }

    @Test
        void testGetClass_withArrayObject() {
            assertEquals(int[].class, ObjectUtils.getClass(new int[0]));
        }

    @Test
        void testHashCodeMulti_withNullValues() {
            assertEquals(1, ObjectUtils.hashCodeMulti((Object[]) null));
        }

    @Test
        void testHashCodeMulti_withNonNullValues() {
            assertEquals(31 * 42 + "test".hashCode(), ObjectUtils.hashCodeMulti("test", 42));
        }

    @Test
        void testIdentityToString_withAppendable() throws IOException {
            StringBuilder builder = new StringBuilder();
            ObjectUtils.identityToString(builder, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), builder.toString());
        }

    @Test
        void testMedian_withComparatorAndEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median(Comparator.naturalOrder(), new Integer[0]));
        }

    @Test
        void testMedian_withComparatorAndNullElement() {
            assertThrows(NullPointerException.class, () -> ObjectUtils.median(Comparator.naturalOrder(), 1, null, 3));
        }

    @Test
        void testMode_withEmptyArray() {
            assertNull(ObjectUtils.mode());
        }

    @Test
        void testMode_withSingleItem() {
            assertEquals("a", ObjectUtils.mode("a"));
        }

    @Test
        void testRequireNonEmpty_withNullObject() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty((String) null));
        }

    @Test
        void testRequireNonEmpty_withEmptyString_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
        }

    @Test
        void testToString_withSupplierAndNonNullObject() {
            assertEquals("test", ObjectUtils.toString(() -> "test", () -> "default"));
        }

    @Test
        void testToString_withSupplierAndNullObject() {
            assertEquals("default", ObjectUtils.toString((String) null, () -> "default"));
        }
}
