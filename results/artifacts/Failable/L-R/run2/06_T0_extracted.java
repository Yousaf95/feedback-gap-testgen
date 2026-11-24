package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

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
        FailableConsumer<String, IOException> consumer = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.accept(consumer, "test"));
        assertTrue(exception.getCause() instanceof IOException);
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
        FailableFunction<String, Integer, IOException> function = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.apply(function, "test"));
        assertTrue(exception.getCause() instanceof IOException);
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
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.get(supplier));
        assertTrue(exception.getCause() instanceof IOException);
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
        FailableRunnable<IOException> runnable = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.run(runnable));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testAsConsumerNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableConsumer<String, IOException> failableConsumer = sb::append;
        Consumer<String> consumer = Failable.asConsumer(failableConsumer);

        // Act
        consumer.accept("test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAsFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> failableFunction = String::length;
        Function<String, Integer> function = Failable.asFunction(failableFunction);

        // Act
        int result = function.apply("test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testAsStreamNormalCase() {
        // Arrange
        Collection<Integer> collection = List.of(1, 2, 3);
        FailableStream<Integer> failableStream = Failable.stream(collection);

        // Act
        int sum = failableStream.mapToInt(Integer::intValue).sum();

        // Assert
        assertEquals(6, sum);
    }

    @Test
    void testTryWithResourcesNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> action = () -> sb.append("test");
        FailableRunnable<IOException>[] resources = new FailableRunnable[0];

        // Act
        Failable.tryWithResources(action, resources);

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testTryWithResourcesThrowsException() {
        // Arrange
        FailableRunnable<IOException> action = () -> { throw new IOException("Expected"); };
        FailableConsumer<Throwable, IOException> errorHandler = t -> {};
        FailableRunnable<IOException>[] resources = new FailableRunnable[0];

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.tryWithResources(action, errorHandler, resources));
        assertTrue(exception.getCause() instanceof IOException);
    }
}
