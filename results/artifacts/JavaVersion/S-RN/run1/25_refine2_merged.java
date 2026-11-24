package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaVersion_GenTest {

    
    
    
    @Test
    public void testToStringMethod() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("17", JavaVersion.JAVA_17.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAtLeastMethodWithHigherVersion() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_16));
        }

    @Test
        public void testAtLeastMethodWithEqualVersion() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtLeastMethodWithLowerVersion() {
            assertFalse(JavaVersion.JAVA_16.atLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtMostMethodWithLowerVersion() {
            assertTrue(JavaVersion.JAVA_16.atMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtMostMethodWithEqualVersion() {
            assertTrue(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testAtMostMethodWithHigherVersion() {
            assertFalse(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_16));
        }

    @Test
        public void testDeprecatedJavaVersion() {
            assertEquals("9", JavaVersion.JAVA_1_9.toString());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAtLeastMethodWithMaxVersion() {
            assertTrue(JavaVersion.JAVA_RECENT.atLeast(JavaVersion.JAVA_RECENT));
        }

    @Test
        public void testAtMostMethodWithMinVersion() {
            assertTrue(JavaVersion.JAVA_0_9.atMost(JavaVersion.JAVA_0_9));
        }

    @Test
        public void testToStringMethodForDeprecatedVersion() {
            assertEquals("1.9", JavaVersion.JAVA_1_9.toString());
        }

    @Test
        public void testAtLeastMethodWithNonExistentVersion() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_RECENT.atLeast(null));
        }

    @Test
        public void testAtMostMethodWithNonExistentVersion() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_RECENT.atMost(null));
        }
}
