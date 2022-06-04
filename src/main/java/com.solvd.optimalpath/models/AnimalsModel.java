package com.solvd.optimalpath.models;

import java.util.List;

public class AnimalsModel {
    private int id;
    private String typeOfAnimal;
    private List <TicketsModel> ticketsModelList;

    public AnimalsModel() {
    }

    public AnimalsModel(int id, String typeOfAnimal, List <TicketsModel> ticketsModelList) {
        this.id = id;
        this.typeOfAnimal = typeOfAnimal;
        this.ticketsModelList = ticketsModelList;

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

    public List<TicketsModel> getTicketsModelList() {
        return ticketsModelList;
    }

    public void setTicketsModelList(List<TicketsModel> ticketsModelList) {
        this.ticketsModelList = ticketsModelList;
    }

    @Override
    public String toString() {
        return "AnimalsModel{" +
                "id=" + id +
                ", typeOfAnimal='" + typeOfAnimal + '\'' +
                '}';
    }
}
