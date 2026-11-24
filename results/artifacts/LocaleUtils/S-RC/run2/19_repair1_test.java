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
    public void testToLocale_invalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en#US"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsISO3166CountryCode_valid() {
            assertTrue(LocaleUtils.isISO3166CountryCode("US"));
        }

    @Test
        public void testIsISO3166CountryCode_invalidLength() {
            assertFalse(LocaleUtils.isISO3166CountryCode("USA"));
        }

    @Test
        public void testIsISO3166CountryCode_nonUpperCase() {
            assertFalse(LocaleUtils.isISO3166CountryCode("us"));
        }

    @Test
        public void testIsISO639LanguageCode_valid2Letter() {
            assertTrue(LocaleUtils.isISO639LanguageCode("en"));
        }

    @Test
        public void testIsISO639LanguageCode_valid3Letter() {
            assertTrue(LocaleUtils.isISO639LanguageCode("spa"));
        }

    @Test
        public void testIsISO639LanguageCode_invalidLength2() {
            assertFalse(LocaleUtils.isISO639LanguageCode("en-US"));
        }

    @Test
        public void testIsISO639LanguageCode_invalidLength3() {
            assertFalse(LocaleUtils.isISO639LanguageCode("spa-ES"));
        }

    @Test
        public void testIsISO639LanguageCode_nonLowerCase2() {
            assertFalse(LocaleUtils.isISO639LanguageCode("EN"));
        }

    @Test
        public void testIsISO639LanguageCode_nonLowerCase3() {
            assertFalse(LocaleUtils.isISO639LanguageCode("spa-ES"));
        }

    @Test
        public void testIsNumericAreaCode_valid() {
            assertTrue(LocaleUtils.isNumericAreaCode("123"));
        }

    @Test
        public void testIsNumericAreaCode_invalidLength() {
            assertFalse(LocaleUtils.isNumericAreaCode("12"));
        }

    @Test
        public void testParseLocale_valid2LetterLanguage() {
            Locale result = LocaleUtils.parseLocale("en");
            assertEquals(Locale.ENGLISH, result);
        }

    @Test
        public void testParseLocale_valid3LetterLanguage() {
            Locale result = LocaleUtils.parseLocale("spa");
            assertEquals(new Locale("spa"), result);
        }

    @Test
        public void testParseLocale_validCountryCode() {
            Locale result = LocaleUtils.parseLocale("en-US");
            assertEquals(Locale.US, result);
        }

    @Test
        public void testParseLocale_validLanguageAndVariant() {
            Locale result = LocaleUtils.parseLocale("en_US_12HourClock");
            assertEquals(new Locale("en", "US", "12HourClock"), result);
        }

    @Test
        public void testParseLocale_invalidFormat() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("invalid"));
        }

    @Test
        public void testToLocale_nullInput() {
            Locale result = LocaleUtils.toLocale(null);
            assertEquals(Locale.getDefault(), result);
        }
}
