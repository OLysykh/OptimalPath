package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.AirlinesDao;
import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.dao.TicketsDao;
import com.solvd.optimalpath.interfaces.IAirlinesDao;
import com.solvd.optimalpath.interfaces.ICitiesDao;
import com.solvd.optimalpath.interfaces.ITicketsDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.models.TicketsModel;
import com.solvd.optimalpath.services.algorythm.DijkstraAlgorithm;
import com.solvd.optimalpath.services.algorythm.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AdminMenu {
    private static final Logger LOGGER = LogManager.getLogger(AdminMenu.class);

    public static void start0() {
        LOGGER.info("Please choose a mode of work you need:");
        LOGGER.info("*************************************************");
        LOGGER.info("Press 1 to choose administrate mode");
        //LOGGER.info("Press 2 to choose clients mode");
        LOGGER.info("Press 3 to see information about program");
        LOGGER.info("Press 4 to EXIT from program\n");
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        if (!line.matches("[1-4]")) {
            System.out.println("Wrong input, please try again");
            start0();
        } else {
            switch (line) {
                case "1":
                    ckeckPassword();
                    LOGGER.info("----------------------------------------------------------");
                    LOGGER.info("Press 1 choose delete ticket");
                    LOGGER.info("Press 2 choose information about sold place");
                    LOGGER.info("Press 3 choose information about programm software");
                    LOGGER.info("Press 4 to back to main menu");
                    LOGGER.info("Press 5 to EXIT from program");

                    line = in.nextLine();
                    if (!line.matches("[1-5]")) {
                        System.out.println("Wrong input, please try again");
                        start0();
                    } else {
                        switch (line) {
                            case "1":
                                deleteTicket();
                                start0();
                            case "2":
                                infoSeatsSold();
                                start0();
                            case "3":
                                infoAboutSystem();
                                start0();
                                break;
                            case "4":
                                start0();
                            case "5":
                                LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                                System.exit(0);
                            default:
                                LOGGER.info("Incorrect option selected. Please try again.");
                                break;
                        }
                    }
                    break;
                case "2":
                    System.out.println("Client menu. Thanks for use our service.");
                    break;
                case "3":
                    infoAboutSystem();
                    start0();
                case "4":
                    LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                    System.exit(0);
                default:
                    LOGGER.info("Incorrect option selected. Please try again.");
                    break;
            }
        }


    }


    public static void infoSeatsSold() {
        TicketsDao ticketsDao = new TicketsDao();
        System.out.println(ticketsDao.getALLTickets());
        deleteTicket();

    }

    public static void deleteTicket() {
        LOGGER.info("Please, input number of ticket");
        TicketsDao ticketsDao = new TicketsDao();
        TicketsModel ticketsModel = ticketsDao.getTicketsById(1);
        ticketsDao.deleteTicketsById(ticketsModel);
    }

    public static void infoAboutSystem() {
        LOGGER.info("Ticketing system. Version 1.0. Written by a team of SOLVD's interns. May of 2022.");
    }

    public static void ckeckPassword() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter admin's name:");
            String userName = sc.nextLine();
            System.out.println("Enter password:");
            String password = sc.nextLine();
            if ("admin".equals(userName) && "12345".equals(password)) {
                LOGGER.info("Welkome, " + userName + "!");
                LOGGER.info("Please choose a mode of administrator you need:");
                break;
            } else {
                if(i == 2) {
                    LOGGER.info("You have exhausted all attempts.");
                    System.exit(0);
                }
                else{
                    LOGGER.info("Login or pasword is not correct. Try again.");
                }
            }
        }
        LOGGER.info("Login or pasword is not correct. Try again.");
    }


}