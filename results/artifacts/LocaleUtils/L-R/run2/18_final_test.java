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
}
