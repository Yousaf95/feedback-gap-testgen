package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    @Test
    public void testJavaVersionEnumValues() {
        assertEquals(1.5f, JavaVersion.JAVA_0_9.value);
        assertEquals("0.9", JavaVersion.JAVA_0_9.name);

        assertEquals(1.6f, JavaVersion.JAVA_1_6.value);
        assertEquals("1.6", JavaVersion.JAVA_1_6.name);

        assertEquals(21, JavaVersion.JAVA_21.value);
        assertEquals("21", JavaVersion.JAVA_21.name);

        assertTrue(JavaVersion.JAVA_RECENT.value > 0);
        assertTrue(JavaVersion.JAVA_RECENT.name.length() > 0);
    }

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
        assertEquals("21", JavaVersion.JAVA_21.toString());
        assertTrue(JavaVersion.JAVA_RECENT.toString().length() > 0);
    }
}
