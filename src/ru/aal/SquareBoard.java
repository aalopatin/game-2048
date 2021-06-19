package ru.aal;

import java.util.*;
import java.util.stream.Collectors;

public class SquareBoard extends Board {
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    void fillBoard(List<Integer> list) {
        Iterator<Integer> iterator = null;
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
                (key, integer) -> {
                    if (integer == null) available.add(key);
                });
        return available;
    }

    @Override
    void addItem(Key key, Integer value) {
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
    Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    List<Key> getColumn(int j) {
        return board.keySet()
                .stream()
                .filter(key -> key.getJ() == j)
                .sorted(Comparator.comparingInt(Key::getI))
                .collect(Collectors.toList());
    }

    @Override
    List<Key> getRow(int i) {
        return board.keySet()
                .stream()
                .filter(key -> key.getI() == i)
                .sorted(Comparator.comparingInt(Key::getJ))
                .collect(Collectors.toList());
    }

    @Override
    boolean hasValue(Integer value) {
        return board.values()
                .stream()
                .anyMatch(integer -> Objects.equals(integer, value));
    }

    @Override
    List<Integer> getValues(List<Key> keys) {
        List<Integer> values = new ArrayList<>();

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
