package com.solvd.optimalpath.services;


import com.solvd.optimalpath.configuration.DataBaseConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.optimalpath.dao.*;
import com.solvd.optimalpath.enums.Drinks;
import com.solvd.optimalpath.enums.Menu;
import com.solvd.optimalpath.interfaces.*;
import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClientsModel;
import com.solvd.optimalpath.models.TicketsModel;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import com.solvd.optimalpath.services.algorythm.Weather.Main;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherData;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClientMenu {
    private static final Logger LOGGER = LogManager.getLogger(ClientMenu.class);
    private static TicketsModel ticket = new TicketsModel();
    private static ITicketsDao iTicketsDao = new TicketsDao();
    public static int delay = 1000;

    public static void initialisationUser() {
        LOGGER.info("Make your choice, you are an Administrator or an Airport visitor: ");
        LOGGER.info("Press 1, if you are Administrator");
        LOGGER.info("Press 2, if you are Visitor of Airport");
        LOGGER.info("Press 3, to EXIT from program");
        Scanner inputFirstData = new Scanner(System.in);
        String startString;
        startString = inputFirstData.nextLine();
        if (!startString.matches("[1-3]")) {
            LOGGER.info("Wrong input, please try again");
            start();
        } else {
            switch (startString) {
                case "1" -> AdminMenu.supervisor();

                case "2" -> start();

                case "3" -> {
                    LOGGER.info("Thank you for visiting our Airport, will be glad see you soon");
                    System.exit(0);
                }
            }
        }
    }

    public static void start() {

        ICitiesDao iCitiesDao = new CitiesDao();
        sleepNSeconds(2);
        LOGGER.info("Welcome to our airport! Choose a city in which you want to fly:");
        LOGGER.info("------------------------------------------");
        CitiesDao citiesDao = new CitiesDao();
        List<CitiesModel> citiesNames = citiesDao.getALLCities();
        int i = 2;
        for (CitiesModel element : citiesNames) {
            if (element.getStandartTariff() != 0) {
                LOGGER.info("Press " + i++ + " if you choose " + element.getName() + " Minimum price is: " + element.getStandartTariff() + " UAH");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info("If you are not interested in travelling and want to leave our ticket office just press 0");
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
                LOGGER.info("We hope to see you soon. Have a nice day!");
                System.exit(0);
            }
            if (line.equals("1")) {
                LOGGER.info("So sorry, but you are in Kyiv right now, plz choose option according to provided list");
                sleepNSeconds(2);
                start();
            }
            cityId = Integer.parseInt(line);
            ticket.setId(cityId);
            IAirlinesDao iAirlinesDao = new AirlinesDao();
            ticket.setPrice(iCitiesDao.getCitiesById(cityId).getStandartTariff());
            ticket.setAirlineName(iAirlinesDao.getAirlinesById(cityId).getName());
            ticket.setCityArrival(iCitiesDao.getCitiesById(cityId).getName());

            double distance = DistanceCalculation.distance_Between_LatLong(iCitiesDao.getCitiesById(1).getLatitude(), iCitiesDao.getCitiesById(1).getLongitude(), iCitiesDao.getCitiesById(cityId).getLatitude(), iCitiesDao.getCitiesById(cityId).getLongitude());

            ticket.setTimeFlight(Math.round((distance / 950.00 + 0.85) * 100) / 100.00);//
            if (ticket.getTimeFlight() > 1.6) {
                ticket.setTimeFlight(2.0 + ticket.getTimeFlight() - 1.6);
            }

            showInfo(cityId);
            sleepNSeconds(5);
        }
    }

    public static void showInfo(int number) {
        IAirlinesDao airlinesDao = new AirlinesDao();
        ICitiesDao iCitiesDao = new CitiesDao();
        Graph graph = Initialization.addCitiesFromDB();

        DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getIt());

        for (CitiesModel nod : graph.getNodes()) {
            if (nod.getId() == number) {
                LOGGER.info("Your travel distance is  " + nod.getDistance() + " km to " + nod.getName() + " city.");
                List<CitiesModel> list = nod.getShortestPath();
                LOGGER.info("Your flight will pass through following cities:");
                for (CitiesModel ele : list) {
                    LOGGER.info(ele.getName());
                    LOGGER.info("-->");
                }
                ICitiesDao iCity = new CitiesDao();

                LOGGER.info(" your flight will take: " + ticket.getTimeFlight() + " hours to " + iCity.getCitiesById(number).getName() + " and your airline is: " + airlinesDao.getAirlinesById(number).getName() + ".");
                ticket.setAirlinesModel(airlinesDao.getAirlinesById(number));
                ticket.setCitiesModel(iCity.getCitiesById(number));
                LOGGER.info("*************************************************\n");
                sleepNSeconds(1);
                WeatherMethods.createCityRequest(iCitiesDao.getCitiesById(number).getLatitude(), iCitiesDao.getCitiesById(number).getLongitude());
                WeatherData weatherData = WeatherMethods.readFromJson();
                LOGGER.info(weatherData);
            }
        }
        sleepNSeconds(1);
        chooseYourSeat(number);
    }

    public static void chooseYourSeat(int number) {
        IClassTypesDao iClassTypesDao = new ClassTypesDao();
        LOGGER.info("Please choose class in which you want to fly:\n");
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
            chooseYourSeat(number);
        } else {
            switch (line) {
                case "1" -> {
                    ticket.setPrice(ticket.getPrice() + 300);
                    ticket.setClassTypesModel(iClassTypesDao.getClassTypesById((number * 2) - 1));
                    generateRandomBusinessPlace();
                    foodTicket();
                }
                case "2" -> {
                    generateRandomPlace();
                    ticket.setClassTypesModel(iClassTypesDao.getClassTypesById((number * 2)));
                    foodTicket();
                }
                case "3" -> start();
                case "4" -> {
                    LOGGER.info("Thank you for visiting our Airport, will be glad see you soon");
                    System.exit(0);
                }
                default -> {
                    LOGGER.info("Incorrect option selected. Please try again.");
                    chooseYourSeat(number);
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
        LOGGER.info("Your seat is: " + a);
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

    public static void foodTicket() {
        LOGGER.info("Would you like to have a lunch, during your flight?");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose meal from our delicious menu");
        LOGGER.info("Press 2 if you are not interested");
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

    public static void animalTicket() {

        LOGGER.info("Do you plan to take animal with you?");
        LOGGER.info("------------------------------------------");
        LOGGER.info("Press 1, if Yes");
        LOGGER.info("Press 2, if No");
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
                    LOGGER.info("Please specify animal`s type");
                    IAnimalsDao iAnimalsDao = new AnimalsDao();

                    Scanner input = new Scanner(System.in);
                    String lineAnimal;
                    lineAnimal = input.nextLine();
                    ticket.setAnimal(lineAnimal);
                    animalTicketPrice();
                }
                case "2" -> {
                    LOGGER.info("You are travelling without animal and your actual ticket price is:");
                    LOGGER.info(ticket.getPrice());
                    userTicket();
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

    public static void animalTicketPrice() {
        LOGGER.info("Please type animal`s weight");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int weight = Integer.MIN_VALUE;
        if (!line.matches("[1-9]|1[0-9]|2[0-9]")) {
            LOGGER.info("wrong weight please choose weight between 1-29 kg without grams");
            animalTicketPrice();
        }
        weight = Integer.parseInt(line);
        if (weight <= 5) {
            LOGGER.info("50 UAH will be added to your ticket price");
            ticket.setPrice(ticket.getPrice() + 50);
        } else {
            LOGGER.info("100 UAH will be added to your ticket price");
            ticket.setPrice(ticket.getPrice() + 100);
        }
        userTicket();
    }

    public static void sleepNSeconds(int n) {
        try {
            Thread.sleep(n * delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ticketToFile(TicketsModel ticket) {
        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File("src/main/resources/projectMaterials/jsonFiles/ticket.json");
        try {
            if (!file.exists())
                file.createNewFile();
            om.writeValue(file, ticket);
            LOGGER.info("created!");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static void userTicket() {
        LOGGER.info("Do you want to by this ticket?");
        LOGGER.info("------------------------------------------");
        LOGGER.info("Press 1, if YES");
        LOGGER.info("Press 2 if, you want to choose another direction");
        LOGGER.info("Press 3 to EXIT from program");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (!line.matches("[1-3]")) {
            LOGGER.info("Wrong input, please try again22222222");
            userTicket();
        } else {
            switch (line) {
                case "1" -> {
                    ClientsModel clientsModel = new ClientsModel();
                    ClientsDao clientsDao = new ClientsDao();
                    LOGGER.info("Please enter your personal data:");
                    LOGGER.info("-------------------------------------");
                    Scanner inClient = new Scanner(System.in);
                    LOGGER.info("Please input you name:");
                    String clientName = inClient.nextLine();
                    ticket.setName(clientName);
                    LOGGER.info("Please input you surname:");
                    String clientLastName = inClient.nextLine();
                    ticket.setLastName(clientLastName);
                    LOGGER.info("Please input you passport number:");
                    String clientPassportNum = inClient.nextLine();
                    ticket.setPassport(clientPassportNum);
                    LOGGER.info("Please input you phone  number:");
                    String clientPhoneNum = inClient.nextLine();
                    ticket.setPhone(clientPhoneNum);
                    clientsModel.setFirstName(clientName);
                    clientsModel.setLastName(clientLastName);
                    clientsModel.setPassportNum(clientPassportNum);
                    clientsModel.setPhoneNum(clientPhoneNum);
                    clientsDao.createClients(clientsModel);
                    List<ClientsModel> list = clientsDao.getALLClients();
                    int id = Integer.MIN_VALUE;
                    for (ClientsModel element : list) {
                        if (element.getLastName().equals(clientsModel.getLastName())) {
                            id = element.getId();
                        }
                    }
                    clientsModel.setId(id);
                    ticket.setClientsModel(clientsModel);
                    ticketToDB(ticket);
                    LOGGER.info("Your final ticket price is: " + ticket.getPrice() + " UAH" + "\nThank you for your choice, your ticket will send in JSON file on you phone number.");
                    ticketToFile(ticket);
                }

                case "2" -> {
                    start();
                }
                case "3" -> {
                    LOGGER.info("Thank you for visiting our Airport, will be glad see you soon");
                    System.exit(0);
                }
                default -> {
                    LOGGER.info("Incorrect option selected. Please try again.");
                    userTicket();
                }
            }
        }

    }

    public static void ticketToDB(TicketsModel ticket) {
        temp(ticket);
//        ITicketsDao iTicketsDao = new TicketsDao();
//        iTicketsDao.createTickets(ticket);

    }

    public static void temp(TicketsModel ticket) {
        ITicketsDao iTicketsDao = new TicketsDao();
        iTicketsDao.createTickets(ticket);
        LOGGER.info("hello from here");
    }
}
