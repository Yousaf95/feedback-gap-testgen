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
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

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
        public void testDefaultIfNullWithNull() {
            String result = ObjectUtils.defaultIfNull(null, "default");
            assertEquals("default", result);
        }

    @Test
        public void testDefaultIfNullWithValue() {
            String result = ObjectUtils.defaultIfNull("value", "default");
            assertEquals("value", result);
        }

    @Test
        public void testGetIfNullWithNullObject() {
            String result = ObjectUtils.getIfNull(null, () -> "default");
            assertEquals("default", result);
        }

    @Test
        public void testGetIfNullWithValueObject() {
            String result = ObjectUtils.getIfNull("value", () -> "default");
            assertEquals("value", result);
        }

    @Test
        public void testGetFirstNonNullWithNullSuppliers() {
            String result = ObjectUtils.getFirstNonNull(() -> null, () -> "default");
            assertEquals("default", result);
        }

    @Test
        public void testGetFirstNonNullWithNonNullSuppliers() {
            String result = ObjectUtils.getFirstNonNull(() -> null, () -> "value");
            assertEquals("value", result);
        }

    @Test
        public void testModeWithEmptyArray() {
            String result = ObjectUtils.mode();
            assertNull(result);
        }

    @Test
        public void testModeWithSingleElementArray() {
            String result = ObjectUtils.mode("a");
            assertEquals("a", result);
        }

    @Test
        public void testModeWithMultipleElementsArray() {
            String result = ObjectUtils.mode("a", "b", "a");
            assertEquals("a", result);
        }

    @Test
        public void testMedianWithEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median());
        }

    @Test
        public void testMedianWithSingleElementArray() {
            Integer result = ObjectUtils.median(1);
            assertEquals(1, (int) result);
        }

    @Test
        public void testMedianWithMultipleElementsArray() {
            Integer result = ObjectUtils.median(3, 1, 2);
            assertEquals(2, (int) result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testDefaultIfNullWithNullSupplier() {
            Supplier<String> nullSupplier = () -> null;
            String result = ObjectUtils.defaultIfNull(null, nullSupplier);
            assertNull(result);
        }

    @Test
        public void testDefaultIfNullWithValueSupplier() {
            Supplier<String> valueSupplier = () -> "default";
            String result = ObjectUtils.defaultIfNull("value", valueSupplier);
            assertEquals("value", result);
        }

    @Test
        public void testGetFirstNonNullWithEmptySuppliersArray() {
            Supplier<String>[] emptySuppliers = new Supplier[0];
            String result = ObjectUtils.getFirstNonNull(emptySuppliers);
            assertNull(result);
        }

    @Test
        public void testGetFirstNonNullWithNullSuppliers_2() {
            Supplier<String> nullSupplier = () -> null;
            Supplier<String> valueSupplier = () -> "value";
            Supplier<String>[] suppliers = new Supplier[]{nullSupplier, valueSupplier};
            String result = ObjectUtils.getFirstNonNull(suppliers);
            assertEquals("value", result);
        }

    @Test
        public void testGetFirstNonNullWithNullObject() {
            Supplier<String> nullSupplier = () -> null;
            String result = ObjectUtils.getIfNull(null, nullSupplier);
            assertNull(result);
        }

    @Test
        public void testGetFirstNonNullWithValueObject() {
            Supplier<String> valueSupplier = () -> "value";
            String result = ObjectUtils.getIfNull("value", valueSupplier);
            assertEquals("value", result);
        }

    @Test
        public void testModeWithEmptyArray_2() {
            String[] emptyArray = new String[0];
            String result = ObjectUtils.mode(emptyArray);
            assertNull(result);
        }

    @Test
        public void testModeWithSingleElementArray_2() {
            String[] singleElementArray = {"single"};
            String result = ObjectUtils.mode(singleElementArray);
            assertEquals("single", result);
        }

    @Test
        public void testMedianWithEmptyArray_2() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median((String[]) null));
        }

    @Test
        public void testMedianWithSingleElementArray_2() {
            String[] singleElementArray = {"single"};
            String result = ObjectUtils.median(singleElementArray);
            assertEquals("single", result);
        }
}
