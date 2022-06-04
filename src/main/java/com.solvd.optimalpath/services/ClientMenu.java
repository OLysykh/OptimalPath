package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.CitiesDao;
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
            System.out.println("try again");
            start();
        } else {
            cityId = Integer.parseInt(line);
            showInfo(cityId);
        }
        LOGGER.info("Please choose class in which you want to fly:");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose business class");
        LOGGER.info("Press 2 to choose economy class");
        LOGGER.info("Press 3 to back to main menu");
        LOGGER.info("Press 4 to EXIT from program");
        int optionClass = 0;
        try {
            optionClass = in.nextInt();
            switch (optionClass) {
                case 1:
                    try {
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
                        try {
                            optionClass = in.nextInt();
                            switch (optionClass) {
                                case 1:
                                    try {

                                    } catch (InputMismatchException e) {
                                        LOGGER.error("Exception in you method!");
                                    }
                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                case 4:

                                    break;
                                case 7:
                                    start();
                                    break;
                                case 8:
                                    LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                                    System.exit(0);
                                default:
                                    LOGGER.info("Incorrect option selected. Please try again.");
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            LOGGER.error("Invalid selection! Please try again! Enter any character to continue");
                            optionClass = -1;
                            in.next();
                        } catch (IndexOutOfBoundsException e) {
                            LOGGER.error("IndexOutOfBounds! You have tried to reference an unassigned index!");
                        } catch (Exception e) {
                            LOGGER.error("General type of exception was thrown!" + e.getClass());
                        }
                    } catch (InputMismatchException e) {
                        LOGGER.error("Exception in you method!");
                    }
                    break;
                case 2:
                    generateRandomPlace();
                    //add  this place to ticket
                    break;
                case 3:
                    start();
                    break;
                case 4:
                    LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                    System.exit(0);
                default:
                    LOGGER.info("Incorrect option selected. Please try again.");
                    break;
            }
        } catch (InputMismatchException e) {
            LOGGER.error("Invalid selection! Please try again! Enter any character to continue");
            optionClass = -1;
            in.next();
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("IndexOutOfBounds! You have tried to reference an unassigned index!");
        } catch (Exception e) {
            LOGGER.error("General type of exception was thrown!" + e.getClass());
        }
    }

    public static void showInfo(int number) {
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
                LOGGER.info(iCity.getCitiesById(number).getName());
                System.out.println("-------------");
            }
        }
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
