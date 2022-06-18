package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return findMaxOrMin(value, comparator);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return findMaxOrMin(value, comparator);
    }

    private static <T> T findMaxOrMin(List<T> value, Comparator<T> comparator) {

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Лист значений пуст");
        }
        T extremum = value.get(0);
        T next;
        for (T element : value) {
            next = element;
            if (comparator.compare(extremum, next) <= 0) {
                extremum = next;
            }
        }
        return extremum;
    }
}