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
    public void testCountriesByLanguage_NormalCase() {
        List<Locale> countries = LocaleUtils.countriesByLanguage("en");
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    @Test
    public void testCountriesByLanguage_Null() {
        List<Locale> countries = LocaleUtils.countriesByLanguage(null);
        assertTrue(countries.isEmpty());
    }

    @Test
    public void testIsAvailableLocale_NormalCase() {
        Locale locale = new Locale("en", "US");
        assertTrue(LocaleUtils.isAvailableLocale(locale));
    }

    @Test
    public void testIsAvailableLocale_NotAvailable() {
        Locale locale = new Locale("xx", "YY");
        assertFalse(LocaleUtils.isAvailableLocale(locale));
    }

    @Test
    public void testIsLanguageUndetermined_NormalCase() {
        Locale locale = new Locale("en", "US");
        assertFalse(LocaleUtils.isLanguageUndetermined(locale));
    }

    @Test
    public void testIsLanguageUndetermined_Null() {
        assertTrue(LocaleUtils.isLanguageUndetermined(null));
    }

    @Test
    public void testLanguagesByCountry_NormalCase() {
        List<Locale> languages = LocaleUtils.languagesByCountry("US");
        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }

    @Test
    public void testLanguagesByCountry_Null() {
        List<Locale> languages = LocaleUtils.languagesByCountry(null);
        assertTrue(languages.isEmpty());
    }

    
    
    
    @Test
    public void testToLocale_NormalCase() {
        Locale locale = new Locale("en", "US");
        Locale result = LocaleUtils.toLocale(locale);
        assertEquals(locale, result);
    }

    
    
    @Test
    public void testToLocale_InvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_EmptyLanguageCode() {
            List<Locale> result = LocaleUtils.countriesByLanguage("");
            assertTrue(result.isEmpty());
        }

    @Test
        public void testCountriesByLanguage_InvalidLanguageCode() {
            List<Locale> result = LocaleUtils.countriesByLanguage("xyz");
            assertTrue(result.isEmpty());
        }

    
    @Test
        public void testLanguagesByCountry_InvalidCountryCode() {
            List<Locale> result = LocaleUtils.languagesByCountry("xyz");
            assertTrue(result.isEmpty());
        }

    @Test
        public void testIsLanguageUndetermined_EmptyLocale() {
            boolean result = LocaleUtils.isLanguageUndetermined(new Locale("", ""));
            assertTrue(result);
        }

    @Test
        public void testIsLanguageUndetermined_NullLocale() {
            boolean result = LocaleUtils.isLanguageUndetermined(null);
            assertTrue(result);
        }

    @Test
        public void testToLocale_EmptyString() {
            Locale result = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), result);
        }

    @Test
        public void testToLocale_InvalidFormat_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en#US"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_EmptyLocale() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    
    
    @Test
        public void testToLocale_EmptyString_2() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(Locale.ROOT, locale);
        }

    
    
    @Test
        public void testToLocale_InvalidFormat_5() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("--US"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_EmptyLocale_2() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    
    
    @Test
        public void testToLocale_EmptyString_3() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale(StringUtils.EMPTY, StringUtils.EMPTY), locale);
        }

    @Test
        public void testToLocale_InvalidFormat_6() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-US#script"));
        }
}
