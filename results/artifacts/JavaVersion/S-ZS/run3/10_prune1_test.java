package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    
    @Test
    public void testAtLeastMethod() {
        assertTrue(JavaVersion.JAVA_17.atLeast(JavaVersion.JAVA_15));
        assertFalse(JavaVersion.JAVA_15.atLeast(JavaVersion.JAVA_17));
        assertTrue(JavaVersion.JAVA_RECENT.atLeast(JavaVersion.JAVA_20));
    }

    @Test
    public void testAtMostMethod() {
        assertTrue(JavaVersion.JAVA_15.atMost(JavaVersion.JAVA_17));
        assertFalse(JavaVersion.JAVA_17.atMost(JavaVersion.JAVA_15));
        assertTrue(JavaVersion.JAVA_20.atMost(JavaVersion.JAVA_RECENT));
    }

    @Test
    public void testToStringMethod() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("17", JavaVersion.JAVA_17.toString());
        // Add more assertions for other enum values
    }
}
