package com.solvd.optimalpath.enums;

public enum Drinks {
    SODA(2.5),
    WATER(1.5),
    TEA(2.5),
    COFFEE(4.5),
    JUICE(3),
    BEER(5.5),
    VINE(5);

    private double price;

    Drinks(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

}
