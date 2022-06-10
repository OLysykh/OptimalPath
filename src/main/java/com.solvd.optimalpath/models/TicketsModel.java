package com.solvd.optimalpath.models;

import java.util.List;

public class TicketsModel {
    private int id;
    private AirlinesModel airlinesModel;
    private CitiesModel citiesModel;
    private ClassTypesModel classTypesModel;
    private ClientsModel clientsModel;
    private List<AnimalsModel> animalsModelsList;
    private String destinationCity;
    private String seatsNum;
    private String airlineName;
    private String cityArrival;
    private int price;
    private double timeFlight;

    public TicketsModel() {
    }

    public TicketsModel(int id, AirlinesModel airlinesModel, CitiesModel citiesModel, ClassTypesModel classTypesModel,
                        ClientsModel clientsModel, List<AnimalsModel> animalsModelsList, String destinationCity,
                        String seatsNum, String airlineName, String cityArrival, int price, double timeFlight) {
        this.id = id;
        this.airlinesModel = airlinesModel;
        this.citiesModel = citiesModel;
        this.classTypesModel = classTypesModel;
        this.clientsModel = clientsModel;
        this.animalsModelsList = animalsModelsList;
        this.destinationCity = destinationCity;
        this.seatsNum = seatsNum;
        this.airlineName = airlineName;
        this.cityArrival = cityArrival;
        this.price = price;
        this.timeFlight = timeFlight;
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

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(String seatsNum) {
        this.seatsNum = seatsNum;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTimeFlight() {
        return timeFlight;
    }

    public void setTimeFlight(double timeFlight) {
        this.timeFlight = timeFlight;
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
                ", destinationCity='" + destinationCity + '\'' +
                ", seatsNum='" + seatsNum + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", cityArrival='" + cityArrival + '\'' +
                ", price=" + price +
                ", timeFlight=" + timeFlight +
                '}';
    }
}

