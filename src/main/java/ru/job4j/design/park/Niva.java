package ru.job4j.design.park;

public class Niva implements Car {
    private final int id;
    private final int size;
    public Niva(int id, int size) {
        this.id = id;
        this.size = size;
    }
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public CarPark getParkingSpace() {
        return null;
    }

    @Override
    public void setParkingSpace() {

    }
}