package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.JavaVersion.*;

public class JavaVersion_GenTest {

    
    @Test
    public void testAtLeastMethod() {
        assertTrue(JavaVersion.JAVA_15.atLeast(JavaVersion.JAVA_10));
        assertFalse(JavaVersion.JAVA_10.atLeast(JavaVersion.JAVA_15));
    }

    @Test
    public void testAtMostMethod() {
        assertTrue(JavaVersion.JAVA_10.atMost(JavaVersion.JAVA_15));
        assertFalse(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_10));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetNull() {
            assertNull(JavaVersion.get(null));
        }

    
    @Test
        public void testGetRecentVersion() {
            JavaVersion version = JavaVersion.get("10.9");
            assertEquals(JavaVersion.JAVA_RECENT, version);
        }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetJava_0_9() {
            assertEquals(JAVA_0_9, get("0.9"));
        }

    @Test
        public void testGetJava_1_1() {
            assertEquals(JAVA_1_1, get("1.1"));
        }

    @Test
        public void testGetJava_1_2() {
            assertEquals(JAVA_1_2, get("1.2"));
        }

    @Test
        public void testGetJava_1_3() {
            assertEquals(JAVA_1_3, get("1.3"));
        }

    @Test
        public void testGetJava_1_4() {
            assertEquals(JAVA_1_4, get("1.4"));
        }

    @Test
        public void testGetJava_1_5() {
            assertEquals(JAVA_1_5, get("1.5"));
        }

    @Test
        public void testGetJava_1_6() {
            assertEquals(JAVA_1_6, get("1.6"));
        }

    @Test
        public void testGetJava_1_7() {
            assertEquals(JAVA_1_7, get("1.7"));
        }

    @Test
        public void testGetJava_1_8() {
            assertEquals(JAVA_1_8, get("1.8"));
        }

    @Test
        public void testGetJava_9() {
            assertEquals(JAVA_9, get("9"));
        }

    @Test
        public void testGetJava_10() {
            assertEquals(JAVA_10, get("10"));
        }

    @Test
        public void testGetJava_11() {
            assertEquals(JAVA_11, get("11"));
        }

    @Test
        public void testGetJava_12() {
            assertEquals(JAVA_12, get("12"));
        }

    @Test
        public void testGetJava_13() {
            assertEquals(JAVA_13, get("13"));
        }

    @Test
        public void testGetJava_14() {
            assertEquals(JAVA_14, get("14"));
        }

    @Test
        public void testGetJava_15() {
            assertEquals(JAVA_15, get("15"));
        }

    @Test
        public void testGetJava_16() {
            assertEquals(JAVA_16, get("16"));
        }

    @Test
        public void testGetJava_17() {
            assertEquals(JAVA_17, get("17"));
        }

    @Test
        public void testGetJava_18() {
            assertEquals(JAVA_18, get("18"));
        }

    @Test
        public void testGetJava_19() {
            assertEquals(JAVA_19, get("19"));
        }

    @Test
        public void testGetJava_20() {
            assertEquals(JAVA_20, get("20"));
        }

    @Test
        public void testGetJava_21() {
            assertEquals(JAVA_21, get("21"));
        }

    @Test
        public void testGetRecentVersion_2() {
            assertEquals(JAVA_RECENT, get("99.0"));
        }

    @Test
        public void testGetDecimalVersionGreaterThanPointNine() {
            assertEquals(JAVA_RECENT, get("1.8.5"));
        }

    @Test
        public void testGetDecimalVersionLessThanTen() {
            assertEquals(JAVA_10, get("9.9"));
        }

    @Test
        public void testGetNull_2() {
            assertNull(get(null));
        }

    @Test
        public void testGetEmptyString() {
            assertNull(get(""));
        }

    @Test
        public void testGetInvalidVersion() {
            assertNull(get("25.0"));
        }
}
