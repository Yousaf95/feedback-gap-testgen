package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaVersion_GenTest {

    
    
    
    @Test
    public void testToStringMethod() {
        assertEquals("0.9", JavaVersion.JAVA_0_9.toString());
        assertEquals("21", JavaVersion.JAVA_21.toString());
        assertTrue(JavaVersion.JAVA_RECENT.toString().length() > 0);
    }

    
    
    }
