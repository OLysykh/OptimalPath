package com.solvd.optimalpath.models;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CitiesModel {
    private int id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<AirlinesModel> airlinesModelList;
    private List<CitiesModel> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<CitiesModel, Integer> adjacentNodes = new HashMap<>();
    private List<ClassTypesModel> classTypesModels = new LinkedList<>();


    public CitiesModel() {
    }

    public CitiesModel(String name) {
        this.name = name;
    }

    public CitiesModel(int id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<CitiesModel> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<CitiesModel> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<CitiesModel, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<CitiesModel, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public void addDestination(CitiesModel destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public void setClassTypesModels(List<ClassTypesModel> classTypesModels) {
        this.classTypesModels = classTypesModels;
    }

    @Override
    public String toString() {
        return "CitiesModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
