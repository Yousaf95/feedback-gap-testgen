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
            String nonExistingVar = "NON_EXISTING_ENV_VAR";
            String defaultValue = "default_value";
            assertEquals(defaultValue, SystemUtils.getEnvironmentVariable(nonExistingVar, defaultValue));
        }

    @Test
        void testIsJavaAwtHeadless_whenTrue() {
            System.setProperty(SystemUtils.JAVA_AWT_HEADLESS, "true");
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }

    @Test
        void testGetHostName_onWindows() {
            assumeTrue(SystemUtils.IS_OS_WINDOWS);
            assertNotNull(SystemUtils.getHostName(), "HOSTNAME should not be null on Windows");
        }

    @Test
        void testGetHostName_onNonWindows() {
            assumeFalse(SystemUtils.IS_OS_WINDOWS);
            assertNotNull(SystemUtils.getHostName(), "COMPUTERNAME should not be null on non-Windows systems");
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
        void testGetUserName_withCustomDefault() {
            String customDefault = "custom_user";
            assertEquals(customDefault, SystemUtils.getUserName(customDefault));
        }
}
