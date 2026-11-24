package org.apache.commons.lang3.time;

import org.junit.jupiter.api.Test;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

public class DurationFormatUtils_GenTest {

    @Test
    public void testFormatDurationHMS_withZeroMillis() {
        // Arrange
        long durationMillis = 0L;

        // Act
        String result = DurationFormatUtils.formatDurationHMS(durationMillis);

        // Assert
        assertEquals("00:00:00.000", result);
    }

    @Test
    public void testFormatDurationHMS_withOneSecond() {
        // Arrange
        long durationMillis = 1000L;

        // Act
        String result = DurationFormatUtils.formatDurationHMS(durationMillis);

        // Assert
        assertEquals("00:00:01.000", result);
    }

    @Test
    public void testFormatDurationISO_withZeroMillis() {
        // Arrange
        long durationMillis = 0L;

        // Act
        String result = DurationFormatUtils.formatDurationISO(durationMillis);

        // Assert
        assertEquals("P0Y0M0DT0H0M0.000S", result);
    }

    @Test
    public void testFormatDurationISO_withOneSecond() {
        // Arrange
        long durationMillis = 1000L;

        // Act
        String result = DurationFormatUtils.formatDurationISO(durationMillis);

        // Assert
        assertEquals("P0Y0M0DT0H0M1.000S", result);
    }

    @Test
    public void testFormatDurationWords_withZeroMillis() {
        // Arrange
        long durationMillis = 0L;

        // Act
        String result = DurationFormatUtils.formatDurationWords(durationMillis, true, true);

        // Assert
        assertEquals("0 seconds", result);
    }

    @Test
    public void testFormatDurationWords_withOneSecond() {
        // Arrange
        long durationMillis = 1000L;

        // Act
        String result = DurationFormatUtils.formatDurationWords(durationMillis, true, true);

        // Assert
        assertEquals("1 second", result);
    }

    
    
    @Test
    public void testFormatPeriodISO_withOneSecondDifference() {
        // Arrange
        long startMillis = 0L;
        long endMillis = 1000L;

        // Act
        String result = DurationFormatUtils.formatPeriodISO(startMillis, endMillis);

        // Assert
        assertEquals("P0Y0M0DT0H0M1.000S", result);
    }

    @Test
    public void testFormatDuration_withNegativeDuration() {
        // Arrange
        long durationMillis = -1L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss"));
    }

    @Test
    public void testFormatPeriod_withStartAfterEnd() {
        // Arrange
        long startMillis = 1000L;
        long endMillis = 0L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatPeriod(startMillis, endMillis, "HH:mm:ss"));
    }

    @Test
    public void testFormatDuration_withNullPattern() {
        // Arrange
        long durationMillis = 1000L;
        String pattern = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> DurationFormatUtils.formatDuration(durationMillis, pattern));
    }

    @Test
    public void testFormatPeriod_withNullPattern() {
        // Arrange
        long startMillis = 0L;
        long endMillis = 1000L;
        String pattern = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> DurationFormatUtils.formatPeriod(startMillis, endMillis, pattern));
    }

    @Test
    public void testFormatDuration_withEmptyPattern() {
        // Arrange
        long durationMillis = 1000L;
        String pattern = "";

        // Act
        String result = DurationFormatUtils.formatDuration(durationMillis, pattern);

        // Assert
        assertEquals("", result);
    }

    @Test
    public void testFormatPeriod_withEmptyPattern() {
        // Arrange
        long startMillis = 0L;
        long endMillis = 1000L;
        String pattern = "";

        // Act
        String result = DurationFormatUtils.formatPeriod(startMillis, endMillis, pattern);

        // Assert
        assertEquals("", result);
    }

    
    
    
    @Test
    public void testFormatDurationWords_withoutSuppressingZeros() {
        // Arrange
        long durationMillis = 3601000L; // 1 hour and 1 second

        // Act
        String result = DurationFormatUtils.formatDurationWords(durationMillis, false, false);

        // Assert
        assertEquals("0 days 1 hour 0 minutes 1 second", result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testSuppressLeadingZeroElements() {
            String result = DurationFormatUtils.formatDurationWords(3600000, true, false);
            assertEquals("1 hour 0 minutes 0 seconds", result);
        }

    
    
    
    @Test
        public void testFormatDurationWithOptionalTokens() {
            String result = DurationFormatUtils.formatDuration(1000, "H'H'm'M's'S'", true);
            assertEquals("0H0M1S", result);
        }

    @Test
        public void testFormatPeriodWithOptionalTokens() {
            long startMillis = Calendar.getInstance().getTimeInMillis();
            long endMillis = startMillis + 3600000; // one hour later
            String result = DurationFormatUtils.formatPeriod(startMillis, endMillis, "H'H'm'M's'S'");
            assertEquals("1H0M0S", result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testSuppressLeadingZeroElements_2() {
            String result = DurationFormatUtils.formatDurationWords(3600000L, true, false);
            assertEquals("1 hour 0 minutes 0 seconds", result);
        }

    @Test
        public void testSuppressTrailingZeroElements() {
            String result = DurationFormatUtils.formatDurationWords(86400000L, false, true);
            assertEquals("1 day", result);
        }

    @Test
        public void testFormatPeriodWithNegativeDifference() {
            long startMillis = System.currentTimeMillis();
            long endMillis = startMillis - 3600000L;
            assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatPeriod(startMillis, endMillis, "H'H'm'M's'S'"));
        }

    @Test
        public void testFormatDurationWithNegativeMilliseconds() {
            assertThrows(IllegalArgumentException.class, () -> DurationFormatUtils.formatDuration(-1000L, "H'H'm'M's'S'"));
        }

    
    }
