package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.CharUtils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import java.util.Arrays;

public class CharUtils_GenTest {

    @Test
    public void testCompare() {
        assertEquals(0, compare('a', 'a'));
        assertTrue(compare('a', 'b') < 0);
        assertTrue(compare('b', 'a') > 0);
    }

    @Test
    public void testIsAscii() {
        assertTrue(isAscii('A'));
        assertFalse(isAscii('\u0100'));
    }

    @Test
    public void testIsAsciiAlpha() {
        assertTrue(isAsciiAlpha('A'));
        assertTrue(isAsciiAlpha('z'));
        assertFalse(isAsciiAlpha('1'));
        assertFalse(isAsciiAlpha(' '));
    }

    @Test
    public void testIsAsciiAlphaLower() {
        assertTrue(isAsciiAlphaLower('a'));
        assertFalse(isAsciiAlphaLower('Z'));
        assertFalse(isAsciiAlphaLower('1'));
        assertFalse(isAsciiAlphaLower(' '));
    }

    @Test
    public void testIsAsciiAlphanumeric() {
        assertTrue(isAsciiAlphanumeric('A'));
        assertTrue(isAsciiAlphanumeric('z'));
        assertTrue(isAsciiAlphanumeric('0'));
        assertFalse(isAsciiAlphanumeric(' '));
    }

    @Test
    public void testIsAsciiAlphaUpper() {
        assertTrue(isAsciiAlphaUpper('A'));
        assertFalse(isAsciiAlphaUpper('a'));
        assertFalse(isAsciiAlphaUpper('1'));
        assertFalse(isAsciiAlphaUpper(' '));
    }

    @Test
    public void testIsAsciiControl() {
        assertTrue(isAsciiControl('\0'));
        assertTrue(isAsciiControl('\n'));
        assertFalse(isAsciiControl('A'));
    }

    @Test
    public void testIsAsciiNumeric() {
        assertTrue(isAsciiNumeric('0'));
        assertFalse(isAsciiNumeric('a'));
    }

    @Test
    public void testIsAsciiPrintable() {
        assertTrue(isAsciiPrintable('A'));
        assertFalse(isAsciiPrintable('\0'));
    }

    @Test
    public void testToChar_Character() {
        assertEquals('A', toChar(Character.valueOf('A')));
    }

    
    @Test
    public void testToChar_String() {
        assertEquals('A', toChar("AB"));
    }

    @Test
    public void testToChar_StringDefaultValue() {
        assertEquals('B', toChar("", 'B'));
    }

    @Test
    public void testToCharacterObject_Character() {
        assertNotNull(toCharacterObject(Character.valueOf('A')));
    }

    @Test
    public void testToCharacterObject_String() {
        assertNotNull(toCharacterObject("AB"));
    }

    @Test
    public void testToIntValue_Character() {
        assertEquals(0, toIntValue(Character.valueOf('0')));
    }

    @Test
    public void testToIntValue_CharacterDefaultValue() {
        assertEquals(1, toIntValue(Character.valueOf('A'), 1));
    }

    
    
    
    @Test
    public void testUnicodeEscaped_Character() {
        assertEquals("\\u0041", unicodeEscaped(Character.valueOf('A')));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLowerPartialBranches() {
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
            assertFalse(CharUtils.isAsciiAlphaLower('0'));
            assertFalse(CharUtils.isAsciiAlphaLower(' '));
        }

    @Test
        public void testIsAsciiControlPartialBranches() {
            assertTrue(CharUtils.isAsciiControl('\t'));
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertTrue(CharUtils.isAsciiControl('\r'));
            assertTrue(CharUtils.isAsciiControl((char) 31));
            assertFalse(CharUtils.isAsciiControl(' '));
            assertFalse(CharUtils.isAsciiControl('A'));
        }

    @Test
        public void testIsAsciiPrintablePartialBranches() {
            assertTrue(CharUtils.isAsciiPrintable('a'));
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertTrue(CharUtils.isAsciiPrintable('0'));
            assertTrue(CharUtils.isAsciiPrintable(' '));
            assertFalse(CharUtils.isAsciiPrintable((char) 31));
        }

    @Test
        public void testToChar_CharacterNotCovered() {
            assertEquals('a', CharUtils.toChar(Character.valueOf('a')));
        }

    
    
    @Test
        public void testToIntValue_CharacterNotCovered() {
            assertEquals(5, CharUtils.toIntValue(Character.valueOf('5')));
        }

    @Test
        public void testToIntValue_CharacterDefaultValuePartialBranches() {
            assertEquals(5, CharUtils.toIntValue(Character.valueOf('5'), 10));
            assertEquals(10, CharUtils.toIntValue(Character.valueOf('a'), 10));
        }

    @Test
        public void testUnicodeEscaped_CharacterPartialBranches() {
            assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
            assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
            assertEquals("\\u0030", CharUtils.unicodeEscaped('0'));
            assertEquals("\\u0020", CharUtils.unicodeEscaped(' '));
        }

    @Test
        public void testToString_CharacterNotCovered() {
            assertEquals("a", CharUtils.toString(Character.valueOf('a')));
        }

    
    @Test
            public void testIsAsciiControlPartialBranches_2() {
                assertTrue(CharUtils.isAsciiControl('\0'));
                assertTrue(CharUtils.isAsciiControl('\r'));
                assertTrue(CharUtils.isAsciiControl('\n'));
                assertFalse(CharUtils.isAsciiControl(' '));
            }

    @Test
            public void testIsAsciiPrintablePartialBranches_2() {
                assertTrue(CharUtils.isAsciiPrintable('a'));
                assertTrue(CharUtils.isAsciiPrintable('9'));
                assertTrue(CharUtils.isAsciiPrintable('~'));
                assertFalse(CharUtils.isAsciiPrintable('\0'));
            }

    @Test
            public void testToIntValue_CharacterNotCovered_2() {
                assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue('A'));
            }

    @Test
            public void testToIntValue_CharacterDefaultValuePartialBranches_2() {
                assertEquals(0, CharUtils.toIntValue('A', 0));
                assertEquals(10, CharUtils.toIntValue('A', 10));
            }

    @Test
            public void testUnicodeEscaped_CharacterPartialBranches_2() {
                assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
                assertEquals("\\u0061", CharUtils.unicodeEscaped('a'));
                assertEquals("\\u0030", CharUtils.unicodeEscaped('0'));
            }

    @Test
            public void testToString_CharacterNotCovered_2() {
                assertNull(CharUtils.toString(null));
            }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsAsciiAlphaLowerPartialBranches_2() {
            assertFalse(CharUtils.isAsciiAlphaLower('A'));
            assertTrue(CharUtils.isAsciiAlphaLower('a'));
            assertFalse(CharUtils.isAsciiAlphaLower('z'));
            assertFalse(CharUtils.isAsciiAlphaLower('0'));
        }

    @Test
        public void testIsAsciiControlPartialBranches_3() {
            assertTrue(CharUtils.isAsciiControl('\r'));
            assertTrue(CharUtils.isAsciiControl('\n'));
            assertFalse(CharUtils.isAsciiControl(' '));
            assertTrue(CharUtils.isAsciiControl((char) 127));
        }

    @Test
        public void testIsAsciiPrintablePartialBranches_3() {
            assertTrue(CharUtils.isAsciiPrintable('a'));
            assertTrue(CharUtils.isAsciiPrintable('A'));
            assertFalse(CharUtils.isAsciiPrintable('\r'));
            assertTrue(CharUtils.isAsciiPrintable(' '));
        }

    @Test
        public void testToChar_CharacterNotCovered_2() {
            assertThrows(NullPointerException.class, () -> CharUtils.toChar(null));
        }

    @Test
        public void testToIntValue_CharacterNotCovered_3() {
            assertEquals(-1, CharUtils.toIntValue((char) 0, -1));
        }

    @Test
        public void testToIntValue_CharacterDefaultValuePartialBranches_3() {
            assertEquals(5, CharUtils.toIntValue('5', 3));
            assertEquals(3, CharUtils.toIntValue('a', 3));
        }

    @Test
        public void testUnicodeEscaped_CharacterPartialBranches_3() {
            assertNull(CharUtils.unicodeEscaped(null));
            assertEquals("\\u0020", CharUtils.unicodeEscaped(' '));
        }

    @Test
        public void testToString_CharacterNotCovered_3() {
            assertNull(CharUtils.toString((Character) null));
        }
}
