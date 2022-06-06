package com.solvd.optimalpath.models;

public class TicketsModel {
    private int id;
    private AnimalsModel animalsModel;
    private AirlinesModel airlinesModel;
    private CitiesModel citiesModel;
    private ClassTypesModel classTypesModel;
    private ClientsModel clientsModel;
    private int seatsNum;

    public TicketsModel() {
    }

    public TicketsModel(int id, AnimalsModel animalsModel, AirlinesModel airlinesModel, CitiesModel citiesModel, ClassTypesModel classTypesModel, ClientsModel clientsModel, int seatsNum) {
        this.id = id;
        this.animalsModel = animalsModel;
        this.airlinesModel = airlinesModel;
        this.citiesModel = citiesModel;
        this.classTypesModel = classTypesModel;
        this.clientsModel = clientsModel;
        this.seatsNum = seatsNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimalsModel getAnimalsModel() {
        return animalsModel;
    }

    public void setAnimalsModel(AnimalsModel animalsModel) {
        this.animalsModel = animalsModel;
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

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    @Override
    public String toString() {
        return "TicketsModel{" +
                "id=" + id +
                ", animalsModel=" + animalsModel +
                ", airlinesModel=" + airlinesModel +
                ", citiesModel=" + citiesModel +
                ", classTypesModel=" + classTypesModel +
                ", clientsModel=" + clientsModel +
                ", seatsNum=" + seatsNum +
                '}';
    }
}
