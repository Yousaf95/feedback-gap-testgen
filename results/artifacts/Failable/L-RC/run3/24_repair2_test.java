package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Failable_GenTest {

    @Test
    void testAcceptConsumerNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableConsumer<String, IOException> consumer = sb::append;

        // Act
        Failable.accept(consumer, "test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAcceptConsumerThrowsException() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> { throw new IOException("test exception"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.accept(consumer, "test"));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testApplyFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = String::length;

        // Act
        int result = Failable.apply(function, "test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testApplyFunctionThrowsException() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = s -> { throw new IOException("test exception"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.apply(function, "test"));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testAsConsumerNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableConsumer<String, IOException> consumer = sb::append;
        java.util.function.Consumer<String> standardConsumer = Failable.asConsumer(consumer);

        // Act
        standardConsumer.accept("test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAsConsumerThrowsException() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> { throw new IOException("test exception"); };
        java.util.function.Consumer<String> standardConsumer = Failable.asConsumer(consumer);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> standardConsumer.accept("test"));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testAsFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = String::length;
        java.util.function.Function<String, Integer> standardFunction = Failable.asFunction(function);

        // Act
        int result = standardFunction.apply("test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testAsFunctionThrowsException() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = s -> { throw new IOException("test exception"); };
        java.util.function.Function<String, Integer> standardFunction = Failable.asFunction(function);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> standardFunction.apply("test"));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testCallCallableNormalCase() {
        // Arrange
        FailableCallable<String, IOException> callable = () -> "test";

        // Act
        String result = Failable.call(callable);

        // Assert
        assertEquals("test", result);
    }

    @Test
    void testCallCallableThrowsException() {
        // Arrange
        FailableCallable<String, IOException> callable = () -> { throw new IOException("test exception"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.call(callable));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testGetSupplierNormalCase() {
        // Arrange
        FailableSupplier<String, IOException> supplier = () -> "test";

        // Act
        String result = Failable.get(supplier);

        // Assert
        assertEquals("test", result);
    }

    @Test
    void testGetSupplierThrowsException() {
        // Arrange
        FailableSupplier<String, IOException> supplier = () -> { throw new IOException("test exception"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.get(supplier));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testRunRunnableNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> runnable = () -> sb.append("test");

        // Act
        Failable.run(runnable);

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testRunRunnableThrowsException() {
        // Arrange
        FailableRunnable<IOException> runnable = () -> { throw new IOException("test exception"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.run(runnable));
        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("test exception", exception.getCause().getMessage());
    }

    @Test
    void testTryWithResourcesNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> action = () -> sb.append("action");
        FailableConsumer<Throwable, IOException> errorHandler = t -> sb.append("errorHandler");

        // Act
        Failable.tryWithResources(action, errorHandler);

        // Assert
        assertEquals("action", sb.toString());
    }

    @Test
    void testTryWithResourcesActionThrowsException() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> action = () -> { throw new IOException("test exception"); };
        FailableConsumer<Throwable, IOException> errorHandler = t -> sb.append("errorHandler");

        // Act
        Failable.tryWithResources(action, errorHandler);

        // Assert
        assertEquals("errorHandler", sb.toString());
    }

    @Test
    void testTryWithResourcesResourceThrowsException() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> action = () -> sb.append("action");
        FailableConsumer<Throwable, IOException> errorHandler = t -> sb.append("errorHandler");
        FailableRunnable<IOException> resource = () -> { throw new IOException("resource exception"); };

        // Act
        Failable.tryWithResources(action, errorHandler, resource);

        // Assert
        assertEquals("actionerrorHandler", sb.toString());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAcceptDoubleConsumerNormalCase() {
            Failable.accept((value) -> {}, 1.0);
        }

    @Test
        void testAcceptIntConsumerNormalCase() {
            Failable.accept((FailableIntConsumer<Throwable>) (value) -> {}, 1);
        }

    @Test
        void testAcceptLongConsumerNormalCase() {
            Failable.accept((FailableLongConsumer<Throwable>) (value) -> {}, 1L);
        }

    @Test
        void testApplyBiFunctionNormalCase() {
            String result = Failable.apply((a, b) -> a + b, "Hello", "World");
            assertTrue(result.equals("HelloWorld"));
        }

    @Test
        void testApplyAsDoubleNormalCase() {
            double result = Failable.applyAsDouble((a, b) -> a + b, 1.0, 2.0);
            assertTrue(result == 3.0);
        }

    @Test
        void testAsBiConsumerNormalCase() {
            java.util.function.BiConsumer<String, String> consumer = Failable.asBiConsumer((a, b) -> {});
            consumer.accept("Hello", "World");
        }

    @Test
        void testAsBiFunctionNormalCase() {
            java.util.function.BiFunction<String, String, String> function = Failable.asBiFunction((a, b) -> a + b);
            assertTrue(function.apply("Hello", "World").equals("HelloWorld"));
        }

    @Test
        void testAsBiPredicateNormalCase() {
            java.util.function.BiPredicate<String, String> predicate = Failable.asBiPredicate((a, b) -> a.equals(b));
            assertTrue(predicate.test("Hello", "Hello"));
        }

    @Test
        void testAsStreamCollectionNormalCase() {
            Streams.FailableStream<String> failableStream = Failable.stream(Collections.singletonList("Hello"));
            List<String> result = failableStream.collect(Collectors.toList());
            assertTrue(result.contains("Hello"));
        }

    @Test
        void testAsStreamStreamNormalCase() {
            Stream<String> stream = Stream.of("Hello");
            Streams.FailableStream<String> failableStream = Failable.stream(stream);
            List<String> result = failableStream.collect(Collectors.toList());
            assertTrue(result.contains("Hello"));
        }

    @Test
        void testTestBiPredicateNormalCase() {
            boolean result = Failable.test((a, b) -> a.equals(b), "Hello", "Hello");
            assertTrue(result);
        }

    @Test
        void testTryWithResourcesActionThrowsException_2() {
            assertThrows(RuntimeException.class, () -> 
                Failable.tryWithResources(() -> { throw new IOException("Test"); })
            );
        }

    @Test
        void testTryWithResourcesResourceThrowsException_2() {
            assertThrows(RuntimeException.class, () -> 
                Failable.tryWithResources(
                    () -> {}, 
                    (Throwable t) -> {},
                    () -> { throw new IOException("Test"); }
                )
            );
        }

    @Test
        void testTryWithResourcesNoErrorHandler() {
            assertThrows(RuntimeException.class, () -> 
                Failable.tryWithResources(
                    () -> { throw new IOException("Test"); },
                    (FailableRunnable<Throwable>[]) null
                )
            );
        }

    @Test
        void testGetAsBooleanNormalCase() {
            boolean result = Failable.getAsBoolean(() -> true);
            assertTrue(result);
        }

    @Test
        void testGetAsDoubleNormalCase() {
            double result = Failable.getAsDouble(() -> 1.0);
            assertTrue(result == 1.0);
        }

    @Test
        void testGetAsIntNormalCase() {
            int result = Failable.getAsInt(() -> 1);
            assertTrue(result == 1);
        }

    @Test
        void testGetAsLongNormalCase() {
            long result = Failable.getAsLong(() -> 1L);
            assertTrue(result == 1L);
        }

    @Test
        void testGetAsShortNormalCase() {
            short result = Failable.getAsShort(() -> (short) 1);
            assertTrue(result == 1);
        }

    @Test
        void testRethrowIOException() {
            assertThrows(java.io.UncheckedIOException.class, () -> 
                Failable.rethrow(new IOException("Test"))
            );
        }

    @Test
        void testRethrowUndeclaredThrowableException() {
            assertThrows(java.lang.reflect.UndeclaredThrowableException.class, () -> 
                Failable.rethrow(new Exception("Test"))
            );
        }
}
