package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        java.util.function.Consumer<String> consumer = Failable.asConsumer(failableConsumer);

        // Act
        consumer.accept("test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAsFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> failableFunction = String::length;
        java.util.function.Function<String, Integer> function = Failable.asFunction(failableFunction);

        // Act
        int result = function.apply("test");

        // Assert
        assertEquals(4, result);
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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testApplyAsDoubleNormalCase() {
            double result = Failable.applyAsDouble((left, right) -> left + right, 2.5, 3.5);
            assertEquals(6.0, result);
        }

    @Test
        public void testApplyAsDoubleThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.applyAsDouble((left, right) -> { throw new IOException("test"); }, 2.5, 3.5)
            );
        }

    @Test
        public void testGetAsBooleanNormalCase() {
            boolean result = Failable.getAsBoolean(() -> true);
            assertEquals(true, result);
        }

    @Test
        public void testGetAsBooleanThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.getAsBoolean(() -> { throw new IOException("test"); })
            );
        }

    @Test
        public void testGetAsIntNormalCase() {
            int result = Failable.getAsInt(() -> 42);
            assertEquals(42, result);
        }

    @Test
        public void testGetAsIntThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.getAsInt(() -> { throw new IOException("test"); })
            );
        }

    @Test
        public void testGetAsLongNormalCase() {
            long result = Failable.getAsLong(() -> 123456789L);
            assertEquals(123456789L, result);
        }

    @Test
        public void testGetAsLongThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.getAsLong(() -> { throw new IOException("test"); })
            );
        }

    @Test
        public void testGetAsShortNormalCase() {
            short result = Failable.getAsShort(() -> (short) 123);
            assertEquals(123, result);
        }

    @Test
        public void testGetAsShortThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.getAsShort(() -> { throw new IOException("test"); })
            );
        }

    @Test
        public void testCallNormalCase() {
            String result = Failable.call(() -> "Hello");
            assertEquals("Hello", result);
        }

    @Test
        public void testCallThrowsException() {
            assertThrows(RuntimeException.class, () -> 
                Failable.call(() -> { throw new IOException("test"); })
            );
        }
}
