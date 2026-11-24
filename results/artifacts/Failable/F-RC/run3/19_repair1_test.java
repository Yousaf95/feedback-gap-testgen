package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Failable_GenTest {

    
    
    @ParameterizedTest
    @MethodSource("provideDoubleConsumer")
    public <E extends Throwable> void testAccept(FailableDoubleConsumer<E> consumer, double value) {
        // Arrange
        boolean[] called = {false};
        FailableDoubleConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    @ParameterizedTest
    @MethodSource("provideIntConsumer")
    public <E extends Throwable> void testAccept(FailableIntConsumer<E> consumer, int value) {
        // Arrange
        boolean[] called = {false};
        FailableIntConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    @ParameterizedTest
    @MethodSource("provideLongConsumer")
    public <E extends Throwable> void testAccept(FailableLongConsumer<E> consumer, long value) {
        // Arrange
        boolean[] called = {false};
        FailableLongConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @ParameterizedTest
    @MethodSource("provideRunnableWithResources")
    public <E extends Throwable> void testTryWithResources(FailableRunnable<E> action,
            FailableConsumer<Throwable, E> errorHandler, FailableRunnable<? extends Throwable>[] resources) {
        // Arrange
        boolean[] called = {false};
        FailableRunnable<E> failableAction = () -> {
            called[0] = true;
            action.run();
        };

        // Act & Assert
        assertDoesNotThrow(() -> Failable.tryWithResources(failableAction, errorHandler, resources));
        assertTrue(called[0]);
    }

    
    
    
    private static Stream<Arguments> provideBiConsumer() {
        return Stream.of(
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", 1),
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, null, 1),
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", null)
        );
    }

    private static Stream<Arguments> provideConsumer() {
        return Stream.of(
                arguments((Consumer<String>) s -> {}, "test"),
                arguments((Consumer<String>) s -> {}, null)
        );
    }

    private static Stream<Arguments> provideDoubleConsumer() {
        return Stream.of(
                arguments((FailableDoubleConsumer<IOException>) v -> {}, 1.0),
                arguments((FailableDoubleConsumer<IOException>) v -> {}, Double.NaN)
        );
    }

    private static Stream<Arguments> provideIntConsumer() {
        return Stream.of(
                arguments((FailableIntConsumer<IOException>) v -> {}, 1),
                arguments((FailableIntConsumer<IOException>) v -> {}, Integer.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideLongConsumer() {
        return Stream.of(
                arguments((FailableLongConsumer<IOException>) v -> {}, 1L),
                arguments((FailableLongConsumer<IOException>) v -> {}, Long.MAX_VALUE)
        );
    }

    
    
    private static Stream<Arguments> provideDoubleBinaryOperator() {
        return Stream.of(
                arguments((FailableDoubleBinaryOperator<IOException>) (l, r) -> l + r, 1.0, 2.0),
                arguments((FailableDoubleBinaryOperator<IOException>) (l, r) -> l + r, Double.NaN, 2.0)
        );
    }

    private static Stream<Arguments> provideCallable() {
        return Stream.of(
                arguments((FailableCallable<String, IOException>) () -> "test"),
                arguments((FailableCallable<String, IOException>) () -> null)
        );
    }

    private static Stream<Arguments> provideSupplier() {
        return Stream.of(
                arguments((FailableSupplier<String, IOException>) () -> "test"),
                arguments((FailableSupplier<String, IOException>) () -> null)
        );
    }

    private static Stream<Arguments> provideBooleanSupplier() {
        return Stream.of(
                arguments((FailableBooleanSupplier<IOException>) () -> true),
                arguments((FailableBooleanSupplier<IOException>) () -> false)
        );
    }

    private static Stream<Arguments> provideDoubleSupplier() {
        return Stream.of(
                arguments((FailableDoubleSupplier<IOException>) () -> 1.0),
                arguments((FailableDoubleSupplier<IOException>) () -> Double.NaN)
        );
    }

    private static Stream<Arguments> provideIntSupplier() {
        return Stream.of(
                arguments((FailableIntSupplier<IOException>) () -> 1),
                arguments((FailableIntSupplier<IOException>) () -> Integer.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideLongSupplier() {
        return Stream.of(
                arguments((FailableLongSupplier<IOException>) () -> 1L),
                arguments((FailableLongSupplier<IOException>) () -> Long.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideShortSupplier() {
        return Stream.of(
                arguments((FailableShortSupplier<IOException>) () -> (short) 1),
                arguments((FailableShortSupplier<IOException>) () -> Short.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideRunnable() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> {}),
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); })
        );
    }

    
    
    private static Stream<Arguments> provideRunnableWithResources() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0])
        );
    }

    private static Stream<Arguments> provideRunnableWithResourcesThrowsException() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> { throw new IOException(); },
                        new FailableRunnable[0])
        );
    }

    private static Stream<Arguments> provideRunnableWithResourcesThrowsExceptionNoErrorHandler() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        null,
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> { throw new IOException(); },
                        new FailableRunnable[0])
        );
    }

    // ---- added by coverage-guided refinement ----
    @ParameterizedTest
        @MethodSource("provideBiConsumerData")
        public void testBiConsumer(BiConsumer<String, Integer> biConsumer, String input1, Integer input2) {
            assertDoesNotThrow(() -> biConsumer.accept(input1, input2));
        }

    private static Stream<Arguments> provideBiConsumerData() {
            return Stream.of(
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", 1),
                arguments((BiConsumer<String, Integer>) (s, i) -> { throw new IOException("Expected exception"); }, "test", 1)
            );
        }

    @ParameterizedTest
        @MethodSource("provideBiFunctionData")
        public void testBiFunction(BiFunction<String, Integer, String> biFunction, String input1, Integer input2, String expected) {
            assertEquals(expected, biFunction.apply(input1, input2));
        }

    private static Stream<Arguments> provideBiFunctionData() {
            return Stream.of(
                arguments((BiFunction<String, Integer, String>) (s, i) -> s + i, "notest", 2, "notest2"),
                arguments((BiFunction<String, Integer, String>) (s, i) -> { throw new IOException("Expected exception"); }, "test", 1, null)
            );
        }

    @ParameterizedTest
        @MethodSource("provideBiPredicateData")
        public void testBiPredicate(BiPredicate<String, Integer> biPredicate, String input1, Integer input2, boolean expected) {
            assertEquals(expected, biPredicate.test(input1, input2));
        }

    private static Stream<Arguments> provideBiPredicateData() {
            return Stream.of(
                arguments((BiPredicate<String, Integer>) (s, i) -> true, "test", 1, true),
                arguments((BiPredicate<String, Integer>) (s, i) -> s.equals("test"), "test", 1, true)
            );
        }

    @ParameterizedTest
        @MethodSource("provideCallableData")
        public void testCallable(Callable<String> callable, String expected) {
            assertEquals(expected, callable.call());
        }

    private static Stream<Arguments> provideCallableData() {
            return Stream.of(
                arguments((Callable<String>) () -> "result", "result"),
                arguments((Callable<String>) () -> { throw new IOException("Expected exception"); }, null)
            );
        }

    @ParameterizedTest
        @MethodSource("provideConsumerData")
        public void testConsumer(Consumer<String> consumer, String input) {
            assertDoesNotThrow(() -> consumer.accept(input));
        }

    private static Stream<Arguments> provideConsumerData() {
            return Stream.of(
                arguments((Consumer<String>) s -> {}, "test"),
                arguments((Consumer<String>) s -> { throw new IOException("Expected exception"); }, "test")
            );
        }

    @ParameterizedTest
        @MethodSource("provideFunctionData")
        public void testFunction(Function<String, Integer> function, String input, int expected) {
            assertEquals(expected, function.apply(input));
        }

    private static Stream<Arguments> provideFunctionData() {
            return Stream.of(
                arguments((Function<String, Integer>) s -> 1, "test", 1),
                arguments((Function<String, Integer>) s -> { throw new IOException("Expected exception"); }, "test", -1)
            );
        }

    @ParameterizedTest
        @MethodSource("providePredicateData")
        public void testPredicate(Predicate<String> predicate, String input, boolean expected) {
            assertEquals(expected, predicate.test(input));
        }

    private static Stream<Arguments> providePredicateData() {
            return Stream.of(
                arguments((Predicate<String>) s -> true, "test", true),
                arguments((Predicate<String>) s -> s.equals("test"), "notest", false)
            );
        }

    @ParameterizedTest
        @MethodSource("provideRunnableData")
        public void testRunnable(Runnable runnable) {
            assertDoesNotThrow(runnable::run);
        }

    private static Stream<Arguments> provideRunnableData() {
            return Stream.of(
                arguments((Runnable) () -> {}),
                arguments((Runnable) () -> { throw new IOException("Expected exception"); })
            );
        }

    @ParameterizedTest
        @MethodSource("provideSupplierData")
        public void testSupplier(Supplier<String> supplier, String expected) {
            assertEquals(expected, supplier.get());
        }

    private static Stream<Arguments> provideSupplierData() {
            return Stream.of(
                arguments((Supplier<String>) () -> "result", "result"),
                arguments((Supplier<String>) () -> { throw new IOException("Expected exception"); }, null)
            );
        }
}
