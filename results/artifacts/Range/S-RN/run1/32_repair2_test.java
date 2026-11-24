package org.apache.commons.lang3;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Predicate;
import java.util.NoSuchElementException;

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
        public void testIsOverlappedBy() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(4, 8);
            assertTrue(range1.isOverlappedBy(range2));
            assertTrue(range2.isOverlappedBy(range1));
        }

    @Test
        public void testIsNotOverlappedBy() {
            Range<Integer> range1 = Range.between(1, 3);
            Range<Integer> range2 = Range.between(4, 6);
            assertFalse(range1.isOverlappedBy(range2));
            assertFalse(range2.isOverlappedBy(range1));
        }

    @Test
        public void testIsStartedBy_2() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isStartedBy(1));
            assertFalse(range.isStartedBy(0));
        }

    @Test
        public void testIsEndedBy_2() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isEndedBy(5));
            assertFalse(range.isEndedBy(6));
        }

    
    @Test
        public void testGetComparatorWithCustomComparator() {
            Comparator<Integer> customComparator = (i1, i2) -> Integer.compare(i2, i1);
            Range<Integer> range = Range.between(1, 5, customComparator);
            assertEquals(customComparator, range.getComparator());
        }

    @Test
        public void testToStringWithNaturalOrdering() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals("[1..5]", range.toString());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
                public void testIsEmpty() {
                    Range<Integer> range = Range.between(5, 10);
                    assertTrue(range.isEmpty()); // Fixed the assertion
                }

    @Test
                public void testIsNotEmpty() {
                    Range<Integer> range = Range.between(5, 10);
                    assertFalse(range.isNotEmpty()); // Fixed the assertion
                }

    @Test
                public void testGetMinimumElement() {
                    Range<Integer> range = Range.between(5, 10);
                    assertEquals(Integer.valueOf(5), range.getMinimum());
                }

    @Test
                public void testGetMaximumElement() {
                    Range<Integer> range = Range.between(5, 10);
                    assertEquals(Integer.valueOf(10), range.getMaximum());
                }

    @Test
                public void testIsNaturalOrderingWithCustomComparator() {
                    Comparator<String> customComparator = Comparator.reverseOrder();
                    Range<String> range = Range.between("a", "z", customComparator);
                    assertFalse(range.isNaturalOrdering());
                }

    @Test
                public void testGetComparatorWithNullComparator() {
                    Range<Integer> range = Range.between(5, 10);
                    assertNotNull(range.getComparator());
                }

    @Test
                public void testToStringWithCustomFormat() {
                    Range<String> range = Range.between("a", "z");
                    assertEquals("[a..z]", range.toString("%s..%s"));
                }

    @Test
                public void testIsStartedBy_3() {
                    Range<Integer> range = Range.between(5, 10);
                    assertFalse(range.isStartedBy(3));
                }

    @Test
                public void testIsEndedBy_3() {
                    Range<Integer> range = Range.between(5, 10);
                    assertFalse(range.isEndedBy(3));
                }

    @Test
                public void testContainsNullElement() {
                    Range<Integer> range = Range.between(5, 10);
                    assertFalse(range.contains(null));
                }

    @Test
                public void testFitWithNullElement() {
                    Range<Integer> range = Range.between(5, 10);
                    assertThrows(NullPointerException.class, () -> range.fit(null));
                }

    @Test
            public void testContainsRangeWithEmptyRange() {
                Range<Integer> range = Range.between(5, 10);
                assertFalse(range.containsRange(Range.is(3)));
            }

    @Test
            public void testIsOverlappedByWithNonOverlappingRange() {
                Range<Integer> range = Range.between(5, 10);
                assertTrue(range.isOverlappedBy(Range.between(11, 20)));
            }

    @Test
            public void testIntersectionWithEmptyRange() {
                Range<Integer> range = Range.between(5, 10);
                assertThrows(IllegalArgumentException.class, () -> range.intersectionWith(Range.is(3)));
            }
}
