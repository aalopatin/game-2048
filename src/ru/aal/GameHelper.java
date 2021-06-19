package ru.aal;

import javax.swing.text.InternationalFormatter;
import java.util.*;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        }
        var movedList = move(list);
        var mergedList = merge(movedList);
        return addNullElements(mergedList, list.size() - mergedList.size());
    }

    ;

    private List<Integer> move(List<Integer> list) {
        List<Integer> movedList = new ArrayList<>();
        for (Integer elem : list) {
            if (Objects.nonNull(elem)) {
                movedList.add(elem);
            }
        }
        return movedList;
    }

    private List<Integer> merge(List<Integer> list) {
        List<Integer> mergedList = new ArrayList<>();

        ListIterator<Integer> listIterator = list.listIterator();

        while(listIterator.hasNext()) {
            Integer current = listIterator.next();
            if (listIterator.hasNext()) {
                Integer next = listIterator.next();
                if (current == next) {
                    mergedList.add(current + next);
                } else {
                    mergedList.add(current);
                    listIterator.previous();
                }
            } else {
                mergedList.add(current);
            }
        }

        return mergedList;
    }


    private List<Integer> addNullElements(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(null);
        }
        return list;
    }
}
