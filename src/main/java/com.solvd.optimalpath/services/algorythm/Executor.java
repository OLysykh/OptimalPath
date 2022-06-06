package com.solvd.optimalpath.services.algorythm;

import com.solvd.optimalpath.services.ClientMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {


    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(Executor.class);


//        Weather.getWeather();//this feature is in production!
         ClientMenu.start();

    }
}
