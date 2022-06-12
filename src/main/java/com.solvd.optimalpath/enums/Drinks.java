package com.solvd.optimalpath.enums;

public enum Drinks {
    SODA(35),
    WATER(30),
    TEA(50),
    COFFEE(70),
    JUICE(45),
    BEER(75),
    VINE(100);

    private int price;

    Drinks(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

}
