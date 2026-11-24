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
        public void testGetWithUnrecognizedVersion() {
            assertNull(get("25"));
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
        public void testGetWithDeprecatedVersion() {
            assertEquals(JAVA_1_9, get("1.9"));
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
}
