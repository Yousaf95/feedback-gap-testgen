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
        void testJavaVersionName() {
            assertEquals("11", JavaVersion.JAVA_11.name());
        }

    @Test
        void testJavaRecentIsGreaterThanAllVersions() {
            assertTrue(JavaVersion.JAVA_RECENT.atLeast(JavaVersion.JAVA_21));
        }

    @Test
        void testJavaVersionComparisonWithNull() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_17.atLeast(null));
        }
}
