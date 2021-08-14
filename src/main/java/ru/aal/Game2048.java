package ru.aal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {

    public static final int GAME_SIZE = 4;
    GameHelper helper = new GameHelper();
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    Random random = new Random();

    @Override
    public void init() {

        List<Integer> initBoard = new ArrayList<>();

        for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
            initBoard.add(null);
        }

        board.fillBoard(initBoard);

        addItem();
        addItem();

    }

    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < GAME_SIZE; i++) {
                for (int j = 0; j < GAME_SIZE-1; j++) {

                    Integer current = board.getValue(new Key(i, j));
                    Integer next = board.getValue(new Key(i, j+1));

                    if(current.equals(next)) {
                        return true;
                    }

                    current = board.getValue(new Key(j, i));
                    next = board.getValue(new Key(j+1, i));

                    if(current.equals(next)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        int countMoves = 0;
        if (canMove()) {
            for (int n = 0; n < GAME_SIZE; n++) {
                List<Key> keyList = new ArrayList<>();
                switch (direction) {
                    case UP:
                        keyList = board.getColumn(n);
                        break;
                    case DOWN:
                        keyList = board.getColumn(n);
                        Collections.reverse(keyList);
                        break;
                    case LEFT:
                        keyList = board.getRow(n);
                        break;
                    case RIGHT:
                        keyList = board.getRow(n);
                        Collections.reverse(keyList);
                        break;
                }
                List<Integer> values = new ArrayList<>();
                for (Key key : keyList) {
                    values.add(board.getValue(key));
                }
                List<Integer> newValues = helper.moveAndMergeEqual(values);
                if (!newValues.equals(values)) {
                    for (int i = 0; i < GAME_SIZE; i++) {
                        board.addItem(keyList.get(i), newValues.get(i));
                    }
                    countMoves++;
                }
            }
            if (countMoves > 0) {
                addItem();
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void addItem() {
        List<Key> availableSpace = board.availableSpace();
        Integer value = random.nextInt(9) == 9 ? 4 : 2;
        int index = availableSpace.size() == 1 ? 0 : random.nextInt(availableSpace.size() - 1);
        Key key = availableSpace.get(index);
        board.addItem(key, value);
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
