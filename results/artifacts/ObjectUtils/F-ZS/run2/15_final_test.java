package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

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

    
    
    
    
    }
