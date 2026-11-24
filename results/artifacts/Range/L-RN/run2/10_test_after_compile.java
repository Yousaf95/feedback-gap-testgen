package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

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
    void testElementCompareTo() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertEquals(-1, range.elementCompareTo(0));
        assertEquals(0, range.elementCompareTo(3));
        assertEquals(1, range.elementCompareTo(6));
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
    void testNullElementInContainsRange() {
        // Arrange
        Range<Integer> range = Range.between(1, 5);

        // Act & Assert
        assertFalse(range.containsRange(null));
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
        void testIsNaturalOrdering() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isNaturalOrdering());
        }

    @Test
        void testIsNaturalOrderingWithComparator() {
            Range<Integer> range = Range.between(1, 5, Integer::compareTo);
            assertFalse(range.isNaturalOrdering());
        }

    @Test
        void testGetMinimum() {
            Range<Integer> range = Range.of(3, 7);
            assertEquals(Integer.valueOf(3), range.getMinimum());
        }

    @Test
        void testGetMaximum() {
            Range<Integer> range = Range.of(3, 7);
            assertEquals(Integer.valueOf(7), range.getMaximum());
        }

    @Test
        void testIsAfterRange() {
            Range<Integer> range1 = Range.between(5, 10);
            Range<Integer> range2 = Range.between(1, 4);
            assertTrue(range1.isAfterRange(range2));
        }

    @Test
        void testIsBeforeRange() {
            Range<Integer> range1 = Range.between(1, 4);
            Range<Integer> range2 = Range.between(5, 10);
            assertTrue(range1.isBeforeRange(range2));
        }

    @Test
        void testIntersectionWithOverlappingRanges() {
            Range<Integer> range1 = Range.between(3, 7);
            Range<Integer> range2 = Range.between(5, 9);
            Range<Integer> intersection = range1.intersectionWith(range2);
            assertEquals(Range.of(5, 7), intersection);
        }

    @Test
        void testIntersectionWithNonOverlappingRangesThrowsException() {
            Range<Integer> range1 = Range.between(1, 3);
            Range<Integer> range2 = Range.between(5, 9);
            assertThrows(IllegalArgumentException.class, () -> range1.intersectionWith(range2));
        }

    @Test
        void testToStringWithFormat() {
            Range<Integer> range = Range.of(3, 7);
            assertEquals("[3..7]", range.toString());
            assertEquals("Min: 3 Max: 7", range.toString("Min: %s Max: %s"));
        }
}
