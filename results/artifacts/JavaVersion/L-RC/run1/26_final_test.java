package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaVersion_GenTest {

    @Test
    void testJavaVersionToString() {
        assertEquals("1.8", JavaVersion.JAVA_1_8.toString());
        assertEquals("9", JavaVersion.JAVA_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    @Test
    void testAtLeastWithLowerVersion() {
        assertTrue(JavaVersion.JAVA_11.atLeast(JavaVersion.JAVA_10));
    }

    @Test
    void testAtLeastWithSameVersion() {
        assertTrue(JavaVersion.JAVA_11.atLeast(JavaVersion.JAVA_11));
    }

    @Test
    void testAtLeastWithHigherVersion() {
        assertFalse(JavaVersion.JAVA_11.atLeast(JavaVersion.JAVA_12));
    }

    @Test
    void testAtMostWithLowerVersion() {
        assertFalse(JavaVersion.JAVA_11.atMost(JavaVersion.JAVA_10));
    }

    @Test
    void testAtMostWithSameVersion() {
        assertTrue(JavaVersion.JAVA_11.atMost(JavaVersion.JAVA_11));
    }

    @Test
    void testAtMostWithHigherVersion() {
        assertTrue(JavaVersion.JAVA_11.atMost(JavaVersion.JAVA_12));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testGetWithNullString() {
            assertNull(JavaVersion.get(null));
        }

    @Test
        void testGetWithUnknownVersion() {
            assertNull(JavaVersion.get("2.0"));
        }

    @Test
        void testGetWithJava0_9() {
            assertEquals(JavaVersion.JAVA_0_9, JavaVersion.get("0.9"));
        }

    @Test
        void testGetWithJava1_1() {
            assertEquals(JavaVersion.JAVA_1_1, JavaVersion.get("1.1"));
        }

    @Test
        void testGetWithJava1_2() {
            assertEquals(JavaVersion.JAVA_1_2, JavaVersion.get("1.2"));
        }

    @Test
        void testGetWithJava1_3() {
            assertEquals(JavaVersion.JAVA_1_3, JavaVersion.get("1.3"));
        }

    @Test
        void testGetWithJava1_4() {
            assertEquals(JavaVersion.JAVA_1_4, JavaVersion.get("1.4"));
        }

    @Test
        void testGetWithJava1_5() {
            assertEquals(JavaVersion.JAVA_1_5, JavaVersion.get("1.5"));
        }

    @Test
        void testGetWithJava1_6() {
            assertEquals(JavaVersion.JAVA_1_6, JavaVersion.get("1.6"));
        }

    @Test
        void testGetWithJava1_7() {
            assertEquals(JavaVersion.JAVA_1_7, JavaVersion.get("1.7"));
        }

    @Test
        void testGetWithJava1_8() {
            assertEquals(JavaVersion.JAVA_1_8, JavaVersion.get("1.8"));
        }

    @Test
        void testGetWithJava9() {
            assertEquals(JavaVersion.JAVA_9, JavaVersion.get("9"));
        }

    @Test
        void testGetWithJava10() {
            assertEquals(JavaVersion.JAVA_10, JavaVersion.get("10"));
        }

    @Test
        void testGetWithJava11() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.get("11"));
        }

    @Test
        void testGetWithJava12() {
            assertEquals(JavaVersion.JAVA_12, JavaVersion.get("12"));
        }

    @Test
        void testGetWithJava13() {
            assertEquals(JavaVersion.JAVA_13, JavaVersion.get("13"));
        }

    @Test
        void testGetWithJava14() {
            assertEquals(JavaVersion.JAVA_14, JavaVersion.get("14"));
        }

    @Test
        void testGetWithJava15() {
            assertEquals(JavaVersion.JAVA_15, JavaVersion.get("15"));
        }

    @Test
        void testGetWithJava16() {
            assertEquals(JavaVersion.JAVA_16, JavaVersion.get("16"));
        }

    @Test
        void testGetWithJava17() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17"));
        }

    @Test
        void testGetWithJava18() {
            assertEquals(JavaVersion.JAVA_18, JavaVersion.get("18"));
        }

    @Test
        void testGetWithJava19() {
            assertEquals(JavaVersion.JAVA_19, JavaVersion.get("19"));
        }

    @Test
        void testGetWithJava20() {
            assertEquals(JavaVersion.JAVA_20, JavaVersion.get("20"));
        }

    @Test
        void testGetWithJava21() {
            assertEquals(JavaVersion.JAVA_21, JavaVersion.get("21"));
        }

    @Test
        void testGetWithRecentVersionAbove10() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("10.9"));
        }

    @Test
        void testGetWithRecentVersionBelow10() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.9"));
        }

    @Test
        void testGetJavaVersionDelegatesToGet() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.getJavaVersion("11"));
        }
}
