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
import java.util.Arrays;

public class ObjectUtils_GenTest {

    
    private static Stream<Arguments> provideAllNotNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(1, 2, 3),
            arguments("a", "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    
    private static Stream<Arguments> provideAnyNotNullValues() {
        return Stream.of(
            arguments(1, 2, null),
            arguments(null, "b", "c"),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    
    private static Stream<Arguments> provideAllNullValues() {
        return Stream.of(
            arguments((Object[]) new Object[0]),
            arguments(null, null, null),
            arguments(new HashMap<>(), new ArrayList<>())
        );
    }

    
    private static Stream<Arguments> provideAnyNullValues() {
        return Stream.of(
            arguments(1, null, 3),
            arguments(null, "b", null),
            arguments(new HashMap<>(), new ArrayList<>(), null)
        );
    }

    
    
    
    
    @Test
    public void testFirstNonNull() {
        assertEquals("a", ObjectUtils.firstNonNull("a", "b"));
        assertEquals("b", ObjectUtils.firstNonNull(null, "b"));
        assertNull(ObjectUtils.firstNonNull((String) null, (String) null));
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

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetFirstNonNull() {
            Supplier<String> supplier1 = () -> null;
            Supplier<String> supplier2 = () -> "value";
            String result = ObjectUtils.getFirstNonNull(supplier1, supplier2);
            assertEquals("value", result);
    
            Supplier<Integer> supplier3 = () -> 10;
            Integer intResult = ObjectUtils.getFirstNonNull(supplier1, supplier3);
            assertEquals(10, intResult);
        }

    @Test
        public void testMedian() {
            List<Double> doubleList = Arrays.asList(1.0, 2.0, 3.0);
            Double median = ObjectUtils.median(doubleList.toArray(new Double[0]));
            assertEquals(2.0, median);
    
            List<Integer> integerList = Arrays.asList(3, 1, 2);
            Integer intMedian = ObjectUtils.median(integerList.toArray(new Integer[0]));
            assertEquals(2, intMedian);
        }
}
