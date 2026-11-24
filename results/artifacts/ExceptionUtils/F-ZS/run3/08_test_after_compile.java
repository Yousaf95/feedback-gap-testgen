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
        assertEquals(RuntimeException.class, result.getClass());
        assertEquals("test", result.getMessage());
    }

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
    public void testGetRootCause() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        Throwable result = ExceptionUtils.getRootCause(wrapper);
        assertSame(rootCause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        String message = ExceptionUtils.getRootCauseMessage(wrapper);
        assertEquals("RuntimeException: root cause", message);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(wrapper);
        assertTrue(stackTrace.length > 0);
        assertTrue(Arrays.stream(stackTrace).anyMatch(s -> s.contains("InvocationTargetException")));
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
    public void testGetThrowableCount() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        int count = ExceptionUtils.getThrowableCount(wrapper);
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        List<Throwable> list = ExceptionUtils.getThrowableList(wrapper);
        assertEquals(2, list.size());
        assertSame(rootCause, list.get(0));
    }

    @Test
    public void testGetThrowables() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        Throwable[] throwables = ExceptionUtils.getThrowables(wrapper);
        assertEquals(2, throwables.length);
        assertSame(rootCause, throwables[0]);
    }

    @Test
    public void testHasCause() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        boolean result = ExceptionUtils.hasCause(wrapper, RuntimeException.class);
        assertTrue(result);
    }

    @Test
    public void testIndexOfThrowable() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        int index = ExceptionUtils.indexOfThrowable(wrapper, RuntimeException.class);
        assertEquals(0, index);
    }

    @Test
    public void testIsChecked() {
        Throwable checked = new IOException("checked");
        assertFalse(ExceptionUtils.isChecked(checked));
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

    @Test
    public void testRemoveCommonFrames() {
        List<String> causeFrames = Arrays.asList(
            "com.example.CauseClass.method1",
            "com.example.CauseClass.method2"
        );
        List<String> wrapperFrames = Arrays.asList(
            "com.example.WrapperClass.method1",
            "com.example.CauseClass.method1",
            "com.example.CauseClass.method2"
        );
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
        assertEquals(0, causeFrames.size());
    }

    @Test
    public void testStream() {
        Throwable rootCause = new RuntimeException("root cause");
        Throwable wrapper = new InvocationTargetException(rootCause, "wrapper");
        List<Throwable> list = ExceptionUtils.stream(wrapper).toList();
        assertEquals(2, list.size());
        assertSame(rootCause, list.get(0));
    }

    @Test
    public void testThrowUnchecked() {
        RuntimeException unchecked = new RuntimeException("unchecked");
        Throwable result = ExceptionUtils.throwUnchecked(unchecked);
        assertSame(result, unchecked);
    }

    @Test
    public void testWrapAndThrow() {
        RuntimeException unchecked = new RuntimeException("unchecked");
        Throwable result = ExceptionUtils.wrapAndThrow(unchecked);
        assertTrue(result instanceof UndeclaredThrowableException);
        assertTrue(result.getCause() instanceof RuntimeException);
        assertEquals("unchecked", result.getCause().getMessage());
    }
}
