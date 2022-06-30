package ru.job4j.design.park;

public interface ParkingControl {

    boolean checkParkingSpace();

    int addCar(Car car);

    int removeCar(int id);

    int removeCar(Car car);
}
