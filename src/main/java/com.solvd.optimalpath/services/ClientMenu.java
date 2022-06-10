package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.*;
import com.solvd.optimalpath.enums.Drinks;
import com.solvd.optimalpath.enums.Menu;
import com.solvd.optimalpath.interfaces.IAirlinesDao;
import com.solvd.optimalpath.interfaces.IAnimalsDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.interfaces.ITicketsDao;
import com.solvd.optimalpath.models.*;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import com.solvd.optimalpath.services.algorythm.Weather.Main;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherData;
import com.solvd.optimalpath.services.algorythm.Weather.WeatherMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ClientMenu {
    private static final Logger LOGGER = LogManager.getLogger(ClientMenu.class);
    private static TicketsModel ticket = new TicketsModel();
    private static ITicketsDao iTicketsDao = new TicketsDao();


    public static void start() {

        ICitiesDao iCitiesDao = new CitiesDao();

        LOGGER.info("Welcome to our airport! Choose a city in which you want to fly:");
        LOGGER.info("------------------------------------------");
        CitiesDao citiesDao = new CitiesDao();
        List<CitiesModel> citiesNames = citiesDao.getALLCities();
        int i = 1;
        for (CitiesModel element : citiesNames) {
            LOGGER.info("Press " + i++ + " if you choose " + element.getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("If you want to EXIT from program press 0");
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        int cityId = 0;
        if (!line.matches("[0-9]|1[0-9]|2[0-3]")) {
            LOGGER.info("Wrong input, please try again");
            start();
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
                WeatherMethods.createCityRequest(iCitiesDao.getCitiesById(number).getLatitude(), iCitiesDao.getCitiesById(number).getLongitude());
                WeatherData weatherData = WeatherMethods.readFromJson();
                LOGGER.info(weatherData);
            }
        }
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
        if (!line.matches("[1-4]")) {
            LOGGER.info("Wrong input, please try again");
            chooseYourSeat();
        } else {
            switch (line) {
                case "1" -> {
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
        for (int i = 0; i < len; i++) a = String.valueOf(sb.append(chars.charAt(rnd.nextInt(chars.length()))) + "1");
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
        for (int i = 0; i < len; i++) sb.append(chars.charAt(rnd.nextInt(chars.length())));
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        LOGGER.info("Your seat is: " + sb.toString() + random_int);
        ticket.setSeatsNum(sb.toString() + random_int);
        //LOGGER.info(ticket);
    }

    public static void foodTicket() {
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


    public static void animalTicket() {
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
                        LOGGER.info("Your ticket will expensive on 50  UAH");
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

    public static void userTicket() {
        LOGGER.info("Do you want to by this ticket?");
        LOGGER.info("------------------------------------------");
        LOGGER.info("Press 1, if YES");
        LOGGER.info("Press 2 if NO");
        LOGGER.info("Press 3 to back to main menu");
        LOGGER.info("Press 4 to EXIT from program");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (!line.matches("[1-4]")) {
            LOGGER.info("Wrong input, please try again");
            userTicket();
        } else {
            switch (line) {
                case "1" -> {
                    ClientsModel clientsModel = new ClientsModel();
                    ClientsDao clientsDao = new ClientsDao();
                    LOGGER.info("Please enter your personal data:");
                    LOGGER.info("-------------------------------------");
                    Scanner inClient = new Scanner(System.in);
                    String clientName = inClient.nextLine();
                    LOGGER.info("Please input you name:");
                    String clientLastName = inClient.nextLine();
                    LOGGER.info("Please input you surname:");
                    String clientPassportNum = inClient.nextLine();
                    LOGGER.info("Please input you passport number:");
                    String clientPhoneNum = inClient.nextLine();
                    LOGGER.info("Please input you phone  number:");
                    clientsModel.setFirstName(clientName);
                    clientsModel.setLastName(clientLastName);
                    clientsModel.setPassportNum(clientPassportNum);
                    clientsModel.setPhoneNum(clientPhoneNum);
                    clientsDao.createClients(clientsModel);
                    //iTicketsDao.createTickets(ticket);//price,seatsNum,destinationCity
                    LOGGER.info("Thank you for your choice, your ticket will send in JSON file on you phone number.");
                    LOGGER.info("Do you want to buy another one ticket?");
                }
                case "2" -> {
                    LOGGER.info("Do you want to choose some another direction?");
                    start();
                }
                case "3" -> start();
                case "4" -> {
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

}