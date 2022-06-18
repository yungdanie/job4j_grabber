package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MaxMinTest {

    @Test
    public void whenFindMax() {
        Integer max = MaxMin.max(Arrays.asList(1, 2, 3, 5, 10, 101, 12, 13, 4, 6, 3, 2, 0), new MaxComparator());
        Integer exp = 101;
        Assert.assertEquals(exp, max);
    }

    @Test
    public void whenFindMin() {
        Integer max = MaxMin.min(Arrays.asList(1, 2, 3, 5, 10, 12, 13, 0, 6, 3, 2), new MinComparator());
        Integer exp = 0;
        Assert.assertEquals(exp, max);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenListEmpty() {
        Integer max = MaxMin.max(new ArrayList<>(), new MinComparator());
    }
    @Test
    public void whenFindMaxString() {
        String max = MaxMin.max(Arrays.asList("а", "б", "в", "гд", "у"), Comparator.naturalOrder());
        String exp = "у";
        Assert.assertEquals(exp, max);
    }

    @Test
    public void whenFindMinString() {
        String max = MaxMin.min(Arrays.asList("а", "б", "в", "гд", "у"), Comparator.reverseOrder());
        String exp = "а";
        Assert.assertEquals(exp, max);
    }
}