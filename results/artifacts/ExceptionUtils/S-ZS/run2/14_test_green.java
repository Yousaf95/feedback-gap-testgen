package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    
    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetCauseWithNullThrowable() {
        Throwable result = ExceptionUtils.getCause(null);
        assertNull(result);
    }

    @Test
    public void testGetCauseWithNullMethodNames() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getCause(throwable, null);
        assertEquals(cause, result);
    }

    
    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String result = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", result);
    }

    @Test
    public void testGetRootCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        String result = ExceptionUtils.getRootCauseMessage(throwable);
        assertEquals("RuntimeException: cause", result);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] result = ExceptionUtils.getStackFrames(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String result = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.getThrowableCount(throwable);
        assertEquals(2, result);
    }

    
    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable[] result = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, result.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
        assertTrue(result);
    }

    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
        assertEquals(0, result);
    }

    @Test
    public void testIsChecked() {
        Throwable throwable = new RuntimeException("test");
        boolean result = ExceptionUtils.isChecked(throwable);
        assertFalse(result);
    }

    @Test
    public void testIsUnchecked() {
        Throwable throwable = new RuntimeException("test");
        boolean result = ExceptionUtils.isUnchecked(throwable);
        assertTrue(result);
    }

    @Test
    public void testPrintRootCauseStackTrace() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        ExceptionUtils.printRootCauseStackTrace(throwable, ps);
        String result = baos.toString();
        assertTrue(result.contains("cause"));
    }

    
    
    
    
    
    
    }
