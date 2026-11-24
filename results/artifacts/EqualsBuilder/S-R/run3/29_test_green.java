package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EqualsBuilder_GenTest {

    private EqualsBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendSuper() {
        builder.appendSuper(true);
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReset() {
        builder.append(true, false);
        assertFalse(builder.isEquals());
        builder.reset();
        assertTrue(builder.isEquals());
    }

    
    
    
    
    
    
    
    
    
    }
