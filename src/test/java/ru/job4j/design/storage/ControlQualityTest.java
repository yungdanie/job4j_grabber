package ru.job4j.design.storage;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenFoodPlacedInShopWithoutDiscount() {
        Calendar calendarCreate = Calendar.getInstance();
        calendarCreate.setTimeInMillis(1653771600);
        Calendar calendarExp = Calendar.getInstance();
        calendarExp.setTimeInMillis(1659042000);
        List<Food> foodList = List.of(
                new Food("shaverma", calendarCreate, calendarExp, 100, 1),
                new Food("pelmeni", calendarCreate, calendarExp, 200, 2)
        );
        Store store = new Shop();
        List<Store> storeList = List.of(store);
        ControlQuality controlQuality = new ControlQuality(foodList, storeList);
        controlQuality.validator();
        Assert.assertEquals(foodList, store.getAll());
    }

    @Test
    public void whenFoodPlacedInShopWithDiscount() {
        Calendar calendarCreate = Calendar.getInstance();
        calendarCreate.setTimeInMillis(1651408846);
        Calendar calendarExp = Calendar.getInstance();
        calendarExp.setTimeInMillis(1657456846);
        List<Food> foodList = List.of(
                new Food("old shaverma", calendarCreate, calendarExp, 100,  1),
                new Food("old pelmeni", calendarCreate, calendarExp, 200,  2)
        );
        Store store = new Shop();
        List<Store> storeList = List.of(store);
        ControlQuality controlQuality = new ControlQuality(foodList, storeList);
        controlQuality.validator();
        Assert.assertEquals(foodList, store.getAll());
        Assert.assertEquals(foodList.get(0).getDiscount(), 0.75, 0.001);
    }

    @Test
    public void whenFoodPlacedInTrash() {
        Calendar calendarCreate = Calendar.getInstance();
        calendarCreate.setTimeInMillis(1651408846);
        Calendar calendarExp = Calendar.getInstance();
        calendarExp.setTimeInMillis(1654432846);
        List<Food> foodList = List.of(
                new Food("spoiled shaverma", calendarCreate, calendarExp, 100,  1),
                new Food("spoiled pelmeni", calendarCreate, calendarExp, 200,  2)
        );
        Store store = new Trash();
        List<Store> storeList = List.of(store);
        ControlQuality controlQuality = new ControlQuality(foodList, storeList);
        controlQuality.validator();
        Assert.assertEquals(foodList, store.getAll());
    }

    @Test
    public void whenFoodPlacedInWareHouse() {
        Calendar calendarCreate = Calendar.getInstance();
        calendarCreate.setTimeInMillis(1656592846);
        Calendar calendarExp = Calendar.getInstance();
        calendarExp.setTimeInMillis(1672404046);
        List<Food> foodList = List.of(
                new Food("new shaverma", calendarCreate, calendarExp, 100,  1),
                new Food("new pelmeni", calendarCreate, calendarExp, 200,  2)
        );
        Store store = new WareHouse();
        List<Store> storeList = List.of(store);
        ControlQuality controlQuality = new ControlQuality(foodList, storeList);
        controlQuality.validator();
        Assert.assertEquals(foodList, store.getAll());
    }
}