package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testGetCause_NullThrowable() {
            Throwable cause = ExceptionUtils.getCause(null);
            assertNull(cause);
        }

    @Test
        public void testGetCause_NoMethodNames() {
            Throwable throwable = new RuntimeException();
            Throwable cause = ExceptionUtils.getCause(throwable, null);
            assertEquals(throwable, cause);
        }

    @Test
        public void testGetCause_MethodNameFound() throws NoSuchMethodException {
            Throwable throwable = new RuntimeException("test");
            Method method = throwable.getClass().getMethod("getMessage");
            when(method.invoke(throwable)).thenReturn("test");
            Throwable cause = ExceptionUtils.getCauseUsingMethodName(throwable, "getMessage");
            assertEquals(RuntimeException.class, cause.getClass());
        }

    @Test
        public void testGetMessage_NullThrowable() {
            String message = ExceptionUtils.getMessage(null);
            assertEquals(StringUtils.EMPTY, message);
        }

    @Test
        public void testGetRootCause_NullThrowable() {
            Throwable rootCause = ExceptionUtils.getRootCause(null);
            assertNull(rootCause);
        }

    @Test
        public void testGetRootCauseMessage_NullThrowable() {
            String message = ExceptionUtils.getRootCauseMessage(null);
            assertEquals(StringUtils.EMPTY, message);
        }

    @Test
        public void testGetRootCauseStackTraceList_NullThrowable() {
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(stackTrace.isEmpty());
        }

    @Test
        public void testGetStackFrames_NullThrowable() {
            String[] frames = ExceptionUtils.getStackFrames(null);
            assertEquals(0, frames.length);
        }

    @Test
        public void testGetStackTrace_NullThrowable() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals(StringUtils.EMPTY, stackTrace);
        }

    @Test
        public void testGetThrowableCount_NullThrowable() {
            int count = ExceptionUtils.getThrowableCount(null);
            assertEquals(0, count);
        }

    @Test
        public void testGetThrowableList_NullThrowable() {
            List<Throwable> list = ExceptionUtils.getThrowableList(null);
            assertTrue(list.isEmpty());
        }

    @Test
        public void testHasCause_UnwrappedException() {
            Throwable throwable = new RuntimeException();
            boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
            assertTrue(result);
        }

    @Test
        public void testIndexOfThrowable_ClassFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
            assertEquals(0, index);
        }

    @Test
        public void testIndexOfThrowable_ClassNotFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfThrowable(throwable, IOException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIndexOfType_TypeFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfType(throwable, RuntimeException.class);
            assertEquals(0, index);
        }

    @Test
        public void testIndexOfType_TypeNotFound() {
            Throwable throwable = new RuntimeException("test");
            int index = ExceptionUtils.indexOfType(throwable, IOException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIsChecked_CheckedException() {
            boolean result = ExceptionUtils.isChecked(new IOException());
            assertTrue(result);
        }

    @Test
        public void testIsUnchecked_UncheckedException() {
            boolean result = ExceptionUtils.isUnchecked(new RuntimeException());
            assertTrue(result);
        }

    @Test
        public void testPrintRootCauseStackTrace_NullThrowable() {
            ExceptionUtils.printRootCauseStackTrace(null, System.err);
        }

    @Test
        public void testRemoveCommonFrames() {
            List<String> causeFrames = Arrays.asList("frame1", "frame2");
            List<String> wrapperFrames = Arrays.asList("frame3", "frame4", "frame2");
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(Arrays.asList("frame1"), causeFrames);
        }

    @Test
        public void testRethrow_2() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.rethrow(throwable);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testStream_NullThrowable() {
            Stream<Throwable> stream = ExceptionUtils.stream(null);
            assertFalse(stream.findAny().isPresent());
        }

    @Test
        public void testThrowableOfThrowable_ClassFound() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.throwableOfThrowable(throwable, RuntimeException.class);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testThrowableOfType_TypeFound() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.throwableOfType(throwable, RuntimeException.class);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testThrowUnchecked_UncheckedException() {
            RuntimeException exception = new RuntimeException();
            RuntimeException result = ExceptionUtils.throwUnchecked(exception);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testWrapAndThrow() {
            Throwable throwable = new IOException("test");
            assertThrows(UndeclaredThrowableException.class, () -> ExceptionUtils.wrapAndThrow(throwable));
        }
}
