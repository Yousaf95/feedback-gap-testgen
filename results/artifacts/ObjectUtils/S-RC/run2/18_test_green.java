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
}
