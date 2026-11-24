package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testToStringMethod() {
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetNullInput() {
            assertNull(JavaVersion.get(null));
        }

    
    @Test
        public void testGetDecimalVersion() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.8.9"));
        }

    @Test
        public void testGetRecentVersion() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("20.5"));
        }

    @Test
        public void testGetNonDecimalVersion() {
            assertEquals(JavaVersion.JAVA_1_5, JavaVersion.get("1.5"));
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
        public void testGetRecentVersion_2() {
            assertTrue(JavaVersion.JAVA_RECENT.value > 21);
        }

    @Test
        public void testGetNonDecimalVersion_2() {
            assertNull(JavaVersion.get("invalid"));
        }

    @Test
        public void testMaxVersion() {
            assertEquals(21, JavaVersion.maxVersion());
        }
}
