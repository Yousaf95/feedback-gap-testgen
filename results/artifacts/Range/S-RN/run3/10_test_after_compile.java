package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsElement() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.contains(3));
            assertFalse(range.contains(0));
            assertFalse(range.contains(6));
        }

    @Test
        public void testContainsRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(2, 4);
            assertTrue(range1.containsRange(range2));
            assertFalse(range2.containsRange(range1));
        }

    @Test
        public void testElementCompareTo() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(-1, range.elementCompareTo(0));
            assertEquals(0, range.elementCompareTo(3));
            assertEquals(1, range.elementCompareTo(6));
        }

    @Test
        public void testFitElement() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(1, (int) range.fit(0));
            assertEquals(3, (int) range.fit(3));
            assertEquals(5, (int) range.fit(6));
        }

    @Test
        public void testGetComparator() {
            Range<Integer> range = Range.between(1, 5);
            assertNotNull(range.getComparator());
        }

    @Test
        public void testGetMaximum() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(5, (int) range.getMaximum());
        }

    @Test
        public void testGetMinimum() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(1, (int) range.getMinimum());
        }

    @Test
        public void testIntersectionWithRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(3, 7);
            Range<Integer> intersection = range1.intersectionWith(range2);
            assertEquals(Range.between(3, 5), intersection);
        }

    @Test
        public void testIntersectionWithNonOverlappingRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 10);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsAfterElement() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isAfter(0));
            assertFalse(range.isAfter(3));
            assertFalse(range.isAfter(6));
        }

    @Test
        public void testIsAfterRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 10);
            assertTrue(range1.isAfterRange(range2));
            assertFalse(range2.isAfterRange(range1));
        }

    @Test
        public void testIsBeforeElement() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBefore(0));
            assertTrue(range.isBefore(3));
            assertTrue(range.isBefore(6));
        }

    @Test
        public void testIsBeforeRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 10);
            assertFalse(range1.isBeforeRange(range2));
            assertTrue(range2.isBeforeRange(range1));
        }

    @Test
        public void testIsEndedByElement() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isEndedBy(0));
            assertTrue(range.isEndedBy(5));
            assertFalse(range.isEndedBy(6));
        }

    @Test
        public void testIsOverlappedByRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(3, 7);
            assertTrue(range1.isOverlappedBy(range2));
            assertFalse(range2.isOverlappedBy(range1));
        }

    @Test
        public void testIsStartedByElement() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isStartedBy(1));
            assertFalse(range.isStartedBy(3));
            assertFalse(range.isStartedBy(6));
        }
}
