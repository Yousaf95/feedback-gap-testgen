package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Range_GenTest {

    @ParameterizedTest
    @MethodSource("provideRangeData")
    public <T extends Comparable<? super T>> void testContains(T element, Range<T> range, boolean expected) {
        assertEquals(expected, range.contains(element));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static Stream<Arguments> provideRangeData() {
        return Stream.of(
            arguments(5, Range.between(1, 10), true),
            arguments(0, Range.between(1, 10), false),
            arguments(10, Range.between(1, 10), true),
            arguments(null, Range.between(1, 10), false),
            arguments(5, Range.is(5), true),
            arguments(4, Range.is(5), false),
            arguments(6, Range.is(5), false),
            arguments("a", Range.between("a", "z"), true),
            arguments("b", Range.between("a", "z"), true),
            arguments("A", Range.between("a", "z"), false),
            arguments(null, Range.between("a", "z"), false)
        );
    }
}
