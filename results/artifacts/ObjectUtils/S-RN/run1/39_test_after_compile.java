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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

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
        public void testAllNull() {
            assertTrue(ObjectUtils.allNull(null, null, null));
            assertFalse(ObjectUtils.allNull(1, null, 3));
            assertTrue(ObjectUtils.allNull());
        }

    
    
    
    @Test
        public void testCloneIfPossible() {
            Integer original = 42;
            Integer cloned = ObjectUtils.cloneIfPossible(original);
            assertSame(Integer.valueOf(42), cloned);
    
            String[] array = {"a", "b", "c"};
            String[] clonedArray = ObjectUtils.cloneIfPossible(array);
            assertArrayEquals(array, clonedArray);
        }

    @Test
        public void testCompare_2() {
            assertEquals(0, ObjectUtils.compare(1, 1));
            assertTrue(ObjectUtils.compare(2, 1) > 0);
            assertTrue(ObjectUtils.compare(1, 2) < 0);
    
            assertEquals(0, ObjectUtils.compare("a", "a"));
            assertTrue(ObjectUtils.compare("b", "a") > 0);
            assertTrue(ObjectUtils.compare("a", "b") < 0);
    
            assertTrue(ObjectUtils.compare(null, null) == 0);
            assertTrue(ObjectUtils.compare(null, "a") < 0);
            assertTrue(ObjectUtils.compare("a", null) > 0);
        }

    @Test
        public void testCompareWithNullGreater() {
            assertEquals(0, ObjectUtils.compare(1, 1, true));
            assertTrue(ObjectUtils.compare(2, 1, true) > 0);
            assertTrue(ObjectUtils.compare(1, 2, true) < 0);
    
            assertEquals(0, ObjectUtils.compare("a", "a", true));
            assertTrue(ObjectUtils.compare("b", "a", true) > 0);
            assertTrue(ObjectUtils.compare("a", "b", true) < 0);
    
            assertTrue(ObjectUtils.compare(null, null, true) == 0);
            assertTrue(ObjectUtils.compare(null, "a", true) > 0);
            assertTrue(ObjectUtils.compare("a", null, true) < 0);
        }

    @Test
        public void testDefaultIfNull_2() {
            String result = ObjectUtils.defaultIfNull(null, "default");
            assertEquals("default", result);
    
            result = ObjectUtils.defaultIfNull("value", "default");
            assertEquals("value", result);
        }

    @Test
        public void testFirstNonNull() {
            Integer result = ObjectUtils.firstNonNull(1, null, 3);
            assertEquals(Integer.valueOf(1), result);
    
            result = ObjectUtils.firstNonNull(null, null, 3);
            assertEquals(Integer.valueOf(3), result);
    
            result = ObjectUtils.firstNonNull();
            assertNull(result);
        }

    @Test
        public void testGetFirstNonNull() {
            Integer result = ObjectUtils.getFirstNonNull(() -> 1, () -> null, () -> 3);
            assertEquals(Integer.valueOf(1), result);
    
            result = ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> 3);
            assertEquals(Integer.valueOf(3), result);
    
            result = ObjectUtils.getFirstNonNull();
            assertNull(result);
        }

    @Test
        public void testGetIfNull() {
            Integer result = ObjectUtils.getIfNull(null, () -> 1);
            assertEquals(Integer.valueOf(1), result);
    
            result = ObjectUtils.getIfNull(2, () -> 1);
            assertEquals(Integer.valueOf(2), result);
        }

    
    
    
    @Test
        public void testIsEmpty() {
            assertTrue(ObjectUtils.isEmpty(null));
            assertTrue(ObjectUtils.isEmpty(""));
            assertTrue(ObjectUtils.isEmpty(new ArrayList<>())); // empty collection
            assertTrue(ObjectUtils.isEmpty(new int[0])); // empty array
    
            assertFalse(ObjectUtils.isEmpty("test"));
            assertFalse(ObjectUtils.isEmpty(Arrays.asList(1, 2, 3)));
            assertFalse(ObjectUtils.isEmpty(new int[]{1, 2, 3}));
        }

    @Test
        public void testIsNotEmpty() {
            assertTrue(ObjectUtils.isNotEmpty("test"));
            assertTrue(ObjectUtils.isNotEmpty(Arrays.asList(1, 2, 3)));
            assertTrue(ObjectUtils.isNotEmpty(new int[]{1, 2, 3}));
    
            assertFalse(ObjectUtils.isNotEmpty(null));
            assertFalse(ObjectUtils.isNotEmpty(""));
            assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>())); // empty collection
            assertFalse(ObjectUtils.isNotEmpty(new int[0])); // empty array
        }

    @Test
        public void testMax() {
            Integer result = ObjectUtils.max(1, 2, 3);
            assertEquals(Integer.valueOf(3), result);
    
            String resultStr = ObjectUtils.max("a", "b", "c");
            assertEquals("c", resultStr);
        }

    @Test
        public void testMedian() {
            Double result = ObjectUtils.median(1.0, 2.0, 3.0);
            assertEquals(Double.valueOf(2.0), result);
    
            Integer[] array = {3, 1, 4, 1, 5};
            Integer resultArray = ObjectUtils.median(array);
            assertEquals(Integer.valueOf(3), resultArray);
        }

    @Test
        public void testMin() {
            Integer result = ObjectUtils.min(1, 2, 3);
            assertEquals(Integer.valueOf(1), result);
    
            String resultStr = ObjectUtils.min("a", "b", "c");
            assertEquals("a", resultStr);
        }

    @Test
        public void testMode() {
            Integer result = ObjectUtils.mode(1, 2, 2, 3);
            assertEquals(Integer.valueOf(2), result);
    
            String[] array = {"apple", "banana", "apple"};
            String resultArray = ObjectUtils.mode(array);
            assertEquals("apple", resultArray);
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
            String result = ObjectUtils.requireNonEmpty("test");
            assertEquals("test", result);
    
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
        }

    @Test
        public void testToStringWithSupplier() {
            String result = ObjectUtils.toString(() -> null, () -> "default");
            assertEquals("default", result);
        }

    
    @Test
        public void testMedianWithEmptyArray() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.median(new Integer[0]));
        }

    @Test
        public void testMedianWithNullElements() {
            assertThrows(NullPointerException.class, () -> ObjectUtils.median((Integer[]) null));
        }

    @Test
        public void testModeWithEmptyArray() {
            assertNull(ObjectUtils.mode());
        }

    @Test
        public void testModeWithSingleElement() {
            assertEquals("a", ObjectUtils.mode("a"));
        }

    @Test
        public void testModeWithMultipleElements() {
            String[] elements = {"a", "b", "a", "c"};
            assertEquals("a", ObjectUtils.mode(elements));
        }

    
    
    @Test
        public void testToStringWithSupplierNull() {
            Supplier<Object> supplier = null;
            assertEquals("Default", ObjectUtils.toString(supplier, () -> "Default"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testMaxWithNullValues() {
            Integer[] values = {1, null, 3};
            assertEquals(3, (int) ObjectUtils.max(values));
        }

    @Test
        public void testMinWithNullValues() {
            Integer[] values = {1, null, 3};
            assertEquals(1, (int) ObjectUtils.min(values));
        }

    @Test
        public void testMedianWithOddNumberOfElements() {
            Integer[] items = {5, 2, 8, 4, 7};
            assertEquals(5, (int) ObjectUtils.median(items));
        }

    @Test
        public void testMedianWithEvenNumberOfElements() {
            Integer[] items = {5, 2, 8, 4};
            assertEquals(4.5, ObjectUtils.median(items), 0.01);
        }

    @Test
        public void testModeWithNullValues() {
            Integer[] items = {1, null, 3, 1, null};
            assertEquals(1, (int) ObjectUtils.mode(items));
        }

    @Test
        public void testModeWithEqualFrequencies() {
            Integer[] items = {1, 2, 2, 3, 3};
            assertNull(ObjectUtils.mode(items));
        }

    @Test
        public void testRequireNonEmptyWithNull() {
            assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmpty(null));
        }

    @Test
        public void testRequireNonEmptyWithEmptyString() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(""));
        }

    @Test
        public void testRequireNonEmptyWithEmptyCollection() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(Collections.emptyList()));
        }

    @Test
        public void testRequireNonEmptyWithEmptyMap() {
            assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(new HashMap<>()));
        }
}
