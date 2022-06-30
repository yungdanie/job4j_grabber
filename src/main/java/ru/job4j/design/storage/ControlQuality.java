package ru.job4j.design.storage;

import java.util.List;

public class ControlQuality {
    private final List<Food> foodList;

    private final List<Store> storeList;

    public ControlQuality(List<Food> list, List<Store> storeList) {
        this.foodList = list;
        this.storeList = storeList;
    }

    public void validator() {
        for (Store store : storeList) {
            store.validate(foodList);
        }
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public List<Store> getStoreList() {
        return storeList;
    }
}
