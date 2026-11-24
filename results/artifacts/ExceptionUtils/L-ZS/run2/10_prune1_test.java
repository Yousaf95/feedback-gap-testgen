package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    @Test
    void testAsRuntimeException_withCheckedException() {
        // Arrange
        Throwable checkedException = new Exception("Checked exception");

        // Act & Assert
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(checkedException));
        assertEquals(checkedException.getMessage(), runtimeException.getCause().getMessage());
    }

    
    
    @Test
    void testGetCause_withNullThrowable() {
        // Arrange & Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    @Test
    void testGetCause_withNoCause() {
        // Arrange
        Throwable throwable = new Exception("No cause");

        // Act
        Throwable cause = ExceptionUtils.getCause(throwable);

        // Assert
        assertNull(cause);
    }

    
    
    @Test
    void testGetMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetMessage_withEmptyMessage() {
        // Arrange
        Throwable throwable = new Exception();

        // Act
        String message = ExceptionUtils.getMessage(throwable);

        // Assert
        assertEquals("Exception: ", message);
    }

    @Test
    void testGetRootCause_withNullThrowable() {
        // Arrange & Act
        Throwable rootCause = ExceptionUtils.getRootCause(null);

        // Assert
        assertNull(rootCause);
    }

    @Test
    void testGetRootCause_withSingleThrowable() {
        // Arrange
        Throwable throwable = new Exception("Single");

        // Act
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);

        // Assert
        assertSame(throwable, rootCause);
    }

    
    @Test
    void testGetRootCauseMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertNotNull(stackTrace);
        assertEquals(0, stackTrace.length);
    }

    @Test
    void testGetStackFrames_withNullThrowable() {
        // Arrange & Act
        String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);

        // Assert
        assertNotNull(stackFrames);
        assertEquals(0, stackFrames.length);
    }

    @Test
    void testGetStackTrace_withNullThrowable() {
        // Arrange & Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    void testGetThrowableCount_withNullThrowable() {
        // Arrange & Act
        int count = ExceptionUtils.getThrowableCount(null);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void testGetThrowableList_withNullThrowable() {
        // Arrange & Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    
    
    
    @Test
    void testIsChecked_withRuntimeException() {
        // Arrange & Act
        boolean isChecked = ExceptionUtils.isChecked(new RuntimeException());

        // Assert
        assertFalse(isChecked);
    }

    @Test
    void testIsUnchecked_withError() {
        // Arrange & Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(new Error());

        // Assert
        assertTrue(isUnchecked);
    }

    @Test
    void testPrintRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act & Assert
        // No assertion needed as printRootCauseStackTrace should not throw and no output is expected on null
        ExceptionUtils.printRootCauseStackTrace(null);
    }

    
    
    
    @Test
    void testThrowUnchecked_withRuntimeException() {
        // Arrange
        RuntimeException runtimeException = new RuntimeException("Runtime exception");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
    }

    
    }
