package com.solvd.optimalpath.menu;

import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.services.Initialization;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);

    public static void start() {
ICitiesDao iCitiesDao = new CitiesDao();
        System.out.println(iCitiesDao.getCitiesById(7));
        LOGGER.info("Welcome to our airport! Choose a city in which you want to fly:");
        LOGGER.info("------------------------------------------");
        LOGGER.info("Press 2 if you choose Dnipro");
        LOGGER.info("Press 3 if you choose Vinnytsia");
        LOGGER.info("Press 4 if you choose Lutsk");
        LOGGER.info("Press 5 if you choose Lviv");
        LOGGER.info("Press 6 if you choose Khmelnytskiy");
        LOGGER.info("Press 7 if you choose Uzhhorod");
        LOGGER.info("Press 8 if you choose Kalush");
        LOGGER.info("Press 9 if you choose Ivano-Frankivsk");
        LOGGER.info("Press 10 if you choose Chernivtsi");
        LOGGER.info("Press 11 if you choose Kryvyi Rih");
        LOGGER.info("Press 12 if you choose Odesa");
        LOGGER.info("Press 13 if you choose Mykolaiv");
        LOGGER.info("Press 14 if you choose Kherson");
        LOGGER.info("Press 15 if you choose Simferopol");
        LOGGER.info("Press 16 if you choose Sevastopol");
        LOGGER.info("Press 17 if you choose Kharkiv");
        LOGGER.info("Press 18 if you choose Chernihiv");
        LOGGER.info("Press 19 if you choose Sumy");
        LOGGER.info("Press 20 if you choose Luhansk");
        LOGGER.info("Press 21 if you choose Donetsk");
        LOGGER.info("Press 22 if you choose Zaporizhzhia");
        LOGGER.info("Press 23 if you choose Mariupol");
        LOGGER.info("If you want to EXIT press 0");
        //int option = 0;
        Scanner in = new Scanner(System.in);
            //option = in.nextInt();
            //Scanner scnaner = new Scanner(System.in);
            String line;
            line = in.nextLine();
            int cityId=0;
            if (! line.matches("[0-9]|1[0-9]|2[0-3]")){
                System.out.println("try again");
                start();
            }else{
                cityId = Integer.parseInt(line);
                showInfo(cityId);
            }
     }

    public static void showInfo(int number) {
/*        Graph graph = Initialization.addCitiesFromDB();

        graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());

        for (CitiesModel nod : graph.getNodes()) {
            LOGGER.info("distance is  " + nod.getDistance() + " km to " + nod.getName());
            List<CitiesModel> list = nod.getShortestPath();
            LOGGER.info("Your paths through");
            for (CitiesModel ele : list) {
                LOGGER.info(ele.getName());
                LOGGER.info("-->");
            }
            System.out.println("-------------");
        }
    }*/
        Graph graph = Initialization.addCitiesFromDB();

        graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());

        for (CitiesModel nod : graph.getNodes()) {
            if (nod.getId() == number) {
                System.out.println("distance is  " + nod.getDistance() + " km to " + nod.getName());
                List<CitiesModel> list = nod.getShortestPath();
                System.out.println("Your paths through");
                for (CitiesModel ele : list) {
                    System.out.println(ele.getName());
                    System.out.println("-->");
                }
                System.out.println("-------------");
            }
        }
    }
}
