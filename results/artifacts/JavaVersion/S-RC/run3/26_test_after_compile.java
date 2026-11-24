package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    
    
    
    @Test
    public void testToStringMethod() {
        assertEquals("1.5", JavaVersion.JAVA_1_5.toString());
        assertEquals("17", JavaVersion.JAVA_17.toString());
        // Add more assertions for other enum values
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetNullInput() {
            assertNull(JavaVersion.get(null));
        }

    
    @Test
        public void testGetRecentVersion() {
            JavaVersion version = JavaVersion.get("10.9");
            assertEquals(JavaVersion.JAVA_RECENT, version);
        }

    
    @Test
        public void testAtLeastMethod() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_16));
            assertFalse(JavaVersion.JAVA_16.atLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtMostMethod() {
            assertTrue(JavaVersion.JAVA_16.atMost(JavaVersion.JAVA_17));
            assertFalse(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_16));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetJava09() {
            assertEquals(JavaVersion.JAVA_0_9, JavaVersion.get("0.9"));
        }

    @Test
        public void testGetJava11() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.get("1.1"));
        }

    @Test
        public void testGetJava12() {
            assertEquals(JavaVersion.JAVA_12, JavaVersion.get("1.2"));
        }

    @Test
        public void testGetJava13() {
            assertEquals(JavaVersion.JAVA_13, JavaVersion.get("1.3"));
        }

    @Test
        public void testGetJava14() {
            assertEquals(JavaVersion.JAVA_14, JavaVersion.get("1.4"));
        }

    @Test
        public void testGetJava15() {
            assertEquals(JavaVersion.JAVA_15, JavaVersion.get("1.5"));
        }

    @Test
        public void testGetJava16() {
            assertEquals(JavaVersion.JAVA_16, JavaVersion.get("1.6"));
        }

    @Test
        public void testGetJava17() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("1.7"));
        }

    @Test
        public void testGetJava18() {
            assertEquals(JavaVersion.JAVA_18, JavaVersion.get("1.8"));
        }

    @Test
        public void testGetJava9() {
            assertEquals(JavaVersion.JAVA_9, JavaVersion.get("9"));
        }

    @Test
        public void testGetJava10() {
            assertEquals(JavaVersion.JAVA_10, JavaVersion.get("10"));
        }

    @Test
        public void testGetJava11Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.1.1"));
        }

    @Test
        public void testGetJava12Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.2.5"));
        }

    @Test
        public void testGetJava13Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.3.9"));
        }

    @Test
        public void testGetJava14Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.4.0"));
        }

    @Test
        public void testGetJava15Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.5.2"));
        }

    @Test
        public void testGetJava16Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.6.3"));
        }

    @Test
        public void testGetJava17Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.7.4"));
        }

    @Test
        public void testGetJava18Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.8.5"));
        }

    @Test
        public void testGetJava9Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("9.0.1"));
        }

    @Test
        public void testGetJava10Decimal() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("10.0.2"));
        }

    @Test
        public void testGetJavaRecentForNewerVersion() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("99.0"));
        }

    @Test
        public void testGetJavaRecentForOlderVersion() {
            assertEquals(JavaVersion.JAVA_21, JavaVersion.get("21.0"));
        }

    @Test
        public void testGetJavaNullInput() {
            assertNull(JavaVersion.get(null));
        }

    @Test
        public void testGetInvalidVersion() {
            assertNull(JavaVersion.get("invalid"));
        }
}
