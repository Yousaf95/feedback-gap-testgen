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

    
    
    @Test
        void testToBoolean_IntegerWithTrueValueNull() {
            assertTrue(BooleanUtils.toBoolean(null, null, 1));
        }

    
    @Test
        void testToBoolean_IntegerNoMatch_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
        }

    @Test
        void testToBoolean_StringNotCovered() {
            assertFalse(BooleanUtils.toBoolean("notTrue"));
        }

    @Test
        void testToBoolean_StringWithTrueStringNull() {
            assertTrue(BooleanUtils.toBoolean(null, null, "false"));
        }

    @Test
        void testToBoolean_StringWithFalseStringNull() {
            assertFalse(BooleanUtils.toBoolean(null, "true", null));
        }

    @Test
        void testToBoolean_StringWithBothStringsNull() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("true", null, null));
        }

    @Test
        void testToBoolean_StringExactMatchTrue() {
            assertTrue(BooleanUtils.toBoolean("true", "true", "false"));
        }

    @Test
        void testToBoolean_StringExactMatchFalse() {
            assertFalse(BooleanUtils.toBoolean("false", "true", "false"));
        }

    
    
    @Test
        void testToBoolean_StringNoMatch() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("maybe", "true", "false"));
        }

    @Test
        void testToBooleanDefaultIfNull_true() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false));
        }

    @Test
        void testToBooleanDefaultIfNull_false() {
            assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true));
        }

    @Test
        void testToBooleanDefaultIfNull_null() {
            assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
        }

    @Test
        void testToBooleanObject_intZero() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0));
        }

    @Test
        void testToBooleanObject_intNonZero() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1));
        }

    @Test
        void testToBooleanObject_IntegerNullWithTrueValueNull() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(null, null, 1, 2));
        }

    @Test
        void testToBooleanObject_IntegerNoMatch() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(3, 1, 0, 2));
        }

    @Test
        void testToBooleanObject_StringTrue() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true"));
        }

    @Test
        void testToBooleanObject_StringFalse() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false"));
        }

    @Test
        void testToBooleanObject_StringNull() {
            assertNull(BooleanUtils.toBooleanObject((String) null));
        }

    @Test
        void testToBooleanObject_StringY() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("y"));
        }

    @Test
        void testToBooleanObject_StringN() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("n"));
        }

    @Test
        void testToBooleanObject_StringT() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("t"));
        }

    @Test
        void testToBooleanObject_StringF() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("f"));
        }

    @Test
        void testToBooleanObject_String1() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("1"));
        }

    @Test
        void testToBooleanObject_String0() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("0"));
        }

    @Test
        void testToBooleanObject_StringOn() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("on"));
        }

    @Test
        void testToBooleanObject_StringOff() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("off"));
        }

    @Test
        void testToBooleanObject_StringYes() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("yes"));
        }

    @Test
        void testToBooleanObject_StringNo() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("no"));
        }

    @Test
        void testToBooleanObject_StringTrueLongerCase() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TRUE"));
        }

    @Test
        void testToBooleanObject_StringFalseLongerCase() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("FALSE"));
        }

    @Test
        void testToBooleanObject_StringNotMatch() {
            assertNull(BooleanUtils.toBooleanObject("maybe"));
        }

    
    
    @Test
        void testToBoolean_IntegerWithTrueValueNullAndFalseValueNull() {
            assertTrue(BooleanUtils.toBoolean(null, null, 1));
        }

    
    @Test
        void testToBoolean_IntegerNoMatch_3() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 0, 1));
        }

    
    
    
    @Test
        void testToBoolean_StringNoMatch_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("maybe", "yes", "no"));
        }

    
    @Test
        void testToBooleanObject_IntegerNoMatch_4() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(3, 0, 1, 2));
        }

    
    
    @Test
        void testToBooleanObject_StringWithBothStringsNull() {
            assertNull(BooleanUtils.toBooleanObject("true", null, null, "true"));
        }

    @Test
        void testToBooleanObject_StringNoMatch_3() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("maybe", "yes", "no", "unknown"));
        }

    @Test
        void testToBooleanObject_StringExactMatchTrueLongerCase() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("trUe"));
        }

    @Test
        void testToBooleanObject_StringFalseLongerCase_2() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("fAlSe"));
        }

    @Test
        void testToBooleanObject_StringNotMatch_2() {
            assertNull(BooleanUtils.toBooleanObject("unknown"));
        }

    @Test
        void testToInteger_true() {
            assertEquals(1, BooleanUtils.toInteger(true));
        }

    @Test
        void testToInteger_false() {
            assertEquals(0, BooleanUtils.toInteger(false));
        }

    @Test
        void testToInteger_trueWithCustomValues() {
            assertEquals(5, BooleanUtils.toInteger(true, 5, 10));
        }

    @Test
        void testToInteger_falseWithCustomValues() {
            assertEquals(10, BooleanUtils.toInteger(false, 5, 10));
        }

    @Test
        void testToInteger_nullWithCustomValues() {
            assertEquals(20, BooleanUtils.toInteger(null, 5, 10, 20));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAnd_BooleanArray_withNulls() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and((Boolean[]) null));
        }

    @Test
        void testOr_BooleanArray_withNulls() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or((Boolean[]) null));
        }

    @Test
        void testToBoolean_IntegerWithTrueValueNullAndFalseValueNotNull() {
            assertFalse(BooleanUtils.toBoolean(null, null, 1));
        }

    @Test
        void testToBoolean_IntegerWithTrueValueNotNullAndFalseValueNull() {
            assertTrue(BooleanUtils.toBoolean(0, 0, null));
        }

    @Test
        void testToBoolean_IntegerNoMatch_3_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 0, 1));
        }

    @Test
        void testToBoolean_StringWithTrueStringNullAndFalseStringNotNull() {
            assertFalse(BooleanUtils.toBoolean("false", null, "false"));
        }

    @Test
        void testToBoolean_StringWithTrueStringNotNullAndFalseStringNull() {
            assertTrue(BooleanUtils.toBoolean("true", "true", null));
        }

    @Test
        void testToBoolean_StringNoMatch_2_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("maybe", "true", "false"));
        }

    @Test
        void testToBooleanObject_IntegerWithTrueValueNullAndFalseValueNotNull() {
            assertNull(BooleanUtils.toBooleanObject(null, null, 1));
        }

    @Test
        void testToBooleanObject_IntegerWithTrueValueNotNullAndFalseValueNull() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(0, 0, null));
        }

    @Test
        void testToBooleanObject_IntegerNoMatch_4_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(2, 0, 1, 3));
        }

    @Test
        void testToBooleanObject_StringWithTrueStringNullAndFalseStringNotNull() {
            assertNull(BooleanUtils.toBooleanObject("false", null, "false"));
        }

    @Test
        void testToBooleanObject_StringWithTrueStringNotNullAndFalseStringNull() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true", "true", null));
        }

    @Test
        void testToBooleanObject_StringNoMatch_3_2() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("maybe", "true", "false"));
        }

    @Test
        void testToBooleanObject_StringExactMatchTrueLongerCase_2() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("trUe"));
        }

    @Test
        void testToBooleanObject_StringFalseLongerCase_2_2() {
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("faLse"));
        }

    @Test
        void testToBooleanObject_StringNotMatch_2_2() {
            assertNull(BooleanUtils.toBooleanObject("unknown"));
        }

    @Test
        void testToInteger_trueWithCustomValuesAndNullValue() {
            assertEquals(10, BooleanUtils.toInteger(true, 10, 20, 30));
        }

    @Test
        void testToInteger_falseWithCustomValuesAndNullValue() {
            assertEquals(20, BooleanUtils.toInteger(false, 10, 20, 30));
        }

    @Test
        void testToInteger_nullWithCustomValuesAndNullValue() {
            assertEquals(30, BooleanUtils.toInteger(null, 10, 20, 30));
        }
}
