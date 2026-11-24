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

    @ParameterizedTest
    @MethodSource("provideAllNotNullData")
    public void testAllNotNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.allNotNull(values));
    }

    private static Stream<Arguments> provideAllNotNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, true},
            {new Object[]{null, 2, 3}, false},
            {new Object[]{}, true},
            {new Object[]{null}, false}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAllNullData")
    public void testAllNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.allNull(values));
    }

    private static Stream<Arguments> provideAllNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, false},
            {new Object[]{null, null, null}, true},
            {new Object[]{}, true},
            {new Object[]{null}, true}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAnyNotNullData")
    public void testAnyNotNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNotNull(values));
    }

    private static Stream<Arguments> provideAnyNotNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, true},
            {new Object[]{null, null, null}, false},
            {new Object[]{}, false},
            {new Object[]{null}, false}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideAnyNullData")
    public void testAnyNull(Object[] values, boolean expected) {
        assertEquals(expected, ObjectUtils.anyNull(values));
    }

    private static Stream<Arguments> provideAnyNullData() {
        return Arrays.stream(new Object[][]{
            {new Object[]{1, 2, 3}, false},
            {new Object[]{null, null, null}, true},
            {new Object[]{}, true},
            {new Object[]{null}, true}
        }).map(Arguments::arguments);
    }

    @Test
    public void testClone() {
        String original = "test";
        String clone = ObjectUtils.clone(original);
        assertEquals(original, clone);
        assertNotSame(original, clone);
    }

    @Test
    public void testCloneIfPossible() {
        String original = "test";
        String clone = ObjectUtils.cloneIfPossible(original);
        assertEquals(original, clone);
        assertNotSame(original, clone);

        Object nullObject = null;
        assertNull(ObjectUtils.cloneIfPossible(nullObject));
    }

    @ParameterizedTest
    @MethodSource("provideCompareData")
    public <T extends Comparable<? super T>> void testCompare(T c1, T c2, boolean nullGreater, int expected) {
        assertEquals(expected, ObjectUtils.compare(c1, c2, nullGreater));
    }

    private static Stream<Arguments> provideCompareData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), false, -1},
            {Integer.valueOf(2), Integer.valueOf(1), false, 1},
            {Integer.valueOf(1), Integer.valueOf(1), false, 0},
            {null, null, false, 0},
            {null, Integer.valueOf(1), false, -1},
            {Integer.valueOf(1), null, false, 1},
            {null, null, true, 0},
            {null, Integer.valueOf(1), true, 1},
            {Integer.valueOf(1), null, true, -1}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideDefaultIfNullData")
    public <T> void testDefaultIfNull(T object, T defaultValue, T expected) {
        assertEquals(expected, ObjectUtils.defaultIfNull(object, defaultValue));
    }

    private static Stream<Arguments> provideDefaultIfNullData() {
        return Arrays.stream(new Object[][]{
            {null, "default", "default"},
            {"value", "default", "value"}
        }).map(Arguments::arguments);
    }

    @Test
    public void testFirstNonNull() {
        String result = ObjectUtils.firstNonNull(null, "default");
        assertEquals("default", result);

        result = ObjectUtils.firstNonNull("value", null);
        assertEquals("value", result);

        result = ObjectUtils.firstNonNull(null, null, "default");
        assertEquals("default", result);
    }

    @ParameterizedTest
    @MethodSource("provideGetClassData")
    public <T> void testGetClass(T object, Class<?> expected) {
        assertEquals(expected, ObjectUtils.getClass(object));
    }

    private static Stream<Arguments> provideGetClassData() {
        return Arrays.stream(new Object[][]{
            {null, null},
            {"test", String.class},
            (Object) new Integer(1), Integer.class
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayData")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    private static Stream<Arguments> provideIsArrayData() {
        return Arrays.stream(new Object[][]{
            {null, false},
            {"test", false},
            (Object) new Integer[]{1, 2, 3}, true},
            (Object) new int[]{1, 2, 3}, true}
        ).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideIsEmptyData")
    public void testIsEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isEmpty(object));
    }

    private static Stream<Arguments> provideIsEmptyData() {
        return Arrays.stream(new Object[][]{
            {null, true},
            {"", true},
            {"test", false},
            (Object) new Integer[]{}, true},
            (Object) new int[]{1, 2, 3}, false}
        ).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideIsNotEmptyData")
    public void testIsNotEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isNotEmpty(object));
    }

    private static Stream<Arguments> provideIsNotEmptyData() {
        return Arrays.stream(new Object[][]{
            {null, false},
            {"", false},
            {"test", true},
            (Object) new Integer[]{}, false},
            (Object) new int[]{1, 2, 3}, true}
        ).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideMaxData")
    public <T extends Comparable<? super T>> void testMax(T... values) {
        assertEquals(ObjectUtils.max(values), Arrays.stream(values).max().orElse(null));
    }

    private static Stream<Arguments> provideMaxData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)}, Integer.valueOf(3),
            {Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(1)}, Integer.valueOf(3)
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideMedianData")
    public <T extends Comparable<? super T>> void testMedian(T... items) {
        assertEquals(ObjectUtils.median(items), Arrays.stream(items).sorted().skip((items.length - 1) / 2).findFirst().orElse(null));
    }

    private static Stream<Arguments> provideMedianData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)}, Integer.valueOf(2),
            {Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(1)}, Integer.valueOf(2)
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideMinData")
    public <T extends Comparable<? super T>> void testMin(T... values) {
        assertEquals(ObjectUtils.min(values), Arrays.stream(values).min().orElse(null));
    }

    private static Stream<Arguments> provideMinData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)}, Integer.valueOf(1),
            {Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(1)}, Integer.valueOf(1)
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideModeData")
    public <T> void testMode(T... items) {
        assertEquals(ObjectUtils.mode(items), Arrays.stream(items).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));
    }

    private static Stream<Arguments> provideModeData() {
        return Arrays.stream(new Object[][]{
            {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)}, null,
            {Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(2)}, Integer.valueOf(1)
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideNotEqualData")
    public void testNotEqual(Object object1, Object object2, boolean expected) {
        assertEquals(expected, ObjectUtils.notEqual(object1, object2));
    }

    private static Stream<Arguments> provideNotEqualData() {
        return Arrays.stream(new Object[][]{
            {null, null, false},
            {"test", "test", false},
            {"test", "other", true}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideRequireNonEmptyData")
    public <T> void testRequireNonEmpty(T obj, String message) {
        assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmpty(obj, message));
    }

    private static Stream<Arguments> provideRequireNonEmptyData() {
        return Arrays.stream(new Object[][]{
            {null, "object"},
            {"", "object"}
        }).map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("provideToStringData")
    public void testToString(Object obj, String nullStr, String expected) {
        assertEquals(expected, ObjectUtils.toString(obj, nullStr));
    }

    private static Stream<Arguments> provideToStringData() {
        return Arrays.stream(new Object[][]{
            {null, "default", "default"},
            {"test", "default", "test"}
        }).map(Arguments::arguments);
    }

    @Test
    public void testWait() throws InterruptedException {
        Object obj = new Object();
        Duration duration = Duration.ofMillis(100);
        ObjectUtils.wait(obj, duration);
        assertTrue(duration.toNanos() <= System.nanoTime() - obj.hashCode());
    }
}
