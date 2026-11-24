package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class JavaVersion_GenTest {

    @ParameterizedTest
    @EnumSource(JavaVersion.class)
    public void testAtLeast(JavaVersion version) {
        assertTrue(version.atLeast(JavaVersion.JAVA_0_9));
        assertTrue(version.atLeast(version));
        assertFalse(version.atLeast(JavaVersion.JAVA_RECENT));
    }

    @ParameterizedTest
    @EnumSource(JavaVersion.class)
    public void testAtMost(JavaVersion version) {
        assertTrue(version.atMost(JavaVersion.JAVA_RECENT));
        assertTrue(version.atMost(version));
        assertFalse(version.atMost(JavaVersion.JAVA_0_9));
    }

    @Test
    public void testToString() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    
    }
