package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtils_GenTest {

    @Test
    public void testAvailableLocaleList() {
        List<Locale> locales = LocaleUtils.availableLocaleList();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    public void testAvailableLocaleSet() {
        Set<Locale> locales = LocaleUtils.availableLocaleSet();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    public void testCountriesByLanguage_withValidCode() {
        List<Locale> countries = LocaleUtils.countriesByLanguage("en");
        assertNotNull(countries);
        assertTrue(countries.stream().anyMatch(locale -> locale.getCountry().equals("US")));
    }

    @Test
    public void testCountriesByLanguage_withNull() {
        List<Locale> countries = LocaleUtils.countriesByLanguage(null);
        assertEquals(Collections.emptyList(), countries);
    }

    @Test
    public void testIsAvailableLocale_withValidLocale() {
        boolean isAvailable = LocaleUtils.isAvailableLocale(Locale.US);
        assertTrue(isAvailable);
    }

    @Test
    public void testIsAvailableLocale_withInvalidLocale() {
        boolean isAvailable = LocaleUtils.isAvailableLocale(new Locale("xx", "YY"));
        assertFalse(isAvailable);
    }

    @Test
    public void testIsLanguageUndetermined_withNullLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(null);
        assertTrue(isUndetermined);
    }

    @Test
    public void testIsLanguageUndetermined_withUndeterminedLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(new Locale("und"));
        assertTrue(isUndetermined);
    }

    @Test
    public void testIsLanguageUndetermined_withValidLocale() {
        boolean isUndetermined = LocaleUtils.isLanguageUndetermined(Locale.US);
        assertFalse(isUndetermined);
    }

    @Test
    public void testLanguagesByCountry_withValidCode() {
        List<Locale> languages = LocaleUtils.languagesByCountry("US");
        assertNotNull(languages);
        assertTrue(languages.stream().anyMatch(locale -> locale.getLanguage().equals("en")));
    }

    @Test
    public void testLanguagesByCountry_withNull() {
        List<Locale> languages = LocaleUtils.languagesByCountry(null);
        assertEquals(Collections.emptyList(), languages);
    }

    @Test
    public void testLocaleLookupList_withValidLocaleAndDefault() {
        List<Locale> lookupList = LocaleUtils.localeLookupList(Locale.US, Locale.UK);
        assertArrayEquals(new Locale[]{Locale.US, new Locale("en"), Locale.UK}, lookupList.toArray());
    }

    
    @Test
    public void testToLocale_withValidLocale() {
        Locale locale = LocaleUtils.toLocale((Locale) Locale.US);
        assertEquals(Locale.US, locale);
    }

    @Test
    public void testToString_withValidString() {
        Locale locale = LocaleUtils.toLocale("en_US");
        assertEquals(new Locale("en", "US"), locale);
    }

    @Test
    public void testToString_withEmptyString() {
        Locale locale = LocaleUtils.toLocale("");
        assertEquals(new Locale("", ""), locale);
    }

    @Test
    public void testToString_withNullString() {
        Locale locale = LocaleUtils.toLocale((String) null);
        assertNull(locale);
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoidInitialization() {
            assertNotNull(LocaleUtils.availableLocaleList());
            assertNotNull(LocaleUtils.availableLocaleSet());
        }

    
    @Test
        void testParseLocale_withLanguageOnly() {
            Locale locale = LocaleUtils.toLocale("en");
            assertEquals(new Locale("en"), locale);
        }

    @Test
        void testParseLocale_withLanguageAndCountry() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(new Locale("en", "US"), locale);
        }

    @Test
        void testParseLocale_withLanguageCountryAndVariant() {
            Locale locale = LocaleUtils.toLocale("en_US_WIN");
            assertEquals(new Locale("en", "US", "WIN"), locale);
        }

    
    @Test
        void testParseLocale_withEmptyString() {
            assertEquals(new Locale("", ""), LocaleUtils.toLocale(""));
        }

    @Test
        void testParseLocale_withHashCharacter() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#WIN"));
        }

    @Test
        void testParseLocale_withShortCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_U"));
        }

    
    @Test
        void testToString_withHashCharacter() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#WIN"));
        }

    @Test
        void testToString_withShortCountryCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_U"));
        }

    
    @Test
        void testLocaleConstructor() {
            new LocaleUtils();
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoidInitialization_2() {
            // This test ensures that the SyncAvoid class is initialized
            assertNotNull(LocaleUtils.availableLocaleList());
        }

    @Test
        void testIsISO3166CountryCode_withInvalidCode() {
            assertFalse(LocaleUtils.isISO3166CountryCode("USA"));
            assertFalse(LocaleUtils.isISO3166CountryCode("US"));
            assertFalse(LocaleUtils.isISO3166CountryCode("usA"));
            assertFalse(LocaleUtils.isISO3166CountryCode("uS"));
        }

    @Test
        void testIsISO639LanguageCode_withInvalidCode() {
            assertFalse(LocaleUtils.isISO639LanguageCode("ENG"));
            assertFalse(LocaleUtils.isISO639LanguageCode("ENGLISH"));
            assertFalse(LocaleUtils.isISO639LanguageCode("enG"));
            assertFalse(LocaleUtils.isISO639LanguageCode("english"));
        }

    @Test
        void testIsNumericAreaCode_withInvalidCode() {
            assertFalse(LocaleUtils.isNumericAreaCode("12A"));
            assertFalse(LocaleUtils.isNumericAreaCode("1234"));
            assertFalse(LocaleUtils.isNumericAreaCode("12"));
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
        }

    @Test
        void testLocaleLookupList_withNullLocale() {
            List<Locale> result = LocaleUtils.localeLookupList(null);
            assertEquals(1, result.size());
            assertEquals(Locale.getDefault(), result.get(0));
        }

    @Test
        void testLocaleLookupList_withVariant() {
            Locale localeWithVariant = new Locale("en", "US", "POSIX");
            List<Locale> result = LocaleUtils.localeLookupList(localeWithVariant);
            assertEquals(3, result.size());
            assertEquals(localeWithVariant, result.get(0));
            assertEquals(new Locale("en", "US"), result.get(1));
            assertEquals(new Locale("en", ""), result.get(2));
        }

    @Test
        void testLocaleLookupList_withCountryOnly() {
            Locale localeWithCountry = new Locale("fr", "FR");
            List<Locale> result = LocaleUtils.localeLookupList(localeWithCountry);
            assertEquals(3, result.size());
            assertEquals(localeWithCountry, result.get(0));
            assertEquals(new Locale("fr", ""), result.get(1));
        }

    @Test
        void testLocaleLookupList_withDefaultLocaleAlreadyPresent() {
            Locale defaultLocale = new Locale("en", "US");
            List<Locale> result = LocaleUtils.localeLookupList(defaultLocale, defaultLocale);
            assertEquals(1, result.size());
            assertEquals(defaultLocale, result.get(0));
        }

    @Test
        void testParseLocale_withInvalidFormat() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_USA"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_US"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("-US"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("_U"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("-U"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-"));
        }

    @Test
        void testParseLocale_withShortCountryCode_2() {
            Locale result = LocaleUtils.toLocale("_US");
            assertEquals(new Locale("", "US"), result);
    
            result = LocaleUtils.toLocale("-US");
            assertEquals(new Locale("", "US"), result);
        }
}
