package com.solvd.optimalpath.models;

import java.util.LinkedList;
import java.util.List;

public class AdministratorsModel {
    private int id;
    private String adminName;
    private String pass;
    private CitiesModel citiesModel;
    private List<TicketsModel> ticketsModelList = new LinkedList<>();

    public AdministratorsModel() {
    }

    public AdministratorsModel(int id, String adminName, String pass, CitiesModel citiesModel, List<TicketsModel> ticketsModelList) {
        this.id = id;
        this.adminName = adminName;
        this.pass = pass;
        this.citiesModel = citiesModel;
        this.ticketsModelList = ticketsModelList;
    }

    public AdministratorsModel(String adminName, String pass) {
        this.adminName = adminName;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public CitiesModel getCitiesModel() {
        return citiesModel;
    }

    public void setCitiesModel(CitiesModel citiesModel) {
        this.citiesModel = citiesModel;
    }

    public List<TicketsModel> getTicketsModelList() {
        return ticketsModelList;
    }

    public void setTicketsModelList(List<TicketsModel> ticketsModelList) {
        this.ticketsModelList = ticketsModelList;
    }

    @Override
    public String toString() {
        return "AdministratorsModel{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", pass='" + pass + '\'' +
                ", citiesModel=" + citiesModel +
                ", ticketsModelList=" + ticketsModelList +
                '}';
    }
}

