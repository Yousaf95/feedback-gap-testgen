package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;

public class HashCodeBuilder_GenTest {

    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(17 * 37 + obj.hashCode(), builder.toHashCode());
    }

    @Test
    public void testAppendNull() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object) null);
        assertEquals(17 * 37, builder.toHashCode());
    }

    
    
    
    
    
    
    
    
    @Test
    public void testAppendArrayNull() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object[]) null);
        assertEquals(17 * 37, builder.toHashCode());
    }

    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        int superHashCode = 42;
        builder.appendSuper(superHashCode);
        assertEquals(17 * 37 + superHashCode, builder.toHashCode());
    }

    @Test
    public void testBuild() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Integer hashCode = builder.build();
        assertNotNull(hashCode);
        assertEquals(builder.toHashCode(), hashCode.intValue());
    }

    @Test
    public void testEquals() {
        HashCodeBuilder builder1 = new HashCodeBuilder().append(42);
        HashCodeBuilder builder2 = new HashCodeBuilder().append(42);
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(builder.toHashCode(), builder.hashCode());
    }

    // ---- added by coverage-guided refinement ----
    private Object testObject1;

    private Object testObject2;

    private Set<IDKey> registry;

    @BeforeEach
        public void setUp() {
            testObject1 = new Object();
            testObject2 = new Object();
            registry = new HashSet<>();
            REGISTRY.set(registry);
        }

    @Test
        public void testGetRegistry() {
            assertNotNull(getRegistry());
        }

    @Test
        public void testIsRegistered() {
            assertFalse(isRegistered(testObject1));
            register(testObject1);
            assertTrue(isRegistered(testObject1));
        }

    @Test
        public void testReflectionAppend() {
            HashCodeBuilder builder = new HashCodeBuilder();
            reflectionAppend(testObject1, testObject1.getClass(), builder, false, null);
            assertNotEquals(0, builder.toHashCode());
        }

    @Test
        public void testReflectionHashCode() {
            int hashCode = reflectionHashCode(testObject1, false);
            assertNotEquals(0, hashCode);
        }

    @Test
        public void testRegister() {
            register(testObject1);
            assertTrue(registry.contains(new IDKey(testObject1)));
        }

    @Test
        public void testUnregister() {
            register(testObject1);
            unregister(testObject1);
            assertFalse(registry.contains(new IDKey(testObject1)));
        }

    @Test
        public void testHashCodeBuilderConstructor() {
            HashCodeBuilder builder = new HashCodeBuilder(17, 37);
            assertEquals(17, builder.iTotal);
            assertEquals(37, builder.iConstant);
        }
}
