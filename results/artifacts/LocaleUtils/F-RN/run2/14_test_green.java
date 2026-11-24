package org.apache.commons.lang3;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtils_GenTest {

    @Test
    public void testAvailableLocaleList() {
        List<Locale> locales = LocaleUtils.availableLocaleList();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
    }

    @Test
    public void testAvailableLocaleSet() {
        Set<Locale> locales = LocaleUtils.availableLocaleSet();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
    }

    @Test
    public void testCountriesByLanguage_validLanguageCode() {
        List<Locale> countries = LocaleUtils.countriesByLanguage("en");
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    @Test
    public void testCountriesByLanguage_nullLanguageCode() {
        List<Locale> countries = LocaleUtils.countriesByLanguage(null);
        assertTrue(countries.isEmpty());
    }

    @Test
    public void testIsAvailableLocale_validLocale() {
        boolean result = LocaleUtils.isAvailableLocale(Locale.US);
        assertTrue(result);
    }

    @Test
    public void testIsAvailableLocale_nullLocale() {
        boolean result = LocaleUtils.isAvailableLocale(null);
        assertFalse(result);
    }

    @Test
    public void testIsLanguageUndetermined_validLocale() {
        boolean result = LocaleUtils.isLanguageUndetermined(Locale.US);
        assertFalse(result);
    }

    @Test
    public void testIsLanguageUndetermined_nullLocale() {
        boolean result = LocaleUtils.isLanguageUndetermined(null);
        assertTrue(result);
    }

    @Test
    public void testLanguagesByCountry_validCountryCode() {
        List<Locale> languages = LocaleUtils.languagesByCountry("US");
        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }

    @Test
    public void testLanguagesByCountry_nullCountryCode() {
        List<Locale> languages = LocaleUtils.languagesByCountry(null);
        assertTrue(languages.isEmpty());
    }

    
    
    @Test
    public void testToLocale_validLocale() {
        Locale locale = LocaleUtils.toLocale(Locale.US);
        assertEquals(Locale.US, locale);
    }

    
    @Test
    public void testToLocale_validString() {
        Locale locale = LocaleUtils.toLocale("en_US");
        assertNotNull(locale);
        assertEquals("en", locale.getLanguage());
        assertEquals("US", locale.getCountry());
    }

    @Test
    public void testToLocale_emptyString() {
        Locale locale = LocaleUtils.toLocale("");
        assertNotNull(locale);
        assertEquals("", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    @Test
    public void testToLocale_invalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en#US"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_emptyLanguageCode() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    
    @Test
        public void testToLocale_invalidFormat_withHash() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en#US"));
        }

    @Test
        public void testToLocale_invalidFormat_tooShort() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e"));
        }

    @Test
        public void testToLocale_validString_withCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("en_US_WIN");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("WIN", locale.getVariant());
        }

    @Test
        public void testToLocale_validString_withCountry() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertTrue(locale.getVariant().isEmpty());
        }

    @Test
        public void testToLocale_validString_withLanguageOnly() {
            Locale locale = LocaleUtils.toLocale("en");
            assertEquals("en", locale.getLanguage());
            assertTrue(locale.getCountry().isEmpty());
            assertTrue(locale.getVariant().isEmpty());
        }
}
