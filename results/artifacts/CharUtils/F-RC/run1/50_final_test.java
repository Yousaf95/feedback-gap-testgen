package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import java.util.Arrays;
import java.util.Objects;

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
        public void testIsAsciiAlphaLower_LowerCase() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
        }

    @Test
        public void testIsAsciiAlphaLower_UpperCase() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
        }

    @Test
        public void testIsAsciiControl_ControlCharacter() {
            assertTrue(CharUtils.isAsciiControl('\0'));
        }

    @Test
        public void testIsAsciiControl_PrintableCharacter() {
            assertFalse(CharUtils.isAsciiControl('a'));
        }

    @Test
        public void testIsAsciiPrintable_ControlCharacter() {
            assertFalse(CharUtils.isAsciiPrintable('\0'));
        }

    @Test
        public void testIsAsciiPrintable_PrintableCharacter() {
            assertTrue(CharUtils.isAsciiPrintable('a'));
        }

    @Test
        public void testToChar_CharacterNonNull() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringNotEmpty() {
            assertEquals('a', CharUtils.toChar("abc"));
        }

    @Test
        public void testToIntValue_CharacterNumeric() {
            assertEquals(5, CharUtils.toIntValue('5'));
        }

    @Test
        public void testToIntValue_CharacterNonNumeric() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToString_CharacterValid() {
            assertEquals("a", CharUtils.toString('a'));
        }

    @Test
        public void testUnicodeEscaped_CharacterValid() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_LowerCase_2() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
        }

    @Test
        public void testIsAsciiAlphaLower_UpperCase_2() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
        }

    @Test
        public void testIsAsciiControl_ControlCharacter_2() {
            assertTrue(CharUtils.isAsciiControl('\t')); // Tab character
        }

    @Test
        public void testIsAsciiControl_PrintableCharacter_2() {
            assertFalse(CharUtils.isAsciiControl('a'));
        }

    @Test
        public void testIsAsciiPrintable_ControlCharacter_2() {
            assertFalse(CharUtils.isAsciiPrintable('\n')); // Linefeed character
        }

    @Test
        public void testIsAsciiPrintable_PrintableCharacter_2() {
            assertTrue(CharUtils.isAsciiPrintable('A'));
        }

    @Test
        public void testToChar_CharacterNonNull_2() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringNotEmpty_2() {
            assertEquals('b', CharUtils.toChar("bc"));
        }

    @Test
        public void testToIntValue_CharacterNumeric_2() {
            assertEquals(5, CharUtils.toIntValue('5'));
        }

    @Test
        public void testToIntValue_CharacterNonNumeric_2() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToString_CharacterValid_2() {
            assertEquals("A", CharUtils.toString('A'));
        }

    @Test
        public void testUnicodeEscaped_CharacterValid_2() {
            assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
        }

    @Test
        public void testToChar_StringDefaultValue_2() {
            assertEquals('x', CharUtils.toChar("", 'x'));
        }

    @Test
        public void testToIntValue_CharacterDefaultValue_2() {
            assertEquals(9, CharUtils.toIntValue('a', 9));
        }

    @Test
        public void testToString_CharacterValid2() {
            assertEquals("z", CharUtils.toString('z'));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLower_LowerCase_3() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
        }

    @Test
        public void testIsAsciiAlphaLower_UpperCase_3() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
        }

    @Test
        public void testIsAsciiControl_ControlCharacter_3() {
            assertTrue(CharUtils.isAsciiControl('\t'));
        }

    @Test
        public void testIsAsciiControl_PrintableCharacter_3() {
            assertFalse(CharUtils.isAsciiControl('a'));
        }

    @Test
        public void testIsAsciiPrintable_ControlCharacter_3() {
            assertFalse(CharUtils.isAsciiPrintable('\t'));
        }

    @Test
        public void testIsAsciiPrintable_PrintableCharacter_3() {
            assertTrue(CharUtils.isAsciiPrintable('a'));
        }

    @Test
        public void testToChar_CharacterNonNull_3() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    @Test
        public void testToChar_StringNotEmpty_3() {
            assertEquals('a', CharUtils.toChar("abc"));
        }

    @Test
        public void testToIntValue_CharacterNumeric_3() {
            assertEquals(5, CharUtils.toIntValue('5'));
        }

    @Test
        public void testToIntValue_CharacterNonNumeric_3() {
            assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('a'));
        }

    @Test
        public void testToString_CharacterValid_3() {
            assertEquals("a", CharUtils.toString('a'));
        }

    @Test
        public void testUnicodeEscaped_CharacterValid_3() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
        }

    @Test
        public void testToChar_StringDefaultValue_3() {
            assertEquals('b', CharUtils.toChar("", 'b'));
        }

    @Test
        public void testToIntValue_CharacterDefaultValue_3() {
            assertEquals(42, CharUtils.toIntValue(null, 42));
        }

    @Test
        public void testToString_CharacterValid2_2() {
            assertEquals("a", CharUtils.toString(Character.valueOf('a')));
        }

    @Test
        public void testUnicodeEscaped_CharacterValid_2_2() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped(Character.valueOf('a')));
        }
}
