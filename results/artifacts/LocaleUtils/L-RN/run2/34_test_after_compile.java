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
            assertFalse(countries.stream().anyMatch(locale -> locale.getCountry().isEmpty()));
        }
    }

    @Test
    void testIsAvailableLocale() {
        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx", "YY")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"und"})
    void testIsLanguageUndetermined(String languageTag) {
        assertTrue(LocaleUtils.isLanguageUndetermined(new Locale(languageTag)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"US", "FR"})
    void testLanguagesByCountry(String countryCode) {
        List<Locale> languages = LocaleUtils.languagesByCountry(countryCode);
        assertNotNull(languages);
        if (countryCode != null && !countryCode.isEmpty()) {
            assertFalse(languages.stream().anyMatch(locale -> locale.getLanguage().isEmpty()));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"US"})
    void testLocaleLookupList(String languageTag) {
        List<Locale> lookupList = LocaleUtils.localeLookupList(new Locale(languageTag));
        assertNotNull(lookupList);
        assertTrue(lookupList.contains(new Locale(languageTag)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"US"})
    void testLocaleLookupListWithDefault(String languageTag) {
        List<Locale> lookupList = LocaleUtils.localeLookupList(new Locale(languageTag), Locale.UK);
        assertNotNull(lookupList);
        assertTrue(lookupList.contains(Locale.UK));
    }

    @ParameterizedTest
    @ValueSource(strings = {"und"})
    void testToLocale(String languageTag) {
        Locale result = LocaleUtils.toLocale(new Locale(languageTag));
        assertNotNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"en", "fr_FR", "en_US_WIN"})
    void testToLocal(String str) {
        if (str == null || str.isEmpty() || str.equals("_FR") || str.equals("-FR")) {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale(str));
        } else {
            Locale result = LocaleUtils.toLocale(str);
            assertNotNull(result);
        }
    }

    @Test
    void testToLocalWithInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#WIN"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCountriesByLanguageWithEmptyCode() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    
    
    
    
    
    @Test
        void testToLocalWithNull() {
            assertNull(LocaleUtils.toLocale((String) null));
        }

    @Test
        void testToLocalWithEmptyString() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), locale);
        }

    @Test
        void testToLocalWithInvalidFormatTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("a"));
        }

    
    @Test
        void testToLocalWithInvalidFormatTooShortAfterSeparator() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_"));
        }

    @Test
        void testToLocalWithInvalidFormatIncorrectCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_USA"));
        }

    @Test
        void testToLocalWithValidLanguageOnly() {
            Locale locale = LocaleUtils.toLocale("fr");
            assertEquals(new Locale("fr"), locale);
        }

    @Test
        void testToLocalWithValidCountryCode() {
            Locale locale = LocaleUtils.toLocale("_US");
            assertEquals(new Locale("", "US"), locale);
        }

    @Test
        void testToLocalWithValidLanguageAndCountryCode() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(new Locale("en", "US"), locale);
        }

    @Test
        void testToLocalWithInvalidFormatScript() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#Latn"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCountriesByLanguageWithNullCode() {
            List<Locale> result = LocaleUtils.countriesByLanguage(null);
            assertTrue(result.isEmpty());
        }

    
    @Test
        void testLanguagesByCountryWithNullCode() {
            List<Locale> result = LocaleUtils.languagesByCountry(null);
            assertTrue(result.isEmpty());
        }

    @Test
        void testIsLanguageUndeterminedWithUndeterminedLocale() {
            Locale undeterminedLocale = new Locale("und");
            assertTrue(LocaleUtils.isLanguageUndetermined(undeterminedLocale));
        }

    @Test
        void testIsLanguageUndeterminedWithNullLocale() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        void testIsLanguageUndeterminedWithNonUndeterminedLocale() {
            Locale nonUndeterminedLocale = new Locale("en");
            assertFalse(LocaleUtils.isLanguageUndetermined(nonUndeterminedLocale));
        }

    
    
    
    
    @Test
        void testLocaleLookupListWithDefaultLocaleAlreadyInList() {
            Locale defaultLocale = new Locale("es", "ES");
            List<Locale> result = LocaleUtils.localeLookupList(defaultLocale, defaultLocale);
            assertEquals(2, result.size());
            assertEquals(new Locale("es", "ES"), result.get(0));
            assertEquals(new Locale("es"), result.get(1));
        }

    @Test
        void testToLocalWithHashInString() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#POSIX"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testCountriesByLanguageWithNonExistentCode() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("xyz");
            assertTrue(countries.isEmpty());
        }

    @Test
        void testLanguagesByCountryWithNonExistentCode() {
            List<Locale> languages = LocaleUtils.languagesByCountry("XYZ");
            assertTrue(languages.isEmpty());
        }

    @Test
        void testLocaleLookupListWithNullLocaleAndDefaultLocale() {
            List<Locale> lookupList = LocaleUtils.localeLookupList(null, Locale.US);
            assertEquals(List.of(Locale.US), lookupList);
        }

    @Test
        void testLocaleLookupListWithVariantLocale() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> lookupList = LocaleUtils.localeLookupList(locale);
            assertEquals(List.of(locale, new Locale("en", "US"), new Locale("en")), lookupList);
        }

    @Test
        void testLocaleLookupListWithCountryOnlyLocale() {
            Locale locale = new Locale("", "FR");
            List<Locale> lookupList = LocaleUtils.localeLookupList(locale);
            assertEquals(List.of(locale, new Locale("")), lookupList);
        }

    @Test
        void testLocaleLookupListWithLanguageOnlyLocale() {
            Locale locale = new Locale("es");
            List<Locale> lookupList = LocaleUtils.localeLookupList(locale);
            assertEquals(List.of(locale), lookupList);
        }

    @Test
        void testToLocalWithHashInStringAtEnd() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#"));
        }

    @Test
        void testToLocalWithInvalidCountryCodeTooLong() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_ABCD"));
        }

    @Test
        void testToLocalWithInvalidLanguageCodeTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e_US"));
        }

    @Test
        void testToLocalWithInvalidCountryCodeTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_U"));
        }
}
