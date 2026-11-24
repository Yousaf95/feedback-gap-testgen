package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Failable_GenTest {

    @Test
    public void testAccept() {
        BiConsumer<String, String> consumer = (a, b) -> System.out.println(a + b);
        Failable.accept(consumer, "Hello", "World");
    }

    @Test
    public void testApply() {
        Function<Integer, Integer> function = x -> x * 2;
        int result = Failable.apply(function, 5);
        assertEquals(10, result);
    }

    @Test
    public void testCall() throws IOException {
        FailableCallable<String, IOException> callable = () -> "Hello";
        String result = Failable.call(callable);
        assertEquals("Hello", result);
    }

    @Test
    public void testGet() {
        FailableSupplier<Integer, RuntimeException> supplier = () -> 42;
        int result = Failable.get(supplier);
        assertEquals(42, result);
    }

    @Test
    public void testRun() throws IOException {
        FailableRunnable<IOException> runnable = () -> System.out.println("Hello");
        Failable.run(runnable);
    }

    @Test
    public void testTest() {
        Predicate<String> predicate = s -> s.length() > 0;
        boolean result = Failable.test(predicate, "Hello");
        assertTrue(result);
    }

    @Test
    public void testTryWithResources() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> System.out.println("Resource2")
        );
        Failable.tryWithResources(() -> System.out.println("Action"), null, resources.toArray(new FailableRunnable[0]));
    }

    @Test
    public void testTryWithResourcesWithError() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), null, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandler() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        Failable.tryWithResources(() -> System.out.println("Action"), t -> System.out.println("Error handled: " + t.getMessage()), resources.toArray(new FailableRunnable[0]));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandler() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAndExceptionInErrorHandlerAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndExceptionAndException() throws IOException {
        List<FailableRunnable<IOException>> resources = Arrays.asList(
            () -> System.out.println("Resource1"),
            () -> { throw new IOException("Error"); }
        );
        assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> System.out.println("Action"), t -> { throw new RuntimeException(t); }, resources.toArray(new FailableRunnable[0])));
    }

    @Test
    public void testTryWithResourcesWithErrorHandlerAnd