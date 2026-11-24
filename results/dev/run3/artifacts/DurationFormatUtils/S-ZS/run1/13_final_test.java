package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.time.DurationFormatUtils.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DurationFormatUtils_GenTest {

    @ParameterizedTest
    @ValueSource(longs = {0, 123456789L})
    public void testFormatDurationHMS(long durationMillis) {
        String formatted = formatDurationHMS(durationMillis);
        assertTrue(formatted.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
    }

    
    @Test
    public void testFormatDurationWords() {
        String formatted = formatDurationWords(86400000L, true, true);
        assertEquals("1 day", formatted);

        formatted = formatDurationWords(3600000L, true, true);
        assertEquals("1 hour", formatted);

        formatted = formatDurationWords(60000L, true, true);
        assertEquals("1 minute", formatted);

        formatted = formatDurationWords(1000L, true, true);
        assertEquals("1 second", formatted);
    }

    
    @Test
    public void testFormatDurationThrowsExceptionForNegativeDuration() {
        assertThrows(IllegalArgumentException.class, () -> formatDuration(-1L, "HH:mm:ss.SSS"));
    }

    @Test
    public void testFormatPeriodThrowsExceptionForStartAfterEnd() {
        assertThrows(IllegalArgumentException.class, () -> formatPeriod(2L, 1L, "P"));
    }
}
