package ru.job4j.design.park;

public class BigParking implements CarPark {

    private final int passSize;
    private final int truckSize;
    public BigParking(int passSize, int truckSize) {
        this.passSize = passSize;
        this.truckSize = truckSize;
    }


    @Override
    public int getPassengerSize() {
        return 0;
    }

    @Override
    public int getTruckSize() {
        return 0;
    }

    @Override
    public int getOccupiedPassengerSpace() {
        return 0;
    }

    @Override
    public int getOccupiedTruckSpace() {
        return 0;
    }

    @Override
    public void setPassengerOccupiedSpace() {

    }

    @Override
    public void setTruckOccupiedSpace() {

    }
}
