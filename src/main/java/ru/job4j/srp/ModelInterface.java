package ru.job4j.srp;

/**
 * Анти-паттерн (шаблон) проектирования "Божественный объект", который нарушает SRP.
 */

public interface ModelInterface {

    int addMoneyToModel(Model model);

    Model addModel(String description);

    String getInfo(Model model);

    void loadModel(Model model);
}
