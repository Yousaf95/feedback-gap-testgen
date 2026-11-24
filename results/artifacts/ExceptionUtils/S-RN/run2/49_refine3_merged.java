package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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

    
    
    @Test
        public void testGetMessageWithNullThrowable() {
            String message = ExceptionUtils.getMessage(null);
                    
            assertEquals("", message);
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
        public void testGetRootCauseMessageWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            String message = ExceptionUtils.getRootCauseMessage(checked);
            assertEquals("Exception: Checked exception", message, "Should return the root cause message for a checked exception");
        }

    
    @Test
        public void testGetThrowableCountWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            int count = ExceptionUtils.getThrowableCount(checked);
            assertEquals(1, count, "Should return 1 for a checked exception with no cause");
        }

    @Test
        public void testGetThrowableListWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            List<Throwable> list = ExceptionUtils.getThrowableList(checked);
            assertEquals(1, list.size(), "Should return a list of size 1 for a checked exception with no cause");
        }

    @Test
        public void testGetThrowablesWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            Throwable[] throwables = ExceptionUtils.getThrowables(checked);
            assertEquals(1, throwables.length, "Should return an array of length 1 for a checked exception with no cause");
        }

    @Test
        public void testHasCauseWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            boolean hasCause = ExceptionUtils.hasCause(checked, Exception.class);
            assertTrue(hasCause, "Should return true for a checked exception with the specified type");
        }

    @Test
        public void testIndexOfThrowableWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            int index = ExceptionUtils.indexOfThrowable(checked, Exception.class);
            assertEquals(0, index, "Should return 0 for a checked exception with the specified type");
        }

    @Test
        public void testIsCheckedWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            boolean isChecked = ExceptionUtils.isChecked(checked);
            assertTrue(isChecked, "Should return true for a checked exception");
        }

    @Test
        public void testIsUncheckedWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            boolean isUnchecked = ExceptionUtils.isUnchecked(checked);
            assertFalse(isUnchecked, "Should return false for a checked exception");
        }

    
    
    @Test
        public void testStreamWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            Stream<Throwable> stream = ExceptionUtils.stream(checked);
            List<Throwable> list = stream.toList();
            assertEquals(1, list.size(), "Should return a stream of size 1 for a checked exception with no cause");
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetRootCauseWithCheckedException() {
            Throwable checkedException = new Exception("Checked Exception");
            Throwable rootCause = ExceptionUtils.getRootCause(checkedException);
            assertNull(rootCause, "Should return null for a non-wrapped checked exception");
        }

    @Test
        public void testGetRootCauseMessageWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            String message = ExceptionUtils.getRootCauseMessage(checkedException);
            assertEquals("Checked Exception", message, "Should return the same message for a non-wrapped checked exception");
        }

    @Test
        public void testGetRootCauseStackTraceWithNoCause_2() {
            Throwable throwable = new RuntimeException();
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertEquals(Arrays.asList("java.lang.RuntimeException"), stackTrace, "Should return the stack trace of the root cause");
        }

    @Test
        public void testGetThrowableCountWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            int count = ExceptionUtils.getThrowableCount(checkedException);
            assertEquals(1, count, "Should return 1 for a non-wrapped checked exception");
        }

    @Test
        public void testGetThrowableListWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            List<Throwable> throwableList = ExceptionUtils.getThrowableList(checkedException);
            assertEquals(Arrays.asList(checkedException), throwableList, "Should return a list containing the root cause");
        }

    @Test
        public void testGetThrowablesWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            Throwable[] throwables = ExceptionUtils.getThrowables(checkedException);
            assertEquals(new Throwable[]{checkedException}, throwables, "Should return an array containing the root cause");
        }

    @Test
        public void testHasCauseWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            boolean hasCause = ExceptionUtils.hasCause(checkedException, Exception.class);
            assertTrue(hasCause, "Should return true for a non-wrapped checked exception");
        }

    @Test
        public void testIndexOfThrowableWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            int index = ExceptionUtils.indexOfThrowable(checkedException, Exception.class);
            assertEquals(0, index, "Should return 0 for the root cause");
        }

    @Test
        public void testIsCheckedWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            boolean isChecked = ExceptionUtils.isChecked(checkedException);
            assertTrue(isChecked, "Should return true for a checked exception");
        }

    @Test
        public void testIsUncheckedWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            boolean isUnchecked = ExceptionUtils.isUnchecked(checkedException);
            assertFalse(isUnchecked, "Should return false for a checked exception");
        }

    @Test
        public void testStreamWithCheckedException_2() {
            Exception checkedException = new Exception("Checked Exception");
            List<Throwable> throwableList = ExceptionUtils.stream(checkedException).toList();
            assertEquals(Arrays.asList(checkedException), throwableList, "Should stream the root cause");
        }
}
