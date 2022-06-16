package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxMin {
    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return findMaxOrMin(value, comparator);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return findMaxOrMin(value, comparator);
    }

    public static <T> T findMaxOrMin(List<T> value, Comparator<T> comparator) {
        Iterator<T> iterator = value.iterator();
        T num;
        T next;
        if (iterator.hasNext()) {
            num = iterator.next();
        } else {
            throw new IllegalArgumentException("Лист значений пуст");
        }
        while (iterator.hasNext()) {
            next = iterator.next();
            if (comparator.compare(num, next) <= 0) {
                num = next;
            }
        }
        return num;
    }
}