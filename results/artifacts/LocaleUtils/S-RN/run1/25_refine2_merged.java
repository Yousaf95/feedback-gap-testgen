package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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
    public void testCountriesByLanguage() {
        List<Locale> countries = LocaleUtils.countriesByLanguage("en");
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        List<Locale> emptyList = LocaleUtils.countriesByLanguage(null);
        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void testIsAvailableLocale() {
        Locale locale = new Locale("en", "US");
        assertTrue(LocaleUtils.isAvailableLocale(locale));

        Locale notAvailableLocale = new Locale("xx", "YY");
        assertFalse(LocaleUtils.isAvailableLocale(notAvailableLocale));
    }

    @Test
    public void testIsLanguageUndetermined() {
        Locale locale = new Locale("und", "");
        assertTrue(LocaleUtils.isLanguageUndetermined(locale));

        Locale notUndeterminedLocale = new Locale("en", "US");
        assertFalse(LocaleUtils.isLanguageUndetermined(notUndeterminedLocale));
    }

    @Test
    public void testLanguagesByCountry() {
        List<Locale> languages = LocaleUtils.languagesByCountry("US");
        assertNotNull(languages);
        assertFalse(languages.isEmpty());

        List<Locale> emptyList = LocaleUtils.languagesByCountry(null);
        assertTrue(emptyList.isEmpty());
    }

    
    @Test
    public void testToLocale() {
        Locale locale = new Locale("en", "US");
        Locale result = LocaleUtils.toLocale(locale);
        assertEquals(locale, result);

        Locale defaultLocale = Locale.getDefault();
        Locale nullResult = LocaleUtils.toLocale((Locale) null);
        assertEquals(defaultLocale, nullResult);
    }

    @Test
    public void testToLocaleWithEmptyString() {
        Locale locale = LocaleUtils.toLocale("");
        assertNotNull(locale);
        assertTrue(locale.getLanguage().isEmpty());
        assertTrue(locale.getCountry().isEmpty());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
            public void testAvailableLocaleListNotEmpty() {
                assertFalse(LocaleUtils.availableLocaleList().isEmpty());
            }

    @Test
            public void testAvailableLocaleSetNotEmpty() {
                assertFalse(LocaleUtils.availableLocaleSet().isEmpty());
            }

    @Test
            public void testCountriesByLanguageEmptyInput() {
                assertTrue(LocaleUtils.countriesByLanguage("").isEmpty());
            }

    @Test
            public void testIsAvailableLocaleNull() {
                assertFalse(LocaleUtils.isAvailableLocale(null));
            }

    @Test
            public void testIsLanguageUndeterminedNull() {
                assertTrue(LocaleUtils.isLanguageUndetermined(null));
            }

    
    @Test
            public void testToLocaleWithNull() {
            assertEquals(Locale.getDefault(), LocaleUtils.toLocale((Locale) null));
        }

    @Test
            public void testToLocaleWithEmptyString_2() {
                assertEquals(new Locale(StringUtils.EMPTY, StringUtils.EMPTY), LocaleUtils.toLocale(""));
            }

    @Test
            public void testToLocaleWithInvalidFormat() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en#US"));
        }

    @Test
            public void testToLocaleWithShortLanguageCode() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e"));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testCountriesByLanguageWithInvalidInput() {
            List<Locale> result = LocaleUtils.countriesByLanguage("invalid");
            assertTrue(result.isEmpty());
        }

    @Test
        public void testLanguagesByCountryWithInvalidInput() {
            List<Locale> result = LocaleUtils.languagesByCountry("invalid");
            assertTrue(result.isEmpty());
        }

    @Test
        public void testToLocaleWithValidFormat() {
            Locale locale = LocaleUtils.toLocale("en_US");
            assertEquals(Locale.US, locale);
        }

    @Test
        public void testToLocaleWithEmptyString_3() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(new Locale(StringUtils.EMPTY, StringUtils.EMPTY), locale);
        }

    @Test
        public void testToLocaleWithScriptAndExtensions() {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#script=Latn"));
        }

    @Test
        public void testToLocaleWithShortCountryCode() {
            Locale locale = LocaleUtils.toLocale("en_U");
            assertEquals(new Locale("en", "U"), locale);
        }

    @Test
        public void testIsLanguageUndeterminedWithNull() {
            assertTrue(LocaleUtils.isLanguageUndetermined(null));
        }

    @Test
        public void testIsLanguageUndeterminedWithValidLocale() {
            assertFalse(LocaleUtils.isLanguageUndetermined(new Locale("en", "US")));
        }

    @Test
        public void testIsLanguageUndeterminedWithUndeterminedLocale() {
            assertTrue(LocaleUtils.isLanguageUndetermined(new Locale("und", "")));
        }
}
