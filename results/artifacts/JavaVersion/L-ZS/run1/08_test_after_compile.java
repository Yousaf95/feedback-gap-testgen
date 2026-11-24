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

    @Test
    void testJAVA_RECENTIsLatest() {
        assertEquals(JavaVersion.values()[JavaVersion.values().length - 2], JavaVersion.JAVA_RECENT);
    }
}
