package ru.job4j.design.srp.model;

public enum ExchangeRate {
    DOLLAR(55), EURO(60), GBR(68), CHF(58);
    private final int rate;

    ExchangeRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
