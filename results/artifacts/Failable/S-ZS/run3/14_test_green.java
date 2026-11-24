package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Failable_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static Stream<Arguments> provideBiConsumerData() {
        return Stream.of(
            arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", 123),
            arguments((BiConsumer<List<String>, String>) (list, s) -> list.add(s), Arrays.asList(), "item")
        );
    }

    private static Stream<Arguments> provideConsumerData() {
        return Stream.of(
            arguments((Consumer<String>) s -> {}, "test"),
            arguments((Consumer<List<String>>) list -> list.clear(), Arrays.asList("item"))
        );
    }

    private static Stream<Arguments> provideDoubleConsumerData() {
        return Stream.of(
            arguments((FailableDoubleConsumer<IOException>) d -> {}, 123.45)
        );
    }

    private static Stream<Arguments> provideIntConsumerData() {
        return Stream.of(
            arguments((FailableIntConsumer<IOException>) i -> {}, 123)
        );
    }

    private static Stream<Arguments> provideLongConsumerData() {
        return Stream.of(
            arguments((FailableLongConsumer<IOException>) l -> {}, 123L)
        );
    }

    
    
    private static Stream<Arguments> provideDoubleBinaryOperatorData() {
        return Stream.of(
            arguments((FailableDoubleBinaryOperator<IOException>) (d1, d2) -> d1 + d2, 1.0, 2.0),
            arguments((FailableDoubleBinaryOperator<IOException>) (d1, d2) -> d1 * d2, 3.0, 4.0)
        );
    }

    private static Stream<Arguments> provideBooleanSupplierData() {
        return Stream.of(
            arguments((FailableBooleanSupplier<IOException>) () -> true),
            arguments((FailableBooleanSupplier<IOException>) () -> false)
        );
    }

    private static Stream<Arguments> provideDoubleSupplierData() {
        return Stream.of(
            arguments((FailableDoubleSupplier<IOException>) () -> 1.0),
            arguments((FailableDoubleSupplier<IOException>) () -> 2.0)
        );
    }

    private static Stream<Arguments> provideIntSupplierData() {
        return Stream.of(
            arguments((FailableIntSupplier<IOException>) () -> 1),
            arguments((FailableIntSupplier<IOException>) () -> 2)
        );
    }

    private static Stream<Arguments> provideLongSupplierData() {
        return Stream.of(
            arguments((FailableLongSupplier<IOException>) () -> 1L),
            arguments((FailableLongSupplier<IOException>) () -> 2L)
        );
    }

    private static Stream<Arguments> provideShortSupplierData() {
        return Stream.of(
            arguments((FailableShortSupplier<IOException>) () -> (short) 1),
            arguments((FailableShortSupplier<IOException>) () -> (short) 2)
        );
    }

    
    
    }
