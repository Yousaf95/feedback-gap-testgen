package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testGetCause_NullThrowable_2() {
            assertNull(ExceptionUtils.getCause(null));
        }

    @Test
        public void testGetCause_NoCause() {
            Throwable throwable = new RuntimeException("test");
            assertNull(ExceptionUtils.getCause(throwable));
        }

    @Test
        public void testGetCause_WithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(IllegalArgumentException.class, ExceptionUtils.getCause(throwable).getClass());
            assertEquals("cause", ExceptionUtils.getCause(throwable).getMessage());
        }

    @Test
        public void testGetDefaultCauseMethodNames_2() {
            String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();
            assertNotNull(methodNames);
            assertTrue(Arrays.asList(methodNames).containsAll(Arrays.asList(ExceptionUtils.CAUSE_METHOD_NAMES)));
        }

    @Test
        public void testGetMessage_NullThrowable_2() {
            assertEquals("", ExceptionUtils.getMessage(null));
        }

    @Test
        public void testGetMessage_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            assertEquals("RuntimeException: test", ExceptionUtils.getMessage(throwable));
        }

    @Test
        public void testGetRootCause_NullThrowable_2() {
            assertNull(ExceptionUtils.getRootCause(null));
        }

    @Test
        public void testGetRootCause_NoCause() {
            Throwable throwable = new RuntimeException("test");
            assertEquals(RuntimeException.class, ExceptionUtils.getRootCause(throwable).getClass());
            assertEquals("test", ExceptionUtils.getRootCause(throwable).getMessage());
        }

    @Test
        public void testGetRootCause_WithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(IllegalArgumentException.class, ExceptionUtils.getRootCause(throwable).getClass());
            assertEquals("cause", ExceptionUtils.getRootCause(throwable).getMessage());
        }

    @Test
        public void testGetRootCauseMessage_NullThrowable_2() {
            assertEquals("", ExceptionUtils.getRootCauseMessage(null));
        }

    @Test
        public void testGetRootCauseMessage_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            assertEquals("RuntimeException: test", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetRootCauseMessage_WithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals("IllegalArgumentException: cause", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetRootCauseStackTrace_NullThrowable() {
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(null);
            assertNotNull(stackTrace);
            assertTrue(stackTrace.isEmpty());
        }

    @Test
        public void testGetRootCauseStackTrace_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertNotNull(stackTrace);
            assertFalse(stackTrace.isEmpty());
            assertTrue(stackTrace.stream().anyMatch(s -> s.contains("RuntimeException: test")));
        }

    @Test
        public void testGetRootCauseStackTrace_WithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertNotNull(stackTrace);
            assertFalse(stackTrace.isEmpty());
            assertTrue(stackTrace.stream().anyMatch(s -> s.contains("IllegalArgumentException: cause")));
        }

    @Test
        public void testGetStackFrames_NullThrowable() {
            String[] frames = ExceptionUtils.getStackFrames(null);
            assertNotNull(frames);
            assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, frames));
        }

    @Test
        public void testGetStackFrames_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            String[] frames = ExceptionUtils.getStackFrames(throwable);
            assertNotNull(frames);
            assertFalse(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, frames));
            assertTrue(Arrays.stream(frames).anyMatch(s -> s.contains("RuntimeException: test")));
        }

    @Test
        public void testGetStackTrace_NullThrowable_2() {
            assertEquals("", ExceptionUtils.getStackTrace(null));
        }

    @Test
        public void testGetStackTrace_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertNotNull(stackTrace);
            assertFalse(stackTrace.isEmpty());
            assertTrue(stackTrace.contains("RuntimeException: test"));
        }

    @Test
        public void testGetThrowableCount_NullThrowable() {
            assertEquals(0, ExceptionUtils.getThrowableCount(null));
        }

    @Test
        public void testGetThrowableCount_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            assertEquals(1, ExceptionUtils.getThrowableCount(throwable));
        }

    @Test
        public void testGetThrowableList_NullThrowable() {
            List<Throwable> list = ExceptionUtils.getThrowableList(null);
            assertNotNull(list);
            assertTrue(list.isEmpty());
        }

    @Test
        public void testGetThrowableList_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
            assertNotNull(list);
            assertFalse(list.isEmpty());
            assertEquals(RuntimeException.class, list.get(0).getClass());
            assertEquals("test", list.get(0).getMessage());
        }

    @Test
        public void testGetThrowables_NullThrowable() {
            Throwable[] throwables = ExceptionUtils.getThrowables(null);
            assertNotNull(throwables);
            assertTrue(Arrays.equals(ArrayUtils.EMPTY_THROWABLE_ARRAY, throwables));
        }

    @Test
        public void testGetThrowables_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
            assertNotNull(throwables);
            assertFalse(Arrays.equals(ArrayUtils.EMPTY_THROWABLE_ARRAY, throwables));
            assertEquals(RuntimeException.class, throwables[0].getClass());
            assertEquals("test", throwables[0].getMessage());
        }

    @Test
        public void testHasCause_NullThrowable() {
            assertFalse(ExceptionUtils.hasCause(null, RuntimeException.class));
        }

    @Test
        public void testHasCause_NoCause() {
            Throwable throwable = new RuntimeException("test");
            assertFalse(ExceptionUtils.hasCause(throwable, IllegalArgumentException.class));
        }

    @Test
        public void testHasCause_WithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertTrue(ExceptionUtils.hasCause(throwable, IllegalArgumentException.class));
        }

    @Test
        public void testIndexOfThrowable_ClassFound_2() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(1, ExceptionUtils.indexOfThrowable(throwable, IllegalArgumentException.class));
        }

    @Test
        public void testIndexOfThrowable_TypeFound() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(1, ExceptionUtils.indexOfType(throwable, IllegalArgumentException.class));
        }

    @Test
        public void testIsChecked_CheckedException_2() {
            assertTrue(ExceptionUtils.isChecked(new IOException()));
        }

    @Test
        public void testIsUnchecked_UncheckedException() {
            assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        }

    @Test
        public void testPrintRootCauseStackTrace_NullThrowable() {
            ExceptionUtils.printRootCauseStackTrace(null);
        }

    @Test
        public void testPrintRootCauseStackTrace_WithMessage() {
            Throwable throwable = new RuntimeException("test");
            ExceptionUtils.printRootCauseStackTrace(throwable);
        }

    @Test
        public void testPrintRootCauseStackTrace_NullWriter() {
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(new RuntimeException(), null));
        }

    @Test
        public void testPrintRootCauseStackTrace_WithMessageAndWriter() {
            Throwable throwable = new RuntimeException("test");
            ExceptionUtils.printRootCauseStackTrace(throwable, System.out);
        }

    @Test
        public void testRethrow_2() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.rethrow(throwable);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testThrowableOfThrowable_ClassFound() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(IllegalArgumentException.class, ExceptionUtils.throwableOfThrowable(throwable, IllegalArgumentException.class).getClass());
        }

    @Test
        public void testThrowableOfThrowable_TypeFound() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(IllegalArgumentException.class, ExceptionUtils.throwableOfType(throwable, IllegalArgumentException.class).getClass());
        }
}
