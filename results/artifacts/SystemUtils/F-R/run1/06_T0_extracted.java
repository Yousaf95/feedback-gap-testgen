package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

import java.io.File;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("SystemUtils Tests")
public class SystemUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // Reset system properties to their default values before each test
        Properties defaultProps = System.getProperties();
        defaultProps.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
    }

    @Test
    @DisplayName("Should return the correct file encoding")
    public void testGetFileEncoding() {
        String expectedEncoding = System.getProperty("file.encoding");
        assertEquals(expectedEncoding, SystemUtils.FILE_ENCODING);
    }

    @ParameterizedTest(name = "Should return the correct Java version for {0}")
    @EnumSource(JavaVersion.class)
    public void testIsJavaVersionAtLeast(JavaVersion requiredVersion) {
        boolean result = SystemUtils.isJavaVersionAtLeast(requiredVersion);
        assertTrue(result, () -> "Java version should be at least " + requiredVersion);
    }

    @ParameterizedTest(name = "Should return the correct Java version for {0}")
    @EnumSource(value = JavaVersion.class, mode = EXCLUDE)
    public void testIsJavaVersionAtLeast_NotMatch(JavaVersion requiredVersion) {
        boolean result = SystemUtils.isJavaVersionAtLeast(requiredVersion);
        assertFalse(result, () -> "Java version should not be at least " + requiredVersion);
    }

    @Test
    @DisplayName("Should return true if Java is version 17")
    public void testIsJava17() {
        assertTrue(SystemUtils.IS_JAVA_1_7);
    }

    @ParameterizedTest(name = "Should return the correct OS name for {0}")
    @MethodSource("osNameProvider")
    public void testGetOsMatchesName(String osName, boolean expected) {
        boolean result = SystemUtils.getOsMatchesName(osName);
        assertEquals(expected, result, () -> "OS name should match " + osName);
    }

    private static Stream<Arguments> osNameProvider() {
        return Stream.of(
            arguments("Windows", true),
            arguments("Linux", true),
            arguments("Mac OS X", true),
            arguments("AIX", true),
            arguments("HP-UX", true),
            arguments("OS/400", true),
            arguments("Irix", true),
            arguments("FreeBSD", true),
            arguments("OpenBSD", true),
            arguments("NetBSD", true),
            arguments("OS/2", true),
            arguments("Solaris", true),
            arguments("SunOS", true),
            arguments("z/OS", true),
            arguments("unknown", false)
        );
    }

    @ParameterizedTest(name = "Should return the correct OS matches for {0}")
    @MethodSource("osMatchesProvider")
    public void testGetOsMatches(String osName, String version, boolean expected) {
        boolean result = SystemUtils.getOsMatches(osName, version);
        assertEquals(expected, result, () -> "OS should match " + osName + " with version " + version);
    }

    private static Stream<Arguments> osMatchesProvider() {
        return Stream.of(
            arguments("Mac OS X", "10.15", true),
            arguments("Windows 10", "", false),
            arguments("unknown", "unknown", false)
        );
    }

    @Test
    @DisplayName("Should return the correct user home directory")
    public void testGetUserHome() {
        File expectedDir = new File(System.getProperty("user.home"));
        assertEquals(expectedDir, SystemUtils.getUserHome());
    }

    @ParameterizedTest(name = "Should return the correct environment variable {0}")
    @MethodSource("environmentVariableProvider")
    public void testGetEnvironmentVariable(String name, String defaultValue, String expectedValue) {
        String result = SystemUtils.getEnvironmentVariable(name, defaultValue);
        assertEquals(expectedValue, result, () -> "Environment variable should be " + expectedValue);
    }

    private static Stream<Arguments> environmentVariableProvider() {
        return Stream.of(
            arguments("JAVA_HOME", "/usr/lib/jvm/java-17-openjdk-amd64", "/usr/lib/jvm/java-17-openjdk-amd64"),
            arguments("UNKNOWN_VAR", "default", "default")
        );
    }

    @Test
    @DisplayName("Should return the correct host name")
    public void testGetHostName() {
        String expectedHost = System.getenv("COMPUTERNAME") != null ? System.getenv("COMPUTERNAME") : System.getenv("HOSTNAME");
        assertEquals(expectedHost, SystemUtils.getHostName());
    }

    @Test
    @DisplayName("Should return the correct Java home directory as a File")
    public void testGetJavaHome() {
        File expectedDir = new File(System.getProperty("java.home"));
        assertEquals(expectedDir, SystemUtils.getJavaHome());
    }

    @Test
    @DisplayName("Should return the correct Java IO temporary directory as a File")
    public void testGetJavaIoTmpDir() {
        File expectedDir = new File(System.getProperty("java.io.tmpdir"));
        assertEquals(expectedDir, SystemUtils.getJavaIoTmpDir());
    }

    @Test
    @DisplayName("Should return the correct user directory as a File")
    public void testGetUserDir() {
        File expectedDir = new File(System.getProperty("user.dir"));
        assertEquals(expectedDir, SystemUtils.getUserDir());
    }
}
