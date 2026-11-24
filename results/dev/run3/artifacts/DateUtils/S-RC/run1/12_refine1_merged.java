package org.apache.commons.lang3.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils_GenTest {

    private Calendar startCalendar;
    private Calendar endCalendar;

    @BeforeEach
    public void setUp() {
        startCalendar = Calendar.getInstance();
        startCalendar.set(2023, Calendar.JANUARY, 1);
        endCalendar = Calendar.getInstance();
        endCalendar.set(2023, Calendar.DECEMBER, 31);
    }

    @Test
    public void testAddDays() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addDays(date, 5);
        assertEquals(6, result.getDate());
    }

    
    @Test
    public void testAddMilliseconds() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addMilliseconds(date, 500);
        assertEquals(500, result.getTime() - date.getTime());
    }

    
    @Test
    public void testAddMonths() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addMonths(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(Calendar.FEBRUARY, cal.get(Calendar.MONTH));
    }

    
    @Test
    public void testAddWeeks() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addWeeks(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(8, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddYears() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addYears(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2024, cal.get(Calendar.YEAR));
    }

    
    
    @Test
    public void testIsSameDay() {
        Date date1 = new Date(startCalendar.getTimeInMillis());
        Date date2 = new Date(startCalendar.getTimeInMillis());
        assertTrue(DateUtils.isSameDay(date1, date2));
    }

    @Test
    public void testIsSameInstant() {
        Date date1 = new Date(startCalendar.getTimeInMillis());
        Date date2 = new Date(startCalendar.getTimeInMillis());
        assertTrue(DateUtils.isSameInstant(date1, date2));
    }

    
    
    
    
    @Test
    public void testRound() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.round(date, Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetDays() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setDays(date, 15);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetHours() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setHours(date, 12);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
    }

    
    @Test
    public void testSetMinutes() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setMinutes(date, 30);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(30, cal.get(Calendar.MINUTE));
    }

    @Test
    public void testSetMonths() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setMonths(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(Calendar.FEBRUARY, cal.get(Calendar.MONTH));
    }

    
    @Test
    public void testSetYears() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setYears(date, 2024);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2024, cal.get(Calendar.YEAR));
    }

    @Test
    public void testToCalendar() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Calendar result = DateUtils.toCalendar(date);
        assertEquals(startCalendar.getTimeInMillis(), result.getTimeInMillis());
    }

    @Test
    public void testTruncate() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    }

    // ---- added by coverage-guided refinement ----
    @BeforeEach
        public void setUp_2() {
            startCalendar = Calendar.getInstance();
            startCalendar.set(2023, Calendar.JANUARY, 1);
            endCalendar = Calendar.getInstance();
            endCalendar.set(2023, Calendar.FEBRUARY, 1);
        }

    @Test
        public void testDateIterator() {
            DateIterator iterator = new DateIterator(startCalendar, endCalendar);
            assertTrue(iterator.hasNext());
            assertEquals(startCalendar.getTime(), iterator.next().getTime());
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }

    @Test
        public void testIsSameDay_2() {
            Calendar cal1 = Calendar.getInstance();
            cal1.set(2023, Calendar.JANUARY, 1);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(2023, Calendar.JANUARY, 1);
            assertTrue(DateUtils.isSameDay(cal1, cal2));
    
            cal2.set(2023, Calendar.JANUARY, 2);
            assertFalse(DateUtils.isSameDay(cal1, cal2));
        }

    @Test
        public void testIsSameInstant_2() {
            Date date1 = new Date();
            Date date2 = new Date(date1.getTime());
            assertTrue(DateUtils.isSameInstant(date1, date2));
    
            date2.setTime(date1.getTime() + 1);
            assertFalse(DateUtils.isSameInstant(date1, date2));
        }

    @Test
        public void testAddHours() {
            Date date = new Date();
            Date result = DateUtils.addHours(date, 1);
            assertEquals(date.getTime() + TimeUnit.HOURS.toMillis(1), result.getTime());
        }

    @Test
        public void testAddMinutes() {
            Date date = new Date();
            Date result = DateUtils.addMinutes(date, 30);
            assertEquals(date.getTime() + TimeUnit.MINUTES.toMillis(30), result.getTime());
        }

    @Test
        public void testAddSeconds() {
            Date date = new Date();
            Date result = DateUtils.addSeconds(date, 60);
            assertEquals(date.getTime() + TimeUnit.SECONDS.toMillis(60), result.getTime());
        }

    @Test
        public void testGetFragmentInDays() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 15);
            long fragment = DateUtils.getFragmentInDays(calendar, Calendar.DAY_OF_MONTH);
            assertEquals(15, fragment);
        }

    @Test
        public void testGetFragmentInHours() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1, 12, 0, 0);
            long fragment = DateUtils.getFragmentInHours(calendar, Calendar.HOUR_OF_DAY);
            assertEquals(12, fragment);
        }

    @Test
        public void testGetFragmentInMilliseconds() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1, 0, 0, 0, 500);
            long fragment = DateUtils.getFragmentInMilliseconds(calendar, Calendar.MILLISECOND);
            assertEquals(500, fragment);
        }

    @Test
        public void testGetFragmentInMinutes() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1, 12, 30, 0);
            long fragment = DateUtils.getFragmentInMinutes(calendar, Calendar.MINUTE);
            assertEquals(30, fragment);
        }

    @Test
        public void testGetFragmentInSeconds() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1, 12, 0, 45);
            long fragment = DateUtils.getFragmentInSeconds(calendar, Calendar.SECOND);
            assertEquals(45, fragment);
        }
}
