package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Range_GenTest {

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testRangeCreation(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(fromInclusive, range.getMinimum());
        assertEquals(toInclusive, range.getMaximum());
        assertEquals(comparator, range.getComparator());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testContains(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.contains(fromInclusive));
        assertTrue(range.contains(toInclusive));
        assertFalse(range.contains((T) null));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testContainsRange(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.containsRange(Range.between(fromInclusive, toInclusive, comparator)));
        assertFalse(range.containsRange(Range.between((T) null, (T) null, comparator)));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testElementCompareTo(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(0, range.elementCompareTo(fromInclusive));
        assertEquals(0, range.elementCompareTo(toInclusive));
        assertTrue(range.elementCompareTo((T) null) < 0);
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testFit(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(fromInclusive, range.fit((T) null));
        assertEquals(fromInclusive, range.fit(fromInclusive));
        assertEquals(toInclusive, range.fit(toInclusive));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testGetComparator(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(comparator, range.getComparator());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testGetMaximum(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(toInclusive, range.getMaximum());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testGetMinimum(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(fromInclusive, range.getMinimum());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testHashCode(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        int hashCode = 17;
        hashCode = 37 * hashCode + getClass().hashCode();
        hashCode = 37 * hashCode + range.getMinimum().hashCode();
        hashCode = 37 * hashCode + range.getMaximum().hashCode();
        assertEquals(hashCode, range.hashCode());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIntersectionWith(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        Range<T> otherRange = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(range, range.intersectionWith(otherRange));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsAfter(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.isAfter((T) null));
        assertFalse(range.isAfter(fromInclusive));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsBefore(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertFalse(range.isBefore((T) null));
        assertTrue(range.isBefore(toInclusive));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsEndedBy(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.isEndedBy(toInclusive));
        assertFalse(range.isEndedBy((T) null));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsNaturalOrdering(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(comparator == null || comparator instanceof ComparableComparator, range.isNaturalOrdering());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsOverlappedBy(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.isOverlappedBy(Range.between(fromInclusive, toInclusive, comparator)));
        assertFalse(range.isOverlappedBy(Range.between((T) null, (T) null, comparator)));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testIsStartedBy(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertTrue(range.isStartedBy(fromInclusive));
        assertFalse(range.isStartedBy((T) null));
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testToString(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals("[" + fromInclusive + ".." + toInclusive + "]", range.toString());
    }

    @ParameterizedTest
    @MethodSource("provideRangeCreationParameters")
    public <T extends Comparable<? super T>> void testToStringWithFormat(T fromInclusive, T toInclusive, Comparator<T> comparator) {
        Range<T> range = Range.between(fromInclusive, toInclusive, comparator);
        assertEquals(String.format("[%s..%s]", fromInclusive, toInclusive), range.toString("%s"));
    }

    public static Stream<Arguments> provideRangeCreationParameters() {
        return Stream.of(
            arguments(1, 5, null),
            arguments("a", "z", Comparator.naturalOrder()),
            arguments(new Integer[]{3}, new Integer[]{7}, Comparator.comparingInt(Integer::intValue))
        );
    }
}
