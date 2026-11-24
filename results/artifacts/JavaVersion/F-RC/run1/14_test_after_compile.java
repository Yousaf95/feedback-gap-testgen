package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

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
        public void testGetUnknownVersion() {
            assertNull(JavaVersion.get("25"));
        }

    @Test
        public void testGetRecentVersion() {
            JavaVersion version = JavaVersion.get("10.9");
            assertEquals(JavaVersion.JAVA_RECENT, version);
        }

    @Test
        public void testGetDecimalVersion() {
            JavaVersion version = JavaVersion.get("1.8.1");
            assertEquals(JavaVersion.JAVA_1_8, version);
        }

    @Test
        public void testGetRecentVersionWithDecimals() {
            JavaVersion version = JavaVersion.get("9.0.1");
            assertEquals(JavaVersion.JAVA_RECENT, version);
        }

    @Test
        public void testGetMaxVersion() {
            float max = JavaVersion.maxVersion();
            assertTrue(max >= 21);
        }

    @Test
        public void testToFloatVersionNoDecimal() {
            float result = JavaVersion.toFloatVersion("9");
            assertEquals(9.0f, result);
        }

    @Test
        public void testToFloatVersionWithDecimal() {
            float result = JavaVersion.toFloatVersion("1.8");
            assertEquals(1.8f, result);
        }
}
