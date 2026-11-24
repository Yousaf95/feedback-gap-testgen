package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtils_GenTest {

    @Test
    void testAvailableLocaleList() {
        List<Locale> locales = LocaleUtils.availableLocaleList();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    void testAvailableLocaleSet() {
        Set<Locale> locales = LocaleUtils.availableLocaleSet();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @ParameterizedTest
    @ValueSource(strings = {"en", "fr"})
    void testCountriesByLanguage(String languageCode) {
        List<Locale> countries = LocaleUtils.countriesByLanguage(languageCode);
        assertNotNull(countries);
        if (languageCode != null && !languageCode.isEmpty()) {
            assertTrue(countries.stream().allMatch(locale -> languageCode.equals(locale.getLanguage())));
        }
    }

    @Test
    void testIsAvailableLocale() {
        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx", "YY")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"null", "und", "en"})
    void testIsLanguageUndetermined(String languageCode) {
        Locale locale = null;
        if (!"null".equals(languageCode)) {
            locale = new Locale(languageCode);
        }
        boolean result = LocaleUtils.isLanguageUndetermined(locale);
        if (locale == null || "und".equals(locale.toLanguageTag())) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"US", "FR"})
    void testLanguagesByCountry(String countryCode) {
        List<Locale> languages = LocaleUtils.languagesByCountry(countryCode);
        assertNotNull(languages);
        if (countryCode != null && !countryCode.isEmpty()) {
            assertTrue(languages.stream().allMatch(locale -> countryCode.equals(locale.getCountry())));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"null", "en_US"})
    void testLocaleLookupList(String localeStr) {
        Locale locale = null;
        if (!"null".equals(localeStr)) {
            String[] parts = localeStr.split("_");
            if (parts.length == 1) {
                locale = new Locale(parts[0]);
            } else if (parts.length == 2) {
                locale = new Locale(parts[0], parts[1]);
            }
        }
        List<Locale> lookupList = LocaleUtils.localeLookupList(locale);
        assertNotNull(lookupList);
        if (locale != null) {
            assertTrue(lookupList.contains(locale));
        }
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"null", "en_US"})
    void testToLocaleWithLocale(String localeStr) {
        Locale locale = null;
        if (!"null".equals(localeStr)) {
            String[] parts = localeStr.split("_");
            if (parts.length == 1) {
                locale = new Locale(parts[0]);
            } else if (parts.length == 2) {
                locale = new Locale(parts[0], parts[1]);
            }
        }
        Locale result = LocaleUtils.toLocale(locale);
        assertNotNull(result);
        if (locale != null) {
            assertEquals(locale, result);
        } else {
            assertEquals(Locale.getDefault(), result);
        }
    }

    }
