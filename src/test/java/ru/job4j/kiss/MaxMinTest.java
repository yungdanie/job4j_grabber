package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
}