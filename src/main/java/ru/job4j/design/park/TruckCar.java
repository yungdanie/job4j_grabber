package ru.job4j.design.park;

public class TruckCar implements Car {

    private int size;
    private int id;


    public TruckCar(int id, int size) {
        if (size > Car.ONE) {
            this.size = size;
        }
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
