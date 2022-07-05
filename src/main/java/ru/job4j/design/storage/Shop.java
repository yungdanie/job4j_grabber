package ru.job4j.design.storage;

import java.util.*;


public class Shop implements Store {

    private final Map<Integer, Food> foodMap = new HashMap<>();
    private final static double ZERO_PERCENTS = 0;
    private final static double SEVENTY_FIVE_PERCENTS = 0.75;
    private final static double TWENTY_FIVE_PERCENTS = 0.25;

    private final static double ONE_HUNDRED_PERCENTS = 1;

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
            if (getPercentLifeExpired(food) < TWENTY_FIVE_PERCENTS) {
                food.setDiscount(TWENTY_FIVE_PERCENTS);
            }
            foodMap.put(food.getId(), food);
            res = true;
        }
        return res;
    }

    @Override
    public boolean validate(Food food) {
        boolean res = false;
        double percentLifeExpired = getPercentLifeExpired(food);
        if (percentLifeExpired >= ZERO_PERCENTS && percentLifeExpired <= SEVENTY_FIVE_PERCENTS) {
            res = true;
        }
        return res;
    }

    @Override
    public void clearStore() {
        foodMap.clear();
    }
}
