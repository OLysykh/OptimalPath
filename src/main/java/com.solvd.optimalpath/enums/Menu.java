package com.solvd.optimalpath.enums;

public enum Menu {
    LASAGNA(270),
    SALAD(200),
    PASTA(250),
    PANINI(130),
    BURGER(150),
    TAPAS(80);

    private int price;

    Menu(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}