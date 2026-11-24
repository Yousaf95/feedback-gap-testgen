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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_emptyLanguageCode() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    
    @Test
        public void testIsAvailableLocale_invalidLocale() {
            assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx", "YY")));
        }

    @Test
        public void testToLocale_validLanguageCountryVariant_2() {
            Locale locale = LocaleUtils.toLocale("en_US_POSIX");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("POSIX", locale.getVariant());
        }

    
    @Test
        public void testToLocale_missingVariant_2() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
            assertEquals("", locale.getVariant());
        }

    @Test
        public void testIsLanguageUndetermined_validLocale_2() {
            assertTrue(LocaleUtils.isLanguageUndetermined(new Locale("und")));
        }

    @Test
        public void testIsLanguageUndetermined_nullLocale_2() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguage_emptyLanguageCode_2() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("");
            assertTrue(countries.isEmpty());
        }

    @Test
        public void testLanguagesByCountry_emptyCountryCode() {
            List<Locale> languages = LocaleUtils.languagesByCountry("");
            assertTrue(languages.isEmpty());
        }

    @Test
        public void testToLocale_validLanguageCountryVariant_3() {
            Locale locale = LocaleUtils.toLocale("en_US_WIN");
            assertEquals(Locale.US, locale);
        }

    @Test
        public void testToLocale_missingCountry_2() {
            Locale locale = LocaleUtils.toLocale("en_");
            assertEquals(new Locale("en", ""), locale);
        }

    @Test
        public void testToLocale_missingVariant_3() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(Locale.US, locale);
        }

    @Test
        public void testIsLanguageUndetermined_validLocale_3() {
            Locale locale = new Locale("und", "US");
            assertTrue(LocaleUtils.isLanguageUndetermined(locale));
        }

    @Test
        public void testIsLanguageUndetermined_nullLocale_3() {
            assertFalse(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        public void testCountriesByLanguage_invalidLanguageCode_2() {
            List<Locale> countries = LocaleUtils.countriesByLanguage("zz");
            assertTrue(countries.isEmpty());
        }

    @Test
        public void testLanguagesByCountry_invalidCountryCode_2() {
            List<Locale> languages = LocaleUtils.languagesByCountry("ZZ");
            assertTrue(languages.isEmpty());
        }

    @Test
        public void testIsAvailableLocale_nonExistentLocale_2() {
            Locale locale = new Locale("zz", "ZZ");
            assertFalse(LocaleUtils.isAvailableLocale(locale));
        }

    @Test
        public void testToLocale_emptyString_3() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale("", ""), locale);
        }

    @Test
        public void testToLocale_validLanguageCountryVariant_4() {
            Locale locale = LocaleUtils.toLocale("en_US_POSIX");
            assertEquals(Locale.US, locale);
        }

    @Test
        public void testToLocale_missingCountry_3() {
            Locale locale = LocaleUtils.toLocale("en_");
            assertEquals(new Locale("en", ""), locale);
        }

    @Test
        public void testToLocale_missingVariant_4() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(Locale.US, locale);
        }

    @Test
        public void testIsLanguageUndetermined_validLocale_4() {
            Locale locale = new Locale("und", "US");
            assertTrue(LocaleUtils.isLanguageUndetermined(locale));
        }

    @Test
        public void testIsLanguageUndetermined_nullLocale_4() {
            assertFalse(LocaleUtils.isLanguageUndetermined(null));
        }
}
