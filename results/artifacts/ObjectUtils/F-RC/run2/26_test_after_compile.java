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
        public void testReadResolve() {
            // This method is private and not intended to be tested directly.
            // However, we can verify that it returns the NULL singleton instance.
            Object result = new ObjectUtils.Null();
            assertEquals(ObjectUtils.NULL, result);
        }

    @Test
        public void testAllNotNull_withNullValues_2() {
            assertFalse(ObjectUtils.allNotNull(null, "value", 123));
        }

    @Test
        public void testAllNotNull_withNonNullValues_2() {
            assertTrue(ObjectUtils.allNotNull("value", 123));
        }

    @Test
        public void testAnyNull_withNullValues_2() {
            assertTrue(ObjectUtils.anyNull(null, "value", 123));
        }

    @Test
        public void testAnyNull_withNonNullValues_2() {
            assertFalse(ObjectUtils.anyNull("value", 123));
        }

    @Test
        public void testCloneWithArray_2() {
            Integer[] original = {1, 2, 3};
            Integer[] cloned = ObjectUtils.clone(original);
            assertArrayEquals(original, cloned);
        }

    @Test
        public void testCloneWithNull_2() {
            assertNull(ObjectUtils.clone(null));
        }

    @Test
        public void testCompare_withNullValues_2() {
            assertEquals(0, ObjectUtils.compare(null, null));
            assertEquals(-1, ObjectUtils.compare(null, "value"));
            assertEquals(1, ObjectUtils.compare("value", null));
        }

    @Test
        public void testCompare_withNonNullValues_2() {
            assertEquals(0, ObjectUtils.compare("value", "value"));
            assertTrue(ObjectUtils.compare("a", "b") < 0);
            assertTrue(ObjectUtils.compare("b", "a") > 0);
        }

    @Test
        public void testCONST_BYTE_withValidValue_2() {
            byte result = ObjectUtils.CONST_BYTE(127);
            assertEquals((byte) 127, result);
        }

    @Test
        public void testCONST_SHORT_withValidValue_2() {
            short result = ObjectUtils.CONST_SHORT(32767);
            assertEquals((short) 32767, result);
        }

    @Test
        public void testCONST_BYTE_withInvalidValue_2() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_BYTE(-129));
            assertEquals("Supplied value must be a valid byte literal between -128 and 127: [-129]", exception.getMessage());
        }

    @Test
        public void testCONST_SHORT_withInvalidValue_2() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals("Supplied value must be a valid byte literal between -32768 and 32767: [32768]", exception.getMessage());
        }

    @Test
        public void testCONST_2() {
            String result = ObjectUtils.CONST("value");
            assertEquals("value", result);
        }

    @Test
        public void testGetIfNull_withNonNullValue() {
            Supplier<String> supplier = () -> "default";
            String result = ObjectUtils.getIfNull("value", supplier);
            assertEquals("value", result);
        }

    @Test
        public void testGetIfNull_withNullValue() {
            Supplier<String> supplier = () -> "default";
            String result = ObjectUtils.getIfNull(null, supplier);
            assertEquals("default", result);
        }

    @Test
        public void testHashCodeHex_2() {
            int hash = 123;
            String result = ObjectUtils.hashCodeHex(new Object() { @Override public int hashCode() { return hash; } });
            assertEquals(Integer.toHexString(hash), result);
        }

    @Test
        public void testIdentityHashCodeHex_2() {
            int hash = System.identityHashCode("value");
            String result = ObjectUtils.identityHashCodeHex("value");
            assertEquals(Integer.toHexString(hash), result);
        }

    @Test
        public void testIdentityToString_withNull() {
            assertNull(ObjectUtils.identityToString(null));
        }

    @Test
        public void testIdentityToString_withNonNull() {
            String result = ObjectUtils.identityToString(new Object());
            assertTrue(result.contains("@"));
        }

    @Test
        public void testIsArray_withArray() {
            assertTrue(ObjectUtils.isArray(new int[] {1, 2, 3}));
        }

    @Test
        public void testIsArray_withNonArray() {
            assertFalse(ObjectUtils.isArray("value"));
        }

    @Test
        public void testIsEmpty_withNull() {
            assertTrue(ObjectUtils.isEmpty(null));
        }

    @Test
        public void testIsEmpty_withEmptyCollection() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        public void testIsEmpty_withNonEmptyCollection() {
            assertFalse(ObjectUtils.isEmpty(Arrays.asList("value")));
        }

    @Test
        public void testMax_withNullValues() {
            assertNull(ObjectUtils.max(null, "value", 123));
        }

    @Test
        public void testMin_withNullValues() {
            assertNull(ObjectUtils.min(null, "value", 123));
        }
}
