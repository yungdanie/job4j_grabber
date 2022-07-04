package ru.job4j.design.park;

public interface AbstractPark {
    boolean addCar(Car car);

    boolean validateLightCar(Car car);

    boolean validateTruckCar(Car car);

    Car getCar(int id);
}
