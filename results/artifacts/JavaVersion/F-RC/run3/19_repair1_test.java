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
        public void testGetValidVersionWithDecimal() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17.0"));
        }

    @Test
        public void testGetValidVersionWithComma() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17,0"));
        }

    @Test
        public void testGetValidVersionWithLeadingZero() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("017.0"));
        }

    @Test
        public void testAtLeastValidVersion() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_15));
        }

    @Test
        public void testAtMostValidVersion() {
            assertTrue(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_17));
        }
}
