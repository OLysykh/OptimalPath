package com.solvd.optimalpath.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Menu {
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);

    public static void start() {
        LOGGER.info("Welcome! Choose a city in which you want to fly:");
        //getAllCities();
        Scanner in = new Scanner(System.in);
    }
}
