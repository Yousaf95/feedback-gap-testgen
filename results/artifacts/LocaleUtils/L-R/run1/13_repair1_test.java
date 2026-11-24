package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtils_GenTest {

    @Test
    public void testAvailableLocaleList() {
        List<Locale> locales = LocaleUtils.availableLocaleList();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    public void testAvailableLocaleSet() {
        Set<Locale> locales = LocaleUtils.availableLocaleSet();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    public void testCountriesByLanguage_withValidCode() {
        List<Locale> countries = LocaleUtils.countriesByLanguage("en");
        assertNotNull(countries);
        assertTrue(countries.stream().anyMatch(locale -> locale.getCountry().equals("US")));
    }

    @Test
    public void testCountriesByLanguage_withNull() {
        List<Locale> countries = LocaleUtils.countriesByLanguage(null);
        assertEquals(Collections.emptyList(), countries);
    }

    @Test
    public void testIsAvailableLocale_withValidLocale() {
        boolean isAvailable = LocaleUtils.isAvailableLocale(Locale.US);
        assertTrue(isAvailable);
    }

    @Test
    public void testIsAvailableLocale_withInvalidLocale() {
        boolean isAvailable = LocaleUtils.isAvailableLocale(new Locale("xx", "YY"));
        assertFalse(isAvailable);
    }

    @Test
    public void testIsLanguageUndetermined_withNullLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(null);
        assertTrue(isUndetermined);
    }

    @Test
    public void testIsLanguageUndetermined_withUndeterminedLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(new Locale("und"));
        assertTrue(isUndetermined);
    }

    @Test
    public void testIsLanguageUndetermined_withValidLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(Locale.US);
        assertFalse(isUndetermined);
    }

    @Test
    public void testLanguagesByCountry_withValidCode() {
        List<Locale> languages = LocaleUtils.languagesByCountry("US");
        assertNotNull(languages);
        assertTrue(languages.stream().anyMatch(locale -> locale.getLanguage().equals("en")));
    }

    @Test
    public void testLanguagesByCountry_withNull() {
        List<Locale> languages = LocaleUtils.languagesByCountry(null);
        assertEquals(Collections.emptyList(), languages);
    }

    @Test
    public void testLocaleLookupList_withValidLocaleAndDefault() {
        List<Locale> lookupList = LocaleUtils.localeLookupList(Locale.US, Locale.UK);
        assertArrayEquals(new Locale[]{Locale.US, new Locale("en"), Locale.UK}, lookupList.toArray());
    }

    @Test
    public void testLocaleLookupList_withNullLocaleAndDefault() {
        List<Locale> lookupList = LocaleUtils.localeLookupList(null, Locale.UK);
        assertEquals(Collections.singletonList(Locale.UK), lookupList);
    }

    @Test
    public void testToLocale_withValidLocale() {
        Locale locale = LocaleUtils.toLocale((Locale) Locale.US);
        assertEquals(Locale.US, locale);
    }

    @Test
    public void testToString_withValidString() {
        Locale locale = LocaleUtils.toLocale("en_US");
        assertEquals(new Locale("en", "US"), locale);
    }

    @Test
    public void testToString_withEmptyString() {
        Locale locale = LocaleUtils.toLocale("");
        assertEquals(new Locale("", ""), locale);
    }

    @Test
    public void testToString_withNullString() {
        Locale locale = LocaleUtils.toLocale((String) null);
        assertNull(locale);
    }

    @Test
    public void testToString_withInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US_xx"));
    }
}
