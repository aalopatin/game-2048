package ru.aal;

import java.util.*;
import java.util.stream.Collectors;

public class SquareBoard<V> extends Board<Key, V> {
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    void fillBoard(List<V> list) {
        Iterator<V> iterator = null;
        if (list != null) {
            iterator = list.iterator();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                Key key = new Key(i, j);
                if (iterator != null && iterator.hasNext()) {
                    addItem(key, iterator.next());
                } else {
                    addItem(key, null);
                }
            }
        }
    }

    @Override
    List<Key> availableSpace() {
        List<Key> available = new ArrayList<>();
        board.forEach(
                (key, v) -> {
                    if (v == null) available.add(key);
                });
        return available;
    }

    @Override
    void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    Key getKey(int i, int j) {
        Key key = new Key(i, j);
        return board.keySet()
                .stream()
                .filter(k -> k.equals(key))
                .findFirst()
                .orElse(null);
    }

    @Override
    V getValue(Key key) {
        return board.get(key);
    }

    @Override
    List<Key> getColumn(int j) {
        List<Key> column = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            column.add(getKey(i, j));
        }
        return column;
    }

    @Override
    List<Key> getRow(int i) {
        List<Key> row = new ArrayList<>();
        for (int j = 0; j < weight; j++) {
            row.add(getKey(i, j));
        }
        return row;
    }

    @Override
    boolean hasValue(V value) {
        return board.values()
                .stream()
                .anyMatch(v -> Objects.equals(v, value));
    }

    @Override
    List<V> getValues(List<Key> keys) {
        List<V> values = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                Key key = getKey(i, j);
                if (keys.contains(key)) {
                    values.add(getValue(key));
                }
            }
        }

        return values;
    }
}
