package ru.job4j.design.park;

import org.junit.Assert;
import org.junit.Test;

public class ParkTest {

    @Test
    public void whenAddLightCarToPark() {
        Car car = new LightCar(1);
        AbstractPark abstractPark = new Park(1, 1);
        Assert.assertTrue(abstractPark.addCar(car));
    }

    @Test
    public void whenAddTruckCarToPark() {
        Car car = new TruckCar(1, 2);
        AbstractPark abstractPark = new Park(1, 2);
        Assert.assertTrue(abstractPark.addCar(car));
    }

    @Test
    public void whenCanNotAddLightCarToPark() {
        Car car = new LightCar(1);
        AbstractPark abstractPark = new Park(0, 1);
        Assert.assertFalse(abstractPark.addCar(car));
    }

    @Test
    public void whenCanNotAddTruckCarToPark() {
        Car car = new TruckCar(10, 10);
        AbstractPark abstractPark = new Park(0, 5);
        Assert.assertFalse(abstractPark.addCar(car));
    }
}