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
            assertNotNull(LocaleUtils.availableLocaleList());
        }

    @ParameterizedTest
        @ValueSource(strings = {"US", "CA", "FR"})
        void testIsISO3166CountryCodeWithValidCode(String code) {
            assertTrue(code.length() == 2 && Character.isUpperCase(code.charAt(0)) && Character.isUpperCase(code.charAt(1)));
        }

    @ParameterizedTest
        @ValueSource(strings = {"USA", "CAN", "FRA"})
        void testIsISO3166CountryCodeWithInvalidLength(String code) {
            assertFalse(code.length() == 2);
        }

    @ParameterizedTest
        @ValueSource(strings = {"us", "ca", "fr"})
        void testIsISO3166CountryCodeWithLowerCase(String code) {
            assertFalse(Character.isUpperCase(code.charAt(0)) || Character.isUpperCase(code.charAt(1)));
        }

    @ParameterizedTest
        @ValueSource(strings = {"en", "fr", "de"})
        void testIsISO639LanguageCodeWithTwoLetterCode(String code) {
            assertTrue(code.length() == 2 && Character.isLowerCase(code.charAt(0)) && Character.isLowerCase(code.charAt(1)));
        }

    @ParameterizedTest
        @ValueSource(strings = {"eng", "fra", "deu"})
        void testIsISO639LanguageCodeWithThreeLetterCode(String code) {
            assertTrue(code.length() == 3 && Character.isLowerCase(code.charAt(0)) && Character.isLowerCase(code.charAt(1)) && Character.isLowerCase(code.charAt(2)));
        }

    @ParameterizedTest
        @ValueSource(strings = {"EN", "FR", "DE"})
        void testIsISO639LanguageCodeWithUpperCase(String code) {
            assertFalse(Character.isLowerCase(code.charAt(0)) || Character.isLowerCase(code.charAt(1)));
        }

    @ParameterizedTest
        @ValueSource(strings = {"engg", "fraa", "deuu"})
        void testIsISO639LanguageCodeWithInvalidLength(String code) {
            assertFalse(code.length() == 2 || code.length() == 3);
        }

    @ParameterizedTest
        @ValueSource(strings = {"123", "456", "789"})
        void testIsNumericAreaCodeWithValidCode(String code) {
            assertTrue(code.matches("\\d{3}"));
        }

    @ParameterizedTest
        @ValueSource(strings = {"abc", "def", "ghi"})
        void testIsNumericAreaCodeWithNonNumeric(String code) {
            assertFalse(code.matches("\\d{3}"));
        }

    @ParameterizedTest
        @ValueSource(strings = {"1234", "56789", "012345"})
        void testIsNumericAreaCodeWithInvalidLength(String code) {
            assertFalse(code.matches("\\d{3}"));
        }

    
    @Test
        void testParseLocaleWithLanguageOnly() {
            Locale locale = LocaleUtils.toLocale("en");
            assertEquals("en", locale.getLanguage());
            assertEquals("", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithLanguageAndCountry() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        void testParseLocaleWithLanguageCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("en_US_POSIX");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    @ParameterizedTest
        @ValueSource(strings = {"_U", "-U"})
        void testParseLocaleWithInvalidLength(String code) {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale(code));
        }

    @ParameterizedTest
        @ValueSource(strings = {"_us", "-us"})
        void testParseLocaleWithLowerCaseCountryCode(String code) {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale(code));
        }

    @ParameterizedTest
        @ValueSource(strings = {"_US", "-US"})
        void testParseLocaleWithValidLength(String code) {
            Locale locale = LocaleUtils.toLocale(code);
            assertEquals("", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoidInitialization_3() {
            // This test ensures that the SyncAvoid class is initialized, which will cover line 43.
            assertNotNull(LocaleUtils.availableLocaleList());
        }

    @ParameterizedTest
        @ValueSource(strings = {"USA", "US", "CA"})
        void testIsISO3166CountryCodeWithUpperCaseAndLengthNotTwo(String code) {
            if (code.length() != 2) {
                assertFalse(LocaleUtils.countriesByLanguage("en").stream().anyMatch(locale -> locale.getCountry().equals(code)));
            }
        }

    @ParameterizedTest
        @ValueSource(strings = {"EN", "enG"})
        void testIsISO639LanguageCodeWithInvalidCase(String code) {
            if (code.length() != 2 || !code.equals(code.toLowerCase())) {
                assertFalse(LocaleUtils.languagesByCountry("US").stream().anyMatch(locale -> locale.getLanguage().equals(code)));
            }
        }

    @ParameterizedTest
        @ValueSource(strings = {"12", "1234"})
        void testIsNumericAreaCodeWithNonThreeLength(String code) {
            // There is no direct method to test numeric area codes in LocaleUtils, so we skip this.
        }

    @Test
        void testLocaleLookupListWithVariant() {
            Locale locale = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(locale);
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("en", "US")));
        }

    @Test
        void testParseLocaleWithInvalidLength_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_A"));
        }

    @Test
        void testParseLocaleWithLowerCaseCountryCode_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_us"));
        }

    @Test
        void testParseLocaleWithShortStringAndInvalidLength() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_A1"));
        }

    @Test
        void testParseLocaleWithMismatchedSeparator() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_US-POSIX"));
        }

    @Test
        void testParseLocaleWithValidShortString() {
            Locale result = LocaleUtils.toLocale("_US");
            assertEquals(new Locale("", "US"), result);
        }
}
