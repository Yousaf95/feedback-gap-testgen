package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    @Test
    public void testForEach() {
        Throwable throwable = new RuntimeException("test");
        List<String> messages = new ArrayList<>();
        ExceptionUtils.forEach(throwable, t -> messages.add(t.getMessage()));
        assertEquals(1, messages.size());
        assertEquals("test", messages.get(0));
    }

    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
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
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        String result = ExceptionUtils.getRootCauseMessage(throwable);
        assertEquals("RuntimeException: cause", result);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] result = ExceptionUtils.getStackFrames(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String result = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.getThrowableCount(throwable);
        assertEquals(2, result);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        List<Throwable> result = ExceptionUtils.getThrowableList(throwable);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable[] result = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, result.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
        assertTrue(result);
    }

    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
        assertEquals(0, result);
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

    @Test
    public void testPrintRootCauseStackTrace() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        ExceptionUtils.printRootCauseStackTrace(throwable, ps);
        String result = baos.toString();
        assertTrue(result.contains("cause"));
    }

    @Test
    public void testRemoveCommonFrames() {
        List<String> causeFrames = new ArrayList<>();
        causeFrames.add("Frame1");
        causeFrames.add("Frame2");
        List<String> wrapperFrames = new ArrayList<>();
        wrapperFrames.add("Frame3");
        wrapperFrames.add("Frame2");
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
        assertEquals(1, causeFrames.size());
        assertEquals("Frame1", causeFrames.get(0));
    }

    
    @Test
    public void testStream() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Stream<Throwable> stream = ExceptionUtils.stream(throwable);
        List<Throwable> result = stream.toList();
        assertEquals(2, result.size());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetCauseWithCustomMethodName() {
            Throwable cause = new RuntimeException("Custom Cause");
            Throwable rootCause = new IOException("Root Cause", cause);
            
            Throwable result = ExceptionUtils.getCause(rootCause, new String[]{"getTargetException"});
            
            assertEquals(cause, result);
        }

    @Test
        public void testGetMessageWithNullThrowable() {
            String message = ExceptionUtils.getMessage(null);
            
            assertEquals("", message);
        }

    @Test
        public void testGetRootCauseWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            
            Throwable result = ExceptionUtils.getRootCause(throwable);
            
            assertNull(result);
        }

    @Test
        public void testGetRootCauseMessageWithNullThrowable() {
            String message = ExceptionUtils.getRootCauseMessage(null);
            
            assertEquals("", message);
        }

    @Test
        public void testGetRootCauseStackTraceWithNoCause() {
            Throwable throwable = new RuntimeException("No Cause");
            
            String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(throwable);
            
            assertTrue(stackTrace.length > 0);
        }

    @Test
        public void testGetStackFramesWithNullThrowable() {
            String[] frames = ExceptionUtils.getStackFrames(null);
            
            assertEquals(0, frames.length);
        }

    @Test
        public void testGetStackTraceWithNullThrowable() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            
            assertEquals("", stackTrace);
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
            
            assertEquals(1, throwables.length);
        }

    @Test
        public void testHasCauseWithNullThrowable() {
            boolean result = ExceptionUtils.hasCause(null, RuntimeException.class);
            
            assertFalse(result);
        }

    @Test
        public void testIndexOfThrowableWithNoMatch() {
            Throwable throwable = new RuntimeException("No Match");
            
            int index = ExceptionUtils.indexOfThrowable(throwable, IOException.class);
            
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
            
            // No assertion needed as the method should not throw an exception
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
        public void testStreamWithNullThrowable() {
            Stream<Throwable> stream = ExceptionUtils.stream(null);
            
            assertFalse(stream.findAny().isPresent());
        }

    @Test
        public void testThrowUncheckedWithError() {
            Error error = new Error();
            
            assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(error));
        }
}
