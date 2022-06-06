package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.AirlinesDao;
import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.interfaces.IAirlinesDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClientMenu {
    private static final Logger LOGGER = LogManager.getLogger(ClientMenu.class);

    public static void start() {
        // создаьть объект тикет
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
        LOGGER.info("If you want to EXIT from program press 0");
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        int cityId = 0;
        if (!line.matches("[0-9]|1[0-9]|2[0-3]")) {
            System.out.println("Wrong input, please try again");
            start();
        } else {
            cityId = Integer.parseInt(line);
            showInfo(cityId);
        }
    }

    public static void chooseYourSeat() {
        LOGGER.info("Please choose class in which you want to fly:");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose business class");
        LOGGER.info("Press 2 to choose economy class");
        LOGGER.info("Press 3 to back to main menu");
        LOGGER.info("Press 4 to EXIT from program");

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        if (!line.matches("[0-4]")) {
            System.out.println("Wrong input, please try again");
            chooseYourSeat();
        } else {
            switch (line) {
                case "1" -> {
                    LOGGER.info("Please choose a place in the cabin where you want to fly:");
                    LOGGER.info("----------------------------------------------------------");
                    LOGGER.info("Press 1 to choose A1");
                    LOGGER.info("Press 2 to choose B1");
                    LOGGER.info("Press 3 to choose C1");
                    LOGGER.info("Press 4 to choose D1");
                    LOGGER.info("Press 5 to choose E1");
                    LOGGER.info("Press 6 to choose F1");
                    LOGGER.info("Press 7 to back to main menu");
                    LOGGER.info("Press 8 to EXIT from program");
                    String temp = in.nextLine();
                    if (!line.matches("[1-8]")) {
                        System.out.println("Wrong input, please try again");
                        chooseYourSeat();
                    } else {
                        LOGGER.info("we should add here method saving into DB place!");
                    }
                }
                case "2" -> generateRandomPlace();
                case "3" -> start();
                case "4" -> LOGGER.info("We should add here exit OR something else");
                default -> LOGGER.info("Incorrect option selected. Please try again.");
            }
        }
    }

    public static void showInfo(int number) {
        IAirlinesDao airlinesDao = new AirlinesDao();
        Graph graph = Initialization.addCitiesFromDB();

        graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());

        for (CitiesModel nod : graph.getNodes()) {
            if (nod.getId() == number) {
                System.out.println("distance is  " + nod.getDistance() + " km to " + nod.getName());
                List<CitiesModel> list = nod.getShortestPath();
                System.out.println("Your paths through");
                for (CitiesModel ele : list) {
                    LOGGER.info(ele.getName());
                    LOGGER.info("-->");
                }
                ICitiesDao iCity = new CitiesDao();
                LOGGER.info(iCity.getCitiesById(number).getName()+ "your airline is: "+airlinesDao.getAirlinesById(number) );
                System.out.println("-------------");
            }
        }
        chooseYourSeat();
    }

    public static void generateRandomPlace() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int min = 30;
        int max = 550;
        int len = 1;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        LOGGER.info(random_int + sb.toString());
    }

}