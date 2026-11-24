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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testFirstNonNullWithEmptyArray() {
            assertNull(ObjectUtils.firstNonNull(new String[]{}));
        }

    @Test
        public void testGetFirstNonNullWithNullSuppliers_2() {
            assertNull(ObjectUtils.getFirstNonNull((Supplier<String>) null, (Supplier<String>) null));
        }

    @Test
        public void testGetIfNullWithNonEmptySupplier() {
            assertEquals("default", ObjectUtils.getIfNull(null, () -> "default"));
        }

    @Test
        public void testGetIfNullWithNullObjectAndNullSupplier_2() {
            assertNull(ObjectUtils.getIfNull(null, (Supplier<String>) null));
        }

    @Test
        public void testHashCodeHexForNull() {
            assertEquals("0", ObjectUtils.hashCodeHex(null));
        }

    @Test
        public void testIdentityToStringForNull() {
            assertNull(ObjectUtils.identityToString((Object) null));
        }

    @Test
        public void testIsArrayWithNonArrayObject() {
            assertFalse(ObjectUtils.isArray(new Integer(1)));
        }

    @Test
        public void testIsEmptyWithEmptyCollection() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
        }

    @Test
        public void testIsNotEmptyWithEmptyCollection() {
            assertFalse(ObjectUtils.isNotEmpty(Collections.emptyList()));
        }

    @Test
        public void testMaxWithNullValues() {
            assertNull(ObjectUtils.max((Integer) null, 2));
        }

    @Test
        public void testMinWithNullValues() {
            assertNull(ObjectUtils.min(1, (Integer) null));
        }

    @Test
        public void testModeWithEmptyArray() {
            assertNull(ObjectUtils.mode());
        }
}
