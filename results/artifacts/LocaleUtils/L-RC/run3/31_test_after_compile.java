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
        void testSyncAvoidInitialization() {
            List<Locale> locales = LocaleUtils.availableLocaleList();
            assertNotNull(locales);
            assertFalse(locales.isEmpty());
        }

    @Test
        void testCountriesByLanguageWithNull() {
            List<Locale> result = LocaleUtils.countriesByLanguage(null);
            assertTrue(result.isEmpty());
        }

    @Test
        void testLanguagesByCountryWithNull() {
            List<Locale> result = LocaleUtils.languagesByCountry(null);
            assertTrue(result.isEmpty());
        }

    
    
    @Test
        void testLocaleLookupListWithoutCountry() {
            Locale locale = new Locale("en");
            List<Locale> result = LocaleUtils.localeLookupList(locale, Locale.getDefault());
            assertEquals(2, result.size());
            assertEquals(new Locale("en"), result.get(0));
            assertEquals(Locale.getDefault(), result.get(1));
        }

    @Test
        void testToLocaleWithNull() {
            assertNull(LocaleUtils.toLocale((String) null));
        }

    @Test
        void testToLocaleWithEmptyString() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), locale);
        }

    @Test
        void testToLocaleWithInvalidScriptExtensions() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#POSIX"));
        }

    @Test
        void testToLocaleWithShortString() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e"));
        }

    @Test
        void testToLocaleWithUnderscorePrefix() {
            Locale locale = LocaleUtils.toLocale("_US");
            assertEquals(new Locale("", "US"), locale);
        }

    @Test
        void testToLocaleWithDashPrefix() {
            Locale locale = LocaleUtils.toLocale("-US");
            assertEquals(new Locale("", "US"), locale);
        }

    @Test
        void testToLocaleWithInvalidCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_USA"));
        }

    @Test
        void testToLocaleConstructor() {
            new LocaleUtils();
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoidInitialization_2() {
            // This test ensures that the SyncAvoid class is initialized
            assertNotNull(LocaleUtils.availableLocaleList());
        }

    @Test
        void testIsISO3166CountryCodeWithValidCode() {
            assertTrue(LocaleUtils.isISO3166CountryCode("US"));
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
        void testIsISO639LanguageCodeWithTwoLetterCode() {
            assertTrue(LocaleUtils.isISO639LanguageCode("en"));
        }

    @Test
        void testIsISO639LanguageCodeWithThreeLetterCode() {
            assertTrue(LocaleUtils.isISO639LanguageCode("eng"));
        }

    @Test
        void testIsISO639LanguageCodeWithUpperCase() {
            assertFalse(LocaleUtils.isISO639LanguageCode("EN"));
        }

    @Test
        void testIsISO639LanguageCodeWithInvalidLength() {
            assertFalse(LocaleUtils.isISO639LanguageCode("engg"));
        }

    @Test
        void testIsNumericAreaCodeWithValidCode() {
            assertTrue(LocaleUtils.isNumericAreaCode("123"));
        }

    @Test
        void testIsNumericAreaCodeWithNonNumeric() {
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
        }

    @Test
        void testIsNumericAreaCodeWithInvalidLength() {
            assertFalse(LocaleUtils.isNumericAreaCode("1234"));
        }

    @Test
        void testLocaleLookupListWithVariant() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("en", "US")));
        }

    @Test
        void testParseLocaleWithLanguageOnly() {
            Locale locale = LocaleUtils.parseLocale("en");
            assertEquals("en", locale.getLanguage());
            assertEquals("", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithLanguageAndCountry() {
            Locale locale = LocaleUtils.parseLocale("en_US");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithLanguageCountryAndVariant() {
            Locale locale = LocaleUtils.parseLocale("en_US_POSIX");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    @Test
        void testParseLocaleWithUnderscorePrefixInvalidLength() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("_US"));
        }

    @Test
        void testParseLocaleWithDashPrefixInvalidLength() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("-US"));
        }

    @Test
        void testParseLocaleWithUnderscorePrefixInvalidCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("_us"));
        }

    @Test
        void testParseLocaleWithDashPrefixInvalidCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("-us"));
        }

    @Test
        void testParseLocaleWithUnderscorePrefixValidLength() {
            Locale locale = LocaleUtils.parseLocale("_US");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithDashPrefixValidLength() {
            Locale locale = LocaleUtils.parseLocale("-US");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithUnderscorePrefixAndVariant() {
            Locale locale = LocaleUtils.parseLocale("_US_POSIX");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    @Test
        void testParseLocaleWithDashPrefixAndVariant() {
            Locale locale = LocaleUtils.parseLocale("-US_POSIX");
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }
}
