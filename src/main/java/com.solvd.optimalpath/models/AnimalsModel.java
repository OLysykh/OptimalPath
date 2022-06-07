package com.solvd.optimalpath.models;

import java.util.List;

public class AnimalsModel {
    private int id;
    private String typeOfAnimal;
    private TicketsModel ticketsModel;

    public AnimalsModel() {
    }


    public AnimalsModel(int id, String typeOfAnimal, TicketsModel ticketsModel) {
        this.id = id;
        this.typeOfAnimal = typeOfAnimal;
        this.ticketsModel = ticketsModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public TicketsModel getTicketsModel() {
        return ticketsModel;
    }

    public void setTicketsModel(TicketsModel ticketsModel) {
        this.ticketsModel = ticketsModel;
    }

    @Override
    public String toString() {
        return "AnimalsModel{" +
                "id=" + id +
                ", typeOfAnimal='" + typeOfAnimal + '\'' +
                ", ticketsModel=" + ticketsModel +
                '}';
    }
}
