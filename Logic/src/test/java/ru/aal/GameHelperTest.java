package ru.aal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GameHelperTest {

    GameHelper helper = new GameHelper();

    @ParameterizedTest
    @MethodSource("moveAndMergeEqualParameters")
    void moveAndMergeEqual(List<Integer> expected, List<Integer> value) {
        assertEquals(expected, helper.moveAndMergeEqual(value));
    }

    private static Stream<Arguments> moveAndMergeEqualParameters() {
        return Stream.of(
                arguments(Arrays.asList(2, 8, 16, null), Arrays.asList(2, 4, 4, 16)),
                arguments(Arrays.asList(2, null, null, null), Arrays.asList(null, null, null, 2)),
                arguments(Arrays.asList(4, 4, null, null), Arrays.asList(2, 2, 2, 2)),
                arguments(Arrays.asList(4, 8, null, null), Arrays.asList(2, 2, 4, 4)),
                arguments(Arrays.asList(2, 4, 32, null), Arrays.asList(2, 4, 16, 16)),
                arguments(Arrays.asList(2, 4, 32, 64), Arrays.asList(2, 4, 32, 64)),
                arguments(Arrays.asList(128, null, null, null), Arrays.asList(null, 64, null, 64))
        );
    }
}