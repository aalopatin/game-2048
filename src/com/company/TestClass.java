package com.company;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;

import static com.company.BoardTest.assertEquals;
import static java.util.Arrays.asList;

public class TestClass {

    public static void main(String[] args) {
	    Board board = new SquareBoard(4);
	    Game game2048 = new Game2048(board);
        System.out.println(game2048.canMove());
    }
}
