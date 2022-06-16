package ru.job4j.kiss;

public class MaxComparator implements java.util.Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
