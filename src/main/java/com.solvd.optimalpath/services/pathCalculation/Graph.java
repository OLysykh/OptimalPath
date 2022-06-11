package com.solvd.optimalpath.services.pathCalculation;


import com.solvd.optimalpath.models.CitiesModel;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    //https://www.baeldung.com/java-dijkstra

    private Set<CitiesModel> nodes = new HashSet<>();

    public Graph() {
    }

    public Graph(Set<CitiesModel> nodes) {
        this.nodes = nodes;
    }

    public Set<CitiesModel> getNodes() {
        return nodes;
    }

    public void addNode(CitiesModel nodeA) {
        nodes.add(nodeA);
    }

    public CitiesModel getIt(){
        CitiesModel temp = null;
        for (CitiesModel element: nodes){
            if (element.getName().equals("Kyiv")){
                temp = element;
            }
        }return temp;
    }



    @Override
    public String toString() {
        return "Graph{" +
                "cities=" + nodes +
                '}';
    }
}
