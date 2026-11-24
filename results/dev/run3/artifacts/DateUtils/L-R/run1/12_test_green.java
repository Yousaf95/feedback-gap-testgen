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
}
