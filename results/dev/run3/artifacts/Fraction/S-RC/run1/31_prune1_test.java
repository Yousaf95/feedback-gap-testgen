package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.apache.commons.lang3.math.Fraction.*;

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
        @ValueSource(ints = {-1, 0, 1})
        public void testGetFractionSign(int sign) {
            assertEquals(sign, getFraction(sign * 1.5).getNumerator());
        }

    @ParameterizedTest
        @ValueSource(doubles = {Integer.MAX_VALUE + 1.0, Double.NaN})
        public void testGetFractionDoubleException(double value) {
            assertThrows(ArithmeticException.class, () -> getFraction(value));
        }

    @ParameterizedTest
        @ValueSource(ints = {-2, -1, 0, 1, 2})
        public void testGetFractionDenominatorLarge_2(int denominator) {
            if (denominator != 0) {
                assertThrows(ArithmeticException.class, () -> getFraction(1, denominator));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetFractionNumeratorLarge(int numerator) {
            if (numerator != 0) {
                assertThrows(ArithmeticException.class, () -> getFraction(numerator, 1));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetFractionDenominatorNegative(int denominator) {
            if (denominator != 0) {
                assertThrows(ArithmeticException.class, () -> getFraction(1, -denominator));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetFractionNumeratorNegative(int numerator) {
            if (numerator != 0) {
                assertThrows(ArithmeticException.class, () -> getFraction(-numerator, 1));
            }
        }

    @ParameterizedTest
        @ValueSource(strings = {"-1.5", "2.5"})
        public void testGetFractionStringDouble(String str) {
            assertEquals(3, getFraction(str).getNumerator());
            assertEquals(2, getFraction(str).getDenominator());
        }

    @ParameterizedTest
        @ValueSource(strings = {"-1 1/2", "2 1/4"})
        public void testGetFractionStringWholeAndFraction(String str) {
            assertEquals(5, getFraction(str).getNumerator());
            assertEquals(2, getFraction(str).getDenominator());
        }

    @ParameterizedTest
        @ValueSource(strings = {"-3/2", "1/0"})
        public void testGetFractionStringException(String str) {
            assertThrows(NumberFormatException.class, () -> getFraction(str));
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetReducedFractionNumeratorLarge(int numerator) {
            if (numerator != 0) {
                assertThrows(ArithmeticException.class, () -> getReducedFraction(numerator, 1));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetReducedFractionDenominatorLarge(int denominator) {
            if (denominator != 0) {
                assertThrows(ArithmeticException.class, () -> getReducedFraction(1, denominator));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetReducedFractionDenominatorNegative(int denominator) {
            if (denominator != 0) {
                assertThrows(ArithmeticException.class, () -> getReducedFraction(1, -denominator));
            }
        }

    @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, Integer.MAX_VALUE})
        public void testGetReducedFractionNumeratorNegative(int numerator) {
            if (numerator != 0) {
                assertThrows(ArithmeticException.class, () -> getReducedFraction(-numerator, 1));
            }
        }

    }
