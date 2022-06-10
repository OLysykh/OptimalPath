package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.AirlinesDao;
import com.solvd.optimalpath.dao.AnimalsDao;
import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.dao.TicketsDao;
import com.solvd.optimalpath.enums.Drinks;
import com.solvd.optimalpath.enums.Menu;
import com.solvd.optimalpath.interfaces.IAirlinesDao;
import com.solvd.optimalpath.interfaces.IAnimalsDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.interfaces.ITicketsDao;
import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.TicketsModel;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherData;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClientMenu {
    private static final Logger LOGGER = LogManager.getLogger(ClientMenu.class);
    private static TicketsModel ticket = new TicketsModel();
    private static ITicketsDao iTicketsDao = new TicketsDao();


    public static void start() {

        ICitiesDao iCitiesDao = new CitiesDao();
        sleepNSeconds(2);
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
            LOGGER.info("Wrong input, please try again");
            start();
            sleepNSeconds(3);
        } else {
            if (line.equals("0")) {
                System.exit(0);
            }
            cityId = Integer.parseInt(line);
            ticket.setId(cityId);
            IAirlinesDao iAirlinesDao = new AirlinesDao();
            ticket.setPrice(iCitiesDao.getCitiesById(cityId).getStandartTariff());
            ticket.setAirlineName(iAirlinesDao.getAirlinesById(cityId).getName());
            ticket.setCityArrival(iCitiesDao.getCitiesById(cityId).getName());

            double distance = DistanceCalculation.distance_Between_LatLong(iCitiesDao.getCitiesById(1).getLatitude(), iCitiesDao.getCitiesById(1).getLongitude(), iCitiesDao.getCitiesById(cityId).getLatitude(), iCitiesDao.getCitiesById(cityId).getLongitude());
            ticket.setTimeFlight(Math.round((distance / 950.00 + 0.95) * 100) / 100.00);//
            showInfo(cityId);



            sleepNSeconds(5);

        }
    }

    public static void showInfo(int number) {
        IAirlinesDao airlinesDao = new AirlinesDao();
        ICitiesDao iCitiesDao = new CitiesDao();
        Graph graph = Initialization.addCitiesFromDB();

        graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());

        for (CitiesModel nod : graph.getNodes()) {
            if (nod.getId() == number) {
                LOGGER.info("distance is  " + nod.getDistance() + " km to " + nod.getName());
                List<CitiesModel> list = nod.getShortestPath();
                LOGGER.info("Your paths through");
                for (CitiesModel ele : list) {
                    LOGGER.info(ele.getName());
                    LOGGER.info("-->");
                }
                ICitiesDao iCity = new CitiesDao();
                LOGGER.info(iCity.getCitiesById(number).getName() + " your airline is: " + airlinesDao.getAirlinesById(number) + " your flight will take: " + ticket.getTimeFlight() + " hours");
                LOGGER.info("-------------");
                sleepNSeconds(2);
                WeatherMethods.createCityRequest(iCitiesDao.getCitiesById(number).getLatitude(), iCitiesDao.getCitiesById(number).getLongitude());
                WeatherData weatherData = WeatherMethods.readFromJson();
                LOGGER.info(weatherData);
            }
        }
        sleepNSeconds(4);
        chooseYourSeat();
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
            LOGGER.info("Wrong input, please try again");
            chooseYourSeat();
        } else {
            switch (line) {
                case "1" -> {

                    LOGGER.info("Please choose a place in the cabin where you want to fly:");
                    LOGGER.info("----------------------------------------------------------");
                    // add random to business class;
                    LOGGER.info("Press 1 to choose A1");
                    LOGGER.info("Press 2 to choose B1");
                    LOGGER.info("Press 3 to choose C1");
                    LOGGER.info("Press 4 to choose D1");
                    LOGGER.info("Press 5 to choose E1");
                    LOGGER.info("Press 6 to choose F1");
                    LOGGER.info("Press 7 to back to main menu");
                    LOGGER.info("Press 8 to EXIT from program");
                    String temp = in.nextLine();
                    if (!line.matches("[1-8]")) { //походу є помилка, змінна темп має бути замість лайн
                        System.out.println("Wrong input, please try again");
                        chooseYourSeat();
                    } else {
                        LOGGER.info("we should add here method saving into DB place!");
                        ticket.setPrice(ticket.getPrice()+300);
                        // add to Ticket price+300;
                        // add to ticket place
                    }

                    ticket.setPrice(ticket.getPrice() + 300);
                    generateRandomBusinessPlace();
                    foodTicket();

                }
                case "2" -> {
                    generateRandomPlace();
                    foodTicket();
                }
                case "3" -> start();
                case "4" -> {
                    LOGGER.info("Thank you for visiting our Airport, will be glad see you soon");
                    System.exit(0);
                }
                default -> {
                    LOGGER.info("Incorrect option selected. Please try again.");
                    chooseYourSeat();
                }
            }
        }
    }

    public static void generateRandomBusinessPlace() {
        String chars = "ABCD";
        int len = 1;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        String a = null;
        for (int i = 0; i < len; i++) {
            a = String.valueOf(sb.append(chars.charAt(rnd.nextInt(chars.length()))) + "1");
        }
        ticket.setSeatsNum(a);
    }

    public static void generateRandomPlace() {
        String chars = "ABCDEF";
        int min = 2;
        int max = 40;
        int len = 1;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        LOGGER.info("Your seat is: " + sb.toString() + random_int);
        ticket.setSeatsNum(sb.toString() + random_int);
        //LOGGER.info(ticket);
    }



        public static void foodTicket () {
            LOGGER.info("Do you want added some meal?");
            LOGGER.info("*************************************************");
            LOGGER.info("Press 1 if yes");
            LOGGER.info("Press 2 if no");
            LOGGER.info("Press 3 to back to main menu");
            LOGGER.info("Press 4 to EXIT from program");
            Scanner in = new Scanner(System.in);
            String line;
            line = in.nextLine();
            if (!line.matches("[1-4]")) {
                LOGGER.info("Wrong input, please try again");
                foodTicket();
            } else {
                switch (line) {
                    case "1" -> {
                        LOGGER.info("Please, write number of meal: ");
                        Arrays.stream(Menu.values())
                                .forEach(item -> LOGGER.info(item.ordinal() + " - " + item + " - " + item.getPrice()));
                        Scanner scanner = new Scanner(System.in);
                        int idMeal = scanner.nextInt();
                        Menu selectedMeal = Menu.values()[idMeal];
                        LOGGER.info("Got it. Do you want some drink? Write number of drink: ");


                        Arrays.stream(Drinks.values())
                                .forEach(item -> LOGGER.info(item.ordinal() + " - " + item + " - " + item.getPrice()));

                        int idDrink = scanner.nextInt();
                        Drinks selectedDrink = Drinks.values()[idDrink];
                        LOGGER.info("Selected meal - " + selectedMeal + " and " + selectedDrink);
                        ticket.setPrice(ticket.getPrice() + selectedMeal.getPrice() + selectedDrink.getPrice());
                        LOGGER.info("");
                        animalTicket();
                    }
                    case "2" -> animalTicket();
                    case "3" -> start();
                    case "4" -> System.exit(0);
                    default -> LOGGER.info("Incorrect option selected. Please try again.");
                }
            }
        }

        public static void animalTicket () {
            LOGGER.info("Do you have animals?, Please make your choice: ");
            LOGGER.info("------------------------------------------");
            LOGGER.info("Press 1, if you are have animals");
            LOGGER.info("Press 2 if you haven't animals");
            LOGGER.info("Press 3 to back to main menu");
            LOGGER.info("Press 4 to EXIT from program");

            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if (!line.matches("[1-4]")) {
                LOGGER.info("Wrong input, please try again");
                animalTicket();
            } else {
                switch (line) {
                    case "1" -> {
                        LOGGER.info("Please choose your type of animal:");
                        IAnimalsDao iAnimalsDao = new AnimalsDao();
                        LOGGER.info("----------------------------------------------------------");
                        LOGGER.info("Press 1, if you have a CAT");
                        LOGGER.info("Press 2, if you have a DOG");
                        LOGGER.info("Press 3, if you have a RABBIT");
                        LOGGER.info("Press 4, if you have a CHINCHILLA");
                        LOGGER.info("Press 5, if you have a MOUSE");
                        LOGGER.info("Press 6, if you have a other animal");
                        Scanner input = new Scanner(System.in);
                        String lineAnimal;
                        lineAnimal = input.nextLine();
                        if (!lineAnimal.matches("[1-6]")) {
                            LOGGER.info("Wrong input, please try again");
                            animalTicket();
                        } else if (lineAnimal.matches("6")) {
                            LOGGER.info("Please write type of your animal:");
                            int i = iAnimalsDao.getMaxId();
                            Scanner writeTypeAnimal = new Scanner(System.in);
                            String inputTypeOfAnimal = writeTypeAnimal.nextLine();
                            AnimalsModel animalsModel = new AnimalsModel(++i, inputTypeOfAnimal, new TicketsModel());
                            iAnimalsDao.createAnimals(animalsModel);

                            LOGGER.info(iAnimalsDao.getAnimalsById(i));
                            LOGGER.info("Your ticket will expensive on 50");
                            ticket.setPrice(ticket.getPrice() + 50);
                            LOGGER.info("Price of your ticket = " + ticket.getPrice());
                        } else {
                            LOGGER.info(iAnimalsDao.getAnimalsById(Integer.parseInt(lineAnimal)));
                            LOGGER.info("Your ticket will expensive on 50 UAH");
                            ticket.setPrice(ticket.getPrice() + 50);
                            LOGGER.info("Price of your ticket = " + ticket.getPrice() + " UAH");
                        }
                    }
                    case "2" -> {
                        LOGGER.info("Thank you for your choice, your ticket price:");
                        LOGGER.info(ticket.getPrice());
                    }
                    case "3" -> start();
                    case "4" -> {
                        LOGGER.info("Thank you for visiting our Airport, will be glad see you soon");
                        System.exit(0);
                    }
                    default -> {
                        LOGGER.info("Incorrect option selected. Please try again.");
                        animalTicket();
                    }
                }
            }
        }


        public static int delay = 1000;

        public static void sleepNSeconds(int n) {
        try {
            Thread.sleep(n * delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}