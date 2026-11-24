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
        public void testSyncAvoid() {
            // This method is not directly testable as it's a private static inner class.
            // However, we can indirectly test its functionality through other methods that use it.
        }

    @Test
        public void testIsISO3166CountryCode_validCode() {
            assertTrue(LocaleUtils.isISO3166CountryCode("US"));
        }

    @Test
        public void testIsISO3166CountryCode_invalidCode() {
            assertFalse(LocaleUtils.isISO3166CountryCode("ZZ"));
        }

    @Test
        public void testIsISO639LanguageCode_validCode() {
            assertTrue(LocaleUtils.isISO639LanguageCode("en"));
        }

    @Test
        public void testIsISO639LanguageCode_invalidCode() {
            assertFalse(LocaleUtils.isISO639LanguageCode("zz"));
        }

    @Test
        public void testIsNumericAreaCode_validCode() {
            assertTrue(LocaleUtils.isNumericAreaCode("123"));
        }

    @Test
        public void testIsNumericAreaCode_invalidCode() {
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
        }

    @Test
        public void testLocaleLookupList_nullLocale() {
            List<Locale> list = LocaleUtils.localeLookupList(null, Locale.US);
            assertEquals(1, list.size());
            assertEquals(Locale.US, list.get(0));
        }

    @Test
        public void testLocaleLookupList_nonNullLocale() {
            List<Locale> list = LocaleUtils.localeLookupList(Locale.US, Locale.US);
            assertEquals(4, list.size());
            assertTrue(list.contains(Locale.US));
            assertTrue(list.contains(new Locale("en", "US")));
            assertTrue(list.contains(new Locale("en", "")));
            assertTrue(list.contains(Locale.US));
        }

    @Test
        public void testToLocale_nullString() {
            assertNull(LocaleUtils.toLocale(null));
        }

    @Test
        public void testToLocale_emptyString_2() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals("", locale.getLanguage());
            assertEquals("", locale.getCountry());
        }

    @Test
        public void testToLocale_invalidFormat_withHash_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#"));
        }

    @Test
        public void testToLocale_invalidFormat_tooShort_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e"));
        }

    @Test
        public void testToLocale_invalidFormat_missingCountry_2() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_"));
        }
}
