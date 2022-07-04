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
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.validator(foodList);
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
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.validator(foodList);
        Assert.assertEquals(foodList, store.getAll());
        Assert.assertEquals(0.25, foodList.get(0).getDiscount(), 0.001);
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
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.validator(foodList);
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
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.validator(foodList);
        Assert.assertEquals(foodList, store.getAll());
    }

    @Test
    public void whenFoodPlacedInSeveralPlaces() {

        Calendar calendarCreate1 = Calendar.getInstance();
        calendarCreate1.setTimeInMillis(1656592846);
        Calendar calendarExp1 = Calendar.getInstance();
        calendarExp1.setTimeInMillis(1672404046);
        Food newFood1 = new Food("new shaverma", calendarCreate1, calendarExp1, 100,  1);
        Food newFood2 = new Food("new pelmeni", calendarCreate1, calendarExp1, 200,  2);
        Calendar calendarExp2 = Calendar.getInstance();
        Calendar calendarCreate2 = Calendar.getInstance();
        calendarCreate2.setTimeInMillis(1621408846);
        calendarExp2.setTimeInMillis(1646945378);
        Food oldFood1 = new Food("spoiled shaverma", calendarCreate2, calendarExp2, 100,  1);
        Food oldFood2 = new Food("spoiled pelmeni", calendarCreate2, calendarExp2, 200,  2);
        Calendar calendarExp3 = Calendar.getInstance();
        Calendar calendarCreate3 = Calendar.getInstance();
        calendarCreate3.setTimeInMillis(1653771600);
        calendarExp3.setTimeInMillis(1659042000);
        Food food1 = new Food("shaverma", calendarCreate3, calendarExp3, 100, 1);
        Food food2 = new Food("pelmeni", calendarCreate3, calendarExp3, 200, 2);
        Store wareStore = new WareHouse();
        Store trashStore = new Trash();
        Store shopStore = new Shop();
        List<Store> storeList = List.of(wareStore, trashStore, shopStore);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.validator(List.of(newFood1, newFood2, oldFood1, oldFood2, food1, food2));
        Assert.assertEquals(List.of(newFood1, newFood2), wareStore.getAll());
        Assert.assertEquals(List.of(oldFood1, oldFood2), trashStore.getAll());
        Assert.assertEquals(List.of(food1, food2), shopStore.getAll());
    }
}