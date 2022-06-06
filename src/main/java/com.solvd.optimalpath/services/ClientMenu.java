package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.ClientsModel;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ClientMenu {
    private static final Logger LOGGER = LogManager.getLogger(ClientMenu.class);

    public static void start0() {
        int animalsId = 0;
        int airlinesId = 0;
        int citiesId = 0;
        int clientsId = 0;
        String [] personal = new String[5];


        Scanner in = new Scanner(System.in);
        LOGGER.info("Please choose a mode of work you need:");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose administrate mode");
        LOGGER.info("Press 2 to choose customs mode");
        LOGGER.info("Press 3 to see information about program");
        LOGGER.info("Press 4 to EXIT from program\n");
        int optionClass = 0;
        try {
            optionClass = in.nextInt();
            switch (optionClass) {
                case 1:
                    try {
                        LOGGER.info("Please choose a mode of administrator you need:");
                        LOGGER.info("----------------------------------------------------------");
                        LOGGER.info("Press 1 choose load new date");
                        LOGGER.info("Press 2 choose information about free place");
                        LOGGER.info("Press 3 to back to main menu");
                        LOGGER.info("Press 4 to EXIT from program");
                        try {
                            optionClass = in.nextInt();
                            switch (optionClass) {
                                case 1:
                                    inputDate();
                                case 2:
                                    info();
                                    start0();
                                case 3:
                                    start0();
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
                    } catch (InputMismatchException e) {
                        LOGGER.error("Exception in you method!");
                    }
                    break;
                case 2:
                    int [] seatsAndType = new int[2];
                    citiesId = start();
                    seatsAndType = chooseYourSeat();
                    animalsId = animals();
                    personal = inputPersonalInfo();
                    System.out.println("sdfsdfsdf" + citiesId + seatsAndType[0] + seatsAndType[1] + animalsId + Arrays.toString(personal));
                    //chooseYourSeat();
                    break;
                case 3:
                    infoAboutSystem();
                    start0();
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

    public static int start() {
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
        return cityId;
    }

    public static int[] chooseYourSeat() {

        int [] seatAndClass = new int[2];
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
                    seatAndClass[0] = Integer.parseInt(line);
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
                        seatAndClass[1] = Integer.parseInt(temp);
                        return seatAndClass;
                    }
                }
                case "2" -> {
                    seatAndClass[0] = Integer.parseInt(line);
                    seatAndClass[1] = generateRandomPlace();
                    return seatAndClass;
                }
                case "3" -> start0();
                case "4" -> LOGGER.info("We should add here exit OR something else");
                default -> LOGGER.info("Incorrect option selected. Please try again.");
            }
        }

        return seatAndClass;
    }

    public static int animals() {

        LOGGER.info("Please choose will be fly any animals with you:");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose no");
        LOGGER.info("Press 2 to choose yes");

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        if (!line.matches("[1-2]")) {
            System.out.println("Wrong input, please try again");
            chooseYourSeat();
        } else {

            switch (line) {
                case "1" -> {
                    return 0;
                }
                case "2" -> {
                    LOGGER.info("Please choose a type of animal:");
                    LOGGER.info("----------------------------------------------------------");
                    LOGGER.info("Press 1 to choose Cat");
                    LOGGER.info("Press 2 to choose Dog");
                    LOGGER.info("Press 3 to choose Rabbit");
                    LOGGER.info("Press 4 to choose Chinchilla");
                    LOGGER.info("Press 5 to choose Mouse");
                    Scanner in1 = new Scanner(System.in);
                    String type;
                    type = in.nextLine();
                    return Integer.parseInt(type);
                }
            }
        }

        return 0;
    }

    public static String[] inputPersonalInfo() {
        String [] personal = new String[4];
        Scanner in = new Scanner(System.in);
        String firstName;
        String lastName;
        String passportNum;
        String phoneNum;
        int id=22;

        LOGGER.info("Please input your personal date:");
        LOGGER.info("*************************************************");
        LOGGER.info("What is your surname");
        firstName = in.nextLine();
        personal[0] = firstName;

        LOGGER.info("What is your last name");
        lastName = in.nextLine();
        personal[1] = lastName;

        LOGGER.info("What is your passportNum");
        passportNum = in.nextLine();
        personal[2] = passportNum;

        LOGGER.info("What is your phoneNum");
        phoneNum = in.nextLine();
        personal[3] = phoneNum;

        //ClientsModel clientsModel = new ClientsModel(id, firstName, lastName, passportNum, phoneNum, 22);

        return (personal);
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

    public static int generateRandomPlace() {
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
        return min;
    }

    public static void info() {
        LOGGER.info("Number of free seats on the flight number MH18 is 45");
    }

    public static void inputDate() {
        LOGGER.info("Please, input new date");
        start0();
    }

    public static void infoAboutSystem() {
        LOGGER.info("Version number 1.0");
        start0();
    }

}