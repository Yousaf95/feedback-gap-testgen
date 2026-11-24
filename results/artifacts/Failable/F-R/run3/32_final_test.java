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
}
