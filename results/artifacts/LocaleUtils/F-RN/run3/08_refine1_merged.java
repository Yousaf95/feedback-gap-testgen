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
    public void testToLocale_emptyString() {
        Locale locale = LocaleUtils.toLocale("");
        assertEquals(new Locale("", ""), locale);
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
    public void testToLocale_invalidFormat_missingCountry() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_invalidLanguageCode() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("invalid");
            assertTrue(countries.isEmpty());
        }

    @Test
        public void testLanguagesByCountry_invalidCountryCode() {
            List<Locale> languages = LocaleUtils.languagesByCountry("invalid");
            assertTrue(languages.isEmpty());
        }

    @Test
        public void testIsAvailableLocale_nonExistentLocale() {
            Locale nonExistentLocale = new Locale("zz", "ZZ");
            assertFalse(LocaleUtils.isAvailableLocale(nonExistentLocale));
        }

    @Test
        public void testToLocale_nullString() {
            Locale locale = LocaleUtils.toLocale((String) null);
            assertEquals(Locale.getDefault(), locale);
        }

    @Test
        public void testToLocale_emptyString_2() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), locale);
        }

    @Test
        public void testToLocale_validLanguageCountryVariant() {
            Locale locale = LocaleUtils.toLocale("en_US_WIN");
            assertEquals(new Locale("en", "US", "WIN"), locale);
        }

    @Test
        public void testToLocale_missingCountry() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_"));
        }

    @Test
        public void testToLocale_missingVariant() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(new Locale("en", "US"), locale);
        }
}
