package ru.job4j.design.storage;

import java.util.*;

public class Trash implements Store {

    private final Map<Integer, Food> foodMap = new HashMap<>();

    @Override
    public Food get(int id) {
        return foodMap.getOrDefault(id, null);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foodMap.values());
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (validate(food)) {
            foodMap.put(food.getId(), food);
            res = true;
        }
        return res;
    }

    @Override
    public boolean validate(Food food) {
        return getPercentLifeExpired(food) < StaticNumbers.ZERO_PERCENTS;
    }

    @Override
    public void clearStore() {
        foodMap.clear();
    }
}
