package com.solvd.optimalpath.services.algorythm;

import com.solvd.optimalpath.models.CitiesModel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

    public static Graph calculateShortestPathFromSource(Graph graph, CitiesModel source) {
        source.setDistance(0);

        Set<CitiesModel> settledNodes = new HashSet<>();
        Set<CitiesModel> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            CitiesModel currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<CitiesModel, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                CitiesModel adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static CitiesModel getLowestDistanceNode(Set < CitiesModel > unsettledNodes) {
        CitiesModel lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (CitiesModel node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(CitiesModel evaluationNode,
                                                 Integer edgeWeigh, CitiesModel sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<CitiesModel> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}
