package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class ObjectUtils_GenTest {

    
    
    
    
    
    
    @Test
    public void testCompare() {
        assertEquals(0, ObjectUtils.compare(1, 1));
        assertTrue(ObjectUtils.compare(2, 1) > 0);
        assertTrue(ObjectUtils.compare(1, 2) < 0);

        String str1 = "a";
        String str2 = "b";
        assertTrue(ObjectUtils.compare(str1, str2) < 0);
        assertTrue(ObjectUtils.compare(str2, str1) > 0);
    }

    
    @Test
    public void testDefaultIfNull() {
        Integer defaultValue = 42;
        assertEquals(defaultValue, ObjectUtils.defaultIfNull(null, defaultValue));
        assertEquals(1, ObjectUtils.defaultIfNull(1, defaultValue));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
        public void testIsEmpty() {
            assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
            assertFalse(ObjectUtils.isEmpty(Collections.singletonList(1)));
        }

    @Test
        public void testIsNotEmpty() {
            assertTrue(ObjectUtils.isNotEmpty(Collections.singletonList(1)));
            assertFalse(ObjectUtils.isNotEmpty(Collections.emptyList()));
        }

    
    
    }
