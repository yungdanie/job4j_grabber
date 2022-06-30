package ru.job4j.design.storage;

import java.util.*;

public class Trash implements Store {

    private final Map<Integer, Food> foodMap = new HashMap<>();

    public Trash() {
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
        Calendar currentDate = Calendar.getInstance();
        for (Food food : list) {
            if (currentDate.after(food.getExpiryDate())) {
                add(food);
            }
        }
    }
}
