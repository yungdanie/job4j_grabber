package ru.job4j.design.park;

public class LightCar implements Car {

    public static final int ONE = 1;
    private final int id;

    public LightCar(int id) {
        this.id = id;
    }
    @Override
    public int getSize() {
        return ONE;
    }

    @Override
    public int getId() {
        return id;
    }
}
