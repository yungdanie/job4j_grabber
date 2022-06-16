package ru.job4j.kiss;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenFindMax() {
        Integer max = MaxMin.findMaxOrMin(Arrays.asList(1, 2, 3, 5, 10, 12, 13, 4, 6, 3, 2, 0), new MaxComparator());
        Integer exp = 13;
        Assert.assertEquals(exp, max);
    }

    @Test
    public void whenFindMin() {
        Integer max = MaxMin.findMaxOrMin(Arrays.asList(1, 2, 3, 5, 10, 12, 13, 0, 6, 3, 2), new MinComparator());
        Integer exp = 0;
        Assert.assertEquals(exp, max);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenListEmpty() {
        Integer max = MaxMin.findMaxOrMin(new ArrayList<>(), new MinComparator());
    }
}