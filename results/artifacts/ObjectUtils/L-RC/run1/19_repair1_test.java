package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;

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
        void testReadResolve() throws Exception {
            ObjectUtils.Null nullInstance = new ObjectUtils.Null();
            assertEquals(ObjectUtils.NULL, nullInstance);
        }

    @Test
        void testAllNotNull_withNullArray() {
            assertFalse(ObjectUtils.allNotNull((Object[]) null));
        }

    @Test
        void testClone_primitiveArray() {
            int[] original = {1, 2, 3};
            int[] cloned = ObjectUtils.clone(original);
            assertArrayEquals(original, cloned);
            assertNotSame(original, cloned);
        }

    @Test
        void testClone_objectArray() {
            String[] original = {"a", "b", "c"};
            String[] cloned = ObjectUtils.clone(original);
            assertArrayEquals(original, cloned);
            assertNotSame(original, cloned);
        }

    @Test
        void testClone_nonClonableObject() {
            assertNull(ObjectUtils.clone(new Object()));
        }

    @Test
        void testCompare_withNullGreaterTrue() {
            assertEquals(1, ObjectUtils.compare(null, "a", true));
            assertEquals(-1, ObjectUtils.compare("a", null, true));
        }

    @Test
        void testCONST_boolean() {
            assertTrue(ObjectUtils.CONST(true));
            assertFalse(ObjectUtils.CONST(false));
        }

    @Test
        void testCONST_byte() {
            assertEquals((byte) 127, ObjectUtils.CONST((byte) 127));
        }

    @Test
        void testCONST_char() {
            assertEquals('a', ObjectUtils.CONST('a'));
        }

    @Test
        void testCONST_double() {
            assertEquals(1.0, ObjectUtils.CONST(1.0), 0);
        }

    @Test
        void testCONST_float() {
            assertEquals(1.0f, ObjectUtils.CONST(1.0f), 0);
        }

    @Test
        void testCONST_int() {
            assertEquals(1, ObjectUtils.CONST(1));
        }

    @Test
        void testCONST_long() {
            assertEquals(1L, ObjectUtils.CONST(1L));
        }

    @Test
        void testCONST_short() {
            assertEquals((short) 127, ObjectUtils.CONST((short) 127));
        }

    @Test
        void testCONST_generic() {
            String value = "test";
            assertSame(value, ObjectUtils.CONST(value));
        }

    @Test
        void testCONST_BYTE_validValue() {
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        void testCONST_BYTE_invalidValue() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertEquals("Supplied value must be a valid byte literal between -128 and 127: [128]", exception.getMessage());
        }

    @Test
        void testCONST_SHORT_validValue() {
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        void testCONST_SHORT_invalidValue() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals("Supplied value must be a valid byte literal between -32768 and 32767: [32768]", exception.getMessage());
        }

    @Test
        void testGetFirstNonNull_withNullSuppliers() {
            assertNull(ObjectUtils.getFirstNonNull((Supplier<Object>[]) null));
        }

    @Test
        void testHashCodeMulti_withNullArray() {
            assertEquals(1, ObjectUtils.hashCodeMulti((Object[]) null));
        }

    @Test
        void testIdentityToString_withAppendable() throws java.io.IOException {
            StringWriter writer = new StringWriter();
            ObjectUtils.identityToString(writer, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), writer.toString());
        }

    @Test
        void testIdentityToString_withNullObject() {
            assertNull(ObjectUtils.identityToString((Object) null));
        }

    @Test
        void testIdentityToString_withStrBuilder() {
            org.apache.commons.lang3.text.StrBuilder builder = new org.apache.commons.lang3.text.StrBuilder();
            ObjectUtils.identityToString(builder, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), builder.toString());
        }

    @Test
        void testIdentityToString_withStringBuffer() {
            StringBuffer buffer = new StringBuffer();
            ObjectUtils.identityToString(buffer, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), buffer.toString());
        }
}
