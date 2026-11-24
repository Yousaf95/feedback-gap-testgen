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
import java.util.function.Supplier;

public class Failable_GenTest {

    @Test
    public void testAccept() {
        BiConsumer<String, String> consumer = (a, b) -> System.out.println(a + b);
        Failable.accept((FailableBiConsumer<String, String, IOException>) consumer::accept, "Hello", "World");
    }

    @Test
    public void testApply() {
        Function<Integer, Integer> function = x -> x * 2;
        int result = Failable.apply((FailableFunction<Integer, Integer, IOException>) function::apply, 5);
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
        boolean result = Failable.test((FailablePredicate<String, IOException>) predicate::test, "Hello");
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


    // ---- added by coverage-guided refinement ----
    @Test
        public void testAccept_2() {
            Failable.accept((String s) -> System.out.println(s), "test");
        }

    @Test
        public void testApply_2() {
            Function<String, Integer> function = (s) -> s.length();
            assertEquals(4, Failable.apply(function, "test"));
        }

    @Test
        public void testCall_2() throws Exception {
            FailableCallable<Integer, IOException> callable = () -> 42;
            assertEquals(42, Failable.call(callable));
        }

    @Test
        public void testGet_2() {
            FailableSupplier<String, IOException> supplier = () -> "test";
            assertEquals("test", Failable.get(supplier));
        }

    @Test
        public void testRun_2() throws Exception {
            FailableRunnable<IOException> runnable = () -> System.out.println("run");
            Failable.run(runnable);
        }

    @Test
        public void testTest_2() {
            Predicate<String> predicate = (s) -> s.length() > 0;
            assertTrue(Failable.test(predicate, "test"));
        }

    @Test
        public void testTryWithResourcesWithError_2() throws Exception {
            FailableRunnable<IOException> action = () -> System.out.println("action");
            FailableRunnable<IOException> resource = () -> { throw new IOException(); };
            assertThrows(UncheckedIOException.class, () -> Failable.tryWithResources(action, resource));
        }

    @Test
        public void testTryWithResourcesWithErrorHandler_2() throws Exception {
            FailableRunnable<IOException> action = () -> System.out.println("action");
            FailableConsumer<Throwable, IOException> errorHandler = t -> { throw new UncheckedIOException((IOException) t); };
            FailableRunnable<IOException> resource = () -> { throw new IOException(); };
            assertThrows(UncheckedIOException.class, () -> Failable.tryWithResources(action, errorHandler, resource));
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerAndException_2() throws Exception {
            FailableRunnable<IOException> action = () -> System.out.println("action");
            FailableConsumer<Throwable, IOException> errorHandler = t -> { throw new UncheckedIOException((IOException) t); };
            FailableRunnable<IOException> resource = () -> { throw new IOException(); };
            assertThrows(UncheckedIOException.class, () -> Failable.tryWithResources(action, errorHandler, resource));
        }

    @Test
        public void testTryWithResources_2() throws Exception {
            FailableRunnable<IOException> action = () -> System.out.println("action");
            FailableRunnable<IOException> resource = () -> { };
            Failable.tryWithResources(action, resource);
        }
}
