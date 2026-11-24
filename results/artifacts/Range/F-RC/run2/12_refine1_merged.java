package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Range_GenTest {

    private Range<Integer> range1;
    private Range<Integer> range2;
    private Range<String> stringRange;

    @BeforeEach
    public void setUp() {
        range1 = Range.between(1, 5);
        range2 = Range.between(3, 7);
        stringRange = Range.between("a", "d");
    }

    @Test
    public void testBetweenWithNullValuesThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Range.between(null, 5));
        assertThrows(NullPointerException.class, () -> Range.between(1, null));
        assertThrows(NullPointerException.class, () -> Range.between(null, null));
    }

    @Test
    public void testIsWithNullValueReturnsFalse() {
        assertFalse(range1.isAfter(null));
        assertFalse(range1.isBefore(null));
        assertFalse(range1.contains(null));
    }

    @ParameterizedTest
    @MethodSource("provideElementComparisonData")
    public void testElementCompareTo(int element, int expected) {
        assertEquals(expected, range1.elementCompareTo(element));
    }

    private static Stream<Arguments> provideElementComparisonData() {
        return Arrays.stream(new Object[][]{
            {0, -1},
            {1, 0},
            {2, 0},
            {3, 0},
            {4, 0},
            {5, 0},
            {6, 1}
        }).map(Arguments::arguments);
    }

    @Test
    public void testContains() {
        assertTrue(range1.contains(3));
        assertFalse(range1.contains(0));
        assertFalse(range1.contains(6));
    }

    @Test
    public void testContainsRange() {
        assertTrue(range1.containsRange(Range.between(2, 4)));
        assertFalse(range1.containsRange(Range.between(0, 2)));
        assertFalse(range1.containsRange(Range.between(5, 7)));
    }

    
    private static Stream<Arguments> provideFitData() {
        return Arrays.stream(new Object[][]{
            {0, 'a'},
            {1, 'a'},
            {2, 'b'},
            {3, 'c'},
            {4, 'd'},
            {5, 'd'}
        }).map(Arguments::arguments);
    }

    
    @Test
    public void testGetMaximumAndMinimum() {
        assertEquals(5, range1.getMaximum().intValue());
        assertEquals(1, range1.getMinimum().intValue());
    }

    @Test
    public void testHashCode() {
        assertEquals(range1.hashCode(), Range.between(1, 5).hashCode());
    }

    
    private static Stream<Arguments> provideIntersectionData() {
        return Arrays.stream(new Object[][]{
            {Range.between(2, 4), Range.between(3, 4)},
            {Range.between(0, 2), null},
            {Range.between(5, 7), null}
        }).map(args -> arguments((Range<Integer>) args[0], (Range<Integer>) args[1]));
    }

    @Test
    public void testIsAfter() {
        assertTrue(range1.isAfter(0));
        assertFalse(range1.isAfter(1));
    }

    
    
    private static Stream<Arguments> provideIsOverlappedByData() {
        return Arrays.stream(new Object[][]{
            {Range.between(2, 4), true},
            {Range.between(0, 2), false},
            {Range.between(5, 7), false}
        }).map(args -> arguments((Range<Integer>) args[0], (boolean) args[1]));
    }

    @Test
    public void testToString() {
        assertEquals("[1..5]", range1.toString());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testBetweenWithNullValuesThrowsNullPointerException_2() {
            assertThrows(NullPointerException.class, () -> Range.between(null, "b"));
            assertThrows(NullPointerException.class, () -> Range.between("a", null));
            assertThrows(NullPointerException.class, () -> Range.between(null, null, Comparator.naturalOrder()));
        }

    @Test
        public void testIsWithNullValueReturnsFalse_2() {
            assertFalse(Range.is((String) null).contains("a"));
        }

    @Test
        public void testElementCompareTo_2() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(-1, range.elementCompareTo(0));
            assertEquals(0, range.elementCompareTo(3));
            assertEquals(1, range.elementCompareTo(6));
        }

    @Test
        public void testContains_2() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.contains(2));
            assertFalse(range.contains(0));
            assertFalse(range.contains(6));
        }

    @Test
        public void testContainsRange_2() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.containsRange(Range.between(2, 4)));
            assertFalse(range.containsRange(Range.between(0, 1)));
            assertFalse(range.containsRange(Range.between(5, 6)));
        }

    @Test
        public void testGetMaximumAndMinimum_2() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals(1, (int) range.getMinimum());
            assertEquals(5, (int) range.getMaximum());
        }

    @Test
        public void testHashCode_2() {
            Range<Integer> range1 = Range.between(1, 5);
            Range<Integer> range2 = Range.between(1, 5);
            assertEquals(range1.hashCode(), range2.hashCode());
    
            Range<Integer> range3 = Range.between(2, 6);
            assertNotEquals(range1.hashCode(), range3.hashCode());
        }

    @Test
        public void testIsAfter_2() {
            Range<Integer> range = Range.between(1, 5);
            assertTrue(range.isAfter(0));
            assertFalse(range.isAfter(5));
            assertFalse(range.isAfter(6));
        }

    @Test
        public void testToString_2() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals("[1..5]", range.toString());
        }

    @Test
        public void testToStringWithFormat() {
            Range<Integer> range = Range.between(1, 5);
            assertEquals("Range{minimum=1, maximum=5}", range.toString("%s"));
        }
}
