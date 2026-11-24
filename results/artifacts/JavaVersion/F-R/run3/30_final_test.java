package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class JavaVersion_GenTest {

    
    
    @Test
    public void testToString() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
    }

    
    
    }
