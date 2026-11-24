package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.apache.commons.lang3.math.Fraction.*;
import java.util.Arrays;
import java.util.List;

public class Fraction_GenTest {

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "1, 2",
        "1, 3",
        "2, 3",
        "1, 4",
        "2, 4",
        "3, 4",
        "1, 5",
        "2, 5",
        "3, 5",
        "4, 5"
    })
    public void testGetFraction(int numerator, int denominator) {
        Fraction fraction = Fraction.getFraction(numerator, denominator);
        assertEquals(numerator, fraction.getNumerator());
        assertEquals(denominator, fraction.getDenominator());
    }

    @ParameterizedTest
    @CsvSource({
        "0.5, 1/2",
        "0.3333333333333333, 1/3",
        "0.6666666666666666, 2/3",
        "0.25, 1/4",
        "0.5, 1/2",
        "0.75, 3/4"
    })
    public void testGetFractionDouble(double value, String expected) {
        Fraction fraction = Fraction.getFraction(value);
        assertEquals(expected, fraction.toString());
    }

    @ParameterizedTest
    @CsvSource({
        "1 1/2, 3/2",
        "2 1/4, 9/4"
    })
    public void testGetFractionString(String str, String expected) {
        Fraction fraction = Fraction.getFraction(str);
        assertEquals(expected, fraction.toString());
    }

    @Test
    public void testGetFractionZero() {
        Fraction fraction = Fraction.getFraction(0, 1);
        assertEquals(Fraction.ZERO, fraction);
    }

    @Test
    public void testGetFractionOne() {
        Fraction fraction = Fraction.getFraction(1, 1);
        assertEquals(Fraction.ONE, fraction);
    }

    
    @ParameterizedTest
    @CsvSource({
        "0.5, 2",
        "0.3333333333333333, 3",
        "0.6666666666666666, 3",
        "0.25, 4"
    })
    public void testGetFractionDenominatorLarge(double value, int expectedDenominator) {
        Fraction fraction = Fraction.getFraction(value);
        assertEquals(expectedDenominator, fraction.getDenominator());
    }

    @Test
    public void testAdd() {
        Fraction f1 = Fraction.getFraction(1, 2);
        Fraction f2 = Fraction.getFraction(1, 3);
        Fraction result = f1.add(f2);
        assertEquals("5/6", result.toString());
    }

    @Test
    public void testSubtract() {
        Fraction f1 = Fraction.getFraction(1, 2);
        Fraction f2 = Fraction.getFraction(1, 4);
        Fraction result = f1.subtract(f2);
        assertEquals("1/4", result.toString());
    }

    @Test
    public void testMultiplyBy() {
        Fraction f1 = Fraction.getFraction(1, 2);
        Fraction f2 = Fraction.getFraction(3, 4);
        Fraction result = f1.multiplyBy(f2);
        assertEquals("3/8", result.toString());
    }

    @Test
    public void testDivideBy() {
        Fraction f1 = Fraction.getFraction(1, 2);
        Fraction f2 = Fraction.getFraction(1, 4);
        Fraction result = f1.divideBy(f2);
        assertEquals("2/1", result.toString());
    }

    @Test
    public void testInvert() {
        Fraction f = Fraction.getFraction(3, 4);
        Fraction result = f.invert();
        assertEquals("4/3", result.toString());
    }

    @Test
    public void testPow() {
        Fraction f = Fraction.getFraction(2, 3);
        Fraction result = f.pow(2);
        assertEquals("4/9", result.toString());
    }

    @Test
    public void testAbs() {
        Fraction f1 = Fraction.getFraction(-1, 2);
        Fraction f2 = Fraction.getFraction(1, -2);
        assertEquals(Fraction.ONE_HALF, f1.abs());
        assertEquals(Fraction.ONE_HALF, f2.abs());
    }

    @Test
    public void testReduce() {
        Fraction f = Fraction.getFraction(4, 8);
        Fraction result = f.reduce();
        assertEquals("1/2", result.toString());
    }

    
    
    @ParameterizedTest
        @ValueSource(doubles = {Integer.MAX_VALUE + 1.0, Double.NaN})
        public void testGetFractionDoubleException(double value) {
            assertThrows(ArithmeticException.class, () -> getFraction(value));
        }

    
    
    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetFractionDoubleException_2() {
            assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MAX_VALUE + 1.0));
        }

    @Test
        public void testGetFractionStringDoubleFormat() {
            assertEquals(Fraction.ONE_HALF, Fraction.getFraction("0.5"));
        }

    @Test
        public void testGetFractionStringX_Y_ZFormat() {
            assertEquals(Fraction.valueOf(3, 4), Fraction.getFraction("1 2/4"));
        }

    @Test
        public void testGetFractionStringY_ZFormat() {
            assertEquals(Fraction.ONE_HALF, Fraction.getFraction("1/2"));
        }

    @Test
        public void testGetFractionDenominatorLarge_2() {
            assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        }

    @Test
        public void testAddAndCheckOverflow() {
            assertEquals(Integer.MAX_VALUE, addAndCheck(Integer.MAX_VALUE - 1, 2));
        }

    @Test
        public void testSubtractAndCheckUnderflow() {
            assertEquals(Integer.MIN_VALUE, subAndCheck(Integer.MIN_VALUE + 1, 2));
        }

    @Test
        public void testMulAndCheckOverflow() {
            assertThrows(ArithmeticException.class, () -> mulAndCheck(Integer.MAX_VALUE / 2, 3));
        }

    @Test
        public void testMulPosAndCheckNegative() {
            assertThrows(ArithmeticException.class, () -> mulPosAndCheck(Integer.MIN_VALUE, -1));
        }

    @Test
        public void testGreatestCommonDivisorZeroDenominator() {
            assertEquals(2147483647, greatestCommonDivisor(2147483647, 0));
        }

    @Test
        public void testGetFractionNegativeNumerator() {
            assertThrows(ArithmeticException.class, () -> Fraction.getFraction(-1, 1));
        }

    @Test
        public void testGetFractionNegativeDenominator() {
            assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, -1));
        }

    @Test
        public void testGetFractionLargeNumerator() {
            assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MAX_VALUE + 1, 1));
        }
}
