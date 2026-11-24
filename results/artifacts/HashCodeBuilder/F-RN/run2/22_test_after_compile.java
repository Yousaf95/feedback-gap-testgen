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

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendObjectWithCustomConstants() {
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.append("test");
            assertEquals(62, builder.toHashCode());
        }

    @Test
        public void testAppendNullWithCustomConstants() {
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.append((Object) null);
            assertEquals(5, builder.toHashCode());
        }

    @Test
        public void testAppendArrayNullWithCustomConstants() {
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.append((String[]) null);
            assertEquals(5, builder.toHashCode());
        }

    @Test
        public void testAppendSuperWithCustomConstants() {
            class Parent {
                @Override
                public int hashCode() {
                    return 42;
                }
            }
            class Child extends Parent {}
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.appendSuper(new Child().hashCode());
            assertEquals(203, builder.toHashCode());
        }

    @Test
        public void testBuildWithCustomConstants() {
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.append("test");
            assertEquals(Integer.valueOf(62), builder.build());
        }

    @Test
        public void testEqualsWithDifferentObjectsAndCustomConstants() {
            HashCodeBuilder builder1 = new HashCodeBuilder(5, 11);
            builder1.append("test");
            HashCodeBuilder builder2 = new HashCodeBuilder(5, 11);
            builder2.append("test");
            assertTrue(builder1.equals(builder2));
        }

    @Test
        public void testHashCodeWithDifferentObjectsAndCustomConstants() {
            HashCodeBuilder builder1 = new HashCodeBuilder(5, 11);
            builder1.append("test");
            HashCodeBuilder builder2 = new HashCodeBuilder(5, 11);
            builder2.append("test");
            assertEquals(builder1.toHashCode(), builder2.toHashCode());
        }

    @Test
        public void testAppendArrayWithEmptyArrayAndCustomConstants() {
            HashCodeBuilder builder = new HashCodeBuilder(5, 11);
            builder.append(new String[0]);
            assertEquals(5, builder.toHashCode());
        }
}
