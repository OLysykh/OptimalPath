package com.solvd.optimalpath.services.algorythm;

import com.solvd.optimalpath.dao.CitiesDao;
import com.solvd.optimalpath.models.CitiesModel;
import com.solvd.optimalpath.services.ClientMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Executor {


    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(Executor.class);


//        WeatherMethods.createCityRequest(50.0755, 14.4378);//this feature is in production!
        ClientMenu.start();
    }


}
