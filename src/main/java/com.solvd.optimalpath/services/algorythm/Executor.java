package com.solvd.optimalpath.services.algorythm;

import com.solvd.optimalpath.services.ClientMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {


    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(Executor.class);
//        ICitiesDao iCitiesDao = new CitiesDao();
//        CitiesModel nodeA = iCitiesDao.getCitiesById(1);//kyiv
//        CitiesModel nodeB = iCitiesDao.getCitiesById(2);//dnipro
//        CitiesModel nodeC = iCitiesDao.getCitiesById(3);//vinny
//        CitiesModel nodeD = iCitiesDao.getCitiesById(17);//kharkiv
//        CitiesModel nodeE = iCitiesDao.getCitiesById(22);//zap
//        CitiesModel nodeF = iCitiesDao.getCitiesById(21);//Donetsk
//        CitiesModel nodeG = iCitiesDao.getCitiesById(6);//Donetsk
//
////will be added from SQL
//        nodeA.addDestination(nodeB, DistanceCalculation.distance_Between_LatLong(nodeA.getLatitude(),nodeA.getLongitude(),nodeB.getLatitude(),nodeB.getLongitude())); //adding neighboors
//        nodeA.addDestination(nodeC, DistanceCalculation.distance_Between_LatLong(nodeA.getLatitude(),nodeA.getLongitude(),nodeC.getLatitude(),nodeC.getLongitude()));
//
//        nodeB.addDestination(nodeD, DistanceCalculation.distance_Between_LatLong(nodeB.getLatitude(),nodeB.getLongitude(),nodeD.getLatitude(),nodeD.getLongitude()));
//        nodeB.addDestination(nodeE, DistanceCalculation.distance_Between_LatLong(nodeB.getLatitude(),nodeB.getLongitude(),nodeE.getLatitude(),nodeE.getLongitude()));
//
//        nodeE.addDestination(nodeF, DistanceCalculation.distance_Between_LatLong(nodeE.getLatitude(),nodeE.getLongitude(),nodeF.getLatitude(),nodeF.getLongitude()));
//        nodeC.addDestination(nodeG, DistanceCalculation.distance_Between_LatLong(nodeC.getLatitude(),nodeC.getLongitude(),nodeG.getLatitude(),nodeG.getLongitude()));
//        nodeC.addDestination(nodeE, 10);


         ClientMenu.start0();
/*        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int min = 30;
        int max = 550;
        int len=1;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++){
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(random_int+sb.toString());*/

/*        Graph graph = Initialization.addCitiesFromDB();

        graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());
        int i=0;
        for (CitiesModel nod : graph.getNodes()) {
                if (i ==22) {
                    System.out.println("distance is  " + nod.getDistance() + " km to " + nod.getName());
                    List<CitiesModel> list = nod.getShortestPath();
                    System.out.println("Your paths through");
                    for (CitiesModel ele : list) {
                        System.out.println(ele.getName());
                        System.out.println("-->");
                    }
                    System.out.println("-------------");
                }
            i++;
        }*/
    }
}
