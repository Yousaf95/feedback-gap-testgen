package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    
    private static Stream<Arguments> provideAllNotNullCases() {
        return Stream.of(
            of(1, 2, 3),
            of("a", "b", "c"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideAnyNotNullCases() {
        return Stream.of(
            of(1, null, 3),
            of(null, "b", null),
            of(true, false, null)
        );
    }

    
    private static Stream<Arguments> provideAllNullCases() {
        return Stream.of(
            of(null, null, null),
            of("", "", ""),
            of(false, false, false)
        );
    }

    
    private static Stream<Arguments> provideAnyNullCases() {
        return Stream.of(
            of(1, null, 3),
            of(null, "b", null),
            of(true, false, null)
        );
    }

    
    
    @ParameterizedTest
    @MethodSource("provideCompareCases")
    public <T extends Comparable<? super T>> void testCompare(T c1, T c2, int expected) {
        assertEquals(expected, ObjectUtils.compare(c1, c2));
    }

    private static Stream<Arguments> provideCompareCases() {
        return Stream.of(
            of(1, 2, -1),
            of("a", "b", -1),
            of(true, false, 1)
        );
    }

    
    private static Stream<Arguments> provideFirstNonNullCases() {
        return Stream.of(
            of(1, null, 3),
            of("a", "b", null),
            of(true, false, true)
        );
    }

    
    
    
    
    
    private static Stream<Arguments> provideHashCodeHexCases() {
        return Stream.of(
            of(1),
            of("a"),
            of(true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIdentityToStringCases")
    public void testIdentityToString(Object object) {
        assertEquals(object.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(object)), ObjectUtils.identityToString(object));
    }

    private static Stream<Arguments> provideIdentityToStringCases() {
        return Stream.of(
            of(1),
            of("a"),
            of(true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsArrayCases")
    public void testIsArray(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isArray(object));
    }

    private static Stream<Arguments> provideIsArrayCases() {
        return Stream.of(
            of(1, false),
            of(new int[]{1, 2, 3}, true),
            of("a", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsEmptyCases")
    public void testIsEmpty(Object object, boolean expected) {
        assertEquals(expected, ObjectUtils.isEmpty(object));
    }

    private static Stream<Arguments> provideIsEmptyCases() {
        return Stream.of(
            of(1, false),
            of("", true),
            of(new int[]{}, true),
            of(Collections.emptyList(), true),
            of(Optional.empty(), true)
        );
    }

    
    private static Stream<Arguments> provideIsNotEmptyCases() {
        return Stream.of(
            of(1, true),
            of("", false),
            of(new int[]{1, 2, 3}, false),
            of(Collections.singletonList("a"), false),
            of(Optional.of(1), false)
        );
    }

    
    private static Stream<Arguments> provideMaxCases() {
        return Stream.of(
            of(1, 3, 2),
            of("a", "c", "b"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideMinCases() {
        return Stream.of(
            of(1, 3, 2),
            of("a", "c", "b"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideModeCases() {
        return Stream.of(
            of(1, 2, 2, 3),
            of("a", "b", "b", "c"),
            of(true, false, true)
        );
    }

    
    private static Stream<Arguments> provideNotEqualCases() {
        return Stream.of(
            of(1, 2),
            of("a", "b"),
            of(true, false)
        );
    }
}
