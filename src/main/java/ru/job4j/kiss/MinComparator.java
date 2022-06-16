package ru.job4j.kiss;

import java.util.Comparator;

public class MinComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
