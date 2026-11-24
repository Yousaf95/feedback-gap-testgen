package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationUtils_GenTest {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestAnnotation {
        String value();
        int number() default 42;
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnotherTestAnnotation {
        String name();
    }

    @Test
    void testEquals_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertTrue(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_nullAnnotations() {
        // Arrange
        TestAnnotation annotation = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation, null));
        assertFalse(AnnotationUtils.equals(null, annotation));
        assertTrue(AnnotationUtils.equals(null, null));
    }

    @Test
    void testEquals_differentValues() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "different";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testHashCode_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertNotEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.hashCode(null));
    }

    @ParameterizedTest
    @ValueSource(classes = {String.class, int.class, Class.class, TestAnnotation.class})
    void testIsValidAnnotationMemberType_validTypes(Class<?> type) {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    
    
    @Test
    void testToString_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendDetailWithAnnotationValue() {
            TestAnnotation annotation = new TestAnnotation() {
                                        @Override
                public Class<?> clazz() { return Object.class; }
                @Override
                public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override
                public TestAnnotation nested() {
                    return new TestAnnotation() {
                                                                @Override
                        public Class<?> clazz() { return Object.class; }
                        @Override
                        public TestEnum anEnum() { return TestEnum.VALUE1; }
                        @Override
                        public TestAnnotation nested() { return this; }
                        @Override
                        public TestAnnotation[] array() { return new TestAnnotation[0]; }
                    };
                }
                @Override
                public TestAnnotation[] array() { return new TestAnnotation[0]; }
            };
    
            AnnotationUtils.toString(annotation);
        }

    @Test
        void testGetShortClassNameWithAnnotationInterface() {
            String result = AnnotationUtils.toString(TestAnnotation.class.getAnnotations()[0]);
            assertTrue(result.contains("@org.apache.commons.lang3.AnnotationUtils_GenTest$TestAnnotation"));
        }

    @Test
        void testAnnotationArrayMemberEqualsDifferentLengths() {
            TestAnnotation[] a1 = new TestAnnotation[0];
            TestAnnotation[] a2 = new TestAnnotation[1];
            assertFalse(AnnotationUtils.equals(a1, a2));
        }

    @Test
        void testAnnotationArrayMemberEqualsDifferentAnnotations() {
            TestAnnotation[] a1 = { new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            }};
    
            TestAnnotation[] a2 = { new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            }};
    
            assertFalse(AnnotationUtils.equals(a1, a2));
        }

    @Test
        void testArrayMemberEqualsWithAnnotations() {
            TestAnnotation[] a1 = { new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            }};
    
            TestAnnotation[] a2 = { new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            }};
    
            assertTrue(AnnotationUtils.equals(a1, a2));
        }

    @Test
        void testArrayMemberEqualsWithBytes() {
            byte[] b1 = { 1, 2, 3 };
            byte[] b2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.equals(b1, b2));
        }

    @Test
        void testArrayMemberEqualsWithShorts() {
            short[] s1 = { 1, 2, 3 };
            short[] s2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.equals(s1, s2));
        }

    @Test
        void testArrayMemberEqualsWithInts() {
            int[] i1 = { 1, 2, 3 };
            int[] i2 = { 1, 2, 3 };
            assertTrue(AnnotationUtils.equals(i1, i2));
        }

    @Test
        void testArrayMemberEqualsWithChars() {
            char[] c1 = { 'a', 'b', 'c' };
            char[] c2 = { 'a', 'b', 'c' };
            assertTrue(AnnotationUtils.equals(c1, c2));
        }

    @Test
        void testArrayMemberEqualsWithLongs() {
            long[] l1 = { 1L, 2L, 3L };
            long[] l2 = { 1L, 2L, 3L };
            assertTrue(AnnotationUtils.equals(l1, l2));
        }

    @Test
        void testArrayMemberEqualsWithFloats() {
            float[] f1 = { 1.0f, 2.0f, 3.0f };
            float[] f2 = { 1.0f, 2.0f, 3.0f };
            assertTrue(AnnotationUtils.equals(f1, f2));
        }

    @Test
        void testArrayMemberEqualsWithDoubles() {
            double[] d1 = { 1.0, 2.0, 3.0 };
            double[] d2 = { 1.0, 2.0, 3.0 };
            assertTrue(AnnotationUtils.equals(d1, d2));
        }

    @Test
        void testArrayMemberEqualsWithBooleans() {
            boolean[] b1 = { true, false, true };
            boolean[] b2 = { true, false, true };
            assertTrue(AnnotationUtils.equals(b1, b2));
        }

    @Test
        void testArrayMemberEqualsWithObjects() {
            Object[] o1 = { "a", "b", "c" };
            Object[] o2 = { "a", "b", "c" };
            assertTrue(AnnotationUtils.equals(o1, o2));
        }

    @Test
        void testArrayMemberHashWithBytes() {
            byte[] b = { 1, 2, 3 };
            AnnotationUtils.hashCode(b);
        }

    @Test
        void testArrayMemberHashWithShorts() {
            short[] s = { 1, 2, 3 };
            AnnotationUtils.hashCode(s);
        }

    @Test
        void testArrayMemberHashWithInts() {
            int[] i = { 1, 2, 3 };
            AnnotationUtils.hashCode(i);
        }

    @Test
        void testArrayMemberHashWithChars() {
            char[] c = { 'a', 'b', 'c' };
            AnnotationUtils.hashCode(c);
        }

    @Test
        void testArrayMemberHashWithLongs() {
            long[] l = { 1L, 2L, 3L };
            AnnotationUtils.hashCode(l);
        }

    @Test
        void testArrayMemberHashWithFloats() {
            float[] f = { 1.0f, 2.0f, 3.0f };
            AnnotationUtils.hashCode(f);
        }

    @Test
        void testArrayMemberHashWithDoubles() {
            double[] d = { 1.0, 2.0, 3.0 };
            AnnotationUtils.hashCode(d);
        }

    @Test
        void testArrayMemberHashWithBooleans() {
            boolean[] b = { true, false, true };
            AnnotationUtils.hashCode(b);
        }

    @Test
        void testArrayMemberHashWithObjects() {
            Object[] o = { "a", "b", "c" };
            AnnotationUtils.hashCode(o);
        }

    @Test
        void testEqualsReflectiveOperationException() {
            TestAnnotation a1 = new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            };
    
            TestAnnotation a2 = new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            };
    
            assertFalse(AnnotationUtils.equals(a1, a2));
        }

    @Test
        void testHashCodeWithNullValue() {
            TestAnnotation annotation = new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return null; } // This will cause the exception
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            };
    
            assertThrows(IllegalStateException.class, () -> AnnotationUtils.hashCode(annotation));
        }

    @Test
        void testHashMemberWithArrayValue() {
            int result = AnnotationUtils.hashMember("testField", new String[]{"a", "b", "c"});
            assertEquals(1368495207, result);
        }

    @Test
        void testHashMemberWithAnnotationValue() {
            TestAnnotation annotation = new TestAnnotation() {
                                        @Override public Class<?> clazz() { return Object.class; }
                @Override public TestEnum anEnum() { return TestEnum.VALUE1; }
                @Override public TestAnnotation nested() { return this; }
                @Override public TestAnnotation[] array() { return new TestAnnotation[0]; }
            };
    
            int result = AnnotationUtils.hashMember("testField", annotation);
            assertEquals(1368495207, result);
        }
}
