package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
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
        public void testIsISO3166CountryCode() {
            assertTrue(LocaleUtils.isISO3166CountryCode("US"));
            assertFalse(LocaleUtils.isISO3166CountryCode("us"));
            assertFalse(LocaleUtils.isISO3166CountryCode("U"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.isISO3166CountryCode(null));
        }

    @Test
        public void testIsISO639LanguageCode() {
            assertTrue(LocaleUtils.isISO639LanguageCode("en"));
            assertFalse(LocaleUtils.isISO639LanguageCode("EN"));
            assertFalse(LocaleUtils.isISO639LanguageCode("eng"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.isISO639LanguageCode(null));
        }

    @Test
        public void testIsNumericAreaCode() {
            assertTrue(LocaleUtils.isNumericAreaCode("123"));
            assertFalse(LocaleUtils.isNumericAreaCode("abc"));
            assertFalse(LocaleUtils.isNumericAreaCode("12"));
            assertFalse(LocaleUtils.isNumericAreaCode("1234"));
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.isNumericAreaCode(null));
        }

    @Test
        public void testLocaleLookupList() {
            List<Locale> list = LocaleUtils.localeLookupList(Locale.US);
            assertEquals(4, list.size());
            assertTrue(list.contains(Locale.US));
            assertTrue(list.contains(new Locale("en", "US")));
            assertTrue(list.contains(new Locale("en")));
            assertTrue(list.contains(Locale.getDefault()));
    
            list = LocaleUtils.localeLookupList(null, Locale.US);
            assertEquals(4, list.size());
            assertTrue(list.contains(Locale.US));
            assertTrue(list.contains(new Locale("en", "US")));
            assertTrue(list.contains(new Locale("en")));
            assertTrue(list.contains(Locale.getDefault()));
        }

    @Test
        public void testParseLocale() {
            Locale locale = LocaleUtils.parseLocale("en_US");
            assertEquals("en", locale.getLanguage());
            assertEquals("US", locale.getCountry());
    
            locale = LocaleUtils.parseLocale("fr-FR");
            assertEquals("fr", locale.getLanguage());
            assertEquals("FR", locale.getCountry());
    
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.parseLocale("invalid"));
        }

    @Test
        public void testToLocaleWithEmptyString_2() {
            Locale locale = LocaleUtils.toLocale("");
            assertEquals(StringUtils.EMPTY, locale.getLanguage());
            assertEquals(StringUtils.EMPTY, locale.getCountry());
        }
}
