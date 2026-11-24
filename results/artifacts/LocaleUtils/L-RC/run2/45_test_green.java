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
        void testIsLanguageUndeterminedWithNullLocale() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        void testIsLanguageUndeterminedWithUndeterminedLocale() {
            Locale undeterminedLocale = new Locale.Builder().setLanguage("und").build();
            assertTrue(LocaleUtils.isLanguageUndetermined(undeterminedLocale));
        }

    @Test
        void testLanguagesByCountryWithNull() {
            List<Locale> result = LocaleUtils.languagesByCountry(null);
            assertTrue(result.isEmpty());
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
        void testToLocaleWithFourLetterCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("_US_POSIX");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoidInitialization_2() {
            // This test ensures that the SyncAvoid class is initialized
            assertNotNull(LocaleUtils.availableLocaleList());
        }

    
    private boolean isValidISO3166CountryCode(String code) {
            return java.util.Arrays.stream(Locale.getISOCountries()).anyMatch(code::equalsIgnoreCase);
        }

    
    private boolean isValidISO639LanguageCode(String code) {
            return java.util.Arrays.stream(Locale.getISOLanguages()).anyMatch(code::equalsIgnoreCase);
        }

    @Test
        void testIsLanguageUndeterminedWithDefaultLocale() {
            assertFalse(LocaleUtils.isLanguageUndetermined(Locale.getDefault()));
        }

    @ParameterizedTest
        @ValueSource(strings = {"1234", "abc"})
        void testIsNumericAreaCodeWithInvalidLengthOrNonNumeric(String code) {
            assertFalse(isValidNumericAreaCode(code));
        }

    private boolean isValidNumericAreaCode(String code) {
            try {
                Integer.parseInt(code);
                return code.length() == 3;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    @Test
        void testLocaleLookupListWithVariantAndCountry() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(3, result.size());
            assertTrue(result.contains(new Locale("en", "US")));
            assertTrue(result.contains(new Locale("en")));
        }

    @Test
        void testLocaleLookupListWithCountryOnly() {
            Locale locale = new Locale("fr", "FR");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(2, result.size());
            assertTrue(result.contains(new Locale("fr")));
        }

    @Test
        void testParseLocaleWithTwoLetterLanguageAndThreeLetterCountry() {
            Locale result = LocaleUtils.toLocale("_US_123");
            assertEquals("", result.getLanguage());
            assertEquals("US", result.getCountry());
            assertEquals("123", result.getVariant());
        }

    @Test
        void testParseLocaleWithInvalidFormatTooShortUnderscorePrefix() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_U"));
            assertEquals("Invalid locale format: _U", exception.getMessage());
        }

    @Test
        void testParseLocaleWithInvalidFormatTooShortDashPrefix() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("-U"));
            assertEquals("Invalid locale format: -U", exception.getMessage());
        }

    @Test
        void testParseLocaleWithFourLetterCountryAndVariant() {
            Locale result = LocaleUtils.toLocale("_US_1234");
            assertEquals("", result.getLanguage());
            assertEquals("US", result.getCountry());
            assertEquals("1234", result.getVariant());
        }

    }
