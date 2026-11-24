package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

public class CompareToBuilder_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testBuild() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(Integer.valueOf(0), builder.build());
        builder.append(1, 2);
        assertEquals(Integer.valueOf(-1), builder.build());
    }

    @Test
    public void testReflectionCompare() {
        // Arrange
        class TestClass implements Comparable<TestClass> {
            private int value;
            public TestClass(int value) { this.value = value; }
            @Override
            public int compareTo(TestClass other) { return Integer.compare(this.value, other.value); }
        }

        // Act & Assert
        assertEquals(0, CompareToBuilder.reflectionCompare(new TestClass(1), new TestClass(1)));
        assertEquals(-1, CompareToBuilder.reflectionCompare(new TestClass(0), new TestClass(1)));
        assertEquals(1, CompareToBuilder.reflectionCompare(new TestClass(1), new TestClass(0)));
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(new TestClass(1), "string"));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new TestClass(1)));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new TestClass(1), null));
    }

    
    
    
    
    @Test
        void testReflectionCompareWithEmptyCollectionExcludeFields() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", Collections.emptyList()));
        }

    @Test
        void testReflectionCompareWithNonEmptyCollectionExcludeFields() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", Arrays.asList("field")));
        }

    @Test
        void testAppendBooleanSameValues() {
            CompareToBuilder builder = new CompareToBuilder().append(true, true);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(true, false);
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySameLengthAndContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new boolean[]{true}, new boolean[]{true});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendBooleanArrayDifferentLengths() {
            CompareToBuilder builder = new CompareToBuilder().append(new boolean[]{true}, new boolean[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendBooleanArraySameLengthDifferentContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new boolean[]{true}, new boolean[]{false});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteSameValues() {
            CompareToBuilder builder = new CompareToBuilder().append((byte) 1, (byte) 1);
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySameLengthAndContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{1});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendByteArrayDifferentLengths() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendByteArraySameLengthDifferentContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new byte[]{1}, new byte[]{2});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharSameValues() {
            CompareToBuilder builder = new CompareToBuilder().append('a', 'a');
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySameLengthAndContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, new char[]{'a'});
            assertEquals(0, builder.toComparison());
        }

    @Test
        void testAppendCharArrayDifferentLengths() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, new char[]{});
            assertEquals(1, builder.toComparison());
        }

    @Test
        void testAppendCharArraySameLengthDifferentContent() {
            CompareToBuilder builder = new CompareToBuilder().append(new char[]{'a'}, new char[]{'b'});
            assertEquals(-1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompareWithNullExcludeFields() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", false, null));
        }

    @Test
        public void testAppendBooleanFalseTrue() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(false, true));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendBooleanArrayNulls() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append((boolean[]) null, (boolean[]) null));
        }

    @Test
        public void testAppendBooleanArrayLeftNull() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(null, new boolean[]{true}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendBooleanArrayRightNull() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new boolean[]{true}, null));
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendBooleanArrayDifferentLengths_2() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new boolean[]{true}, new boolean[]{}));
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(1.0, 2.0));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArrayNulls() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append((double[]) null, (double[]) null));
        }

    @Test
        public void testAppendDoubleArrayLeftNull() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(null, new double[]{1.0}));
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArrayRightNull() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new double[]{1.0}, null));
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppendDoubleArrayDifferentLengths() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new double[]{1.0}, new double[]{}));
            assertEquals(1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompareWithNullExcludeFields_2() {
            assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object(), false, null));
        }

    @Test
        public void testAppendBooleanAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(true, true);
            assertEquals(builder, builder.append(true, false));
        }

    @Test
        public void testAppendBooleanArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new boolean[]{true}, new boolean[]{true});
            assertEquals(builder, builder.append(new boolean[]{true}, new boolean[]{false}));
        }

    @Test
        public void testAppendByteAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 1);
            assertEquals(builder, builder.append((byte) 1, (byte) 2));
        }

    @Test
        public void testAppendByteArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new byte[]{1}, new byte[]{1});
            assertEquals(builder, builder.append(new byte[]{1}, new byte[]{2}));
        }

    @Test
        public void testAppendCharAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'a');
            assertEquals(builder, builder.append('a', 'b'));
        }

    @Test
        public void testAppendCharArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new char[]{'a'}, new char[]{'a'});
            assertEquals(builder, builder.append(new char[]{'a'}, new char[]{'b'}));
        }

    @Test
        public void testAppendDoubleAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 1.0);
            assertEquals(builder, builder.append(1.0, 2.0));
        }

    @Test
        public void testAppendDoubleArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new double[]{1.0}, new double[]{1.0});
            assertEquals(builder, builder.append(new double[]{1.0}, new double[]{2.0}));
        }

    @Test
        public void testAppendFloatAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 1.0f);
            assertEquals(builder, builder.append(1.0f, 2.0f));
        }

    @Test
        public void testAppendFloatArrayAlreadyCompared() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f}, new float[]{1.0f});
            assertEquals(builder, builder.append(new float[]{1.0f}, new float[]{2.0f}));
        }

    
    @Test
        public void testAppendFloatArrayNulls() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((float[]) null, (float[]) null);
            assertEquals(0, builder.toComparison());
        }

    @Test
        public void testAppendFloatArrayLeftNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, new float[]{1.0f});
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendFloatArrayRightNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(new float[]{1.0f}, null);
            assertEquals(1, builder.toComparison());
        }
}
