package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Optional;
import java.time.Duration;
import java.io.IOException;
import org.apache.commons.lang3.exception.CloneFailedException;

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

    
    @Test
        void testAllNotNull_withNullArray() {
            assertFalse(ObjectUtils.allNotNull((Object[]) null));
        }

    @Test
        void testClone_primitiveArray() {
            int[] original = {1, 2, 3};
            int[] cloned = ObjectUtils.clone(original);
            assertNotSame(original, cloned);
            assertArrayEquals(original, cloned);
        }

    @Test
        void testClone_objectArray() {
            String[] original = {"a", "b", "c"};
            String[] cloned = ObjectUtils.clone(original);
            assertNotSame(original, cloned);
            assertArrayEquals(original, cloned);
        }

    @Test
        void testClone_nonClonableObject() {
            assertNull(ObjectUtils.clone(new Object()));
        }

    @Test
        void testCompare_withNullGreaterTrue() {
            assertEquals(1, ObjectUtils.compare(null, new Integer(5), true));
            assertEquals(-1, ObjectUtils.compare(new Integer(5), null, true));
        }

    @Test
        void testCONST_boolean() {
            assertTrue(ObjectUtils.CONST(true));
            assertFalse(ObjectUtils.CONST(false));
        }

    @Test
        void testCONST_byte() {
            assertEquals((byte) 10, ObjectUtils.CONST((byte) 10));
        }

    @Test
        void testCONST_char() {
            assertEquals('a', ObjectUtils.CONST('a'));
        }

    @Test
        void testCONST_double() {
            assertEquals(1.5, ObjectUtils.CONST(1.5), 0);
        }

    @Test
        void testCONST_float() {
            assertEquals(1.5f, ObjectUtils.CONST(1.5f), 0);
        }

    @Test
        void testCONST_int() {
            assertEquals(10, ObjectUtils.CONST(10));
        }

    @Test
        void testCONST_long() {
            assertEquals(10L, ObjectUtils.CONST(10L));
        }

    @Test
        void testCONST_short() {
            assertEquals((short) 10, ObjectUtils.CONST((short) 10));
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
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
        }

    @Test
        void testCONST_SHORT_validValue() {
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        void testCONST_SHORT_invalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(-32769));
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
        void testIdentityToString_appendable() throws java.io.IOException {
            StringWriter writer = new StringWriter();
            ObjectUtils.identityToString(writer, "test");
            assertEquals("java.lang.String@".length() + 8, writer.toString().length()); // Assuming hash code length is 8
        }

    @Test
        void testIdentityToString_withNullObject() {
            assertNull(ObjectUtils.identityToString((Object) null));
        }

    @Test
        void testIdentityToString_strBuilder() {
            StrBuilder builder = new StrBuilder();
            ObjectUtils.identityToString(builder, "test");
            assertEquals("java.lang.String@".length() + 8, builder.length()); // Assuming hash code length is 8
        }

    @Test
        void testIdentityToString_stringBuffer() {
            StringBuffer buffer = new StringBuffer();
            ObjectUtils.identityToString(buffer, "test");
            assertEquals("java.lang.String@".length() + 8, buffer.length()); // Assuming hash code length is 8
        }

    
    @Test
        void testClone_NoCloneMethod() throws CloneNotSupportedException {
            class NonClonableObject implements Cloneable {}
            NonClonableObject obj = new NonClonableObject();
            Exception exception = assertThrows(org.apache.commons.lang3.exception.CloneFailedException.class, () -> ObjectUtils.clone(obj));
            assertTrue(exception.getMessage().contains("has no clone method"));
        }

    
    
    @Test
        void testGetFirstNonNull_SingleSupplierReturningNull() {
            Supplier<String> supplier = () -> null;
            assertNull(ObjectUtils.getFirstNonNull(supplier));
        }

    @Test
        void testHashCodeMulti_NullArray() {
            assertEquals(1, ObjectUtils.hashCodeMulti((Object[]) null));
        }

    @Test
        void testIdentityToString_Appendable() throws java.io.IOException {
            Appendable appendable = new StringBuilder();
            ObjectUtils.identityToString(appendable, "test");
            String expected = "java.lang.String@" + Integer.toHexString(System.identityHashCode("test"));
            assertEquals(expected, appendable.toString());
        }

    @Test
        void testIdentityToString_StrBuilder() {
            StrBuilder builder = new StrBuilder();
            ObjectUtils.identityToString(builder, "test");
            String expected = "java.lang.String@" + Integer.toHexString(System.identityHashCode("test"));
            assertEquals(expected, builder.toString());
        }

    @Test
        void testIdentityToString_StringBuffer() {
            StringBuffer buffer = new StringBuffer();
            ObjectUtils.identityToString(buffer, "test");
            String expected = "java.lang.String@" + Integer.toHexString(System.identityHashCode("test"));
            assertEquals(expected, buffer.toString());
        }

    @Test
        void testIsArray_ArrayObject() {
            assertTrue(ObjectUtils.isArray(new int[0]));
        }

    @Test
        void testIsEmpty_EmptyString() {
            assertTrue(ObjectUtils.isEmpty(""));
        }

    @Test
        void testIsEmpty_EmptyCollection() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        void testIsEmpty_EmptyMap() {
            assertTrue(ObjectUtils.isEmpty(new Hashtable<>()));
        }

    @Test
        void testIsEmpty_EmptyOptional() {
            assertTrue(ObjectUtils.isEmpty(Optional.empty()));
        }

    @Test
        void testMax_NullArray() {
            assertNull(ObjectUtils.max((Integer[]) null));
        }

    @Test
        void testMin_NullArray() {
            assertNull(ObjectUtils.min((Integer[]) null));
        }

    @Test
        void testMode_NoItems() {
            assertNull(ObjectUtils.mode());
        }

    @Test
        void testMode_MultipleValuesWithTie() {
            assertNull(ObjectUtils.mode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        }

    @Test
        void testRequireNonEmpty_EmptyString() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
            assertEquals("object", exception.getMessage());
        }

    
    @Test
        void testToString_SupplierNull() {
            Supplier<Object> objSupplier = () -> null;
            Supplier<String> strSupplier = () -> "default";
            assertEquals("default", ObjectUtils.toString(objSupplier, strSupplier));
        }

    @Test
        void testWait_ZeroDuration() throws InterruptedException {
            Object lock = new Object();
            synchronized (lock) {
                Thread before = new Thread(() -> {
                    try {
                        ObjectUtils.wait(lock, Duration.ZERO);
                    } catch (InterruptedException e) {
                        fail("Thread interrupted");
                    }
                });
                before.start();
                before.join();
            }
        }

    @Test
        void testConstructor() {
            assertNotNull(new ObjectUtils());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testReadResolve() {
            ObjectUtils.Null nullInstance = new ObjectUtils.Null();
            assertSame(ObjectUtils.NULL, nullInstance);
        }

    @Test
        void testClone_IllegalAccessException() {
            final Object obj = new ArrayList<>();
            Exception exception = assertThrows(CloneFailedException.class, () -> ObjectUtils.clone(obj));
            assertTrue(exception.getMessage().contains("Cannot clone Cloneable type"));
        }

    @Test
        void testHashCodeMulti_NullArray_2() {
            assertEquals(1, ObjectUtils.hashCodeMulti((Object[]) null));
        }

    @Test
        void testIdentityToString_StringBuilder() throws IOException {
            StringBuilder builder = new StringBuilder();
            Object obj = new Object();
            ObjectUtils.identityToString(builder, obj);
            assertTrue(builder.toString().contains(obj.getClass().getName()));
        }

    @Test
        void testIsEmpty_EmptyOptional_2() {
            assertTrue(ObjectUtils.isEmpty(Optional.empty()));
        }

    @Test
        void testIsEmpty_NonEmptyOptional() {
            assertFalse(ObjectUtils.isEmpty(Optional.of("test")));
        }

    @Test
        void testGetFirstNonNull_SingleSupplierReturningNull_2() {
            Supplier<String> supplier = () -> null;
            assertNull(ObjectUtils.getFirstNonNull(supplier));
        }

    @Test
        void testToString_SupplierNull_2() {
            assertEquals("default", ObjectUtils.toString((Supplier<Object>) null, () -> "default"));
        }

    @Test
        void testWait_ZeroDuration_2() throws InterruptedException {
            final Object obj = new Object();
            assertDoesNotThrow(() -> ObjectUtils.wait(obj, Duration.ZERO));
        }
}
