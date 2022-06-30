package ru.job4j.design.storage;

import java.util.List;

public interface Store {
    Food get(int id);

    List<Food> getAll();
    void add(Food food);

    void validate(List<Food> list);
}
