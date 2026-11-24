package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        assertTrue(Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames()).contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable ex = new RuntimeException("test");
        assertEquals("RuntimeException: test", ExceptionUtils.getMessage(ex));
    }

    @Test
    public void testGetRootCause() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertSame(root, ExceptionUtils.getRootCause(cause));
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertEquals("RuntimeException: root", ExceptionUtils.getRootCauseMessage(cause));
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable ex = new RuntimeException("test");
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(ex);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable ex = new RuntimeException("test");
        String[] frames = ExceptionUtils.getStackFrames(ex);
        assertTrue(frames.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable ex = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(ex);
        assertTrue(stackTrace.contains("test"));
    }

    @Test
    public void testGetThrowableCount() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertEquals(2, ExceptionUtils.getThrowableCount(cause));
    }

    @Test
    public void testGetThrowableList() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        List<Throwable> list = ExceptionUtils.getThrowableList(cause);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        Throwable[] throwables = ExceptionUtils.getThrowables(cause);
        assertEquals(2, throwables.length);
    }

    @Test
    public void testHasCause() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertTrue(ExceptionUtils.hasCause(cause, RuntimeException.class));
    }

    
    
    
    
    @Test
    public void testPrintRootCauseStackTrace() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        ExceptionUtils.printRootCauseStackTrace(new RuntimeException("test"), printStream);
        assertTrue(out.toString().contains("test"));
    }

    
    
    
    
    
    }
