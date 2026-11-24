package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.time.DurationFormatUtils.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.TimeZone;

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

    
    
    
    
    
    @Test
                public void testFormatDurationWithOptionalToken() {
                    Token token = new Token("test", true, 0);
                    assertTrue(token.equals(new Token("test", true, 0)));
                }

    @Test
                public void testFormatDurationWithNumberValue() {
                    Token token = new Token(123, false, -1);
                    assertTrue(token.equals(new Token(123, false, -1)));
                }

    @Test
                public void testFormatDurationWithStringBuilderValue() {
                    Token token = new Token(new StringBuilder("test"), false, -1);
                    assertTrue(token.equals(new Token(new StringBuilder("test"), false, -1)));
                }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testTokenEqualsDifferentClass() {
            Token token1 = new Token("test", false, -1);
            Token token2 = new Token(123, false, -1);
            assertFalse(token1.equals(token2));
        }

    @Test
        public void testTokenEqualsDifferentCount() {
            Token token1 = new Token("test", false, -1);
            Token token2 = new Token("test", 2, -1);
            assertFalse(token1.equals(token2));
        }

    @Test
        public void testTokenHashCode() {
            Token token = new Token("test", false, -1);
            assertEquals("test".hashCode(), token.hashCode());
        }

    @Test
        public void testTokenToString() {
            Token token = new Token("test", false, -1);
            assertEquals("testtesttest", token.toString());
        }

    @Test
        public void testFormatDurationISO() {
            String result = DurationFormatUtils.formatDurationISO(123456789L);
            assertTrue(result.contains("PT"));
        }

    @Test
        public void testFormatPeriodThrowsExceptionForStartAfterEnd_2() {
            assertThrows(IllegalArgumentException.class, () -> 
                DurationFormatUtils.formatPeriod(2000, 1000, "P")
            );
        }

    @Test
        public void testFormatDurationWithOptionalToken_2() {
            String result = DurationFormatUtils.formatDuration(123456789L, "'d' days 'H' hours 'm' minutes 's' seconds'");
            assertTrue(result.contains("days"));
        }

    @Test
        public void testFormatDurationWithNumberValue_2() {
            String result = DurationFormatUtils.formatDuration(123456789L, "HH:mm:ss.SSS");
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }

    @Test
        public void testFormatDurationWithStringBuilderValue_2() {
            String result = DurationFormatUtils.formatDuration(123456789L, "HH:mm:ss.SSS");
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }

    @Test
        public void testFormatPeriodWithOptionalToken() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "'d' days 'H' hours 'm' minutes 's' seconds'");
            assertTrue(result.contains("days"));
        }

    @Test
        public void testFormatPeriodWithNumberValue() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "HH:mm:ss.SSS");
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }

    @Test
        public void testFormatPeriodWithStringBuilderValue() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "HH:mm:ss.SSS");
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }

    @Test
        public void testFormatDurationWithOptionalTokenInTimeZone() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "'d' days 'H' hours 'm' minutes 's' seconds'", true, TimeZone.getTimeZone("UTC"));
            assertTrue(result.contains("days"));
        }

    @Test
        public void testFormatDurationWithNumberValueInTimeZone() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "HH:mm:ss.SSS", true, TimeZone.getTimeZone("UTC"));
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }

    @Test
        public void testFormatDurationWithStringBuilderValueInTimeZone() {
            String result = DurationFormatUtils.formatPeriod(1000, 2000, "HH:mm:ss.SSS", true, TimeZone.getTimeZone("UTC"));
            assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
        }
}
