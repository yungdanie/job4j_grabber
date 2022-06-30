package ru.job4j.design.park;

public class SimpleParkController implements ParkingControl {

    private final CarPark carPark;
    public SimpleParkController(CarPark carPark) {
        this.carPark = carPark;
    }

    @Override
    public boolean checkParkingSpace() {
        return false;
    }

    @Override
    public boolean addCar(Car car) {
        return true;
    }

    @Override
    public int removeCar(int id) {
        return 0;
    }

    @Override
    public int removeCar(Car car) {
        return 0;
    }

    @Override
    public Car getCar(int id) {
        return null;
    }

    @Override
    public Car getCar(Car car) {
        return null;
    }
}
