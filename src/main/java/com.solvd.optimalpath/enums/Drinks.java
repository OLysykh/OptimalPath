package com.solvd.optimalpath.enums;

public enum Drinks {
    SODA(5),
    WATER(2),
    TEA(8),
    COFFEE(10),
    JUICE(6),
    BEER(12),
    VINE(15);

    private int price;

    Drinks(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

}
