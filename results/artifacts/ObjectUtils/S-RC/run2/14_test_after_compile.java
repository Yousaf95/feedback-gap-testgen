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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, 2, 3));
            assertFalse(ObjectUtils.allNotNull(1, null, 3));
            assertFalse(ObjectUtils.allNotNull(null, 2, 3));
            assertFalse(ObjectUtils.allNotNull());
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
        public void testAnyNull() {
            assertTrue(ObjectUtils.anyNull(1, null, 3));
            assertFalse(ObjectUtils.anyNull());
            assertFalse(ObjectUtils.anyNull(1, 2, 3));
            assertFalse(ObjectUtils.anyNull((Object[]) null));
        }

    @Test
        public void testClone() {
            String original = "test";
            String clone = ObjectUtils.clone(original);
            assertEquals(original, clone);
    
            Integer[] array = {1, 2, 3};
            Integer[] clonedArray = ObjectUtils.clone(array);
            assertArrayEquals(array, clonedArray);
    
            Cloneable obj = new Cloneable() {};
            Cloneable cloneObj = ObjectUtils.clone(obj);
            assertNotNull(cloneObj);
    
            try {
                ObjectUtils.clone(null);
                fail("Expected NullPointerException");
            } catch (NullPointerException e) {
                // Expected
            }
        }

    @Test
        public void testCloneIfPossible() {
            String original = "test";
            String clone = ObjectUtils.cloneIfPossible(original);
            assertEquals(original, clone);
    
            Integer[] array = {1, 2, 3};
            Integer[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertArrayEquals(array, clonedArray);
    
            Cloneable obj = new Cloneable() {};
            Cloneable cloneObj = ObjectUtils.cloneIfPossible(obj);
            assertNotNull(cloneObj);
    
            Object nullObject = null;
            assertNull(ObjectUtils.cloneIfPossible(nullObject));
        }

    @Test
        public void testCompare() {
            assertEquals(0, ObjectUtils.compare(1, 1));
            assertEquals(-1, ObjectUtils.compare(1, 2));
            assertEquals(1, ObjectUtils.compare(2, 1));
    
            assertTrue(ObjectUtils.compare(1, 2, true) > 0);
            assertTrue(ObjectUtils.compare(2, 1, true) < 0);
    
            assertNull(ObjectUtils.compare(null, null));
            assertEquals(-1, ObjectUtils.compare(null, 1));
            assertEquals(1, ObjectUtils.compare(1, null));
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
        public void testGetFirstNonNull() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "test";
            assertEquals("test", ObjectUtils.getFirstNonNull(supplier1, supplier2));
    
            Supplier<Integer> supplier3 = () -> 42;
            assertNull(ObjectUtils.getFirstNonNull(supplier3));
        }

    @Test
        public void testGetClass() {
            String str = "test";
            Class<String> clazz = ObjectUtils.getClass(str);
            assertEquals(String.class, clazz);
    
            assertNull(ObjectUtils.getClass(null));
        }
}
