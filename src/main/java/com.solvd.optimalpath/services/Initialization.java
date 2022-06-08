package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.services.algorythm.Graph;

import java.util.ArrayList;
import java.util.List;

public class Initialization {


    public static Graph addCitiesFromDB(){

        ICitiesDao iCitiesDao = new CitiesDao();
        CitiesModel nodeKYI = iCitiesDao.getCitiesById(1);//kyiv
        CitiesModel nodeDNI = iCitiesDao.getCitiesById(2);//dnipro
        CitiesModel nodeVIN = iCitiesDao.getCitiesById(3);//vinny
        CitiesModel nodeLUC = iCitiesDao.getCitiesById(4);//lutsk
        CitiesModel nodeLVI = iCitiesDao.getCitiesById(5);//lviv
        CitiesModel nodeKHM = iCitiesDao.getCitiesById(6);//KHMel
        CitiesModel nodeUZH = iCitiesDao.getCitiesById(7);//Uzhgot
        CitiesModel nodeKal = iCitiesDao.getCitiesById(8);//Kalush
        CitiesModel nodeIF = iCitiesDao.getCitiesById(9);//I-F
        CitiesModel nodeCtsy = iCitiesDao.getCitiesById(10);//Chernovstvy
        CitiesModel nodeKryv = iCitiesDao.getCitiesById(11);//Kryvoy
        CitiesModel nodeOdes = iCitiesDao.getCitiesById(12);//Odessa
        CitiesModel nodeMykol = iCitiesDao.getCitiesById(13);//Mykol
        CitiesModel nodeKher = iCitiesDao.getCitiesById(14);//Khers
        CitiesModel nodeSymph = iCitiesDao.getCitiesById(15);//Symp
        CitiesModel nodeSevas = iCitiesDao.getCitiesById(16);//Sevat
        CitiesModel nodeKhar = iCitiesDao.getCitiesById(17);//Kharj
        CitiesModel nodeCher = iCitiesDao.getCitiesById(18);//Chernigiv
        CitiesModel nodeSym = iCitiesDao.getCitiesById(19);//Sym
        CitiesModel nodeLyg = iCitiesDao.getCitiesById(20);//Lyg
        CitiesModel nodeDON = iCitiesDao.getCitiesById(21);//Donetsk
        CitiesModel nodeZAP = iCitiesDao.getCitiesById(22);//zap
        CitiesModel nodeMarik = iCitiesDao.getCitiesById(23);//Marik

        nodeKYI.addDestination(nodeDNI, DistanceCalculation.distance_Between_LatLong(nodeKYI.getLatitude(),nodeKYI.getLongitude(),nodeDNI.getLatitude(),nodeDNI.getLongitude()));
        nodeKYI.addDestination(nodeVIN, DistanceCalculation.distance_Between_LatLong(nodeKYI.getLatitude(),nodeKYI.getLongitude(),nodeVIN.getLatitude(),nodeVIN.getLongitude()));
        nodeDNI.addDestination(nodeKhar, DistanceCalculation.distance_Between_LatLong(nodeDNI.getLatitude(),nodeDNI.getLongitude(),nodeKhar.getLatitude(),nodeKhar.getLongitude()));
        nodeDNI.addDestination(nodeZAP, DistanceCalculation.distance_Between_LatLong(nodeDNI.getLatitude(),nodeDNI.getLongitude(),nodeZAP.getLatitude(),nodeZAP.getLongitude()));
        nodeKhar.addDestination(nodeCher, DistanceCalculation.distance_Between_LatLong(nodeKhar.getLatitude(),nodeKhar.getLongitude(),nodeCher.getLatitude(),nodeCher.getLongitude()));
        nodeKhar.addDestination(nodeSym, DistanceCalculation.distance_Between_LatLong(nodeKhar.getLatitude(),nodeKhar.getLongitude(),nodeSym.getLatitude(),nodeSym.getLongitude()));
        nodeSym.addDestination(nodeLyg, DistanceCalculation.distance_Between_LatLong(nodeSym.getLatitude(),nodeSym.getLongitude(),nodeLyg.getLatitude(),nodeLyg.getLongitude()));
        nodeZAP.addDestination(nodeDON, DistanceCalculation.distance_Between_LatLong(nodeZAP.getLatitude(),nodeZAP.getLongitude(),nodeDON.getLatitude(),nodeDON.getLongitude()));
        nodeZAP.addDestination(nodeMarik, DistanceCalculation.distance_Between_LatLong(nodeZAP.getLatitude(),nodeZAP.getLongitude(),nodeMarik.getLatitude(),nodeMarik.getLongitude()));
        nodeDON.addDestination(nodeLyg, DistanceCalculation.distance_Between_LatLong(nodeDON.getLatitude(),nodeDON.getLongitude(),nodeLyg.getLatitude(),nodeLyg.getLongitude()));
        nodeMarik.addDestination(nodeSevas, DistanceCalculation.distance_Between_LatLong(nodeMarik.getLatitude(),nodeMarik.getLongitude(),nodeSevas.getLatitude(),nodeSevas.getLongitude()));
        nodeVIN.addDestination(nodeKryv, DistanceCalculation.distance_Between_LatLong(nodeVIN.getLatitude(),nodeVIN.getLongitude(),nodeKryv.getLatitude(),nodeKryv.getLongitude()));
        nodeKryv.addDestination(nodeKher, DistanceCalculation.distance_Between_LatLong(nodeKryv.getLatitude(),nodeKryv.getLongitude(),nodeKher.getLatitude(),nodeKher.getLongitude()));
        nodeKryv.addDestination(nodeOdes, DistanceCalculation.distance_Between_LatLong(nodeKryv.getLatitude(),nodeKryv.getLongitude(),nodeOdes.getLatitude(),nodeOdes.getLongitude()));
        nodeOdes.addDestination(nodeMykol, DistanceCalculation.distance_Between_LatLong(nodeOdes.getLatitude(),nodeOdes.getLongitude(),nodeMykol.getLatitude(),nodeMykol.getLongitude()));
        nodeKher.addDestination(nodeSevas, DistanceCalculation.distance_Between_LatLong(nodeKher.getLatitude(),nodeKher.getLongitude(),nodeSevas.getLatitude(),nodeSevas.getLongitude()));
        nodeKher.addDestination(nodeSymph, DistanceCalculation.distance_Between_LatLong(nodeKher.getLatitude(),nodeKher.getLongitude(),nodeSymph.getLatitude(),nodeSymph.getLongitude()));
        nodeSymph.addDestination(nodeMykol, DistanceCalculation.distance_Between_LatLong(nodeSymph.getLatitude(),nodeSymph.getLongitude(),nodeMykol.getLatitude(),nodeMykol.getLongitude()));
        nodeVIN.addDestination(nodeLUC, DistanceCalculation.distance_Between_LatLong(nodeVIN.getLatitude(),nodeVIN.getLongitude(),nodeLUC.getLatitude(),nodeLUC.getLongitude()));
        nodeLUC.addDestination(nodeUZH, DistanceCalculation.distance_Between_LatLong(nodeLUC.getLatitude(),nodeLUC.getLongitude(),nodeUZH.getLatitude(),nodeUZH.getLongitude()));
        nodeVIN.addDestination(nodeLVI, DistanceCalculation.distance_Between_LatLong(nodeVIN.getLatitude(),nodeVIN.getLongitude(),nodeLVI.getLatitude(),nodeLVI.getLongitude()));
        nodeLVI.addDestination(nodeUZH, DistanceCalculation.distance_Between_LatLong(nodeLVI.getLatitude(),nodeLVI.getLongitude(),nodeUZH.getLatitude(),nodeUZH.getLongitude()));
        nodeLVI.addDestination(nodeKal, DistanceCalculation.distance_Between_LatLong(nodeLVI.getLatitude(),nodeLVI.getLongitude(),nodeKal.getLatitude(),nodeKal.getLongitude()));
        nodeVIN.addDestination(nodeKHM, DistanceCalculation.distance_Between_LatLong(nodeVIN.getLatitude(),nodeVIN.getLongitude(),nodeKHM.getLatitude(),nodeKHM.getLongitude()));
        nodeKHM.addDestination(nodeIF, DistanceCalculation.distance_Between_LatLong(nodeKHM.getLatitude(),nodeKHM.getLongitude(),nodeIF.getLatitude(),nodeIF.getLongitude()));
        nodeIF.addDestination(nodeKal, DistanceCalculation.distance_Between_LatLong(nodeIF.getLatitude(),nodeIF.getLongitude(),nodeKal.getLatitude(),nodeKal.getLongitude()));
        nodeKHM.addDestination(nodeCtsy, DistanceCalculation.distance_Between_LatLong(nodeKHM.getLatitude(),nodeKHM.getLongitude(),nodeCtsy.getLatitude(),nodeCtsy.getLongitude()));

        Graph graph = new Graph();
        graph.addNode(nodeKYI);
        graph.addNode(nodeDNI);
        graph.addNode(nodeVIN);
        graph.addNode(nodeLUC);
        graph.addNode(nodeLVI);
        graph.addNode(nodeKHM);
        graph.addNode(nodeUZH);
        graph.addNode(nodeKal);
        graph.addNode(nodeIF);
        graph.addNode(nodeCtsy);
        graph.addNode(nodeKryv);
        graph.addNode(nodeOdes);
        graph.addNode(nodeMykol);
        graph.addNode(nodeKher);
        graph.addNode(nodeSymph);
        graph.addNode(nodeSevas);
        graph.addNode(nodeKhar);
        graph.addNode(nodeCher);
        graph.addNode(nodeSym);
        graph.addNode(nodeLyg);
        graph.addNode(nodeDON);
        graph.addNode(nodeZAP);
        graph.addNode(nodeMarik);

        return graph;
    }

}
