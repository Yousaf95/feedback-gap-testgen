package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testFirstNonNull() {
        String result = ObjectUtils.firstNonNull(null, "default");
        assertEquals("default", result);

        result = ObjectUtils.firstNonNull("value", null);
        assertEquals("value", result);

        result = ObjectUtils.firstNonNull(null, null, "default");
        assertEquals("default", result);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull((Object[]) null));
            assertTrue(ObjectUtils.allNull());
            assertFalse(ObjectUtils.allNull(1, 2, 3));
            assertFalse(ObjectUtils.allNull(1, null, 3));
            assertFalse(ObjectUtils.allNull(null, 2, 3));
        }

    @Test
        public void testAnyNotNull() {
            assertTrue(ObjectUtils.anyNotNull(1, null, 3));
            assertFalse(ObjectUtils.anyNotNull((Object[]) null));
            assertFalse(ObjectUtils.anyNotNull());
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
        public void testDefaultIfNull() {
            String value = "test";
            String defaultValue = "default";
            assertEquals(value, ObjectUtils.defaultIfNull(value, defaultValue));
            assertEquals(defaultValue, ObjectUtils.defaultIfNull(null, defaultValue));
        }

    
    @Test
        public void testGetClass() {
            String str = "test";
            Class<String> clazz = ObjectUtils.getClass(str);
            assertEquals(String.class, clazz);
    
            assertNull(ObjectUtils.getClass(null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReadResolve() {
            // This method is private and returns a singleton instance, so we can't directly test it.
            // However, we can ensure that the class has been loaded correctly.
            assertNotNull(ObjectUtils.NULL);
        }

    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, 2, 3));
            assertFalse(ObjectUtils.allNotNull(1, null, 3));
            assertTrue(ObjectUtils.allNotNull());
        }

    
    @Test
        public void testCONST_2() {
            assertEquals(true, ObjectUtils.CONST(true));
            assertEquals(false, ObjectUtils.CONST(false));
            assertEquals('a', ObjectUtils.CONST('a'));
            assertEquals((byte) 10, ObjectUtils.CONST_BYTE(10));
            assertEquals((short) 20, ObjectUtils.CONST_SHORT(20));
            assertEquals(3.14f, ObjectUtils.CONST(3.14f));
            assertEquals(2.718, ObjectUtils.CONST(2.718));
            assertEquals(1L, ObjectUtils.CONST(1L));
            assertEquals((short) 5, ObjectUtils.CONST((short) 5));
        }

    @Test
        public void testCONST_BYTE() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertEquals((byte) -128, ObjectUtils.CONST_BYTE(-128));
            assertEquals((byte) 0, ObjectUtils.CONST_BYTE(0));
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        public void testCONST_SHORT() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(-32769));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals((short) -32768, ObjectUtils.CONST_SHORT(-32768));
            assertEquals((short) 0, ObjectUtils.CONST_SHORT(0));
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    
    @Test
        public void testGetIfNull() {
            assertEquals("value", ObjectUtils.getIfNull("value", () -> "default"));
            assertEquals("default", ObjectUtils.getIfNull(null, () -> "default"));
        }
}
