package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class BooleanUtils_GenTest {

    @Test
    void testAnd_booleanArray() {
        // Arrange
        boolean[] array = {true, true, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testAnd_booleanArray_withFalse() {
        // Arrange
        boolean[] array = {true, false, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testAnd_BooleanArray() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE};

        // Act
        Boolean result = BooleanUtils.and(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    
    @Test
    void testBooleanValues() {
        // Arrange

        // Act
        Boolean[] result = BooleanUtils.booleanValues();

        // Assert
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, result);
    }

    @Test
    void testCompare_trueTrue() {
        // Arrange

        // Act
        int result = BooleanUtils.compare(true, true);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void testCompare_falseFalse() {
        // Arrange

        // Act
        int result = BooleanUtils.compare(false, false);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void testCompare_trueFalse() {
        // Arrange

        // Act
        int result = BooleanUtils.compare(true, false);

        // Assert
        assertEquals(1, result);
    }

    @Test
    void testCompare_falseTrue() {
        // Arrange

        // Act
        int result = BooleanUtils.compare(false, true);

        // Assert
        assertEquals(-1, result);
    }

    @Test
    void testForEach() {
        // Arrange
        List<Boolean> results = new ArrayList<>();
        Consumer<Boolean> action = results::add;

        // Act
        BooleanUtils.forEach(action);

        // Assert
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, results.toArray());
    }

    @Test
    void testIsFalse_true() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isFalse(Boolean.TRUE);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsFalse_false() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isFalse(Boolean.FALSE);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsFalse_null() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isFalse(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsNotFalse_true() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotFalse(Boolean.TRUE);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsNotFalse_false() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotFalse(Boolean.FALSE);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsNotFalse_null() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotFalse(null);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsNotTrue_true() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotTrue(Boolean.TRUE);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsNotTrue_false() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotTrue(Boolean.FALSE);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsNotTrue_null() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isNotTrue(null);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsTrue_true() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isTrue(Boolean.TRUE);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsTrue_false() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isTrue(Boolean.FALSE);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsTrue_null() {
        // Arrange

        // Act
        boolean result = BooleanUtils.isTrue(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testNegate_true() {
        // Arrange

        // Act
        Boolean result = BooleanUtils.negate(Boolean.TRUE);

        // Assert
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testNegate_false() {
        // Arrange

        // Act
        Boolean result = BooleanUtils.negate(Boolean.FALSE);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testNegate_null() {
        // Arrange

        // Act
        Boolean result = BooleanUtils.negate(null);

        // Assert
        assertNull(result);
    }

    @Test
    void testOneHot_trueFalseTrue() {
        // Arrange
        boolean[] array = {true, false, true};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testOneHot_trueFalseFalse() {
        // Arrange
        boolean[] array = {true, false, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testOneHot_BooleanArray() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE};

        // Act
        Boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testOr_booleanArray() {
        // Arrange
        boolean[] array = {false, false, true};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testOr_booleanArray_allFalse() {
        // Arrange
        boolean[] array = {false, false, false};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testOr_BooleanArray() {
        // Arrange
        Boolean[] array = {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE};

        // Act
        Boolean result = BooleanUtils.or(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testPrimitiveValues() {
        // Arrange

        // Act
        boolean[] result = BooleanUtils.primitiveValues();

        // Assert
        assertArrayEquals(new boolean[]{false, true}, result);
    }

    @Test
    void testToBoolean_true() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(Boolean.TRUE);

        // Assert
        assertTrue(result);
    }

    @Test
    void testToBoolean_false() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(Boolean.FALSE);

        // Assert
        assertFalse(result);
    }

    @Test
    void testToBoolean_null() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean((Boolean) null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testToBoolean_intZero() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(0);

        // Assert
        assertFalse(result);
    }

    @Test
    void testToBoolean_intNonZero() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(1);

        // Assert
        assertTrue(result);
    }

    @Test
    void testToBoolean_intWithTrueFalseValues() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(1, 1, 0);

        // Assert
        assertTrue(result);
    }

    @Test
    void testToBoolean_intWithFalseValue() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(0, 1, 0);

        // Assert
        assertFalse(result);
    }

    @Test
    void testToBoolean_intNoMatch() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
    }

    @Test
    void testToBoolean_IntegerWithTrueFalseValues() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void testToBoolean_IntegerNullWithTrueValueNull() {
        // Arrange

        // Act
        boolean result = BooleanUtils.toBoolean(null, null, Integer.valueOf(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void testToBoolean_IntegerNoMatch() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(0)));
    }

    @Test
    void testToStringOnOff_true() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringOnOff(true);

        // Assert
        assertEquals(BooleanUtils.ON, result);
    }

    @Test
    void testToStringOnOff_false() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringOnOff(false);

        // Assert
        assertEquals(BooleanUtils.OFF, result);
    }

    @Test
    void testToStringTrueFalse_true() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringTrueFalse(true);

        // Assert
        assertEquals(BooleanUtils.TRUE, result);
    }

    @Test
    void testToStringTrueFalse_false() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringTrueFalse(false);

        // Assert
        assertEquals(BooleanUtils.FALSE, result);
    }

    @Test
    void testToStringYesNo_true() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringYesNo(true);

        // Assert
        assertEquals(BooleanUtils.YES, result);
    }

    @Test
    void testToStringYesNo_false() {
        // Arrange

        // Act
        String result = BooleanUtils.toStringYesNo(false);

        // Assert
        assertEquals(BooleanUtils.NO, result);
    }

    @Test
    void testXor_trueFalseTrue() {
        // Arrange
        boolean[] array = {true, false, true};

        // Act
        boolean result = BooleanUtils.xor(array);

        // Assert
        assertFalse(result);
    }

    @Test
    void testXor_trueFalseFalse() {
        // Arrange
        boolean[] array = {true, false, false};

        // Act
        boolean result = BooleanUtils.xor(array);

        // Assert
        assertTrue(result);
    }

    @Test
    void testXor_BooleanArray() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE};

        // Act
        Boolean result = BooleanUtils.xor(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testToBooleanObject_intWithNullValue() {
            assertEquals(null, BooleanUtils.toBooleanObject(2, 1, 0, 2));
        }

    @Test
        void testToBooleanObject_IntegerWithTrueFalseValuesAndNull() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(0), null));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0), null));
            assertEquals(null, BooleanUtils.toBooleanObject(null, Integer.valueOf(1), Integer.valueOf(0), null));
        }

    @Test
        void testToInteger_booleanTrueWithCustomValues() {
            assertEquals(5, BooleanUtils.toInteger(true, 5, 10));
        }

    @Test
        void testToInteger_booleanFalseWithCustomValues() {
            assertEquals(10, BooleanUtils.toInteger(false, 5, 10));
        }

    @Test
        void testToIntegerObject_BooleanTrueWithCustomValues() {
            assertEquals(Integer.valueOf(5), BooleanUtils.toIntegerObject(Boolean.TRUE, Integer.valueOf(5), Integer.valueOf(10)));
        }

    @Test
        void testToIntegerObject_BooleanFalseWithCustomValues() {
            assertEquals(Integer.valueOf(10), BooleanUtils.toIntegerObject(Boolean.FALSE, Integer.valueOf(5), Integer.valueOf(10)));
        }

    @Test
        void testToIntegerObject_BooleanNullWithCustomValuesAndNullValue() {
            assertEquals(null, BooleanUtils.toIntegerObject(null, Integer.valueOf(5), Integer.valueOf(10), null));
        }

    @Test
        void testToString_booleanTrueWithCustomStrings() {
            assertEquals("yes", BooleanUtils.toString(true, "yes", "no"));
        }

    @Test
        void testToString_booleanFalseWithCustomStrings() {
            assertEquals("no", BooleanUtils.toString(false, "yes", "no"));
        }

    @Test
        void testToString_BooleanTrueWithCustomStringsAndNullString() {
            assertEquals("yes", BooleanUtils.toString(Boolean.TRUE, "yes", "no", "unknown"));
        }

    @Test
        void testToString_BooleanFalseWithCustomStringsAndNullString() {
            assertEquals("no", BooleanUtils.toString(Boolean.FALSE, "yes", "no", "unknown"));
        }

    @Test
        void testToString_BooleanNullWithCustomStringsAndNullString() {
            assertEquals("unknown", BooleanUtils.toString(null, "yes", "no", "unknown"));
        }

    @Test
        void testToBooleanObject_StringWithTrueFalseValuesAndNullString() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true", "true", "false", null));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false", "true", "false", null));
            assertEquals(null, BooleanUtils.toBooleanObject(null, "true", "false", null));
        }

    
    @Test
        void testToBooleanObject_StringWithTrueFalseValuesAndNullString_InvalidInput() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("unknown", "true", "false", null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testToBooleanDefaultIfNull_withTrue() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false));
        }

    @Test
        void testToBooleanDefaultIfNull_withFalse() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true));
        }

    @Test
        void testToBooleanDefaultIfNull_withNullAndDefaultValueTrue() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        void testToBooleanDefaultIfNull_withNullAndDefaultValueFalse() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
        }

    @Test
        void testToIntegerObject_withCustomValues_null() {
            assertEquals(Integer.valueOf(2), BooleanUtils.toIntegerObject(null, 1, 0, 2));
        }

    @Test
        void testToIntegerObject_withCustomValues_true() {
            assertEquals(Integer.valueOf(1), BooleanUtils.toIntegerObject(true, 1, 0, 2));
        }

    @Test
        void testToIntegerObject_withCustomValues_false() {
            assertEquals(Integer.valueOf(0), BooleanUtils.toIntegerObject(false, 1, 0, 2));
        }

    @Test
        void testToString_withCustomStrings_true() {
            assertEquals("yes", BooleanUtils.toString(true, "yes", "no"));
        }

    @Test
        void testToString_withCustomStrings_false() {
            assertEquals("no", BooleanUtils.toString(false, "yes", "no"));
        }

    @Test
        void testToBooleanObject_withStringTrue() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true"));
        }

    @Test
        void testToBooleanObject_withStringFalse() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false"));
        }

    @Test
        void testToBooleanObject_withStringYes() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("yes"));
        }

    @Test
        void testToBooleanObject_withStringNo() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("no"));
        }

    @Test
        void testToBooleanObject_withStringOn() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("on"));
        }

    @Test
        void testToBooleanObject_withStringOff() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("off"));
        }

    @Test
        void testToBooleanObject_withStringInvalid() {
            assertNull(BooleanUtils.toBooleanObject("invalid"));
        }

    @Test
        void testToInteger_withCustomValues_true() {
            assertEquals(10, BooleanUtils.toInteger(true, 10, 20));
        }

    @Test
        void testToInteger_withCustomValues_false() {
            assertEquals(20, BooleanUtils.toInteger(false, 10, 20));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAnd_emptyArray_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and((boolean[]) null));
        }

    @Test
        void testOr_emptyArray_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or((boolean[]) null));
        }

    @Test
        void testOneHot_emptyArray_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.oneHot((boolean[]) null));
        }

    @Test
        void testXor_emptyArray_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.xor((boolean[]) null));
        }

    @Test
        void testToBooleanObject_withNullStringAndNoMatch_returnsNull() {
            assertNull(BooleanUtils.toBooleanObject(null, "true", "false", null));
        }

    @Test
        void testToIntegerObject_withTrueValueAsNull_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toIntegerObject(true, null, 0));
        }

    @Test
        void testToIntegerObject_withFalseValueAsNull_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toIntegerObject(false, 1, null));
        }

    @Test
        void testToString_withTrueStringAsNull_returnsFalseString() {
            assertEquals("false", BooleanUtils.toString(true, null, "false"));
        }

    @Test
        void testToString_withFalseStringAsNull_returnsTrueString() {
            assertEquals("true", BooleanUtils.toString(false, "true", null));
        }

    @Test
        void testToBooleanObject_withInvalidString_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("invalid"));
        }

    @Test
        void testToBooleanObject_withCustomStringsAndNullStringAsNull_returnsNullForNullInput() {
            assertNull(BooleanUtils.toBooleanObject(null, "yes", "no", null));
        }

    @Test
        void testToInteger_withTrueValueEqualToFalseValue_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toInteger(true, 1, 1));
        }
}
