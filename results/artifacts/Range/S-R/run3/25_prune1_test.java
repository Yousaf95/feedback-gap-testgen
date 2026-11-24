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
    public <T extends Comparable<? super T>> void testRangeCreation(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        assertEquals(from, range.getMinimum());
        assertEquals(to, range.getMaximum());
        if (comparator == null) {
            assertTrue(range.isNaturalOrdering());
        } else {
            assertNotSame(Comparator.naturalOrder(), range.getComparator());
        }
    }

    
    
    
    
    @ParameterizedTest
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testGetComparator(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        if (comparator == null) {
            assertTrue(range.isNaturalOrdering());
        } else {
            assertNotSame(Comparator.naturalOrder(), range.getComparator());
        }
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testGetMaximum(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        assertEquals(to, range.getMaximum());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testGetMinimum(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        assertEquals(from, range.getMinimum());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testHashCode(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        int hashCode = 17;
        hashCode = 37 * hashCode + getClass().hashCode();
        hashCode = 37 * hashCode + range.getMinimum().hashCode();
        hashCode = 37 * hashCode + range.getMaximum().hashCode();
        assertEquals(hashCode, range.hashCode());
    }

    
    
    
    @ParameterizedTest
    @MethodSource("provideRangeCreationData")
    public <T extends Comparable<? super T>> void testIsStartedBy(T from, T to, Comparator<T> comparator) {
        Range<T> range = Range.of(from, to, comparator);
        assertTrue(range.isStartedBy(from));
        assertFalse(range.isStartedBy(to));
    }

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
