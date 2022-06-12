package com.solvd.optimalpath.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassTypesModel {
    private int id;
    private String typeName;
    private CitiesModel citiesModel;

    public ClassTypesModel() {

    }

    public ClassTypesModel(int id, String typeName, CitiesModel citiesModel) {
        this.id = id;
        this.typeName = typeName;
        this.citiesModel = citiesModel;
        }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public CitiesModel getCitiesModel() {
        return citiesModel;
    }

   public void setId(int id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setCitiesModel(CitiesModel citiesModel) {
        this.citiesModel = citiesModel;
    }

    @Override
    public String toString() {
        return "ClassTypesModel{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", citiesModel=" + citiesModel +
                '}';
    }
}
