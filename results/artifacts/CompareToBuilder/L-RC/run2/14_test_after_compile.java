package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // ---- added by coverage-guided refinement ----
    @Test
        void testReflectionCompareWithNullExcludeFields() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", false, null));
        }

    @Test
        void testReflectionCompareWithEmptyExcludeFieldsArray() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", false, new String[0]));
        }

    @Test
        void testReflectionCompareWithNonEmptyExcludeFieldsArray() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", false, new String[]{"field"}));
        }

    @Test
        void testReflectionCompareWithNullCollectionExcludeFields() {
            assertEquals(0, CompareToBuilder.reflectionCompare("test", "test", (Collection<String>) null));
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
}
