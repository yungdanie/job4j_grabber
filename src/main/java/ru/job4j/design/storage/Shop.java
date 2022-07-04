package ru.job4j.design.storage;

import java.util.*;

public class Shop implements Store {

    private final Map<Integer, Food> foodMap = new HashMap<>();
    private final double zeroPercents = 0;
    private final double seventyFivePercents = 0.75;
    private final double twentyFivePercents = 0.25;


    public Shop() {
    }

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
        boolean res = false;
        double a = getPercentLifeExpired(food);
        if (a >= twentyFivePercents && a <= seventyFivePercents) {
            res = true;
        } else if (a < twentyFivePercents && a >= zeroPercents) {
            food.setDiscount(twentyFivePercents);
            res = true;
        }
        return res;
    }
}
