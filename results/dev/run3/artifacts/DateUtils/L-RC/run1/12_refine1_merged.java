package org.apache.commons.lang3.time;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

public class DateUtils_GenTest {

    @Test
    void testAddDays() {
        Date date = new GregorianCalendar(2023, Calendar.OCTOBER, 1).getTime();
        Date expectedDate = new GregorianCalendar(2023, Calendar.OCTOBER, 2).getTime();
        assertEquals(expectedDate, DateUtils.addDays(date, 1));
    }

    @Test
    void testAddDaysWithNegativeAmount() {
        Date date = new GregorianCalendar(2023, Calendar.OCTOBER, 1).getTime();
        Date expectedDate = new GregorianCalendar(2023, Calendar.SEPTEMBER, 30).getTime();
        assertEquals(expectedDate, DateUtils.addDays(date, -1));
    }

    @Test
    void testAddDaysWithZeroAmount() {
        Date date = new GregorianCalendar(2023, Calendar.OCTOBER, 1).getTime();
        assertEquals(date, DateUtils.addDays(date, 0));
    }

    
    
    
    
    @Test
    void testIteratorInvalidRangeStyle() {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15);
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator(calendar, -1));
    }

    @Test
    void testCeilingDayOfMonth() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 1, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2023, Calendar.OCTOBER, 2);
        assertEquals(expectedCalendar.getTime(), DateUtils.ceiling(calendar, Calendar.DAY_OF_MONTH).getTime());
    }

    @Test
    void testCeilingMonth() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 1);
        assertEquals(expectedCalendar.getTime(), DateUtils.ceiling(calendar, Calendar.MONTH).getTime());
    }

    @Test
    void testCeilingYear() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2024, Calendar.JANUARY, 1);
        assertEquals(expectedCalendar.getTime(), DateUtils.ceiling(calendar, Calendar.YEAR).getTime());
    }

    @Test
    void testTruncateDayOfMonth() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15);
        assertEquals(expectedCalendar.getTime(), DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH).getTime());
    }

    @Test
    void testTruncateMonth() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2023, Calendar.OCTOBER, 1);
        assertEquals(expectedCalendar.getTime(), DateUtils.truncate(calendar, Calendar.MONTH).getTime());
    }

    @Test
    void testTruncateYear() throws Exception {
        Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45);
        Calendar expectedCalendar = new GregorianCalendar(2023, Calendar.JANUARY, 1);
        assertEquals(expectedCalendar.getTime(), DateUtils.truncate(calendar, Calendar.YEAR).getTime());
    }

    @Test
    void testIsSameDay() {
        Date date1 = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        assertTrue(DateUtils.isSameDay(date1, date2));
    }

    @Test
    void testIsNotSameDay() {
        Date date1 = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.OCTOBER, 16).getTime();
        assertFalse(DateUtils.isSameDay(date1, date2));
    }

    @Test
    void testIsSameInstant() {
        Date date1 = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45).getTime();
        assertTrue(DateUtils.isSameInstant(date1, date2));
    }

    @Test
    void testIsNotSameInstant() {
        Date date1 = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 45).getTime();
        Date date2 = new GregorianCalendar(2023, Calendar.OCTOBER, 15, 12, 30, 46).getTime();
        assertFalse(DateUtils.isSameInstant(date1, date2));
    }

    @Test
    void testParseDate() throws ParseException {
        String dateStr = "2023-10-15";
        Date expectedDate = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        assertEquals(expectedDate, DateUtils.parseDate(dateStr, "yyyy-MM-dd"));
    }

    @Test
    void testParseDateWithMultiplePatterns() throws ParseException {
        String dateStr = "15/10/2023";
        Date expectedDate = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        assertEquals(expectedDate, DateUtils.parseDate(dateStr, "yyyy-MM-dd", "dd/MM/yyyy"));
    }

    @Test
    void testParseDateStrictly() throws ParseException {
        String dateStr = "2023-10-15";
        Date expectedDate = new GregorianCalendar(2023, Calendar.OCTOBER, 15).getTime();
        assertEquals(expectedDate, DateUtils.parseDateStrictly(dateStr, "yyyy-MM-dd"));
    }

    @Test
    void testParseDateWithInvalidPattern() {
        String dateStr = "2023-10-15";
        assertThrows(ParseException.class, () -> DateUtils.parseDate(dateStr, "dd/MM/yyyy"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testDateIteratorConstructor() {
            Calendar start = Calendar.getInstance();
            Calendar end = (Calendar) start.clone();
            end.add(Calendar.DATE, 1);
            DateUtils.DateIterator iterator = new DateUtils.DateIterator(start, end);
            assertTrue(iterator.hasNext());
        }

    @Test
        public void testDateIteratorHasNextFalse() {
            Calendar start = Calendar.getInstance();
            Calendar end = (Calendar) start.clone();
            DateUtils.DateIterator iterator = new DateUtils.DateIterator(start, end);
            assertFalse(iterator.hasNext());
        }

    @Test
        public void testDateIteratorNextThrowsNoSuchElementException() {
            Calendar start = Calendar.getInstance();
            Calendar end = (Calendar) start.clone();
            DateUtils.DateIterator iterator = new DateUtils.DateIterator(start, end);
            assertThrows(NoSuchElementException.class, iterator::next);
        }

    @Test
        public void testDateIteratorRemoveThrowsUnsupportedOperationException() {
            Calendar start = Calendar.getInstance();
            Calendar end = (Calendar) start.clone();
            end.add(Calendar.DATE, 1);
            DateUtils.DateIterator iterator = new DateUtils.DateIterator(start, end);
            assertThrows(UnsupportedOperationException.class, iterator::remove);
        }

    @Test
        public void testAddHours() {
            Date date = new Date();
            Date result = DateUtils.addHours(date, 1);
            assertEquals(DateUtils.MILLIS_PER_HOUR, result.getTime() - date.getTime());
        }

    @Test
        public void testAddMilliseconds() {
            Date date = new Date();
            Date result = DateUtils.addMilliseconds(date, 500);
            assertEquals(500, result.getTime() - date.getTime());
        }

    @Test
        public void testAddMinutes() {
            Date date = new Date();
            Date result = DateUtils.addMinutes(date, 1);
            assertEquals(DateUtils.MILLIS_PER_MINUTE, result.getTime() - date.getTime());
        }

    @Test
        public void testAddMonths() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            Date date = calendar.getTime();
            Date result = DateUtils.addMonths(date, 1);
            calendar.add(Calendar.MONTH, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testAddSeconds() {
            Date date = new Date();
            Date result = DateUtils.addSeconds(date, 1);
            assertEquals(DateUtils.MILLIS_PER_SECOND, result.getTime() - date.getTime());
        }

    @Test
        public void testAddWeeks() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            Date date = calendar.getTime();
            Date result = DateUtils.addWeeks(date, 1);
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testAddYears() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            Date date = calendar.getTime();
            Date result = DateUtils.addYears(date, 1);
            calendar.add(Calendar.YEAR, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testCeilingDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            Date date = calendar.getTime();
            Date result = DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testCeilingObjectDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            Date date = calendar.getTime();
            Date result = DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testCeilingObjectCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            Date result = DateUtils.ceiling(calendar, Calendar.HOUR_OF_DAY).getTime();
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            assertEquals(calendar.getTime(), result);
        }

    @Test
        public void testCeilingObjectThrowsClassCastException() {
            assertThrows(ClassCastException.class, () -> DateUtils.ceiling("not a date", Calendar.HOUR_OF_DAY));
        }

    @Test
        public void testGetFragmentInDaysCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            long result = DateUtils.getFragmentInDays(calendar, Calendar.MONTH);
            assertEquals(14, result); // January 15th is the 15th day of the year
        }

    @Test
        public void testGetFragmentInDaysDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            Date date = calendar.getTime();
            long result = DateUtils.getFragmentInDays(date, Calendar.MONTH);
            assertEquals(14, result); // January 15th is the 15th day of the year
        }

    @Test
        public void testGetFragmentInHoursCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30);
            long result = DateUtils.getFragmentInHours(calendar, Calendar.DAY_OF_MONTH);
            assertEquals(12, result); // 12 hours into the day
        }

    @Test
        public void testGetFragmentInHoursDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30);
            Date date = calendar.getTime();
            long result = DateUtils.getFragmentInHours(date, Calendar.DAY_OF_MONTH);
            assertEquals(12, result); // 12 hours into the day
        }

    @Test
        public void testGetFragmentInMillisecondsCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45, 678);
            long result = DateUtils.getFragmentInMilliseconds(calendar, Calendar.SECOND);
            assertEquals(678, result); // 678 milliseconds into the second
        }

    @Test
        public void testGetFragmentInMillisecondsDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45, 678);
            Date date = calendar.getTime();
            long result = DateUtils.getFragmentInMilliseconds(date, Calendar.SECOND);
            assertEquals(678, result); // 678 milliseconds into the second
        }

    @Test
        public void testGetFragmentInMinutesCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30);
            long result = DateUtils.getFragmentInMinutes(calendar, Calendar.HOUR_OF_DAY);
            assertEquals(30, result); // 30 minutes into the hour
        }

    @Test
        public void testGetFragmentInMinutesDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30);
            Date date = calendar.getTime();
            long result = DateUtils.getFragmentInMinutes(date, Calendar.HOUR_OF_DAY);
            assertEquals(30, result); // 30 minutes into the hour
        }

    @Test
        public void testGetFragmentInSecondsCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            long result = DateUtils.getFragmentInSeconds(calendar, Calendar.MINUTE);
            assertEquals(45, result); // 45 seconds into the minute
        }

    @Test
        public void testGetFragmentInSecondsDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            Date date = calendar.getTime();
            long result = DateUtils.getFragmentInSeconds(date, Calendar.MINUTE);
            assertEquals(45, result); // 45 seconds into the minute
        }

    @Test
        public void testIsSameDayPartialBranches() {
            Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal1.set(2023, Calendar.JANUARY, 15);
            Calendar cal2 = (Calendar) cal1.clone();
            assertTrue(DateUtils.isSameDay(cal1, cal2));
    
            cal2.add(Calendar.YEAR, 1);
            assertFalse(DateUtils.isSameDay(cal1, cal2));
        }

    @Test
        public void testIsSameInstantCalendar() {
            Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal1.set(2023, Calendar.JANUARY, 15, 12, 30, 45);
            Calendar cal2 = (Calendar) cal1.clone();
            assertTrue(DateUtils.isSameInstant(cal1, cal2));
    
            cal2.add(Calendar.SECOND, 1);
            assertFalse(DateUtils.isSameInstant(cal1, cal2));
        }
}
