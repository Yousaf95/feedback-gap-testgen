package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testIsOverlappedBy() {
        Range<Integer> range1 = Range.between(1, 5);
        Range<Integer> range2 = Range.between(3, 7);
        assertTrue(range1.isOverlappedBy(range2));
        assertFalse(range2.isOverlappedBy(range1));
    }

    @Test
    public void testToString() {
        Range<Integer> range = Range.between(1, 5);
        assertEquals("[1..5]", range.toString());
    }
}
