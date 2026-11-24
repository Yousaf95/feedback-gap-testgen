package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.StringWriter;
import java.io.PrintWriter;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    
    @Test
    public void testGetCause_withNullThrowable() {
        // Arrange & Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    
    @Test
    public void testGetMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    public void testGetRootCause_withNestedExceptions() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.getRootCause(nested);

        // Assert
        assertEquals(rootCause, result);
    }

    @Test
    public void testGetRootCauseMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    public void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertArrayEquals(new String[0], stackTrace);
    }

    
    @Test
    public void testGetStackTrace_withNullThrowable() {
        // Arrange & Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    public void testGetThrowableCount_withNestedExceptions() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int count = ExceptionUtils.getThrowableCount(nested);

        // Assert
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList_withNullThrowable() {
        // Arrange & Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertTrue(list.isEmpty());
    }

    
    @Test
    public void testIndexOfThrowable_withExactMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int index = ExceptionUtils.indexOfThrowable(nested, RuntimeException.class);

        // Assert
        assertEquals(1, index);
    }

    @Test
    public void testIndexOfType_withSubclassMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int index = ExceptionUtils.indexOfType(nested, Exception.class);

        // Assert
        assertEquals(0, index);
    }

    @Test
    public void testIsChecked_withRuntimeException() {
        // Arrange & Act
        boolean isChecked = ExceptionUtils.isChecked(new RuntimeException());

        // Assert
        assertFalse(isChecked);
    }

    @Test
    public void testIsUnchecked_withError() {
        // Arrange & Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(new Error());

        // Assert
        assertTrue(isUnchecked);
    }

    @Test
    public void testPrintRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act (no exception thrown)
        ExceptionUtils.printRootCauseStackTrace(null);
    }

    
    
    @Test
    public void testThrowableOfThrowable_withExactMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.throwableOfThrowable(nested, RuntimeException.class);

        // Assert
        assertEquals(rootCause, result);
    }

    @Test
    public void testThrowableOfType_withSubclassMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.throwableOfType(nested, Exception.class);

        // Assert
        assertEquals(nested, result);
    }

    @Test
    public void testThrowUnchecked_withRuntimeException() {
        // Arrange
        RuntimeException runtimeException = new RuntimeException("Runtime Exception");

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
        assertEquals("Runtime Exception", thrown.getMessage());
    }

    
    }
