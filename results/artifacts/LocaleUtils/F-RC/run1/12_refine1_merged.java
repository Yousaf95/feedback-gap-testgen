package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import static org.apache.commons.lang3.LocaleUtils.*;

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
        boolean available = LocaleUtils.isAvailableLocale(Locale.US);
        assertTrue(available);
    }

    @Test
    public void testIsAvailableLocale_nullLocale() {
        boolean available = LocaleUtils.isAvailableLocale(null);
        assertFalse(available);
    }

    @Test
    public void testIsLanguageUndetermined_validLocale() {
        boolean undetermined = LocaleUtils.isLanguageUndetermined(Locale.US);
        assertFalse(undetermined);
    }

    @Test
    public void testIsLanguageUndetermined_nullLocale() {
        boolean undetermined = LocaleUtils.isLanguageUndetermined(null);
        assertTrue(undetermined);
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
        assertNotNull(locale);
        assertTrue(locale.getLanguage().isEmpty());
        assertTrue(locale.getCountry().isEmpty());
    }

    @Test
    public void testToLocale_invalidFormat_withHash() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_US#script"));
    }

    @Test
    public void testToLocale_invalidFormat_tooShort() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("e"));
    }

    @Test
    public void testToLocale_invalidFormat_missingCountry() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en_"));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testSyncAvoid() {
            assertNotNull(SyncAvoid.AVAILABLE_LOCALE_LIST);
            assertNotNull(SyncAvoid.AVAILABLE_LOCALE_SET);
        }

    @Test
        void testIsISO3166CountryCode_valid() {
            assertTrue(isISO3166CountryCode("US"));
        }

    @Test
        void testIsISO3166CountryCode_invalidLength() {
            assertFalse(isISO3166CountryCode("USA"));
        }

    @Test
        void testIsISO3166CountryCode_notUpperCase() {
            assertFalse(isISO3166CountryCode("us"));
        }

    @Test
        void testIsISO639LanguageCode_valid2Letter() {
            assertTrue(isISO639LanguageCode("en"));
        }

    @Test
        void testIsISO639LanguageCode_valid3Letter() {
            assertTrue(isISO639LanguageCode("spa"));
        }

    @Test
        void testIsISO639LanguageCode_invalidLength2() {
            assertFalse(isISO639LanguageCode("en-US"));
        }

    @Test
        void testIsISO639LanguageCode_notLowerCase() {
            assertFalse(isISO639LanguageCode("EN"));
        }

    @Test
        void testIsLanguageUndetermined_validLocale_2() {
            assertTrue(isLanguageUndetermined(new Locale("und")));
        }

    @Test
        void testIsLanguageUndetermined_nullLocale_2() {
            assertTrue(isLanguageUndetermined(null));
        }

    @Test
        void testIsNumericAreaCode_valid() {
            assertTrue(isNumericAreaCode("123"));
        }

    @Test
        void testIsNumericAreaCode_invalidLength() {
            assertFalse(isNumericAreaCode("12"));
        }

    @Test
        void testIsNumericAreaCode_notNumeric() {
            assertFalse(isNumericAreaCode("abc"));
        }

    @Test
        void testLocaleLookupList_nullLocale() {
            assertEquals(1, localeLookupList(null).size());
        }

    @Test
        void testLocaleLookupList_nonNullLocale() {
            Locale locale = new Locale("en", "US");
            List<Locale> list = localeLookupList(locale);
            assertEquals(4, list.size());
            assertTrue(list.contains(locale));
            assertTrue(list.contains(new Locale("en", "US")));
            assertTrue(list.contains(new Locale("en")));
            assertTrue(list.contains(Locale.getDefault()));
        }

    @Test
        void testParseLocale_validLanguageCode() {
            Locale locale = parseLocale("en");
            assertEquals("en", locale.getLanguage());
        }

    @Test
        void testParseLocale_validCountryCode() {
            Locale locale = parseLocale("_US");
            assertEquals("US", locale.getCountry());
        }

    @Test
        void testParseLocale_validVariant() {
            Locale locale = parseLocale("_US_123");
            assertEquals("123", locale.getVariant());
        }

    @Test
        void testParseLocale_invalidFormat_withHash() {
            assertThrows(IllegalArgumentException.class, () -> parseLocale("en#US"));
        }

    @Test
        void testParseLocale_invalidFormat_tooShort() {
            assertThrows(IllegalArgumentException.class, () -> parseLocale("e"));
        }

    @Test
        void testParseLocale_invalidFormat_missingCountry() {
            assertThrows(IllegalArgumentException.class, () -> parseLocale("_"));
        }

    @Test
        void testToLocale_validLocale_2() {
            Locale locale = new Locale("en", "US");
            assertEquals(locale, toLocale(locale));
        }

    @Test
        void testToLocale_emptyString_2() {
            assertNull(toLocale(""));
        }

    @Test
        void testToLocale_nullString() {
            assertNull(toLocale(null));
        }
}
