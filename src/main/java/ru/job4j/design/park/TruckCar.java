package ru.job4j.design.park;

public class TruckCar implements Car {

    private final int size;
    private final int id;


    public TruckCar(int id, int size) {
        if (size <= LightCar.ONE) {
            throw new IllegalArgumentException();
        }
        this.size = size;
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
