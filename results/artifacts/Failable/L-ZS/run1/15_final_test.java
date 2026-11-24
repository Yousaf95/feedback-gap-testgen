package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Failable_GenTest {

    @Test
    void testAcceptConsumerNormalCase() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> System.out.println(s);

        // Act & Assert
        assertDoesNotThrow(() -> Failable.accept(consumer, "test"));
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
    void testGetSupplierNormalCase() {
        // Arrange
        FailableSupplier<String, IOException> supplier = () -> "test";

        // Act
        String result = Failable.get(supplier);

        // Assert
        assertEquals("test", result);
    }

    
    @Test
    void testRunRunnableNormalCase() {
        // Arrange
        FailableRunnable<IOException> runnable = () -> System.out.println("Running");

        // Act & Assert
        assertDoesNotThrow(() -> Failable.run(runnable));
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
    void testAsRunnableNormalCase() {
        // Arrange
        FailableRunnable<IOException> failableRunnable = () -> System.out.println("Running");
        Runnable runnable = Failable.asRunnable(failableRunnable);

        // Act & Assert
        assertDoesNotThrow(runnable::run);
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

    
    }
