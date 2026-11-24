package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Range_GenTest {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static Stream<Arguments> provideRangeCreationParameters() {
        return Stream.of(
            arguments(1, 5, null),
            arguments("a", "z", Comparator.naturalOrder()),
            arguments(new Integer[]{3}, new Integer[]{7}, Comparator.comparingInt(Integer::intValue))
        );
    }
}
