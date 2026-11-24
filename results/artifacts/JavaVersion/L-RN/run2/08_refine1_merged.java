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
        void testJAVA_RECENTIsLatestVersion() {
            assertEquals(JavaVersion.values()[JavaVersion.values().length - 2], JavaVersion.JAVA_RECENT);
        }

    @Test
        void testValueReturnsCorrectFloat() {
            assertEquals(1.8f, JavaVersion.JAVA_1_8.value);
        }

    @Test
        void testNameReturnsCorrectString() {
            assertEquals("10", JavaVersion.JAVA_10.name);
        }

    @Test
        void testAtLeastWithNullThrowsNullPointerException() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_17.atLeast(null));
        }

    @Test
        void testAtMostWithNullThrowsNullPointerException() {
            assertThrows(NullPointerException.class, () -> JavaVersion.JAVA_17.atMost(null));
        }
}
