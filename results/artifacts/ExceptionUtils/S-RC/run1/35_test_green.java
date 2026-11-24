package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    
    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable result = ExceptionUtils.getCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetCauseWithNullThrowable() {
        Throwable result = ExceptionUtils.getCause(null);
        assertNull(result);
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
        Throwable throwable = new Exception("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        String result = ExceptionUtils.getRootCauseMessage(throwable);
        assertEquals("RuntimeException: cause", result);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("cause")));
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] frames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(Arrays.stream(frames).anyMatch(s -> s.contains("RuntimeException: test")));
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(stackTrace.contains("RuntimeException: test"));
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        int count = ExceptionUtils.getThrowableCount(throwable);
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
        assertTrue(list.contains(throwable));
        assertTrue(list.contains(cause));
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, throwables.length);
        assertTrue(Arrays.asList(throwables).contains(throwable));
        assertTrue(Arrays.asList(throwables).contains(cause));
    }

    
    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        int index = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
        assertEquals(1, index);
    }

    @Test
    public void testIsChecked() {
        Throwable checked = new Exception("checked");
        assertTrue(ExceptionUtils.isChecked(checked));
    }

    @Test
    public void testIsUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertTrue(ExceptionUtils.isUnchecked(unchecked));
    }

    @Test
    public void testPrintRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        ExceptionUtils.printRootCauseStackTrace(throwable, System.out);
        // Output is not captured for verification in this simple test
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
        assertTrue(result.contains(throwable));
        assertTrue(result.contains(cause));
    }

    @Test
    public void testThrowUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(unchecked));
    }

    
    @Test
            public void testForEach() {
            List<Throwable> throwables = Arrays.asList(new NullPointerException(), new IllegalArgumentException());
            ExceptionUtils.forEach(throwables.get(0), t -> assertTrue(throwables.contains(t)));
        }

    @Test
            public void testGetMessageWithNullThrowable() {
                String message = ExceptionUtils.getMessage(null);
                assertEquals("", message);
            }

    @Test
            public void testGetRootCauseMessageWithNullThrowable() {
                String rootCauseMessage = ExceptionUtils.getRootCauseMessage(null);
                assertEquals("", rootCauseMessage);
            }

    @Test
            public void testGetStackFramesWithNullThrowable() {
                String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);
                assertTrue(Arrays.equals(new String[0], stackFrames));
            }

    @Test
            public void testGetStackTraceWithNullThrowable() {
                String stackTrace = ExceptionUtils.getStackTrace(null);
                assertEquals("", stackTrace);
            }

    @Test
            public void testGetThrowablesWithNullThrowable() {
                Throwable[] throwables = ExceptionUtils.getThrowables((Throwable) null);
                assertTrue(Arrays.equals(new Throwable[0], throwables));
            }

    
    @Test
            public void testIndexOfThrowableWithNullThrowable() {
                int index = ExceptionUtils.indexOfThrowable(null, NullPointerException.class);
                assertEquals(-1, index);
            }

    @Test
            public void testIndexOfTypeWithNullThrowable() {
                int index = ExceptionUtils.indexOfType(null, NullPointerException.class);
                assertEquals(-1, index);
            }

    @Test
            public void testPrintRootCauseStackTraceWithNullThrowable() {
                assertDoesNotThrow(() -> ExceptionUtils.printRootCauseStackTrace((Throwable) null));
            }
}
