package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharUtils_GenTest {

    @Test
    public void testCompare() {
        assertEquals(0, CharUtils.compare('a', 'a'));
        assertTrue(CharUtils.compare('a', 'b') < 0);
        assertTrue(CharUtils.compare('b', 'a') > 0);
    }

    @Test
    public void testIsAscii() {
        assertTrue(CharUtils.isAscii('A'));
        assertTrue(CharUtils.isAscii('z'));
        assertFalse(CharUtils.isAscii('\u0080'));
    }

    @Test
    public void testIsAsciiAlpha() {
        assertTrue(CharUtils.isAsciiAlpha('a'));
        assertTrue(CharUtils.isAsciiAlpha('Z'));
        assertFalse(CharUtils.isAsciiAlpha('1'));
        assertFalse(CharUtils.isAsciiAlpha(' '));
    }

    @Test
    public void testIsAsciiAlphaLower() {
        assertTrue(CharUtils.isAsciiAlphaLower('a'));
        assertFalse(CharUtils.isAsciiAlphaLower('A'));
        assertFalse(CharUtils.isAsciiAlphaLower('1'));
        assertFalse(CharUtils.isAsciiAlphaLower(' '));
    }

    @Test
    public void testIsAsciiAlphanumeric() {
        assertTrue(CharUtils.isAsciiAlphanumeric('a'));
        assertTrue(CharUtils.isAsciiAlphanumeric('Z'));
        assertTrue(CharUtils.isAsciiAlphanumeric('1'));
        assertFalse(CharUtils.isAsciiAlphanumeric(' '));
    }

    @Test
    public void testIsAsciiAlphaUpper() {
        assertTrue(CharUtils.isAsciiAlphaUpper('A'));
        assertFalse(CharUtils.isAsciiAlphaUpper('a'));
        assertFalse(CharUtils.isAsciiAlphaUpper('1'));
        assertFalse(CharUtils.isAsciiAlphaUpper(' '));
    }

    @Test
    public void testIsAsciiControl() {
        assertTrue(CharUtils.isAsciiControl('\0'));
        assertTrue(CharUtils.isAsciiControl('\r'));
        assertTrue(CharUtils.isAsciiControl('\n'));
        assertFalse(CharUtils.isAsciiControl('A'));
    }

    @Test
    public void testIsAsciiNumeric() {
        assertTrue(CharUtils.isAsciiNumeric('1'));
        assertFalse(CharUtils.isAsciiNumeric('a'));
        assertFalse(CharUtils.isAsciiNumeric(' '));
    }

    @Test
    public void testIsAsciiPrintable() {
        assertTrue(CharUtils.isAsciiPrintable('A'));
        assertTrue(CharUtils.isAsciiPrintable('z'));
        assertTrue(CharUtils.isAsciiPrintable('1'));
        assertFalse(CharUtils.isAsciiPrintable('\0'));
        assertFalse(CharUtils.isAsciiPrintable('\r'));
        assertFalse(CharUtils.isAsciiPrintable('\n'));
    }

    @Test
    public void testToChar_Character() {
        assertEquals('A', CharUtils.toChar(Character.valueOf('A')));
    }

    
    @Test
    public void testToChar_String() {
        assertEquals('A', CharUtils.toChar("ABC"));
    }

    @Test
    public void testToChar_StringDefaultValue() {
        assertEquals('B', CharUtils.toChar("", 'B'));
    }

    @Test
    public void testToCharacterObject_Character() {
        assertNotNull(CharUtils.toCharacterObject(Character.valueOf('A')));
    }

    @Test
    public void testToCharacterObject_String() {
        assertNotNull(CharUtils.toCharacterObject("ABC"));
    }

    @Test
    public void testToCharacterObject_EmptyString() {
        assertNull(CharUtils.toCharacterObject(""));
    }

    @Test
    public void testToIntValue_Character() {
        assertEquals(1, CharUtils.toIntValue(Character.valueOf('1')));
    }

    @Test
    public void testToIntValue_CharacterDefaultValue() {
        assertEquals(2, CharUtils.toIntValue(null, 2));
    }

    
    
    @Test
    public void testToString_Character() {
        assertNotNull(CharUtils.toString(Character.valueOf('A')));
    }

    
    @Test
    public void testUnicodeEscaped_Character() {
        assertEquals("\\u0041", CharUtils.unicodeEscaped(Character.valueOf('A')));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiExtendedChar() {
            assertFalse(CharUtils.isAscii('\u0100'));
        }

    @Test
        public void testIsAsciiAlphaExtendedChar() {
            assertFalse(CharUtils.isAsciiAlpha('\u0100'));
        }

    @Test
        public void testIsAsciiAlphaLowerExtendedChar() {
            assertFalse(CharUtils.isAsciiAlphaLower('\u0100'));
        }

    @Test
        public void testIsAsciiAlphanumericExtendedChar() {
            assertFalse(CharUtils.isAsciiAlphanumeric('\u0100'));
        }

    @Test
        public void testIsAsciiAlphaUpperExtendedChar() {
            assertFalse(CharUtils.isAsciiAlphaUpper('\u0100'));
        }

    @Test
        public void testIsAsciiControlExtendedChar() {
            assertFalse(CharUtils.isAsciiControl('\u0100'));
        }

    @Test
        public void testIsAsciiNumericExtendedChar() {
            assertFalse(CharUtils.isAsciiNumeric('\u0100'));
        }

    @Test
        public void testIsAsciiPrintableExtendedChar() {
            assertFalse(CharUtils.isAsciiPrintable('\u0100'));
        }

    @Test
        public void testToChar_NullCharacter() {
            assertThrows(NullPointerException.class, () -> CharUtils.toChar(null));
        }

    @Test
        public void testToChar_EmptyString() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toChar(""));
        }

    @Test
        public void testToChar_DefaultValueOnEmptyString() {
            assertEquals('a', CharUtils.toChar("", 'a'));
        }

    @Test
        public void testToCharacterObject_NullString() {
            assertNull(CharUtils.toCharacterObject(null));
        }

    @Test
        public void testToCharacterObject_EmptyString_2() {
            assertNull(CharUtils.toCharacterObject(""));
        }

    @Test
        public void testToIntValue_NonNumericChar() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToIntValue_DefaultValueOnNonNumericChar() {
            assertEquals(0, CharUtils.toIntValue('a', 0));
        }

    @Test
        public void testToString_NonAsciiChar() {
            assertEquals("\\u0100", CharUtils.unicodeEscaped('\u0100'));
        }
}
