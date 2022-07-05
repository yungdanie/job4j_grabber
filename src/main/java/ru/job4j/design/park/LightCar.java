package ru.job4j.design.park;

public class LightCar implements Car {

    private static final int SIZE = 1;
    private final int id;

    public LightCar(int id) {
        this.id = id;
    }
    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public int getId() {
        return id;
    }
}
