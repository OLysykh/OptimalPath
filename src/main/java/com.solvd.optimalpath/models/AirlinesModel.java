package com.solvd.optimalpath.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AirlinesModel {
    private int id;
    private String name;
    private CitiesModel citiesModel;

    public AirlinesModel() {
    }

    public AirlinesModel(int id, String name, CitiesModel citiesModel) {
        this.id = id;
        this.name = name;
        this.citiesModel = citiesModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CitiesModel getCitiesModel() {
        return citiesModel;
    }

    public void setCitiesModel(CitiesModel citiesModel) {
        this.citiesModel = citiesModel;
    }

    @Override
    public String toString() {
        return "AirlinesModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", citiesModel=" + citiesModel +
                '}';
    }
}
