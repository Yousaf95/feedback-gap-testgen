package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Arrays;

public class ObjectUtils_GenTest {

    
    
    
    
    
    
    @Test
    public void testCompare() {
        assertEquals(0, ObjectUtils.compare(1, 1));
        assertTrue(ObjectUtils.compare(2, 1) > 0);
        assertTrue(ObjectUtils.compare(1, 2) < 0);

        String str1 = "a";
        String str2 = "b";
        assertTrue(ObjectUtils.compare(str1, str2) < 0);
        assertTrue(ObjectUtils.compare(str2, str1) > 0);
    }

    
    @Test
    public void testDefaultIfNull() {
        Integer defaultValue = 42;
        assertEquals(defaultValue, ObjectUtils.defaultIfNull(null, defaultValue));
        assertEquals(1, ObjectUtils.defaultIfNull(1, defaultValue));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
        public void testIsEmpty() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
            assertFalse(ObjectUtils.isEmpty(Collections.singletonList(1)));
        }

    @Test
        public void testIsNotEmpty() {
            assertTrue(ObjectUtils.isNotEmpty(Collections.singletonList(1)));
            assertFalse(ObjectUtils.isNotEmpty(Collections.emptyList()));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReadResolve() {
            Null nullObject = new Null();
            assertSame(nullObject, nullObject.readResolve());
        }

    @Test
        public void testAllNotNull() {
            assertTrue(ObjectUtils.allNotNull(1, "test", 3.14));
            assertFalse(ObjectUtils.allNotNull(1, null, 3.14));
            assertFalse(ObjectUtils.allNotNull((Object[]) null));
        }

    @Test
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull(null, null, null));
            assertFalse(ObjectUtils.allNull(1, "test", 3.14));
            assertFalse(ObjectUtils.allNull());
        }

    @Test
        public void testAnyNotNull() {
            assertTrue(ObjectUtils.anyNotNull(1, null, 3.14));
            assertFalse(ObjectUtils.anyNotNull(null, null, null));
            assertFalse(ObjectUtils.anyNotNull((Object[]) null));
        }

    @Test
        public void testAnyNull() {
            assertTrue(ObjectUtils.anyNull(1, null, 3.14));
            assertFalse(ObjectUtils.anyNull(1, "test", 3.14));
            assertFalse(ObjectUtils.anyNull());
        }

    @Test
        public void testClone() {
            Integer original = 123;
            Integer cloned = ObjectUtils.clone(original);
            assertSame(Integer.class, cloned.getClass());
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.clone(array);
            assertSame(String[].class, clonedArray.getClass());
            assertArrayEquals(array, clonedArray);
    
            Object nonCloneable = new Object();
            assertNull(ObjectUtils.clone(nonCloneable));
        }

    @Test
        public void testCloneIfPossible() {
            Integer original = 123;
            Integer cloned = ObjectUtils.cloneIfPossible(original);
            assertSame(Integer.class, cloned.getClass());
            assertEquals(original, cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertSame(String[].class, clonedArray.getClass());
            assertArrayEquals(array, clonedArray);
    
            Object nonCloneable = new Object();
            assertSame(nonCloneable, ObjectUtils.cloneIfPossible(nonCloneable));
        }

    @Test
        public void testCompare_2() {
            assertEquals(0, ObjectUtils.compare("a", "a"));
            assertTrue(ObjectUtils.compare("b", "a") > 0);
            assertTrue(ObjectUtils.compare("a", "b") < 0);
    
            assertTrue(ObjectUtils.compare(null, null) == 0);
            assertTrue(ObjectUtils.compare(null, "a") < 0);
            assertTrue(ObjectUtils.compare("a", null) > 0);
    
            assertTrue(ObjectUtils.compare("a", "a", true) == 0);
            assertTrue(ObjectUtils.compare("b", "a", true) > 0);
            assertTrue(ObjectUtils.compare("a", "b", true) < 0);
    
            assertTrue(ObjectUtils.compare(null, null, true) == 0);
            assertTrue(ObjectUtils.compare(null, "a", true) < 0);
            assertTrue(ObjectUtils.compare("a", null, true) > 0);
        }

    @Test
        public void testCONST() {
            assertEquals(true, ObjectUtils.CONST(true));
            assertEquals((byte) 123, ObjectUtils.CONST_BYTE(123));
            assertEquals((short) 123, ObjectUtils.CONST_SHORT(123));
            assertEquals('a', ObjectUtils.CONST('a'));
            assertEquals(1.0, ObjectUtils.CONST(1.0), 0.0);
            assertEquals(1f, ObjectUtils.CONST(1f), 0.0);
            assertEquals(123, ObjectUtils.CONST(123));
            assertEquals((short) 123, ObjectUtils.CONST((short) 123));
        }

    @Test
        public void testCONST_BYTE() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(-129));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_BYTE(128));
            assertEquals((byte) -128, ObjectUtils.CONST_BYTE(-128));
            assertEquals((byte) 127, ObjectUtils.CONST_BYTE(127));
        }

    @Test
        public void testCONST_SHORT() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(-32769));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.CONST_SHORT(32768));
            assertEquals((short) -32768, ObjectUtils.CONST_SHORT(-32768));
            assertEquals((short) 32767, ObjectUtils.CONST_SHORT(32767));
        }

    @Test
        public void testFirstNonNull() {
            assertEquals("a", ObjectUtils.firstNonNull("a", "b"));
            assertEquals("b", ObjectUtils.firstNonNull(null, "b"));
            assertNull(ObjectUtils.firstNonNull((String) null, (String) null));
        }

    @Test
        public void testGetClass() {
            assertEquals(String.class, ObjectUtils.getClass("test"));
            assertNull(ObjectUtils.getClass(null));
        }

    @Test
        public void testGetFirstNonNull() {
            assertEquals("a", ObjectUtils.getFirstNonNull(() -> "a", () -> "b"));
            assertEquals("b", ObjectUtils.getFirstNonNull(() -> null, () -> "b"));
            assertNull(ObjectUtils.getFirstNonNull((Supplier<String>) null, () -> "b"));
        }

    @Test
        public void testGetIfNull() {
            assertEquals("a", ObjectUtils.getIfNull("a", () -> "b"));
            assertEquals("b", ObjectUtils.getIfNull(null, () -> "b"));
            assertNull(ObjectUtils.getIfNull((String) null, (Supplier<String>) null));
        }

    @Test
        public void testHashCode() {
            assertEquals(0, ObjectUtils.hashCode(null));
            assertEquals(Integer.hashCode(123), ObjectUtils.hashCode(123));
        }

    @Test
        public void testHashCodeHex() {
            assertEquals("0", ObjectUtils.hashCodeHex(null));
            assertEquals(Integer.toHexString(Integer.hashCode(123)), ObjectUtils.hashCodeHex(123));
        }

    @Test
        public void testIdentityToString() throws IOException {
            Appendable appendable = new StringBuilder();
            ObjectUtils.identityToString(appendable, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), appendable.toString());
    
            StrBuilder builder = new StrBuilder();
            ObjectUtils.identityToString(builder, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), builder.toString());
    
            StringBuffer buffer = new StringBuffer();
            ObjectUtils.identityToString(buffer, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), buffer.toString());
    
            StringBuilder stringBuilder = new StringBuilder();
            ObjectUtils.identityToString(stringBuilder, "test");
            assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode("test")), stringBuilder.toString());
        }

    @Test
        public void testIsArray() {
            assertTrue(ObjectUtils.isArray(new int[]{}));
            assertFalse(ObjectUtils.isArray(123));
            assertFalse(ObjectUtils.isArray(null));
        }

    @Test
        public void testIsEmpty_2() {
            assertTrue(ObjectUtils.isEmpty(""));
            assertTrue(ObjectUtils.isEmpty(" "));
            assertTrue(ObjectUtils.isEmpty(new ArrayList<>())); 
            assertTrue(ObjectUtils.isEmpty(new HashMap<>())); 
            assertTrue(ObjectUtils.isEmpty((Object) null));
    
            assertFalse(ObjectUtils.isEmpty("test"));
            assertFalse(ObjectUtils.isEmpty(new int[]{1, 2, 3}));
        }

    @Test
        public void testIsNotEmpty_2() {
            assertTrue(ObjectUtils.isNotEmpty("test"));
            assertTrue(ObjectUtils.isNotEmpty(new int[]{1, 2, 3}));
    
            assertFalse(ObjectUtils.isNotEmpty(""));
            assertFalse(ObjectUtils.isNotEmpty(" "));
            assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>())); 
            assertFalse(ObjectUtils.isNotEmpty(new HashMap<>())); 
            assertFalse(ObjectUtils.isNotEmpty((Object) null));
        }

    @Test
        public void testMax() {
            assertEquals(3.14, ObjectUtils.max(1.0, 2.0, 3.14), 0.0);
            assertNull(ObjectUtils.max());
        }

    @Test
        public void testMedian() {
            assertEquals(2.0, ObjectUtils.median(1.0, 2.0, 3.0), 0.0);
            assertEquals(2.0, ObjectUtils.median(Arrays.asList(1.0, 2.0, 3.0)), 0.0);
        }

    @Test
        public void testMin() {
            assertEquals(1.0, ObjectUtils.min(1.0, 2.0, 3.0), 0.0);
            assertNull(ObjectUtils.min());
        }

    @Test
        public void testMode() {
            assertEquals("b", ObjectUtils.mode("a", "b", "b", "c"));
            assertNull(ObjectUtils.mode());
        }

    @Test
        public void testNotEqual() {
            assertTrue(ObjectUtils.notEqual(1, 2));
            assertFalse(ObjectUtils.notEqual(1, 1));
    
            assertTrue(ObjectUtils.notEqual("a", "b"));
            assertFalse(ObjectUtils.notEqual("a", "a"));
    
            assertTrue(ObjectUtils.notEqual(null, "a"));
            assertTrue(ObjectUtils.notEqual("a", null));
        }

    @Test
        public void testRequireNonEmpty() {
            assertEquals("test", ObjectUtils.requireNonEmpty("test"));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty((String) null));
        }
}
