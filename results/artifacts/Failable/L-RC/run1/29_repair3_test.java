package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Failable_GenTest {

    @Test
    void testAcceptConsumerNormalCase() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> System.out.println(s);

        // Act & Assert
        assertDoesNotThrow(() -> Failable.accept(consumer, "test"));
    }

    @Test
    void testAcceptConsumerThrowsException() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(java.io.UncheckedIOException.class, () -> Failable.accept(consumer, "test"));
        assertEquals("Expected", exception.getCause().getMessage());
    }

    @Test
    void testApplyFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = s -> s.length();

        // Act
        int result = Failable.apply(function, "test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testApplyFunctionThrowsException() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(java.io.UncheckedIOException.class, () -> Failable.apply(function, "test"));
        assertEquals("Expected", exception.getCause().getMessage());
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
        FailableSupplier<String, IOException> supplier = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(java.io.UncheckedIOException.class, () -> Failable.get(supplier));
        assertEquals("Expected", exception.getCause().getMessage());
    }

    @Test
    void testRunRunnableNormalCase() {
        // Arrange
        FailableRunnable<IOException> runnable = () -> System.out.println("Running");

        // Act & Assert
        assertDoesNotThrow(() -> Failable.run(runnable));
    }

    @Test
    void testRunRunnableThrowsException() {
        // Arrange
        FailableRunnable<IOException> runnable = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(java.io.UncheckedIOException.class, () -> Failable.run(runnable));
        assertEquals("Expected", exception.getCause().getMessage());
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
        FailableCallable<String, IOException> callable = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(java.io.UncheckedIOException.class, () -> Failable.call(callable));
        assertEquals("Expected", exception.getCause().getMessage());
    }

    @Test
    void testAsConsumerNormalCase() {
        // Arrange
        FailableConsumer<String, IOException> failableConsumer = s -> System.out.println(s);
        Consumer<String> consumer = Failable.asConsumer(failableConsumer);

        // Act & Assert
        assertDoesNotThrow(() -> consumer.accept("test"));
    }

    @Test
    void testAsFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> failableFunction = s -> s.length();
        Function<String, Integer> function = Failable.asFunction(failableFunction);

        // Act
        int result = function.apply("test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testAsPredicateNormalCase() {
        // Arrange
        FailablePredicate<String, IOException> failablePredicate = s -> s.length() > 0;
        Predicate<String> predicate = Failable.asPredicate(failablePredicate);

        // Act & Assert
        assertTrue(predicate.test("test"));
    }

    @Test
    void testAsRunnableNormalCase() {
        // Arrange
        FailableRunnable<IOException> failableRunnable = () -> System.out.println("Running");
        Runnable runnable = Failable.asRunnable(failableRunnable);

        // Act & Assert
        assertDoesNotThrow(runnable::run);
    }

    @Test
    void testAsSupplierNormalCase() {
        // Arrange
        FailableSupplier<String, IOException> failableSupplier = () -> "test";
        Supplier<String> supplier = Failable.asSupplier(failableSupplier);

        // Act
        String result = supplier.get();

        // Assert
        assertEquals("test", result);
    }

    @Test
    void testTryWithResourcesNormalCase() {
        // Arrange
        FailableRunnable<IOException> action = () -> System.out.println("Action");
        FailableConsumer<Throwable, IOException> errorHandler = t -> System.out.println(t.getMessage());
        FailableRunnable<IOException>[] resources = new FailableRunnable[]{ () -> System.out.println("Resource") };

        // Act & Assert
        assertDoesNotThrow(() -> Failable.tryWithResources(action, errorHandler, resources));
    }

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        void testAcceptDoubleConsumerNormalCase() {
            Failable.accept((value) -> {}, 1.0);
        }

    @Test
        void testAcceptIntConsumerNormalCase() {
            Failable.accept((FailableIntConsumer<?>) (value) -> {}, 1);
        }

    @Test
        void testAcceptLongConsumerNormalCase() {
            Failable.accept((FailableLongConsumer<?>) (value) -> {}, 1L);
        }

    @Test
        void testApplyBiFunctionNormalCase() {
            assertEquals("result", Failable.apply((input1, input2) -> "result", "in1", "in2"));
        }

    @Test
        void testApplyAsDoubleNormalCase() {
            assertEquals(1.0, Failable.applyAsDouble((left, right) -> 1.0, 1.0, 2.0));
        }

    @Test
        void testAsBiConsumerNormalCase() {
            List<String> list = new ArrayList<>();
            java.util.function.BiConsumer<String, String> consumer = Failable.asBiConsumer((input1, input2) -> list.add(input1 + input2));
            consumer.accept("a", "b");
            assertEquals("ab", list.get(0));
        }

    @Test
        void testAsBiFunctionNormalCase() {
            java.util.function.BiFunction<String, String, String> function = Failable.asBiFunction((input1, input2) -> input1 + input2);
            assertEquals("ab", function.apply("a", "b"));
        }

    @Test
        void testAsBiPredicateNormalCase() {
            java.util.function.BiPredicate<String, String> predicate = Failable.asBiPredicate((input1, input2) -> input1.equals(input2));
            assertTrue(predicate.test("a", "a"));
        }

    @Test
        void testAsCallableNormalCase() {
            java.util.concurrent.Callable<String> callable = Failable.asCallable(() -> "result");
            assertEquals("result", Failable.call(callable));
        }

    @Test
        void testGetAsDoubleNormalCase() {
            assertEquals(1.0, Failable.getAsDouble(() -> 1.0));
        }

    @Test
        void testGetAsIntNormalCase() {
            assertEquals(1, Failable.getAsInt(() -> 1));
        }

    @Test
        void testGetAsLongNormalCase() {
            assertEquals(1L, Failable.getAsLong(() -> 1L));
        }

    @Test
        void testGetAsShortNormalCase() {
            assertEquals((short) 1, Failable.getAsShort(() -> (short) 1));
        }

    @Test
        void testRethrowIOException() {
            IOException ioException = new IOException("test");
            java.io.UncheckedIOException uncheckedIOException = assertThrows(java.io.UncheckedIOException.class, () -> Failable.rethrow(ioException));
            assertEquals(ioException, uncheckedIOException.getCause());
        }

    @Test
        void testRethrowUndeclaredThrowableException() {
            RuntimeException runtimeException = new RuntimeException("test");
            java.lang.reflect.UndeclaredThrowableException undeclaredThrowableException = assertThrows(java.lang.reflect.UndeclaredThrowableException.class, () -> Failable.rethrow(runtimeException));
            assertEquals(runtimeException, undeclaredThrowableException.getCause());
        }

    @Test
        void testRunRunnableThrowsException_2() {
            IOException ioException = new IOException("test");
            RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> Failable.run(() -> { throw ioException; }));
            assertTrue(runtimeException.getCause() instanceof IOException);
        }

    @Test
        void testStreamCollectionNormalCase() {
            List<String> list = List.of("a", "b");
            org.apache.commons.lang3.stream.Streams.FailableStream<String> failableStream = Failable.stream(list);
            assertEquals(List.of("a", "b"), failableStream.collect(Collectors.toList()));
        }

    @Test
        void testStreamStreamNormalCase() {
            java.util.stream.Stream<String> stream = java.util.stream.Stream.of("a", "b");
            org.apache.commons.lang3.stream.Streams.FailableStream<String> failableStream = Failable.stream(stream);
            assertEquals(List.of("a", "b"), failableStream.collect(Collectors.toList()));
        }

    @Test
        void testTestBiPredicateNormalCase() {
            assertTrue(Failable.test((input1, input2) -> true, "a", "b"));
        }

    @Test
        void testTryWithResourcesNoErrorHandler() {
            IOException ioException = new IOException("test");
            assertThrows(RuntimeException.class, () -> Failable.tryWithResources(() -> { throw ioException; }, (FailableRunnable<? extends Throwable>) null));
        }

    @Test
        void testTryWithResourcesWithErrorHandler() {
            List<Throwable> errors = new ArrayList<>();
            Failable.tryWithResources(
                () -> { throw new IOException("test"); },
                (error) -> errors.add(error)
            );
            assertEquals(1, errors.size());
            assertTrue(errors.get(0) instanceof IOException);
        }

    @Test
        void testTryWithResourcesNormalCase_2() {
            Failable.tryWithResources(() -> {});
        }
}
