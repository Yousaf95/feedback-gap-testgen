package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    @ParameterizedTest
    @MethodSource("provideAllNotNullValues")
    public void testAllNotNull(Object... values) {
        assertTrue(ObjectUtils.allNotNull(values));
    }

    private static Stream<Arguments> provideAllNotNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(1, 2, 3),
            arguments("a", "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnyNotNullValues")
    public void testAnyNotNull(Object... values) {
        assertTrue(ObjectUtils.anyNotNull(values));
    }

    private static Stream<Arguments> provideAnyNotNullValues() {
        return Stream.of(
            arguments(1, 2, null),
            arguments(null, "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAllNullValues")
    public void testAllNull(Object... values) {
        assertTrue(ObjectUtils.allNull(values));
    }

    private static Stream<Arguments> provideAllNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(null, null, null),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnyNullValues")
    public void testAnyNull(Object... values) {
        assertTrue(ObjectUtils.anyNull(values));
    }

    private static Stream<Arguments> provideAnyNullValues() {
        return Stream.of(
            arguments(1, null, 3),
            arguments(null, "b", null),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    
    
    @Test
    public void testCompare() {
        assertEquals(0, ObjectUtils.compare(1, 1));
        assertTrue(ObjectUtils.compare(2, 1) > 0);
        assertTrue(ObjectUtils.compare(1, 2) < 0);

        assertNull(ObjectUtils.compare(null, null));
        assertTrue(ObjectUtils.compare(null, 1) < 0);
        assertTrue(ObjectUtils.compare(1, null) > 0);
    }

    @Test
    public void testCompareNullGreater() {
        assertEquals(0, ObjectUtils.compare(1, 1, true));
        assertTrue(ObjectUtils.compare(2, 1, true) > 0);
        assertTrue(ObjectUtils.compare(1, 2, true) < 0);

        assertNull(ObjectUtils.compare(null, null, true));
        assertTrue(ObjectUtils.compare(null, 1, true) > 0);
        assertTrue(ObjectUtils.compare(1, null, true) < 0);
    }

    @Test
    public void testFirstNonNull() {
        assertEquals("a", ObjectUtils.firstNonNull("a", "b"));
        assertEquals("b", ObjectUtils.firstNonNull(null, "b"));
        assertNull(ObjectUtils.firstNonNull((String) null, (String) null));
    }

    @ParameterizedTest
    @MethodSource("provideGetFirstNonNullValues")
    public void testGetFirstNonNull(Supplier<String>... suppliers) {
        assertEquals("a", ObjectUtils.getFirstNonNull(suppliers[0], suppliers[1]));
        assertEquals("b", ObjectUtils.getFirstNonNull(suppliers[1], suppliers[2]));
        assertNull(ObjectUtils.getFirstNonNull(suppliers[3], suppliers[4]));
    }

    
    @Test
    public void testGetIfNull() {
        assertEquals("a", ObjectUtils.getIfNull("a", () -> "b"));
        assertEquals("b", ObjectUtils.getIfNull(null, () -> "b"));
    }

    @ParameterizedTest
    @MethodSource("provideHashCodeValues")
    public void testHashCode(Object object, int expectedHash) {
        assertEquals(expectedHash, ObjectUtils.hashCode(object));
    }

    private static Stream<Arguments> provideHashCodeValues() {
        return Stream.of(
            arguments(null, 0),
            arguments("a", "a".hashCode()),
            arguments(123, 123)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIdentityToStringValues")
    public void testIdentityToString(Object object, String expected) {
        assertEquals(expected, ObjectUtils.identityToString(object));
    }

    private static Stream<Arguments> provideIdentityToStringValues() {
        return Stream.of(
            arguments(null, null),
            arguments("a", "java.lang.String@12345678"),
            arguments(123, "int@12345678")
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayValues")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    private static Stream<Arguments> provideIsArrayValues() {
        return Stream.of(
            arguments(null, false),
            arguments(new int[]{1, 2, 3}, true),
            arguments("a", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsEmptyValues")
    public void testIsEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isEmpty(object));
    }

    
    @ParameterizedTest
    @MethodSource("provideIsNotEmptyValues")
    public void testIsNotEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isNotEmpty(object));
    }

    
    
    private static Stream<Arguments> provideMaxValues() {
        return Stream.of(
            arguments(1, 2, 3),
            arguments("c", "a", "b"),
            arguments(new Integer[]{3, 1, 4})
        );
    }

    
    private static Stream<Arguments> provideMinValues() {
        return Stream.of(
            arguments(1, 2, 3),
            arguments("a", "b", "c"),
            arguments(new Integer[]{3, 1, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("provideToStringValues")
    public void testToString(Object object, String expected) {
        assertEquals(expected, ObjectUtils.toString(object));
    }

    private static Stream<Arguments> provideToStringValues() {
        return Stream.of(
            arguments(null, ""),
            arguments("a", "a"),
            arguments(123, "123")
        );
    }

    @Test
    public void testWait() throws InterruptedException {
        Object obj = new Object();
        Duration duration = Duration.ofMillis(10);
        ObjectUtils.wait(obj, duration);
        // No assertion needed as the method should not throw an exception
    }

    
    }
