package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SystemUtils_GenTest {

    private Properties mockProperties;

    
    
    
    
    
    @Test
    public void testGetEnvironmentVariable_defaultValue() {
        assertNull(SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "default"));
    }

    
    
    
    
    
    
    @Test
    public void testGetUserName_defaultValue() {
        assertNull(SystemUtils.getUserName("default"));
    }

    
    }
