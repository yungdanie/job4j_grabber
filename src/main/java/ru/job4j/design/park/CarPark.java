package ru.job4j.design.park;

public interface CarPark {

    int getSize();

    int addCar(Car car);

    int removeCar(int id);

    int removeCar(Car car);
}
