package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Failable_GenTest {

    @Test
    public void testAccept() {
        Consumer<String> consumer = s -> System.out.println(s);
        Failable.accept(consumer, "test");
    }

    @Test
    public void testApply() {
        Function<Integer, Integer> function = i -> i * 2;
        int result = Failable.apply(function, 5);
        assertEquals(10, result);
    }

    @Test
    public void testCall() throws IOException {
        FailableCallable<String, IOException> callable = () -> "test";
        String result = Failable.call(callable);
        assertEquals("test", result);
    }

    @Test
    public void testGet() {
        FailableSupplier<Integer, RuntimeException> supplier = () -> 5;
        int result = Failable.get(supplier);
        assertEquals(5, result);
    }

    @Test
    public void testRun() {
        FailableRunnable<RuntimeException> runnable = () -> System.out.println("test");
        Failable.run(runnable);
    }

    @Test
    public void testTryWithResources() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableConsumer<Throwable, IOException> errorHandler = mock(FailableConsumer.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        Failable.tryWithResources(action, errorHandler, resource1, resource2);

        verify(action).run();
        verify(resource1).run();
        verify(resource2).run();
    }

    @Test
    public void testTryWithResourcesWithErrorHandler() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableConsumer<Throwable, IOException> errorHandler = mock(FailableConsumer.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        doThrow(new IOException("test")).when(action).run();

        Failable.tryWithResources(action, errorHandler, resource1, resource2);

        verify(errorHandler).accept(any(IOException.class));
    }

    @Test
    public void testTryWithResourcesWithError() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        doThrow(new IOException("test")).when(action).run();
        doThrow(new IOException("resource1")).when(resource1).run();

        assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, null, resource1, resource2);
        });
    }

    @Test
    public void testTryWithResourcesWithErrorInErrorHandler() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableConsumer<Throwable, IOException> errorHandler = mock(FailableConsumer.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        doThrow(new IOException("test")).when(action).run();
        doThrow(new IOException("errorHandler")).when(errorHandler).accept(any(IOException.class));

        assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, errorHandler, resource1, resource2);
        });
    }

    @Test
    public void testTryWithResourcesWithErrorInResource() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableConsumer<Throwable, IOException> errorHandler = mock(FailableConsumer.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        doThrow(new IOException("test")).when(action).run();
        doThrow(new IOException("resource2")).when(resource2).run();

        assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, errorHandler, resource1, resource2);
        });
    }

    @Test
    public void testTryWithResourcesWithErrorInAll() throws IOException {
        FailableRunnable<IOException> action = mock(FailableRunnable.class);
        FailableConsumer<Throwable, IOException> errorHandler = mock(FailableConsumer.class);
        FailableRunnable<IOException> resource1 = mock(FailableRunnable.class);
        FailableRunnable<IOException> resource2 = mock(FailableRunnable.class);

        doThrow(new IOException("test")).when(action).run();
        doThrow(new IOException("resource1")).when(resource1).run();
        doThrow(new IOException("resource2")).when(resource2).run();

        assertThrows(IOException.class, () -> {
            Failable.tryWithResources(action, errorHandler, resource1, resource2);
        });
    }

}
