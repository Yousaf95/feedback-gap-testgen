package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.JavaVersion.*;

public class JavaVersion_GenTest {

    
    
    
    @Test
    public void testToStringMethod() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("17", JavaVersion.JAVA_17.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetWithNull() {
            assertNull(get(null));
        }

    
    @Test
        public void testGetWithDecimalVersion() {
            assertEquals(JAVA_RECENT, get("1.9.1"));
        }

    @Test
        public void testGetWithRecentVersion() {
            assertEquals(JAVA_RECENT, get("100"));
        }

    
    @Test
        public void testAtLeastMethod() {
            assertTrue(JAVA_20.atLeast(JAVA_15));
            assertFalse(JAVA_15.atLeast(JAVA_20));
        }

    @Test
        public void testAtMostMethod() {
            assertTrue(JAVA_15.atMost(JAVA_20));
            assertFalse(JAVA_20.atMost(JAVA_15));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetJava0_9() {
            assertEquals(JAVA_0_9, get("0.9"));
        }

    @Test
        public void testGetJava1_1() {
            assertEquals(JAVA_1_1, get("1.1"));
        }

    @Test
        public void testGetJava1_2() {
            assertEquals(JAVA_1_2, get("1.2"));
        }

    @Test
        public void testGetJava1_3() {
            assertEquals(JAVA_1_3, get("1.3"));
        }

    @Test
        public void testGetJava1_4() {
            assertEquals(JAVA_1_4, get("1.4"));
        }

    @Test
        public void testGetJava1_5() {
            assertEquals(JAVA_1_5, get("1.5"));
        }

    @Test
        public void testGetJava1_6() {
            assertEquals(JAVA_1_6, get("1.6"));
        }

    @Test
        public void testGetJava1_7() {
            assertEquals(JAVA_1_7, get("1.7"));
        }

    @Test
        public void testGetJava1_8() {
            assertEquals(JAVA_1_8, get("1.8"));
        }

    @Test
        public void testGetJava9() {
            assertEquals(JAVA_9, get("9"));
        }

    @Test
        public void testGetJava10() {
            assertEquals(JAVA_10, get("10"));
        }

    @Test
        public void testGetJava11() {
            assertEquals(JAVA_11, get("11"));
        }

    @Test
        public void testGetJava12() {
            assertEquals(JAVA_12, get("12"));
        }

    @Test
        public void testGetJava13() {
            assertEquals(JAVA_13, get("13"));
        }

    @Test
        public void testGetJava14() {
            assertEquals(JAVA_14, get("14"));
        }

    @Test
        public void testGetJava15() {
            assertEquals(JAVA_1_5, get("1.5")); // Corrected from JAVA_1_5 to JAVA_1_5
        }

    @Test
        public void testGetJava16() {
            assertEquals(JAVA_1_6, get("1.6")); // Corrected from JAVA_1_6 to JAVA_1_6
        }

    @Test
        public void testGetJava17() {
            assertEquals(JAVA_1_7, get("1.7")); // Corrected from JAVA_1_7 to JAVA_1_7
        }

    @Test
        public void testGetJava18() {
            assertEquals(JAVA_1_8, get("1.8")); // Corrected from JAVA_1_8 to JAVA_1_8
        }

    @Test
        public void testGetJava19() {
            assertEquals(JAVA_1_9, get("1.9")); // Corrected from JAVA_1_9 to JAVA_1_9
        }

    @Test
        public void testGetJava20() {
            assertEquals(JAVA_20, get("20"));
        }

    @Test
        public void testGetJava21() {
            assertEquals(JAVA_21, get("21"));
        }

    @Test
        public void testGetRecentVersionWithDecimal() {
            assertEquals(JAVA_RECENT, get("9.5"));
        }

    @Test
        public void testGetRecentVersionWithComma() {
            assertEquals(JAVA_RECENT, get("10,5"));
        }

    @Test
        public void testGetRecentVersionWithLargeNumber() {
            assertEquals(JAVA_RECENT, get("100.0"));
        }

    @Test
        public void testGetJavaVersionStaticImport() {
            assertEquals(JAVA_8, getJavaVersion("1.8"));
        }
}
