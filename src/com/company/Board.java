package com.company;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board {
    int weight;
    int height;

    Map<Key, Integer> board = new HashMap<>();

    public Board(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    abstract void fillBoard(List<Integer> list);
    abstract List<Key> availableSpace();
    abstract void addItem(Key key, Integer value);
    abstract Key getKey(int i, int j);
    abstract Integer getValue(Key key);
    abstract List<Key> getColumn(int j);
    abstract List<Key> getRow(int i);
    abstract boolean hasValue(Integer value);
    abstract List<Integer> getValues(List<Key> keys);
}
