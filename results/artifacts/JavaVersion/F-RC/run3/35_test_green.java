package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class JavaVersion_GenTest {

    
    
    @Test
    public void testToString() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetNullInput() {
            assertNull(JavaVersion.get(null));
        }

    @Test
        public void testGetUnknownVersion() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("25"));
        }

    @Test
        public void testGetRecentVersionWithDecimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("10.9"));
        }

    @Test
        public void testGetRecentVersionGreaterThanTen() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("25.0"));
        }

    @Test
        public void testGetValidVersion() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17"));
        }

    
    
    
    @Test
        public void testAtLeastValidVersion() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_15));
        }

    @Test
        public void testAtMostValidVersion() {
            assertTrue(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_17));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetJava_0_9() {
            assertEquals(JavaVersion.JAVA_0_9, JavaVersion.get("0.9"));
        }

    @Test
        public void testGetJava_1_1() {
            assertEquals(JavaVersion.JAVA_1_1, JavaVersion.get("1.1"));
        }

    @Test
        public void testGetJava_1_2() {
            assertEquals(JavaVersion.JAVA_1_2, JavaVersion.get("1.2"));
        }

    @Test
        public void testGetJava_1_3() {
            assertEquals(JavaVersion.JAVA_1_3, JavaVersion.get("1.3"));
        }

    @Test
        public void testGetJava_1_4() {
            assertEquals(JavaVersion.JAVA_1_4, JavaVersion.get("1.4"));
        }

    @Test
        public void testGetJava_1_5() {
            assertEquals(JavaVersion.JAVA_1_5, JavaVersion.get("1.5"));
        }

    @Test
        public void testGetJava_1_6() {
            assertEquals(JavaVersion.JAVA_1_6, JavaVersion.get("1.6"));
        }

    @Test
        public void testGetJava_1_7() {
            assertEquals(JavaVersion.JAVA_1_7, JavaVersion.get("1.7"));
        }

    @Test
        public void testGetJava_1_8() {
            assertEquals(JavaVersion.JAVA_1_8, JavaVersion.get("1.8"));
        }

    @Test
        public void testGetJava_9() {
            assertEquals(JavaVersion.JAVA_9, JavaVersion.get("9"));
        }

    @Test
        public void testGetJava_10() {
            assertEquals(JavaVersion.JAVA_10, JavaVersion.get("10"));
        }

    @Test
        public void testGetJava_11() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.get("11"));
        }

    @Test
        public void testGetJava_12() {
            assertEquals(JavaVersion.JAVA_12, JavaVersion.get("12"));
        }

    @Test
        public void testGetJava_13() {
            assertEquals(JavaVersion.JAVA_13, JavaVersion.get("13"));
        }

    @Test
        public void testGetJava_14() {
            assertEquals(JavaVersion.JAVA_14, JavaVersion.get("14"));
        }

    @Test
        public void testGetJava_15() {
            assertEquals(JavaVersion.JAVA_15, JavaVersion.get("15"));
        }

    @Test
        public void testGetJava_16() {
            assertEquals(JavaVersion.JAVA_16, JavaVersion.get("16"));
        }

    @Test
        public void testGetJava_17() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17"));
        }

    @Test
        public void testGetJava_18() {
            assertEquals(JavaVersion.JAVA_18, JavaVersion.get("18"));
        }

    @Test
        public void testGetJava_19() {
            assertEquals(JavaVersion.JAVA_19, JavaVersion.get("19"));
        }

    @Test
        public void testGetJava_20() {
            assertEquals(JavaVersion.JAVA_20, JavaVersion.get("20"));
        }

    @Test
        public void testGetJava_21() {
            assertEquals(JavaVersion.JAVA_21, JavaVersion.get("21"));
        }

    @Test
        public void testGetRecentVersionWithDecimal_2() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("17.9"));
        }

    @Test
        public void testGetRecentVersionGreaterThanTen_2() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("25"));
        }

    
    @Test
        public void testAtLeastValidVersion_2() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtMostValidVersion_2() {
            assertTrue(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_17));
        }
}
