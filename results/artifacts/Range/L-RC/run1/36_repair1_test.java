package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class Range_GenTest {

    @Test
    void testBetweenWithComparable() {
        // Arrange
        Integer from = 1;
        Integer to = 5;

        // Act
        Range<Integer> range = Range.between(from, to);

        // Assert
        assertEquals(1, range.getMinimum());
        assertEquals(5, range.getMaximum());
        assertTrue(range.isNaturalOrdering());
    }

    @Test
    void testBetweenWithComparator() {
        // Arrange
        Integer from = 5;
        Integer to = 1;
        Comparator<Integer> comparator = Comparator.reverseOrder();

        // Act
        Range<Integer> range = Range.between(from, to, comparator);

        // Assert
        assertEquals(5, range.getMinimum());
        assertEquals(1, range.getMaximum());
        assertFalse(range.isNaturalOrdering());
    }

    @Test
    void testIsWithComparable() {
        // Arrange
        Integer element = 3;

        // Act
        Range<Integer> range = Range.is(element);

        // Assert
        assertEquals(3, range.getMinimum());
        assertEquals(3, range.getMaximum());
        assertTrue(range.isNaturalOrdering());
    }

    @Test
    void testIsWithComparator() {
        // Arrange
        Integer element = 3;
        Comparator<Integer> comparator = Comparator.reverseOrder();

        // Act
        Range<Integer> range = Range.is(element, comparator);

        // Assert
        assertEquals(3, range.getMinimum());
        assertEquals(3, range.getMaximum());
        assertFalse(range.isNaturalOrdering());
    }

    @Test
    void testOfWithComparable() {
        // Arrange
        Integer from = 1;
        Integer to = 5;

        // Act
        Range<Integer> range = Range.of(from, to);

        // Assert
        assertEquals(1, range.getMinimum());
        assertEquals(5, range.getMaximum());
        assertTrue(range.isNaturalOrdering());
    }

    @Test
    void testOfWithComparator() {
        // Arrange
        Integer from = 5;
        Integer to = 1;
        Comparator<Integer> comparator = Comparator.reverseOrder();

        // Act
        Range<Integer> range = Range.of(from, to, comparator);

        // Assert
        assertEquals(5, range.getMinimum());
        assertEquals(1, range.getMaximum());
        assertFalse(range.isNaturalOrdering());
    }

    @Test
    void testContainsElement() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertTrue(range.contains(3));
        assertFalse(range.contains(0));
        assertFalse(range.contains(6));
    }

    @Test
    void testContainsRange() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);
        Range<Integer> subRange = Range.between(2, 4);

        // Act & Assert
        assertTrue(range.containsRange(subRange));
        assertFalse(range.containsRange(Range.between(0, 6)));
    }

    
    @Test
    void testEquals() {
        // Arrange
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(1, 5);
        Range<Integer> range3 = Range.between(2, 4);

        // Act & Assert
        assertEquals(range1, range2);
        assertNotEquals(range1, range3);
    }

    @Test
    void testFit() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertEquals(3, range.fit(3));
        assertEquals(1, range.fit(0));
        assertEquals(5, range.fit(6));
    }

    @Test
    void testGetComparator() {
        // Arrange
        Comparator<Integer> comparator = Comparator.reverseOrder();
        Range<Integer> range = Range.between(5, 1, comparator);

        // Act & Assert
        assertEquals(comparator, range.getComparator());
    }

    @Test
    void testIntersectionWith() {
        // Arrange
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(3, 7);

        // Act
        Range<Integer> intersection = range1.intersectionWith(range2);

        // Assert
        assertEquals(Range.between(3, 5), intersection);
    }

    @Test
    void testIntersectionWithNonOverlapping() {
        // Arrange
        Range<Integer> range1 = Range.between(1, 2);
        Range<Integer> range2 = Range.between(4, 6);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
    }

    @Test
    void testIsAfter() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertTrue(range.isAfter(0));
        assertFalse(range.isAfter(3));
        assertFalse(range.isAfter(6));
    }

    @Test
    void testIsBefore() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertFalse(range.isBefore(0));
        assertFalse(range.isBefore(3));
        assertTrue(range.isBefore(6));
    }

    @Test
    void testIsStartedBy() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertTrue(range.isStartedBy(1));
        assertFalse(range.isStartedBy(0));
        assertFalse(range.isStartedBy(6));
    }

    @Test
    void testIsEndedBy() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertTrue(range.isEndedBy(5));
        assertFalse(range.isEndedBy(0));
        assertFalse(range.isEndedBy(6));
    }

    @Test
    void testIsOverlappedBy() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);
        Range<Integer> overlappingRange = Range.between(3, 7);

        // Act & Assert
        assertTrue(range.isOverlappedBy(overlappingRange));
        assertFalse(range.isOverlappedBy(Range.between(6, 8)));
    }

    @Test
    void testToString() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertEquals("[1..5]", range.toString());
    }

    
    @Test
    void testNullElementInBetween() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> Range.between(null, 5));
        assertThrows(NullPointerException.class, () -> Range.between(1, null));
    }

    @Test
    void testNullElementInIs() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> Range.is((Integer) null));
    }

    @Test
    void testNullElementInOf() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> Range.of(null, 5));
        assertThrows(NullPointerException.class, () -> Range.of(1, null));
    }

    @Test
    void testNullElementInContains() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertFalse(range.contains(null));
    }

    @Test
    void testNullElementInElementCompareTo() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> range.elementCompareTo(null));
    }

    @Test
    void testNullElementInFit() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> range.fit(null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testConstructorWithComparator() {
            Comparator<Integer> comparator = Integer::compareTo;
            Range<Integer> range = new Range<>(5, 10, comparator);
            assertEquals(5, range.getMinimum());
            assertEquals(10, range.getMaximum());
        }

    @Test
        void testContainsRangeWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.containsRange(null));
        }

    @Test
        void testElementCompareToWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertThrows(NullPointerException.class, () -> range.elementCompareTo(null));
        }

    @Test
        void testElementCompareToWhenAfter() {
            Range<Integer> range = Range.between(5, 10);
            assertEquals(-1, range.elementCompareTo(3));
        }

    @Test
        void testElementCompareToWhenBefore() {
            Range<Integer> range = Range.between(5, 10);
            assertEquals(1, range.elementCompareTo(12));
        }

    @Test
        void testElementCompareToWhenInRange() {
            Range<Integer> range = Range.between(5, 10);
            assertEquals(0, range.elementCompareTo(7));
        }

    @Test
        void testEqualsWithSameInstance() {
            Range<Integer> range = Range.between(5, 10);
            assertTrue(range.equals(range));
        }

    @Test
        void testEqualsWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.equals(null));
        }

    @Test
        void testEqualsWithDifferentClass() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.equals("not a range"));
        }

    @Test
        void testEqualsWithDifferentMinimum() {
            Range<Integer> range1 = Range.between(5, 10);
            Range<Integer> range2 = Range.between(6, 10);
            assertFalse(range1.equals(range2));
        }

    @Test
        void testEqualsWithDifferentMaximum() {
            Range<Integer> range1 = Range.between(5, 10);
            Range<Integer> range2 = Range.between(5, 9);
            assertFalse(range1.equals(range2));
        }

    
    @Test
        void testIntersectionWithSameRange() {
            Range<Integer> range1 = Range.between(5, 10);
            Range<Integer> range2 = Range.between(5, 10);
            assertSame(range1, range1.intersectionWith(range2));
        }

    @Test
        void testIsAfterWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isAfter(null));
        }

    @Test
        void testIsAfterRangeWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isAfterRange(null));
        }

    @Test
        void testIsBeforeWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isBefore(null));
        }

    @Test
        void testIsBeforeRangeWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isBeforeRange(null));
        }

    @Test
        void testIsEndedByWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isEndedBy(null));
        }

    @Test
        void testIsOverlappedByWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isOverlappedBy(null));
        }

    @Test
        void testIsStartedByWithNull() {
            Range<Integer> range = Range.between(5, 10);
            assertFalse(range.isStartedBy(null));
        }

    @Test
        void testToStringWhenNotCached() {
            Range<Integer> range = new Range<>(5, 10, null);
            assertEquals("[5..10]", range.toString());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testConstructorWithComparatorSwapsElements() {
            Comparator<Integer> reverseOrder = (a, b) -> b.compareTo(a);
            Range<Integer> range = Range.of(2, 1, reverseOrder);
            assertEquals(Integer.valueOf(1), range.getMinimum());
            assertEquals(Integer.valueOf(2), range.getMaximum());
        }

    @Test
        void testContainsRangeWithNull_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.containsRange(null));
        }

    @Test
        void testHashCodeCalculation() {
            Range<Integer> range = Range.between(1, 5);
            int expectedHash = java.util.Objects.hash(Range.class, 1, 5);
            assertEquals(expectedHash, range.hashCode());
        }

    @Test
        void testIntersectionWithDifferentMinimumAndMaximum() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(3, 7);
            Range<Integer> intersection = range1.intersectionWith(range2);
            assertEquals(Range.between(3, 5), intersection);
        }

    @Test
        void testIsAfterRangeWithNull_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isAfterRange(null));
        }

    @Test
        void testIsBeforeRangeWithNull_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isBeforeRange(null));
        }

    @Test
        void testIsOverlappedByWithNull_2() {
            Range<Integer> range = Range.between(1, 5);
            assertFalse(range.isOverlappedBy(null));
        }

    @Test
        void testToStringWhenCached() {
            Range<Integer> range = Range.between(1, 5);
            String initialString = range.toString();
            assertEquals(initialString, range.toString());
        }

    @Test
        void testToStringWithFormat() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals("[1..5]", range.toString("[%s..%s]"));
        }
}
