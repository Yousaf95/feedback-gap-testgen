package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    
    @Test
    void testGetCause_withNullThrowable() {
        // Arrange

        // Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    
    @Test
    void testGetMessage_withNullThrowable() {
        // Arrange

        // Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCause_withNullThrowable() {
        // Arrange

        // Act
        Throwable rootCause = ExceptionUtils.getRootCause(null);

        // Assert
        assertNull(rootCause);
    }

    @Test
    void testGetRootCauseMessage_withNullThrowable() {
        // Arrange

        // Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange

        // Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertArrayEquals(new String[0], stackTrace);
    }

    @Test
    void testGetStackFrames_withNullThrowable() {
        // Arrange

        // Act
        String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);

        // Assert
        assertArrayEquals(new String[0], stackFrames);
    }

    @Test
    void testGetStackTrace_withNullThrowable() {
        // Arrange

        // Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    void testGetThrowableCount_withNullThrowable() {
        // Arrange

        // Act
        int count = ExceptionUtils.getThrowableCount(null);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void testGetThrowableList_withNullThrowable() {
        // Arrange

        // Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void testHasCause_withNullChain() {
        // Arrange

        // Act
        boolean hasCause = ExceptionUtils.hasCause(null, Exception.class);

        // Assert
        assertFalse(hasCause);
    }

    @Test
    void testIndexOfThrowable_withNullThrowable() {
        // Arrange

        // Act
        int index = ExceptionUtils.indexOfThrowable(null, Exception.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIndexOfType_withNullThrowable() {
        // Arrange

        // Act
        int index = ExceptionUtils.indexOfType(null, Exception.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIsChecked_withNullThrowable() {
        // Arrange

        // Act
        boolean isChecked = ExceptionUtils.isChecked(null);

        // Assert
        assertFalse(isChecked);
    }

    @Test
    void testIsUnchecked_withNullThrowable() {
        // Arrange

        // Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(null);

        // Assert
        assertFalse(isUnchecked);
    }

    
    @Test
    void testRemoveCommonFrames_withNullLists() {
        // Arrange

        // Act & Assert
        assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(null, List.of()));
        assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(List.of(), null));
    }

    
    @Test
    void testThrowableOfThrowable_withNullThrowable() {
        // Arrange

        // Act
        Throwable throwable = ExceptionUtils.throwableOfThrowable(null, Exception.class);

        // Assert
        assertNull(throwable);
    }

    
    
    }
