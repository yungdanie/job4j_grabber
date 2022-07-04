package ru.job4j.design.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void validator(List<Food> list) {
        List<Food> newList = new ArrayList<>(list);
            for (Store store : storeList) {
                for (Food food : newList) {
                    if (store.validate(food)) {
                        store.add(food);
                    }
                }
            }
    }

    public List<Store> getStoreList() {
        return new ArrayList<>(storeList);
    }
}
