package org.apache.commons.lang3.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testAddHours() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addHours(date, 10);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(11, cal.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testAddMilliseconds() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addMilliseconds(date, 500);
        assertEquals(500, result.getTime() - date.getTime());
    }

    @Test
    public void testAddMinutes() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addMinutes(date, 30);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(30, cal.get(Calendar.MINUTE));
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
    public void testAddSeconds() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.addSeconds(date, 60);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1, cal.get(Calendar.HOUR_OF_DAY));
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
    public void testCeiling() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.ceiling(date, Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetFragmentInDays() {
        Date date = new Date(startCalendar.getTimeInMillis());
        long result = DateUtils.getFragmentInDays(date, Calendar.MONTH);
        assertEquals(31, result);
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
    public void testIsSameLocalTime() {
        Date date1 = new Date(startCalendar.getTimeInMillis());
        Date date2 = new Date(startCalendar.getTimeInMillis());
        assertTrue(DateUtils.isSameLocalTime(date1, date2));
    }

    @Test
    public void testIterator() {
        Iterator<Calendar> iterator = DateUtils.iterator(startCalendar, Calendar.MONTH);
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(31, count);
    }

    @Test
    public void testParseDate() throws ParseException {
        String dateStr = "2023-04-30";
        Date result = DateUtils.parseDate(dateStr, Locale.US, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2023, cal.get(Calendar.YEAR));
        assertEquals(4, cal.get(Calendar.MONTH));
        assertEquals(30, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testParseDateStrictly() throws ParseException {
        String dateStr = "2023-04-30";
        Date result = DateUtils.parseDateStrictly(dateStr, Locale.US, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2023, cal.get(Calendar.YEAR));
        assertEquals(4, cal.get(Calendar.MONTH));
        assertEquals(30, cal.get(Calendar.DAY_OF_MONTH));
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
    public void testSetMilliseconds() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setMilliseconds(date, 500);
        assertEquals(500, result.getTime() - date.getTime());
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
    public void testSetSeconds() {
        Date date = new Date(startCalendar.getTimeInMillis());
        Date result = DateUtils.setSeconds(date, 60);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1, cal.get(Calendar.HOUR_OF_DAY));
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
}
