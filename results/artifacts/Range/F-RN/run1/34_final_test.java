package org.apache.commons.lang3;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Function;

public class Range_GenTest {

    @Test
    public void testBetweenWithComparable() {
        Range<Integer> range = Range.between(1, 5);
        assertTrue(range.contains(3));
        assertFalse(range.contains(0));
        assertFalse(range.contains(6));
    }

    @Test
    public void testBetweenWithComparator() {
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Range<String> range = Range.between("a", "c", comparator);
        assertTrue(range.contains("A"));
        assertTrue(range.contains("b"));
        assertFalse(range.contains("d"));
    }

    @Test
    public void testIsWithComparable() {
        Range<Integer> range = Range.is(3);
        assertTrue(range.contains(3));
        assertFalse(range.contains(2));
        assertFalse(range.contains(4));
    }

    @Test
    public void testIsWithComparator() {
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Range<String> range = Range.is("a", comparator);
        assertTrue(range.contains("A"));
        assertFalse(range.contains("b"));
    }

    @Test
    public void testContainsRange() {
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(3, 4);
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
    public void testFit() {
        Range<Integer> range = Range.between(1, 5);
        assertEquals(2, (int) range.fit(2));
        assertEquals(1, (int) range.fit(0));
        assertEquals(5, (int) range.fit(6));
    }

    @Test
    public void testGetComparator() {
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Range<String> range = Range.between("a", "c", comparator);
        assertSame(comparator, range.getComparator());
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
    public void testIntersectionWith() {
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(3, 7);
        Range<Integer> intersection = range1.intersectionWith(range2);
        assertEquals(Range.between(3, 5), intersection);
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
        assertTrue(range.isBefore(6));
        assertFalse(range.isBefore(0));
    }

    @Test
    public void testIsEndedBy() {
        Range<Integer> range = Range.between(1, 5);
        assertTrue(range.isEndedBy(5));
        assertFalse(range.isEndedBy(4));
    }

    @Test
    public void testIsStartedBy() {
        Range<Integer> range = Range.between(1, 5);
        assertTrue(range.isStartedBy(1));
        assertFalse(range.isStartedBy(2));
    }

    
    @Test
    public void testToString() {
        Range<Integer> range = Range.between(1, 5);
        assertEquals("[1..5]", range.toString());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsOverlappedBy() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(4, 8);
            assertTrue(range1.isOverlappedBy(range2));
            assertFalse(range1.isOverlappedBy(Range.between(6, 9)));
        }

    @Test
        public void testIsNaturalOrdering() {
            Range<String> range = Range.between("apple", "orange");
            assertTrue(range.isNaturalOrdering());
        }

    @Test
        public void testGetComparatorWithCustomComparator() {
            Comparator<Integer> customComparator = (a, b) -> b - a;
            Range<Integer> range = Range.between(1, 5, customComparator);
            assertEquals(customComparator, range.getComparator());
        }

    @Test
        public void testContainsRangeWhenSubset() {
            Range<Integer> range1 = Range.between(1, 10);
            Range<Integer> range2 = Range.between(3, 7);
            assertTrue(range1.containsRange(range2));
        }

    @Test
        public void testContainsRangeWhenNotSubset() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(6, 9);
            assertFalse(range1.containsRange(range2));
        }

    @Test
        public void testElementCompareToWithEqualElements() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(0, range.elementCompareTo(5));
        }

    @Test
        public void testElementCompareToWithSmallerElement() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(-1, range.elementCompareTo(2));
        }

    @Test
        public void testElementCompareToWithLargerElement() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(1, range.elementCompareTo(8));
        }

    @Test
        public void testFitWhenInBounds() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(5, (int) range.fit(5));
        }

    @Test
        public void testFitWhenBelowRange() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(3, (int) range.fit(2));
        }

    @Test
        public void testFitWhenAboveRange() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(7, (int) range.fit(8));
        }

    @Test
        public void testToStringWithCustomFormat() {
            Range<String> range = Range.between("apple", "orange");
            String result = range.toString("%s to %s");
            assertTrue(result.contains("apple"));
            assertTrue(result.contains("orange"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testContainsRangeWhenEmptyRange() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.containsRange(Range.is(3)));
        }

    @Test
        public void testContainsRangeWhenFullRange() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.containsRange(Range.between(6, 10)));
        }

    @Test
        public void testIsOverlappedByWhenNoOverlap() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isOverlappedBy(Range.between(6, 10)));
        }

    @Test
        public void testIsOverlappedByWhenPartialOverlap() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isOverlappedBy(Range.between(4, 8)));
        }

    
    @Test
        public void testGetComparatorWithCustomComparator_2() {
            Comparator<Integer> customComparator = (a, b) -> b - a;
            Range<Integer> range = Range.between(1, 5, customComparator);
            assertEquals(customComparator, range.getComparator());
        }

    @Test
        public void testToStringWithNullFormat() {
            Range<String> range = Range.between("a", "b");
            assertThrows(NullPointerException.class, () -> range.toString((String) null));
        }

    @Test
        public void testToStringWithCustomFormat_2() {
            Range<Integer> range = Range.between(1, 5);
            String result = range.toString("%s to %s");
            assertEquals("1 to 5", result);
        }
}
