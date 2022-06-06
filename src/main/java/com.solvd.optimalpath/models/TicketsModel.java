package com.solvd.optimalpath.models;

import java.util.List;

public class TicketsModel {
    private int id;
    private AirlinesModel airlinesModel;
    private CitiesModel citiesModel;
    private ClassTypesModel classTypesModel;
    private ClientsModel clientsModel;
    private List<AnimalsModel> animalsModelsList;
    private int seatsNum;
    private int price;

    public TicketsModel() {
    }

    public TicketsModel(int id, AirlinesModel airlinesModel, CitiesModel citiesModel, ClassTypesModel classTypesModel, ClientsModel clientsModel, List<AnimalsModel> animalsModelsList, int seatsNum, int price) {
        this.id = id;
        this.airlinesModel = airlinesModel;
        this.citiesModel = citiesModel;
        this.classTypesModel = classTypesModel;
        this.clientsModel = clientsModel;
        this.animalsModelsList = animalsModelsList;
        this.seatsNum = seatsNum;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AirlinesModel getAirlinesModel() {
        return airlinesModel;
    }

    public void setAirlinesModel(AirlinesModel airlinesModel) {
        this.airlinesModel = airlinesModel;
    }

    public CitiesModel getCitiesModel() {
        return citiesModel;
    }

    public void setCitiesModel(CitiesModel citiesModel) {
        this.citiesModel = citiesModel;
    }

    public ClassTypesModel getClassTypesModel() {
        return classTypesModel;
    }

    public void setClassTypesModel(ClassTypesModel classTypesModel) {
        this.classTypesModel = classTypesModel;
    }

    public ClientsModel getClientsModel() {
        return clientsModel;
    }

    public void setClientsModel(ClientsModel clientsModel) {
        this.clientsModel = clientsModel;
    }

    public List<AnimalsModel> getAnimalsModelsList() {
        return animalsModelsList;
    }

    public void setAnimalsModelsList(List<AnimalsModel> animalsModelsList) {
        this.animalsModelsList = animalsModelsList;
    }

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketsModel{" +
                "id=" + id +
                ", airlinesModel=" + airlinesModel +
                ", citiesModel=" + citiesModel +
                ", classTypesModel=" + classTypesModel +
                ", clientsModel=" + clientsModel +
                ", animalsModelsList=" + animalsModelsList +
                ", seatsNum=" + seatsNum +
                ", price=" + price +
                '}';
    }
}
