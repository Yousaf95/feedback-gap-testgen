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
        public void testIsISO3166CountryCode_upperCaseTwoLetters() {
            assertTrue(LocaleUtils.isISO3166CountryCode("US"));
        }

    @Test
        public void testIsISO3166CountryCode_lowerCaseTwoLetters() {
            assertFalse(LocaleUtils.isISO3166CountryCode("us"));
        }

    @Test
        public void testIsISO3166CountryCode_threeLetters() {
            assertFalse(LocaleUtils.isISO3166CountryCode("USA"));
        }

    @Test
        public void testIsISO3166CountryCode_null() {
            assertFalse(LocaleUtils.isISO3166CountryCode(null));
        }

    @Test
        public void testIsISO639LanguageCode_lowerCaseTwoLetters() {
            assertTrue(LocaleUtils.isISO639LanguageCode("en"));
        }

    @Test
        public void testIsISO639LanguageCode_upperCaseTwoLetters() {
            assertFalse(LocaleUtils.isISO639LanguageCode("EN"));
        }

    @Test
        public void testIsISO639LanguageCode_lowerCaseThreeLetters() {
            assertTrue(LocaleUtils.isISO639LanguageCode("eng"));
        }

    @Test
        public void testIsISO639LanguageCode_upperCaseThreeLetters() {
            assertFalse(LocaleUtils.isISO639LanguageCode("ENG"));
        }

    @Test
        public void testIsISO639LanguageCode_null() {
            assertFalse(LocaleUtils.isISO639LanguageCode(null));
        }

    @Test
        public void testIsNumericAreaCode_numericThreeDigits() {
            assertTrue(LocaleUtils.isNumericAreaCode("123"));
        }

    @Test
        public void testIsNumericAreaCode_nonNumericString() {
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
        }

    @Test
        public void testIsNumericAreaCode_null() {
            assertFalse(LocaleUtils.isNumericAreaCode(null));
        }

    @Test
        public void testLocaleLookupList_localeNotNull() {
            List<Locale> result = LocaleUtils.localeLookupList(new Locale("en", "US"));
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("en", "US")));
            assertTrue(result.contains(new Locale("en", "")));
            assertTrue(result.contains(new Locale("", "US")));
        }

    @Test
        public void testLocaleLookupList_localeNull() {
            List<Locale> result = LocaleUtils.localeLookupList(null, new Locale("en", "US"));
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("", "")));
            assertTrue(result.contains(new Locale("", "US")));
            assertTrue(result.contains(new Locale("en", "")));
        }

    @Test
        public void testLocaleLookupList_defaultLocaleNull() {
            List<Locale> result = LocaleUtils.localeLookupList(new Locale("en", "US"), null);
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("en", "US")));
            assertTrue(result.contains(new Locale("", "")));
            assertTrue(result.contains(new Locale("", "US")));
        }

    @Test
        public void testLocaleLookupList_localeAndDefaultLocaleNull() {
            List<Locale> result = LocaleUtils.localeLookupList(null, null);
            assertEquals(4, result.size());
            assertTrue(result.contains(new Locale("", "")));
            assertTrue(result.contains(new Locale("", "")));
            assertTrue(result.contains(new Locale("", "")));
        }

    @Test
        public void testParseLocale_validLanguageCode() {
            Locale locale = LocaleUtils.parseLocale("en");
            assertEquals(Locale.ENGLISH, locale);
        }

    @Test
        public void testParseLocale_validCountryAndLanguageCodes() {
            Locale locale = LocaleUtils.parseLocale("en_US");
            assertEquals(new Locale("en", "US"), locale);
        }

    @Test
        public void testParseLocale_validCountryLanguageAndVariantCodes() {
            Locale locale = LocaleUtils.parseLocale("en_US_WIN");
            assertEquals(new Locale("en", "US", "WIN"), locale);
        }

    @Test
        public void testParseLocale_invalidFormat() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("en-US"));
        }

    @Test
        public void testParseLocale_emptyString() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale(""));
        }

    @Test
        public void testParseLocale_nullString() {
            assertThrows(NullPointerException.class, () -> LocaleUtils.parseLocale(null));
        }
}
