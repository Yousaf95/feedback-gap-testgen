package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import java.util.function.Supplier;
import java.util.Collections;
import java.util.Arrays;

public class ObjectUtils_GenTest {

    @Test
    public void testAllNotNull() {
        assertTrue(allNotNull(1, "test", 3.14));
        assertFalse(allNotNull(1, null, 3.14));
        assertTrue(allNotNull());
    }

    @Test
    public void testAllNull() {
        assertTrue(allNull(null, null, null));
        assertFalse(allNull(1, null, 3.14));
        assertTrue(allNull());
    }

    
    
    
    
    
    @Test
    public void testCONST() {
        assertEquals(true, CONST(true));
        assertEquals(42, CONST(42));
        assertEquals('a', CONST('a'));
        assertEquals(3.14, CONST(3.14));
        assertEquals((byte) 1, CONST_BYTE(1));
        assertEquals((short) 2, CONST_SHORT(2));
    }

    @Test
    public void testDefaultIfNull() {
        String str = defaultIfNull(null, "default");
        assertEquals("default", str);

        str = defaultIfNull("value", "default");
        assertEquals("value", str);
    }

    
    
    
    @Test
    public void testGetIfNull() {
        Integer result = getIfNull(null, () -> 42);
        assertEquals(Integer.valueOf(42), result);

        result = getIfNull(1, () -> 42);
        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void testHashCodeHex() {
        String hash = hashCodeHex("test");
        assertNotNull(hash);
        assertTrue(hash.matches("[0-9a-fA-F]+"));

        hash = hashCodeHex(null);
        assertNotNull(hash);
        assertTrue(hash.matches("[0-9a-fA-F]+"));
    }

    @Test
    public void testIdentityHashCodeHex() {
        String hash = identityHashCodeHex("test");
        assertNotNull(hash);
        assertTrue(hash.matches("[0-9a-fA-F]+"));

        hash = identityHashCodeHex(null);
        assertNotNull(hash);
        assertTrue(hash.matches("[0-9a-fA-F]+"));
    }

    @Test
    public void testIdentityToString() throws IOException {
        String str = identityToString("test");
        assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), str);

        str = identityToString(null);
        assertNull(str);

        Appendable appendable = new StringBuilder();
        identityToString(appendable, "test");
        assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), appendable.toString());

        StringBuffer buffer = new StringBuffer();
        identityToString(buffer, "test");
        assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), buffer.toString());
    }

    
    
    
    @Test
    public void testMax() {
        assertEquals(Integer.valueOf(4), max(1, 2, 3, 4));
        assertEquals(Double.valueOf(5.0), max(1.0, 2.0, 3.0, 4.0, 5.0));

        assertNull(max());
    }

    
    @Test
    public void testMin() {
        assertEquals(Integer.valueOf(1), min(1, 2, 3, 4));
        assertEquals(Double.valueOf(1.0), min(1.0, 2.0, 3.0, 4.0));

        assertNull(min());
    }

    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAllNotNull_withNullValues() {
            assertFalse(ObjectUtils.allNotNull(null, "value"));
        }

    @Test
        public void testAllNotNull_withNonNullValues() {
            assertTrue(ObjectUtils.allNotNull("value1", "value2"));
        }

    @Test
        public void testAnyNull_withNullValues() {
            assertTrue(ObjectUtils.anyNull(null, "value"));
        }

    @Test
        public void testAnyNull_withNonNullValues() {
            assertFalse(ObjectUtils.anyNull("value1", "value2"));
        }

    @Test
        public void testCloneWithArray() {
            Integer[] original = {1, 2, 3};
            Integer[] cloned = ObjectUtils.clone(original);
            assertArrayEquals(original, cloned);
        }

    
    @Test
        public void testCloneWithNull() {
            assertNull(ObjectUtils.clone(null));
        }

    @Test
        public void testCompare_withNullValues() {
            assertEquals(0, ObjectUtils.compare(null, null));
        }

    @Test
        public void testCompare_withNonNullValues() {
            assertEquals(-1, ObjectUtils.compare("apple", "banana"));
        }

    @Test
        public void testCONST_BYTE_withValidValue() {
            byte result = ObjectUtils.CONST_BYTE(50);
            assertEquals((byte) 50, result);
        }

    @Test
        public void testCONST_SHORT_withValidValue() {
            short result = ObjectUtils.CONST_SHORT(12345);
            assertEquals((short) 12345, result);
        }

    @Test
        public void testCONST_BYTE_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
        }

    @Test
        public void testCONST_SHORT_withInvalidValue() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsEmpty_withEmptyCollection() {
            assertTrue(isEmpty(Collections.emptyList()));
        }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testReadResolve() {
            // This method is private and returns a singleton instance, so we can't directly test it.
            // However, we can ensure that the class has a no-arg constructor which is used by readResolve.
            assertNotNull(new ObjectUtils.Null());
        }

    @Test
        public void testAllNotNull_withNullValues_2() {
            assertFalse(ObjectUtils.allNotNull(null, "value"));
        }

    @Test
        public void testAllNotNull_withNonNullValues_2() {
            assertTrue(ObjectUtils.allNotNull("value1", "value2"));
        }

    @Test
        public void testAnyNull_withNullValues_2() {
            assertTrue(ObjectUtils.anyNull(null, "value"));
        }

    @Test
        public void testAnyNull_withNonNullValues_2() {
            assertFalse(ObjectUtils.anyNull("value1", "value2"));
        }

    @Test
        public void testCloneWithArray_2() throws CloneFailedException {
            Integer[] original = {1, 2, 3};
            Integer[] cloned = ObjectUtils.clone(original);
            assertArrayEquals(original, cloned);
        }

    @Test
        public void testCloneWithNull_2() throws CloneFailedException {
            assertNull(ObjectUtils.clone(null));
        }

    @Test
        public void testCompare_withNullValues_2() {
            assertEquals(1, ObjectUtils.compare(null, "value"));
            assertEquals(-1, ObjectUtils.compare("value", null));
            assertEquals(0, ObjectUtils.compare(null, null));
        }

    @Test
        public void testCompare_withNonNullValues_2() {
            assertEquals(-1, ObjectUtils.compare("apple", "banana"));
            assertEquals(1, ObjectUtils.compare("banana", "apple"));
            assertEquals(0, ObjectUtils.compare("apple", "apple"));
        }

    @Test
        public void testCONST_BYTE_withValidValue_2() {
            assertEquals((byte) 5, ObjectUtils.CONST_BYTE(5));
        }

    @Test
        public void testCONST_SHORT_withValidValue_2() {
            assertEquals((short) 10, ObjectUtils.CONST_SHORT(10));
        }

    @Test
        public void testCONST_BYTE_withInvalidValue_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
        }

    @Test
        public void testCONST_SHORT_withInvalidValue_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
        }

    @Test
        public void testDefaultIfNull_2() {
            assertEquals("default", ObjectUtils.defaultIfNull(null, "default"));
            assertEquals("value", ObjectUtils.defaultIfNull("value", "default"));
        }

    @Test
        public void testEquals_withNullValues() {
            assertTrue(ObjectUtils.equals(null, null));
            assertFalse(ObjectUtils.equals("value1", null));
            assertFalse(ObjectUtils.equals(null, "value2"));
        }

    @Test
        public void testGetIfNull_2() {
            assertEquals("default", ObjectUtils.getIfNull(null, () -> "default"));
            assertEquals("value", ObjectUtils.getIfNull("value", () -> "default"));
        }

    @Test
        public void testHashCodeHex_2() {
            assertEquals("0", ObjectUtils.hashCodeHex(null));
            assertEquals(Integer.toHexString(Objects.hashCode("test")), ObjectUtils.hashCodeHex("test"));
        }

    @Test
        public void testIdentityHashCodeHex_2() {
            assertEquals(Integer.toHexString(System.identityHashCode(new Object())), ObjectUtils.identityHashCodeHex(new Object()));
        }

    @Test
        public void testIdentityToString_withNull() {
            assertNull(ObjectUtils.identityToString(null));
        }

    @Test
        public void testIdentityToString_withNonNull() {
            String expected = "java.lang.Object@" + Integer.toHexString(System.identityHashCode(new Object()));
            assertEquals(expected, ObjectUtils.identityToString(new Object()));
        }

    @Test
        public void testIsArray_withNull() {
            assertFalse(ObjectUtils.isArray(null));
        }

    @Test
        public void testIsArray_withNonArrayObject() {
            assertFalse(ObjectUtils.isArray("test"));
        }

    @Test
        public void testIsArray_withArrayObject() {
            assertTrue(ObjectUtils.isArray(new String[]{"a", "b"}));
        }

    @Test
        public void testIsEmpty_withNull() {
            assertTrue(ObjectUtils.isEmpty(null));
        }

    @Test
        public void testIsEmpty_withEmptyCollection_2() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        public void testIsEmpty_withNonEmptyCollection() {
            assertFalse(ObjectUtils.isEmpty(Arrays.asList("a", "b")));
        }
}
