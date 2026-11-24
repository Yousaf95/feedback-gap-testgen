package org.apache.commons.lang3;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Function;

public class Range_GenTest {

    @Test
    public void testBetweenWithComparable() {
        Range<Integer> range = Range.between(1, 5);
        assertTrue(range.contains(1));
        assertTrue(range.contains(5));
        assertFalse(range.contains(0));
        assertFalse(range.contains(6));
    }

    
    @Test
    public void testIsWithComparable() {
        Range<Integer> range = Range.is(3);
        assertTrue(range.contains(3));
        assertFalse(range.contains(2));
        assertFalse(range.contains(4));
    }

    @Test
    public void testContains() {
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
    public void testFit() {
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
    public void testHashCode() {
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(1, 5);
        assertEquals(range1.hashCode(), range2.hashCode());
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
        assertFalse(range.isAfter(5));
    }

    @Test
    public void testIsBefore() {
        Range<Integer> range = Range.between(1, 5);
        assertFalse(range.isBefore(0));
        assertTrue(range.isBefore(6));
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
        public void testContainsNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.contains(null));
        }

    @Test
        public void testContainsRangeNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.containsRange(null));
        }

    @Test
        public void testElementCompareToNull() {
            Range<Integer> range = Range.between(1, 5);
            assertThrows(NullPointerException.class, () -> range.elementCompareTo(null));
        }

    @Test
        public void testFitBelow() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(3, (int) range.fit(2));
        }

    @Test
        public void testFitAbove() {
            Range<Integer> range = Range.between(3, 7);
            assertEquals(7, (int) range.fit(8));
        }

    
    
    @Test
        public void testHashCodeSameComparators() {
            Comparator<Integer> comparator = Integer::compare;
            Range<Integer> range1 = Range.between(1, 5, comparator);
            Range<Integer> range2 = Range.between(1, 5, comparator);
            assertEquals(range1.hashCode(), range2.hashCode());
        }

    @Test
        public void testIntersectionWithNonOverlapping() {
            Range<Integer> range1 = Range.between(1, 3);
            Range<Integer> range2 = Range.between(4, 6);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsAfterNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isAfter(null));
        }

    @Test
        public void testIsBeforeNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBefore(null));
        }

    @Test
        public void testIsEndedByNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isEndedBy(null));
        }

    @Test
        public void testIsStartedByNull() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isStartedBy(null));
        }

    @Test
        public void testToStringFormat() {
            Range<Integer> range = Range.between(1, 5);
            String result = range.toString("%s to %s");
            assertTrue(result.contains("1"));
            assertTrue(result.contains("5"));
        }
}
