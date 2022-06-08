package com.solvd.optimalpath.enums;

public enum Menu {
    LASAGNA(10.5),
    SALAD(9.9),
    PASTA(10.5),
    PANINI(10.5),
    BURGER(8.9),
    TAPAS(5.5);

    private double price;

    Menu(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}