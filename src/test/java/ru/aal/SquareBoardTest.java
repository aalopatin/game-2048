package ru.aal;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class SquareBoardTest {

    List<Key> keyList;
    List<Integer> initialValues;

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

    private static Stream<Arguments> parametersTestFillBoard() {
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

    private static Stream<Arguments> parametersTestHasValue() {
        return Stream.of(
                arguments(
                        Arrays.asList(2, 2, null, null,
                                null, null, null, null,
                                null, null, null, null,
                                null, null, null, null
                        ),
                        2
                ),
                arguments(
                        Arrays.asList(2, 2, 4, 8,
                                64, 8, 64, 16,
                                16, 128, 256, 8,
                                64, 4, 32, 512
                        ),
                        256
                ),
                arguments(
                        Arrays.asList(2, 8, 4, 8,
                                8, 16, 64, 16,
                                16, 128, 256, 8,
                                64, 4, 32, 512
                        ),
                        512
                )
        );
    }


    @BeforeEach
    public void init() {
        keyList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                keyList.add(new Key(i, j));
            }
        }

        initialValues = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            initialValues.add(null);
        }
    }

    @ParameterizedTest
    @MethodSource("parametersTestAvailableSpace")
    void testAvailableSpace(List<Integer> initList, boolean expected) {
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initList);
        assertEquals(expected, board.availableSpace().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("parametersTestFillBoard")
    void testFillBoard(List<Integer> initList) {
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initList);
        assertEquals(initList, board.getValues(keyList));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void testGetRow(int row) {
        List<Key> keyList = new ArrayList<>() {{
            add(new Key(row, 0));
            add(new Key(row, 1));
            add(new Key(row, 2));
            add(new Key(row, 3));
        }};
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initialValues);
        assertEquals(keyList, board.getRow(row));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void testGetColumn(int column) {
        List<Key> keyList = new ArrayList<>() {{
            add(new Key(0, column));
            add(new Key(1, column));
            add(new Key(2, column));
            add(new Key(3, column));
        }};
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initialValues);
        assertEquals(keyList, board.getColumn(column));
    }

    @ParameterizedTest
    @MethodSource("parametersTestHasValue")
    void testHasValue(List<Integer> initList, Integer value) {
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(initList);
        assertTrue(board.hasValue(value));
    }
}