package ru.job4j.design.park;

import java.util.ArrayList;
import java.util.List;

public class Park implements AbstractPark {
    List<Car> lightCarList;
    List<Car> truckCarList;

    private final int lightCapacity;
    private final int truckCapacity;

    private int lightSize = 0;
    private int truckSize = 0;


    public Park(int lightCarSize, int truckCarSize) {
        this.lightCapacity = lightCarSize;
        this.truckCapacity = truckCarSize;
        lightCarList = new ArrayList<>(lightCarSize);
        truckCarList = new ArrayList<>(truckCarSize);
    }

    @Override
    public boolean addCar(Car car) {
        boolean res = false;
        if (validateLightCar(car)) {
            lightCarList.add(car);
            lightSize++;
            res = true;
        } else if (validateTruckCar(car)) {
            truckCarList.add(car);
            truckSize++;
            res = true;
        } else if (validateTruckCarOnLightPlace(car)) {
            lightCarList.add(car);
            lightSize += car.getSize();
            res = true;
        }
        return res;
    }

    @Override
    public boolean validateLightCar(Car car) {
        return car.getSize() == LightCar.ONE && lightCapacity > lightSize;
    }

    @Override
    public boolean validateTruckCar(Car car) {
        return car.getSize() > LightCar.ONE
                && truckCapacity > truckSize;
    }

    @Override
    public boolean validateTruckCarOnLightPlace(Car car) {
        return lightCapacity - lightSize >= car.getSize();
    }


    @Override
    public Car getCar(int id) {
        Car res = null;
        for (Car car : lightCarList) {
            if (car.getId() == id) {
                res = car;
            }
        }
        for (Car car : truckCarList) {
            if (car.getId() == id) {
                res = car;
            }
        }
        return res;
    }
}
