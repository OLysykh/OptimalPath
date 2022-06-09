package com.solvd.optimalpath.enums;

public enum Menu {
    LASAGNA(25),
    SALAD(18),
    PASTA(20),
    PANINI(15),
    BURGER(12),
    TAPAS(10);

    private int price;

    Menu(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}