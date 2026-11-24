package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    
    
    
    @Test
    public void testToStringMethod() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
        assertTrue(JavaVersion.JAVA_RECENT.toString().length() > 0);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetNullInput() {
            assertNull(JavaVersion.get(null));
        }

    
    @Test
        public void testGetRecentVersion() {
            assertNotNull(JavaVersion.get("10.5"));
        }

    
    @Test
        public void testAtLeastMethod() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_15));
        }

    @Test
        public void testAtMostMethod() {
            assertTrue(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_17));
        }
}
