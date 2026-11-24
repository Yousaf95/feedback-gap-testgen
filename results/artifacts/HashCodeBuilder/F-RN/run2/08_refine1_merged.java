package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    @Test
        public void testAppendObjectWithNull() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((Object) null);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testAppendArrayWithNull() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((Object[]) null);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testAppendSuperWithZero() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.appendSuper(0);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testBuildWithInitialValueAndMultiplier() {
            HashCodeBuilder builder = new HashCodeBuilder(53, 41);
            builder.append("test");
            assertEquals(289, builder.build().intValue());
        }

    @Test
        public void testEqualsWithDifferentObjects() {
            HashCodeBuilder builder1 = new HashCodeBuilder();
            builder1.append("test");
            HashCodeBuilder builder2 = new HashCodeBuilder();
            builder2.append("test");
            assertTrue(builder1.equals(builder2));
        }

    @Test
        public void testHashCodeWithDifferentObjects() {
            HashCodeBuilder builder1 = new HashCodeBuilder();
            builder1.append("test");
            HashCodeBuilder builder2 = new HashCodeBuilder();
            builder2.append("test");
            assertEquals(builder1.hashCode(), builder2.hashCode());
        }

    @Test
        public void testAppendArrayWithEmptyArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new int[0]);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testAppendArrayWithNonEmptyArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new int[]{1, 2, 3});
            assertEquals(65, builder.toHashCode());
        }

    @Test
        public void testAppendObjectWithSameObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            String str = "test";
            builder.append(str);
            builder.append(str);
            assertEquals(108, builder.toHashCode());
        }
}
