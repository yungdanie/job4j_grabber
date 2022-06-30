package ru.job4j.design.park;

public interface CarPark {

    int getPassengerSize();
    int getTruckSize();
    int getOccupiedPassengerSpace();
    int getOccupiedTruckSpace();

    void setPassengerOccupiedSpace();
    void setTruckOccupiedSpace();
}
