package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectUtils_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testFirstNonNull() {
        String result = ObjectUtils.firstNonNull(null, "default");
        assertEquals("default", result);

        result = ObjectUtils.firstNonNull("value", null);
        assertEquals("value", result);

        result = ObjectUtils.firstNonNull(null, null, "default");
        assertEquals("default", result);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testFirstNonNullWithNulls() {
            String result = ObjectUtils.firstNonNull(null, null, "default");
            assertEquals("default", result);
        }

    @Test
        public void testFirstNonNullWithNonEmptyArray() {
            String result = ObjectUtils.firstNonNull("first", "second", "third");
            assertEquals("first", result);
        }

    @Test
        public void testGetFirstNonNullWithNullSuppliers() {
            String result = ObjectUtils.getFirstNonNull(() -> null, () -> "default", () -> null);
            assertEquals("default", result);
        }

    @Test
        public void testGetFirstNonNullWithNonEmptySuppliers() {
            String result = ObjectUtils.getFirstNonNull(() -> null, () -> null, () -> "third");
            assertEquals("third", result);
        }

    @Test
        public void testGetIfNullWithNonNullObject() {
            String result = ObjectUtils.getIfNull("value", () -> "default");
            assertEquals("value", result);
        }

    @Test
        public void testGetIfNullWithNullObjectAndNonEmptySupplier() {
            String result = ObjectUtils.getIfNull(null, () -> "default");
            assertEquals("default", result);
        }

    @Test
        public void testGetIfNullWithNullObjectAndNullSupplier() {
            Supplier<String> supplier = null;
            String result = ObjectUtils.getIfNull(null, supplier);
            assertNull(result);
        }
}
