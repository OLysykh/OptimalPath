package com.solvd.optimalpath;

import com.solvd.optimalpath.dao.AirlinesDao;
import com.solvd.optimalpath.dao.AnimalsDao;
import com.solvd.optimalpath.dao.ClassTypesDao;
import com.solvd.optimalpath.dao.TicketsDao;
import com.solvd.optimalpath.models.AirlinesModel;
import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.TicketsModel;
import com.solvd.optimalpath.services.ClientMenu;

public class Main {
    public static void main(String[] args) {
        TicketsModel ticketsModel = new TicketsModel();
        TicketsDao ticketsDao = new TicketsDao();
        System.out.println(ticketsDao.getTicketsById(3));
        System.out.println(ticketsDao.getALLTickets());
//        AirlinesModel airlinesModel = new AirlinesModel();
//        AnimalsDao animalsDaoDao = new AnimalsDao();
//        System.out.println(animalsDaoDao.getAnimalsById(2));
//        ClassTypesDao classTypesDao = new ClassTypesDao();
//        System.out.println(classTypesDao.getClassTypesById(2));
        ClientMenu clientMenu = new ClientMenu();
        clientMenu.animalTicket();
    }


}
