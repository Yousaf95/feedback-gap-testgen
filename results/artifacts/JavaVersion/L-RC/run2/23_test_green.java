package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    @Test
    void testAtLeastWithOlderVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion olderVersion = JavaVersion.JAVA_1_8;

        // Act & Assert
        assertTrue(version.atLeast(olderVersion));
    }

    @Test
    void testAtLeastWithSameVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion sameVersion = JavaVersion.JAVA_11;

        // Act & Assert
        assertTrue(version.atLeast(sameVersion));
    }

    @Test
    void testAtLeastWithNewerVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion newerVersion = JavaVersion.JAVA_17;

        // Act & Assert
        assertFalse(version.atLeast(newerVersion));
    }

    @Test
    void testAtMostWithOlderVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion olderVersion = JavaVersion.JAVA_1_8;

        // Act & Assert
        assertFalse(version.atMost(olderVersion));
    }

    @Test
    void testAtMostWithSameVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion sameVersion = JavaVersion.JAVA_11;

        // Act & Assert
        assertTrue(version.atMost(sameVersion));
    }

    @Test
    void testAtMostWithNewerVersion() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;
        JavaVersion newerVersion = JavaVersion.JAVA_17;

        // Act & Assert
        assertTrue(version.atMost(newerVersion));
    }

    @Test
    void testToStringReturnsCorrectName() {
        // Arrange
        JavaVersion version = JavaVersion.JAVA_11;

        // Act & Assert
        assertEquals("11", version.toString());
    }

    @Test
    void testJAVA_RECENTIsNotNull() {
        // Arrange & Act & Assert
        assertNotNull(JavaVersion.JAVA_RECENT);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetWithNullString() {
            assertNull(JavaVersion.get(null));
        }

    
    @Test
        void testGetWithVersion0_9() {
            assertEquals(JavaVersion.JAVA_0_9, JavaVersion.get("0.9"));
        }

    @Test
        void testGetWithVersion1_1() {
            assertEquals(JavaVersion.JAVA_1_1, JavaVersion.get("1.1"));
        }

    @Test
        void testGetWithVersion1_2() {
            assertEquals(JavaVersion.JAVA_1_2, JavaVersion.get("1.2"));
        }

    @Test
        void testGetWithVersion1_3() {
            assertEquals(JavaVersion.JAVA_1_3, JavaVersion.get("1.3"));
        }

    @Test
        void testGetWithVersion1_4() {
            assertEquals(JavaVersion.JAVA_1_4, JavaVersion.get("1.4"));
        }

    @Test
        void testGetWithVersion1_5() {
            assertEquals(JavaVersion.JAVA_1_5, JavaVersion.get("1.5"));
        }

    @Test
        void testGetWithVersion1_6() {
            assertEquals(JavaVersion.JAVA_1_6, JavaVersion.get("1.6"));
        }

    @Test
        void testGetWithVersion1_7() {
            assertEquals(JavaVersion.JAVA_1_7, JavaVersion.get("1.7"));
        }

    @Test
        void testGetWithVersion1_8() {
            assertEquals(JavaVersion.JAVA_1_8, JavaVersion.get("1.8"));
        }

    @Test
        void testGetWithVersion9() {
            assertEquals(JavaVersion.JAVA_9, JavaVersion.get("9"));
        }

    @Test
        void testGetWithVersion10() {
            assertEquals(JavaVersion.JAVA_10, JavaVersion.get("10"));
        }

    @Test
        void testGetWithVersion11() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.get("11"));
        }

    @Test
        void testGetWithVersion12() {
            assertEquals(JavaVersion.JAVA_12, JavaVersion.get("12"));
        }

    @Test
        void testGetWithVersion13() {
            assertEquals(JavaVersion.JAVA_13, JavaVersion.get("13"));
        }

    @Test
        void testGetWithVersion14() {
            assertEquals(JavaVersion.JAVA_14, JavaVersion.get("14"));
        }

    @Test
        void testGetWithVersion15() {
            assertEquals(JavaVersion.JAVA_15, JavaVersion.get("15"));
        }

    @Test
        void testGetWithVersion16() {
            assertEquals(JavaVersion.JAVA_16, JavaVersion.get("16"));
        }

    @Test
        void testGetWithVersion17() {
            assertEquals(JavaVersion.JAVA_17, JavaVersion.get("17"));
        }

    @Test
        void testGetWithVersion18() {
            assertEquals(JavaVersion.JAVA_18, JavaVersion.get("18"));
        }

    @Test
        void testGetWithVersion19() {
            assertEquals(JavaVersion.JAVA_19, JavaVersion.get("19"));
        }

    @Test
        void testGetWithVersion20() {
            assertEquals(JavaVersion.JAVA_20, JavaVersion.get("20"));
        }

    @Test
        void testGetWithVersion21() {
            assertEquals(JavaVersion.JAVA_21, JavaVersion.get("21"));
        }

    @Test
        void testGetWithDecimalGreaterThan9() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("1.95"));
        }

    @Test
        void testGetWithVersionGreaterThan10() {
            assertEquals(JavaVersion.JAVA_RECENT, JavaVersion.get("11.0"));
        }

    @Test
        void testGetJavaVersionWithNullString() {
            assertNull(JavaVersion.getJavaVersion(null));
        }
}
