package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
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
        public void testGetDefaultCauseMethodNames_2() {
            String[] expected = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
            assertArrayEquals(expected, ExceptionUtils.getDefaultCauseMethodNames());
        }

    @Test
        public void testGetMessageWithNullThrowable() {
            String result = ExceptionUtils.getMessage(null);
            assertEquals("", result);
        }

    @Test
        public void testGetRootCauseWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            Throwable rootCause = ExceptionUtils.getRootCause(throwable);
            assertNull(rootCause);
        }

    @Test
        public void testGetRootCauseMessageWithNullThrowable() {
            String result = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", result);
        }

    @Test
        public void testGetRootCauseStackTraceWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertTrue(stackTrace.isEmpty());
        }

    
    @Test
        public void testGetStackTraceWithNullThrowable() {
            String result = ExceptionUtils.getStackTrace(null);
            assertEquals("", result);
        }

    @Test
        public void testGetThrowableCountWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            int count = ExceptionUtils.getThrowableCount(throwable);
            assertEquals(1, count);
        }

    @Test
        public void testGetThrowableListWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
            assertEquals(1, list.size());
        }

    @Test
        public void testGetThrowablesWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
            assertArrayEquals(new Throwable[]{throwable}, throwables);
        }

    @Test
        public void testHasCauseWithNullThrowable() {
            boolean result = ExceptionUtils.hasCause(null, RuntimeException.class);
            assertFalse(result);
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable() {
            int index = ExceptionUtils.indexOfThrowable(null, RuntimeException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIndexOfTypeWithNullThrowable() {
            int index = ExceptionUtils.indexOfType(null, RuntimeException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIsCheckedWithRuntimeException() {
            boolean result = ExceptionUtils.isChecked(new RuntimeException());
            assertFalse(result);
        }

    @Test
        public void testIsUncheckedWithError() {
            boolean result = ExceptionUtils.isUnchecked(new Error());
            assertTrue(result);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable() {
            ExceptionUtils.printRootCauseStackTrace(null, System.err);
        }

    @Test
        public void testRemoveCommonFramesWithEmptyLists() {
            List<String> causeFrames = new ArrayList<>();
            List<String> wrapperFrames = new ArrayList<>();
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertTrue(causeFrames.isEmpty());
            assertTrue(wrapperFrames.isEmpty());
        }

    @Test
        public void testRethrowWithNullThrowable() {
            assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(null));
        }

    @Test
        public void testStreamWithNullThrowable() {
            Stream<Throwable> stream = ExceptionUtils.stream(null);
            assertFalse(stream.findAny().isPresent());
        }

    @Test
        public void testThrowableOfThrowableWithNullThrowable() {
            assertThrows(RuntimeException.class, () -> ExceptionUtils.throwableOfThrowable(null, RuntimeException.class));
        }

    @Test
        public void testThrowUncheckedWithRuntimeException() {
            RuntimeException exception = new RuntimeException();
            Throwable result = ExceptionUtils.throwUnchecked(exception);
            assertEquals(exception, result);
        }
}
