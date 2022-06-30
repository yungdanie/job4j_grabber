package ru.job4j.design.storage;

import java.util.*;

public class Shop implements Store {

    private final Map<Integer, Food> foodMap = new HashMap<>();

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
    public void add(Food food) {
        foodMap.put(food.getId(), food);
    }

    @Override
    public void validate(List<Food> list) {
        double currentInMillis = Calendar.getInstance().getTimeInMillis();
        for (Food food : list) {
            double createInMillis = food.getCreateDate().getTimeInMillis() * 1000;
            double expiredInMillis = food.getExpiryDate().getTimeInMillis() * 1000;
            double a = (expiredInMillis - currentInMillis) / (expiredInMillis - createInMillis + 1);
            if (a >= 0.25 && a <= 0.75) {
                add(food);
            } else if (a < 0.25 && a >= 0) {
                food.setDiscount(0.75);
                add(food);
            }
        }
    }
}
