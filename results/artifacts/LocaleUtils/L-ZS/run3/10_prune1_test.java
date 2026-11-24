package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtils_GenTest {

    @Test
    void testAvailableLocaleList() {
        List<Locale> locales = LocaleUtils.availableLocaleList();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    @Test
    void testAvailableLocaleSet() {
        Set<Locale> locales = LocaleUtils.availableLocaleSet();
        assertNotNull(locales);
        assertFalse(locales.isEmpty());
        assertTrue(locales.contains(Locale.US));
    }

    
    @Test
    void testIsAvailableLocale() {
        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("xx", "YY")));
    }

    
    
    
    
    
    @ParameterizedTest
    @ValueSource(strings = {"en", "fr_FR", "_US", "-CA", "xx_YY", "", "#invalid"})
    void testToLocaleWithString(String str) {
        if (str == null || str.isEmpty() || str.contains("#") || str.length() < 2) {
            assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale(str));
        } else {
            Locale result = LocaleUtils.toLocale(str);
            assertNotNull(result);
            if (str.equals("_US")) {
                assertEquals("", result.getLanguage());
                assertEquals("US", result.getCountry());
            } else if (str.equals("-CA")) {
                assertEquals("", result.getLanguage());
                assertEquals("CA", result.getCountry());
            }
        }
    }
}
