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
        void testSyncAvoidInitialization() {
            // Accessing the availableLocaleList to trigger SyncAvoid initialization
            List<Locale> locales = LocaleUtils.availableLocaleList();
            assertNotNull(locales);
        }

    @Test
        void testCountriesByLanguageWithNull() {
            List<Locale> result = LocaleUtils.countriesByLanguage(null);
            assertTrue(result.isEmpty());
        }

    @Test
        void testIsISO3166CountryCodeWithInvalidLength() {
            assertFalse(LocaleUtils.isISO3166CountryCode("USA"));
        }

    @Test
        void testIsISO3166CountryCodeWithLowerCase() {
            assertFalse(LocaleUtils.isISO3166CountryCode("us"));
        }

    @Test
        void testIsISO639LanguageCodeWithInvalidLength() {
            assertFalse(LocaleUtils.isISO639LanguageCode("engg"));
        }

    @Test
        void testIsISO639LanguageCodeWithUpperCase() {
            assertFalse(LocaleUtils.isISO639LanguageCode("EN"));
        }

    @Test
        void testIsLanguageUndeterminedWithNullLocale() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        void testIsLanguageUndeterminedWithUndeterminedLocale() {
            Locale undeterminedLocale = new Locale.Builder().setLanguage("und").build();
            assertTrue(LocaleUtils.isLanguageUndetermined(undeterminedLocale));
        }

    @Test
        void testIsNumericAreaCodeWithInvalidLength() {
            assertFalse(LocaleUtils.isNumericAreaCode("12"));
        }

    @Test
        void testIsNumericAreaCodeWithNonNumeric() {
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
        }

    @Test
        void testLanguagesByCountryWithNull() {
            List<Locale> result = LocaleUtils.languagesByCountry(null);
            assertTrue(result.isEmpty());
        }

    @Test
        void testLocaleLookupListWithNullLocale() {
            List<Locale> result = LocaleUtils.localeLookupList(null, Locale.US);
            assertEquals(1, result.size());
            assertEquals(Locale.US, result.get(0));
        }

    @Test
        void testLocaleLookupListWithEmptyVariantAndCountry() {
            Locale locale = new Locale("en");
            List<Locale> result = LocaleUtils.localeLookupList(locale, Locale.US);
            assertEquals(2, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(Locale.US, result.get(1));
        }

    @Test
        void testLocaleLookupListWithVariant() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(locale, Locale.US);
            assertEquals(3, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(new Locale("en", "US"), result.get(1));
            assertEquals(Locale.US, result.get(2));
        }

    @Test
        void testLocaleLookupListWithCountry() {
            Locale locale = new Locale("en", "US");
            List<Locale> result = LocaleUtils.localeLookupList(locale, Locale.US);
            assertEquals(3, result.size());
            assertEquals(locale, result.get(0));
            assertEquals(new Locale("en"), result.get(1));
            assertEquals(Locale.US, result.get(2));
        }

    @Test
        void testToLocaleWithNullString() {
            assertNull(LocaleUtils.toLocale((String) null));
        }

    @Test
        void testToLocaleWithEmptyString() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals("", locale.getLanguage());
            assertEquals("", locale.getCountry());
        }

    @Test
        void testToLocaleWithInvalidFormatTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("a"));
        }

    @Test
        void testToLocaleWithInvalidFormatUnderscorePrefixTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_A"));
        }

    @Test
        void testToLocaleWithInvalidFormatDashPrefixTooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("-A"));
        }

    @Test
        void testToLocaleWithInvalidFormatUnderscorePrefixNonUpperCase() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_aB"));
        }

    @Test
        void testToLocaleWithInvalidFormatDashPrefixNonUpperCase() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("-ab"));
        }

    @Test
        void testToLocaleWithThreeLetterCountryCode() {
            Locale locale = LocaleUtils.toLocale("en_USA");
            assertEquals("", locale.getLanguage());
            assertEquals("USA", locale.getCountry());
        }

    @Test
        void testToLocaleWithFourLetterCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("_US_POSIX");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    @Test
        void testConstructorIsPrivate() {
            assertThrows(UnsupportedOperationException.class, () -> {
                try {
                    LocaleUtils.class.getDeclaredConstructor().newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new UnsupportedOperationException(e);
                }
            });
        }
}
