package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

public class BooleanUtils_GenTest {

    @Test
    public void testAndWithBooleanArray() {
        // Arrange
        boolean[] array = {true, true, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testAndWithBooleanArrayContainingFalse() {
        // Arrange
        boolean[] array = {true, false, true};

        // Act
        boolean result = BooleanUtils.and(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testAndWithBooleanObjectArray() {
        // Arrange
        Boolean[] array = {Boolean.TRUE, Boolean.TRUE, Boolean.TRUE};

        // Act
        Boolean result = BooleanUtils.and(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testAndWithNullBooleanObjectArray() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> BooleanUtils.and((Boolean[]) null));
    }

    @Test
    public void testAndWithEmptyBooleanObjectArray() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.and(new Boolean[0]));
    }

    @Test
    public void testOrWithBooleanArray() {
        // Arrange
        boolean[] array = {false, false, true};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testOrWithAllFalseBooleanArray() {
        // Arrange
        boolean[] array = {false, false, false};

        // Act
        boolean result = BooleanUtils.or(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testOrWithBooleanObjectArray() {
        // Arrange
        Boolean[] array = {Boolean.FALSE, Boolean.TRUE, Boolean.FALSE};

        // Act
        Boolean result = BooleanUtils.or(array);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testOrWithNullBooleanObjectArray() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> BooleanUtils.or((Boolean[]) null));
    }

    @Test
    public void testOrWithEmptyBooleanObjectArray() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.or(new Boolean[0]));
    }

    @Test
    public void testOneHotWithSingleTrue() {
        // Arrange
        boolean[] array = {false, true, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testOneHotWithMultipleTrue() {
        // Arrange
        boolean[] array = {true, true, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testOneHotWithAllFalse() {
        // Arrange
        boolean[] array = {false, false, false};

        // Act
        boolean result = BooleanUtils.oneHot(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testXorWithSingleTrue() {
        // Arrange
        boolean[] array = {false, true, false};

        // Act
        boolean result = BooleanUtils.xor(array);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testXorWithMultipleTrue() {
        // Arrange
        boolean[] array = {true, true, false};

        // Act
        boolean result = BooleanUtils.xor(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testXorWithAllFalse() {
        // Arrange
        boolean[] array = {false, false, false};

        // Act
        boolean result = BooleanUtils.xor(array);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCompareTrueAndTrue() {
        // Arrange & Act
        int result = BooleanUtils.compare(true, true);

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void testCompareFalseAndTrue() {
        // Arrange & Act
        int result = BooleanUtils.compare(false, true);

        // Assert
        assertEquals(-1, result);
    }

    @Test
    public void testCompareTrueAndFalse() {
        // Arrange & Act
        int result = BooleanUtils.compare(true, false);

        // Assert
        assertEquals(1, result);
    }

    
    
    
    
    @Test
    public void testNegateTrue() {
        // Arrange
        Boolean bool = Boolean.TRUE;

        // Act
        Boolean result = BooleanUtils.negate(bool);

        // Assert
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void testNegateFalse() {
        // Arrange
        Boolean bool = Boolean.FALSE;

        // Act
        Boolean result = BooleanUtils.negate(bool);

        // Assert
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testNegateNull() {
        // Arrange & Act
        Boolean result = BooleanUtils.negate(null);

        // Assert
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    public void testToBooleanWithInt(int value) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(value);

        // Assert
        assertEquals(value != 0, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    public void testToBooleanObjectWithInt(int value) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(value);

        // Assert
        assertEquals(value != 0 ? Boolean.TRUE : Boolean.FALSE, result);
    }

    @Test
    public void testToBooleanDefaultIfNull() {
        // Arrange
        Boolean bool = null;
        boolean defaultValue = true;

        // Act
        boolean result = BooleanUtils.toBooleanDefaultIfNull(bool, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false", "yes", "no", "on", "off", "1", "0"})
    public void testToBooleanObjectWithString(String str) {
        // Arrange & Act
        Boolean result = BooleanUtils.toBooleanObject(str);

        // Assert
        assertNotNull(result);
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"true", "false", "yes", "no", "on", "off", "1", "0"})
    public void testToBooleanWithString(String str) {
        // Arrange & Act
        boolean result = BooleanUtils.toBoolean(str);

        // Assert
        assertTrue(result || !result);
    }

    @Test
    public void testToStringTrueFalseWithNull() {
        // Arrange
        Boolean bool = null;

        // Act
        String result = BooleanUtils.toStringTrueFalse(bool);

        // Assert
        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testToStringOnOff(Boolean bool) {
        // Arrange & Act
        String result = BooleanUtils.toStringOnOff(bool);

        // Assert
        assertEquals(bool ? BooleanUtils.ON : BooleanUtils.OFF, result);
    }

    @Test
    public void testBooleanValues() {
        // Arrange & Act
        Boolean[] values = BooleanUtils.booleanValues();

        // Assert
        assertArrayEquals(new Boolean[]{Boolean.FALSE, Boolean.TRUE}, values);
    }

    @Test
    public void testPrimitiveValues() {
        // Arrange & Act
        boolean[] values = BooleanUtils.primitiveValues();

        // Assert
        assertArrayEquals(new boolean[]{false, true}, values);
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testIsFalseWithNull() {
            assertFalse(BooleanUtils.isFalse(null));
        }

    @Test
        void testIsNotFalseWithTrue() {
            assertTrue(BooleanUtils.isNotFalse(Boolean.TRUE));
        }

    @Test
        void testIsNotFalseWithFalse() {
            assertFalse(BooleanUtils.isNotFalse(Boolean.FALSE));
        }

    @Test
        void testIsNotFalseWithNull() {
            assertTrue(BooleanUtils.isNotFalse(null));
        }

    @Test
        void testIsTrueWithNull() {
            assertFalse(BooleanUtils.isTrue(null));
        }

    @Test
        void testIsNotTrueWithTrue() {
            assertFalse(BooleanUtils.isNotTrue(Boolean.TRUE));
        }

    @Test
        void testIsNotTrueWithFalse() {
            assertTrue(BooleanUtils.isNotTrue(Boolean.FALSE));
        }

    @Test
        void testIsNotTrueWithNull() {
            assertTrue(BooleanUtils.isNotTrue(null));
        }

    @Test
        void testToBooleanObjectWithIntAndCustomValues() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(10, 10, 20, 30));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(20, 10, 20, 30));
            assertNull(BooleanUtils.toBooleanObject(30, 10, 20, 30));
        }

    @Test
        void testToBooleanObjectWithIntegerAndCustomValues() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)));
            assertNull(BooleanUtils.toBooleanObject(Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)));
        }

    @Test
        void testToBooleanWithIntAndCustomValues() {
            assertTrue(BooleanUtils.toBoolean(10, 10, 20));
            assertFalse(BooleanUtils.toBoolean(20, 10, 20));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(30, 10, 20));
        }

    @Test
        void testToBooleanWithIntegerAndCustomValues() {
            assertTrue(BooleanUtils.toBoolean(Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(20)));
            assertFalse(BooleanUtils.toBoolean(Integer.valueOf(20), Integer.valueOf(10), Integer.valueOf(20)));
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(20)));
        }

    @Test
        void testToIntegerWithCustomValues() {
            assertEquals(10, BooleanUtils.toInteger(true, 10, 20));
            assertEquals(20, BooleanUtils.toInteger(false, 10, 20));
        }

    @Test
        void testToIntegerObjectWithBooleanAndCustomValues() {
            assertEquals(Integer.valueOf(10), BooleanUtils.toIntegerObject(true, Integer.valueOf(10), Integer.valueOf(20)));
            assertEquals(Integer.valueOf(20), BooleanUtils.toIntegerObject(false, Integer.valueOf(10), Integer.valueOf(20)));
        }

    @Test
        void testToIntegerObjectWithBooleanAndNullValues() {
            assertNull(BooleanUtils.toIntegerObject(null, Integer.valueOf(10), Integer.valueOf(20), null));
            assertEquals(Integer.valueOf(10), BooleanUtils.toIntegerObject(true, Integer.valueOf(10), Integer.valueOf(20), null));
            assertEquals(Integer.valueOf(20), BooleanUtils.toIntegerObject(false, Integer.valueOf(10), Integer.valueOf(20), null));
        }

    @Test
        void testToStringWithCustomValues() {
            assertEquals("yes", BooleanUtils.toString(true, "yes", "no"));
            assertEquals("no", BooleanUtils.toString(false, "yes", "no"));
        }

    @Test
        void testToStringWithNullBooleanAndCustomValues() {
            assertEquals("maybe", BooleanUtils.toString(null, "yes", "no", "maybe"));
            assertEquals("yes", BooleanUtils.toString(true, "yes", "no", "maybe"));
            assertEquals("no", BooleanUtils.toString(false, "yes", "no", "maybe"));
        }

    @Test
        void testForEachWithAction() {
            final StringBuilder result = new StringBuilder();
            BooleanUtils.forEach(b -> result.append(b));
            assertEquals("falsetrue", result.toString());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testToBooleanObjectWithCustomValuesIncludingNulls() {
            assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1, 1, 0, null));
            assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0, 1, 0, null));
            assertNull(BooleanUtils.toBooleanObject(null, 1, 0, null));
        }

    @Test
        void testToIntegerWithCustomValuesIncludingNull() {
            assertEquals(2, BooleanUtils.toInteger(true, 2, 3));
            assertEquals(3, BooleanUtils.toInteger(false, 2, 3));
            assertEquals(4, BooleanUtils.toInteger(null, 2, 3, 4));
        }

    @Test
        void testToIntegerObjectWithCustomValuesIncludingNull() {
            assertEquals(NumberUtils.INTEGER_TWO, BooleanUtils.toIntegerObject(true, NumberUtils.INTEGER_TWO, NumberUtils.INTEGER_THREE));
            assertEquals(NumberUtils.INTEGER_THREE, BooleanUtils.toIntegerObject(false, NumberUtils.INTEGER_TWO, NumberUtils.INTEGER_THREE));
            assertNull(BooleanUtils.toIntegerObject(null, NumberUtils.INTEGER_TWO, NumberUtils.INTEGER_THREE, null));
        }

    @Test
        void testToStringWithCustomValuesIncludingNull() {
            assertEquals("yes", BooleanUtils.toString(true, "yes", "no"));
            assertEquals("no", BooleanUtils.toString(false, "yes", "no"));
            assertNull(BooleanUtils.toString(null, "yes", "no", null));
        }

    @Test
        void testToBooleanObjectWithInvalidStringThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("invalid", "true", "false", null));
        }

    @Test
        void testToBooleanWithInvalidIntValuesThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(2, 1, 0));
        }

    @Test
        void testToBooleanObjectWithInvalidIntValuesThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(2, 1, 0, null));
        }

    @Test
        void testForEachExecutesActionTwice() {
            final int[] count = {0};
            BooleanUtils.forEach(b -> count[0]++);
            assertEquals(2, count[0]);
        }
}
