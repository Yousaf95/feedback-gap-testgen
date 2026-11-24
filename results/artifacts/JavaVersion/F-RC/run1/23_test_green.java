package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    
    }
