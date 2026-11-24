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
        public void testGetUnknownVersion() {
            assertNull(JavaVersion.get("25"));
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

    @Test
        public void testGetMaxVersion() {
            assertTrue(JavaVersion.maxVersion() > 0);
        }

    @Test
        public void testGetFloatVersionNonDecimal() {
            assertEquals(20f, JavaVersion.toFloatVersion("20"));
        }

    @Test
        public void testGetFloatVersionDecimal() {
            assertEquals(1.8f, JavaVersion.toFloatVersion("1.8"));
        }
}
