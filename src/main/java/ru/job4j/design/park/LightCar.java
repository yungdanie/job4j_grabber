package ru.job4j.design.park;

public class LightCar implements Car {

    private int size = Car.ONE;
    private int id;

    public LightCar(int id) {
        this.id = id;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getId() {
        return id;
    }
}
