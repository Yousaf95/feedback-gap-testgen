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
        public void testGetUnknownVersion() {
            assertNull(JavaVersion.get("25"));
        }

    @Test
        public void testGetRecentVersion() {
            JavaVersion version = JavaVersion.get("10.9");
            assertEquals(JavaVersion.JAVA_RECENT, version);
        }

    @Test
        public void testGetDecimalVersion() {
            JavaVersion version = JavaVersion.get("1.5.1");
            assertEquals(JavaVersion.JAVA_1_5, version);
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
}
