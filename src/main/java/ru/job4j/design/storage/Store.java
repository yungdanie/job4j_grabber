package ru.job4j.design.storage;

import java.util.Calendar;
import java.util.List;

public interface Store {

    default double getPercentLifeExpired(Food food) {
        double currentInMillis = Calendar.getInstance().getTimeInMillis();
        double createInMillis = food.getCreateDate().getTimeInMillis() * 1000;
        double expiredInMillis = food.getExpiryDate().getTimeInMillis() * 1000;
        return (expiredInMillis - currentInMillis) / (expiredInMillis - createInMillis + 1);
    }
    Food get(int id);

    List<Food> getAll();
    boolean add(Food food);

    boolean validate(Food food);

    void clearStore();
}
