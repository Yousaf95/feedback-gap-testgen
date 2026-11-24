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
        public void testAtLeast() {
            assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_15));
            assertFalse(JavaVersion.JAVA_15.atLeast(JavaVersion.JAVA_17));
            assertTrue(JavaVersion.JAVA_RECENT.atLeast(JavaVersion.JAVA_20));
        }

    @Test
        public void testAtMost() {
            assertTrue(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_17));
            assertFalse(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_15));
            assertTrue(JavaVersion.JAVA_20.atMost(JavaVersion.JAVA_RECENT));
        }

    @Test
        public void testMaxVersion() {
            assertEquals(21.0f, JavaVersion.maxVersion());
        }

    @Test
        public void testDeprecatedJavaVersion() {
            assertThrows(IllegalArgumentException.class, () -> JavaVersion.valueOf("JAVA_1_9"));
        }
}
