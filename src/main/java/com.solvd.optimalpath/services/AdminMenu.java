package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.TicketsDao;
import com.solvd.optimalpath.dao.UserPassDao;
import com.solvd.optimalpath.models.TicketsModel;
import com.solvd.optimalpath.models.UserPassModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AdminMenu {
    private static final Logger LOGGER = LogManager.getLogger(AdminMenu.class);

    public static void supervisor() {
        while(true) {
            LOGGER.info("Please choose a mode of work you need:");
            LOGGER.info("*************************************************");
            LOGGER.info("Press 1 choose supervisor mode");
            LOGGER.info("Press 2 choose administrator of airport mode");
            LOGGER.info("Press 3 to EXIT from program");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (!line.matches("[1-3]")) {
                System.out.println("Wrong input, please try again");
                continue;
            } else {
                switch (line) {
                    case "1":
                        ckeckPasswordSupervisor();
                        System.out.println("Good!");
                        LOGGER.info("Please choose a mode of work you need:");
                        LOGGER.info("*************************************************");
                        LOGGER.info("Press 1 choose add new admin mode");
                        LOGGER.info("Press 2 choose administrator of airport mode");
                        LOGGER.info("Press 3 to EXIT from program");
                        Scanner in1 = new Scanner(System.in);
                        String line1 = in1.nextLine();

                        if (!line1.matches("[1-3]")) {
                            System.out.println("Wrong input, please try again");
                        } else {
                            switch (line1) {
                                case "1":
                                    adminAdd();
                                    supervisor();
                                case "2":
                                    administrator();
                                    break;
                                case "3":
                                    LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                                    System.exit(0);
                            }
                        }
                    case "2":
                        administrator();
                        break;
                    case "3":
                        LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                        System.exit(0);
                }
                continue;
            }
        }
    }

    public static void administrator() {
        ckeckPasswordAdmin();
        while(true) {
            LOGGER.info("Please choose a mode of work you need:");
            LOGGER.info("*************************************************");
            LOGGER.info("Press 1 choose information about sold place");
            LOGGER.info("Press 2 choose information about the amount for which the tickets were sold");
            LOGGER.info("Press 3 choose information about sold place of client");
            LOGGER.info("Press 4 choose delete ticket by client's last name");
            LOGGER.info("Press 5 choose information about programm software");
            LOGGER.info("Press 6 to EXIT from program");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (!line.matches("[1-6]")) {
                System.out.println("Wrong input, please try again");
                continue;
            } else {
                switch (line) {
                    case "1":
                        infoSeatsSold();
                        break;
                    case "2":
                        infoSeatsSoldSum();
                        break;
                    case "3":
                        LOGGER.info("Input last name of client");
                        Scanner in1 = new Scanner(System.in);
                        String line1;
                        line1 = in1.nextLine();
                        infoTicketClient(line1);
                        break;
                    case "4":
                        deleteTicketByLastName();
                        break;
                    case "5":
                        infoAboutSystem();
                        break;
                    case "6":
                        LOGGER.info("You have selected to exit our program! Thanks for visiting!");
                        System.exit(0);
                }
                continue;
            }
                }
            }

    public static void adminAdd() {
        UserPassDao userPassDao = new UserPassDao();

        LOGGER.info("Please, input new addministrator name");
        Scanner in = new Scanner(System.in);
        String adminName = in.nextLine();

        LOGGER.info("Please, input password of addministrator");
        Scanner in1 = new Scanner(System.in);
        String adminPass1 = in1.nextLine();

        LOGGER.info("Please, input password of addministrator again");
        Scanner in2 = new Scanner(System.in);
        String adminPass2 = in2.nextLine();

        if (adminPass1.equals(adminPass2)) {
            UserPassModel userPassModel = new UserPassModel(adminName, adminPass2);
            userPassDao.createUserPass(userPassModel);
        } else {
            LOGGER.info("Passwords are not equivalent. Please, try again");
            adminAdd();
        }
    }

    public static void infoSeatsSold() {
        TicketsDao ticketsDao = new TicketsDao();

        LOGGER.info("+-----------------+-------------+----------------+-------------------+-----------------------+");
        LOGGER.info(" Number of ticket" + "    First name " + "   Last name " + "      Number of passport " + " Where client will fly ");
        LOGGER.info("+-----------------+-------------+----------------+-------------------+-----------------------+");
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            LOGGER.info("  " + ticket.getId() + "                    " + ticket.getClientsModel().getFirstName() + "       " +
                    ticket.getClientsModel().getLastName() + "          " + ticket.getClientsModel().getPassportNum() +
                    "                " + ticket.getCitiesModel().getName());

//            LOGGER.info("Number of ticket is: " + ticket.getId() +
//                    " First name: " + ticket.getClientsModel().getFirstName() + " Last name: " +
//                    ticket.getClientsModel().getLastName() + " Number of passport: " + ticket.getClientsModel().getPassportNum() +
//                    " Where client will fly " + ticket.getCitiesModel().getName());
        }
        LOGGER.info("+-----------------+-------------+----------------+-------------------+-----------------------+");
    }

    public static void infoSeatsSoldSum() {
        TicketsDao ticketsDao = new TicketsDao();
        long sum = 0;
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            sum = sum + ticket.getPrice();
        }
        LOGGER.info("*************************************************");
        LOGGER.info("Tickets sold for " + sum +  " hryvnias;");
        LOGGER.info("*************************************************");
    }

    public static void infoTicketClient(String lastName) {
        TicketsDao ticketsDao = new TicketsDao();
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            if (lastName.equals(ticket.getClientsModel().getLastName())) {
                LOGGER.info("************************************************************" +
                        "****************************************************************");
                LOGGER.info("Number of ticket is: " + ticket.getId() +
                        " First name: " + ticket.getClientsModel().getFirstName() + " Last name: " +
                        ticket.getClientsModel().getLastName() + " Number of passport: " +
                        ticket.getClientsModel().getPassportNum() +
                        " Where client will fly " + ticket.getCitiesModel().getName());
                LOGGER.info("************************************************************" +
                        "****************************************************************");
            }
        }
    }

    public static void deleteTicketByLastName() {
        LOGGER.info("Please, input last name of client");
        Scanner in = new Scanner(System.in);
        String lastName = in.nextLine();
        TicketsDao ticketsDao = new TicketsDao();
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            if (lastName.equals(ticket.getClientsModel().getLastName())) {
                TicketsModel ticketsModel = ticketsDao.getTicketsById(ticket.getId());
                ticketsDao.deleteTicketsById(ticketsModel);
            }
        }
    }

    public static void infoAboutSystem() {
        LOGGER.info("**********************************************************************************");
        LOGGER.info("Ticketing system. Version 1.0. Written by a team of SOLVD's interns. May of 2022.");
        LOGGER.info("**********************************************************************************");
    }

    public static void ckeckPasswordAdmin() {
        UserPassDao userPassDao = new UserPassDao();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            LOGGER.info("Enter admin's name:");
            String user = sc.nextLine();
            LOGGER.info("Enter password:");
            String password = sc.nextLine();
            boolean isPassCorect = false;
            int index = 1;
            while(userPassDao.getUserPassById(index).getUser()!=null){
                if (user.equals(userPassDao.getUserPassById(index).getUser()) &&
                    password.equals(userPassDao.getUserPassById(index).getPass())) {
                    LOGGER.info("Welkome, " + user + "!");
                    isPassCorect = true;
                    break;
                }
                index++;
            }  if(isPassCorect) {
                break;
            } if(i==2) {
                LOGGER.info("You haven't more oportunity!");
                System.exit(0);
            } else {
                LOGGER.info("Name or password isn't correct. You have " + (2-i) + " oportunity. Try again.");
            }

        }
    }

    public static void ckeckPasswordSupervisor() {
        UserPassDao userPassDao = new UserPassDao();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            LOGGER.info("Enter password:");
            String password = sc.nextLine();
            boolean isPassCorect = false;
            int index = 1;
            while(userPassDao.getUserPassById(index).getUser()!=null){
                if ("supervisor".equals(userPassDao.getUserPassById(index).getUser()) &&
                        password.equals(userPassDao.getUserPassById(index).getPass())) {
                    LOGGER.info("Welkome, supervisor !");
                    isPassCorect = true;
                    break;
                }
                index++;
            }  if(isPassCorect) {
                break;
            } if(i==2) {
                LOGGER.info("You haven't more oportunity!");
                System.exit(0);
            } else {
                LOGGER.info("Name or password isn't correct. You have " + (2-i) + " oportunity. Try again.");
            }
        }
    }
}