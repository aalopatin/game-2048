package ru.aal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Game2048Test {

    @Mock
    Board<Key, Integer> board = mock(SquareBoard.class);

    @InjectMocks
    Game game = new Game2048();

    @Test
    void testCanMove() {
        when(board.availableSpace()).thenReturn(Arrays.asList(new Key(1, 1)));
        assertTrue(game.canMove());
    }
}