package org.apache.commons.lang3;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;

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
        public void testOfWithNullComparator() {
            Range<Integer> range = Range.of(1, 2);
            assertEquals(Integer.compare(1, 2), range.getComparator().compare(1, 2));
        }

    @Test
        public void testContainsWithNullElement() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.contains(null));
        }

    @Test
        public void testContainsRangeWithNullOtherRange() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.containsRange(null));
        }

    @Test
        public void testEqualsWithNullObject() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.equals(null));
        }

    @Test
        public void testEqualsWithDifferentClass() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.equals("not a range"));
        }

    @Test
        public void testEqualsWithSameObject() {
            Range<Integer> range = Range.of(1, 2);
            assertTrue(range.equals(range));
        }

    @Test
        public void testEqualsWithDifferentRange() {
            Range<Integer> range1 = Range.of(1, 2);
            Range<Integer> range2 = Range.of(3, 4);
            assertFalse(range1.equals(range2));
        }

    @Test
        public void testHashCodeWithNullComparator() {
            Range<Integer> range = Range.of(1, 2);
            int hashCode = range.hashCode();
            assertNotEquals(0, hashCode);
        }

    @Test
        public void testToStringWithNullToString() {
            Range<Integer> range = Range.of(1, 2);
            String toString = range.toString();
            assertNotNull(toString);
        }

    @Test
        public void testToStringWithFormat() {
            Range<Integer> range = Range.of(1, 2);
            String toString = range.toString("%s to %s");
            assertEquals("1 to 2", toString);
        }

    @Test
        public void testIsAfterWithNullElement() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.isAfter(null));
        }

    @Test
        public void testIsBeforeWithNullElement() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.isBefore(null));
        }

    @Test
        public void testIsEndedByWithNullElement() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.isEndedBy(null));
        }

    @Test
        public void testIsStartedByWithNullElement() {
            Range<Integer> range = Range.of(1, 2);
            assertFalse(range.isStartedBy(null));
        }

    @Test
        public void testIntersectionWithNonOverlappingRange() {
            Range<Integer> range1 = Range.of(1, 3);
            Range<Integer> range2 = Range.of(4, 6);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsNaturalOrderingWithNullComparator() {
            Range<Integer> range = Range.of(1, 2);
            assertTrue(range.isNaturalOrdering());
        }

    // ---- added by coverage-guided refinement ----
    private Range<Integer> range1;

    private Range<Integer> range2;

    private Range<Integer> range3;

    @BeforeEach
        public void setUp() {
            range1 = Range.between(1, 5);
            range2 = Range.between(6, 10);
            range3 = Range.is(7);
        }

    @Test
        public void testComparatorComparison() {
            assertTrue(range1.getComparator().compare(3, 4) < 0);
            assertTrue(range1.getComparator().compare(4, 3) > 0);
            assertEquals(0, range1.getComparator().compare(3, 3));
        }

    @Test
        public void testContainsRangeNonOverlapping() {
            assertFalse(range1.containsRange(range2));
        }

    @Test
        public void testContainsRangeOverlapping() {
            Range<Integer> overlapping = Range.between(4, 6);
            assertTrue(range1.containsRange(overlapping));
        }

    @Test
        public void testElementCompareTo_2() {
            assertEquals(-1, range1.elementCompareTo(2));
            assertEquals(0, range1.elementCompareTo(3));
            assertEquals(1, range1.elementCompareTo(4));
        }

    @Test
        public void testEqualsWithNullObject_2() {
            assertFalse(range1.equals(null));
        }

    @Test
        public void testEqualsWithDifferentClass_2() {
            assertFalse(range1.equals("not a Range"));
        }

    @Test
        public void testEqualsWithSameObject_2() {
            assertTrue(range1.equals(range1));
        }

    @Test
        public void testEqualsWithDifferentRange_2() {
            assertFalse(range1.equals(Range.between(2, 6)));
        }

    @Test
        public void testHashCodeWithNullComparator_2() {
            Range<Integer> range = new Range<>(1, 5, null);
            assertEquals(37 * (37 * (37 * 17 + Integer.class.hashCode()) + 1) + 5, range.hashCode());
        }

    @Test
        public void testToStringWithNullToString_2() {
            Range<Integer> range = new Range<>(1, 5) {
                @Override
                public String toString() {
                    return null;
                }
            };
            assertEquals("[1..5]", range.toString());
        }

    @Test
        public void testToStringWithFormat_2() {
            assertEquals("Range{minimum=1, maximum=5}", range1.toString("%s{%s, %s}"));
        }

    @Test
        public void testIsAfterWithNullElement_2() {
            assertFalse(range1.isAfter(null));
        }

    @Test
        public void testIsBeforeWithNullElement_2() {
            assertFalse(range1.isBefore(null));
        }

    @Test
        public void testIsEndedByWithNullElement_2() {
            assertFalse(range1.isEndedBy(null));
        }

    @Test
        public void testIsStartedByWithNullElement_2() {
            assertFalse(range1.isStartedBy(null));
        }

    @Test
        public void testIntersectionWithNonOverlappingRange_2() {
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        public void testIsNaturalOrderingWithNullComparator_2() {
            Range<Integer> range = new Range<>(1, 5, null);
            assertTrue(range.isNaturalOrdering());
        }

    @Test
        public void testIsOverlappedByNonOverlappingRange() {
            assertFalse(range1.isOverlappedBy(range2));
        }

    @Test
        public void testIsOverlappedByOverlappingRange() {
            Range<Integer> overlapping = Range.between(4, 6);
            assertTrue(range1.isOverlappedBy(overlapping));
        }
}
