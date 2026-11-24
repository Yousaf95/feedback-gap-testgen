package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.stream.Stream;

public class ExceptionUtils_GenTest {

    
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
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        assertEquals(1, ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class));
    }

    
    
    @Test
    public void testPrintRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        assertDoesNotThrow(() -> ExceptionUtils.printRootCauseStackTrace(throwable));
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

    
    @Test
        public void testGetCause_NullThrowable() {
            Throwable cause = ExceptionUtils.getCause(null);
            assertNull(cause);
        }

    
    @Test
        public void testGetMessage_NullThrowable() {
            String message = ExceptionUtils.getMessage(null);
            assertEquals("", message);
        }

    @Test
        public void testGetRootCause_NullThrowable() {
            Throwable rootCause = ExceptionUtils.getRootCause(null);
            assertNull(rootCause);
        }

    @Test
        public void testGetRootCauseMessage_NullThrowable() {
            String message = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", message);
        }

    
    @Test
        public void testGetStackTrace_NullThrowable() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals("", stackTrace);
        }

    @Test
        public void testIndexOfThrowable_ClassFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
            assertEquals(0, index);
        }

    @Test
        public void testIndexOfType_TypeFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfType(throwable, RuntimeException.class);
            assertEquals(0, index);
        }

    @Test
        public void testIsChecked_CheckedException() {
            boolean result = ExceptionUtils.isChecked(new IOException());
            assertTrue(result);
        }

    @Test
        public void testStream_NullThrowable() {
            Stream<Throwable> stream = ExceptionUtils.stream(null);
            assertFalse(stream.findAny().isPresent());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetDefaultCauseMethodNames_2() {
            String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();
            assertNotNull(methodNames);
            assertTrue(Arrays.asList(methodNames).containsAll(Arrays.asList(ExceptionUtils.CAUSE_METHOD_NAMES)));
        }

    @Test
        public void testGetStackFrames_NullThrowable() {
            String[] frames = ExceptionUtils.getStackFrames(null);
            assertNotNull(frames);
            assertTrue(Arrays.equals(new String[0], frames));
        }

    @Test
        public void testGetThrowables_NullThrowable() {
            Throwable[] throwables = ExceptionUtils.getThrowables(null);
            assertNotNull(throwables);
            assertTrue(Arrays.equals(new Throwable[0], throwables));
        }

    @Test
        public void testPrintRootCauseStackTrace_NullWriter() {
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(new RuntimeException(), null));
        }
}
