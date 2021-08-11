package ru.aal;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SquareBoardTest {

    private static Stream<Arguments> parametersTestAvailableSpace() {
        return Stream.of(
                arguments(
                        Arrays.asList(2, 2, null, null,
                                null, null, null, null,
                                null, null, null, null,
                                null, null, null, null
                        ),
                        false
                ),
                arguments(
                        Arrays.asList(2, 2, 4, 8,
                                64, 8, 64, 16,
                                16, 128, 256, 8,
                                64, 4, 32, 512
                        ),
                        true
                ),
                arguments(
                        Arrays.asList(2, 8, 4, 8,
                                8, 16, 64, 16,
                                16, 128, 256, 8,
                                64, 4, 32, 512
                        ),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("parametersTestAvailableSpace")
    void testAvailableSpace(List<Integer> initList, boolean expected) {
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initList);
        assertEquals(expected, board.availableSpace().isEmpty());
    }
}