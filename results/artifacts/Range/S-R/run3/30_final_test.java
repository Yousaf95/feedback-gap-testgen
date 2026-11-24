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
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testToString(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        assertEquals("[" + range.getMinimum() + ".." + range.getMaximum() + "]", range.toString());
    }

    private static Stream<Arguments> provideRangeCreationData() {
        return Stream.of(
            arguments(1, 5, null),
            arguments(10.5, 20.5, Comparator.naturalOrder()),
            arguments("a", "z", Comparator.reverseOrder())
        );
    }
}
