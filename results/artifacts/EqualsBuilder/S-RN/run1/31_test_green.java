package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    public void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendSuper() {
        assertTrue(equalsBuilder.appendSuper(true).isEquals());
        assertFalse(equalsBuilder.appendSuper(false).isEquals());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            equalsBuilder.append((Object) null, (Object) null);
            assertTrue(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendDifferentTypes() {
            equalsBuilder.append(123, "123");
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendArrayNull() {
            equalsBuilder.append((Object[]) null, (Object[]) null);
            assertTrue(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendArrayDifferentLengths() {
            equalsBuilder.append(new Integer[]{1, 2}, new Integer[]{1, 2, 3});
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendArrayDifferentValues() {
            equalsBuilder.append(new Integer[]{1, 2}, new Integer[]{1, 3});
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendCollectionNull() {
            equalsBuilder.append((List<?>) null, (List<?>) null);
            assertTrue(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendCollectionDifferentSize() {
            equalsBuilder.append(List.of(1, 2), List.of(1, 2, 3));
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendCollectionDifferentValues() {
            equalsBuilder.append(List.of(1, 2), List.of(1, 3));
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendSetNull() {
            equalsBuilder.append((Set<?>) null, (Set<?>) null);
            assertTrue(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendSetDifferentSize() {
            equalsBuilder.append(Set.of(1, 2), Set.of(1, 2, 3));
            assertFalse(equalsBuilder.isEquals());
        }

    @Test
        public void testAppendSetDifferentValues() {
            equalsBuilder.append(Set.of(1, 2), Set.of(1, 3));
            assertFalse(equalsBuilder.isEquals());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendMapNull() {
            Map<String, String> map1 = null;
            Map<String, String> map2 = new HashMap<>();
            assertFalse(new EqualsBuilder().append(map1, map2).isEquals());
        }

    @Test
        public void testAppendMapDifferentSize() {
            Map<String, String> map1 = Map.of("key1", "value1");
            Map<String, String> map2 = Map.of("key1", "value1", "key2", "value2");
            assertFalse(new EqualsBuilder().append(map1, map2).isEquals());
        }

    @Test
        public void testAppendMapDifferentValues() {
            Map<String, String> map1 = Map.of("key1", "value1");
            Map<String, String> map2 = Map.of("key1", "value2");
            assertFalse(new EqualsBuilder().append(map1, map2).isEquals());
        }

    @Test
        public void testAppendMapSameValues() {
            Map<String, String> map1 = Map.of("key1", "value1");
            Map<String, String> map2 = Map.of("key1", "value1");
            assertTrue(new EqualsBuilder().append(map1, map2).isEquals());
        }

    
    
    
    
    
    
    
    
    
    
    
    
    }
