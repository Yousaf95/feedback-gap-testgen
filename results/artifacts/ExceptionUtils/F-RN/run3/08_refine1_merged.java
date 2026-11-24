package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        List<String> defaultMethods = Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames());
        assertTrue(defaultMethods.contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String message = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", message);
    }

    
    
    
    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(stackFrames.length > 0);
        assertTrue(Arrays.stream(stackFrames).anyMatch(s -> s.contains("RuntimeException: test")));
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(stackTrace.contains("RuntimeException: test"));
    }

    
    
    
    
    
    
    @Test
    public void testIsUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertTrue(ExceptionUtils.isUnchecked(unchecked));
    }

    @Test
    public void testPrintRootCauseStackTrace() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Throwable rootCause = new RuntimeException("root cause");
        ExceptionUtils.printRootCauseStackTrace(rootCause, printStream);
        assertTrue(outputStream.toString().contains("RuntimeException: root cause"));
    }

    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetDefaultCauseMethodNames_2() {
            String[] expected = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
            assertArrayEquals(expected, ExceptionUtils.getDefaultCauseMethodNames());
        }

    @Test
        public void testGetMessage_2() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            assertEquals("IllegalArgumentException: Invalid argument", ExceptionUtils.getMessage(throwable));
        }

    @Test
        public void testGetStackFrames_2() {
            Throwable throwable = new RuntimeException("Runtime exception");
            String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(stackFrames.length > 0);
        }

    @Test
        public void testGetStackTrace_2() {
            Throwable throwable = new NullPointerException("Null pointer");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertTrue(!stackTrace.isEmpty());
        }

    @Test
        public void testIsUnchecked_2() {
            Throwable checkedException = new IOException("Checked exception");
            Throwable uncheckedException = new RuntimeException("Unchecked exception");
            assertFalse(ExceptionUtils.isUnchecked(checkedException));
            assertTrue(ExceptionUtils.isUnchecked(uncheckedException));
        }

    @Test
        public void testPrintRootCauseStackTrace_2() {
            Throwable throwable = new Exception("Root cause exception");
            assertThrows(SecurityException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, System.err));
        }
}
