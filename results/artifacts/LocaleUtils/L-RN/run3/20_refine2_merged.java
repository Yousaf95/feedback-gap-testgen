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

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testCountriesByLanguageWithNonExistentLanguage() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("xyz");
            assertTrue(countries.isEmpty());
        }

    @Test
        void testLanguagesByCountryWithNonExistentCountry() {
            List<Locale> languages = LocaleUtils.languagesByCountry("XY");
            assertTrue(languages.isEmpty());
        }

    
    
    
    
    @Test
        void testIsLanguageUndeterminedWithNullLocale() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        void testIsLanguageUndeterminedWithUndeterminedLocale() {
            Locale undeterminedLocale = new Locale("und");
            assertTrue(LocaleUtils.isLanguageUndetermined(undeterminedLocale));
        }

    @Test
        void testIsLanguageUndeterminedWithDeterminedLocale() {
            Locale determinedLocale = new Locale("en", "US");
            assertFalse(LocaleUtils.isLanguageUndetermined(determinedLocale));
        }

    @Test
        void testToLocaleWithNullString() {
            assertNull(LocaleUtils.toLocale((String) null));
        }

    @Test
        void testToLocaleWithEmptyString() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), locale);
        }

    @Test
        void testToLocaleWithInvalidFormatTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("a"));
        }

    
    
    @Test
        void testToLocaleWithValidCountryCodeOnly() {
            Locale locale = LocaleUtils.toLocale("_FR");
            assertEquals(new Locale("", "FR"), locale);
        }

    @Test
        void testToLocaleWithValidLanguageAndCountryCode() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(new Locale("en", "US"), locale);
        }

    @Test
        void testToLocaleWithValidLanguageCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("en_US_POSIX");
            assertEquals(new Locale("en", "US", "POSIX"), locale);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCountriesByLanguageWithEmptyLanguageCode() {
            List<Locale> result = LocaleUtils.countriesByLanguage("");
            assertTrue(result.isEmpty());
        }

    @Test
        void testLanguagesByCountryWithEmptyCountryCode() {
            List<Locale> result = LocaleUtils.languagesByCountry("");
            assertTrue(result.isEmpty());
        }

    @Test
        void testLocaleLookupListWithNullLocaleAndDefaultLocale() {
            List<Locale> result = LocaleUtils.localeLookupList(null, Locale.US);
            assertEquals(1, result.size());
            assertEquals(Locale.US, result.get(0));
        }

    @Test
        void testLocaleLookupListWithVariantLocale() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(4, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(new Locale("en", "US"), result.get(1));
            assertEquals(new Locale("en"), result.get(2));
            assertEquals(Locale.getDefault(), result.get(3));
        }

    @Test
        void testLocaleLookupListWithCountryOnlyLocale() {
            Locale locale = new Locale("fr", "FR");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(4, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(new Locale("fr"), result.get(1));
            assertEquals(Locale.getDefault(), result.get(2));
        }

    @Test
        void testLocaleLookupListWithLanguageOnlyLocale() {
            Locale locale = new Locale("de");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(4, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(Locale.getDefault(), result.get(1));
        }

    @Test
        void testToLocaleWithValidLanguageAndCountryCodeUsingDash() {
            Locale result = LocaleUtils.toLocale("en-US");
            assertEquals(new Locale("en", "US"), result);
        }

    @Test
        void testToLocaleWithValidLanguageAndCountryCodeUsingUnderscore() {
            Locale result = LocaleUtils.toLocale("en_US");
            assertEquals(new Locale("en", "US"), result);
        }

    @Test
        void testToLocaleWithInvalidFormatTooLong() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US_POSIX_EXTRA"));
        }

    @Test
        void testToLocaleWithValidLanguageCountryAndVariantUsingDash() {
            Locale result = LocaleUtils.toLocale("en-US-POSIX");
            assertEquals(new Locale("en", "US", "POSIX"), result);
        }

    @Test
        void testToLocaleWithValidLanguageCountryAndVariantUsingUnderscore() {
            Locale result = LocaleUtils.toLocale("en_US_POSIX");
            assertEquals(new Locale("en", "US", "POSIX"), result);
        }
}
