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

    @Test
    public void testAsRuntimeException() {
        Throwable throwable = new RuntimeException("test");
        RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
        assertEquals(throwable, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        List<String> methodNames = Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames());
        assertTrue(methodNames.contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String message = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", message);
    }

    @Test
    public void testGetRootCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        Throwable result = ExceptionUtils.getRootCause(rootCause);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        String message = ExceptionUtils.getRootCauseMessage(rootCause);
        assertEquals("RuntimeException: cause", message);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(rootCause);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(stackFrames.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!stackTrace.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        int count = ExceptionUtils.getThrowableCount(rootCause);
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        List<Throwable> throwableList = ExceptionUtils.getThrowableList(rootCause);
        assertEquals(2, throwableList.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        Throwable[] throwables = ExceptionUtils.getThrowables(rootCause);
        assertEquals(2, throwables.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        boolean result = ExceptionUtils.hasCause(rootCause, RuntimeException.class);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testIndexOfThrowable(int fromIndex) {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        int index = ExceptionUtils.indexOfThrowable(rootCause, RuntimeException.class, fromIndex);
        assertEquals(0, index);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void testIndexOfType(int fromIndex) {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        int index = ExceptionUtils.indexOfType(rootCause, RuntimeException.class, fromIndex);
        assertEquals(0, index);
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

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testPrintRootCauseStackTrace(String methodName) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        ExceptionUtils.printRootCauseStackTrace(rootCause, printStream);
        assertTrue(!outputStream.toString().isEmpty());
    }

    @Test
    public void testRemoveCommonFrames() {
        List<String> causeFrames = Arrays.asList("frame1", "frame2");
        List<String> wrapperFrames = Arrays.asList("frame2", "frame3");
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
        assertEquals(Arrays.asList("frame1"), causeFrames);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testStream(String methodName) {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        ExceptionUtils.stream(rootCause).forEach(t -> assertTrue(t instanceof RuntimeException));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testThrowUnchecked(String methodName) {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(throwable));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testWrapAndThrow(String methodName) {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.wrapAndThrow(throwable));
    }
}
