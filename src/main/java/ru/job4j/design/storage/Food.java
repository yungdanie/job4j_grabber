package ru.job4j.design.storage;

import java.util.Calendar;

public class Food {

    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private double price;
    private double discount;

    private int id;

    private Store currentStore;

    public Food(String name, Calendar createDate, Calendar expiryDate,
                double price, double discount, int id) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.discount = discount;
        this.price = price * (1 - discount);
        this.id = id;
    }

    public Food(String name, Calendar createDate, Calendar expiryDate,
                double price, int id) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price * (1 - discount);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price * (1 - discount);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        setPrice(price);
    }

    public Store getCurrentStore() {
        return currentStore;
    }

    public void setCurrentStore(Store currentStore) {
        this.currentStore = currentStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
