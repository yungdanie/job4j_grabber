package ru.job4j.design.park;

import java.util.ArrayList;
import java.util.List;

public class Park implements AbstractPark {
    List<Car> ligthCarList;
    List<Car> truckCarList;

    private final int ligthSize;
    private final int truckSize;


    public Park(int lightCarSize, int truckCarSize) {
        ligthCarList = new ArrayList<>();
        truckCarList = new ArrayList<>();
        this.ligthSize = lightCarSize;
        this.truckSize = truckCarSize;
    }

    @Override
    public boolean addCar(Car car) {
        boolean res = false;
        if (validateLightCar(car)) {
            ligthCarList.add(car);
            res = true;
        } else if (validateTruckCar(car)) {
            truckCarList.add(car);
            res = true;
        }
        return res;
    }

    @Override
    public boolean validateLightCar(Car car) {
        return car.getSize() == 1 && ligthSize > ligthCarList.size();
    }

    @Override
    public boolean validateTruckCar(Car car) {
        return car.getSize() > 1
                && car.getSize() != 0
                && truckSize >= truckCarList.size()
                && truckSize >= car.getSize();
    }


    @Override
    public Car getCar(int id) {
        Car res = null;
        for (Car car : ligthCarList) {
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
