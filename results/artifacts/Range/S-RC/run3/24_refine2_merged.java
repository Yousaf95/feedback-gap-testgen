package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

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

    
    
    
    @Test
        public void testIsWithNonNullComparator() {
            Comparator<String> comparator = Comparator.reverseOrder();
            Range<String> range = Range.is("a", comparator);
            assertNotNull(range);
            assertEquals("a", range.getMinimum());
            assertEquals("a", range.getMaximum());
            assertSame(comparator, range.getComparator());
        }

    
    
    @Test
        public void testContainsWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.contains(null));
        }

    @Test
        public void testContainsRangeWithNullOtherRange() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.containsRange(null));
        }

    @Test
        public void testElementCompareToWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertThrows(NullPointerException.class, () -> range.elementCompareTo(null));
        }

    @Test
        public void testEqualsWithNullObject() {
            Range<String> range = Range.is("a");
            assertFalse(range.equals(null));
        }

    @Test
        public void testEqualsWithDifferentClass() {
            Range<String> range = Range.is("a");
            assertFalse(range.equals(new Object()));
        }

    @Test
        public void testFitWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertThrows(NullPointerException.class, () -> range.fit(null));
        }

    @Test
        public void testIsAfterWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isAfter(null));
        }

    @Test
        public void testIsBeforeWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isBefore(null));
        }

    @Test
        public void testIsEndedByWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isEndedBy(null));
        }

    @Test
        public void testIntersectionWithNonOverlappingRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 10);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsAfterRangeWithNullOtherRange() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isAfterRange(null));
        }

    @Test
        public void testIsBeforeRangeWithNullOtherRange() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isBeforeRange(null));
        }

    @Test
        public void testIsStartedByWithNullElement() {
            Range<Integer> range = Range.between(1, 10);
            assertFalse(range.isStartedBy(null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testBetweenWithNullComparator() {
            Range<String> range = Range.between("a", "b");
            assertNotNull(range);
        }

    @Test
        public void testOfWithNullComparator() {
            Range<String> range = Range.of("a", "b");
            assertNotNull(range);
        }

    @Test
        public void testContainsWithNonNullElement() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.contains(3));
        }

    @Test
        public void testContainsRangeWithNullOtherRange_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.containsRange(null));
        }

    @Test
        public void testElementCompareToWithNonNullElement() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(-1, range.elementCompareTo(0));
        }

    @Test
        public void testEqualsWithNullObject_2() {
            Range<String> range = Range.between("a", "b");
            assertFalse(range.equals(null));
        }

    @Test
        public void testEqualsWithDifferentClass_2() {
            Range<String> range = Range.between("a", "b");
            assertFalse(range.equals(new Object()));
        }

    @Test
        public void testFitWithNullElement_2() {
            Range<Integer> range = Range.between(1, 5);
            assertThrows(NullPointerException.class, () -> range.fit(null));
        }

    @Test
        public void testIsAfterWithNullElement_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isAfter(null));
        }

    @Test
        public void testIsBeforeWithNullElement_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBefore(null));
        }

    @Test
        public void testIsEndedByWithNullElement_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isEndedBy(null));
        }

    @Test
        public void testIntersectionWithNonOverlappingRange_2() {
            Range<Integer> range1 = Range.between(1, 3);
            Range<Integer> range2 = Range.between(4, 6);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsAfterRangeWithNullOtherRange_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isAfterRange(null));
        }

    @Test
        public void testIsBeforeRangeWithNullOtherRange_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBeforeRange(null));
        }

    @Test
        public void testIsStartedByWithNullElement_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isStartedBy(null));
        }
}
