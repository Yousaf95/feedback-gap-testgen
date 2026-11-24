package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class SystemUtils_GenTest {

    @Test
    void testGetEnvironmentVariable_withExistingVariable() {
        // Arrange
        String variableName = "PATH";
        String expectedValue = System.getenv(variableName);

        // Act
        String result = SystemUtils.getEnvironmentVariable(variableName, "default");

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testGetEnvironmentVariable_withNonExistingVariable() {
        // Arrange
        String variableName = "NON_EXISTING_VARIABLE";
        String defaultValue = "default";

        // Act
        String result = SystemUtils.getEnvironmentVariable(variableName, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }

    
    @Test
    void testGetHostName_onUnix() {
        // Arrange
        assumeTrue(SystemUtils.IS_OS_UNIX);
        String expectedValue = System.getenv("HOSTNAME");

        // Act
        String result = SystemUtils.getHostName();

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testGetJavaHome() {
        // Arrange
        File expectedFile = new File(System.getProperty("java.home"));

        // Act
        File result = SystemUtils.getJavaHome();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetJavaIoTmpDir() {
        // Arrange
        File expectedFile = new File(System.getProperty("java.io.tmpdir"));

        // Act
        File result = SystemUtils.getJavaIoTmpDir();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserDir() {
        // Arrange
        File expectedFile = new File(System.getProperty("user.dir"));

        // Act
        File result = SystemUtils.getUserDir();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserHome() {
        // Arrange
        File expectedFile = new File(System.getProperty("user.home"));

        // Act
        File result = SystemUtils.getUserHome();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserName_withDefault() {
        // Arrange
        String defaultValue = "default";
        String expectedValue = System.getProperty(SystemUtils.USER_NAME_KEY, defaultValue);

        // Act
        String result = SystemUtils.getUserName(defaultValue);

        // Assert
        assertEquals(expectedValue, result);
    }

    
    @Test
    void testIsJavaAwtHeadless_whenFalse() {
        // Arrange
        assumeFalse(Boolean.TRUE.toString().equals(System.getProperty("java.awt.headless")));

        // Act
        boolean result = SystemUtils.isJavaAwtHeadless();

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsJavaVersionAtLeast_withCurrentVersion() {
        // Arrange
        JavaVersion currentVersion = JavaVersion.get(SystemUtils.JAVA_SPECIFICATION_VERSION);

        // Act
        boolean result = SystemUtils.isJavaVersionAtLeast(currentVersion);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsJavaVersionAtMost_withCurrentVersion() {
        // Arrange
        JavaVersion currentVersion = JavaVersion.get(SystemUtils.JAVA_SPECIFICATION_VERSION);

        // Act
        boolean result = SystemUtils.isJavaVersionAtMost(currentVersion);

        // Assert
        assertTrue(result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetEnvironmentVariable_withDefault() {
            String result = SystemUtils.getEnvironmentVariable("NON_EXISTENT_VARIABLE", "default");
            assertEquals("default", result);
        }

    
    
    @Test
        void testGetHostName_onUnixLike() {
            assumeFalse(SystemUtils.IS_OS_WINDOWS);
            String result = SystemUtils.getHostName();
            assertNotNull(result); // Assuming the environment variable HOSTNAME is set
        }

    @Test
        void testIsJavaVersionAtLeast_withLowerVersion() {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8));
        }

    @Test
        void testIsJavaVersionAtMost_withHigherVersion() {
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_RECENT));
        }

    @Test
        void testGetUserDir_asFile() {
            File userDir = SystemUtils.getUserDir();
            assertNotNull(userDir);
            assertTrue(userDir.exists());
            assertTrue(userDir.isDirectory());
        }

    @Test
        void testGetUserHome_asFile() {
            File userHome = SystemUtils.getUserHome();
            assertNotNull(userHome);
            assertTrue(userHome.exists());
            assertTrue(userHome.isDirectory());
        }

    @Test
        void testGetJavaHome_asFile() {
            File javaHome = SystemUtils.getJavaHome();
            assertNotNull(javaHome);
            assertTrue(javaHome.exists());
            assertTrue(javaHome.isDirectory());
        }

    @Test
        void testGetJavaIoTmpDir_asFile() {
            File tmpDir = SystemUtils.getJavaIoTmpDir();
            assertNotNull(tmpDir);
            assertTrue(tmpDir.exists());
            assertTrue(tmpDir.isDirectory());
        }
}
