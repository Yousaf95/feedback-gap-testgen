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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testBetweenWithComparator() {
            Comparator<Integer> comparator = Integer::compare;
            Range<Integer> range = Range.between(1, 5, comparator);
            assertEquals(1, range.getMinimum());
            assertEquals(5, range.getMaximum());
            assertEquals(comparator, range.getComparator());
        }

    @Test
        public void testIsWithComparator() {
            Comparator<String> comparator = String::compareToIgnoreCase;
            Range<String> range = Range.is("test", comparator);
            assertEquals("test", range.getMinimum());
            assertEquals("test", range.getMaximum());
            assertEquals(comparator, range.getComparator());
        }

    @Test
        public void testOfWithNullComparator() {
            Range<Integer> range = Range.of(1, 5);
            assertEquals(1, range.getMinimum());
            assertEquals(5, range.getMaximum());
            assertNotNull(range.getComparator());
        }

    @Test
        public void testContainsRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(3, 4);
            assertTrue(range1.containsRange(range2));
        }

    @Test
        public void testIsAfter() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isAfter(0));
            assertFalse(range.isAfter(6));
        }

    @Test
        public void testIsBefore() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBefore(0));
            assertTrue(range.isBefore(6));
        }

    @Test
        public void testFit() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(1, (int) range.fit(0));
            assertEquals(5, (int) range.fit(6));
            assertEquals(3, (int) range.fit(3));
        }

    @Test
        public void testIntersectionWith() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(3, 7);
            Range<Integer> intersection = range1.intersectionWith(range2);
            assertEquals(3, (int) intersection.getMinimum());
            assertEquals(5, (int) intersection.getMaximum());
        }

    @Test
        public void testIsAfterRange() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 10);
            assertFalse(range1.isAfterRange(range2));
            assertTrue(range2.isAfterRange(range1));
        }

    
    @Test
        public void testIsEndedBy() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isEndedBy(5));
            assertFalse(range.isEndedBy(6));
        }

    @Test
        public void testIsStartedBy() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isStartedBy(1));
            assertFalse(range.isStartedBy(0));
        }

    @Test
        public void testContainsNullElement() {
            Range<String> range = Range.between("a", "b");
            assertFalse(range.contains(null));
        }

    @Test
        public void testIsAfterNullElement() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isAfter(null));
        }

    @Test
        public void testIsBeforeNullElement() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBefore(null));
        }

    @Test
        public void testFitNullElement() {
            Range<String> range = Range.between("a", "b");
            assertThrows(NullPointerException.class, () -> range.fit(null));
        }
}
