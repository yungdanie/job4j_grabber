package ru.job4j.design.park;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ParkingControlTest {

    @Ignore
    @Test
    public void whenAddPassCarInFreeCarPark() {
        Car smallCar1 = new Niva(1, 1);
        Car smallCar2 = new Lada(2, 1);
        CarPark carPark = new BigParking(10, 0);
        ParkingControl parkingControl = new SimpleParkController(carPark);
        parkingControl.addCar(smallCar1);
        parkingControl.addCar(smallCar2);
        Assert.assertEquals(smallCar1, parkingControl.getCar(1));
        Assert.assertEquals(smallCar2, parkingControl.getCar(2));
    }

    @Ignore
    @Test
    public void whenAddPassCarInOccupiedCarPark() {
        Car smallCar1 = new Niva(1, 1);
        Car smallCar2 = new Lada(2, 1);
        CarPark carPark = new BigParking(0, 0);
        ParkingControl parkingControl = new SimpleParkController(carPark);
        parkingControl.addCar(smallCar1);
        parkingControl.addCar(smallCar2);
        Assert.assertNull(null, parkingControl.getCar(1));
        Assert.assertNull(null, parkingControl.getCar(2));
    }

    @Ignore
    @Test
    public void whenAddPassCarInCarParkWithOnlyFreeTruckSpace() {
        Car smallCar1 = new Niva(1, 1);
        Car smallCar2 = new Lada(2, 1);
        CarPark carPark = new BigParking(0, 200);
        ParkingControl parkingControl = new SimpleParkController(carPark);
        parkingControl.addCar(smallCar1);
        parkingControl.addCar(smallCar2);
        Assert.assertFalse(parkingControl.addCar(smallCar1));
        Assert.assertFalse(parkingControl.addCar(smallCar1));
    }

    @Ignore
    @Test
    public void whenAddTruckInFreeCarPark() {
        Car truck = new Kamaz(1, 10);
        CarPark carPark = new BigParking(0, 10);
        ParkingControl parkingControl = new SimpleParkController(carPark);
        parkingControl.addCar(truck);
        Assert.assertEquals(truck, parkingControl.getCar(1));
    }

    @Ignore
    @Test
    public void whenAddTruckInFreeCarParkWithFreePassTruckSpaces() {
        Car truck = new Kamaz(1, 10);
        CarPark carPark = new BigParking(2, 8);
        ParkingControl parkingControl = new SimpleParkController(carPark);
        parkingControl.addCar(truck);
        Assert.assertEquals(truck, parkingControl.getCar(1));
    }
}