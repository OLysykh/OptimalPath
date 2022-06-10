package com.solvd.optimalpath.services;

import com.solvd.optimalpath.dao.TicketsDao;
import com.solvd.optimalpath.dao.UserPassDao;
import com.solvd.optimalpath.models.TicketsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AdminMenu {
    private static final Logger LOGGER = LogManager.getLogger(AdminMenu.class);

    public static void start0() {
        ckeckPassword();
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

    public static void infoSeatsSold() {
        TicketsDao ticketsDao = new TicketsDao();
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            LOGGER.info("Number of ticket is: " + ticket.getId() +
                    " First name: " + ticket.getClientsModel().getFirstName() + " Last name: " +
                    ticket.getClientsModel().getLastName() + " Number of passport: " + ticket.getClientsModel().getPassportNum() +
                    " Where client will fly " + ticket.getCitiesModel().getName());
        }
    }

    public static void infoSeatsSoldSum() {
        TicketsDao ticketsDao = new TicketsDao();
        long sum = 0;
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            sum = sum + ticket.getPrice();
        }
        LOGGER.info("Tickets sold for " + sum +  " hryvnias;");
    }

    public static void infoTicketClient(String lastName) {
        TicketsDao ticketsDao = new TicketsDao();
        for (TicketsModel ticket : ticketsDao.getALLTickets()) {
            if (lastName.equals(ticket.getClientsModel().getLastName())) {
                LOGGER.info("Number of ticket is: " + ticket.getId() +
                        " First name: " + ticket.getClientsModel().getFirstName() + " Last name: " +
                        ticket.getClientsModel().getLastName() + " Number of passport: " + ticket.getClientsModel().getPassportNum() +
                        " Where client will fly " + ticket.getCitiesModel().getName());
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
        LOGGER.info("Ticketing system. Version 1.0. Written by a team of SOLVD's interns. May of 2022.");
    }

    public static void ckeckPassword() {
        UserPassDao userPassDao = new UserPassDao();
        LOGGER.info(userPassDao.getUserPassById(6));
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            LOGGER.info("Enter admin's name:");
            String userName = sc.nextLine();
            LOGGER.info("Enter password:");
            String password = sc.nextLine();
            boolean isPassCorect = false;
            int index = 1;
            while(userPassDao.getUserPassById(index).getPass() != null){
                if (password.equals(userPassDao.getUserPassById(index).getPass())) {
                    LOGGER.info("Welkome, " + userName + "!");
                    isPassCorect = true;
                    break;
                }
                index++;
            }   if(isPassCorect) {
                break;
            } if(i==2) {
                LOGGER.info("You haven't more oportunity!");
                System.exit(0);
            } else {
                LOGGER.info("Name or password isn't correct. You have three oportunity. Try again.");

            }

        }
    }
}