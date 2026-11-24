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

    @Test
    public void testToStringMethod() {
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    }
