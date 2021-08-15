package ru.aal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Game2048Test {

    @Spy
    private GameHelper helper;

    @Mock
    private Board<Key, Integer> board;

    @InjectMocks
    private Game2048 game;

    @Test
    void testCanMove() {
        when(board.availableSpace()).thenReturn(List.of(new Key(1,1)));
        game.init();
        assertTrue(game.canMove());
    }

    @Test
    void testInit() {
        doNothing().when(board).fillBoard(anyList());
        when(board.availableSpace()).thenReturn(List.of(new Key(1,1)));
        game.init();
        verify(board).fillBoard(anyList());
    }
}