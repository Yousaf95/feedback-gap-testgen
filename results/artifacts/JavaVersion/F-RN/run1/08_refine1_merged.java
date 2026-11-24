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
        public void testAtLeastMethodForNewestVersion() {
            assertTrue(JavaVersion.JAVA_21.atLeast(JavaVersion.JAVA_RECENT));
        }

    @Test
        public void testAtMostMethodForOldestVersion() {
            assertTrue(JavaVersion.JAVA_0_9.atMost(JavaVersion.JAVA_RECENT));
        }

    @Test
        public void testAtLeastMethodWithNullArgument() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_17.atLeast(null));
        }

    @Test
        public void testAtMostMethodWithNullArgument() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_17.atMost(null));
        }
}
