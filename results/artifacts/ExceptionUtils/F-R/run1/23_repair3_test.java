package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    @Test
    public void testAsRuntimeException() {
        Throwable throwable = new RuntimeException("test");
        RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
        assertEquals(RuntimeException.class, result.getClass());
        assertEquals("test", result.getMessage());
    }

    @Test
    public void testForEach() {
        List<Throwable> throwables = Arrays.asList(new NullPointerException(), new IllegalArgumentException());
        int[] count = {0};
        ExceptionUtils.forEach(throwables.get(0), t -> count[0]++);
        assertEquals(1, count[0]);
    }

    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable result = ExceptionUtils.getCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetDefaultCauseMethodNames() {
        String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();
        assertTrue(Arrays.asList(methodNames).containsAll(Arrays.asList("getCause")));
    }

    @Test
    public void testGetMessage() {
        Throwable throwable = new NullPointerException("test");
        assertEquals("NullPointerException: test", ExceptionUtils.getMessage(throwable));
    }

    @Test
    public void testGetRootCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new NullPointerException("cause");
        Throwable throwable = new Exception("test", cause);
        assertEquals("NullPointerException: cause", ExceptionUtils.getRootCauseMessage(throwable));
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new NullPointerException("test");
        String[] result = ExceptionUtils.getStackFrames(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new NullPointerException("test");
        String result = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        assertEquals(2, ExceptionUtils.getThrowableCount(throwable));
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        List<Throwable> result = ExceptionUtils.getThrowableList(throwable);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable[] result = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, result.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new NullPointerException("cause");
        Throwable throwable = new Exception("test", cause);
        assertTrue(ExceptionUtils.hasCause(throwable, NullPointerException.class));
    }

    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        assertEquals(1, ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class));
    }

    @Test
    public void testIsChecked() {
        assertTrue(ExceptionUtils.isChecked(new NullPointerException()));
        assertFalse(ExceptionUtils.isChecked(new RuntimeException()));
    }

    @Test
    public void testIsUnchecked() {
        assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        assertFalse(ExceptionUtils.isUnchecked(new NullPointerException()));
    }

    @Test
    public void testPrintRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        assertDoesNotThrow(() -> ExceptionUtils.printRootCauseStackTrace(throwable));
    }

    @Test
    public void testRemoveCommonFrames() {
        List<String> causeFrames = Arrays.asList("frame1", "frame2", "frame3");
        List<String> wrapperFrames = Arrays.asList("frame2", "frame3", "frame4");
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
        assertEquals(Arrays.asList("frame1"), causeFrames);
    }

    @Test
    public void testRethrow() {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(throwable));
    }

    @Test
    public void testStream() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        List<Throwable> result = ExceptionUtils.stream(throwable).toList();
        assertEquals(2, result.size());
    }

    @Test
    public void testThrowableOfThrowable() {
        Throwable cause = new NullPointerException("cause");
        Throwable throwable = new Exception("test", cause);
        assertNotNull(ExceptionUtils.throwableOfThrowable(throwable, NullPointerException.class));
    }

    @Test
    public void testThrowUnchecked() {
        RuntimeException runtimeException = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
    }
}
